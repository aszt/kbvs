<%--
  Created by IntelliJ IDEA.
  User: c-kx
  Date: 2016/8/3
  Time: 2:54
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'common.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'bootstrap.min.css')}" type="text/css">
    <g:javascript src="jquery-1.11.3.min.js"/>
    <g:javascript src="echarts.min.js"/>
    <g:javascript src="bootstrap.min.js"/>
</head>

<body>
<div class="container-fluid">
    <div class="xiangxiziliao">
        <div class="l_userziliao">
            <div class="userheaderimg">
                <img src="${resource(dir: '',file: UserInfo.imagePath)}" width="100" height="100">
            </div>
            <div class="user_ziliaotable">
                <div class="panel panel-success" style="margin:0;">
                    <div class="panel-body pan_hei">
                        姓名
                    </div>
                    <div class="panel-footer pan_hei">
                        ${UserInfo.name}
                    </div>
                </div>
                <div class="panel panel-success" style="margin:0;">
                    <div class="panel-body pan_hei">
                        用户名
                    </div>
                    <div class="panel-footer pan_hei">
                       ${UserInfo.loginName}
                    </div>
                </div>
                <div class="panel panel-success" style="margin:0;">
                    <div class="panel-body pan_hei">
                        邮箱
                    </div>
                    <div class="panel-footer pan_hei">
                        ${UserInfo.email}
                    </div>
                </div>
                <div class="panel panel-success" style="margin:0;">
                    <div class="panel-body pan_hei">
                        手机号
                    </div>
                    <div class="panel-footer pan_hei">
                        ${UserInfo.mobilePhone}
                    </div>
                </div>
            </div>
        </div>
        <div class="r_usermap">
            <div id="test" style="padding: 50px 0;margin-top: 50px;background-color: #fff; width: 500px;height:450px;margin: 50px auto;"></div>
        </div>

    </div>

</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('test'));
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
</body>
</html>