<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkStorageLogList" checkbox="false" pagination="true" fitColumns="true" title="库存日记表" actionUrl="emkStorageLogController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品ID"  field="proId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品编号"  field="proNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中文描述"  field="proZnName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入库前数量"  field="preTotal"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入库后数量"  field="nextTotal"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入库数量"  field="total"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品类型"  field="proTypeName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入库单号"  field="rkNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" operationCode="delete" url="emkStorageLogController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkStorageLogController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkStorageLogController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete" operationCode="delete"  icon="fa fa-remove" url="emkStorageLogController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkStorageLogController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/storagelog/emkStorageLogList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkStorageLogController.do?upload', "emkStorageLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkStorageLogController.do?exportXls","emkStorageLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkStorageLogController.do?exportXlsByT","emkStorageLogList");
}

 </script>