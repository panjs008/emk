<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>仓库表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkStorageSetController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkStorageSetPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								仓库名称:
							</label>
						</td>
						<td class="value">
						    <input id="storageName" name="storageName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageSetPage.storageName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								地区:
							</label>
						</td>
						<td class="value">
						    <input id="areaCode" name="areaCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageSetPage.areaCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								仓库地址:
							</label>
						</td>
						<td class="value">
						    <input id="storageAddress" name="storageAddress" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageSetPage.storageAddress}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageSetPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/storageset/emkStorageSet.js"></script>		
