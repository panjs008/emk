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
        $(document).ready(function(){
            $(".datagrid-toolbar").parent().css("width","auto");
        });
	</script>
</head>
<body>
<iframe id="printfrm" src="emkOrderDetailController.do?list" width="100%" height="410"
		style="border: 0px; line-height: 21px; background: #fff;; " frameborder="no" border="0" scrolling="yes">
</iframe>
</body>

