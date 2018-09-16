<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>入库表明细表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkInStorageDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkInStorageDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库单ID:
							</label>
						</td>
						<td class="value">
						    <input id="inStorageId" name="inStorageId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.inStorageId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库单ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								商品ID:
							</label>
						</td>
						<td class="value">
						    <input id="proId" name="proId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.proId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物料编号:
							</label>
						</td>
						<td class="value">
						    <input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.proNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								规格:
							</label>
						</td>
						<td class="value">
						    <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.brand}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="signTotal" name="signTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.signTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								单位:
							</label>
						</td>
						<td class="value">
						    <input id="signUnit" name="signUnit" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.signUnit}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
						    <input id="signPrice" name="signPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.signPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单价</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物料名称:
							</label>
						</td>
						<td class="value">
						    <input id="proName" name="proName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.proName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								仓库ID:
							</label>
						</td>
						<td class="value">
						    <input id="storageSetId" name="storageSetId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.storageSetId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								仓库名称:
							</label>
						</td>
						<td class="value">
						    <input id="storageName" name="storageName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.storageName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								库位ID:
							</label>
						</td>
						<td class="value">
						    <input id="positionId" name="positionId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.positionId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">库位ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								库位名称:
							</label>
						</td>
						<td class="value">
						    <input id="positionName" name="positionName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.positionName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">库位名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								款号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.sampleNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								款号名称:
							</label>
						</td>
						<td class="value">
						    <input id="sampleName" name="sampleName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkInStorageDetailPage.sampleName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号名称</label>
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
  <script src = "webpage/com/emk/storage/instoragedetail/emkInStorageDetail.js"></script>		
