<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkPassFactoryList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkPassFactoryController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="进度更新日期"  field="cargoDate"  formatter="yyyy-MM-dd"    queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="更新操作员"  field="createName"    queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkPassFactoryController.do?goAdd&winTitle=录入离厂放行单" funname="add" height="600" width="1200"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkPassFactoryController.do?goUpdate&winTitle=编辑离厂放行单" funname="update" height="600" width="1200"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkPassFactoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/outforum/passfactory/emkPassFactoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkPassFactoryController.do?upload', "emkPassFactoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkPassFactoryController.do?exportXls","emkPassFactoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkPassFactoryController.do?exportXlsByT","emkPassFactoryList");
}

 </script>