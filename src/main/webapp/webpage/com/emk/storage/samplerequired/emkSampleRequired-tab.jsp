<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script>
		function resetTrNum(tableId) {
			$tbody = $("#"+tableId+"");
			$tbody.find('>tr').each(function(i){
				$(':input, select', this).each(function(){
					var $this = $(this), name = $this.attr('name'), val = $this.val();
					if(name!=null){
						if (name.indexOf("#index#") >= 0){
							$this.attr("name",name.replace('#index#',i));
						}else{
							var s = name.indexOf("[");
							var e = name.indexOf("]");
							var new_name = name.substring(s+1,e);
							$this.attr("name",name.replace(new_name,i));
						}
					}
				});
			});
		}
	</script>
</head>

<body>

<t:tabs id="sampleDetail"  iframe="false" tabPosition="top" width="100%" heigth="${param.hVal}"  fit="true">
	<t:tab iframe="emkSampleRequiredController.do?goBase&id=${param.id}"  width="100%" heigth="99%"   icon="fa fa-calendar" title="基本信息" id="base"></t:tab>
	<t:tab href="emkSampleRequiredController.do?goPb&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="无缝坯布" id="pbdetail"></t:tab>
	<t:tab href="emkPriceController.do?orderMxList&priceId=${param.id}"   width="100%" icon="fa fa-flask" title="原料面料" id="detail"></t:tab>
	<t:tab href="emkPriceController.do?orderMxList2&priceId=${param.id}"   width="100%" icon="fa fa-cut" title="缝制辅料" id="fengdetail"></t:tab>
	<t:tab href="emkPriceController.do?orderMxList3&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="包装辅料" id="bzdetail"></t:tab>
	<t:tab href="emkPriceController.do?gxList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="人工" id="rgdetail"></t:tab>
	<t:tab href="emkPriceController.do?ranList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="染色" id="randetail"></t:tab>
	<t:tab href="emkPriceController.do?yinList&priceId=${param.id}"   width="100%" icon="fa fa-cube" title="印花" id="yindetail"></t:tab>

</t:tabs>

</body>
