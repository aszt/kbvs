
<%@ page import="com.panda.kbvs.ResourceInfo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>我的视频</title>
    <link rel="stylesheet" href="${resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css')}" type="text/css">

</head>
<body>
<!--导航栏 start-->
<nav class="navbar navbar-fixed-top" style="background-color: #3083EB;">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse">
            <span class="icon-bar" style="background:#FFF"></span>
            <span class="icon-bar" style="background:#FFF"></span>
            <span class="icon-bar" style="background:#FFF"></span>
        </button>
        <g:link action="nextIndex" class="navbar-brand logo" style="width:160px; margin-top:-15px;"><img src="${resource(dir: 'images',file: 'LOGOO.png')}"></g:link>
    </div>
    <div class="collapse navbar-collapse xNav">
        <ul class="nav navbar-nav nav_ul"
            style="margin-left:40px;">
            <li style="background: #1D74E0; border-left:1px solid #1B6DD3;border-right:1px solid  #1B6DD3;"><g:link action="nextIndex"><i class="fa fa-home" style="font-size: 20px;"></i> 我的主页</g:link></li>
            <li class="wode group"><g:link controller="groupInfo" action="index"><i class="fa fa-group" style="font-size: 16px;"></i> 我的组群</g:link></li>
        </ul>

        <div class="user_myself">
        %{--头像--}%
            <img class="user_header_pic hImgPath" src="${resource(file: session.imagePath)}">
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
    </div>

</nav>
<!--导航栏 end-->
<!--主体-->
<div class="container mainbody">
    <div class="row">
        <div class="left-bar">
            <div class="nav-left">
                <ul class="list-unstyled nav-left-ul">
                    <g:link action="index" style="text-decoration: none"><li><i class="fa fa-files-o"></i><span class="text">所有文件</span></li></g:link>
                    <g:link action="categoryPage" id="activeFile" style="text-decoration: none"><li><i class="fa fa-file-o"></i><span class="text">常用文件</span></li></g:link>
                    <g:link action="categoryPage" id="video" style="text-decoration: none"><li class="current"><i class="fa fa-file-movie-o"></i><span class="text">视频</span></li></g:link>
                    <g:link action="categoryPage" id="music" style="text-decoration: none"><li><i class="fa fa-file-audio-o"></i><span class="text">音乐</span></li></g:link>
                    <g:link action="categoryPage" id="word" style="text-decoration: none"><li><i class="fa fa-file-text-o"></i><span class="text">文档</span></li></g:link>
                    <g:link action="categoryPage" id="images" style="text-decoration: none"><li><i class="fa fa-file-image-o"></i><span class="text">图片</span></li></g:link>
                    <g:link action="categoryPage" id="other" style="text-decoration: none"><li><i class="fa fa-navicon"></i><span class="text">其他</span></li></g:link>
                    <g:link controller="shareInfo" action="index" style="text-decoration: none"><li><i class="fa fa-share"></i><span class="text">我的分享</span></li></g:link>
                    <g:link controller="libraryInfo" action="index" style="text-decoration: none"><li class=""><i class="fa fa-cog"></i><span class="text">知识库</span></li></g:link>
                </ul>
            </div>
        </div>

        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 right-bar">
            <div class="body-right">
                <div class="all-file tab" style="display:block">
                    <div class="header">
                        <g:form controller="resourceInfo" action="categorySearch" method="get" class="navbar-form navbar-right hidden-xs sea" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control search" placeholder="搜索视频" name="search"  value="${search}">
                                <input type="hidden"  name="" value="${search}" id="search">
                                <input type="hidden"  name="fillType" value="${fillType}" id="fillType">
                                %{--记录页码--}%
                                <input type="hidden" id="pageNo" value="1">
                            </div>
                            <button type="submit" class="btn comtwo seabtn" >
                                <span class="glyphicon glyphicon-search" style=""></span>
                            </button>
                        </g:form>

                    </div>
                    <div class="body">

                        <div class="body-nav">
                            <div style="clear:both;"></div>

                            <div class="videoscrollbar">
                                %{--触发滚动事件的条件--}%

                                <div class="videofilescrollbar">

                                    <div class="row videospc" id="wjs">
                                    <g:each in="${ResourceInfoList}" status="i" var="resourceInfoInstance">
                                        <g:link controller="resourceInfo" action="showVideo" id="${resourceInfoInstance.id}">
                                        <div class="col-lg-2 videoone">
                                            <div class="thumbnail videolol">
                                                <img src="${resource(dir: '',file: resourceInfoInstance.preImgPath)}" class="special_img">
                                                <div class="bgvd"></div>
                                            </div>
                                            <div class="caption checkfuji">

                                                <p>${raw(resourceInfoInstance.name)}.${raw(resourceInfoInstance.extName)}</p>
                                            </div>
                                        </div>
                                        </g:link>
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
<script type="text/javascript">

    $(function() {

        $(".videoscrollbar").scroll(function() {
//			当前页码
            var page = $("#pageNo").val();
            var pageNo=Number(page)
            //得到当前渲染的页面
            var fillType = $("#fillType").val();
            //得到搜索框内容
            var search = $("#search").val();
            var $this = $(this),
                    viewH = $(this).height(),//可见高度
                    contentH = $(this).get(0).scrollHeight,//内容高度
                    scrollTop = $(this).scrollTop();//滚动高度
            if(contentH - viewH - scrollTop <= 1) { //到达底部0px时,加载新内容
                // 这里加载数据..
                var index = layer.load(0, {shade: false});
                $.ajax({
                    url:'<g:createLink controller="resourceInfo" action="pagination" />',
                    type:'post',
                    dataType:'json',
                    data:{search:search,fillType:fillType,pageNo:pageNo},
                    success:function(msg){
                        //将新页码加入pageNumber中
                        $("#pageNo").val(pageNo+1);
                        var path = "../";
                        if(!search){
                            path="../../"
                        }
                        ResourceInfoList=msg.resourceInfoList
                        for(var i= 0;i<ResourceInfoList.length;i++){
                            var resourceInfo=ResourceInfoList[i]

                            var res='<a href="/kbvs/resourceInfo/showVideo/'+resourceInfo.id+'">' +
                                    '<div class="col-lg-2 videoone"><div class="thumbnail videolol">' +
                                    '<img src="../../'+resourceInfo.preImgPath+'" class="special_img">' +
                                    '<div class="bgvd"></div></div><div class="caption checkfuji"><p>' +
                                    resourceInfo.name+
                                    '</p></div></div></a>'
                            $("#wjs").append(res)
                        }
                        layer.closeAll('loading');
                    }
                })
            }
        })

    });
</script>
</body>

</html>
