<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<title>登录</title>
	<link rel="stylesheet" href="${resource(dir: 'css',file: 'style.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css',file: 'iconfont.css')}">
	<g:javascript src="jquery.js"/>
	<!-- 为使用方便，直接使用jquery.js库 -->
	<!-- 引入封装了failback的接口--initGeetest -->
	<script src="http://static.geetest.com/static/tools/gt.js"></script>
</head>
<body>
  
	
    <div class="logo">
    <a>
    <img src="${resource(dir: 'images',file: 'logo.png.png')}">
    </a>
    </div>
	<div class="login-box">
		<div class="box-con tran">
			<div class="login-con f-l">
			<g:form controller="userInfo" action="doLogin" method="post" id="loginForm">
				<div class="form-group">
					<input type="text" placeholder="邮箱/用户名" onblur="verify.verifyLoginName(this)" id="loginName" name="loginName"/>
					<span class="error-notic">邮箱/密码不能为空</span>
				</div>
				<div class="form-group">
					<input type="password" placeholder="密码" onblur="verify.verifyLoginPwd(this)" id="loginPwd" name="password"/>
					<span class="error-notic">密码不能为空</span>
					<span style="font-size: 14px; color: #ff4e00; font-weight: 500;"> ${flash.message}</span>
				</div>
				<div class="form-group">
					<button type="button" class="tran pr" style=" cursor:pointer;" id="popup-submit">
						<a href="javascript:;" class="tran" >登录</a>
						<img class="loading" src="images/loading.gif" style="display:none">
					</button>
					<span class="error-notic" id="lverifyError">验证码错误</span>
					<span style="font-size: 14px; color: #ff4e00; font-weight: 500;"> ${flash.registMsg}</span>
				</div>
				<div id="popup-captcha"></div>
			</g:form>
				<div class="from-line"></div>
				<div class="form-group">
					<a href="javascript:;" class="move-signup a-tag tran blue-border">还没有帐号？免费注册<i class="iconfont tran">&#xe606;</i></a>
				</div>
				<div class="form-group">
					<a href="javascript:;" class="move-reset a-tag tran">忘记密码？重置 <i class="iconfont tran">&#xe606;</i></a>
				</div>
			</div>
			<!-- 登录 -->

			<div class="signup f-l">

			<g:form controller="userInfo" action="doRegister" method="post" id="registForm">
				<div class="signup-email">
                <div class="form-group">
						<input type="text" placeholder="邮箱" class="email-mobile" onblur="verify.verifyEmail(this)" id="registEmail" name="email">
						<span class="error-notic" id="registEmailError" >邮箱格式不正确</span>
				</div>
					<div class="form-group">
						<input type="text" placeholder="用户名" onblur="verify.verifyUserName(this)" id="userName" name="loginName">
						<span class="error-notic" id="userNameError">用户名已被注册!</span>
					</div>
					<div class="form-group">
						<input type="password" placeholder="密码（字母、数字，至少6位）" onblur="verify.PasswordLenght(this)" id="password" name="password">
						<span class="error-notic">请根据提示输入密码!</span>
					</div>
                    <div class="form-group">
						<input type="password" placeholder="确认密码" onblur="verify.verifyPassword(this)" id="password1" name="passwords">
						<span class="error-notic">两次密码输入不一致!</span>
					</div>
                    <!--验证码-->

					<div id="float-captcha" ></div>
					<p id="wait" class="show">正在加载验证码......</p>
					<p id="notice" class="hide">请先拖动验证码到相应位置</p>
					<div class="form-group">
						<button type="button" class="tran pr" id="float-submit">
							<a href="javascript:;" class="tran">注册</a>
							<img class="loading" src="images/loading.gif">
						</button>
						<span class="error-notic" id="rverifyError">验证码错误</span>
					</div>
					%{--<p class="view-clause">点击注册，即同意我们的 <a href="#">用户隐私条款</a></p>--}%
				</div><!-- 邮箱注册 -->
			</g:form>
				<div class="from-line"></div>
				<div class="form-group">
					<a href="javascript:;" class="move-login a-tag tran blue-border">已有帐号？登录<i class="iconfont tran">&#xe606;</i></a>
				</div>
			</div>
			<!-- 注册 -->
			<!--重置密码-->
			<div class="mimachongzhi f-l">
			<g:form controller="userInfo" action="sendResetEmail" method="post">
				<div class="form-group">
					<input type="text" placeholder="请输入您的注册邮箱" onblur="verify.verifyResetEmail(this)" id="resetEmail" name="resetEmail">
					<span class="error-notic" id="resetEmailError">邮箱格式不正确</span>
				</div>
				<div class="form-group">
					<button type="button" class="tran pr" id="reset-submit">
						<a href="javascript:;" class="tran">发送重置密码邮件</a>
						<img class="loading" src="images/loading.gif">
					</button>
				</div>
			</g:form>
				<div class="from-line"></div>
				<div class="form-group">
					<a href="javascript:;" class="move-signup	a-tag tran blue-border">还没有帐号？免费注册<i class="iconfont tran">&#xe606;</i></a>
				</div>
				<div class="form-group">
					<a href="javascript:;" class="move-login a-tag tran">已有帐号？登录<i class="iconfont tran">&#xe606;</i></a>
				</div>
			</div>
		</div>
	</div>

	<div class="login-footer">
		<h1>知识库管理系统</h1>
		<p style="margin-top: 5px;">第五届“中国软件杯”大学生软件设计大赛</p>
		<p>滁州职业技术学院代表队</p>
	</div>
	<script>
		var _handle='';//储存电话是否填写正确
		$(function(){
			$(".signup-form input").on("focus",function(){
				$(this).parent().addClass("border");
			});
			$(".signup-form input").on("blur",function(){
				$(this).parent().removeClass("border");
			})
			//注册方式切换
			$(".signup-select").on("click",function(){
				var _text=$(this).text();
				var $_input=$(this).prev();
				$_input.val('');
				if(_text=="邮箱注册"){
					$(".signup-tel").fadeOut(180);
					$(".signup-email").fadeIn(200);
					$(this).text("手机注册");
					$_input.attr("placeholder","邮箱");
					$_input.attr("onblur","verify.verifyEmail(this)");
					$(this).parents(".form-group").find(".error-notic").text("邮箱格式不正确")
				}
			});
			//步骤切换
			var _boxCon=$(".box-con");
			$(".move-login").on("click",function(){
				$(_boxCon).css({
					'marginLeft':0
				})
			});
			$(".move-signup").on("click",function(){
				$(_boxCon).css({
					'marginLeft':-320
				})
			});
			$(".move-other").on("click",function(){
				$(_boxCon).css({
					'marginLeft':-640
				})
			});
			$(".move-reset").on("click",function(){
				$(_boxCon).css({
					'marginLeft':-640
				})
			});
			$("body").on("click",".move-addinf",function(){
				$(_boxCon).css({
					'marginLeft':-1280
				})
			});

		});
		
		//表单验证
		function showNotic(_this){
			$(_this).parents(".form-group").find(".error-notic").fadeIn(100);
            $(_this).focus();
		}//错误提示显示
		function hideNotic(_this){
			$(_this).parents(".form-group").find(".error-notic").fadeOut(100);
		}//错误提示隐藏
		var verify={
			verifyLoginName:function(_this){
				var _length=$(_this).val().length;
				if(_length==0){
					showNotic(_this)
				}else{
					hideNotic(_this)
				}
			},//验证登录名不为空

			verifyLoginPwd:function(_this){
				var _length=$(_this).val().length;
				if(_length==0){
					showNotic(_this)
				}else{
					hideNotic(_this)
				}
			},//验证登录密码不为空
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
			},//验证邮箱
			verifyResetEmail:function(_this){
				var validateReset = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				var _value=$(_this).val();
				if(!validateReset.test(_value)){
					$("#resetEmailError").text("邮箱格式不正确!")
					showNotic(_this)
				}else{
					hideNotic(_this)
					$.ajax({
								url:'<g:createLink controller="userInfo" action="checkResetEmail" />',
								type:'post',
								dataType:'json',
								data:{email:$("#resetEmail").val()},
								success:function(msg){
									if(msg.state=='200'){
										$("#resetEmailError").text("无该邮箱注册的用户!")
										showNotic(_this)
									}
								}
							}
					);
				}
			},//验证重置密码邮箱
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
			},//验证用户名
			PasswordLenght:function(_this){
				var _length=$(_this).val().length;
				if(_length<6){
					showNotic(_this)
				}else{
            		hideNotic(_this)
            	}
			},//验证设置密码长度
			verifyPassword:function(_this){
				var password=$(_this).val();
				var password1=$("#password").val();
				if(password!=password1){
					showNotic(_this)
				}else{
					hideNotic(_this)
				}
			}//验证两次密码是否相同
		}


<!--登录验证码-->
	var handlerPopup = function (captchaObj) {
		$("#popup-submit").click(function () {


			if($("#loginName").val().length==0){
				showNotic("#loginName")
				return
			}
			if($("#loginPwd").val().length==0){
				showNotic("#loginPwd")
				return
			}


			var validate = captchaObj.getValidate();
			if (!validate) {
				alert('请先完成验证！');
				return;
			}
			$.ajax({
				url: "<g:createLink controller="userInfo" action="verifyLogin"/>", // 进行二次验证
				type: "post",
				dataType: "json",
				data: {
					// 二次验证所需的三个值
					geetest_challenge: validate.geetest_challenge,
					geetest_validate: validate.geetest_validate,
					geetest_seccode: validate.geetest_seccode
				},
				success: function (data) {
					if (data && (data.status === "success")) {
							document.forms[0].submit();

					} else {
						showNotic("#lverifyError")
						setTimeout(function () {
							hideNotic("#lverifyError")
						}, 1000);
					}
				}
			});
		});

		// 弹出式需要绑定触发验证码弹出按钮
		captchaObj.bindOn("#popup-submit");

		// 将验证码加到id为captcha的元素里
		captchaObj.appendTo("#popup-captcha");

		// 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
	};

	$.ajax({
		// 获取id，challenge，success（是否启用failback）
		url: "<g:createLink controller="userInfo" action="startCaptcha"/>",
		type: "get",
		dataType: "json",
		success: function (data) {
			// 使用initGeetest接口
			// 参数1：配置参数
			// 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件

			initGeetest({
				gt: data.gt,
				challenge: data.challenge,
				product: "popup", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
				offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
			}, handlerPopup);
		}
	});

</script>

<!--注册验证码-->
<script>

	var handlerEmbed = function (captchaObj) {

		$("#float-submit").click(function (e) {
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
			var password_length=$("#password").val().length;
			if(password_length<6){
				showNotic("#password")
				return
			}else{
				hideNotic("#password")
			}
			//验证密码相等
			var password1=$("#password1").val();
			var password=$("#password").val();
			if(password!=password1){
				showNotic("#password1")
				return
			}else{
				hideNotic("#password1")
			}

			var validate = captchaObj.getValidate();
			if (!validate) {
				$("#notice")[0].className = "show";
				setTimeout(function () {
					$("#notice")[0].className = "hide";
				}, 2000);
				e.preventDefault();
				return
			}
			$.ajax({
				url: "<g:createLink controller="userInfo" action="verifyLogin"/> ", // 进行二次验证
				type: "post",
				dataType: "json",
				data: {
					// 二次验证所需的三个值
					geetest_challenge: validate.geetest_challenge,
					geetest_validate: validate.geetest_validate,
					geetest_seccode: validate.geetest_seccode
				},
				success: function (data) {
					if (data && (data.status === "success")) {
						document.forms[1].submit();

					} else {
						showNotic("#rverifyError")
						setTimeout(function () {
							hideNotic("#rverifyError")
						}, 1000);
					}
				}
			});

		});

		// 将验证码加到id为captcha的元素里
		captchaObj.appendTo("#float-captcha");

		captchaObj.onReady(function () {
			$("#wait")[0].className = "hide";
		});

		// 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
	};
	$.ajax({
		// 获取id，challenge，success（是否启用failback）
		url: "<g:createLink controller="userInfo" action="startCaptcha"/> ",
		type: "get",
		dataType: "json",
		success: function (data) {

			// 使用initGeetest接口
			// 参数1：配置参数
			// 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
			initGeetest({
				gt: data.gt,
				challenge: data.challenge,
				product: "float", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
				offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
			}, handlerEmbed);
		}
	});

</script>
<script>
	$("#reset-submit").click(function(e){
		var validateReset = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var resetEmail=$("#resetEmail").val();
		if(!validateReset.test(resetEmail)){
			$("#resetEmailError").text("邮箱格式不正确!")
			showNotic("#resetEmail")
			return
		}else{
			hideNotic("#resetEmail")
			$.ajax({
						url:'<g:createLink controller="userInfo" action="checkResetEmail" />',
						type:'post',
						dataType:'json',
						data:{email:$("#resetEmail").val()},
						success:function(msg){
							if(msg.state=='200'){
								$("#resetEmailError").text("无该邮箱注册的用户!")
								showNotic("#resetEmail")
								return
							}
						}
					}
			);

		}
		document.forms[2].submit();

	})
</script>
</body>
</html>