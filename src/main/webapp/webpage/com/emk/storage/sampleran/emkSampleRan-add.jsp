<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>样品染色表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
	  function toDecimal(x) {
		  if(!x){
			  return x;
		  }
		  var result = parseFloat(x);
		  if (isNaN(result)) {
			  return x;
		  }
		  result = Math.round(x * 100) / 100;
		  return result;
	  }
	  function setChengben(){
		  if($("#price").val() != "" && $("#oneWeight").val() != ""){
			  $("#chengben").val(toDecimal(toDecimal(parseFloat($("#price").val())*parseFloat($("#oneWeight").val())/1000)));
		  }
	  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleRanController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkSampleRanPage.id }"/>
	  <input id="sampleId" name="sampleId" type="hidden" value="${param.sampleId }" />

	  <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							布面种类:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="buType" field="buType" typeGroupCode="bmzl" datatype="*" defaultVal="default" hasLabel="false" title="布面种类"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">布面种类</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							染色单价:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" onkeyup="setChengben();" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">染色单价</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
							<t:dictSelect id="unit" field="unit" typeGroupCode="units" datatype="*" defaultVal="default" hasLabel="false" title="单位"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单件克重:
						</label>
					</td>
					<td class="value">
					     	 <input id="oneWeight" name="oneWeight" type="text" readonly style="width: 150px" value="<fmt:formatNumber type="number" value="${emkSampleEntity.xjkz*(1+emkSampleEntity.qdshl+emkSampleEntity.hdshl)}" maxFractionDigits="2"/>" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单件克重</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							成本:
						</label>
					</td>
					<td class="value">
					     	 <input id="chengben" name="chengben" readonly type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成本</label>
						</td>
				</tr>

				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/sampleran/emkSampleRan.js"></script>		
