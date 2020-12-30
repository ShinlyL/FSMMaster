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
							<div class="message-p pn" style="background: #fcf8e3">
								<div class="message-header" style="background: #4ecdc4;">
									<h4>企业申领系统</h4>
								</div>
								<div class="row">
									<div class="col-md-3 centered hidden-sm hidden-xs">
										<img src="assets/img/ui_top.jpg" class="img-rounded"
											width="240">
									</div>
									<div class="col-md-9">
										<p>
											The <name>StarsFire</name> team recommends you 
										</p>
										<p class="message">计算机不断发展的今天，<br>
										越来越多的企业选择将管理进行自动化来节约人力资源成本以及纸质申请表格申领时产生的错误率。<br>
										办公用品申领系统可以对办公用品的信息进行管理，<br>
										后台人员可以将办公用品的分发、管理、统计等工作交由系统完成。</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 mb">
							<div class="message-p" style="background: #fcf8e3">
								<div class="message-header" style="background: #4ecdc4;">
									<h4>系统使用注意事项</h4>
								</div>
								<div class="row">
								
									<div class="col-md-12 mb table-responsive">
										<c:if test="${status==0 }">
											<p>
												你好,您当前的权限是
												<name>普通用户</name>
											</p>
											<table class="table table-condensed">
												<thead>
													<tr>
														<td>功能模块</td>
														<td>可执行的操作</td>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>申领管理</td>
														<td>可以查看你所申领的未审核的记录，你可以在这修改申领数量和删除记录。</td>
													</tr>
													<tr>
														<td>用户信息</td>
														<td>查看和修改当前用户的信息。</td>
													</tr>
													<tr>
														<td>库存管理</td>
														<td>查看库存中的物品信息，你可以直接在这选择物品申领。</td>
													</tr>
													<tr>
														<td>申领记录</td>
														<td>查看你的所有已审核的申领记录，你可以凭借此记录尽快去领取物品。</td>
													</tr>
												</tbody>
											</table>
										</c:if>
										<c:if test="${status==1 }">
											<p>
												你好,您当前的权限是
												<name>管理员</name>
											</p>
											<table class="table table-condensed">
												<thead>
													<tr>
														<td>功能模块</td>
														<td>可执行的操作</td>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>人员管理</td>
														<td>可以查看所有已注册本系统的用户，你可以在这修改用户的基本信息。</td>
													</tr>
													<tr>
														<td>物品管理</td>
														<td>查看物品的存货状态，为物品补充使用信息，可以从库存中添加物品。</td>
													</tr>
													<tr>
														<td>库存管理</td>
														<td>查看库存物品信息，你可以修改物品的分类用途，或者删除此物品，注意这是用户申领的物品。</td>
													</tr>
													<tr>
														<td>入库管理</td>
														<td>查看你的所有已入库的物品记录，你可以添加新的记录、删除有误的记录或者将物品选择入库。</td>
													</tr>
													<tr>
														<td>审核申领管理</td>
														<td>你可以选择查看所有用户的未审核记录，进行审核批准，或者查看所有审核通过的记录。</td>
													</tr>
												</tbody>
											</table>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 ds" style="background: #fcf8e3;">
					<h2 class="centered mt" style="margin-top:5px;color:#4ecdc4;">最新入库记录</h2>
					<c:forEach items="${iList.data}" var="inventory" varStatus="status">
							<div class="desc">
								<div class="thumb">
									<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
								</div>
								<div class="details">
									<p>
										<muted>最新入库物品</muted>
										<br />刚入库了<a href="#">${inventory.name }</a>&nbsp;数量：${inventory.num }<br />
									</p>
								</div>
							</div>
					</c:forEach>
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
</html>