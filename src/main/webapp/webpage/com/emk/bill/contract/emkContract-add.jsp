<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>购销合同</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <%@include file="/context/header.jsp"%>

	 <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkContractController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkContractPage.id }"/>
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
							合同编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同编号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							甲方:
						</label>
					</td>
					<td class="value">
					     	 <input id="partyA" name="partyA" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">甲方</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							甲方ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="partyAId" name="partyAId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">甲方ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							乙方:
						</label>
					</td>
					<td class="value">
					     	 <input id="partyB" name="partyB" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">乙方</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							乙方ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="partyBId" name="partyBId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">乙方ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							FOB:
						</label>
					</td>
					<td class="value">
					     	 <input id="fob" name="fob" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">FOB</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							交货地点:
						</label>
					</td>
					<td class="value">
					     	 <input id="place" name="place" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交货地点</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							原产地:
						</label>
					</td>
					<td class="value">
					     	 <input id="ycd" name="ycd" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">原产地</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							付款方式:
						</label>
					</td>
					<td class="value">
					     	 <input id="payType" name="payType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款方式</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							授权代表:
						</label>
					</td>
					<td class="value">
					     	 <input id="sqdb" name="sqdb" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">授权代表</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							签定日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="signDate" name="signDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签定日期</label>
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
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/contract/emkContract.js"></script>		
