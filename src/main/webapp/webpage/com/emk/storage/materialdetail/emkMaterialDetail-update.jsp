<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>原料需求开发单明细</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function(){
			BindSelect("gysId","ymkCustomController.do?findSupplierList",1,"${emkMaterialDetailPage.gysCode},${emkMaterialDetailPage.gys}");
			$("#gysId").change(function(){
				var itemarr = $("#gysId").val().split(","); //字符分割
				$("#gysCode").val(itemarr[0]);
				$("#gys").val(itemarr[1]);
			});
		});
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
		};
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialDetailController.do?doUpdate" >
	<input id="id" name="id" type="hidden" value="${emkMaterialDetailPage.id }"/>
	<input id="materialId" name="materialId" type="hidden" value="${emkMaterialDetailPage.materialId }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>

			<td align="right">
				<label class="Validform_label">
					原料面料名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="proZnName" name="proZnName" value="${emkMaterialDetailPage.proZnName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="750px" height="500px"
						   icon="icon-search" title="选择物料" textname="id,proNum,proZnName,unit" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料面料名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkMaterialDetailPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" value="${emkMaterialDetailPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					物料编号:
				</label>
			</td>
			<td class="value">
				<input id="proNum" name="proNum" value="${emkMaterialDetailPage.proNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物料编号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit" value="${emkMaterialDetailPage.unit }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="signTotal" name="signTotal" value="${emkMaterialDetailPage.signTotal }" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					捻向:
				</label>
			</td>
			<td class="value">
				<input id="direction" name="direction" value="${emkMaterialDetailPage.direction }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">捻向</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					批号:
				</label>
			</td>
			<td class="value">
				<input id="betchNum" name="betchNum" value="${emkMaterialDetailPage.betchNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">批号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					幅宽:
				</label>
			</td>
			<td class="value">
				<input id="width" name="width" type="text" value="${emkMaterialDetailPage.width }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">幅宽</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					颜色:
				</label>
			</td>
			<td class="value">
				<input id="color" name="color" type="text" value="${emkMaterialDetailPage.color }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					克重:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight" type="text"  value="${emkMaterialDetailPage.weight }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					成分:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf" type="text" value="${emkMaterialDetailPage.chengf }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">成分</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="remark" style="width:95%;height:50px" class="inputxt" rows="5" name="remark">${emkMaterialDetailPage.remark }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>

		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/materialdetail/emkMaterialDetail.js"></script>
