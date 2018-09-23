<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkPassFactoryList" checkbox="false" pagination="true" fitColumns="true" title="离厂放行单" actionUrl="emkPassFactoryController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务员ID"  field="businesserName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员ID"  field="tracerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门ID"  field="businesseDeptId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="developer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员ID"  field="developerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款式大类"  field="proType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="sampleNoDesc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主键"  field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出货通知单号"  field="outFourmNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订舱通知单号"  field="cargoNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂放行条号"  field="levealFactoryNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="船务员"  field="cwer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提交订舱日期"  field="cargoDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂日期"  field="levealDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总箱数"  field="sumBox"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输单位名称"  field="ysdwName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输单位代码"  field="ysdwCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="司机姓名"  field="driver"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="司机电话"  field="drverTel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车牌"  field="driverNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂通知单状态"  field="state"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="emkPassFactoryController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkPassFactoryController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkPassFactoryController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="emkPassFactoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkPassFactoryController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/outforum/passfactory/emkPassFactoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkPassFactoryController.do?upload', "emkPassFactoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkPassFactoryController.do?exportXls","emkPassFactoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkPassFactoryController.do?exportXlsByT","emkPassFactoryList");
}

 </script>