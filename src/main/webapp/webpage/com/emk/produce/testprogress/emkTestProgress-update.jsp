<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>测试进度更新表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#testContentUrl").val(d.attributes.url);
			$("#testContent").val(d.attributes.name);
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkTestProgressController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkTestProgressPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					进度更新日期:
				</label>
			</td>
			<td class="value">
				<input id="updateDate" name="updateDate" readonly value="${emkTestProgressPage.updateDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">进度更新日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					更新操作员:
				</label>
			</td>
			<td class="value">
				<input id="createName" name="createName"  value="${emkTestProgressPage.createName }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">更新操作员</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkTestProgressPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkTestProgressPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkTestProgressPage.businesser }"/>
				<input id="businesserName" name="businesserName"  type="hidden" value="${emkTestProgressPage.businesserName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkTestProgressPage.tracer }"/>
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkTestProgressPage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkTestProgressPage.developer }"/>
				<input id="developerName" name="developerName"  type="hidden" value="${emkTestProgressPage.developerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
	</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkTestProgressController.do?detailMxList&costId=${emkTestProgressPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
</t:formvalid>
</body>
