<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script>




	</script>
</head>

<body>
	<t:tabs id="priceDetail"  iframe="false" tabPosition="top" width="100%" heigth="${param.hVal}"  fit="true">
		<t:formvalid formid="formobj" dialog="true" layout="table" beforeSubmit="connactFrm()" action="emkPriceController.do?doSubmit"  tiptype="1" >
			<input name="id" value="${param.id}" type="hidden">

			<t:tab iframe="emkPriceController.do?goBase&id=${param.id}"  width="100%" heigth="${param.hVal2}"   icon="fa fa-calendar" title="基本信息" id="base"></t:tab>
			<t:tab href="emkPriceController.do?goPb&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="坯布" id="pbdetail"></t:tab>
			<t:tab href="emkPriceController.do?orderMxList&priceId=${param.id}"   width="100%" icon="fa fa-flask" title="原料面料" id="detail"></t:tab>
			<t:tab href="emkPriceController.do?orderMxList2&priceId=${param.id}"   width="100%" icon="fa fa-cut" title="缝制辅料" id="fengdetail"></t:tab>
			<t:tab href="emkPriceController.do?orderMxList3&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="包装辅料" id="bzdetail"></t:tab>
			<t:tab href="emkPriceController.do?gxList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="人工" id="rgdetail"></t:tab>
			<t:tab href="emkPriceController.do?ranList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="染色" id="randetail"></t:tab>
			<t:tab href="emkPriceController.do?yinList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="印花" id="yindetail"></t:tab>
			<t:tab href="emkPriceController.do?zjList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="助剂" id="zjdetail"></t:tab>
			<t:tab href="emkPriceController.do?goManage&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="管理" id="gldetail"></t:tab>
			<t:tab href="emkPriceController.do?goPrice&id=${param.id}"   width="100%" icon="fa fa-cube" title="报价" id="gldetail"></t:tab>
		</t:formvalid>

	</t:tabs>


</body>
