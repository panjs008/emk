<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkFinanceYfCheckList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkFinanceYfCheckController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="应付核准单号"  field="yfCheckNo" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="付款项目类别"  field="fkxmlb" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="出货通知单号"  field="outFormnNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="应付通知日期"  field="yftzDate"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkFinanceYfCheckController.do?goAdd&winTitle=录入应付核准单" funname="add" height="600" width="1350"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkFinanceYfCheckController.do?goUpdate&winTitle=编辑应付核准单" funname="update" height="600" width="1350"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkFinanceYfCheckController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/caiwu/yfcheck/emkFinanceYfCheckList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkFinanceYfCheckController.do?upload', "emkFinanceYfCheckList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkFinanceYfCheckController.do?exportXls","emkFinanceYfCheckList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkFinanceYfCheckController.do?exportXlsByT","emkFinanceYfCheckList");
}

 </script>