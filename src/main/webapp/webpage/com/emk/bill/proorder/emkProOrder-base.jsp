<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>生产订单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#boxImageUrl").val(d.attributes.url);
			$("#boxImage").val(d.attributes.name);
			$("#boxImageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg1").attr('src',d.attributes.url);

		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkProOrderPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<%--<tr>
			<td align="right" >
				<label class="Validform_label">
					工单号:
				</label>
			</td>
			<td class="value" >
				&lt;%&ndash;<input id="workNo" name="workNo" datatype="*" value="${emkProOrderPage.workNo}" validType="emk_pro_order,work_no,id"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />&ndash;%&gt;
				<input id="workNo" name="workNo" datatype="*" value="${emkProOrderPage.workNo}"  type="text" style="width: 150px" class="inputxt" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订单类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="orderType" field="orderType" typeGroupCode="orderType" datatype="*" defaultVal="${emkProOrderPage.orderType}" hasLabel="false" title="工单类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单类型</label>
			</td>
		</tr>--%>
			<tr>
				<td align="right" >
					<label class="Validform_label">
						客户代码:
					</label>
				</td>
				<td class="value" >
					<input id="cusNum" name="cusNum" value="${emkProOrderPage.cusNum }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">客户代码</label>
				</td>
				<td align="right" >
					<label class="Validform_label">
						客户名称:
					</label>
				</td>
				<td class="value" colspan="6">
					<input id="cusName" name="cusName" value="${emkProOrderPage.cusName }" readonly type="text" style="width: 150px" class="inputxt"  datatype="*"/>
					<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
							   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">客户名称</label>
				</td>
				<td class="value" rowspan="5">
					<input id="customSample" name="customSample" value="${emkProOrderPage.customSample }" type="hidden" />
					<img id="uploadimg0" src="${emkProOrderPage.customSampleUrl eq null ? 'images/bjlogo.png':emkProOrderPage.customSampleUrl}" width="150" height="150">
					<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
					</t:upload>
					<c:if test="${emkProOrderPage.customSampleUrl ne null && emkProOrderPage.customSampleUrl ne ''}">[<a href="javascript:findDetail('${emkProOrderPage.customSampleUrl }')">${emkProOrderPage.customSample }</a>]</c:if>
					<span id="customSampleId"></span>
					<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkProOrderPage.customSampleUrl }"/>
				</td>
			</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value"  >
				<input id="orderNo" name="orderNo" readonly value="${emkProOrderPage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value" colspan="6">
				<input id="ysDate" name="recevieDate" readonly value="${emkProOrderPage.recevieDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>
		</tr>
		<tr>
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
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="sumTotal" name="sumTotal"  datatype="n"  value="${emkProOrderPage.sumTotal }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" value="${emkProOrderPage.gysCode }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden"  value="${emkProOrderPage.gys }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkProOrderPage.businesser }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkProOrderPage.businesserName }"  type="hidden"  />
				<input id="businesseDeptName" name="businesseDeptName" value="${emkProOrderPage.businesseDeptName }"  readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkProOrderPage.businesseDeptId }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" type="text" value="${emkProOrderPage.sampleNoDesc }"  style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					客户单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price"  type="text" style="width: 150px" value="${emkProOrderPage.price }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户单价</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商单价:
				</label>
			</td>
			<td class="value">
				<input id="gysPrice" name="gysPrice"  type="text" style="width: 150px" value="${emkProOrderPage.gysPrice }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商单价</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkProOrderPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkProOrderPage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色:
				</label>
			</td>
			<td class="value">
				<input id="color" name="color"  type="text" style="width: 150px" value="${emkProOrderPage.color }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					客户金额:
				</label>
			</td>
			<td class="value">
				<input id="cusJin" name="cusJin"  type="text" style="width: 150px" value="${emkProOrderPage.cusJin }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户金额</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商金额:
				</label>
			</td>
			<td class="value">
				<input id="gysJin" name="gysJin"  type="text" style="width: 150px" value="${emkProOrderPage.gysJin }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商金额</label>
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
				<input id="developer" name="developer" readonly type="hidden" value="${emkProOrderPage.developer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  value="${emkProOrderPage.developerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						尺码范围:
					</label>
				</td>
				<td class="value" colspan="9">
					<input id="sizeFw" name="sizeFw"   type="text" value="${emkProOrderPage.sizeFw }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">尺码范围</label>
				</td>
				<%--<td align="right">
					<label class="Validform_label">
						总金额:
					</label>
				</td>
				<td class="value">
					<input id="sumMoney" name="sumMoney"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">总金额</label>
				</td>--%>
			</tr>

			<tr>
				<td colspan="10" id="instructionfile" class="value">
				</td>
			</tr>

	</table>
	<t:tabs id="orderDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?detailMxList&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?barCodeMxList&type=0&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="洗水标条码" id="barCode"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?barCodeMxList&type=1&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="胶袋贴条码" id="barCode1"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?barCodeMxList&type=2&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="箱贴条码" id="barCode2"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<%--<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="">
				<input id="proTypeName" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="default" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="orderTime" readonly value="${kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>

		</tr>
		<tr>


		</tr>--%>

		<tr id="dgrImageDiv">
			<td align="right" >
				<label class="Validform_label">
					包装方式:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="bzfs" name="bzfs"  value="${emkProOrderPage.bzfs }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装方式</label>
			</td>
			<td align="right" rowspan="5" >
				<label class="Validform_label">
					包装效果图:
				</label>
			</td>
			<td class="value" rowspan="5" colspan="3" >
				<input id="boxImage" name="boxImage" value="${emkProOrderPage.boxImage }" type="hidden" />
				<img id="uploadimg1" src="${emkProOrderPage.boxImageUrl eq null ? 'images/bjlogo.png':emkProOrderPage.boxImageUrl}" width="150" height="150">
				<t:upload name="instruction1" id="instruction1" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile1" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess1" >
				</t:upload>
				<span id="boxImageId"></span>
				<input id="boxImageUrl" name="boxImageUrl" value="${emkProOrderPage.boxImageUrl }" type="hidden" />
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单件:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="one" name="one" value="${emkProOrderPage.one }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
			<td class="value" colspan="3">
				<input id="polybag" name="polybag"  value="${emkProOrderPage.polybag }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
			<td class="value" colspan="3">
				<input id="boxup" name="boxup"  value="${emkProOrderPage.boxup }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">装箱</label>
			</td>
		</tr>

		<tr>
			<td colspan="4" id="instructionfile1" class="value">&nbsp;
			</td>
		</tr>

			<%--	<tr>
                    <td align="right">
                        <label class="Validform_label">
                            备注:
                        </label>
                    </td>
                    <td class="value" colspan="7">
                        <textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark"></textarea>
                        <span class="Validform_checktip"></span>
                        <label class="Validform_label" style="display: none;">备注</label>
                    </td>
                </tr>--%>
			<tr>
				<td align="right" >
					<label class="Validform_label">
						订单号:
					</label>
				</td>
				<td class="value"  >
					<input id="corderNo" name="corderNo" value="${emkProOrderPage.orderNo}" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">订单号</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						出货日期:
					</label>
				</td>
				<td class="value">
					<input id="chDate" name="chDate" readonly value="${emkProOrderPage.chDate}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">出货日期</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						单件毛重:
					</label>
				</td>
				<td class="value">
					<input id="oneWeightMao" name="oneWeightMao" value="${emkProOrderPage.oneWeightMao}"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">单件毛重</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						单件净重:
					</label>
				</td>
				<td class="value">
					<input id="oneWeightJz" name="oneWeightJz"  value="${emkProOrderPage.oneWeightJz}"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">单件净重</label>
				</td>
			</tr>
	</table>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?boxMxList&type=0&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="单色单码装" id="box1"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?boxMxList&type=1&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="单色混码装" id="box2"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?boxMxList&type=2&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="混色单码装" id="box3"></t:tab>
	</t:tabs>
	<t:tabs id="barCodeDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkProOrderController.do?boxMxList&type=3&proOrderId=${emkProOrderPage.id}" icon="icon-search" title="混色混码装" id="box4"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="12%">
				<label class="Validform_label">
					预计中期验货日期:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="zqyhDate" name="zqyhDate"  value="${emkProOrderPage.zqyhDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
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
			<td class="value" colspan="7">
				<input id="wqyhDate" name="wqyhDate" readonly value="${emkProOrderPage.wqyhDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
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
			<td class="value" colspan="7">
				<input id="cystate" name="cystate"   value="${emkProOrderPage.cystate }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样状态</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					实际出厂日期:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="outDate" name="outDate" readonly  value="${emkProOrderPage.outDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际出厂日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value" colspan="7">
				<input id="levelDays" name="levelDays" value="${emkProOrderPage.levelDays }" datatype="n" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
		$("#instruction1-button").css("width","70px");

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