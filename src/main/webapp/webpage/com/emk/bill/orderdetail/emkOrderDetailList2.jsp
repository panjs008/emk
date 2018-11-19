<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkOrderDetailList" checkbox="true"  pagination="true" fitColumns="false" title="订单商品表" actionUrl="emkOrderDetailController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品编号"  field="proNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中文描述"  field="proNameZn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="英文描述"  field="proNameEn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格型号"  field="brand"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签约数量"  field="signTotal"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签约单位"  field="signUnit"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签约单价"  field="signPrice"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="不含税金额"  field="notSignPrice"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="增值税率"  field="zzsl"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="增值税额"  field="zzse"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="退税率"  field="tsl"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="退税额"  field="tse"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="海关商品编码"  field="hairProNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单ID"  field="orderId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" operationCode="delete" url="emkOrderDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderDetailController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOrderDetailController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete" operationCode="delete"  icon="fa fa-remove" url="emkOrderDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" url="emkOrderDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/orderdetail/emkOrderDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOrderDetailController.do?upload', "emkOrderDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOrderDetailController.do?exportXls","emkOrderDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOrderDetailController.do?exportXlsByT","emkOrderDetailList");
}

 </script>