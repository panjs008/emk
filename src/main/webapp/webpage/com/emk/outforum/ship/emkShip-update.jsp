<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客用船务文件</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkShipController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkShipPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务员:
							</label>
						</td>
						<td class="value">
						    <input id="businesser" name="businesser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.businesser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesserName" name="businesserName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.businesserName}'/>
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
						    <input id="tracer" name="tracer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.tracer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="tracerName" name="tracerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.tracerName}'/>
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
						    <input id="businesseDeptName" name="businesseDeptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.businesseDeptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务部门ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesseDeptId" name="businesseDeptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.businesseDeptId}'/>
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
						    <input id="developer" name="developer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.developer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="developerName" name="developerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.developerName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								船务员:
							</label>
						</td>
						<td class="value">
						    <input id="cwer" name="cwer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.cwer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">船务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
						    <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.price}'/>
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
						    <input id="sumBoxTotal" name="sumBoxTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.sumBoxTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总箱数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								总体积:
							</label>
						</td>
						<td class="value">
						    <input id="sumBoxVolume" name="sumBoxVolume" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.sumBoxVolume}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总体积</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总净重:
							</label>
						</td>
						<td class="value">
						    <input id="sumBoxJz" name="sumBoxJz" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.sumBoxJz}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总净重</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								总毛重:
							</label>
						</td>
						<td class="value">
						    <input id="sumBoxMao" name="sumBoxMao" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.sumBoxMao}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总毛重</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订舱通知单号:
							</label>
						</td>
						<td class="value">
						    <input id="cargoNo" name="cargoNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.cargoNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订舱通知单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出货通知单号:
							</label>
						</td>
						<td class="value">
						    <input id="outForumNo" name="outForumNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.outForumNo}'/>
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
						    <input id="levealFactoryNo" name="levealFactoryNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.levealFactoryNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂放行条号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								供应商:
							</label>
						</td>
						<td class="value">
						    <input id="gys" name="gys" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.gys}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								供应商:
							</label>
						</td>
						<td class="value">
						    <input id="gysCode" name="gysCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.gysCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户代码:
							</label>
						</td>
						<td class="value">
						    <input id="cusNum" name="cusNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.cusNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户名称:
							</label>
						</td>
						<td class="value">
						    <input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.cusName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								离厂日期:
							</label>
						</td>
						<td class="value">
						    <input id="levealDate" name="levealDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.levealDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.orderNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产合同号:
							</label>
						</td>
						<td class="value">
						    <input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.htNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产合同号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						    <input id="shipDesc" name="shipDesc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.shipDesc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkShipPage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提单号:
							</label>
						</td>
						<td class="value">
						    <input id="tdNum" name="tdNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.tdNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								提单状态:
							</label>
						</td>
						<td class="value">
						    <input id="tdState" name="tdState" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.tdState}'/>
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
						    <input id="fpNum" name="fpNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.fpNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发票编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								发票状态:
							</label>
						</td>
						<td class="value">
						    <input id="fpState" name="fpState" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.fpState}'/>
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
						    <input id="boxNum" name="boxNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.boxNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">装箱单编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								装箱单状态:
							</label>
						</td>
						<td class="value">
						    <input id="boxState" name="boxState" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.boxState}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">装箱单状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								到港时间:
							</label>
						</td>
						<td class="value">
						    <input id="arrvieDate" name="arrvieDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkShipPage.arrvieDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">到港时间</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/outforum/ship/emkShip.js"></script>		
