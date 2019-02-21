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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPriceController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkPricePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					询盘单号:
				</label>
			</td>
			<td class="value" >
				<input id="xpNo" name="xpNo" datatype="*" value="${emkPricePage.xpNo }" validType="emk_price,xp_no,id"  type="text" style="width: 140px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">询盘单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					打样通知单号:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="dyNo" name="dyNo"  value="${emkPricePage.dyNo }" type="text" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样通知单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					报价单号:
				</label>
			</td>
			<td class="value" >
				<input id="pirceNo" name="pirceNo" value="${emkPricePage.pirceNo }" readonly type="text" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" value="${emkPricePage.cusNum }" readonly type="text" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly value="${emkPricePage.cusName }" type="text" style="width: 140px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,bz,guoJia" isclear="true" isInit="true"></t:choose>
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
				<input id="businesseDeptName" name="businesseDeptName" value="${emkPricePage.businesseDeptName }" readonly type="text" style="width: 140px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkPricePage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId"  >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkPricePage.businesser }" type="hidden" style="width: 140px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkPricePage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkPricePage.proTypeName }" >
				<input id="proTypeName" name="proTypeName" datatype="*" value="${emkPricePage.proTypeName }" type="hidden" style="width: 140px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkPricePage.proType }" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" readonly type="text" value="${emkPricePage.sampleNo }" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					报价日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkPricePage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 140px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					报价有效期:
				</label>
			</td>
			<td class="value">
				<input id="limitDate" name="limitDate" readonly value="${emkPricePage.limitDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 140px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价有效期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					目的国:
				</label>
			</td>
			<td class="value">
				<input id="guoJia" name="guoJia"  type="text" value="${emkPricePage.guoJia }" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">目的国</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					报价币种:
				</label>
			</td>
			<td class="value">
				<input id="bz" name="bz" readonly type="text" value="${emkPricePage.bz }" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价币种</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkPricePage.gyzl}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>

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
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" >
				<input id="customSample" name="customSample" type="hidden" value="${emkPricePage.customSample }"/>
				<img id="uploadimg0" src="${emkPricePage.customSampleUrl eq '' ? 'images/bjlogo.png':emkPricePage.customSampleUrl}" width="140" height="140">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkPricePage.customSampleUrl }')">${emkPricePage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkPricePage.customSampleUrl }" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					设计稿:
				</label>
			</td>
			<td class="value">
				<input id="dgrImage" name="dgrImage" type="hidden" value="${emkPricePage.dgrImage }"/>
				<img id="uploadimg3" src="${emkPricePage.dgrImageUrl eq '' ? 'images/bjlogo.png':emkPricePage.dgrImageUrl}" width="140" height="140">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>[<a href="javascript:findDetail('${emkPricePage.dgrImageUrl }')">${emkPricePage.dgrImage }</a>]
				<span id="dgrImageId"></span>
				<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkPricePage.dgrImageUrl }"/>
			</td>
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value">
				<input id="oldImage" name="oldImage" type="hidden" value="${emkPricePage.oldImage }"/>
				<img id="uploadimg" src="${emkPricePage.oldImageUrl eq '' ? 'images/bjlogo.png':emkPricePage.oldImageUrl}" width="140" height="140">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>[<a href="javascript:findDetail('${emkPricePage.oldImageUrl }')">${emkPricePage.oldImage }</a>]
				<span id="oldImageId"></span>
				<input id="oldImageUrl" name="oldImageUrl" type="hidden" value="${emkPricePage.oldImageUrl }"/>
			</td>

			<td align="right">
				<label class="Validform_label">
					尺寸表:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg2" src="${emkPricePage.sizeImageUrl eq '' ? 'images/bjlogo.png':emkPricePage.sizeImageUrl}" width="140" height="140">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>[<a href="javascript:findDetail('${emkPricePage.sizeImageUrl }')">${emkPricePage.sizeImage }</a>]
				<span id="sizeImageId"></span>
				<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkPricePage.sizeImageUrl }"/>
				<input id="sizeImage" name="sizeImage" type="hidden" value="${emkPricePage.sizeImage }"/>
			</td>

		</tr>


		<tr>
			<td colspan="8" id="instructionfile" class="value">
			</td>
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
					是否需要验厂:
				</label>
			</td>
			<td class="value">
				<input name="isChkhuo" type="radio" datatype="*" <c:if test="${emkPricePage.isChkhuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isChkhuo"  type="radio" datatype="*"  <c:if test="${emkPricePage.isChkhuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否需要验厂</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否需要验货:
				</label>
			</td>
			<td class="value" colspan="3">
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
					克重要求:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight" type="text" style="width: 140px" value="${emkPricePage.weight }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">克重要求</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					成分要求:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf" type="text" style="width: 140px" value="${emkPricePage.chengf }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">成分要求</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					目标价位:
				</label>
			</td>
			<td class="value">
				<input id="targetPrice" name="targetPrice" type="text" datatype="d" value="${emkPricePage.targetPrice }" style="width: 140px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">目标价位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					预计数量:
				</label>
			</td>
			<td class="value">
				<input id="evlateTotal" name="evlateTotal"  datatype="n" type="text" value="${emkPricePage.evlateTotal }" style="width: 140px" class="inputxt"  ignore="ignore" />
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
			<td class="value" colspan="7">
				<input id="sizeFawei" name="sizeFawei" type="text" style="width: 140px" value="${emkPricePage.sizeFawei }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码范围</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkPricePage.sampleNoDesc }</textarea>
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
					下机克重:
				</label>
			</td>
			<td class="value">
				<input id="xjkz" name="xjkz" datatype="d" type="text" value="${emkPricePage.xjkz }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单件所需时间:
				</label>
			</td>
			<td class="value">
				<input id="djsxTime" name="djsxTime"  type="text"  value="${emkPricePage.djsxTime }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件所需时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit"  type="text" style="width: 140px" value="${emkPricePage.unit }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					机台日产量:
				</label>
			</td>
			<td class="value">
				<input id="jtrcl" name="jtrcl" datatype="n" type="text" style="width: 140px" value="${emkPricePage.jtrcl }" ignore="ignore" />
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
				<input id="qdshl" name="qdshl" datatype="d"  type="text" style="width: 140px" value="${emkPricePage.qdshl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">前道损耗率</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					后道损耗率:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="hdshl" name="hdshl"  datatype="d" type="text" style="width: 140px" value="${emkPricePage.hdshl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">后道损耗率</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					原料成本小计:
				</label>
			</td>
			<td class="value">
				<input id="sumYl" name="sumYl" datatype="d" readonly type="text" style="width: 140px" value="${emkPricePage.sumYl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料面料成本小计</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					缝制成本小计:
				</label>
			</td>
			<td class="value">
				<input id="sumFeng" name="sumFeng" datatype="d" readonly type="text" style="width: 140px" value="${emkPricePage.sumFeng }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制材料成本小计</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					包装成本小计:
				</label>
			</td>
			<td class="value">
				<input id="sumBao" name="sumBao" datatype="d" readonly type="text" style="width: 140px" value="${emkPricePage.sumBao }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装材料成本小计</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					人工成本小计:
				</label>
			</td>
			<td class="value">
				<input id="sumRg" name="sumRg" datatype="d" readonly type="text" style="width: 140px" value="${emkPricePage.sumRg }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">人工成本小计</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					染色成本小计:
				</label>
			</td>
			<td class="value">
				<input id="sumRan" name="sumRan" datatype="d" readonly type="text" style="width: 140px" value="${emkPricePage.sumRan }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染色成本小计</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					印花成本小计:
				</label>
			</td>
			<td class="value">
				<input id="sumYin" name="sumYin" datatype="d"  readonly type="text" value="${emkPricePage.sumYin }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">印花成本小计</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					管理成本:
				</label>
			</td>
			<td class="value">
				<input id="glMoney" name="glMoney" datatype="d" type="text" value="${emkPricePage.glMoney }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">管理成本</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试成本小计:
				</label>
			</td>
			<td class="value">
				<input id="testMoney" name="testMoney" datatype="d" type="text" value="${emkPricePage.testMoney }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试成本小计</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					不可预见成本:
				</label>
			</td>
			<td class="value">
				<input id="unableMoney" name="unableMoney" datatype="d" value="${emkPricePage.unableMoney }" type="text" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">不可预见成本成本</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					税收:
				</label>
			</td>
			<td class="value">
				<input id="tax" name="tax"  type="text" datatype="d" readonly style="width: 140px" value="${emkPricePage.tax }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">税收</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					利润:
				</label>
			</td>
			<td class="value">
				<input id="profit" name="profit" datatype="d" type="text" style="width: 140px" value="${emkPricePage.profit }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">利润</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总计:
				</label>
			</td>
			<td class="value">
				<input id="sumMoney" name="sumMoney" datatype="d" readonly  type="text" style="width: 140px" value="${emkPricePage.sumMoney }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总计</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					汇率:
				</label>
			</td>
			<td class="value">
				<input id="huilv" name="huilv" datatype="d" type="text" style="width: 140px" value="${emkPricePage.huilv }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">汇率</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					汇率日期:
				</label>
			</td>
			<td class="value">
				<input id="huilvDate" name="huilvDate" readonly value="${emkPricePage.huilvDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" class="Wdate"  style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">汇率日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<input id="bizhong" name="bizhong" type="text" style="width: 140px" value="${emkPricePage.bizhong }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					外币价:
				</label>
			</td>
			<td class="value">
				<input id="sumWb" name="sumWb" type="text" style="width: 140px" readonly value="${emkPricePage.sumWb }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">外币价</label>
			</td>

		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					实际最终同意价:
				</label>
			</td>
			<td class="value">
				<input id="argeePrice" name="argeePrice" datatype="d" type="text" value="${emkPricePage.argeePrice }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际最终同意价</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					实际毛利润率:
				</label>
			</td>
			<td class="value">
				<input id="maoRate" name="maoRate"  datatype="d" type="text" value="${emkPricePage.maoRate }" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际毛利润率</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商同意价:
				</label>
			</td>
			<td class="value">
				<input id="gysArgeePrice" name="gysArgeePrice" datatype="d" value="${emkPricePage.gysArgeePrice }" type="text" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商同意价</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					客户同意价:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusArgeePrice" name="cusArgeePrice" datatype="d" value="${emkPricePage.cusArgeePrice }" type="text" style="width: 140px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户同意价</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkPricePage.remark }</textarea>
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
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");

		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 147,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>