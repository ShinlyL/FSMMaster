<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- jstl库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/page-tags" prefix="p"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户管理</title>
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="assets/css/jquery.gritter.css" />
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
									<h2>用户管理列表</h2>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item">用户管理</li>
								</ul>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="tile">
										<div class="tile-body">
											<table class="table table-hover table-bordered"
												id="sampleTable">
												<thead>
													<tr>
														<td>用户名</td>
														<td>密码</td>
														<td>真实姓名</td>
														<td>邮箱</td>
														<td>职工编号</td>
														<td>年龄</td>
														<td>性别</td>
														<td>部门</td>
														<td>操作</td>
													</tr>
												</thead>
												<tbody>
													<!-- el表达式-->
													<!-- varStatus  var的下标序号 -->
													<c:forEach items="${page.data}" var="user"
														varStatus="status">
														<tr>
															<td>${user.username}</td>
															<td>${user.password}</td>
															<td>${user.realname}</td>
															<td>${user.email}</td>
															<td>${user.employeeId}</td>
															<td>${user.age}</td>
															<td>${user.sex}</td>
															<td>${user.dept}</td>
															<td>
																<button class="btn btn-success" type="button"
																	onclick="window.location.href='updateUserByadmin.udo?id='+${user.id}">修改</button>
																<button class="btn btn-danger" type="button"
																	onclick="del(${user.id})">删除</button>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<!-- 生成分页工具栏 -->
											<div class="col-md-8 col-md-offset-4">
												<p:page action="userList.udo" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/common-scripts.js"></script>
	<script type="text/javascript" src="assets/js/jquery.gritter.js"></script>
	<script type="text/javascript">
	function del(id){
		 if(confirm("确实要删除吗?")){
			 $.ajax({
		    		type: "post",              //方法类型
		            url: "delUser.udo",    //url
		            data: {id:id},
		            success: function (data) {
		                if (data=="no") {
		                    alert("删除失败，请稍后重试或者联系管理员！");
		                }else{
		                	alert("删除成功！")
		                	//刷新当前页面
		                	location.reload();
		                }
		            },
		            error : function() {
		                alert("异常请求！");
		            }
		    	}); 
			}else{
				return;
			}
		 }
	
	function addRepertroy(){
		window.location.href="toAddRepertory.do";
	}
	</script>
</body>
</html>