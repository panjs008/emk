<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkTestList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkTestController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>

      <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="大货日期"  field="ysDate"  queryMode="single"  width="60"></t:dgCol>

      <t:dgCol title="测试申请单号"  field="cssqdh"  query="true" queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="生产合同号"  field="produceNum"  query="true" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo" query="true"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="145"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="60"></t:dgCol>

      <t:dgFunOpt funname="goToProcess(id)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkTestController.do?goAdd&winTitle=录入测试申请单" funname="add" height="600" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkTestController.do?goUpdate&winTitle=编辑测试申请单" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkTestController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/produce/test/emkTestList.js"></script>		
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
     var rowsData = $('#emkTestList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的测试申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交测试申请单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkTestController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkTestList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url: "flowController.do?getCurrentProcess&tableName=emk_test&title=测试申请单&id=" + id,
         type: 'post',
         cache: false,
         data: null,
         success: function (data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if (msg == "完成") {
                     createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/produce/test/emkTest-process&id=' + id, 1200, height);
                 } else {
                     createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/produce/test/emkTest-process&id=" + id, 1200, height);
                 }

             }
         }
     });
 }


 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkTestController.do?upload', "emkTestList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkTestController.do?exportXls","emkTestList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkTestController.do?exportXlsByT","emkTestList");
}

 </script>