<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkShipList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkShipController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="进度更新日期"  field="kdDate"  formatter="yyyy-MM-dd"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="更新操作员"  field="createBy"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkShipController.do?goAdd&winTitle=录入客用船务文件" funname="add" height="600" width="1200"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkShipController.do?goUpdate&winTitle=编辑客用船务文件" funname="update" height="600" width="1200"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkShipController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/outforum/ship/emkShipList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkShipController.do?upload', "emkShipList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkShipController.do?exportXls","emkShipList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkShipController.do?exportXlsByT","emkShipList");
}

 </script>