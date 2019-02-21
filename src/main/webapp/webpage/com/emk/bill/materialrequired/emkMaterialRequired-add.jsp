<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>生产订单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialRequiredController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkMaterialRequiredPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					需求单号:
				</label>
			</td>
			<td class="value" >
				<input id="materialNo" name="materialNo" readonly value="${materialNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">需求单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkMaterialRequiredPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" value="${emkMaterialRequiredPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					合同编号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum" readonly value="${emkMaterialRequiredPage.htNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNum" name="orderNum" readonly value="${emkMaterialRequiredPage.orderNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMaterialRequiredPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMaterialRequiredPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMaterialRequiredPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMaterialRequiredPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId"  >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkMaterialRequiredPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMaterialRequiredPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>


		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMaterialRequiredPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMaterialRequiredPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkMaterialRequiredPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkMaterialRequiredPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkMaterialRequiredPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkMaterialRequiredPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkMaterialRequiredPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMaterialRequiredPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkMaterialRequiredPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="dhjqDate" name="dhjqDate" readonly value="${emkMaterialRequiredPage.dhjqDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					样品交期:
				</label>
			</td>
			<td class="value">
				<input id="ypjqDate" name="ypjqDate" readonly value="${emkMaterialRequiredPage.ypjqDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkMaterialRequiredPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>

		</tr>

		<tr id="dgrImageDiv">
			<td align="right">
				<label class="Validform_label">
					距样品交期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leaveYpjqDays" name="leaveYpjqDays" value="${emkMaterialRequiredPage.leaveYpjqDays }"   datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距样品交期剩余天数</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leaveDhjqDays" name="levelDays" value="${emkMaterialRequiredPage.leaveDhjqDays }"   datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>



		<tr>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sumTotal" name="sumTotal" value="${emkMaterialRequiredPage.sumTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkMaterialRequiredPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
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