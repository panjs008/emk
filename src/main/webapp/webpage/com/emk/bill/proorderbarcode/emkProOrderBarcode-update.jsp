<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>条码表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderBarcodeController.do?doUpdate" tiptype="1">
			<input id="id" name="id" type="hidden" value="${emkProOrderBarcodePage.id }"/>
			<input id="orderId" name="orderId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderBarcodePage.orderId}'/>

			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="type" field="type" typeGroupCode="barcode" datatype="*" defaultVal="${emkProOrderBarcodePage.type}" hasLabel="false" title="条码类型"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								颜色:
							</label>
						</td>
						<td class="value">
						    <input id="color" name="color" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderBarcodePage.color}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颜色</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								尺码:
							</label>
						</td>
						<td class="value">
						    <input id="size" name="size" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderBarcodePage.size}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								条码:
							</label>
						</td>
						<td class="value">
						    <input id="code" name="code" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderBarcodePage.code}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">条码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkProOrderBarcodePage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/proorderbarcode/emkProOrderBarcode.js"></script>		
