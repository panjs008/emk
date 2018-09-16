<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单条款表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderClauseController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkOrderClausePage.id }"/>
	  <input id="orderId" name="orderId" type="hidden" value="${order.id}"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="150px">
						<label class="Validform_label">
							条款编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="clauseNum" name="clauseNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">条款编号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容描述:
						</label>
					</td>
					<td class="value"><textarea id="clauseContent" style="width:95%;height:80px" class="inputxt" rows="5" name="clauseContent"></textarea>

				</tr>
				<%--<tr>
					<td align="right">
						<label class="Validform_label">
							订单ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单ID</label>
						</td>
				</tr>--%>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/orderclause/emkOrderClause.js"></script>		
