<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkEnquiryDetailList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkEnquiryDetailController.do?datagrid&enquiryId=${param.enquiryId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="颜色名称"  field="color"  dictionary="t_s_category,code,name"  queryMode="single"  width="80"></t:dgCol>
    <t:dgCol title="色号"  field="colorValue" dictionary="t_s_category,code,name"   queryMode="single"  width="60"></t:dgCol>
    <t:dgCol title="尺码"  field="size" dictionary="size"  queryMode="single"  width="55"></t:dgCol>
    <t:dgCol title="数量"  field="total"  queryMode="single"  width="70"></t:dgCol>
    <t:dgCol title="单码总数"  field="sizeTotal"  queryMode="single"  width="78"></t:dgCol>
    <t:dgCol title="单色总数"  field="colorTotal"  queryMode="single"  width="78"></t:dgCol>
    <t:dgCol title="单价"  field="price"  queryMode="single"  width="70"></t:dgCol>
    <c:if test="${param.state eq '0'}">
        <t:dgToolBar title="录入" icon="fa fa-plus"  url="emkEnquiryDetailController.do?goAdd&enquiryId=${param.enquiryId}" funname="add"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkEnquiryDetailController.do?goUpdate" funname="update"></t:dgToolBar>
        <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkEnquiryDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    </c:if>

</t:datagrid>
 <script src = "webpage/com/emk/storage/enquirydetail/emkEnquiryDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkEnquiryDetailController.do?upload', "emkEnquiryDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkEnquiryDetailController.do?exportXls","emkEnquiryDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkEnquiryDetailController.do?exportXlsByT","emkEnquiryDetailList");
}

 </script>