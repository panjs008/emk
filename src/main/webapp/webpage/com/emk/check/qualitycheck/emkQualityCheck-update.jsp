<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品大货质量检查表</title>
	<t:base type="jquery,tools"></t:base>

	<script>
		function save(){
			var baseformArray =  $("#tabfrm").contents().find("#base").contents().find("#formobj").serializeArray();
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#detail").contents().find("#imageFrm").serializeArray());

			var sizeCheck = $("#tabfrm").contents().find("#sizeCheckFrm").serializeArray();
			$.ajax({
				url : "emkQualityCheckController.do?doUpdate",
				type : 'post',
				cache : false,
				data: baseformArray,
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						$.ajax({
							url: "emkSizeCheckController.do?doUpdate&qualityCheckId=${emkQualityCheckPage.id }",
							type: 'post',
							cache: false,
							data: sizeCheck,
							success: function (data) {
								var d1 = $.parseJSON(data);
								if (d1.success) {
									tip(d1.msg);
									W.document.location.reload(true);
								}
							}
						});
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
<div style="padding: 3px; height: 35px;width:auto;border-color: #ddd" class="datagrid-toolbar" align="left">
	<input class="btn" type="button" value="保存" onclick="save()" style="background:#18a689 none repeat scroll 0 0;height:30px;width:90px !important;border-radius:5px;color: #fff;" onclick="save();">
	<input class="btn" type="button" value="提交" style="background:#18a689 none repeat scroll 0 0;height:30px;width:90px !important;border-radius:5px;color: #fff;" onclick="save();">
</div>
<iframe id="tabfrm" src="emkQualityCheckController.do?goTab&hVal2=${param.hVal2}&id=${emkQualityCheckPage.id }" width="100%"  height="${param.hVal}"
		style="border: 0px; line-height: 21px; background: #fff;overflow-x: hidden ; " frameborder="no" border="0" scrolling="no">
</iframe>
</body>
