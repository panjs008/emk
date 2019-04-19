<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>采购生产表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			$("#detailId").load("emkProduceScheduleController.do?orderMxList&proOrderId=${emkProduceSchedulePage.id }");
			$("#ranState").val("${emkProduceSchedulePage.ranState }");
			$("#caiState").val("${emkProduceSchedulePage.caiState }");
			$("#fzState").val("${emkProduceSchedulePage.fzState }");
			$("#btState").val("${emkProduceSchedulePage.btState }");
			$("#ztState").val("${emkProduceSchedulePage.ztState }");
			$("#bzState").val("${emkProduceSchedulePage.bzState }");
		});

		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html(d.attributes.name);
			$("#uploadimg0").attr('src',d.attributes.url);
		}



		function resetTrNum(tableId) {
			$tbody = $("#"+tableId+"");
			$tbody.find('>tr').each(function(i){
				$(':input, select', this).each(function(){
					var $this = $(this), name = $this.attr('name'), val = $this.val();
					if(name!=null){
						if (name.indexOf("#index#") >= 0){
							$this.attr("name",name.replace('#index#',i));
						}else{
							var s = name.indexOf("[");
							var e = name.indexOf("]");
							var new_name = name.substring(s+1,e);
							$this.attr("name",name.replace(new_name,i));
						}
					}
				});
			});
		}
		function setEndTimeP() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#ylblLimitDate").val();
			$("#leavelYlblDay").val(DateDiff(d1,d2));
		}

		function setEndTimeP2() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#fzblLimitDate").val();
			$("#leavelFzblDay").val(DateDiff(d1,d2));
		}

		function setEndTimeP3() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#bzblLimitDate").val();
			$("#leavelBzblDay").val(DateDiff(d1,d2));
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProduceScheduleController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkProduceSchedulePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceHtNum" name="produceHtNum" value="${emkProduceSchedulePage.produceHtNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo"  type="text" value="${emkProduceSchedulePage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkProduceSchedulePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkProduceSchedulePage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkProduceSchedulePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkProduceSchedulePage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>

		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" value="${emkProduceSchedulePage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkProduceSchedulePage.customSampleUrl eq '' ? 'images/bjlogo.png':emkProduceSchedulePage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkProduceSchedulePage.customSampleUrl }')">${emkProduceSchedulePage.customSample }</a>]
				<span id="customSampleId" ></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkProduceSchedulePage.customSampleUrl }" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.gysCode }"/>
				<input id="gys" name="gys" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.gys }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					操作员:
				</label>
			</td>
			<td class="value" >
				<input id="createName" name="createName"  readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.createName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">操作员</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.businesser }"/>
				<input id="businesserName" name="businesserName"  type="hidden" value="${emkProduceSchedulePage.businesserName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.tracer }"/>
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkProduceSchedulePage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.developer }"/>
				<input id="developerName" name="developerName"  type="hidden" value="${emkProduceSchedulePage.developerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.sampleNo }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkProduceSchedulePage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkProduceSchedulePage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.proTypeName }"/>
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.proType }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkProduceSchedulePage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" value="${emkProduceSchedulePage.outDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>
		</tr>

		<tr>
			<td colspan="6" id="instructionfile" class="value">
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkProduceSchedulePage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

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
			<td class="value" >
				<input id="ssyDate" name="ssyDate" value="${emkProduceSchedulePage.ssyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">试身样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					试身样距离剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelSsy" name="leavelYlblDay" value="${emkProduceSchedulePage.leavelSsy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">试身样距离剩余天数</label>
			</td>
		</tr>
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
				<input id="cqyDate" name="cqyDate" value="${emkProduceSchedulePage.cqyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产前样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					产前样距离剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelCq" name="leavelCq" value="${emkProduceSchedulePage.leavelCq }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产前样距离剩余天数</label>
			</td>
		</tr>
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
				<input id="syDate" name="syDate" value="${emkProduceSchedulePage.syDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					色样距离剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelSy" name="leavelSy" value="${emkProduceSchedulePage.leavelSy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样距离剩余天数</label>
			</td>
		</tr>
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
				<input id="cyDate" name="cyDate" value="${emkProduceSchedulePage.cyDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样最晚确认时间</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					船样距离剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelCy" name="leavelCy" value="${emkProduceSchedulePage.leavelCy }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样距离剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					产前会议:
				</label>
			</td>
			<td class="value" >
				<input id="cqhy" name="cqhy"  type="text" value="${emkProduceSchedulePage.cqhy }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
		<tr>
			<td align="right" >
				<label class="Validform_label">
					测试通过日期:
				</label>
			</td>
			<td class="value" >
				<input id="testPass" name="testPass"  type="text" value="${emkProduceSchedulePage.testPass }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
		<tr>
			<td align="right" >
				<label class="Validform_label">
					中期检查:
				</label>
			</td>
			<td class="value" >
				<input id="zcrq" name="zcrq"  type="text" value="${emkProduceSchedulePage.zcrq }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">中期检查</label>
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
		<tr>
			<td align="right" >
				<label class="Validform_label">
					尾查日期:
				</label>
			</td>
			<td class="value" >
				<input id="wcrq" name="wcrq"  type="text" value="${emkProduceSchedulePage.wcrq }" style="width: 150px" class="Wdate"  ignore="ignore" />
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
	</table>
	<div id="detailId" style="width: auto; height: 200px;" ><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>

	</div>
	<!-- 添加 产品明细 模版-->
	<table style="width:100%;display: none" cellpadding="0" cellspacing="2" border="0">
		<tbody id="add_jeecgOrderProduct_table_template">
		<tr>
			<td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
			<td align="left">
				<select name="orderMxList[#index#].color" style="width: 60%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
					<c:forEach items="${categoryEntityList}" var="category">
						<option value="${category.code}">${category.name}</option>
					</c:forEach>
				</select>
					<%--<input nullmsg="请输入颜色！" id="proName00"  errormsg="请输入颜色" name="orderMxList[#index#].color" maxlength="100" type="text" value=""
                           style="width: 70%;">--%>
			</td>
			<td align="left">
				<input id="size00" nullmsg="请输入尺码！"  errormsg="请输入尺码" name="orderMxList[#index#].size" maxlength="100" type="text" value=""
					   style="width: 60%;"></td>
			<td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入整数" name="orderMxList[#index#].signTotal" maxlength="100" type="text" value=""
									style="width: 60%;"></td>
		</tr>
		</tbody>

	</table>


</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");

		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 157,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>