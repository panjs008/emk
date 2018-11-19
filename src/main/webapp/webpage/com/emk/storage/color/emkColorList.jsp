<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkColorList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkColorController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="色样需求单号"  field="colorNo"  queryMode="single"  width="130"></t:dgCol>
   <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="标准色卡"  field="colorCardUrl" imageSize="30,30"  image="true"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="颜色名称"  field="colorZnName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="开单日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="交期"  field="recevieDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="色样状态"  field="colorState"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="色号"  field="cNum"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="色样编号"  field="cyNum"  queryMode="single"  width="70"></t:dgCol>


       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add"  url="emkColorController.do?goAdd&winTitle=录入色样需求单" funname="add" height="580" width="1100"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit"  url="emkColorController.do?goUpdate&winTitle=编辑色样需求单" funname="update" height="580" width="1100"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkColorController.do?goUpdate&goUpdate&winTitle=查看色样需求单" funname="detail" height="580" width="1100"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove"  url="emkColorController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>


  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/color/emkColorList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkColorController.do?upload', "emkColorList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkColorController.do?exportXls","emkColorList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkColorController.do?exportXlsByT","emkColorList");
}

 </script>