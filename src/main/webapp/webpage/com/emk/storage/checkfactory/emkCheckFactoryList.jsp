<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkCheckFactoryList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkCheckFactoryController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="验厂申请表编号" query="true" field="ycsqbh"  queryMode="single"  width="150" frozenColumn="true"></t:dgCol>
      <t:dgCol title="业务部门" query="true"  field="businesseDeptName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="审核类型" query="true"  field="checkType"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="验厂标准"  field="brand"  queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="审核项目" query="true"  field="checkItem" replace="反恐_0,质量_1,人权_2,环保_3,其他_4" queryMode="single"  width="80"></t:dgCol>

      <%--<t:dgCol title="申请日期"  field="kdDate"  queryMode="single"  width="80"></t:dgCol>--%>
      <t:dgCol title="申请审核日期"  field="shDate"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="工厂名称" field="factoryCode"  dictionary="gongchang" queryMode="single"  width="130"></t:dgCol>
      <t:dgCol title="联系人" field="relationer"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="电话" field="telphone"   queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务跟单员"  field="tracerName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="生产跟单员"  field="developerName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="100"></t:dgCol>
      <%--<t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="130"></t:dgCol>--%>
      <%--<t:dgCol title="供应商" query="true" field="gys"  queryMode="single"  width="130"></t:dgCol>--%>
      <t:dgCol title="申请表状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="验厂状态"  field="ycstate" formatterjs="formatColor2"  queryMode="single"  width="100"></t:dgCol>

      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add"  url="emkCheckFactoryController.do?goAdd&winTitle=录入验厂申请" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkCheckFactoryController.do?goUpdate&winTitle=编辑验厂申请" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkCheckFactoryController.do?goUpdate&winTitle=查看验厂申请" funname="detail" height="580" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>

      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkCheckFactoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/checkfactory/emkCheckFactoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function formatColor(val, row) {
     if (row.state == "1") {
         return '<span style="color:	#FF0000;">处理中</span>';
     } else if (row.state == "2") {
         return '<span style="color:	#0000FF;">完成</span>';
     } else {
         return '创建';
     }
 }

 function formatColor2(val, row) {
     if (row.ycstate == "0") {
         return '未分配验厂员';
     } else if (row.state == "1") {
         return '<span style="color:	#0000FF;">已分配验厂员</span>';
     }
 }

 function doSubmitV() {
     var rowsData = $('#emkCheckFactoryList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的验厂申请单');
         return;
     }
     for (var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交验厂申请单?', function (r) {
         if (r) {
             $.ajax({
                 url: "emkCheckFactoryController.do?doSubmit&ids=" + ids,
                 type: 'post',
                 cache: false,
                 data: null,
                 success: function (data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkCheckFactoryList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id,createBy,processName,state){
     var height = window.top.document.body.offsetHeight * 0.85;
     var processNameVal='',node='';
     if(processName != null){
         if(processName.indexOf('-') > 0){
             processNameVal = processName.substring(0,processName.indexOf('-'));
             node = processName.substring(processName.indexOf('-')+1);
         }
     }
     console.log(processName);
     if(createBy == "${CUR_USER.userName}"){
         if(state == '0'){
             createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=" + id, 1200, height);
         }else {
             createdetailwindow('流程进度--当前环节：' + processNameVal, 'flowController.do?goProcess&node='+node+'&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=' + id, 1200, height);
         }
     }else {
         if (state == '2') {
             createdetailwindow('流程进度--当前环节：完成', 'flowController.do?goProcess&node='+node+'&processUrl=com/emk/storage/enquiry/emkEnquiry-process&id=' + id, 1155, height);
         } else {
             if(processNameVal =="领导审核" && "${ROLE.rolecode}" == "ywjl") {
                 createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=" + id, 1200, height);
             }else{
                 createdetailwindow('流程进度--当前环节：' + processNameVal, 'flowController.do?goProcess&node='+node+'&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=' + id, 1200, height);
             }
         }
     }
    /* $.ajax({
         url: "flowController.do?getCurrentProcess&tableName=emk_check_factory&title=验厂申请单&id=" + id,
         type: 'post',
         cache: false,
         data: null,
         success: function (data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(processName == "" && createBy == "${CUR_USER.userName}"){
                     createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=' + id, 1200, height);
                 }else{
                     if (msg == "完成") {
                         createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=' + id, 1200, height);
                     } else {
                         if(processName =="领导审核" && "${ROLE.rolecode}" == "ycjl") {
                             createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=" + id, 1200, height);
                         }else if(processName =="查验" && "${ROLE.rolecode}" == "ycy") {
                             createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=" + id, 1200, height);
                         }else if(processName =="报告" && "${ROLE.rolecode}" == "ycy") {
                             createwindow("流程进度--当前环节：" + msg, "flowController.do?goProcess&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=" + id, 1200, height);
                         }else{
                             createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/checkfactory/emkCheckFactory-process&id=' + id, 1200, height);
                         }
                     }
                 }
             }
         }
     });*/
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkCheckFactoryController.do?upload', "emkCheckFactoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkCheckFactoryController.do?exportXls","emkCheckFactoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkCheckFactoryController.do?exportXlsByT","emkCheckFactoryList");
}

 </script>