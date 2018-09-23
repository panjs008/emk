<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>提单货代费用</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkTdhdCostController.do?doUpdate" >
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务员:
							</label>
						</td>
						<td class="value">
						    <input id="businesser" name="businesser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.businesser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesserName" name="businesserName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.businesserName}'/>
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
						    <input id="tracer" name="tracer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.tracer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="tracerName" name="tracerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.tracerName}'/>
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
						    <input id="businesseDeptName" name="businesseDeptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.businesseDeptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务部门ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesseDeptId" name="businesseDeptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.businesseDeptId}'/>
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
						    <input id="developer" name="developer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.developer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="developerName" name="developerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.developerName}'/>
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
						    <input id="sampleNoDesc" name="sampleNoDesc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.sampleNoDesc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
						    <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkTdhdCostPage.price}'/>
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
						    <input id="sumBoxTotal" name="sumBoxTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkTdhdCostPage.sumBoxTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总箱数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								总体积:
							</label>
						</td>
						<td class="value">
						    <input id="sumBoxVolume" name="sumBoxVolume" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkTdhdCostPage.sumBoxVolume}'/>
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
						    <input id="sumBoxJz" name="sumBoxJz" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkTdhdCostPage.sumBoxJz}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总净重</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								总毛重:
							</label>
						</td>
						<td class="value">
						    <input id="sumBoxMao" name="sumBoxMao" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkTdhdCostPage.sumBoxMao}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总毛重</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								主键:
							</label>
						</td>
						<td class="value">
						    <input id="id" name="id" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${emkTdhdCostPage.id}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主键</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建人名称:
							</label>
						</td>
						<td class="value">
						    <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.createName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.createBy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人登录名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建日期:
							</label>
						</td>
						<td class="value">
						    <input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.createDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.sysOrgCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="cwyer" name="cwyer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.cwyer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订舱通知单号:
							</label>
						</td>
						<td class="value">
						    <input id="cargoNo" name="cargoNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.cargoNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订舱通知单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								离厂放行条号:
							</label>
						</td>
						<td class="value">
						    <input id="levealFactoryNo" name="levealFactoryNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.levealFactoryNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂放行条号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户代码:
							</label>
						</td>
						<td class="value">
						    <input id="cusNum" name="cusNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.cusNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户名称:
							</label>
						</td>
						<td class="value">
						    <input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.cusName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								供应商:
							</label>
						</td>
						<td class="value">
						    <input id="gys" name="gys" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.gys}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								供应商代码:
							</label>
						</td>
						<td class="value">
						    <input id="gysCode" name="gysCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.gysCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								离厂日期:
							</label>
						</td>
						<td class="value">
						    <input id="levealDate" name="levealDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.levealDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出货通知单号:
							</label>
						</td>
						<td class="value">
						    <input id="outForumNo" name="outForumNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.outForumNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货通知单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkTdhdCostPage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产合同号:
							</label>
						</td>
						<td class="value">
						    <input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.htNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产合同号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提单号:
							</label>
						</td>
						<td class="value">
						    <input id="tdNum" name="tdNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.tdNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								提单状态:
							</label>
						</td>
						<td class="value">
						    <input id="tdState" name="tdState" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.tdState}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提单状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								货代费用发票号:
							</label>
						</td>
						<td class="value">
						    <input id="hdfyFp" name="hdfyFp" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.hdfyFp}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">货代费用发票号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								费用金额:
							</label>
						</td>
						<td class="value">
						    <input id="cost" name="cost" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.cost}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								费用付款状态:
							</label>
						</td>
						<td class="value">
						    <input id="payState" name="payState" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.payState}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用付款状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								货代名称:
							</label>
						</td>
						<td class="value">
						    <input id="hdName" name="hdName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.hdName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">货代名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								货代代码:
							</label>
						</td>
						<td class="value">
						    <input id="hdCode" name="hdCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.hdCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">货代代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								报关单状态:
							</label>
						</td>
						<td class="value">
						    <input id="bgzt" name="bgzt" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.bgzt}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报关单状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								海关放行条状态:
							</label>
						</td>
						<td class="value">
						    <input id="hgfxzt" name="hgfxzt" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkTdhdCostPage.hgfxzt}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">海关放行条状态</label>
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
  <script src = "webpage/com/emk/outforum/tdhdcost/emkTdhdCost.js"></script>		
