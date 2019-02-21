<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>色样通知单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
	  function uploadSuccess(d,file,response){
		  var src = d.attributes.url;
		  $("#customSampleUrl").val(d.attributes.url);
		  $("#customSample").val(d.attributes.name);
		  $("#khyyId").html(d.attributes.name);

		  $("#uploadimg").attr('src',d.attributes.url);

	  }
  </script>
 </head>
 <body>
 <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSyController.do?doAdd" tiptype="1">
	 <input id="id" name="id" type="hidden" value="${emkSyPage.id }"/>
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
				 <input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				 <t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
							icon="icon-search" title="选择客户" textname="cusName" isclear="true" isInit="true"></t:choose>
				 <span class="Validform_checktip"></span>
				 <label class="Validform_label" style="display: none;">客户名称</label>
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
					 交货时间:
				 </label>
			 </td>
			 <td class="value">
				 <input id="receviceDate" name="receviceDate" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				 <span class="Validform_checktip"></span>
				 <label class="Validform_label" style="display: none;">交货时间</label>
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
					 版次:
				 </label>
			 </td>
			 <td class="value">
				 <input id="version" name="version"  type="text" style="width: 150px"  ignore="ignore" />
				 <span class="Validform_checktip"></span>
				 <label class="Validform_label" style="display: none;">版次</label>
			 </td>
		 </tr>
		 <tr>
			 <td align="right">
				 <label class="Validform_label">
					 布面克重:
				 </label>
			 </td>
			 <td class="value">
				 <input id="weight" name="weight"  type="text" style="width: 150px"  ignore="ignore" />
				 <span class="Validform_checktip"></span>
				 <label class="Validform_label" style="display: none;">布面克重</label>
			 </td>
			 <td align="right">
				 <label class="Validform_label">
					 布面成分:
				 </label>
			 </td>
			 <td class="value">
				 <input id="chengf" name="chengf"  type="text" style="width: 150px"  ignore="ignore" />
				 <span class="Validform_checktip"></span>
				 <label class="Validform_label" style="display: none;">布面成分</label>
			 </td>
		 </tr>
		 <tr>
			 <td align="right">
				 <label class="Validform_label">
					 图片:
				 </label>
			 </td>
			 <td class="value">
				 <input id="customSample" name="customSample" type="hidden" />
				 <img id="uploadimg" src="${emkSyPage.customSampleUrl eq null ? 'images/bjlogo.png':emkSyPage.customSampleUrl}" width="150" height="150">
				 <t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				 </t:upload>
				 <span id="khyyId"></span>
				 <input id="customSampleUrl" name="customSampleUrl" type="hidden" />
			 </td>

		 </tr>
		 <tr>
			 <td colspan="4" id="instructionfile" class="value">
			 </td>
		 </tr>

	 </table>
 </t:formvalid>

 </body>
  <script src = "webpage/com/emk/storage/sy/emkSy.js"></script>
 <script>
	 $(document).ready(function() {
		 $("#instruction-button").css("width","70px");
		 $("#instruction2-button").css("width","70px");
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