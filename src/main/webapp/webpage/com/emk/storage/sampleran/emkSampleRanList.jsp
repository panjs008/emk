<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkSampleRanList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkSampleRanController.do?datagrid&sampleId=${param.sampleId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="布面种类"  field="buType" dictionary="bmzl"  queryMode="single"  width="150"></t:dgCol>
    <t:dgCol title="染色单价"  field="price"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="单位"  field="unit" dictionary="units"  queryMode="single"  width="70"></t:dgCol>
    <t:dgCol title="单件克重"  field="oneWeight"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="成本"  field="chengben"  queryMode="single"  width="120"></t:dgCol>
    <c:if test="${param.state eq '0'}">
        <t:dgToolBar title="录入" icon="fa fa-plus" url="emkSampleRanController.do?goAdd&type=${param.type}&sampleId=${param.sampleId}" funname="add"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkSampleRanController.do?goUpdate" funname="update"></t:dgToolBar>
        <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkSampleRanController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    </c:if>


</t:datagrid>
 <script src = "webpage/com/emk/storage/sampleran/emkSampleRanList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSampleRanController.do?upload', "emkSampleRanList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSampleRanController.do?exportXls","emkSampleRanList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSampleRanController.do?exportXlsByT","emkSampleRanList");
}

 </script>