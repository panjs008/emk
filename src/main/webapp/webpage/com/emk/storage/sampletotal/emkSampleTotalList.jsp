<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkSampleTotalList" checkbox="true" pagination="true" fitColumns="false" title="" actionUrl="emkSampleTotalController.do?datagrid&sampleId=${param.sampleId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="颜色"  field="color"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="尺码"  field="size"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="备注"  field="total"  queryMode="single"  width="100"></t:dgCol>
    <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkSampleTotalController.do?goAdd&sampleId=${param.sampleId}" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkSampleTotalController.do?goUpdate" funname="update"></t:dgToolBar>
    <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkSampleTotalController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
    <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
</t:datagrid>
 <script src = "webpage/com/emk/storage/sampletotal/emkSampleTotalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSampleTotalController.do?upload', "emkSampleTotalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSampleTotalController.do?exportXls","emkSampleTotalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSampleTotalController.do?exportXlsByT","emkSampleTotalList");
}

 </script>