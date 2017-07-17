<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<title>重置密码</title>
	<link rel="stylesheet" href="${resource(dir: 'css',file: 'style.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css',file: 'iconfont.css')}">
	<g:javascript src="jquery.js"/>
</head>
<body>

    <div class="logo">
    <a>
    <img src="../images/logo.png.png">
    </a>
    </div>
	<div class="login-box">
		<div class="box-con tran">
			<div class="login-con f-l">
			<g:form controller="userInfo" action="doResetPassword" method="post">
				 <input type="hidden"name="email" value="${raw(email)}">
				 <input type="hidden"name="code" value="${raw(code)}">
				<div class="form-group">
					<input type="password" placeholder="密码（字母、数字，至少6位）" onblur="verify.PasswordLenght(this)" id="password" name="password">
					<span class="error-notic">请根据提示输入密码</span>
				</div>
				<div class="form-group">
					<input type="password" placeholder="再次输入密码" onblur="verify.verifyPassword(this)" id="password1">
					<span class="error-notic">两次密码输入不一致</span>
				</div>
				<div class="form-group">
					<button type="button" class="tran pr" style=" cursor:pointer;" id="reset-button">
						<a href="javascript:;" class="tran" >确认重置</a>
						<img class="loading" src="../images/loading.gif" style="display:none">
					</button>
				</div>
			</g:form>
		</div>	
	</div>

	<div class="login-footer">
		<h1>知识库管理系统</h1>
		<p style="margin-top: 5px;">第五届“中国软件杯”大学生软件设计大赛</p>
		<p>滁州职业技术学院代表队</p>
	</div>
	<script>
		//表单验证
		function showNotic(_this){
			$(_this).parents(".form-group").find(".error-notic").fadeIn(100);
            $(_this).focus();
		}//错误提示显示
		function hideNotic(_this){
			$(_this).parents(".form-group").find(".error-notic").fadeOut(100);
		}//错误提示隐藏
		var verify={
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
	</script>
		<script>
			$("#reset-button").click(function(e){
				var _length=$($("#password")).val().length;
				if(_length<6){
					showNotic("#password")
					return
				}else{
					hideNotic("#password")
				}

				var password=$("#password1").val();
				var password1=$("#password").val();
				if(password!=password1){
					showNotic("#password1")
					return
				}else{
					hideNotic("#password1")
				}
				document.forms[0].submit();

			})
		</script>
</body>
</html>