<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkProduceList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkProduceController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="进度更新日期"  field="createDate"  formatter="yyyy-MM-dd"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="更新操作员"  field="createBy"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="60"></t:dgCol>--%>
   <%--<t:dgCol title="出货日期"  field="outDate"  queryMode="single"  width="60"></t:dgCol>--%>
   <%--<t:dgCol title="生产合同号"  field="produceHtNum"  query="true" queryMode="single"  width="85"></t:dgCol>--%>
   <%--<t:dgCol title="订单号"  field="orderNo" query="true"  queryMode="single"  width="80"></t:dgCol>--%>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracerName"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="developerName"  queryMode="single"  width="60"></t:dgCol>

   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="140"></t:dgCol>
   <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>

   <%--<t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>--%>
   <%--<t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>--%>
   <%--<t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>--%>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkProduceController.do?goAdd&winTitle=录入采购生产进度" funname="add" height="600" width="1150"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkProduceController.do?goUpdate&winTitle=编辑采购生产进度" funname="update" height="600" width="1150"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/produce/emkProduceList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProduceController.do?upload', "emkProduceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProduceController.do?exportXls","emkProduceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProduceController.do?exportXlsByT","emkProduceList");
}

 </script>