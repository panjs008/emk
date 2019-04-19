<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>生产订单</title>
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
  $(document).ready(function(){
	  $("#detailId").load("emkProOrderController.do?orderMxList");
  });
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkProOrderPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							订单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderNo" name="orderNo" type="text" readonly value="${orderNo}" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							开单时间:
						</label>
					</td>
					<td class="value">
						<input id="orderTime" name="orderTime" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" class="Wdate"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">开单时间</label>
					</td>
				</tr>

				<tr>

					<td align="right">
						<label class="Validform_label">
							客户名称:
						</label>
					</td>
					<td class="value">
						<input id="cusNum" name="cusNum" type="hidden" value="${emkSamplePage.cusNum }" class="inputxt"  ignore="ignore" />
						<input id="cusName" name="cusName" type="text" value="${emkSamplePage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
					<%--	<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
								   icon="icon-search" title="选择客户" textname="cusName" isclear="true" isInit="true"></t:choose>--%>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">客户名称</label>
					</td>

					<td align="right">
						<label class="Validform_label">
							运费:
						</label>
					</td>
					<td class="value">
						<input id="yunfei" name="yunfei" datatype="*" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">运费</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							交货时间:
						</label>
					</td>
					<td class="value">
						<input id="supplyTime" name="supplyTime" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" class="Wdate"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">交货时间</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">备注</label>
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
						  <input type="hidden" id="proId00" name="orderMxList[#index#].proId" />
						  <input type="hidden" id="proNum00" name="orderMxList[#index#].proNum" /></td>
					  <td align="left"><input nullmsg="请输入款号！" id="sampleNo00"   errormsg="请输入款号" name="orderMxList[#index#].sampleNo" maxlength="100" type="text" value=""
											  style="width: 100px;"></td>
					  <td align="left">
						  <input nullmsg="请输入商号名称！" id="sampleName00"  errormsg="请输入商品名称" name="orderMxList[#index#].proName" maxlength="100" type="text" value=""
								 style="width: 120px;">
					  </td>
					  <td align="left">
						  <input nullmsg="请输入颜色！" id="proName00"  errormsg="请输入颜色" name="orderMxList[#index#].color" maxlength="100" type="text" value=""
								 style="width: 120px;">
					  </td>
					  <td align="left"><input id="brand00" nullmsg="请输入尺码！"  errormsg="请输入尺码" name="orderMxList[#index#].brand" maxlength="100" type="text" value=""
											  style="width: 120px;"></td>
					  <td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入整数" name="orderMxList[#index#].signTotal" maxlength="100" type="text" value=""
											  style="width: 120px;"></td>
					  <td align="left">
						  <input nullmsg="请输入单位！" id="signUnit00" errormsg="请输入单位" name="orderMxList[#index#].signUnit" maxlength="100" type="text" value=""
								 style="width: 60px;"></td>
					  <td align="left"><input nullmsg="请输入单价！" id="signPrice00" errormsg="请输入单价" name="orderMxList[#index#].signPrice" maxlength="100" type="text" value=""
											  style="width: 100px;"></td>
					  <td align="left"><input nullmsg="请输入备注！" id="remark00"  errormsg="请输入备注" name="orderMxList[#index#].remark" maxlength="100" type="text" value=""
											  style="width: 100px;"></td>
				  </tr>
				  </tbody>

			  </table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/proorder/emkProOrder.js"></script>		
