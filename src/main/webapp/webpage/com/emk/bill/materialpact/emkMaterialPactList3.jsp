<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMaterialPactList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkMaterialPactController.do?datagrid&type=0" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
   <t:dgCol title="采购合同编号"  field="materialNo" queryMode="single" query="true"  width="100"></t:dgCol>
   <t:dgCol title="合同编号"  field="htNum" queryMode="single" width="105"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNum" queryMode="single" width="100"></t:dgCol>

   <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="交货日期"  field="dhjqDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>

   <t:dgFunOpt funname="queryDetail2(id,materialNo)" title="原料面料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="fa fa-plus" url="emkMaterialPactController.do?goAdd&type=0&winTitle=录入原料预采购合同单" funname="add" height="600" width="1000"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkMaterialPactController.do?goUpdate&type=0&winTitle=编辑原料预采购合同单" funname="update" height="600" width="1000"></t:dgToolBar>
   <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkMaterialPactController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'订单明细',
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
 <script src = "webpage/com/emk/bill/materialpact/emkMaterialPactList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function queryDetail2(id,proName){
      var rowsData = $('#emkMaterialPactList').datagrid('getSelections');

      var title = "原料面料清单："+proName+","+rowsData[0].cusName;
      if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
       $('#main_list').layout('expand','east');
      }
      $('#main_list').layout('panel','east').panel('setTitle', title);
      $('#main_list').layout('panel','east').panel('resize', {width: 600});

      $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&sampleType=order&sampleId=" + id);
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMaterialPactController.do?upload', "emkMaterialPactList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMaterialPactController.do?exportXls","emkMaterialPactList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMaterialPactController.do?exportXlsByT","emkMaterialPactList");
}

 </script>