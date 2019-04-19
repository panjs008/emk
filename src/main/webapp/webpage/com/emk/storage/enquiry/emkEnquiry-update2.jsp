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
		$(document).ready(function(){
			//$("#detailId").load("emkEnquiryController.do?orderMxList&proOrderId=${emkEnquiryPage.id }");
			$("#pstate").val("${samplePriceEntity.pstate }");
		});

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
	<style>
		.table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
		.table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
	</style>

</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkEnquiryController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkEnquiryPage.id }"/>
	<table style="width: 100%;" id="etableid" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					意向订单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="enquiryNo" name="enquiryNo" value="${emkEnquiryPage.enquiryNo }"  readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">意向订单号</label>
			</td>
		</tr>

		<tr>
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkEnquiryPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesseDeptName" name="businesseDeptName" value="${emkEnquiryPage.businesseDeptName }"  type="hidden"   />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkEnquiryPage.businesseDeptId }"  type="hidden"  />
				<input id="businesser" name="businesser" value="${emkEnquiryPage.businesser }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkEnquiryPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkEnquiryPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkEnquiryPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产跟单员</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly value="${emkEnquiryPage.tracer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName" value="${emkEnquiryPage.tracerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务跟单员</label>
			</td>
		</tr>
		<tr height="36">
			<td align="right">
				<label class="Validform_label">
					意向大货交期:
				</label>
			</td>
			<td class="value">
				<input id="ysDate" name="ysDate" readonly value="${emkEnquiryPage.ysDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">意向大货交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距交期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="levelDays" readonly type="text" style="width: 150px" value="${emkEnquiryPage.levelDays }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" datatype="*" value="${emkEnquiryPage.cusName }" readonly type="text" style="width: 150px" class="inputxt" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right" rowspan="6" style="width: 18%">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="6" style="width: 32%" valign="middle" align="center">
				<input id="customSample" name="customSample" value="${emkEnquiryPage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkEnquiryPage.customSampleUrl eq null ? 'images/bjlogo.png':emkEnquiryPage.customSampleUrl}" width="200" height="200">
				<br/><c:if test="${emkEnquiryPage.customSampleUrl ne null && emkEnquiryPage.customSampleUrl ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.customSampleUrl }')">${emkEnquiryPage.customSample }</a>]</c:if>
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkEnquiryPage.customSampleUrl }"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly value="${emkEnquiryPage.cusNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkEnquiryPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>


		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺种类:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkEnquiryPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺种类</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" type="text" value="${emkEnquiryPage.sampleNoDesc }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkEnquiryPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" value="${emkEnquiryPage.proTypeName }" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" value="${emkEnquiryPage.proType }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
		</tr>

		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>
	</table>
	<div class="table-c" style="margin-top: 4px;">
		<table id="mxtb" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="#F8F8F8" style="height: 32px;" >
				<td align="center"  width="40" rowspan="2"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
				<td align="center" width="40" rowspan="2">序号</td>
				<td align="center"  width="150" rowspan="2">颜色</td>
				<td align="center"  width="150" rowspan="2">色号</td>
				<td align="center"  width="400" colspan="11" >尺码</td>
			</tr>
			<tr>
				<td align="center" ><input  value="${emkSizePage.sizeA}" name="sizeA" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeB}" name="sizeB" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeC}" name="sizeC" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeD}" name="sizeD" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeE}" name="sizeE" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeF}" name="sizeF" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeG}" name="sizeG" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeH}" name="sizeH" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeI}" name="sizeI" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeJ}" name="sizeJ" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
				<td align="center" ><input  value="${emkSizePage.sizeK}" name="sizeK" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
			</tr>
			<tbody id="add_jeecgOrderProduct_table">
			<c:if test="${fn:length(emkProOrderDetailEntities)  > 0 }">
				<c:forEach items="${emkProOrderDetailEntities}" var="poVal" varStatus="status">
					<tr>
						<td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
						</td>
						<td align="center" width="40">${status.index+1}</td>

						<td align="center">
							<select name="orderMxList[${status.index}].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
								<c:forEach items="${colorList}" var="category">
									<option value="${category.typecode}" ${category.typecode eq poVal.color ? 'selected':''}>${category.typename}</option>
								</c:forEach>
							</select>
						</td>
						<td align="center">
							<select name="orderMxList[${status.index}].colorNum" style="width: 86%;" nullmsg="请输入色号！" errormsg="请输入色号" datatype="*">
								<c:forEach items="${colorNumList}" var="category">
									<option value="${category.typecode}" ${category.typecode eq poVal.colorValue ? 'selected':''}>${category.typename}</option>
								</c:forEach>
							</select></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" value="${poVal.emkSizeTotalEntity.totalA}" name="orderMxList[${status.index}].totalA" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalB}" name="orderMxList[${status.index}].totalB" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalC}" name="orderMxList[${status.index}].totalC" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalD}" name="orderMxList[${status.index}].totalD" maxlength="100" type="text" value=""
												  style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalE}" name="orderMxList[${status.index}].totalE" maxlength="100" type="text" value=""
												  style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalF}" name="orderMxList[${status.index}].totalF" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalG}" name="orderMxList[${status.index}].totalG" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalH}" name="orderMxList[${status.index}].totalH" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalI}" name="orderMxList[${status.index}].totalI" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalJ}" name="orderMxList[${status.index}].totalJ" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>
						<td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalK}" name="orderMxList[${status.index}].totalK" maxlength="100" type="text" value=""
												   style="width: 86%;" ignore="ignore"></td>


					</tr>

				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>

	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr height="36">
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					是否有原样:
				</label>
			</td>
			<td class="value" style="width: 32%" colspan="3">
				<input name="isHaveOld" type="radio" datatype="*" <c:if test="${emkEnquiryPage.isHaveOld eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveOld" type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isHaveOld eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有原样</label>
			</td>


		</tr>

		<tr id="dgrImageDiv" height="36">
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="oldImage" name="oldImage" type="hidden" value="${emkEnquiryPage.oldImage }"/>
				<img id="uploadimg" onclick="findDetail('${emkEnquiryPage.oldImageUrl }')" src="${emkEnquiryPage.oldImageUrl eq '' ? 'images/bjlogo.png':emkEnquiryPage.oldImageUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload><c:if test="${emkEnquiryPage.oldImage ne null && emkEnquiryPage.oldImage ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.oldImageUrl }')">${emkEnquiryPage.oldImage }</a>]</c:if>
				<span id="oldImageId"></span>
				<input id="oldImageUrl" name="oldImageUrl" type="hidden" value="${emkEnquiryPage.oldImageUrl }"/>
			</td>

		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					是否有设计稿:
				</label>
			</td>
			<td class="value" style="width: 32%" colspan="3">
				<input name="isHaveDgr" type="radio" datatype="*" <c:if test="${emkEnquiryPage.isHaveDgr eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveDgr"  type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isHaveDgr eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有设计稿</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					设计稿:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="dgrImage" name="dgrImage" type="hidden" value="${emkEnquiryPage.dgrImage }"/>
				<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkEnquiryPage.dgrImageUrl }"/>
						<span id="dgrImageId">
							<c:if test="${emkEnquiryPage.dgrImageUrl ne null && emkEnquiryPage.dgrImageUrl ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.dgrImageUrl }')">${emkEnquiryPage.dgrImage }</a>]</c:if>
						</span>
			</td>
			<td class="value" colspan="2">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>
			</td>
		</tr>

		<tr >
			<td align="right">
				<label class="Validform_label">
					是否有尺寸表:
				</label>
			</td>
			<td class="value" colspan="3">
				<input name="isHaveSize" type="radio" datatype="*" <c:if test="${emkEnquiryPage.isHaveSize eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveSize" type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isHaveSize eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有尺寸表</label>
			</td>
		</tr>
		<tr height="36">
			<td align="right">
				<label class="Validform_label">
					尺寸表:
				</label>
			</td>
			<td class="value">
				<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkEnquiryPage.sizeImageUrl }"/>
				<input id="sizeImage" name="sizeImage" type="hidden" value="${emkEnquiryPage.sizeImage }" />
					<span id="sizeImageId">
						<c:if test="${emkEnquiryPage.sizeImageUrl ne null && emkEnquiryPage.sizeImageUrl ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.sizeImageUrl }')">${emkEnquiryPage.sizeImage }</a>]</c:if>
					</span>
			</td>
			<td class="value" colspan="2">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>
			</td>
		</tr>

		<tr>
			<td colspan="4" id="instructionfile2" class="value">
			</td>
		</tr>

		<tr height="36">
			<td align="right">
				<label class="Validform_label">
					是否打过初样:
				</label>
			</td>
			<td class="value">
				<input name="isPrintSample" type="radio" datatype="*" <c:if test="${emkEnquiryPage.isPrintSample eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isPrintSample" type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isPrintSample eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否打过初样</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否收取打样费:
				</label>
			</td>
			<td class="value">
				<input name="isGetSample" type="radio" onclick="showPriceDiv(0)" datatype="*" <c:if test="${emkEnquiryPage.isGetSample eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isGetSample" onclick="showPriceDiv(1)"  type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isGetSample eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否收取打样费</label>
			</td>
		</tr>
		<tr id="priceDiv" style="display: none;">
			<td colspan="4">
				<table style="width: 100%;font-size: 12px;" border="0" cellpadding="0" cellspacing="1" >
					<tr>
						<td align="right" style="height:12px;">
							<label class="Validform_label">
								金额:
							</label>
						</td>
						<td class="value">
							<input id="money" name="money" value="${samplePriceEntity.money }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								币种:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="pbz" field="pbz" typeGroupCode="cointype" defaultVal="${samplePriceEntity.pbz }" hasLabel="false" title="币种"></t:dictSelect>
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
								<option value="0">未到账</option>
								<option value="1">已到账</option>
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
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkEnquiryPage.remark }</textarea>
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
	});
</script>