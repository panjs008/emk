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
            $("#gxhtFrm").css("height",height);
            $("#proFrm").css("height",height);

        });

        function resetTrNum(tableId) {
            $tbody = $("#"+tableId+"");
            $tbody.find('>tr').each(function(i){
                $(':input, select', this).each(function(){
                    var $this = $(this), name = $this.attr('name'), val = $this.val();
                    if(name!=null){
                        if (name.indexOf("#index#") >= 0){
                            $this.attr("name",name.replace('#index#',i));
                        }else{
                            var s = name.indexOf("[");
                            var e = name.indexOf("]");
                            var new_name = name.substring(s+1,e);
                            $this.attr("name",name.replace(new_name,i));
                        }
                    }
                });
            });
        }

        function selectAll(selectStatus){//传入参数（全选框的选中状态）
            //根据name属性获取到单选框的input，使用each方法循环设置所有单选框的选中状态
            if(selectStatus){
                $("input[name='ck']").each(function(i,n){
                    n.checked = true;
                });
            }else{
                $("input[name='ck']").each(function(i,n){
                    n.checked = false;
                });
            }
        }

    </script>

</head>
<body>
<t:tabs id="prorderTabId" iframe="false" heigth="${param.hVal}" tabPosition="top" fit="true" >
    <c:if test="${param.node ne 'gxhtTask'}">
        <t:tab title="任务处理" id="workFrm"  heigth="600px"  width="100%" href="emkProOrderController.do?goWork&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
        <t:tab title="订单表" id="orderFrm"  heigth="${param.hVal}"  width="100%" iframe="emkProOrderController.do?goUpdate2&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
        <%--<c:choose>
            <c:when test="${param.node eq 'ywjlshTask2' || param.node eq 'zjlpfTask'}">
                <t:tab title="购销合同" id="gxhtFrm"  heigth="${param.hVal}"  width="100%" iframe="emkContractController.do?goUpdate2&node=${param.node}&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>--%>
         <c:if test="${param.node eq 'ywjlshTask2' || param.node eq 'zjlpfTask' || param.state eq '2'}">
             <t:tab title="购销合同" id="gxhtFrm"  heigth="${param.hVal}"  width="100%" iframe="emkContractController.do?goUpdate2&node=${param.node}&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
         </c:if>

    </c:if>
    <c:if test="${param.node eq 'gxhtTask'}">
        <t:tab title="任务处理" id="workFrm"  heigth="600px"  width="100%" iframe="emkProOrderController.do?goWork&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
        <t:tab title="订单表" id="orderFrm"  heigth="${param.hVal}"  width="100%" iframe="emkProOrderController.do?goUpdate2&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
        <t:tab title="购销合同" id="gxhtFrm"  heigth="${param.hVal}"  width="100%" href="emkContractController.do?goUpdate2&node=${param.node}&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${param.id}" icon="fa fa-calendar"></t:tab>
    </c:if>
    <t:tab title="流程图" id="proFrm"  heigth="600px" width="100%" icon="fa fa-sitemap" iframe="flowController.do?process&processUrl=com/emk/bill/proorder/process&id=${param.id}"></t:tab>
</t:tabs>

</body>

