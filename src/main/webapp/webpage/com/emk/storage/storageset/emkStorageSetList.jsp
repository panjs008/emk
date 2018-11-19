<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkStorageSetList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkStorageSetController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="50"></t:dgCol>

      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="仓库名称"  field="storageName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地区"  field="areaCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="仓库地址"  field="storageAddress"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
      <t:dgFunOpt funname="queryDetail(id,storageName)" title="库位" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
   <%--<t:dgDelOpt title="删除" operationCode="delete" url="emkStorageSetController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkStorageSetController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkStorageSetController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkStorageSetController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkStorageSetController.do?goUpdate" funname="detail"></t:dgToolBar>
       <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>--%>
       <%--<t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
       <%--<t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'库位明细',
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
    <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="positionDetialListpanel"></div>
 <script src = "webpage/com/emk/storage/storageset/emkStorageSetList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function queryDetail(id,storageName){
     $('#emkStorageSetList').datagrid('unselectAll');
     var title = "仓库：" +storageName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#positionDetialListpanel').panel("refresh", "emkStorageSetPositionController.do?list&storageId=" + id);

 }


 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkStorageSetController.do?upload', "emkStorageSetList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkStorageSetController.do?exportXls","emkStorageSetList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkStorageSetController.do?exportXlsByT","emkStorageSetList");
}

 </script>