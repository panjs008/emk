<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
    $(function () {
        $('#orgtree').tree({
            animate: true,
            url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
            onClick: function (node) {
                if ($('#orgtree').tree('isLeaf', node.target) || $('#orgtree').tree('expand', node.target)) {
                    $('#emkProductInfoList').datagrid("reload", {"proType": node.id});
                }
            },
            onLoadSuccess: function (node) {
                expandAll();
            }
        });
    });

    /**
     * 展开所有节点
     */
    function expandAll() {
        var node = $('#orgtree').tree('getSelected');
        if (node) {
            $('#orgtree').tree('expandAll', node.target);
        } else {
            var root=$('#orgtree').tree('getRoot');//获取根节点
            $('#orgtree').tree('expand', root.target);//展开根节点
            //$('#orgtree').tree('expandAll');
        }
    }
</script>
<div class="easyui-layout" fit="true">
    <div region="west" style="width: 200px;" title="产品类别" split="true">
        <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false">
            <ul id="orgtree">
            </ul>
        </div>
    </div>
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="emkProductInfoList" checkbox="false"  pagination="true" fitColumns="false" title="" actionUrl="emkProductInfoController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
            <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="款号"  field="hsCode"  queryMode="single"  width="90"></t:dgCol>
            <t:dgCol title="款号名称"  field="hsName"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="产品编号"  field="proNum" hidden="true"  query="true" queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="产品名称"  field="proName"  query="true" queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="类别"  field="proTypeName"  queryMode="single"  width="80"></t:dgCol>
            <t:dgCol title="规格型号"  field="brand"  queryMode="single"  width="100"></t:dgCol>
            <t:dgCol title="单位"  field="unit"  queryMode="single"  width="60"></t:dgCol>
            <t:dgCol title="单价"  field="price"  queryMode="single"  width="70"></t:dgCol>

        </t:datagrid>
    </div>
</div>
<script src = "webpage/com/emk/product/productinfo/emkProductInfoList.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    });



    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'emkProductInfoController.do?upload', "emkProductInfoList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("emkProductInfoController.do?exportXls","emkProductInfoList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("emkProductInfoController.do?exportXlsByT","emkProductInfoList");
    }

</script>