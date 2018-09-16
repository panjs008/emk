<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkTestList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkTestController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="生产合同号"  field="schtNum"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkTestController.do?goAdd&winTitle=录入测试申请单" funname="add" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkTestController.do?goUpdate&winTitle=编辑测试申请单" funname="update" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkTestController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/test/emkTestList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkTestController.do?upload', "emkTestList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkTestController.do?exportXls","emkTestList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkTestController.do?exportXlsByT","emkTestList");
}

 </script>