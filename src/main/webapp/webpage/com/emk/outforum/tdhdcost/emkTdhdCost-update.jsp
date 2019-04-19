<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>提单货代费用</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkTdhdCostController.do?doUpdate" tiptype="1">
	<input id="tdhdCostId" name="tdhdCostId" type="hidden" value="${emkTdhdCostPage.id }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>

			<td align="right" width="15%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkTdhdCostPage.cusName }" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" width="18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkTdhdCostPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" width="18%">
				<label class="Validform_label">
					货代费用付款申请表编号:
				</label>
			</td>
			<td class="value">
				<input id="hdfyPayNo" name="hdfyPayNo" type="text" value="${emkTdhdCostPage.hdfyPayNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">货代费用付款申请表编号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkTdhdCostPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkTdhdCostPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					货代名称:
				</label>
			</td>
			<td class="value">
				<input id="hdName" name="hdName" type="text" value="${emkTdhdCostPage.hdName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">货代名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value"  >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkTdhdCostPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkTdhdCostPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					总金额:
				</label>
			</td>
			<td class="value"  >
				<input value="" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkTdhdCostPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkTdhdCostPage.tracerName }" />
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
			<td class="value" colspan="3">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkTdhdCostPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkTdhdCostPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cwyer" name="cwyer" type="text" value="${emkTdhdCostPage.cwyer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>
		</tr>
		</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkTdhdCostController.do?detailMxList&tdhdCostId=${emkTdhdCostPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="18%">
				<label class="Validform_label">
					收款单位:
				</label>
			</td>
			<td class="value">
				<input id="skDept" name="skDept" type="text" value="${emkTdhdCostPage.skDept }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收款单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					开户行:
				</label>
			</td>
			<td class="value">
				<input id="bankName" name="bankName" type="text" value="${emkTdhdCostPage.bankName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户行</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					账号:
				</label>
			</td>
			<td class="value">
				<input id="account" name="account" type="text" value="${emkTdhdCostPage.account }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" value="${emkTdhdCostPage.telphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					费用付款状态:
				</label>
			</td>
			<td class="value">
				<input id="payState" name="payState" type="text" value="${emkTdhdCostPage.payState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">费用付款状态</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					费用付款日期:
				</label>
			</td>
			<td class="value">
				<input id="payDate" name="payDate" readonly  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" value="${emkTdhdCostPage.payDate }" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">费用付款日期</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					货代发票状态:
				</label>
			</td>
			<td class="value">
				<input id="hdfyFpState" name="hdfyFpState" type="text" value="${emkTdhdCostPage.hdfyFpState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">货代发票状态</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					发票号:
				</label>
			</td>
			<td class="value">
				<input id="hdfyFp" name="hdfyFp" type="text" value="${emkTdhdCostPage.hdfyFp }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票号</label>
			</td>
		</tr>
	</table>

</t:formvalid>
</body>
