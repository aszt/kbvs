<!doctype html>
<html>
<head>
<meta charset="utf-8">
    <meta name="layout" content="main">
    <title>无标题文档</title>
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'reset.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'mygroup.css')}" type="text/css">
    <g:javascript src="group.js"/>
    <script type="text/javascript">

    $(document).ready(function(){

        $('body').on('click','#newGroup',function(){
            var name =$("#newGroupName").val()
            $.ajax(
                    {
                        url:'<g:createLink controller="groupInfo" action="newGroup" />',
                        type:'post',
                        dataType:'json',
                        data:{groupName:name},
                        success:function(msg){
                            if(msg.state=='200'){
                                $(".last-list-user").hide().select();
                                gruopInfo=msg.group
                                var con='<div class="list-user" id="g'+gruopInfo.id+'">' +
                                        '<div class="headerpic" onclick="showMember('+'gruopInfo.id'+')">' +
                                        '<img src="../images/group.png"  height="40" width="40"></div>' +
                                        '<div class="right-xinxi" onclick="showContent('+'gruopInfo.id'+')">' +
                                        gruopInfo.name+
                                        '</div><div class="xlcd"><i class="xl-btn"><ul class="xl-list">' +
                                        '<li onclick="deleteGroup('+'gruopInfo.id'+')">删除组群</li>'+
                                        '<li onclick="shareGroupLink('+'gruopInfo.id'+')">分享好友</li>' +
                                        ' </ul></i></div></div>'
                                $("#addGroup").append(con)


                                layer.msg('新建文件成功', {
                                    icon: 1,
                                    time: 1000//时间设置无反应
                                });
                            }
                        }
                    }
            )
        })

        window.onload=function(){
            if("${state}"==600){
                layer.open({
                    title :'组群"${groupName}"中的成员',
                    type: 1,
                    area: ['592px', '550px'],
                    shadeClose: true, //点击遮罩关闭
                    content:$('#joinzuqun-people')
                });

            }
            if("${state}"==601){
                layer.msg('邀请链接失效', {
                    icon: 0,
                    time: 2000//时间设置无反应
                });
            }

        }


    })
    //组群页的分享好友弹窗
    function shareGroupLink(groupId){
        $.ajax({
                    url:'<g:createLink controller="groupInfo" action="shares" />',
                    type:'post',
                    dataType:'json',
                    data:{groupId:groupId},
                    success:function(msg){
                        if(msg.state=='200'){
                            $("#groupLink").val(msg.value)
                            layer.open({
                                title :'分享给好友',
                                type: 1,
                                area: ['400px', '200px'],
                                shadeClose: true, //点击遮罩关闭
                                content: $('#share-people-group')
                            });
                        }
                    }
                }
        )


    }
    function copyLink(){
        var Url2=document.getElementById("groupLink");
        Url2.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        layer.msg('复制成功', {
            icon: 1,
            time: 1000 //20s后自动关闭
        });
    }

    function deleteGroup(groupId){
        var groupId=groupId
        $.ajax({
                    url:'<g:createLink controller="groupInfo" action="delGroup" />',
                    type:'post',
                    dataType:'json',
                    data:{groupId:groupId},
                    success:function(msg){
//							alert(msg.state)
                        if(msg.state=='200'){
                            $("#g"+groupId).remove()
                            layer.msg('删除成功', {
                                icon: 1,
                                time: 1000 //20s后自动关闭
                            });
                            }

                        }
                    }
        )
    }
    function quitGroup(groupId){
        var groupId=groupId
        $.ajax({
                    url:'<g:createLink controller="groupInfo" action="quitGroup" />',
                    type:'post',
                    dataType:'json',
                    data:{groupId:groupId},
                    success:function(msg){
                        if(msg.state=='200'){
                            $("#g"+groupId).remove()
                            layer.msg('退出成功', {
                                icon: 1,
                                time: 1000 //20s后自动关闭
                            });
                        }

                    }
                }
        )
    }

    function showMember(groupId){
        $('.people-list').remove()
        var groupId=groupId
        var groupName
        $.ajax({
                    url:'<g:createLink controller="groupInfo" action="getMember" />',
                    type:'post',
                    dataType:'json',
                    data:{groupId:groupId},
                    success:function(msg){
                        groupName=msg.groupName
                        userInfoList=msg.value
                        if(msg.state=='200'){
                            for(var i=0;i<userInfoList.length;i++){
                                var userInfo=userInfoList[i]
                                var user='<div class="people-list"><div class="people-list-headerpic">' +
                                        ' <img src="../'+userInfo.imagePath+'" width="80" height="80"></div>' +
                                        '<div class="people-list-name">'+userInfo.loginName+
                                        '</div></div>'
                                $('.limian-zuqun-people').append(user)
                            }
                        }

                        layer.open({
                            title :'组群"'+groupName+'"中的成员',
                            type: 1,
                            area: ['592px', '550px'],
                            shadeClose: true, //点击遮罩关闭
                            content:$('#zuqun-people')
                            
                        });

                    }
                }
        )

    }
    function showContent(groupId){
        $(".cs").remove()
        $.ajax(
                {
                    url:'<g:createLink controller="groupInfo" action="getShare" />',
                    type:'post',
                    dataType:'json',
                    data:{groupId:groupId},
                    success:function(msg){
                        if(msg.state=='200'){
                            shareInfoList=msg.value
                            for(var i=0;i<shareInfoList.length;i++){
                                shareInfo=shareInfoList[i]
                                var date = new Date(shareInfo.dateCreated);
                                 date= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                                var con=' <div class="cs"><div class="'+shareInfo.id+'">' +
                                        '<div class="user-file" id="'+shareInfo.id+'">' +
                                        '<div class="col user-filename">' +
                                        '<div class="fileicon share-ico"></div><span style=""></span><span class="file-name">' +
                                        '<a href="/kbvs/shareInfo/show/'+shareInfo.id+'">' +
                                        shareInfo.name+
                                        '</a></span>' +

                                        '</div><div class="col user-filesize"><span class="file-size">-</span></div><div class="col user-filedate">' +
                                        '<span class="file-date">'+date+'</span></div></div></div></div>'

                                $("#wjjs").append(con)
                            }


                        }
                    }
                }
        )
    }
    function addToGroup(){

        var groupId="${groupId}"
        $.ajax(
                {
                    url:'<g:createLink controller="groupInfo" action="addGroup" />',
                    type:'post',
                    dataType:'json',
                    data:{groupId:groupId},
                    success:function(msg){
                        if(msg.state=='200'){

                            gruopInfo=msg.group
                            var con='<div class="list-user" id="g'+gruopInfo.id+'">' +
                                    '<div class="headerpic" onclick="showMember('+'gruopInfo.id'+')">' +
                                    '<img src="../images/group.png"  height="40" width="40"></div>' +
                                    '<div class="right-xinxi" onclick="showContent('+'gruopInfo.id'+')">' +
                                    gruopInfo.name+
                                    '</div><div class="xlcd"><i class="xl-btn"><ul class="xl-list">' +
                                    '<li onclick="deleteGroup('+'gruopInfo.id'+')">删除组群</li>'+
                                    '<li onclick="shareGroupLink('+'gruopInfo.id'+')">分享好友</li>' +
                                    ' </ul></i></div></div>'
                            $("#addGroup").append(con)

                            layer.closeAll()
                            layer.msg('加入组群成功', {
                                icon: 1,
                                time: 1000//时间设置无反应
                            });
                        }
                    }
                }
        )
    }

</script>
</head>

<body style="background:#F6FAFD;">
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
            <li><g:link controller="resourceInfo" action="nextIndex"><i class="fa fa-home" style="font-size: 20px;"></i> 我的主页</g:link></li>
            <li class="wode group" style="background: #1D74E0; border-left:1px solid #1B6DD3;border-right:1px solid  #1B6DD3;"><g:link controller="groupInfo" action="index"><i class="fa fa-group" style="font-size: 16px;"></i>我的组群</g:link></li>
        </ul>

        <div class="user_myself">
        %{--头像--}%
            <img class="user_header_pic hImgPath" src="${resource(dir: '',file:session.imagePath )}">
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
	<div class="mainbody">
     <div class="center">
          <div class="left-box">
         	<div class="box-header">

                      <a>我的组群</a>

                
            </div>
            <div class="box-body">
              <div class="box-body-left">
            	<div class="limain-box" id="addGroup">


                    <!--新建组群-->
                    <div class="list-user last-list-user" style="display:none;">
                        <div class="headerpic">
                            <img src="../images/group.png">
                        </div>
                        <div class="" style="line-height:25px; margin-top: 5px;">
                            <input type="text" style="width: 100px; margin-left:15px;float: left;" id="newGroupName">
                            <span class="sure" id="newGroup" dir="${FolderInfoId}" style="float: left; margin-top: 3px; margin-left: 15px"></span>
                            <span class="cancel" onclick="closeFolder()"  style="float: left; margin-top: 3px; margin-left: 5px;"></span>
                        </div>
                    </div>

                <g:each in="${groupInfoList}" status="i" var="gruopInfo">
                    <div class="list-user" id="g${gruopInfo.id}">
                        <div class="headerpic" onclick="showMember('${gruopInfo.id}')">
                            <img src="../images/group.png">
                        </div>
                        <div class="right-xinxi" onclick="showContent('${gruopInfo.id}')">
                            ${gruopInfo.name}
                        </div>
                        <div class="xlcd">
                            <i class="xl-btn">
                                <ul class="xl-list">
                                    <g:if test="${gruopInfo.creatorId==session.userId}">
                                        <li onclick="deleteGroup('${gruopInfo.id}')">删除组群</li>
                                    </g:if>
                                    <g:else >
                                        <li onclick="quitGroup('${gruopInfo.id}')">退出组群</li>
                                    </g:else>
                                    <li onclick="shareGroupLink('${gruopInfo.id}')">分享好友</li>

                                </ul>
                            </i>
                        </div>
                    </div>
                </g:each>

                   </div> 
                </div>
            </div>


            <div class="box-footer">
           		 
            		<i class="bak"></i><a>创建组群</a>
                
            </div>
          </div>
          <div class="right-box">
          	   <div class="box-header">
                		<label>分享</label>
               </div>
               <div class="box-body">
               	<div class="box-right-body">
                    <div clas="small">

                    </div>
          
  <!-- 文件显示 -->
                    <div class="body-nav" style="margin-top: 5px;">
                        <ul class=" list-unstyled list-col">
                            <li class="first">
                                <span class="file-name" style="margin-left:5px;">分享名</span>
                            </li>

                            <li class="second">
                                <span clas="file-size">大小</span>
                            </li>
                            <li class="three">
                                <span class="file-date">分享日期</span>
                            </li>
                        </ul>
                        <div style="clear:both;"></div>

                        <div class="scrollbar">
                            %{--触发滚动事件的条件--}%
                            <input type="hidden" id="fillType" value="${fillType}">
                            <div class="filescrollbar">
                                <div id="kl">

                                    %{--遍历所有文件夹--}%
                                    <div id="wjjs">
                                    <div id="cs">
                                        <g:each in="${ShareInfoLidt}" status="i" var="folderInfoListInstance">
                                        %{--重命名包裹input输入框--}%
                                            <div class="${folderInfoListInstance.id}">
                                                <div class="user-file" id="${folderInfoListInstance.id}">
                                                    %{--<div class="user-file" id="s_${folderInfoListInstance.id}">--}%
                                                    <div class="col user-filename">
                                                        <div class="fileicon share-ico"></div>
                                                        <span style=""></span>
                                                        <span class="file-name">
                                                            <g:link controller="shareInfo" action="show" id="${folderInfoListInstance.id}">
                                                            %{--将文件夹id带给控制层--}%
                                                                ${fieldValue(bean: folderInfoListInstance, field: "name")}
                                                            </g:link>
                                                        </span>
                                                        <i class="anniu fa fa-trash" data-toggle="tooltip" data-placement="left" title="下载"onclick="downl('${folderInfoListInstance.id}')"></i>
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
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
  <!-- 文件显示 -->
        </div>
               </div>
          </div>
     </div>
  </div>

<div id="share-people-group">
    <label style="display:block; margin-top:20px; font-size:13px; font-weight:500;"> 链接地址：</label>
    <input type="text" class="form-control share-people-group-input" id="groupLink"/>
    <a class="btn btn-info btn-sm" style="float:right; margin-top:10px; font-size:13px; font-weight:500;" onclick="copyLink()">复制链接</a>
</div>
<!--组群成员-->
<div id="zuqun-people">
        <div class="waimian-zuqun-people">
            <div class="limian-zuqun-people">


            </div>
        </div>

    <a class="zuqun-people-btn btn zuqun-people-closeBtn">关闭成员显示</a>
</div>
<!--确认加入成员-->
<div id="joinzuqun-people">
    <div class="waimian-joinzuqun-people">
        <div class="limian-joinzuqun-people">

            <g:each in="${userInfoList}" status="i" var="userInfo">
            <div class="joinpeople-list">
                <div class="joinpeople-list-headerpic">
                    <img src="../${userInfo.imagePath}" width="80" height="80">
                </div>
                <div class="joinpeople-list-name">
                    ${userInfo.loginName}
                </div>
            </div>
            </g:each>
        </div>
    </div>
    <a class="join-zuqun-btn btn" onclick="addToGroup()">加入该组群</a>
</div>

</body>
</html>
