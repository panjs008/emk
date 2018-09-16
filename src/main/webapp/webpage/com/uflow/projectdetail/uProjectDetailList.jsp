<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="uProjectDetailList" checkbox="false" pagination="true" fitColumns="true" title="项目：${projectName}" actionUrl="uProjectDetailController.do?datagrid&projectId=${param.projectId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="项目ID"  field="projectId" hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="设备名称"  field="proName"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="设备ID"  field="proId" hidden="true" queryMode="single"  width="120"></t:dgCol>
    <%--<t:dgCol title="设备类型"  field="sblx"  queryMode="single"  width="90"></t:dgCol>--%>
    <t:dgCol title="品牌"  field="pp"  queryMode="single" dictionary="t_s_category,code,name"   width="90"></t:dgCol>
    <t:dgCol title="型号"  field="xh"  queryMode="single" dictionary="t_s_category,code,name"  width="90"></t:dgCol>
    <t:dgCol title="单位"  field="unit"  queryMode="single"  width="70"></t:dgCol>
    <t:dgCol title="单价"  field="price"  queryMode="single"  width="70"></t:dgCol>
    <t:dgCol title="数量"  field="total"  queryMode="single"  width="65"></t:dgCol>
    <t:dgCol title="总价"  field="sumPrice"  queryMode="single"  width="80"></t:dgCol>
    <t:dgCol title="质保年限" hidden="true" field="zbTime"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="备注" hidden="true"  field="remark"  queryMode="single"  width="120"></t:dgCol>
    <t:dgToolBar title="录入" icon="fa fa-plus" url="uProjectDetailController.do?goAdd&projectId=${param.projectId}" funname="add" width="800" height="450"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="fa fa-edit" url="uProjectDetailController.do?goUpdate" funname="update" width="800" height="450"></t:dgToolBar>
    <t:dgToolBar title="删除"  icon="fa fa-remove" url="uProjectDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    <%--<t:dgToolBar title="查看" icon="fa fa-search" url="uProjectDetailController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
    <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
    <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
</t:datagrid>
 <script src = "webpage/com/uflow/projectdetail/uProjectDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'uProjectDetailController.do?upload', "uProjectDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("uProjectDetailController.do?exportXls","uProjectDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("uProjectDetailController.do?exportXlsByT","uProjectDetailList");
}

 </script>