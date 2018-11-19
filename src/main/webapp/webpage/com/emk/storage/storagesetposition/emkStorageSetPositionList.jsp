<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkStorageSetPositionList" checkbox="true" pagination="true" fitColumns="true" title="库位表" actionUrl="emkStorageSetPositionController.do?datagrid&storageId=${param.storageId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="60"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="仓库ID"  field="storageId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="位置代码"  field="code"  queryMode="single"  width="70"></t:dgCol>
    <t:dgCol title="位置名称"  field="positionName"  queryMode="single"  width="150"></t:dgCol>
    <t:dgCol title="备注"  field="remark" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkStorageSetPositionController.do?goAdd&storageId=${param.storageId}" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkStorageSetPositionController.do?goUpdate" funname="update"></t:dgToolBar>
    <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkStorageSetPositionController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
</t:datagrid>
 <script src = "webpage/com/emk/storage/storagesetposition/emkStorageSetPositionList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkStorageSetPositionController.do?upload', "emkStorageSetPositionList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkStorageSetPositionController.do?exportXls","emkStorageSetPositionList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkStorageSetPositionController.do?exportXlsByT","emkStorageSetPositionList");
}

 </script>