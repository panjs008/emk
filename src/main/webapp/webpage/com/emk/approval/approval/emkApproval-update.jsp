<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>审批业务表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkApprovalController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkApprovalPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								申请人ID:
							</label>
						</td>
						<td class="value">
						    <input id="commitId" name="commitId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.commitId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请人ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								提交时间:
							</label>
						</td>
						<td class="value">
						    <input id="commitTime" name="commitTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.commitTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提交时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工单类型:
							</label>
						</td>
						<td class="value">
						    <input id="type" name="type" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkApprovalPage.type}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								当前节点名称:
							</label>
						</td>
						<td class="value">
						    <input id="processName" name="processName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.processName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当前节点名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								当前节点代码:
							</label>
						</td>
						<td class="value">
						    <input id="processNode" name="processNode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.processNode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当前节点代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						    <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkApprovalPage.status}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核人:
							</label>
						</td>
						<td class="value">
						    <input id="bpmSher" name="bpmSher" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.bpmSher}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审核状态:
							</label>
						</td>
						<td class="value">
						    <input id="bpmStatus" name="bpmStatus" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.bpmStatus}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核人ID:
							</label>
						</td>
						<td class="value">
						    <input id="bpmSherId" name="bpmSherId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.bpmSherId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核人ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审核时间:
							</label>
						</td>
						<td class="value">
						    <input id="bpmDate" name="bpmDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.bpmDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单号:
							</label>
						</td>
						<td class="value">
						    <input id="workNum" name="workNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkApprovalPage.workNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单号</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/approval/approval/emkApproval.js"></script>		
