<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkInStorageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkInStorageController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="130"></t:dgCol>
      <t:dgCol title="当前环节"  field="createName"   queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="入库单号"  field="rkNo"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="类型"  field="type" replace="打样_0,生产_1"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="供应商"  field="factoryCode"  dictionary="factory" queryMode="single"  width="230"></t:dgCol>
   <%--<t:dgCol title="供应商代码"  field="factoryCode"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="运费"  field="yunfei"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="交付日期"  field="receiveDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>

   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="150"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(id)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <t:dgDelOpt title="删除" operationCode="delete" url="emkInStorageController.do?doDel&id={id}" exp="state#eq#0" urlclass="ace_button"  urlfont="fa-trash-o"/>
       <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkInStorageController.do?goAdd&winTitle=录入采购订单" funname="add" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkInStorageController.do?goUpdate&winTitle=编辑采购订单" funname="update" height="550" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-right" funname="doSubmitV"></t:dgToolBar>

      <%--<t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkInStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
 <%--      <t:dgToolBar title="查看" icon="fa fa-search" url="emkInStorageController.do?goUpdate" funname="detail" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
       <%--<t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/instorage/emkInStorageList.js"></script>		
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
     var rowsData = $('#emkInStorageList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的采购订单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交采购订单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkInStorageController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkInStorageList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url : "emkInStorageController.do?getCurrentProcess&id="+id,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(msg == "完成"){
                     createdetailwindow('流程进度--当前环节：'+msg,'emkInStorageController.do?goProcess&id='+id,1150,height);
                 }else{
                     createwindow("流程进度--当前环节："+msg, "emkInStorageController.do?goProcess&id="+id,1150,height);
                 }

             }
         }
     });
 }


 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkInStorageController.do?upload', "emkInStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkInStorageController.do?exportXls","emkInStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkInStorageController.do?exportXlsByT","emkInStorageList");
}

 </script>