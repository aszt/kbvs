
<%@ page import="com.panda.kbvs.ResourceInfo" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>其它</title>
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
		function deletes(){
			//数组元素的字符串化(控制层好处理)
			var fileIds = fileId.join(',')
			alert(fileIds)
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
			var search = $("#search").val();
			$.ajax({
						url:'<g:createLink controller="groupInfo" action="myGroup" />',
						type:'post',
						dataType:'json',
						success:function(msg){
							if(msg.state=='200'){
								var path = "../";
								if(!search){
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
		$('body').on('click','#deletes',function(){
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

		//文档关联
		$("#documentRelation").click(function(){
			var fileIds = fileId.join(',')
			$.ajax({
						url:'<g:createLink controller="resourceInfo" action="documentRelation"/>',
						type:'post',
						dataType:'json',
						data:{fileIds:fileIds},
						success:function(msg){
							if(msg.state=="关联成功"){
								layer.msg('文件关联成功', {
									icon: 1,
									time: 1000//时间设置无反应
								});
							}else{
								layer.msg('关联失败', {
									icon: 2,
									time: 1000//时间设置无反应
								});
							}
						}
					}
			)
		})

		//多文件下载
		$("#xz").click(function(){
			var fileIds = fileId.join(',')
			window.location = "<g:createLink controller="resourceInfo" action="download" />?id=" +fileIds;
		})

		//重命名
		$("#update").click(function(){
			var ids = $("#t").val()//要重命名文件的id
			var names = $("#cx").val()//要重命名文件的名字
//			alert(ids)
//			alert(names)
			$.ajax({
				url:'<g:createLink controller="folderInfo" action="reName"/>',
				type:'post',
				dataType:'json',
				data:{infoId:ids,infoName:names},
				success:function(msg){
						if(msg.state=='200'){
							window.location.reload();
						}
					}
				}
			)
		});
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
		//重命名输入框
		function rename(id,name,extName){
//			alert(id)
			$("#xx").css('display','block').appendTo("."+id);
			$("#s_"+id).css("display","none");
			$("#"+id).css("display","none");
//			摘要隐藏
			$(".a_"+id).css("display","none");

			$("#cx").val(name);
			$("#t").val(id);

			//判断修改文件格式
			if(extName=="mp3"||extName=="wav"||extName=="wma"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-music" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="doc"||extName=="docx"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-word" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="xls"||extName=="xlsx"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-xls" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="jpg"||extName=="png"||extName=="jpeg"||extName=="gif"||extName=="bmp"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-pic" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="avi"||extName=="3gp"||extName=="mp4"||extName=="rmvb"||extName=="mkv"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-video" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="pptx"||extName=="ppt"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-ppt" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="rar"||extName=="zip"){
				$(".mhsb").remove()
//				<div class="fileicon fileicon-pic" ></div>
				var s = '<div class="fileicon fileicon-yasuo" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="pdf"){
				$(".mhsb").remove()
				var s = '<div class="fileicon fileicon-pdf" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="txt"){
				$(".mhsb").remove()
				var s = '<div class="fileicon fileicon-text" ></div>'
				$(".tubiao").append(s)
			}else if(extName=="torrent"){
				$(".mhsb").remove()
				var s = '<div class="fileicon fileicon-bt" ></div>'
				$(".tubiao").append(s)
			}else if(extName==null){
				$(".mhsb").remove()
				var s = '<div class="fileicon folder" ></div>'
				$(".tubiao").append(s)
			}else{
				$(".mhsb").remove()
				var s = '<div class="fileicon fileicon-other" ></div>'
				$(".tubiao").append(s)
			}

			document.getElementById("cx").select();
		}

		//关闭重命名
		function closeRename(p){
			$("#xx").css('display','none');
//			$("#xx").remove();
			$("#"+p).css("display","block");
			$("#s_"+p).css("display","block");
//摘要显示
			$(".a_"+p).css("display","block");
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
			<li style="background: #1D74E0; border-left:1px solid #1B6DD3;border-right:1px solid  #1B6DD3;"><g:link action="nextIndex"><i class="fa fa-home" style="font-size: 20px;"></i> 我的主页</g:link></li>
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
					<g:link action="index" style="text-decoration: none"><li class=""><i class="fa fa-files-o"></i><span class="text">所有文件</span></li></g:link>
					<g:link action="categoryPage" id="activeFile" style="text-decoration: none"><li class=""><i class="fa fa-file-o"></i><span class="text">常用文件</span></li></g:link>
					<g:link action="categoryPage" id="video" style="text-decoration: none"><li class=""><i class="fa fa-file-movie-o"></i><span class="text">视频</span></li></g:link>
					<g:link action="categoryPage" id="music" style="text-decoration: none"><li class=""><i class="fa fa-file-audio-o"></i><span class="text">音乐</span></li></g:link>
					<g:link action="categoryPage" id="word" style="text-decoration: none"><li class=""><i class="fa fa-file-text-o"></i><span class="text">文档</span></li></g:link>
					<g:link action="categoryPage" id="images" style="text-decoration: none"><li class=""><i class="fa fa-file-image-o"></i><span class="text">图片</span></li></g:link>
					<g:link action="categoryPage" id="other" style="text-decoration: none"><li class="current"><i class="fa fa-navicon"></i><span class="text">其他</span></li></g:link>
					<g:link controller="shareInfo" action="index" style="text-decoration: none"><li class=""><i class="fa fa-share"></i><span class="text">我的分享</span></li></g:link>
					<g:link controller="libraryInfo" action="index" style="text-decoration: none"><li class=""><i class="fa fa-cog"></i><span class="text">知识库</span></li></g:link>
				</ul>
			</div>
		</div>

		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 right-bar">
			<div class="body-right">
				<div class="all-file tab" style="display:block">
					<div class="header"  style="border:none;">

						<g:form controller="resourceInfo" action="categorySearch" method="get" class="navbar-form navbar-right hidden-xs sea" role="search">
							<div class="form-group">
								<input type="text" class="form-control search" placeholder="搜索" name="search"  value="${search}">
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
							<ul class=" list-unstyled list-col">
								<li class="first">
									<input type="checkbox" id="selAll" onClick="selectAll()">
									<span class="file-name" style="margin-left:5px;">文件名</span>
									<a class="g-button" id="documentRelation">
										<span class="g-button-right">
											<span class="text" style="width: auto;">文件关联</span>
										</span>
									</a>
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

							<div class="otherscrollbar">
								%{--触发滚动事件的条件--}%
								<div class="otherfilescrollbar">

									%{--重命名input--}%
									<div id="xx" class="user-file" style="display: none">
										<div class="col user-filename" style="position: relative;">
											<input type="checkbox" name="checkAlls" style=" float: left ; margin-top: 12px;" >

											%{--图标样式--}%
											<div class="tubiao">
											<div class="fileicon mhsb" style="float: left;"></div>
											</div>
											%{--<div class="fileicon folder" style="float: left;"></div>--}%

											<span style=""></span>

											<span style="float: left;">
												%{--需要修改文件的id===value--}%
												<input type="hidden" id="t">
												<input id="cx" class="file-name newfile" type="text" name="folderName" value="新文件名" style="height: 20px; width: 180px; line-height: 20px;">
												<div class="surecancel">
													<span class="sure" id="update"></span>
													<span class="cancel" onclick="closeRename(t.value)"></span>
												</div>
											</span>

										</div>

										<div class="col user-filesize">
											<span class="file-size">-</span>
										</div>

										<div class="col user-filedate">
											<span class="file-date">-</span>
										</div>
									</div>
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
												<g:link action="show" id="${resourceInfoInstance.id}">
													${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}
												</g:link>
											</g:else>
										</span>
										<i class="anniu fa fa-tags" data-toggle="tooltip" data-placement="left" title="重命名" onClick="rename('${resourceInfoInstance.id}','${resourceInfoInstance.name}','${resourceInfoInstance.extName}')"></i>
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
					<span clas="file-size">大小</span>
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
			content: ['<g:createLink action="showMusic"/>?mId='+mId, 'no']//iframe的url，no代表不显示滚动条
		});
	}

	//查看图片
	function showImg(imgId){
		var search = $("#search").val();
		var fillType = $("#fillType").val();
		$.ajax({
					url:'<g:createLink controller="resourceInfo" action="showImg" />',
					type:'post',
					dataType:'json',
					data:{imgId:imgId},
					success:function(msg){
						resourceInfo=msg.value

						var path = "../"+resourceInfo.path;
						if(!search){
							path="../../"+resourceInfo.path;
						}
						$(".showImgPath").attr("src",path)
						$(".showImgName").html(resourceInfo.name)
						$(".showRaw").attr("href","http://localhost:8080/kbvs/"+resourceInfo.path)
						$(".dow").html('<a onclick="singleFileDownload('+'resourceInfo.id'+')">下载原图</a>')
						$("#imageScreen").show();
					}
				}
		)
	}
	$(function() {



		$(".otherscrollbar").scroll(function() {
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
								ResourceInfoList=msg.resourceInfoList;
								for(var i= 0;i<ResourceInfoList.length;i++){
									var resourceInfo=ResourceInfoList[i]
									var date = new Date(resourceInfo.dateCreated);
									date= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
									var fileicon
									var showDetail='<a href="/kbvs/resourceInfo/show/'+resourceInfo.id+'">'+resourceInfo.name+'.'+resourceInfo.extName+'</a></span>'
									if(resourceInfo.extName=="jpg"||resourceInfo.extName=="jpeg"||resourceInfo.extName=="png"){
										fileicon='<div class="fileicon fileicon-pic" ></div>'
										%{--<a onclick="showImg('${resourceInfoInstance.id}')" href="#">${raw(resourceInfoInstance.name)}.${resourceInfoInstance.extName}</a>--}%
										showDetail='<a href="#" onclick="showImg('+"'"+resourceInfo.id+"'"+')">'+resourceInfo.name+'.'+resourceInfo.extName+'</a>'
									}else if(resourceInfo.extName=="doc"||resourceInfo.extName=="docx"){
										fileicon='<div class="fileicon fileicon-word" ></div>'
									}else if(resourceInfo.extName=="xls"||resourceInfo.extName=="xlsx"){
										fileicon='<div class="fileicon fileicon-xls" ></div>'
									}else if(resourceInfo.extName=="mp3"||resourceInfo.extName=="wav"||resourceInfo.extName=="wma"){
										showDetail='<a onclick="showMusic('+"'"+resourceInfo.id+"'"+')" href="#">'+resourceInfo.name+'.'+resourceInfo.extName+'</a>'
										fileicon='<div class="fileicon fileicon-music" ></div>'
									}else if(resourceInfo.extName=="avi"||resourceInfo.extName=="3gp"||resourceInfo.extName=="rmvb"||resourceInfo.extName=="mp4"||resourceInfo.extName=="mkv"||resourceInfo.extName=="flv"){
										showDetail='<a href="/kbvs/resourceInfo/showVideo/'+resourceInfo.id+'">'+resourceInfo.name+'.'+resourceInfo.extName+'</a></span>'
										fileicon='<div class="fileicon fileicon-video" ></div>'
									}else if(resourceInfo.extName=="ppt"||resourceInfo.extName=="pptx"){
										fileicon='<div class="fileicon fileicon-ppt" ></div>'
									}else if(resourceInfo.extName=="rar"||resourceInfo.extName=="zip"){
										fileicon='<div class="fileicon fileicon-yasuo" ></div>'
									}else if(resourceInfo.extName=="pdf"){
										fileicon='<div class="fileicon fileicon-pdf" ></div>'
									}else if(resourceInfo.extName=="txt"){
										fileicon='<div class="fileicon fileicon-text" ></div>'
									}else if(resourceInfo.extName=="torrent"){
										fileicon='<div class="fileicon fileicon-bt" ></div>'
									}else{
										fileicon='<div class="fileicon fileicon-other" ></div>'
									}
									if(resourceInfo.type=="other"){
										showDetail='<a onclick="showOther()" href="#">'+resourceInfo.name+'.'+resourceInfo.extName+'</a>'
									}
									var res='<div class="'+resourceInfo.id+'"><div>' +
											'<div class="user-file" id="s_'+resourceInfo.id+'"><div class="col user-filename">' +
											'<input type="checkbox" name="checkAll" value="'+resourceInfo.id+'" onClick="setSelectAll()">' +
											fileicon+
											'<span style=""></span><span class="file-name">' +
											showDetail +
//											'<i class="anniu fa fa-tags" data-toggle="tooltip" data-placement="left" title="重命名" onClick="rename('+'resourceInfo.id'+','+'resourceInfo.name'+')"></i>' +
											'<i class="anniu fa fa-tags" data-toggle="tooltip" data-placement="left" title="重命名" onClick="rename('+"'"+resourceInfo.id+"'"+','+"'"+resourceInfo.name+"'"+','+"'"+resourceInfo.extName+"'"+')"></i>' +
											'<i class="anniu fa fa-download" data-toggle="tooltip" data-placement="left" title="下载" onclick="singleFileDownload('+"'"+resourceInfo.id+"'"+')"></i>' +
											'<i class="anniu fa fa-arrow-left" data-toggle="tooltip" data-placement="left" title="添加至常用文件" onclick="moveCommon('+"'"+resourceInfo.id+"'"+')"></i>' +

											'<i class="anniu fa fa-chain gl" data-toggle="tooltip" data-placement="left" title="查看关联的文件" onclick="showLink('+"'"+resourceInfo.id+"'"+')"></i>' +
											'</div><div class="col user-filesize"><span class="file-size">'+resourceInfo.fileSize+'B</span>' +
											'</div><div class="col user-filedate"><span class="file-date">'+date+'</span>' +
											'</div></div>' +
											'<div class="a_'+resourceInfo.id+'">' +
											'<div class="gaiyao"><span>'+resourceInfo.remark+'</span></div>' +
											'</div></div></div>'

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
