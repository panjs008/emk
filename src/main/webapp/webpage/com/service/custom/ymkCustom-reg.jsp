<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>注册用户</title>
	<link rel="stylesheet" href="login/css/bootstrap.min.css">
	<link rel="stylesheet" href="login/css/animate.css">
	<link rel="stylesheet" href="login/css/style.css">
	<!-- Modernizr JS -->
	<script src="login/js/modernizr-2.6.2.min.js"></script>
	<script src="login/js/respond.min.js"></script>
	<script src="login/js/jquery.min.js"></script>
	<script src="login/js/bootstrap.min.js"></script>
	<script src="login/js/jquery.placeholder.min.js"></script>
	<script src="login/js/jquery.waypoints.min.js"></script>
	<script src="login/js/main.js"></script>

	<script>
		function save(){
			var formArray =  $("#formobj").serializeArray();
			var item = $(":radio:checked");
			var len = item.length;
			if(len==0){
				//alert("yes--选中的值为:"+$(":radio:checked").val());
				showErrorMsg("服务商类型必填");
				return false;
			}
			if($('#radiobutton1').is(':checked')){
				showErrorMsg("企业名称必填");
				return false;
			}
			if($("#cusName").val()==""){
				showErrorMsg("企业名称必填");
				return false;
			}
			if($("#telphone").val()==""){
				showErrorMsg("手机号码必填");
				return false;
			}
			var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
			if (!myreg.test($("#telphone").val())) {
				showErrorMsg("手机号码格式不正确");
				return false;
			}
			if($("#userName").val()==""){
				showErrorMsg("用户账号必填");
				return false;
			}
			if($("#password").val()==""){
				showErrorMsg("密码必填");
				return false;
			}
			if($("#twopassword").val()==""){
				showErrorMsg("重复密码必填");
				return false;
			}
			if(!$("#password").val()==$("#twopassword").val()){
				showErrorMsg("两次输入的密码不一致");
				return false;
			}

			$.ajax({
				url : "ymkCustomController.do?doAdd",
				type : 'post',
				cache : false,
				data: formArray,
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						window.location.href='${webRoot}/loginController.do?login';
					}
				}
			});

		}
	</script>
</head>
<body>
<div class="row">
	<div class="col-md-4 col-md-offset-4">
		<!-- Start Sign In Form -->
		<form id="formobj" name="formobj" action="#" class="fh5co-form animate-box" data-animate-effect="fadeIn">
			<h2>注册用户</h2>
			<div id="errMsgContiner" class="form-group">
				<div class="alert alert-success" role="alert"> <div id="showErrMsg"></div></div>
			</div>
			<div class="form-group">
				<input name="cusType" type="radio"  value="2">
				业主&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="cusType" type="radio"  value="0">
				设备中标商&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="cusType" type="radio"  value="1">
				本地服务商
			</div>
			<div class="form-group">
				<label for="cusName" class="sr-only">企业名称</label>
				<input type="text" class="form-control" id="cusName" name="cusName" placeholder="企业名称" autocomplete="off">
			</div>
			<div class="form-group">
				<label for="telphone" class="sr-only">手机号码</label>
				<input type="text" class="form-control" id="telphone" name="telphone" placeholder="手机号码" autocomplete="off">
			</div>
			<div class="form-group">
				<label for="userName" class="sr-only">用户账号</label>
				<input type="text" class="form-control" id="userName" name="userName" placeholder="用户账号" autocomplete="off">
			</div>
			<div class="form-group">
				<label for="password" class="sr-only">密码</label>
				<input type="password" class="form-control" id="password" name="password" name="password" placeholder="密码" autocomplete="off">
			</div>
			<div class="form-group">
				<label for="twopassword" class="sr-only">重复密码</label>
				<input type="password" class="form-control" id="twopassword" name="twopassword" name="twopassword" placeholder="重复密码" autocomplete="off">
			</div>
			<div class="form-group">
				<input type="button" value="注册" onclick="save()" class="btn btn-primary">&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="home();" value="返回" class="btn btn-primary">
			</div>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		optErrMsg();
	});
	$("#errMsgContiner").hide();
	function optErrMsg(){
		$("#showErrMsg").html('');
		$("#errMsgContiner").hide();
	}
	//登录提示消息显示
	function showErrorMsg(msg){
		$("#errMsgContiner").show();
		$("#showErrMsg").html(msg);
		window.setTimeout(optErrMsg,3000);
	}
	function home(){
		window.location.href = "loginController.do?login";
	}
</script>
<script src = "webpage/com/service/custom/ymkCustom.js"></script>
