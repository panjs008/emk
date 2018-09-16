<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><t:mutiLang langKey="common.role.info"/></title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" action="roleController.do?saveRole">
	<input name="id" type="hidden" value="${role.id}">
	<fieldset class="step">
	<div class="form"><label class="Validform_label"><t:mutiLang langKey="common.role.name"/>:</label> <input name="roleName" class="inputxt" value="${role.roleName }" datatype="s2-16"> <span class="Validform_checktip">角色范围在2~16位字符</span>
	</div>
	<div class="form"><label class="Validform_label"> <t:mutiLang langKey="common.role.code"/>: </label> <input name="roleCode" id="roleCode" ajaxurl="roleController.do?checkRole&code=${role.roleCode }" class="inputxt"
		value="${role.roleCode }" datatype="s2-26"> <span class="Validform_checktip">角色编码范围在2~26位字符</span></div>
	</fieldset>
</t:formvalid>
</body>
</html>
