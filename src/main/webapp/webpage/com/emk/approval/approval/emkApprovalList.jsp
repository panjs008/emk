<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkApprovalList" checkbox="false" pagination="true" fitColumns="true" pageSize="15" title="审批业务表" actionUrl="emkApprovalController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="80" ></t:dgCol>
      <t:dgCol title="单号"  field="workNum"  query="true" queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="工单类型"  field="type"  replace="意向询盘单_0,验厂申请单_1,报价单_2,样品需求单_3,样品材料采购_4,入库申请单_5" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="提交人"  field="createName"    queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

   <t:dgCol title="申请人ID"  field="commitId" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提交时间"  field="commitTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前节点名称"  field="processName" formatterjs="formatProcessName" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前节点代码"  field="processNode"  hidden="true" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="formId"  field="formId"  hidden="true" queryMode="single"  width="120"></t:dgCol>

      <t:dgCol title="节点状态"  field="status" formatterjs="formatColor"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="审核人"  field="bpmSher"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="审核状态"  field="bpmStatus" formatterjs="formatBpmStateColor" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="审核人ID"  field="bpmSherId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核时间"  field="bpmDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title=""  field="state1" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title=""  field="state2" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title=""  field="state3" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(formId,commitId,processNode,status,type,state1,state2,state3)" title="审批"  urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <%--<t:dgToolBar title="录入" icon="fa fa-plus" url="emkApprovalController.do?goAdd" funname="add"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkApprovalController.do?goUpdate" funname="update"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" url="emkApprovalController.do?goUpdate" funname="detail"></t:dgToolBar>
      <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/approval/approval/emkApprovalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function formatColor(val,row){
     if(row.status=="1"){
         return '<span style="color:	#0000FF;">已提交</span>';
     }else if(row.status=="2"){
         return '<span style="color:	#00FF00;">完成</span>';
     }else if(row.status=="3"){
         return '<span style="color:	#0000FF;">业务经理通过</span>';
     }else if(row.status=="4"){
         return '<span style="color:	#FF0000;">回退业务经理</span>';
     }else if(row.status=="5"){
         return '<span style="color:	#0000FF;">技术经理通过</span>';
     }else if(row.status=="6"){
         return '<span style="color:	#0000FF;">回退技术经理</span>';
     }else if(row.status=="7"){
         return '<span style="color:	#0000FF;">染色部经理通过</span>';
     }else if(row.status=="8"){
         return '<span style="color:	#0000FF;">回退染色部经理</span>';
     }else if(row.status=="9"){
         return '<span style="color:	#0000FF;">缝制部经理通过</span>';
     }else if(row.status=="10"){
         return '<span style="color:	#0000FF;">回退缝制部经理</span>';
     }else if(row.status=="11"){
         return '<span style="color:	#0000FF;">烫标整烫组长通过</span>';
     }else if(row.status=="12"){
         return '<span style="color:	#0000FF;">回退烫标整烫组长</span>';
     }else if(row.status=="13"){
         return '<span style="color:	#0000FF;">包装组长通过</span>';
     }else if(row.status=="14"){
         return '<span style="color:	#0000FF;">回退包装组长</span>';
     }else if(row.status=="15"){
         return '<span style="color:	#0000FF;">采购部经理通过</span>';
     }else if(row.status=="16"){
         return '<span style="color:	#0000FF;">回退采购部经理</span>';
     }else if(row.status=="17"){
         return '<span style="color:	#0000FF;">生产计划部通过</span>';
     }else if(row.status=="18"){
         return '<span style="color:	#0000FF;">回退生产计划部</span>';
     }else if(row.status=="19"){
         return '<span style="color:	#0000FF;">生产总负责人通过</span>';
     }else if(row.status=="20"){
         return '<span style="color:	#0000FF;">报价</span>';
     }else if(row.status=="21"){
         return '<span style="color:	#0000FF;">回退业务员</span>';
     }else if(row.status=="22"){
         return '<span style="color:	#0000FF;">技术员通过</span>';
     }else if(row.status=="23"){
         return '<span style="color:	#0000FF;">回退技术员</span>';
     }else if(row.status=="24"){
         return '<span style="color:	#0000FF;">采购员通过</span>';
     }else if(row.status=="25"){
         return '<span style="color:	#0000FF;">财务通过</span>';
     }else if(row.status=="26"){
         return '<span style="color:	#0000FF;">财务经理通过</span>';
     }else if(row.status=="27"){
         return '<span style="color:	#0000FF;">回退财务</span>';
     }else if(row.status=="30"){
         return '<span style="color:	#0000FF;">价格确认</span>';
     }else if(row.status=="31"){
         return '<span style="color:	#0000FF;">打样</span>';
     }else if(row.status=="33"){
         return '<span style="color:	#0000FF;">生产跟单员通过</span>';
     }else if(row.status=="35"){
         return '<span style="color:	#0000FF;">业务员通过</span>';
     }else if(row.status=="37"){
         return '<span style="color:	#0000FF;">采购员执行</span>';
     }else if(row.status=="38"){
         return '<span style="color:	#0000FF;">采购员进度</span>';
     }else if(row.status=="39"){
         return '<span style="color:	#0000FF;">入库申请【采购员】</span>';
     }else{
         return '创建';
     }
 }
 function formatBpmStateColor(val,row){
     if(row.bpmStatus=="1"){
         return '<span style="color:	#FF0000;">驳回</span>';
     }else{
         return '<span style="color:	#0000FF;">通过</span>';
     }
 }
function formatProcessName(val,row){
    if(row.processNode != null){
        if(row.processNode.indexOf('-') > 0){
            var processNameVal = row.processNode.substring(0,row.processNode.indexOf('-'));
            return processNameVal;
        }
    }
}
 function goToProcess(id,createBy,processName,state,type,state1,state2,state3){
     var height =window.top.document.body.offsetHeight*0.85;
     var processNameVal='',node='',w=1280;
     if(processName != null){
         if(processName.indexOf('-') > 0){
             processNameVal = processName.substring(0,processName.indexOf('-'));
             node = processName.substring(processName.indexOf('-')+1);
             initProcessName = processNameVal;
         }
     }
     var processUrl,initProcessName;
     if(type == '0'){
         processUrl = 'com/emk/storage/enquiry/emkEnquiry-process';
         initProcessName = '业务部审核';
     }else if(type == '2'){
         processUrl = 'com/emk/storage/price/emkPrice-process';
         initProcessName = '报价单【业务员】';
     }else if(type == '3'){
         processUrl = 'com/emk/storage/samplerequired/emkSampleRequired-process';
         initProcessName = '样品需求【业务跟单员】';
     }else if(type == '4'){
         processUrl = 'com/emk/storage/material/emkMaterial-process';
         initProcessName = '原料面料需求开发单';
         w = 1300;
     }else if(type == '5'){
         processUrl = 'com/emk/bound/minstorage/emkMInStorage-process';
         initProcessName = '入库申请单';
     }
     if(state == '30'){
         initProcessName = processNameVal;
     }
     createwindow("流程进度--当前环节："+initProcessName, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);

     /*if("${ROLE.rolecode}" == "admin" ) {
         createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
     }else{
         if(createBy == "${CUR_USER.id}"){
             if(state == '0' || state=='19' || state == '21' || state == '30'){
                 createwindow("流程进度--当前环节："+initProcessName, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
             }else {
                 createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
             }
         }else{
             if(state == '2'){
                 createdetailwindow('流程进度--当前环节：完成' , 'flowController.do?goProcess&processUrl="+processUrl+"&id=' + id, w, height);
             }else{
                 if((state == '1' || state == '4'|| state == '35') && "${ROLE.rolecode}" == "ywjl" || (state == '3' || state == '6') && "${ROLE.rolecode}" == "jsjl"
                         || (state == '5' || state == '8') && "${ROLE.rolecode}" == "rsjl" || (state == '7' || state == '10') && "${ROLE.rolecode}" == "fzjl"
                         || (state == '9' || state == '12') && "${ROLE.rolecode}" == "btztzz" || (state == '11' || state == '14') && "${ROLE.rolecode}" == "bzzz"
                         || (state == '13' || state == '16' || state == '24') && "${ROLE.rolecode}" == "cgjl" || (state == '15' || state == '18') && "${ROLE.rolecode}" == "scjjbjl"
                         || (state == '17' || state == '20') && "${ROLE.rolecode}" == "sczfr") {
                     createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
                 }else if((state == '1' || state == '23') && "${ROLE.rolecode}" == "jsy" || state == '22' && "${ROLE.rolecode}" == "cgy" || (state == '24' || state == '27') && "${ROLE.rolecode}" == "cw" || state == '25' && "${ROLE.rolecode}" == "cwjl" || state == '26' && "${ROLE.rolecode}" == "zjl"){
                     createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
                 }else if((state == '5' || state == '33') && "${ROLE.rolecode}" == "scgdy" && (node=="dytzdTask" || node=="gxjdTask")){
                     createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
                 }else if((type == '4' && (state == '3'|| state == '36'|| state == '15'|| state == '37'|| state == '38') || (type == '5' && state =='1') ) && "${ROLE.rolecode}" == "cgy"){
                     createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
                 }else if((state == '1') && "${ROLE.rolecode}" == "ywy"){
                     createwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node="+node+"&processUrl="+processUrl+"&id=" + id, w, height);
                 }else{
                     createdetailwindow('流程进度--当前环节：' + processNameVal , 'flowController.do?goProcess&processUrl="+processUrl+"&id=' + id, w, height);
                 }
             }
         }
     }*/
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkApprovalController.do?upload', "emkApprovalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkApprovalController.do?exportXls","emkApprovalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkApprovalController.do?exportXlsByT","emkApprovalList");
}

 </script>