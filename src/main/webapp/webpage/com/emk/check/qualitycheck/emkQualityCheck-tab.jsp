<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品大货质量检查表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<%--<script>
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

	</script>--%>
</head>

<body>

<t:tabs id="quailtyCheckDetail"  iframe="false" tabPosition="top" width="100%" heigth="${param.hVal}"  fit="true">
	<t:tab iframe="emkQualityCheckController.do?goBase&id=${param.id}"  width="100%" heigth="99%"   icon="fa fa-calendar" title="第一版" id="base"></t:tab>
	<t:tab href="emkSizeCheckController.do?goAdd&id=${param.id}"   width="100%" icon="fa fa-cube" title="尺寸" id="ccdetail"></t:tab>
	<t:tab iframe="emkQualityCheckController.do?goImage&id=${param.id}"   width="100%" heigth="99%"  icon="fa fa-flask" title="图片" id="detail"></t:tab>

</t:tabs>

</body>
