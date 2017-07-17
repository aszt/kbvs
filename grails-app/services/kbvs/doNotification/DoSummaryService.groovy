package kbvs.doNotification

import com.panda.kbvs.ResourceInfo
import kbvs.converter.LuceneServices
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.Parser
import org.apache.tika.sax.BodyContentHandler

class DoSummaryService {

    boolean transactional = false
    static exposes = ['jms']
    static destination = "queue.summary"

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
            //System.out.println(handler.toString());
            String[] metadataNames = metadata.names();
            String tabloid=""
            String zuozhe="";
            String yishujia="";
            String liupai="";
            String zhuanji=""
            String cjsj=""
            String make=""
            for (String name : metadataNames) {
                //得到文件创建时间
                if (name.equals("Creation-Date")||name.equals("Date/Time Digitized")||name.equals("Date/Time Original")||name.equals("Date/Time")||name.equals("Last-Save-Date")||name.equals("xmpDM:releaseDate")){
                    resourceInfo.creationDate="${metadata.get(name)}"
                    cjsj="创建时间:${metadata.get(name)}"
                }
                //得到作者
                if (name.equals("Author")||name.equals("creator")||name.equals("Last-Author")){
                    resourceInfo.author="${metadata.get(name)}"
                    zuozhe="作者：${metadata.get(name)} "
                }
                //得到音乐艺术家
                if (name.equals("xmpDM:artist")){
                    resourceInfo.artist="${metadata.get(name)}"
                    yishujia="艺术家:${metadata.get(name)} "
                }
                //得到音乐流派
                if (name.equals("xmpDM:genre")){
                    resourceInfo.genre="${metadata.get(name)}"
                    liupai="流派：${metadata.get(name)} "
                }
                //得到音乐专辑
                if (name.equals("xmpDM:album")){
                    resourceInfo.special="${metadata.get(name)}"
                    zhuanji="专辑：${metadata.get(name)} "
                }
                if (name.equals("tiff:Make")){
                    resourceInfo.equipment="${metadata.get(name)}"
                    make="设备：${metadata.get(name)} "
                }
                if (!name.equals("xmpDM:logComment")&&!name.equals("163 key(Don't modify)")&&!name.equals("X-Parsed-By)")&&!name.equals("xmpDM:duration")){
                    tabloid=tabloid+metadata.get(name).trim()+","
                }
            }
            inputstream.close()
            def duration=""
            if(resourceInfo.duration!=null&&!resourceInfo.duration.equals("")){
                duration="时长:"+resourceInfo.duration
            }
            resourceInfo.metadata=tabloid
            resourceInfo.remark=" "+zuozhe+yishujia+liupai+zhuanji+duration+make+cjsj
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
