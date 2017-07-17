package kbvs.util;

import com.panda.kbvs.ResourceInfo;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by c-kx on 2016/5/8.
 */
public class DocumentUtil {
    /*
    * 把document转换成resourceInfo
    * */
     private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     public static ResourceInfo documentToRes(Document doc) throws ParseException {
        ResourceInfo resourceInfo=new ResourceInfo();
        resourceInfo.setContent(doc.get("content"));
        resourceInfo .setMetadata(doc.get("metadata"));
        resourceInfo.setName(doc.get("name"));
        resourceInfo.setArtist(doc.get("artist"));
        resourceInfo.setAuthor(doc.get("author"));
        resourceInfo.setExtName(doc.get("extName"));
        resourceInfo.setId(doc.get("id"));
        resourceInfo.setType(doc.get("type"));
        resourceInfo.setConvertName(doc.get("convertName"));
        String s=doc.get("dateCreated");


         Date taTime;
         try {
             taTime = sdf.parse(s);
         } catch (Exception e) {
             taTime=new Date();
         }

        resourceInfo.setDateCreated(taTime);
        resourceInfo.setCreationDate(doc.get("creationDate"));
        resourceInfo.setUserId(doc.get("userId"));
        resourceInfo.setOwnerId(doc.get("ownerId"));
        resourceInfo.setFileSize(doc.get("fileSize"));
        resourceInfo.setPreImgPath(doc.get("preImgPath"));
        resourceInfo.setRemark(doc.get("remark"));
         resourceInfo.setCommon(doc.get("common"));
         resourceInfo.setPath(doc.get("path"));
        return resourceInfo;
    }

    public static Document resToDocument(ResourceInfo resourceInfo) {
        Document doc = new Document();
        //Index.ANALYZED分词后更新到索引的目录区域
        doc.add(new Field("content",resourceInfo.getContent(),Store.YES,Index.ANALYZED));
        doc.add(new Field("metadata",resourceInfo.getAuthor().toString(),Store.YES,Index.ANALYZED));
        doc.add(new Field("name",resourceInfo.getName(),Store.YES,Index.ANALYZED));
        //Index.NOT_ANALYZED不分词，直接更新到索引的目录区域
        doc.add(new Field("artist",resourceInfo.getArtist(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("author",resourceInfo.getAuthor().toString(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("extName",resourceInfo.getExtName(),Store.YES,Index.NOT_ANALYZED));

        doc.add(new Field("id",resourceInfo.getId(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("type",resourceInfo.getType(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("convertName",resourceInfo.getConvertName(),Store.YES,Index.NOT_ANALYZED));
       // doc.add(new Field("dateCreated",resourceInfo.getDateCreated().toString(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("creationDate",resourceInfo.getCreationDate().toString(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("userId",resourceInfo.getUserId(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("ownerId",resourceInfo.getOwnerId(),Store.YES,Index.NOT_ANALYZED));
        doc.add(new Field("common",resourceInfo.getCommon(),Store.YES,Index.NOT_ANALYZED));
        String dateCreated=sdf.format(resourceInfo.getDateCreated());
        doc.add(new Field("dateCreated",dateCreated,Store.YES,Index.NOT_ANALYZED));

        //Index.NO不更新索引的目录区域(无法通过该字段搜索)
        doc.add(new Field("fileSize",resourceInfo.getFileSize(),Store.YES,Index.NO));
        doc.add(new Field("preImgPath",resourceInfo.getPreImgPath(),Store.YES,Index.NO));
        doc.add(new Field("remark",resourceInfo.getRemark(),Store.YES,Index.NO));

        doc.add(new Field("path",resourceInfo.getPath(),Store.YES,Index.NO));
        return doc;
    }
}
