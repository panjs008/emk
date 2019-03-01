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
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					色样通知单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="sytzdbh" name="sytzdbh" readonly type="text" value="${emkSampleColorPage.sytzdbh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样通知单号</label>
			</td>
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
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					通知单日期:
				</label>
			</td>
			<td class="value">
				<input id="syxqdrq" name="syxqdrq" readonly value="${emkSampleColorPage.syxqdrq }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">通知单日期</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" value="${emkSampleColorPage.businesser }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkSampleColorPage.businesserName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
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
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkSampleColorPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkSampleColorPage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					色样需求单日期:
				</label>
			</td>
			<td class="value">
				<input id="sytzdrq" name="sytzdrq" readonly value="${emkSampleColorPage.sytzdrq }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样需求单日期</label>
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
				<input id="developer" name="developer" readonly value="${emkSampleColorPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkSampleColorPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly value="${emkSampleColorPage.cusName }" type="text" style="width: 150px" class="inputxt"  datatype="*"  />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,tracer,tracerName,businesser,businesserName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkSampleColorPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkSampleColorPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkSampleColorPage.sampleNoDesc }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					布面克重:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf" type="text" value="${emkSampleColorPage.chengf }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight" type="text" value="${emkSampleColorPage.weight}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工厂代码:
				</label>
			</td>
			<td class="value">
				<input id="gc" name="gc" type="text" style="width: 150px" value="${emkSampleColorPage.gc}"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂代码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkSampleColorPage.proTypeName}" >
				<input id="proTypeName" name="proTypeName" value="${emkSampleColorPage.proTypeName}"  datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkSampleColorPage.proType}"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
		</tr>
	</table>
	<t:tabs id="sampleColorDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkColorController.do?detailMxList2&colorId=${emkSampleColorPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top: 20px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					配方:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="pf" name="pf" value="${emkSampleColorPage.pf}"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">配方</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					温度:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="wd" name="wd" value="${emkSampleColorPage.wd}"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="sj" name="sj"  value="${emkSampleColorPage.sj}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">时间</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					打样日志:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkSampleColorPage.remark}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样日志</label>
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