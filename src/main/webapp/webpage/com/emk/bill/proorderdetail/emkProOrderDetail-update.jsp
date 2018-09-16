<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>生产订单明细</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkProOrderDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单ID:
							</label>
						</td>
						<td class="value">
						    <input id="proOrderId" name="proOrderId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.proOrderId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								款号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.sampleNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								尺码:
							</label>
						</td>
						<td class="value">
						    <input id="size" name="size" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.size}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								颜色:
							</label>
						</td>
						<td class="value">
						    <input id="color" name="color" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.color}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颜色</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								价格:
							</label>
						</td>
						<td class="value">
						    <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkProOrderDetailPage.price}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">价格</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/proorderdetail/emkProOrderDetail.js"></script>		
