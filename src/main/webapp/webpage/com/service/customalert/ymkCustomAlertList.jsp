<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
 <div region="center" style="padding:0px;border:0px">
<t:datagrid name="ymkCustomAlertList" checkbox="true"  pagination="true" fitColumns="false" title="" actionUrl="ymkCustomAlertController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
 <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="客户"  field="customName" queryMode="single"  width="200"></t:dgCol>
 <t:dgCol title="提醒时间"  field="alertTime"  queryMode="single"  width="150"></t:dgCol>
 <t:dgCol title="提醒内容"  field="alertContent"  queryMode="single"  width="300"></t:dgCol>
 <%--<t:dgCol title="提醒人员ID"  field="alertUserIds"  queryMode="single"  width="120"></t:dgCol>--%>
 <%--<t:dgCol title="提醒人员"  field="alertUserNames"  queryMode="single"  width="120"></t:dgCol>--%>
 <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomAlertController.do?goAdd" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomAlertController.do?goUpdate" funname="update"></t:dgToolBar>
 <t:dgToolBar title="批量删除"  icon="fa fa-remove" url="ymkCustomAlertController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
 <t:dgToolBar title="查看" icon="fa fa-search" url="ymkCustomAlertController.do?goUpdate" funname="detail"></t:dgToolBar>
</t:datagrid>
 </div>
</div>
 <script src = "webpage/com/service/customalert/ymkCustomAlertList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'ymkCustomAlertController.do?upload', "ymkCustomAlertList");
}

//导出
function ExportXls() {
	JeecgExcelExport("ymkCustomAlertController.do?exportXls","ymkCustomAlertList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("ymkCustomAlertController.do?exportXlsByT","ymkCustomAlertList");
}

 </script>