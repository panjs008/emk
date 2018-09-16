<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
 <div region="center" style="padding:0px;border:0px">
<t:datagrid name="ymkCustomTraceList" checkbox="true"  pagination="false" fitColumns="false" title="" actionUrl="ymkCustomTraceController.do?datagrid&cusId=${param.customId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
 <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="客户名称"  field="cusName" query="true"  queryMode="single"  width="250"></t:dgCol>
 <t:dgCol title="跟进时间"  field="traceTime"  queryMode="single"  width="140"></t:dgCol>
 <t:dgCol title="跟进方式"  field="traceType" dictionary="tracetype" queryMode="single"  width="90"></t:dgCol>
 <t:dgCol title="跟进状态"  field="traceState" dictionary="tarcestate" queryMode="single"  width="90"></t:dgCol>
 <t:dgCol title="客户ID"  field="cusId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="联系人"  field="contactName" query="true"   queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="联系人ID"  field="contactId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="跟进人ID"  field="traceId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="跟进人"  field="createName"  queryMode="single"  width="120"></t:dgCol>

 <t:dgCol title="跟进内容"  field="traceContent"  queryMode="single"  width="210"></t:dgCol>
 <t:dgDelOpt title="删除" url="ymkCustomTraceController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
 <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomTraceController.do?goAdd1&cusId=${param.customId}" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomTraceController.do?goUpdate1" funname="update"></t:dgToolBar>
 <t:dgToolBar title="删除"  icon="fa fa-remove" url="ymkCustomTraceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
</t:datagrid>
 </div>
</div>
 <script src = "webpage/com/service/customtrace/ymkCustomTraceList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'ymkCustomTraceController.do?upload', "ymkCustomTraceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("ymkCustomTraceController.do?exportXls","ymkCustomTraceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("ymkCustomTraceController.do?exportXlsByT","ymkCustomTraceList");
}

 </script>