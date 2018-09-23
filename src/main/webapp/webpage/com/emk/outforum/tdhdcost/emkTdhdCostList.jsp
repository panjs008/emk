<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkTdhdCostList" checkbox="false" pagination="true" fitColumns="true" title="提单货代费用" actionUrl="emkTdhdCostController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务员ID"  field="businesserName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务跟单员ID"  field="tracerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门ID"  field="businesseDeptId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="developer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员ID"  field="developerName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="sampleNoDesc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="price"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总箱数"  field="sumBoxTotal"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总体积"  field="sumBoxVolume"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总净重"  field="sumBoxJz"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总毛重"  field="sumBoxMao"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主键"  field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="cwyer"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订舱通知单号"  field="cargoNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂放行条号"  field="levealFactoryNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="gys"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商代码"  field="gysCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="离厂日期"  field="levealDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出货通知单号"  field="outForumNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="total"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产合同号"  field="htNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提单号"  field="tdNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提单状态"  field="tdState"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="货代费用发票号"  field="hdfyFp"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="费用金额"  field="cost"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="费用付款状态"  field="payState"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="货代名称"  field="hdName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="货代代码"  field="hdCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="报关单状态"  field="bgzt"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="海关放行条状态"  field="hgfxzt"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="emkTdhdCostController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkTdhdCostController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkTdhdCostController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="emkTdhdCostController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkTdhdCostController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
       <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/outforum/tdhdcost/emkTdhdCostList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkTdhdCostController.do?upload', "emkTdhdCostList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkTdhdCostController.do?exportXls","emkTdhdCostList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkTdhdCostController.do?exportXlsByT","emkTdhdCostList");
}

 </script>