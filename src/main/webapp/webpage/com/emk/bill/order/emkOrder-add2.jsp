<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>采购合同表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkOrderPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							采购合同号:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderNum" name="orderNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购合同号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同币种:
						</label>
					</td>
					<td class="value">
					     	 <input id="htbz" name="htbz" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同币种</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							签约日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="signDate" name="signDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							签约地:
						</label>
					</td>
					<td class="value">
					     	 <input id="signPlace" name="signPlace" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约地</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							运输方式:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysfs" name="ysfs" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输方式</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							目的地:
						</label>
					</td>
					<td class="value">
					     	 <input id="mdd" name="mdd" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">目的地</label>
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
  <script src = "webpage/com/emk/bill/order/emkOrder.js"></script>		
