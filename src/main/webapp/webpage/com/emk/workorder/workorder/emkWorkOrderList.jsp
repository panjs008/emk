<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="emkWorkOrderList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkWorkOrderController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建人名称" field="createName" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建人登录名称" field="createBy" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建日期" field="createDate" formatter="yyyy-MM-dd" hidden="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="所属部门" field="sysOrgCode" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" frozenColumn="true" width="100"></t:dgCol>
            <%--<t:dgCol title="预采购合同号"  field="yhtNum" queryMode="single" width="105"></t:dgCol>--%>
            <t:dgCol title="当前工单环节"  field="processName"   queryMode="single"  width="80"></t:dgCol>
            <t:dgCol title="工单号" query="true" field="workNo" queryMode="single" width="80"></t:dgCol>
            <t:dgCol title="工单时间" query="true" field="kdDate" queryMode="single" width="60"></t:dgCol>
            <t:dgCol title="是否先打样" field="isPrint" replace="否_0,是_1" queryMode="single" width="60"></t:dgCol>
            <t:dgCol title="询盘单号" field="askNo" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="打样单号" field="sampleNum" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="订单号" field="orderNo" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="合同单号" field="htNo" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="生产单号" field="produceNo" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="状态" field="state" formatterjs="formatColor" queryMode="single" width="60"></t:dgCol>

            <t:dgFunOpt funname="goToProcess(id,workNo)" title="流程进度" operationCode="process" urlclass="ace_button"
                        urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
            <%--<t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkWorkOrderController.do?goUpdate&winTitle=编辑意向询盘单" funname="update" height="600" width="1150"></t:dgToolBar>--%>
            <%--<t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-right" funname="doSubmitV"></t:dgToolBar>--%>

        </t:datagrid>
    </div>
</div>
<script src="webpage/com/emk/workorder/workorder/emkWorkOrderList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    });

    function formatColor(val, row) {
        if (row.state == "1") {
            return '<span style="color:	#FF0000;">处理中</span>';
        } else if (row.state == "2") {
            return '<span style="color:	#0000FF;">完成</span>';
        } else {
            return '创建';
        }
    }

    function doSubmitV() {
        var rowsData = $('#emkWorkOrderList').datagrid('getSelections');
        var ids = [];
        if (!rowsData || rowsData.length == 0) {
            tip('请选择需要提交的询盘订单');
            return;
        }
        for ( var i = 0; i < rowsData.length; i++) {
            ids.push(rowsData[i].id);
        }
        $.dialog.confirm('您是否确定提交询盘订单?', function(r) {
            if (r) {
                $.ajax({
                    url : "emkWorkOrderController.do?doSubmit&ids="+ids,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        tip(d.msg);
                        if (d.success) {
                            $('#emkWorkOrderList').datagrid('reload');
                        }
                    }
                });
            }
        });
    }

    function goToProcess(id,workNo) {
        var height = window.top.document.body.offsetHeight * 0.85;

        $.ajax({
            url: "flowController.do?getCurrentProcess&tableName=emk_work_order&title=询盘订单申请单&id=" + id,
            type: 'post',
            cache: false,
            data: null,
            success: function (data) {
                var d = $.parseJSON(data);
                if (d.success) {
                    var msg = d.msg;
                    createdetailwindow('工单'+workNo+'--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/workorder/workorder/emkWorkOrder-process&id=' + id, 1200, height);

                }
            }
        });
    }


    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'emkWorkOrderController.do?upload', "emkWorkOrderList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("emkWorkOrderController.do?exportXls", "emkWorkOrderList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("emkWorkOrderController.do?exportXlsByT", "emkWorkOrderList");
    }

</script>