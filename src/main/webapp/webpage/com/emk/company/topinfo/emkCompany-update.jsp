<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>公司抬头信息表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
        //编写自定义JS代码
        $(function(){
            $("#isChecked").val("${emkCompanyPage.isChecked}");

        });
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkCompanyController.do?doUpdate" >
	<input id="id" name="id" type="hidden" value="${emkCompanyPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					中文公司:
				</label>
			</td>
			<td class="value">
				<input id="znName" name="znName" value="${emkCompanyPage.znName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">中文公司</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					外文公司:
				</label>
			</td>
			<td class="value">
				<input id="enName" name="enName" value="${emkCompanyPage.enName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">外文公司</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					中文地址:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="znAddress" name="znAddress" value="${emkCompanyPage.znAddress }" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">中文地址</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					外文地址:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="enAddress" name="enAddress" value="${emkCompanyPage.enAddress }" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">外文地址</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					办公电话:
				</label>
			</td>
			<td class="value">
				<input id="workTel" name="workTel" value="${emkCompanyPage.workTel }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公电话</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					传真:
				</label>
			</td>
			<td class="value">
				<input id="fax" name="fax" type="text" value="${emkCompanyPage.fax }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">传真</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					统一社会信用代码:
				</label>
			</td>
			<td class="value">
				<input id="tyshCode" name="tyshCode" value="${emkCompanyPage.tyshCode }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">统一社会信用代码</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					海关认证10代码:
				</label>
			</td>
			<td class="value">
				<input id="hgrzCode" name="hgrzCode" value="${emkCompanyPage.hgrzCode }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">海关认证10代码</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="remark" datatype="*"  style="width:90%;height:60px" class="inputxt" rows="3" name="remark">${emkCompanyPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					默认:
				</label>
			</td>
			<td class="value" colspan="3">
				<select id="isChecked" name="isChecked" style="width: 100px;" >
					<option value="是">是</option>
					<option value="否" selected>否</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">默认</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/company/topinfo/emkCompany.js"></script>
