<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkYjyhTimeList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkYjyhTimeController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <%--<t:dgCol title="操作" field="opt" width="200" frozenColumn="true"></t:dgCol>--%>
      <t:dgCol title="订单号"  field="orderNo" query="true"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="180"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="数量"  field="total"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="中期验货状态"  field="zqyhSate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="尾期验货状态"  field="wqyhState"  queryMode="single"  width="80"></t:dgCol>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkYjyhTimeController.do?goUpdate&winTitle=查看预计验货时间" funname="detail" height="600" width="1000"></t:dgToolBar>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkYjyhTimeController.do?goAdd&winTitle=录入预计验货时间" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkYjyhTimeController.do?goUpdate&winTitle=编辑预计验货时间" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkYjyhTimeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/yjyhtime/emkYjyhTimeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkYjyhTimeController.do?upload', "emkYjyhTimeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkYjyhTimeController.do?exportXls","emkYjyhTimeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkYjyhTimeController.do?exportXlsByT","emkYjyhTimeList");
}

 </script>