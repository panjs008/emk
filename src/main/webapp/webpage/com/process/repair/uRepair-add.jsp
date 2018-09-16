<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报修单表</title>
	 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码

  function uploadSuccess(d,file,response){
	  var src = d.attributes.url;
	  $("#imageFileName1Path").val(d.attributes.url);
	  $("#imageFileName1").val(d.attributes.name);
  }

  function uploadSuccess1(d,file,response){
	  var src = d.attributes.url;
	  $("#audioPath").val(d.attributes.url);
	  $("#audioName").val(d.attributes.name);
  }
  function returnToVal(){

  }

  $(function() {
	 /* $.ajax({
		  url : "emkEquipController.do?getEquipType&code=A02",
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
				  $('#sblx').empty();
				  var option1 = '<option value="">--选择--</option>';

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
				  $("#sblx").append(option1);

			  }
		  }
	  });

	  $("#sblx").change(function(){
		  $.ajax({
			  url : "emkEquipController.do?getEquipType&code="+$("#sblx").val(),
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
					  $('#pp').empty();
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
					  $("#pp").append(option1);

					  $.ajax({
						  url : "emkEquipController.do?getEquipType&code="+firstJgmc,
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
								  $('#xh').empty();
								  var option1='';
								  for (i=0;i<dataItems.length ;i++ ) {
									  var dataitem = new Array(); //定义一数组
									  dataitem = dataItems[i].split(","); //字符分割
									  if(dataitem[0]!="") {
										  option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
									  }
								  }
								  $("#xh").append(option1);
							  }
						  }
					  });
				  }
			  }
		  });
	  });

	  $("#pp").change(function(){
		  $.ajax({
			  url : "emkEquipController.do?getEquipType&code="+$("#pp").val(),
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
					  $('#xh').empty();
					  var option4='';
					  for (i=0;i<dataItems.length ;i++ ) {
						  var dataitem = new Array(); //定义一数组
						  dataitem = dataItems[i].split(","); //字符分割
						  if(dataitem[0]!=""){
							  option4 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
						  }
					  }
					  $("#xh").append(option4);
				  }
			  }
		  });
	  });*/


  });

  var selectSblx = function(param,success,error){
	  var q = param.q || '';
	  //if (q.length <= 1){return false}
	  $.ajax({
		  url: 'emkEquipController.do?getEquipByText&code=A02',
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

		  }
	  });
  }

  var selectPp = function(param,success,error){
	  var q = param.q || '';
	  //if (q.length <= 1){return false}
	  $.ajax({
		  url: 'emkEquipController.do?getEquipByText&code='+$("#sblx").val(),
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

		  }
	  });
  }

  var selectXh = function(param,success,error){
	  var q = param.q || '';
	  //if (q.length <= 1){return false}
	  $.ajax({
		  url: 'emkEquipController.do?getEquipByText&code='+$("#pp").val(),
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

		  }
	  });
  }


  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password"  layout="table" action="uRepairController.do?doAdd"  tiptype="1">
					<input id="id" name="id" type="hidden" value="${uRepairPage.id }"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						报修单号:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="repairNum" name="repairNum" datatype="*" readonly value="${repairNum}" type="text" style="width: 150px" class="inputxt"   />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报修单号</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						<font color="red">*</font>报修方式:
					</label>
				</td>
				<td class="value" width="30%">
					<select id="bxType" name="bxType" datatype="*" style="width:157px;" datatype="*">
						<option value="telphone">电话</option>
						<option value="weixin">微信</option>
						<option value="saomiao">扫描</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报修方式</label>
				</td>
			</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报修人:
						</label>
					</td>
					<td class="value" width="30%">
					     	 <input id="userName" name="userName" datatype="*" readonly value="${CUR_USER.realName}" type="text" style="width: 150px" class="inputxt"   />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报修人</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							报修时间:
						</label>
					</td>
					<td class="value" width="30%">
						<input id="applyDate" datatype="*" readonly value="${applyDate}" name="applyDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">报修时间</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<font color="red">*</font>紧急度:
						</label>
					</td>
					<td class="value" colspan="3">
						<select id="jjd" name="jjd" datatype="*" style="width:157px;" datatype="*">
							<option value="0">一般</option>
							<option value="1">紧急</option>
							<option value="2">非常紧急</option>

						</select>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">紧急度</label>
					</td>
					<%--<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
						<input id="relationer" name="relationer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">联系人</label>
					</td>--%>

				</tr>
			<%--<tr>
				<td align="right">
					<label class="Validform_label">
						设备编号:
					</label>
				</td>
				<td class="value">
					<input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产品编号</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						设备名称:
					</label>
				</td>
				<td class="value">
					<input id="proId" name="proId" type="hidden" style="width: 100px" class="inputxt"  ignore="ignore" />
					<input id="proZnName" name="proZnName" type="text" style="width: 100px" class="inputxt"  ignore="ignore" />

					<t:choose  hiddenName="proId"  hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="750px" height="500px"
							   icon="icon-search" title="选择商品" textname="proNum,proType,proTypeName,proZnName,brand,unit" isclear="true" isInit="true"></t:choose>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">产品名称</label>
				</td>

			</tr>--%>
			<tr>
				<td align="right">
					<label class="Validform_label">
						<font color="red">*</font>设备名称:
					</label>
				</td>
				<td class="value">
					<input id="proZnName" name="proZnName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">设备名称</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						<font color="red">*</font>设备类型:
					</label>
				</td>
				<td class="value">
					<%--<select id="sblx" name="sblx" datatype="*" style="width:157px;" datatype="*">
						<option value="">--选择--</option>
							&lt;%&ndash;<c:forEach var="item" items="${codeList2}">
                                <option value="${item.code}">${item.name}</option>
                            </c:forEach>&ndash;%&gt;
					</select>--%>
						<input id="sblx" name="sblx" type="hidden" style="width: 160px" class="inputxt"  ignore="ignore" />
						<input class="easyui-combobox" id="sblxVal"  name="sblxVal"  style="width:158px;" data-options="
                    loader: selectSblx,
                    mode: 'remote',
                    valueField: 'id',
                    textField: 'name',
                     onSelect: function(rec){
							$('#sblx').val(rec.id);
						}
                    ">
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">设备类型</label>
				</td>
			</tr>
			<tr>

				<td align="right">
					<label class="Validform_label">
						品牌:
					</label>
				</td>
				<td class="value">
					<%--<select id="pp" name="pp" datatype="*" style="width:157px;" datatype="*">

					</select>--%>
						<input id="pp" name="pp" type="hidden" style="width: 160px" class="inputxt"  ignore="ignore" />
						<input class="easyui-combobox" id="ppVal"  name="ppVal"  style="width:158px;" data-options="
                    loader: selectPp,
                    mode: 'remote',
                    valueField: 'id',
                    textField: 'name',
                     onSelect: function(rec){
                     		$('#pp').val(rec.id);
						}
                    ">
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">品牌</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						型号:
					</label>
				</td>
				<td class="value">
					<%--<select id="xh" name="xh" datatype="*" style="width:157px;" datatype="*">

					</select>--%>
						<input id="xh" name="xh" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
					<input class="easyui-combobox" id="xhVal" name="xhVal" style="width:158px;" data-options="
                    loader: selectXh,
                    mode: 'remote',
                    valueField: 'id',
                    textField: 'name',
                     onSelect: function(rec){
							$('#xh').val(rec.id);
						}
                    ">
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">型号</label>
				</td>
			</tr>

			<%--<tr>
					<td align="right">
						<label class="Validform_label">
							设备类型:
						</label>
					</td>
					<td class="value">
						<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="proTypeName" name="proTypeName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">设备类型</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							设备型号:
						</label>
					</td>
					<td class="value">
						<input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">设备型号</label>
					</td>
				</tr>--%>

			<%--<tr>
				<td align="right">
					<label class="Validform_label">
						报修数量:
					</label>
				</td>
				<td class="value">
					<input id="total" name="total" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报修数量</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						单位:
					</label>
				</td>
				<td class="value">
					<input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">单位</label>
				</td>

			</tr>--%>
			<tr>
				<td align="right">
					<label class="Validform_label">
						图片:
					</label>
				</td>
				<td class="value">
					<input id="imageFileName1Path" name="imageFileName1Path" type="hidden">
					<input id="imageFileName1" class="inputxt"  readonly name="imageFileName1" type="text">
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">图片</label>
				</td>
				<td class="value" colspan="2">
					<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
					</t:upload>
				</td>

			</tr>
			  <tr>
				  <td align="right">
					  <label class="Validform_label">
						  录音文件:
					  </label>
				  </td>
				  <td class="value">
					  <input id="audioName" name="audioName" class="inputxt" readonly type="text">
					  <input id="audioPath" name="audioPath" type="hidden">
					  <span class="Validform_checktip"></span>
					  <label class="Validform_label" style="display: none;">故障描述</label>
				  </td>
				  <td class="value" colspan="2">
					  <t:upload name="instruction2" id="instruction2" dialog="false" extend="*.mp4,*.mp3,*.rmvb" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
					  </t:upload>
				  </td>
			  </tr>
			  <tr>
				  <td colspan="4" id="instructionfile" class="value">
				  </td>
			  </tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							故障描述:
						</label>
					</td>
					<td class="value" colspan="3">
						<textarea id="fault" style="width:95%;height:80px" class="inputxt" rows="5" name="fault"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">故障描述</label>
						</td>
				</tr>



	  <%--<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark"></textarea>
					</td>
				</tr>--%>


			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/process/repair/uRepair.js"></script>
