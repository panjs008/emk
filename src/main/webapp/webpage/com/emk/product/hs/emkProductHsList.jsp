<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkProductHsList" checkbox="true"  pagination="true" fitColumns="false" title="" actionUrl="emkProductHsController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="海关编码"  field="hsCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="海关名称"  field="hsName"  queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="报关要素"  field="bgys"  queryMode="single"  width="300"></t:dgCol>
       <t:dgCol title="增值税率(%)"  field="zzVal"  queryMode="single"  width="80"></t:dgCol>
       <t:dgCol title="退税率(%)"  field="tsVal"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="销售指导价"  field="salePrice"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkProductHsController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkProductHsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkProductHsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" url="emkProductHsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/product/hs/emkProductHsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProductHsController.do?upload', "emkProductHsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProductHsController.do?exportXls","emkProductHsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProductHsController.do?exportXlsByT","emkProductHsList");
}

 </script>