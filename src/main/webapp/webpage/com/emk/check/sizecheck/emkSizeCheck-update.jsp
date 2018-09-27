<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>尺寸检查表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSizeCheckController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkSizeCheckPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								尺寸检查编号:
							</label>
						</td>
						<td class="value">
						    <input id="sizeCheckNum" name="sizeCheckNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.sizeCheckNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺寸检查编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员:
							</label>
						</td>
						<td class="value">
						    <input id="businesser" name="businesser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.businesser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务员ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesserName" name="businesserName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.businesserName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务跟单员:
							</label>
						</td>
						<td class="value">
						    <input id="tracer" name="tracer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.tracer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="tracerName" name="tracerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.tracerName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务部门:
							</label>
						</td>
						<td class="value">
						    <input id="businesseDeptName" name="businesseDeptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.businesseDeptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务部门ID:
							</label>
						</td>
						<td class="value">
						    <input id="businesseDeptId" name="businesseDeptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.businesseDeptId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产跟单员ID:
							</label>
						</td>
						<td class="value">
						    <input id="developerName" name="developerName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.developerName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								生产跟单员:
							</label>
						</td>
						<td class="value">
						    <input id="developer" name="developer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.developer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								供应商名称:
							</label>
						</td>
						<td class="value">
						    <input id="gys" name="gys" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.gys}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								供应商代码:
							</label>
						</td>
						<td class="value">
						    <input id="gysCode" name="gysCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.gysCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户代码:
							</label>
						</td>
						<td class="value">
						    <input id="cusNum" name="cusNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.cusNum}'/>
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
						    <input id="cusName" name="cusName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.cusName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.orderNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合同号:
							</label>
						</td>
						<td class="value">
						    <input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.htNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								工厂名称:
							</label>
						</td>
						<td class="value">
						    <input id="factoryName" name="factoryName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.factoryName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工厂名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工厂地址:
							</label>
						</td>
						<td class="value">
						    <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.address}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工厂地址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								联系人:
							</label>
						</td>
						<td class="value">
						    <input id="relationer" name="relationer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.relationer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
						    <input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.telphone}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								验货类型:
							</label>
						</td>
						<td class="value">
						    <input id="checkType" name="checkType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.checkType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">验货类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								款号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.sampleNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						    <input id="sampleDesc" name="sampleDesc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.sampleDesc}'/>
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
						    <input id="color" name="color" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.color}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颜色</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								尺码范围:
							</label>
						</td>
						<td class="value">
						    <input id="size" name="size" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.size}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺码范围</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								总箱数:
							</label>
						</td>
						<td class="value">
						    <input id="boxTotal" name="boxTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.boxTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总箱数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								版次:
							</label>
						</td>
						<td class="value">
						    <input id="vesion" name="vesion" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.vesion}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版次</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								抽检数量:
							</label>
						</td>
						<td class="value">
						    <input id="checkTotal" name="checkTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.checkTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抽检数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								检查日期:
							</label>
						</td>
						<td class="value">
						    <input id="checkDate" name="checkDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.checkDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">检查日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								测量部位A:
							</label>
						</td>
						<td class="value">
						    <input id="clbwa" name="clbwa" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.clbwa}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">测量部位A</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								尺码 :
							</label>
						</td>
						<td class="value">
						    <input id="chima" name="chima" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.chima}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺码 </label>
						</td>
						<td align="right">
							<label class="Validform_label">
								测量部位A标准尺:
							</label>
						</td>
						<td class="value">
						    <input id="clbwabzc" name="clbwabzc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.clbwabzc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">测量部位A标准尺</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								样衣1尺寸:
							</label>
						</td>
						<td class="value">
						    <input id="yycc" name="yycc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.yycc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样衣1尺寸</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								抽检结果:
							</label>
						</td>
						<td class="value">
						    <input id="checkResult" name="checkResult" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.checkResult}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抽检结果</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remart" name="remart" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSizeCheckPage.remart}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
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
  <script src = "webpage/com/emk/check/sizecheck/emkSizeCheck.js"></script>		
