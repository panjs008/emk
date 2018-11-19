<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkSampleDetailList" checkbox="true" pagination="true" fitColumns="false" title="" actionUrl="emkSampleDetailController.do?datagrid&sampleId=${param.sampleId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="物料ID"  field="pId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="物料编号"  field="proNum"  queryMode="single"  width="95"></t:dgCol>
    <t:dgCol title="物料名称"  field="proZnName"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="类型"  field="type"  replace="原料_0,缝制_1,包装_2"  queryMode="single"  width="45"></t:dgCol>
    <%--<t:dgCol title="比例"  field="precent"  queryMode="single"  width="45"></t:dgCol>--%>
    <t:dgCol title="用量"  field="yongliang"  queryMode="single"  width="50"></t:dgCol>
    <t:dgCol title="损耗率"  field="sunhaoPrecent"  queryMode="single"  width="60"></t:dgCol>
    <t:dgCol title="单位"  field="unit"  queryMode="single"  width="50"></t:dgCol>
    <t:dgCol title="单价"  field="signPrice"  queryMode="single"  width="50"></t:dgCol>
    <t:dgCol title="成本"  field="chengben"  queryMode="single"  width="60"></t:dgCol>
    <c:if test="${param.state eq '0'}">
        <t:dgToolBar title="录入" icon="fa fa-plus" url="emkSampleDetailController.do?goAdd&type=${param.type}&sampleId=${param.sampleId}&sampleType=${param.sampleType}&winTitle=录入样品主辅料" funname="add"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="fa fa-edit"  url="emkSampleDetailController.do?goUpdate&sampleType=${param.sampleType}" funname="update"></t:dgToolBar>
        <t:dgToolBar title="删除" icon="fa fa-remove" url="emkSampleDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    </c:if>

</t:datagrid>
 <script src = "webpage/com/emk/storage/sampledetail/emkSampleDetailList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });



//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSampleDetailController.do?upload', "emkSampleDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSampleDetailController.do?exportXls","emkSampleDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSampleDetailController.do?exportXlsByT","emkSampleDetailList");
}

 </script>