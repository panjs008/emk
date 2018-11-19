<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkSyList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkSyController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgCol title="色样单号"  field="sytzdbh"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="sytzdrq"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="需求单号"  field="syxqdbh"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务部门"  field="ywbm"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务员"  field="ywy"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务跟单员"  field="ywgdy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产跟单员"  field="scgdy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户代码"  field="khdm"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="款式大类"  field="ksdl"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="颜色英文名"  field="ysywm"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="颜色中文名"  field="yaswm"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="色号"  field="sh"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="色样规格"  field="sygg"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="色样数量"  field="sysl"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="技术参数"  field="jscs"  queryMode="single"  width="120"></t:dgCol>
<%--   <t:dgCol title="配方"  field="pf"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="温度"  field="wd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间"  field="sj"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图片"  field="customSampleUrl"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版次"  field="version"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="成分"  field="chengf"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="克重"  field="weight"  queryMode="single"  width="120"></t:dgCol>--%>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkSyController.do?goAdd" funname="add" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkSyController.do?goUpdate" funname="update" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkSyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/sy/emkSyList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSyController.do?upload', "emkSyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSyController.do?exportXls","emkSyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSyController.do?exportXlsByT","emkSyList");
}

 </script>