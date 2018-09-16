<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>库存日记表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkStorageLogController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkStorageLogPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品ID:
							</label>
						</td>
						<td class="value">
						    <input id="proId" name="proId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.proId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								产品编号:
							</label>
						</td>
						<td class="value">
						    <input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.proNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								中文描述:
							</label>
						</td>
						<td class="value">
						    <input id="proZnName" name="proZnName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.proZnName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库前数量:
							</label>
						</td>
						<td class="value">
						    <input id="preTotal" name="preTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.preTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库前数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库后数量:
							</label>
						</td>
						<td class="value">
						    <input id="nextTotal" name="nextTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.nextTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库后数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品类型:
							</label>
						</td>
						<td class="value">
						    <input id="proTypeName" name="proTypeName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.proTypeName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库单号:
							</label>
						</td>
						<td class="value">
						    <input id="rkNo" name="rkNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkStorageLogPage.rkNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库单号</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/storagelog/emkStorageLog.js"></script>		
