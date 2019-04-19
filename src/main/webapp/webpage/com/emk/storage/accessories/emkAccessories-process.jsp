<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>缝制辅料入库申请单</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script>


    </script>

</head>
<body>
<t:tabs id="materailTabId" iframe="false" heigth="600px" tabPosition="top" fit="true" >
    <t:tab title="任务处理" id="orderFrm"  heigth="600px"  width="100%" href="emkAccessoriesController.do?goWork&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
    <c:choose>
        <c:when test="${param.node eq 'rksqTask' || param.node eq 'cgjlshTask'}">
            <t:tab title="缝制辅料入库申请单" id="accessoriesFrm"  heigth="600px"  width="100%" iframe="emkMInStorageController.do?goUpdate&type=1&materialNo=${param.id}" icon="fa fa-calendar"></t:tab>
        </c:when>
        <c:otherwise>
            <t:tab title="基本信息" id="baseFrm"  heigth="600px"  width="100%" iframe="emkAccessoriesController.do?goUpdate2&id=${param.id}" icon="fa fa-calendar"></t:tab>
        </c:otherwise>
    </c:choose>
    <t:tab title="流程图" id="proFrm"  heigth="600px" width="100%" icon="fa fa-sitemap" iframe="flowController.do?process&processUrl=com/emk/storage/material/process&id=${param.id}"></t:tab>
</t:tabs>

</body>

