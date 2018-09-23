<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkShipExpensesList" checkbox="false" pagination="true" fitColumns="true" title="运费费" actionUrl="emkShipExpensesController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务员ID"  field="businesserName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员ID"  field="tracerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门ID"  field="businesseDeptId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="developer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员ID"  field="developerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="sampleNoDesc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总箱数"  field="sumBoxTotal"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总体积"  field="sumBoxVolume"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总净重"  field="sumBoxJz"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总毛重"  field="sumBoxMao"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主键"  field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订舱通知单号"  field="cargoNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出货通知单号"  field="outForumNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂放行条号"  field="levealFactoryNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="船务员"  field="cwyer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="gysCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="gys"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂日期"  field="levealDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产合同号"  field="htNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输企业名称"  field="ysqyName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输企业代码"  field="ysqyCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输费用金额"  field="ysCost"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输费用总金额"  field="ysSumMoney"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输费用发票号"  field="ysFp"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运输费用付款状态"  field="ysPayState"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收款单位"  field="skdw"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账号"  field="bankAccount"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户行"  field="bankName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="emkShipExpensesController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkShipExpensesController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkShipExpensesController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="emkShipExpensesController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkShipExpensesController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/outforum/shipexpenses/emkShipExpensesList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkShipExpensesController.do?upload', "emkShipExpensesList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkShipExpensesController.do?exportXls","emkShipExpensesList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkShipExpensesController.do?exportXlsByT","emkShipExpensesList");
}

 </script>