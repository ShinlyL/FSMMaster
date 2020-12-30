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
									<h3>添加入库记录</h3>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item"><i class="fa fa-th"></i></li>
									<li class="breadcrumb-item active"><a href="#">添加入库记录</a></li>
								</ul>
							</div>
							<div class="col-md-6 col-sm-offset-3">
								<form action="insideAdd.ido" method="post" id="myform">
									<div class="tile">
										<div class="row">
											<div class="col-md-12 col-md-offset-6">
												<h3 class="tile-title">添加入库记录</h3>
											</div>
											<div class="tile-body">
												<div class="form-horizontal">
													<div class="form-group row">
														<label class="control-label col-md-4">物品名称</label>
														<div class="col-md-8">
															<input name="name" id="name" class="form-control"
																type="text" placeholder="请输入物品名称" onblur="checkName(this.value)">
															<span id="nameSpan" style="font-size: 12px"></span>
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">条形码</label>
														<div class="col-md-8">
															<input name="barcode" id="barcode" class="form-control"
																type="text" placeholder="请输入物品条形码" onblur="checkBarcode(this.value)">
															<span id="barcodeSpan" style="font-size: 12px"></span>
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">价格</label>
														<div class="col-md-8">
															<input name="purchaseprice" id="purchaseprice" class="form-control"
																type="text" placeholder="请输入物品价格" onblur="checkPurchaseprice(this.value)">
															<span id="purchasepriceSpan" style="font-size: 12px"></span>
														</div>
													</div>
													<div class="form-group row">
														<label class="control-label col-md-4">数量</label>
														<div class="col-md-8">
															<input name="num" id="num" class="form-control"
																type="text" placeholder="请输入物品数量" onblur="checkNum(this.value)">
															<span id="numSpan" style="font-size: 12px"></span>
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
															onclick="javascript:window.location.href='insideList.ido'"
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
		var name = $("#name").val();
		var barcode = $("#barcode").val();
		var purchaseprice = $("#purchaseprice").val();
		var num = $("#num").val();
		checkName(name);
		checkBarcode(barcode);
		checkPurchaseprice(purchaseprice);
		checkNum(num);
		<%Thread.sleep(1000);%>
		if(name==null || name==""){
			$("#name").focus();
			return false;
		}		
		var nameStr = $("#nameSpan").text();
		var barcodeStr = $("#barcodeSpan").text();
		var purchasepriceStr = $("#purchasepriceSpan").text();
		var numStr = $("#numSpan").text();
		if(nameStr!="" || barcodeStr!="" || purchasepriceStr!="" || numStr!=""){
			alert("页面中输入有错误，请按照要求更改！");
			return false;
		}else{
			//提交表单
			$("#myform").submit();	
		}
	});
});
	function checkName(name){
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState == 4) {
	            if (xhr.status == 200) {
	                var nameSpan = document.getElementById("nameSpan");
	                nameSpan.innerHTML = xhr.responseText;

	            } else {
	                alert(xhr.status+"错误");
	            }
	        }
	    }
	    xhr.open("GET", "ajaxnameCheck.ido?name="+name, true);
	    xhr.send();
	};
	function checkBarcode(barcode){
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState == 4) {
	            if (xhr.status == 200) {
	                var barcodeSpan = document.getElementById("barcodeSpan");
	                barcodeSpan.innerHTML = xhr.responseText;

	            } else {
	                alert(xhr.status+"错误");
	            }
	        }
	    }
	    xhr.open("GET", "ajaxbarcodeCheck.ido?barcode="+barcode, true);
	    xhr.send();
	};
	function checkPurchaseprice(purchaseprice){
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState == 4) {
	            if (xhr.status == 200) {
	                var purchasepriceSpan = document.getElementById("purchasepriceSpan");
	                purchasepriceSpan.innerHTML = xhr.responseText;

	            } else {
	                alert(xhr.status+"错误");
	            }
	        }
	    }
	    xhr.open("GET", "ajaxpurchasepriceCheck.ido?purchaseprice="+purchaseprice, true);
	    xhr.send();
	};
	function checkNum(num){
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState == 4) {
	            if (xhr.status == 200) {
	                var numSpan = document.getElementById("numSpan");
	                numSpan.innerHTML = xhr.responseText;

	            } else {
	                alert(xhr.status+"错误");
	            }
	        }
	    }
	    xhr.open("GET", "ajaxnumCheck.ido?num="+num, true);
	    xhr.send();
	};
</script>
</html>