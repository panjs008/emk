<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMOutStorageList" checkbox="false" pagination="true" fitColumns="true" title="出库申请表" actionUrl="emkMOutStorageController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务员ID"  field="businesserName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员ID"  field="tracerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门ID"  field="businesseDeptId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="developer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员ID"  field="developerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出货日期"  field="outDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请人"  field="appler"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请人ID"  field="applerId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="领料人"  field="ller"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="领料ID"  field="llerId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购合同号"  field="htNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预采购合同号"  field="yhtNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="gys"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商代码"  field="gysCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="gys"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商代码"  field="gysCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主键"  field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户编号"  field="cusNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出库日期"  field="kdDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="emkMOutStorageController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkMOutStorageController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkMOutStorageController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="emkMOutStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkMOutStorageController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bound/moutstorage/emkMOutStorageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMOutStorageController.do?upload', "emkMOutStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMOutStorageController.do?exportXls","emkMOutStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMOutStorageController.do?exportXlsByT","emkMOutStorageList");
}

 </script>