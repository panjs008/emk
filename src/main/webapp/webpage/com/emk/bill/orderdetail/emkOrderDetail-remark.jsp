<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>备注</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

	<script type="text/javascript">

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doAdd" tiptype="1">
	<textarea id="remark" style="width:96%;height:180px" class="inputxt" rows="9" name="remark">${param.remark}</textarea>

</t:formvalid>
</body>
<script src = "webpage/com/service/custom/ymkCustom.js"></script>
