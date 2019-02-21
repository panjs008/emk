<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkCustomUseList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkCustomUseController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="130"></t:dgCol>
      <t:dgCol title="子客户名称" field="subCusName"  queryMode="single"  width="130"></t:dgCol>

   <t:dgCol title="客户手册编号"  field="kyscbh"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>--%>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="生产跟单员"  field="developerName"  queryMode="single"  width="80"></t:dgCol>

      <%--
            <t:dgCol title="验厂员"  field="checkUser"  queryMode="single"  width="70"></t:dgCol>
         <t:dgCol title="指定货代名称"  field="zdhdmc"  queryMode="single"  width="120"></t:dgCol>
         <t:dgCol title="联系人"  field="relation"  queryMode="single"  width="70"></t:dgCol>
         <t:dgCol title="电话"  field="telphone"  queryMode="single"  width="90"></t:dgCol>
         <t:dgCol title="起运港"  field="lyg"  queryMode="single"  width="90"></t:dgCol>--%>

       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add"  url="emkCustomUseController.do?goAdd&winTitle=录入客户手册" funname="add" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit"  url="emkCustomUseController.do?goUpdate&winTitle=编辑客户手册" funname="update" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove"  url="emkCustomUseController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search"  operationCode="look" url="emkCustomUseController.do?goUpdate&winTitle=查看客户手册" funname="detail" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/customuse/emkCustomUseList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkCustomUseController.do?upload', "emkCustomUseList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkCustomUseController.do?exportXls","emkCustomUseList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkCustomUseController.do?exportXlsByT","emkCustomUseList");
}

 </script>