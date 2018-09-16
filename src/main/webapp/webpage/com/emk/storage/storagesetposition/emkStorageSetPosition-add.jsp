<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>库位表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkStorageSetPositionController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkStorageSetPositionPage.id }"/>
	  <input id="storageId" name="storageId" type="hidden" value="${param.storageId }" style="width: 150px" class="inputxt"  ignore="ignore" />

	  <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">

				<tr>
					<td align="right">
						<label class="Validform_label">
							位置代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">位置代码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							位置名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="positionName" name="positionName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">位置名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/storagesetposition/emkStorageSetPosition.js"></script>		
