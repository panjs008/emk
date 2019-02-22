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
			//BindSelect("cusFrom","",1,"${ymkCustomPage.cusFrom}");
			$("#cusType").val("${ymkCustomPage.cusType}");
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
						var option1 = '<option value="">--选择--</option>';
						var firstJgmc;
						for (i=0;i<dataItems.length ;i++ ) {
							var dataitem = new Array(); //定义一数组
							dataitem = dataItems[i].split(","); //字符分割
							if(dataitem[0]!="") {
								if(dataitem[0] == '${ymkCustomPage.shengFen}'){
									option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
								}else{
									option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}
						}
						$("#shengFen").append(option1);
						if(${ymkCustomPage.chengShi ne '' && ymkCustomPage.chengShi ne null}){
							$.ajax({
								url : "ymkCustomController.do?getCity&code=${ymkCustomPage.shengFen}",
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
											if(dataitem[0] == '${ymkCustomPage.chengShi}'){
												option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
											}else{
												option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
											}
										}
										$("#chengShi").append(option1);

										$.ajax({
											url : "ymkCustomController.do?getCity&code=${ymkCustomPage.chengShi}",
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
														if(dataitem[0] == '${ymkCustomPage.pianQu}'){
															option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
														}else{
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

			BindSelect("businesserId","ymkCustomController.do?findUserList&userKey=业务员",0,"");
			$("#businesserId").change(function(){
				var itemarr = $("#businesserId").val().split(","); //字符分割
				$("#businesser").val(itemarr[0]);
				$("#businesserName").val(itemarr[1]);

				returnToDept($("#businesser").val());

			});

			function returnToDept(userName){
				$.ajax({
					url: "ymkCustomController.do?getDeptInfoByUser&userName="+userName,
					type: 'post',
					cache: false,
					data: null,
					success: function (data) {
						var dept = $.parseJSON(data);

						$("#businesseDeptName").val(dept.departname);
						$("#businesseDeptId").val(dept.orgCode);
					}
				});
			}

			BindSelect("tracerId","ymkCustomController.do?findUserList&userKey=业务跟单员",0,"");
			$("#tracerId").change(function(){
				var itemarr = $("#tracerId").val().split(","); //字符分割
				$("#tracer").val(itemarr[0]);
				$("#tracerName").val(itemarr[1]);
			});


			BindSelect("developerId","ymkCustomController.do?findUserList&userKey=生产跟单员",0,"");
			$("#developerId").change(function(){
				var itemarr = $("#developerId").val().split(","); //字符分割
				$("#developer").val(itemarr[0]);
				$("#developerName").val(itemarr[1]);
			});


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
			$.getJSON(url, function (data) {
				control.empty();//清空下拉框
				control.append("<option value=''>请选择</option>");
				$.each(data.obj, function (i, item) {
					control.append("<option value='" + item.userName + ","+item.realName +"'>&nbsp;" + item.realName + "</option>");
				});

			});
		}

		function formatState (state) {
			if (!state.id) { return state.text; }
			var $state = $(
					'<span>' + state.text + '</span>'
			);
			return $state;
		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${ymkCustomPage.id }"/>
	<input id="businesser" name="businesser" type="hidden" value="${ymkCustomPage.businesser }"/>
	<input id="businesserName" name="businesserName" type="hidden" value="${ymkCustomPage.businesserName }"/>
	<input id="tracer" name="tracer" type="hidden" value="${ymkCustomPage.tracer }"/>
	<input id="tracerName" name="tracerName" type="hidden" value="${ymkCustomPage.tracerName }"/>
	<input id="developer" name="developer" type="hidden" value="${ymkCustomPage.developer }"/>
	<input id="developerName" name="developerName" type="hidden" value="${ymkCustomPage.developerName }"/>
	<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					档案编号:
				</label>
			</td>
			<td class="value">
				<input id="daanNum" name="daanNum" type="text" value="${daanNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">档案编号</label>
			</td>
			</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value">
				<input id="cusName" name="cusName" type="text" value="${ymkCustomPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value">
				<input id="businesseDeptName" name="businesseDeptName"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value">
				<input id="cusNum" name="cusNum" type="text" value="${cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="businesserId"   >
					<option value=''>请选择</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="cusType" field="cusType" typeGroupCode="custom" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					主联系人:
				</label>
			</td>
			<td class="value">
				<input id="zlxr" name="zlxr" type="text" datatype="*" value="${ymkCustomPage.zlxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" value="${ymkCustomPage.telphone }" datatype="m" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					办公电话:
				</label>
			</td>

			<td class="value">
				<input id="workphone" name="workphone" value="${ymkCustomPage.workphone }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公电话</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					办公传真:
				</label>
			</td>
			<td class="value">
				<input id="fax" name="fax" type="text" value="${ymkCustomPage.fax }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公传真</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					邮箱:
				</label>
			</td>
			<td class="value">
				<input id="email" name="email" type="text" value="${ymkCustomPage.email }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">邮箱</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					贸易国别:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="guoJia" field="guoJia" typeGroupCode="trade" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">贸易国别</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					所属区域:
				</label>
			</td>
			<td class="value" colspan="3">
				<select id="shengFen" name="shengFen"  style="width:130px;">
					<option>--省份--</option>
				</select>
				<%--<select id="chengShi" name="chengShi" style="width:150px;">
					<option>--城市--</option>
				</select>
				<select id="pianQu" name="pianQu" style="width:150px;">
					<option>--片区--</option>
				</select>--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">所属区域</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户地址:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea id="address" style="width:90%;height:50px" class="inputxt" rows="3" name="address">${ymkCustomPage.address }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户地址</label>
			</td>
		</tr>
		<tr>


			<td align="right">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="tracerId"  >
					<%--<option value="">请选择客户</option>--%>
						<option value=''>请选择</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务跟单员</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
						<%--<option value="">请选择客户</option>--%>
							<option value=''>请选择</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产跟单员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					主营业务:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea id="mainContent" style="width:90%;height:50px" class="inputxt" rows="3" name="mainContent">${ymkCustomPage.mainContent }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					业务类型:
				</label>
			</td>
			<td class="value">
				<select id="businessType" name="businessType">
					<option name="0">直接</option>
					<option name="1">中间</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					实际业务量:
				</label>
			</td>
			<td class="value">
				<input id="ywl" name="ywl" type="text" value="${ymkCustomPage.ywl }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际业务量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit" type="text" value="${ymkCustomPage.unit }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="bz" field="bz" typeGroupCode="cointype" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					实际业务金额:
				</label>
			</td>
			<td class="value">
				<input id="ywMoney" name="ywMoney" datatype="d" type="text" value="${ymkCustomPage.ywMoney }" style="width: 150px" class="inputxt"  ignore="ignore"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际业务金额</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					潜在业务量/年:
				</label>
			</td>
			<td class="value">
				<input id="qzywl" name="qzywl" type="text" datatype="*" value="${ymkCustomPage.qzywl }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">潜在业务量/年</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="qzunit" name="qzunit" type="text" value="${ymkCustomPage.qzunit }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际业务金额</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					潜在业务金额/年:
				</label>
			</td>
			<td class="value">
				<input id="qzywMoney" name="qzywMoney" type="text" datatype="d" value="${ymkCustomPage.qzywMoney }" style="width: 150px" class="inputxt"   ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">潜在业务金额/年</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="qzbz" field="qzbz" typeGroupCode="cointype" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					建立商业关系时间:
				</label>
			</td>
			<td class="value">
				<input id="relationDate" name="relationDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${createDate}" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">建立商业关系时间</label>
			</td>



			<td align="right">
				<label class="Validform_label">
					法律联系人:
				</label>
			</td>
			<td class="value">
				<input id="fllxr" name="fllxr" type="text" datatype="*" value="${ymkCustomPage.fllxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法律联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					质量联系人:
				</label>
			</td>
			<td class="value">
				<input id="zllxr" name="zllxr" type="text" datatype="*" value="${ymkCustomPage.zllxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">质量联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					业务联系人一:
				</label>
			</td>
			<td class="value">
				<input id="ywlxr1" name="ywlxr1" type="text" datatype="*" value="${ymkCustomPage.ywlxr1 }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务联系人一</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					业务联系人二:
				</label>
			</td>
			<td class="value">
				<input id="ywlxr2" name="ywlxr2" type="text" datatype="*" value="${ymkCustomPage.ywlxr2 }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务联系人二</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					业务联系人三:
				</label>
			</td>
			<td class="value">
				<input id="ywlxr3" name="ywlxr3" type="text" datatype="*" value="${ymkCustomPage.ywlxr3 }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务联系人三</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					试身联系人:
				</label>
			</td>
			<td class="value">
				<input id="sslxr" name="sslxr" type="text" datatype="*" value="${ymkCustomPage.sslxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">试身联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					包装联系人:
				</label>
			</td>
			<td class="value">
				<input id="bzlxr" name="bzlxr" type="text" datatype="*" value="${ymkCustomPage.bzlxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试联系人:
				</label>
			</td>
			<td class="value">
				<input id="cslxr" name="cslxr" type="text" datatype="*" value="${ymkCustomPage.cslxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试联系人</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					验厂联系人:
				</label>
			</td>
			<td class="value">
				<input id="yclxr" name="yclxr" type="text" datatype="*" value="${ymkCustomPage.yclxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验厂联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					船务联系人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cwlxr" name="cwlxr" type="text" datatype="*" value="${ymkCustomPage.cwlxr }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务联系人</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea id="remark" style="width:90%;height:50px" class="inputxt" rows="3" name="remark">${ymkCustomPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/service/custom/ymkCustom.js"></script>
