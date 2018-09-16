<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>待入库表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkWaitStorageController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkWaitStoragePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								待入库单号:
							</label>
						</td>
						<td class="value">
						    <input id="drkNo" name="drkNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWaitStoragePage.drkNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">待入库单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								菜单订单ID:
							</label>
						</td>
						<td class="value">
						    <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWaitStoragePage.orderId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">菜单订单ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								采购合同:
							</label>
						</td>
						<td class="value">
						    <input id="orderNum" name="orderNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWaitStoragePage.orderNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购合同</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWaitStoragePage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						    <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWaitStoragePage.state}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/waitstorage/emkWaitStorage.js"></script>		
