<<%--
  Created by IntelliJ IDEA.
  User: YunEr
  Date: 2016/8/11
  Time: 15:39
--%>


<%@ page import="com.panda.kbvs.ResourceInfo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>我的文档</title>
    <link rel="stylesheet" href="${resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css',file:'mobiscroll.2.13.2.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css',file:'liMarquee.css')}" type="text/css">
    <g:javascript src="mobiscroll.2.13.2.js"/>
    <g:javascript src="jquery.liMarquee.js"/>

    %{--全选--}%
    <script language="javascript">
        var selAll = document.getElementById("selAll");
        var fileId = null;
        function selectAll()
        {
            var obj = document.getElementsByName("checkAll");
            if(document.getElementById("selAll").checked == false)
            {
                //全选取消数组数据内容重置
                fileId = null;
                for(var i=0; i<obj.length; i++)
                {
                    obj[i].checked=false;
                    $(".g-button").css("display","none");
                }
            }else
            {
                fileId = new Array();
                for(var i=0; i<obj.length; i++)
                {
                    obj[i].checked=true;
                    $(".g-button").css("display","block");
                    fileId.push(obj[i].value)
                }
            }

        }

        //当选中所有的时候，全选按钮会勾上
        function setSelectAll()
        {
            var obj=document.getElementsByName("checkAll");
            var count = obj.length;
//			alert(count)
            var selectCount = 0;
            fileId = new Array();
            for(var i = 0; i < count; i++)
            {
                if(obj[i].checked == true)
                {
                    selectCount++;
//					alert(selectCount)
                    $(".g-button").css("display","block");
//					勾选后id往数组里添加
                    fileId.push(obj[i].value)
                }
            }
            if(selectCount==0){
                fileId = null;
                $(".g-button").css("display","none");
            }
            if(count == selectCount)
            {
                document.all.selAll.checked = true;
//				alert(fileId.join(','))
            }
            else
            {
                document.all.selAll.checked = false;
            }
//			alert(fileId.join(','))
        }

        //删除
        function remove(id){
            //数组元素的字符串化(控制层好处理)
            var libraryId=$("#libraryId").val()
            var fileIds = id
            $.ajax({
                        url:'<g:createLink controller="libraryInfo" action="deleteRes" />',
                        type:'post',
                        dataType:'json',
                        data:{fileId:fileIds,libraryId:libraryId},
                        success:function(msg){
//							alert(msg.state)
                            if(msg.state=='200'){
                                    var a = fileIds
//									var a = "b_"+fileId[i]
                                    $("#"+a).remove()
//									$("#checkValue").remove()

                                $(".g-button").css("display","none");
                            }
                        }
                    }
            )
        }
        //打开分享弹框
        function openShare(){
            //初始化
            $("#shareUrl").val("")

            layer.open({
                title :'我的分享',
                type: 1,
                area: ['620px', '570px'],
                shadeClose: true, //点击遮罩关闭
                content: $('#mylink')
            });
        }
        //分享链接
        function shareLink(){
            //数组元素的字符串化
            var fileIds = fileId.join(',')
            $.ajax({
                        url:'<g:createLink controller="shareInfo" action="shares" />',
                        type:'post',
                        dataType:'json',
                        data:{fileId:fileIds},
                        success:function(msg){
                            if(msg.state=='200'){
                                $("#shareUrl").val(msg.value)
                            }
                        }
                    }
            )
        }

        function copyUrl()
        {
            var Url2=document.getElementById("shareUrl");

            Url2.select(); // 选择对象
            document.execCommand("Copy"); // 执行浏览器复制命令
            layer.msg('复制成功', {
                icon: 1,
                time: 1000 //20s后自动关闭
            });
        }

        %{--分享到组群选中事件--}%

        var selAllGroup = document.getElementById("selAllGroup");
        var groupId = null;
        function selectAllGroup()
        {
            var obj = document.getElementsByName("checkGroup");
            if(document.getElementById("selAllGroup").checked == false)
            {
                //全选取消数组数据内容重置
                groupId = null;
                for(var i=0; i<obj.length; i++)
                {
                    obj[i].checked=false;
                    $(".but").css("display","none");
                }
            }else
            {
                groupId = new Array();
                for(var i=0; i<obj.length; i++)
                {
                    obj[i].checked=true;
                    $(".but").css("display","block");
                    groupId.push(obj[i].value)
                }
            }

        }

        //当选中所有的时候，全选按钮会勾上
        function setSelectGroup()
        {
            var obj=document.getElementsByName("checkGroup");
            var count = obj.length;
            var selectCount = 0;
            groupId = new Array();
            for(var i = 0; i < count; i++)
            {
                if(obj[i].checked == true)
                {
                    selectCount++;
                    $(".but").css("display","block");
//					勾选后id往数组里添加
                    groupId.push(obj[i].value)
                }
            }
            if(selectCount==0){
                groupId = null;
                $(".but").css("display","none");
            }
            if(count == selectCount)
            {
                document.all.selAllGroup.checked = true;
            }
            else
            {
                document.all.selAllGroup.checked = false;
            }
//			alert(fileId.join(','))
        }

        function shareGroup(){
            $('.list-user').remove()
            var fillType = $("#fillType").val();
            $.ajax({
                        url:'<g:createLink controller="groupInfo" action="myGroup" />',
                        type:'post',
                        dataType:'json',
                        success:function(msg){
                            if(msg.state=='200'){

                                var path="../"
                                if(fillType=="images"||fillType=="music"||fillType=="other"||fillType=="activeFile"||fillType=="video"||fillType=="word"){
                                    path="../../"
                                }

                                groupList=msg.value
                                for(var i=0;i<groupList.length;i++){
                                    var groupInfo=groupList[i]
                                    var gro='<div class="list-user">'+
                                            '<div class="headerpic" style="float:left;">' +
                                            '<img src="'+path+'images/group.png" height="40" width="40">' +
                                            '<div class="zuqun-check">' +
                                            '<input type="checkbox" name="checkGroup"  onClick="setSelectGroup()" value="'+groupInfo.id+'"/></div></div>' +
                                            '<div class="right-xinxi" style="float:left;">' +
                                            groupInfo.name
                                    '</div></div>'
                                    $("#myGroup").append(gro);
                                }
                            }value='+groupInfo.id'
                        }
                    }
            )
        }
        function shareToGroup(){
            var fileIds = fileId.join(',')
            var groupIds=groupId.join(',')
            $.ajax({
                        url:'<g:createLink controller="shareInfo" action="shareGroup"/>',
                        type:'post',
                        dataType:'json',
                        data:{resId:fileIds,groupId:groupIds},
                        success:function(msg){
                            layer.msg('分享成功', {
                                icon: 1,
                                time: 1000 //20s后自动关闭
                            });
                        }
                    }
            )
        }

        $(document).ready(function(){

//		$("#deletes").on("click", function(){

            $('body').on('click','#deletes',function(){

//		$("#deletes").click(function(){
                //数组元素的字符串化(控制层好处理)
                var fileIds = fileId.join(',')
                $.ajax({
                            url:'<g:createLink controller="resourceInfo" action="deletes" />',
                            type:'post',
                            dataType:'json',
                            data:{fileId:fileIds},
                            success:function(msg){
//							alert(msg.state)
                                if(msg.state=='删除成功'){
                                    for(var i=0;i<fileId.length;i++){
                                        var a = fileId[i]
                                        $("."+a).remove()
//									$("."+a).css("display","none");
                                    }
                                    $(".g-button").css("display","none");
                                }
                            }
                        },function(){
                        }
                )


            })

            //多文件下载
            $("#xz").click(function(){
                var fileIds = fileId.join(',')
                window.location = "<g:createLink controller="resourceInfo" action="download" />?id=" +fileIds;
            })
        })



        //单文件下载
        function singleFileDownload(id){
            window.location = "<g:createLink controller="resourceInfo" action="singleFileDownload" />?id=" +id;
        }

        function moveCommon(id){
            $.ajax({
                        url:'<g:createLink controller="resourceInfo" action="moveCommon"/>',
                        type:'post',
                        dataType:'json',
                        data:{id:id},
                        success:function(msg){
                            if(msg.state=='200'){
                                layer.msg('添加成功', {
                                    icon: 1,
                                    time: 1000//时间设置无反应
                                });
                            }
                        }
                    }
            )
        }


        //title提示
        $(function () {
            $('[data-toggle="tooltip"]').tooltip();
        })


    </script>

</head>
<body>
<!--导航栏 start-->
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
            <li style="background: #1D74E0; border-left:1px solid #1B6DD3;border-right:1px solid  #1B6DD3;"><g:link controller="resourceInfo" action="nextIndex"><i class="fa fa-home" style="font-size: 20px;"></i> 我的主页</g:link></li>
            <li class="wode group"><g:link controller="groupInfo" action="index"><i class="fa fa-group" style="font-size: 16px;"></i> 我的组群</g:link></li>
        </ul>

        <div class="user_myself">
        %{--头像--}%
            <img class="user_header_pic hImgPath" src="${resource(dir: '',file: session.imagePath)}">
            <div role="presentation" class="dropdown user_name">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
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
<!--主体-->
<div class="container mainbody">
    <div class="row">
        <div class="left-bar">
            <div class="nav-left">
                <ul class="list-unstyled nav-left-ul">
                    <g:link controller="resourceInfo" action="index" style="text-decoration: none"><li class=""><i class="fa fa-files-o"></i><span class="text">所有文件</span></li></g:link>
                    <g:link controller="resourceInfo" action="categoryPage" id="activeFile" style="text-decoration: none"><li class=""><i class="fa fa-file-o"></i><span class="text">常用文件</span></li></g:link>
                    <g:link controller="resourceInfo" action="categoryPage" id="video" style="text-decoration: none"><li class=""><i class="fa fa-file-movie-o"></i><span class="text">视频</span></li></g:link>
                    <g:link controller="resourceInfo" action="categoryPage" id="music" style="text-decoration: none"><li class=""><i class="fa fa-file-audio-o"></i><span class="text">音乐</span></li></g:link>
                    <g:link controller="resourceInfo" action="categoryPage" id="word" style="text-decoration: none"><li class=""><i class="fa fa-file-text-o"></i><span class="text">文档</span></li></g:link>
                    <g:link controller="resourceInfo" action="categoryPage" id="images" style="text-decoration: none"><li class=""><i class="fa fa-file-image-o"></i><span class="text">图片</span></li></g:link>
                    <g:link controller="resourceInfo" action="categoryPage" id="other" style="text-decoration: none"><li class=""><i class="fa fa-navicon"></i><span class="text">其他</span></li></g:link>
                    <g:link controller="shareInfo" action="index" style="text-decoration: none"><li class=""><i class="fa fa-share"></i><span class="text">我的分享</span></li></g:link>
                    <g:link controller="libraryInfo" action="index" style="text-decoration: none"><li class="current"><i class="fa fa-cog"></i><span class="text">知识库</span></li></g:link>
                </ul>
            </div>
        </div>

        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 right-bar">
            <div class="body-right">
                <div class="all-file tab" style="display:block">
                    <div class="header_guize gtex">
                        <p> <span class="tipstitle"><i class="fa fa-bell-o"></i> 当前知识库规则是</span>：
                            <span class="tipstext">文件标题关键词</span> : { <span class="tipscontent">${libraryInfo.resName}</span> }
                            <span class="tipstext">文件内容关键词:</span> 1. { <span class="tipscontent">${libraryInfo.resContent1}</span> } 2. { <span class="tipscontent">${libraryInfo.resContent2}</span> } 3. { <span class="tipscontent">${libraryInfo.resContent3}</span> }
                            <span class="tipstext"> 文件作者关键词</span>: { <span class="tipscontent">${libraryInfo.resAuthor}</span> }
                            <span class="tipstext">文件上传时间</span>：从<span class="tipscontent">
                            <g:if test="${libraryInfo?.beginTime!=null}">
                            ${libraryInfo?.beginTime.format('yyyy-MM-dd')}
                            </g:if>
                            </span>到<span class="tipscontent">
                            <g:if test="${libraryInfo?.endTime!=null}">
                                ${libraryInfo?.endTime.format('yyyy-MM-dd')}
                            </g:if>
                            </span>
                            <span class="tipstext">文件类型</span>：<span class="tipscontent">${libraryInfo.types.replace(","," ")}</span>
                        </p>
                        <a class="tipbianji"><i class="fa fa-pencil-square-o"></i>编辑</a>
                        <input type="hidden" id="libraryId" value="${libraryInfo.id}">
                    </div>
                    <div class="body">
                        <div class="body-nav">
                            <ul class=" list-unstyled list-col" style="margin-left: 0; margin-top: 10px; background-color: #F6FAFD; border:1px solid #E8EEF3;">
                                <li class="first">
                                    <input type="checkbox" id="selAll" onClick="selectAll()">
                                    <span class="file-name" style="margin-left:5px;">文件名</span>

                                    <a class="g-button">
                                        <span class="g-button-right">
                                            <i class="fa fa-download" title="下载"></i>
                                            <span class="text" style="width: auto;">下载</span>
                                        </span>
                                    </a>
                                    <a class="g-button">
                                        <span class="g-button-right">
                                            <i class="fa fa-trash" title="删除"></i>
                                            <span class="text" style="width: auto;" id="deletes">删除</span>
                                            %{--<span class="text" style="width: auto;" onclick="deletes()">删除</span>--}%
                                        </span>
                                    </a>
                                    <a class="g-button" id="test3" onclick="openShare()">
                                        <span class="g-button-right" >
                                            <i class="fa fa-share" title="分享"></i>
                                            <span class="text" style="width: auto;">分享</span>
                                        </span>
                                    </a>
                                </li>

                                <li class="second">
                                    <span clas="file-size">大小</span>
                                </li>
                                <li class="three">
                                    <span class="file-date">创建日期</span>
                                </li>
                            </ul>
                            <div style="clear:both;"></div>

                            <div class="libshowscrollbar">
                                %{--触发滚动事件的条件--}%
                                <div class="libshowfilescrollbar">


                                <div id="wjs">
                                %{--遍历所有文件--}%
                                    <g:each in="${ResourceInfoList}" status="i" var="resourceInfoInstance">
                                        <div class="${resourceInfoInstance.id}">
                                            %{--重命名放到这里--}%
                                            <div>
                                                <div id="${resourceInfoInstance.id}">
                                                    %{--<div id="b_${resourceInfoInstance.id}">--}%
                                                    <div class="user-file" id="s_${resourceInfoInstance.id}">

                                                        <div class="col user-filename">
                                                            <input type="checkbox" name="checkAll" value="${resourceInfoInstance.id}" onClick="setSelectAll()">
                                                            <g:if test="${resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'||resourceInfoInstance?.extName=='gif'||resourceInfoInstance?.extName=='bmp'||resourceInfoInstance?.extName=='ico'}">
                                                                <div class="fileicon fileicon-pic" ></div>
                                                            </g:if>
                                                            <g:elseif test="${resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx'}">
                                                                <div class="fileicon fileicon-word" ></div>
                                                            </g:elseif>
                                                            <g:elseif test="${resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx'}">
                                                                <div class="fileicon fileicon-xls" ></div>
                                                            </g:elseif>
                                                            <g:elseif test="${resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'||resourceInfoInstance?.extName=='flac'||resourceInfoInstance?.extName=='ape'}">
                                                                <div class="fileicon fileicon-music" ></div>
                                                            </g:elseif>
                                                            <g:elseif test="${resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf'}">
                                                                <div class="fileicon fileicon-video" ></div>
                                                            </g:elseif>
                                                            <g:elseif test="${resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt'}">
                                                                <div class="fileicon fileicon-ppt" ></div>
                                                            </g:elseif>
                                                            <g:elseif test="${resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip'||resourceInfoInstance?.extName=='7z'}">
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
                                                                <g:elseif test="${resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf'}">
                                                                    <g:link controller="resourceInfo" action="showVideo" id="${resourceInfoInstance.id}">
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
                                                            <i class="anniu fa fa-trash" data-toggle="tooltip" data-placement="left" title="移出知识库" onClick="remove('${resourceInfoInstance.id}')"></i>
                                                            <i class="anniu fa fa-download" data-toggle="tooltip" data-placement="left" title="下载" onclick="singleFileDownload('${resourceInfoInstance.id}')"></i>
                                                            <i class="anniu fa fa-arrow-left" data-toggle="tooltip" data-placement="left" title="添加至常用文件" onclick="moveCommon('${resourceInfoInstance.id}')"></i>
                                                            <i class="anniu fa fa-chain gl" data-toggle="tooltip" data-placement="left" title="查看关联的文件" onclick="showLink('${resourceInfoInstance.id}')"></i>

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
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>
</div>
<!--主体-->

<form id="newkbvs">

    <div class="group_kbvs">
        <span class="kbvsname">新建名称：</span>
        <input type="text" class=" form-control kbvsinput" name="name" id="name" value="${libraryInfo.name}" placeholder="知识库名称"/>
    </div>
    <span class="error-notic" id="nameError"></span>
    <p class="newguize" style="margin-top: 0;">文件标题关键词</p>
    <div class="group_kbvs">
        <span class="kbvsname">文件标题：</span>
        <input type="text" class=" form-control kbvsinput" name="resName" id="resName" value="${libraryInfo.resName}" placeholder="文件标题关键词"/>
    </div>
    <span class="error-notic" id="resNameError"></span>
    <p class="newguize">文件内容关键词</p>
    <div class="group_kbvs">
        <div class="guanjiandiv">
            <span class="kbvsname">关键词1：</span>
            <input type="text" class=" form-control kbvsgjinput" name="resContent1" id="resContent1" value="${libraryInfo.resContent1}" placeholder="文件内容关键词1"/>
        </div>
    </div>
    <div class="group_kbvs">
        <div class="guanjiandiv">
            <span class="kbvsname">关键词2：</span>
            <input type="text" class=" form-control kbvsgjinput"name="resContent2"id="resContent2" value="${libraryInfo.resContent2}" placeholder="文件内容关键词2"/>
        </div>
    </div>
    <div class="group_kbvs">
        <div class="guanjiandiv">
            <span class="kbvsname">关键词3：</span>
            <input type="text" class=" form-control kbvsgjinput"name="resContent3" id="resContent3" value="${libraryInfo.resContent3}" placeholder="文件内容关键词3"/>
        </div>
    </div>
    <div class="group_kbvs">
        <span class="kbvsname">文件作者：</span>
        <input type="text" class=" form-control kbvszuozheinput" id="resAuthor"  value="${libraryInfo.resAuthor}" name="resAuthor" placeholder="文件作者"/>
    </div>
    <p class="newguize">创建时间</p>
    <div class="group_kbvs">
        <div class="settings" style="display:none;">
            <select name="demo" id="demo">
                <option value="date">日期</option>
            </select>
        </div>
        <span class="kbvsname" style=" margin-left: 60px">开始时间：</span>
        <input type="text" name="beginTime" id="beginTime" class=" form-control kbvsstartinput demo-test-date demo-test-datetime demo-test-time demo-test-credit" value="${libraryInfo.beginTime}" placeholder="开始时间"/>
        <span class="kbvsname kbvsendname" style=" margin-left: 60px">结束时间：</span>
        <input type="text" name="endTime" id="endTime" class=" form-control kbvsendinput demo-test-date demo-test-datetime demo-test-time demo-test-credit " value="${libraryInfo.endTime}" placeholder="结束时间"/>

    </div>
    <span class="error-notic" id="timeError"></span>
    <p class="newguize">文件类型（默认全选）</p>
    <div class="group_kbvs" style="clear: both;">
        <div class="allchecktype">
            <input type="checkbox" class="ch" name="checkType" id="v" value="video" style="margin-left: 15px;" checked/><span>视频</span>
            <input type="checkbox" class="ch" name="checkType" id="m" value="music" checked/><span>音乐</span>
            <input type="checkbox" class="ch" name="checkType" id="w" value="word" checked/><span>文档</span>
            <input type="checkbox" class="ch" name="checkType" id="i" value="images" checked/><span>图片</span>
            <input type="checkbox" class="ch" name="checkType" id="o" value="other" checked/><span>其他</span>
        </div>
    </div>
    <button type="button" class="btn btn-primary btn-sm addmit" onclick="update()">确认</button>
    <script type="text/javascript">




        //新建知识库
        function update(){
            var name=$("#name").val()
            if(name.length==0){
                $("#nameError").text("知识库名不能为空!")
                return
            }else{
                $("#nameError").text("")
            }

            var resName=$("#resName").val()
            var resContent1=$("#resContent1").val()
            var resContent2=$("#resContent2").val()
            var resContent3=$("#resContent3").val()
            var beginTime=$("#beginTime").val()
            var endTime=$("#endTime").val()

            var resAuthor=$("#resAuthor").val()
            var date1 = beginTime.replace(/\-/gi,"/");
            var date2 = endTime.replace(/\-/gi,"/");
            var time1 = new Date(date1).getTime();
            var time2 = new Date(date2).getTime();
            var libraryId=$("#libraryId").val()
            if(time1 > time2){
                $("#timeError").text("开始时间不能大于结束时间!")
                return
            }
            var type = null;
            var obj = document.getElementsByName("checkType");
            type = new Array();
            for(var i=0; i<obj.length; i++)
            {
                if(obj[i].checked==true){
                    type.push(obj[i].value)
                }
            }
            var types = type.join(',')


            $.ajax({
                        url:'<g:createLink controller="libraryInfo" action="validate" />',
                        type:'post',
                        dataType:'json',
                        data:{name:name,resName:resName,resAuthor:resAuthor,resContent1:resContent1,resContent2:resContent2,resContent3:resContent3,beginTime:beginTime,endTime:endTime,types:types},
                        success:function(msg){
                            if(msg.state==200){
                                layer.closeAll('page'); //关闭加载层
                                layer.msg('至少要有一个关键词', {
                                    icon: 2,
                                    time: 1000 //20s后自动关闭
                                });
                                return
                            }else{
                                $.ajax({
                                            url:'<g:createLink controller="libraryInfo" action="update" />',
                                            type:'post',
                                            dataType:'json',
                                            data:{id:libraryId,name:name,resName:resName,resAuthor:resAuthor,resContent1:resContent1,resContent2:resContent2,resContent3:resContent3,beginTime:beginTime,endTime:endTime,types:types},
                                            success:function(msg){
                                                if(msg.state==200){
                                                    libraryInfo = msg.value.id
                                                    window.location.href="<g:createLink controller="libraryInfo" action="show" id="${libraryInfo.id}"/>"
                                                }
                                            }
                                        }
                                );
                            }
                        }
                    }
            );





        }
        //时间选择器
        $(function () {
            var curr = new Date().getFullYear();
            var opt={};
            opt.date = {preset : 'date'};
            opt.datetime = {preset : 'datetime'};
            opt.time = {preset : 'time'};
            opt.default = {
                preset: 'date',
                theme: 'android-holo light', //皮肤样式
                display: 'modal', //显示方式
                mode: 'scroller', //日期选择模式
                dateFormat: 'yyyy-mm-dd',
                lang: 'zh',
                showNow: true,
                nowText: "今天",
                // dateOrder: 'yyyymmdd', //面板中日期排列格式
                stepMinute: 5,
                startYear: curr - 3, //开始年份
                endYear: curr + 0 //结束年份
            };
            $('.settings').bind('change', function() {
                var demo = 'datetime';
                if (!demo.match(/select/i)) {
                    $('.demo-test-' + demo).val('');
                }
                $('.demo-test-' + demo).scroller('destroy').scroller($.extend(opt['datetime'], opt['default']));
                $('.demo').hide();
                $('.demo-' + demo).show();
            });
            $('#demo').trigger('change');
        });
    </script>
</form>

<div id="mylink" style=" display:none;">
    <div class="link_header">
        <ul class="share-tab list-unstyled">
            <li class="share-link current">
                <em class="icon"></em>
                链接分享
            </li>
            <li class="share-group"  onclick="shareGroup()">
                <em class="icon"></em>
                发到组群
            </li>
        </ul>
    </div>
    <div style="clear: both;"></div>
    <div class="link_body" >
        <div class="shengchen_link">
            <div class="share-jieshao share-tt">
                生成下载链接，然后复制链接发送到QQ、MSN等好友
            </div>
            <div class="share-box">
                <div>
                    <a class="btn btn-info btn-sm" onclick="shareLink()">创建链接</a>
                    <span>（文件会出现在你的分享主页，其他人都能查看下载）</span>
                    <label style="display:block; margin-top:20px;"> 链接地址：</label>
                    <input type="text" class="form-control" id="shareUrl"/>
                    <a class="btn btn-info btn-sm" style="float:right; margin-top:10px;" onclick="copyUrl()">复制链接</a>
                </div>
                <div>
                </div>
            </div>
        </div>
        <div class="shengchen_link" style="display:none;">
            <div class="share-zuqun share-tt">
                选择好友分享文件，一次最多50人
            </div>
            <div class="quanxuan-title">
                <input type="checkbox" id="selAllGroup" onClick="selectAllGroup()"/>
                全选我的组群
            </div>
            <div class="box-body-left">

                <div class="limain-box" id="myGroup">

                </div>
            </div>
            <a class="btn btn-info btn-sm" style="float:right; margin-right:50px;" onclick="shareToGroup()">确定</a>
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



<!--文件关联显示-->
<div id="chakanguanlian" style="display:none;">
    <div class="gl-all">
        <div class="body-nav" style="margin-left:-10px;">
            <ul class=" list-unstyled list-col">
                <li class="first">
                    <span class="file-name" style="margin-left:5px;">文件名</span>
                </li>
                <li class="second">
                    <span class="file-size">大小</span>
                </li>
                <li class="three">
                    <span class="file-date">创建日期</span>
                </li>
            </ul>
            <div style="clear:both;"></div>
            <div class="glscrollbar" style="height:470px; overflow: scroll;overflow-x:hidden; overflow-y:hidden;">
                <div class="filescrollbars filesc">


                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    //显示关联
    function showLink(id){
        $('.guanlian').remove()
        $.ajax({
                    url:'<g:createLink controller="resourceInfo" action="showLink" />',
                    type:'post',
                    dataType:'json',
                    data:{id:id},
                    success:function(msg){
                        resourceList=msg.resourceInfoList
                        for(var i= 0;i<resourceList.length;i++){
                            var resourceInfo=resourceList[i]
                            var date = new Date(resourceInfo.dateCreated);
                            date= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                            var fileicon
                            var showDetail='<a href="/kbvs/resourceInfo/show/'+resourceInfo.id+'">'+resourceInfo.name+'.'+resourceInfo.extName+'</a></span>'
                            if(resourceInfo.extName=="jpg"||resourceInfo.extName=="jpeg"||resourceInfo.extName=="png"){
                                fileicon='<div class="fileicon fileicon-pic" style="position:absolute; left:20px;" ></div>'
                                %{--<a onclick="showImg('${resourceInfoInstance.id}')" href="#">${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}</a>--}%
                                showDetail='<a href="#" onclick="showImg('+"'"+resourceInfo.id+"'"+')">'+resourceInfo.name+'.'+resourceInfo.extName+'</a>'
                            }else if(resourceInfo.extName=="doc"||resourceInfo.extName=="docx"){
                                fileicon='<div class="fileicon fileicon-word" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="xls"||resourceInfo.extName=="xlsx"){
                                fileicon='<div class="fileicon fileicon-xls" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="mp3"||resourceInfo.extName=="wav"||resourceInfo.extName=="wma"){
                                showDetail='<a onclick="showMusic('+"'"+resourceInfo.id+"'"+')" href="#">'+resourceInfo.name+'.'+resourceInfo.extName+'</a>'
                                fileicon='<div class="fileicon fileicon-music" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="avi"||resourceInfo.extName=="3gp"||resourceInfo.extName=="rmvb"||resourceInfo.extName=="mp4"||resourceInfo.extName=="mkv"||resourceInfo.extName=="flv"){
                                showDetail='<a href="/kbvs/resourceInfo/showVideo/'+resourceInfo.id+'">'+resourceInfo.name+'.'+resourceInfo.extName+'</a></span>'
                                fileicon='<div class="fileicon fileicon-video" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="ppt"||resourceInfo.extName=="pptx"){
                                fileicon='<div class="fileicon fileicon-ppt" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="rar"||resourceInfo.extName=="zip"){
                                fileicon='<div class="fileicon fileicon-yasuo" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="pdf"){
                                fileicon='<div class="fileicon fileicon-pdf" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="txt"){
                                fileicon='<div class="fileicon fileicon-text" style="position:absolute; left:20px;"></div>'
                            }else if(resourceInfo.extName=="torrent"){
                                fileicon='<div class="fileicon fileicon-bt" style="position:absolute; left:20px;"></div>'
                            }else{
                                fileicon='<div class="fileicon fileicon-other" style="position:absolute; left:20px;"></div>'
                            }
                            if(resourceInfo.type=="other"){
                                showDetail='<a onclick="showOther()" href="#">'+resourceInfo.name+'.'+resourceInfo.extName+'</a>'
                            }
                            var res='<div class="guanlian"><div>' +
                                    '<div class="user-file" id="s_'+resourceInfo.id+'"><div class="col user-filename">' +

                                    fileicon+
                                    '<span style=""></span><span class="file-name">' +
                                    showDetail +
                                    '</div><div class="col user-filesize"><span class="file-size">'+resourceInfo.fileSize+'B</span>' +
                                    '</div><div class="col user-filedate"><span class="file-date">'+date+'</span>' +
                                    '</div></div>' +
                                    '<div class="a_'+resourceInfo.id+'">' +
                                    '<div class="gaiyao"><span>'+resourceInfo.remark+'</span></div>' +
                                    '</div></div></div>'

                            $(".filescrollbars").append(res)
                        }
                        layer.open({
                            zIndex:1000,
                            title :['关联的内容', 'font-size:14px;'],
                            type: 1,
                            skin: 'layui-layer-rim', //加上边框
                            area: ['820px', '570px'], //宽高
                            content: $('#chakanguanlian')
                        });
                    }
                }
        )
    }
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
            content: ['<g:createLink controller="resourceInfo" action="showMusic"/>?mId='+mId, 'no']//iframe的url，no代表不显示滚动条
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
                       var path="../../"+resourceInfo.path
                        $(".showImgPath").attr("src",path)
                        $(".showImgName").html(resourceInfo.name)
                        $(".showRaw").attr("href","http://localhost:8080/kbvs/"+resourceInfo.path)
                        $(".dow").html('<a onclick="singleFileDownload('+'resourceInfo.id'+')">下载原图</a>')
                        $("#imageScreen").show();
                    }
                }
        )
    }
</script>


</body>

</html>
