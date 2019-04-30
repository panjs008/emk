<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMaterialContractList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="emkMaterialContractController.do?datagrid&type=0" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <t:dgCol title="原料面料开发费付款申请单请单编号" frozenColumn="true"  query="true"  field="payNo" queryMode="single" width="130"></t:dgCol>
      <t:dgCol title="原料面料需求单号"  query="true"  field="requireNo" queryMode="single" width="140"></t:dgCol>
      <t:dgCol title="原料面料采购单号"  query="true"  field="caigouNo" queryMode="single" width="140"></t:dgCol>
      <t:dgCol title="打样通知单号"  query="true"  field="dytzdNo" queryMode="single" width="130"></t:dgCol>
      <t:dgCol title="打样需求单号"  query="true"  field="dyxqdNo" queryMode="single" width="130"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码" field="cusNum"  queryMode="single"  width="75"></t:dgCol>
      <t:dgCol title="客户名称" field="cusName"  queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <%--<t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>--%>
      <%--<t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>--%>
      <%--<t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>--%>
      <%--<t:dgFunOpt funname="goToProcess(id,createBy,createName)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>--%>
      <t:dgCol title="来源"  field="formType" replace="手工创建_0,采购需求单生成_1" queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="100"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkMaterialContractController.do?goAdd&type=0&winTitle=录入原料面料开发费付款申请单" funname="add" height="600" width="1200"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkMaterialContractController.do?goUpdate&type=0&winTitle=编辑原料面料开发费付款申请单" funname="update" height="600" width="1200"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkMaterialContractController.do?goUpdate&type=0&winTitle=查看原料面料开发费付款申请单" funname="detail" height="600" width="1200"></t:dgToolBar>

      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-right" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

      <%--<t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkMaterialContractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/materialcontract/emkMaterialContractList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function formatColor(val,row){
     if(row.state=="1"){
         return '<span style="color:	#0000FF;">已提交</span>';
     }else if(row.state=="2"){
         return '<span style="color:	#00FF00;">完成</span>';
     }else if(row.state=="3"){
         return '<span style="color:	#0000FF;">业务经理通过</span>';
     }else if(row.state=="35"){
         return '<span style="color:	#0000FF;">业务员通过</span>';
     }else if(row.state=="24"){
         return '<span style="color:	#0000FF;">采购员通过</span>';
     }else if(row.state=="15" || row.state=="41"  || row.state=="42"){
         return '<span style="color:	#0000FF;">采购经理通过</span>';
     }else if(row.state=="37"){
         return '<span style="color:	#0000FF;">采购员执行</span>';
     }else if(row.state=="38"){
         return '<span style="color:	#0000FF;">采购员进度</span>';
     }else if(row.state=="41"){
         return '<span style="color:	#0000FF;">采购部经理通过</span>';
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
     if(createBy == "${CUR_USER.userName}" || ${CUR_USER.userName eq 'admin'}){
         if(state == '0'){
             createwindow("流程进度--当前环节：原料面料开发费付款申请单", "flowController.do?goProcess&node="+node+"&processUrl=com/emk/bill/materialcontract/emkMaterialContract-process&id=" + id, 1300, height);
         }else {
             createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl=com/emk/bill/materialcontract/emkMaterialContract-process&id=" + id, 1300, height);
         }
     }
 }

 function doSubmitV() {
     var rowsData = $('#emkMaterialContractList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的原料面料开发费付款申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交原料面料开发费付款申请单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkMaterialContractController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkMaterialContractList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }



 /*function formatColor(val,row){
     if(row.state=="1"){
         return '<span style="color:	#FF0000;">处理中</span>';
     }else if(row.state=="2"){
         return '<span style="color:	#0000FF;">完成</span>';
     }else{
         return '创建';
     }
 }
 function doSubmitV() {
     var rowsData = $('#emkMaterialContractList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的采购原料面料开发费付款申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交采购原料面料开发费付款申请单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkMaterialContractController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkMaterialContractList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function goToProcess(id,createBy,createName){
     var height =window.top.document.body.offsetHeight*0.85;

     $.ajax({
         url : "emkMaterialContractController.do?getCurrentProcess&id="+id,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 if(createName == "入库" && createName == "出库"  && createBy == "${CUR_USER.userName}"){
                     createdetailwindow('流程进度--当前环节：'+msg,'emkMaterialContractController.do?goProcess&id='+id,1150,height);
                 }else{
                     if (msg == "完成") {
                         createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/price/emkPrice-process&id=' + id, 1230, height);
                     } else {
                         if(createName == "领导审核" &&  "${ROLE.rolecode}" == "cgjl") {
                             createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                         }else if(createName == "预采购合同" &&  "${ROLE.rolecode}" == "cw") {
                             createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                         }else if(createName == "正式合同" &&  "${ROLE.rolecode}" == "cwjl") {
                             createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                         }else if((createName == "入库" || createName == "出库" ) &&  "${ROLE.rolecode}" == "cgy") {
                             createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                         }else{
                             createdetailwindow('流程进度--当前环节：'+msg,'emkMaterialContractController.do?goProcess&id='+id,1150,height);
                         }
                     }
                 }
             }
         }
     });
 }*/

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMaterialContractController.do?upload', "emkMaterialContractList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMaterialContractController.do?exportXls","emkMaterialContractList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMaterialContractController.do?exportXlsByT","emkMaterialContractList");
}

 </script>