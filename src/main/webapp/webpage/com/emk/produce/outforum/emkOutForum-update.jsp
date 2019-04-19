<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>出货通知单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码

		$(document).ready(function(){
			$("#detailId").load("emkOutForumController.do?orderMxList&proOrderId=${emkOutForumPage.id }");
		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkOutForumController.do?doUpdate" tiptype="1">
	<input id="outForumId" name="outForumId" type="hidden" value="${emkOutForumPage.id }"/>
	<input id="type" name="type" type="hidden" value="${param.type }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="5">
				<input id="cusName" name="cusName" readonly type="text" value="${emkOutForumPage.cusName }" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkOutForumPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkOutForumPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" value="${emkOutForumPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					出货日期:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" readonly value="${emkOutForumPage.outDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货日期</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					提交订舱日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkOutForumPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交订舱日期</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right" >
				<label class="Validform_label">
					出货通知单号:
				</label>
			</td>
			<td class="value">
				<input id="outForumNo" name="outForumNo"  value="${emkOutForumPage.outForumNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货通知单号</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					订舱通知单号:
				</label>
			</td>
			<td class="value">
				<input id="cargoNo" name="cargoNo" type="text" value="${emkOutForumPage.cargoNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱通知单号</label>
			</td>
			</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					离厂放行条号:
				</label>
			</td>
			<td class="value">
				<input id="levealFactoryNo" name="levealFactoryNo" type="text" value="${emkOutForumPage.levealFactoryNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂放行条号</label>
			</td>
		</tr>


		<tr>
			<td class="value" colspan="4"></td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkOutForumPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkOutForumPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkOutForumPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkOutForumPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkOutForumPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkOutForumPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkOutForumPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkOutForumPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value">
				<input id="cwyer" name="cwyer" type="text" value="${emkOutForumPage.cwyer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>
		</tr>
		<%--<tr>

			<td align="right">
				<label class="Validform_label">
					离厂日期:
				</label>
			</td>
			<td class="value">
				<input id="levalFactoryDate" name="levalFactoryDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${emkOutForumPage.levalFactoryDate }" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂日期</label>
			</td>
		</tr>--%>

	</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkOutForumController.do?detailMxList&outForumId=${emkOutForumPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%">
				<label class="Validform_label">
					预计收款日期:
				</label>
			</td>
			<td class="value" >
				<input id="skDate" name="skDate" readonly value="${emkOutForumPage.skDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计收款日期</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					收款方式:
				</label>
			</td>
			<td class="value" >
				<input id="skType" name="skType" type="text" value="${emkOutForumPage.skType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收款方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提单号:
				</label>
			</td>
			<td class="value" >
				<input id="tdNum" name="tdNum" type="text" value="${emkOutForumPage.tdNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提单状态:
				</label>
			</td>
			<td class="value" >
				<input id="tdState" name="tdState" type="text" value="${emkOutForumPage.tdState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单状态</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
