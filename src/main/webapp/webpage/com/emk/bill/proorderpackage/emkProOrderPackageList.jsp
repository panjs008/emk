<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkProOrderPackageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkProOrderPackageController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="颜色"  field="color"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="类型" dictionary="package" field="packageType"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="尺码"  field="size"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="箱内数量"  field="inboxTotal"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="箱数"  field="boxTotal"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="总数量"  field="sumTotal"  queryMode="single"  width="120"></t:dgCol>
    <c:if test="${param.state eq '0'}">
        <t:dgToolBar title="录入" icon="fa fa-plus" url="emkProOrderPackageController.do?goAdd&orderId=${param.orderId}" funname="add"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkProOrderPackageController.do?goUpdate" funname="update"></t:dgToolBar>
        <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkProOrderPackageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
            </c:if>

</t:datagrid>
 <script src = "webpage/com/emk/bill/proorderpackage/emkProOrderPackageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProOrderPackageController.do?upload', "emkProOrderPackageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProOrderPackageController.do?exportXls","emkProOrderPackageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProOrderPackageController.do?exportXlsByT","emkProOrderPackageList");
}

 </script>