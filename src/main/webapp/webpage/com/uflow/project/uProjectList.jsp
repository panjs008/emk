<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="uProjectList" checkbox="true" pagination="true" fitColumns="true" title="中标项目" actionUrl="uProjectController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true" width="95"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="项目编号"  field="projectNum" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="项目名称"  field="projectName" query="true" queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="合同编号"  field="htNum"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="供应商名称"  field="supplyer"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="采购人单位"  field="owner"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="采购人名称"  field="purchaser"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="单位ID"  field="ownerId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商ID"  field="supplyerId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购人ID"  field="purchaserId" hidden="true"   queryMode="single"  width="120"></t:dgCol>
      <t:dgFunOpt funname="queryDetail1(id)" title="项目清单" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="uProjectController.do?goAdd" funname="addProject" width="800"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="uProjectController.do?goUpdate" funname="updateProject" width="800"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="uProjectController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="uProjectController.do?goUpdate" funname="detail"></t:dgToolBar>
       <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'项目清单',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
     style="width: 500px; overflow: hidden;" id="eastPanel">
    <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="proDetialListpanel"></div>
 <script src = "webpage/com/uflow/project/uProjectList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){

 });

 function addProject(){
     add("添加中标项目", "uProjectController.do?goAdd","uProjectList","800px","450px");
 }

 function updateProject() {
     var url = "uProjectController.do?goUpdate";
     update('编辑中标项目', url, "uProjectList","800px","450px");
 }

 function queryDetail1(id){
     $('#uProjectList').datagrid('unselectAll');
     var title = "项目清单" ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "uProjectDetailController.do?list&projectId=" + id);

 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'uProjectController.do?upload', "uProjectList");
}

//导出
function ExportXls() {
	JeecgExcelExport("uProjectController.do?exportXls","uProjectList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("uProjectController.do?exportXlsByT","uProjectList");
}

 </script>