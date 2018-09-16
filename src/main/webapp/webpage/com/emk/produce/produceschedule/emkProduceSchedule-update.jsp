<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>采购生产表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			BindSelect("gysId","ymkCustomController.do?findSupplierList",1,"${emkProduceSchedulePage.gysCode},${emkProduceSchedulePage.gys}");

			$("#gysId").change(function(){
				var itemarr = $("#gysId").val().split(","); //字符分割
				$("#gysCode").val(itemarr[0]);
				$("#gys").val(itemarr[1]);
			});
			$("#detailId").load("emkProduceScheduleController.do?orderMxList&proOrderId=${emkProduceSchedulePage.id }");

		});


		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html(d.attributes.name);
			$("#uploadimg0").attr('src',d.attributes.url);

		}


		function BindSelect(ctrlName, url,type,categoryId) {
			var control = $('#' + ctrlName);
			//设置Select2的处理
			control.select2({
				formatResult: formatState,
				formatSelection: formatState,
				escapeMarkup: function (m) {
					return m;
				}
			});
			//绑定Ajax的内容
			$.getJSON(url, function (data) {
				control.empty();//清空下拉框
				control.append("<option value=''>请选择</option>");
				$.each(data.obj, function (i, item) {
					control.append("<option value='" + item.supplierCode + ","+item.supplier +"'>" + item.supplier + "</option>");
				});
				if(type ==1){
					$("#"+ctrlName).select2('val',categoryId);
				}
			});
		}

		function formatState (state) {
			if (!state.id) { return state.text; }
			var $state = $(
					'<span>' + state.text + '</span>'
			);
			return $state;
		}
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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProduceScheduleController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkProduceSchedulePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceHtNum" name="produceHtNum" value="${emkProduceSchedulePage.produceHtNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo"  type="text" value="${emkProduceSchedulePage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkProduceSchedulePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkProduceSchedulePage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户编号:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkProduceSchedulePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkProduceSchedulePage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>

		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" value="${emkProduceSchedulePage.customSample }" type="hidden" />
				<img id="uploadimg0" src="${emkProduceSchedulePage.customSampleUrl eq null ? 'images/bjlogo.png':emkProduceSchedulePage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkProduceSchedulePage.customSampleUrl }')">${emkProduceSchedulePage.customSample }</a>]
				<span id="customSampleId" ></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkProduceSchedulePage.customSampleUrl }" type="hidden" />
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
				<input id="gysCode" name="gysCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.gysCode }"/>
				<input id="gys" name="gys" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.gys }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					操作员:
				</label>
			</td>
			<td class="value" >
				<input id="createName" name="createName"  readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.createName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">操作员</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<input id="businesser" name="businesser" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.businesser }"/>
				<input id="businesserName" name="businesserName"  type="hidden" value="${emkProduceSchedulePage.businesserName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<input id="tracer" name="tracer" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.tracer }"/>
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkProduceSchedulePage.tracerName }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<input id="developer" name="developer" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.developer }"/>
				<input id="developerName" name="developerName"  type="hidden" value="${emkProduceSchedulePage.developerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.sampleNo }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkProduceSchedulePage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkProduceSchedulePage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.proTypeName }"/>
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" value="${emkProduceSchedulePage.proType }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" readonly value="${emkProduceSchedulePage.kdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" value="${emkProduceSchedulePage.outDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>
		</tr>

		<tr>
			<td colspan="6" id="instructionfile" class="value">
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkProduceSchedulePage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					原料布料状态:
				</label>
			</td>
			<td class="value" >
				<input id="ylblState" name="ylblState"  type="text" value="${emkProduceSchedulePage.ylblState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料布料状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					原料布料到厂日期:
				</label>
			</td>
			<td class="value" >
				<input id="ylblLimitDate" name="ylblLimitDate" value="${emkProduceSchedulePage.ylblLimitDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料布料到厂日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					距原料到厂剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelYlblDay" name="leavelYlblDay" value="${emkProduceSchedulePage.leavelYlblDay }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距原料到厂剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					缝制辅料状态:
				</label>
			</td>
			<td class="value" >
				<input id="fzblState" name="fzblState" value="${emkProduceSchedulePage.fzblState }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制辅料状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					缝制辅料到厂日期:
				</label>
			</td>
			<td class="value" >
				<input id="fzblLimitDate" name="fzblLimitDate" value="${emkProduceSchedulePage.fzblLimitDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制辅料到厂日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					距缝制到厂剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelFzblDay" name="leavelFzblDay" value="${emkProduceSchedulePage.leavelFzblDay }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距缝制到厂剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					包装辅料状态:
				</label>
			</td>
			<td class="value" >
				<input id="bzblState" name="bzblState" value="${emkProduceSchedulePage.bzblState }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装辅料状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					包装辅料到厂日期:
				</label>
			</td>
			<td class="value" >
				<input id="bzblLimitDate" name="bzblLimitDate" value="${emkProduceSchedulePage.bzblLimitDate }" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装辅料到厂日期</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					距包装到厂剩余天数:
				</label>
			</td>
			<td class="value" >
				<input id="leavelBzblDay" name="leavelBzblDay" value="${emkProduceSchedulePage.leavelBzblDay }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距包装到厂剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					染色状态:
				</label>
			</td>
			<td class="value" >
				<select  id="ranState" name="ranState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染色状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					染色已完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="ranFinish" name="ranFinish" value="${emkProduceSchedulePage.ranFinish }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染色已完成数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					染色未完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="ranUnfinish" name="ranUnfinish" value="${emkProduceSchedulePage.ranUnfinish }" datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染色未完成数量</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					裁剪状态:
				</label>
			</td>
			<td class="value" >
				<select  id="caiState" name="caiState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">裁剪状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					裁剪已完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="caiFinish" name="cFinish" datatype="n" value="${emkProduceSchedulePage.caiFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">裁剪已完成数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					裁剪未完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="caiUnfinish" name="cUnfinish" datatype="n" value="${emkProduceSchedulePage.caiUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">染色未完成数量</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					缝制状态:
				</label>
			</td>
			<td class="value" >
				<select  id="fzState" name="fzState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					缝制已完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="fzFinish" name="fzFinish" datatype="n" value="${emkProduceSchedulePage.fzFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制已完成数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					缝制未完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="fzUnfinish" name="fzUnfinish" datatype="n" value="${emkProduceSchedulePage.fzUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制未完成数量</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					烫标状态:
				</label>
			</td>
			<td class="value" >
				<select  id="btState" name="btState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装辅料状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					烫标已完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="btFinish" name="btFinish" datatype="n" value="${emkProduceSchedulePage.btFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">烫标已完成数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					烫标未完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="btUnfinish" name="btUnfinish" datatype="n" value="${emkProduceSchedulePage.btUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">烫标未完成数量</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					整烫状态:
				</label>
			</td>
			<td class="value" >
				<select  id="ztState" name="ztState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装辅料状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					整烫已完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="ztFinish" name="ztFinish" datatype="n" value="${emkProduceSchedulePage.ztFinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">整烫已完成数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					整烫未完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="ztUnfinish" name="ztUnfinish" datatype="n" value="${emkProduceSchedulePage.ztUnfinish }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">整烫未完成数量</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					包装状态:
				</label>
			</td>
			<td class="value" >
				<select  id="bzState" name="bzState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装辅料状态</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					包装已完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="bzFinish" name="bzFinish" datatype="n" type="text" value="${emkProduceSchedulePage.bzFinish }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装已完成数量</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					包装未完成数量:
				</label>
			</td>
			<td class="value" >
				<input id="bzUnfinish" name="bzUnfinish" datatype="n" type="text" value="${emkProduceSchedulePage.bzUnfinish }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装未完成数量</label>
			</td>
		</tr>
	</table>
	<div id="detailId" style="width: auto; height: 200px;" ><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>

	</div>
	<!-- 添加 产品明细 模版-->
	<table style="width:100%;display: none" cellpadding="0" cellspacing="2" border="0">
		<tbody id="add_jeecgOrderProduct_table_template">
		<tr>
			<td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
			<td align="left">
				<select name="orderMxList[#index#].color" style="width: 60%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
					<c:forEach items="${categoryEntityList}" var="category">
						<option value="${category.code}">${category.name}</option>
					</c:forEach>
				</select>
					<%--<input nullmsg="请输入颜色！" id="proName00"  errormsg="请输入颜色" name="orderMxList[#index#].color" maxlength="100" type="text" value=""
                           style="width: 70%;">--%>
			</td>
			<td align="left">
				<input id="size00" nullmsg="请输入尺码！"  errormsg="请输入尺码" name="orderMxList[#index#].size" maxlength="100" type="text" value=""
					   style="width: 60%;"></td>
			<td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal" maxlength="100" type="text" value=""
									style="width: 60%;"></td>
		</tr>
		</tbody>

	</table>


</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");

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