<%--
  Created by IntelliJ IDEA.
  User: c-kx
  Date: 2016/6/6
  Time: 15:39
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'flashshow.css')}" type="text/css">
    <title>视频播放</title>
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
            style="margin-left:92px;">
            <li><g:link action="nextIndex"><i class="fa fa-home" style="font-size: 20px;"></i> 我的主页</g:link></li>
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
<div class="flashcontent"  style="margin-top: 80px;">
    <div class="flash-title">
        视频名称:${resourceInfo.name}.${resourceInfo.extName}
    </div>
    <div id="a1">
    </div>
    <div class="othervideo">
        <div class="othervideo-title">
            相关视频
        </div>

        <div class="video-list row">
        <g:each in="${resourceInfoList}" status="i" var="resourceInfo">
            <div class="video-list-file">
                %{--<div class="video-img"></div>--}%
            <g:link controller="resourceInfo" action="showVideo" id="${resourceInfo.id}">
                <div class="video-img thumbnail">
                  <img class="user_header_pic " src="../../${raw(resourceInfo.preImgPath)}"><div class="bgvd"></div>
                </div>
            </g:link>
                <div class="video-name">
                    ${resourceInfo.name}
                </div>
                <div class="video-m">
                    ${resourceInfo.fileSize}B
                </div>
            </div>
        </g:each>
        </div>

    </div>
</div>

<g:javascript src="../ckplayer/ckplayer.js"/>
<script type="text/javascript">

    var urls = $("#urls").val()

    var flashvars={
        p:1,
        e:1,
        hl:'http://pan.baidu.com/s/1qYw21EO',
        ht:'20',
        hr:'http://www.ckplayer.com'
    };
    var video=['<g:createLink url="../../${firstUrl}"/>','http://www.ckplayer.com/webm/0.webm->video/webm','http://www.ckplayer.com/webm/0.ogv->video/ogg'];
    var support=['all'];
    CKobject.embedHTML5('a1','ckplayer_a1',980,480,video,flashvars,support);
</script>
<input type="hidden" id="urls" value="${firstUrl}">
%{--${firstUrl}--}%
</body>
</html>