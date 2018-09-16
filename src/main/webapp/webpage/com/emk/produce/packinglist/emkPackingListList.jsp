<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkPackingListList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkPackingListController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="发票编号"  field="fpbh" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="发票日期"  field="fpDate"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="签名日期"  field="signDate"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总数量"  field="total"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总箱数"  field="sumBoxTotal"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总体积"  field="sumBoxVolume"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总净重"  field="sumBoxJz"  queryMode="single"  width="80"></t:dgCol>

      <t:dgToolBar title="录入" icon="fa fa-plus" url="emkPackingListController.do?goAdd&winTitle=录入装箱单" funname="add" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkPackingListController.do?goUpdate&winTitle=编辑装箱单" funname="update" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkPackingListController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/packinglist/emkPackingListList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkPackingListController.do?upload', "emkPackingListList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkPackingListController.do?exportXls","emkPackingListList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkPackingListController.do?exportXlsByT","emkPackingListList");
}

 </script>