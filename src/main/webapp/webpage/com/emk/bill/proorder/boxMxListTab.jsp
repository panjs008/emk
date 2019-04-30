<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>入箱方式</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

</head>

<body>
<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?boxMxList&type=0&proOrderId=${param.proOrderId}" icon="icon-search" title="单色单码装" id="box1"></t:tab>
</t:tabs>
<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?boxMxList&type=1&proOrderId=${param.proOrderId}" icon="icon-search" title="单色混码装" id="box2"></t:tab>
</t:tabs>
<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?boxMxList&type=2&proOrderId=${param.proOrderId}" icon="icon-search" title="混色单码装" id="box3"></t:tab>
</t:tabs>
<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
	<t:tab href="emkProOrderController.do?boxMxList&type=3&proOrderId=${param.proOrderId}" icon="icon-search" title="混色混码装" id="box4"></t:tab>
</t:tabs>

</body>
