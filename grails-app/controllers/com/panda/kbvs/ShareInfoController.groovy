package com.panda.kbvs

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

class ShareInfoController {

    //查找该文件夹里的资源
    def findResourcesByFolder(String id) {

        def userId = params.userId
        def shareId = params.shareId

        def shareInfo=ShareInfo.findById(shareId)
        def userInfo=UserInfo.findById(userId)

        def folderInfo = null
        def folderList = new ArrayList<FolderInfo>()

        //音乐文件夹
        def folderInfoInstance = FolderInfo.findById(id)

        //获取文件夹id
        def folderInfoId = folderInfoInstance.id
        def folderInfoName = folderInfoInstance.name

        //面包线处理
        def ownerId = folderInfoInstance.ownerId

        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }

        while (!ownerId.equals(userIds)) {
            folderInfo = new FolderInfo()
            folderInfo.id = folderInfoInstance.id
            folderInfo.name = folderInfoInstance.name
            folderInfo.ownerId = folderInfoInstance.ownerId
            folderList.add(folderInfo)

            folderInfoInstance = FolderInfo.findById(folderInfo.ownerId)
            ownerId = folderInfoInstance.ownerId
        }

        //倒序
        def dFolderList = folderList.reverse()

        //通过文件夹id得到所有文件夹里的文件
        def ResourceInfoList = ResourceInfo.findAllByOwnerId(folderInfoId)
        def FolderInfoList = FolderInfo.findAllByOwnerId(folderInfoId)
//        [ResourceInfoList:resourceList,FolderInfoList: folderList,UserInfo:userInfo,ShareInfo:shareInfo]
        render(view: "show", model: ['ResourceInfoList': ResourceInfoList, 'FolderInfoList': FolderInfoList,'UserInfo':userInfo,'ShareInfo':shareInfo, 'FolderInfoId': folderInfoId, 'FolderList': dFolderList])
    }

    def shareGroup(String resId, String groupId){
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        String[] stringArray = resId.split(",");
        ShareInfo shareInfo=new ShareInfo()
        shareInfo.content=resId
        shareInfo.ownerId=groupId
        shareInfo.userId=userIds
        if (stringArray.length>1){
            if (ResourceInfo.findById(stringArray[0])){

                shareInfo.name=ResourceInfo.findById(stringArray[0]).name+"...等"+stringArray.length+"个文件"
            }
            else{
                shareInfo.name=FolderInfo.findById(stringArray[0]).name+"...等"+stringArray.length+"个文件"

            }
        }else{
            if (ResourceInfo.findById(stringArray[0])){
                shareInfo.name=ResourceInfo.findById(stringArray[0]).name
            }
            else{
                shareInfo.name=FolderInfo.findById(stringArray[0]).name
            }
        }
        shareInfo.save flush: true
        List groupIdList
        def groupInfoList
        GroupInfo groupInfo
        if (groupId.indexOf(",")!=-1){
            print"分享到多个组群"
            String[] array = groupId.split(",");
            groupIdList = Arrays.asList(array)
            groupInfoList=GroupInfo.findAllByIdInList(groupIdList)
            for(int i=0;i<groupInfoList.size();i++){
                groupInfo=  groupInfoList.get(i)
                if(groupInfo.content==null||groupInfo.content.equals("")){
                    groupInfo.content=shareInfo.id+","
                }else{
                    groupInfo.content=groupInfo.content+shareInfo.id+","
                }
                groupInfo.save(flush: true)
            }
        }else{
            print"分享到一个组群"
            groupInfo=GroupInfo.findById(groupId)
            if(groupInfo.content==null||groupInfo.content.equals("")){
                groupInfo.content=shareInfo.id+","
            }else{
                groupInfo.content=groupInfo.content+shareInfo.id+","
            }
            groupInfo.save(flush: true)
        }

        render(view: 'resourceInfo/index', contentType: "application/json"){
            state = "200"
        }
    }
    def shares(String fileId){
        println"分享的文件id是：${fileId}"
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        String[] stringArray = fileId.split(",");
        ShareInfo shareInfo=new ShareInfo()
        shareInfo.content=fileId
        shareInfo.ownerId="public"
        shareInfo.userId=userIds
        if (stringArray.length>1){
            if (ResourceInfo.findById(stringArray[0])){

                shareInfo.name=ResourceInfo.findById(stringArray[0]).name+"...等"+stringArray.length+"个文件"
            }
            else{
                shareInfo.name=FolderInfo.findById(stringArray[0]).name+"...等"+stringArray.length+"个文件"

            }
        }else{
            if (ResourceInfo.findById(stringArray[0])){
                shareInfo.name=ResourceInfo.findById(stringArray[0]).name
            }
            else{
                shareInfo.name=FolderInfo.findById(stringArray[0]).name
            }
        }
        shareInfo.save flush: true
        def spath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/kbvs/shareInfo/show/${shareInfo.id}"
        render(view: 'resourceInfo/index', contentType: "application/json"){
            state = "200"
            value=spath
        }
    }




    def index() {
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def shareInfoList=ShareInfo.findAllByUserId(userIds)
        [ShareInfoList: shareInfoList]
    }
//显示分享
    def show(String id) {
        def resourceList
        def folderList

        def shareInfo=ShareInfo.findById(id)
        if(shareInfo.content.indexOf(",")!=-1){
            String[] stringArray = shareInfo.content.split(",");
            List foIdList = Arrays.asList(stringArray)
            //得到分享中的文件夹
            folderList = FolderInfo.findAllByIdInList(foIdList)
            //得到分享中的文件
            resourceList = ResourceInfo.findAllByIdInList(foIdList)
        }else {
            //得到分享中的文件夹
            folderList = FolderInfo.findAllById(shareInfo.content)
            //得到分享中的文件
            resourceList = ResourceInfo.findAllById(shareInfo.content)
        }
        def userInfo=UserInfo.findById(shareInfo.userId)
        [ResourceInfoList:resourceList,FolderInfoList: folderList,UserInfo:userInfo,ShareInfo:shareInfo]
    }
    //删除多个
    def deletes(String shareId){
        print("shanchu ")
        String[] stringArray = shareId.split(",");
        List foIdList = Arrays.asList(stringArray)
        def shareInfoList = ShareInfo.findAllByIdInList(foIdList)
        for (int i=0;i<shareInfoList.size();i++){
            shareInfoList.get(i).delete(flush: true)
        }
        render(contentType: "application/json"){
            state = "200"

        }
    }
    //删除单个
    def del(String shareId){
        print(shareId)
        def shareInfo=ShareInfo.get(shareId)
        shareInfo.delete(flush: true)
        render(contentType: "application/json"){
            state = "200"

        }
    }


    def create() {
        respond new ShareInfo(params)
    }

    @Transactional
    def save(ShareInfo shareInfoInstance) {
        if (shareInfoInstance == null) {
            notFound()
            return
        }

        if (shareInfoInstance.hasErrors()) {
            respond shareInfoInstance.errors, view: 'create'
            return
        }

        shareInfoInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shareInfo.label', default: 'ShareInfo'), shareInfoInstance.id])
                redirect shareInfoInstance
            }
            '*' { respond shareInfoInstance, [status: CREATED] }
        }
    }

    def edit(ShareInfo shareInfoInstance) {
        respond shareInfoInstance
    }

    @Transactional
    def update(ShareInfo shareInfoInstance) {
        if (shareInfoInstance == null) {
            notFound()
            return
        }

        if (shareInfoInstance.hasErrors()) {
            respond shareInfoInstance.errors, view: 'edit'
            return
        }

        shareInfoInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ShareInfo.label', default: 'ShareInfo'), shareInfoInstance.id])
                redirect shareInfoInstance
            }
            '*' { respond shareInfoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ShareInfo shareInfoInstance) {

        if (shareInfoInstance == null) {
            notFound()
            return
        }

        shareInfoInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ShareInfo.label', default: 'ShareInfo'), shareInfoInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareInfo.label', default: 'ShareInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
