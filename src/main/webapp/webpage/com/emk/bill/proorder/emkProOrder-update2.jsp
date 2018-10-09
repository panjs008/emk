<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>生产订单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

	<script type="text/javascript">


		function findDetail(photoUrl) {
			$.dialog({
				content: 'url:emkEnquiryController.do?photo&photoUrl='+photoUrl,
				zIndex: getzIndex(),
				title : "查看",
				lock : true,
				width:900,
				height: 500,
				opacity : 0.3,
				cache:false,
				lock : true,
				cache:false,
				max: true,
				min: true,
				drag: true,
				resize: false
			});
		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkProOrderPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					工单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="workNo" name="workNo" datatype="*" value="${emkProOrderPage.workNo}" validType="emk_pro_order,work_no,id"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工单类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="orderType" field="orderType" typeGroupCode="orderType" datatype="*" defaultVal="${emkProOrderPage.orderType}" hasLabel="false" title="工单类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工单类型</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" style="width: 32%" >
				<input id="orderNo" name="orderNo" readonly value="${emkProOrderPage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<input id="gysCode" name="gysCode" value="${emkProOrderPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" value="${emkProOrderPage.gys }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkProOrderPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkProOrderPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesser" name="businesser" readonly value="${emkProOrderPage.businesser }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkProOrderPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户编号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkProOrderPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkProOrderPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />

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
				<input id="tracer" name="tracer" readonly type="text" value="${emkProOrderPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkProOrderPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="developer" name="developer" readonly value="${emkProOrderPage.developer }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkProOrderPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkProOrderPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkProOrderPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkProOrderPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkProOrderPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="orderTime" name="orderTime" readonly value="${emkProOrderPage.orderTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="recevieDate" name="recevieDate" readonly value="${emkProOrderPage.recevieDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>

		</tr>

		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" value="${emkProOrderPage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkProOrderPage.customSampleUrl eq null ? 'images/bjlogo.png':emkProOrderPage.customSampleUrl}" width="150" height="150">
				<br/>[<a href="javascript:findDetail('${emkProOrderPage.customSampleUrl }')">${emkProOrderPage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkProOrderPage.customSampleUrl }" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkProOrderPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="levelDays" value="${levelDays}"   datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value">
				<input id="sumTotal" name="sumTotal" value="${emkProOrderPage.sumTotal }"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总金额:
				</label>
			</td>
			<td class="value">
				<input id="sumMoney" name="sumMoney" value="${emkProOrderPage.sumMoney }"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					尺码范围:
				</label>
			</td>
			<td class="value">
				<input id="sizeFw" name="sizeFw"  value="${emkProOrderPage.sizeFw }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码范围</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price" value="${emkProOrderPage.price }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<input id="bz" name="bz" value="${emkProOrderPage.bz }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc">${emkProOrderPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
			<%--<tr>
                <td align="center"  colspan="4" height="30px;">
                    <label class="Validform_label">
                        包装方式
                    </label>
                </td>
            </tr>--%>
		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					包装效果图:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="boxImage" name="boxImage" type="hidden" value="${emkProOrderPage.boxImage }" />
				<img id="uploadimg1" src="${emkProOrderPage.boxImageUrl eq null ? 'images/bjlogo.png':emkProOrderPage.boxImageUrl}" width="150" height="150">
				<br/>[<a href="javascript:findDetail('${emkProOrderPage.boxImageUrl }')">${emkProOrderPage.boxImage }</a>]
				<span id="boxImageId"></span>
				<input id="boxImageUrl" name="boxImageUrl" type="hidden" value="${emkProOrderPage.boxImageUrl }" />
			</td>
			<td align="right">
				<label class="Validform_label">
					包装方式:
				</label>
			</td>
			<td class="value">
				<input id="bzfs" name="bzfs"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProOrderPage.bzfs }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单件:
				</label>
			</td>
			<td class="value">
				<input id="one" name="one"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProOrderPage.one }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装效果图</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					胶袋:
				</label>
			</td>
			<td class="value">
				<input id="polybag" name="polybag"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProOrderPage.polybag }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					装箱:
				</label>
			</td>
			<td class="value">
				<input id="boxup" name="boxup"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProOrderPage.boxup }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">装箱</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					预计中期验货日期:
				</label>
			</td>
			<td class="value">
				<input id="zqyhDate" name="zqyhDate"  readonly value="${emkProOrderPage.zqyhDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计中期验货日期</label>
			</td>
			</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					预计尾期验货日期:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="wqyhDate" name="wqyhDate" readonly  value="${emkProOrderPage.wqyhDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计尾期验货日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					船样状态:
				</label>
			</td>
			<td class="value">
				<input id="cystate" name="cystate"  value="${emkProOrderPage.cystate }"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					实际出厂日期:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" readonly  value="${emkProOrderPage.outDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际出厂日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark">${emkProOrderPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
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