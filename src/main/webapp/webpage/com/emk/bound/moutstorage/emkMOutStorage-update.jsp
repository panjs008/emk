<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>出库申请表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMOutStorageController.do?doUpdate" >
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务员:
							</label>
						</td>
						<td class="value">
						    <input id="businesser" name="businesser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.businesser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesserName" name="businesserName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.businesserName}'/>
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
						    <input id="tracer" name="tracer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.tracer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="tracerName" name="tracerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.tracerName}'/>
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
						    <input id="businesseDeptName" name="businesseDeptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.businesseDeptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务部门ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesseDeptId" name="businesseDeptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.businesseDeptId}'/>
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
						    <input id="developer" name="developer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.developer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="developerName" name="developerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.developerName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出货日期:
							</label>
						</td>
						<td class="value">
						    <input id="outDate" name="outDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.outDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出货日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								申请人:
							</label>
						</td>
						<td class="value">
						    <input id="appler" name="appler" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.appler}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								申请人ID:
							</label>
						</td>
						<td class="value">
						    <input id="applerId" name="applerId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.applerId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请人ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								领料人:
							</label>
						</td>
						<td class="value">
						    <input id="ller" name="ller" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.ller}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">领料人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								领料ID:
							</label>
						</td>
						<td class="value">
						    <input id="llerId" name="llerId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.llerId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">领料ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								款号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.sampleNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								采购合同号:
							</label>
						</td>
						<td class="value">
						    <input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.htNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购合同号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								预采购合同号:
							</label>
						</td>
						<td class="value">
						    <input id="yhtNum" name="yhtNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.yhtNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预采购合同号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								供应商:
							</label>
						</td>
						<td class="value">
						    <input id="gys" name="gys" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.gys}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								供应商代码:
							</label>
						</td>
						<td class="value">
						    <input id="gysCode" name="gysCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.gysCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								供应商:
							</label>
						</td>
						<td class="value">
						    <input id="gys" name="gys" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.gys}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								供应商代码:
							</label>
						</td>
						<td class="value">
						    <input id="gysCode" name="gysCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.gysCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
						    <input id="type" name="type" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.type}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								主键:
							</label>
						</td>
						<td class="value">
						    <input id="id" name="id" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.id}'/>
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
						    <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.createName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.createBy}'/>
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
						    <input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.createDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.sysOrgCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						    <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.state}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.orderNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户编号:
							</label>
						</td>
						<td class="value">
						    <input id="cusNum" name="cusNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.cusNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户名称:
							</label>
						</td>
						<td class="value">
						    <input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.cusName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出库日期:
							</label>
						</td>
						<td class="value">
						    <input id="kdDate" name="kdDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMOutStoragePage.kdDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出库日期</label>
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
  <script src = "webpage/com/emk/bound/moutstorage/emkMOutStorage.js"></script>		
