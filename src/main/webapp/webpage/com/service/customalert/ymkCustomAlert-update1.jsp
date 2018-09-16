<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户提醒表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	 <script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	 <script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  $(function(){
      $("#sex").val("${ymkCustomContactPage.sex}");

      //BindSelect("customName","",1,"${ymkCustomAlertPage.customName}");

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
      $.getJSON('ymkCustomController.do?findCustomList', function (data) {
          control.empty();//清空下拉框
          $.each(data.obj, function (i, item) {
              if(item.fname == categoryId){
                  control.append("<option value='" + item.fname + "'>&nbsp;" + item.fname + "</option>");
              }
          });

          $.each(data.obj, function (i, item) {
              if(item.fname != categoryId){
                  control.append("<option value='" + item.fname + "'>&nbsp;" + item.fname + "</option>");
              }
          });

          /*if(type ==1){
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

  var myloader = function(param,success,error){
	  var q = param.q || '';
	  if (q.length <= 1){return false}
	  $.ajax({
		  url: 'ymkCustomController.do?findCustomList',
		  dataType: 'json',
		  data: {
			  q: q,
			  maxRows: 20
		  },
		  success: function(data){
			  var items = $.map(data, function(item,index){
				  return {
					  id: item.id,
					  name: item.name
				  };
			  });
			  success(items);
		  },
		  error: function(){
			  ///alert("1234");
			  //error.apply(this, arguments);
		  }
	  });
  }
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="ymkCustomAlertController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${ymkCustomAlertPage.id }"/>
			<input id="customId" name="customId" type="hidden" value="${ymkCustomAlertPage.customId }"/>

			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户:
						</label>
					</td>
					<td class="value">
						<input class="easyui-combobox" name="customIdVal" value="${ymkCustomAlertPage.customName }"  style="width:200px;" data-options="
                    loader: myloader,
                    mode: 'remote',
                    valueField: 'id',
                    textField: 'name',
                     onSelect: function(rec){
							$('#customId').val(rec.id);

						}
                    ">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">客户</label>
					</td>
				</tr>
			<tr>
				<td align="right" width="200px;">
					<label class="Validform_label">
						提醒时间:
					</label>
				</td>
				<td class="value">
					<input id="alertTime" datatype="*" name="alertTime" value='${ymkCustomAlertPage.alertTime}' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" style="width: 150px" class="inputxt" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">提醒时间</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						提醒内容:
					</label>
				</td>
				<td class="value">
					<textarea id="alertContent" datatype="*"  style="width:90%;height:60px" class="inputxt" rows="3" name="alertContent">${ymkCustomAlertPage.alertContent}</textarea>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">提醒内容</label>
				</td>
			</tr>


			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/service/customalert/ymkCustomAlert.js"></script>		
