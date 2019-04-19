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
<c:if test="${emkPbPage.id ne null}">
	<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>
</c:if>
<t:formvalid formid="formpb" dialog="true" usePlugin="password" layout="table" tiptype="1">
	<input id="pbid" name="pbid" type="hidden" value="${emkPbPage.id }"/>
	<table style="width: 98%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 10%">
				<label class="Validform_label">
					下机克重:
				</label>
			</td>
			<td class="value"  style="width: 90%">
				<input id="xjkz" name="xjkz" value="${emkPbPage.xjkz }" type="text" datatype="d" style="width: 150px" class="inputxt"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机克重</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					单件所需时间:
				</label>
			</td>
			<td class="value" >
				<input id="djsxsj" name="djsxsj" value="${emkPbPage.djsxsj }" datatype="d"  type="text" style="width: 150px" class="inputxt"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件所需时间</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value" >
				<input id="dw" name="dw" value="${emkPbPage.dw eq null ? "秒":emkPbPage.dw}"  datatype="*" type="text" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					机台日产量:
				</label>
			</td>
			<td class="value" >
				<input id="jtrcl" name="jtrcl" value="${emkPbPage.jtrcl eq null ? '79200':emkPbPage.jtrcl }" datatype="n" type="text" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">机台日产量</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					前道损耗率:
				</label>
			</td>
			<td class="value" >
				<input id="qdhsl" name="qdhsl" value="${emkPbPage.qdhsl eq null ? "5": emkPbPage.qdhsl}"  datatype="d" type="text" style="width: 50px" class="inputxt" />%
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">前道损耗率</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					后道损耗率:
				</label>
			</td>
			<td class="value" >
				<input id="hdhsl" name="hdhsl"  value="${emkPbPage.hdhsl eq null ? "3": emkPbPage.hdhsl}"  datatype="d" type="text" style="width: 50px" class="inputxt" />%
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">后道损耗率</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
