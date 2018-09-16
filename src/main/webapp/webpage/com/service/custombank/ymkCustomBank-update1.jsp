<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行账户表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	 <script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	 <script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	 <script type="text/javascript">
  //编写自定义JS代码
  $(function(){
      $("#bz").val("${ymkCustomBankPage.bz}");
      $("#state").val("${ymkCustomBankPage.state}");
      //BindSelect("customName","",1,"${ymkCustomBankPage.customName}");

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
          control.append("<option value=''>请选择客户</option>");
          $.each(data.obj, function (i, item) {
              control.append("<option value='" + item.fname + "'>&nbsp;" + item.fname + "</option>");
          });

          if(type ==1){
              $("#"+ctrlName).select2('val',categoryId);
          }
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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="ymkCustomBankController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${ymkCustomBankPage.id }"/>
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户:
						</label>
					</td>
					<td class="value">
						<input id="customId" datatype="*" name="customId" type="hidden" value="${ymkCustomBankPage.customId }"/>

						<input class="easyui-combobox"  id="customIdVal" name="customIdVal"  value="${ymkCustomBankPage.customName }" style="width:200px;" data-options="
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
						<td align="right">
							<label class="Validform_label">
								银行名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="bankName" name="bankName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ymkCustomBankPage.bankName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								银行账号:
							</label>
						</td>
						<td class="value">
						     	 <input id="bankAccount" name="bankAccount" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ymkCustomBankPage.bankAccount}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								银行户名:
							</label>
						</td>
						<td class="value">
						     	 <input id="bankAccountName" name="bankAccountName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ymkCustomBankPage.bankAccountName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行户名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								SWIFT号:
							</label>
						</td>
						<td class="value">
						     	 <input id="swifi" name="swifi" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ymkCustomBankPage.swifi}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">SWIFT号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								币种:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="bz" field="bz" typeGroupCode="cointype" datatype="*" defaultVal="default" hasLabel="false" title="币种"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">币种</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								默认账号:
							</label>
						</td>
						<td class="value">
							<select id="state" name="state" >
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">默认账号</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/service/custombank/ymkCustomBank.js"></script>		
