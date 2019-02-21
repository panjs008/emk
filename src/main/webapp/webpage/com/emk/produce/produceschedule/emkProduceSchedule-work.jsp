<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工单处理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

</head>
<body>
<div id="timeDiv" title="流程时间轴" style="width:100%;height:360px;overflow:hidden" >
	<iframe scrolling="no" id="processFrm" frameborder="0"  src="emkProduceScheduleController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
	</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProduceScheduleController.do?doSubmit"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${param.id }"/>
	<input id="kdDate" name="kdDate" type="hidden"  value="${emkProduceSchedule.kdDate }" />

	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'ssyTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						试身样状态:
					</label>
				</td>
				<td class="value" >
					<input id="ssyzt" name="ssyzt"  type="text" value="${emkProduceSchedulePage.ssyzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">试身样状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						试身样最晚确认时间:
					</label>
				</td>
				<td class="value">
					<input id="ssyDate" name="ssyDate" value="${emkProduceSchedulePage.ssyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeSsy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">试身样最晚确认时间</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						试身样距离剩余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leavelSsy" name="leavelSsy" readonly value="${emkProduceSchedulePage.leavelSsy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">试身样距离剩余天数</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'cqyTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						产前样状态:
					</label>
				</td>
				<td class="value" >
					<input id="cqyzt" name="cqyzt"  type="text" value="${emkProduceSchedulePage.cqyzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产前样状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						产前样最晚确认时间:
					</label>
				</td>
				<td class="value" >
					<input id="cqyDate" name="cqyDate" value="${emkProduceSchedulePage.cqyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeCqy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产前样最晚确认时间</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						产前样距离剩余天数:
					</label>
				</td>
				<td class="value">
					<input id="leavelCq" name="leavelCq" readonly value="${emkProduceSchedulePage.leavelCq }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产前样距离剩余天数</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'syTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						色样状态:
					</label>
				</td>
				<td class="value" >
					<input id="syzt" name="syzt"  type="text" value="${emkProduceSchedulePage.syzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">色样状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						色样最晚确认时间:
					</label>
				</td>
				<td class="value" >
					<input id="syDate" name="syDate" value="${emkProduceSchedulePage.syDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeSy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">色样最晚确认时间</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						色样距离剩余天数:
					</label>
				</td>
				<td class="value">
					<input id="leavelSy" name="leavelSy" readonly value="${emkProduceSchedulePage.leavelSy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">色样距离剩余天数</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'cyTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						船样状态:
					</label>
				</td>
				<td class="value" >
					<input id="cyzt" name="cyzt"  type="text" value="${emkProduceSchedulePage.cyzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">船样状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						船样最晚确认时间:
					</label>
				</td>
				<td class="value" >
					<input id="cyDate" name="cyDate" value="${emkProduceSchedulePage.cyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeCy})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">船样最晚确认时间</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						船样距离剩余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leavelCy" name="leavelCy" readonly value="${emkProduceSchedulePage.leavelCy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">船样距离剩余天数</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'testTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						测试通过日期:
					</label>
				</td>
				<td class="value" >
					<input id="testPass" name="testPass"  type="text" value="${emkProduceSchedulePage.testPass }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})" style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">测试通过日期</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						最晚通过测试日期:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="testPassDate" name="testPassDate" value="${emkProduceSchedulePage.testPassDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">最晚通过测试日期</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'meetingTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						产前会议:
					</label>
				</td>
				<td class="value" >
					<input id="cqhy" name="cqhy"  type="text" value="${emkProduceSchedulePage.cqhy }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})" style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产前会议</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						产前会议最晚日期:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="cqhyDate" name="cqhyDate" value="${emkProduceSchedulePage.cqhyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产前会议最晚日期</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'ylTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						原料布料状态:
					</label>
				</td>
				<td class="value" >
					<input id="ylblState" name="ylblState"  type="text" value="${emkProduceSchedulePage.ylblState }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">原料布料状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						原料布料到厂日期:
					</label>
				</td>
				<td class="value" >
					<input id="ylblLimitDate" name="ylblLimitDate" value="${emkProduceSchedulePage.ylblLimitDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeP})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">原料布料到厂日期</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						距原料到厂剩余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leavelYlblDay" name="leavelYlblDay" value="${emkProduceSchedulePage.leavelYlblDay }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">距原料到厂剩余天数</label>
				</td>
			</tr>

			<tr>
				<td align="right" >
					<label class="Validform_label">
						缝制辅料状态:
					</label>
				</td>
				<td class="value" >
					<input id="fzblState" name="fzblState" value="${emkProduceSchedulePage.fzblState }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">缝制辅料状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						缝制辅料到厂日期:
					</label>
				</td>
				<td class="value" >
					<input id="fzblLimitDate" name="fzblLimitDate" value="${emkProduceSchedulePage.fzblLimitDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeP2})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">缝制辅料到厂日期</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						距缝制到厂剩余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leavelFzblDay" name="leavelFzblDay" value="${emkProduceSchedulePage.leavelFzblDay }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">距缝制到厂剩余天数</label>
				</td>
			</tr>

			<tr>
				<td align="right" >
					<label class="Validform_label">
						包装辅料状态:
					</label>
				</td>
				<td class="value" >
					<input id="bzblState" name="bzblState" value="${emkProduceSchedulePage.bzblState }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装辅料状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						包装辅料到厂日期:
					</label>
				</td>
				<td class="value" >
					<input id="bzblLimitDate" name="bzblLimitDate" value="${emkProduceSchedulePage.bzblLimitDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTimeP3})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装辅料到厂日期</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						距包装到厂剩余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leavelBzblDay" name="leavelBzblDay" value="${emkProduceSchedulePage.leavelBzblDay }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">距包装到厂剩余天数</label>
				</td>
			</tr>

		</c:if>

		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'ranTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						染色状态:
					</label>
				</td>
				<td class="value" >
					<select  id="ranState" name="ranState">
						<option value="0">未完成</option>
						<option value="1">已完成</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">染色状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						染色已完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="ranFinish" name="ranFinish" value="${emkProduceSchedulePage.ranFinish }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">染色已完成数量</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						染色未完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="ranUnfinish" name="ranUnfinish" value="${emkProduceSchedulePage.ranUnfinish }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">染色未完成数量</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'caiTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						裁剪状态:
					</label>
				</td>
				<td class="value" >
					<select  id="caiState" name="caiState">
						<option value="0">未完成</option>
						<option value="1">已完成</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">裁剪状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						裁剪已完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="caiFinish" name="cFinish" datatype="n" value="${emkProduceSchedulePage.caiFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">裁剪已完成数量</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						裁剪未完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="caiUnfinish" name="cUnfinish" datatype="n" value="${emkProduceSchedulePage.caiUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">染色未完成数量</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'fengTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						缝制状态:
					</label>
				</td>
				<td class="value" >
					<select  id="fzState" name="fzState">
						<option value="0">未完成</option>
						<option value="1">已完成</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">缝制状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						缝制已完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="fzFinish" name="fzFinish" datatype="n" value="${emkProduceSchedulePage.fzFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">缝制已完成数量</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						缝制未完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="fzUnfinish" name="fzUnfinish" datatype="n" value="${emkProduceSchedulePage.fzUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">缝制未完成数量</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'zqjcTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						中期检查日期:
					</label>
				</td>
				<td class="value" >
					<input id="zcrq" name="zcrq"  type="text" value="${emkProduceSchedulePage.zcrq }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">中期检查日期</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						中期检查最晚日期:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="zcrqDate" name="zcrqDate" value="${emkProduceSchedulePage.zcrqDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">中期检查最晚日期</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'btTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						烫标状态:
					</label>
				</td>
				<td class="value" >
					<select  id="btState" name="btState">
						<option value="0">未完成</option>
						<option value="1">已完成</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装辅料状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						烫标已完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="btFinish" name="btFinish" datatype="n" value="${emkProduceSchedulePage.btFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">烫标已完成数量</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						烫标未完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="btUnfinish" name="btUnfinish" datatype="n" value="${emkProduceSchedulePage.btUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">烫标未完成数量</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'ztTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						整烫状态:
					</label>
				</td>
				<td class="value" >
					<select  id="ztState" name="ztState">
						<option value="0">未完成</option>
						<option value="1">已完成</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装辅料状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						整烫已完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="ztFinish" name="ztFinish" datatype="n" value="${emkProduceSchedulePage.ztFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">整烫已完成数量</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						整烫未完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="ztUnfinish" name="ztUnfinish" datatype="n" value="${emkProduceSchedulePage.ztUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">整烫未完成数量</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'bzTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						包装状态:
					</label>
				</td>
				<td class="value" >
					<select  id="bzState" name="bzState">
						<option value="0">未完成</option>
						<option value="1">已完成</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装辅料状态</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						包装已完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="bzFinish" name="bzFinish" datatype="n" type="text" value="${emkProduceSchedulePage.bzFinish }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装已完成数量</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						包装未完成数量:
					</label>
				</td>
				<td class="value" >
					<input id="bzUnfinish" name="bzUnfinish" datatype="n" type="text" value="${emkProduceSchedulePage.bzUnfinish }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">包装未完成数量</label>
				</td>
			</tr>

		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'wqTask')}">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						尾查日期:
					</label>
				</td>
				<td class="value" >
					<input id="wcrq" name="wcrq"  type="text" value="${emkProduceSchedulePage.wcrq }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />

					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">尾查日期</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						尾查最晚日期:
					</label>
				</td>
				<td class="value" colspan="3">

					<input id="wcrqDate" name="wcrqDate" value="${emkProduceSchedulePage.wcrqDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">尾查最晚日期</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${orderFinish eq '0' && (orderPorcess.taskDefinitionKey eq 'checkTask')}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否同意:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="isPass" type="radio" datatype="*" <c:if test="${emkProduceScheduleEntity.isPass eq '0'}">checked="true"</c:if> value="0">
					是
					<input name="isPass" type="radio" datatype="*"  <c:if test="${emkProduceScheduleEntity.isPass eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否同意</label>
				</td>
			</tr>
		</c:if>
		<%--<tr id="chooseUser">
			<td align="right" width="150px" valign="middle">
				<label class="Validform_label">
					指定下一步操作人:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="realName" name="realName" type="text" readonly style="width: 300px" class="inputxt" >
				<input name="userName"   type="hidden"  id="userName" type="text"  />
				<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelectCw" name="userList1" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
			</td>
		</tr>--%>

		<tr>
			<td align="right" width="150px" valign="middle">
				<label class="Validform_label">
					处理意见:
				</label>
			</td>
			<td class="value" colspan="5"><textarea datatype="*" id="leadAdvice" style="width:95%;height:80px" class="inputxt" rows="5" name="leadAdvice"></textarea>
			</td>
		</tr>

</table>
</t:formvalid>

</body>
</html>