<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>联系人表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">
		$(function(){

		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomContactController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${ymkCustomContactPage.id }"/>
	<input id="customId" name="customId" type="hidden" value="${ymkCustomContactPage.id}"/>
	<input id="customName" name="customName" type="hidden" value="${ymkCustomContactPage.customName}"/>

	<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					姓名:
				</label>
			</td>
			<td class="value">
				<input id="userName" name="userName" type="text" style="width: 150px" value="${ymkCustomContactPage.userName }" class="inputxt"  datatype="*" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">姓名</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					性别:
				</label>
			</td>
			<td class="value">
				<select name="sex" >
					<option value="0">男</option>
					<option value="1">女</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">性别</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">用户账号:  </label>
			</td>
			<td class="value" colspan="3">
				<%--<input id="orgIds" name="orgIds" type="hidden" value="${orgIds}"/>
				<input id="roleId" name="roleid" type="hidden" value="${roleId}"/>--%>

				<input id="userId" class="inputxt" name="userId" type="hidden" value="${user.id }"/>
				<input id="oldPassword" class="inputxt" name="oldPassword" type="hidden" value="${user.password }"/>

				<input id="userAccount" class="inputxt" name="userAccount" readonly  value="${user.userName }"/>
				<label class="Validform_label" style="display: none;">用户账号</label>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.password"/>: </label></td>
			<td class="value" colspan="3">
				<input type="password" class="inputxt" name="password" value="${user.password}"  plugin="passwordStrength" datatype="*6-18" errormsg="" />
                    <span class="passwordStrength" style="display: none;">
                        <span><t:mutiLang langKey="common.weak"/></span>
                        <span><t:mutiLang langKey="common.middle"/></span>
                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    </span>
				<span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.repeat.password"/>: </label></td>
			<td class="value" colspan="3">
				<input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"/>
				<span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					职务:
				</label>
			</td>
			<td class="value">
				<input id="position" name="position" type="text" style="width: 150px" value="${ymkCustomContactPage.position }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">职务</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					生日:
				</label>
			</td>
			<td class="value">
				<input id="brithDay" name="brithDay" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${ymkCustomContactPage.brithDay }"  style="width: 150px"  class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生日</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					email:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="email" name="email" type="text" style="width: 150px" value="${ymkCustomContactPage.email }" datatype="e" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">email</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					手机:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" style="width: 150px" value="${ymkCustomContactPage.telphone }" datatype="m" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">手机</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="mobile" name="mobile" type="text" style="width: 150px" value="${ymkCustomContactPage.mobile }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="remark" style="width:90%;height:60px" class="inputxt" rows="3" name="remark">${ymkCustomContactPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/service/customcontact/ymkCustomContact.js"></script>
