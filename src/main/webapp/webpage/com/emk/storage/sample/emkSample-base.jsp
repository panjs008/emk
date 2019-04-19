<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			$("#type").change(function(){
				varval = $('#type').val();
				if(varval == 'cy'){
					$("#orderNo").attr("ignore","ignore");
					$("#pirceNo").removeAttr("ignore");
				}else{
					/* $("#settb").css("display","none");
					 $("#settb2").css("display","none");

					 $("#tk0").attr("ignore","ignore");
					 $("#settb").find(":text,textarea").attr("ignore","ignore");
					 $("#settb2").find(":text,textarea").attr("ignore","ignore");*/
					$("#pirceNo").attr("ignore","ignore");
					$("#orderNo").removeAttr("ignore");
				}
			});


		});

		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#khyyId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#sampleSizeUrl").val(d.attributes.url);
			$("#sampleSize").val(d.attributes.name);
			$("#ccbId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg2").attr('src',d.attributes.url);

		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkYptzdController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSamplePage.id }"/>
	<input id="flag" name="flag" type="hidden" value="${param.flag}"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					打样通知单编号:
				</label>
			</td>
			<td class="value" >
				<input id="sampleNum" name="sampleNum" readonly value="${emkSamplePage.sampleNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样通知单编号</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					打样通知单日期:
				</label>
			</td>
			<td class="value">
				<input id="kdTime" name="kdTime" value="${emkSamplePage.kdTime }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样通知单日期</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					打样需求单编号:
				</label>
			</td>
			<td class="value">
				<input id="xqdh" name="xqdh" type="text" value="${emkSamplePage.xqdh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单编号</label>
			</td>
		</tr>

		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					打样需求单日期:
				</label>
			</td>
			<td class="value">
				<input id="dyxqdTime" name="dyxqdTime" value="${emkSamplePage.dyxqdTime }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单日期</label>
			</td>
		</tr>

		<tr>
			<td class="value" align="right" colspan="7">
				<label class="Validform_label">
					工厂代码:
				</label>
			</td>
			<td class="value">
				<input id="factoryCode" name="factoryCode" value="${emkSamplePage.factoryCode }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单日期</label>
			</td>
		</tr>
		<%--<tr>
			<td align="right" >
				<label class="Validform_label">
					报价单号:
				</label>
			</td>
			<td class="value" >
				<input id="pirceNo" name="pirceNo" datatype="*" value="${emkSamplePage.pirceNo}" type="text" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo" datatype="*"  value="${emkSamplePage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>--%>
		<%--<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="cusName" name="cusName" readonly type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>--%>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkSamplePage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkSamplePage.proTypeName }">
				<input id="proTypeName" name="proTypeName" value="${emkSamplePage.proTypeName }" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkSamplePage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkSamplePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version"  type="text" value="${emkSamplePage.version }" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>
		</tr>
		<%--<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>


		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>--%>

		<tr>
			<td align="right">
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="type" field="type"  typeGroupCode="sampletype" datatype="*" defaultVal="${emkSamplePage.type }" hasLabel="false" title="样品类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkSamplePage.sampleNoDesc }"  type="text" style="width: 95%"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					大货出货日期:
				</label>
			</td>
			<td class="value">
				<input id="dhjq" name="dhjq" readonly  value="${emkSamplePage.dhjq }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdTime\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大货出货日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					原料规格:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="ylgg" name="ylgg" value="${emkSamplePage.ylgg }"  type="text" style="width: 80%"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					平方克重:
				</label>
			</td>
			<td class="value">
				<input id="pfkz" name="pfkz" value="${emkSamplePage.pfkz }"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">平方克重</label>
			</td>
		</tr>
	</table>
	<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkYptzdController.do?detailMxList&proOrderId=${emkSamplePage.id }" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkYptzdController.do?contentList&type=0&sampleId=${emkSamplePage.id }" icon="icon-search" title="一. 织造（无缝）:" id="detail"></t:tab>
	</t:tabs>
	<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkYptzdController.do?contentList&type=1&sampleId=${emkSamplePage.id }" icon="icon-search" title="二.染色:" id="detail"></t:tab>
	</t:tabs>
	<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkYptzdController.do?contentList&type=2&sampleId=${emkSamplePage.id }" icon="icon-search" title="三.缝制:" id="detail"></t:tab>
	</t:tabs>

</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 157,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>