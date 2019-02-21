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
			$("#businessType").val("${ymkCustomPage.businessType}");
			$("#cusTypeName").val("${ymkCustomPage.cusTypeName}");

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

					}
				}
			});

			BindSelect("businesserId","ymkCustomController.do?findUserList&userKey=业务员",1,"${ymkCustomPage.businesserName},${ymkCustomPage.businesser}");
			$("#businesserId").change(function(){
				var itemarr = $("#businesserId").val().split(","); //字符分割
				$("#businesser").val(itemarr[1]);
				$("#businesserName").val(itemarr[0]);
			});

			BindSelect("tracerId","ymkCustomController.do?findUserList&userKey=业务跟单员",1,"${ymkCustomPage.tracerName},${ymkCustomPage.tracer}");
			$("#tracerId").change(function(){
				var itemarr = $("#tracerId").val().split(","); //字符分割
				$("#tracer").val(itemarr[1]);
				$("#tracerName").val(itemarr[0]);
			});


			BindSelect("developerId","ymkCustomController.do?findUserList&userKey=生产跟单员",1,"${ymkCustomPage.developerName},${ymkCustomPage.developer}");
			$("#developerId").change(function(){
				var itemarr = $("#developerId").val().split(","); //字符分割
				$("#developer").val(itemarr[1]);
				$("#developerName").val(itemarr[0]);
			});

			$("#cusType").change(function(){
				$.ajax({
					url : "ymkCustomController.do?getCustomTypeName&groupCode="+$("#cusType").val(),
					type : 'post',
					cache : false,
					data: null,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							$('#cusTypeName').empty();
							var option1 = '<option value="">请选择</option>';
							$.each(d.obj, function (i, item) {
								option1 += '<option value='+item.typecode+'>'+item.typename+'</option>';
							});
							$("#cusTypeName").append(option1);
						}
					}
				});
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
					control.append("<option value='" + item.realName + ","+item.userName +"'>&nbsp;" + item.realName + "</option>");
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
	</script>
</head>
<body>
<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${ymkCustomPage.id }"/>
	<input id="businesser" name="businesser" type="hidden" value="${ymkCustomPage.businesser }"/>
	<input id="businesserName" name="businesserName" type="hidden" value="${ymkCustomPage.businesserName }"/>
	<input id="tracer" name="tracer" type="hidden" value="${ymkCustomPage.tracer }"/>
	<input id="tracerName" name="tracerName" type="hidden" value="${ymkCustomPage.tracerName }"/>
	<input id="developer" name="developer" type="hidden" value="${ymkCustomPage.developer }"/>
	<input id="developerName" name="developerName" type="hidden" value="${ymkCustomPage.developerName }"/>
	<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					档案编号:
				</label>
			</td>
			<td class="value">
				<input id="daanNum" name="daanNum" type="text" value="${ymkCustomPage.daanNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">档案编号</label>
			</td>
		</tr>

		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" type="text" value="${ymkCustomPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${ymkCustomPage.businesseDeptName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${ymkCustomPage.businesseDeptId }" type="hidden"  />
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
				<input id="cusNum" name="cusNum" type="text" value="${ymkCustomPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					客户地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address"  type="text" style="width: 150px"  value="${ymkCustomPage.address }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户地址</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务跟单员</label>
			</td>
		</tr>
		<tr>
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

			<td align="right">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产跟单员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					主营产品:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="mainContent" style="width:90%;height:50px" class="inputxt" rows="3" name="mainContent">${ymkCustomPage.mainContent }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主营产品</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="cusType" field="cusType" typeGroupCode="custom" datatype="*" defaultVal="${ymkCustomPage.cusType}" hasLabel="false" title="客户类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					名称:
				</label>
			</td>
			<td class="value">
				<select id="cusTypeName" name="cusTypeName" >
					<option value=''>请选择</option>
					<c:forEach var="type" items="${typeList}">
						<option value="${type.typecode}">${type.typename}</option>
					</c:forEach>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					业务类型:
				</label>
			</td>
			<td class="value" colspan="3">
				<select id="businessType" name="businessType">
					<option name="0">直接</option>
					<option name="1">中间</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					潜在业务量/年:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="qzywl" name="qzywl" type="text" datatype="*" value="${ymkCustomPage.qzywl }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">潜在业务量/年</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					建立商业关系时间:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="relationDate" name="relationDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${ymkCustomPage.relationDate}" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">建立商业关系时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					区域:
				</label>
			</td>
			<td class="value" colspan="3">
				<select id="shengFen" name="shengFen"  style="width:155px;">
					<option>--区域--</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">区域</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					国家:
				</label>
			</td>
			<td class="value" colspan="3">
				<t:dictSelect id="guoJia" field="guoJia" typeGroupCode="trade" datatype="*" defaultVal="${ymkCustomPage.guoJia}" hasLabel="false" title="国家"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">国家</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					联系人
				</label>
			</td>
			<td class="value" colspan="3">
				<table style="width:890px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr >
						<td width="50" height="32" align="center"></td>
						<td width="120" >&nbsp;&nbsp;业务联系人一</td>
						<td width="120" >&nbsp;&nbsp;包装联系人</td>
						<td width="120" >&nbsp;&nbsp;测试联系人</td>
						<td width="120" >&nbsp;&nbsp;质量联系人</td>
						<td width="120" >&nbsp;&nbsp;验厂联系人</td>
						<td width="120" >&nbsp;&nbsp;船务联系人</td>
						<td width="120" >&nbsp;&nbsp;法律联系人</td>
					</tr>
					<tr>
						<td width="50" height="32" align="center">姓名</td>
						<td class="value" height="32">
							<input id="zlxr" name="zlxr" type="text" datatype="*" value="${ymkCustomPage.zlxr }" style="width: 90px" class="inputxt"   />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主联系人</label>
						</td>

						<td class="value" height="32">
							<input id="bzlxr" name="bzlxr" type="text" datatype="*" value="${ymkCustomPage.bzlxr }" style="width: 90px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">包装联系人</label>
						</td>

						<td class="value" height="32">
							<input id="cslxr" name="cslxr" type="text" datatype="*" value="${ymkCustomPage.cslxr }" style="width: 90px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">测试联系人</label>
						</td>

						<td class="value" height="32">
							<input id="zllxr" name="zllxr" type="text" datatype="*" value="${ymkCustomPage.zllxr }" style="width: 90px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">质量联系人</label>
						</td>

						<td class="value" height="32">
							<input id="yclxr" name="yclxr" type="text" datatype="*" value="${ymkCustomPage.yclxr }" style="width: 90px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">验厂联系人</label>
						</td>

						<td class="value" height="32">
							<input id="cwlxr" name="cwlxr" type="text" datatype="*" value="${ymkCustomPage.cwlxr }" style="width: 90px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">船务联系人</label>
						</td>

						<td class="value" height="32">
							<input id="fllxr" name="fllxr" type="text" datatype="*" value="${ymkCustomPage.fllxr }" style="width: 90px" class="inputxt"   />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法律联系人</label>
						</td>
					</tr>
					<tr>
						<td align="center">邮箱</td>
						<td class="value">
							<input id="email" name="email" type="text" datatype="e" value="${ymkCustomPage.email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value">
							<input id="bzlxrEmail" name="bzlxrEmail" datatype="e" type="text" value="${ymkCustomPage.bzlxrEmail }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value">
							<input id="cslxrEmail" name="cslxrEmail" datatype="e" type="text" value="${ymkCustomPage.cslxrEmail }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value">
							<input id="zllxrEmail" name="zllxrEmail" datatype="e" type="text" value="${ymkCustomPage.zllxrEmail }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value">
							<input id="yclxrEmail" name="yclxrEmail" datatype="e" type="text" value="${ymkCustomPage.yclxrEmail }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value">
							<input id="cwlxrEmail" name="cwlxrEmail" datatype="e"  type="text" value="${ymkCustomPage.cwlxrEmail }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value">
							<input id="fllxrEmail" name="fllxrEmail" datatype="e" type="text" value="${ymkCustomPage.fllxrEmail }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>

					</tr>
					<tr>
						<td align="center">电话</td>
						<td class="value">
							<input id="workphone" name="workphone" datatype="m" value="${ymkCustomPage.workphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value">
							<input id="bzlxrTelphone" name="bzlxrTelphone" datatype="m" value="${ymkCustomPage.bzlxrTelphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value">
							<input id="cslxrTelphone" name="cslxrTelphone" datatype="m" value="${ymkCustomPage.cslxrTelphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value">
							<input id="zllxrTelphone" name="zllxrTelphone" datatype="m" value="${ymkCustomPage.zllxrTelphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value">
							<input id="yclxrTelphone" name="yclxrTelphone" datatype="m" value="${ymkCustomPage.yclxrTelphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value">
							<input id="cwlxrTelphone" name="cwlxrTelphone" datatype="m" value="${ymkCustomPage.cwlxrTelphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value">
							<input id="fllxrTelphone" name="fllxrTelphone" datatype="m" value="${ymkCustomPage.fllxrTelphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务联系人
				</label>
			</td>
			<td class="value" colspan="3">
				<table style="width:890px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr >
						<td width="50" height="32" align="center"></td>
						<td width="120" >&nbsp;&nbsp;联系人二</td>
						<td width="120">&nbsp;&nbsp;联系人三</td>
						<td width="120">&nbsp;&nbsp;联系人四</td>
						<td width="120">&nbsp;&nbsp;联系人五</td>
						<td width="360" colspan="3">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td width="50" height="32" align="center">姓名</td>
						<td class="value" height="32">
							<input id="ywlxr2" name="ywlxr2" type="text" value="${ymkCustomPage.ywlxr2 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人二</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr3" name="ywlxr3" type="text"  value="${ymkCustomPage.ywlxr3 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人三</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr4" name="ywlxr4" type="text"  value="${ymkCustomPage.ywlxr4 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人四</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr5" name="ywlxr5" type="text"  value="${ymkCustomPage.ywlxr5 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人五</label>
						</td>
						<td class="value" colspan="3" height="32">
					</tr>
					<tr>
						<td width="50" height="32" align="center">邮箱</td>
						<td class="value" height="32">
							<input id="ywlxr2Email" name="ywlxr2Email" type="text" datatype="e" value="${ymkCustomPage.ywlxr2Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人二</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr3Email" name="ywlxr3Email" type="text"  datatype="e" value="${ymkCustomPage.ywlxr3Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人三</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr4Email" name="ywlxr4Email" type="text"  datatype="e" value="${ymkCustomPage.ywlxr4Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人四</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr5Email" name="ywlxr5Email" type="text"  datatype="e" value="${ymkCustomPage.ywlxr5Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人五</label>
						</td>
						<td class="value" colspan="3" height="32">
					</tr>
					<tr>
						<td width="50" height="32" align="center">电话</td>
						<td class="value" height="32">
							<input id="ywlxr2Telphone" name="ywlxr2Telphone" datatype="m" type="text" value="${ymkCustomPage.ywlxr2Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人二</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr3Telphone" name="ywlxr3Telphone" datatype="m" type="text"  value="${ymkCustomPage.ywlxr3Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人三</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr4Telphone" name="ywlxr4Telphone" datatype="m" type="text"  value="${ymkCustomPage.ywlxr4Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人四</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr5Telphone" name="ywlxr5Telphone" datatype="m" type="text"  value="${ymkCustomPage.ywlxr5Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人五</label>
						</td>
						<td class="value" colspan="3" height="32">
					</tr>
				</table>
			</td>
		</tr>

	</table>
</t:formvalid>
</body>
<script src = "webpage/com/service/custom/ymkCustom.js"></script>
