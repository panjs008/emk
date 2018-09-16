<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="uRepairList" checkbox="true" pagination="true" fitColumns="true" title="" actionUrl="uRepairController.do?waitdatagrid&status=1" sortName="applyDate" sortOrder="desc" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
            <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="180"></t:dgCol>
            <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="用户ID"  field="userId" hidden="true" queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="当前节点"  field="createName"   queryMode="single"  width="95"></t:dgCol>
            <t:dgCol title="报修单号"  field="repairNum"   queryMode="single"  width="105"></t:dgCol>
            <t:dgCol title="报修人" field="userName"  queryMode="single"  width="60"></t:dgCol>
            <t:dgCol title="报修时间"  field="applyDate"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="流程状态"  field="status"  replace="创建_0,待受理_1,待派发_2,维修_3,完成_4,关闭_5" extendParams="styler:statetype" queryMode="single"  width="70"></t:dgCol>
            <t:dgCol title="报修方式"  field="bxType" dictionary="bxType"  queryMode="single"  width="65"></t:dgCol>
            <%--<t:dgCol title="设备型号"  field="brand"  queryMode="single"  width="90"></t:dgCol>--%>
            <t:dgCol title="设备名称"  field="proZnName"  queryMode="single"  width="70"></t:dgCol>
            <t:dgCol title="设备类型"  field="sblx"  queryMode="single"   width="70"></t:dgCol>
            <t:dgCol title="品牌"  field="pp"  queryMode="single"   width="65"></t:dgCol>
            <t:dgCol title="型号"  field="xh"  queryMode="single"   width="65"></t:dgCol>
            <%--<t:dgCol title="报修数量"  field="total"  queryMode="single"  width="70"></t:dgCol>
            <t:dgCol title="单位"  field="unit"  queryMode="single"  width="45"></t:dgCol>--%>
            <t:dgCol title="故障描述"  field="fault"  queryMode="single"  width="120"></t:dgCol>
            <%--<t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>--%>
            <t:dgFunOpt funname="goToProcess(id,createName)" title="流程进度" urlclass="ace_button" urlfont="fa-tasks"></t:dgFunOpt>
            <t:dgFunOpt funname="doRecover(id,createName)" title="驳回" exp="status#eq#1,2,3" urlclass="ace_button" urlStyle="background-color:#ec4758;" urlfont="fa-exchange"></t:dgFunOpt>
            <t:dgFunOpt funname="endProcess(id)" exp="status#ne#4,5" title="关闭" urlclass="ace_button" urlStyle="background-color:#18a689;" urlfont="fa-close"></t:dgFunOpt>
            <t:dgToolBar title="新建" icon="fa fa-plus" url="uRepairController.do?goAdd" funname="add" width="900" height="500"></t:dgToolBar>
            <%--<t:dgToolBar title="编辑" icon="fa fa-edit" url="uRepairController.do?goUpdate" funname="update" width="900" height="500"></t:dgToolBar>--%>
            <%--<t:dgToolBar title="提交" icon="fa fa-arrow-circle-right" funname="doSubmit"></t:dgToolBar>--%>

            <t:dgToolBar title="删除"  icon="fa fa-remove" url="uRepairController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="fa fa-search" url="uRepairController.do?goUpdate" funname="detail" width="900" height="500"></t:dgToolBar>
            <%--<t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>--%>
            <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
             <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
        </t:datagrid>
    </div>
</div>
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
    function goToProcess(id,processNodeName){
        var height =window.top.document.body.offsetHeight*0.85;
        createwindow("流程进度--当前环节："+processNodeName, "uRepairController.do?goProcess&id="+id,1150,height);

        /*$.ajax({
            url : "flowController.do?getCurrentProcess&id="+id,
            type : 'post',
            cache : false,
            data: null,
            success : function(data) {
                var d = $.parseJSON(data);
                if (d.success) {
                    var msg = d.msg;
                    if(msg == "完成"){
                        createdetailwindow('流程进度--当前环节：'+msg,'uRepairController.do?goProcess&id='+id,1150,height);
                    }else{
                        createwindow("流程进度--当前环节："+msg, "uRepairController.do?goProcess&id="+id,1150,height);
                        /!*  $.dialog({
                         content: 'url:uRepairController.do?goProcess&id='+id,
                         zIndex: getzIndex(),
                         lock : true,
                         width:1150,
                         height: height,
                         parent:windowapi,
                         title:"流程进度--当前环节："+msg,
                         opacity : 0.3,
                         cache:false,
                         ok: function(){
                         iframe = this.iframe.contentWindow;
                         $('#btn_sub', iframe.document).click();

                         /!*iframe = this.iframe.contentWindow;
                         //win = frameElement.api.opener;// 来源页面
                         //var formArray =  $('#orderFrm',  iframe.document).contents().find("#formobj").serializeArray();
                         $('#workFrm',  iframe.document).contents().find("#btn_sub").click();*!/
                         //alertTip('操作成功');
                         //W.document.location.reload(true);
                         /!* win.reloadTable();
                         win.tip("工单处理成功");
                         frameElement.api.close();*!/
                         /!* $.ajax({
                         url: "uRepairController.do?doshowFlag",
                         type: 'post',
                         cache: false,
                         data: null,
                         success: function (data) {
                         if(data == 1){
                         alertTip('操作成功');
                         }
                         }
                         });*!/

                         return false;
                         },
                         cancelVal: '关闭',
                         cancel: true /!*为true等价于function(){}*!/
                         });*!/
                    }



                }
            }
        });*/
    }

    function doRecover(id,processName) {
        $.dialog.confirm('您是否确定要追回报修单?', function(r) {
            if (r) {
                $.ajax({
                    url : "uRepairController.do?doRecover&id="+id+"&processName="+processName,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        tip(d.msg);
                        if (d.success) {
                            $('#uRepairList').datagrid('reload');
                        }
                    }
                });
            }
        });
    }

    function endProcess(id) {
        $.dialog.confirm('您是否确定要关闭报修单?', function(r) {
            if (r) {
                $.ajax({
                    url : "uRepairController.do?endProcess&id="+id,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        tip(d.msg);
                        if (d.success) {
                            $('#uRepairList').datagrid('reload');
                        }
                    }
                });
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