<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>


</head>
<body>

<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkYptzdController.do?doCreateKfd"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr id="chooseUser">
			<td align="right" width="150px" valign="middle">
				<label class="Validform_label">
					指定技术员:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="realName" name="realName" type="text" readonly style="width: 150px" class="inputxt" >
				<input name="userName"   type="hidden"  id="userName" type="text"  />
				<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelectUserKey&userKey=技术员" name="userList1" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
			</td>
		</tr>
	</table>

</t:formvalid>

</body>
</html>