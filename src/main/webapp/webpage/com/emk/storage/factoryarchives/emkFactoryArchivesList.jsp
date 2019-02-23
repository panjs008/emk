<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkFactoryArchivesList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkFactoryArchivesController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="供应商档案编号" query="true" field="archivesNo" frozenColumn="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="工厂名称"  query="true" field="companyNameZn"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="工厂代码"  query="true" field="companyCode"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="地址"  field="addressZn"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="电话"  field="primaryContactTel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"  field="shengFen" dictionary="t_s_category,code,name"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="城市"  field="chengShi" dictionary="t_s_category,code,name"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="国家"  field="guoJia" dictionary="trade"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="联系人"  field="primaryContact"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品类型"  dictionary="emk_product_type,id,content" field="proType"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="产业类型" formatterjs="bhFormat" field="productClassification"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="营业执照号"  field="licenseNumber"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="有效期"  field="permitExpirationDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="风险等级"  field="level" replace="放心做单_2,能做单有风险_1,不能做单_0"  queryMode="single"  width="100"></t:dgCol>

   <t:dgToolBar title="录入" icon="fa fa-plus" url="emkFactoryArchivesController.do?goAdd&winTitle=录入供应商档案" funname="add" width="1100" height="580"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkFactoryArchivesController.do?goUpdate&winTitle=编辑供应商档案" funname="update" width="1100" height="580"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkFactoryArchivesController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkFactoryArchivesController.do?goUpdate" funname="detail" width="1100" height="580"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/factoryarchives/emkFactoryArchivesList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function bhFormat(value, row, index) {
  var text = "";
  if (row.productClassification.indexOf('[0]') >= 0) {
   text += "Home communications, entertainment & hobby equipment,";
  }
  if (row.productClassification.indexOf('[1]') >= 0) {
   text += "Sports and Outdoor Recreation Equipment,";
  }
  if (row.productClassification.indexOf('[2]') >= 0) {
   text += "Furniture and Furnishings,";
  }
  if (row.productClassification.indexOf('[3]') >= 0) {
   text += "Packaging and Containers,";
  }
  if (row.productClassification.indexOf('[4]') >= 0) {
   text += "House appliance,";
  }
  if (row.productClassification.indexOf('[5]') >= 0) {
   text += "Toys,";
  }
  if (row.productClassification.indexOf('[6]') >= 0) {
   text += "Housewares (non-powered),";
  }
  if (row.productClassification.indexOf('[7]') >= 0) {
   text += "Personal care items,";
  }
  if (row.productClassification.indexOf('[8]') >= 0) {
   text += "Chemicals & chemical products,";
  }
  if (row.productClassification.indexOf('[9]') >= 0) {
   text += "Auto Part & Accessory,";
  }
  if (row.productClassification.indexOf('[10]') >= 0) {
   text += "Home workshop apparatus tools,";
  }
  return text;
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkFactoryArchivesController.do?upload', "emkFactoryArchivesList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkFactoryArchivesController.do?exportXls","emkFactoryArchivesList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkFactoryArchivesController.do?exportXlsByT","emkFactoryArchivesList");
}

 </script>