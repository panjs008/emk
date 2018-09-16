<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
 <div region="center" style="padding:0px;border:0px">
<t:datagrid name="ymkCustomContactList" checkbox="true" title="社会化工程师列表" pagination="true" fitColumns="false"  actionUrl="ymkCustomContactController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
 <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="服务商名称"  field="customName" query="true" queryMode="single"  width="200"></t:dgCol>
 <t:dgCol title="用户账号"  field="createName"  query="true"  queryMode="single" dictionary="t_s_base_user,id,username"   width="120"></t:dgCol>

 <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="姓名"  field="userName" query="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="性别"  field="sex" replace="男_0,女_1"  queryMode="single"  width="60"></t:dgCol>
 <t:dgCol title="职务"  field="position"  queryMode="single"  width="180"></t:dgCol>
 <t:dgCol title="生日"  field="brithDay" hidden="true"  queryMode="single"  width="120"></t:dgCol>
 <t:dgCol title="E-mail"  field="email"  queryMode="single"  width="150"></t:dgCol>
 <t:dgCol title="手机"  field="telphone"  queryMode="single"  width="150"></t:dgCol>
 <t:dgCol title="电话"  field="mobile"  queryMode="single"  width="150"></t:dgCol>
 <t:dgCol title="备注"  field="remark" hidden="true" queryMode="single"  width="120"></t:dgCol>
 <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomContactController.do?goAdd1&customId=${param.customId}" funname="add" width="960" height="450"></t:dgToolBar>
 <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomContactController.do?goUpdate1" funname="update" width="960" height="450"></t:dgToolBar>
 <t:dgToolBar title="删除"  icon="fa fa-remove" url="ymkCustomContactController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
 <%--<t:dgToolBar title="查看" icon="fa fa-search" url="ymkCustomContactController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
 <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>--%>
 <%--<t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
</t:datagrid>
 </div>
</div>
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