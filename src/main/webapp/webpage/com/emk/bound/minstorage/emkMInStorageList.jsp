<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMInStorageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkMInStorageController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <%--<t:dgCol title="预采购合同号"  field="yhtNum" queryMode="single" width="105"></t:dgCol>--%>
      <t:dgCol title="采购合同号"  query="true"  field="htNum" queryMode="single" width="105"></t:dgCol>
      <t:dgCol title="订单号" query="true"   field="orderNum" queryMode="single" width="100"></t:dgCol>

      <t:dgCol title="入库日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="出货日期"  field="outDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" field="cusName"  queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>

       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkMInStorageController.do?goAdd&winTitle=添加入库申请单" funname="add" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkMInStorageController.do?goUpdate&winTitle=编辑入库申请单" funname="update" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkMInStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bound/minstorage/emkMInStorageList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });



//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMInStorageController.do?upload', "emkMInStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMInStorageController.do?exportXls","emkMInStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMInStorageController.do?exportXlsByT","emkMInStorageList");
}

 </script>