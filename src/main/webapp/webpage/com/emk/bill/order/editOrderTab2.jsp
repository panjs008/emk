<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script src="metro/js/jquery.nicescroll.js" type="text/javascript"></script>

	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">

        $(document).ready(
            /*function() {
                $("#rkglTabs").niceScroll();
            }*/
        );


        $(document).ready(function(){
            $(".datagrid-toolbar").parent().css("width","auto");

        });

        function save(){
            //$("#orderFrm").contents().find("#formobj").click();
            var formArray =  $("#orderFrm").contents().find("#formobj").serializeArray();
            var companyformArray =  $("#companyFrm").contents().find("#formobj").serializeArray();

            if($("#orderFrm").contents().find("#orderNum").val()==""){
                tip("采购合同号必填");
                return false;
            }
            if($("#orderFrm").contents().find("#htbz").val()==""){
                tip("合同币种必填");
                return false;
            }
            if($("#orderFrm").contents().find("#signDate").val()==""){
                tip("签约日期必填");
                return false;
            }
            if($("#orderFrm").contents().find("#signPlace").val()==""){
                tip("签约地必填");
                return false;
            }
            if($("#orderFrm").contents().find("#ysfs").val()==""){
                tip("运输方式必填");
                return false;
            }
            if($("#orderFrm").contents().find("#cusName").val()==""){
                tip("客户名称必填");
                return false;
            }
            if($("#orderFrm").contents().find("#comTop").val()==""){
                tip("公司抬头必填");
                return false;
            }
            $.ajax({
                url : "emkOrderController.do?doSave",
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
                        //W.document.location.reload(true);
                        $.ajax({
                            url : "emkOrderCompanyTopController.do?doAdd&orderId="+d.obj,
                            type : 'post',
                            cache : false,
                            data: companyformArray,
                            success : function(data) {

                            }
                        });
                        window.location.href='${webRoot}/emkOrderController.do?editOrderTab&id='+d.obj;
                    }
                }
            });

        }

        function doSubmit(){
            //$("#orderFrm").contents().find("#formobj").click();
            var formArray =  $("#orderFrm").contents().find("#formobj").serializeArray();
            var companyformArray =  $("#companyFrm").contents().find("#formobj").serializeArray();

            if($("#orderFrm").contents().find("#orderNum").val()==""){
                tip("采购合同号必填");
                return false;
            }
            if($("#orderFrm").contents().find("#htbz").val()==""){
                tip("合同币种必填");
                return false;
            }
            if($("#orderFrm").contents().find("#signDate").val()==""){
                tip("签约日期必填");
                return false;
            }
            if($("#orderFrm").contents().find("#signPlace").val()==""){
                tip("签约地必填");
                return false;
            }
            if($("#orderFrm").contents().find("#ysfs").val()==""){
                tip("运输方式必填");
                return false;
            }
            if($("#orderFrm").contents().find("#cusName").val()==""){
                tip("客户名称必填");
                return false;
            }
            if($("#orderFrm").contents().find("#comTop").val()==""){
                tip("公司抬头必填");
                return false;
            }
            $.ajax({
                url : "emkOrderController.do?doSubmit",
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
                        //W.document.location.reload(true);
                        $.ajax({
                            url : "emkOrderCompanyTopController.do?doAdd",
                            type : 'post',
                            cache : false,
                            data: companyformArray,
                            success : function(data) {

                            }
                        });
                        window.location.href='${webRoot}/emkOrderController.do?editOrderTab&id='+d.obj;
                    }
                }
            });

        }

        function doUpdate(){
            var formArray =  $("#orderFrm").contents().find("#formobj").serializeArray();
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
                        window.location.href='${webRoot}/emkOrderController.do?editOrderTab&id='+d.obj;
                    }
                }
            });

        }
	</script>
</head>
<body>
<span style="text-align: left;float: left;font-size: 22px"><img src="images/order.jpg" style="float: left;width: 35px;height:35px;">&nbsp;<strong>订货订单-${order.orderNum}</strong>&nbsp;&nbsp;
	<c:if test="${order.state eq 0}"><img src="images/state1.jpg" style="margin-top: 5px;"></c:if>
	<c:if test="${order.state eq 1}"><img src="images/state2.jpg" style="margin-top: 5px;"></c:if></span>
<div style="padding: 3px; height: 35px;width:auto;" class="datagrid-toolbar" align="right">
		<c:if test="${order.state eq 1}"><input class="btn" type="submit" onclick="doUpdate();" value="修改" style="height:30px;width:100px !important;border-radius:5px"></c:if>
	<c:if test="${order.state eq 0}">
		<input class="btn" type="submit" value="保存" style="height:30px;width:100px !important;border-radius:5px" onclick="save();">
		<input class="btn" type="submit" value="审核" onclick="doSubmit();" style="height:30px;width:100px !important;border-radius:5px"></c:if>
	<input class="btn" type="submit" value="复制" style="height:30px;width:100px !important;border-radius:5px">

</div>
<t:tabs id="rkglTabs" iframe="false" tabPosition="top" fit="false">
	<%--<t:tab title="基本信息" id="ciFrm"  heigth="100%" width="100%" icon="" iframe="zcCiController.do?list&eType=1&ciId=-1"></t:tab>--%>
	<t:tab title="基本信息" id="orderFrm"  heigth="720" width="100%" icon="" iframe="emkOrderController.do?goAdd&id=${param.id}"></t:tab>
	<%--<t:tab title="商品信息" id="ciFrm2"  heigth="420" width="100%" icon="" iframe="emkOrderDetailController.do?orderDetailTab"></t:tab>--%>
	<t:tab title="商品信息" id="ciFrm2"  heigth="420" width="100%" icon="" iframe="emkOrderDetailController.do?list&orderId=${param.id}"></t:tab>
	<t:tab title="公司抬头" id="companyFrm"  heigth="440" width="100%" icon="" iframe="emkOrderCompanyTopController.do?goAdd&orderId=${param.id}"></t:tab>
	<t:tab title="附件文档" id="fileFrm"  heigth="420" width="100%" icon="" iframe=""></t:tab>
	<t:tab title="关联单据" id="relationFrm"  heigth="420" width="100%" icon="" iframe=""></t:tab>
</t:tabs>

</body>

