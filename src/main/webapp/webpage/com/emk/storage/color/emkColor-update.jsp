<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>色样需求单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html(d.attributes.name);
			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#colorCardUrl").val(d.attributes.url);
			$("#colorCard").val(d.attributes.name);
			$("#colorCardId").html(d.attributes.name);
			$("#uploadimg1").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#colorNumUrl").val(d.attributes.url);
			$("#colorNum").val(d.attributes.name);
			$("#colorNumId").html(d.attributes.name);
			$("#uploadimg2").attr('src',d.attributes.url);

		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#colorDataUrl").val(d.attributes.url);
			$("#colorData").val(d.attributes.name);
			$("#colorDataId").html(d.attributes.name);
			$("#uploadimg3").attr('src',d.attributes.url);
		}
		function uploadSuccess4(d,file,response){
			var src = d.attributes.url;
			$("#colorQtxUrl").val(d.attributes.url);
			$("#colorQtx").val(d.attributes.name);
			$("#colorQtxId").html(d.attributes.name);
			$("#uploadimg4").attr('src',d.attributes.url);
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

		function findDetail(photoUrl) {
			$.dialog({
				content: 'url:emkEnquiryController.do?photo&photoUrl='+photoUrl,
				zIndex: getzIndex(),
				title : "查看",
				lock : true,
				width:900,
				height: 500,
				opacity : 0.3,
				cache:false,
				lock : true,
				cache:false,
				max: true,
				min: true,
				drag: true,
				resize: false
			});
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkColorController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkColorPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					色样需求单号:
				</label>
			</td>
			<td class="value"  colspan="5">
				<input id="colorNo" name="cusNumcolorNo" value="${emkColorPage.colorNo }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样需求单号</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户编号:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" value="${emkColorPage.cusNum }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" value="${emkColorPage.cusName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="businesseDeptName" value="${emkColorPage.businesseDeptName }" name="businesseDeptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" value="${emkColorPage.businesseDeptId }" name="businesseDeptId"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<input id="businesser" value="${emkColorPage.businesser }" name="businesser" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" value="${emkColorPage.businesserName }" name="businesserName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkColorPage.proTypeName }">
				<input id="proTypeName" value="${emkColorPage.proTypeName }" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" value="${emkColorPage.proType }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="sampleNo" name="sampleNo" value="${emkColorPage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkColorPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="recevieDate" name="recevieDate" readonly value="${emkColorPage.recevieDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*"  defaultVal="${emkColorPage.gyzl}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version" value="${emkColorPage.version}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					色样种类:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="colorType" field="colorType" typeGroupCode="colortype" datatype="*" defaultVal="${emkColorPage.colorType}" hasLabel="false" title="色样种类"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样种类</label>
			</td>
		</tr>

		<tr>
			<td align="right" rowspan="2">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="2" valign="bottom">
				<input id="customSample" value="${emkColorPage.customSample }" name="customSample" type="hidden" />
				<img id="uploadimg0" src="${emkColorPage.customSampleUrl eq null ? 'images/bjlogo.png':emkColorPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkColorPage.customSampleUrl }')">${emkColorPage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" value="${emkColorPage.customSampleUrl }" name="customSampleUrl" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有标准色卡:
				</label>
			</td>
			<td class="value">
				<input name="isColorCard" type="radio"  datatype="*" <c:if test="${emkColorPage.isColorCard eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isColorCard" type="radio" datatype="*"  <c:if test="${emkColorPage.isColorCard eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有标准色卡</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有标准色号:
				</label>
			</td>
			<td class="value">
				<input name="isColorQtx" type="radio" datatype="*" <c:if test="${emkColorPage.isColorQtx eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isColorQtx" type="radio" datatype="*"  <c:if test="${emkColorPage.isColorQtx eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有标准色号</label>
			</td>

		</tr>
		<tr id="dgrImageDiv" >
			<td align="right">
				<label class="Validform_label">
					标准色卡:
				</label>
			</td>
			<td class="value">
				<input id="colorCard" value="${emkColorPage.colorCard }" name="colorCard" type="hidden" />
				<img id="uploadimg1" src="${emkColorPage.colorCardUrl eq null ? 'images/bjlogo.png':emkColorPage.colorCardUrl}" width="150" height="150">
				<t:upload name="instruction1" id="instruction1" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess1" >
				</t:upload>[<a href="javascript:findDetail('${emkColorPage.colorCardUrl }')">${emkColorPage.colorCard }</a>]
				<span id="colorCardId"></span>
				<input id="colorCardUrl" value="${emkColorPage.colorCardUrl }" name="colorCardUrl" type="hidden" />
			</td>

			<td align="right">
				<label class="Validform_label">
					标准色号:
				</label>
			</td>
			<td class="value">
				<input id="colorNum" value="${emkColorPage.id }" name="colorNum" type="hidden" />
				<img id="uploadimg2" src="${emkColorPage.colorNumUrl eq null ? 'images/bjlogo.png':emkColorPage.colorNumUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>[<a href="javascript:findDetail('${emkColorPage.colorNumUrl }')">${emkColorPage.colorNum }</a>]
				<span id="colorNumId"></span>
				<input id="colorNumUrl" value="${emkColorPage.colorNumUrl }" name="colorNumUrl" type="hidden" />
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有标准色数据:
				</label>
			</td>
			<td class="value">
				<input name="isColorData" type="radio" datatype="*" <c:if test="${emkColorPage.isColorData eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isColorData" type="radio" datatype="*"  <c:if test="${emkColorPage.isColorData eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有标准色数据</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有标准色QTX文件:
				</label>
			</td>
			<td class="value" colspan="5">
				<input name="isColorQtx" type="radio" datatype="*" <c:if test="${emkColorPage.isColorQtx eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isColorQtx" type="radio" datatype="*"  <c:if test="${emkColorPage.isColorQtx eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有标准色QTX文件</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					标准色数据:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg3" src="${emkColorPage.colorDataUrl eq null ? 'images/bjlogo.png':emkColorPage.colorDataUrl}" width="150" height="150">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>[<a href="javascript:findDetail('${emkColorPage.colorDataUrl }')">${emkColorPage.colorData }</a>]
				<span id="colorDataId"></span>
				<input id="colorDataUrl" value="${emkColorPage.colorDataUrl }" name="colorDataUrl" type="hidden" />
				<input id="colorData" value="${emkColorPage.colorData }" name="colorData" type="hidden" />
			</td>

			<td align="right">
				<label class="Validform_label">
					准色QTX文件:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="colorQtx"  value="${emkColorPage.colorQtx }" name="colorQtx" type="hidden" />
				<img id="uploadimg4" src="${emkColorPage.colorQtxUrl eq null ? 'images/bjlogo.png':emkColorPage.colorQtxUrl}" width="150" height="150">
				<t:upload name="instruction4" id="instruction4" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess4" >
				</t:upload>[<a href="javascript:findDetail('${emkColorPage.colorQtxUrl }')">${emkColorPage.colorQtx }</a>]
				<span id="colorQtxId"></span>
				<input id="colorQtxUrl" value="${emkColorPage.colorQtxUrl }" name="colorQtxUrl" type="hidden" />
			</td>

		</tr>


		<tr>
			<td colspan="6" id="instructionfile" class="value">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色英文名称:
				</label>
			</td>
			<td class="value">
				<input id="colorEnName" name="colorEnName" value="${emkColorPage.colorEnName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色英文名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					颜色中文名称:
				</label>
			</td>
			<td class="value">
				<input id="colorZnName" name="colorZnName" value="${emkColorPage.colorZnName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色中文名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色号:
				</label>
			</td>
			<td class="value">
				<input id="seNum" name="seNum" type="text" value="${emkColorPage.seNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色号</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					色样编号:
				</label>
			</td>
			<td class="value">
				<input id="syNum" name="syNum" type="text" value="${emkColorPage.syNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					主光源:
				</label>
			</td>
			<td class="value">
				<input id="zgy" name="zgy" type="text" value="${emkColorPage.zgy }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主光源</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					次光源:
				</label>
			</td>
			<td class="value">
				<input id="cgy" name="cgy" type="text" value="${emkColorPage.cgy }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">次光源</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					色样规格:
				</label>
			</td>
			<td class="value">
				<input id="colorBrand" name="colorBrand" value="${emkColorPage.colorBrand }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样规格</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色样数量:
				</label>
			</td>
			<td class="value">
				<input id="colorTotal" name="colorTotal" value="${emkColorPage.colorTotal }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色牢度:
				</label>
			</td>
			<td class="value">
				<input id="colorSlg" name="colorSlg"  value="${emkColorPage.colorSlg }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色牢度</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					化学物质:
				</label>
			</td>
			<td class="value">
				<input id="hxwz" name="hxwz" type="text" value="${emkColorPage.hxwz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">化学物质</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					重金属:
				</label>
			</td>
			<td class="value">
				<input id="gjs" name="gjs" type="text" value="${emkColorPage.gjs }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">重金属</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色样去向:
				</label>
			</td>
			<td class="value">
				<input id="syTo" name="syTo" type="text" value="${emkColorPage.syTo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色样去向</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					收件人:
				</label>
			</td>
			<td class="value">
				<input id="recevier" name="recevier" value="${emkColorPage.recevier }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收件人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="riqi" name="riqi" value="${emkColorPage.riqi }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					寄件人:
				</label>
			</td>
			<td class="value">
				<input id="sender" name="sender" value="${emkColorPage.sender }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">寄件人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					寄件数量:
				</label>
			</td>
			<td class="value">
				<input id="sendTotal" name="sendTotal" value="${emkColorPage.sendTotal }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">寄件数量</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					剩余数量:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="syTotal" name="syTotal" value="${emkColorPage.syTotal }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">剩余数量</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkColorPage.sampleNoDesc }</textarea>
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
			<td class="value" colspan="5">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkColorPage.remark }</textarea>
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
		$("#instruction1-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");
		$("#instruction4-button").css("width","70px");

		/*$("#gyzl").css("width","100px");
		 $("#colorType").css("width","100px");*/


		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 150,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>