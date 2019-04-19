<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>尺寸检查表</title>
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
<t:formvalid formid="sizeCheckFrm" dialog="true" usePlugin="password" layout="table" action="emkSizeCheckController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSizeCheckPage.id }"/>
	<table style="width: 100%;margin-top: 5px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" class="value" colspan="7" >
				<label class="Validform_label">
					报告号:
				</label>
			</td>
			<td class="value" >
				<input id="sizeCheckNum" name="sizeCheckNum" value="${emkSizeCheckPage.sizeCheckNum eq null ? sizeCheckNum:emkSizeCheckPage.sizeCheckNum }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报告号</label>
			</td>
		</tr>
		<tr>
			<td align="right" class="value" colspan="7">
				<label class="Validform_label">
					报告日期:
				</label>
			</td>
			<td class="value" >
				<input id="kdDate" name="kdDate" readonly  value="${emkSizeCheckPage.kdDate eq null ? kdDate:emkSizeCheckPage.kdDate  }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报告日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工厂名称+代码:
				</label>
			</td>
			<td class="value">
				<input id="factoryName" name="factoryName" type="text"  value="${emkSizeCheckPage.factoryName }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称+代码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订单数量:
				</label>
			</td>
			<td class="value">
				<input id="orderTotal" datatype="n" name="orderTotal"  value="${emkSizeCheckPage.orderTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					品名描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleDesc" name="sampleDesc" type="text"  value="${emkSizeCheckPage.sampleDesc }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">品名描述</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					样品数量:
				</label>
			</td>
			<td class="value">
				<input id="ypTotal" datatype="n" name="ypTotal" type="text" value="${emkSizeCheckPage.ypTotal }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品数量</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value" >
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" value="${emkSizeCheckPage.sampleNo }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					出货日期:
				</label>
			</td>
			<td class="value" >
				<input id="outDate" name="outDate" readonly  value="${emkSizeCheckPage.outDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					样品尺码:
				</label>
			</td>
			<td class="value" >
				<input id="ypSize" name="ypSize" type="text"  value="${emkSizeCheckPage.ypSize }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品尺码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value" >
				<input id="ypType" name="ypType" type="text" value="${emkSizeCheckPage.ypType }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo" type="text"  value="${emkSizeCheckPage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					样品日期:
				</label>
			</td>
			<td class="value" >
				<input id="ypDate" name="ypDate" readonly  value="${emkSizeCheckPage.ypDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					样品颜色:
				</label>
			</td>
			<td class="value" >
				<input id="color" name="color" type="text" value="${emkSizeCheckPage.color }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品颜色</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="vesion" name="vesion" type="text"  value="${emkSizeCheckPage.vesion }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总箱数:
				</label>
			</td>
			<td class="value">
				<input id="boxTotal" name="boxTotal" type="text"  value="${emkSizeCheckPage.boxTotal }" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总箱数</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					合同号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum" type="text" value="${emkSizeCheckPage.htNum }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					验货类型:
				</label>
			</td>
			<td class="value">
				<input id="checkType" name="checkType"  value="${emkSizeCheckPage.checkType }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					抽检数量:
				</label>
			</td>
			<td class="value">
				<input id="checkTotal" name="checkTotal" value="${emkSizeCheckPage.checkTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽检数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					检查日期:
				</label>
			</td>
			<td class="value">
				<input id="checkDate" name="checkDate" readonly  value="${emkSizeCheckPage.checkDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">检查日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					尺码范围:
				</label>
			</td>
			<td class="value">
				<input id="size" name="size" type="text"  value="${emkSizeCheckPage.size }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码范围</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					尺寸单位:
				</label>
			</td>
			<td class="value">
				<input id="chima" name="chima" type="text"  value="${emkSizeCheckPage.chima }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺寸单位</label>
			<td align="right">
				<label class="Validform_label">
					抽检结果:
				</label>
			</td>
			<td class="value">
				<input id="checkResult" name="checkResult"  value="${emkSizeCheckPage.checkResult }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽检结果</label>
			</td>
		</tr>
		</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkSizeCheckController.do?detailMxList&sizeCheckId=${emkSizeCheckPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkSizeCheckPage.cusName }"  style="width: 150px" class="inputxt"  datatype="*"/>
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
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly value="${emkSizeCheckPage.cusNum }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" value="${emkSizeCheckPage.gysCode }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkSizeCheckPage.gys }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkSizeCheckPage.businesseDeptName }"  readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkSizeCheckPage.businesseDeptId }"   type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					工厂名称:
				</label>
			</td>
			<td class="value" >
				<input id="factoryName2" type="text" style="width: 150px" value="${emkSizeCheckPage.factoryName }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称+代码</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkSizeCheckPage.businesser }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkSizeCheckPage.businesserName }"   type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					工厂地址:
				</label>
			</td>
			<td class="value" >
				<input id="address" name="address" value="${emkSizeCheckPage.address }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂地址</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkSizeCheckPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkSizeCheckPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value" >
				<input id="relationer" name="relationer" value="${emkSizeCheckPage.relationer }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkSizeCheckPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkSizeCheckPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value" >
				<input id="telphone" name="telphone" type="text" value="${emkSizeCheckPage.telphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
	</table>

</t:formvalid>
</body>
<script src = "webpage/com/emk/check/qualitycheck/emkQualityCheck.js"></script>
