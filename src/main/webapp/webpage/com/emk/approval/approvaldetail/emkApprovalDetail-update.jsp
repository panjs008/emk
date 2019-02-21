<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>审批业务处理表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkApprovalDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkApprovalDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属审批业务ID:
							</label>
						</td>
						<td class="value">
						    <input id="approvalId" name="approvalId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalDetailPage.approvalId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属审批业务ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								表单ID:
							</label>
						</td>
						<td class="value">
						    <input id="formId" name="formId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalDetailPage.formId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">表单ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批人ID:
							</label>
						</td>
						<td class="value">
						    <input id="approveUserId" name="approveUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalDetailPage.approveUserId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批人ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核状态:
							</label>
						</td>
						<td class="value">
						    <input id="approveStatus" name="approveStatus" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkApprovalDetailPage.approveStatus}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批时间:
							</label>
						</td>
						<td class="value">
						    <input id="approveDate" name="approveDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalDetailPage.approveDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批意见:
							</label>
						</td>
						<td class="value">
						    <input id="approveAdvice" name="approveAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalDetailPage.approveAdvice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批意见</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/approval/approvaldetail/emkApprovalDetail.js"></script>		
