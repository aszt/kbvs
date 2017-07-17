<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 2016/5/17
  Time: 11:01
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'myself.css')}" type="text/css">
    <g:javascript src="echarts.min.js"/>
    <title>个人中心</title>
    <script type="text/javascript">

        $(document).ready(function(){
            //个人中心头像上传弹窗
            $('.huantouxiang').on('click', function(){

                layer.open({
                    title :'头像上传',
                    type: 1,
                    shade: 0.8,
                    area: ['630px', '500px'],
                    shadeClose: true, //点击遮罩关闭
                    content:$('#shangchuan')

                });
            });
        })


        //修改个人资料
        function updateInfo(){
            var name = document.fm.names.value;
            var loginName = document.fm.loginName.value;
            var code = document.fm.code.value;
            var email = document.fm.email.value;
            var mobilePhone = document.fm.mobilePhone.value;
            var telephone = document.fm.telephone.value;
            var department = document.fm.department.value;
            $.ajax({
                        url:'<g:createLink controller="userInfo" action="updateInfo" />',
                        type:'post',
                        dataType:'json',
                        data:{name:name,loginName:loginName,code:code,email:email,mobilePhone:mobilePhone,telephone:telephone,department:department},
                        success:function(msg){
                            if(msg.msg='修改成功'){
                                location.reload();
//                                layer.msg('修改成功');
                            }
                        }
                    },function(){
                    }
            )
        }

        function cancel(){
            $('#x').css('display','block')
            $('.xggrzl').css('display','none')

        }
    </script>
</head>

<body>
<!--导航栏 start-->
<nav class="navbar navbar-fixed-top" style="background-color: #3083EB; opacity: 0.7">
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
            <li><g:link controller="resourceInfo" action="nextIndex">我的主页</g:link></li>
            <li class="wode group"><g:link controller="groupInfo" action="index">我的组群</g:link></li>
        </ul>

        <div class="user_myself">
        %{--头像--}%
            <img class="user_header_pic hImgPath" src="${resource(dir: '',file:session.imagePath )}">
            <div role="presentation" class="dropdown user_name">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" style=" color: fff;">
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

<div class=" content" style="margin-top:180px;">
    <div class="header-box">
        <div class="userheaderpic huantouxiang">
            <img src="${resource(dir: '',file:session.imagePath )}" width="100" height="100">
        </div>
        <div class="zh">
            ${UserInfoInstance.loginName}
        </div>
    </div>
    <div class="mainbody">
        <div class="main-left">
            <div class="PersonalCenter">
                个人中心
            </div>
            <div class="clear"></div>
            <div class="grzl" id="x">
                <h4>个人信息</h4>
                <div class="user-xm">
                    <span class="xm"><span class="qianmian">姓</span><span class="houmian">名：</span></span>
                    <span class="mz" style="letter-spacing:10px">${UserInfoInstance.name}</span>
                </div>
                <div class="user-zh">
                    <span class="zhm">账<span class="houmian">名：</span></span>
                    <span class="zhmz" style="letter-spacing:1px">${UserInfoInstance.loginName}</span>
                </div>
                <div class="user-email">
                    <span class="email">电子邮箱：</span>
                    <span class="emailnum" style="letter-spacing:1px">${UserInfoInstance.email}</span>
                </div>
                <div class="user-photo">
                    <span class="photo">手<span style="padding-left:6px">机</span><span style="padding-left:6px">号：</span></span>
                    <span class="photonum" style="letter-spacing:1px">${UserInfoInstance.mobilePhone}</span>
                </div>
                <div class="user-bgphoto">
                    <span class="bgphoto">办<span class="houmian">公：</span></span>
                    <span class="bgphotonum" style="letter-spacing:1px">${UserInfoInstance.telephone}</span>
                </div>
                <div class="user-bm">
                    <span class="bm">部<span class="houmian">门：</span></span>
                    <span class="bmnum" style="letter-spacing:10px">${UserInfoInstance.department}</span>
                </div>

                <div style=" border-bottom:none; text-align:right; cursor: pointer;">
                    <span class="btn-bj">编辑个人资料</span>
                </div>
            </div>
            <div class="xggrzl">
                <h4>个人信息</h4>

                <g:form name="fm">
                    <div class="user-xm">
                        <span class="xm"><span class="qianmian">姓</span><span class="houmian">名：</span></span>
                        <input type="text" name="names" class="usrmz" style="height:25px;">
                    </div>
                    <div class="user-zh">
                        <span class="zhm">账<span class="houmian">名：</span></span>
                        <input type="text" name="loginName" class="usrzhm"style="height:25px;">
                    </div>
                    <div class="user-gh">
                        <span class="gh">工<span class="houmian">号：</span></span>
                        <input type="text" name="code" class="usrgh" style="height:25px;">
                    </div>
                    <div class="user-email">
                        <span class="email">电子邮箱：</span>
                        <input type="text" name="email" class="usremail" style="height:25px;">
                    </div>
                    <div class="user-photo">
                        <span class="photo">手<span style="padding-left:6px">机</span><span style="padding-left:6px">号：</span></span>
                        <input type="text" name="mobilePhone" class="usrphoto" style="height:25px;">
                    </div>
                    <div class="user-bgphoto">
                        <span class="bgphoto">办<span class="houmian">公：</span></span>
                        <input type="text" name="telephone" class="usrbgphoto" style="height:25px;">
                    </div>
                    <div class="user-bm">
                        <span class="bm">部<span class="houmian">门：</span></span>
                        <input type="text" name="department" class="usrbm" style="height:25px;">
                    </div>
                    <div style=" border-bottom:none; text-align:right; line-height:24px;">
                        %{--<g:actionSubmit value="保存" action="updateInfo"/>--}%
                        <button type="button" onclick="updateInfo()">保存</button>
                        <button type="reset" onclick="cancel()">取消</button>
                    </div>
                </g:form>
            </div>
        </div>


        <div id="main" style="width: 600px;height:420px; float:left; margin-left:320px; margin-top:-245px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            var video = ${Video}
            var word = ${Word}
            var music = ${Music}
            var photo = ${Images}
            var other = ${Other}
            var count=${Count}
            // 指定图表的配置项和数据
            var option = {
                backgroundColor: '#fff',//背景色
                title : {
                    text: '文件各类型个数比例',
                    subtext: '共'+count+"个文件",
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['视频','文档','音乐','图片','其它']
                },
                series : [
                    {
                        name: '文件类型',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:video, name:'视频'},
                            {value:word, name:'文档'},
                            {value:music, name:'音乐'},
                            {value:photo, name:'图片'},
                            {value:other, name:'其它'},

                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        </script>
    </div>
</div>

<div id="shangchuan" style="display: none;">
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
                    src_upload: 0, //是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
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
                            setTimeout("window.location='<g:createLink controller="userInfo" action="personal"/>'", 1000);

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