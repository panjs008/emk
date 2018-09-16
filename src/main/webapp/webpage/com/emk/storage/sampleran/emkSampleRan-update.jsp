<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品工序</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleGxController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSampleGxPage.id }"/>
	<input id="sampleId" name="sampleId" type="hidden" value="${emkSampleGxPage.sampleId }" />

	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					工序名称:
				</label>
			</td>
			<td class="value">
				<input id="gxmc" name="gxmc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工序名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单件耗时:
				</label>
			</td>
			<td class="value">
				<input id="djhs" name="djhs" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件耗时</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="unit" field="unit" typeGroupCode="units" datatype="*" defaultVal="default" hasLabel="false" title="单位"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					每天产量:
				</label>
			</td>
			<td class="value">
				<input id="productTotal" name="productTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">每天产量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					成本:
				</label>
			</td>
			<td class="value">
				<input id="chengben" name="chengben" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">成本</label>
			</td>
		</tr>
		<tr>



	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/samplegx/emkSampleGx.js"></script>
