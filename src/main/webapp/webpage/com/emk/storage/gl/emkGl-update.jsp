<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkGlController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkGlPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								场地:
							</label>
						</td>
						<td class="value">
						    <input id="place" name="place" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.place}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">场地</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								运输费:
							</label>
						</td>
						<td class="value">
						    <input id="tranCost" name="tranCost" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.tranCost}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								设备:
							</label>
						</td>
						<td class="value">
						    <input id="equip" name="equip" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.equip}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">设备</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								管理费:
							</label>
						</td>
						<td class="value">
						    <input id="glf" name="glf" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.glf}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">管理费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电费:
							</label>
						</td>
						<td class="value">
						    <input id="dianfei" name="dianfei" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.dianfei}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								车辆使用费:
							</label>
						</td>
						<td class="value">
						    <input id="carCost" name="carCost" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.carCost}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车辆使用费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								水费:
							</label>
						</td>
						<td class="value">
						    <input id="waterCost" name="waterCost" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.waterCost}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">水费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								其他:
							</label>
						</td>
						<td class="value">
						    <input id="other" name="other" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.other}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">其他</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								报价单ID:
							</label>
						</td>
						<td class="value">
						    <input id="priceId" name="priceId" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkGlPage.priceId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报价单ID</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/gl/emkGl.js"></script>		
