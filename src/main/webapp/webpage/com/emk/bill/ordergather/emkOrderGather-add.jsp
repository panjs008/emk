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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderGatherController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkOrderGatherPage.id }"/>
	  <input id="orderId" name="orderId" type="hidden" value="${order.id}"/>
	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

				<tr>
					<td align="right" width="200px;">
						<label class="Validform_label">
							货前后:
						</label>
					</td>
					<td class="value">
						<select id="hpreNext" name="hpreNext">
							<option value="0">货前</option>
							<option value="1">货后</option>
						</select>
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
					     	 <input id="gatherType" name="gatherType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
						<t:dictSelect id="dateType" field="dateType" typeGroupCode="rqfs" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
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
					     	 <input id="dopute" name="dopute" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="money" name="money" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					<td class="value"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark"></textarea>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/ordergather/emkOrderGather.js"></script>		
