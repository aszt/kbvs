<!doctype html>
<html lang="ch">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="description" content="">
		<meta name="keywords" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<title>KBVS后台</title>
		<g:javascript src="jquery-1.11.3.min.js"/>
		<g:javascript src="bootstrap.min.js"/>
		<g:javascript src="layer/layer.js"/>
		<g:javascript src="echarts.min.js"/>
		<!--分页-->
		<script type="text/javascript">
		$(document).ready(function(){
			var addState="${flash.msg}"
			if(addState=="添加成功"){
				layer.msg('添加成功！', {
					icon: 1,
					time: 1000//时间设置无反应
				});
			}else if(addState=="添加失败"){
				layer.msg('添加失败！', {
					icon: 1,
					time: 1000//时间设置无反应
				});
			}else{

			}
		})
		</script>
		<script type="text/javascript">
			function changePage(method) {
				var pageNo = document.forms[0].pageNo.value;
				var totalPage = document.forms[0].totalPage.value;
				if (method == "next") {
					if (parseInt(pageNo) >= parseInt(totalPage)) {
						layer.msg('当前已经是最后一页！', {
							icon: 2,
							time: 1000//时间设置无反应
						});
						return false;
					} else {
						document.forms[0].pageNo.value = parseInt(pageNo) + 1;
					}
				}
				if (method == "previous") {
					if (parseInt(pageNo) <= 1) {
						layer.msg('当前已经是第一页！', {
							icon: 2,
							time: 1000//时间设置无反应
						});
						return false;
					} else {
						document.forms[0].pageNo.value = parseInt(pageNo) - 1;
					}
				}
				if (method == "frist") {
					document.forms[0].pageNo.value = 1;
				}
				if (method == "end") {
					document.forms[0].pageNo.value = totalPage;
				}

				document.forms[0].submit();
			}
		</script>


		<script>
		//用户操作（改变用户状态）
		function operate(id){
			$.ajax({
						url:'<g:createLink controller="adminInfo" action="changeState" />',
						type:'post',
						dataType:'json',
						data:{id:id},
						success:function(msg){
							layer.msg('操作成功', {
								icon: 1,
								time: 1000//时间设置无反应
							});
							state=msg.state
								if(state=="正常"){
									$("#operate"+id).text("禁用");
									$("#operate"+id).attr("class","btn btn-danger btn-xs")
									$("#state"+id).text("正常");
									$("#state"+id).attr("class","btn btn-success btn-xs")
								}else{
									$("#operate"+id).text("启用");
									$("#operate"+id).attr("class","btn btn-success btn-xs")
									$("#state"+id).text("禁用");
									$("#state"+id).attr("class","btn btn-danger btn-xs")
								}

						}
					}
			)
		}
			$(function() {
				$(".meun-item").click(function() {
					$(".meun-item").removeClass("meun-item-active");
					$(this).addClass("meun-item-active");
					var itmeObj = $(".meun-item").find("img");
					itmeObj.each(function() {
						var items = $(this).attr("src");
						items = items.replace("_grey.png", ".png");
						items = items.replace(".png", "_grey.png")
						$(this).attr("src", items);
					});
					var attrObj = $(this).find("img").attr("src");;
					attrObj = attrObj.replace("_grey.png", ".png");
					$(this).find("img").attr("src", attrObj);
				});

				$(".toggle-btn").click(function() {
					$("#leftMeun").toggleClass("show");
					$("#rightContent").toggleClass("pd0px");
				})
			})
//添加用户相关
		function showNotic(_this){
			$(_this).parents(".form-group").find(".error-notic").fadeIn(100);
			$(_this).focus();
		}//错误提示显示
		function hideNotic(_this){
			$(_this).parents(".form-group").find(".error-notic").fadeOut(100);
		}//错误提示隐藏
		var verify={
			//验证邮箱
			verifyEmail:function(_this){
				var validateReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				var _value=$(_this).val();
				if(!validateReg.test(_value)){
					$("#registEmailError").text("邮箱格式不正确!")
					showNotic(_this)
				}else{
					hideNotic(_this)
					$.ajax({
								url:'<g:createLink controller="userInfo" action="checkEmail" />',
								type:'post',
								dataType:'json',
								data:{email:$("#registEmail").val()},
								success:function(msg){
									if(msg.state=='200'){
										$("#registEmailError").text("当前邮箱已被注册!")
										showNotic(_this)
									}
								}
							}
					);
				}
			},
			//验证用户名
			verifyUserName:function(_this){
				var _length=$(_this).val().length;
				if(_length==0){
					$("#userNameError").text("用户名不能为空")
					showNotic(_this)
				}else{
					hideNotic(_this)
					$.ajax({
								url:'<g:createLink controller="userInfo" action="checkUserName" />',
								type:'post',
								dataType:'json',
								data:{userName:$("#userName").val()},
								success:function(msg){
									if(msg.state=='200'){
										$("#userNameError").text("用户名已被注册")
										showNotic(_this)
									}
								}
							}
					);
				}
			},
			//验证密码
			PasswordLenght:function(_this){
				var _length=$(_this).val().length;
				if(_length<6){
					showNotic(_this)
				}else{
					hideNotic(_this)
				}
			},
			//验证两次密码是否相同
			verifyPassword:function(_this){
				var password=$(_this).val();
				var password1=$("#pwd").val();
				if(password!=password1){
					showNotic(_this)
				}else{
					hideNotic(_this)
				}
			}
		}

		function addSubmit(){
			//验证用户名
			var userName_length=$("#userName").val().length;
			if(userName_length==0){
				$("#userNameError").text("用户名不能为空")
				showNotic("#userName")
				return
			}else{
				hideNotic("#userName")
				$.ajax({
							url:'<g:createLink controller="userInfo" action="checkUserName" />',
							type:'post',
							dataType:'json',
							data:{userName:$("#userName").val()},
							success:function(msg){
								if(msg.state=='200'){
									$("#userNameError").text("用户名已被注册")
									showNotic("#userName")
									return
								}
							}
						}
				);

			}
			//验证密码
			var pawd_length=$("#pwd").val().length;
			if(pawd_length<6){
				showNotic("#pwd")
				return
			}else{
				hideNotic("#pwd")
			}
			//验证密码相等
			var pwd1=$("#pwd1").val();
			var pwd=$("#pwd").val();
			if(pwd!=pwd1){
				showNotic("#pwd1")
				return
			}else{
				hideNotic("#pwd1")
			}

			//验证邮箱
			var validateReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			var registEmail=$("#registEmail").val();
			if(!validateReg.test(registEmail)){
				$("#registEmailError").text("邮箱格式不正确!")
				showNotic("#registEmail")
				return
			}else{
				hideNotic("#registEmail")
				$.ajax({
							url:'<g:createLink controller="userInfo" action="checkEmail" />',
							type:'post',
							dataType:'json',
							data:{email:$("#registEmail").val()},
							success:function(msg){
								if(msg.state=='200'){
									$("#registEmailError").text("当前邮箱已被注册!")
									showNotic("#registEmail")
									return
								}
							}
						}
				);

			}


			document.forms[2].submit();
		}

function show(id){
	layer.open({
		type: 2,
		title: '用户详情',
		shadeClose: true,
		shade: 0.8,
		area: ['900px', '600px'],
		content: "<g:createLink controller="adminInfo" action="showUser"/>"+"?id="+id
	});
}
		</script>
		<!--[if lt IE 9]>
		<g:javascript src="html5.js"/>
		<g:javascript src="respond.min.js"/>
        <![endif]-->
		<g:javascript src="bootstrap-switch.min.js"/>
		<g:javascript src="layer/layer.js"/>
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'common.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'slide.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'bootstrap.min.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'flat-ui.min.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'jquery.nouislider.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'bootstrap-switch.min.css')}" type="text/css">
	</head>

	<body>
		<div id="wrap">
			<!-- 左侧菜单栏目块 -->
			<div class="leftMeun" id="leftMeun">
				<div id="logoDiv"> 
					<p id="logoP"><img id="logo" src="${resource(dir: 'images',file: 'logo1.png')}"><span></span></p>
				</div>
				<div id="personInfor">
					<p id="adminName">管理员</p>
					<p><span>${session.adminInfo.name}</span></p>
					<p>
						<g:link controller="userInfo" action="loginOut">退出登录</g:link>
					</p>
				</div>
				<div class="meun-title">账号管理</div>
				<div class="meun-item meun-item-active" href="#user" aria-controls="user" role="tab" data-toggle="tab"><img src="${resource(dir: 'images',file: 'icon_chara_grey.png')}">用户管理</div>
				<div onclick="resCount()" class="meun-item" href="#count" aria-controls="count" role="tab" data-toggle="tab"><img src="${resource(dir: 'images',file: 'icon_user_grey.png')}">资源统计</div>
				<div class="meun-item" href="#chan" aria-controls="chan" role="tab" data-toggle="tab"><img src="${resource(dir: 'images',file: 'icon_change_grey.png')}">修改密码</div>
			</div>
			<!-- 右侧具体内容栏目 -->
			<div id="rightContent">
				<a class="toggle-btn" id="nimei">
					<i class="glyphicon glyphicon-align-justify"></i>
				</a>
				<!-- Tab panes -->
				<div class="tab-content">
					<!--用户管理模块-->
					<div role="tabpanel" class="tab-pane active" id="user">
						<div class="check-div form-inline">

							<div class="col-xs-3">
								<button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addUser">添加用户 </button>
							</div>
							<form>
								<input type="hidden" name="search" value="${search}">
								<input type="hidden" name="pageNo" value="${pageNo}">
								<input type="hidden" name="totalPage" value="${totalPage}">
							</form>
							<form>
								<div class="col-xs-4">
									<input type="text" class="form-control input-sm" placeholder="登录名/姓名" name="search" value="${search}">
									<input type="hidden" name="pageNo" value="1">
									<button class="btn btn-white btn-xs" type="submit">查 询</button>
								</div>
							</form>
						</div>
						<div class="data-div">
							<div class="row tableHeader">
								<div class="col-xs-2 ">
									姓名
								</div>
								<div class="col-xs-2">
									用户名
								</div>
								<div class="col-xs-2">
									邮箱
								</div>
								<div class="col-xs-2">
									电话
								</div>
								<div class="col-xs-2">
									状态
								</div>
								<div class="col-xs-2">
									操作
								</div>
							</div>
							<!--用户列表-->
							<g:each in="${userInfoList}"  var="userInfo">
							<div class="tablebody" style="margin-bottom: 0px">

								<div class="row">
									<div class="col-xs-2 ">
										${userInfo.name}
									</div>
									<div class="col-xs-2">
									    ${userInfo.loginName}
									</div>
									<div class="col-xs-2">
										${userInfo.email}
									</div>
									<div class="col-xs-2">
										${userInfo.mobilePhone}
									</div>
									<div class="col-xs-2">

										<g:if test="${userInfo?.state=='正常'}">
											<button class="btn btn-success btn-xs" id="state${userInfo.id}" style=" cursor:default;">正常</button>
										</g:if>
										<g:elseif test="${userInfo?.state=='未激活'}">
											<button class="btn btn-default btn-xs" id="state${userInfo.id}" style=" cursor:default;">未激活</button>
										</g:elseif>
										<g:else>
											<button class="btn btn-danger btn-xs" id="state${userInfo.id}" style=" cursor:default;">禁用</button>
										</g:else>
									</div>
									<div class="col-xs-2">
										<button class="btn btn-success btn-xs" onclick="show('${userInfo.id}')">查看</button>

										<g:if test="${userInfo?.state=='禁用'}">
											<button class="btn btn-success btn-xs" id="operate${userInfo.id}" onclick="operate('${userInfo.id}')">启用</button>
										</g:if>

										<g:elseif test="${userInfo?.state=='正常'}">
											<button class="btn btn-danger btn-xs" id="operate${userInfo.id}" onclick="operate('${userInfo.id}')">禁用</button>
										</g:elseif>

										<g:else>
											<button class="btn btn-warning btn-xs" id="operate${userInfo.id}" onclick="operate('${userInfo.id}')">激活</button>
										</g:else>
									</div>
								</div>

							</div>

							</g:each>

						</div>
						<!--页码块-->

				<div class="row text-center" style="margin-top: 0px">
					<ul class="pagination">
						<!-- <li class="active"><a href="#">1</a></li> -->
						<li><a>共有${count }条记录，当前第${pageNo }/${totalPage}页</a></li>
						<li><a href="#" onclick="changePage('frist')">首页</a></li>
						<li><a href="#" onclick="changePage('previous')">上一页</a></li>
						<li><a href="#" onclick="changePage('next')">下一页</a></li>
						<li><a href="#" onclick="changePage('end')">尾页</a></li>
					</ul>
				</div>

						<!--弹出添加用户窗口-->
						<div class="modal fade" id="addUser" role="dialog" aria-labelledby="gridSystemModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title" id="gridSystemModalLabel">添加用户</h4>
									</div>
									<g:form controller="adminInfo" action="addUser" method="post" class="form-horizontal">
									<div class="modal-body">
										<div class="container-fluid">


												<div class="form-group ">
													<label for="userName" class="col-xs-3 control-label">*用户名：</label>
													<div class="col-xs-8 ">
														<input type="text" class="form-control input-sm duiqi" id="userName" placeholder="输入用户名" name="userName" onblur="verify.verifyUserName(this)" >
														<span class="error-notic" id="userNameError">用户名已被注册!</span>
													</div>
												</div>
												<div class="form-group">
													<label for="pwd" class="col-xs-3 control-label">*密码：</label>
													<div class="col-xs-8 ">
														<input type="password" class="form-control input-sm duiqi" id="pwd" placeholder="密码（字母、数字，至少6位）" name="pwd" onblur="verify.PasswordLenght(this)">
														<span class="error-notic">请根据提示输入密码!</span>
													</div>
												</div>
												<div class="form-group">
													<label for="pwd1" class="col-xs-3 control-label">*确认密码：</label>
													<div class="col-xs-8 ">
														<input type="password" class="form-control input-sm duiqi" id="pwd1" placeholder="再次输入密码" onblur="verify.verifyPassword(this)">
														<span class="error-notic">两次密码输入不一致!</span>
													</div>
												</div>
												<div class="form-group">
													<label for="registEmail" class="col-xs-3 control-label">*电子邮箱：</label>
													<div class="col-xs-8">
														<input type="email" class="form-control input-sm duiqi" id="registEmail" placeholder="输入邮箱" name="email" onblur="verify.verifyEmail(this)">
														<span class="error-notic" id="registEmailError" >邮箱格式不正确</span>
													</div>
												</div>
												<div class="form-group">
													<label for="sLink" class="col-xs-3 control-label">真实姓名：</label>
													<div class="col-xs-8 ">
														<input type="text" class="form-control input-sm duiqi" id="sLink" placeholder="" name="realName">
													</div>
												</div>

												<div class="form-group">
													<label for="phone" class="col-xs-3 control-label">电话：</label>
													<div class="col-xs-8">
														<input type="tel" class="form-control input-sm duiqi" id="phone" placeholder="" name="phone">
													</div>
												</div>

										</div>
									</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
											<button type="button" class="btn btn-xs btn-green" onclick="addSubmit()">添 加</button>
										</div>
									</g:form>

								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->

						<!--弹出查看用户窗口-->
						<div class="modal fade" id="reviseUser" role="dialog" aria-labelledby="gridSystemModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									
										
									<div class="modal-body"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									


									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->

						

					</div>
				<!--资源统计-->
				<div role="tabpanel" class="tab-pane" id="count">
					<div class="check-div">
						资源统计
					</div>


				<div id="resCount" style="padding: 50px 0;margin-top: 50px;background-color: #fff; width: 750px;height:520px;margin: 50px auto;"></div>

			</div>



					<!-- 修改密码模块 -->
					<div role="tabpanel" class="tab-pane" id="chan">
						<div class="check-div">
							密码修改
						</div>
						<div style="padding: 50px 0;margin-top: 50px;background-color: #fff; text-align: right;width: 420px;margin: 50px auto;">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="oldPwd" class="col-xs-4 control-label">原密码：</label>
									<div class="col-xs-5">
										<input type="password" class="form-control input-sm duiqi" placeholder="输入密码" style="margin-top: 7px;"name="oldPwd" id="oldPwd">
										<span class="error-notic" id="oldPwdError">原密码不能为空</span>
									</div>
								</div>
								<div class="form-group">
									<label for="resetPwd" class="col-xs-4 control-label">新密码：</label>
									<div class="col-xs-5">
										<input type="password" class="form-control input-sm duiqi" id="resetPwd" placeholder="密码（字母、数字，至少6位）" style="margin-top: 7px;" name="password">
										<span class="error-notic">请根据提示输入密码</span>
									</div>
								</div>
								<div class="form-group">
									<label for="resetPwd1" class="col-xs-4 control-label">重复密码：</label>
									<div class="col-xs-5">
										<input type="password" class="form-control input-sm duiqi" id="resetPwd1" placeholder="确认密码" style="margin-top: 7px;">
										<span class="error-notic">两次密码不同！</span>
									</div>
								</div>
								<div class="form-group text-right">
									<div class="col-xs-offset-4 col-xs-5" style="margin-left: 169px;">
										<button type="reset" class="btn btn-xs btn-white">取 消</button>
										<button type="button" class="btn btn-xs btn-green" onclick="changePwd()">修改</button>
									</div>
								</div>
							</form>
						</div>

					</div>
					
					</div>
					
				</div>
			</div>
		</div>
	    <g:javascript src="jquery.nouislider.js"/>

<!--资源统计相关-->
<script>

		function resCount(){

			$.ajax({
				url:'<g:createLink controller="adminInfo" action="resCount" />',
				type:'post',
				dataType:'json',
				data:{},
				success:function(msg){
					// 基于准备好的dom，初始化echarts实例
					var myChart = echarts.init(document.getElementById('resCount'));
					video = msg.Video
					word =msg.Word
					music =msg.Music
					photo =msg.Images
					other =msg.Other
					count=msg.Count
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

				}

			})
		}


</script>

	<!--修改密码相关-->
		<script>
			function changePwd(){

				var oldPwd_length=$("#oldPwd").val().length;
				if(oldPwd_length==0){
					$("#oldPwdError").text("原密码不能为空")
					showNotic("#oldPwd")
					return
				}else{
					hideNotic("#oldPwd")
				}

				//验证密码
				var newPwd_length=$("#resetPwd").val().length;
				if(newPwd_length<6){
					showNotic("#resetPwd")
					return
				}else{
					hideNotic("#resetPwd")
				}
				//验证密码相等
				var newPwd1=$("#resetPwd1").val();
				var newPwd=$("#resetPwd1").val();
				if(newPwd1!=newPwd){
					showNotic("#resetPwd1")
					return
				}else{
					hideNotic("#resetPwd1")
				}
				var oldPwd=$("#oldPwd").val()

				$.ajax({
							url:'<g:createLink controller="adminInfo" action="changePwd" />',
							type:'post',
							dataType:'json',
							data:{oldPassword:oldPwd,password:newPwd},
							success:function(msg){
								state=msg.state
								if(state=="200"){
									layer.msg('修改成功，请重新登陆', {
										icon: 1,
										time: 4000//时间设置无反应
									});
									window.location = "<g:createLink controller="adminInfo" action="adminLogin" />"
								}else{
									$("#oldPwdError").text("原密码输入有误")
									showNotic("#oldPwd")
									setTimeout(function () {
										hideNotic("#verifyError")
									}, 2000);
								}

							}
						}
				)
			}

		</script>
	</body>

</html>