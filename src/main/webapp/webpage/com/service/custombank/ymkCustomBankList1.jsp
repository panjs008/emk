<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
 <div region="center" style="padding:0px;border:0px">
<t:datagrid name="ymkCustomBankList" checkbox="true"  pagination="true" fitColumns="false" title="" actionUrl="ymkCustomBankController.do?datagrid&customId=${param.customId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
 <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="客户"  query="true"  field="customName" queryMode="single"  width="200"></t:dgCol>
 <t:dgCol title="银行名称" query="true" field="bankName"  queryMode="single"  width="150"></t:dgCol>
 <t:dgCol title="银行账号" query="true" field="bankAccount"  queryMode="single"  width="200"></t:dgCol>
 <t:dgCol title="银行户名"  field="bankAccountName"  queryMode="single"  width="200"></t:dgCol>
 <t:dgCol title="SWIFT号"  field="swifi"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="币种"  field="bz"  queryMode="single"  width="90"></t:dgCol>
 <t:dgCol title="默认账号"  field="state"  queryMode="single"  width="90"></t:dgCol>
 <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomBankController.do?goAdd1&customId=${param.customId}" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomBankController.do?goUpdate1" funname="update"></t:dgToolBar>
 <t:dgToolBar title="删除"  icon="fa fa-remove" url="ymkCustomBankController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
</t:datagrid>
 </div>
</div>
 <script src = "webpage/com/service/custombank/ymkCustomBankList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'ymkCustomBankController.do?upload', "ymkCustomBankList");
}

//导出
function ExportXls() {
	JeecgExcelExport("ymkCustomBankController.do?exportXls","ymkCustomBankList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("ymkCustomBankController.do?exportXlsByT","ymkCustomBankList");
}

 </script>