<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>

<div id="orderDiv" title="流程图" style="width:100%;height:300px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0" style="margin-top: -8px;"  src="flowController.do?showProcess&id=${param.id}&tableName=emk_enquiry" width="100%" height="100%"></iframe>
</div>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:300px;overflow:hidden" >
	<iframe scrolling="no" id="timeFrm" frameborder="0"  src="emkEnquiryController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkEnquiryController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${((emkEnquiry.state eq 1 || emkEnquiry.state eq 4) && ROLE.rolecode eq 'ywjl' && param.node eq 'ywbCheckTask' )
				|| ((emkEnquiry.state eq 3  || emkEnquiry.state eq 6 )&& param.node eq 'jsbCheckTask' && ROLE.rolecode eq 'jsjl')
		 		|| ((emkEnquiry.state eq 5 || emkEnquiry.state eq 8) && param.node eq 'ranCheckTask' && ROLE.rolecode eq 'rsjl')
		 		|| ((emkEnquiry.state eq 7 || emkEnquiry.state eq 10) && param.node eq 'fengCheckTask' && ROLE.rolecode eq 'fzjl')
		 		|| ((emkEnquiry.state eq 9 || emkEnquiry.state eq 12) && param.node eq 'biaoTask' && ROLE.rolecode eq 'btztzz')
		 		|| ((emkEnquiry.state eq 11 || emkEnquiry.state eq 14) && param.node eq 'baoTask' && ROLE.rolecode eq 'bzzz')
		 		|| ((emkEnquiry.state eq 13 || emkEnquiry.state eq 16) && param.node eq 'caiTask' && ROLE.rolecode eq 'cgjl')
		 		|| ((emkEnquiry.state eq 15 || emkEnquiry.state eq 18) && param.node eq 'scbCheckTask' && ROLE.rolecode eq 'scjjbjl')
		 		|| ((emkEnquiry.state eq 17 || emkEnquiry.state eq 20) && param.node eq 'sczfzrpfTask' && ROLE.rolecode eq 'sczfr')
		 		|| ((emkEnquiry.state eq 19 ) && param.node eq 'sczfzrpfTask' && ROLE.rolecode eq 'ywy')
		 		|| ((emkEnquiry.state eq 30 ) && param.node eq 'jgqrTask' && ROLE.rolecode eq 'ywgdy') }">
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
			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						处理意见:
					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="advice" style="width:90%;height:60px" class="inputxt" rows="5" name="advice"></textarea>
				</td>
			</tr>


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
		<c:if test="${(emkEnquiry.state eq 19 && param.node eq 'replyCustomTask' )}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						客户要求:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="cusType" type="radio" datatype="*" value="0">
					需要打样&nbsp;&nbsp;
					<input name="cusType" type="radio" datatype="*" value="1">
					需要报价&nbsp;&nbsp;
					<input name="cusType" type="radio" datatype="*" value="2">
					需要打样报价
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">客户要求</label>
				</td>
			</tr>
			<tr id="chooseUser">
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						指定业务员:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="realName" name="realName" type="text" readonly style="width: 150px" class="inputxt" >
					<input name="userName"   type="hidden"  id="userName" type="text"  />
					<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelectUserKey&userKey=业务员" name="userList1" width="700px" height="500px"
							   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
				</td>
			</tr>
			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						处理意见:
					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="ywadvice" style="width:90%;height:60px" class="inputxt" rows="5" name="ywadvice"></textarea>
				</td>
			</tr>
		</c:if>

</table>
</t:formvalid>

</body>
</html>