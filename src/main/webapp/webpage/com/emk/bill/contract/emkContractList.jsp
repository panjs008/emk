<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkContractList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkContractController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <%--<t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>--%>

      <t:dgCol title="合同编号"  field="htNum" queryMode="single" width="90"></t:dgCol>
      <t:dgCol title="甲方"  field="partyA" queryMode="single" width="100"></t:dgCol>
      <t:dgCol title="乙方"  field="partyB" queryMode="single" width="100"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo" queryMode="single" width="100"></t:dgCol>
      <t:dgCol title="签定日期"  field="signDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="交货日期"  field="recevieDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>

      <%--<t:dgFunOpt funname="queryDetail2(id,materialNo)" title="原料面料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>--%>
      <%--<t:dgToolBar title="录入" icon="fa fa-plus" url="emkContractController.do?goAdd&type=0&winTitle=录入购销合同同单" funname="add" height="600" width="1100"></t:dgToolBar>--%>
      <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkContractController.do?goUpdate&type=0&winTitle=编辑购销合同同单" funname="update" height="600" width="1100"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkContractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/contract/emkContractList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkContractController.do?upload', "emkContractList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkContractController.do?exportXls","emkContractList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkContractController.do?exportXlsByT","emkContractList");
}

 </script>