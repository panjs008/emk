<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>

<div class="easyui-layout" style="width:700px;height:500px;">

    <div data-options="region:'center'">
<t:datagrid name="userList0" checkbox="true" pagination="true" fitColumns="true" title="" width="100%" actionUrl="userController.do?userdeptdatagrid2"
             idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
            <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
    <t:dgCol title="组织机构" sortable="false" width="150" field="copyDeptName" query="false"></t:dgCol>
    <t:dgCol title="组织机构" sortable="false" hidden="true" field="copyDeptCode" query="false"></t:dgCol>
    <t:dgCol title="角色" sortable="false" width="130" field="userkey2" query="false"></t:dgCol>
            <t:dgCol title="用户名称" field="copyer" query="true" width="120"></t:dgCol>
    <t:dgCol title="用户名称" field="copyerUserNames" hidden="true"  width="120"></t:dgCol>
</t:datagrid>
    </div>
</div>

<script type="text/javascript">

</script>
