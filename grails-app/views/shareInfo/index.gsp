
<%@ page import="com.panda.kbvs.ResourceInfo" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>我的分享</title>
	<link rel="stylesheet" href="${resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css')}" type="text/css">

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
			if(count== selectCount)
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
		function deletes(){
			//数组元素的字符串化(控制层好处理)
			var fileIds = fileId.join(',')
			$.ajax({
						url:'<g:createLink controller="shareInfo" action="deletes" />',
						type:'post',
						dataType:'json',
						data:{shareId:fileIds},
						success:function(msg){
//							alert(msg.state)
							if(msg.state=='删除成功'){
								for(var i=0;i<fileId.length;i++){
									var a = fileId[i]
//									var a = "b_"+fileId[i]
									$("#"+a).remove()
//									$("#checkValue").remove()
								}
								$(".g-button").css("display","none");
							}
						}
					},function(){
					}
			)
		}

	function del(shareId){
		$.ajax({
					url:'<g:createLink controller="resourceInfo" action="deletes" />',
					type:'post',
					dataType:'json',
					data:{fileId:shareId},
					success:function(msg){
						if(msg.state=='200'){
								var a = shareId
								$("#"+a).remove()
							$(".g-button").css("display","none");
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
					<g:link controller="shareInfo" action="index" style="text-decoration: none"><li class="current"><i class="fa fa-share"></i><span class="text">我的分享</span></li></g:link>
					<g:link controller="libraryInfo" action="index" style="text-decoration: none"><li class=""><i class="fa fa-cog"></i><span class="text">知识库</span></li></g:link>
				</ul>
			</div>
		</div>

		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 right-bar">
			<div class="body-right">
				<div class="all-file tab" style="display:block">

					<div class="body">
						<div class="body-nav" style="margin-top: 5px;">
							<ul class=" list-unstyled list-col">
								<li class="first">
									<input type="checkbox" id="selAll" onClick="selectAll()">
									<span class="file-name" style="margin-left:5px;">分享名</span>
									<a class="g-button" style="margin-right: 20px;">
										<span class="g-button-right">
											<i class="fa fa-trash" title="删除" style="font-size: 14px;"></i>
											<span class="text" style="width: auto;" id="deletes"onclick="deletes()">删除</span>
											%{--<span class="text" style="width: auto;" onclick="deletes()">删除</span>--}%
										</span>
									</a>

								</li>

								<li class="second">
									<span clas="file-size">大小</span>
								</li>
								<li class="three">
									<span class="file-date">分享日期</span>
								</li>
							</ul>
							<div style="clear:both;"></div>

							<div class="sharescrollbar">
								%{--触发滚动事件的条件--}%
								<input type="hidden" id="fillType" value="${fillType}">
								<div class="sharefilescrollbar">
							<div id="kl">

						%{--遍历所有文件夹--}%
						<div id="wjjs">
							<g:each in="${ShareInfoList}" status="i" var="folderInfoListInstance">
								<div class="${folderInfoListInstance.id}">
								<div class="user-file" id="${folderInfoListInstance.id}">
								%{--<div class="user-file" id="s_${folderInfoListInstance.id}">--}%
									<div class="col user-filename">
										<input type="checkbox" name="checkAll" value="${folderInfoListInstance.id}" onClick="setSelectAll()">
										<div class="fileicon share-ico"></div>
										<span style=""></span>
										<span class="file-name">
											<g:link controller="shareInfo" action="show" id="${folderInfoListInstance.id}">
											%{--将文件夹id带给控制层--}%
												${fieldValue(bean: folderInfoListInstance, field: "name")}
											</g:link>
										</span>
									<i class="anniu fa fa-trash" data-toggle="tooltip" data-placement="left" title="删除"onclick="del('${folderInfoListInstance.id}')"></i>
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
			</div>
		</div>
	</div>
</div>
	</div>
<script language="javascript">
	//当前页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $shareheight=$(".navbar").height()+$(".list-col").height()+8;
	$(".sharescrollbar").height(window.innerHeight-$shareheight);
	var $sharegao=$(".sharescrollbar").height();
	var $shareligao=$(".sharefilescrollbar").height();
	if($sharegao>$shareligao){
		$(".sharescrollbar").css("overflow-y","hidden");
	}else{
		$(".sharescrollbar").css("overflow-y","scroll");
	}
	</script>
</body>

</html>
