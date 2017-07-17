<%--
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
    <title>知识库</title>
    <link rel="stylesheet" href="${resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css',file:'mobiscroll.2.13.2.css')}" type="text/css">
    <g:javascript src="mobiscroll.2.13.2.js"/>

    <script language="javascript">

        //删除
        function deletes(id){
            var fileIds = id
            $.ajax({
                        url:'<g:createLink controller="libraryInfo" action="delete" />',
                        type:'post',
                        dataType:'json',
                        data:{id:fileIds},
                        success:function(msg){
                            if(msg.state=='200'){
                                    var a = fileIds
                                    $("#"+a).remove()
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000 //20s后自动关闭
                                });
                            }else{
                                layer.msg('删除失败', {
                                    icon: 2,
                                    time: 1000 //20s后自动关闭
                                });
                            }
                        }
                    }
            )
        }

        //更新
        function update(id){
            var libraryId=id
            var index = layer.load(1, {shade: false});
            $.ajax({
                        url:'<g:createLink controller="libraryInfo" action="ajaxUpdate" />',
                        type:'post',
                        dataType:'json',
                        data:{id:libraryId},
                        success:function(msg){
                            layer.closeAll('loading');
                            if(msg.state=='200'){
                                layer.msg('更新成功', {
                                    icon: 1,
                                    time: 1000 //20s后自动关闭
                                });
                            }else{
                                layer.msg('更新失败', {
                                    icon: 2,
                                    time: 1000 //20s后自动关闭
                                });
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
                            if(msg.state='200'){
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
                    <div class="body">
                        <div class="body-nav">

                            <div style="clear:both;"></div>

                            <div class="libscrollbar">
                                %{--触发滚动事件的条件--}%
                                <div class="libfilescrollbar">
                                <div id="lib">
                                    <g:each in="${libraryInfoList}" status="i" var="libraryInfo">
                                        <div id="${libraryInfo.id}">
                                        <g:link controller="libraryInfo" action="show" id="${libraryInfo.id}">
                                        <div class="file-r">
                                            <div class="file-r-touxiang file-r-folder">

                                            </div>
                                            <div class="file-r-shijian">
                                            <a>
                                                <i class="fa fa-refresh" title="更新" onclick="update('${libraryInfo.id}')"></i>
                                            </a>
                                            <a>
                                                <i class="fa fa-trash-o" title="删除" onclick="deletes('${libraryInfo.id}')"></i>
                                            </a>
                                        </div>
                                            <div class="file-r-name">
                                                <a>${libraryInfo.name}</a>
                                            </div>
                                        </div>
                                        </g:link>
                                        </div>
                                    </g:each>
                                </div>

                                        <div class="file-r addlayer">
                                            <div class="file-r-touxiang file-r-folder-add">
                                            </div>
                                            <div class="file-r-name">
                                                <a>新建知识库</a>
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
</div>
<!--主体-->

<form id="newkbvs">

    <div class="group_kbvs">
        <span class="kbvsname">新建名称：</span>
        <input type="text" class=" form-control kbvsinput" name="name" id="name"  placeholder="知识库名称"/>
    </div>
    <span class="error-notic" id="nameError"></span>
    <p class="newguize" style="margin-top: 0;">文件标题关键词</p>
    <div class="group_kbvs">
            <span class="kbvsname">文件标题：</span>
            <input type="text" class=" form-control kbvsinput" name="resName" id="resName" placeholder="文件标题关键词"/>
    </div>
    <p class="newguize">文件内容关键词</p>
    <div class="group_kbvs">
        <div class="guanjiandiv">
            <span class="kbvsname">关键词1：</span>
            <input type="text" class=" form-control kbvsgjinput" name="resContent1" id="resContent1" placeholder="文件内容关键词1"/>
        </div>
    </div>
    <div class="group_kbvs">
        <div class="guanjiandiv">
            <span class="kbvsname">关键词2：</span>
            <input type="text" class=" form-control kbvsgjinput"name="resContent2"id="resContent2" placeholder="文件内容关键词2"/>
        </div>
    </div>
    <div class="group_kbvs">
        <div class="guanjiandiv">
            <span class="kbvsname">关键词3：</span>
            <input type="text" class=" form-control kbvsgjinput"name="resContent3" id="resContent3" placeholder="文件内容关键词3"/>
        </div>
    </div>
    <div class="group_kbvs">
        <span class="kbvsname">文件作者：</span>
        <input type="text" class=" form-control kbvszuozheinput" id="resAuthor" name="resAuthor" placeholder="文件作者"/>
    </div>
    <p class="newguize">创建时间</p>
    <div class="group_kbvs">
        <div class="settings" style="display:none;">
            <select name="demo" id="demo">
                <option value="date">日期</option>
            </select>
        </div>
        <span class="kbvsname" style=" margin-left: 60px">开始时间：</span>
        <input type="text" name="beginTime" id="beginTime" class=" form-control kbvsstartinput demo-test-date demo-test-datetime demo-test-time demo-test-credit" placeholder="开始时间"/>
        <span class="kbvsname kbvsendname" style=" margin-left: 60px">结束时间：</span>
        <input type="text" name="endTime" id="endTime" class=" form-control kbvsendinput demo-test-date demo-test-datetime demo-test-time demo-test-credit " placeholder="结束时间"/>

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
    <button type="button" class="btn btn-primary btn-sm addmit" onclick="addLibrary()">确认</button>
    <script type="text/javascript">
        //当前页面高度
        $(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
        var $libheight=$(".navbar").height()+3;
        $(".libscrollbar").height(window.innerHeight-$libheight);
        var $libgao=$(".libscrollbar").height();
        var $libligao=$(".libfilescrollbar").height();
        if($libgao>$libligao){
            $(".libscrollbar").css("overflow-y","hidden");
        }else{
            $(".libscrollbar").css("overflow-y","scroll");
        }



        //新建知识库
        function addLibrary(){
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
                                var index = layer.load(1, {shade: false});
                                $.ajax({
                                            url:'<g:createLink controller="libraryInfo" action="addLibrary" />',
                                            type:'post',
                                            dataType:'json',
                                            data:{name:name,resName:resName,resAuthor:resAuthor,resContent1:resContent1,resContent2:resContent2,resContent3:resContent3,beginTime:beginTime,endTime:endTime,types:types},
                                            success:function(msg){
                                                if(msg.state==200){
                                                    libraryInfo=msg.value
                                                    var con='<div id="'+libraryInfo.id+'"><a href="/kbvs/libraryInfo/show/'+libraryInfo.id+'">' +
                                                            '<div class="file-r"><div class="file-r-touxiang file-r-folder"></div><div class="file-r-shijian"><a>' +
                                                            '<i class="fa fa-refresh" title="更新" onclick="update('+"'"+libraryInfo.id+"'"+')"></i>' +
                                                            '</a><a><i class="fa fa-trash-o" title="删除" onclick="deletes('+"'"+libraryInfo.id+"'"+')"></i></a></div>' +
                                                            '<div class="file-r-name"><a>'+libraryInfo.name+'</a></div></div></a></div>'
                                                    $("#lib").append(con)
                                                    layer.closeAll('page'); //关闭加载层
                                                    layer.msg('创建成功', {
                                                        icon: 1,
                                                        time: 1000 //20s后自动关闭
                                                    });
                                                }else{
                                                    layer.closeAll('page'); //关闭加载层
                                                    layer.msg('创建失败', {
                                                        icon: 2,
                                                        time: 1000 //20s后自动关闭
                                                    });
                                                }
                                                layer.closeAll('loading');
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
</body>

</html>
