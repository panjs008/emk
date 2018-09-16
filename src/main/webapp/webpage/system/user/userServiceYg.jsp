<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>

<div class="easyui-layout" style="width:700px;height:500px;">

    <div data-options="region:'center'">
<t:datagrid name="userList0" checkbox="false" pagination="true" fitColumns="true" title="" width="100%" actionUrl="userController.do?ygservicedatagrid"
             idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
            <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
    <t:dgCol title="服务商名称" sortable="false" width="150" field="cus_name" query="false"></t:dgCol>
    <t:dgCol title="角色" sortable="false" width="130" field="userkey" query="false"></t:dgCol>
    <t:dgCol title="服务商ID" sortable="false" width="150" hidden="true" field="cus_id" query="false"></t:dgCol>
    <t:dgCol title="common.username" sortable="false" hidden="true" width="90" field="userName" query="true"></t:dgCol>
            <t:dgCol title="用户名称" field="realName" query="true" width="120"></t:dgCol>

    <t:dgCol title="手机号" sortable="false" width="150" hidden="true" field="mobilePhone" query="false"></t:dgCol>

    <%--<t:dgCol title="common.status" sortable="true" field="status"  width="70" replace="common.active_1,common.inactive_0,super.admin_-1" ></t:dgCol>--%>
        </t:datagrid>
    </div>
</div>

<script type="text/javascript">

</script>
