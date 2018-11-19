<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMOutStorageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkMOutStorageController.do?datagrid&type=2" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <%--<t:dgCol title="预采购合同号"  field="yhtNum" queryMode="single" width="105"></t:dgCol>--%>
      <t:dgCol title="采购合同号"  query="true"  field="htNum" queryMode="single" width="105"></t:dgCol>
      <t:dgCol title="订单号" query="true"   field="orderNo" queryMode="single" width="100"></t:dgCol>

      <t:dgCol title="出库日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="出货日期"  field="outDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" field="cusName"  queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>

      <t:dgFunOpt funname="goToProcess(id,createBy,createName)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkMOutStorageController.do?goAdd&type=2&winTitle=添加包装辅料出库申请单" funname="add" height="600" width="1100"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkMOutStorageController.do?goUpdate&winTitle=编辑包装辅料出库申请单" funname="update" height="600" width="1100"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkMOutStorageController.do?goUpdate&goUpdate&winTitle=查看包装辅料出库申请单" funname="detail" height="600" width="1100"></t:dgToolBar>

      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkMOutStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bound/moutstorage/emkMOutStorageList.js"></script>		
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
     var rowsData = $('#emkMOutStorageList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的包装辅料出库申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交包装辅料出库申请单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkMOutStorageController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkMOutStorageList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id,createBy,createName){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url : "emkMOutStorageController.do?getCurrentProcess&id="+id,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(createName != "出料" && createName != "出库" && createBy == "${CUR_USER.userName}"){
                     createdetailwindow('流程进度--当前环节：'+msg,'emkMOutStorageController.do?goProcess&id='+id,1150,height);

                 }else{
                     if (msg == "完成") {
                         createdetailwindow('流程进度--当前环节：'+msg,'emkMOutStorageController.do?goProcess&id='+id,1150,height);
                     } else {
                         if(createName == "领导审核" && "${ROLE.rolecode}" == "cgjl") {
                             createwindow("流程进度--当前环节："+msg, "emkMOutStorageController.do?goProcess&id="+id,1150,height);

                         }else if(createName == "出料" &&  createBy == "${CUR_USER.userName}") {
                             createwindow("流程进度--当前环节："+msg, "emkMOutStorageController.do?goProcess&id="+id,1150,height);

                         }else if(createName == "出库" &&  createBy == "${CUR_USER.userName}") {
                             createwindow("流程进度--当前环节："+msg, "emkMOutStorageController.do?goProcess&id="+id,1150,height);

                         }else{
                             createdetailwindow('流程进度--当前环节：'+msg,'emkMOutStorageController.do?goProcess&id='+id,1150,height);
                         }
                     }
                 }

             }
         }
     });
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMOutStorageController.do?upload', "emkMOutStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMOutStorageController.do?exportXls","emkMOutStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMOutStorageController.do?exportXlsByT","emkMOutStorageList");
}

 </script>