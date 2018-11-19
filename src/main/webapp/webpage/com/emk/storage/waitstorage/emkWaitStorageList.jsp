<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkWaitStorageList" checkbox="true" pagination="true" fitColumns="true" btnCls="bootstrap"  title="" actionUrl="emkWaitStorageController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="待入库单号"  field="drkNo" query="true"  queryMode="single"  formatterjs="detailOrder" width="120"></t:dgCol>
   <t:dgCol title="菜单订单ID"  field="orderId" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购合同"  field="orderNum" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state" replace="待入库_0,已入库_1"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDelOpt title="删除" operationCode="delete" url="emkWaitStorageController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <%--<t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkWaitStorageController.do?goAdd" funname="add"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkWaitStorageController.do?goUpdate" funname="update"></t:dgToolBar>--%>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkWaitStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="emkWaitStorageController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" operationCode="exp" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/storage/waitstorage/emkWaitStorageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function detailOrder(val,row,index){
     $('#emkOrderList').datagrid('unselectAll');
     var s = '<a href="javascript:addOneTab(\''+row.drkNo+'-待入库商品信息\',\'emkOrderDetailController.do?drklist&drkNo='+row.drkNo+'&drkId='+row.id+'&orderId='+row.orderId+'\')">'+row.drkNo+'</a>';
     return s;
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkWaitStorageController.do?upload', "emkWaitStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkWaitStorageController.do?exportXls","emkWaitStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkWaitStorageController.do?exportXlsByT","emkWaitStorageList");
}

 </script>