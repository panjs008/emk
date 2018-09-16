<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>设备资产表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			var firstJgmc;
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
								if(dataitem[0] == '${emkEquipPage.shengFen}'){
									option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
								}else{
									option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}
						}
						$("#shengFen").append(option1);

						$.ajax({
							url : "ymkCustomController.do?getCity&code=${emkEquipPage.shengFen}",
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
									for (i=0;i<dataItems.length ;i++ ) {
										var dataitem = new Array(); //定义一数组
										dataitem = dataItems[i].split(","); //字符分割

										if(dataitem[0]!="") {
											if(dataitem[0] == '${emkEquipPage.chengShi}'){
												option3 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
											}else{
												option3 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
											}
										}
									}
									$("#chengShi").append(option3);

									$.ajax({
										url : "ymkCustomController.do?getCity&code=${emkEquipPage.chengShi}",
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
														if(dataitem[0] == '${emkEquipPage.chengShi}'){
															firstJgmc = dataitem[0];
															option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
														}else{
															option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
														}
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



			$.ajax({
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
						$('#zclx').empty();
						var option1 = '<option value="">--选择--</option>';

						var firstJgmc;
						for (i=0;i<dataItems.length ;i++ ) {
							var dataitem = new Array(); //定义一数组
							dataitem = dataItems[i].split(","); //字符分割

							if(dataitem[0]!="") {
								if(dataitem[0] == '${emkEquipPage.zclx}'){
									option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
								}else{
									option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}

						}
						$("#zclx").append(option1);

						$.ajax({
							url : "emkEquipController.do?getEquipType&code=${emkEquipPage.zclx}",
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

										if(dataitem[0]!="") {
											if(dataitem[0] == '${emkEquipPage.pp}'){
												option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
											}else{
												option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
											}
										}
									}
									$("#pp").append(option1);

									$.ajax({
										url : "emkEquipController.do?getEquipType&code=${emkEquipPage.pp}",
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
														if(dataitem[0] == '${emkEquipPage.xh}'){
															option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
														}else{
															option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
														}
													}
												}
												$("#xh").append(option1);
											}
										}
									});
								}
							}
						});

					}
				}
			});

			$("#zclx").change(function(){
				$.ajax({
					url : "emkEquipController.do?getEquipType&code="+$("#zclx").val(),
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
			});


		});
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkEquipController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkEquipPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font> 省份:
				</label>
			</td>
			<td class="value">
				<select id="shengFen" name="shengFen" datatype="*" style="width:157px;">

				</select>
					<%--<input id="pq" name="pq" type="text" style="width: 150px" class="inputxt" >--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">省份</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font> 城市:
				</label>
			</td>
			<td class="value">
				<select datatype="*" id="chengShi" name="chengShi" style="width:157px;">

				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">城市</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font> 片区:
				</label>
			</td>
			<td class="value" colspan="3">
				<select datatype="*" id="pianQu" name="pianQu" style="width:157px;">

				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">片区</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font> 资产编号:
				</label>
			</td>
			<td class="value">
				<input id="gzbh" name="gzbh" type="text" datatype="*" value="${emkEquipPage.gzbh }" validType="emk_equip,gzbh,id"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">资产编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>资产名称:
				</label>
			</td>
			<td class="value">
				<input id="zcmc" name="zcmc" type="text" datatype="*" value="${emkEquipPage.zcmc }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">资产名称</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>资产类型:
				</label>
			</td>
			<td class="value">
				<select id="zclx" name="zclx" datatype="*" style="width:157px;" datatype="*">
					<option value="">--选择--</option>
					<%--<c:forEach var="item" items="${codeList2}">
						<option value="${item.code}">${item.name}</option>
					</c:forEach>--%>
				</select>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">资产类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>品牌:
				</label>
			</td>
			<td class="value">
				<select id="pp" name="pp" datatype="*" style="width:157px;" datatype="*">

				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">品牌</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>型号:
				</label>
			</td>
			<td class="value">
				<select id="xh" name="xh" datatype="*" style="width:157px;" datatype="*">

				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">型号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font> 序列号:
				</label>
			</td>
			<td class="value">
				<input id="xlh" datatype="*" name="xlh" type="text" value="${emkEquipPage.xlh }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">序列号</label>
			</td>

		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					位置
				</label>
			</td>
			<td class="value">
				<input id="wz" name="wz" type="text" value="${emkEquipPage.wz }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">位置</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					用途
				</label>
			</td>
			<td class="value">
				<input id="yt" name="yt" type="text" value="${emkEquipPage.yt }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用途</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					状态:
				</label>
			</td>
			<td class="value">
				<input id="zt" name="zt" type="text" value="${emkEquipPage.zt }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					归属:
				</label>
			</td>
			<td class="value">
				<input id="gs" name="gs" type="text" value="${emkEquipPage.gs }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">归属</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					上架日期:
				</label>
			</td>
			<td class="value">
				<input id="sjrq" name="sjrq" type="text" value="${emkEquipPage.sjrq }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="width: 150px" class="Wdate" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">上架日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					托管人
				</label>
			</td>
			<td class="value">
				<input id="tgr" name="tgr"  type="text" value="${emkEquipPage.tgr }" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">托管人</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="remark" style="width:90%;height:60px" class="inputxt" rows="3" name="remark">${emkEquipPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>

	</table>
</t:formvalid>
</body>
<script src = "webpage/com/uflow/equip/emkEquip.js"></script>
