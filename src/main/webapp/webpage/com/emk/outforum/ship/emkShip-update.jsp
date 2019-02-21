<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客用船务文件</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkShipController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkShipPage.id }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkShipPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkShipPage.cusName }" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right"  >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkShipPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkShipPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value"  >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkShipPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkShipPage.businesserName }" type="hidden"  />
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkShipPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkShipPage.tracerName }" />
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
				<input id="developer" name="developer" readonly value="${emkShipPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkShipPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo" type="text" value="${emkShipPage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum" type="text" value="${emkShipPage.htNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					出货通知单号:
				</label>
			</td>
			<td class="value">
				<input id="outForumNo" name="outForumNo" type="text" value="${emkShipPage.outForumNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订舱通知单号:
				</label>
			</td>
			<td class="value">
				<input id="cargoNo" name="cargoNo" type="text" value="${emkShipPage.cargoNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					离厂放行条号:
				</label>
			</td>
			<td class="value">
				<input id="levealFactoryNo" name="levealFactoryNo" type="text" value="${emkShipPage.levealFactoryNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂放行条号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value">
				<input id="cwer" name="cwer" type="text" value="${emkShipPage.cwer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					离厂日期:
				</label>
			</td>
			<td class="value">
				<input id="levealDate" name="levealDate" readonly value="${emkShipPage.levealDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					到港时间:
				</label>
			</td>
			<td class="value">
				<input id="arrvieDate" name="arrvieDate" readonly value="${emkShipPage.arrvieDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">到港时间</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="shipDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="shipDesc">${emkShipPage.shipDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
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
				<input id="gysCode" name="gysCode" value="${emkShipPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys"  value="${emkShipPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" value="${emkShipPage.total }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price" type="text" value="${emkShipPage.price }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总箱数:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxTotal" name="sumBoxTotal" type="text" value="${emkShipPage.sumBoxTotal }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总箱数</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总体积:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxVolume" name="sumBoxVolume" type="text" value="${emkShipPage.sumBoxVolume }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总体积</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总净重:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxJz" name="sumBoxJz" type="text" value="${emkShipPage.sumBoxJz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总净重</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					总毛重:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxMao" name="sumBoxMao" type="text" value="${emkShipPage.sumBoxMao }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总毛重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					提单号:
				</label>
			</td>
			<td class="value">
				<input id="tdNum" name="tdNum" type="text" value="${emkShipPage.tdNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					提单状态:
				</label>
			</td>
			<td class="value">
				<input id="tdState" name="tdState" type="text" value="${emkShipPage.tdState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单状态</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					发票编号:
				</label>
			</td>
			<td class="value">
				<input id="fpNum" name="fpNum" type="text" value="${emkShipPage.fpNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					发票状态:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="fpState" name="fpState" type="text" value="${emkShipPage.fpState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票状态</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					装箱单编号:
				</label>
			</td>
			<td class="value">
				<input id="boxNum" name="boxNum" type="text" value="${emkShipPage.boxNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">装箱单编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					装箱单状态:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="boxState" name="boxState" type="text" value="${emkShipPage.boxState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">装箱单状态</label>
			</td>
		</tr>
	</table>

</t:formvalid>
</body>
