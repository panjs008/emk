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
            $("#workFrm").css("height",height);

          /* <c:if test="${repairPorcess eq null}">
                $("#orderFrm").css("height","450px");
            </c:if>
            <c:if test="${processFinish eq  '0'}">
                $("#orderFrm").css("height","1000px");
            </c:if>
            <c:if test="${processFinish eq  '1'}">
                $("#orderFrm").css("height","1100px");
            </c:if>
            <c:if test="${repairPorcess.taskDefinitionKey eq 'checkTask'}">
                $("#orderFrm").css("height","600px");
            </c:if>
            <c:if test="${repairPorcess.taskDefinitionKey eq 'zxRepair'}">
                $("#orderFrm").css("height","800px");
            </c:if>
            <c:if test="${repairPorcess.taskDefinitionKey eq 'serviceTask'}">
                $("#orderFrm").css("height","800px");
            </c:if>
            <c:if test="${repairPorcess.taskDefinitionKey eq 'saveRecord'}">
                $("#orderFrm").css("height","1000px");
            </c:if>
            <c:if test="${repairPorcess.taskDefinitionKey eq 'normalRepairTask'}">
                $("#orderFrm").css("height","1100px");
            </c:if>*/

        });
        function saveRepair(){
            var formArray =  $("#orderFrm").contents().find("#formobj").serializeArray();

           /* if($("#orderFrm").contents().find("#orderNum").val()==""){
                tip("采购合同号必填");
                return false;
            }
            if($("#orderFrm").contents().find("#htbz").val()==""){
                tip("合同币种必填");
                return false;
            }*/


            $.ajax({
                url : "emkOrderController.do?doUpdate",
                type : 'post',
                cache : false,
                data: formArray,
                success : function(data) {
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var msg = d.msg;
                        tip(msg);
                        //openwindow("销售单打印预览",'xhdController.do?jump&r=qtPrintSheetFrm',"xhdList",900,550)
                        //addOneTab('销售单打印预览','xhdController.do?goAdd');
                        W.document.location.reload(true);
                       /* $.ajax({
                            url : "emkOrderCompanyTopController.do?doAdd&orderId2="+d.obj,
                            type : 'post',
                            cache : false,
                            data: companyformArray,
                            success : function(data) {

                            }
                        });

                        window.location.href='${webRoot}/emkOrderController.do?editOrderTab&id='+d.obj;*/
                    }
                }
            });

        }


    </script>

</head>
<body>
<t:tabs id="repairTabId" iframe="false" heigth="600px" tabPosition="top" fit="true" >
    <t:tab title="基本信息" id="orderFrm"  heigth="560px"  width="100%" icon="" iframe="uRepairController.do?goUpdate&id=${param.id}"></t:tab>
    <t:tab title="任务处理" id="workFrm"  heigth="480px"  width="100%" icon="" href="uRepairController.do?goWork&id=${param.id}"></t:tab>
    <t:tab title="流程图" id="proFrm"  heigth="560px" width="100%" icon="" iframe="uRepairController.do?process&id=${param.id}"></t:tab>
</t:tabs>

</body>

