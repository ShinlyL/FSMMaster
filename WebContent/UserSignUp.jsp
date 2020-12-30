<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>企业申领系统-用户注册</title>
    <link rel="stylesheet" type="text/css" href="assets/css/styleSignUp.css"/>
</head>
<body>
<div class="container">
    <div class="panel">
        <div class="content">
            <div class="switch">
                <h1 id="signUp">用户注册</h1>
            </div>
            <div id="wrapform">
                <div id="wrapuserName" class="input" aria-placeholder="用户名"><input id="username" type="text"></div>
                <div id="Email" class="input" aria-placeholder="邮箱"><input type="text" id="email"></div>
                <p><button id="getEmailCode">点击获取验证码</button></p>
                <div id="emailCode" class="input" aria-placeholder="邮箱验证码"><input type="text" id="verifycode"></div>
                <div id="wrappassWord" class="input" aria-placeholder="密码"><input id="password" type="password"></div>
                <div class="input" aria-placeholder="确认密码"><input id="repeat" type="password"></div>
                <p>
                    <a id="login" href="UserLogin.jsp" class="input">已有账户？点击登录</a>
                </p>
                <button id="registBtn" onclick="toSignUp()" type="button">注册</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="assets/js/commons.js"></script>
<script type="text/javascript">
$(function() {
	//匿名函数   没有名字的方法
	$("#getEmailCode").click(function() {
		//验证邮箱是否已被注册
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
		    url: "signUpGetCode.udo" ,  //url
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
function toSignUp(){
	var username=$("#username").val();
	var email=$("#email").val();
	var verifycode = $("#verifycode").val();
	var password=$("#password").val();
	var repeat=$("#repeat").val();
	if(username == null || username ==""){
		alert("用户名不能为空！");
		$("#username").focus();
		return false;
	}else if(email == null || email == ""){
		alert("邮箱不能为空");
		$("#email").focus();
		return false;
	}else if(verifycode == null || verifycode == ""){
		alert("验证码不能为空");
		$("#verifycode").focus();
		return false;
	}else if(password == null || password == ""){
		alert("密码不能为空！");
		$("#password").focus();
		return false;
	}else if(repeat == null || repeat == ""){
		alert("重新输入密码不能为空！");
		$("#repeat").focus();
		return false;
	}else if(repeat != password){
		alert("两次输入密码不一致！");
		$("#repeat").focus();
		return false;
	};
	$.ajax({
		type: "post",//方法类型
	    url: "registUser.udo",
	    dataType:"JSON",
	    async:true,
	    data: {"username":username,"email":email,"verifycode":verifycode,"password":password},
	    success: function (data) {
	    	if(data.flag == false) {
	    		alert(data.msg);
	        }else{
	          	alert("注册成功");
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