<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>面料预采购合同</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

	<%@include file="/context/header2.jsp"%>
	<%--<script src="${webRoot}/context/gys.js"></script>--%>

	<script type="text/javascript">
		//编写自定义JS代码

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialPactController.do?doAdd" tiptype="1">
	<input id="pactId" name="pactId" type="hidden" value="${emkMaterialPactPage.id }"/>
	<input id="type" name="type" type="hidden" value="${param.type}"/>
	<input id="type" name="flag" type="hidden" value="0"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					甲方:
				</label>
			</td>
			<td class="value">
				<input id="partyA" name="partyA"  value="${emkMaterialPactPage.partyA}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">甲方</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum"  type="text" value="${emkMaterialPactPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMaterialPactPage.businesseDeptName }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMaterialPactPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNum" name="orderNum"  value="${emkMaterialPactPage.orderNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					乙方:
				</label>
			</td>
			<td class="value">
				<input id="partyB" name="partyB"  value="${emkMaterialPactPage.partyB}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">乙方</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMaterialPactPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkMaterialPactPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMaterialPactPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					大货交期:
				</label>
			</td>
			<td class="value">
				<input id="dhjqDate" name="dhjqDate" readonly value="${emkMaterialPactPage.dhjqDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大货交期</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkMaterialPactPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMaterialPactPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMaterialPactPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="sumTotal" name="sumTotal" value="${emkMaterialPactPage.sumTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkMaterialPactPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkMaterialPactPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkMaterialPactPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkMaterialPactPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkMaterialPactPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					采购合同提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkMaterialPactPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">采购合同提交日期</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">
			</td>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkMaterialPactPage.sampleNoDesc }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td align="left" class="value"  colspan="8">
				<label class="Validform_label">
					一、订单明细和条款:
				</label>
			</td>
		</tr>
	</table>
	<t:tabs id="pactDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkMaterialPactController.do?orderMxList&type=${param.type}&flag=0&proOrderId=${emkMaterialPactPage.id}" icon="icon-search" title="${pactTypeName}" id="pact"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" colspan="4">
				1）以上单价为出厂价：<input id="outPrice" name="outPrice" value="${emkMaterialPactPage.outPrice }"  type="text" style="width: 60px" class="inputxt"  ignore="ignore" />，FOB：<input id="fob" name="fob" value="${emkMaterialPactPage.fob }"  type="text" style="width: 100px" class="inputxt"  ignore="ignore" />(备注： )，尺码颜色的数量分配如上表或见附件。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				2）甲方确保按以上条款及乙方确认品质后生产大货，准时进仓，如有变化按双方修改文件为准；
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				3）甲方在出货前合理时间内提供准确的预报箱单供乙方定仓，通过乙方指定的出口代理公司和货运代理报关出口。需要商检的货物乙方提前一周以上通知甲方，甲方在报关前及时提供相关单证。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				4）甲方须及时提供乙方生产进程并同意乙方的查货人员进入生产工厂验货，根据乙方的验货报告及时改进；经验货合格后方可出货。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				5）包装:根据客人指示资料提供的要求。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				6）交货地点：乙方指定<input id="place" name="place" value="${emkMaterialPactPage.place }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				或<input id="boundName" name="boundName" value="${emkMaterialPactPage.boundName }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				仓库。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				7）原产地及生产商：<input id="ycd" name="ycd" value="${emkMaterialPactPage.ycd }"  type="text" style="width: 50%" class="inputxt"  ignore="ignore" />
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				二、付款方式：
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				1）<input id="payType" name="payType" value="${emkMaterialPactPage.payType }"  type="text" style="width: 50%" class="inputxt"  ignore="ignore" />
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				2）订单确认后，乙方应及时付订金，甲方收到订金之后安排预定原料。出货前乙方应及时安排货款。如不能安时付款的，依次造成不能及时预定原料，不能上机生产的并耽误货期的情况，甲方概不负责。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				3）甲方仅代付衣架款，乙方负责衣架的订购及送货至甲方仓库。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				三、其他：
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				1）本合同依法签订，一旦签订即具法律效力，双方必须全面履行。不得单方擅自变更或解除。如履行本协协议时发生争议，由甲，乙双方协商解决。协商不成的，双方一致同意交由协议签订双方的任何一方当地人民法院通过诉讼方式予以解决。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				2）本合同正本二份，双方各执一份，具有同等法律效力。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				3）甲方仅代付衣架款，乙方负责衣架的订购及送货至甲方仓库。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2" align="left">
				甲方
			</td>
			<td class="value" colspan="2" align="left">
				乙方
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					法定代表授权代表:
				</label>
			</td>
			<td class="value">
				<input id="sqdb" name="sqdb" value="${emkMaterialPactPage.sqdb }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表授权代表</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					法定代表授权代表:
				</label>
			</td>
			<td class="value">
				<input id="ysqdb" name="ysqdb" value="${emkMaterialPactPage.ysqdb }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表授权代表</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" value="${emkMaterialPactPage.address }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="yaddress" name="yaddress" value="${emkMaterialPactPage.yaddress }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" value="${emkMaterialPactPage.telphone }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="ytelphone" name="ytelphone" value="${emkMaterialPactPage.ytelphone }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="signDate" name="signDate" value="${emkMaterialPactPage.signDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签定日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					签定日期:
				</label>
			</td>
			<td class="value">
				<input id="ysignDate" name="ysignDate" value="${emkMaterialPactPage.ysignDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签定日期</label>
			</td>
		</tr>
	</table>

</t:formvalid>
</body>
<script>
	$(document).ready(function() {

		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 157,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>