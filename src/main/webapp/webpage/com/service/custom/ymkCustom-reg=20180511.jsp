<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>

	 <script type="text/javascript">
	  //编写自定义JS代码
	  $(function(){

		  $.ajax({
			  url : "ymkCustomController.do?getCity&code=A01",
			  type : 'post',
			  cache : false,
			  data: null,
			  success : function(data) {
				  var d = $.parseJSON(data);
				  if (d.success) {
					  var msg = d.msg;
					  var dataItems = new Array(); //定义一数组
					  dataItems = d.obj.split(";"); //字符分割
					  //W.document.location.reload(true);
					  $('#shengFen').empty();
					  var option1='';
					  var firstJgmc;
					  for (i=0;i<dataItems.length ;i++ ) {
						  var dataitem = new Array(); //定义一数组
						  dataitem = dataItems[i].split(","); //字符分割
						  if(i == 0){
							  firstJgmc = dataitem[0];
						  }
						  if(dataitem[0]!="") {
							  option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
						  }
					  }
					  $("#shengFen").append(option1);
					  $.ajax({
						  url : "ymkCustomController.do?getCity&code="+firstJgmc,
						  type : 'post',
						  cache : false,
						  data: null,
						  success : function(data) {
							  var d = $.parseJSON(data);
							  if (d.success) {
								  var msg = d.msg;
								  var dataItems = new Array(); //定义一数组
								  dataItems = d.obj.split(";"); //字符分割
								  //W.document.location.reload(true);
								  $('#chengShi').empty();
								  var option1='';
								  for (i=0;i<dataItems.length ;i++ ) {
									  var dataitem = new Array(); //定义一数组
									  dataitem = dataItems[i].split(","); //字符分割
									  if(i == 0){
										  firstJgmc = dataitem[0];
									  }
									  if(dataitem[0]!="") {
										  option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
									  }
								  }
								  $("#chengShi").append(option1);

								  $.ajax({
									  url : "ymkCustomController.do?getCity&code="+firstJgmc,
									  type : 'post',
									  cache : false,
									  data: null,
									  success : function(data) {
										  var d = $.parseJSON(data);
										  if (d.success) {
											  var msg = d.msg;
											  var dataItems = new Array(); //定义一数组
											  dataItems = d.obj.split(";"); //字符分割
											  //W.document.location.reload(true);
											  $('#pianQu').empty();
											  var option1='';
											  for (i=0;i<dataItems.length ;i++ ) {
												  var dataitem = new Array(); //定义一数组
												  dataitem = dataItems[i].split(","); //字符分割
												  if(dataitem[0]!="") {
													  option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
												  }
											  }
											  $("#pianQu").append(option1);
										  }
									  }
								  });
							  }
						  }
					  });
				  }
			  }
		  });


		  $("#shengFen").change(function(){
			  $.ajax({
				  url : "ymkCustomController.do?getCity&code="+$("#shengFen").val(),
				  type : 'post',
				  cache : false,
				  data: null,
				  success : function(data) {
					  var d = $.parseJSON(data);
					  if (d.success) {
						  var msg = d.msg;
						  var dataItems = new Array(); //定义一数组
						  dataItems = d.obj.split(";"); //字符分割
						  //W.document.location.reload(true);
						  $('#chengShi').empty();
						  $('#pianQu').empty();

						  var option3='';
						  var firstJgmc;

						  for (i=0;i<dataItems.length ;i++ ) {
							  var dataitem = new Array(); //定义一数组
							  dataitem = dataItems[i].split(","); //字符分割
							  if(i == 0){
								  firstJgmc = dataitem[0];
							  }
							  if(dataitem[0]!="") {
								  option3 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
							  }
						  }
						  $("#chengShi").append(option3);

						  $.ajax({
							  url : "ymkCustomController.do?getCity&code="+firstJgmc,
							  type : 'post',
							  cache : false,
							  data: null,
							  success : function(data) {
								  var d = $.parseJSON(data);
								  if (d.success) {
									  var msg = d.msg;
									  var dataItems = new Array(); //定义一数组
									  dataItems = d.obj.split(";"); //字符分割
									  //W.document.location.reload(true);
									  $('#pianQu').empty();
									  var option1='';
									  for (i=0;i<dataItems.length ;i++ ) {
										  var dataitem = new Array(); //定义一数组
										  dataitem = dataItems[i].split(","); //字符分割
										  if(dataitem[0]!="") {
											  option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
										  }
									  }
									  $("#pianQu").append(option1);
								  }
							  }
						  });
					  }
				  }
			  });
		  });

		  $("#chengShi").change(function(){
			  $.ajax({
				  url : "ymkCustomController.do?getCity&code="+$("#chengShi").val(),
				  type : 'post',
				  cache : false,
				  data: null,
				  success : function(data) {
					  var d = $.parseJSON(data);
					  if (d.success) {
						  var msg = d.msg;
						  var dataItems = new Array(); //定义一数组
						  dataItems = d.obj.split(";"); //字符分割
						  //W.document.location.reload(true);
						  $('#pianQu').empty();
						  var option3='';

						  for (i=0;i<dataItems.length ;i++ ) {
							  var dataitem = new Array(); //定义一数组
							  dataitem = dataItems[i].split(","); //字符分割
							  if(dataitem[0]!="") {
								  option3 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
							  }
						  }
						  $("#pianQu").append(option3);
					  }
				  }
			  });
		  });
		  /*if(${khmc ne null}){
			  BindSelect("khmc","",1,"${khmc}");
		  }else{
			  BindSelect("khmc","",0,"");
		  }*/

		  //$("#khmc").val("${xhdPage.khCode}");


	  });

	  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${ymkCustomPage.id }"/>
	  <input id="createDate" name="createDate" type="hidden" value="${ymkCustomPage.createDate }">
		<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="cusNum" name="cusNum" type="text" value="${cusNum}" datatype="*"  readonly validType="ymk_custom,cus_num,id"  style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户编码</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							服务商名称:
						</label>
					</td>
					<td class="value">
						<input id="cusName" name="cusName" type="text" datatype="*"  style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">客户名称</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							服务商类型:
						</label>
					</td>
					<td class="value">
						<select id="cusType" name="cusType" datatype="*" style="width:157px;">
							<option value="">--选择--</option>
							<option value="0">设备中标商</option>
							<option value="1">本地服务商</option>
						</select>
							<%--<t:dictSelect id="cusType" field="cusType" typeGroupCode="custom" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>--%>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">客户类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">用户账号:  </label>
					</td>
					<td class="value" colspan="5">
						<input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" />
						<label class="Validform_label" style="display: none;">用户账号</label>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.password"/>: </label></td>
					<td class="value" colspan="5">
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
					<td class="value" colspan="5">
						<input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"/>
						<span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行业:
						</label>
					</td>
					<td class="value">
						<input id="hangye" name="hangye" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">行业</label>
					</td>

					<td align="right">
						<label class="Validform_label">
							经营年限:
						</label>
					</td>
					<td class="value">
						<input id="manageYear" name="manageYear" type="text"  style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">经营年限</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
						<input id="contactName" datatype="*" name="contactName" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">联系人</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							联系人电话:
						</label>
					</td>
					<td class="value">
						<input id="telphone" name="telphone" type="text" datatype="m" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">联系人电话</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							办公电话:
						</label>
					</td>
					<td class="value">
						<input id="workphone" name="workphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">办公电话</label>
					</td>

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
				</tr>



				<tr>

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
					<td align="right">
						<label class="Validform_label">
							员工人数:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="people" name="people" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">员工人数</label>
					</td>
				</tr>
				<tr>

					<td align="right">
						<label class="Validform_label">
							所属区域:
						</label>
					</td>
					<td class="value" colspan="5">
						<select id="shengFen" name="shengFen" datatype="*" style="width:130px;">
							<option>--省份--</option>
						</select>
						<select id="chengShi" name="chengShi" datatype="*" style="width:150px;">
							<option>--城市--</option>
						</select>
						<select id="pianQu" name="pianQu" datatype="*" style="width:150px;">
							<option>--片区--</option>
						</select>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">所属区域</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							办公地址:
						</label>
					</td>
					<td class="value" colspan="5">
						<textarea id="address" style="width:90%;height:60px" class="inputxt" rows="3" name="address"></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">办公地址</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							主营业务:
						</label>
					</td>
					<td class="value" colspan="5">
						<textarea id="mainContent" style="width:90%;height:60px" class="inputxt" rows="3" name="mainContent"></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">备注</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							注册地址:
						</label>
					</td>
					<td class="value" colspan="5">
						<textarea id="regAddress" style="width:90%;height:60px" class="inputxt" rows="3" name="regAddress"></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">备注</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="5">
						<textarea id="remark" style="width:90%;height:60px" class="inputxt" rows="3" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/service/custom/ymkCustom.js"></script>		
