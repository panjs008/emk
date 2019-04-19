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
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#oldImageUrl").val(d.attributes.url);
			$("#oldImage").val(d.attributes.name);
			$("#oldImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#sizeImageUrl").val(d.attributes.url);
			$("#sizeImage").val(d.attributes.name);
			$("#sizeImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			//$("#uploadimg2").attr('src',d.attributes.url);

		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#dgrImageUrl").val(d.attributes.url);
			$("#dgrImage").val(d.attributes.name);
			$("#dgrImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			//$("#uploadimg3").attr('src',d.attributes.url);
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

		function setLimitTime() {
			var d1  =  $("#kdDate").val();
			$("#limitDate").val(dateAddDays(d1,60));
		}

	</script>
</head>
<body>
<c:if test="${param.id ne null && param.id ne ''}">
	<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>
</c:if>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1">
	<input id="peid" name="peid" type="hidden" value="${emkPricePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<%--<tr>
			<td align="right" >
				<label class="Validform_label">
					询盘单号:
				</label>
			</td>
			<td class="value" >
				<input id="xpNo" name="xpNo" datatype="*"  type="text" validType="emk_price,xp_no,id" style="width: 100px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">询盘单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					打样通知单号:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="dyNo" name="dyNo"  type="text" style="width: 100px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样通知单号</label>
			</td>
		</tr>--%>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					报价单号:
				</label>
			</td>
			<td class="value" >
				<input id="pirceNo" name="pirceNo" value="${emkPricePage.pirceNo }" readonly type="text" style="width: 100px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly value="${emkPricePage.cusNum }" type="text" style="width: 100px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="6">
				<input id="cusName" name="cusName" readonly  value="${emkPricePage.cusName }" type="text" datatype="*" style="width: 180px" class="inputxt"  />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,bz,tracer,tracerName,developer,developerName" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
			<tr>
				<td class="value" colspan="6">
					&nbsp;
				</td>
				<td align="right">
					<label class="Validform_label">
						款式大类:
					</label>
				</td>
				<td class="value">
					<input id="proTypeTree" value="${emkPricePage.proTypeName }">
					<input id="proTypeName" name="proTypeName"  value="${emkPricePage.proTypeName }" datatype="*"  type="hidden" style="width: 100px"   ignore="ignore" />
					<input id="proType" name="proType" type="hidden"  value="${emkPricePage.proType }" style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">款式大类</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						业务部门:
					</label>
				</td>
				<td class="value" >
					<input id="businesseDeptName" name="businesseDeptName" value="${emkPricePage.businesseDeptName }" readonly type="text" style="width: 100px" />
					<input id="businesseDeptId" name="businesseDeptId"  value="${emkPricePage.businesseDeptId }" type="hidden"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">业务部门</label>
				</td>

				<td class="value"  rowspan="5">
					<input id="customSample" name="customSample" type="hidden" value="${emkPricePage.customSample }"/>
					<img id="uploadimg0" src="${emkPricePage.customSampleUrl eq null || emkPricePage.customSampleUrl eq ''  ? 'images/bjlogo.png':emkPricePage.customSampleUrl}" width="120" height="120">
					<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
					</t:upload>
					<c:if test="${emkPricePage.customSampleUrl ne null && emkPricePage.customSampleUrl ne ''}">[<a href="javascript:findDetail('${emkPricePage.customSampleUrl }')">${emkPricePage.customSample }</a>]</c:if>

					<span id="customSampleId"></span>
					<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkPricePage.customSampleUrl }"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						报价日期:
					</label>
				</td>
				<td class="value">
					<input id="kdDate" name="kdDate" readonly value="${emkPricePage.kdDate ne null ? emkPricePage.kdDate:kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:setLimitTime})"  type="text" style="width: 100px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价日期</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						报价有效期:
					</label>
				</td>
				<td class="value">
					<input id="limitDate" name="limitDate" readonly value="${emkPricePage.limitDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 100px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价有效期</label>
				</td>

				<td align="right">
					<label class="Validform_label">
						工艺类型:
					</label>
				</td>
				<td class="value">
					<t:dictSelect id="gyzl" field="gyzl"  typeGroupCode="gylx" datatype="*" defaultVal="${emkPricePage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">工艺类型</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						款号:
					</label>
				</td>
				<td class="value">
					<input id="sampleNo" name="sampleNo" type="text"  value="${emkPricePage.sampleNo }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">款号</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						业务员:
					</label>
				</td>
				<td class="value" >
					<select class="form-control select2" datatype="*"  style="width: 100px" id="businesserId"  >
						<option value=''>请选择</option>
					</select>
					<input id="businesser" name="businesser" readonly value="${emkPricePage.businesser }"  type="hidden"  class="inputxt"  ignore="ignore" />
					<input id="businesserName" name="businesserName"  value="${emkPricePage.businesserName }"  type="hidden"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">业务员</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						目的国:
					</label>
				</td>
				<td class="value">
					<input id="guoJia" name="guoJia"  type="text" value="${emkPricePage.guoJia }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">目的国</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						报价类型:
					</label>
				</td>
				<td class="value">
					<input id="bjlx" name="bjlx" type="text" value="${emkPricePage.bjlx }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价类型</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						报价币种:
					</label>
				</td>
				<td class="value">
					<input id="bz" name="bz" type="text" value="${emkPricePage.bz }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价币种</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						描述:
					</label>
				</td>
				<td class="value">
					<input id="sampleNoDesc" name="sampleNoDesc" value="${emkPricePage.sampleNoDesc }"  type="text" style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">描述</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						业务跟单员:
					</label>
				</td>
				<td class="value">
					<select class="form-control select2" id="tracerId"  style="width: 100px">
						<option value=''>请选择</option>
					</select>
					<input id="tracer" name="tracer" readonly type="hidden" value="${emkPricePage.tracer }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<input id="tracerName" name="tracerName"  type="hidden" value="${emkPricePage.tracerName }"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">业务跟单员</label>
				</td>
			</tr>
			<tr>

				<td align="right">
					<label class="Validform_label">
						报价说明:
					</label>
				</td>
				<td class="value" colspan="9">
					<input id="bjsm" name="bjsm" type="text" style="width: 100px" value="${emkPricePage.bjsm }"  class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价说明</label>
				</td>
			</tr>
			<tr>
				<td class="value" colspan="10">
					&nbsp;
				</td>

			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否有原样:
					</label>
				</td>
				<td class="value" >
					<input name="isHaveOld" type="radio" datatype="*" <c:if test="${emkPricePage.isHaveOld eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isHaveOld" type="radio" datatype="*"  <c:if test="${emkPricePage.isHaveOld eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有原样</label>
				</td>
				<td class="value" colspan="9">
					<input id="oldImage" name="oldImage" type="hidden" value="${emkPricePage.oldImage }"/>
					<img id="uploadimg" src="${emkPricePage.oldImageUrl eq null || emkPricePage.oldImageUrl eq '' ? 'images/bjlogo.png':emkPricePage.oldImageUrl}" width="120" height="120">
					<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
					</t:upload>
					<c:if test="${emkPricePage.oldImageUrl ne null && emkPricePage.oldImageUrl ne ''}">[<a href="javascript:findDetail('${emkPricePage.oldImageUrl }')">${emkPricePage.oldImage }</a>]</c:if>
					<span id="oldImageId"></span>
					<input id="oldImageUrl" name="oldImageUrl" type="hidden" value="${emkPricePage.oldImageUrl }"/>
				</td>
				</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否有设计稿:
					</label>
				</td>
				<td class="value">
					<input name="isHaveDgr" type="radio"  datatype="*" <c:if test="${emkPricePage.isHaveDgr eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isHaveDgr" type="radio" datatype="*"  <c:if test="${emkPricePage.isHaveDgr eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有设计稿</label>
				</td>
				<td class="value">
					<input id="dgrImage" name="dgrImage" type="hidden" value="${emkPricePage.dgrImage }"/>
					<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkPricePage.dgrImageUrl }"/>
						<span id="dgrImageId">
							<c:if test="${emkPricePage.dgrImageUrl ne null && emkPricePage.dgrImageUrl ne ''}">[<a href="javascript:findDetail('${emkPricePage.dgrImageUrl }')">${emkPricePage.dgrImage }</a>]</c:if>
						</span>
				</td>
				<td class="value" colspan="8">
					<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
					</t:upload>
				</td>
				</tr>

			<tr>
				<%--<td class="value">
					<input id="dgrImage" name="dgrImage" type="hidden" value="${emkPricePage.dgrImage }"/>
					<img id="uploadimg3" src="${emkPricePage.dgrImageUrl eq null || emkPricePage.dgrImageUrl eq ''  ? 'images/bjlogo.png':emkPricePage.dgrImageUrl}" width="120" height="120">
					<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
					</t:upload>
					<c:if test="${emkPricePage.dgrImageUrl ne null && emkPricePage.dgrImageUrl ne ''}">[<a href="javascript:findDetail('${emkPricePage.dgrImageUrl }')">${emkPricePage.dgrImage }</a>]</c:if>

					<span id="dgrImageId"></span>
					<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkPricePage.dgrImageUrl }" />
				</td>--%>
				<td align="right">
					<label class="Validform_label">
						是否有尺寸表:
					</label>
				</td>
				<td class="value" >
					<input name="isHaveSize" type="radio" datatype="*" <c:if test="${emkPricePage.isHaveSize eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isHaveSize" type="radio" datatype="*"  <c:if test="${emkPricePage.isHaveSize eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否有尺寸表</label>
				</td>
					<td class="value">
						<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkPricePage.sizeImageUrl }"/>
						<input id="sizeImage" name="sizeImage" type="hidden" value="${emkPricePage.sizeImage }" />
					<span id="sizeImageId">
						<c:if test="${emkPricePage.sizeImageUrl ne null && emkPricePage.sizeImageUrl ne ''}">[<a href="javascript:findDetail('${emkPricePage.sizeImageUrl }')">${emkPricePage.sizeImage }</a>]</c:if>
					</span>
					</td>
					<td class="value" colspan="8">
						<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
						</t:upload>
					</td>
				<%--<td class="value" colspan="3">
					<img id="uploadimg2" src="${emkPricePage.sizeImageUrl eq null || emkPricePage.sizeImageUrl eq '' ? 'images/bjlogo.png':emkPricePage.sizeImageUrl}" width="120" height="120">
					<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
					</t:upload>
					<c:if test="${emkPricePage.sizeImageUrl ne null && emkPricePage.sizeImageUrl ne ''}">[<a href="javascript:findDetail('${emkPricePage.sizeImageUrl }')">${emkPricePage.sizeImage }</a>]</c:if>

					<span id="sizeImageId"></span>
					<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkPricePage.sizeImageUrl }"/>
					<input id="sizeImage" name="sizeImage" type="hidden" value="${emkPricePage.sizeImage }"/>
				</td>--%>
			</tr>


			<tr>
				<td align="right">
					<label class="Validform_label">
						是否需要测试:
					</label>
				</td>
				<td class="value">
					<input name="isTest" type="radio" datatype="*" <c:if test="${emkPricePage.isTest eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isTest" type="radio" datatype="*"  <c:if test="${emkPricePage.isTest eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否需要测试</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						测试说明:
					</label>
				</td>
				<td class="value" colspan="8">
					<input id="testDesc" name="testDesc" type="text" value="${emkPricePage.testDesc }"  style="width: 50%" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">测试说明</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否需要验厂:
					</label>
				</td>
				<td class="value" colspan="10">
					<input name="isChfactory" type="radio" datatype="*" <c:if test="${emkPricePage.isChfactory eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isChfactory"  type="radio" datatype="*"  <c:if test="${emkPricePage.isChfactory eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否需要验厂</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						是否需要验货:
					</label>
				</td>
				<td class="value" colspan="10">
					<input name="isChkhuo" type="radio" datatype="*" <c:if test="${emkPricePage.isChkhuo eq '0'}">checked="true"</c:if> value="0">
					是
					&nbsp;&nbsp;<input name="isChkhuo"  type="radio" datatype="*"  <c:if test="${emkPricePage.isChkhuo eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否需要验货</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						成分要求:
					</label>
				</td>
				<td class="value" colspan="10">
					<input id="chengf" name="chengf" type="text" value="${emkPricePage.chengf }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">成分要求</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						克重要求:
					</label>
				</td>
				<td class="value" colspan="10">
					<input id="weight" name="weight" type="text" value="${emkPricePage.weight }"  style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">克重要求</label>
				</td>
			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						目标价位:
					</label>
				</td>
				<td class="value" colspan="10">
					<input id="targetPrice" name="targetPrice" value="${emkPricePage.targetPrice }" type="text" datatype="d" style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">目标价位</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						预计数量:
					</label>
				</td>
				<td class="value" colspan="10">
					<input id="evlateTotal" name="evlateTotal" value="${emkPricePage.evlateTotal }"  datatype="n" type="text" style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">预计数量</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						尺码范围:
					</label>
				</td>
				<td class="value" colspan="10">
					<input id="sizeFawei" name="sizeFawei" value="${emkPricePage.sizeFawei }"  type="text" style="width: 100px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">尺码范围</label>
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
		$("#gyzl").css("width","105px");
		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 100,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>