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
									<h2>申领管理列表</h2>
								</div>
								<ul class="app-breadcrumb breadcrumb side">
									<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
									<c:if test="${tag==1 }">
										<li class="breadcrumb-item">待审核申领管理</li>
									</c:if>
									<c:if test="${tag==2 }">
										<li class="breadcrumb-item">已审批申领记录</li>
									</c:if>
									<c:if test="${tag==3 }">
										<li class="breadcrumb-item">驳回申领记录管理</li>
									</c:if>
								</ul>
							</div>
							<div class="row">
								<div class="row">
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
															<td>总价格</td>
															<td>申领人</td>
															<td>登记日期</td>
															<td>审核状态</td>
															<td>用户操作</td>
														</tr>
													</thead>
													<tbody>
														<!-- el表达式-->
														<!-- varStatus  var的下标序号 -->
														<c:forEach items="${page.data}" var="apply"
															varStatus="status">
															<tr>
																<td>${apply.name}</td>
																<td>${apply.barcode}</td>
																<td>${apply.purchaseprice}</td>
																<td>${apply.num}</td>
																<td>${apply.sums }</td>
																<td>${apply.manager}</td>
																<td>${apply.releaseDate}</td>
																<td>${apply.flag }</td>
																<td>
																<c:if test="${apply.flag=='未审核' }">
																	<button class="btn btn-success" type="button"
																		onclick="window.location.href='toUpdateApply.ado?id='+${apply.id}">修改</button>
																	<button class="btn btn-danger" type="button"
																		onclick="del(${apply.id})">删除</button>
																</c:if>
																<c:if test="${apply.flag=='已审核' }">
																	<button class="btn btn-success" type="button" >审核通过</button>
																</c:if>
																<c:if test="${apply.flag=='不通过' }">
																	<button class="btn btn-success" type="button"
																		onclick="window.location.href='toUpdateApply.ado?id='+${apply.id}">修改</button>
																	<button class="btn btn-danger" type="button"
																		onclick="del(${apply.id})">删除</button>
																</c:if>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<!-- 生成分页工具栏 -->
												<div class="col-md-8 col-md-offset-4">
													<c:if test="${status==1 }">
														<p:page action="unApplyListByRln.ado?tag=1" />
													</c:if>
													<c:if test="${status==2 }">
														<p:page action="unApplyListByRln.ado?tag=2" />
													</c:if>
													<c:if test="${status==3 }">
														<p:page action="unApplyListByRln.ado?tag=3" />
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
		            url: "delApply.ado",    //url
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
		 };
	function addApply(){
		window.location.href="toAddApplyUser.ado";
	}
	</script>
</body>
</html>