<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkProOrderBarcodeList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkProOrderBarcodeController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="类型"  dictionary="barcode" field="type"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="颜色"  field="color"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="尺码"  field="size"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="条码"  field="code"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="数量"  field="total"  queryMode="single"  width="120"></t:dgCol>
    <c:if test="${param.state eq '0'}">
        <t:dgToolBar title="录入" icon="fa fa-plus"  url="emkProOrderBarcodeController.do?goAdd&orderId=${param.orderId}" funname="add"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkProOrderBarcodeController.do?goUpdate" funname="update"></t:dgToolBar>
        <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkProOrderBarcodeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    </c:if>

</t:datagrid>
 <script src = "webpage/com/emk/bill/proorderbarcode/emkProOrderBarcodeList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });



//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProOrderBarcodeController.do?upload', "emkProOrderBarcodeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProOrderBarcodeController.do?exportXls","emkProOrderBarcodeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProOrderBarcodeController.do?exportXlsByT","emkProOrderBarcodeList");
}

 </script>