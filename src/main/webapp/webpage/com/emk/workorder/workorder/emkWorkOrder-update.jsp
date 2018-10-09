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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkWorkOrderController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkWorkOrderPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								工单编号:
							</label>
						</td>
						<td class="value">
							<input id="workNo" name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.workNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								工单日期:
							</label>
						</td>
						<td class="value">
							<input id="kdDate" name="kdDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.kdDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工单状态:
							</label>
						</td>
						<td class="value">
							<input id="state" name="state" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='<c:if test="${emkWorkOrderPage.state eq 0}">创建</c:if><c:if test="${emkWorkOrderPage.state eq 1}">处理中</c:if><c:if test="${emkWorkOrderPage.state eq 2}">完成</c:if>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工单状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否先打样:
							</label>
						</td>
						<td class="value">
							<input name="isPrint" type="radio"  datatype="*" <c:if test="${emkWorkOrderPage.isPrint eq '0'}">checked="true"</c:if> value="0">
							否
							&nbsp;&nbsp;<input name="isPrint"  type="radio" datatype="*"  <c:if test="${emkWorkOrderPage.isPrint eq '1'}">checked="true"</c:if> value="1">
							是
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否先打样</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								询盘单号:
							</label>
						</td>
						<td class="value">
						    <input id="askNo" name="askNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.askNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								询盘时间:
							</label>
						</td>
						<td class="value">
							<input id="askDate" name="askDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.askDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								询盘处理人:
							</label>
						</td>
						<td class="value">
							<input id="askWorkUserId" name="askWorkUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.askWorkUserId}'/>
							<input id="askWorkUser" name="askWorkUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.askWorkUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘处理人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								询盘处理时间:
							</label>
						</td>
						<td class="value">
							<input id="askWorkDate" name="askWorkDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.askWorkDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">询盘处理时间</label>
						</td>
					</tr>


					<tr>
						<td align="right">
							<label class="Validform_label">
								打样单号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNum" name="sampleNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.sampleNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								打样处理人:
							</label>
						</td>
						<td class="value">
							<input id="sampleUserId" name="sampleUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.sampleUserId}'/>
							<input id="sampleUser" name="sampleUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.sampleUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								打样处理时间:
							</label>
						</td>
						<td class="value" colspan="3">
						    <input id="sampleDate" name="sampleDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.sampleDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								打样处理意见:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="sampleAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleAdvice"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打样处理意见</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								样品质检人:
							</label>
						</td>
						<td class="value">
							<input id="sampleCheckUserId" name="sampleCheckUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.sampleCheckUserId}'/>
							<input id="sampleCheckUser" name="sampleCheckUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.sampleCheckUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								样品质检单号:
							</label>
						</td>
						<td class="value">
							<input id="sampleCheckNo" name="sampleCheckNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.sampleCheckNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检单号</label>
						</td>
						</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								样品质检时间:
							</label>
						</td>
						<td class="value">
						    <input id="sampleCheckDate" name="sampleCheckDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.sampleCheckDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								样品质检是否通过:
							</label>
						</td>
						<td class="value">
							<input name="isPass" type="radio"  datatype="*" <c:if test="${emkWorkOrderPage.isPass eq '2'}">checked="true"</c:if> value="0">
							否
							&nbsp;&nbsp;<input name="isPass"  type="radio" datatype="*"  <c:if test="${emkWorkOrderPage.isPass eq '1' || emkWorkOrderPage.isPass eq '0'}">checked="true"</c:if> value="1">
							是
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检是否通过</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								样品质检处理意见:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="sampleCheckAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleCheckAdvice"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检处理意见</label>
						</td>
					</tr>


					<tr>
						<td align="right">
							<label class="Validform_label">
								录单人:
							</label>
						</td>
						<td class="value">
							<input id="orderUserId" name="orderUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.orderUserId}'/>
							<input id="orderUser" name="orderUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.orderUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">录单人</label>
						</td>

						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.orderNo}'/>
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
						<td class="value" colspan="3">
						    <input id="orderDate" name="orderDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.orderDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入单时间</label>
						</td>

					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入单处理内容:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="orderAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="orderAdvice"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入单处理内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合同处理人:
							</label>
						</td>
						<td class="value">
							<input id="htUserId" name="htUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.htUserId}'/>

							<input id="htUser" name="htUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.htUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								合同单号:
							</label>
						</td>
						<td class="value">
							<input id="htNo" name="htNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.htNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合同处理时间:
							</label>
						</td>
						<td class="value" colspan="3">
							<input id="htDate" name="htDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.htDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合同处理内容:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="htAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="htAdvice"></textarea>
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
							<input id="produceUserId" name="produceUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.produceUserId}'/>
							<input id="produceUser" name="produceUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.produceUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产处理人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产单号:
							</label>
						</td>
						<td class="value">
							<input id="produceNo" name="produceNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.produceNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								生产处理时间:
							</label>
						</td>
						<td class="value" colspan="3">
						    <input id="produceDate" name="produceDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.produceDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产处理时间</label>
						</td>

					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								生产处理内容:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="produceAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="produceAdvice"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同处理内容</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								质检人:
							</label>
						</td>
						<td class="value">
							<input id="checkUserId" name="checkUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.checkUserId}'/>
							<input id="checkUser" name="checkUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.checkUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								质检单号:
							</label>
						</td>
						<td class="value">
							<input id="checkNo" name="checkNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.checkNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检单号</label>
						</td>
						</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								质检日期:
							</label>
						</td>
						<td class="value">
						    <input id="checkDate" name="checkDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.checkDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否通过质检:
							</label>
						</td>
						<td class="value">
							<input name="isPassCheck" type="radio"  datatype="*" <c:if test="${emkWorkOrderPage.isPassCheck eq '0'}">checked="true"</c:if> value="0">
							否
							&nbsp;&nbsp;<input name="isPrint"  type="radio" datatype="*"  <c:if test="${emkWorkOrderPage.isPassCheck eq '1'}">checked="true"</c:if> value="1">
							是
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否通过质检</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								质检处理意见:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="checkAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="checkAdvice"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质检处理意见</label>
						</td>
					</tr>
			
					<tr>
						<td align="right">
							<label class="Validform_label">
								出货人:
							</label>
						</td>
						<td class="value">
							<input id="outUserId" name="outUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.outUserId}'/>
							<input id="outUser" name="outUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.outUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出货通知单号:
							</label>
						</td>
						<td class="value">
							<input id="outNo" name="outNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.outNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货通知单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出货时间:
							</label>
						</td>
						<td class="value" colspan="3">
						    <input id="outDate" name="outDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.outDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货时间</label>
						</td>

					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出货处理意见:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="outAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="outAdvice"></textarea>
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
							<input id="caiwuUserId" name="caiwuUserId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.caiwuUserId}'/>
							<input id="caiwuUser" name="caiwuUser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkWorkOrderPage.caiwuUser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								财务处理时间:
							</label>
						</td>
						<td class="value">
							<input id="caiwuDate" name="caiwuDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${emkWorkOrderPage.caiwuDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理时间</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								财务处理意见:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea  id="caiwuAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="caiwuAdvice"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务处理意见</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/workorder/workorder/emkWorkOrder.js"></script>		
