<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script>
		/*$(document).ready(function(){
			var height =window.top.document.body.offsetHeight*0.79;
			$("#proOrderDetail").css("height",height);
		});*/
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

		function toDecimal(x) {
			if(!x){
				return x;
			}
			var result = parseFloat(x);
			if (isNaN(result)) {
				return x;
			}
			result = Math.round(x * 100) / 100;
			return result;
		}
		function selectAll(selectStatus){//传入参数（全选框的选中状态）
			//根据name属性获取到单选框的input，使用each方法循环设置所有单选框的选中状态
			if(selectStatus){
				$("input[name='ck']").each(function(i,n){
					n.checked = true;
				});
			}else{
				$("input[name='ck']").each(function(i,n){
					n.checked = false;
				});
			}
		}
		function setPrice(ii){
			var chengbenV = toDecimal((parseFloat($("#sunhaoPrecent"+ii).val())+1)*parseFloat($('#yongliang'+ii).val()))*parseFloat($('#signPrice'+ii).val());
			$("#chengben"+ii).val(toDecimal(chengbenV));
		}
		function setPrice2(ii){
			var chengbenV = toDecimal((parseFloat($("#bsunhaoPrecent"+ii).val())+1)*parseFloat($('#byongliang'+ii).val()))*parseFloat($('#bsignPrice'+ii).val());
			$("#bchengben"+ii).val(toDecimal(chengbenV));
		}
		function setPrice3(ii){
			var chengbenV = toDecimal((parseFloat($("#csunhaoPrecent"+ii).val())+1)*parseFloat($('#cyongliang'+ii).val()))*parseFloat($('#csignPrice'+ii).val());
			$("#cchengben"+ii).val(toDecimal(chengbenV));
		}
	</script>
</head>

<body>
<t:tabs id="proOrderDetail"  iframe="false" tabPosition="top" width="100%" heigth="${param.hVal}"  fit="true">
	<t:tab iframe="emkProOrderController.do?goBase&id=${param.id}"  width="100%" heigth="${param.hVal2}"   icon="fa fa-calendar" title="基本信息" id="base"></t:tab>
	<t:tab href="emkProOrderController.do?orderMxList&proOrderId=${param.id}"   width="100%" icon="fa fa-flask" title="原料面料" id="detail"></t:tab>
	<t:tab href="emkProOrderController.do?orderMxList2&proOrderId=${param.id}"   width="100%" icon="fa fa-cut" title="缝制辅料" id="fengdetail"></t:tab>
	<t:tab href="emkProOrderController.do?orderMxList3&proOrderId=${param.id}"   width="100%" icon="fa fa-cube" title="包装辅料" id="bzdetail"></t:tab>
	<t:tab href="emkProOrderController.do?barCodeMxListTab&proOrderId=${param.id}" icon="fa fa-cube" title="条码" id="barCodeTab"></t:tab>
	<t:tab href="emkProOrderController.do?boxMxListTab&proOrderId=${param.id}" icon="fa fa-cube" title="入箱方式" id="boxCodeTab"></t:tab>

</t:tabs>

</body>
