<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>
<div id="orderDiv" title="流程图" style="width:100%;height:250px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0" style="margin-top: -20px;"  src="flowController.do?showProcess&id=${param.id}&tableName=emk_price" width="100%" height="100%"></iframe>
</div>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:300px;overflow:hidden" >
	<iframe scrolling="no" id="timeFrm" frameborder="0"  src="emkPriceController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
	</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPriceController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<input id="fgg" name="fgg" type="hidden" value="${param.id }"/>

	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${((emkPrice.state eq 1 || emkPrice.state eq 23) && ROLE.rolecode eq 'jsy' || emkPrice.state eq 22 && ROLE.rolecode eq 'cgy'
								|| emkPrice.state eq 25 && ROLE.rolecode eq 'cwjl' || emkPrice.state eq 26 && ROLE.rolecode eq 'zjl') }">
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否同意:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="isPass" type="radio" datatype="*"  value="0">
					是
					<input name="isPass" type="radio" datatype="*"  value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否同意</label>
				</td>
			</tr>
		<%--<c:if test="${orderFinish eq '0' && orderPorcess.taskDefinitionKey eq 'jsTask'}">
			<c:set value="采购经理" var="userKey" scope="session"></c:set>
		</c:if>
		<c:if test="${orderFinish eq '0' && orderPorcess.taskDefinitionKey eq 'cgTask'}">
			<c:set value="财务经理" var="userKey" scope="session"></c:set>
		</c:if>
		<c:if test="${orderFinish eq '0' && orderPorcess.taskDefinitionKey ne 'cwTask' && orderPorcess.taskDefinitionKey ne 'usertask1'}">
			<tr id="chooseUser">
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						指定下一步操作人:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="realName" name="realName" type="text" readonly style="width: 300px" class="inputxt" >
					<input name="userName"   type="hidden"  id="userName" type="text"  />
					<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelectCw" name="userList1" width="700px" height="500px"
							   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
				</td>
			</tr>
		</c:if>--%>
			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						处理意见:
					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="advice" style="width:95%;height:80px" class="inputxt" rows="5" name="advice"></textarea>
				</td>
			</tr>
		</c:if>

	</table>
</t:formvalid>

</body>
</html>