package kbvs.converter;

import com.panda.kbvs.ResourceInfo;
import kbvs.util.DocumentUtil;
import kbvs.util.LuceneUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 使用lucene 来操作索引库...
 * Created by c-kx on 2016/5/9.
 */
public class LuceneServices {
    /**
     * 增删改索引都是通过indexWriter 对象来完成...
     * @throws java.io.IOException
     */
    public void addIndex(ResourceInfo resourceInfo) throws IOException {
        IndexWriter indexWriter= LuceneUtils.getIndexWriter();
        Document doc= DocumentUtil.resToDocument(resourceInfo);
        indexWriter.addDocument(doc);
        indexWriter.close();
    }


    /**
     * 删除索引,根据id删
     * @param resourceInfo
     * @throws java.io.IOException
     */
    public void delIndex(ResourceInfo resourceInfo) throws IOException{
        IndexWriter indexWriter= LuceneUtils.getIndexWriter();
        Term term=new Term("id", resourceInfo.getId());
        //delete from table where condition
        indexWriter.deleteDocuments(term);

        indexWriter.close();

    }


    /**
     * 先删除符合条件的记录，再创建一个符合条件的记录....
     * @param resourceInfo
     * @throws java.io.IOException
     */
    public void updateIndex(ResourceInfo resourceInfo) throws IOException{
        IndexWriter indexWriter= LuceneUtils.getIndexWriter();
        Term term=new Term("id", resourceInfo.getId());
        Document doc= DocumentUtil.resToDocument(resourceInfo);
        indexWriter.updateDocument(term, doc);
        indexWriter.close();
    }

    /**
     * 此方法用检索文件
     * @param keywords：搜索关键词
     * @param userId：当前用户Id
     * @param type:搜索的范围，如果搜全部就为null或“”
     * @param start：从第几条开始
     * @param rows；每次取多少
     * @return resourceInfoList:文件列表
     * @throws Exception
     */
    public List<ResourceInfo> findIndex(String keywords,String userId,String type,int start,int rows) throws Exception{
        long startTime = new Date().getTime();
        IndexSearcher indexSearcher= LuceneUtils.getIndexSearcher();
        //封装查询条件(使用BooleanQuery对象，连接多个查询条件)
        BooleanQuery booleanQueryM = new BooleanQuery();
        //查询当前登录用户的文件
        TermQuery termQuery1 = new TermQuery(new Term("userId", userId));
        BooleanQuery booleanQuery1 = new BooleanQuery();
        booleanQuery1.add(new TermQuery(new Term("artist",keywords)), Occur.SHOULD);
        booleanQuery1.add(new TermQuery(new Term("author",keywords)), Occur.SHOULD);
        booleanQuery1.add(new TermQuery(new Term("extName",keywords)), Occur.SHOULD);
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"content","name"}, LuceneUtils.getAnalyzer());
        Query query = queryParser.parse(keywords);
        booleanQuery1.add(query, Occur.SHOULD);

        if(type==null||type.equals("")){
            booleanQueryM.add(termQuery1, Occur.MUST);
            booleanQueryM.add(booleanQuery1, Occur.MUST);
        }else{
            BooleanQuery booleanQuery2 = new BooleanQuery();
            TermQuery termQuery2 = new TermQuery(new Term("type", type));
            booleanQuery2.add(termQuery1, Occur.MUST);
            booleanQuery2.add(termQuery2, Occur.MUST);
            booleanQueryM.add(booleanQuery2, Occur.MUST);
            booleanQueryM.add(booleanQuery1, Occur.MUST);
        }
        //检索符合booleanQueryM前面N条记录...
        TopDocs topDocs=indexSearcher.search(booleanQueryM, start+rows);
        System.out.println("总记录数==total=="+topDocs.totalHits);
        ScoreDoc scoreDocs []=topDocs.scoreDocs;

        Formatter formatter=new SimpleHTMLFormatter("<font color='red'>", "</font>");


        //query 里面条件，条件里面有搜索关键字
        QueryScorer fragmentScorer=new QueryScorer(booleanQueryM);

        //构造高亮气...
        /**
         * 1:我要高亮成什么颜色
         * 2：我要将那些关键字进行高亮...
         *
         */
        Highlighter highlighter=new Highlighter(formatter, fragmentScorer);
        /*
        * 设置高亮后的字符长度
        * */
        highlighter.setTextFragmenter(new SimpleFragmenter(100));

        ResourceInfo resourceInfo=null;
        List<ResourceInfo>resourceInfoList=new ArrayList<ResourceInfo>();
        int endResult=Math.min(scoreDocs.length, start+rows);
        for(int i=start;i<endResult;i++){


            //docID lucene 的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识.. 自增长
            int docID=scoreDocs[i].doc;
            //System.out.println("编号的标识==="+docID+"得分=="+scoreDocs[i].score);
            Document document=indexSearcher.doc(docID);

            // 给想要的字段设置高亮
            String name=document.get("name");
            String content=document.get("content");
            String artist=document.get("artist");
            String author=document.get("author");

            String hName=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "name", name);
            String hContent=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "content", content);
            String hArtist=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "artist", artist);
            String hAuthor=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "author", author);
            resourceInfo=new ResourceInfo();

            resourceInfo= DocumentUtil.documentToRes(document);
            String reAuthor="";
            String reContent="";
            if(hName!=null){
                resourceInfo.setName(hName);
            }

            if(hContent!=null){
                resourceInfo.setContent(hContent);
                reContent="内容:"+hContent;
            }else{
                reContent="内容:"+resourceInfo.getContent()+" ";
            }
            if(hArtist!=null){
                resourceInfo.setArtist(hArtist);
            }
            if(hAuthor!=null){
                resourceInfo.setAuthor(hAuthor);
                reAuthor="作者:"+hAuthor+" ";
            }else{
                reAuthor="作者:"+resourceInfo.getAuthor()+" ";
            }
            if(resourceInfo.getType()!=null&&resourceInfo.getType().equals("word")){
                resourceInfo.setRemark(reAuthor+reContent);
            }

            resourceInfoList.add(resourceInfo);
        }
        long endTime = new Date().getTime();
        System.out.println("这花费了" + (endTime - startTime) + " 毫秒来检索出来文件!");
        return resourceInfoList;
    }



    public List<ResourceInfo> findIndex1(String keywords,String userId,String type,int start,int rows) throws Exception{
        long startTime = new Date().getTime();
        IndexSearcher indexSearcher= LuceneUtils.getIndexSearcher();
        //封装查询条件(使用BooleanQuery对象，连接多个查询条件)
        BooleanQuery booleanQueryM = new BooleanQuery();
        //查询当前登录用户的文件
        TermQuery termQuery1 = new TermQuery(new Term("userId", userId));
        BooleanQuery booleanQuery1 = new BooleanQuery();
        booleanQuery1.add(new TermQuery(new Term("artist",keywords)), Occur.SHOULD);
        booleanQuery1.add(new TermQuery(new Term("author",keywords)), Occur.SHOULD);
        booleanQuery1.add(new TermQuery(new Term("extName",keywords)), Occur.SHOULD);
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"content","name"}, LuceneUtils.getAnalyzer());
        Query query = queryParser.parse(keywords);
        booleanQuery1.add(query, Occur.SHOULD);

            BooleanQuery booleanQuery2 = new BooleanQuery();
            TermQuery termQuery2 = new TermQuery(new Term("common", type));
            booleanQuery2.add(termQuery1, Occur.MUST);
            booleanQuery2.add(termQuery2, Occur.MUST);
            booleanQueryM.add(booleanQuery2, Occur.MUST);
            booleanQueryM.add(booleanQuery1, Occur.MUST);

        //检索符合booleanQueryM前面N条记录...
        TopDocs topDocs=indexSearcher.search(booleanQueryM, start+rows);
        System.out.println("总记录数==total=="+topDocs.totalHits);
        ScoreDoc scoreDocs []=topDocs.scoreDocs;

        Formatter formatter=new SimpleHTMLFormatter("<font color='red'>", "</font>");


        //query 里面条件，条件里面有搜索关键字
        QueryScorer fragmentScorer=new QueryScorer(booleanQueryM);

        //构造高亮气...
        /**
         * 1:我要高亮成什么颜色
         * 2：我要将那些关键字进行高亮...
         *
         */
        Highlighter highlighter=new Highlighter(formatter, fragmentScorer);
        /*
        * 设置高亮后的字符长度
        * */
        highlighter.setTextFragmenter(new SimpleFragmenter(100));

        ResourceInfo resourceInfo=null;
        List<ResourceInfo>resourceInfoList=new ArrayList<ResourceInfo>();
        int endResult=Math.min(scoreDocs.length, start+rows);
        for(int i=start;i<endResult;i++){
            //docID lucene 的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识.. 自增长
            int docID=scoreDocs[i].doc;
            //System.out.println("编号的标识==="+docID+"得分=="+scoreDocs[i].score);
            Document document=indexSearcher.doc(docID);

            // 给想要的字段设置高亮
            String name=document.get("name");
            String content=document.get("content");
            String artist=document.get("artist");
            String author=document.get("author");

            String hName=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "name", name);
            String hContent=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "content", content);
            String hArtist=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "artist", artist);
            String hAuthor=highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "author", author);
            resourceInfo=new ResourceInfo();

            resourceInfo= DocumentUtil.documentToRes(document);
            String reAuthor="";
            String reContent="";
            if(hName!=null){
                resourceInfo.setName(hName);
            }

            if(hContent!=null){
                resourceInfo.setContent(hContent);
                reContent="内容:"+hContent;
            }else{
                reContent="内容:"+resourceInfo.getContent()+" ";
            }
            if(hArtist!=null){
                resourceInfo.setArtist(hArtist);
            }
            if(hAuthor!=null){
                resourceInfo.setAuthor(hAuthor);
                reAuthor="作者:"+hAuthor+" ";
            }else{
                reAuthor="作者:"+resourceInfo.getAuthor()+" ";
            }
            if(resourceInfo.getType()!=null&&resourceInfo.getType().equals("word")){
                resourceInfo.setRemark(reAuthor+reContent);
            }
            resourceInfoList.add(resourceInfo);
        }
        long endTime = new Date().getTime();
        System.out.println("这花费了" + (endTime - startTime) + " 毫秒来检索出来文件!");
        return resourceInfoList;
    }
    /**
     * 此方法用来显示与该文件相关的文件
     * @param resourceInfo：文件实体
     * @param userId：当前用户Id
     * @param type:搜索的范围，如果搜全部就为null或“”
     * @param start：从第几条开始
     * @param rows；每次取多少
     * @return resourceInfoList:文件列表
     * @throws Exception
     */
    public List<ResourceInfo> findIndex(ResourceInfo resourceInfo,String userId,String type,int start,int rows) throws Exception{
        IndexSearcher indexSearcher= LuceneUtils.getIndexSearcher();
        //封装查询条件(使用BooleanQuery对象，连接多个查询条件)
        BooleanQuery booleanQueryM = new BooleanQuery();
        //查询当前登录用户的文件
        TermQuery termQuery1 = new TermQuery(new Term("userId", userId));
        BooleanQuery booleanQuery1 = new BooleanQuery();
        booleanQuery1.add(new TermQuery(new Term("author",resourceInfo.getAuthor())), Occur.SHOULD);
        booleanQuery1.add(new TermQuery(new Term("name",resourceInfo.getName())), Occur.SHOULD);
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"name"}, LuceneUtils.getAnalyzer());
        Query query = queryParser.parse(resourceInfo.getName());
        booleanQuery1.add(query, Occur.SHOULD);

        booleanQueryM.add(termQuery1, Occur.MUST);
        booleanQueryM.add(booleanQuery1, Occur.MUST);
        //检索符合booleanQueryM前面N条记录...
        TopDocs topDocs=indexSearcher.search(booleanQueryM, start+rows);
        System.out.println("总记录数==total=="+topDocs.totalHits);
        ScoreDoc scoreDocs []=topDocs.scoreDocs;
        List<ResourceInfo>resourceInfoList=new ArrayList<ResourceInfo>();
        int endResult=Math.min(scoreDocs.length, start+rows);
        for(int i=start;i<endResult;i++){
            //docID lucene 的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识.. 自增长
            int docID=scoreDocs[i].doc;
           // System.out.println("编号的标识==="+docID+"得分=="+scoreDocs[i].score);
            Document document=indexSearcher.doc(docID);
            resourceInfo= DocumentUtil.documentToRes(document);
            resourceInfoList.add(resourceInfo);
        }

        return resourceInfoList;
    }


    public List<String> findIndex(String resName,String userId) throws Exception{
        IndexSearcher indexSearcher= LuceneUtils.getIndexSearcher();
        //封装查询条件(使用BooleanQuery对象，连接多个查询条件)
        BooleanQuery booleanQueryM = new BooleanQuery();
        //查询当前登录用户的文件
        TermQuery termQuery1 = new TermQuery(new Term("userId", userId));

//        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"name"}, LuceneUtils.getAnalyzer());
//        Query query = queryParser.parse(resName);
        booleanQueryM.add(termQuery1, Occur.MUST);
        booleanQueryM.add(new TermQuery(new Term("name",resName)), Occur.MUST);
        //检索符合booleanQueryM前面N条记录...
        TopDocs topDocs=indexSearcher.search(booleanQueryM, 1000000);
        System.out.println("总记录数==total=="+topDocs.totalHits);
        ScoreDoc scoreDocs []=topDocs.scoreDocs;
        List<String>resourceIdList=new ArrayList<String >();
        for(int i=0;i<scoreDocs.length;i++){
            //docID lucene 的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识.. 自增长
            int docID=scoreDocs[i].doc;
           // System.out.println("编号的标识==="+docID+"得分=="+scoreDocs[i].score);
            Document document=indexSearcher.doc(docID);
            String  resourceInfoId= DocumentUtil.documentToRes(document).getId();
            resourceIdList.add(resourceInfoId);
        }

        return resourceIdList;
    }

    public List<String> findIndexC(String resContent,String userId) throws Exception{
        IndexSearcher indexSearcher= LuceneUtils.getIndexSearcher();
        //封装查询条件(使用BooleanQuery对象，连接多个查询条件)
        BooleanQuery booleanQueryM = new BooleanQuery();
        //查询当前登录用户的文件
        TermQuery termQuery1 = new TermQuery(new Term("userId", userId));

//        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"content"}, LuceneUtils.getAnalyzer());
//        Query query = queryParser.parse(resContent);
        booleanQueryM.add(termQuery1, Occur.MUST);
        booleanQueryM.add(new TermQuery(new Term("content",resContent)), Occur.MUST);
        //检索符合booleanQueryM前面N条记录...
        TopDocs topDocs=indexSearcher.search(booleanQueryM, 1000000);
        System.out.println("总记录数==total=="+topDocs.totalHits);
        ScoreDoc scoreDocs []=topDocs.scoreDocs;
        List<String>resourceIdList=new ArrayList<String >();
        for(int i=0;i<scoreDocs.length;i++){
            //docID lucene 的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识.. 自增长
            int docID=scoreDocs[i].doc;
            // System.out.println("编号的标识==="+docID+"得分=="+scoreDocs[i].score);
            Document document=indexSearcher.doc(docID);
            String  resourceInfoId= DocumentUtil.documentToRes(document).getId();
            resourceIdList.add(resourceInfoId);
        }

        return resourceIdList;
    }

    public List<String> findIndex(String resName ,String resContent,String userId) throws Exception{
        IndexSearcher indexSearcher= LuceneUtils.getIndexSearcher();
        //封装查询条件(使用BooleanQuery对象，连接多个查询条件)
        BooleanQuery booleanQueryM = new BooleanQuery();
        //查询当前登录用户的文件
        TermQuery termQuery1 = new TermQuery(new Term("userId", userId));

//        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"name"}, LuceneUtils.getAnalyzer());
//        Query query = queryParser.parse(resName);
//
//        QueryParser queryParser1 = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),new String[]{"content"}, LuceneUtils.getAnalyzer());
//        Query query1 = queryParser.parse(resContent);

        booleanQueryM.add(termQuery1, Occur.MUST);
        booleanQueryM.add(new TermQuery(new Term("name",resName)), Occur.MUST);
        booleanQueryM.add(new TermQuery(new Term("content",resContent)), Occur.MUST);
        //检索符合booleanQueryM前面N条记录...
        TopDocs topDocs=indexSearcher.search(booleanQueryM, 1000000);
        System.out.println("总记录数==total=="+topDocs.totalHits);
        ScoreDoc scoreDocs []=topDocs.scoreDocs;
        List<String>resourceIdList=new ArrayList<String >();
        for(int i=0;i<scoreDocs.length;i++){
            //docID lucene 的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识.. 自增长
            int docID=scoreDocs[i].doc;
            //System.out.println("编号的标识==="+docID+"得分=="+scoreDocs[i].score);
            Document document=indexSearcher.doc(docID);
            String  resourceInfoId= DocumentUtil.documentToRes(document).getId();
            resourceIdList.add(resourceInfoId);
        }

        return resourceIdList;
    }


}
