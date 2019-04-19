<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>验货申请表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){

		});



	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkCheckController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkCheckPage.id }"/>
	<input id="type" name="type" type="hidden" value="0"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right">
				<label class="Validform_label">
					验货申请单号:
				</label>
			</td>
			<td class="value">
				<input id="checkNum" name="checkNum" type="text" readonly value="${checkNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货申请单号</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkCheckPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkCheckPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			</tr>

		<tr>
			<td class="value" colspan="2"></td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" colspan="2">
				<select class="form-control select2" id="businesserId"  >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkCheckPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkCheckPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkCheckPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkCheckPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkCheckPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkCheckPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkCheckPage.cusName }" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkCheckPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td class="value" colspan="2"></td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td class="value" colspan="2"></td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工厂名称:
				</label>
			</td>
			<td class="value" >
				<input id="factoryName" name="factoryName" type="text" value="${emkCheckPage.factoryName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称</label>
			</td>
			<td class="value" colspan="2"></td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工厂地址:
				</label>
			</td>
			<td class="value" >
				<input id="address" name="address" type="text" value="${emkCheckPage.address }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂地址</label>
			</td>
			<td class="value" colspan="2"></td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value" >
				<input id="relationer" name="relationer" type="text" value="${emkCheckPage.relationer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
			<td class="value" colspan="2"></td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value" >
				<input id="telphone" name="telphone" type="text" value="${emkCheckPage.telphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td class="value" colspan="2"></td>

		</tr>



	</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkInvoicesController.do?detailMxList&invoicesId=${emkCheckPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="19%">
				<label class="Validform_label">
					验货时间:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货时间</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					验货类型:
				</label>
			</td>
			<td class="value">
				<input id="checkType" name="checkType" type="text" value="${emkCheckPage.checkType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货类型</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
