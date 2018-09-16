<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>询盘单明细</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function(){
			$.ajax({
				url : "ymkCustomController.do?getCity&code=A03",
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
						$('#color').empty();
						var option1 = '<option value="">--选择--</option>';
						var firstJgmc;
						for (i=0;i<dataItems.length ;i++ ) {
							var dataitem = new Array(); //定义一数组
							dataitem = dataItems[i].split(","); //字符分割
							if(dataitem[0]!="") {
								if(dataitem[0] == '${emkEnquiryDetailPage.color}'){
									option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
								}else{
									option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}
						}
						$("#color").append(option1);
						if(${emkEnquiryDetailPage.colorValue ne '' && emkEnquiryDetailPage.colorValue ne null}){
							$.ajax({
								url : "ymkCustomController.do?getCity&code=${emkEnquiryDetailPage.color}",
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
										$('#colorValue').empty();
										var option1='';
										for (i=0;i<dataItems.length ;i++ ) {
											var dataitem = new Array(); //定义一数组
											dataitem = dataItems[i].split(","); //字符分割
											if(dataitem[0] == '${emkEnquiryDetailPage.colorValue}'){
												option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
											}else{
												option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
											}
										}
										$("#colorValue").append(option1);


									}
								}
							});
						}

					}
				}
			});

			$("#color").change(function(){
				$.ajax({
					url : "ymkCustomController.do?getCity&code="+$("#color").val(),
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
							$('#colorValue').empty();

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
							$("#colorValue").append(option3);

						}
					}
				});
			});


		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkEnquiryDetailController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkEnquiryDetailPage.id }"/>
	<input id="enquiryId" name="enquiryId" type="hidden" value="${emkEnquiryDetailPage.enquiryId }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色名称:
				</label>
			</td>
			<td class="value">
				<select id="color" name="color" datatype="*" style="width:160px;">
					<option>--颜色--</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					色号:
				</label>
			</td>
			<td class="value">
				<select id="colorValue" name="colorValue" datatype="*" style="width:90px;">
					<option>--色号--</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					尺码:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="size" field="size" typeGroupCode="size" datatype="*" defaultVal="${emkEnquiryDetailPage.size}" hasLabel="false" title="尺码"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" datatype="n" value="${emkEnquiryDetailPage.total}"  type="text" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单尺码总数:
				</label>
			</td>
			<td class="value">
				<input id="sizeTotal" name="sizeTotal" readonly value="${emkEnquiryDetailPage.sizeTotal}" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单尺码总数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单颜色总数:
				</label>
			</td>
			<td class="value">
				<input id="colorTotal" name="colorTotal" value="${emkEnquiryDetailPage.colorTotal}"  readonly type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单颜色总数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price" type="text" style="width: 150px" class="inputxt"  value="${emkEnquiryDetailPage.price}"  datatype="/^(-?\d+)(\.\d+)?$/"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/enquirydetail/emkEnquiryDetail.js"></script>
