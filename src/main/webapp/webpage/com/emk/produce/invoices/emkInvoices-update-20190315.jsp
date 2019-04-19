<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>发票</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码

		$(document).ready(function(){
			$("#detailId").load("emkInvoicesController.do?orderMxList&proOrderId=${emkInvoicesPage.id }");

		});



	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkInvoicesController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkInvoicesPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					发票编号:
				</label>
			</td>
			<td class="value">
				<input id="fpbh" name="fpbh" type="text" value="${emkInvoicesPage.fpbh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo"  value="${emkInvoicesPage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkInvoicesPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					订舱通知单号:
				</label>
			</td>
			<td class="value">
				<input id="cargoNo" name="cargoNo" type="text" value="${emkInvoicesPage.cargoNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱通知单号</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					离厂放行条号:
				</label>
			</td>
			<td class="value">
				<input id="levealFactoryNo" name="levealFactoryNo" type="text" value="${emkInvoicesPage.levealFactoryNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂放行条号</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value">
				<input id="cwyer" name="cwyer" type="text" value="${emkInvoicesPage.cwyer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkInvoicesPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkInvoicesPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
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
				<input id="businesseDeptName" name="businesseDeptName" value="${emkInvoicesPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkInvoicesPage.businesseDeptId }" type="hidden"  />
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
				<input id="businesser" name="businesser" readonly value="${emkInvoicesPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkInvoicesPage.businesserName }" type="hidden"  />
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkInvoicesPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkInvoicesPage.tracerName }" />
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
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkInvoicesPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkInvoicesPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<input id="bz" name="bz" type="text" value="${emkInvoicesPage.bz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					总金额:
				</label>
			</td>
			<td class="value">
				<input id="sumMoney" name="sumMoney" type="text" value="${emkInvoicesPage.sumMoney }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总金额</label>
			</td>

		</tr>


		<tr>

			<td align="right">
				<label class="Validform_label">
					发票日期:
				</label>
			</td>
			<td class="value">
				<input id="fpDate" name="fpDate" readonly value="${emkInvoicesPage.fpDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					法定代表签名:
				</label>
			</td>
			<td class="value">
				<input id="signer" name="signer" type="text" value="${emkInvoicesPage.signer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表签名</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					签名日期:
				</label>
			</td>
			<td class="value">
				<input id="signDate" name="signDate" type="text" value="${emkInvoicesPage.signDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签名日期</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkInvoicesPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					收款方式:
				</label>
			</td>
			<td class="value">
				<input id="skfs" name="skfs" type="text" value="${emkInvoicesPage.skfs }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收款方式</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					提单号:
				</label>
			</td>
			<td class="value">
				<input id="tdNum" name="tdNum" type="text" value="${emkInvoicesPage.tdNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单号</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					提单状态:
				</label>
			</td>
			<td class="value">
				<input id="tdzt" name="tdzt" type="text" value="${emkInvoicesPage.tdzt }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单状态</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					生产方名称:
				</label>
			</td>
			<td class="value">
				<input id="scfmc" name="scfmc" type="text" value="${emkInvoicesPage.scfmc }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产方名称</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" type="text" value="${emkInvoicesPage.address }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="signTelphone" name="signTelphone" type="text" value="${emkInvoicesPage.signTelphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
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
			<td align="left"><input id="signPrice00" nullmsg="请输入单价！"  errormsg="请输入单价" name="orderMxList[#index#].signPrice" maxlength="100" type="text" value=""
									style="width: 80%;"></td>
		</tr>
		</tbody>

	</table>
</t:formvalid>
</body>
