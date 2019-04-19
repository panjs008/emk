<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,tools"></t:base>

	<script>
		function save(){
			var baseformArray =  $("#tabfrm").contents().find("#base").contents().find("#formobj").serializeArray();
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#formpb").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#ylfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#fzfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#bzfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#gxfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#ranfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#zjfrm").serializeArray());

			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#yinfrm").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#formmange").serializeArray());
			baseformArray = baseformArray.concat($("#tabfrm").contents().find("#formprice").serializeArray());
			/*if($("#orderFrm").contents().find("#orderNum").val()==""){
			 tip("采购合同号必填");
			 return false;
			 }
			 if($("#orderFrm").contents().find("#htbz").val()==""){
			 tip("合同币种必填");
			 return false;
			 }

			 var indexFlag0 =  $("#orderFrm").contents().find("iframe").contents().find("#indexFlag0").val();
			 var result0={};
			 for(var i=0;i<=indexFlag0;i++){
			 result0["hpreNext"+i] = $("#orderFrm").contents().find("iframe").contents().find("#hpreNext"+i).val();
			 result0["gatherType"+i] = $("#orderFrm").contents().find("iframe").contents().find("#gatherType"+i).val();
			 result0["dateType"+i] = $("#orderFrm").contents().find("iframe").contents().find("#dateType"+i).val();
			 }*/

			$.ajax({
				//url : "emkPriceController.do?doAdd&dataSize="+indexFlag0+"&gather="+JSON.stringify(result0),
				url : "emkPriceController.do?doUpdate",
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
<input id="id" name="id" type="hidden" value="${param.id }"/>
<%--<div style="padding: 3px; height: 35px;width:auto;border-color: #ddd" class="datagrid-toolbar" align="left">
	<input class="btn" type="button" value="保存" onclick="save()" style="background:#18a689 none repeat scroll 0 0;height:30px;width:90px !important;border-radius:5px;color: #fff;" onclick="save();">
	<input class="btn" type="button" value="提交" style="background:#18a689 none repeat scroll 0 0;height:30px;width:90px !important;border-radius:5px;color: #fff;" onclick="save();">
</div>--%>
<form>
	<iframe id="tabfrm" src="emkPriceController.do?goTab&hVal2=${param.hVal2}&id=${param.id }" width="100%"  height="${param.hVal}"
			style="border: 0px; line-height: 21px; background: #fff;overflow-x: hidden ;margin-top:${ROLE.rolecode eq 'cw' ? '':'-8px'} ; " frameborder="no" border="0" scrolling="no">
	</iframe>
</form>

</body>
