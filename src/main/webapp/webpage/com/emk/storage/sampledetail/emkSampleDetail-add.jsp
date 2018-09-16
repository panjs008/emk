<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>样品单明细</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	 <script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	 <script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>
  <script type="text/javascript">
	  $(function(){
		  BindSelect("gysId","ymkCustomController.do?findSupplierList",0,"");
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

		  });
	  }

	  function formatState (state) {
		  if (!state.id) { return state.text; }
		  var $state = $(
				  '<span>' + state.text + '</span>'
		  );
		  return $state;
	  };
  //编写自定义JS代码
	  function returnToVal(){
		if($("#type").val()=="0"){
			$("#typeVal").val("原料面料");
		}else if($("#type").val()=="1"){
			$("#typeVal").val("缝制辅料");
		}else if($("#type").val()=="2"){
			$("#typeVal").val("包装辅料");
		}
	  }

	  function toDecimal(x) {
		  if(!x){
			  return x;
		  }
		  var result = parseFloat(x);
		  if (isNaN(result)) {
			  return x;
		  }
		  result = Math.round(x * 100) / 100;
		  return result;
	  }

	  function setChengben(){
			if($("#yongliang").val() != "" && $("#sunhaoPrecent").val() != "" && $("#signPrice").val() != ""){
				$("#chengben").val(toDecimal(toDecimal(parseFloat($("#yongliang").val())*parseFloat($("#signPrice").val()))*(1+parseFloat($("#sunhaoPrecent").val()))));
			}
	  }


  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleDetailController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkSampleDetailPage.id }"/>
	  <input id="sampleId" name="sampleId" type="hidden" value="${param.sampleId }"  ignore="ignore" />

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							物料名称:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="proZnName" name="proZnName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="750px" height="500px"
								   icon="icon-search" title="选择物料" textname="id,proNum,proZnName,unit,type,precent,brand" isclear="true" isInit="true"></t:choose>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物料名称</label>
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
						  <input id="gysCode" name="gysCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						  <input id="gys" name="gys" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						  <span class="Validform_checktip"></span>
						  <label class="Validform_label" style="display: none;">供应商</label>
					  </td>
					  <td align="right">
						  <label class="Validform_label">
							  物料类型:
						  </label>
					  </td>
					  <td class="value">
						  <input id="type" name="type"  type="hidden"  />
						  <input id="typeVal" name="typeVal" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					  </td>
				  </tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物料编号:
						</label>
					</td>
					<td class="value">
						<input id="proNum" name="proNum" type="text" readonly style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">物料编号</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							规格:
						</label>
					</td>
					<td class="value" >
						<input id="brand" name="brand"  type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">规格</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
						<input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">单位</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							比例:
						</label>
					</td>
					<td class="value">
						<input id="precent" name="precent" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">比例</label>
					</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单件用量:
							</label>
						</td>
						<td class="value">
							<input id="yongliang" name="yongliang" onkeyup="setChengben()" datatype="d" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单件用量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								损耗率:
							</label>
						</td>
						<td class="value">
							<input id="sunhaoPrecent" name="sunhaoPrecent" datatype="d" readonly value=" <fmt:formatNumber type="number" value="${emkSampleEntity.qdshl+emkSampleEntity.hdshl}" maxFractionDigits="2"/>" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">损耗率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
							<input id="signPrice" name="signPrice" onkeyup="setChengben()"  datatype="d" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单价</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								成本:
							</label>
						</td>
						<td class="value">
							<input id="chengben" name="chengben" datatype="d" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成本</label>
						</td>
					</tr>
				  <tr>
					  <td align="right">
						  <label class="Validform_label">
							  位置:
						  </label>
					  </td>
					  <td class="value" colspan="${param.sampleType eq 'order' ? '0':'3'}">
						  <input id="position" name="position"  type="text" style="width: 150px" class="inputxt"  />
						  <span class="Validform_checktip"></span>
						  <label class="Validform_label" style="display: none;">位置</label>
					  </td>
					  <c:if test="${param.sampleType eq 'order'}">
						  <td align="right">
							  <label class="Validform_label">
								  入库状态:
							  </label>
						  </td>
						  <td class="value">
							  <select id="rkState" name="rkState">
								  <option value="0">未入库</option>
								  <option value="1">已入库</option>
								  <option value="2">已出库</option>
							  </select>
							  <span class="Validform_checktip"></span>
							  <label class="Validform_label" style="display: none;">入库状态</label>
						  </td>
					  </c:if>

				  </tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3">
						<textarea  id="remark" style="width:95%;height:50px" class="inputxt" rows="5" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/sampledetail/emkSampleDetail.js"></script>		
