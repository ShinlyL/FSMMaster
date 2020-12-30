<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>企业申领系统-用户登录</title>
    <link rel="stylesheet" href="assets/css/styleLogin.css"/>
</head>
<body>
    <div class="container">
        <div class="panel">
            <div class="content">
                <div class="switch">
                    <h1 id="login">登录</h1>
                </div>
                <div id="wrapform">
                    <div id="userName" class="input" aria-placeholder="用户名"><input type="text" id="username" value=""></div>
                    <div id="passWord" class="input" aria-placeholder="密码"><input type="password" id="password" value=""></div>
						<p>
						<a id="signUp" href="UserSignUp.jsp" class="input">跳转注册</a>
                        <span>|</span>
                        <a id="forget" href="UserRetrieve.jsp" class="input">忘记密码？</a>
                    	</p>                   
                    <button class="loginBtn" onclick="toLogin()">登录</button>
              </div>
            </div>
        </div>
    </div>
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="assets/js/commons.js"></script>
	<script type="text/javascript">
		function toLogin() {
			var username = $("#username").val();
			var password = $("#password").val();
			$.ajax({
				type : "post", //方法类型
				url : "login.udo", //url
				dataType : "JSON",
				async : true,
				data : {
					"username" : username,
					"password" : password,
				},
				success : function(data) {
					if (data.flag == false) {
						alert(data.msg);
					} else {
						alert(data.msg);
						window.location.href = "indexServlet";
					}
				},
				error : function() {
					alert("异常请求！");
				}
			});
		};
	</script>
</body>
</html>