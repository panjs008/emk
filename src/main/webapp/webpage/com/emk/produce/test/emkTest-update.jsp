<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>测试申请表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			$("#testJd").val(${emkTestPage.testJd});
		});
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#testContentUrl").val(d.attributes.url);
			$("#testContent").val(d.attributes.name);
		}

		function downloadFile() {
			var url   = '${emkTestPage.testContentUrl }';
			window.location.href = url;
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkTestController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkTestPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkTestPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkTestPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo"  type="text" value="${emkTestPage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					测试申请表编号:
				</label>
			</td>
			<td class="value" >
				<input id="cssqdh" name="cssqdh" value="${emkTestPage.cssqdh }" type="text" style="width: 150px" class="inputxt"  datatype="*" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试申请表编号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<input id="businesser" name="businesser" value="${emkTestPage.businesser }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkTestPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" value="${emkTestPage.sampleNo }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试申请提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate"  readonly value="${emkTestPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试申请提交日期</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<input id="tracer" name="tracer" readonly type="text" value="${emkTestPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkTestPage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkTestPage.sampleNoDesc }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试机构名称:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="testCode" field="testCode" typeGroupCode="orgCode" datatype="*" defaultVal="${emkTestPage.testCode }" hasLabel="false" title="机构名称"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试机构名称</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="cusName" name="cusName" readonly type="text" value="${emkTestPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>

			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkTestPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceNum" name="produceNum" value="${emkTestPage.produceNum }" type="text" style="width: 150px" class="inputxt"  datatype="*" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试种类:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="testType" field="testType" typeGroupCode="testtype" datatype="*" defaultVal="${emkTestPage.testType }" hasLabel="false" title="测试种类"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试种类</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">
			</td>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" style="width: 150px" value="${emkTestPage.total }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
			<td class="value" colspan="2">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试样品要求:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="testRequired" name="testRequired" value="${emkTestPage.testRequired }" type="text" style="width: 80%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试样品要求</label>
			</td>
		</tr>
		<tr id="dgrImageDiv">
			<td align="right">
				<label class="Validform_label">
					测试内容:
				</label>
			</td>
			<td class="value">
				<input id="testContent" name="testContent" value="${emkTestPage.testContent }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<c:if test="${emkTestPage.testContentUrl ne ''}">[<a href="emkTestController.do?dowaLoadFile&id=${emkTestPage.id}&testContentUrl=${emkTestPage.testContentUrl }">下载</a>]</c:if>
				<input id="testContentUrl" name="testContentUrl" value="${emkTestPage.testContentUrl }" type="hidden" />
			</td>
			<td class="value" colspan="4">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.doc;*.xls;*.docx;*.xlsx;*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
			</td>
		</tr>
		<tr>
			<td colspan="6" id="instructionfile" class="value">
			</td>
		</tr>
		<%--<tr>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<input id="developer" name="developer" readonly value="${emkTestPage.developer }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  value="${emkTestPage.developerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>--%>







		<tr>
			<td align="right">
				<label class="Validform_label">
					报告有效期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="limitDate" name="limitDate" value="${emkTestPage.limitDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报告有效期</label>
			</td>
			</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					大货交期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="ysDate" name="ysDate" readonly value="${emkTestPage.ysDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大货交期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					距离交期剩余天数:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="levelDays" name="levelDays" value="${emkTestPage.levelDays }" datatype="n"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距离交期剩余天数</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					测试进度:
				</label>
			</td>
			<td class="value" colspan="5">
				<select id="testJd" name="testJd">
					<option value="0">样品</option>
					<option value="1">寄出测试</option>
					<option value="2">测试机构正在测试</option>
					<option value="3">付款</option>
					<option value="4">收到报告</option>
					<option value="5">重测</option>
					<option value="6">已通过</option>

				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试进度</label>
			</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试样通知日期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="testNoticDate" name="testNoticDate" value="${emkTestPage.testNoticDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试样通知日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					寄出测试日期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="sendDate" name="sendDate" readonly value="${emkTestPage.sendDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">寄出测试日期</label>
			</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试报告收到日期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="recevieDate" name="recevieDate" value="${emkTestPage.recevieDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试报告收到日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试报告号:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="testNo" name="testNo" value="${emkTestPage.testNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试报告号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试结果:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="testResult" name="testResult" value="${emkTestPage.testResult }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试结果</label>
			</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试费用金额:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="testMoney" name="testMoney" datatype="d" value="${emkTestPage.testMoney }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试费用金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试费付款状态:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="testState" name="testState"  value="${emkTestPage.testState }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试费付款状态</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","90px");
	});
</script>