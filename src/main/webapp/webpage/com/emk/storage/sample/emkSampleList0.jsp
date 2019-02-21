<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkSampleList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkYptzdController.do?datagrid&flag=${param.flag}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="140"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="190" frozenColumn="true"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="客户原样"  image="true"  imageSize="30,30" field="customSampleUrl"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="尺码表" image="true"  imageSize="30,30"  field="sampleSizeUrl"   queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="产品类别"  field="proTypeName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="商品名称"  field="proName"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl" dictionary="gylx"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="版次"  field="version"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="布面克重"  field="weight" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="布面成分"  field="chengf"  queryMode="single"  width="80"></t:dgCol>
      <t:dgFunOpt funname="queryDetail1(id,proName)" title="尺寸规格" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail2(id,proName)" title="主辅料清单" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkYptzdController.do?goAdd&flag=${param.flag}" funname="add" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkYptzdController.do?goUpdate" funname="update" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkYptzdController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'清单明细',
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
 <script src = "webpage/com/emk/storage/sample/emkSampleList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 function queryDetail1(id,proName){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "尺寸规格：" +proName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "emkSampleTotalController.do?list&sampleId=" + id);
 }
 function queryDetail2(id,proName){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "辅料清单："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&sampleId=" + id);
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkYptzdController.do?upload', "emkSampleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkYptzdController.do?exportXls","emkSampleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkYptzdController.do?exportXlsByT","emkSampleList");
}

 </script>