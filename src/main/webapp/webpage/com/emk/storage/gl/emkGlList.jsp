<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkGlList" checkbox="false" pagination="true" fitColumns="true" title="管理" actionUrl="emkGlController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="场地"  field="place"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输费"  field="tranCost"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="设备"  field="equip"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="管理费"  field="glf"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电费"  field="dianfei"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车辆使用费"  field="carCost"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="水费"  field="waterCost"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="其他"  field="other"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="报价单ID"  field="priceId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="emkGlController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkGlController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkGlController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="emkGlController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkGlController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/gl/emkGlList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkGlController.do?upload', "emkGlList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkGlController.do?exportXls","emkGlList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkGlController.do?exportXlsByT","emkGlList");
}

 </script>