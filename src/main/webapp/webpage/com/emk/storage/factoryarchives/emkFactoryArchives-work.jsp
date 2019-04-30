<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>

<div id="orderDiv" title="流程图" style="width:100%;height:310px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0" style="margin-top: -10px;"  src="flowController.do?showProcess&id=${param.id}&tableName=emk_factory_archives" width="100%" height="100%"></iframe>
</div>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:295px;overflow:hidden" >
	<iframe scrolling="no" id="timeFrm" frameborder="0"  src="emkFactoryArchivesController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkFactoryArchivesController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${(emkFactoryArchivesPage.state eq 1 || emkFactoryArchivesPage.state eq 4) && ROLE.rolecode eq 'ywjl' && param.node eq 'checkTask'
						|| emkFactoryArchivesPage.state eq 3 && ROLE.rolecode eq 'zjl' && param.node eq 'zjlshTask'
						|| (emkFactoryArchivesPage.state eq 50 || emkFactoryArchivesPage.state eq 4) && ROLE.rolecode eq 'ywjl' && param.node eq 'ywjlshTask'
						|| emkFactoryArchivesPage.state eq 3  && ROLE.rolecode eq 'ycjl' && param.node eq 'fpycyTask'
						|| emkFactoryArchivesPage.state eq 53  && ROLE.rolecode eq 'ycjl' && param.node eq 'ycbshTask'
						|| (emkFactoryArchivesPage.state eq 51 or emkFactoryArchivesPage.state eq 52) && ROLE.rolecode eq 'ycy'}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否同意:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="isPass" type="radio" datatype="*" value="0" onclick="setReruire(0);">
					是
					<input name="isPass" type="radio" datatype="*" value="1" onclick="setReruire(1);">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否同意</label>
				</td>
			</tr>
			<c:if test="${emkFactoryArchivesPage.state eq 3 && ROLE.rolecode eq 'ycjl' && param.node eq 'fpycyTask'}">
				<tr id="chooseUser">
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							指定验厂员:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="realName" name="realName" type="text" datatype="*" readonly style="width: 150px" class="inputxt" >
						<input name="userName"   type="hidden"  id="userName" datatype="*"  type="text"  />
						<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelectUserKey&userKey=验厂员" name="userList1" width="700px" height="500px"
								   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
					</td>
				</tr>
			</c:if>

			<c:if test="${emkFactoryArchivesPage.state eq 52 && ROLE.rolecode eq 'ycy' && param.node eq 'bgTask'}">
				<tr>
					<td align="right">
						<label class="Validform_label">
							验厂报告:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="fileName" name="fileName" datatype="*" type="hidden" />
						<input id="fileNameUrl" name="fileNameUrl"   type="hidden" />
						<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
						</t:upload><span id="fileNameId"></span>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="4" id="instructionfile" class="value">
				</td>
			</tr>
			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						处理意见:
					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="advice" style="width:90%;height:60px" class="inputxt" rows="5" name="advice"></textarea>
				</td>
			</tr>
		</c:if>


</table>
</t:formvalid>

</body>
</html>