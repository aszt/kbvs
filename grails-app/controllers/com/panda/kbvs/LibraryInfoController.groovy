package com.panda.kbvs

import kbvs.converter.LuceneServices

import java.text.SimpleDateFormat

class LibraryInfoController {

    //知识库列表
    def index() {
        def userIds
        def libraryInfoList
        try {
            userIds = session.userInfo.id
        } catch (Exception e) {
            redirect(uri: '/')
            return
        }
        libraryInfoList = LibraryInfo.findAllByUserId(userIds)
        render(view: "index", model: ['libraryInfoList': libraryInfoList])
    }

    def validate(String name, String resName, String resContent1, String resContent2, String resContent3, String resAuthor, String beginTime, String endTime, String types){
        if ((resName==null||resName.trim().equals(""))&&((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
            render(contentType: "application/json") {
                state =200
            }
        }else {
            render(contentType: "application/json") {
                state = 500
            }
        }

    }
    //添加知识库
    def addLibrary(String name, String resName, String resContent1, String resContent2, String resContent3, String resAuthor, String beginTime, String endTime, String types) {
        def userIds = null
        def resourceIds = ""
        def resourceIdList
        Date b
        Date e
        LuceneServices luceneServices = new LuceneServices()
        LibraryInfo libraryInfo = new LibraryInfo()
        def resourceInfoList
        try {
            userIds = session.userInfo.id
        } catch (Exception error) {
            redirect(uri: '/')
            return
        }

        if (resName!=null&&!resName.trim().equals("")) {
            if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceIdList.get(i)
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    print "${types}"
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,2,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceIdList.get(i)
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,2,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,2,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,3,4,5")
                print "${types}"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,2,3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
        }else if(((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals(""))&&(resName==null||resName.trim().equals(""))){
            if((resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))){
                print("2,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceIdList.get(i)
                    }
                } else {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    print "${types}"
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }else if((resAuthor != null &&! resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))){
                print("2,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
            else if((resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && beginTime.trim().equals(""))){
                print("2,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }else if((resAuthor != null &&! resAuthor.trim().equals("")) && (beginTime != null && beginTime.trim().equals(""))){
                print("2,3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
        }else if(((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals(""))&&(resName==null||resName.trim().equals(""))){
            if((resAuthor!=null&&!resAuthor.trim().equals(""))&&(beginTime == null || beginTime.trim().equals(""))){
                print("3,5")
                if (types == null || types.trim().equals("")) {
                    resourceInfoList = ResourceInfo.findAllByUserIdAndArtistIlike(userIds,"%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndTypeInList(userIds,"%" + resAuthor + "%", typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if((resAuthor!=null&&!resAuthor.trim().equals(""))&&(beginTime != null && !beginTime.trim().equals(""))){
                print("3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
            else if((resAuthor==null||resAuthor.trim().equals(""))&&(beginTime != null && !beginTime.trim().equals(""))){
                print("4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
        }
        def flag = true
        int i = 1
        while (flag) {
            if (LibraryInfo.findByUserIdAndName(userIds,name)) {
                if (name.endsWith(")")) {
                    name = name.substring(0, name.lastIndexOf("(")) + "(" + i + ")"
                } else {
                    name = name + "(" + i + ")"
                }
                i++
            } else
                flag = false
        }
        print"resourceIds:${resourceIds}"
        libraryInfo.resourceIds = resourceIds
        libraryInfo.name = name
        if(types == null || types.trim().equals("")){
            types="video,music,word,images,other"
        }
        libraryInfo.types=types
        libraryInfo.resName = resName
        libraryInfo.resContent1 = resContent1
        libraryInfo.resContent2=resContent2
        libraryInfo.resContent3=resContent3
        libraryInfo.resAuthor = resAuthor
        libraryInfo.beginTime = b
        libraryInfo.endTime = e
        libraryInfo.filtration=""
        libraryInfo.userId=userIds
       if(libraryInfo.save(flush: true)){
           render(contentType: "application/json") {
               state = 200
               value=libraryInfo
           }
       }else{
           render(contentType: "application/json") {
               state = 500
           }
       }


        }

        def show(String id) {
            print "${id}"
            def libraryInfo=LibraryInfo.get(id)
            def resourceIds=libraryInfo.resourceIds
            print resourceIds
            String[] stringArray = resourceIds.split(",");
            List resourceIdsList = Arrays.asList(stringArray)
            def resourceInfo=ResourceInfo.findAllByIdInList(resourceIdsList)
            render(view: "show", model: ['ResourceInfoList': resourceInfo,libraryInfo:libraryInfo])

        }
//删除知识库
    def delete(String id) {
        def userIds
        try {
            userIds = session.userInfo.id
        } catch (Exception e) {
            redirect(uri: '/')
            return
        }
        def libraryInfo=LibraryInfo.get(id)
        libraryInfo.delete(flush: true)
        render(contentType: "application/json") {
            state = 200
        }

    }

    //更新知识库
    def ajaxUpdate(String id){
        def userIds
        def resourceIds = ""
        def resourceIdList
        Date b
        Date e
        LuceneServices luceneServices = new LuceneServices()
        def resourceInfoList
        try {
            userIds = session.userInfo.id
        } catch (Exception error) {
            redirect(uri: '/')
            return
        }
        def libraryInfo=LibraryInfo.get(id)
        String resName=libraryInfo.resName
        String resContent=libraryInfo.resContent1+libraryInfo.resContent2+libraryInfo.resContent3
        String resAuthor=libraryInfo.resAuthor
        String beginTime=libraryInfo.beginTime
        String endTime=libraryInfo.endTime
        String types=libraryInfo.types
        String filtration=libraryInfo.filtration
        if (resName!=null&&!resName.trim().equals("")) {
            if ((resContent == null || resContent.trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        if(filtration.indexOf(resourceIdList.get(i))<1){
                            resourceIds = resourceIds + "," + resourceIdList.get(i)
                        }
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    print "${types}"
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            } else if ((resContent != null && !resContent.trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,2,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        if(filtration.indexOf(resourceIdList.get(i))<1){
                            resourceIds = resourceIds + "," + resourceIdList.get(i)
                        }
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            } else if ((resContent == null || resContent.trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            } else if ((resContent == null || resContent.trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            } else if ((resContent != null && !resContent.trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,2,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            } else if ((resContent != null && !resContent.trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,2,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            } else if ((resContent == null || resContent.trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,3,4,5")
                print "${types}"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            } else if ((resContent != null && !resContent.trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,2,3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName,resContent, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }
                    }
                }
            }
        }else if((resContent != null && !resContent.trim().equals(""))&&(resName==null||resName.trim().equals(""))){
            if((resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))){
                print("2,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        if(filtration.indexOf(resourceIdList.get(i))<1){
                            resourceIds = resourceIds + "," + resourceIdList.get(i)
                        }
                    }
                } else {
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    print "${types}"
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            }else if((resAuthor != null &&! resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))){
                print("2,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                } else {
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            }
            else if((resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && beginTime.trim().equals(""))){
                print("2,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            }else if((resAuthor != null &&! resAuthor.trim().equals("")) && (beginTime != null && beginTime.trim().equals(""))){
                print("2,3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            }
        }else if((resContent == null || resContent.trim().equals(""))&&(resName==null||resName.trim().equals(""))){
            if((resAuthor!=null&&!resAuthor.trim().equals(""))&&(beginTime == null || beginTime.trim().equals(""))){
                print("3,5")
                if (types == null || types.trim().equals("")) {
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlike(userIds,"%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                } else {
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndTypeInList(userIds,"%" + resAuthor + "%", typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            } else if((resAuthor!=null&&!resAuthor.trim().equals(""))&&(beginTime != null && !beginTime.trim().equals(""))){
                print("3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            }
            else if((resAuthor==null||resAuthor.trim().equals(""))&&(beginTime != null && !beginTime.trim().equals(""))){
                print("4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        if(filtration.indexOf(resourceInfoList.get(i).id)<1){
                            resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                        }

                    }
                }
            }
        }
        libraryInfo.resourceIds=resourceIds
        if(libraryInfo.save(flush: true)){
            render(contentType: "application/json") {
                state = 200
            }
        }else{
            render(contentType: "application/json") {
                state = 500
            }
        }

    }
    //从知识库中移除
    def deleteRes(String fileId,String libraryId){
        def libraryInfo
        libraryInfo=LibraryInfo.get(libraryId)
        libraryInfo.resourceIds=libraryInfo.resourceIds.replace(fileId,"")
        libraryInfo.filtration=libraryInfo.filtration+fileId+","
        if(libraryInfo.save(flush: true)){
            render(contentType: "application/json") {
                state = 200
            }
        }else{
            render(contentType: "application/json") {
                state = 500
            }
        }
    }



    def update(String id,String name, String resName, String resContent1, String resContent2, String resContent3, String resAuthor, String beginTime, String endTime, String types) {
        def userIds = null
        def resourceIds = ""
        def resourceIdList
        Date b
        Date e
        LuceneServices luceneServices = new LuceneServices()
        def libraryInfo =LibraryInfo.get(id)
        def resourceInfoList
        try {
            userIds = session.userInfo.id
        } catch (Exception error) {
            redirect(uri: '/')
            return
        }

        if (resName!=null&&!resName.trim().equals("")) {
            if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceIdList.get(i)
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    print "${types}"
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,2,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceIdList.get(i)
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))) {
                print("1,2,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,2,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,3,4,5")
                print "${types}"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if (((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals("")) && (resAuthor != null && !resAuthor.trim().equals("")) && (beginTime != null && !beginTime.trim().equals(""))) {
                print("1,2,3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndex(resName, resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
        }else if(((resContent1 + resContent2 + resContent3) != null && !(resContent1 + resContent2 + resContent3).trim().equals(""))&&(resName==null||resName.trim().equals(""))){
            if((resAuthor == null || resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))){
                print("2,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    for (int i = 0; i < resourceIdList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceIdList.get(i)
                    }
                } else {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    print "${types}"
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInList(userIds, resourceIdList, typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }else if((resAuthor != null &&! resAuthor.trim().equals("")) && (beginTime == null || beginTime.trim().equals(""))){
                print("2,3,5")
                if (types == null || types.trim().equals("")) {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndAuthorIlike(userIds, resourceIdList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlike(userIds, resourceIdList, typeList, "%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
            else if((resAuthor == null || resAuthor.trim().equals("")) && (beginTime != null && beginTime.trim().equals(""))){
                print("2,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndDateCreatedBetween(resourceIdList, userIds, b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndDateCreatedBetween(userIds, resourceIdList, typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }else if((resAuthor != null &&! resAuthor.trim().equals("")) && (beginTime != null && beginTime.trim().equals(""))){
                print("2,3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    resourceInfoList = ResourceInfo.findAllByIdInListAndUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceIdList = luceneServices.findIndexC(resContent1 + resContent2 + resContent3, userIds)
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndIdInListAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, resourceIdList, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
        }else if(((resContent1 + resContent2 + resContent3) == null || (resContent1 + resContent2 + resContent3).trim().equals(""))&&(resName==null||resName.trim().equals(""))){
            if((resAuthor!=null&&!resAuthor.trim().equals(""))&&(beginTime == null || beginTime.trim().equals(""))){
                print("3,5")
                if (types == null || types.trim().equals("")) {
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlike(userIds,"%" + resAuthor + "%")
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndTypeInList(userIds,"%" + resAuthor + "%", typeList)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            } else if((resAuthor!=null&&!resAuthor.trim().equals(""))&&(beginTime != null && !beginTime.trim().equals(""))){
                print("3,4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
            else if((resAuthor==null||resAuthor.trim().equals(""))&&(beginTime != null && !beginTime.trim().equals(""))){
                print("4,5")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (endTime == null || endTime.trim().equals("")) {
                    Date date = new Date();
                    endTime = sdf.format(date)
                }
                if (types == null || types.trim().equals("")) {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    resourceInfoList = ResourceInfo.findAllByUserIdAndAuthorIlikeAndDateCreatedBetween(resourceIdList, userIds, "%" + resAuthor + "%", b, e,)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                } else {
                    b = sdf.parse(beginTime);
                    e = sdf.parse(endTime);
                    String[] stringArray = types.split(",");
                    List typeList = Arrays.asList(stringArray)
                    resourceInfoList = ResourceInfo.findAllByUserIdAndTypeInListAndAuthorIlikeAndDateCreatedBetween(userIds, "%" + resAuthor + "%", typeList, b, e)
                    for (int i = 0; i < resourceInfoList.size(); i++) {
                        resourceIds = resourceIds + "," + resourceInfoList.get(i).id
                    }
                }
            }
        }

        def flag = true
        int i = 1
        while (flag) {
            if (LibraryInfo.findByUserIdAndName(userIds,name)) {
                if (name.endsWith(")")) {
                    name = name.substring(0, name.lastIndexOf("(")) + "(" + i + ")"
                } else {
                    name = name + "(" + i + ")"
                }
                i++
            } else
                flag = false
        }
        print"resourceIds:${resourceIds}"
        libraryInfo.resourceIds = resourceIds
        libraryInfo.name = name
        if(types == null || types.trim().equals("")){
            types="video,music,word,images,other"
        }
        libraryInfo.types=types
        print"resName:${resName}"
        libraryInfo.resName = resName
        libraryInfo.resContent1 = resContent1
        libraryInfo.resContent2=resContent2
        libraryInfo.resContent3=resContent3
        libraryInfo.resAuthor = resAuthor
        libraryInfo.beginTime = b
        libraryInfo.endTime = e
        libraryInfo.filtration=""
        libraryInfo.userId=userIds
        if(libraryInfo.save(flush: true)){
            render(contentType: "application/json") {
                state = 200
                value=libraryInfo
            }
        }else{
            render(contentType: "application/json") {
                state = 500
            }
        }


    }





}
