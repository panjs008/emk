<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>
<div id="orderDiv" title="流程图" style="width:100%;height:325px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0" style="margin-top: -10px;"  src="flowController.do?showProcess&id=${param.id}&tableName=emk_material" width="100%" height="100%"></iframe>
</div>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:295px;overflow:hidden" >
	<iframe scrolling="no" id="timeFrm" frameborder="0"  src="emkMaterialController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
	</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${emkMaterial.state eq '1' && param.node  eq 'ywyTask'  && ROLE.rolecode eq 'ywy'
						|| (emkMaterial.state eq '4' || emkMaterial.state eq '35') && param.node  eq 'checkTask'  && ROLE.rolecode eq 'ywjl'
						|| (emkMaterial.state eq '0' && param.node  eq 'materialTask' || (emkMaterial.state eq '23' || emkMaterial.state eq '41') && param.node  eq 'cksqdTask')  && ROLE.rolecode eq 'jsy'
						|| (emkMaterial.state eq '3' || emkMaterial.state eq '36' || emkMaterial.state eq '37' || emkMaterial.state eq '38'|| emkMaterial.state eq '39' || emkMaterial.state eq '15') && (param.node  eq 'cgyTask' || param.node  eq 'cgyzxTask' || param.node  eq 'cgyjdTask' || param.node  eq 'rksqTask')  && ROLE.rolecode eq 'cgy'
						|| (emkMaterial.state eq '24' && param.node  eq 'cgjlTask' || emkMaterial.state eq '39' && param.node  eq 'cgjlshTask' || emkMaterial.state eq '43' && param.node  eq 'cgjlshTask')  && ROLE.rolecode eq 'cgjl'}">
			<c:if test="${ROLE.rolecode ne 'jsy'}">
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
			<c:if test="${param.node  eq 'rksqTask'}">
				<tr id="chooseUser">
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							指定入库操作员:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="realName" name="realName" type="text" readonly style="width: 150px" class="inputxt" >
						<input name="userName"   type="hidden"  id="userName" type="text"  />
						<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelectUserKey&userKey=仓库员" name="userList1" width="700px" height="500px"
								   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
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
		</c:if>

</table>
</t:formvalid>

</body>
</html>