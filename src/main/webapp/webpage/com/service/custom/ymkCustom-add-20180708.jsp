<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	 <script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	 <script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	 <script type="text/javascript">
	  //编写自定义JS代码
	  $(function(){
		  BindSelect("cusFrom","",0,"");
		  /*if(${khmc ne null}){
			  BindSelect("khmc","",1,"${khmc}");
		  }else{
			  BindSelect("khmc","",0,"");
		  }*/

		  //$("#khmc").val("${xhdPage.khCode}");

	  });
	  function BindSelect(ctrlName, url,type,categoryId) {
		  var control = $('#' + ctrlName);
		  //设置Select2的处理
		  control.select2({
			  formatResult: formatState,
			  formatSelection: formatState,
			  escapeMarkup: function (m) {
				  return m;
			  }
		  });
		  //绑定Ajax的内容
		  $.getJSON('ymkCustomFromController.do?findSelectList', function (data) {
			  control.empty();//清空下拉框
			  control.append("<option value=''>请选择客户</option>");
			  $.each(data.obj, function (i, item) {
				  control.append("<option value='" + item.fname + "'>&nbsp;" + item.fname + "</option>");
			  });

			 /* if(type ==1){
				  $("#"+ctrlName).select2('val',categoryId);
			  }*/
		  });
	  }

	  function formatState (state) {
		  if (!state.id) { return state.text; }
		  var $state = $(
			  '<span>' + state.text + '</span>'
		  );
		  return $state;
	  };
	  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${ymkCustomPage.id }"/>
		<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="cusNum" name="cusNum" type="text" value="${cusNum}" validType="ymk_custom,cus_num,id"  style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户编码</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							服务商名称:
						</label>
					</td>
					<td class="value">
						<input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">客户名称</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="cusType" field="cusType" typeGroupCode="custom" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户类型</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							来源:
						</label>
					</td>
					<td class="value">
						<select class="form-control select2" id="cusFrom" name="cusFrom" datatype="*"  >
							<option value="">请选择客户</option>
						</select>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">来源</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							办公电话:
						</label>
					</td>
					<td class="value">
						<input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">办公电话</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							办公传真:
						</label>
					</td>
					<td class="value">
					     	 <input id="fax" name="fax" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公传真</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							公司网址:
						</label>
					</td>
					<td class="value">
						<input id="companyNet" name="companyNet" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">公司网址</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3">
						<textarea id="remark" style="width:90%;height:60px" class="inputxt" rows="3" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/service/custom/ymkCustom.js"></script>		
