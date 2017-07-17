<%--
  Created by IntelliJ IDEA.
  User: c-kx
  Date: 2016/6/11
  Time: 19:43
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>分享页面</title>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css',file: 'bootstrap.min.css')}">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css',file: 'link.css')}">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css',file: 'base.css')}">
    <link rel="shortcut icon" href="${resource(dir: 'images',file: 'favicon.png')}">
    <g:javascript src="main.js"/>
    <script type="text/javascript">
        $(function () {
            $('[data-toggle="tooltip"]').tooltip();
        })

        //单文件下载
        function singleFileDownload(id){
            window.location = "<g:createLink controller="resourceInfo" action="singleFileDownload" />?id=" +id;
        }
</script>
</head>

<body>

<nav class="navbar navbar-fixed-top" style="background-color: #3083EB;">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#xNav">
            <span class="icon-bar" style="background:#FFF"></span>
            <span class="icon-bar" style="background:#FFF"></span>
            <span class="icon-bar" style="background:#FFF"></span>
        </button>
        <g:link action="nextIndex" class="navbar-brand logo" style="width:160px; margin-top:-15px;"><img src="${resource(dir: 'images',file: 'LOGOO.png')}"></g:link>
    </div>
    <div class="collapse navbar-collapse xNav">
        <ul class="nav navbar-nav nav_ul"
            style="margin-left:40px;">
            <li><g:link controller="resourceInfo" action="nextIndex"><i class="fa fa-home" style="font-size: 20px;"></i> 我的主页</g:link></li>
            <li class="wode group"><g:link controller="groupInfo" action="index"><i class="fa fa-group" style="font-size: 16px;"></i> 我的组群</g:link></li>
        </ul>

        <div class="user_myself">
        %{--头像--}%
            <img class="user_header_pic hImgPath" src="${resource(dir: '',file: session.imagePath)}">
            <div role="presentation" class="dropdown user_name">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" style=" color: #fff">
                    <label>${session.userName}</label> <span class="caret" style="color: #fff;"></span>
                </a>
                <ul class="dropdown-menu"  style="position: absolute;left:-50px; min-width: 100px;">
                    <li style="line-height: 20px; font-size: 12px;"><g:link controller="userInfo" action="personal"><span class="glyphicon glyphicon-user" ></span>个人中心</g:link></li>
                    <li role="separator" class="divider"></li>
                    <li style="line-height: 20px; font-size: 12px;"><g:link controller="userInfo" action="loginOut"><span class="glyphicon glyphicon-log-out"></span>退出账号</g:link></li>
                </ul>
            </div>
        </div>
    </div>

</nav>

<!--导航栏 end-->

    <div class="content" style="margin-top: 48px;">
        <div class="rightjialeft">
            <div class="left-content">
                <div class="header">
                    <div class="fileicon share-ico"></div>
                    <div class="file-name-header">
                        <label>${ShareInfo.name}</label>
                    </div>
                    <div class="link-time">
                        <small><span>分享时间：</span><span>${ShareInfo.dateCreated.format('yyyy-MM-dd')}</span></small>
                    </div>
                </div>

                <div class="small">
                    <span class="txt"><a href="#>">全部文件</a> > <span style="color:#666;">
                        <g:link controller="shareInfo" action="show" id="${ShareInfo.id}">
                        ${ShareInfo.name}
                        %{--面包线--}%
                            <g:each in="${FolderList}" status="i" var="folderList">
                                >&nbsp;
                                <g:link controller="shareInfo" action="findResourcesByFolder" id="${folderList.id}" params="[userId: UserInfo.id, shareId: ShareInfo.id]">
                                    ${folderList.name}
                                </g:link>
                            </g:each>

                        </g:link>
                    </span></span>
                </div>

                <div class="clear"></div>
                <div class="anniu-group">

                    
                </div>

                <div class="body-nav">
                    <ul class=" list-unstyled list-col">
                        <li class="first">
                            <input type="checkbox" name="love" onClick="selAllNo();" id="boxid">
                            <span class="file-name" style="margin-left:5px;">文件名</span>
                        </li>
                        <li class="second">
                            <span clas="file-size">大小</span>
                        </li>
                        <li class="three">
                            <span class="file-date">创建日期</span>
                        </li>
                    </ul>

                    <div style="clear:both;"></div>

                    <div class="showscrollbar" style="height:480px; overflow:scroll; overflow-x:hidden; overflow-y:hidden;">
                        <div class="showfilescrollbar">
                            <g:each in="${FolderInfoList}" status="i" var="folderInfoListInstance">
                            %{--重命名包裹input输入框--}%
                                <div class="${folderInfoListInstance.id}">
                                    <div class="user-file" id="${folderInfoListInstance.id}">
                                        %{--<div class="user-file" id="s_${folderInfoListInstance.id}">--}%
                                        <div class="col user-filename">
                                            <input type="checkbox" name="checkAll" value="${folderInfoListInstance.id}" onClick="setSelectAll()">
                                            <div class="fileicon folder"></div>
                                            <span style=""></span>

                                            <span class="file-name">
                                                <g:link controller="shareInfo" action="findResourcesByFolder" id="${folderInfoListInstance.id}" params="[userId: UserInfo.id, shareId: ShareInfo.id]">
                                                %{--将文件夹id带给控制层--}%
                                                    ${fieldValue(bean: folderInfoListInstance, field: "name")}
                                                </g:link>
                                            </span>
                                            <i class="anniu fa fa-download" data-toggle="tooltip" data-placement="left" title="下载" onclick="singleFileDownload('${folderInfoListInstance.id}')"></i>
                                        </div>

                                        <div class="col user-filesize">
                                            <span class="file-size">${fieldValue(bean: folderInfoListInstance, field: "")}-</span>
                                        </div>
                                        <div class="col user-filedate">
                                            <span class="file-date">${folderInfoListInstance.dateCreated.format('yyyy-MM-dd')}</span>
                                        </div>
                                    </div>
                                </div>
                            </g:each>

                            <g:each in="${ResourceInfoList}" status="i" var="resourceInfoInstance">
                                <div class="${resourceInfoInstance.id}">
                                    %{--重命名放到这里--}%
                                    <div>
                                        <div id="${resourceInfoInstance.id}">
                                            %{--<div id="b_${resourceInfoInstance.id}">--}%
                                            <div class="user-file" id="s_${resourceInfoInstance.id}">

                                                <div class="col user-filename">
                                                    <input type="checkbox" name="checkAll" value="${resourceInfoInstance.id}" onClick="setSelectAll()">
                                                    <g:if test="${resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'}">
                                                        <div class="fileicon fileicon-pic" ></div>
                                                    </g:if>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx'}">
                                                        <div class="fileicon fileicon-word" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx'}">
                                                        <div class="fileicon fileicon-xls" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'}">
                                                        <div class="fileicon fileicon-music" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'}">
                                                        <div class="fileicon fileicon-video" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt'}">
                                                        <div class="fileicon fileicon-ppt" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip'}">
                                                        <div class="fileicon fileicon-yasuo" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='pdf'}">
                                                        <div class="fileicon fileicon-pdf" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='txt'}">
                                                        <div class="fileicon fileicon-text" ></div>
                                                    </g:elseif>
                                                    <g:elseif test="${resourceInfoInstance?.extName=='torrent'}">
                                                        <div class="fileicon fileicon-bt" ></div>
                                                    </g:elseif>
                                                    <g:else>
                                                        <div class="fileicon fileicon-other"></div>
                                                    </g:else>
                                                    <span style=""></span>
                                                    <span class="file-name">
                                                        <g:if test="${resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'}">
                                                            <a onclick="showImg('${resourceInfoInstance.id}')" href="#">${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}</a>
                                                        </g:if>
                                                        <g:elseif test="${resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'}">
                                                            <g:link action="showVideo" id="${resourceInfoInstance.id}">
                                                                ${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}
                                                            </g:link>
                                                        </g:elseif>
                                                        <g:elseif test="${resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'}">
                                                            <a onclick="showMusic('${resourceInfoInstance.id}')" href="#">${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}</a>
                                                        </g:elseif>
                                                        <g:elseif test="${resourceInfoInstance?.type=='other'}">
                                                            <a onclick="showOther()" href="#">${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}</a>
                                                        </g:elseif>
                                                        <g:else>
                                                            <g:link controller="resourceInfo" action="show" id="${resourceInfoInstance.id}">
                                                                ${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}
                                                            </g:link>
                                                        </g:else>
                                                    </span>
                                                    <i class="anniu fa fa-download" data-toggle="tooltip" data-placement="left" title="下载" onclick="singleFileDownload('${resourceInfoInstance.id}')"></i>
                                                </div>
                                                <div class="col user-filesize">
                                                    <span class="file-size">${fieldValue(bean: resourceInfoInstance, field: "fileSize")}B</span>
                                                </div>
                                                <div class="col user-filedate">
                                                    <span class="file-date">${resourceInfoInstance.dateCreated.format('yyyy-MM-dd')}</span>
                                                </div>
                                            </div>

                                            <div class="a_${resourceInfoInstance.id}">
                                                <div class="gaiyao">
                                                    <span>${raw(resourceInfoInstance.remark)}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </g:each>



                        </div>
                    </div>


                </div>
            </div>
            <div class="right-content">
                    <div class="right-content-header">
                        <img src="${resource(dir:'',file:UserInfo.imagePath)}">
                    </div>
                    <div class="user-header-name">
                        <label>${UserInfo.loginName}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>


<!--图片显示-->
<div id="imageScreen" style="height: 696px;">
    <div class="hd">
        <div class="imgInfo">
            <span class="info showImgName">显示的文件名</span>
            <span class="num">
                (
                <span class="currentNum">1</span>
                /
                <span class="totalNums">1</span>
                )
            </span>
        </div>
        <div class="toolbar">
            <p class="close"></p>
            <ul class=" list-unstyled">
                <li class="vie">
                    <a target="_blank" class="showRaw" href="#">查看原图</a>
                </li>
                <li class="dow">

                </li>

            </ul>
        </div>
    </div>
    <div class="bd">
        <div id="imgList" class="imgList-my">
            <div class="bimg cur" style="display: block;">
                <img class="showImgPath" src="">
            </div>
            <div id="imgMask"></div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function showOther(){
        layer.msg('该文件无法预览', {
            icon: 2,
            time: 1000//时间设置无反应
        });
    }
    //播放音乐
    function showMusic(mId){
        layer.closeAll('iframe');
        layer.open({
            type: 2,
            title: false,
            closeBtn: 2, //显示关闭按钮
            shade:false,
            area: ['300px', '80px'],
            offset: 'rb', //右下角弹出
            content: ['<g:createLink action="showMusic"/>?mId='+mId, 'no']//iframe的url，no代表不显示滚动条
        });
    }


    //查看图片
    function showImg(imgId){
        var fillType = $("#fillType").val();
//		alert(fillType)
        $.ajax({
                    url:'<g:createLink controller="resourceInfo" action="showImg" />',
                    type:'post',
                    dataType:'json',
                    data:{imgId:imgId},
                    success:function(msg){
                        resourceInfo=msg.value

                            path="../../"+resourceInfo.path

                        $(".showImgPath").attr("src",path)
                        $(".showImgName").html(resourceInfo.name)
                        $(".showRaw").attr("href","http://localhost:8080/kbvs/"+resourceInfo.path)
                        $(".dow").html('<a onclick="singleFileDownload('+'resourceInfo.id'+')">下载原图</a>')
                        $("#imageScreen").show();
                    }
                }
        )
    }
    $(".user_myself").click(function(){ $(".option").toggle();});


</script>


</body>
</html>