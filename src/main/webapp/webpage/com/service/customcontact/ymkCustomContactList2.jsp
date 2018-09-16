<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="ymkCustomContactList" checkbox="true" pagination="true" fitColumns="false"  title="联系人表" actionUrl="ymkCustomContactController.do?datagrid&customId=${param.customId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
 <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="姓名"  field="userName"  queryMode="single"  width="80"></t:dgCol>
 <t:dgCol title="性别"  field="sex" replace="男_0,女_1"  queryMode="single"  width="40"></t:dgCol>
 <t:dgCol title="职务"  field="position"  queryMode="single"  width="80"></t:dgCol>
 <t:dgCol title="生日"  field="brithDay" hidden="true"  queryMode="single"  width="80"></t:dgCol>
 <t:dgCol title="E-mail"  field="email"  queryMode="single"  width="70"></t:dgCol>
 <t:dgCol title="手机"  field="telphone"  queryMode="single"  width="80"></t:dgCol>
 <t:dgCol title="电话"  field="mobile"  queryMode="single"  width="80"></t:dgCol>
 <t:dgCol title="备注"  field="remark" hidden="true" queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="客户ID"  field="customId" hidden="true" queryMode="single"  width="120"></t:dgCol>
 <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomContactController.do?goAdd&&customId=${param.customId}" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomContactController.do?goUpdate" funname="update"></t:dgToolBar>
 <t:dgToolBar title="删除"  icon="fa fa-remove" url="ymkCustomContactController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
 <t:dgToolBar title="查看" icon="fa fa-search" url="ymkCustomContactController.do?goUpdate" funname="detail"></t:dgToolBar>
 <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
 <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
</t:datagrid>
 <script src = "webpage/com/service/customcontact/ymkCustomContactList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'ymkCustomContactController.do?upload', "ymkCustomContactList");
}

//导出
function ExportXls() {
	JeecgExcelExport("ymkCustomContactController.do?exportXls","ymkCustomContactList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("ymkCustomContactController.do?exportXlsByT","ymkCustomContactList");
}

 </script>