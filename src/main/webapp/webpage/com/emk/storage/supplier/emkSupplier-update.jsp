<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>供应商管理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">

		//编写自定义JS代码
		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#licenceUrl").val(d.attributes.url);
			$("#licenceName").val(d.attributes.name);
			$("#licenceImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#factoryInfoUrl").val(d.attributes.url);
			$("#factoryInfoName").val(d.attributes.name);
			$("#factoryInfoImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg1").attr('src',d.attributes.url);

		}
	</script>
</head>
<body>
<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSupplierController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSupplierPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商代码:
				</label>
			</td>
			<td class="value" colspan="5">
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
			<td class="value" colspan="5">
				<t:dictSelect id="supplierType" field="supplierType" typeGroupCode="gyslx" datatype="*" defaultVal="${emkSupplierPage.supplierType }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商类型</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					产品类型:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="productType" name="productType" value="${emkSupplierPage.productType }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					企业全称:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="supplier" name="supplier" value="${emkSupplierPage.supplier }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">企业全称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value" colspan="5">
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
			<td class="value" colspan="5">
				<input id="taxpayerNum" name="taxpayerNum" value="${emkSupplierPage.taxpayerNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">纳税人识别号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					开户行:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="bankName" name="bankName" value="${emkSupplierPage.bankName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
			<td class="value" colspan="5">
				<input id="bankAccount" name="bankAccount" value="${emkSupplierPage.bankAccount }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户账号 </label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="telphone" name="telphone" type="text" value="${emkSupplierPage.telphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="legaler" name="legaler" type="text" value="${emkSupplierPage.legaler }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="legalerTelphone" name="legalerTelphone" value="${emkSupplierPage.legalerTelphone }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					邮箱:
				</label>
			</td>
			<td class="value">
				<input id="legalerEmail" name="legalerEmail" value="${emkSupplierPage.legalerEmail }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">邮箱</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="contacter" name="contacter" type="text" value="${emkSupplierPage.bankAccount }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="contacterTelphone" name="contacterTelphone" value="${emkSupplierPage.bankAccount }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					邮箱:
				</label>
			</td>
			<td class="value">
				<input id="contacterEmail" name="contacterEmail" type="text" value="${emkSupplierPage.bankAccount }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">邮箱</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					财务联系人:
				</label>
			</td>
			<td class="value">
				<input id="cwContacter" name="cwContacter" type="text" value="${emkSupplierPage.bankAccount }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">财务联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="cwContacterTelphone" name="cwContacterTelphone" value="${emkSupplierPage.bankAccount }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					邮箱:
				</label>
			</td>
			<td class="value">
				<input id="cwContacterEmail" name="cwContacterEmail" type="text" value="${emkSupplierPage.bankAccount }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">邮箱</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					营业执照:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg" src="${emkSupplierPage.licenceUrl eq null ? 'images/bjlogo.png':emkSupplierPage.licenceUrl}" width="150" height="150">
				<t:upload name="instruction1" id="instruction1" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>
				<c:if test="${emkSupplierPage.licenceUrl ne null && emkSupplierPage.licenceUrl ne ''}">[<a href="javascript:findDetail('${emkSupplierPage.licenceUrl }')">${emkSupplierPage.licenceName }</a>]</c:if>
				<span id="licenceImageId"></span>
				<input id="licenceUrl" name="licenceUrl" type="hidden" value="${emkSupplierPage.licenceUrl }"/>
				<input id="licenceName" name="licenceName" type="hidden" value="${emkSupplierPage.licenceName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">营业执照</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					工厂信息表:
				</label>
			</td>
			<td class="value" colspan="3">
				<img id="uploadimg1" src="${emkSupplierPage.factoryInfoUrl eq null ? 'images/bjlogo.png':emkSupplierPage.factoryInfoUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess1" >
				</t:upload>
				<c:if test="${emkSupplierPage.factoryInfoUrl ne null && emkSupplierPage.factoryInfoUrl ne ''}">[<a href="javascript:findDetail('${emkSupplierPage.factoryInfoUrl }')">${emkSupplierPage.factoryInfoName }</a>]</c:if>
				<span id="factoryInfoImageId"></span>
				<input id="factoryInfoUrl" name="factoryInfoUrl" type="hidden" value="${emkSupplierPage.factoryInfoUrl }"/>
				<input id="factoryInfoName" name="factoryInfoName" type="hidden" value="${emkSupplierPage.factoryInfoName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂信息表</label>
			</td>
		</tr>

		<tr>
			<td colspan="6" id="instructionfile" class="value">
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/supplier/emkSupplier.js"></script>
