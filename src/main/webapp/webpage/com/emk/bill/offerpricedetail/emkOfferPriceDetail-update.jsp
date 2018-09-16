<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报价方案明细</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkOfferPriceDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkOfferPriceDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								方案ID:
							</label>
						</td>
						<td class="value">
						    <input id="offerPriceId" name="offerPriceId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOfferPriceDetailPage.offerPriceId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">方案ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物料ID:
							</label>
						</td>
						<td class="value">
						    <input id="proId" name="proId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOfferPriceDetailPage.proId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物料编号:
							</label>
						</td>
						<td class="value">
						    <input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOfferPriceDetailPage.proNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								规格:
							</label>
						</td>
						<td class="value">
						    <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOfferPriceDetailPage.brand}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="signTotal" name="signTotal" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkOfferPriceDetailPage.signTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
						    <input id="signPrice" name="signPrice" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkOfferPriceDetailPage.signPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单价</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单位:
							</label>
						</td>
						<td class="value">
						    <input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOfferPriceDetailPage.unit}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOfferPriceDetailPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/offerpricedetail/emkOfferPriceDetail.js"></script>		
