<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>预计验货时间表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			BindSelect("gysId","ymkCustomController.do?findSupplierList",1,"${emkExamTimePage.gysCode},${emkExamTimePage.gys}");

			$("#gysId").change(function(){
				var itemarr = $("#gysId").val().split(","); //字符分割
				$("#gysCode").val(itemarr[0]);
				$("#gys").val(itemarr[1]);
			});
			$("#detailId").load("emkExamTimeController.do?orderMxList&proOrderId=${emkExamTimePage.id }");

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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkExamTimeController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkExamTimePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceNum" name="produceNum" value="${emkExamTimePage.produceNum }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo"  type="text" value="${emkExamTimePage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkExamTimePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkExamTimePage.businesseDeptId }"  type="hidden"  />
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
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkExamTimePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkExamTimePage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
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
				<input id="customSample" name="customSample" type="hidden" value="${emkExamTimePage.customSample }"/>
				<img id="uploadimg0" src="${emkExamTimePage.customSampleUrl eq null ? 'images/bjlogo.png':emkExamTimePage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>[<a href="javascript:findDetail('${emkExamTimePage.customSampleUrl }')">${emkExamTimePage.customSample }</a>]
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkExamTimePage.customSampleUrl }"/>
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
				<input id="gysCode" name="gysCode" type="hidden" value="${emkExamTimePage.gysCode }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkExamTimePage.gys }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					操作员:
				</label>
			</td>
			<td class="value" >
				<input id="createName" name="createName" value="${emkExamTimePage.createName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<input id="businesser" name="businesser" value="${emkExamTimePage.businesser }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkExamTimePage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<input id="tracer" name="tracer" readonly type="text" value="${emkExamTimePage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkExamTimePage.tracerName }"/>
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
				<input id="developer" name="developer" readonly type="text" value="${emkExamTimePage.developer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden" value="${emkExamTimePage.developerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" value="${emkExamTimePage.sampleNo }" class="inputxt"  ignore="ignore" />
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
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkExamTimePage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkExamTimePage.sampleNo }" >
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkExamTimePage.sampleNo }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px"  value="${emkExamTimePage.sampleNo }"  class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					更新时间:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="kdDate" name="kdDate" readonly value="${emkExamTimePage.kdDate }"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">更新时间</label>
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
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkExamTimePage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					中期验货状态:
				</label>
			</td>
			<td class="value">
				<input id="zqyhState" name="zqyhState" value="${emkExamTimePage.zqyhState }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">中期验货状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					预计中期验货日期:
				</label>
			</td>
			<td class="value">
				<input id="zqyhDate" name="zqyhDate" readonly value="${emkExamTimePage.zqyhDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计中期验货日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距中期验货剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levealZqyh" name="levealZqyh" datatype="n" value="${emkExamTimePage.levealZqyh }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距中期验货剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					尾期验货状态:
				</label>
			</td>
			<td class="value">
				<input id="wqyhState" name="wqyhState" type="text" value="${emkExamTimePage.wqyhState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尾期验货状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					预计尾期验货日期:
				</label>
			</td>
			<td class="value">
				<input id="wqyhDate" name="wqyhDate" readonly value="${emkExamTimePage.wqyhDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计尾期验货日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距尾期验货剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levealWqyh" name="levealWqyh" datatype="n" value="${emkExamTimePage.levealWqyh }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距尾期验货剩余天数</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					最终中期验货日期:
				</label>
			</td>
			<td class="value">
				<input id="qrzqyhDate" name="qrzqyhDate" readonly value="${emkExamTimePage.qrzqyhDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">最终中期验货日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					最终尾期验货日期:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="qrwqyhDate" name="qrwqyhDate" readonly value="${emkExamTimePage.qrwqyhDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">最终尾期验货日期</label>
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