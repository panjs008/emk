<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkOfferPriceList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkOfferPriceController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="开单日期"  field="kdTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="款式"  field="proTypeName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="图片"  field="customSampleUrl" image="true" imageSize="30,30" queryMode="single"  width="120"></t:dgCol>
      <%--<t:dgCol title="款式"  field="proType"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="加工费"  field="workPrice"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="厂皮损耗费"  field="lossPrice"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="商检运费"  field="freightPrice"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="税费"  field="tax"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="80"></t:dgCol>
   <%--<t:dgCol title="图片"  field="customSample"  queryMode="single"  width="120"></t:dgCol>--%>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOfferPriceController.do?goAdd" funname="add" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOfferPriceController.do?goUpdate" funname="update" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkOfferPriceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/offerprice/emkOfferPriceList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOfferPriceController.do?upload', "emkOfferPriceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOfferPriceController.do?exportXls","emkOfferPriceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOfferPriceController.do?exportXlsByT","emkOfferPriceList");
}

 </script>