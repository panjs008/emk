<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>面料预采购合同</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialPactController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkMaterialPactPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					<c:if test="${emkMaterialPactPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialPactPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialPactPage.type eq 2}">包装辅料</c:if><c:if test="${emkMaterialPactPage.flag eq 1}">正式</c:if>采购合同编号:
				</label>
			</td>
			<td class="value" >
				<input id="materialNo" name="materialNo" readonly value="${emkMaterialPactPage.materialNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;"><c:if test="${emkMaterialPactPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialPactPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialPactPage.type eq 2}">包装辅料</c:if><c:if test="${emkMaterialPactPage.flag eq 1}">正式</c:if>采购合同编号</label>
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
				<input id="gysCode" name="gysCode" value="${emkMaterialDetailPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" value="${emkMaterialDetailPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					甲方:
				</label>
			</td>
			<td class="value">
				<input id="partyA" name="partyA"  value="${emkMaterialPactPage.partyA}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">甲方</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					乙方:
				</label>
			</td>
			<td class="value">
				<input id="partyB" name="partyB"  value="${emkMaterialPactPage.partyB}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">乙方</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					正式合同编号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum"  value="${emkMaterialPactPage.htNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">正式合同编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNum" name="orderNum" readonly value="${emkMaterialPactPage.orderNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
		<c:if test="${emkMaterialPactPage.flag eq 1}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						<c:if test="${emkMaterialPactPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialPactPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialPactPage.type eq 2}">包装辅料</c:if>采购需求单号:
					</label>
				</td>
				<td class="value">
					<input id="cgxqdh" name="cgxqdh"  value="${emkMaterialPactPage.cgxqdh}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;"><c:if test="${emkMaterialPactPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialPactPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialPactPage.type eq 2}">包装辅料</c:if>采购需求单号</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						<c:if test="${emkMaterialPactPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialPactPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialPactPage.type eq 2}">包装辅料</c:if>采购合同号:
					</label>
				</td>
				<td class="value">
					<input id="cghtbh" name="cghtbh" readonly value="${emkMaterialPactPage.cghtbh}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;"><c:if test="${emkMaterialPactPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialPactPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialPactPage.type eq 2}">包装辅料</c:if>采购合同号</label>
				</td>
			</tr>
		</c:if>

		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMaterialPactPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMaterialPactPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkMaterialPactPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMaterialPactPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMaterialPactPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMaterialPactPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMaterialPactPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMaterialPactPage.tracerName }" />
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
				<input id="developer" name="developer" readonly value="${emkMaterialPactPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkMaterialPactPage.developerName }" type="hidden"  />
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
				<input id="proTypeTree" value="${emkMaterialPactPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkMaterialPactPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkMaterialPactPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMaterialPactPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="kdDate" name="kdDate" readonly value="${emkMaterialPactPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="dhjqDate" name="dhjqDate" readonly value="${emkMaterialPactPage.dhjqDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
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
				<input id="ypjqDate" name="ypjqDate" readonly value="${emkMaterialPactPage.ypjqDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkMaterialPactPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
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
				<input id="leaveYpjqDays" name="leaveYpjqDays" value="${emkMaterialPactPage.leaveYpjqDays }"   datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距样品交期剩余天数</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leaveDhjqDays" name="levelDays" value="${emkMaterialPactPage.leaveDhjqDays }"   datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
			<td class="value">
				<input id="sumTotal" name="sumTotal" value="${emkMaterialPactPage.sumTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货地点:
				</label>
			</td>
			<td class="value">
				<input id="place" name="place" value="${emkMaterialPactPage.place }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货地点</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkMaterialPactPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					原产地及生产商:
				</label>
			</td>
			<td class="value">
				<input id="ycd" name="ycd" value="${emkMaterialPactPage.ycd }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原产地及生产商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					付款方式:
				</label>
			</td>
			<td class="value">
				<input id="payType" name="payType" value="${emkMaterialPactPage.payType }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款方式</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					法定代表授权代表:
				</label>
			</td>
			<td class="value">
				<input id="sqdb" name="sqdb" value="${emkMaterialPactPage.sqdb }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表授权代表</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" value="${emkMaterialPactPage.address }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" value="${emkMaterialPactPage.telphone }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					签定日期:
				</label>
			</td>
			<td class="value">
				<input id="signDate" name="signDate" value="${emkMaterialPactPage.signDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签定日期</label>
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