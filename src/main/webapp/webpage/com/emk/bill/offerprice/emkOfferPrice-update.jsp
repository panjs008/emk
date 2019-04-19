<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>报价方案</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#uploadimg").attr('src',d.attributes.url);
		}
		$(document).ready(function(){
			$("#detailId").load("emkOfferPriceController.do?offerMxList&offerPriceId=${emkOfferPricePage.id }");
		});
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkOfferPriceController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkOfferPricePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>

			<td align="right">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value">
				<input id="cusNum" name="cusNum" type="hidden"  value="${emkOfferPricePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />

				<input id="cusName" name="cusName" type="text" value="${emkOfferPricePage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkOfferPricePage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款式:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkOfferPricePage.proTypeName }">
				<input id="proType" name="proType" value="${emkOfferPricePage.proType }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proTypeName" name="proTypeName" value="${emkOfferPricePage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					开单日期:
				</label>
			</td>
			<td class="value">
				<input id="kdTime" name="kdTime" type="text" value="${emkOfferPricePage.kdTime }" style="width: 150px"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开单日期</label>
			</td>

		</tr>
		<tr>
			<td align="right" rowspan="5">
				<label class="Validform_label" >
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" value="${emkOfferPricePage.customSample }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="customSampleUrl" name="customSampleUrl" value="${emkOfferPricePage.customSampleUrl }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<img id="uploadimg" src="${emkOfferPricePage.customSampleUrl eq '' ? 'images/bjlogo.png':emkOfferPricePage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>
			</td>
			<td align="right">
				<label class="Validform_label">
					加工费:
				</label>
			</td>
			<td class="value">
				<input id="workPrice" name="workPrice" datatype="d" value="${emkOfferPricePage.workPrice }" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">加工费</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					厂皮损耗费:
				</label>
			</td>
			<td class="value">
				<input id="lossPrice" name="lossPrice" datatype="d" value="${emkOfferPricePage.lossPrice }" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">厂皮损耗费</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					商检运费:
				</label>
			</td>
			<td class="value">
				<input id="freightPrice" name="freightPrice" datatype="d" value="${emkOfferPricePage.freightPrice }" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">商检运费</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					税费:
				</label>
			</td>
			<td class="value">
				<input id="tax" name="tax" type="text" datatype="d" style="width: 150px" value="${emkOfferPricePage.tax }" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">税费</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value">
				<input id="remark" name="remark" type="text" style="width: 150px" value="${emkOfferPricePage.remark }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>

		</tr>
		<tr>
			<td colspan="4" id="instructionfile" class="value">
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
				<input type="hidden" id="proId00" name="offerMxList[#index#].proId" />
				<input type="hidden" id="proNum00" name="offerMxList[#index#].proNum" /></td>
			<td align="left"><input nullmsg="请输入款号！" id="sampleNo00"   errormsg="请输入款号" name="offerMxList[#index#].sampleNo" maxlength="100" type="text" value=""
									style="width: 100px;"></td>
			<td align="left">
				<input nullmsg="请输入商号名称！" id="sampleName00"  errormsg="请输入商品名称" name="offerMxList[#index#].proName" maxlength="100" type="text" value=""
					   style="width: 120px;">
			</td>
			<td align="left">
				<input nullmsg="请输入颜色！" id="proName00"  errormsg="请输入颜色" name="offerMxList[#index#].color" maxlength="100" type="text" value=""
					   style="width: 120px;">
			</td>
			<td align="left"><input id="brand00" nullmsg="请输入尺码！"  errormsg="请输入尺码" name="offerMxList[#index#].brand" maxlength="100" type="text" value=""
									style="width: 120px;"></td>
			<td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入整数" name="offerMxList[#index#].signTotal" maxlength="100" type="text" value=""
									style="width: 120px;"></td>
			<td align="left">
				<input nullmsg="请输入单位！" id="signUnit00" errormsg="请输入单位" name="offerMxList[#index#].signUnit" maxlength="100" type="text" value=""
					   style="width: 60px;"></td>
			<td align="left"><input nullmsg="请输入单价！" id="signPrice00" errormsg="请输入单价" name="offerMxList[#index#].signPrice" maxlength="100" type="text" value=""
									style="width: 100px;"></td>
			<td align="left"><input nullmsg="请输入备注！" id="remark00"  errormsg="请输入备注" name="offerMxList[#index#].remark" maxlength="100" type="text" value=""
									style="width: 100px;"></td>
		</tr>
		</tbody>

	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/bill/offerprice/emkOfferPrice.js"></script>
<script>
	$(document).ready(function() {
		$("#instruction-button").css("width","70px");
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