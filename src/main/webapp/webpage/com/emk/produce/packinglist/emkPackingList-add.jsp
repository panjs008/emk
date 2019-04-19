<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>装箱单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码

		$(document).ready(function(){

		});



	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPackingListController.do?doAdd" tiptype="1">
	<input id="packId" name="packId" type="hidden" value="${emkPackingListPage.id }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" colspan="2" width="50%">&nbsp;</td>
			<td class="value" width="15%" align="right">
				<label class="Validform_label">
					生产方名称:
				</label>
			</td>
			<td class="value">
				<input id="scfmc" name="scfmc" type="text" value="${emkPackingListPage.scfmc }" style="width: 80%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产方名称</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td class="value" align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" type="text" value="${emkPackingListPage.address }" style="width:80%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>
		</tr>

		<tr>
			<td class="value" align="right"  width="15%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkPackingListPage.cusName }" style="width: 200px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>

		</tr>

		<tr>
			<td class="value" align="right"  width="15%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkPackingListPage.cusNum }" style="width: 200px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>

		</tr>
		<tr>
			<td class="value" align="right"  width="15%">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusAddress" name="cusAddress"  type="text" value="${emkPackingListPage.cusAddress }" style="width: 200px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right"  width="15%">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="telphone" name="telphone"  type="text" value="${emkPackingListPage.telphone }" style="width: 200px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>

	</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkPackingListController.do?detailMxList&pactId=${emkPackingListPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
</t:formvalid>
</body>
