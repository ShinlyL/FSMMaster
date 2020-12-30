<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- jstl库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>
<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<!--external css-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.gritter.css" />
<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<style>
body {
	padding: 50px;
}
</style>
</head>
<body>
	<!-- MAIN CONTENT -->
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<div class="row">
				<div class="col-lg-9 main-chart">
					<!-- /row -->
					<div class="row">
						<!-- DIRECT MESSAGE PANEL -->
						<div class="col-md-12 mb">
							<div class="message-p pn">
								<div class="message-header">
									<h4>企业申领系统</h4>
								</div>
								<div class="row">
									<div class="col-md-3 centered hidden-sm hidden-xs">
										<img src="assets/img/ui_top.jpg" class="img-rounded"
											width="240">
									</div>
									<div class="col-md-9">
										<p>
											<name>Dan Rogers</name>
											sent you a message.
										</p>
										<p class="small">3 hours ago</p>
										<p class="message">阿巴阿巴阿巴阿巴阿巴阿巴</p>
										<form class="form-inline" role="form">
											<div class="form-group">
												<input type="text" class="form-control"
													id="exampleInputText" placeholder="Reply Dan">
											</div>
											<button type="submit" class="btn btn-default">Send</button>
										</form>
									</div>
								</div>
							</div>
							<!-- /Message Panel-->
						</div>
						<!-- /col-md-12  -->
						<!-- DIRECT MESSAGE PANEL -->
						<div class="col-md-12 mb">
							<div class="message-p pn">
								<div class="message-header">
									<h4>申领留言板</h4>
								</div>
								<div class="row">
									<div class="col-md-3 centered hidden-sm hidden-xs">
										<img src="assets/img/ui-danro.jpg" class="img-circle"
											width="65">
									</div>
									<div class="col-md-9">
										<p>
											<name>Dan Rogers</name>
											sent you a message.
										</p>
										<p class="small">3 hours ago</p>
										<p class="message">阿巴阿巴阿巴阿巴阿巴阿巴</p>
										<form class="form-inline" role="form">
											<div class="form-group">
												<input type="text" class="form-control"
													id="exampleInputText" placeholder="Reply Dan">
											</div>
											<button type="submit" class="btn btn-default">Send</button>
										</form>
									</div>
								</div>
							</div>
							<!-- /Message Panel-->
						</div>
						<!-- /col-md-12  -->
					</div>
				</div>
				<!-- /col-lg-9 END SECTION MIDDLE -->
				<div class="col-lg-3 ds">
					<!--new earning end-->
					<!-- RECENT ACTIVITIES SECTION -->
					<h4 class="centered mt">最新入库记录</h4>
					<!-- First Activity -->
					<div class="desc">
						<div class="thumb">
							<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
						</div>
						<div class="details">
							<p>
								<muted>Just Now</muted>
								<br /> <a href="#">Paul Rudd</a> purchased an item.<br />
							</p>
						</div>
					</div>
					<!-- Second Activity -->
					<div class="desc">
						<div class="thumb">
							<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
						</div>
						<div class="details">
							<p>
								<muted>2 Minutes Ago</muted>
								<br /> <a href="#">James Brown</a> subscribed to your
								newsletter.<br />
							</p>
						</div>
					</div>
					<!-- Third Activity -->
					<div class="desc">
						<div class="thumb">
							<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
						</div>
						<div class="details">
							<p>
								<muted>3 Hours Ago</muted>
								<br /> <a href="#">Diana Kennedy</a> purchased a year
								subscription.<br />
							</p>
						</div>
					</div>
					<!-- USERS ONLINE SECTION -->
					<h4 class="centered mt">最新申请记录</h4>
					<!-- First Member -->
					<div class="desc">
						<div class="thumb">
							<img class="img-circle" src="assets/img/ui-divya.jpg"
								width="35px" height="35px" align="">
						</div>
						<div class="details">
							<p>
								<a href="#">DIVYA MANIAN</a><br />
								<muted>Available</muted>
							</p>
						</div>
					</div>
					<!-- Second Member -->
					<div class="desc">
						<div class="thumb">
							<img class="img-circle" src="assets/img/ui-sherman.jpg"
								width="35px" height="35px" align="">
						</div>
						<div class="details">
							<p>
								<a href="#">DJ SHERMAN</a><br />
								<muted>I am Busy</muted>
							</p>
						</div>
					</div>
					<!-- Third Member -->
					<div class="desc">
						<div class="thumb">
							<img class="img-circle" src="assets/img/ui-danro.jpg"
								width="35px" height="35px" align="" />
						</div>
						<div class="details">
							<p>
								<a href="#">DAN ROGERS</a><br />
								<muted>Available</muted>
							</p>
						</div>
					</div>
					<!-- Fourth Member -->
				</div>
				<!-- /col-lg-3 -->
			</div>
			<!-- /row -->
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
</html>