<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
    var setting = {
        check: {
            enable: false,
            chkboxType: { "Y": "", "N": "" }
        },
        data: {
            simpleData: {
                enable: true
            }
        },callback: {
            onExpand: zTreeOnExpand,
            onClick:onClick
        }
    };
    function onClick(event, treeId, treeNode){
        var departname = treeNode.name;
        var queryParams = $('#userList0').datagrid('options').queryParams;
        queryParams.orgIds = treeNode.id;
        $('#userList0').datagrid('options').queryParams=queryParams;
        $("#userList0").datagrid("reload");
    }
    //加载展开方法
    function zTreeOnExpand(event, treeId, treeNode){
        var treeNodeId = treeNode.id;
        $.post(
                'departController.do?getDepartInfo',
                {parentid:treeNodeId},
                function(data){
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var dbDate = eval(d.msg);
                        var tree = $.fn.zTree.getZTreeObj("departSelect");

                        if (!treeNode.zAsync){
                            tree.addNodes(treeNode, dbDate);
                            treeNode.zAsync = true;
                        } else{
                            tree.reAsyncChildNodes(treeNode, "refresh");
                        }
                        //tree.addNodes(treeNode, dbDate);
                    }
                }
        );
    }

    //首次进入加载level为1的
    $(function(){
        $.post(
                'departController.do?getDepartInfo',
                function(data){
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var dbDate = eval(d.msg);
                        $.fn.zTree.init($("#departSelect"), setting, dbDate);
                    }
                }
        );
    });
</script>
<div class="easyui-layout" style="width:700px;height:500px;">
    <div data-options="region:'west',split:true" title="<t:mutiLang langKey='common.department'/>" style="width:200px;">
        <ul id="departSelect" class="ztree" ></ul>
    </div>
    <div data-options="region:'center'">
<t:datagrid name="userList0" checkbox="true" pagination="true" fitColumns="true" title="" width="500px" actionUrl="userController.do?datagrid&userFlag=0"
             idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
            <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
    <t:dgCol title="common.department" sortable="false" width="170" field="userOrgList.tsDepart.departname" query="false"></t:dgCol>
    <t:dgCol title="common.username" sortable="false" width="90" field="userName" query="true"></t:dgCol>
            <t:dgCol title="common.real.name" field="realName" query="false" width="120"></t:dgCol>
            <t:dgCol title="common.status" sortable="true" field="status"  width="70" replace="common.active_1,common.inactive_0,super.admin_-1" ></t:dgCol>
        </t:datagrid>
    </div>
</div>

<script type="text/javascript">

</script>
