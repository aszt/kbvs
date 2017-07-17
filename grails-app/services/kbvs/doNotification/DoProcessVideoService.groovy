package kbvs.doNotification

import com.panda.kbvs.ResourceInfo
import kbvs.converter.LuceneServices
import kbvs.converter.VideoConverter
import kbvs.util.FileUtils
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.Parser
import org.apache.tika.sax.BodyContentHandler

class DoProcessVideoService {

    boolean transactional = false
    static exposes = ['jms']
    static destination = "queue.processVideo"

    def onMessage(msg) {
        String[] message=msg.split(",");
        ResourceInfo resourceInfo=new ResourceInfo()
        try {
            resourceInfo = ResourceInfo.findById("${message[0]}")
            print "要处理的文件是：${resourceInfo.name}"
            /*
            * 拿到上传的文件使用tika解析获得源数据
            * */
            def file=new File("${message[1]}"+resourceInfo.getPath())
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler(100000000);
            Metadata metadata = new Metadata();
            InputStream inputstream = new FileInputStream(file);
            ParseContext context = new ParseContext();
            parser.parse(inputstream, handler, metadata, context);
            String[] metadataNames = metadata.names();
            String tabloid,cjsj=""
            String re=" "
            for (String name : metadataNames) {
                //得到文件创建时间
                if (name.equals("Creation-Date")||name.equals("Date/Time Digitized")||name.equals("Date/Time Original")||name.equals("Date/Time")||name.equals("Last-Save-Date")||name.equals("xmpDM:releaseDate")){
                    cjsj="${metadata.get(name)}"
                    resourceInfo.creationDate="${metadata.get(name)}"
                }
                //得到作者
                if (name.equals("Author")||name.equals("creator")||name.equals("Last-Author")){
                    resourceInfo.author="${metadata.get(name)}"
                }
                if (!name.equals("xmpDM:logComment")&&!name.equals("163 key(Don't modify)")&&!name.equals("X-Parsed-By)")&&!name.equals("xmpDM:duration")){
                    tabloid=tabloid+metadata.get(name).trim()+","
                }
            }
            inputstream.close()
            resourceInfo.metadata=tabloid
            /*
            * 处理视屏，对视频进行截图
            * */
            VideoConverter videoConverter=new VideoConverter();
            String preImgPath= videoConverter.videoCapture("${message[1]}"+resourceInfo.getPath())
            def duration=videoConverter.getDuration("${message[1]}"+resourceInfo.getPath())
            resourceInfo.duration=duration
            resourceInfo.preImgPath=FileUtils.getRelPath(FileUtils.getFilePrefix(preImgPath)+".jpg")
            if(cjsj!=null&&!cjsj.equals("")){
                re="创建时间:"+cjsj
            }
            if(duration!=null&&!duration.equals()){
                re=re+" 时长:${duration}"
            }
            resourceInfo.remark=re
            resourceInfo.save(flush: true)
            /*
            *调用luncene分词
            * */
            LuceneServices luceneServices=new LuceneServices()
            luceneServices.addIndex(resourceInfo)
        }catch (Exception e){
            println e.printStackTrace()
            println"处理${resourceInfo.id}出错了！"
        }
        println "处理完毕"
    }
}
