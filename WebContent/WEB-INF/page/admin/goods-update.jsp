<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>库存信息修改</title>
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.gritter.css" />
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/style-responsive.css" rel="stylesheet" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<style>
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
									<h3>物品信息修改</h3>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item"><i class="fa fa-th"></i></li>
									<li class="breadcrumb-item active"><a href="#">物品信息修改</a></li>
								</ul>
							</div>
							<div class="col-md-6 col-sm-offset-3">
								<form action="updateById.gdo" method="post" id="myform">
									<div class="tile">
									<div class="row">
												<div class="col-md-12 col-md-offset-6">
										<h3 class="tile-title">修改物品</h3>
										</div>
										</div>
										<div class="tile-body">
											<div class="form-horizontal">
												<div class="form-group row">
													<label class="control-label col-md-4">物品名称</label> <input
														type="hidden" name="id" id="id" value=${goods.id }>
													<div class="col-md-8">
														<input name="name" id="name" class="form-control"
															value=${goods.name } type="text" readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">条形码</label>
													<div class="col-md-8">
														<input name="barcode" id="barcode" class="form-control"
															value=${goods.barcode } type="text"
															readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">价格</label>
													<div class="col-md-8">
														<input name="purchaseprice" id="purchaseprice" class="form-control"
															type="text" value=${goods.purchaseprice } readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">类型一</label>
													<div class="col-md-8">
														<input name="typeone" id="typeone" class="form-control"
															type="text" value=${goods.typeone }
															readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">类型二</label>
													<div class="col-md-8">
														<input name="typetwo" id="typetwo" class="form-control"
															type="text" value=${goods.typetwo }
															readonly="readonly">
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-md-4">详细信息</label>
													<div class="col-md-8">
														<input name="info" id="info" class="form-control"
															type="text" value=${goods.info }
															placeholder="请输入物品详细信息" onblur="checkInfo(this.value)">
															<span id="infoSpan" style="font-size: 12px"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="tile-footer">
											<div class="row">
												<div class="col-md-12 col-md-offset-6">
													<button class="btn btn-primary" type="button"
														id="submitBtn">
														<i class="fa fa-fw fa-lg fa-check-circle"></i>保存
													</button>
													<button class="btn btn-primary" type="button"
														onclick="javascript:window.location.href='goodsList.gdo'"
														id="retBtn">
														<i class="glyphicon glyphicon-remove-circle"></i>
														返回</button>
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
	<script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/common-scripts.js"></script>
	<script type="text/javascript" src="assets/js/jquery.gritter.js"></script>
<script type="text/javascript">
$(function() {
	//匿名函数   没有名字的方法
	$("#submitBtn").click(function() {
		//验证表单项是否为空
		var info = $("#info").val();
		var infoStr = $("#infoSpan").text();
		if(infoStr!=""){
			alert("页面中输入有错误，请按照要求更改！");
			return false;
		}
		if(info == null || info == ""){
			alert("物品的详细信息不能为空");
			$("#info").focus();
			return false;
		}
		//提交表单
		$("#myform").submit();
	});
});
function checkInfo(info){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var infoSpan = document.getElementById("infoSpan");
                infoSpan.innerHTML = xhr.responseText;

            } else {
                alert(xhr.status+"错误");
            }
        }
    }
    xhr.open("GET", "ajaxinfoCheck.gdo?info="+info, true);
    xhr.send();
};
</script>
</html>