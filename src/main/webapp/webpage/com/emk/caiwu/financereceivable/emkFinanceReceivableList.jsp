<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkFinanceReceivableList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkFinanceReceivableController.do?datagrid&type=0" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  hidden="true" field="id"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  hidden="true" field="createName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  hidden="true" field="createBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  hidden="true" field="createDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  hidden="true" field="sysOrgCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>

      <t:dgCol title="应收通知单号"  field="receiveNum" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="订舱通知单号"  field="cargoNo" query="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="离厂放行条号"  field="levealFactoryNo" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="出货日期"  field="outForrmDate"   queryMode="single"  width="70"></t:dgCol>

   <t:dgCol title="订单号"  field="orderNo"  query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="客户代码"  field="cusNum"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="145"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>


      <t:dgFunOpt funname="goToProcess(id)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <%--<t:dgFunOpt funname="queryDetail2(id,materialNo)" title="原料面料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>--%>
      <%--<t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkContractController.do?goAdd&type=0&winTitle=录入购销合同同单" funname="add" height="600" width="1100"></t:dgToolBar>--%>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkFinanceReceivableController.do?goAdd&type=0&winTitle=录入应收通知单" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkFinanceReceivableController.do?goUpdate&type=0&winTitle=编辑应收通知单" funname="update" height="600" width="1100"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkFinanceReceivableController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/caiwu/financereceivable/emkFinanceReceivableList.js"></script>		
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

 function doSubmitV() {
     var rowsData = $('#emkFinanceReceivableList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的应收通知单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交应收通知单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkFinanceReceivableController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkFinanceReceivableList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url: "flowController.do?getCurrentProcess&tableName=emk_finance_receivable&title=应收通知单&id=" + id,
         type: 'post',
         cache: false,
         data: null,
         success: function (data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if (msg == "完成") {
                     createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/caiwu/financereceivable/emkFinanceReceivable-process&id=' + id, 1200, height);
                 } else {
                     createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/caiwu/financereceivable/emkFinanceReceivable-process&id=" + id, 1200, height);
                 }

             }
         }
     });
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkFinanceReceivableController.do?upload', "emkFinanceReceivableList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkFinanceReceivableController.do?exportXls","emkFinanceReceivableList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkFinanceReceivableController.do?exportXlsByT","emkFinanceReceivableList");
}

 </script>