package com.panda.kbvs


import kbvs.converter.JpegTool
import kbvs.converter.JpegToolException
import kbvs.converter.LuceneServices
import kbvs.converter.VideoConverter
import kbvs.util.FileUtils

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class ResourceInfoController {

    //查看关联
    def viewAssociation(String id){
        def resourceList
        def resourceInfo = ResourceInfo.findById(id)
        if(resourceInfo.relation==null){
            resourceList==null
        }else{
            String relations = resourceInfo.relation
            String[] stringArray = relations.split(",");
            List arrays = Arrays.asList(stringArray)
            resourceList = ResourceInfo.findAllByIdInList(arrays)
        }

        [ResourceList:resourceList]
    }

    def moveCommon(String id){
        LuceneServices luceneServices=new LuceneServices()
        def resourceInfo = ResourceInfo.findById(id)
        resourceInfo.common = "activeFile"
        luceneServices.updateIndex(resourceInfo)
        resourceInfo.save(flush: true)
        render(contentType: "application/json") {
            state = '200'
        }
    }

    //文档关联
    def documentRelation(String fileIds){
        String[] stringArray = fileIds.split(",");
        //返回的是AbstractList
        List foIdList = Arrays.asList(stringArray)
        List foIdLists = new ArrayList(foIdList);
        if(foIdList.size()<2){
            render(contentType: "application/json") {
                state = '无法关联'
            }
            return
        }
        def folderList = FolderInfo.findAllByIdInList(foIdList)
        if(folderList.size()==0){

            def resourceList = ResourceInfo.findAllByIdInList(foIdList)
            for (ResourceInfo resourceInfo : resourceList) {

//                def is = fileIds.replace(resourceInfo.id,"")
                def relation = resourceInfo.relation
                if(relation==null){
                    relation=""
                }
                //除却本身之外的id字符串转换为数组
                relation = fileIds
                String[] array = fileIds.split(",");
                for (int i=0;i<array.length;i++){
                    if(relation.indexOf(array[i])==-1){
                        //没找到=-1
                        relation +=relation+array[i]+","
//                        print("${relation}")
                    }
                }
                resourceInfo.relation = relation
                resourceInfo.save(flush: true)
            }
            render(contentType: "application/json") {
                state = '关联成功'
            }
        }else{
            print("有文件夹=============")
            render(contentType: "application/json") {
                state = '无法关联'
            }
        }

    }


    //iframe层,打开新页面加载文件夹数据
    def open(){
        //要移动文件夹id集合
        def FolderId =params.id
//        print("${a}")
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        //home文件夹实体
        def FolderInfoInstance = FolderInfo.findByOwnerIdAndName(userIds, "home")
        def FolderInfoList = FolderInfo.findAllByOwnerId(FolderInfoInstance.id)
        [FolderInfoList: FolderInfoList,FolderId:FolderId,FolderInfos:FolderInfoInstance]
    }

    //单文件下载
    def singleFileDownload(String id) {
        def reName = ''
        def resourceInfoInstance = ResourceInfo.get(id)
        if (!resourceInfoInstance) {
            flash.message = "未找到该文件，错误请求或已被删除"
            redirect(action: "/resourceInfo/index")
        } else {
            def webRootDir = servletContext.getRealPath("/")
            //文件名+扩展名
            reName = resourceInfoInstance.name +'.'+ resourceInfoInstance.extName

            String myName = new String(reName.getBytes("UTF-8"), "ISO8859_1");
            response.reset();
            response.setCharacterEncoding("ISO8859_1");

            response.setContentType("application/octet-stream; charset=UTF-8");
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
                //IE
                myName = URLEncoder.encode(reName, "UTF-8");
            }
            response.setHeader("Content-Disposition", "ResourceInfo; filename=\""+ myName + "\"");
            try {
                def filePath
                filePath = webRootDir + resourceInfoInstance.path
                print("${filePath}")
                File file = new File("${filePath}")
                byte[] b = new byte[10240];
                FileInputStream inStream = null
                int len;
                if (file.exists() && file.isFile()) {
                    inStream = new FileInputStream(file);
                    if (inStream != null) {
                        while ((len = inStream.read(b)) > 0) {
                            response.getOutputStream().write(b, 0, len);
                        }
                        //抛出文件流
                        response.getOutputStream().flush();
                        inStream.close();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace()
            }
        }

    }

    //文件下载
    def download(String id) {
        print("${id}")
        String[] stringArray = id.split(",");
        List foIdList = Arrays.asList(stringArray)

        def reName = ''
        def resourceInfoInstance = ResourceInfo.get(stringArray[0])
        if (!resourceInfoInstance) {
            flash.message = "未找到该文件，错误请求或已被删除"
            redirect(action: "/resourceInfo/index")
        } else {
            def webRootDir = servletContext.getRealPath("/")
            //文件名+扩展名
            reName = resourceInfoInstance.name +'.'+ resourceInfoInstance.extName

            String myName = new String(reName.getBytes("UTF-8"), "ISO8859_1");
            response.reset();
            response.setCharacterEncoding("ISO8859_1");

            response.setContentType("application/octet-stream; charset=UTF-8");
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
                //IE
                myName = URLEncoder.encode(reName, "UTF-8");
            }
            response.setHeader("Content-Disposition", "ResourceInfo; filename=\""+ myName + "\"");
            try {
                def filePath
                filePath = webRootDir + resourceInfoInstance.path
                print("${filePath}")
                File file = new File("${filePath}")
                byte[] b = new byte[10240];
                FileInputStream inStream = null
                int len;
                if (file.exists() && file.isFile()) {
                    inStream = new FileInputStream(file);
                    if (inStream != null) {
                        while ((len = inStream.read(b)) > 0) {
                            response.getOutputStream().write(b, 0, len);
                        }
                        //抛出文件流
                        response.getOutputStream().flush();
                        inStream.close();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace()
            }
        }

    }

    //文件/文件夹删除
    def deletes(String fileId) {
//        print("${fileId}")
        LuceneServices luceneServices=new LuceneServices()
        String[] stringArray = fileId.split(",");
        //要删除文件夹的id集合
        List foIdList = Arrays.asList(stringArray)

        //要删除的当前文件夹实体集合
        def folderList = FolderInfo.findAllByIdInList(foIdList)

        //通过文件id集合删除所有文件
        def resourceList = ResourceInfo.findAllByIdInList(foIdList)
        for (ResourceInfo resourceInfo : resourceList) {
            luceneServices.delIndex(resourceInfo)
            resourceInfo.delete(flush: true)
        }

        //子文件夹实体集合（已删除文件夹id属于谁的OwnerId父类）
        def zfolderList = FolderInfo.findAllByOwnerIdInList(foIdList)

        for (int i = 0; i < folderList.size(); i++) {
            def folder = folderList.get(i)
            def zresourceList=ResourceInfo.findAllByOwnerId(folder.id)
            for (ResourceInfo resourceInfo : zresourceList) {
                luceneServices.delIndex(resourceInfo)
                resourceInfo.delete(flush: true)
            }
            folder.delete(flush: true)
        }
        StringBuffer folds
        while (zfolderList.size() > 0) {
            folds = new StringBuffer()
            for (int i = 0; i < zfolderList.size(); i++) {
                folds.append(zfolderList.get(i).id)
                if (i != zfolderList.size() - 1)
                    folds.append(",")
                def folder = zfolderList.get(i)
                def zresourceList=ResourceInfo.findAllByOwnerId(folder.id)
                for (ResourceInfo resourceInfo : zresourceList) {
                    luceneServices.delIndex(resourceInfo)
                    resourceInfo.delete(flush: true)
                }
                folder.delete(flush: true)
            }
            fileId = folds.toString()

            stringArray = fileId.split(",");
            foIdList = Arrays.asList(stringArray)
//            print "${fileId}"
            zfolderList = FolderInfo.findAllByOwnerIdInList(foIdList)
        }
        render(contentType: "application/json") {
            state = '删除成功'
        }
    }

    def remove(String fileId){
        print(22222222222)
        LuceneServices luceneServices=new LuceneServices()
        def resourceInfo=ResourceInfo.get(fileId)
        if(resourceInfo){
            luceneServices.updateIndex(resourceInfo)
            resourceInfo.common="a"
            resourceInfo.save(flush: true)
        }
        render(contentType: "application/json") {
            state = '移除成功'
        }
    }

    //文件上传

    def upload() {
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return

        }

        /*
        * 此处从页面拿到当前上传文件夹
        * */
        def folderId=params.folderId

        def f
        if (request.getFile('txt_file') != null) {
            f = request.getFile('txt_file')
        }
        if (!f.empty) {
            String originalFilename = f.getOriginalFilename()
            String name = originalFilename
            def extension = ""
            def fileSize = FileUtils.FormetFileSize(f.getSize())
            if (originalFilename.indexOf(".") > 0 && originalFilename.lastIndexOf(".") < originalFilename.length()) {
                // 获取原文件名称
                name = originalFilename.substring(0, originalFilename.lastIndexOf("."))
                // 获取上传文件扩展名
                extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase()
            }
            def flag = true
            int i = 1
            while (flag) {
                if (ResourceInfo.findByNameAndExtNameAndOwnerId(name, extension,folderId)) {
                    if (name.endsWith(")")) {
                        name = name.substring(0, name.lastIndexOf("(")) + "(" + i + ")"
                    } else {
                        name = name + "(" + i + ")"
                    }
                    i++
                    print "${i}"
                } else
                    flag = false
            }

            //网站根目录
            def webRootDir = servletContext.getRealPath("/")
            //文件尺寸限制
            if (f.getSize() > 1024 * 2000000) {
                render(text: "上传文件最大限制为200M")
                return
            }
            //重命名文件，根据当前时间确定文件名
            String reName = new Date().getTime()
            //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
            int hashcode = originalFilename.hashCode();
            int dir1 = hashcode & 0xf;  //0--15
            int dir2 = (hashcode & 0xf0) >> 4;  //0-15
            //上传文件储存路径
            String uploadDir = "uploads/${extension}/" + dir1 + "/" + dir2 + "/" + reName + "/"
            def path = webRootDir + uploadDir + reName + '.' + extension
            def userDir = new File(webRootDir, uploadDir)
            userDir.mkdirs()
            f.transferTo(new File("${path}"))
            def resource = new ResourceInfo()
            resource.id = { generator: 'uuid.hex' }
            resource.name = name
            resource.convertName = reName
            resource.extName = extension
            resource.path = FileUtils.getRelPath(path)
            resource.fileSize = fileSize
            resource.userId = userIds
            resource.ownerId = folderId
            resource.content = ""
            resource.metadata = ""
            resource.type = ""
            resource.author = ""
            resource.artist = ""
            resource.creationDate = ""
            resource.preImgPath = ""
            resource.encoding = ""
            resource.common="a"
            File checkFile = new File("${path}")
            if (checkFile.exists()) {
                if (!resource.save(flush: true)) {
                    render(text: "上传过程中出现问题，请重试")
                    return
                }
                extension = extension.toLowerCase(); //toLowerCase这个方法是字符串变小写的方法
                def message = "${resource.getId() + "," + webRootDir}"
                print("${message}")
                switch (extension) {
                    case ["cda", "wav", "mp3", "wma", "ra", "midi", "agg", "ape", "flac", "aac"]:
                        VideoConverter videoConverter=new VideoConverter();
                        def duration=videoConverter.getDuration(path)
                        resource.duration=duration
                        resource.type = 'music'
                        resource.save(flush: true)
                        sendJMSMessage("queue.summary", message)
                        break
                    case ["doc", "docx", "xls", "xlsx", "ppt", "pptx", "wps", "ods", "odp", "ott",
                          "ots", "otp", "pdf", "txt"]:
                        resource.type = 'word'
                        resource.save(flush: true)
                        sendJMSMessage("queue.processText", message)
                        break
                    case ["bmp", "jpeg", "jpg", "gif", "png","ico"]:
                        /*
                        * 先生成缩略图，再去补全摘要
                        * */
                        if(extension.equals("jpeg")||extension.equals("jpg")){
                            def j = new JpegTool()
                            def c = webRootDir + uploadDir + resource.convertName + '_min.' + extension
                            try {
                                j.SetScale(0.9)
                                j.SetSmallHeight(200)
                                j.doFinal("${path}", "${c}")
                            } catch (JpegToolException e) {
                                e.printStackTrace()
                            }
                            //判断是否生成缩略图
                            File check = new File("${c}")
                            if (check.exists()) {
                                resource.preImgPath = FileUtils.getRelPath(c)
                            }
                        }else{
                            resource.preImgPath=FileUtils.getRelPath(path)
                        }
                        resource.type = 'images'
                        resource.save(flush: true)
                        sendJMSMessage("queue.summary", message)
                        break
                    case ["3gp", "asf", "avi", "flv", "mkv", "mov", "mp4", "mpeg", "rmvb", "wmv", "swf"]:
                        resource.type = 'video'
                        resource.save(flush: true)
                        sendJMSMessage("queue.processVideo", message)
                        break
                    default:
                        resource.type = 'other'
                        resource.save(flush: true)
                        sendJMSMessage("queue.summary", message)
                        break
                }
                render(text: "success:${reName}.${extension}:${originalFilename}", "200")
            } else {
                render(text: "上传过程中出现问题，请重试")

            }
            return

        } else {

            render(text: "您没有选择文件或者文件内容为空")
            return
        }

    }

    //查找全部文件，，地址栏不变化
    def nextIndex() {
        redirect(uri: '/resourceInfo/index')
    }

    //滚动加载
    def rollingLoad(Integer max){
        def FolderInfoList//文件夹集合
        def ResourceInfoList//资源集合
        def loadingNumber//文件夹加载完毕后加载条数
        def rMax = params.rMax//资源页码
        def nextNumber = params.nextLoadingNumber
        def folderId=params.folderId//得到当前要渲染的内容类型
        def search=params.search
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }

        if(max==null){
            //max文件夹加载次数
            max=0
        }

        if(rMax==null){
            //默认为1，记录资源加载次数
            rMax=0
        }
        print("资源加载次数:"+rMax)

        if(nextNumber==null){
            //下一次从哪开始加载
            nextNumber=0
        }
        print("下一次从哪开始加载:"+ nextNumber)

        def pageSize = 20

        if(search==null||search.trim().equals("")){



            FolderInfoList = FolderInfo.findAllByOwnerId(folderId, [max: pageSize, sort: "name", order: "desc", offset: max * pageSize])//"max:"每页加载多少条，"offSize:"下次从哪里开始加载

            //当最后一页文件夹没有20条数据时，去获取文件数据
            if(FolderInfoList.size()<pageSize){

                if(FolderInfoList.size()==0){
                    ResourceInfoList = ResourceInfo.findAllByOwnerId(folderId, [max: pageSize-FolderInfoList.size(), sort: "name", order: "asc", offset: max * pageSize])
                }else{
                    ResourceInfoList = ResourceInfo.findAllByOwnerId(folderId, [max: pageSize-FolderInfoList.size(), sort: "name", order: "asc", offset: nextNumber])
                }
                //拿pageSize-FolderInfoList.size()条文件资源
//            ResourceInfoList = ResourceInfo.findAllByOwnerId(FolderInfoInstance.id, [max: pageSize-FolderInfoList.size(), sort: "name", order: "desc", offset: max * pageSize])

                if(FolderInfoList.size()!=0){
                    loadingNumber = pageSize-FolderInfoList.size()//接着文件夹后加载条数
                    print("欠多少条没加载："+loadingNumber)
                }else {
                    print(nextNumber+"++++++++++++++++")
                    ResourceInfoList = ResourceInfo.findAllByOwnerId(folderId, [max: pageSize-FolderInfoList.size(), sort: "name", order: "asc", offset: nextNumber])
                }
            }

            //返回值
            if(max==0){
                [ResourceInfoList: ResourceInfoList, FolderInfoList: FolderInfoList, FolderInfoId: folderId,nextLoadingNumber:loadingNumber]
            }else{
                if(FolderInfoList.size()==0){
                    //文件页码
                    rMax++
                    int num = Integer.parseInt(nextNumber);
                    loadingNumber=num+20
                    print("=================="+nextNumber)
                }else{
                    //文件夹页码
                    max++
                }

                String s = String.valueOf(max);
                render(contentType: "application/json") {
                    folderInfoList = FolderInfoList
                    resourceInfoList = ResourceInfoList
                    folderInfoId = folderId
                    maxs = s
                    rMaxs = rMax
                    nextLoadingNumber = loadingNumber
                }
            }
        }else{
            LuceneServices ls=new LuceneServices();

            ResourceInfoList =ls.findIndex(search,userIds,null,Integer.parseInt(nextNumber),20)
            rMax++
            print("${search}"+ResourceInfoList.size())
            FolderInfoList=new ArrayList<FolderInfo>()
            int num = Integer.parseInt(nextNumber);
            loadingNumber=num+20
            render(contentType: "application/json") {
                folderInfoList = FolderInfoList
                resourceInfoList = ResourceInfoList
                folderInfoId = folderId
                maxs = 0
                rMaxs = rMax
                nextLoadingNumber = loadingNumber
            }
        }

    }


    def find( String search){
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def ResourceInfoList

        if (search==null||search.equals("")){
            redirect(uri: '/')
            return
        }

        print(search)
        LuceneServices ls=new LuceneServices();

        ResourceInfoList =ls.findIndex(search,userIds,null,0,20)
        render(view: "index", model: ['ResourceInfoList': ResourceInfoList,nextLoadingNumber:20,'search':search,'fillType':"home"])
    }

    //查找该文件夹里的资源
    def findResourcesByFolder(String id) {
        def userIds = null
        def folderInfo = null
        def folderList = new ArrayList<FolderInfo>()
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def folderInfoInstance = FolderInfo.findById(id)
       //得到面包线
        def ownerId = folderInfoInstance.ownerId
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

        //获取当前点击文件夹id
        def folderInfoId = id

        def FolderInfoList
        def ResourceInfoList
        def loadingNumber//记录加载了多少条资源
        def pageSize = 20

        FolderInfoList = FolderInfo.findAllByOwnerId(folderInfoId, [max: pageSize, sort: "name", order: "asc", offset: 0 * pageSize])

        if(FolderInfoList.size()<pageSize){
            loadingNumber = pageSize-FolderInfoList.size()
            ResourceInfoList = ResourceInfo.findAllByOwnerId(folderInfoId, [max: loadingNumber, sort: "name", order: "asc", offset: 0 * pageSize])
        }

        render(view: "index", model: ['ResourceInfoList': ResourceInfoList, 'FolderInfoList': FolderInfoList, 'FolderInfoId': folderInfoId, 'FolderList': dFolderList,nextLoadingNumber:loadingNumber,nowPage:'sss'])
    }

    def index(Integer max) {
        def userIds = null
        if(max==null){
            //max文件夹加载次数
            max=0
        }
        def FolderInfoList
        def ResourceInfoList
        def loadingNumber//记录加载了多少条资源
        def pageSize = 20

        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }

        def FolderInfoInstance = FolderInfo.findByOwnerIdAndName(userIds, "home")

        FolderInfoList = FolderInfo.findAllByOwnerId(FolderInfoInstance.id, [max: pageSize, sort: "name", order: "asc", offset: max * pageSize])

        if(FolderInfoList.size()<pageSize){
            loadingNumber = pageSize-FolderInfoList.size()
            ResourceInfoList = ResourceInfo.findAllByOwnerId(FolderInfoInstance.id, [max: loadingNumber, sort: "name", order: "asc", offset: max * pageSize])
        }

        [ResourceInfoList: ResourceInfoList, FolderInfoList: FolderInfoList, FolderInfoId: FolderInfoInstance.id,nextLoadingNumber:loadingNumber,fillType: 'home']
    }

    def showMusic(){
        def mId= params.mId
        def resourceInfo=ResourceInfo.get(mId)
        [resourceInfo: resourceInfo]
    }
    //音乐页播放音乐
    def aShowMusic(){
        def mId= params.id
        def resourceInfo=ResourceInfo.get(mId)
        render(contentType: "application/json") {
            value=resourceInfo
        }
    }


    def showImg(String imgId){
        def resourceInfo=ResourceInfo.get(imgId)
        render(contentType: "application/json"){
            state = "200"
            value=resourceInfo
        }

    }
    def showVideo(String id){
        def ResourceInfoList
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        print("播放视频")
        def resourceInfo=ResourceInfo.get(id)
        def firstUrl = ''
        firstUrl = resourceInfo.path
        print "${firstUrl}"

        LuceneServices luceneServices=new LuceneServices()
        ResourceInfoList=luceneServices.findIndex(resourceInfo,userIds,"video",0,5)

        [firstUrl:firstUrl,resourceInfo:resourceInfo,resourceInfoList:ResourceInfoList]
    }

    def show(String id) {
        print"我是文档显示方法"
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def resourceInfo=ResourceInfo.get(id)
        def resourceInfoList
        def resourceList
        if(resourceInfo!=null){
            if(resourceInfo.relation==null){
                resourceList==null
            }else{

                String relations = resourceInfo.relation

                def is = relations.replace(id,"")

                String[] stringArray = is.split(",");
                List arrays = Arrays.asList(stringArray)
                //            resourceList = ResourceInfo.findAllByIdInList(arrays)
                resourceList = ResourceInfo.findAllByIdInList(arrays, [max: 5, sort: "name", order: "desc", offset: 0])

            }
            LuceneServices luceneServices=new LuceneServices()
            resourceInfoList=luceneServices.findIndex(resourceInfo,userIds,null,0,8)
        }
        [resourceInfo:resourceInfo,resourceInfoList:resourceInfoList,ResourceList:resourceList]

    }


    def show2(){
        print(params.file)
        def firstUrl = ''
        firstUrl = params.file
        [firstUrl:firstUrl]
    }

    //显示关联文件
    def showLink(String id){
        def resourceInfo = ResourceInfo.findById(id)
        def resourceList
        String relations = resourceInfo.relation

        List arrays
        if(relations!=null&&!relations.equals("")){
            String[] stringArray = relations.split(",");
            arrays = Arrays.asList(stringArray)
        }
        resourceList = ResourceInfo.findAllByIdInList(arrays)
        render(contentType: "application/json"){
            state = "200"
            resourceInfoList=resourceList
        }

    }


    //不同类别的文件列表
    def categoryPage(String id){
        def resourceInfoList = null
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        switch (id) {
            case "activeFile":
                resourceInfoList = ResourceInfo.findAllByCommonAndUserId(id,userIds, [max: 24, sort: "name", order: "asc", offset:0])
//                print(ResourceInfo.countByCommon("activeFile")+"个常用")
                render(view: "activePage", model: ['ResourceInfoList': resourceInfoList,'fillType':'activeFile'])
                //常用文件
                break
            case "video":
                resourceInfoList = ResourceInfo.findAllByTypeAndUserId(id,userIds, [max: 30, sort: "name", order: "asc", offset:0])
                render(view: "videoPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'video'])
                //视频
                break
            case "music":

                resourceInfoList = ResourceInfo.findAllByTypeAndUserId(id,userIds, [max: 24, sort: "name", order: "asc", offset:0])
                render(view: "musicPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'music'])
                //音乐
                break
            case "word":
                //文档
                resourceInfoList = ResourceInfo.findAllByTypeAndUserId(id,userIds, [max: 24, sort: "name", order: "asc", offset:0])
                render(view: "textPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'word'])
                break
            case "images":
                //图片
                resourceInfoList = ResourceInfo.findAllByTypeAndUserId(id,userIds, [max: 48, sort: "name", order: "asc", offset:0])
                print(ResourceInfo.countByUserIdAndType(userIds,id))
                render(view: "imgPage", model: ['ResourceInfoList': resourceInfoList, 'fillType':'images'])
                break
            case "other":
                resourceInfoList = ResourceInfo.findAllByTypeAndUserId(id,userIds, [max: 24, sort: "name", order: "asc", offset:0])
                print(resourceInfoList.size())
//                print(ResourceInfo.countByUserIdAndType(userIds,id))
                render(view: "otherPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'other'])
                //其它
                break

        }
    }
    //不同类别的文件搜索
    def categorySearch(String search,String fillType){
        if(search==null||search.trim().equals("")){
            redirect(action: 'categoryPage',id: fillType)
            return
        }
        def resourceInfoList = null
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        LuceneServices luceneServices=new LuceneServices()
        switch (fillType) {
            case "activeFile":
                resourceInfoList=luceneServices.findIndex1(search,userIds,fillType,0,24)
                render(view: "activePage", model: ['ResourceInfoList': resourceInfoList,'fillType':'activeFile','search':search])
                break
            case "video":
                resourceInfoList=luceneServices.findIndex(search,userIds,fillType,0,30)
                render(view: "videoPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'video','search':search])
                break
            case "music":
                resourceInfoList=luceneServices.findIndex(search,userIds,fillType,0,24)
                render(view: "musicPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'music','search':search])
                break
            case "word":
                resourceInfoList=luceneServices.findIndex(search,userIds,fillType,0,24)
                render(view: "textPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'word','search':search])
                break
            case "images":
                resourceInfoList=luceneServices.findIndex(search,userIds,fillType,0,48)
                render(view: "imgPage", model: ['ResourceInfoList': resourceInfoList, 'fillType':'images','search':search])
                break
            case "other":

                resourceInfoList=luceneServices.findIndex(search,userIds,fillType,0,24)

                render(view: "otherPage", model: ['ResourceInfoList': resourceInfoList,'fillType':'other','search':search])
                break
        }
    }
    //资源分类显示分页
    def pagination(){

        print("pagination" +"<"+ params.pageNo)
        def userIds = null
        try {
            userIds = session.userInfo.id
        }catch (Exception e){
            redirect(uri: '/')
            return
        }
        def ResourceInfoList
        String fillType=params.fillType
        String search=params.search
        String page=params.pageNo
        int pageNo=Integer.parseInt(page)+1
        print("pageNo==>>>>>${pageNo}")
        int pageSize = 24;
        int count = 0;
        if(search==null||search.trim().equals("")){

            switch (fillType) {
                case "activeFile":
                    ResourceInfoList = ResourceInfo.findAllByCommonAndUserId(fillType,userIds, [max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*pageSize ])
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }

                    break
                case "video":
                    ResourceInfoList = ResourceInfo.findAllByTypeAndUserId(fillType,userIds, [max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*30 ])
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "music":
//                    print(ResourceInfo.countByTypeAndUserId(fillType,userIds));
                    ResourceInfoList = ResourceInfo.findAllByTypeAndUserId(fillType,userIds, [max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*pageSize ])
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "word":
                    ResourceInfoList = ResourceInfo.findAllByTypeAndUserId(fillType,userIds, [max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*pageSize ])
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "images":
                    print('images')
                    ResourceInfoList = ResourceInfo.findAllByTypeAndUserId(fillType,userIds, [max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*48 ])
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "other":
                    ResourceInfoList = ResourceInfo.findAllByTypeAndUserId(fillType,userIds, [max: pageSize, sort: "name", order: "asc", offset:(pageNo-1)*pageSize ])
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
            }
        }else{
            LuceneServices luceneServices=new LuceneServices()
            switch (fillType) {
                case "activeFile":
                    ResourceInfoList=luceneServices.findIndex1(search,userIds,fillType,(pageNo-1)*pageSize,pageSize)
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "video":
                    ResourceInfoList=luceneServices.findIndex(search,userIds,fillType,(pageNo-1)*30,pageSize)
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "music":
                    ResourceInfoList=luceneServices.findIndex(search,userIds,fillType,(pageNo-1)*pageSize,pageSize)
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "word":
                    ResourceInfoList=luceneServices.findIndex(search,userIds,fillType,(pageNo-1)*pageSize,pageSize)
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "images":
                    ResourceInfoList=luceneServices.findIndex(search,userIds,fillType,(pageNo-1)*48,pageSize)
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
                case "other":
                    ResourceInfoList=luceneServices.findIndex(search,userIds,fillType,(pageNo-1)*pageSize,pageSize)
                    render(contentType: "application/json"){
                        resourceInfoList=ResourceInfoList
                    }
                    break
            }

        }
    }


}
