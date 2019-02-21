<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function(){
			$("#status").val("${ymkCustomPage.status}");
		});
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${ymkCustomPage.id }"/>
	<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户状态:
				</label>
			</td>
			<td class="value">
				<select id="status" name="status">
					<option value="0">激活</option>
					<option value="1">暂停</option>
					<option value="2">禁止</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户状态</label>
			</td>
		</tr>
	</table>
</t:formvalid>

</body>
<script src = "webpage/com/service/custom/ymkCustom.js"></script>
