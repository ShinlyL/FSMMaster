<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>企业申领系统</title>
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.gritter.css" />
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/style-responsive.css" rel="stylesheet" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<style type="text/css">
	.headslxt{
		position:absolute;
		right:10px;
		top:20px;
	}
	.headslxtdropdown{
		top:50px;
	}
</style>
</head>
<body>
	<section id="container">
		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="Toggle Navigation"></div>
			</div>
			<!--logo start-->
			<a href="indexServlet" class="logo"><b><span>企业</span>申领系统</b></a>
			<!--logo end-->
			<ul class="logo">
				<li class="dropdown text-right center">
				<a class="app-nav__item" href="javascript:;" data-toggle="dropdown" aria-label="Open Profile Menu">
				<i class="fa fa-user fa-lg headslxt">${userInfo.username }</i></a>
          		<ul class="dropdown-menu settings-menu dropdown-menu-right headslxtdropdown">
            		<li><a class="dropdown-item" href="javascript:changeMenu('userInfo.udo');"><i class="fa fa-user fa-lg"></i> 个人信息</a></li>
            		<li><a class="dropdown-item" href="javascript:changepw();"><i class="fa fa-cog fa-lg"></i>修改密码</a></li>
            		<li><a class="dropdown-item" href="outLogin.udo"><i class="fa fa-sign-out fa-lg"></i>退出登录</a></li>
          		</ul>
        	</li>
			</ul>
		</header>
		<!--header end-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<ul class="sidebar-menu" id="nav-accordion">
					<p class="centered">
						<a href="javascript:changeMenu('userInfo.udo');"></a>
					</p>
					<h5 class="centered">欢迎您，${userInfo.username }</h5>
					<li class="mt"><a class="active" href="indexServlet"> <i
							class="fa fa-dashboard"></i> <span>首页</span>
					</a></li>
					<li class="sub-menu"><a
						href="javascript:changeMenu('unApplyListByRln.ado?tag=1');"> <i
							class="fa fa-th"></i> <span>申领管理</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:changeMenu('inventoryList.do');"> <i
							class="fa fa-book"></i> <span>库存管理</span>
						</a>
					</li>
					<li class="sub-menu"><a href="#');"> <i
							class="fa fa-tasks"></i> <span>申领记录</span>
						</a>
						<ul class="sub">
							<li><a href="javascript:changeMenu('unApplyListByRln.ado?tag=2');">已审核管理</a></li>
							<li><a href="javascript:changeMenu('unApplyListByRln.ado?tag=3');">未通过管理</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</aside>
		<div class="app-content">
			<!-- 引入页面  src属性中写的是需要引入的页面的访问路径 -->
			<iframe id="main" name="ifd" src="toIndexView.do"
				onload="this.height=this.contentWindow.document.body.scrollHeight"
				width="100%" scrolling="no" frameborder="0"></iframe>
		</div>
		<footer class="site-footer">
			<div class="text-center">
				<p>
					&copy; Copyrights <strong>StarsFire</strong>. All Rights Reserved
				</p>
				<div class="credits">
					More Templates <a href="#" target="_blank" title="企业申领系统">企业申领系统</a>
					- Collect from <a href="#" title="星星之火" target="_blank">星星之火</a>
				</div>
				<a href="index.html#" class="go-top"> <i class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
	</section>
	<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				
				<div class="row">
				<div class="modal-content col-md-8">
				
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">用户密码修改</h4>
					</div>
					<div class="modal-body">
						<label class="control-label col-md-3">用户名</label>
						<div class="col-md-8">
							<input name="username" id="username" class="form-control"
								value=${userInfo.username } type="text" readonly="readonly">
						</div>
						
						<label class="control-label col-md-3">旧密码</label>
						<div class="col-md-8">
							<input name="oldpw" id="oldpw" class="form-control" value=""
								type="text" placeholder="请输入旧密码">
						</div>
						
						<label class="control-label col-md-3">新密码</label>
						<div class="col-md-8">
							<input name="newpw" id="newpw" class="form-control" value=""
								type="text" placeholder="请输入新密码">
						</div>
					</div>
					<div class="modal-footer ol-md-12 col-md-offset-3 ">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updatepw();">提交更改</button>
					</div>
					</div>
				</div>
			</div>
		</div>
	
	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/common-scripts.js"></script>
	<script type="text/javascript" src="assets/js/jquery.gritter.js"></script>
	
	<script>
    	function changeMenu(menuName){
    		/* 获取 iframe  dom对象        修改src属性 */
    		document.getElementById("main").src=menuName;
    	};
    function changepw(){
		$('#myModal').modal();
		$('#myModal').on('show.bs.modal', function () {
		  var $this = $(this);
		  var $modal_dialog = $this.find('.modal-dialog');
		  $modal_dialog.css({'margin-top':Math.max(0, ($(window.parent).height() - $modal_dialog.height()) /2) - 50 });
		});
		};
		function updatepw(){
			var username = $("#username").val();
			var oldpw = $("#oldpw").val();
			var newpw = $("#newpw").val();
			if(oldpw == null || oldpw != '${userInfo.password}'){
				alert("原密码输入有误");
				//鼠标聚焦到指定的输入框中
				$("#oldpw").focus();
				return false;
			}else if(newpw == null || newpw==""){
				alert("新密码不能为空");
				//鼠标聚焦到指定的输入框中
				$("#newpw").focus();
				return false;
			};
			$.ajax({
				type: "post",//方法类型
			    url: "updatePassword.udo" ,//url
			    dataType:"text",
			    async:true,
			    data: {"username":username,"password":newpw},
			    success: function (data) {
			    	if(data == "false") {
			    		alert("修改密码失败，请重试");
			    		window.location.href="error.jsp?id="+data;
			        }else{
			          	window.location.href="outLogin.udo";
			        }
			    },
			    error: function() {
			        alert("出错了，请联系管理员！");
			    }
			});
		};
	</script>
</body>
</html>