<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工单管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkWorkOrderController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkWorkOrderPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							询盘单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="askNo" name="askNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘单号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否先打样:
						</label>
					</td>
					<td class="value">
					     	 <input id="isPrint" name="isPrint" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否先打样</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							询盘时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="askDate" name="askDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							工单环节:
						</label>
					</td>
					<td class="value">
					     	 <input id="processName" name="processName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单环节</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							询盘处理人:
						</label>
					</td>
					<td class="value">
					     	 <input id="askWorkUser" name="askWorkUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘处理人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							询盘处理人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="askWorkUserId" name="askWorkUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘处理人ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							询盘处理时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="askWorkDate" name="askWorkDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘处理时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							打样单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleNum" name="sampleNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样单号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							打样处理人:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleUser" name="sampleUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							打样处理时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleDate" name="sampleDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							样品质检人:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleCheckUser" name="sampleCheckUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							样品质检时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleCheckDate" name="sampleCheckDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							样品质检处理意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleCheckAdvice" name="sampleCheckAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检处理意见</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							打样处理意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleAdvice" name="sampleAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理意见</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							样品质检是否通过:
						</label>
					</td>
					<td class="value">
					     	 <input id="isPass" name="isPass" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检是否通过</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							录单人:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderUser" name="orderUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">录单人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录单人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderUserId" name="orderUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">录单人ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							订单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入单时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderDate" name="orderDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入单时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							入单处理内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderAdvice" name="orderAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入单处理内容</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							样品质检人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleCheckUserId" name="sampleCheckUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检人ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							打样处理人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleUserId" name="sampleUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理人ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同处理人:
						</label>
					</td>
					<td class="value">
					     	 <input id="htUser" name="htUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							合同处理人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="htUserId" name="htUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理人ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="htNo" name="htNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同单号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							合同处理内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="htAdvice" name="htAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理内容</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产处理人:
						</label>
					</td>
					<td class="value">
					     	 <input id="produceUser" name="produceUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产处理人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							生产处理人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="produceUserId" name="produceUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产处理人ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产处理时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="produceDate" name="produceDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产处理时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							合同处理时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="htDate" name="htDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产处理内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="produceAdvice" name="produceAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产处理内容</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							质检人:
						</label>
					</td>
					<td class="value">
					     	 <input id="checkUser" name="checkUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							质检人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="checkUserId" name="checkUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检人ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							质检日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="checkDate" name="checkDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							质检处理意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="checkAdvice" name="checkAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检处理意见</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否通过质检:
						</label>
					</td>
					<td class="value">
					     	 <input id="isPassCheck" name="isPassCheck" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否通过质检</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出货人:
						</label>
					</td>
					<td class="value">
					     	 <input id="outUser" name="outUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							出货人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="outUserId" name="outUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货人ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出货时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="outDate" name="outDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							出货处理意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="outAdvice" name="outAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货处理意见</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							财务处理人:
						</label>
					</td>
					<td class="value">
					     	 <input id="caiwuUser" name="caiwuUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							财务处理人ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="caiwuUserId" name="caiwuUserId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理人ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							财务处理时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="caiwuDate" name="caiwuDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							财务处理意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="caiwuAdvice" name="caiwuAdvice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理意见</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工单状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							工单编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="workNo" name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单编号</label>
						</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/workorder/workorder/emkWorkOrder.js"></script>		
