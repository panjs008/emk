<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkFobBusinessList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkFobBusinessController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="出货通知单号"  field="outFourmNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订舱通知单号"  field="cargoNo" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="生产合同号"  field="htNum" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="离厂放行条号"  field="levealFactoryNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总箱数"  field="sumBox"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总金额"  field="sumMoney"  queryMode="single"  width="80"></t:dgCol>
      <t:dgToolBar title="录入" icon="fa fa-plus" url="emkFobBusinessController.do?goAdd&winTitle=录入订舱进度" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkFobBusinessController.do?goUpdate&winTitle=编辑订舱进度" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkFobBusinessController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/outforum/fobbusiness/emkFobBusinessList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkFobBusinessController.do?upload', "emkFobBusinessList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkFobBusinessController.do?exportXls","emkFobBusinessList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkFobBusinessController.do?exportXlsByT","emkFobBusinessList");
}

 </script>