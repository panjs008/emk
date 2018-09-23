<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>运费费</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkShipExpensesController.do?doAdd" >
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							业务员:
						</label>
					</td>
					<td class="value">
					     	 <input id="businesser" name="businesser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							业务员ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="businesserName" name="businesserName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							业务跟单员:
						</label>
					</td>
					<td class="value">
					     	 <input id="tracer" name="tracer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							业务跟单员ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="tracerName" name="tracerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							业务部门:
						</label>
					</td>
					<td class="value">
					     	 <input id="businesseDeptName" name="businesseDeptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							业务部门ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="businesseDeptId" name="businesseDeptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产跟单员:
						</label>
					</td>
					<td class="value">
					     	 <input id="developer" name="developer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							生产跟单员ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="developerName" name="developerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="sampleNoDesc" name="sampleNoDesc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							总箱数:
						</label>
					</td>
					<td class="value">
					     	 <input id="sumBoxTotal" name="sumBoxTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总箱数</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总体积:
						</label>
					</td>
					<td class="value">
					     	 <input id="sumBoxVolume" name="sumBoxVolume" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总体积</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							总净重:
						</label>
					</td>
					<td class="value">
					     	 <input id="sumBoxJz" name="sumBoxJz" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
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
					     	 <input id="sumBoxMao" name="sumBoxMao" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总毛重</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							主键:
						</label>
					</td>
					<td class="value">
					     	 <input id="id" name="id" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主键</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							创建人登录名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人登录名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							所属部门:
						</label>
					</td>
					<td class="value">
					     	 <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							订舱通知单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="cargoNo" name="cargoNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订舱通知单号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							出货通知单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="outForumNo" name="outForumNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货通知单号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							离厂放行条号:
						</label>
					</td>
					<td class="value">
					     	 <input id="levealFactoryNo" name="levealFactoryNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂放行条号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							船务员:
						</label>
					</td>
					<td class="value">
					     	 <input id="cwyer" name="cwyer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">船务员</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商:
						</label>
					</td>
					<td class="value">
					     	 <input id="gysCode" name="gysCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							供应商:
						</label>
					</td>
					<td class="value">
					     	 <input id="gys" name="gys" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							离厂日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="levealDate" name="levealDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							订单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产合同号:
						</label>
					</td>
					<td class="value">
					     	 <input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产合同号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							运输企业名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysqyName" name="ysqyName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输企业名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							运输企业代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysqyCode" name="ysqyCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输企业代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							运输费用金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysCost" name="ysCost" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输费用金额</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							运输费用总金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysSumMoney" name="ysSumMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输费用总金额</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							运输费用发票号:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysFp" name="ysFp" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输费用发票号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							运输费用付款状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysPayState" name="ysPayState" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输费用付款状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							收款单位:
						</label>
					</td>
					<td class="value">
					     	 <input id="skdw" name="skdw" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款单位</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankAccount" name="bankAccount" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							开户行:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankName" name="bankName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户行</label>
						</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/outforum/shipexpenses/emkShipExpenses.js"></script>		
