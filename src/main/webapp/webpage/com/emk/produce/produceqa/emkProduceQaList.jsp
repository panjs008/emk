<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkProduceQaList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkProduceQaController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>

      <t:dgCol title="生产问题函号"  field="qaNo" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="发函日期"  field="qaDate"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="发函人"  field="sender"   queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="发函部门"  field="sendDeptName"   queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="收函人"  field="recevier"   queryMode="single"  width="80"></t:dgCol>

      <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>

   <t:dgDelOpt title="删除" operationCode="delete" url="emkProduceQaController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkProduceQaController.do?goAdd&winTitle=录入单生产问题单" funname="add" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkProduceQaController.do?goUpdate&winTitle=编辑单生产问题单" funname="update" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkProduceQaController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/produceqa/emkProduceQaList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProduceQaController.do?upload', "emkProduceQaList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProduceQaController.do?exportXls","emkProduceQaList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProduceQaController.do?exportXlsByT","emkProduceQaList");
}

 </script>