<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkMaterialDetailList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkMaterialDetailController.do?datagrid&materialId=${param.materialId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="面料编号"  field="proNum"  queryMode="single"  width="80"></t:dgCol>
    <t:dgCol title="原料面料名称"  field="proZnName"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="供应商"  field="gys"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="数量"  field="signTotal"  queryMode="single"  width="40"></t:dgCol>
    <t:dgCol title="单位"  field="unit"  queryMode="single"  width="40"></t:dgCol>
    <t:dgCol title="捻向"  field="direction"  queryMode="single"  width="60"></t:dgCol>
    <t:dgCol title="批号"  field="betchNum"  queryMode="single"  width="60"></t:dgCol>
    <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkMaterialDetailController.do?goAdd&materialId=${param.materialId}" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkMaterialDetailController.do?goUpdate" funname="update"></t:dgToolBar>
    <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkMaterialDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
</t:datagrid>
 <script src = "webpage/com/emk/storage/materialdetail/emkMaterialDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMaterialDetailController.do?upload', "emkMaterialDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMaterialDetailController.do?exportXls","emkMaterialDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMaterialDetailController.do?exportXlsByT","emkMaterialDetailList");
}

 </script>