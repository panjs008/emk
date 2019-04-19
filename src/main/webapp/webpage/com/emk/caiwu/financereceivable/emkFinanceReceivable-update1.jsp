<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>应付通知单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkFinanceReceivableController.do?doUpdate" tiptype="1">
<input id="finaceId" name="finaceId" type="hidden" value="${emkFinanceReceivablePage.id }"/>
	<input id="type" name="type" type="hidden" value="${param.type }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					应付通知单号:
				</label>
			</td>
			<td class="value">
				<input id="receiveNum" name="receiveNum" type="text" value="${emkFinanceReceivablePage.receiveNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">应付通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" value="${emkFinanceReceivablePage.gysCode }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkFinanceReceivablePage.gys }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>

			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="hidden" value="${emkFinanceReceivablePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="cusName" name="cusName" readonly type="text" value="${emkFinanceReceivablePage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					付款项目类别:
				</label>
			</td>
			<td class="value">
				<input id="fkxmlb" name="fkxmlb" type="text" value="${emkFinanceReceivablePage.fkxmlb }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款项目类别</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					出货通知单号:
				</label>
			</td>
			<td class="value">
				<input id="outFourmNo" name="outFourmNo" type="text" value="${emkFinanceReceivablePage.outFourmNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货通知单号</label>
			</td>
			<td align="right"  >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkFinanceReceivablePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkFinanceReceivablePage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					货代费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="hdfyfkNo" name="hdfyfkNo" type="text" value="${emkFinanceReceivablePage.hdfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">货代费用付款申请单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订舱通知单号:
				</label>
			</td>
			<td class="value">
				<input id="cargoNo" name="cargoNo" type="text" value="${emkFinanceReceivablePage.cargoNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱通知单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value"  >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkFinanceReceivablePage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkFinanceReceivablePage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					运输费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="ysfyfkNo" name="ysfyfkNo" type="text" value="${emkFinanceReceivablePage.ysfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输费用付款申请单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					离厂放行条号:
				</label>
			</td>
			<td class="value">
				<input id="levealFactoryNo" name="levealFactoryNo" type="text" value="${emkFinanceReceivablePage.levealFactoryNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂放行条号</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkFinanceReceivablePage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkFinanceReceivablePage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					原料面料打样费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="ylmldyfyfkNo" name="ylmldyfyfkNo" type="text" value="${emkFinanceReceivablePage.ylmldyfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料面料打样费用付款申请单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					离厂日期:
				</label>
			</td>
			<td class="value">
				<input id="levealDate" name="levealDate" type="text" value="${emkFinanceReceivablePage.levealDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkFinanceReceivablePage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkFinanceReceivablePage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="testNo" name="testNo" type="text" value="${emkFinanceReceivablePage.testNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试费用付款申请单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value">
				<input id="produceHtNum" name="produceHtNum" type="text" value="${emkFinanceReceivablePage.produceHtNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value">
				<input id="cwer" name="cwer" type="text" value="${emkFinanceReceivablePage.cwer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					招待费用付款申请单编号:
				</label>
			</td>
			<td class="value">
				<input id="zdfyfkNo" name="zdfyfkNo" type="text" value="${emkFinanceReceivablePage.zdfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">招待费用付款申请单编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					原料面料打样通知单编号:
				</label>
			</td>
			<td class="value">
				<input id="ylmldyTzdNo" name="ylmldyTzdNo" type="text" value="${emkFinanceReceivablePage.ylmldyTzdNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料面料打样通知单编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					应付金额:
				</label>
			</td>
			<td class="value">
				<input id="money" name="money" type="text" datatype="d" value="${emkFinanceReceivablePage.money }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">应付金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					货款:
				</label>
			</td>
			<td class="value">
				<input id="dk" name="dk" type="text" value="${emkFinanceReceivablePage.dk }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">货款</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					招待申请单编号:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="zdsqNo" name="zdsqNo" type="text"  value="${emkFinanceReceivablePage.zdsqNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">招待申请单编号</label>
			</td>
		</tr>
	</table>

	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkFinanceReceivableController.do?detailMxList1&rId=${emkFinanceReceivablePage.id}" icon="icon-search" title="明细一" id="detail"></t:tab>
	</t:tabs>

	<t:tabs id="testCostDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkFinanceReceivableController.do?detailMxList2&rId=${emkFinanceReceivablePage.id}" icon="icon-search" title="明细二" id="detail2"></t:tab>
	</t:tabs>
</t:formvalid>
</body>
