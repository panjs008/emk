<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkYsPayList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkYsPayController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输单位代码"  field="ysdwCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输单位名称"  field="ysdwName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提交日期"  field="kdName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="币种"  field="bz"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总金额"  field="sumMoney"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收款单位名称"  field="skdwName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账号"  field="account"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="relationer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="telphone"  queryMode="single"  width="120"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkYsPayController.do?goAdd" funname="add" height="600" width="1200"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkYsPayController.do?goUpdate" funname="update" height="600" width="1200"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkYsPayController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkYsPayController.do?goUpdate" height="600" width="1200"  funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/caiwu/yspay/emkYsPayList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkYsPayController.do?upload', "emkYsPayList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkYsPayController.do?exportXls","emkYsPayList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkYsPayController.do?exportXlsByT","emkYsPayList");
}

 </script>