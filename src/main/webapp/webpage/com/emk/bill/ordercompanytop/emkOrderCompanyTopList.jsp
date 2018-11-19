<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkOrderCompanyTopList" checkbox="true"  pagination="true" fitColumns="false" title="公司抬头信息表" actionUrl="emkOrderCompanyTopController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户地址"  field="cusAddress"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户联系人"  field="cusRelationer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户联系人电话"  field="cusTelphone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="办公电话"  field="workTel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="办公传真"  field="workFax"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="其他说明"  field="other"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公司抬头"  field="comTop"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公司地址"  field="comAddress"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门"  field="comDept"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务员"  field="comYwer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系人电话"  field="comTelphone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="办公电话"  field="comWorkTel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="办公传真"  field="comFax"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单ID"  field="orderId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" operationCode="delete" url="emkOrderCompanyTopController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderCompanyTopController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOrderCompanyTopController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete" operationCode="delete"  icon="fa fa-remove" url="emkOrderCompanyTopController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" url="emkOrderCompanyTopController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/ordercompanytop/emkOrderCompanyTopList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOrderCompanyTopController.do?upload', "emkOrderCompanyTopList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOrderCompanyTopController.do?exportXls","emkOrderCompanyTopList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOrderCompanyTopController.do?exportXlsByT","emkOrderCompanyTopList");
}

 </script>