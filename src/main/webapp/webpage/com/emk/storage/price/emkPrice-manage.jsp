<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">


	</script>
</head>
<body>
<c:if test="${emkGlPage.id ne null}">
	<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>
</c:if>
<t:formvalid formid="formmange" dialog="true" usePlugin="password" layout="table" tiptype="1">
	<input id="manageid" name="manageid" type="hidden" value="${emkGlPage.id }"/>
	<table style="width: 98%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					场地:
				</label>
			</td>
			<td class="value"  style="width: 20%">
				<input id="place" name="place" value="${emkGlPage.place }"  datatype="d"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">场地</label>
			</td>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					运输费:
				</label>
			</td>
			<td class="value" style="width: 60%">
				<input id="tranCost" name="tranCost" value="${emkGlPage.tranCost }"  datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输费</label>
			</td>
		</tr>

		<tr>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					设备:
				</label>
			</td>
			<td class="value" style="width: 20%">
				<input id="equip" name="equip" value="${emkGlPage.equip }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">设备</label>
			</td>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					管理费:
				</label>
			</td>
			<td class="value" style="width: 60%">
				<input id="glf" name="glf" value="${emkGlPage.glf }" datatype="d"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">管理费</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					电费:
				</label>
			</td>
			<td class="value" style="width: 20%">
				<input id="dianfei" name="dianfei" value="${emkGlPage.dianfei }" datatype="d"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电费</label>
			</td>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					车辆使用费:
				</label>
			</td>
			<td class="value" style="width: 60%">
				<input id="carCost" name="carCost" value="${emkGlPage.carCost }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">车辆使用费</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					水费:
				</label>
			</td>
			<td class="value" style="width: 20%">
				<input id="waterCost" name="waterCost" value="${emkGlPage.waterCost }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">水费</label>
			</td>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					其他:
				</label>
			</td>
			<td class="value" style="width: 60%">
				<input id="other" name="other" value="${emkGlPage.other }"  type="text" datatype="d" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">其他</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
