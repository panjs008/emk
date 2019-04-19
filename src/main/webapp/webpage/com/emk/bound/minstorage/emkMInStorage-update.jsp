<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>采购入库表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码

		$(document).ready(function(){

		});

		function changeType(){

		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMInStorageController.do?doUpdate" tiptype="1">
	<input id="emkMInStorageId" name="emkMInStorageId" type="hidden" value="${emkMInStoragePage.id }"/>
	<c:set var="selectType" value="${emkMInStoragePage.type }"  scope="session"></c:set>
	<input id="type" name="type" type="hidden" value="${emkMInStoragePage.type }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

		<tr>
			<td align="right" >
				<label class="Validform_label">
					申请人:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="realName" name="realName" value="${emkMInStoragePage.appler }"  type="text" readonly style="width: 30%" class="inputxt" >
				<input name="userName"   type="hidden" value="${emkMInStoragePage.applerId }"   id="userName" type="text"  />
				<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelect0" name="userList0" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					入库人:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="sender" name="sender" type="text" value="${emkMInStoragePage.rker }"  readonly style="width:  30%" class="inputxt" >
				<input name="senderUserNames"   type="hidden" value="${emkMInStoragePage.rkerId }"  id="senderUserNames" type="text"  />
				<t:choose  hiddenName="senderUserNames"  hiddenid="userName" url="userController.do?userdept0" name="userList0" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="sender,sendDeptName,sendDeptCode" isclear="true" isInit="true"></t:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					入库日期:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="kdDate" name="kdDate" readonly value="${emkMInStoragePage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">入库日期</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="7">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMInStoragePage.cusName }" style="width:  30%" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo"  value="${emkMInStoragePage.orderNo}" type="text" style="width: 150px" class="inputxt"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" datatype="*" type="text" value="${emkMInStoragePage.sampleNo }" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value" >
				<input id="total" name="total" datatype="n" type="text" value="${emkMInStoragePage.total }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMInStoragePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
		</tr>
	</table>

	<t:tabs id="inboundDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkMInStorageController.do?orderMxList&type=${param.type}&inStorageId=${emkMInStoragePage.id}" icon="icon-search" title="${pactTypeName}" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMInStoragePage.businesseDeptName }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMInStoragePage.businesseDeptId }" type="hidden"  />
				<input id="businesser" name="businesser" readonly value="${emkMInStoragePage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMInStoragePage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="businesserDate" name="businesserDate" readonly value="${emkMInStoragePage.businesserDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">日期</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMInStoragePage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMInStoragePage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="tracerDate" name="tracerDate" readonly value="${emkMInStoragePage.tracerDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">日期</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					采购员:
				</label>
			</td>
			<td class="value">
				<input id="caigouerName" name="caigouerName" value="${emkMInStoragePage.caigouerName }" type="text"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="caigouerDate" name="caigouerDate" readonly value="${emkMInStoragePage.caigouerDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">日期</label>
			</td>
		</tr>
	</table>

</t:formvalid>
</body>
</html>
