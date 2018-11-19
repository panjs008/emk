<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="emkProOrderBoxList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkProOrderBoxController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="类型"  dictionary="boxtype" field="boxType"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="长"  field="longVal"  queryMode="single"  width="40"></t:dgCol>
    <t:dgCol title="宽"  field="widthVal"  queryMode="single"  width="40"></t:dgCol>
    <t:dgCol title="高"  field="heightVal"  queryMode="single"  width="40"></t:dgCol>
    <t:dgCol title="单件毛重"  field="oneWeightMao"  queryMode="single"  width="60"></t:dgCol>
    <%--<t:dgCol title="单件净重"  field="oneWeightJz"  queryMode="single"  width="60"></t:dgCol>--%>
    <t:dgCol title="箱内数量"  field="inboxTotal"  queryMode="single"  width="60"></t:dgCol>
    <t:dgCol title="单箱毛重"  field="boxWeightMao"  queryMode="single"  width="60"></t:dgCol>
    <%--<t:dgCol title="单箱净重"  field="boxWeightJz"  queryMode="single"  width="60"></t:dgCol>--%>
    <t:dgCol title="单箱体积"  field="boxVolume"  queryMode="single"  width="60"></t:dgCol>
    <t:dgCol title="总体积"  field="sumVolume"  queryMode="single"  width="50"></t:dgCol>
    <t:dgCol title="总净重"  field="sumWeightJz"  queryMode="single"  width="50"></t:dgCol>
    <t:dgCol title="总箱数"  field="sumTotal"  queryMode="single"  width="50"></t:dgCol>
    <c:if test="${param.state eq '0'}">
        <t:dgToolBar title="录入" icon="fa fa-plus"  url="emkProOrderBoxController.do?goAdd&orderId=${param.orderId}" funname="add"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkProOrderBoxController.do?goUpdate" funname="update"></t:dgToolBar>
        <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkProOrderBoxController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    </c:if>

</t:datagrid>
 <script src = "webpage/com/emk/bill/proorderbox/emkProOrderBoxList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProOrderBoxController.do?upload', "emkProOrderBoxList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProOrderBoxController.do?exportXls","emkProOrderBoxList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProOrderBoxController.do?exportXlsByT","emkProOrderBoxList");
}

 </script>