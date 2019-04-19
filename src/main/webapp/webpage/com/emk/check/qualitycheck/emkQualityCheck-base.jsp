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
		$(document).ready(function(){

		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkQualityCheckController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkQualityCheckPage.id }"/>
	<table style="width: 85%;" align="center" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" class="value" colspan="8" >
				<label class="Validform_label">
					报告号:
				</label>
			</td>
			<td class="value" colspan="4" >
				<input id="qualityCheckNum" name="qualityCheckNum" value="${emkQualityCheckPage.qualityCheckNum eq null ? qualityCheckNum:emkQualityCheckPage.qualityCheckNum }" readonly type="text" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报告号</label>
			</td>
			</tr>
		<tr>
			<td align="right" class="value" colspan="8">
				<label class="Validform_label">
					报告日期:
				</label>
			</td>
			<td class="value" colspan="4" >
				<input id="kdDate" name="kdDate" readonly value="${emkQualityCheckPage.kdDate eq null ? kdDate:emkQualityCheckPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报告日期</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					工厂名称+代码:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="factoryName" name="factoryName"  value="${emkQualityCheckPage.factoryName }" type="text" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称+代码</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					品名描述:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="sampleDesc" name="sampleDesc"  value="${emkQualityCheckPage.sampleDesc }" type="text" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">品名描述</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="sampleNo" name="sampleNo" type="text"  value="${emkQualityCheckPage.sampleNo }" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					样品尺码:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="ypSize" name="ypSize" type="text" value="${emkQualityCheckPage.ypSize }"  style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品尺码</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					合同号:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="htNum" name="htNum" type="text" value="${emkQualityCheckPage.htNum }"  style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同号</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					样品颜色:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="color" name="color" type="text" value="${emkQualityCheckPage.color }"  style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品颜色</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					订单数量:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="orderTotal" datatype="n" name="orderTotal"  value="${emkQualityCheckPage.orderTotal }" type="text" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单数量</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					样品数量:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="ypTotal" datatype="n" name="ypTotal" value="${emkQualityCheckPage.ypTotal }" type="text" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品数量</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					出货日期:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="outDate" name="outDate" readonly  value="${emkQualityCheckPage.outDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货日期</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="ypType" name="ypType" type="text" value="${emkQualityCheckPage.ypType }"  style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					样品日期:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="ypDate" name="ypDate" readonly  value="${emkQualityCheckPage.ypDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品日期</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="version" name="version" type="text"  value="${emkQualityCheckPage.version }" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>

		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					抽检数量:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="checkTotal" name="checkTotal" type="text" value="${emkQualityCheckPage.checkTotal }"  style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽检数量</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					总箱数:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="boxTotal" name="boxTotal" type="text" value="${emkQualityCheckPage.boxTotal }"  style="width: 60%" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总箱数</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					尺码范围:
				</label>
			</td>
			<td class="value">
				<input id="size" name="size" type="text"  value="${emkQualityCheckPage.size }" style="width: 60%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码范围</label>
			</td>
			<td class="value" colspan="9"></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="height: 32px;">
				<label class="Validform_label">
					布面/颜色
				</label>
			</td>
			<td align="center">
				<label class="Validform_label">
					MAJOR
				</label>
			</td>
			<td align="center">
				<label class="Validform_label">
					MINOR
				</label>
			</td>
			<td align="center" colspan="2">
				<label class="Validform_label">
					总体外观
				</label>
			</td>
			<td align="center">
				<label class="Validform_label">
					MAJOR
				</label>
			</td>
			<td align="center">
				<label class="Validform_label">
					MINOR
				</label>
			</td>
			<td align="center" colspan="2">
				<label class="Validform_label">
					包装
				</label>
			</td>
			<td align="center">
				<label class="Validform_label">
					MAJOR
				</label>
			</td>
			<td align="center">
				<label class="Validform_label">
					MINOR
				</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">A</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					污渍:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="wuji" name="wuji" type="text" value="${emkQualityCheckPage.wuji }" datatype="n" style="width: 79%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">污渍</label>
			</td>
			<td class="value" width="10%">
				<input id="wujiminor" name="wujiminor" type="text"   value="${emkQualityCheckPage.wujiminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">污渍</label>
			</td>
			<td align="center" width="7%">M</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					线头:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="xt" name="xt" type="text"   value="${emkQualityCheckPage.xt }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">线头</label>
			</td>
			<td class="value" width="10%">
				<input id="xtminor" name="xtminor" type="text"   value="${emkQualityCheckPage.xtminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">线头</label>
			</td>
			<td align="center" width="7%">b</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					挂错/漏打吊牌:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="gcdp" name="gcdp" type="text"   value="${emkQualityCheckPage.gcdp }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂错/漏打吊牌</label>
			</td>
			<td class="value" width="10%">
				<input id="gcdpminor" name="gcdpminor" type="text"   value="${emkQualityCheckPage.gcdpminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂错/漏打吊牌</label>
			</td>
			</tr>
		<tr>
			<td align="center" width="6%">B</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					油渍:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="youji" name="youji" type="text"   value="${emkQualityCheckPage.youji }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">油渍</label>
			</td>
			<td class="value" width="10%">
				<input id="youjiminor" name="youjiminor" type="text"   value="${emkQualityCheckPage.youjiminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">油渍</label>
			</td>
			<td align="center" width="7%">N</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					断线:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="dx" name="dx" type="text"   value="${emkQualityCheckPage.dx }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">断线</label>
			</td>
			<td class="value" width="10%">
				<input id="dxminor" name="dxminor" type="text"   value="${emkQualityCheckPage.dxminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">断线</label>
			</td>
			<td align="center" width="7%">c</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					错打漏打条形码:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="cdtxm" name="cdtxm" type="text"   value="${emkQualityCheckPage.cdtxm }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">错打漏打条形码</label>
			</td>
			<td class="value" width="10%">
				<input id="cdtxmminor" name="cdtxmminor" type="text"   value="${emkQualityCheckPage.cdtxmminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">错打漏打条形码</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">C</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					起毛:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="qimao" name="qimao" type="text"   value="${emkQualityCheckPage.qimao }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">起毛</label>
			</td>
			<td class="value" width="10%">
				<input id="qimaominor" name="qimaominor" type="text"   value="${emkQualityCheckPage.qimaominor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">起毛</label>
			</td>
			<td align="center" width="7%">O</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					落坑:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="lk" name="lk" type="text"   value="${emkQualityCheckPage.lk }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">落坑</label>
			</td>
			<td class="value" width="10%">
				<input id="lkminor" name="lkminor" type="text"   value="${emkQualityCheckPage.lkminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">落坑</label>
			</td>
			<td align="center" width="7%">d</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					挂牌位置错误:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="gpwzcw" name="gpwzcw" type="text"   value="${emkQualityCheckPage.gpwzcw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂牌位置错误</label>
			</td>
			<td class="value" width="10%">
				<input id="gpwzcwminor" name="gpwzcwminor" type="text"   value="${emkQualityCheckPage.gpwzcwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂牌位置错误</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">D</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					断纱:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="duans" name="duans" type="text"   value="${emkQualityCheckPage.duans }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">断纱</label>
			</td>
			<td class="value" width="10%">
				<input id="duansminor" name="duansminor" type="text"   value="${emkQualityCheckPage.duansminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">断纱</label>
			</td>
			<td align="center" width="7%">P</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					左右不对称:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="zybdc" name="zybdc" type="text"   value="${emkQualityCheckPage.zybdc }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">左右不对称</label>
			</td>
			<td class="value" width="10%">
				<input id="zybdcminor" name="zybdcminor" type="text"   value="${emkQualityCheckPage.zybdcminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">左右不对称</label>
			</td>
			<td align="center" width="7%">e</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					衣架用错:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="yjyc" name="yjyc" type="text"   value="${emkQualityCheckPage.yjyc }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">衣架用错</label>
			</td>
			<td class="value" width="10%">
				<input id="yjycminor" name="yjycminor" type="text"   value="${emkQualityCheckPage.yjycminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">衣架用错</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">E</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					抽纱:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="chous" name="chous" type="text"   value="${emkQualityCheckPage.chous }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽纱</label>
			</td>
			<td class="value" width="10%">
				<input id="chousminor" name="chousminor" type="text"   value="${emkQualityCheckPage.chousminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">抽纱</label>
			</td>
			<td align="center" width="7%">Q</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					套结不良:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="tjbl" name="tjbl" type="text"   value="${emkQualityCheckPage.tjbl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">套结不良</label>
			</td>
			<td class="value" width="10%">
				<input id="tjblminor" name="tjblminor" type="text"   value="${emkQualityCheckPage.tjblminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">套结不良</label>
			</td>
			<td align="center" width="7%">f</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					衣架方向错误:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="yjfxcw" name="yjfxcw" type="text"   value="${emkQualityCheckPage.yjfxcw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">衣架方向错误</label>
			</td>
			<td class="value" width="10%">
				<input id="yjfxcwminor" name="yjfxcwminor" type="text"   value="${emkQualityCheckPage.yjfxcwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">衣架方向错误</label>
			</td>
			</tr>
		<tr>
			<td align="center" width="6%">F</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					反纱:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="fans" name="fans" type="text"   value="${emkQualityCheckPage.fans }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">反纱</label>
			</td>
			<td class="value" width="10%">
				<input id="fansminor" name="fansminor" type="text"   value="${emkQualityCheckPage.fansminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">反纱</label>
			</td>
			<td align="center" width="7%">R</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					容皱:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="rongzhou" name="rongzhou" type="text"   value="${emkQualityCheckPage.rongzhou }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">容皱</label>
			</td>
			<td class="value" width="10%">
				<input id="rongzhouminor" name="rongzhouminor" type="text"   value="${emkQualityCheckPage.rongzhouminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">容皱</label>
			</td>
			<td align="center" width="7%">g</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					挂得过长:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="gdgc" name="gdgc" type="text"   value="${emkQualityCheckPage.gdgc }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂得过长</label>
			</td>
			<td class="value" width="10%">
				<input id="gdgcminor" name="gdgcminor" type="text"   value="${emkQualityCheckPage.gdgcminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">挂得过长</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">G</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					破洞:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="pod" name="pod" type="text"   value="${emkQualityCheckPage.pod }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">破洞</label>
			</td>
			<td class="value" width="10%">
				<input id="podminor" name="podminor" type="text"   value="${emkQualityCheckPage.podminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">破洞</label>
			</td>
			<td align="center" width="7%">S</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					止口:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="zhikou" name="zhikou" type="text"   value="${emkQualityCheckPage.zhikou }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">止口</label>
			</td>
			<td class="value" width="10%">
				<input id="zhikouminor" name="zhikouminor" type="text"   value="${emkQualityCheckPage.zhikouminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">止口</label>
			</td>
			<td align="center" width="7%">h</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					错漏码数:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="clms" name="clms" type="text"   value="${emkQualityCheckPage.clms }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">错漏码数</label>
			</td>
			<td class="value" width="10%">
				<input id="clmsminor" name="clmsminor" type="text"   value="${emkQualityCheckPage.clmsminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">错漏码数</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">H</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					横纹:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="hengw" name="hengw" type="text"   value="${emkQualityCheckPage.hengw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">横纹</label>
			</td>
			<td class="value" width="10%">
				<input id="hengwminor" name="hengwminor" type="text"   value="${emkQualityCheckPage.hengwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">横纹</label>
			</td>
			<td align="center" width="7%">T</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					尺寸:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="chicun" name="chicun" type="text"   value="${emkQualityCheckPage.chicun }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺寸</label>
			</td>
			<td class="value" width="10%">
				<input id="chicunminor" name="chicunminor" type="text"   value="${emkQualityCheckPage.chicunminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺寸</label>
			</td>
			<td align="center" width="7%">i</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					胶袋内尺码错误:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="jdncmcw" name="jdncmcw" type="text"   value="${emkQualityCheckPage.jdncmcw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋内尺码错误</label>
			</td>
			<td class="value" width="10%">
				<input id="jdncmcwminor" name="jdncmcwminor" type="text"   value="${emkQualityCheckPage.jdncmcwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋内尺码错误</label>
			</td>
		</tr>

		<tr>
			<td align="center" width="6%">I</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					染花:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="ranhua" name="ranhua" type="text"   value="${emkQualityCheckPage.ranhua }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染花</label>
			</td>
			<td class="value" width="10%">
				<input id="ranhuaminor" name="ranhuaminor" type="text"   value="${emkQualityCheckPage.ranhuaminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染花</label>
			</td>
			<td align="center" width="7%" >U</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					每寸针数:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="mczs" name="mczs" type="text"   value="${emkQualityCheckPage.mczs }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">每寸针数</label>
			</td>
			<td class="value" width="10%">
				<input id="mczsminor" name="mczsminor" type="text"   value="${emkQualityCheckPage.mczsminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">每寸针数</label>
			</td>
			<td align="center" width="7%">j</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					胶袋内配比错误:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="jdnpbcw" name="jdnpbcw" type="text"   value="${emkQualityCheckPage.jdnpbcw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋内配比错误</label>
			</td>
			<td class="value" width="10%">
				<input id="jdnpbcwminor" name="jdnpbcwminor" type="text"   value="${emkQualityCheckPage.jdnpbcwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋内配比错误</label>
			</td>
			</tr>
		<tr>
			<td align="center" width="6%">J</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					色差:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="secha" name="secha" type="text"   value="${emkQualityCheckPage.secha }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色差</label>
			</td>
			<td class="value" width="10%">
				<input id="sechaminor" name="sechaminor" type="text"   value="${emkQualityCheckPage.sechaminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">色差</label>
			</td>
			<td align="center" width="7%">V</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					烫工不良:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="tgbl" name="tgbl" type="text"   value="${emkQualityCheckPage.tgbl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">烫工不良</label>
			</td>
			<td class="value" width="10%">
				<input id="tgblminor" name="tgblminor" type="text"   value="${emkQualityCheckPage.tgblminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">烫工不良</label>
			</td>
			<td align="center" width="7%">k</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					纸箱杂码混色:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="zxzmhs" name="zxzmhs" type="text"   value="${emkQualityCheckPage.zxzmhs }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">纸箱杂码混色</label>
			</td>
			<td class="value" width="10%">
				<input id="zxzmhsminor" name="zxzmhsminor" type="text"   value="${emkQualityCheckPage.zxzmhsminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">纸箱杂码混色</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">K</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					配色不良:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="psbl" name="psbl" type="text"   value="${emkQualityCheckPage.psbl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">配色不良</label>
			</td>
			<td class="value" width="10%">
				<input id="psblminor" name="psblminor" type="text"   value="${emkQualityCheckPage.psblminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">配色不良</label>
			</td>
			<td align="center" width="7%">W</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					骨位方向错误:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="gwfxcw" name="gwfxcw" type="text"   value="${emkQualityCheckPage.gwfxcw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">骨位方向错误</label>
			</td>
			<td class="value" width="10%">
				<input id="gwfxcwminor" name="gwfxcwminor" type="text"   value="${emkQualityCheckPage.gwfxcwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">骨位方向错误</label>
			</td>
			<td align="center" width="7%">l</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					包装外观不良:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="bzwgbl" name="bzwgbl" type="text"   value="${emkQualityCheckPage.bzwgbl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装外观不良</label>
			</td>
			<td class="value" width="10%">
				<input id="bzwgblminor" name="bzwgblminor" type="text"   value="${emkQualityCheckPage.bzwgblminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装外观不良</label>
			</td>
		</tr>
		<tr>
			<td align="center" width="6%">L</td>
			<td align="center" width="6%">
				<label class="Validform_label">
					爆口:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="baokou" name="baokou" type="text"   value="${emkQualityCheckPage.baokou }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">爆口</label>
			</td>
			<td class="value" width="10%">
				<input id="baokouminor" name="baokouminor" type="text"   value="${emkQualityCheckPage.baokouminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">爆口</label>
			</td>
			<td align="center" width="7%">X</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					驳线不良:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="bxbl" name="bxbl" type="text"   value="${emkQualityCheckPage.bxbl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">驳线不良</label>
			</td>
			<td class="value" width="10%">
				<input id="bxblminor" name="bxblminor" type="text"   value="${emkQualityCheckPage.bxblminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">驳线不良</label>
			</td>
			<td align="center" width="7%">m</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					条形码不能扫描:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="txmbnsm" name="txmbnsm" type="text"   value="${emkQualityCheckPage.txmbnsm }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">条形码不能扫描</label>
			</td>
			<td class="value" width="10%">
				<input id="txmbnsmminor" name="txmbnsmminor" type="text"   value="${emkQualityCheckPage.txmbnsmminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">条形码不能扫描</label>
			</td>
		</tr>


		<tr>
			<td class="value" colspan="4"></td>
			<td align="center" width="7%">Y</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					形状不良:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="xzbl" name="xzbl" type="text"   value="${emkQualityCheckPage.xzbl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">形状不良</label>
			</td>
			<td class="value" width="10%">
				<input id="xzblminor" name="xzblminor" type="text"   value="${emkQualityCheckPage.xzblminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">形状不良</label>
			</td>
			<td align="center" width="7%">n</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					吊牌不稳:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="dpbw" name="dpbw" type="text"   value="${emkQualityCheckPage.dpbw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">吊牌不稳</label>
			</td>
			<td class="value" width="10%">
				<input id="dpbwminor" name="dpbwminor" type="text"   value="${emkQualityCheckPage.lwl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">吊牌不稳</label>
			</td>
			</tr>
		<tr>
			<td class="value" width="10%" colspan="4"></td>
			<td align="center" width="7%">Z</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					漏物料:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="lwl" name="lwl" type="text"   value="${emkQualityCheckPage.lwl }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">漏物料</label>
			</td>
			<td class="value" width="10%">
				<input id="lwlminor" name="lwlminor" type="text"   value="${emkQualityCheckPage.lwlminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">漏物料</label>
			</td>
			<td align="center" width="7%">o</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					贴纸不稳:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="tzbw" name="tzbw" type="text"   value="${emkQualityCheckPage.tzbw }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">贴纸不稳</label>
			</td>
			<td class="value" width="10%">
				<input id="tzbwminor" name="tzbwminor" type="text"   value="${emkQualityCheckPage.tzbwminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">贴纸不稳</label>
			</td>
			</tr>
		<tr>
			<td class="value" width="10%" colspan="4"></td>
			<td align="center" width="7%">a</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					针孔:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="zhenkong" name="zhenkong" type="text"   value="${emkQualityCheckPage.zhenkong }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">针孔</label>
			</td>
			<td class="value" width="10%">
				<input id="zhenkongminor" name="zhenkongminor" type="text"   value="${emkQualityCheckPage.zhenkongminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">针孔</label>
			</td>
			<td align="center" width="7%">p</td>
			<td align="center" width="7%">
				<label class="Validform_label">
					包装资料不一:
				</label>
			</td>
			<td class="value" width="10%">
				<input id="bzzlby" name="bzzlby" type="text"   value="${emkQualityCheckPage.bzzlby }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装资料不一</label>
			</td>
			<td class="value" width="10%">
				<input id="bzzlbyminor" name="bzzlbyminor" type="text"   value="${emkQualityCheckPage.bzzlbyminor }" datatype="n"  style="width: 79%"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装资料不一</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					查验结果:
				</label>
			</td>
			<td class="value" colspan="5">
				<input name="cyjg" type="radio" datatype="*"  <c:if test="${emkQualityCheckPage.cyjg eq '0'}">checked="true"</c:if> value="0">
				PASS

			</td>
			<td class="value" colspan="5">
				<input name="cyjg" type="radio" datatype="*"  <c:if test="${emkQualityCheckPage.cyjg eq '1'}">checked="true"</c:if> value="1">
				REJECT
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">查验结果</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="10">
				<input id="cusName" name="cusName" readonly type="text" value="${emkQualityCheckPage.cusName }"  style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="cusNum" name="cusNum" readonly value="${emkQualityCheckPage.cusNum }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" colspan="4">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" value="${emkQualityCheckPage.gysCode }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkQualityCheckPage.gys }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkQualityCheckPage.businesseDeptName }"  readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkQualityCheckPage.businesseDeptId }"   type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					工厂名称:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="factoryName2" type="text" style="width: 150px" value="${emkQualityCheckPage.factoryName }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称+代码</label>
			</td>
			</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" colspan="4">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkQualityCheckPage.businesser }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkQualityCheckPage.businesserName }"   type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					工厂地址:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="address" name="address" value="${emkQualityCheckPage.address }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂地址</label>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" colspan="4">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkQualityCheckPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkQualityCheckPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="relationer" name="relationer" value="${emkQualityCheckPage.relationer }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>

		</tr>
		<tr>
			<td align="right" colspan="2">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" colspan="4">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkQualityCheckPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkQualityCheckPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" colspan="2">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="telphone" name="telphone" type="text" value="${emkQualityCheckPage.telphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>
		<%--
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="cusName" name="cusName" readonly type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>


		<tr>



			<td align="right">
				<label class="Validform_label">
					验货类型:
				</label>
			</td>
			<td class="value">
				<input id="checkType" name="checkType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货类型</label>
			</td>
		</tr>

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
					检查日期:
				</label>
			</td>
			<td class="value" >
				<input id="checkDate" name="checkDate" readonly value="${emkCheckPage.checkDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">检查日期</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>

		</tr>--%>
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