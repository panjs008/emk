<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>色样通知单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg0").attr('src',d.attributes.url);

		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleColorController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSampleColorPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					色样通知单号:
				</label>
			</td>
			<td class="value">
				<input id="sytzdbh" name="sytzdbh" type="text" value="${emkSampleColorPage.sytzdbh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					打样需求单号:
				</label>
			</td>
			<td class="value">
				<input id="xqdh" name="xqdh" type="text" value="${emkSampleColorPage.xqdh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单编号</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkSampleColorPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkSampleColorPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId" datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkSampleColorPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkSampleColorPage.businesserName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkSampleColorPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkSampleColorPage.cusName }"  style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,tracer,tracerName,businesser,businesserName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkSampleColorPage.tracer }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkSampleColorPage.tracerName }" />
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
				<input id="developer" name="developer" readonly value="${emkSampleColorPage.developer }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkSampleColorPage.developerName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkSampleColorPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkSampleColorPage.proTypeName }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkSampleColorPage.proType }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkSampleColorPage.sampleNo }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNum" name="orderNum" type="text" value="${emkSampleColorPage.orderNum }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkSampleColorPage.gyzl}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					通知单日期:
				</label>
			</td>
			<td class="value">
				<input id="sytzdrq" name="sytzdrq" readonly value="${emkSampleColorPage.sytzdrq }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">通知单日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					需求单日期:
				</label>
			</td>
			<td class="value">
				<input id="syxqdrq" name="syxqdrq" readonly value="${emkSampleColorPage.syxqdrq }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">需求单日期</label>
			</td>

		</tr>

		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" type="hidden" value="${emkSampleColorPage.customSample }" />
				<img id="uploadimg0" src="${emkSampleColorPage.customSampleUrl eq '' ? 'images/bjlogo.png':emkSampleColorPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkSampleColorPage.customSampleUrl }')">${emkSampleColorPage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkSampleColorPage.customSampleUrl}"/>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version" type="text" style="width: 150px" value="${emkSampleColorPage.version }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					布面克重:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf" type="text" style="width: 150px" value="${emkSampleColorPage.chengf }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight" type="text" style="width: 150px"  value="${emkSampleColorPage.weight }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色中文名:
				</label>
			</td>
			<td class="value">
				<input id="yaswm" name="yaswm" type="text" style="width: 150px" value="${emkSampleColorPage.yaswm }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色中文名</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色英文名:
				</label>
			</td>
			<td class="value">
				<input id="ysywm" name="ysywm"  type="text" style="width: 150px" value="${emkSampleColorPage.ysywm }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色英文名</label>
			</td>
		</tr>

		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					色号:
				</label>
			</td>
			<td class="value">
				<input id="sh" name="sh" value="${emkSampleColorPage.sh }"    type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色样规格:
				</label>
			</td>
			<td class="value">
				<input id="sygg" name="sygg" value="${emkSampleColorPage.sygg }"    type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样规格</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					色样数量:
				</label>
			</td>
			<td class="value">
				<input id="sysl" name="sysl" value="${emkSampleColorPage.sysl }"    type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					技术参数:
				</label>
			</td>
			<td class="value">
				<input id="jscs" name="jscs"  value="${emkSampleColorPage.jscs }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">技术参数</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					配方:
				</label>
			</td>
			<td class="value">
				<input id="pf" name="pf"   type="text" value="${emkSampleColorPage.pf }"   style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">配方</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					温度:
				</label>
			</td>
			<td class="value">
				<input id="wd" name="wd"   type="text" value="${emkSampleColorPage.wd }"   style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">温度</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					时间:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sj" name="sj"  value="${emkSampleColorPage.sj }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">时间</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkSampleColorPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkSampleColorPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
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