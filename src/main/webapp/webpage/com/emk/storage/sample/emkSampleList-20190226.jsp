<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkSampleList" checkbox="false" pagination="true" sortName="sampleNum" sortOrder="desc" fitColumns="false" title="" actionUrl="emkYptzdController.do?datagrid&flag=${param.flag}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="140"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="245" frozenColumn="true"></t:dgCol>
      <t:dgCol title="样品通知单号" query="true"  field="sampleNum"  queryMode="single"  width="115"></t:dgCol>
      <t:dgCol title="通知单日期"  field="kdTime"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="客户代码" query="true"  field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称"  field="cusName"  queryMode="single"  width="130"></t:dgCol>
      <t:dgCol title="客户原样"  image="true"  imageSize="30,30" field="customSampleUrl"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="尺码表" image="true"  imageSize="30,30"  field="sampleSizeUrl"   queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
      <%--<t:dgCol title="商品名称"  field="proName"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="客户ID"  field="customId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="版次"  field="version"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="工艺种类"  field="gyzl" dictionary="gylx"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="样品类型"  field="type" dictionary="sampletype" queryMode="single"  width="70"></t:dgCol>
      <%--<t:dgCol title="布面克重"  field="weight" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="布面成分"  field="chengf"  queryMode="single"  width="70"></t:dgCol>--%>
   <t:dgCol title="交货时间"  field="receviceDate"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="60"></t:dgCol>
      <%--<t:dgFunOpt funname="queryDetail1(id,sampleNo)" title="数量" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>--%>
      <t:dgFunOpt funname="queryDetail2(id,sampleNum,state)" title="主辅料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail3(id,sampleNum,state)" title="染色" urlStyle="background-color:#ec4758;" urlclass="ace_button" urlfont="fa-file-photo-o"></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail4(id,sampleNum,state)" title="印花" urlStyle="background-color:#18a689;" urlclass="ace_button" urlfont="fa-asterisk"></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail5(id,sampleNum,state)" title="工序" urlStyle="background-color:#ec4758;" urlclass="ace_button" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkYptzdController.do?goAdd&flag=${param.flag}&winTitle=录入样品通知单" funname="add" height="550" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkYptzdController.do?goUpdate&winTitle=编辑样品通知单" funname="update" height="550" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkYptzdController.do?goUpdate&winTitle=查看样品通知单" funname="detail" height="600" width="1210"></t:dgToolBar>
      <t:dgToolBar title="流程进度" operationCode="process" icon="fa fa-plus" funname="goToProcess"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkYptzdController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'清单明细',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
     style="width: 500px; overflow: hidden;" id="eastPanel">
    <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="proDetialListpanel"></div>
</div>
 <script src = "webpage/com/emk/storage/sample/emkSampleList.js"></script>
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
     var rowsData = $('#emkSampleList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的打样单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交打样单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkYptzdController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkSampleList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }
 function goToProcess(id){
     var height =window.top.document.body.offsetHeight*0.85;
     var rowsData = $('#emkSampleList').datagrid('getSelections');
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的打样申请单');
         return;
     }
     $.ajax({
         url: "flowController.do?getCurrentProcess&tableName=emk_sample&title=打样申请单&id=" + rowsData[0].id,
         type: 'post',
         cache: false,
         data: null,
         success: function (data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(rowsData[0].createBy == "${CUR_USER.userName}"){
                     createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/sample/emkSample-process&id=' + rowsData[0].id, 1200, height);
                 }else{
                     if (msg == "完成") {
                         createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/sample/emkSample-process&id=' + rowsData[0].id, 1200, height);
                     } else {
                         if("${ROLE.rolecode}" == "ywjl") {
                             createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/sample/emkSample-process&id=" + rowsData[0].id, 1200, height);
                         }else{
                             createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/sample/emkSample-process&id=' + rowsData[0].id, 1200, height);
                         }
                     }
                 }

             }
         }
     });
 }

 function queryDetail1(id,proName){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "尺寸规格：" +proName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "emkSampleTotalController.do?list&sampleId=" + id);
 }
 function queryDetail2(id,proName,state){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "主辅料清单："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&sampleType=sample&state="+state+"&sampleId=" + id);
 }


 function queryDetail3(id,proName,state){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "样品染色："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleRanController.do?list&type=sample&state="+state+"&sampleId=" + id);
 }

 function queryDetail4(id,proName,state){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "印花染色："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleYinController.do?list&state="+state+"&sampleId=" + id);
 }

 function queryDetail5(id,proName,state){
     $('#emkSampleList').datagrid('unselectAll');
     var title = "样品工序："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleGxController.do?list&state="+state+"&sampleId=" + id);
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkYptzdController.do?upload', "emkSampleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkYptzdController.do?exportXls","emkSampleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkYptzdController.do?exportXlsByT","emkSampleList");
}

 </script>