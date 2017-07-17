package com.panda.kbvs
/**
 * 文件
 */
class ResourceInfo {

    String id
    String type //文档类型:文档、视频、图片、其它
    String name //上传文件名
    String extName  //原始文件扩展名
    String convertName  //上传文件改名
    String path    //文件路径
    String metadata //元数据
    Date dateCreated    //上传时间
    String MD5  //MD5(用来比对文件是否存在)
    String fileSize //文件大小
    String creationDate //文件创建的时间
    String userId   //用户Id
    String ownerId  //所有者Id(记录该文件的上一级)
    String relation //关联
    String remark//摘要
    String common//常用


    //图片、视频类型
    String preImgPath   //预览图
    String equipment//设备

    //音频类型
    String artist   //艺术家
    String special  //专辑
    String genre    //流派
    String duration//时长

    //文本类型
    String pdfPath  //转换pdf路径
    String swfPath  //转换swf路径
    String author   //文件创建的作者
    String encoding //文件使用的编码
    String content  //文本全部内容
    int isSwfDone = 0   //0 代表未转换，1代表正在转换，2代表转换完成,3表示转换失败
    int isPdfDone = 0   //0 代表未转换，1代表正在转换，2代表转换完成,3表示转换失败


    static mapping = {
        id generator: 'uuid.hex'
    }

    static constraints = {
        equipment(nullable: true,blank: true)
        relation(nullable: true)
        remark(nullable: true,blank: true)
        common(nullable: true,blank: true)
//        type(nullable: true)
//        name(blank: false)
//        extName(blank: false)
//        convertName(blank: false)
//        path(blank: false)

        metadata(nullable: true)
        MD5(nullable: true)
//        fileSize(blank: false)
        creationDate(nullable: true)
        duration(nullable: true,blank: true)
//        userId(blank: false)
//        ownerId(blank: false)

        preImgPath(nullable: true)
        artist(nullable: true)
        special(nullable: true)
        genre(nullable: true)
        pdfPath(nullable: true)
        swfPath(nullable: true,blank: true)
        author(nullable: true)
        encoding(nullable: true)
        content(nullable: true)
        isSwfDone(nullable: true)
        isPdfDone(nullable: true)
    }

    String toString(){
        return name
    }
}
