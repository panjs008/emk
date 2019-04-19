<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>试身样色样船样进度</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>


	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {

		});




		function setOutDate() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#ssyDate").val();
			$("#leavelSsy").val(DateDiff(d1,d2));
		}

		function setEndTimeSsy() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#ssyDate").val();
			$("#leavelSsy").val(DateDiff(d1,d2));
		}
		function setEndTimeCqy() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#cqyDate").val();
			$("#leavelCq").val(DateDiff(d1,d2));
		}

		function setEndTimeSy() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#syDate").val();
			$("#leavelSy").val(DateDiff(d1,d2));
		}

		function setEndTimeCy() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#cyDate").val();
			$("#leavelCy").val(DateDiff(d1,d2));
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSsysycyController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSsysycyPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceHtNum" name="produceHtNum" value="${emkSsysycyPage.produceHtNum }" datatype="*" validType="emk_ssysycy,PRODUCE_HT_NUM,id"  type="text"  style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo" datatype="*" value="${emkSsysycyPage.orderNo }" type="text" validType="emk_ssysycy,order_no,id" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkSsysycyPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkSsysycyPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly value="${emkSsysycyPage.cusNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly value="${emkSsysycyPage.cusName }" type="text" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>

		<tr id="dgrImageDiv">
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>

				<input id="gysCode" name="gysCode" value="${emkSsysycyPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkSsysycyPage.gys }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" value="${emkSsysycyPage.businesser }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkSsysycyPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly value="${emkSsysycyPage.tracer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  value="${emkSsysycyPage.tracerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>

			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" value="${emkSsysycyPage.developer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  value="${emkSsysycyPage.developerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" value="${emkSsysycyPage.sampleNo }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkSsysycyPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkSsysycyPage.proTypeName }" >
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkSsysycyPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px" value="${emkSsysycyPage.proType }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkSsysycyPage.kdDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" readonly value="${emkSsysycyPage.outDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkSsysycyPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					试身样状态:
				</label>
			</td>
			<td class="value" >
				<input id="ssyzt" name="ssyzt"  type="text" value="${emkSsysycyPage.ssyzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">试身样状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					试身样最晚确认时间:
				</label>
			</td>
			<td class="value">
				<input id="ssyDate" name="ssyDate" value="${emkSsysycyPage.ssyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeSsy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">试身样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					试身样距离剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelSsy" name="leavelSsy" readonly value="${emkSsysycyPage.leavelSsy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">试身样距离剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					产前样状态:
				</label>
			</td>
			<td class="value" >
				<input id="cqyzt" name="cqyzt"  type="text" value="${emkSsysycyPage.cqyzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产前样状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					产前样最晚确认时间:
				</label>
			</td>
			<td class="value" >
				<input id="cqyDate" name="cqyDate" value="${emkSsysycyPage.cqyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeCqy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产前样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					产前样距离剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leavelCq" name="leavelCq" readonly value="${emkSsysycyPage.leavelCq }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产前样距离剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					色样状态:
				</label>
			</td>
			<td class="value" >
				<input id="syzt" name="syzt"  type="text" value="${emkSsysycyPage.syzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					色样最晚确认时间:
				</label>
			</td>
			<td class="value" >
				<input id="syDate" name="syDate" value="${emkSsysycyPage.syDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeSy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					色样距离剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leavelSy" name="leavelSy" readonly value="${emkSsysycyPage.leavelSy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样距离剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					船样状态:
				</label>
			</td>
			<td class="value" >
				<input id="cyzt" name="cyzt"  type="text" value="${emkSsysycyPage.cyzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					船样最晚确认时间:
				</label>
			</td>
			<td class="value" >
				<input id="cyDate" name="cyDate" value="${emkSsysycyPage.cyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeCy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					船样距离剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelCy" name="leavelCy" readonly value="${emkSsysycyPage.leavelCy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样距离剩余天数</label>
			</td>
		</tr>
	</table>


</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 157,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>