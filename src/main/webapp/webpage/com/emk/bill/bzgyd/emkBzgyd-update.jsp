<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>包装工艺单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#boxImageUrl").val(d.attributes.url);
			$("#boxImage").val(d.attributes.name);
			$("#boxImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg1").attr('src',d.attributes.url);

		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkBzgydController.do?doUpdate" tiptype="1">
	<input id="bzgydId" name="bzgydId" type="hidden" value="${emkBzgydPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo" value="${emkBzgydPage.orderNo }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkBzgydPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkBzgydPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
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
				<input id="sampleNo" name="sampleNo" type="text" value="${emkBzgydPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkBzgydPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkBzgydPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkBzgydPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*" value="${emkBzgydPage.proTypeName }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkBzgydPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkBzgydPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkBzgydPage.gys }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkBzgydPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkBzgydPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" >
				<input id="sampleNoDesc" name="sampleNoDesc"   value="${emkBzgydPage.sampleNoDesc }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkBzgydPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkBzgydPage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value">
				<input id="sumTotal" name="sumTotal"  datatype="n" value="${emkBzgydPage.sumTotal }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" colspan="3">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" value="${emkBzgydPage.developer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden" value="${emkBzgydPage.developerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总箱数:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="sumBoxTotal" name="sumBoxTotal"  datatype="n" type="text" value="${emkBzgydPage.sumBoxTotal }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总箱数</label>
			</td>
		</tr>
		<tr>
			<td align="left" class="value"  colspan="4">
				<label class="Validform_label">
					包装方式
				</label>
			</td>
			<td align="right" rowspan="5" >
				<label class="Validform_label">
					包装效果图:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" type="hidden" value="${emkBzgydPage.customSample }"/>
				<img id="uploadimg0" src="${emkBzgydPage.customSampleUrl eq '' ? 'images/bjlogo.png':emkBzgydPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkBzgydPage.customSampleUrl }')">${emkBzgydPage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkBzgydPage.customSampleUrl }"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单件:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="one" name="one" value="${emkProOrderPage.one }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装效果图</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					胶袋:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="polybag" name="polybag"  value="${emkProOrderPage.polybag }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					装箱:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="boxup" name="boxup"  value="${emkProOrderPage.boxup }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">装箱</label>
			</td>
		</tr>

		<tr>
			<td colspan="4" id="instructionfile1" class="value">&nbsp;
			</td>
		</tr>

	</table>
	<t:tabs id="proOrderDetail"  iframe="false" tabPosition="top"  fit="false">
		<t:tab href="emkProOrderController.do?orderMxList3&proOrderId=${param.id}"   icon="fa fa-cube" title="包装辅料清单" id="bzdetail"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?barCodeMxList&type=1&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="胶袋贴条码" id="barCode1"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?barCodeMxList&type=2&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="箱贴条码" id="barCode2"></t:tab>
	</t:tabs>

	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkBzgydController.do?boxMxList&type=0&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="单色单码装" id="box1"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail2" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkBzgydController.do?boxMxList&type=1&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="单色混码装" id="box2"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail3" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkBzgydController.do?boxMxList&type=2&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="混色单码装" id="box3"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail4" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkBzgydController.do?boxMxList&type=3&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="混色混码装" id="box4"></t:tab>
	</t:tabs>
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