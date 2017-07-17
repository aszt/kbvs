package com.panda.kbvs

import cn.itcast.mail.Mail
import cn.itcast.mail.MailUtils
import cn.itcast.vcode.utils.VerifyCode
import com.panda.kbvs.util.GeetestConfig
import com.panda.kbvs.util.GeetestLib
import com.panda.kbvs.util.Uuid

import javax.mail.MessagingException
import javax.mail.Session
import java.awt.image.BufferedImage

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class UserInfoController {
    //上传用户头像
    def upload(){
    }
    def uploadImg(){
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def userInfo=UserInfo.get(userIds)
        def f = request.getFile('__avatar1')
        def webRootDir = servletContext.getRealPath("/")
        String uploadDir = "uploads/userImg/"
        def path = webRootDir + uploadDir +userIds+".jpg"
        def userDir = new File(webRootDir, uploadDir)
        userDir.mkdirs()
        f.transferTo(new File("${path}"))
        userInfo.imagePath=uploadDir+userIds+".jpg"
        userInfo.save(flush: true)
        render(contentType: "application/json") {
            success =true
        }
//        render(text: "success:true","msg:Success!")
    }

    def verifyCode(){
        /*
		 * 1. 创建验证码类
		 */
        VerifyCode vc = new VerifyCode();
        /*
         * 2. 得到验证码图片
         */
        BufferedImage image = vc.getImage();
        /*
         * 3. 把图片上的文本保存到session中
         */
        session['session_vcode'] = vc.getText()
        /*
         * 4. 把图片响应给客户端
         */
        VerifyCode.output(image, response.getOutputStream());
    }

    //编辑个人信息
    def updateInfo(UserInfo userInfoInstance){
        print"编辑个人信息"
        //用户实体
        def userId = null
        try {
            userId = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def userInfo = UserInfo.findById(userId)

        userInfo.name = userInfoInstance.name
        userInfo.loginName = userInfoInstance.loginName
        userInfo.code = userInfoInstance.code
        userInfo.email = userInfoInstance.email
        userInfo.mobilePhone = userInfoInstance.mobilePhone
        userInfo.telephone = userInfoInstance.telephone
        userInfo.department = userInfoInstance.department

        userInfo.save flush: true
        render(contentType: "application/json",view: 'personal'){
            msg = "修改成功"
        }
    }


    def personal(){
        print"222222222"
        //个人中心
        def userId = null
        try {
            userId = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        //获取用户实体
        def userInfoInstance = UserInfo.findById(userId)
        def word = ResourceInfo.countByUserIdAndType(userId,"word")
//        print("文档："+word)
        def music =ResourceInfo.countByUserIdAndType(userId,"music")
//        print("音乐："+music)
        def video =ResourceInfo.countByUserIdAndType(userId,"video")
//        print("视频："+video)
        def images = ResourceInfo.countByUserIdAndType(userId,"images")
//        print("图片："+images)
        def other = ResourceInfo.countByUserIdAndType(userId,"other")
//        print("其他："+other)
        def count=word+music+video+images+other
        [UserInfoInstance:userInfoInstance,Word:word,Music:music,Video:video,Images:images,Other:other,Count:count]
    }

    //用户注册
    def doRegister(String email,String loginName,String password,String passwords){
            //创建用户
            def user=new UserInfo()
            user.password=password.encodeAsMD5()
            user.loginName=loginName
            user.email=email
            user.state="未激活"
            user.code= Uuid.uuid()
            if (!user.save (flush: true)){
                flash.registMsg = "注册失败，请稍后重试！"
                redirect(uri: '/')
//                render (view: 'account')
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


            String host ="smtp.139.com";// 获取服务器主机
            String uname ="luna_test";// 获取用户名
            String pwd ="luna_test";// 获取密码
            String from ="luna_test@139.com";// 获取发件人
            String to = user.email;// 获取收件人
            String subject ="来自知识库管理系统的注册邮件";// 获取主题
            String content ="<a href=\"http://127.0.0.1:8080/kbvs/userInfo/active?code=${user.code}\">点击激活</a>";// 获取邮件内容
            Session session = MailUtils.createSession(host, uname, pwd);// 得到session

            Mail mail = new Mail(from, to, subject, content);// 创建邮件对象
            try {
                MailUtils.send(session, mail);// 发邮件！
            } catch (MessagingException e) {
            }

            flash.registMsg = "注册成功！请马上到邮箱激活"
            redirect (uri: "/")


    }
    //用户激活
    def active(){
        String code=params.code
        def userInfo=UserInfo.findByCode(code)
        if(userInfo==null){
            flash.registMsg = "激活码无效"
            redirect (uri: "/")
        }else if(!userInfo.state.equals("未激活")){
            flash.registMsg = "你已经激活过了，不用再次激活"
            redirect (uri: "/")
        }else {
            userInfo.state="正常"
            userInfo.save(flush: true)
            flash.registMsg = "激活成功，请登录"
            redirect (uri: "/")
        }
    }
    def startCaptcha(){
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());

        String resStr = "{}";

        //自定义userid
        String userid = "test";

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(userid);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();
        print("resStr"+resStr)

        render(resStr )
    }

    def verifyLogin(){
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String)request.getSession().getAttribute("userid");

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }


        if (gtResult == 1) {
           // 验证成功
            render(contentType: "application/json") {
                status ="success"
                version=gtSdk.getVersionInfo()
            }
        }
        else {
           // 验证失败
            print(22222)
            render(contentType: "application/json") {
                status ="fail"
                version=gtSdk.getVersionInfo()
            }
        }


    }
    //检测邮箱是否已经被注册过
    def checkEmail(String email){
        def userInfo=UserInfo.findByEmail(email)
        if(userInfo){
            if(!userInfo.state.equals("未激活")){
            render(contentType: "application/json"){
                state = "200"
                }
            }
        }else{
            render(contentType: "application/json"){
                state = "201"
            }
        }
    }

    //检测用户名是否已经被注册过
    def checkUserName(String userName){
        def userInfo=UserInfo.findByLoginName(userName)
        if(userInfo){
            render(contentType: "application/json"){
                state = "200"

            }
        }else{
            render(contentType: "application/json"){
                state = "201"
            }
        }
    }
    def checkResetEmail(String email){
        def userInfo=UserInfo.findByEmail(email)
        if(userInfo==null){
                render(contentType: "application/json"){
                    state = "200"
            }
        }else{
            render(contentType: "application/json"){
                state = "201"
            }
        }
    }
//发送重置密码邮件
    def sendResetEmail(){
        def email=params.resetEmail
        print (222+email)
        def userInfo=UserInfo.findByEmail(email)
        if(userInfo==null){
            flash.registMsg = "重置密码出错!!!"
            redirect (uri: "/")
        }
        String host ="smtp.139.com";// 获取服务器主机
        String uname ="luna_test";// 获取用户名
        String pwd ="luna_test";// 获取密码
        String from ="luna_test@139.com";// 获取发件人
        String to = userInfo.email;// 获取收件人
        String subject ="来自知识库管理系统的重置密码邮件";// 获取主题

        String content =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"kbvs/userInfo/resetPwd?email=${userInfo.email}&code=${userInfo.code}\">点击重置密码</a>";// 获取邮件内容
        Session session = MailUtils.createSession(host, uname, pwd);// 得到session

        Mail mail = new Mail(from, to, subject, content);// 创建邮件对象
        try {
            MailUtils.send(session, mail);// 发邮件！
        } catch (MessagingException e) {
        }

        flash.registMsg = "密码重置邮件已发送。"
        redirect (uri: "/")


    }
//到重置密码页
    def resetPwd(String email,String code){
        [email:email,code:code]
    }
//重置密码
    def doResetPassword(String email,String code,String password){
        def userInfo=UserInfo.findByEmailAndCode(email,code)
        if(userInfo){
            userInfo.password=password.encodeAsMD5()
            userInfo.save(flush: true)
            flash.registMsg = "密码重置成功"
            redirect (uri: "/")
        }else{
            flash.registMsg = "密码重置失败"
            redirect (uri: "/")
        }
    }


    //用户登录
    def doLogin(String loginName, String password) {
        def userInfo = UserInfo.findByLoginNameAndPassword(loginName, password.encodeAsMD5())
        def userInfo1 = UserInfo.findByEmailAndPassword(loginName, password.encodeAsMD5())
        if(userInfo){
            //flash.message="恭喜你，登录成功"
            if (userInfo.state.equals("正常")){
                session['userInfo'] = userInfo
                session.userName = userInfo.loginName
                session.imagePath = userInfo.imagePath
                session.userId = userInfo.id
                redirect(uri: '/resourceInfo/index')
            }else {
                flash.message="账号未激活或被禁用！"
                redirect(uri: '/')
            }
        }else if(userInfo1){
            if(userInfo1.state.equals("正常")){
                session['userInfo'] = userInfo1
                session.userName = userInfo1.loginName
                session.imagePath = userInfo1.imagePath
                session.userId = userInfo1.id
                redirect(uri: '/resourceInfo/index')
            }else {
                flash.message="账号未激活或被禁用！"
                redirect(uri: '/')
            }
        }

        else{
            flash.message="用户名或者密码错误！"
            redirect(uri: '/')
        }
    }

    //退出登录
    def loginOut = {
        session.invalidate()
        redirect(uri: "/")
    }



}
