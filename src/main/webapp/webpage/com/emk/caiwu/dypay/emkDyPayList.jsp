<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkDyPayList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkDyPayController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商代码"  field="gysCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="gys"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="原料布料需求单号"  field="xqdNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="原料布料采购单号"  field="cgdNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="120"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkDyPayController.do?goAdd" funname="add" height="600" width="1200"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkDyPayController.do?goUpdate" funname="update" height="600" width="1200"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkDyPayController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkDyPayController.do?goUpdate" height="600" width="1200"  funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/caiwu/dypay/emkDyPayList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkDyPayController.do?upload', "emkDyPayList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkDyPayController.do?exportXls","emkDyPayList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkDyPayController.do?exportXlsByT","emkDyPayList");
}

 </script>