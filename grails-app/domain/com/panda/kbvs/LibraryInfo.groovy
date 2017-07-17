package com.panda.kbvs

class LibraryInfo {
    String id
    String name //知识库名
    String resName//文件名称
    String resContent1//文件内容
    String resContent2//文件内容
    String resContent3//文件内容
    String resAuthor//文件作者
    Date beginTime//开始时间
    Date endTime//结束时间
    Date dateCreated    //创建时间
    Date lastUpdated     //更新时间
    String userId//用户Id
    String types
    String filtration//
    String resourceIds
    static constraints = {
        resContent1(blank:true,nullable: true)
        resContent2(blank:true,nullable: true)
        resContent3(blank:true,nullable: true)
        resAuthor(blank: true,nullable: true)
        beginTime(nullable: true,blank:true)
        endTime(nullable: true,blank:true)
        resName(nullable: true,blank: true)
        userId(nullable: false,blank: false )
        filtration(nullable: true,blank: true)
        resourceIds(nullable: true,blank: true)
        lastUpdated(nullable: true,blank: true)
        types(nullable: true,blank: true)
    }
    static mapping = {
        id generator: 'uuid.hex'
    }
    String toString(){
        return name
    }
}
