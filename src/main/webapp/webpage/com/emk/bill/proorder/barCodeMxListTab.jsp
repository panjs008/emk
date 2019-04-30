<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>条码</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

</head>

<body>
<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?barCodeMxList&type=0&proOrderId=${param.proOrderId}" icon="icon-search" title="洗水标条码" id="barCode"></t:tab>
</t:tabs>
<t:tabs id="barCodeDetail2" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?barCodeMxList&type=1&proOrderId=${param.proOrderId}" icon="icon-search" title="胶袋贴条码" id="barCode1"></t:tab>
</t:tabs>
<t:tabs id="barCodeDetail3" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?barCodeMxList&type=2&proOrderId=${param.proOrderId}" icon="icon-search" title="箱贴条码" id="barCode2"></t:tab>
</t:tabs>

</body>
