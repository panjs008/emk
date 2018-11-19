<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkProductTypeList" checkbox="true" treegrid="true" pagination="true" fitColumns="false" title="" actionUrl="emkProductTypeController.do?treegrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
          <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
       <t:dgCol title="操作" hidden="true" field="opt" width="210"></t:dgCol>
       <t:dgCol title="主键"  field="id"  hidden="true" treefield="id"  queryMode="single"  width="120"></t:dgCol>
       <t:dgCol title="类别名称"  field="content"  treefield="text"  queryMode="single"  width="250"></t:dgCol>
          <t:dgCol title="类别代码"  field="proCode" treefield="fieldMap.proCode" queryMode="single"  width="200"></t:dgCol>
          <t:dgCol title="备注"  field="remark" treefield="fieldMap.remark" queryMode="single"  width="200"></t:dgCol>
          <t:dgDelOpt title="删除" operationCode="delete" url="emkProductTypeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkProductTypeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkProductTypeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkProductTypeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" url="emkProductTypeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
   <%--<t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/product/producttype/emkProductTypeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function addOffice(title,url, id) {
     var rowData = $('#emkProductTypeList').datagrid('getSelected');
     if (rowData) {
         url += '&productTypeEntity.id='+rowData.id;
     }
     add(title,url,'emkProductTypeList',720,500);
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProductTypeController.do?upload', "emkProductTypeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProductTypeController.do?exportXls","emkProductTypeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProductTypeController.do?exportXlsByT","emkProductTypeList");
}

 </script>