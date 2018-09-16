<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>联系人表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	 <script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	 <script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	 <script type="text/javascript">
         $(function(){
             //BindSelect("customName","",0,"");

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


         /*var myloader = function(param,success,error){
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
         }*/
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomContactController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${ymkCustomContactPage.id }"/>
	  <input id="customId" name="customId" type="hidden" value="${custom.id}"/>
	  <%--<input id="customName" name="customName" type="text" value="${ymkCustomContactPage.customName }"/>--%>

	  <table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
		  		<%--<tr>
					<td align="right">
						<label class="Validform_label">
							客户:
						</label>
					</td>
					<td class="value">
						<input id="customId" name="customId" datatype="*" type="hidden" value="${ymkCustomContactPage.customId }"/>

						<input class="easyui-combobox" name="customIdVal"  style="width:200px;" data-options="
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
				</tr>--%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
							<input id="userName" name="userName" type="text" style="width: 150px" class="inputxt"  datatype="*" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>

						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
							<select name="sex" >
								<option value="0">男</option>
								<option value="1">女</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">用户账号:  </label>
						</td>
						<td class="value" colspan="3">
							<input id="userAccount" class="inputxt" name="userAccount" validType="t_s_base_user,userName,id" />
							<label class="Validform_label" style="display: none;">用户账号</label>
						</td>
					</tr>
					<tr>
						<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.password"/>: </label></td>
						<td class="value" colspan="3">
							<input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" />
                    <span class="passwordStrength" style="display: none;">
                        <span><t:mutiLang langKey="common.weak"/></span>
                        <span><t:mutiLang langKey="common.middle"/></span>
                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    </span>
							<span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
						</td>
					</tr>
					<tr>
						<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.repeat.password"/>: </label></td>
						<td class="value" colspan="3">
							<input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"/>
							<span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
						</td>
					</tr>


				<tr>
					<td align="right">
						<label class="Validform_label">
							职务:
						</label>
					</td>
					<td class="value">
					     	 <input id="position" name="position" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职务</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							生日:
						</label>
					</td>
					<td class="value">
						<input id="brithDay" name="brithDay" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px"  class="Wdate"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">生日</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							email:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="email" name="email" type="text" style="width: 150px" datatype="e" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">email</label>
						</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							手机:
						</label>
					</td>
					<td class="value">
						<input id="telphone" name="telphone" type="text" style="width: 150px" datatype="m" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">手机</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="mobile" name="mobile" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
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
  <script src = "webpage/com/service/customcontact/ymkCustomContact.js"></script>		
