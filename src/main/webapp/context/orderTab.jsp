<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script src="metro/js/jquery.nicescroll.js" type="text/javascript"></script>

	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">

        $(document).ready(
            function() {
                $("html").niceScroll();
            }
        );


        $(document).ready(function(){
            $(".datagrid-toolbar").parent().css("width","auto");

        });
	</script>
</head>
<body>
<div style="padding: 3px; height: 35px;width:auto;" class="datagrid-toolbar" align="right">
	<input class="btn" type="submit" value="保存" style="height:30px;width:100px !important;border-radius:5px">
	<input class="btn" type="submit" value="提交" style="height:30px;width:100px !important;border-radius:5px">
	<input class="btn" type="submit" value="审核" style="height:30px;width:100px !important;border-radius:5px">
</div>
<t:tabs id="rkglTabs" iframe="false" tabPosition="top" fit="false">
	<%--<t:tab title="基本信息" id="ciFrm"  heigth="100%" width="100%" icon="" iframe="zcCiController.do?list&eType=1&ciId=-1"></t:tab>--%>
	<t:tab title="基本信息" id="ciFrm"  heigth="500" width="100%" icon="" iframe="ymkCustomController.do?jump&r=order"></t:tab>
	<t:tab title="商品信息" id="ciFrm2"  heigth="500" width="100%" icon="" iframe="ymkCustomController.do?jump&r=order"></t:tab>
	<t:tab title="关联合同" id="ciFrm2"  heigth="500" width="100%" icon="" iframe="ymkCustomController.do?jump&r=order"></t:tab>

</t:tabs>

</body>

