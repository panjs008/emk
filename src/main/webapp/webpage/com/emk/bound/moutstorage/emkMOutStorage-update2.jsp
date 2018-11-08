<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>原料采购合同表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){
			$("#detailId").load("emkMOutStorageController.do?emkMOutStorageDetailList&selectType=0&inStorageId=${emkMOutStoragePage.id }");
		});




	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMOutStorageController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkMOutStoragePage.id }"/>
	<c:set var="selectType" value="0"  scope="session"></c:set>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					出库日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkMOutStoragePage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出库日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					申请人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="realName" name="realName" value="${emkMOutStoragePage.appler }"  type="text" readonly style="width: 150px" class="inputxt" >
				<input name="userName"   type="hidden" value="${emkMOutStoragePage.applerId }"   id="userName" type="text"  />

			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					出库类型:
				</label>
			</td>
			<td class="value" >
				<select name="type" id="type" datatype="*" onchange="changeType();">
					<option value="0">原料面料</option>
					<option value="1">缝制辅料</option>
					<option value="2">包装辅料</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出库类型</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					出库人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="ller" name="ller" type="text" value="${emkMOutStoragePage.ller }"  readonly style="width: 150px" class="inputxt" >
				<input name="llerId"   type="hidden" value="${emkMOutStoragePage.llerId }"  id="llerId" type="text"  />

			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					采购合同号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum"  value="${emkMOutStoragePage.htNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">采购合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					预采购合同号:
				</label>
			</td>
			<td class="value">
				<input id="yhtNum" name="yhtNum"  value="${emkMOutStoragePage.yhtNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预采购合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo"  value="${emkMOutStoragePage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
		<tr>

			<td align="right" >
				<label class="Validform_label">
					客户编号:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMOutStoragePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMOutStoragePage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMOutStoragePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMOutStoragePage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<input id="businesser" name="businesser" readonly value="${emkMOutStoragePage.businesser }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMOutStoragePage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<input id="tracer" name="tracer" readonly type="text" value="${emkMOutStoragePage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMOutStoragePage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>


		<tr>

			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<input id="developer" name="developer" readonly value="${emkMOutStoragePage.developer }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkMOutStoragePage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" >
				<input id="gysCode" name="gysCode" value="${emkMOutStoragePage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="text"value="${emkMOutStoragePage.gys }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMOutStoragePage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					出货日期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="outDate" name="outDate" readonly value="${emkMOutStoragePage.outDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货日期</label>
			</td>
		</tr>

	</table>
	<div id="detailId" style="width: auto; height: 200px;" ><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
	</div>
	<!-- 添加 产品明细 模版-->
	<table style="width:100%;display: none" cellpadding="0" cellspacing="2" border="0">
		<tbody id="add_jeecgOrderProduct_table_template">
		<tr>
			<td align="center"><input style="width: 40px;" type="checkbox" name="ck"/>
			</td>
			<td align="left"><input nullmsg="请输入物料编号！" id="proNum00"  errormsg="请输入物料编号" name="rkglMxList[#index#].proNum" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left">
				<input nullmsg="请输入物料名称！" id="proName00"  errormsg="请输入物料名称" name="rkglMxList[#index#].proName" maxlength="100" type="text" value=""
					   style="width: 95%;">
			</td>
			<td align="left"><input id="brand00" nullmsg="请输入规格！"  errormsg="请输入规格" name="rkglMxList[#index#].brand" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input id="htTotal00" nullmsg="请输入数量！"  errormsg="请输入数量" name="rkglMxList[#index#].htTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input id="totall00" nullmsg="请输入采购数量！"  errormsg="请输入采购数量" name="rkglMxList[#index#].total" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input nullmsg="请输入出库数量！" id="inTotal00" errormsg="请输入出库数量" name="rkglMxList[#index#].inTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input nullmsg="请输入实际出库数量！" id="actualTotal00" errormsg="请输入实际出库数量" name="rkglMxList[#index#].actualTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>

		</tr>
		</tbody>

	</table>
</t:formvalid>
</body>