<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订舱进度</title>
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
			$("#detailId").load("emkCargoSpaceController.do?orderMxList");

		});


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkFobBusinessController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkFobBusinessPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

		<tr>
			<td align="right">
				<label class="Validform_label">
					出货通知单号:
				</label>
			</td>
			<td class="value">
				<input id="outFourmNo" name="outFourmNo" type="text" value="${emkFobBusinessPage.outFourmNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					订舱通知单号:
				</label>
			</td>
			<td class="value">
				<input id="cargoNo" name="cargoNo" type="text" value="${emkFobBusinessPage.cargoNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱通知单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					离厂放行条号:
				</label>
			</td>
			<td class="value">
				<input id="levealFactoryNo" name="levealFactoryNo" type="text" value="${emkFobBusinessPage.levealFactoryNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离厂放行条号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkFobBusinessPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkFobBusinessPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkFobBusinessPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkFobBusinessPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
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
				<input id="businesser" name="businesser" readonly value="${emkFobBusinessPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkFobBusinessPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
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
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkFobBusinessPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden" value="${emkFobBusinessPage.tracerName }" />
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
			<td class="value">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkFobBusinessPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkFobBusinessPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					船务员:
				</label>
			</td>
			<td class="value">
				<input id="cwer" name="cwer" type="text" value="${emkFobBusinessPage.cwer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船务员</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value">
				<input id="htNum" name="htNum" type="text" value="${emkFobBusinessPage.htNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo"  value="${emkFobBusinessPage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<input id="bz" name="bz" type="text" value="${emkFobBusinessPage.bz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总金额:
				</label>
			</td>
			<td class="value">
				<input id="sumMoney" name="sumMoney" type="text" value="${emkFobBusinessPage.sumMoney }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkFobBusinessPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总箱数:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sumBox" name="sumBox" type="text" value="${emkFobBusinessPage.sumBox }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总箱数</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					颜色:
				</label>
			</td>
			<td class="value">
				<input id="color" name="color" type="text" value="${emkFobBusinessPage.color }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" value="${emkFobBusinessPage.total }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price" type="text" value="${emkFobBusinessPage.price }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
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
				<input id="gysCode" name="gysCode" value="${emkFobBusinessPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" value="${emkFobBusinessPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkFobBusinessPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkFobBusinessPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkFobBusinessPage.proTypeName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkFobBusinessPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>


		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkFobBusinessPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					订舱状态:
				</label>
			</td>
			<td class="value">
				<input id="cargoState" name="cargoState" type="text" value="${emkFobBusinessPage.cargoState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					出厂状态:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="levealState" name="levealState" type="text" value="${emkFobBusinessPage.levealState }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订舱状态</label>
			</td>
		</tr>


	</table>
	<!-- 添加 产品明细 模版-->
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