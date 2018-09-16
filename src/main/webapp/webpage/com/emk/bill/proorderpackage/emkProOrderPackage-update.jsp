<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>包装明细表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderPackageController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkProOrderPackagePage.id }"/>
			<input id="orderId" name="orderId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderPackagePage.orderId}'/>

			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="packageType" field="packageType" typeGroupCode="package" datatype="*" defaultVal="${emkProOrderPackagePage.packageType }"  hasLabel="false" title="条码类型"></t:dictSelect>
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
						    <input id="color" name="color" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderPackagePage.color}'/>
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
						    <input id="size" name="size" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderPackagePage.size}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								箱内数量:
							</label>
						</td>
						<td class="value">
						    <input id="inboxTotal" name="inboxTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkProOrderPackagePage.inboxTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">箱内数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								箱数:
							</label>
						</td>
						<td class="value">
						    <input id="boxTotal" name="boxTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkProOrderPackagePage.boxTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">箱数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总数量:
							</label>
						</td>
						<td class="value">
						    <input id="sumTotal" name="sumTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkProOrderPackagePage.sumTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总数量</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/proorderpackage/emkProOrderPackage.js"></script>		
