<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkBzgydList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkBzgydController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="200" frozenColumn="true"></t:dgCol>
      <t:dgCol title="订单号"  field="orderNo"  queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesser"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="80"></t:dgCol>
      <%--<t:dgCol title="生产跟单员"  field="developer"  queryMode="single"  width="80"></t:dgCol>--%>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="180"></t:dgCol>
      <t:dgCol title="图片"  image="true"  imageSize="30,30" field="customSampleUrl"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgFunOpt funname="queryDetail2(id,orderNo)" title="主辅料" urlclass="ace_button" ></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail3(id,orderNo)" title="条码" urlStyle="background-color:#ec4758;" urlclass="ace_button" ></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail4(id,orderNo)" title="包装" urlStyle="background-color:#18a689;" urlclass="ace_button" ></t:dgFunOpt>
      <t:dgFunOpt funname="queryDetail5(id,orderNo)" title="纸箱" urlStyle="background-color:#ec4758;" urlclass="ace_button" ></t:dgFunOpt>
      <t:dgToolBar title="录入" icon="fa fa-plus" url="emkBzgydController.do?goAdd&winTitle=录入包装工艺单" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkBzgydController.do?goUpdate&winTitle=编辑包装工艺单" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkBzgydController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/bzgyd/emkBzgydList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });


 function queryDetail2(id,proName){
     var rowsData = $('#emkBzgydList').datagrid('getSelections');

     var title = "主辅料清单："+proName+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&sampleType=order&sampleId=" + id);
 }
 function queryDetail3(id,proName){
     var rowsData = $('#emkBzgydList').datagrid('getSelections');
     var title = "条码："+proName+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkProOrderBarcodeController.do?list&orderId=" + id);
 }

 function queryDetail4(id,proName){
     var rowsData = $('#emkBzgydList').datagrid('getSelections');
     var title = "包装："+proName+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkProOrderPackageController.do?list&orderId=" + id);
 }

 function queryDetail5(id,proName){
     var rowsData = $('#emkBzgydList').datagrid('getSelections');
     var title = "纸箱："+proName +","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 750});

     $('#proDetialListpanel').panel("refresh", "emkProOrderBoxController.do?list&orderId=" + id);
 }
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkBzgydController.do?upload', "emkBzgydList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkBzgydController.do?exportXls","emkBzgydList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkBzgydController.do?exportXlsByT","emkBzgydList");
}

 </script>