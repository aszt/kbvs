package kbvs.doNotification

import com.panda.kbvs.ResourceInfo
import kbvs.converter.*
import kbvs.util.FileUtils
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.Parser
import org.apache.tika.sax.BodyContentHandler

class DoProcessTestService {

    boolean transactional = false//事务
    static exposes = ['jms']//暴露Java Message Service
    static destination = "queue.processText"//目的地

    def onMessage(msg) {
        String[] message=msg.split(",");
        ResourceInfo resourceInfo=null
        String filePath=""
        try {
            resourceInfo=new ResourceInfo()
            resourceInfo = ResourceInfo.findById("${message[0]}")
            print "要处理的文件是：${resourceInfo.name}"
            /*
            * 拿到上传的文件使用tika解析获得源数据
            * */

            //文件绝对路径（带后缀名）
            filePath="${message[1]}"+resourceInfo.getPath()
            def file=new File(filePath)
            //要使用解析器接口的parse()方法，实例化任何为其提供实现这个接口的类
            Parser parser = new AutoDetectParser();
            //创建一个处理类的对象
            BodyContentHandler handler = new BodyContentHandler(1000000000);
            //创建的元数据对象
            Metadata metadata = new Metadata();
            InputStream inputstream = new FileInputStream(file);
            //创建一个解析的上下文对象
            ParseContext context = new ParseContext();
            //实例化解析器对象，调用parse方法，并通过所有需要的对象
            parser.parse(inputstream, handler, metadata, context);
            //System.out.println(handler.toString());
            String[] metadataNames = metadata.names();
            String tabloid,content=""
            String remark=" "
            for (String name : metadataNames) {
                //得到文件创建时间
                if (name.equals("Creation-Date")||name.equals("Date/Time Digitized")||name.equals("Date/Time Original")||name.equals("Date/Time")||name.equals("Last-Save-Date")||name.equals("xmpDM:releaseDate")){
                    resourceInfo.creationDate="${metadata.get(name)}"
                }
                //得到编码
                if (name.equals("Encoding")||name.equals("Content-Encoding")){
                    resourceInfo.encoding="${metadata.get(name)}"
                }
                //得到作者

                if (name.equals("Author")||name.equals("creator")||name.equals("Last-Author")){
                    resourceInfo.author="${metadata.get(name)}"
                    remark="作者：${metadata.get(name)} "
                }
                if (!name.equals("xmpDM:logComment")&&!name.equals("163 key(Don't modify)")&&!name.equals("X-Parsed-By)")&&!name.equals("xmpDM:duration")){
                    tabloid=tabloid+metadata.get(name).trim()+","
                }
            }
            content=handler.toString()
            inputstream.close()
            if(content.length()>100){
                remark=remark+content.substring(0,100)
            }else{
                remark=remark+content
            }
            resourceInfo.remark=remark
            resourceInfo.content=content

            //元数据
            resourceInfo.metadata=tabloid
            if (resourceInfo.extName.equals("pdf")){
                PDFPageCapture pdfPageCapture=new PDFPageCapture()
                pdfPageCapture.capturePages(filePath,FileUtils.getFilePath(filePath))
                println"DoProcessTestService中得到文件路径：${FileUtils.getFilePath(filePath)}"

                resourceInfo.preImgPath=FileUtils.getRelPath(FileUtils.getFilePrefix(filePath)+".jpg")
                println"截图路径${FileUtils.getRelPath(FileUtils.getRelPath(FileUtils.getFilePrefix(filePath)+".jpg"))}"
                resourceInfo.pdfPath=resourceInfo.path
//                ISWFConverter swfConverter=new SWFConverter()
//                String swfPath=swfConverter.convert2SWF(filePath)
//                resourceInfo.swfPath=swfPath
//                println"转换后SWF路径${swfPath}"
            }else {
                IPDFConverter pdfConverter=new OpenOfficePDFConverter()
                String pdfPath= pdfConverter.convert2PDF(filePath)
                resourceInfo.pdfPath=pdfPath
                println"转换后pdf路径${pdfPath}"
                PDFPageCapture pdfPageCapture=new PDFPageCapture()
                pdfPageCapture.capturePages("${message[1]}${pdfPath}",FileUtils.getFilePath(filePath))
                resourceInfo.preImgPath=FileUtils.getRelPath(FileUtils.getFilePrefix(filePath)+".jpg")
//                ISWFConverter swfConverter=new SWFConverter()
//                String swfPath=swfConverter.convert2SWF("${message[1]}${pdfPath}")
//                resourceInfo.swfPath=swfPath
//                println"转换后SWF路径${swfPath}"
            }

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
