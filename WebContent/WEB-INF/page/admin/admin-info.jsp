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
									<h3>管理员信息查看</h3>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item active"><a href="#">管理员信息查看</a></li>
								</ul>
							</div>
							<div class="col-md-6 col-sm-offset-3">
								<form action="updateUser.udo" method="post" id="myform">
									<div class="tile">
										<h3 class="tile-title" align="center">管理员信息查看</h3>
										<div class="tile-body">
											<div class="form-horizontal">
												<div class="form-group row">
													<label class="control-label col-md-3">用户名</label> <input
														type="hidden" name="id" id="id" value=${adminInfo.id }>
													<div class="col-md-8">
														<input name="username" id="username" class="form-control"
															value=${adminInfo.username } type="text"
															readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">电子邮箱</label>
													<div class="col-md-8">
														<input name="email" id="email" class="form-control"
															type="text" value=${adminInfo.email } readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-3">上一次登录时间</label>
													<div class="col-md-8">
														<input name="dept" id="dept" class="form-control"
															type="text" value=${adminInfo.loginTime } readonly="readonly">
													</div>
												</div>
											</div>
										</div>
										<div class="tile-footer">
											<div class="row">
												<div class="col-md-8 col-md-offset-4">
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
</html>