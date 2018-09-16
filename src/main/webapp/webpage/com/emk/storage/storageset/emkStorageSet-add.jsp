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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkStorageSetController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkStorageSetPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="150px;">
						<label class="Validform_label">
							仓库名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="storageName" name="storageName" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
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
					     	 <input id="areaCode" name="areaCode" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
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
					<td class="value"><textarea id="storageAddress" style="width:95%;height:80px" class="inputxt" rows="5" name="storageAddress"></textarea>

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
					<td class="value"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/storageset/emkStorageSet.js"></script>		
