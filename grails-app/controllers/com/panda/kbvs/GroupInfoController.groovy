package com.panda.kbvs

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

class GroupInfoController {

    def shares(){
        print params.groupId
        def spath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/kbvs/groupInfo/addToGroup?gId="+params.groupId
        print spath
        render(view: 'resourceInfo/index', contentType: "application/json"){
            state = "200"
            value=spath
        }
    }
    /*
    * 创建组群
    * */
    def newGroup(String groupName){
        print("创建组群")
        def userIds = null

        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }

        def flag = true
        int i = 1
        while (flag) {
            if (GroupInfo.findByCreatorIdAndName(userIds,groupName)) {
                if (groupName.endsWith(")")) {
                    groupName = groupName.substring(0, groupName.lastIndexOf("(")) + "(" + i + ")"
                } else {
                    groupName = groupName + "(" + i + ")"
                }
                i++
                print "${i}"
            } else
                flag = false
        }
       def userInfo= UserInfo.get(userIds)
        def members=""
        def groupIds
        if(userInfo.groupIds==null){
            groupIds=""
        }else {
            groupIds=userInfo.groupIds
        }
        GroupInfo groupInfo =new GroupInfo()
        groupInfo.name=groupName
        groupInfo.creatorId=userIds
        groupInfo.members=members+userIds+","
        groupInfo.save(flush: true)
        userInfo.groupIds=groupIds+groupInfo.id+","
        userInfo.save(flush: true)

        render(contentType: "application/json"){
            group=groupInfo
            state = "200"
        }
    }

    /*
    * 得到组群中的成员
    * 返回userInfoList
    * */
        def getMember(String groupId){
            def groupInfo= GroupInfo.get(groupId)
            def userIds=groupInfo.members
            String[] stringArray = userIds.split(",");
            List userIdsList = Arrays.asList(stringArray)
            def userInfoList=UserInfo.findAllByIdInList(userIdsList)
            render(contentType: "application/json"){
                state = "200"
                value=userInfoList
                groupName=groupInfo.name
            }
        }


    /*
    *退出组群
    * 返回200成功*/
    def quitGroup(String groupId){
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def userInfo= UserInfo.get(userIds)
        userInfo.groupIds=userInfo.groupIds.replace(userIds+",","")
        userInfo.save(flush: true)
        def groupInfo=GroupInfo.get(groupId)
        groupInfo.members=groupInfo.members.replace(userIds+","+"")
        groupInfo.save(flush: true)
        def shareInfoList=ShareInfo.findAllByUserIdAndOwnerId(userIds,groupId)
        for(int i=0;i<shareInfoList.size();i++){
            shareInfoList.get(i).delete()
        }
        render(contentType: "application/json"){
            state = "200"
        }

    }



    /*删除组群
    * 返回200删除成功
    * 返回300没有权限删除
    * */
    def delGroup(String groupId){
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
       def groupInfo= GroupInfo.get(groupId)
        if(userIds.equals(groupInfo.creatorId)){
            groupInfo.delete(flush: true)
            render(contentType: "application/json"){
                state = "200"

            }
        }else {
            render(contentType: "application/json"){
                state = "300"
            }
        }

    }
    /*
    * 得到我加入的所有组群
    * */
    def myGroup(){
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def userInfo= UserInfo.get(userIds)
        print("得到我的组群")
        def groupIds= userInfo.groupIds
        print(groupIds)
        def groupInfoLidt
        if(groupIds!=null&&!groupIds.equals("")){
            String[] stringArray = groupIds.split(",");
            List groupIdList = Arrays.asList(stringArray)
            groupInfoLidt = GroupInfo.findAllByIdInList(groupIdList)
        }

        render(view: 'resourceInfo/index', contentType: "application/json"){
            state = "200"
            value=groupInfoLidt
        }
    }
    /*
    * 打开确认添加弹窗
    * */
    def addToGroup(){
        String groupId= params.gId
        print "打开确认加入组群"
        def userId = null
        try {
            userId = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
//        if (userId == null) {
//            redirect(uri: '/')
//            return
//        }
        def userInfo=UserInfo.get(userId)
        def groupIds= userInfo.groupIds
        def groupList
        if(groupIds!=null&&!groupIds.equals("")){
            String[] stringArr = groupIds.split(",");
            List groupIdList = Arrays.asList(stringArr)
            groupList = GroupInfo.findAllByIdInList(groupIdList)
        }
        def groupInfo= GroupInfo.get(groupId)
        print("组群Id：${groupId}")
        if(groupInfo!=null){
           def userIds=groupInfo.members
            print("组群成员：${userIds}")
             print("用户Id：${userId}")
            if(userIds.indexOf(userId)!=-1){
                render(view: "index", model: [groupInfoList: groupList])
                return
            }
            String[] stringArray = userIds.split(",");
            List userIdsList = Arrays.asList(stringArray)
            def userInfoList=UserInfo.findAllByIdInList(userIdsList)
            print("组群中成员个数${userInfoList.size()}")
            render(view: "index", model: [userInfoList: userInfoList,state:600,groupInfoList: groupList,groupName:groupInfo.name,groupId:groupId])
        }else{
            render(view: "index", model: ['userInfoList': null,'state':601,'groupInfoList': groupList])
        }


    }
    /*
    * 用户添加到组群
    * */
    def addGroup(String groupId){
        def userId = null
        try {
            userId = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def userInfo=UserInfo.get(userId)
        def groupInfo=GroupInfo.get(groupId)
        def groupIds
        if(userInfo.groupIds==null){
            groupIds=""
        }
        else {
            groupIds=userInfo.groupIds
        }
        userInfo.groupIds=groupIds+groupId+","
        userInfo.save(flush: true)
        groupInfo.members=groupInfo.members+userId+","
        groupInfo.save(flush: true)
        render(contentType: "application/json"){
            group=groupInfo
            state = "200"
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
//        if (userIds == null) {
//            redirect(uri: '/')
//            return
//        }
        def userInfo= UserInfo.get(userIds)
        def groupIds= userInfo.groupIds
        def groupList
        if(groupIds!=null&&!groupIds.equals("")){
            String[] stringArray = groupIds.split(",");
            List groupIdList = Arrays.asList(stringArray)
            groupList = GroupInfo.findAllByIdInList(groupIdList)
        }
        [groupInfoList: groupList]
    }



    //组群页得到分享
    def getShare(String groupId){
        print"得到分享${groupId}"
        def shareInfoList
        def groupInfo=GroupInfo.get(groupId)
        String shareIds=groupInfo.content
        print shareIds
        if(shareIds!=null&&!shareIds.equals("")){
            String[] stringArray = shareIds.split(",");
            List foIdList = Arrays.asList(stringArray)
            shareInfoList = ShareInfo.findAllByIdInList(foIdList)
        }
        render(view: 'resourceInfo/index', contentType: "application/json"){
            state = "200"
            value=shareInfoList
        }
    }




    def show(GroupInfo groupInfoInstance) {
        respond groupInfoInstance
    }

    def create() {
        respond new GroupInfo(params)
    }

    @Transactional
    def save(GroupInfo groupInfoInstance) {
        if (groupInfoInstance == null) {
            notFound()
            return
        }

        if (groupInfoInstance.hasErrors()) {
            respond groupInfoInstance.errors, view: 'create'
            return
        }

        groupInfoInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'groupInfo.label', default: 'GroupInfo'), groupInfoInstance.id])
                redirect groupInfoInstance
            }
            '*' { respond groupInfoInstance, [status: CREATED] }
        }
    }

    def edit(GroupInfo groupInfoInstance) {
        respond groupInfoInstance
    }

    @Transactional
    def update(GroupInfo groupInfoInstance) {
        if (groupInfoInstance == null) {
            notFound()
            return
        }

        if (groupInfoInstance.hasErrors()) {
            respond groupInfoInstance.errors, view: 'edit'
            return
        }

        groupInfoInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GroupInfo.label', default: 'GroupInfo'), groupInfoInstance.id])
                redirect groupInfoInstance
            }
            '*' { respond groupInfoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GroupInfo groupInfoInstance) {

        if (groupInfoInstance == null) {
            notFound()
            return
        }

        groupInfoInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GroupInfo.label', default: 'GroupInfo'), groupInfoInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'groupInfo.label', default: 'GroupInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
