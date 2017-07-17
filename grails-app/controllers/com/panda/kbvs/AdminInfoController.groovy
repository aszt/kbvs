package com.panda.kbvs

import com.panda.kbvs.util.Uuid
import org.h2.engine.User

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class AdminInfoController {


//管理员登录页
    def adminLogin(){

    }
    //管理员登录
    def doAdminLogin(String name,String password){
        def adminInfo=AdminInfo.findByNameAndPassword(name,password)
        if(adminInfo){
            session.adminInfo = adminInfo
            session.adminName = adminInfo.name
            redirect(uri: '/adminInfo/index')
        }else{
            flash.message="用户名或者密码错误！"
            redirect(uri: '/adminInfo/adminLogin')
        }

    }
    //用户列表
    def index() {

        try {
           def  admin = session.adminInfo.name
        }catch (Exception e){
            flash.message="登录失效"
            redirect(uri: '/adminInfo/adminLogin')
            return
        }

        String search=params.search
        String page=params.pageNo
        int pageSize = 11;
        int pageNo = 1;
        int count = 0;
        def userInfoList
        if(page!=null){
            pageNo=Integer.parseInt(page)
        }
        if ((search==null||search.equals(""))){
            userInfoList=UserInfo.list([max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*pageSize ])
            count=UserInfo.count()
            print(1)
        }else{
            userInfoList=UserInfo.findAllByLoginNameIlikeOrNameIlike("%"+search+"%","%"+search+"%",[max: pageSize, sort: "name", order: "asc", offset:(pageNo-1) * pageSize])
            count=UserInfo.countByLoginNameIlikeOrNameIlike("%"+search+"%","%"+search+"%")
            print(2)
        }
        print("userInfoList.size()"+userInfoList.size()+"count"+count)
        [userInfoList:userInfoList,pageNo:pageNo,count:count, totalPage:(int) Math.ceil((float) count / pageSize),search:search]
    }

    //改变用户状态
    def changeState(String id){
        def userInfo=UserInfo.get(id)
         if(userInfo.state.equals("正常")){
            userInfo.state="禁用"
        }else {
            userInfo.state="正常"
        }
        userInfo.save(flush: true)
        render(contentType: "application/json") {
            state=userInfo.state
        }
    }

    //添加用户
    def addUser(String userName,String pwd,String email,String realName,String phone){
        //创建用户
        def user=new UserInfo()
        user.password=pwd.encodeAsMD5()
        user.loginName=userName
        user.email=email
        user.name=realName
        user.mobilePhone=phone
        user.state="正常"
        user.code= Uuid.uuid()
        if (!user.save (flush: true)){
            flash.msg = "添加失败"
            redirect(uri: '/adminInfo/index')
            return
        }

        def folder1=new FolderInfo()
        folder1.name="home"
        folder1.ownerId=user.id
        folder1.save flush: true
        def folder2=new FolderInfo()
        folder2.name="activeFile "
        folder2.ownerId=user.id
        folder2.save flush: true
        def folder3=new FolderInfo()
        folder3.name="recycleBin"
        folder3.ownerId=user.id
        folder3.save flush: true

        flash.msg = "添加成功"
        redirect (uri: "/adminInfo/index")

    }

    //资源统计
    def resCount(){

        def word=ResourceInfo.countByType("word")
        def music=ResourceInfo.countByType("music")
        def video=ResourceInfo.countByType("video")
        def images=ResourceInfo.countByType("images")
        def other=ResourceInfo.countByType("other")
        def count=ResourceInfo.count;
        print("word:"+word+"music:"+music+"video:"+video+"images:"+images+"other:"+other+"count:"+count)
        render(contentType: "application/json") {
            Word=word
            Music=music
            Video=video
            Images=images
            Other=other
            Count=count
        }

    }
    //管理员修改密码
    def changePwd(String oldPassword,String password){
        def adminId
        try {
            adminId = session.adminInfo.id
        }catch (Exception e){
            flash.message="登录失效"
            redirect(uri: '/adminInfo/adminLogin')
            return
        }
        def adminInfo=AdminInfo.get(adminId)
        if (adminInfo.password.equals(oldPassword)){
            adminInfo.password=password
            adminInfo.save(flush: true)
            render(contentType: "application/json") {
                state=200
            }
        }else {
            render(contentType: "application/json") {
                state=201
            }
        }
    }
def showUser(String id){
    print(id)
    //获取用户实体
    def userInfoInstance = UserInfo.get(id)

    def word = ResourceInfo.countByUserIdAndType(id,"word")
    def music = ResourceInfo.countByUserIdAndType(id,"music")
    def video = ResourceInfo.countByUserIdAndType(id,"video")
    def images = ResourceInfo.countByUserIdAndType(id,"images")
    def other =ResourceInfo.countByUserIdAndType(id,"other")
    def count=ResourceInfo.countByUserId(id)
    print("word:"+word+"music:"+music+"video:"+video+"images:"+images+"other:"+other+"count:"+count)
    [UserInfo:userInfoInstance,Word:word,Music:music,Video:video,Images:images,Other:other,Count:count]
}

}
