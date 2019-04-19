<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>入库申请单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>
<div id="orderDiv" title="流程图" style="width:100%;height:325px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0" style="margin-top: -10px;margin-left: 40px;"  src="flowController.do?showProcess&id=${param.id}&tableName=emk_m_in_storage" width="100%" height="100%"></iframe>
</div>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:295px;overflow:hidden" >
	<iframe scrolling="no" id="timeFrm" frameborder="0"  src="emkMInStorageController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
</div>

<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMInStorageController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${emkInStoragePage.state eq '1' && param.node  eq 'checkTask'  && ROLE.rolecode eq 'cgy'
						|| emkInStoragePage.state eq '24'  && param.node  eq 'cgyzjTask'  && ROLE.rolecode eq 'cgy'}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否同意:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="isPass" type="radio" datatype="*"  value="0">
					是
					<input name="isPass" type="radio" datatype="*"   value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否同意</label>
				</td>
			</tr>
		</c:if>

		<tr>
			<td align="right" width="150px" valign="middle">
				<label class="Validform_label">
					处理意见:
				</label>
			</td>
			<td class="value" colspan="3"><textarea datatype="*" id="advice" style="width:95%;height:80px" class="inputxt" rows="5" name="advice"></textarea>
			</td>
		</tr>
</table>
</t:formvalid>

</body>
</html>