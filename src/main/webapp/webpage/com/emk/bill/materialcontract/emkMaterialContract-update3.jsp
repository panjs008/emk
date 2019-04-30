<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>原料采购合同表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码

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
		$(document).ready(function(){
			$("#rkStatus").val("${emkMaterialContractPage.rkStatus}");

		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialContractController.do?doUpdate" tiptype="1">
	<input id="emkMaterialContractId" name="emkMaterialContractId" type="hidden" value="${emkMaterialContractPage.id }"/>
	<input id="type" name="type" type="hidden" value="${emkMaterialContractPage.type }"/>

	<table style="width: 100%;margin-top: 5px;" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${param.node eq 'fksqdTask' }">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						操作类型:
					</label>
				</td>
				<td class="value"  colspan="5">
					<input  name="isPass" type="radio" datatype="*" value="0">
					保存数据&nbsp;&nbsp;&nbsp;&nbsp;
					<input  name="isPass" type="radio" datatype="*"  value="1">
					提交审核
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">操作类型</label>
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMaterialContractPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
			<%--	<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					<c:if test="${emkMaterialContractPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialContractPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialContractPage.type eq 2}">包装辅料</c:if>开发费付款申请单编号:
				</label>
			</td>
			<td class="value">
				<input id="payNo" name="payNo"  value="${emkMaterialContractPage.payNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;"><c:if test="${emkMaterialContractPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialContractPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialContractPage.type eq 2}">包装辅料</c:if>开发费付款申请单编号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMaterialContractPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMaterialContractPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMaterialContractPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					<c:if test="${emkMaterialContractPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialContractPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialContractPage.type eq 2}">包装辅料</c:if>需求单号:
				</label>
			</td>
			<td class="value">
				<input id="requireNo" name="requireNo"  value="${emkMaterialContractPage.requireNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;"><c:if test="${emkMaterialContractPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialContractPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialContractPage.type eq 2}">包装辅料</c:if>需求单号</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" >
				<select id="gysCode"  name="gysCode" datatype="*">
					<option value=''>请选择</option>
					<c:forEach items="${supplierEntities}" var="supplier">
						<option value='${supplier.companyCode}' ${supplier.companyCode eq emkMaterialContractPage.gysCode ? 'selected':''}>${supplier.companyNameZn}</option>
					</c:forEach>
				</select>
				<%--<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkMaterialContractPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkMaterialContractPage.gys }"  style="width: 150px" class="inputxt"  ignore="ignore" />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>

			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select id="businesser"  name="businesser" datatype="*">
					<option value=''>请选择</option>
					<c:forEach items="${ywyList}" var="ywy">
						<option value='${ywy.userName}' ${ywy.userName eq emkMaterialContractPage.businesser ? 'selected':''}>${ywy.realName}</option>
					</c:forEach>
				</select>
				<%--<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkMaterialContractPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMaterialContractPage.businesserName }" type="hidden"  />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					<c:if test="${emkMaterialContractPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialContractPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialContractPage.type eq 2}">包装辅料</c:if>采购单号:
				</label>
			</td>
			<td class="value">
				<input id="caigouNo" name="caigouNo"  value="${emkMaterialContractPage.caigouNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;"><c:if test="${emkMaterialContractPage.type eq 0}">原料面料</c:if><c:if test="${emkMaterialContractPage.type eq 1}">缝制辅料</c:if><c:if test="${emkMaterialContractPage.type eq 2}">包装辅料</c:if>采购单号</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMaterialContractPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select id="tracer"  name="tracer" datatype="*">
					<option value=''>请选择</option>
					<c:forEach items="${ywgdyList}" var="ywgdy">
						<option value='${ywgdy.userName}' ${ywgdy.userName eq emkMaterialContractPage.tracer ? 'selected':''}>${ywgdy.realName}</option>
					</c:forEach>
				</select>
				<%--<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMaterialContractPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMaterialContractPage.tracerName }" />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					打样通知单号:
				</label>
			</td>
			<td class="value">
				<input id="dytzdNo" name="dytzdNo"  value="${emkMaterialContractPage.dytzdNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样通知单号</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select id="developer"  name="developer" datatype="*">
					<option value=''>请选择</option>
					<c:forEach items="${scgdyList}" var="scgdy">
						<option value='${scgdy.userName}'  ${scgdy.userName eq emkMaterialContractPage.developer ? 'selected':''}>${scgdy.realName}</option>
					</c:forEach>
				</select>
				<%--<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkMaterialContractPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkMaterialContractPage.developerName }" type="hidden"  />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					打样需求单号:
				</label>
			</td>
			<td class="value">
				<input id="dyxqdNo" name="dyxqdNo"  value="${emkMaterialContractPage.dyxqdNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单号</label>
			</td>
		</tr>

	</table>
	<t:tabs id="requiredDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkMaterialContractController.do?orderMxList&type=${emkMaterialContractPage.type}&proOrderId=${emkMaterialContractPage.id}" icon="icon-search" title="${pactTypeName}" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="12%">
				<label class="Validform_label">
					收款单位:
				</label>
			</td>
			<td class="value">
				<input id="skDept" name="skDept"  value="${emkMaterialContractPage.skDept }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收款单位</label>
			</td>
		</tr>
		<tr>
			<td align="right" width="12%">
				<label class="Validform_label">
					账号:
				</label>
			</td>
			<td class="value">
				<input id="account" name="account"  value="${emkMaterialContractPage.account }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账号</label>
			</td>
		</tr>
		<tr>
			<td align="right" width="12%">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="relationer" name="relationer"  value="${emkMaterialContractPage.relationer }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right" width="12%">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone"  value="${emkMaterialContractPage.telphone }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
		<tr>
			<td align="right" width="12%">
				<label class="Validform_label">
					入库状态:
				</label>
			</td>
			<td class="value">
				<select id="rkStatus" name="rkStatus">
					<option value="0">未入库</option>
					<option value="1">入库中</option>
					<option value="2">已入库</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">入库状态</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
