<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkInStorageDetailList" checkbox="false" pagination="true" fitColumns="true" title="入库表明细表" actionUrl="emkInStorageDetailController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入库单ID"  field="inStorageId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品ID"  field="proId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料编号"  field="proNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格"  field="brand"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="signTotal"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位"  field="signUnit"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="signPrice"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料名称"  field="proName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="仓库ID"  field="storageSetId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="仓库名称"  field="storageName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="库位ID"  field="positionId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="库位名称"  field="positionName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号名称"  field="sampleName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" operationCode="delete" url="emkInStorageDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkInStorageDetailController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkInStorageDetailController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete" operationCode="delete"  icon="fa fa-remove" url="emkInStorageDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkInStorageDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/instoragedetail/emkInStorageDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkInStorageDetailController.do?upload', "emkInStorageDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkInStorageDetailController.do?exportXls","emkInStorageDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkInStorageDetailController.do?exportXlsByT","emkInStorageDetailList");
}

 </script>