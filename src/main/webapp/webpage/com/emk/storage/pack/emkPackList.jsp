<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkPackList" checkbox="false" pagination="false" fitColumns="false" title="" actionUrl="emkPackController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
   <t:dgCol title="需求单号"  field="parkNo" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>
   <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产合同号"  field="productHtNo"  queryMode="single"  width="100"></t:dgCol>

   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="开发最迟日期"  field="ysDate"  queryMode="single"  width="95"></t:dgCol>

   <t:dgFunOpt funname="queryDetail1(id,parkNo)" title="包装辅料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="fa fa-plus" url="emkPackController.do?goAdd&winTitle=录入包装需求开发单" funname="add" height="580" width="1000"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkPackController.do?goUpdate&winTitle=编辑包装需求开发单" funname="update" height="580" width="1000"></t:dgToolBar>
   <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkPackController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

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

 <script src = "webpage/com/emk/storage/pack/emkPackList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function queryDetail1(id,proName){
    $('#emkSampleList').datagrid('unselectAll');
    var title = "包装辅料："+proName ;
    if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
     $('#main_list').layout('expand','east');
    }
    $('#main_list').layout('panel','east').panel('setTitle', title);
    $('#main_list').layout('panel','east').panel('resize', {width: 600});

    $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&type=price&sampleId=" + id);
 }



 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkPackController.do?upload', "emkPackList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkPackController.do?exportXls","emkPackList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkPackController.do?exportXlsByT","emkPackList");
}

 </script>