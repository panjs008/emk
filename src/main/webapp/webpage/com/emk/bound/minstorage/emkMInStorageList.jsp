<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMInStorageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkMInStorageController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <%--<t:dgCol title="预采购合同号"  field="yhtNum" queryMode="single" width="105"></t:dgCol>--%>
      <t:dgCol title="采购合同号"  query="true"  field="htNum" queryMode="single" width="105"></t:dgCol>
      <t:dgCol title="订单号" query="true"   field="orderNo" queryMode="single" width="100"></t:dgCol>

      <t:dgCol title="入库日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="出货日期"  field="outDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" field="cusName"  queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>

      <t:dgFunOpt funname="goToProcess(id)" title="流程进度" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkMInStorageController.do?goAdd&winTitle=添加入库申请单" funname="add" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkMInStorageController.do?goUpdate&winTitle=编辑入库申请单" funname="update" height="600" width="1100"></t:dgToolBar>
       <t:dgToolBar title="提交" icon="fa fa-arrow-circle-right" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkMInStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bound/minstorage/emkMInStorageList.js"></script>
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
     var rowsData = $('#emkMInStorageList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的入库申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交入库申请单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkMInStorageController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkMInStorageList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url : "emkMInStorageController.do?getCurrentProcess&id="+id,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(msg == "完成"){
                     createdetailwindow('流程进度--当前环节：'+msg,'emkMInStorageController.do?goProcess&id='+id,1150,height);
                 }else{
                     createwindow("流程进度--当前环节："+msg, "emkMInStorageController.do?goProcess&id="+id,1150,height);
                 }

             }
         }
     });
 }

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMInStorageController.do?upload', "emkMInStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMInStorageController.do?exportXls","emkMInStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMInStorageController.do?exportXlsByT","emkMInStorageList");
}

 </script>