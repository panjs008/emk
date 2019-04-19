<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkFinanceCheckList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkFinanceCheckController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="应收核准单号"  field="checkNum" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="付款项目类别"  field="payProjectType" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="出货通知单号"  field="outFourmNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订舱通知单号"  field="cargoNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="应付通知日期"  field="recevieDate"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkFinanceCheckController.do?goAdd&winTitle=录入应收核准单" funname="add" height="600" width="1350"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkFinanceCheckController.do?goUpdate&winTitle=编辑应收核准单" funname="update" height="600" width="1350"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkFinanceCheckController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/caiwu/financecheck/emkFinanceCheckList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkFinanceCheckController.do?upload', "emkFinanceCheckList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkFinanceCheckController.do?exportXls","emkFinanceCheckList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkFinanceCheckController.do?exportXlsByT","emkFinanceCheckList");
}

 </script>