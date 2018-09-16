<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>产品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  $(function() {
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
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProductInfoController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkProductInfoPage.id }"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		  <tr>
			  <td align="right">
				  <label class="Validform_label">
					  款号:
				  </label>
			  </td>
			  <td class="value">
				  <input id="hsCode" datatype="*"  validType="emk_product_info,hs_code,id" name="hsCode" type="text" style="width: 150px" class="inputxt"  />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">款号</label>
			  </td>

			  <td align="right">
				  <label class="Validform_label">
					  款号名称:
				  </label>
			  </td>
			  <td class="value">
				  <input id="hsName" datatype="*"   name="hsName" type="text" style="width: 150px" class="inputxt"  />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">款号名称</label>
			  </td>
		  </tr>
		  <tr>
			  <td align="right" width="150">
				  <label class="Validform_label">
					  编号:
				  </label>
			  </td>
			  <td class="value" width="260">
				  <input id="proNum" name="proNum" type="text" validType="emk_product_info,pro_num,id" style="width: 150px" class="inputxt"  ignore="ignore" />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">编号</label>
			  </td>

			  <td align="right" width="150">
				  <label class="Validform_label">
					  类别:
				  </label>
			  </td>
			  <td class="value">
				  <input id="proType" datatype="*" name="proType" type="hidden" value="${emkProductPage.proType }"/>
				  <input id="proTypeName" name="proTypeName" type="hidden" value="${emkProductPage.proTypeName }"/>

				  <input id="proTypeTree" value="${emkProductTypePage.productTypeEntity.content}">
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">类别</label>
			  </td>
		  </tr>

		  <tr>
			  <td align="right">
				  <label class="Validform_label">
					  产品名称:
				  </label>
			  </td>
			  <td class="value" >
				  <input id="proName" name="proName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">产品名称</label>
			  </td>
			  <td align="right">
				  <label class="Validform_label">
					  规格型号:
				  </label>
			  </td>
			  <td class="value">
				  <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">规格型号</label>
			  </td>

		  </tr>

		  <tr>
			  <td align="right">
				  <label class="Validform_label">
					  单位:
				  </label>
			  </td>
			  <td class="value" >
				  <input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">单位</label>
			  </td>
			  <td align="right">
				  <label class="Validform_label">
					  单价:
				  </label>
			  </td>
			  <td class="value" >
				  <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">单价</label>
			  </td>
		  </tr>
		  <tr>
			  <td align="right">
				  <label class="Validform_label">
					  备注:
				  </label>
			  </td>
			  <td class="value" colspan="3"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark"></textarea>
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">备注</label>
			  </td>
		  </tr>
	  </table>
  </t:formvalid>
 </body>
  <script src = "webpage/com/emk/product/productinfo/emkProductInfo.js"></script>		
