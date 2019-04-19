<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>原料布料打样付款申请单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <%@include file="/context/header2.jsp"%>
	 <script src="${webRoot}/context/gys.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkDyPayController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkDyPayPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						客户名称:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="cusName" name="cusName" readonly type="text" value="${emkDyPayPage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
					<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
							   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">客户名称</label>
				</td>
			</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="cusNum" name="cusNum" value="${emkDyPayPage.cusNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							原料布料需求单号:
						</label>
					</td>
					<td class="value">
						<input id="xqdNo" name="xqdNo" value="${emkDyPayPage.xqdNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">原料布料需求单号</label>
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
						<input id="gysCode" name="gysCode" value="${emkDyPayPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="gys" name="gys" value="${emkDyPayPage.gys }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">供应商</label>
					</td>

					<td align="right">
						<label class="Validform_label">
							原料布料采购单号:
						</label>
					</td>
					<td class="value">
						<input id="cgdNo" name="cgdNo" value="${emkDyPayPage.cgdNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">原料布料采购单号</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							提交日期:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="kdDate" name="kdDate" readonly value="${kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提交日期</label>
						</td>
				</tr>
			</table>
		  <t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
			  <t:tab href="emkDyPayController.do?detailMxList&dyPayId=${emkDyPayPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
		  </t:tabs>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/caiwu/dypay/emkDyPay.js"></script>		
