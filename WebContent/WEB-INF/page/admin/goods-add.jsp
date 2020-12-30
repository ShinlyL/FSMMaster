<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>库存管理</title>
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
									<h3>添加物品</h3>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item"><i class="fa fa-th"></i></li>
									<li class="breadcrumb-item active"><a href="#">添加物品信息</a></li>
								</ul>
							</div>
							<div class="col-md-6 col-sm-offset-3">
								<form action="goodsAdd.gdo" method="post" id="myform">
									<div class="tile">
										<div class="row">
											<div class="col-md-12 col-md-offset-6">
												<h3 class="tile-title">添加物品</h3>
											</div>
											<div class="tile-body">
												<div class="form-horizontal">
													<div class="form-group row">
														<label class="control-label col-md-4">物品名称</label>
														<div class="col-md-8">
															<div class="btn-group dropdown" id="dropdown">
																<button type="button"
																	class="btn btn-default dropdown-toggle"
																	data-toggle="dropdown" aria-haspopup="true"
																	aria-expanded="false">
																	选择物品 <span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<c:forEach items="${goodsName}" var="goodsname" varStatus="status">
																		<li><a href="toAddGoods.gdo?goodsname=${goodsname }">${goodsname }</a></li>
																	</c:forEach>
																</ul>
															</div>
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">条形码</label>
														<div class="col-md-8">
															<input name="barcode" id="barcode" class="form-control"
																type="text" placeholder="请输入物品条形码">
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">价格</label>
														<div class="col-md-8">
															<input name="purchaseprice" id="c" class="form-control"
																type="text" placeholder="请输入物品价格">
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">数量</label>
														<div class="col-md-8">
															<input name="num" id="num" class="form-control"
																type="text" placeholder="请输入物品数量">
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">详细信息</label>
														<div class="col-md-8">
															<input name="info" id="info" class="form-control"
																type="text" placeholder="请输入物品详细信息">
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
															onclick="javascript:window.location.href='goodsList.gdo'"
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
			alert("请在物品名称中选择要添加的物品！");
			return false;
		});
	})
</script>
</html>