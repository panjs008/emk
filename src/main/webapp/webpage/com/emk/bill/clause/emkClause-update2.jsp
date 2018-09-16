<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>合同条款表</title>
	<t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
        //编写自定义JS代码
        $(document).ready(
            function() {
                var width = window.top.document.body.offsetWidth*0.97;
                var height =window.top.document.body.offsetHeight*0.97;
                $("#cke_clauseContent_text").width(width);
                $("#cke_clauseContent_text").width(height);

            }
        );
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkClauseController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkClausePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="150px">
				<label class="Validform_label">
					条款编号:
				</label>
			</td>
			<td class="value">
				<input id="clauseNum" name="clauseNum" datatype="*" value="${emkClausePage.clauseNum }" type="text" style="width:90%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">条款编号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					内容描述:
				</label>
			</td>
			<td class="value">
					<t:ckeditor name="clauseContent" type="height:300" value="${emkClausePage.clauseContent }"></t:ckeditor>
					<%--<textarea id="clauseContent" datatype="*"  style="width:95%;height:80px" class="inputxt" rows="5" name="clauseContent"></textarea>--%>

		</tr>
			<%--<tr>
                <td align="right">
                    <label class="Validform_label">
                        备注:
                    </label>
                </td>
                <td class="value"><textarea id="remark" style="width:95%;height:30px" class="inputxt" rows="3" name="remark"></textarea>

            </tr>--%>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/bill/clause/emkClause.js"></script>
