<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			$("#type").change(function(){
				varval = $('#type').val();
				if(varval == 'cy'){
					$("#orderNo").attr("ignore","ignore");
					$("#pirceNo").removeAttr("ignore");
				}else{
					$("#pirceNo").attr("ignore","ignore");
					$("#orderNo").removeAttr("ignore");
				}
			});


		});

		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#khyyId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#sizeImageUrl").val(d.attributes.url);
			$("#sizeImage").val(d.attributes.name);
			$("#sizeImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg2").attr('src',d.attributes.url);

		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#dgrImageUrl").val(d.attributes.url);
			$("#dgrImage").val(d.attributes.name);
			$("#dgrImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg3").attr('src',d.attributes.url);
		}
		function uploadSuccessSy(d,file,response){
			var src = d.attributes.url;
			$("#colorImageUrl").val(d.attributes.url);
			$("#colorImage").val(d.attributes.name);
			$("#syId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimgSy").attr('src',d.attributes.url);
		}
		function uploadSuccessSh(d,file,response){
			var src = d.attributes.url;
			$("#colorNumImageUrl").val(d.attributes.url);
			$("#colorNumImage").val(d.attributes.name);
			$("#shId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimgSh").attr('src',d.attributes.url);
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkYptzdController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSampleRequiredPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					打样需求单号:
				</label>
			</td>
			<td class="value">
				<input id="requiredNo" name="requiredNo" value="${emkSampleRequiredPage.requiredNo }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品需求单号</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					打样需求单日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" value="${emkSampleRequiredPage.kdDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单日期</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly value="${emkSampleRequiredPage.cusName }" type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkSampleRequiredPage.businesseDeptName }" readonly type="text" style="width: 150px" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkSampleRequiredPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" datatype="*"  style="width: 150px" id="businesserId"  >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkSampleRequiredPage.businesser }"  type="hidden"  class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkSampleRequiredPage.businesserName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value">
				<input id="cusNum" name="cusNum" readonly value="${emkSampleRequiredPage.cusNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkSampleRequiredPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="proTypeTree" value="${emkSampleRequiredPage.proTypeName }">
				<input id="proTypeName" value="${emkSampleRequiredPage.proTypeName }" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkSampleRequiredPage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkSampleRequiredPage.sampleNoDesc }" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="version" name="version"  type="text" value="${emkSampleRequiredPage.version }" style="width: 150px"  ignore="ignore" />
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
				<input id="weight" name="weight"  type="text" style="width: 150px" value="${emkSampleRequiredPage.weight }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf"  type="text" style="width: 150px" value="${emkSampleRequiredPage.chengf }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					样品交期:
				</label>
			</td>
			<td class="value">
				<input id="ysDate" name="ysDate" readonly value="${emkSampleRequiredPage.ysDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距交期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="levelDays" readonly value="${emkSampleRequiredPage.levelDays }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value" colspan="7">
				<t:dictSelect id="type" field="type"  typeGroupCode="sampletype" datatype="*" defaultVal="${emkSampleRequiredPage.type }" hasLabel="false" title="样品类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>

		</tr>
		</table>
		<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
			<t:tab href="emkSampleRequiredController.do?detailMxList&proOrderId=${emkSampleRequiredPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
		</t:tabs>

		<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否收取打样费:
					</label>
				</td>
				<td class="value" colspan="7">
					<input name="isGetSample" type="radio"  datatype="*" <c:if test="${emkSampleRequiredPage.isGetSample eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isGetSample"  type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isGetSample eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否收取打样费</label>
				</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有原样:
				</label>
			</td>
			<td class="value" >
				<input name="isHaveOld" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveOld eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveOld" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveOld eq '1'}">checked="true"</c:if> value="1">
				否
				<input id="customSample" name="customSample" value="${emkSampleRequiredPage.customSample }" type="hidden" />
				<img id="uploadimg" src="${emkSampleRequiredPage.customSampleUrl eq null ? 'images/bjlogo.png':emkSampleRequiredPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>
				<span id="khyyId"></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkSampleRequiredPage.customSampleUrl }" type="hidden" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有原样</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					是否有设计稿:
				</label>
			</td>
			<td class="value">
				<input name="isHaveDgr" type="radio"  datatype="*" <c:if test="${emkSampleRequiredPage.isHaveDgr eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveDgr" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveDgr eq '1'}">checked="true"</c:if> value="1">
				否
				<input id="dgrImage" name="dgrImage" type="hidden" value="${emkSampleRequiredPage.dgrImage }" />
				<img id="uploadimg3" src="${emkSampleRequiredPage.dgrImageUrl eq null ? 'images/bjlogo.png':emkSampleRequiredPage.dgrImageUrl}" width="150" height="150">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>
				<span id="dgrImageId"></span>
				<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkSampleRequiredPage.dgrImageUrl }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有设计稿</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有尺寸表:
				</label>
			</td>
			<td class="value" >
				<input name="isHaveSize" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveSize eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveSize" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveSize eq '1'}">checked="true"</c:if> value="1">
				否
				<img id="uploadimg2" src="${emkSampleRequiredPage.sizeImageUrl eq null ? 'images/bjlogo.png':emkSampleRequiredPage.sizeImageUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>
				<span id="ccbId"></span>
				<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkSampleRequiredPage.sizeImageUrl }" />
				<input id="sizeImage" name="sizeImage" type="hidden" value="${emkSampleRequiredPage.sizeImage }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有尺寸表</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					是否有色样:
				</label>
			</td>
			<td class="value" >
				<input name="isHaveColor" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveColor eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveColor" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveColor eq '1'}">checked="true"</c:if> value="1">
				否
				<img id="uploadimgSy" src="${emkSampleRequiredPage.colorImageUrl eq null ? 'images/bjlogo.png':emkSampleRequiredPage.colorImageUrl}" width="150" height="150">
				<t:upload name="instructionSy" id="instructionSy" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccessSy" >
				</t:upload>
				<span id="syId"></span>
				<input id="colorImageUrl" name="colorImageUrl" type="hidden" value="${emkSampleRequiredPage.colorImageUrl }" />
				<input id="colorImage" name="colorImage" type="hidden" value="${emkSampleRequiredPage.colorImage }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有色样</label>
			</td>

		</tr>

		<tr>
				<td align="right">
					<label class="Validform_label">
						是否有色号:
					</label>
				</td>
				<td class="value" colspan="7">
					<input name="isHaveColorNum" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveColorNum eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isHaveColorNum" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveColorNum eq '1'}">checked="true"</c:if> value="1">
					否<br/>
					<img id="uploadimgSh" src="${emkSampleRequiredPage.colorNumImageUrl eq null ? 'images/bjlogo.png':emkSampleRequiredPage.colorNumImageUrl}" width="150" height="150">
					<t:upload name="instructionSh" id="instructionSh" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccessSh" >
					</t:upload>
					<span id="shId"></span>
					<input id="colorNumImageUrl" name="colorNumImageUrl" type="hidden" value="${emkSampleRequiredPage.colorNumImageUrl }" />
					<input id="colorNumImage" name="colorNumImage" type="hidden" value="${emkSampleRequiredPage.colorNumImage }" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有色号</label>
				</td>
			</tr>
			<tr>
				<td colspan="8" id="instructionfile" class="value">
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否有意向订单:
					</label>
				</td>
				<td class="value" >
					<input name="isEnquiry" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isEnquiry eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isEnquiry" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isEnquiry eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有意向订单</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						意向订单号:
					</label>
				</td>
				<td class="value" >
					<input id="enquiryNo" name="enquiryNo" value="${emkSampleRequiredPage.enquiryNo }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">意向订单号</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						大货交期:
					</label>
				</td>
				<td class="value" >
					<input id="dhjq" name="dhjq" readonly type="text" value="${emkSampleRequiredPage.dhjq }" style="width: 130px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">大货交期</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						距大货交期余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leaveldhjqDays" name="leaveldhjqDays" value="${emkSampleRequiredPage.leaveldhjqDays }" readonly type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">距大货交期余天数</label>
				</td>
			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						是否有大货:
					</label>
				</td>
				<td class="value" >
					<input name="isDh" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isDh eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isDh" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isDh eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有大货</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						订单号:
					</label>
				</td>
				<td class="value" >
					<input id="orderNo" name="orderNo"  value="${emkSampleRequiredPage.orderNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">订单号</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						大货交期:
					</label>
				</td>
				<td class="value" >
					<input id="dhjq2" name="dhjq2" readonly value="${emkSampleRequiredPage.dhjq2 }" type="text" style="width: 130px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">大货交期</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						距大货交期余天数:
					</label>
				</td>
				<td class="value" >
					<input id="leaveldhjq2Days" name="leaveldhjq2Days" value="${emkSampleRequiredPage.leaveldhjq2Days }" readonly type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">距大货交期余天数</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否有过预报价:
					</label>
				</td>
				<td class="value" >
					<input name="isPrice" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isPrice eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isPrice" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isPrice eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有过预报价</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						报价单号:
					</label>
				</td>
				<td class="value" colspan="5">
					<input id="priceNo" name="priceNo" value="${emkSampleRequiredPage.leaveldhjq2Days }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价单号</label>
				</td>
			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						客户评语:
					</label>
				</td>
				<td class="value" colspan="7">
					<textarea  id="customRate" style="width:95%;height:70px" class="inputxt" rows="3" name="customRate">${emkSampleRequiredPage.customRate }</textarea>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">客户评语</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						备注:
					</label>
				</td>
				<td class="value" colspan="7">
					<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="3" name="remark">${emkSampleRequiredPage.remark }</textarea>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">备注</label>
				</td>
			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						样品去向:
					</label>
				</td>
				<td class="value" colspan="7">
					<input id="sampleTo" name="sampleTo" type="text" value="${emkSampleRequiredPage.sampleTo }" style="width:95%" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">样品去向</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						收件人:
					</label>
				</td>
				<td class="value">
					<input id="recevier" name="recevier" value="${emkSampleRequiredPage.recevier }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">收件人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						日期:
					</label>
				</td>
				<td class="value">
					<input id="recevieDate" name="recevieDate" value="${emkSampleRequiredPage.recevieDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">日期</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						寄件人:
					</label>
				</td>
				<td class="value">
					<input id="sender" name="sender" value="${emkSampleRequiredPage.sender }" type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">寄件人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						寄件数量:
					</label>
				</td>
				<td class="value">
					<input id="sendTotal" name="sendTotal" value="${emkSampleRequiredPage.sendTotal }" datatype="n" type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">寄件数量</label>
				</td>
			</tr>

	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");
		$("#instructionSy-button").css("width","70px");
		$("#instructionSh-button").css("width","70px");

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