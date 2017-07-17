package com.panda.kbvs

import kbvs.converter.LuceneServices

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class FolderInfoController {

    //文件/文件夹重命名
    def reName(){
        def infoId =params.infoId//要移动文件夹的id（逗号隔开的字符串）
        def infoName =params.infoName//移动到此文件夹的id
        LuceneServices luceneServices=new LuceneServices();
        //查找该文件夹实体
        def folderInfoInstance = FolderInfo.findById(infoId)

        if(folderInfoInstance==null){
            def resourceInfoInstance = ResourceInfo.findById(infoId)
            resourceInfoInstance.name = infoName
            resourceInfoInstance.save(flush: true)
            luceneServices.updateIndex(resourceInfoInstance)
        }else{
            folderInfoInstance.name = infoName
            folderInfoInstance.save(flush: true)
        }

        render(contentType: "application/json"){
            state = "200"
        }
    }

    //文件夹移动
    def move(){
        //要移动文件/文件夹的ownerId改成要移动到的文件夹id
        def FolderId =params.FolderIds//要移动文件夹的id（逗号隔开的字符串）
        def folderIds =params.folderIds//移动到此文件夹的id

        String[] stringArray = FolderId.split(",");
        List foIdList = Arrays.asList(stringArray)

        //要移动的文件资源
        def resourceList = ResourceInfo.findAllByIdInList(foIdList)
        for (ResourceInfo resourceInfo : resourceList) {
            resourceInfo.ownerId = folderIds
            resourceInfo.save(flush: true)
        }

        //要移动的文件夹
        def folderList = FolderInfo.findAllByIdInList(foIdList)
        for (FolderInfo folder : folderList) {
            folder.ownerId = folderIds
            folder.save(flush: true)
        }
        render(contentType: "application/json"){
            state = "200"
        }
    }

    //文件夹多层查看
    def findFolder(String folderId){
        def folderList = FolderInfo.findAllByOwnerId(folderId)
//        print("${folderList.size()}")
        render(contentType: "application/json"){
            folderInfoList = folderList
        }
    }

    //ajax处理文件夹
    def newFolders(String folderName,String ownerId){

        def flag = true
        int i = 1
        while (flag) {
            if (FolderInfo.findByNameAndOwnerId(folderName,ownerId)) {
                if (folderName.endsWith(")")) {
                    folderName = folderName.substring(0, folderName.lastIndexOf("(")) + "(" + i + ")"
                } else {
                    folderName = folderName + "(" + i + ")"
                }
                i++
            } else
                flag = false
        }

        /*def folders = FolderInfo.findByNameAndOwnerId(folderName,ownerId)
        if(folders){
            flash.message1 = "对不起！该文件夹已存在。"
            print("对不起！该文件夹已存在。")
            return
        }*/

        def folder=new FolderInfo()
        folder.name = folderName
        folder.ownerId = ownerId

        folder.save(flush: true)

        def id = folder.getId()
        def tms = folder.dateCreated.toString()
//        print("${tms}")
        render(contentType: "application/json"){
            name = folderName
            folderId = id
            tmss = tms
            state = '新建成功'
        }

    }

    //定位文件夹资源
    /*def spotFile(String id){
        def folderInfoInstance = FolderInfo.findById(id)
        //获取文件夹id
        def folderInfoId = folderInfoInstance.id
        def folderInfoName = folderInfoInstance.name
        //通过文件夹id得到所有文件夹里的文件
        def ResourceInfoList = ResourceInfo.findAllByOwnerId(folderInfoId)
        def FolderInfoList = FolderInfo.findAllByOwnerId(folderInfoId)
        render(view: "index", model: ['ResourceInfoList': ResourceInfoList,'FolderInfoList':FolderInfoList,'FolderInfoName':folderInfoName,'FolderInfoId':folderInfoId])
    }*/



}
