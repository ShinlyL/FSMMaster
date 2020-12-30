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
									<h2>入库管理列表</h2>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<li class="breadcrumb-item">入库通知管理</li>
								</ul>
							</div>
							<div class="row">
								<div class="row">
									<div class="col-md-8"></div>
									<div class="col-md-3">
										<div class="input-group">
											<input type="text" class="form-control"
												onkeydown="onKeyDown(event)" placeholder="输入已入库或未入库查找"
												id="searchFlag" /> <span
												class="input-group-addon btn btn-primary" onclick="search()">搜索</span>
										</div>
									</div>
									<div class="col-md-1">
										<button class="btn btn-primary" type="button"
											onclick="addInside()">添加记录</button>
									</div>

									<div class="col-md-12">
										<div class="tile">
											<div class="tile-body">
												<table class="table table-hover table-bordered"
													id="sampleTable">
													<thead>
														<tr>
															<td>物品名称</td>
															<td>条形码</td>
															<td>价格</td>
															<td>数量</td>
															<td>总和价格</td>
															<td>录入人</td>
															<td>录入日期</td>
															<td>用户操作</td>
														</tr>
													</thead>
													<tbody>
														<!-- el表达式-->
														<!-- varStatus  var的下标序号 -->
														<c:forEach items="${page.data}" var="inside"
															varStatus="status">
															<tr>
																<td>${inside.name}</td>
																<td>${inside.barcode}</td>
																<td>${inside.purchaseprice}</td>
																<td>${inside.num}</td>
																<td>${inside.sums}</td>
																<td>${inside.manager}</td>
																<td>${inside.releaseDate}</td>
																<td><c:if test="${inside.status==0 }">
																		<button class="btn btn-success" type="button"
																			onclick="window.location.href='updateInside.ido?id='+${inside.id}">修改</button>
																		<button class="btn btn-danger" type="button"
																			onclick="del(${inside.id})">删除</button>
																		<button class="btn btn-info" type="button"
																			onclick="window.location.href='toAddInventory.do?id='+${inside.id}">入库</button>
																	</c:if> <c:if test="${inside.status==1 }">
																		<button class="btn btn-info" type="button">已入库</button>
																	</c:if></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<div class="col-md-8 col-md-offset-4">
													<!-- 生成分页工具栏 -->
													<c:if test="${flagStatus=='已入库' }">
														<p:page action="searchInsideByFlag.ido?searchFlag=已入库" />
													</c:if>
													<c:if test="${flagStatus=='未入库' }">
														<p:page action="searchInsideByFlag.ido?searchFlag=未入库" />
													</c:if>
													<c:if test="${status=='0' || flagStatus=='0' }">
														<p:page action="insideList.ido" />
													</c:if>
												</div>
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
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/common-scripts.js"></script>
	<script type="text/javascript" src="assets/js/jquery.gritter.js"></script>
	<script type="text/javascript">
	function del(id){
		 if(confirm("确实要删除吗?")){
			 $.ajax({
		    		type: "post",              //方法类型
		            url: "delInside.ido",    //url
		            data: {id:id},
		            success: function (data) {
		                if (data=="no") {
		                    alert("删除失败，请稍后重试或者联系管理员！");
		                }else{
		                	alert("删除成功！");
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
		 };
	function addInside(){
		window.location.href="toAddInside.ido";
	};
	function search(){
		var searchFlag = $("#searchFlag").val();
		window.location.href="searchInsideByFlag.ido?searchFlag="+searchFlag;
	}
	</script>
</body>
</html>
