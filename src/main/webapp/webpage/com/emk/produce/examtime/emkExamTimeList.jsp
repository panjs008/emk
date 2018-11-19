<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkExamTimeList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkExamTimeController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="生产合同号"  field="produceNum" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="更新时间"  field="kdDate"   queryMode="single"  width="70"></t:dgCol>

      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkExamTimeController.do?goAdd&winTitle=录入预计验货时间" funname="add" height="600" width="1150"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkExamTimeController.do?goUpdate&winTitle=编辑预计验货时间" funname="update" height="600" width="1150"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkExamTimeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/examtime/emkExamTimeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkExamTimeController.do?upload', "emkExamTimeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkExamTimeController.do?exportXls","emkExamTimeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkExamTimeController.do?exportXlsByT","emkExamTimeList");
}

 </script>