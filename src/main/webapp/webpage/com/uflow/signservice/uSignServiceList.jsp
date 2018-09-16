<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="uSignServiceList" checkbox="true" pagination="true" fitColumns="true" title="服务商签约表" actionUrl="uSignServiceController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="甲方"  field="partA"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="甲方ID"  field="partAId" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="甲方联系人"  field="aRealtioner"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="甲方联系人ID"  hidden="true"  field="aRealtionerId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="甲方联系人电话"  field="aTelphone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="乙方"  field="partB"  queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="乙方ID"  field="partBId" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="乙方联系人"  field="bRealtioner"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="乙方联系人ID"  field="bRealtionerId" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="乙方联系人电话"  field="bTelphone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="起始时间"  field="workYearBegin" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="结束时间"  field="workYearEnd" query="true"  queryMode="single"  width="80"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="uSignServiceController.do?goAdd" funname="addSignService" width="1000" height="550"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="uSignServiceController.do?goUpdate" funname="updateSignService" width="1000" height="550"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="uSignServiceController.do?doBatchDel" width="1000" height="550" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="uSignServiceController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/uflow/signservice/uSignServiceList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   function addSignService(){
       add("添加服务商签约", "uSignServiceController.do?goAdd","uProjectList","1100px","550px");

   }

   function updateSignService() {
        var url = "uSignServiceController.do?goUpdate";
        update('编辑服务商签约', url, "uSignServiceList","1100px","550px");
    }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'uSignServiceController.do?upload', "uSignServiceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("uSignServiceController.do?exportXls","uSignServiceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("uSignServiceController.do?exportXlsByT","uSignServiceList");
}

 </script>