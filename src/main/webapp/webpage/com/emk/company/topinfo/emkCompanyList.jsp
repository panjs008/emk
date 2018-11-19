<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkCompanyList" checkbox="true"  pagination="true" fitColumns="false" title="" actionUrl="emkCompanyController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中文公司"  field="znName"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="外文公司"  field="enName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中文地址"  field="znAddress"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外文地址"  field="enAddress"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="办公电话"  field="workTel"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="传真"  field="fax"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="统一社会信用代码"  field="wCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="海关认证10代码"  field="hCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="默认"  field="isChecked"  queryMode="single"  width="60"></t:dgCol>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkCompanyController.do?goAdd" funname="add" width="800" height="450"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkCompanyController.do?goUpdate" funname="update" width="800" height="450"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkCompanyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" url="emkCompanyController.do?goUpdate" funname="detail" width="800" height="450"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/company/topinfo/emkCompanyList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkCompanyController.do?upload', "emkCompanyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkCompanyController.do?exportXls","emkCompanyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkCompanyController.do?exportXlsByT","emkCompanyList");
}

 </script>