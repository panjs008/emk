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
	<iframe scrolling="no" id="processFrm" frameborder="0"  src="uRepairController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
	</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="uRepairController.do?doUpdate"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${uRepairPage.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
	<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey eq 'checkTask'}">
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否自修:
				</label>
			</td>
			<td class="value" colspan="3">
				<input name="isFinish" type="radio" onclick="setchooseUser(0);" datatype="*" <c:if test="${uRepairPage.isFinish eq '0'}">checked="true"</c:if> value="0">
				是
				<input name="isFinish" type="radio" onclick="setchooseUser(1);"  datatype="*"  <c:if test="${uRepairPage.isFinish eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否自修</label>
			</td>
		</tr>
			</c:if>
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey eq 'serviceTask'}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						保修类别:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="repairType" type="radio" onclick="setchooseService(0)" datatype="*" <c:if test="${uRepairPage.repairType eq '0'}">checked="true"</c:if> value="0">
					保外
					<input name="repairType" type="radio" onclick="setchooseService(1)"  datatype="*"  <c:if test="${uRepairPage.repairType eq '1'}">checked="true"</c:if> value="1">
					保内
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">保修类别</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey eq 'systemToService'}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						处理类型:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="csType" type="radio" onclick="setchooseServiceYg(0)" datatype="*" <c:if test="${uRepairPage.csType eq '0'}">checked="true"</c:if> value="0">
					委托
					<input name="csType" type="radio" onclick="setchooseServiceYg(1)" datatype="*"  <c:if test="${uRepairPage.csType eq '1'}">checked="true"</c:if> value="1">
					自有
					<input name="csType" type="radio" onclick="setchooseServiceYg(2)" datatype="*"  <c:if test="${uRepairPage.csType eq '2'}">checked="true"</c:if> value="2">
					原厂
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">处理类型</label>
				</td>
			</tr>
				</c:if>
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey ne 'normalRepairTask'  && repairPorcess.taskDefinitionKey ne 'saveRecord' && repairPorcess.taskDefinitionKey ne 'applyTask' && repairPorcess.taskDefinitionKey ne 'belongService'  && repairPorcess.taskDefinitionKey ne 'homeService'}">
			<tr id="chooseUser">
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						指定下一步操作人:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="realName" name="realName" datatype="*" type="text" readonly style="width: 300px" class="inputxt" >
					<input name="userName"   type="hidden"  id="userName" type="text"  />
					<c:choose>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'checkTask'}">
							<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSignService" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择服务商" textname="realName" isclear="true" isInit="true"></t:choose>

						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'zxRepair'}">
							<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userServiceYg" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择服务商员工" textname="realName" isclear="true" isInit="true"></t:choose>
						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'serviceTask'}">
							<span id="spanId1"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userProjectService" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择中标商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
							<span id="spanId2" style="display: none"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userService" name="userList0" width="700px" height="500px"
														  icon="icon-search" title="选择服务商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'systemToService'}">
							<span id="spanId1"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userService" name="userList0" width="700px" height="500px"
														  icon="icon-search" title="选择本地服务商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
							<span id="spanId2" style="display: none"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userServiceYg" name="userList0" width="700px" height="500px"
																				icon="icon-search" title="选择自有工程师" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
							<span id="spanId3" style="display: none"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSignService" name="userList0" width="700px" height="500px"
																				icon="icon-search" title="选择厂商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'localService' || repairPorcess.taskDefinitionKey eq 'tjService'}">
							<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userServiceYg" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择员工" textname="realName" isclear="true" isInit="true"></t:choose>
						</c:when>
						<%--<c:otherwise>
							<t:choose  hiddenName="userName" hiddenid="userName" url="userController.do?userSelect0" name="userList0"
									   icon="icon-search" title="选择用户" textname="realName"  isclear="true" width="700px" height="500px" isInit="true"></t:choose>
						</c:otherwise>--%>
					</c:choose>

				</td>
			</tr>
		</c:if>
		<c:if test="${processFinish eq '0'}">
			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						<c:if test="${repairPorcess.taskDefinitionKey eq 'zxRepair' || repairPorcess.taskDefinitionKey eq 'normalRepairTask'}">维修情况:</c:if>
						<c:if test="${repairPorcess.taskDefinitionKey ne 'zxRepair' && repairPorcess.taskDefinitionKey ne 'normalRepairTask'}">处理意见:</c:if>

					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="adviceContent" style="width:95%;height:80px" class="inputxt" rows="5" name="adviceContent"></textarea>
				</td>
			</tr>
		</c:if>
</table>
</t:formvalid>

</body>
</html>