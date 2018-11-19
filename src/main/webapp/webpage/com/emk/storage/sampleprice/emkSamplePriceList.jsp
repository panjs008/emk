<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkSamplePriceList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkSamplePriceController.do?datagrid&enquiryId=${param.enquiryId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="金额"  field="money"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="币种"  field="bz"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="状态"  field="state"  replace="未出账_0,已出账_1" queryMode="single"  width="80"></t:dgCol>
    <t:dgCol title="备注"  field="remark"  queryMode="single"  width="90"></t:dgCol>
    <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkSamplePriceController.do?goAdd&enquiryId=${param.enquiryId}" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkSamplePriceController.do?goUpdate" funname="update"></t:dgToolBar>
    <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkSamplePriceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>

</t:datagrid>
 <script src = "webpage/com/emk/storage/sampleprice/emkSamplePriceList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSamplePriceController.do?upload', "emkSamplePriceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSamplePriceController.do?exportXls","emkSamplePriceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSamplePriceController.do?exportXlsByT","emkSamplePriceList");
}

 </script>