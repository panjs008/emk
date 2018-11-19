<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>供应商管理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#licenceUrl").val(d.attributes.url);
			$("#licenceImageId").html(d.attributes.name);
			$("#uploadimg").attr('src',d.attributes.url);
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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSupplierController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSupplierPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					企业全称:
				</label>
			</td>
			<td class="value">
				<input id="supplier" name="supplier" value="${emkSupplierPage.supplier }"type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">企业全称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商代码:
				</label>
			</td>
			<td class="value">
				<input id="supplierCode" name="supplierCode" value="${emkSupplierPage.supplierCode }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商代码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="supplierType" field="supplierType" typeGroupCode="gyslx" datatype="*" defaultVal="${emkSupplierPage.supplierType }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					营业执照号:
				</label>
			</td>
			<td class="value">
				<input id="licence" name="licence" type="text" value="${emkSupplierPage.licence }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">营业执照号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					产品类型:
				</label>
			</td>
			<td class="value">
				<input id="productType" name="productType" value="${emkSupplierPage.productType }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					有效期:
				</label>
			</td>
			<td class="value">
				<input id="limitDate" name="limitDate" readonly value="${emkSupplierPage.limitDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">有效期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="address" style="width:95%;height:70px" class="inputxt" rows="5" name="address">${emkSupplierPage.address }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					纳税人识别号:
				</label>
			</td>
			<td class="value">
				<input id="taxpayerNum" name="taxpayerNum" type="text" style="width: 150px" value="${emkSupplierPage.taxpayerNum }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">纳税人识别号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					开户行:
				</label>
			</td>
			<td class="value">
				<input id="bankName" name="bankName" type="text" style="width: 150px" value="${emkSupplierPage.bankName }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户行</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					开户账号:
				</label>
			</td>
			<td class="value">
				<input id="bankAccount" name="bankAccount" type="text" style="width: 150px" value="${emkSupplierPage.bankAccount }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户账号 </label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" style="width: 150px" value="${emkSupplierPage.telphone }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					法定代表人:
				</label>
			</td>
			<td class="value">
				<input id="legaler" name="legaler" type="text" style="width: 150px" value="${emkSupplierPage.legaler }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="contacter" name="contacter" type="text" style="width: 150px" value="${emkSupplierPage.contacter }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					财务联系人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cwContacter" name="cwContacter" type="text" style="width: 150px" value="${emkSupplierPage.cwContacter }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">财务联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					营业执照URL:
				</label>
			</td>
			<td class="value" colspan="3">
				<img id="uploadimg" src="${emkSupplierPage.licenceUrl eq '' ? 'images/bjlogo.png':emkSupplierPage.licenceUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>[<a href="javascript:findDetail('${emkSupplierPage.licenceUrl }')">查看</a>]
				<span id="licenceImageId"></span>
				<input id="licenceUrl" name="licenceUrl" type="hidden" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">营业执照URL</label>
			</td>

		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/supplier/emkSupplier.js"></script>
