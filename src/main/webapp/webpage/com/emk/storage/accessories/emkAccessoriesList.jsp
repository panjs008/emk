<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list"  class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkAccessoriesList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkAccessoriesController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <t:dgCol title="需求单号"  field="materialNo" queryMode="single" query="true"  width="90"></t:dgCol>
      <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="交货日期"  field="dhjqDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>
      <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="采购员"  field="cger"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>

      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="60"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(id,createBy)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <%--<t:dgFunOpt funname="queryDetail(id,materialNo,state)" title="缝制辅料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>--%>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkAccessoriesController.do?goAdd&winTitle=录入缝制辅料开发单" funname="add" height="600" width="1250"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkAccessoriesController.do?goUpdate&winTitle=编辑缝制辅料开发单" funname="update" height="600" width="1250"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkAccessoriesController.do?goUpdate&winTitle=查看缝制辅料开发单" funname="detail" height="600" width="1250"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up"  funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkAccessoriesController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>

 <script src = "webpage/com/emk/storage/accessories/emkAccessoriesList.js"></script>
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

 function goToProcess(id,createBy){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url: "flowController.do?getCurrentProcess&tableName=emk_accessories&title=缝制辅料开发单&id=" + id,
         type: 'post',
         cache: false,
         data: null,
         success: function (data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(createBy == "${CUR_USER.userName}"){
                     createdetailwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/accessories/emkAccessories-process&id=" + id, 1200, height);
                 }else{
                     if (msg == "完成") {
                         createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/accessories/emkAccessories-process&id=' + id, 1200, height);
                     } else {
                         if("${ROLE.rolecode}" == "jsjl") {
                             createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/accessories/emkAccessories-process&id=" + id, 1200, height);
                         }else{
                             createdetailwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/accessories/emkAccessories-process&id=" + id, 1200, height);
                         }
                     }
                 }

             }
         }
     });
 }

 function doSubmitV() {
     var rowsData = $('#emkAccessoriesList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的缝制辅料开发单');
         return;
     }

     $.dialog.confirm('您是否确定提交缝制辅料开发单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkAccessoriesController.do?doSubmit&id="+rowsData[0].id,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkAccessoriesList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function queryDetail(id,proName,state){
     $('#emkAccessoriesList').datagrid('unselectAll');
     var title = "缝制辅料："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&type=accessories&state="+state+"&sampleId=" + id);
 }
 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkAccessoriesController.do?upload', "emkAccessoriesList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkAccessoriesController.do?exportXls","emkAccessoriesList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkAccessoriesController.do?exportXlsByT","emkAccessoriesList");
}

 </script>