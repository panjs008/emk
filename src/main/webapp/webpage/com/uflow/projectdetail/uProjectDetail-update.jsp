<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>项目清单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			var firstJgmc;

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
						$('#sblx').empty();
						var option1 = '<option value="">--选择--</option>';

						var firstJgmc;
						for (i=0;i<dataItems.length ;i++ ) {
							var dataitem = new Array(); //定义一数组
							dataitem = dataItems[i].split(","); //字符分割

							if(dataitem[0]!="") {
								if(dataitem[0] == '${uProjectDetailPage.sblx}'){
									option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
								}else{
									option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}
						}
						$("#sblx").append(option1);

						$.ajax({
							url : "emkEquipController.do?getEquipType&code=${uProjectDetailPage.sblx}",
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
											if(dataitem[0] == '${uProjectDetailPage.pp}'){
												option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
											}else{
												option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
											}
										}
									}
									$("#pp").append(option1);

									$.ajax({
										url : "emkEquipController.do?getEquipType&code=${uProjectDetailPage.pp}",
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
														if(dataitem[0] == '${uProjectDetailPage.xh}'){
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
			});


		});

		function  setCost(){
			if($("#price").val() != "" && $("#total").val() != ""){
				var re = /([0-9]+\.[0-9]{2})[0-9]*/;
				var aNew;
				var sumPrice = parseFloat($("#price").val())*parseFloat($("#total").val())+"";
				aNew = sumPrice.replace(re,"$1");
				$("#sumPrice").val(aNew);
			}
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="uProjectDetailController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${uProjectDetailPage.id }"/>
	<input id="projectId" name="projectId" type="hidden" value="${uProjectDetailPage.projectId }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>设备名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="proName" name="proName" type="text" value="${uProjectDetailPage.proName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">设备名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>设备类型:
				</label>
			</td>
			<td class="value">
				<select id="sblx" name="sblx" datatype="*" style="width:157px;" datatype="*">
					<option value="">--选择--</option>
					<c:forEach var="item" items="${codeList2}">
						<option value="${item.code}">${item.name}</option>
					</c:forEach>
				</select>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">设备类型</label>
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
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit" type="text" value="${uProjectDetailPage.unit }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price" type="text" value="${uProjectDetailPage.price }" onkeyup="setCost()" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" value="${uProjectDetailPage.total }" onkeyup="setCost()" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总价:
				</label>
			</td>
			<td class="value">
				<input id="sumPrice" name="sumPrice" type="text" value="${uProjectDetailPage.sumPrice }" onkeyup="setCost()" readonly style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总价</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					质保年限:
				</label>
			</td>
			<td class="value">
				<input id="zbTime" name="zbTime" type="text" value="${uProjectDetailPage.zbTime }" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">质保年限</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark">${uProjectDetailPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/uflow/projectdetail/uProjectDetail.js"></script>
