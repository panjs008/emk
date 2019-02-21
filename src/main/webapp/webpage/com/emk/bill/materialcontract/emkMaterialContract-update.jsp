<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>原料采购合同表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码

		function resetTrNum(tableId) {
			$tbody = $("#"+tableId+"");
			$tbody.find('>tr').each(function(i){
				$(':input, select', this).each(function(){
					var $this = $(this), name = $this.attr('name'), val = $this.val();
					if(name!=null){
						if (name.indexOf("#index#") >= 0){
							$this.attr("name",name.replace('#index#',i));
						}else{
							var s = name.indexOf("[");
							var e = name.indexOf("]");
							var new_name = name.substring(s+1,e);
							$this.attr("name",name.replace(new_name,i));
						}
					}
				});
			});
		}
		$(document).ready(function(){
			$("#detailId").load("emkMaterialContractController.do?emkMaterialContractDetailList&contractId=${emkMaterialContractPage.id }&selectType=0");

		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialContractController.do?doUpdate" tiptype="1">
	<input id="emkMaterialContractId" name="emkMaterialContractId" type="hidden" value="${emkMaterialContractPage.id }"/>
	<input id="type" name="type" type="hidden" value="${emkMaterialContractPage.type }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

		<tr>
			<td align="right" >
				<label class="Validform_label">
					合同编号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum"  value="${emkMaterialContractPage.htNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					预采购合同号:
				</label>
			</td>
			<td class="value">
				<input id="yhtNum" name="yhtNum"  value="${emkMaterialContractPage.yhtNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预采购合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo"  value="${emkMaterialContractPage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMaterialContractPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMaterialContractPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value">
				<input id="produceNum" name="produceNum"  value="${emkMaterialContractPage.produceNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMaterialContractPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMaterialContractPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
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
				<input id="businesser" name="businesser" readonly value="${emkMaterialContractPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkMaterialContractPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMaterialContractPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkMaterialContractPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkMaterialContractPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkMaterialContractPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkMaterialContractPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkMaterialContractPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkMaterialContractPage.gys }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkMaterialContractPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkMaterialContractPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkMaterialContractPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMaterialContractPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkMaterialContractPage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					出货日期:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" readonly value="${emkMaterialContractPage.outDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					最迟到厂日期:
				</label>
			</td>
			<td class="value">
				<input id="ysDate" name="limitDate" value="${emkMaterialContractPage.limitDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})" readonly type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">最迟到厂日期</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="sumTotal" name="sumTotal" value="${emkMaterialContractPage.sumTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距最迟到厂剩余天数:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="levelDays" name="leaveLimit" type="text" value="${emkMaterialContractPage.leaveLimit }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距最迟到厂剩余天数</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkMaterialContractPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

	</table>
	<div id="detailId" style="width: auto; height: 200px;" ><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
	</div>
	<!-- 添加 产品明细 模版-->
	<table style="width:100%;display: none" cellpadding="0" cellspacing="2" border="0">
		<tbody id="add_jeecgOrderProduct_table_template">
		<tr>
			<td align="center"><input style="width: 40px;" type="checkbox" name="ck"/>
			</td>
			<td align="left"><input nullmsg="请输入物料编号！" id="proNum00"  errormsg="请输入物料编号" name="rkglMxList[#index#].proNum" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left">
				<input nullmsg="请输入物料名称！" id="proName00"  errormsg="请输入物料名称" name="rkglMxList[#index#].proName" maxlength="100" type="text" value=""
					   style="width: 95%;">
			</td>
			<td align="left"><input id="brand00" nullmsg="请输入规格！"  errormsg="请输入规格" name="rkglMxList[#index#].brand" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input id="signTotal00" nullmsg="请输入采购数量！"  errormsg="请输入采购数量" name="rkglMxList[#index#].signTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input nullmsg="请输入单价！" id="signPrice00" errormsg="请输入单价" name="rkglMxList[#index#].signPrice" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input nullmsg="请输入入库数量！" id="inTotal00" errormsg="请输入入库数量" name="rkglMxList[#index#].inTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input nullmsg="请输入出库数量！" ignore="ignore"  id="outTotal00" errormsg="请输入出库数量" name="rkglMxList[#index#].outTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
			<td align="left"><input nullmsg="请输入剩余数量！" ignore="ignore"  id="leavelTotal00" errormsg="请输入剩余数量" name="rkglMxList[#index#].leavelTotal" maxlength="100" type="text" value=""
									style="width: 95%;"></td>
		</tr>
		</tbody>

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