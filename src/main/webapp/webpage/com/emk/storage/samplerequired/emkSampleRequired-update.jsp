<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html(d.attributes.name);

			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#oldImageUrl").val(d.attributes.url);
			$("#oldImage").val(d.attributes.name);
			$("#oldImageId").html(d.attributes.name);

			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#sizeImageUrl").val(d.attributes.url);
			$("#sizeImage").val(d.attributes.name);
			$("#sizeImageId").html(d.attributes.name);
			$("#uploadimg2").attr('src',d.attributes.url);

		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#dgrImageUrl").val(d.attributes.url);
			$("#dgrImage").val(d.attributes.name);
			$("#dgrImageId").html(d.attributes.name);
			$("#uploadimg3").attr('src',d.attributes.url);
		}

		function showPriceDiv(isShow){
			if(isShow==0){
				$("#priceDiv").css("display","");
			}else {
				$("#priceDiv").css("display","none");
			}
		}

		function showDgrImage(isShow){
			if(isShow == 0){
				$("#dgrImageDiv").css("display","");
			}else{
				$("#dgrImageDiv").css("display","none");
			}
		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleRequiredController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSampleRequiredPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					样品需求单号:
				</label>
			</td>
			<td class="value">
				<input id="requiredNo" name="requiredNo" value="${emkSampleRequiredPage.requiredNo }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品需求单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" value="${emkSampleRequiredPage.cusNum }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" value="${emkSampleRequiredPage.cusName }" readonly type="text" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkSampleRequiredPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkSampleRequiredPage.businesseDeptId }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkSampleRequiredPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkSampleRequiredPage.businesserName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkSampleRequiredPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" value="${emkSampleRequiredPage.proTypeName }" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkSampleRequiredPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkSampleRequiredPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="kdDate" name="kdDate" readonly value="${emkSampleRequiredPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
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
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx"  datatype="*" defaultVal="${emkSampleRequiredPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="type" field="type" typeGroupCode="sampletype" datatype="*" defaultVal="${emkSampleRequiredPage.type }" hasLabel="false" title="样品类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version"  type="text" value="${emkSampleRequiredPage.version }" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面克重:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight" type="text" value="${emkSampleRequiredPage.weight }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf" type="text" value="${emkSampleRequiredPage.chengf }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距交期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="levelDays" type="text" value="${levelDays}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色英文名称:
				</label>
			</td>
			<td class="value">
				<input id="colorEnName" name="colorEnName" value="${emkSampleRequiredPage.colorEnName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色英文名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					颜色中文名称:
				</label>
			</td>
			<td class="value">
				<input id="colorZnName" name="colorZnName" value="${emkSampleRequiredPage.colorZnName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色中文名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色号:
				</label>
			</td>
			<td class="value">
				<input id="colorValue" name="colorValue" value="${emkSampleRequiredPage.colorValue }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					尺码:
				</label>
			</td>
			<td class="value">
				<input id="size" name="size" type="text" value="${emkSampleRequiredPage.size }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" value="${emkSampleRequiredPage.total }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					是否有设计稿:
				</label>
			</td>
			<td class="value" >
				<input name="isHaveDgr" type="radio" onclick="showDgrImage(0)" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveDgr eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveDgr" onclick="showDgrImage(1)" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveDgr eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有设计稿</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有原样:
				</label>
			</td>
			<td class="value">
				<input name="isHaveOld" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveOld eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveOld" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveOld eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有原样</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有尺寸表:
				</label>
			</td>
			<td class="value">
				<input name="isHaveSize" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isHaveSize eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveSize" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isHaveSize eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有尺寸表</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value">
				<input id="customSample" name="customSample" value="${emkSampleRequiredPage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkSampleRequiredPage.customSampleUrl eq '' ? 'images/bjlogo.png':emkSampleRequiredPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkSampleRequiredPage.customSampleUrl }')">${emkSampleRequiredPage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkSampleRequiredPage.customSampleUrl }" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					设计稿:
				</label>
			</td>
			<td class="value">
				<input id="dgrImage" name="dgrImage" type="hidden" value="${emkSampleRequiredPage.dgrImage }"/>
				<img id="uploadimg3" src="${emkSampleRequiredPage.dgrImageUrl eq '' ? 'images/bjlogo.png':emkSampleRequiredPage.dgrImageUrl}" width="150" height="150">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>[<a href="javascript:findDetail('${emkSampleRequiredPage.dgrImageUrl }')">${emkSampleRequiredPage.dgrImage }</a>]
				<span id="dgrImageId"></span>
				<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkSampleRequiredPage.dgrImageUrl }"/>
			</td>
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value">
				<input id="oldImage" name="oldImage" type="hidden" value="${emkSampleRequiredPage.oldImage }"/>
				<img id="uploadimg" src="${emkSampleRequiredPage.oldImageUrl eq '' ? 'images/bjlogo.png':emkSampleRequiredPage.oldImageUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>[<a href="javascript:findDetail('${emkSampleRequiredPage.oldImageUrl }')">${emkSampleRequiredPage.oldImage }</a>]
				<span id="oldImageId"></span>
				<input id="oldImageUrl" name="oldImageUrl" type="hidden" value="${emkSampleRequiredPage.oldImageUrl }"/>
			</td>

			<td align="right">
				<label class="Validform_label">
					尺寸表:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg2" src="${emkSampleRequiredPage.sizeImageUrl eq '' ? 'images/bjlogo.png':emkSampleRequiredPage.sizeImageUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>[<a href="javascript:findDetail('${emkSampleRequiredPage.sizeImageUrl }')">${emkSampleRequiredPage.sizeImage }</a>]
				<span id="sizeImageId"></span>
				<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkSampleRequiredPage.sizeImageUrl }"/>
				<input id="sizeImage" name="sizeImage" type="hidden" value="${emkSampleRequiredPage.sizeImage }"/>
			</td>

		</tr>

		<tr>
			<td colspan="8" id="instructionfile" class="value">
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					是否打过初样:
				</label>
			</td>
			<td class="value">
				<input name="isPrintSample" type="radio" datatype="*" <c:if test="${emkSampleRequiredPage.isPrintSample eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isPrintSample" type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isPrintSample eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否打过初样</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否收取打样费:
				</label>
			</td>
			<td class="value" colspan="5">
				<input name="isGetSample" type="radio" onclick="showPriceDiv(0)" datatype="*" <c:if test="${emkSampleRequiredPage.isGetSample eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isGetSample" onclick="showPriceDiv(1)"  type="radio" datatype="*"  <c:if test="${emkSampleRequiredPage.isGetSample eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否收取打样费</label>
			</td>
		</tr>
		<tr id="priceDiv" style="display: ${emkSampleRequiredPage.isGetSample eq '0' ? '':'none'};">
			<td colspan="8">
				<table style="width: 100%;font-size: 12px;" border="0" cellpadding="0" cellspacing="1" >
					<tr>
						<td align="right" style="height:12px;">
							<label class="Validform_label">
								金额:
							</label>
						</td>
						<td class="value">
							<input id="money" name="money" value="${samplePriceEntity.money }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								币种:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="pbz" field="pbz"  typeGroupCode="cointype" defaultVal="${samplePriceEntity.bz}" hasLabel="false" title="币种"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">币种</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								收款状态:
							</label>
						</td>
						<td class="value">
							<select id="pstate" name="pstate">
								<option value="0" ${samplePriceEntity.state eq 0  ? 'selected':''}>未到账</option>
								<option value="1" ${samplePriceEntity.state eq 1  ? 'selected':''}>已到账</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款状态</label>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkSampleRequiredPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="value">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					所用机台尺寸:
				</label>
			</td>
			<td class="value">
				<input id="machineSize" name="machineSize" value="${emkSampleRequiredPage.machineSize }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">所用机台尺寸</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					下机重量:
				</label>
			</td>
			<td class="value">
				<input id="machineWeight" name="machineWeight" value="${emkSampleRequiredPage.machineWeight }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机重量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					下机尺寸:
				</label>
			</td>
			<td class="value">
				<input id="downMachineSize" name="downMachineSize" value="${emkSampleRequiredPage.downMachineSize }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机尺寸</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					密度:
				</label>
			</td>
			<td class="value">
				<input id="prcent" name="prcent" type="text" value="${emkSampleRequiredPage.prcent }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">密度</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					用料:
				</label>
			</td>
			<td class="value">
				<input id="userLiao" name="userLiao" value="${emkSampleRequiredPage.userLiao }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用料</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单件织造时间:
				</label>
			</td>
			<td class="value">
				<input id="oneMakeDate" name="oneMakeDate" value="${emkSampleRequiredPage.oneMakeDate }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件织造时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					剩余数量:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="levelTotal" name="levelTotal" value="${emkSampleRequiredPage.levelTotal }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">剩余数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					染色要求:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="ranRequired" style="width:95%;height:70px" class="inputxt" rows="5" name="ranRequired">${emkSampleRequiredPage.ranRequired }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染色要求</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					缝制要求:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="fengRequired" style="width:95%;height:70px" class="inputxt" rows="5" name="fengRequired">${emkSampleRequiredPage.fengRequired }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制要求</label>
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
				<input id="sender" name="sender" value="${emkSampleRequiredPage.sender }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">寄件人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					寄件数量:
				</label>
			</td>
			<td class="value">
				<input id="sendTotal" name="sendTotal" value="${emkSampleRequiredPage.sendTotal }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">寄件数量</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkSampleRequiredPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					下机克重:
				</label>
			</td>
			<td class="value">
				<input id="xjkz" name="xjkz" datatype="d" value="${emkSampleRequiredPage.xjkz }" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单件所需时间:
				</label>
			</td>
			<td class="value">
				<input id="djsxTime" name="djsxTime" value="${emkSampleRequiredPage.djsxTime }" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件所需时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit" value="${emkSampleRequiredPage.unit }"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					机台日产量:
				</label>
			</td>
			<td class="value">
				<input id="jtrcl" name="jtrcl" value="${emkSampleRequiredPage.jtrcl }" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">机台日产量</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					前道损耗率:
				</label>
			</td>
			<td class="value">
				<input id="qdshl" name="qdshl" value="${emkSampleRequiredPage.qdshl }" datatype="d"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">前道损耗率</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					后道损耗率:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="hdshl" name="hdshl" value="${emkSampleRequiredPage.hdshl }" datatype="d" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">后道损耗率</label>
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