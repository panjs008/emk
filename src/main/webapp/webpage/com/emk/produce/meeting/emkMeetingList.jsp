<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMeetingList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkMeetingController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会议记录编号"  field="meetingNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会议时间"  field="meetingDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="145"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkMeetingController.do?goAdd&winTitle=录入产前会议通知单" funname="add" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkMeetingController.do?goUpdate&winTitle=编辑产前会议通知单" funname="update" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkMeetingController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/meeting/emkMeetingList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMeetingController.do?upload', "emkMeetingList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMeetingController.do?exportXls","emkMeetingList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMeetingController.do?exportXlsByT","emkMeetingList");
}

 </script>