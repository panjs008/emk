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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkContractController.do?doUpdate" tiptype="1">
	<input id="pactId" name="pactId" type="hidden" value="${emkContractPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					甲方:
				</label>
			</td>
			<td class="value">
				<input id="partyA" name="partyA"  value="${emkContractPage.partyA}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">甲方</label>
			</td>

			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkContractPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkContractPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					合同编号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum"  value="${emkContractPage.htNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同编号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					乙方:
				</label>
			</td>
			<td class="value">
				<input id="partyB" name="partyB"  value="${emkContractPage.partyB}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">乙方</label>
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
				<input id="businesser" name="businesser" readonly value="${emkContractPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkContractPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="recevieDate" name="recevieDate" readonly value="${emkContractPage.recevieDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">日期</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">

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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkContractPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkContractPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td class="value" colspan="2">

			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">

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
				<input id="developer" name="developer" readonly value="${emkContractPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkContractPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td class="value" colspan="2">

			</td>
		</tr>

		<tr>
			<td align="left" class="value"  colspan="6">
				<label class="Validform_label">
					一、订单明细和条款:
				</label>
			</td>
		</tr>
	</table>

	<t:tabs id="orderDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkContractController.do?detailMxList&proOrderId=${emkContractPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" colspan="4">
				1）以上单价为出厂价：<input id="outPrice" name="outPrice" value="${emkContractPage.outPrice }"  type="text" style="width: 60px" class="inputxt"  ignore="ignore" />，FOB：<input id="fob" name="fob" value="${emkContractPage.fob }"  type="text" style="width: 100px" class="inputxt"  ignore="ignore" />(备注： )，尺码颜色的数量分配如上表或见附件。
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
				6）交货地点：乙方指定<input id="place" name="place" value="${emkContractPage.place }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				或<input id="boundName" name="boundName" value="${emkContractPage.boundName }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				仓库。
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				7）原产地及生产商：<input id="ycd" name="ycd" value="${emkContractPage.ycd }"  type="text" style="width: 50%" class="inputxt"  ignore="ignore" />
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				二、付款方式：
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4">
				1）<input id="payType" name="payType" value="${emkContractPage.payType }"  type="text" style="width: 50%" class="inputxt"  ignore="ignore" />
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
				<input id="sqdb" name="sqdb" value="${emkContractPage.sqdb }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表授权代表</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					法定代表授权代表:
				</label>
			</td>
			<td class="value">
				<input id="ysqdb" name="ysqdb" value="${emkContractPage.ysqdb }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="address" name="address" value="${emkContractPage.address }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="yaddress" name="yaddress" value="${emkContractPage.yaddress }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="telphone" name="telphone" value="${emkContractPage.telphone }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="ytelphone" name="ytelphone" value="${emkContractPage.ytelphone }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="signDate" name="signDate" value="${emkContractPage.signDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签定日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					签定日期:
				</label>
			</td>
			<td class="value">
				<input id="ysignDate" name="ysignDate" value="${emkContractPage.ysignDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签定日期</label>
			</td>
		</tr>
	</table>

</t:formvalid>
</body>
