<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户管理</title>
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.gritter.css" />
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/style-responsive.css" rel="stylesheet" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<style>
body {
	padding: 50px;
}
</style>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<div class="row">
				<div class="col-lg-12 main-chart">
					<div class="row">
						<div class="container-fluid">
							<div class="app-title">
								<div>
									<h3>添加用户</h3>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item"><i class="fa fa-th"></i></li>
									<li class="breadcrumb-item active"><a href="#">添加用户信息</a></li>
								</ul>
							</div>
							<div class="col-md-6 col-sm-offset-3">
								<form action="userAdd.udo" method="post" id="myform">
									<div class="tile">
										<div class="row">
											<div class="col-md-12 col-md-offset-6">
												<h3 class="tile-title">用户信息添加</h3>
											</div>
											<div class="tile-body">
											<div class="form-horizontal">
												<div class="form-group row">
													<label class="control-label col-md-4">用户名</label>
													<div class="col-md-8">
														<input name="username" id="username" class="form-control" type="text"
															placeholder="用户名(至少8位由数字、大小写字母和_中至少两种组成)" onblur="checkUsername(this.value)"/>
															<span id="nameSpan" style="font-size: 12px"></span>
													</div>
												</div>												
												<div class="form-group row">
													<label class="control-label col-md-4">真实姓名</label>
													<div class="col-md-8">
														<input name="realName" id="realName" class="form-control"
															type="text" placeholder="请输入用户的真实姓名" onblur="checkRealname(this.value)">
															<span id="realNameSpan" style="font-size: 12px"></span>															
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">邮箱</label>
													<div class="col-md-8">
														<input name="email" id="email" class="form-control"
															type="text" placeholder="请输入用户的邮箱" onblur="checkEmail(this.value)">
															<span id="emailSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">员工编号</label>
													<div class="col-md-8">
														<input name="employeeId" id="employeeId" class="form-control"
															type="text" placeholder="请输入员工编号(账号的默认密码)" onblur="checkEmployeeId(this.value)">
															<span id="employeeIdSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">年龄</label>
													<div class="col-md-8">
														<input name="age" id="age" class="form-control"
															type="text" placeholder="请输入用户的年龄（20-50之间）" onblur="checkAge(this.value)">
															<span id="ageSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">性别</label>
													<div class="col-md-8">
														<input name="sex" id="sex" class="form-control"
															type="text" placeholder="请输入用户的性别（'男'或者'女'）" onblur="checkSex(this.value)">
															<span id="sexSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">部门</label>
													<div class="col-md-8">
														<input name="dept" id="dept" class="form-control"
															type="text" placeholder="请输入用户的部门（xxx部）" onblur="checkDept(this.value)">
															<span id="deptSpan" style="font-size: 12px"></span>
													</div>
												</div>
											</div>
										</div>
											
											
											<div class="tile-footer">
												<div class="row">
													<div class="col-md-12 col-md-offset-6">
														<button class="btn btn-primary" type="button"
															id="submitBtn">
															<i class="fa fa-fw fa-lg fa-check-circle"></i>提交
														</button>
														<button class="btn btn-primary" type="button"
															onclick="javascript:window.location.href='userList.udo'"
															id="retBtn">
															<i class="glyphicon glyphicon-remove-circle"></i> 返回
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>

</body>
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script class="include" type="text/javascript"
	src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="assets/js/jquery.scrollTo.min.js"></script>
<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="assets/js/common-scripts.js"></script>
<script type="text/javascript" src="assets/js/jquery.gritter.js"></script>
<script type="text/javascript">
$(function() {
	//匿名函数   没有名字的方法
	$("#submitBtn").click(function() {
		var username = $("#username").val();
		var realName = $("#realName").val();
		var email = $("#email").val();
		var employeeId = $("#employeeId").val();
		var age = $("#age").val();
		var sex = $("#sex").val();
		var dept = $("#dept").val();
		checkUsername(username);
		checkRealname(realName);
		checkEmail(email);
		checkEmployeeId(employeeId);
		checkAge(age);
		checkSex(sex);
		checkDept(dept);
		if(username==null || username==""){
			return false;
		}		
		var usernameStr = $("#nameSpan").text();
		var realNameStr = $("#realNameSpan").text();
		var emailStr = $("#emailSpan").text();
		var employeeIdStr = $("#employeeIdSpan").text();
		var ageStr = $("#ageSpan").text();
		var sexStr = $("#sexSpan").text();
		var deptStr = $("#deptSpan").text();
		if(usernameStr!="" || realNameStr!="" || emailStr!="" || employeeIdStr!="" || ageStr!="" || sexStr!="" || deptStr!=""){
			alert("页面中输入有错误，请按照要求更改！");
			return false;
		}else{
			//提交表单
			$("#myform").submit();	
		}
	});
});
function checkUsername(username){
	//AJAX发送请求全靠浏览器内置的这个对象: XMLHttpRequest对象 。
    //使用这个XMLHttpRequest对象可以在浏览器当中单独启动一个新的浏览器线程，通过浏览器线程发送该请求。
    //达到异步效果。
    //1、创建AJAX核心对象XMLHttpRequest (浏览器内置的，可以直接使用)
    var xhr = new XMLHttpRequest();
    //2、注册回调函数
    /**
     * onreadystatechange存储函数（或函数名），每当 readyState 属性改变时，就会调用该函数
     * readyState：
     存有 XMLHttpRequest 的状态。从 0 到 4 发生变化。
     •0: 请求未初始化
     •1: 服务器连接已建立
     •2: 请求已接收
     •3: 请求处理中
     •4: 请求已完成，且响应已就绪
     */
    xhr.onreadystatechange = function () {//xhr的readyState发生改变的时候,回调一次
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var nameSpan = document.getElementById("nameSpan");
                nameSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    //3、开启浏览器和服务器之间的通道
    /**
     * open(method,url,async)
     规定请求的类型、URL 以及是否异步处理请求。
     •method：请求的类型；GET 或 POST
     •url：文件在服务器上的位置,相当于超链接<a href=""></a>
     •async：true（异步）或 false（同步）
     */
    xhr.open("GET", "ajaxusernameCheck.udo?username="+username, true);
    //4、发送AJAX请求
    xhr.send();
};
function checkRealname(realName){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var realNameSpan = document.getElementById("realNameSpan");
                realNameSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxrealNameCheck.udo?realName="+realName, true);
    xhr.send();
};
function checkEmail(email){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var emailSpan = document.getElementById("emailSpan");
                emailSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxemailCheck.udo?email="+email, true);
    xhr.send();
};
function checkEmployeeId(employeeId){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var employeeIdSpan = document.getElementById("employeeIdSpan");
                employeeIdSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxemployeeIdCheck.udo?employeeId="+employeeId, true);
    xhr.send();
};
function checkAge(age){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var ageSpan = document.getElementById("ageSpan");
                ageSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxageCheck.udo?age="+age, true);
    xhr.send();
};
function checkSex(sex){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var sexSpan = document.getElementById("sexSpan");
                sexSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxsexCheck.udo?sex="+sex, true);
    xhr.send();
};
function checkDept(dept){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var deptSpan = document.getElementById("deptSpan");
                deptSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxdeptCheck.udo?dept="+dept, true);
    xhr.send();
};
</script>
</html>