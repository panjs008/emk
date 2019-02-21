<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>测试费用付款申请</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#testContentUrl").val(d.attributes.url);
			$("#testContent").val(d.attributes.name);
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkTestCostController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkTestCostPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					费用付款申请单号:
				</label>
			</td>
			<td class="value" >
				<input id="costNo" name="costNo"  type="text" style="width: 150px" value="${emkTestCostPage.costNo }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">费用付款申请单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					测试申请单号:
				</label>
			</td>
			<td class="value" >
				<input id="testNo" name="testNo"  type="text" style="width: 150px"  value="${emkTestCostPage.testNo }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试申请单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceNum" name="produceNum"  type="text" style="width: 150px" value="${emkTestCostPage.produceNum }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo"  type="text" style="width: 150px"  value="${emkTestCostPage.orderNo }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" readonly type="text"  value="${emkTestCostPage.businesseDeptName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  type="hidden"  value="${emkTestCostPage.businesseDeptId }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px"  value="${emkTestCostPage.businesser }" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  value="${emkTestCostPage.businesserName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.cusNum }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.cusName }"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" style="width: 150px"  value="${emkTestCostPage.tracer }" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkTestCostPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" style="width: 150px"  value="${emkTestCostPage.developer }" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  value="${emkTestCostPage.developerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					测试机构名称:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="csjgdm" field="csjgdm" typeGroupCode="orgCode" datatype="*" defaultVal="${emkTestCostPage.csjgdm }" hasLabel="false" title="机构名称"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试机构名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.sampleNo }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="total" name="total" type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.total }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate"  value="${emkTestCostPage.kdDate }"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试有效期:
				</label>
			</td>
			<td class="value">
				<input id="limitDate" name="limitDate" readonly value="${emkTestCostPage.limitDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试有效期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkTestCostPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试种类:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="testType" field="testType" typeGroupCode="testtype" datatype="*" defaultVal="${emkTestCostPage.testType }" hasLabel="false" title="测试种类"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试种类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试结果:
				</label>
			</td>
			<td class="value">
				<input id="testResult" name="testResult" type="text" style="width: 150px"  value="${emkTestCostPage.testResult }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试结果</label>
			</td>

		</tr>
		<tr id="dgrImageDiv">
			<td align="right">
				<label class="Validform_label">
					测试内容:
				</label>
			</td>
			<td class="value">
				<input id="testContent" name="testContent" type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.testContent }"  ignore="ignore" />
				<input id="testContentUrl" name="testContentUrl" type="hidden"  value="${emkTestCostPage.testContentUrl }" />
				<c:if test="${emkTestCostPage.testContentUrl ne ''}">[<a href="emkTestController.do?dowaLoadFile&id=${emkTestCostPage.id}&testContentUrl=${emkTestCostPage.testContentUrl }">下载</a>]</c:if>
			</td>
			<td class="value" colspan="2">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.doc;*.xls;*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
			</td>
		</tr>
		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					收款单位:
				</label>
			</td>
			<td class="value">
				<input id="skdw" name="skdw"  type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.skdw }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收款单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					开户行:
				</label>
			</td>
			<td class="value">
				<input id="bankName" name="bankName"  type="text" style="width: 150px"  value="${emkTestCostPage.bankName }" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户行</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					账号:
				</label>
			</td>
			<td class="value">
				<input id="bankAccount" name="bankAccount"  type="text" style="width: 150px"  value="${emkTestCostPage.bankAccount }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					发票号:
				</label>
			</td>
			<td class="value">
				<input id="fpNo" name="fpNo"  type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.fpNo }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					测试费用金额:
				</label>
			</td>
			<td class="value">
				<input id="costMoney" name="costMoney"  datatype="d" type="text" style="width: 150px"  value="${emkTestCostPage.costMoney }"  class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试费用金额</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone"  type="text" style="width: 150px"  value="${emkTestCostPage.telphone }" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>

		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					发票状态:
				</label>
			</td>
			<td class="value">
				<input id="fpState" name="fpState"  type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.fpState }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试费付款状态:
				</label>
			</td>
			<td class="value">
				<input id="costState" name="costState"  type="text" style="width: 150px" class="inputxt"  value="${emkTestCostPage.costState }"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试费付款状态</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","90px");
	});
</script>