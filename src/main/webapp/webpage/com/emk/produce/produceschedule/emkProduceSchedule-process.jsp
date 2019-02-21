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

        function DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式
            var  aDate,  oDate1,  oDate2,  iDays
            aDate  =  sDate1.split("-")
            oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式
            aDate  =  sDate2.split("-")
            oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
            iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
            return  iDays
        }
        function setEndTimeSsy() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#ssyDate").val();
            $("#leavelSsy").val(DateDiff(d1,d2));
        }
        function setEndTimeCqy() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#cqyDate").val();
            $("#leavelCq").val(DateDiff(d1,d2));
        }

        function setEndTimeSy() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#syDate").val();
            $("#leavelSy").val(DateDiff(d1,d2));
        }

        function setEndTimeCy() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#cyDate").val();
            $("#leavelCy").val(DateDiff(d1,d2));
        }

        function setEndTimeP() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#ylblLimitDate").val();
            $("#leavelYlblDay").val(DateDiff(d1,d2));
        }

        function setEndTimeP2() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#fzblLimitDate").val();
            $("#leavelFzblDay").val(DateDiff(d1,d2));
        }

        function setEndTimeP3() {
            var d1  =  $("#kdDate").val();
            var d2  =  $("#bzblLimitDate").val();
            $("#leavelBzblDay").val(DateDiff(d1,d2));
        }
    </script>

</head>
<body>
<t:tabs id="repairTabId" iframe="false" heigth="600px" tabPosition="top" fit="true" >
    <t:tab title="任务处理" id="orderFrm"  heigth="600px"  width="100%" href="emkProduceScheduleController.do?goWork&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
    <t:tab title="基本信息" id="orderFrm"  heigth="600px"  width="100%" iframe="emkProduceScheduleController.do?goUpdate2&id=${param.id}" icon="fa fa-calendar"></t:tab>
    <%--<t:tab title="任务处理" id="workFrm"  heigth="480px"  width="100%" icon="" href="uRepairController.do?goWork&id=${param.id}"></t:tab>--%>
    <t:tab title="流程图" id="proFrm"  heigth="600px" width="100%" icon="fa fa-sitemap" iframe="flowController.do?process&processUrl=com/emk/produce/produceschedule/process&id=${param.id}"></t:tab>
</t:tabs>

</body>

