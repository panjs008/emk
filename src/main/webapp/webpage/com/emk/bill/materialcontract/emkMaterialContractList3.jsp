<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="emkMaterialContractList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="emkMaterialContractController.do?datagrid&type=2" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
            <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
            <%--<t:dgCol title="预采购合同号"  field="yhtNum" queryMode="single" width="105"></t:dgCol>--%>
            <t:dgCol title="包装辅料开发费付款申请单开发费付款申请单编号"  query="true"  field="payNo" queryMode="single" width="210"></t:dgCol>
            <t:dgCol title="包装辅料开发费付款申请单需求单号"  query="true"  field="requireNo" queryMode="single" width="130"></t:dgCol>
            <t:dgCol title="包装辅料开发费付款申请单采购单号"  query="true"  field="caigouNo" queryMode="single" width="130"></t:dgCol>
            <t:dgCol title="打样通知单号"  query="true"  field="dytzdNo" queryMode="single" width="130"></t:dgCol>
            <t:dgCol title="打样需求单号"  query="true"  field="dyxqdNo" queryMode="single" width="130"></t:dgCol>

            <%--<t:dgCol title="交货日期"  field="dhjqDate"  queryMode="single"  width="80"></t:dgCol>--%>
            <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
            <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
            <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
            <t:dgCol title="客户代码" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
            <t:dgCol title="客户名称" field="cusName"  queryMode="single"  width="160"></t:dgCol>
            <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
            <%--<t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>--%>
            <%--<t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>--%>
            <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="70"></t:dgCol>
            <%--<t:dgFunOpt funname="goToProcess(id,createBy,createName)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>--%>
            <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkMaterialContractController.do?goAdd&type=2&winTitle=录入包装辅料开发费付款申请单" funname="add" height="600" width="1200"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkMaterialContractController.do?goUpdate&type=2&winTitle=编辑包装辅料开发费付款申请单" funname="update" height="600" width="1200"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkMaterialContractController.do?goUpdate&type=2&winTitle=查看包装辅料开发费付款申请单" funname="detail" height="600" width="1200"></t:dgToolBar>

            <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-right" funname="doSubmitV"></t:dgToolBar>
            <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>

            <%--<t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkMaterialContractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
        </t:datagrid>
    </div>
</div>
<script src = "webpage/com/emk/bill/materialcontract/emkMaterialContractList.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    });

    function formatColor(val,row){
        if(row.state=="1"){
            return '<span style="color:	#FF0000;">处理中</span>';
        }else if(row.state=="2"){
            return '<span style="color:	#0000FF;">完成</span>';
        }else{
            return '创建';
        }
    }
    function doSubmitV() {
        var rowsData = $('#emkMaterialContractList').datagrid('getSelections');
        var ids = [];
        if (!rowsData || rowsData.length == 0) {
            tip('请选择需要提交的采购订单');
            return;
        }
        for ( var i = 0; i < rowsData.length; i++) {
            ids.push(rowsData[i].id);
        }
        $.dialog.confirm('您是否确定提交采购订单?', function(r) {
            if (r) {
                $.ajax({
                    url : "emkMaterialContractController.do?doSubmit&ids="+ids,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        tip(d.msg);
                        if (d.success) {
                            $('#emkMaterialContractList').datagrid('reload');
                        }
                    }
                });
            }
        });
    }

    function goToProcess(id,createBy,createName){
        var height =window.top.document.body.offsetHeight*0.85;

        $.ajax({
            url : "emkMaterialContractController.do?getCurrentProcess&id="+id,
            type : 'post',
            cache : false,
            data: null,
            success : function(data) {
                var d = $.parseJSON(data);
                if (d.success) {
                    var msg = d.msg;
                    if(createBy == "${CUR_USER.userName}"){
                        createdetailwindow('流程进度--当前环节：'+msg,'emkMaterialContractController.do?goProcess&id='+id,1150,height);
                    }else{
                        if (msg == "完成") {
                            createdetailwindow('流程进度--当前环节：' + msg, 'flowController.do?goProcess&processUrl=com/emk/storage/price/emkPrice-process&id=' + id, 1230, height);
                        } else {
                            if(createName == "领导审核" &&  "${ROLE.rolecode}" == "cgjl") {
                                createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                            }else if(createName == "预采购合同" &&  "${ROLE.rolecode}" == "cw") {
                                createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                            }else if(createName == "正式合同" &&  "${ROLE.rolecode}" == "cwjl") {
                                createwindow("流程进度--当前环节："+msg, "emkMaterialContractController.do?goProcess&id="+id,1150,height);
                            }else{
                                createdetailwindow('流程进度--当前环节：'+msg,'emkMaterialContractController.do?goProcess&id='+id,1150,height);
                            }
                        }
                    }
                }
            }
        });
    }

    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'emkMaterialContractController.do?upload', "emkMaterialContractList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("emkMaterialContractController.do?exportXls","emkMaterialContractList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("emkMaterialContractController.do?exportXlsByT","emkMaterialContractList");
    }

</script>