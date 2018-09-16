<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="uRepairList" checkbox="false" pagination="false" fitColumns="true" title="" actionUrl="flowController.do?hisProcessDatagrid&id=${param.id}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="流程节点"  field="NAME_"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="操作人"  field="workname"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="开始时间"  field="START_TIME_"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="结束时间"  field="END_TIME_"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="DELETE_REASON_" replace="完成_completed"  queryMode="single"  width="120"></t:dgCol>

</t:datagrid>
<script src = "webpage/com/process/repair/uRepairList.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    });
    function statetype(val,row,index){
        var s1 = 'background-color: #EE9A49;border-radius: 5px;color:#fff;';
        var s2 = 'background-color: #9B30FF;border-radius: 5px;color:#fff';
        if (val =='1') {
            return s1;
        }
    }
    function goToProcess(id){
        $.ajax({
            url : "flowController.do?getCurrentProcess&id="+id,
            type : 'post',
            cache : false,
            data: null,
            success : function(data) {
                var d = $.parseJSON(data);
                if (d.success) {
                    var msg = d.msg;
                    createwindow("流程进度--当前环节："+msg, "uRepairController.do?goProcess&id="+id,1020,500);

                }
            }
        });
    }
    function doSubmit() {
        var rowsData = $('#uRepairList').datagrid('getSelections');
        var ids = [];
        if (!rowsData || rowsData.length == 0) {
            tip('请选择需要提交的报修单');
            return;
        }
        for ( var i = 0; i < rowsData.length; i++) {
            ids.push(rowsData[i].id);
        }
        $.dialog.confirm('您是否确定提交报修单?', function(r) {
            if (r) {
                $.ajax({
                    url : "uRepairController.do?doSubmit&ids="+ids,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        tip(d.msg);
                        if (d.success) {
                            //window.open("${webRoot}/gjsTestpaperController.do?reload&index=0&countflag=0");
                            //window.location.href = "emkOrderController.do?list";
                            $('#uRepairList').datagrid('reload');
                        }
                    }
                });
            }
        });



    }

    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'uRepairController.do?upload', "uRepairList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("uRepairController.do?exportXls","uRepairList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("uRepairController.do?exportXlsByT","uRepairList");
    }

</script>