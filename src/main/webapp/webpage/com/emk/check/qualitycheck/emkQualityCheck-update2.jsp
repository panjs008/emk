<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>质量检查表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码
		function resetTrNum(tableId) {
			$tbody = $("#"+tableId+"");
			$tbody.find('>tr').each(function(i){
				$(':input, select', this).each(function(){
					var $this = $(this), name = $this.attr('name'), val = $this.val();
					if(name!=null){
						if (name.indexOf("#index#") >= 0){
							$this.attr("name",name.replace('#index#',i));
						}else{
							var s = name.indexOf("[");
							var e = name.indexOf("]");
							var new_name = name.substring(s+1,e);
							$this.attr("name",name.replace(new_name,i));
						}
					}
				});
			});
		}
		$(document).ready(function(){
			$("#detailId").load("emkQualityCheckController.do?orderMxList&proOrderId=${emkQualityCheckPage.id }");

		});


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkQualityCheckController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkQualityCheckPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					质量检查编号:
				</label>
			</td>
			<td class="value" >
				<input id="qualityCheckNum" name="qualityCheckNum" value="${emkQualityCheckPage.qualityCheckNum}" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">质量检查编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkQualityCheckPage.cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="cusName" name="cusName" readonly type="text" value="${emkQualityCheckPage.cusName}" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkQualityCheckPage.businesseDeptName}" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkQualityCheckPage.businesseDeptId}" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkQualityCheckPage.businesser}" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkQualityCheckPage.businesserName}"   type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkQualityCheckPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkQualityCheckPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkQualityCheckPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkQualityCheckPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					查验结果:
				</label>
			</td>
			<td class="value">
				<input name="cyjg" type="radio" datatype="*"  <c:if test="${emkQualityCheckPage.cyjg eq '0'}">checked="true"</c:if> value="0">
				通过
				<input name="cyjg" type="radio" datatype="*"  <c:if test="${emkQualityCheckPage.cyjg eq '1'}">checked="true"</c:if> value="1">
				不通过
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">查验结果</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工单号:
				</label>
			</td>
			<td class="value">
				<input id="workNo" name="workNo" datatype="*" value="${emkQualityCheckPage.workNo}"  type="text" style="width: 150px" class="inputxt" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					报告日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkQualityCheckPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报告日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					检查日期:
				</label>
			</td>
			<td class="value">
				<input id="checkDate" name="checkDate" readonly value="${emkQualityCheckPage.checkDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">检查日期</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					合同号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum" type="text" style="width: 150px" value="${emkQualityCheckPage.htNum }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo" type="text" value="${emkQualityCheckPage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>


			<td align="right">
				<label class="Validform_label">
					抽检数量:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="checkTotal" name="checkTotal" type="text"  value="${emkQualityCheckPage.checkTotal }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽检数量</label>
			</td>
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
				<input id="gysCode" name="gysCode" type="hidden" value="${emkQualityCheckPage.gysCode }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" style="width: 150px" value="${emkQualityCheckPage.gys }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version" type="text" value="${emkQualityCheckPage.version }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkQualityCheckPage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					验货类型:
				</label>
			</td>
			<td class="value">
				<input id="checkType" name="checkType" value="${emkQualityCheckPage.checkType }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货类型</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					工厂名称:
				</label>
			</td>
			<td class="value">
				<input id="factoryName" name="factoryName" value="${emkQualityCheckPage.factoryName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工厂地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" type="text" value="${emkQualityCheckPage.address }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂地址</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="relationer" name="relationer" type="text" value="${emkQualityCheckPage.relationer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" value="${emkQualityCheckPage.telphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="7">
				<textarea  id="sampleDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleDesc">${emkQualityCheckPage.sampleDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色:
				</label>
			</td>
			<td class="value">
				<input id="color" name="color" type="text" style="width: 150px" value="${emkQualityCheckPage.telphone }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					尺码范围:
				</label>
			</td>
			<td class="value">
				<input id="size" name="size" type="text" style="width: 150px" value="${emkQualityCheckPage.telphone }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码范围</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" style="width: 150px" value="${emkQualityCheckPage.telphone }" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总箱数:
				</label>
			</td>
			<td class="value">
				<input id="boxTotal" name="boxTotal" type="text" style="width: 150px" value="${emkQualityCheckPage.telphone }" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总箱数</label>
			</td>
		</tr>

		<tr>
			<td align="center" colspan="8">
				<label class="Validform_label">
					布面颜色
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					污渍:
				</label>
			</td>
			<td class="value">
				<input id="wuji" name="wuji" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.wuji }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">污渍</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					油渍:
				</label>
			</td>
			<td class="value">
				<input id="youji" name="youji" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.youji }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">油渍</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					起毛:
				</label>
			</td>
			<td class="value">
				<input id="qimao" name="qimao" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.qimao }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">起毛</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					断纱:
				</label>
			</td>
			<td class="value">
				<input id="duans" name="duans" type="text" style="width: 150px" class="inputxt"  value="${emkQualityCheckPage.duans }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">断纱</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					抽纱:
				</label>
			</td>
			<td class="value">
				<input id="chous" name="chous" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.chous }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽纱</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					反纱:
				</label>
			</td>
			<td class="value">
				<input id="fans" name="fans" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.fans }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">反纱</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					破洞:
				</label>
			</td>
			<td class="value">
				<input id="pod" name="pod" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.pod }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">破洞</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					横纹:
				</label>
			</td>
			<td class="value">
				<input id="hengw" name="hengw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.hengw }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">横纹</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					染花:
				</label>
			</td>
			<td class="value">
				<input id="ranhua" name="ranhua" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.ranhua }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染花</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					色差:
				</label>
			</td>
			<td class="value">
				<input id="secha" name="secha" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.secha }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色差</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					配色不良:
				</label>
			</td>
			<td class="value">
				<input id="psbl" name="psbl" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.psbl }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">配色不良</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					爆口:
				</label>
			</td>
			<td class="value">
				<input id="baokou" name="baokou" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.baokou }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">爆口</label>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="8">
				<label class="Validform_label">
					总体外观
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					线头:
				</label>
			</td>
			<td class="value">
				<input id="xt" name="xt" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.xt }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">线头</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					断线:
				</label>
			</td>
			<td class="value">
				<input id="dx" name="dx" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.dx }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">断线</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					落坑:
				</label>
			</td>
			<td class="value">
				<input id="lk" name="lk" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.lk }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">落坑</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					左右不对称:
				</label>
			</td>
			<td class="value">
				<input id="zybdc" name="zybdc" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.zybdc }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">左右不对称</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					套结不良:
				</label>
			</td>
			<td class="value">
				<input id="tjbl" name="tjbl" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.tjbl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">套结不良</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					容皱:
				</label>
			</td>
			<td class="value">
				<input id="rongzhou" name="rongzhou" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.rongzhou }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">容皱</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					止口:
				</label>
			</td>
			<td class="value">
				<input id="zhikou" name="zhikou" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.zhikou }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">止口</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					尺寸:
				</label>
			</td>
			<td class="value">
				<input id="chicun" name="chicun" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.chicun }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺寸</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					每寸针数:
				</label>
			</td>
			<td class="value">
				<input id="mczs" name="mczs" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.mczs }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">每寸针数</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					烫工不良:
				</label>
			</td>
			<td class="value">
				<input id="tgbl" name="tgbl" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.tgbl }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">烫工不良</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					骨位方向错误:
				</label>
			</td>
			<td class="value">
				<input id="gwfxcw" name="gwfxcw" type="text" style="width: 150px" class="inputxt"  value="${emkQualityCheckPage.gwfxcw }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">骨位方向错误</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					驳线不良:
				</label>
			</td>
			<td class="value">
				<input id="bxbl" name="bxbl" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.bxbl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">驳线不良</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					形状不良:
				</label>
			</td>
			<td class="value">
				<input id="xzbl" name="xzbl" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.xzbl }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">形状不良</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					漏物料:
				</label>
			</td>
			<td class="value">
				<input id="lwl" name="lwl" type="text" style="width: 150px" class="inputxt"  value="${emkQualityCheckPage.lwl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">漏物料</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					针孔:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="zhenkong" name="zhenkong" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.zhenkong }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">针孔</label>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="8">
				<label class="Validform_label">
					包装
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					挂错漏打吊牌:
				</label>
			</td>
			<td class="value">
				<input id="gcdp" name="gcdp" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.gcdp }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂错漏打吊牌</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					错打漏打条形码:
				</label>
			</td>
			<td class="value">
				<input id="cdtxm" name="cdtxm" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.cdtxm }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">错打漏打条形码</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					挂牌位置错误:
				</label>
			</td>
			<td class="value">
				<input id="gpwzcw" name="gpwzcw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.gpwzcw }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂牌位置错误</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					衣架用错:
				</label>
			</td>
			<td class="value">
				<input id="yjyc" name="yjyc" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.yjyc }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">衣架用错</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					衣架方向错误:
				</label>
			</td>
			<td class="value">
				<input id="yjfxcw" name="yjfxcw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.yjfxcw }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">衣架方向错误</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					挂得过长:
				</label>
			</td>
			<td class="value">
				<input id="gdgc" name="gdgc" type="text" style="width: 150px" class="inputxt"  value="${emkQualityCheckPage.gdgc }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂得过长</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					错漏码数:
				</label>
			</td>
			<td class="value">
				<input id="clms" name="clms" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.clms }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">错漏码数</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					胶袋内尺码错误:
				</label>
			</td>
			<td class="value">
				<input id="jdncmcw" name="jdncmcw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.jdncmcw }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋内尺码错误</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					胶袋内配比错误:
				</label>
			</td>
			<td class="value">
				<input id="jdnpbcw" name="jdnpbcw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.jdnpbcw }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋内配比错误</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					纸箱杂码混色:
				</label>
			</td>
			<td class="value">
				<input id="zxzmhs" name="zxzmhs" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.zxzmhs }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">纸箱杂码混色</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					包装外观不良:
				</label>
			</td>
			<td class="value">
				<input id="bzwgbl" name="bzwgbl" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.bzwgbl }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装外观不良</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					条形码不能扫描:
				</label>
			</td>
			<td class="value">
				<input id="txmbnsm" name="txmbnsm" type="text" style="width: 150px" class="inputxt"  value="${emkQualityCheckPage.txmbnsm }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">条形码不能扫描</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					吊牌不稳:
				</label>
			</td>
			<td class="value">
				<input id="dpbw" name="dpbw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.dpbw }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">吊牌不稳</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					贴纸不稳:
				</label>
			</td>
			<td class="value">
				<input id="tzbw" name="tzbw" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.tzbw }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">贴纸不稳</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					包装资料不一:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="bzzlby" name="bzzlby" type="text" style="width: 150px" class="inputxt" value="${emkQualityCheckPage.bzzlby }" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装资料不一</label>
			</td>

		</tr>


	</table>
	<div id="detailId" style="width: auto; height: 200px;" ><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
	</div>
	<!-- 添加 产品明细 模版-->
	<table style="width:100%;display: none" cellpadding="0" cellspacing="2" border="0">
		<tbody id="add_jeecgOrderProduct_table_template">
		<tr>
			<td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
			<td align="left">
				<select name="orderMxList[#index#].color" style="width: 80%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
					<c:forEach items="${categoryEntityList}" var="category">
						<option value="${category.code}">${category.name}</option>
					</c:forEach>
				</select>
			</td>
			<td align="left">
				<input id="size00" nullmsg="请输入尺码！"  errormsg="请输入尺码" name="orderMxList[#index#].size" maxlength="100" type="text" value=""
					   style="width: 80%;"></td>
			<td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入整数" name="orderMxList[#index#].signTotal" maxlength="100" type="text" value=""
									style="width: 80%;"></td>

		</tr>
		</tbody>

	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/check/qualitycheck/emkQualityCheck.js"></script>
<script>
	$(document).ready(function() {
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