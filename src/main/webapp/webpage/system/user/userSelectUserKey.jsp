<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<div class="easyui-layout" style="width:700px;height:500px;">
    <div data-options="region:'center'">
        <t:datagrid checkbox="false" name="userList1" title="common.user.select" actionUrl="userController.do?datagrid&userKey=${param.userKey }"
                    fit="true" fitColumns="true" idField="id" queryMode="group" sortName="createDate" sortOrder="desc">
            <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="common.username" sortable="false" field="userName" query="true" width="150"></t:dgCol>
            <t:dgCol title="common.department" sortable="false" field="userOrgList.tsDepart.departname" width="150" query="false"></t:dgCol>
            <t:dgCol title="common.real.name" field="realName" query="false" width="150"></t:dgCol>
            <t:dgCol title="common.role" field="userKey" width="150"></t:dgCol>
            <t:dgCol title="common.createby" field="createBy" hidden="true"></t:dgCol>
            <t:dgCol title="common.createtime" field="createDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
            <t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
            <t:dgCol title="common.updatetime" field="updateDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
        </t:datagrid>
    </div>
</div>

<script type="text/javascript">

</script>
