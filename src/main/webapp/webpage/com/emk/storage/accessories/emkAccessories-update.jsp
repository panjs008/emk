<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>缝制辅料需求开发单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			BindSelect("cgerId","ymkCustomController.do?findUserList&userKey=采购员",1,"${emkAccessoriesPage.cgerName},${emkAccessoriesPage.cger}");

			$("#cgerId").change(function(){
				var itemarr = $("#cgerId").val().split(","); //字符分割
				$("#cger").val(itemarr[0]);
				$("#cgerName").val(itemarr[1]);
			});

		});



		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html(d.attributes.name);
			$("#uploadimg0").attr('src',d.attributes.url);

		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkAccessoriesController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkAccessoriesPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkAccessoriesPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkAccessoriesPage.cusName }" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkAccessoriesPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkAccessoriesPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkAccessoriesPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkAccessoriesPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					缝制辅料需求单号:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="materialNo" name="materialNo" type="text" value="${emkAccessoriesPage.materialNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制辅料需求单号</label>
			</td>
		</tr>

		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkAccessoriesPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkAccessoriesPage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkAccessoriesPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  value="${emkAccessoriesPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					采购员:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cger" name="cger" type="hidden" value="${emkAccessoriesPage.cger }"/>
				<input id="cgerName" name="cgerName" type="hidden" value="${emkAccessoriesPage.cgerName }"/>
				<select class="form-control select2" id="cgerId"   >
					<option value=''>请选择</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">采购员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkAccessoriesPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkAccessoriesPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkAccessoriesPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkAccessoriesPage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkAccessoriesPage.kdDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="dhjqDate" name="dhjqDate" readonly value="${emkAccessoriesPage.dhjqDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime0})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>

		</tr>

		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" value="${emkAccessoriesPage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkAccessoriesPage.customSampleUrl eq '' ? 'images/bjlogo.png':emkAccessoriesPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkAccessoriesPage.customSampleUrl }')">${emkAccessoriesPage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkAccessoriesPage.customSampleUrl }" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkAccessoriesPage.gyzl}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					样品交期:
				</label>
			</td>
			<td class="value">
				<input id="ysDate" name="ypjqDate" readonly value="${emkAccessoriesPage.ypjqDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品交期</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					距样品交期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="leaveYpjqDays" readonly value="${emkAccessoriesPage.leaveYpjqDays }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leaveDhjqDays" name="leaveDhjqDays" readonly value="${emkAccessoriesPage.leaveDhjqDays }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version"   type="text" value="${emkAccessoriesPage.version }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>

		</tr>
		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否已有订单:
				</label>
			</td>
			<td class="value">
				<input name="isOrder" type="radio"  datatype="*" <c:if test="${emkAccessoriesPage.isOrder eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isOrder" type="radio" datatype="*"  <c:if test="${emkAccessoriesPage.isOrder eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否已有订单</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否现有适合原料面料:
				</label>
			</td>
			<td class="value" >
				<input name="isHave" type="radio" datatype="*" <c:if test="${emkAccessoriesPage.isHave eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHave" type="radio" datatype="*"  <c:if test="${emkAccessoriesPage.isHave eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否现有适合原料面料</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					需求开发交期:
				</label>
			</td>
			<td class="value">
				<input id="requiredJqDate" name="requiredJqDate" value="${emkAccessoriesPage.requiredJqDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">需求开发交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					完成时间剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="leaveFinishDays" name="leaveFinishDays"  value="${emkAccessoriesPage.leaveFinishDays }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">完成时间剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkAccessoriesPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					打样原因:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleReason" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleReason">${emkAccessoriesPage.sampleReason }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样原因</label>
			</td>
		</tr>

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