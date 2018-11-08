<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>装箱单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header.jsp"%>
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
			$("#detailId").load("emkPackingListController.do?orderMxList");

		});



	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPackingListController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkPackingListPage.id }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					发票编号:
				</label>
			</td>
			<td class="value">
				<input id="fpbh" name="fpbh" type="text" value="${emkPackingListPage.fpbh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value">
				<input id="orderNo" name="orderNo"  value="${emkPackingListPage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkPackingListPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户编号:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkPackingListPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value"  colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkPackingListPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					发票日期:
				</label>
			</td>
			<td class="value">
				<input id="fpDate" name="fpDate" readonly value="${emkPackingListPage.fpDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					法定代表签名:
				</label>
			</td>
			<td class="value">
				<input id="signer" name="signer" type="text" value="${emkPackingListPage.signer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">法定代表签名</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					签名日期:
				</label>
			</td>
			<td class="value">
				<input id="signDate" name="signDate" type="text" value="${emkPackingListPage.signDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签名日期</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sampleNoDesc" style="width:95%;height:50px" class="inputxt" rows="3" name="sampleNoDesc">${emkPackingListPage.sampleNoDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>

		<tr>

			<td align="right">
				<label class="Validform_label">
					长:
				</label>
			</td>
			<td class="value">
				<input id="changdu" name="changdu" type="text" value="${emkPackingListPage.changdu }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">长</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					宽:
				</label>
			</td>
			<td class="value">
				<input id="kuandu" name="kuandu" type="text" value="${emkPackingListPage.kuandu }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">宽</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					高:
				</label>
			</td>
			<td class="value">
				<input id="gaodu" name="gaodu" type="text" value="${emkPackingListPage.gaodu }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">高</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					单箱体积:
				</label>
			</td>
			<td class="value">
				<input id="oneBoxVolume" name="oneBoxVolume" type="text" value="${emkPackingListPage.oneBoxVolume }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单箱体积</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单箱毛重:
				</label>
			</td>
			<td class="value">
				<input id="oneBoxMz" name="oneBoxMz" type="text" value="${emkPackingListPage.oneBoxMz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单箱毛重</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					单箱净重:
				</label>
			</td>
			<td class="value">
				<input id="oneBoxJz" name="oneBoxJz" type="text" value="${emkPackingListPage.oneBoxJz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单箱净重</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总体积:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxVolume" name="sumBoxVolume" type="text" value="${emkPackingListPage.sumBoxVolume }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总体积</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					总净重:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxJz" name="sumBoxJz" type="text" value="${emkPackingListPage.sumBoxJz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总净重</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					总毛重:
				</label>
			</td>
			<td class="value">
				<input id="sumBoxMao" name="sumBoxMao" type="text" value="${emkPackingListPage.sumBoxMao }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总毛重</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" value="${emkPackingListPage.total }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					生产方名称:
				</label>
			</td>
			<td class="value">
				<input id="scfmc" name="scfmc" type="text" value="${emkPackingListPage.scfmc }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产方名称</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" type="text" value="${emkPackingListPage.address }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">地址</label>
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
				<select name="orderMxList[#index#].color" style="width: 80%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
					<c:forEach items="${categoryEntityList}" var="category">
						<option value="${category.code}">${category.name}</option>
					</c:forEach>
				</select>
			</td>
			<td align="left">
				<input id="size00" nullmsg="请输入尺码！"  errormsg="请输入尺码" name="orderMxList[#index#].size" maxlength="100" type="text" value=""
					   style="width: 80%;"></td>
			<td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].signTotal" maxlength="100" type="text" value=""
									style="width: 80%;"></td>
		</tr>
		</tbody>

	</table>
</t:formvalid>
</body>
