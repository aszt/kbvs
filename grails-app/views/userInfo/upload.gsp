<%--
  Created by IntelliJ IDEA.
  User: c-kx
  Date: 2016/6/1
  Time: 15:37
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div class="container" id="main">

    <div class="demo">
        <p id="swfContainer">
        </p>
    </div>
</div>


<g:javascript src="jquery-1.11.3.min.js"/>
<g:javascript src="swfobject.js"/>
<g:javascript src="fullAvatarEditor.js"/>

<script type="text/javascript">
    swfobject.addDomLoadEvent(function() {

        var swf = new fullAvatarEditor( "swfContainer", {
                    id: 'swf',
                    upload_url: '<g:createLink controller="userInfo" action="uploadImg"></g:createLink>', //上传接口
                    method: 'post', //传递到上传接口中的查询参数的提交方式。更改该值时，请注意更改上传接口中的查询参数的接收方式
                    src_upload: 2, //是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
                    avatar_box_border_width: 0,
                    avatar_sizes: '100*100|50*50|32*32',
                    avatar_sizes_desc: '100*100像素|50*50像素|32*32像素'
                }, function(msg) {
                    switch (msg.code)
                    {
                        case 1 :
                            //  alert("页面成功加载了组件！");
                            break;
                        case 2 :
                            //   alert("已成功加载图片到编辑面板。");
                            document.getElementById("upload").style.display = "inline";
                            break;
                        case 3 :
                            if (msg.type == 0)
                            {
                                alert("摄像头已准备就绪且用户已允许使用。");
                            }
                            else if (msg.type == 1)
                            {
                                alert("摄像头已准备就绪但用户未允许使用！");
                            }
                            else
                            {
                                alert("摄像头被占用！");
                            }
                            break;
                        case 5 :
                            //window.location="<g:createLink controller="userInfo" action="personal"/>"
                            break;
                    }
                }
        );
        document.getElementById("upload").onclick = function() {
            swf.call("upload");
        };
    });
</script>
</body>
</html>