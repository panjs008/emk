<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收款方式表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderGatherController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkOrderGatherPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.orderId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								货前后:
							</label>
						</td>
						<td class="value">
						     	 <input id="hpreNext" name="hpreNext" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.hpreNext}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">货前后</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收款方式:
							</label>
						</td>
						<td class="value">
						     	 <input id="gatherType" name="gatherType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.gatherType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款方式</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								日期方式:
							</label>
						</td>
						<td class="value">
						     	 <input id="dateType" name="dateType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.dateType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期方式</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								比例:
							</label>
						</td>
						<td class="value">
						     	 <input id="dopute" name="dopute" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.dopute}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">比例</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="money" name="money" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.money}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderGatherPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/ordergather/emkOrderGather.js"></script>		
