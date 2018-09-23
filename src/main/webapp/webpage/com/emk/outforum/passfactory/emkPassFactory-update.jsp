<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>离厂放行单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPassFactoryController.do?doUpdate" >
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务员:
							</label>
						</td>
						<td class="value">
						    <input id="businesser" name="businesser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.businesser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesserName" name="businesserName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.businesserName}'/>
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
						    <input id="tracer" name="tracer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.tracer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="tracerName" name="tracerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.tracerName}'/>
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
						    <input id="businesseDeptName" name="businesseDeptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.businesseDeptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务部门ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesseDeptId" name="businesseDeptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.businesseDeptId}'/>
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
						    <input id="developer" name="developer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.developer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="developerName" name="developerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.developerName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工艺种类:
							</label>
						</td>
						<td class="value">
						    <input id="gyzl" name="gyzl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.gyzl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工艺种类</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								款式大类:
							</label>
						</td>
						<td class="value">
						    <input id="proType" name="proType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.proType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款式大类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								款式大类:
							</label>
						</td>
						<td class="value">
						    <input id="proTypeName" name="proTypeName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.proTypeName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款式大类</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								款号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.sampleNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNoDesc" name="sampleNoDesc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.sampleNoDesc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								主键:
							</label>
						</td>
						<td class="value">
						    <input id="id" name="id" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${emkPassFactoryPage.id}'/>
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
						    <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.createName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.createBy}'/>
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
						    <input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.createDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.sysOrgCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出货通知单号:
							</label>
						</td>
						<td class="value">
						    <input id="outFourmNo" name="outFourmNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.outFourmNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货通知单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订舱通知单号:
							</label>
						</td>
						<td class="value">
						    <input id="cargoNo" name="cargoNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.cargoNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订舱通知单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								离厂放行条号:
							</label>
						</td>
						<td class="value">
						    <input id="levealFactoryNo" name="levealFactoryNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.levealFactoryNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂放行条号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.orderNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								船务员:
							</label>
						</td>
						<td class="value">
						    <input id="cwer" name="cwer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.cwer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">船务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								提交订舱日期:
							</label>
						</td>
						<td class="value">
						    <input id="cargoDate" name="cargoDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.cargoDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提交订舱日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								离厂日期:
							</label>
						</td>
						<td class="value">
						    <input id="levealDate" name="levealDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.levealDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								总箱数:
							</label>
						</td>
						<td class="value">
						    <input id="sumBox" name="sumBox" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkPassFactoryPage.sumBox}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总箱数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户代码:
							</label>
						</td>
						<td class="value">
						    <input id="cusNum" name="cusNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.cusNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户名称:
							</label>
						</td>
						<td class="value">
						    <input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.cusName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								运输单位名称:
							</label>
						</td>
						<td class="value">
						    <input id="ysdwName" name="ysdwName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.ysdwName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输单位名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								运输单位代码:
							</label>
						</td>
						<td class="value">
						    <input id="ysdwCode" name="ysdwCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.ysdwCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运输单位代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								司机姓名:
							</label>
						</td>
						<td class="value">
						    <input id="driver" name="driver" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.driver}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">司机姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								司机电话:
							</label>
						</td>
						<td class="value">
						    <input id="drverTel" name="drverTel" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.drverTel}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">司机电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								车牌:
							</label>
						</td>
						<td class="value">
						    <input id="driverNum" name="driverNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.driverNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车牌</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								离厂通知单状态:
							</label>
						</td>
						<td class="value">
						    <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPassFactoryPage.state}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">离厂通知单状态</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/outforum/passfactory/emkPassFactory.js"></script>		
