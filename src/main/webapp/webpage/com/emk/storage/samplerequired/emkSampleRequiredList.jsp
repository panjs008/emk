<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkSampleRequiredList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkSampleRequiredController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="100" frozenColumn="true"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" frozenColumn="true"  width="140"></t:dgCol>--%>
   <t:dgCol title="需求单号"  field="requiredNo" frozenColumn="true" queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="交货日期"  field="ysDate"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>
   <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>
   <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="颜色名称"  field="colorZnName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="颜色名称"  field="colorZnName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="来源"  field="formType" replace="手工创建_0,询盘单生成_1" queryMode="single"  width="80"></t:dgCol>
      <%--<t:dgCol title="状态" replace="创建_0,已生成通知单_1"  field="state"  queryMode="single"  width="90"></t:dgCol>--%>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="90"></t:dgCol>

      <%-- <t:dgFunOpt funname="queryDetail2(id,sampleNo)" title="主辅料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>
       <t:dgFunOpt funname="queryDetail3(id,sampleNo)" title="工序" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>--%>
      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkSampleRequiredController.do?goAdd&winTitle=录入样品需求单" funname="add" height="600" width="1260"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit"  url="emkSampleRequiredController.do?goUpdate&winTitle=编辑样品需求单" funname="update" height="600" width="1260"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkSampleRequiredController.do?goUpdate&winTitle=查看样品需求单" funname="detail" height="600" width="1260"></t:dgToolBar>
      <%--<t:dgToolBar title="生成打样通知单" operationCode="submit" icon="fa fa-arrow-circle-up"  funname="doCreateYptzd"></t:dgToolBar>--%>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up"  funname="doSubmitV"></t:dgToolBar>

      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkSampleRequiredController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
<%--<div data-options="region:'east',
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
</div>--%>
 <script src = "webpage/com/emk/storage/samplerequired/emkSampleRequiredList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function queryDetail1(id,proName){
     $('#emkSampleRequiredList').datagrid('unselectAll');
     var title = "技术参数表：" +proName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "emkSampleTotalController.do?list&sampleId=" + id);
 }
 function queryDetail2(id,proName){
     $('#emkSampleRequiredList').datagrid('unselectAll');
     var title = "主辅料清单："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&sampleType=samplerequired&state=0&sampleId=" + id);
 }


 function queryDetail3(id,proName){
     $('#emkSampleRequiredList').datagrid('unselectAll');
     var title = "样品工序："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleGxController.do?list&state=0&sampleId=" + id);
 }

 function formatColor(val,row){
     if(row.state=="1"){
         return '<span style="color:	#0000FF;">已提交</span>';
     }else if(row.state=="2"){
         return '<span style="color:	#00FF00;">完成</span>';
     }else if(row.state=="3"){
         return '<span style="color:	#0000FF;">业务经理通过</span>';
     }else if(row.state=="31"){
         return '<span style="color:	#0000FF;">打样</span>';
     }else{
         return '创建';
     }
 }

 function goToProcess(id,createBy,processName,state){
     var height =window.top.document.body.offsetHeight*0.85;
     var processNameVal='',node='';
     if(processName != null){
         if(processName.indexOf('-') > 0){
             processNameVal = processName.substring(0,processName.indexOf('-'));
             node = processName.substring(processName.indexOf('-')+1);
         }
     }
     if(createBy == "${CUR_USER.userName}"){
         if(state == '0' || state=='19'){
             createwindow("流程进度--当前环节：业务经理审核", "flowController.do?goProcess&node="+node+"&processUrl=com/emk/storage/samplerequired/emkSampleRequired-process&id=" + id, 1280, height);
         }else {
             createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl=com/emk/storage/samplerequired/emkSampleRequired-process&id=" + id, 1280, height);
         }
     }else{
         if(state == '2'){
             createdetailwindow('流程进度--当前环节：完成' , 'flowController.do?goProcess&processUrl=com/emk/storage/samplerequired/emkSampleRequired-process&id=' + id, 1280, height);
         }else{
             if((state == '1' || state == '4') && "${ROLE.rolecode}" == "ywjl" || (state == '3' || state == '6') && "${ROLE.rolecode}" == "jsjl") {
                 createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl=com/emk/storage/samplerequired/emkSampleRequired-process&id=" + id, 1280, height);
             }else{
                 createdetailwindow('流程进度--当前环节：' + processNameVal , 'flowController.do?goProcess&processUrl=com/emk/storage/samplerequired/emkSampleRequired-process&id=' + id, 1280, height);
             }
         }
     }
 }
 function doSubmitV() {
     var rowsData = $('#emkSampleRequiredList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的样品需求单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交样品需求单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkSampleRequiredController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkSampleRequiredList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }
 function doCreateYptzd() {
     var rowsData = $('#emkSampleRequiredList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要生成打样通知单的样品需求单');
         return;
     }
     createwindow("生成打样通知单", "emkSampleRequiredController.do?goSelectUser&id="+rowsData[0].id,500,100);
 }

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkSampleRequiredController.do?upload', "emkSampleRequiredList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkSampleRequiredController.do?exportXls","emkSampleRequiredList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkSampleRequiredController.do?exportXlsByT","emkSampleRequiredList");
}

 </script>