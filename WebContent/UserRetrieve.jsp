<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>企业申领系统-用户密码找回</title>
    <link rel="stylesheet" href="assets/css/styleRetrieve.css"/>
</head>
<body>
<div class="container">
    <div class="panel">
        <div class="content">
            <div class="switch">
                <h1 id="signUp">忘记密码</h1>
            </div>

            <div id="wrapform">
            	<div id="wrapuserName" class="input" aria-placeholder="用户名"><input type="text" id="username"></div>
                <div id="Email" class="input" aria-placeholder="邮箱"><input type="text" id="email"></div>
                <p><button id="getEmailCode">点击获取验证码</button></p>
                <div id="emailCode" class="input" aria-placeholder="邮箱验证码"><input type="text" id="verifycode"></div>
                <div id="passWord" class="input" aria-placeholder="新密码"><input type="password" id="password"></div>
                <div id="repeat" class="input" aria-placeholder="确认密码"><input type="password" id="repeatpd"></div>
                <p>
                    <a id="login" href="UserLogin.jsp" class="input">点击跳转登录</a>
                </p>
                <button onclick="toRetrieve()" id="reset" type="button">提交更改</button>
        	</div>
    	</div>
	</div>
</div>
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="assets/js/commons.js"></script>
<script type="text/javascript">
$(function() {
	//匿名函数   没有名字的方法
	$("#getEmailCode").click(function() {
		//验证邮箱是否为空
		var username = $("#username").val();
		var email = $("#email").val();
		if(username == null || username == ""){
			alert("用户名不能为空");
			$("#username").focus();
			return false;
		}else if(email == null || email == "") {
			alert("邮箱不能为空");
			$("#email").focus();
			return false;
		};
		$.ajax({
			type: "post",       //方法类型
		    url: "retrieveGetCode.udo" ,  //url
		    dataType:"JSON",
		    async:true,
		    data: {"username":username,"email":email},
		    success: function (data) {
		    	if(data.flag == false) {
		    		alert(data.msg);
		        }
		    },
		    error: function() {
		        alert("异常请求！");
		    }
		});
	});
})
function toRetrieve(){
		var username = $("#username").val();
		var verifycode=$("#verifycode").val();
		var email = $("#email").val();
		var password=$("#password").val();
		var repeatpd=$("#repeatpd").val();
		if(username == null || username == ""){
			alert("用户名不能为空");
			$("#username").focus();
			return false;
		}else if(email == null || email==""){
			alert("邮箱不能为空！");
			$("#email").focus();
			return false;
		}else if(verifycode == null || verifycode == ""){
			alert("验证码不能为空！");
			$("#verifycode").focus();
			return false;
		}else if(password == null || password == ""){
			alert("新密码不能为空");
			$("#password").focus();
			return false;
		}else if(repeatpd == null || repeatpd == ""){
			alert("重新输入密码不能为空！");
			$("#repeatpd").focus();
			return false;
		}else if(repeatpd != password){
			alert("重新输入密码不一致！");
			$("#repeatpd").focus();
			return false;
		};
		$.ajax({
			type: "post",       //方法类型
		    url: "userRetrieve.udo" ,  //url
		    dataType:"JSON",
		    async:true,
		    data: {"email":email,"verifycode":verifycode,"password":password},
		    success: function (data) {
		    	if(data.flag == false) {
		    		alert("登录信息错误，请重试");
		        }else{
		          	alert("修改密码成功，正在登录。");
		          	window.location.href="indexServlet";
		        }
		    },
		    error: function() {
		        alert("异常请求！");
		    }
		});
};
</script>
</body>
</html>