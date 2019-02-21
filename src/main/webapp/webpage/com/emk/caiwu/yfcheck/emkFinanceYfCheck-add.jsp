<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>应付核准单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码



	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkFinanceYfCheckController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkFinanceYfCheckPage.id }"/>
	<input id="type" name="type" type="hidden" value="0"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					应付核准单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="yfCheckNo" name="yfCheckNo"  value="${emkFinanceYfCheckPage.yfCheckNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">应付核准单号</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					应付通知单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="yhzCheckNo" name="yhzCheckNo"  value="${emkFinanceYfCheckPage.yhzCheckNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">应付通知单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkFinanceYfCheckPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" >
				<input id="cusName" name="cusName" readonly type="text" value="${emkFinanceYfCheckPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkFinanceYfCheckPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkFinanceYfCheckPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkFinanceYfCheckPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkFinanceYfCheckPage.businesserName }" type="hidden"  />
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
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkFinanceYfCheckPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkFinanceYfCheckPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
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
				<input id="developer" name="developer" readonly value="${emkFinanceYfCheckPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkFinanceYfCheckPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					出货通知单号:
				</label>
			</td>
			<td class="value">
				<input id="outFourmNo" name="outFourmNo" type="text" value="${emkFinanceYfCheckPage.outFourmNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					付款项目类别:
				</label>
			</td>
			<td class="value">
				<input id="fkxmlb" name="fkxmlb" type="text" value="${emkFinanceYfCheckPage.fkxmlb }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款项目类别</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>

		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					应付通知单日期:
				</label>
			</td>
			<td class="value">
				<input id="yftzDate" name="yftzDate" readonly value="${emkFinanceYfCheckPage.yftzDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">应付通知单日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					预计付款日期:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="yjfkDate" name="yjfkDate" readonly value="${emkFinanceYfCheckPage.yjfkDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计付款日期</label>
			</td>

		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value">
				<input id="cwer" name="cwer" type="text" value="${emkFinanceYfCheckPage.cwer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					应付金额:
				</label>
			</td>
			<td class="value">
				<input id="money" name="money" type="text" value="${emkFinanceYfCheckPage.money }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">应付金额</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					货代费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="hdfyfkNo" name="hdfyfkNo" type="text" value="${emkFinanceYfCheckPage.hdfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">货代费用付款申请单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					运输费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="ysfyfkNo" name="ysfyfkNo" type="text" value="${emkFinanceYfCheckPage.ysfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输费用付款申请单号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					原料面料打样费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="ylmldyfyfkNo" name="ylmldyfyfkNo" type="text" value="${emkFinanceYfCheckPage.ylmldyfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料面料打样费用付款申请单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试费用付款申请单号:
				</label>
			</td>
			<td class="value">
				<input id="testNo" name="testNo" type="text" value="${emkFinanceYfCheckPage.testNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试费用付款申请单号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					招待费用付款申请单编号:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="zdfyfkNo" name="zdfyfkNo" type="text" value="${emkFinanceYfCheckPage.zdfyfkNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">招待费用付款申请单编号</label>
			</td>

		</tr>
	</table>

</t:formvalid>
</body>
