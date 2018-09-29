<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkFinanceReceivableList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkFinanceReceivableController.do?datagrid&type=${param.type}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="应收通知单号"  field="receiveNum" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="订舱通知单号"  field="cargoNo" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="离厂放行条号"  field="levealFactoryNo" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkFinanceReceivableController.do?goAdd&winTitle=录入应收通知单" funname="add" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkFinanceReceivableController.do?goUpdate&winTitle=编辑应收通知单" funname="update" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkFinanceReceivableController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/caiwu/financereceivable/emkFinanceReceivableList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkFinanceReceivableController.do?upload', "emkFinanceReceivableList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkFinanceReceivableController.do?exportXls","emkFinanceReceivableList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkFinanceReceivableController.do?exportXlsByT","emkFinanceReceivableList");
}

 </script>