<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="uSignEquiperList" checkbox="true" pagination="true" fitColumns="true" title="设备中标商管理" actionUrl="uSignEquiperController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编码"  field="cusNum"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="中标商名称"  field="cusName"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="客户简称"  field="cusCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"  field="shengFen"  queryMode="single"  dictionary="t_s_category,code,name" width="90"></t:dgCol>
   <t:dgCol title="城市"  field="chengShi"  queryMode="single" dictionary="t_s_category,code,name"  width="90"></t:dgCol>
   <t:dgCol title="片区"  field="pianQu"  queryMode="single" dictionary="t_s_category,code,name"  width="90"></t:dgCol>
   <t:dgCol title="业主ID"  field="ownerId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="relationer"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="联系人电话"  field="relationerTel"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="90"></t:dgCol>
      <%-- <t:dgToolBar title="录入" icon="fa fa-plus" url="uSignEquiperController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="uSignEquiperController.do?goUpdate" funname="update"></t:dgToolBar>--%>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="uSignEquiperController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="uSignEquiperController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/uflow/signequiper/uSignEquiperList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'uSignEquiperController.do?upload', "uSignEquiperList");
}

//导出
function ExportXls() {
	JeecgExcelExport("uSignEquiperController.do?exportXls","uSignEquiperList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("uSignEquiperController.do?exportXlsByT","uSignEquiperList");
}

 </script>