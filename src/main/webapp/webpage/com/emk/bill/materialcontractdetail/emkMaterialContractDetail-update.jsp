<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>采购单明细</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialContractDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkMaterialContractDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								采购合同ID:
							</label>
						</td>
						<td class="value">
						    <input id="contractId" name="contractId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMaterialContractDetailPage.contractId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购合同ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								品名:
							</label>
						</td>
						<td class="value">
						    <input id="proZnName" name="proZnName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMaterialContractDetailPage.proZnName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">品名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物料编号:
							</label>
						</td>
						<td class="value">
						    <input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMaterialContractDetailPage.proNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								规格:
							</label>
						</td>
						<td class="value">
						    <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMaterialContractDetailPage.brand}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单位:
							</label>
						</td>
						<td class="value">
						    <input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMaterialContractDetailPage.unit}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								物料类型:
							</label>
						</td>
						<td class="value">
						    <input id="type" name="type" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkMaterialContractDetailPage.type}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
						    <input id="signPrice" name="signPrice" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkMaterialContractDetailPage.signPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单价</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								采购数量:
							</label>
						</td>
						<td class="value">
						    <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkMaterialContractDetailPage.total}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库数量:
							</label>
						</td>
						<td class="value">
						    <input id="inTotal" name="inTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkMaterialContractDetailPage.inTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出库数量:
							</label>
						</td>
						<td class="value">
						    <input id="outTotal" name="outTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkMaterialContractDetailPage.outTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出库数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								剩余数量:
							</label>
						</td>
						<td class="value">
						    <input id="leavelTotal" name="leavelTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkMaterialContractDetailPage.leavelTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">剩余数量</label>
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
  <script src = "webpage/com/emk/bill/materialcontractdetail/emkMaterialContractDetail.js"></script>		
