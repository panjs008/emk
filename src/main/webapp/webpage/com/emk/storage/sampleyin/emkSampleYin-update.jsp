<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品印花表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleYinController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSampleYinPage.id }"/>
	<input id="sampleId" name="sampleId" type="hidden" value="${emkSampleYinPage.sampleId }" />

	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺种类:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzy" field="gyzy" typeGroupCode="yhgyzl" datatype="*" defaultVal="${emkSampleYinPage.gyzy}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺种类</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					大小:
				</label>
			</td>
			<td class="value">
				<input id="size" name="size"  type="text" value="${emkSampleYinPage.size }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大小</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					成本:
				</label>
			</td>
			<td class="value">
				<input id="chengben" name="chengben" type="text" value="${emkSampleYinPage.chengben }" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">成本</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/sampleyin/emkSampleYin.js"></script>
