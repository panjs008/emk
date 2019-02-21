<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户手册</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#peopleFileUrl").val(d.attributes.url);
			$("#peopleFile").val(d.attributes.name);
			$("#peopleFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function downloadFile(url) {
			window.open("${webRoot}/"+url);
		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#zlFileUrl").val(d.attributes.url);
			$("#zlFile").val(d.attributes.name);
			$("#zlFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#fkFileUrl").val(d.attributes.url);
			$("#fkFile").val(d.attributes.name);
			$("#fkFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#checkHuoFileUrl").val(d.attributes.url);
			$("#checkHuoFile").val(d.attributes.name);
			$("#checkHuoFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess4(d,file,response){
			var src = d.attributes.url;
			$("#testFileUrl").val(d.attributes.url);
			$("#testFile").val(d.attributes.name);
			$("#testFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess5(d,file,response){
			var src = d.attributes.url;
			$("#pactFileUrl").val(d.attributes.url);
			$("#pactFile").val(d.attributes.name);
			$("#pactFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess6(d,file,response){
			var src = d.attributes.url;
			$("#sampleFileUrl").val(d.attributes.url);
			$("#sampleFile").val(d.attributes.name);
			$("#sampleFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess7(d,file,response){
			var src = d.attributes.url;
			$("#sendHuoFileUrl").val(d.attributes.url);
			$("#sendHuoFile").val(d.attributes.name);
			$("#sendHuoFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkCustomUseController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkCustomUsePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户手册编号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="kyscbh" name="kyscbh"  type="text" readonly value="${emkCustomUsePage.kyscbh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">客户手册编号</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					验厂员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="checkUser" name="checkUser"  type="text" value="${emkCustomUsePage.checkUser }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">验厂员</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkCustomUsePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkCustomUsePage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
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
				<input id="businesseDeptName" name="businesseDeptName" value="${emkCustomUsePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkCustomUsePage.businesseDeptId }" type="hidden"  />
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
				<input id="businesser" name="businesser" readonly type="hidden" value="${emkCustomUsePage.businesser }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  value="${emkCustomUsePage.businesserName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>


		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%" colspan="3">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkCustomUsePage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkCustomUsePage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有人权验厂手册:
				</label>
			</td>
			<td class="value">
				<input name="isPeopleAdvice" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isPeopleAdvice eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isPeopleAdvice" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isPeopleAdvice eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有人权验厂手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					人权验厂手册:
				</label>
			</td>
			<td class="value">
				<input id="peopleFile" name="peopleFile" type="hidden" value="${emkCustomUsePage.peopleFile }" />
				<input id="peopleFileUrl" name="peopleFileUrl" type="hidden" value="${emkCustomUsePage.peopleFileUrl }" />
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload><c:if test="${emkCustomUsePage.peopleFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.peopleFileUrl }')">${emkCustomUsePage.peopleFile }</a>]</c:if><span id="peopleFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					人权验厂说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="peopleAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="peopleAdvice">${emkCustomUsePage.peopleAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">人权验厂说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有质量验厂手册:
				</label>
			</td>
			<td class="value">
				<input name="isZlAdvice" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isZlAdvice eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isZlAdvice" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isZlAdvice eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有质量验厂手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					质量验厂手册:
				</label>
			</td>
			<td class="value">
				<input id="zlFile" name="zlFile" type="hidden" value="${emkCustomUsePage.zlFile }" />
				<input id="zlFileUrl" name="zlFileUrl" type="hidden" value="${emkCustomUsePage.zlFileUrl }" />
				<t:upload name="instruction1" id="instruction1" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess1" >
				</t:upload><c:if test="${emkCustomUsePage.zlFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.zlFileUrl }')">${emkCustomUsePage.zlFile }</a>]</c:if><span id="zlFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					质量验厂说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="zlAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="zlAdvice">${emkCustomUsePage.zlAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">质量验厂说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有反恐验厂手册:
				</label>
			</td>
			<td class="value">
				<input name="isFkAdvice" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isFkAdvice eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isFkAdvice" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isFkAdvice eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有反恐验厂手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					反恐验厂手册:
				</label>
			</td>
			<td class="value">
				<input id="fkFile" name="fkFile" type="hidden" value="${emkCustomUsePage.fkFile }" />
				<input id="fkFileUrl" name="fkFileUrl" type="hidden" value="${emkCustomUsePage.fkFileUrl }" />
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload><c:if test="${emkCustomUsePage.fkFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.fkFileUrl }')">${emkCustomUsePage.fkFile }</a>]</c:if><span id="fkFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					反恐验厂说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="fkAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="fkAdvice">${emkCustomUsePage.fkAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">反恐验厂说明反恐验厂说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有验货手册:
				</label>
			</td>
			<td class="value">
				<input name="isCheckHuo" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isCheckHuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isCheckHuo" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isCheckHuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有验货手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					验货手册:
				</label>
			</td>
			<td class="value">
				<input id="checkHuoFile" name="checkHuoFile" type="hidden" value="${emkCustomUsePage.checkHuoFile }" />
				<input id="checkHuoFileUrl" name="checkHuoFileUrl" type="hidden" value="${emkCustomUsePage.checkHuoFileUrl }" />
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload><c:if test="${emkCustomUsePage.checkHuoFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.checkHuoFileUrl }')">${emkCustomUsePage.checkHuoFile }</a>]</c:if><span id="checkHuoFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					验货手册说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="checkHuo" style="width:95%;height:70px" class="inputxt" rows="5" name="checkHuo">${emkCustomUsePage.checkHuo }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货手册说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有测试手册:
				</label>
			</td>
			<td class="value">
				<input name="isTest" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isTest eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isTest" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isTest eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有测试手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试手册:
				</label>
			</td>
			<td class="value">
				<input id="testFile" name="testFile" type="hidden" value="${emkCustomUsePage.testFile }" />
				<input id="testFileUrl" name="testFileUrl" type="hidden" value="${emkCustomUsePage.testFileUrl }" />
				<t:upload name="instruction4" id="instruction4" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess4" >
				</t:upload><c:if test="${emkCustomUsePage.testFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.testFileUrl }')">${emkCustomUsePage.testFile }</a>]</c:if><span id="testFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					测试手册说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="test" style="width:95%;height:70px" class="inputxt" rows="5" name="test">${emkCustomUsePage.test }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试手册说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有包装手册:
				</label>
			</td>
			<td class="value">
				<input name="isCheckHuo" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isCheckHuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isCheckHuo" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isCheckHuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有包装手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					包装手册:
				</label>
			</td>
			<td class="value">
				<input id="pactFile" name="pactFile" type="hidden" value="${emkCustomUsePage.pactFile }" />
				<input id="pactFileUrl" name="pactFileUrl" type="hidden" value="${emkCustomUsePage.pactFileUrl }" />
				<t:upload name="instruction5" id="instruction5" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess5" >
				</t:upload><c:if test="${emkCustomUsePage.pactFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.pactFileUrl }')">${emkCustomUsePage.pactFile }</a>]</c:if><span id="pactFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					包装手册说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="pact" style="width:95%;height:70px" class="inputxt" rows="5" name="pact">${emkCustomUsePage.pact }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装手册说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有样品手册:
				</label>
			</td>
			<td class="value">
				<input name="isCheckHuo" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isCheckHuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isCheckHuo" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isCheckHuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有样品手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					样品手册:
				</label>
			</td>
			<td class="value">
				<input id="sampleFile" name="sampleFile" type="hidden" value="${emkCustomUsePage.sampleFile }" />
				<input id="sampleFileUrl" name="sampleFileUrl" type="hidden" value="${emkCustomUsePage.sampleFileUrl }" />
				<t:upload name="instruction6" id="instruction6" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess6" >
				</t:upload><c:if test="${emkCustomUsePage.sampleFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.sampleFileUrl }')">${emkCustomUsePage.sampleFile }</a>]</c:if><span id="sampleFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					样品手册说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sample" style="width:95%;height:70px" class="inputxt" rows="5" name="sample">${emkCustomUsePage.sample }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品手册说明</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有发货要求手册:
				</label>
			</td>
			<td class="value">
				<input name="isCheckHuo" type="radio" datatype="*" <c:if test="${emkCustomUsePage.isCheckHuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isCheckHuo" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isCheckHuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有发货要求手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					发货要求手册:
				</label>
			</td>
			<td class="value">
				<input id="sendHuoFile" name="sendHuoFile" type="hidden" value="${emkCustomUsePage.sendHuoFile }" />
				<input id="sendHuoFileUrl" name="sendHuoFileUrl" type="hidden" value="${emkCustomUsePage.sendHuoFileUrl }" />
				<t:upload name="instruction7" id="instruction7" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess7" >
				</t:upload><c:if test="${emkCustomUsePage.sendHuoFileUrl ne ''}">[<a href="javascript:downloadFile('${emkCustomUsePage.sendHuoFileUrl }')">${emkCustomUsePage.sendHuoFile }</a>]<span id="sendHuoFileId"></span></c:if>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					发货要求手册说明:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sendHuo" style="width:95%;height:70px" class="inputxt" rows="5" name="sendHuo">${emkCustomUsePage.sendHuo }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发货要求手册说明</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					指定货代名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="zdhdmc" name="zdhdmc" value="${emkCustomUsePage.zdhdmc }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">指定货代名称</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="relation" name="relation" value="${emkCustomUsePage.relation }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="telphone" name="telphone" value="${emkCustomUsePage.telphone }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					起运港:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="lyg" name="lyg"  type="text" value="${emkCustomUsePage.lyg }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">起运港</label>
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
		$("#instruction5-button").css("width","70px");
		$("#instruction6-button").css("width","70px");
		$("#instruction7-button").css("width","70px");

	});
</script>