<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户信息</title>

<link rel="stylesheet" type="text/css"
	href="assets/css/font-awesome.min.css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.gritter.css" />
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

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
									<h3>用户信息修改</h3>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item active"><a href="#">用户信息修改</a></li>
								</ul>
							</div>
							<div class="col-md-6 col-sm-offset-3">
								<form action="updateUser.udo" method="post" id="myform">
									<div class="tile">
										<h3 class="tile-title" align="center">用户信息修改</h3>
										<div class="tile-body">
											<div class="form-horizontal">
												<div class="form-group row">
													<label class="control-label col-md-3">用户名</label> <input
														type="hidden" name="id" id="id" value=${userInfo.id }>
													<div class="col-md-8">
														<input name="username" id="username" class="form-control"
															value=${userInfo.username } type="text"
															readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">真实姓名</label>
													<div class="col-md-8">
														<input name="realname" id="realname" class="form-control"
															value=${userInfo.realname } type="text" onblur="checkRealname(this.value)">
															<span id="realNameSpan" style="font-size: 12px"></span>	
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">电子邮箱</label>
													<div class="col-md-8">
														<input name="email" id="email" class="form-control"
															type="text" value=${userInfo.email } readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">职工编号</label>
													<div class="col-md-8">
														<input name="employeeId" id="employeeId" class="form-control"
															type="text" value=${userInfo.employeeId } onblur="checkEmployeeId(this.value)">
															<span id="employeeIdSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">年龄</label>
													<div class="col-md-8">
														<input name="age" id="age" class="form-control"
															type="text" value=${userInfo.age } onblur="checkAge(this.value)">
															<span id="ageSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">性别</label>
													<div class="col-md-8">
														<input name="sex" id="sex" class="form-control"
															type="text" value=${userInfo.sex } onblur="checkSex(this.value)">
															<span id="sexSpan" style="font-size: 12px"></span>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">部门</label>
													<div class="col-md-8">
														<input name="dept" id="dept" class="form-control"
															type="text" value=${userInfo.dept } onblur="checkDept(this.value)">
															<span id="deptSpan" style="font-size: 12px"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="tile-footer">
											<div class="row">
												<div class="col-md-8 col-md-offset-4">
													<button class="btn btn-primary" type="button"
														id="submitBtn">确认修改</button>
													<button class="btn btn-primary" type="button"
														onclick="javascript:window.location.href='indexServlet'"
														id="retBtn">返回主页</button>
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
	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/common-scripts.js"></script>
	<script type="text/javascript" src="assets/js/jquery.gritter.js"></script>
</body>
<script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(function() {
	//匿名函数   没有名字的方法
	$("#submitBtn").click(function() {
		var realName = $("#realName").val();
		var employeeId = $("#employeeId").val();
		var age = $("#age").val();
		var sex = $("#sex").val();
		var dept = $("#dept").val();
		checkRealname(realName);
		checkEmployeeId(employeeId);
		checkAge(age);
		checkSex(sex);
		checkDept(dept);
		if(age == 0){
			return false;
		}
		var realNameStr = $("#realNameSpan").text();
		var employeeIdStr = $("#employeeIdSpan").text();
		var ageStr = $("#ageSpan").text();
		var sexStr = $("#sexSpan").text();
		var deptStr = $("#deptSpan").text();
		
		if(realNameStr!="" || employeeIdStr!="" || ageStr!="" || sexStr!="" || deptStr!=""){
			alert("页面中输入有错误，请按照要求更改！");
			return false;
		}
		//提交表单
		$("#myform").submit();
	});
});
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