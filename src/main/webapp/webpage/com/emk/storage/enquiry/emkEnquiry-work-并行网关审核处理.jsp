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
	<iframe scrolling="no" id="processFrm" frameborder="0"  src="emkEnquiryController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
	</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkEnquiryController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<c:if test="${emkEnquiry.state eq 1 && ROLE.rolecode eq 'ywjl'}">
		<input id="processNode" name="processNode" type="hidden" value="ywbCheckTask"/>
	</c:if>
	<c:if test="${emkEnquiry.state eq 1 && ROLE.rolecode eq 'jsjl'}">
		<input id="processNode" name="processNode" type="hidden" value="jsbCheckTask"/>
	</c:if>
	<c:if test="${emkEnquiry.state eq 1 && ROLE.rolecode eq 'scjl'}">
		<input id="processNode" name="processNode" type="hidden" value="scbCheckTask"/>
	</c:if>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${emkEnquiry.state eq 1 && (param.node eq 'ywbCheckTask' || param.node eq 'jsbCheckTask'  || param.node eq 'scbCheckTask') && ROLE.rolecode ne 'ywy'}">
			<%--<c:set value="业务经理" var="userKey" scope="session"></c:set>--%>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否同意:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="isPass" type="radio" datatype="*" value="0">
					是
					<input name="isPass" type="radio" datatype="*" value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否同意</label>
				</td>
			</tr>
			<c:if test="${emkEnquiry.state ne 0 && (ROLE.rolecode eq 'ywjl' || ROLE.rolecode eq 'admin')}">
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							评估意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="pgAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="pgAdvice"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							包装部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="bzAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="bzAdvice"></textarea>
					</td>
				</tr>
			</c:if>
			<c:if test="${emkEnquiry.state eq 1  && ( ROLE.rolecode eq 'jsjl' ||  ROLE.rolecode eq 'admin')}">
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							技术部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="jsbAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="jsbAdvice"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							染色部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="ranAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="ranAdvice"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							缝制部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="fengAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="fengAdvice"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							烫标整烫部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="biaoAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="biaoAdvice"></textarea>
					</td>
				</tr>
			</c:if>
			<c:if test="${emkEnquiry.state eq 1 && ROLE.rolecode eq 'scjl' }">
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							采购部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="caiAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="caiAdvice"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							生产计划部意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="scjhAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="scjhAdvice"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px" valign="middle">
						<label class="Validform_label">
							生产总负责人意见:
						</label>
					</td>
					<td class="value" colspan="3"><textarea datatype="*" id="sczfAdvice" style="width:90%;height:60px" class="inputxt" rows="5" name="sczfAdvice"></textarea>
					</td>
				</tr>
			</c:if>

			<%--<tr id="chooseUser">
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
			</tr>--%>
		</c:if>


</table>
</t:formvalid>

</body>
</html>