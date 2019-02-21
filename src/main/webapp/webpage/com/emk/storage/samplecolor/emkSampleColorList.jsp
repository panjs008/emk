<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkSampleColorList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkSampleColorController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="通知单号"  field="sytzdbh" queryMode="single" query="true"  width="80"></t:dgCol>
   <t:dgCol title="通知单日期"  field="sytzdrq"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="需求单日期"  field="syxqdrq"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>
   <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>

   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkSampleColorController.do?goAdd&winTitle=录入色样通知单" funname="add" height="600" width="1000"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkSampleColorController.do?goUpdate&winTitle=编辑色样通知单" funname="update" height="600" width="1000"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkSampleColorController.do?goUpdate&winTitle=查看色样通知单" funname="detail" height="600" width="1210"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkSampleColorController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/samplecolor/emkSampleColorList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSampleColorController.do?upload', "emkSampleColorList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSampleColorController.do?exportXls","emkSampleColorList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSampleColorController.do?exportXlsByT","emkSampleColorList");
}

 </script>