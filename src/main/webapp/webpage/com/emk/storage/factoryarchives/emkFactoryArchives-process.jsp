<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>验厂处理单</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script>
        $(document).ready(function(){
            var height =window.top.document.body.offsetHeight*0.79;
            $("#fksqdFrm").css("height",height);
            $("#proFrm").css("height",height);

        });

        function setReruire(v){
            if(v == 0){
                $("#realName").attr("datatype","*");
                $("#userName").attr("datatype","*");
            }
            if(v == 1){
                $("#realName").removeAttr("datatype");
                $("#userName").removeAttr("datatype");
            }
        }
        function downloadFile(url) {
            window.open("${webRoot}/"+url);
        }
        function uploadSuccess0(d,file,response){
            var src = d.attributes.url;
            $("#fileNameUrl").val(d.attributes.url);
            $("#fileName").val(d.attributes.name);
            $("#fileNameId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
        }
    </script>
</head>
<body>
<t:tabs id="faTabId" iframe="false" heigth="${param.hVal}" tabPosition="top" fit="true" >
    <c:if test="${param.node ne 'checkfactoryTask'}">
        <t:tab title="任务处理" id="orderFrm"  heigth="600px"  width="100%" href="emkFactoryArchivesController.do?goWork&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
        <t:tab title="工厂信息表" id="fksqdFrm"  heigth="${param.hVal}"  width="100%" iframe="emkFactoryArchivesController.do?goUpdate2&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
    </c:if>
    <c:if test="${param.node eq 'checkfactoryTask'}">
        <t:tab title="任务处理" id="workFrm"  heigth="600px"  width="100%" iframe="emkFactoryArchivesController.do?goWork&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
        <t:tab title="工厂信息表" id="fksqdFrm"  heigth="${param.hVal}"  width="100%" href="emkFactoryArchivesController.do?goUpdate3&node=${param.node}&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
    </c:if>
    <c:if test="${param.node eq 'ywjlshTask' || param.node eq 'fpycyTask' || param.node eq 'cyTask' || param.node eq 'bgTask' || param.node eq 'ycbshTask' || param.state eq '2'}">
        <t:tab title="验厂申请表" id="workFrm"  heigth="600px"  width="100%" iframe="emkCheckFactoryController.do?goUpdate2&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
    </c:if>
    <t:tab title="流程图" id="proFrm"  heigth="600px" width="100%" icon="fa fa-sitemap" iframe="flowController.do?process&processUrl=com/emk/storage/factoryarchives/process&id=${param.id}"></t:tab>
</t:tabs>

</body>

