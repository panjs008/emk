<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkCheckList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkCheckController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="验货申请单号"  field="checkNum" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="审核日期"  field="checkDate"  query="true" queryMode="single"  width="80"></t:dgCol>

      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>

      <t:dgFunOpt funname="goToProcess(id,createBy)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkCheckController.do?goAdd&winTitle=录入验货申请" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkCheckController.do?goUpdate&winTitle=编辑验货申请" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkCheckController.do?goUpdate&winTitle=查看验货申请" funname="detail" height="580" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>

      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkCheckController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/check/check/emkCheckList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 function formatColor(val,row){
     if(row.state=="1"){
         return '<span style="color:	#FF0000;">处理中</span>';
     }else if(row.state=="2"){
         return '<span style="color:	#0000FF;">完成</span>';
     }else{
         return '创建';
     }
 }


 function formatColor(val, row) {
     if (row.state == "1") {
         return '<span style="color:	#FF0000;">处理中</span>';
     } else if (row.state == "2") {
         return '<span style="color:	#0000FF;">完成</span>';
     } else {
         return '创建';
     }
 }

 function doSubmitV() {
     var rowsData = $('#emkCheckList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的验货申请单');
         return;
     }
     for (var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交验货申请单?', function (r) {
         if (r) {
             $.ajax({
                 url: "emkCheckController.do?doSubmit&ids=" + ids,
                 type: 'post',
                 cache: false,
                 data: null,
                 success: function (data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkCheckList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id,createBy) {
     var height = window.top.document.body.offsetHeight * 0.85;

     $.ajax({
         url: "flowController.do?getCurrentProcess&tableName=emk_check&title=验货申请单&id=" + id,
         type: 'post',
         cache: false,
         data: null,
         success: function (data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(createBy == "${CUR_USER.userName}"){
                     createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/check/check/emkCheck-process&id=' + id, 1200, height);
                 }else{
                     if (msg == "完成") {
                         createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/check/check/emkCheck-process&id=' + id, 1200, height);
                     } else {
                         if("${ROLE.rolecode}" == "ycjl") {
                             createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/check/check/emkCheck-process&id=" + id, 1200, height);
                         }else{
                             createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/check/check/emkCheck-process&id=' + id, 1200, height);
                         }
                     }
                 }
             }
         }
     });
 }
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkCheckController.do?upload', "emkCheckList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkCheckController.do?exportXls","emkCheckList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkCheckController.do?exportXlsByT","emkCheckList");
}

 </script>