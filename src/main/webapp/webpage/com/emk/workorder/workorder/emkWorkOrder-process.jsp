<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>报修单</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script>
        $(document).ready(function(){
            var height =window.top.document.body.offsetHeight*0.79;
            //$("#proFrm").css("height",height);
            $("#orderFrm").css("height",height);

        });
        function setchooseUser(flag){
            if(flag==1){
                $("#chooseUser").css("display","none");
               $("#userName").val("fwtadmin");
                $("#realName").val("fwtadmin");
            }else {
                $("#chooseUser").css("display","");
            }
        }
        function setchooseService(flag){
            if(flag==1){
                $("#spanId1").css("display","");
                $("#spanId2").css("display","none");
            }else {
                $("#spanId1").css("display","none");
                $("#spanId2").css("display","");
            }
        }
        function setchooseServiceYg(flag){
            if(flag==0){
                $("#spanId1").css("display","");
                $("#spanId2").css("display","none");
                $("#spanId3").css("display","none");
            }else if(flag==1){
                $("#spanId1").css("display","none");
                $("#spanId2").css("display","");
                $("#spanId3").css("display","none");
            }else {
                $("#spanId1").css("display","none");
                $("#spanId2").css("display","none");
                $("#spanId3").css("display","");
            }
        }
    </script>

</head>
<body>
<%--<div class="easyui-tabs" style="height:550px;overflow:hidden;border: 0px;" id="orderTabId">
    <div id="workDiv" title="任务处理" style="height:550px;overflow:hidden" >
    </div>
    <div id="baseDiv" title="基本信息" style="height:100%;overflow:hidden" >
        <iframe scrolling="yes" id="orderFrm" frameborder="0" src="emkWorkOrderController.do?goUpdate2&id=${param.id}" style="width:100%;height:100%;"></iframe>
    </div>
    <div id="processDiv" title="流程图" style="height:550px;overflow:hidden" >
        <iframe scrolling="yes" id="proFrm" frameborder="0" src="emkWorkOrderController.do?process&id=${param.id}" style="width:100%;height:100%;"></iframe>
    </div>
</div>--%>
<t:tabs id="repairTabId" iframe="false" heigth="550px" tabPosition="top" fit="true" >
    <%--<t:tab title="任务处理" id="orderFrm"  heigth="550px"  width="100%" href="emkWorkOrderController.do?goWork&id=${param.id}" icon="fa fa-crosshairs"></t:tab>--%>
    <t:tab title="基本信息" id="orderFrm"  heigth="550px"  width="100%" iframe="emkWorkOrderController.do?goUpdate2&id=${param.id}" icon="fa fa-calendar"></t:tab>
    <%--<t:tab title="任务处理" id="workFrm"  heigth="480px"  width="100%" icon="" href="uRepairController.do?goWork&id=${param.id}"></t:tab>--%>
    <t:tab title="流程图" id="proFrm"  heigth="600px" width="100%" icon="fa fa-sitemap" iframe="flowController.do?process&processUrl=com/emk/workorder/workorder/process&id=${param.id}"></t:tab>
</t:tabs>

</body>

