<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkApprovalDetailList" checkbox="false" pagination="true" fitColumns="true" title="审批业务处理表" actionUrl="emkApprovalDetailController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属审批业务ID"  field="approvalId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="表单ID"  field="formId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批人ID"  field="approveUserId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核状态"  field="approveStatus"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="approveDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批意见"  field="approveAdvice"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="emkApprovalDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkApprovalDetailController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkApprovalDetailController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="emkApprovalDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkApprovalDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/approval/approvaldetail/emkApprovalDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkApprovalDetailController.do?upload', "emkApprovalDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkApprovalDetailController.do?exportXls","emkApprovalDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkApprovalDetailController.do?exportXlsByT","emkApprovalDetailList");
}

 </script>