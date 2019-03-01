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
			$("#ckImageUrl").val(d.attributes.url);
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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPackController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkPackPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
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
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  />
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
				<input id="proTypeTree" value="">
				<input id="proTypeName" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="kdDate" name="kdDate" readonly value="${kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="ysDate" name="ysDate" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value">
				<input id="productHtNo" name="productHtNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
		</tr>
		<%--<tr>
			<td align="right">
				<label class="Validform_label">
					布面克重:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
		</tr>--%>
		<tr>
			<td align="right">
				<label class="Validform_label">
					规格:
				</label>
			</td>
			<td class="value">
				<input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">规格</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					目标价位:
				</label>
			</td>
			<td class="value">
				<input id="targetPrice" name="targetPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
			<td class="value">
				<input id="total" name="total" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					开发完成交期:
				</label>
			</td>
			<td class="value">
				<input id="endDate" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开发完成交期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					距交期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="levelDays" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="default" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					是否有设计稿:
				</label>
			</td>
			<td class="value" colspan="3">
				<input name="isHaveDgr" type="radio" datatype="*" <c:if test="${emkPackPage.isHaveDgr eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveDgr"  type="radio" datatype="*"  <c:if test="${emkPackPage.isHaveDgr eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有设计稿</label>
			</td>
		</tr>

		<tr id="dgrImageDiv" >
			<td align="right">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value">
				<input id="customSample" name="customSample" type="hidden" />
				<img id="uploadimg0" src="${emkPackPage.customSampleUrl eq null ? 'images/bjlogo.png':emkPackPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					设计稿:
				</label>
			</td>
			<td class="value">
				<input id="dgrImage" name="dgrImage" type="hidden" />
				<img id="uploadimg3" src="${emkPackPage.dgrImageUrl eq null ? 'images/bjlogo.png':emkPackPage.dgrImageUrl}" width="150" height="150">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>
				<span id="dgrImageId"></span>
				<input id="dgrImageUrl" name="dgrImageUrl" type="hidden" />
			</td>



		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有原样:
				</label>
			</td>
			<td class="value">
				<input name="isHaveOld" type="radio" datatype="*" <c:if test="${emkPackPage.isHaveOld eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHaveOld" type="radio" datatype="*"  <c:if test="${emkPackPage.isHaveOld eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有原样</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否有参考图片:
				</label>
			</td>
			<td class="value">
				<input name="isCkImage" type="radio" datatype="*" <c:if test="${emkPackPage.isCkImage eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isCkImage" type="radio" datatype="*"  <c:if test="${emkPackPage.isCkImage eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有参考图片</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value">
				<input id="oldImage" name="oldImage" type="hidden" />
				<img id="uploadimg" src="${emkPackPage.oldImageUrl eq null ? 'images/bjlogo.png':emkPackPage.oldImageUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>
				<span id="oldImageId"></span>
				<input id="oldImageUrl" name="oldImageUrl" type="hidden" />
			</td>

			<td align="right">
				<label class="Validform_label">
					尺寸表:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg2" src="${emkPackPage.ckImageUrl eq null ? 'images/bjlogo.png':emkPackPage.ckImageUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>
				<span id="sizeImageId"></span>
				<input id="ckImageUrl" name="ckImageUrl" type="hidden" />
				<input id="ckImage" name="ckImage" type="hidden" />
			</td>

		</tr>


		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>



		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc"></textarea>
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
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark"></textarea>
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