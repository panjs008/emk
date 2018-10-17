<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:360px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0"  src="emkWorkOrderController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
	</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkWorkOrderController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'khxpTask' || orderPorcess.taskDefinitionKey eq 'sampleTask' || orderPorcess.taskDefinitionKey eq 'sampleCheckTask' || orderPorcess.taskDefinitionKey eq 'billTask'|| orderPorcess.taskDefinitionKey eq 'htTask' || orderPorcess.taskDefinitionKey eq 'produceTask') }">
			<c:if test="${orderPorcess.taskDefinitionKey eq 'sampleCheckTask'}">
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否通过:
						</label>
					</td>
					<td class="value" colspan="3">
						<input name="isPass" type="radio" datatype="*" <c:if test="${emkWorkOrderPage.isPass eq '0'}">checked="true"</c:if> value="0">
						是
						<input name="isPass" type="radio" datatype="*"  <c:if test="${emkWorkOrderPage.isPass eq '1'}">checked="true"</c:if> value="1">
						否
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">是否通过</label>
					</td>
				</tr>
			</c:if>
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

			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						处理意见:
					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="leadAdvice" style="width:95%;height:80px" class="inputxt" rows="5" name="leadAdvice"></textarea>
				</td>
			</tr>
		</c:if>

		<c:if test="${orderFinish eq '0' && orderPorcess.taskDefinitionKey eq 'cwTask'}">

			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						处理意见:
					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="financeAdvice" style="width:95%;height:80px" class="inputxt" rows="5" name="financeAdvice"></textarea>
				</td>
			</tr>
		</c:if>
</table>
</t:formvalid>

</body>
</html>