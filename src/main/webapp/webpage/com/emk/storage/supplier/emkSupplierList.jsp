<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkSupplierList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkSupplierController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="企业全称"  field="supplier" query="true"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="供应商代码"  field="supplierCode" query="true"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="供应商类型"  field="supplierType"  dictionary="gyslx" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="营业执照号"  field="licence"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="有效期"  field="limitDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="地址"  field="address"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="产品类型"  field="productType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="纳税人识别号"  field="taxpayerNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户行"  field="bankName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户账号 "  field="bankAccount"  queryMode="single"  width="150"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkSupplierController.do?goAdd" funname="add" height="500" width="900"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkSupplierController.do?goUpdate" funname="update" height="500" width="900"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkSupplierController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/supplier/emkSupplierList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSupplierController.do?upload', "emkSupplierList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSupplierController.do?exportXls","emkSupplierList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSupplierController.do?exportXlsByT","emkSupplierList");
}

 </script>