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
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务跟单员"  field="tracerName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="生产跟单员"  field="developerName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="船务员"  field="cwer"  queryMode="single"  width="90"></t:dgCol>

      <t:dgToolBar title="录入" icon="fa fa-plus"  url="emkFobBusinessController.do?goAdd&winTitle=录入订舱进度" funname="add" height="620" width="1200"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit"  url="emkFobBusinessController.do?goUpdate&winTitle=编辑订舱进度" funname="update" height="620" width="1200"></t:dgToolBar>
      <t:dgToolBar title="删除"   icon="fa fa-remove" url="emkFobBusinessController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出"  icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

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