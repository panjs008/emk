<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订单编辑</title>
	<t:base type="jquery,tools"></t:base>

	<script>
		function save(){
			var baseformArray =  $("#tabfrm").contents().find("#base").contents().find("#formobj").serializeArray();
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#ylfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#fzfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#bzfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#barCodeFrm0").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#barCodeFrm1").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#barCodeFrm2").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#boxFrm0").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#boxFrm1").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#boxFrm2").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#boxFrm3").serializeArray());

			$.ajax({
				url : "emkProOrderController.do?doUpdate",
				type : 'post',
				cache : false,
				data: baseformArray,
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						tip(msg);
						W.document.location.reload(true);
					}
				}
			});

		}
	</script>
	<style>
		.datagrid-toolbar {
			height: auto;
			padding: 2px 2px 2px 2px;
			border-width: 0 0 1px 0;
			border-style: solid;
		}
	</style>
</head>

<body>
<iframe id="tabfrm" src="emkProOrderController.do?goTab&hVal2=${param.hVal2}&id=${param.id }" width="100%"  height="${param.hVal}"
		style="border: 0px; line-height: 21px; background: #fff;overflow-x: hidden ;margin-top:-8px" frameborder="no" border="0" scrolling="no">
</iframe>

</body>
