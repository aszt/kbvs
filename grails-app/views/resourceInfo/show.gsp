<!doctype html>
<html  dir="ltr" mozdisallowselectionprint moznomarginboxes>
<head>
<meta charset="utf-8">
    <meta name="layout" content="main">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="google" content="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>文档显示</title>

    <link rel="stylesheet" href="${resource(dir: 'css',file: 'bootstrap.css')}" type="text/css">
    <g:javascript src="layer/layer.js"/>

    <link rel="stylesheet" href="${resource(dir: 'css',file: 'textshow.css')}" type="text/css">
    <link href="${resource(dir: 'css', file: 'viewer.css')}" rel="stylesheet">
    <g:javascript src="compatibility.js"/>
    <link href="${resource(dir: 'locale', file: 'locale.properties')}" rel="resource" type="application/l10n">
    <g:javascript src="l10n.js"/>
    <g:javascript src="build/pdf.js"/>
    <g:javascript src="debugger.js"/>
    <g:javascript src="viewer.js"/>

  </head>
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
            style="">
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
<div class="text-title" style="margin-top: 65px;">
        	<i class="ic icw"></i>
            <span class="text-h">${resourceInfo.name}</span>
            <span class="text-xinxi">${resourceInfo.remark}</span>
        </div>
        <div class="clear"></div>
 <div class="content">
       <!--显示pdf-->
 <div class="leftwd-bar">
  <iframe src="${resource(dir: 'resourceInfo',file: 'show2')}?file=${resourceInfo.pdfPath}"  width="954px" height="800px"></iframe>

  </div>
  
  <div class="rightwd-bar">
      <div class="widthnum">
      <div class="tuijian-title">
      	关联文档推荐
      </div>
      <div class="tuijian-list">

          <g:each in="${ResourceList}" status="i" var="resourceInfo">
    	<div class="tuijian">

            <g:if test="${resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg'||resourceInfo?.extName=='gif'||resourceInfo?.extName=='bmp'||resourceInfo?.extName=='ico'}">
                <i class="ic fileicon-pic" ></i>
            </g:if>
            <g:elseif test="${resourceInfo?.extName=='doc'||resourceInfo?.extName=='docx'}">
                <i class="ic fileicon-word" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='xls'||resourceInfo?.extName=='xlsx'}">
                <i class="ic fileicon-xls" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma'||resourceInfo?.extName=='flac'||resourceInfo?.extName=='ape'}">
                <i class="ic fileicon-music" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf'}">
                <i class="ic fileicon-video" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='pptx'||resourceInfo?.extName=='ppt'}">
                <i class="ic fileicon-ppt" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='rar'||resourceInfo?.extName=='zip'||resourceInfo?.extName=='7z'}">
                <i class="ic fileicon-yasuo" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='pdf'}">
                <i class="ic fileicon-pdf" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='txt'}">
                <i class="ic fileicon-text" ></i>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='torrent'}">
                <i class="ic fileicon-bt" ></i>
            </g:elseif>
            <g:else>
                <i class="ic fileicon-other" ></i>
            </g:else>

            <span class="tuijian-name">

            <g:if test="${resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg'}">
                <a onclick="showImg('${resourceInfo.id}')" href="#">${raw(resourceInfo.name)}.${resourceInfo.extName}</a>
            </g:if>
            <g:elseif test="${resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf'}">
                <g:link controller="resourceInfo" action="showVideo" id="${resourceInfoInstance.id}">
                    ${raw(resourceInfo.name)}.${resourceInfo.extName}
                </g:link>
            </g:elseif>
            <g:elseif test="${resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma'}">
                <a onclick="showMusic('${resourceInfo.id}')" href="#">${raw(resourceInfo.name)}.${resourceInfo.extName}</a>
            </g:elseif>
                <g:elseif test="${resourceInfo?.type=='other'}">
                    <a onclick="showOther()" href="#">${raw(resourceInfo.name)}.${resourceInfo.extName}</a>
                </g:elseif>
            <g:else>
                <g:link action="show" id="${resourceInfo.id}">
                    ${raw(resourceInfo.name)}.${resourceInfo.extName}
                </g:link>
            </g:else>

            </span>
            <div class="xianguandu">作者:
                <g:if test="${resourceInfo?.author==""}">
                    --
                </g:if>
                <g:else>
                    ${resourceInfo.author}
                </g:else>
            </div>
        </div>
          </g:each>
=======================
          <div class="tuijian-title">
              相似文档推荐
          </div>
          <g:each in="${resourceInfoList}" status="i" var="resourceInfo">
              <div class="tuijian">

                  <g:if test="${resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg'||resourceInfo?.extName=='gif'||resourceInfo?.extName=='bmp'||resourceInfo?.extName=='ico'}">
                      <i class="ic fileicon-pic" ></i>
                  </g:if>
                  <g:elseif test="${resourceInfo?.extName=='doc'||resourceInfo?.extName=='docx'}">
                      <i class="ic fileicon-word" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='xls'||resourceInfo?.extName=='xlsx'}">
                      <i class="ic fileicon-xls" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma'||resourceInfo?.extName=='flac'||resourceInfo?.extName=='ape'}">
                      <i class="ic fileicon-music" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf'}">
                      <i class="ic fileicon-video" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='pptx'||resourceInfo?.extName=='ppt'}">
                      <i class="ic fileicon-ppt" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='rar'||resourceInfo?.extName=='zip'||resourceInfo?.extName=='7z'}">
                      <i class="ic fileicon-yasuo" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='pdf'}">
                      <i class="ic fileicon-pdf" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='txt'}">
                      <i class="ic fileicon-text" ></i>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='torrent'}">
                      <i class="ic fileicon-bt" ></i>
                  </g:elseif>
                  <g:else>
                      <i class="ic fileicon-other" ></i>
                  </g:else>

                  <span class="tuijian-name">

                  <g:if test="${resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg'}">
                      <a onclick="showImg('${resourceInfo.id}')" href="#">${raw(resourceInfo.name)}.${resourceInfo.extName}</a>
                  </g:if>
                  <g:elseif test="${resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf'}">
                      <g:link controller="resourceInfo" action="showVideo" id="${resourceInfo.id}">
                          ${raw(resourceInfo.name)}.${resourceInfo.extName}
                      </g:link>
                  </g:elseif>
                  <g:elseif test="${resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma'}">
                      <a onclick="showMusic('${resourceInfo.id}')" href="#">${raw(resourceInfo.name)}.${resourceInfo.extName}</a>
                  </g:elseif>
                      <g:elseif test="${resourceInfo?.type=='other'}">
                          <a onclick="showOther()" href="#">${raw(resourceInfo.name)}.${resourceInfo.extName}</a>
                      </g:elseif>
                  <g:else>
                      <g:link action="show" id="${resourceInfo.id}" class="widthnum">
                          ${raw(resourceInfo.name)}.${resourceInfo.extName}
                      </g:link>
                  </g:else>

                  </span>
                  <div class="xianguandu">作者:
                  <g:if test="${resourceInfo?.author==""}">
                      --
                  </g:if>
                  <g:else>
                      ${resourceInfo.author}
                  </g:else>
                  </div>
              </div>
          </g:each>

    </div>


  </div>
      </div>
  </div>

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
                <img class="showImgPath" src="images/DSC01158.JPG">
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
        layer.open({
            type: 2,
            title: false,
            closeBtn: 0, //不显示关闭按钮
            shade: [0],
            area: ['320px', '80px'],
            offset: 'rb', //右下角弹出
            shadeClose: true, //开启遮罩关闭
            shift: 2,
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
</script>

</body>
</html>
