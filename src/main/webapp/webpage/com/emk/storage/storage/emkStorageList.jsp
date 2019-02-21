<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkStorageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkStorageController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <%--<t:dgCol title="仓库"  field="storageName" query="true" queryMode="single"  width="120"></t:dgCol>--%>
      <%--<t:dgCol title="库位"  field="positionName"  queryMode="single"  width="90"></t:dgCol>--%>
      <t:dgCol title="面料类别"  field="remark" query="true" replace="原料面料_0,缝制辅料_1,包装辅料_2" queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="类别"  field="proTypeName" query="false"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="面料名称"  field="proZnName"  query="true" queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="面料代码"  field="proNum" query="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="规格型号"  field="brand" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="total" formatterjs="formatTotal" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位"  field="unit"  queryMode="single"  width="120"></t:dgCol>
      <%-- <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkStorageController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkStorageController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete" operationCode="delete"  icon="fa fa-remove" url="emkStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkStorageController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/storage/emkStorageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function formatTotal(val,row,index){
     var s = '';
     if(row.brand=="合计"){
         s = '<strong><font color="red">'+row.total+'</font></strong>';
     }else{
         s = row.total;

     }
     return s;
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkStorageController.do?upload', "emkStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkStorageController.do?exportXls","emkStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkStorageController.do?exportXlsByT","emkStorageList");
}

 </script>