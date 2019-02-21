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

			$("#uploadimg2").attr('src',d.attributes.url);

		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#dgrImageUrl").val(d.attributes.url);
			$("#dgrImage").val(d.attributes.name);
			$("#dgrImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

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
<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>

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
			<td align="right" rowspan="6" style="width: 18%">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="6" style="width: 32%" valign="middle" align="center">
				<input id="customSample" name="customSample" value="${emkEnquiryPage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkEnquiryPage.customSampleUrl eq null ? 'images/bjlogo.png':emkEnquiryPage.customSampleUrl}" width="200" height="200">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
				<c:if test="${emkEnquiryPage.customSampleUrl ne null && emkEnquiryPage.customSampleUrl ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.customSampleUrl }')">${emkEnquiryPage.customSample }</a>]</c:if>
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkEnquiryPage.customSampleUrl }"/>
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
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
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
	<%--<div id="detailId" style="width: auto; height: 200px;" >&lt;%&ndash; 增加一个div，用于调节页面大小，否则默认太小 &ndash;%&gt;
    </div>--%>
	<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkEnquiryController.do?orderMxList&proOrderId=${emkEnquiryPage.id }" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<!-- 添加明细模版-->
	<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
		<tbody id="add_jeecgOrderProduct_table_template">
		<tr>
			<td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
			<td align="center">
				<select name="orderMxList[#index#].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
					<c:forEach items="${colorList}" var="category">
						<option value="${category.typecode}">${category.typename}</option>
					</c:forEach>
				</select>
			</td>
			<td align="center">
				<select name="orderMxList[#index#].colorNum" style="width: 86%;" nullmsg="请输入色号！" errormsg="请输入色号" datatype="*">
					<c:forEach items="${colorNumList}" var="category">
						<option value="${category.typecode}">${category.typename}</option>
					</c:forEach>
				</select>
			</td>
			<td align="center"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal" maxlength="100" type="text" value=""
									  style="width: 86%;" ignore="ignore"></td>
			<td align="center"><input id="signTotal01" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal01" maxlength="100" type="text" value=""
									  style="width: 86%;" ignore="ignore"></td>
			<td align="center"><input id="signTotal02" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal02" maxlength="100" type="text" value=""
									  style="width: 86%;" ignore="ignore"></td>
			<td align="center"><input id="signTotal03" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal03" maxlength="100" type="text" value=""
									  style="width: 86%;" ignore="ignore"></td>
			<td align="center"><input id="signTotal04" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal04" maxlength="100" type="text" value=""
									  style="width: 86%;" ignore="ignore"></td>
		</tr>
		</tbody>

	</table>

	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
			<%--<tr height="36">
                <td align="right" >
                    <label class="Validform_label">
                        是否先打样:
                    </label>
                </td>
                <td class="value" colspan="3">
                    <input name="isPrint" type="radio"  datatype="*" checked="true"value="0">
                    否
                    &nbsp;&nbsp;<input name="isPrint"  type="radio" datatype="*"  value="1">
                    是
                    <span class="Validform_checktip"></span>
                    <label class="Validform_label" style="display: none;">是否先打样</label>
                </td>
            </tr>--%>
		<tr height="36">
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					是否有原样:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input name="isHaveOld" type="radio" datatype="*" <c:if test="${emkEnquiryPage.isHaveOld eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveOld" type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isHaveOld eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有原样</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					是否有设计稿:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input name="isHaveDgr" type="radio" datatype="*" <c:if test="${emkEnquiryPage.isHaveDgr eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveDgr"  type="radio" datatype="*"  <c:if test="${emkEnquiryPage.isHaveDgr eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有设计稿</label>
			</td>

		</tr>

		<tr id="dgrImageDiv" height="36">
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value" >
				<input id="oldImage" name="oldImage" type="hidden" value="${emkEnquiryPage.oldImage }"/>
				<img id="uploadimg" src="${emkEnquiryPage.oldImageUrl eq '' ? 'images/bjlogo.png':emkEnquiryPage.oldImageUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload><c:if test="${emkEnquiryPage.oldImage ne null && emkEnquiryPage.oldImage ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.oldImageUrl }')">${emkEnquiryPage.oldImage }</a>]</c:if>
				<span id="oldImageId"></span>
				<input id="oldImageUrl" name="oldImageUrl" type="hidden" value="${emkEnquiryPage.oldImageUrl }"/>
			</td>

			<td align="right">
				<label class="Validform_label">
					设计稿:
				</label>
			</td>
			<td class="value">
				<input id="dgrImage" name="dgrImage" type="hidden" value="${emkEnquiryPage.dgrImage }"/>
				<img id="uploadimg3" src="${emkEnquiryPage.dgrImageUrl eq '' ? 'images/bjlogo.png':emkEnquiryPage.dgrImageUrl}" width="150" height="150">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload><c:if test="${emkEnquiryPage.dgrImageUrl ne null && emkEnquiryPage.dgrImageUrl ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.dgrImageUrl }')">${emkEnquiryPage.dgrImage }</a>]</c:if>
				<span id="dgrImageId"></span>
				<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" value="${emkEnquiryPage.dgrImageUrl }"/>
			</td>

		</tr>
		<tr height="36">

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
			<td class="value" colspan="3">
				<img id="uploadimg2" src="${emkEnquiryPage.sizeImageUrl eq '' ? 'images/bjlogo.png':emkEnquiryPage.sizeImageUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload><c:if test="${emkEnquiryPage.sizeImageUrl ne null && emkEnquiryPage.sizeImageUrl ne ''}">[<a href="javascript:findDetail('${emkEnquiryPage.sizeImageUrl }')">${emkEnquiryPage.sizeImage }</a>]</c:if>
				<span id="sizeImageId"></span>
				<input id="sizeImageUrl" name="sizeImageUrl" type="hidden" value="${emkEnquiryPage.sizeImageUrl }"/>
				<input id="sizeImage" name="sizeImage" type="hidden" value="${emkEnquiryPage.sizeImage }" />
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