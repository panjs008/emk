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
            if(navigator.userAgent.indexOf("Chrome") > -1){
                //var docH = $(document).height();
                var is360 = _mime("type", "application/vnd.chromium.remoting-viewer");
                function _mime(option, value) {
                    var mimeTypes = navigator.mimeTypes;
                    for (var mt in mimeTypes) {
                        if (mimeTypes[mt][option] == value) {
                            return true;
                        }
                    }
                    return false;
                }
                if(is360){
                    $("#orderTabId").css("height","422px");

                }else{
                    $("#orderTabId").css("height","475px");

                }

            }else{
                $("#orderTabId").css("height","422px");
            }
            $("#orderTabId").css("overflow","hidden");
        });

        function save(){
            //$("#orderFrm").contents().find("#formobj").click();
            var formArray =  $("#orderFrm").contents().find("#formobj").serializeArray();
            var companyformArray =  $("#companyFrm").contents().find("#formobj").serializeArray();

            if($("#orderFrm").contents().find("#rkNo").val()==""){
                tip("入库单号必填");
                return false;
            }
            if($("#orderFrm").contents().find("#orderNum").val()==""){
                tip("合同币种必填");
                return false;
            }
            if($("#orderFrm").contents().find("#rkDate").val()==""){
                tip("入库日期必填");
                return false;
            }

            $.ajax({
                url : "emkInStorageController.do?doSave",
                type : 'post',
                cache : false,
                data: formArray,
                success : function(data) {
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var msg = d.msg;
                        tip(msg);

                        $.ajax({
                            url : "emkOrderCompanyTopController.do?doAdd&orderId2="+d.obj,
                            type : 'post',
                            cache : false,
                            data: companyformArray,
                            success : function(data) {

                            }
                        });

                        var indexFlag = $("#proFrm").contents().find("#indexFlag").val();
                        var result={};
                        for(var i=0;i<=indexFlag;i++){
                            result["proNum"+i] = $("#proFrm").contents().find("#proNum"+i).val();
                            result["hairProNum"+i] = $("#proFrm").contents().find("#hairProNum"+i).val();
                            result["proName"+i] = $("#proFrm").contents().find("#proName"+i).val();
                            result["proZnName"+i] = $("#proFrm").contents().find("#proZnName"+i).val();
                            result["proEnName"+i] = $("#proFrm").contents().find("#proEnName"+i).val();
                            result["brand"+i] = $("#proFrm").contents().find("#brand"+i).val();
                            result["unit"+i] = $("#proFrm").contents().find("#unit"+i).val();
                            result["signTotal"+i] = $("#proFrm").contents().find("#signTotal"+i).val();
                            result["signPrice"+i] = $("#proFrm").contents().find("#signPrice"+i).val();
                            result["notSignPrice"+i] = $("#proFrm").contents().find("#notSignPrice"+i).val();
                            result["zzsl"+i] = $("#proFrm").contents().find("#zzsl"+i).val();
                            result["zzse"+i] = $("#proFrm").contents().find("#zzse"+i).val();
                            result["tsl"+i] = $("#proFrm").contents().find("#tsl"+i).val();
                            result["tse"+i] = $("#proFrm").contents().find("#tse"+i).val();
                            result["remark"+i] = $("#proFrm").contents().find("#remark"+i).val();
                            result["proId"+i] = $("#proFrm").contents().find("#proId"+i).val();
                        }
                        $.ajax({
                            url:"emkInStorageDetailController.do?saveStroage&inStorageId="+d.obj+"&dataSize="+indexFlag+"&data="+JSON.stringify(result),
                            data:null,
                            type:'post',
                            success:function(data){

                            }
                        });
                        window.location.href='${webRoot}/emkInStorageController.do?editInStorageTab&id='+d.obj;
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
<span style="text-align: left;float: left;font-size: 22px"><img src="images/order.jpg" style="float: left;width: 35px;height:35px;">&nbsp;<strong>订货订单&nbsp;${CUR_USER.currentDepart.departname}&nbsp;${CUR_USER.realName}&nbsp;</font>
	<c:if test="${order.state eq 0}"><font color="red">制单</font></c:if>
	<c:if test="${order.state eq 1}"><font color="blue">审核</font></c:if></strong>&nbsp;&nbsp;</span>
<div style="padding: 3px; height: 35px;width:auto;" class="datagrid-toolbar" align="right">
		<c:if test="${order.state eq 1}"><input class="btn" type="submit" onclick="doUpdate();" value="修改" style="height:30px;width:100px !important;border-radius:5px"></c:if>
		<input class="btn" type="submit" value="保存" style="height:30px;width:100px !important;border-radius:5px" onclick="save();">
		<input class="btn" type="submit" value="审核" onclick="doSubmit();" style="height:30px;width:100px !important;border-radius:5px">
	<input class="btn" type="submit" value="复制" style="height:30px;width:100px !important;border-radius:5px">

</div>
<%--<t:tabs id="rkglTabs" iframe="false" tabPosition="top" fit="false">
	&lt;%&ndash;<t:tab title="基本信息" id="ciFrm"  heigth="100%" width="100%" icon="" iframe="zcCiController.do?list&eType=1&ciId=-1"></t:tab>&ndash;%&gt;
	<t:tab title="基本信息" id="orderFrm"  heigth="720" width="100%" icon="" iframe="emkOrderController.do?goAdd&id=${param.id}"></t:tab>
	&lt;%&ndash;<t:tab title="商品信息" id="ciFrm2"  heigth="420" width="100%" icon="" iframe="emkOrderDetailController.do?orderDetailTab"></t:tab>&ndash;%&gt;
	<t:tab title="商品信息" id="ciFrm2"  heigth="420" width="100%" icon="" iframe="emkOrderDetailController.do?list&orderId=${param.id}"></t:tab>
	<t:tab title="公司抬头" id="companyFrm"  heigth="440" width="100%" icon="" iframe="emkOrderCompanyTopController.do?goAdd&orderId=${param.id}"></t:tab>
	<t:tab title="附件文档" id="fileFrm"  heigth="420" width="100%" icon="" iframe=""></t:tab>
	<t:tab title="关联单据" id="relationFrm"  heigth="420" width="100%" icon="" iframe=""></t:tab>
</t:tabs>--%>

<div style="margin:3px 0 2px 0;"></div>
<div class="easyui-tabs" style="height:475px;overflow:hidden" id="orderTabId">
	<div id="orderDiv" title="基本信息" style="height:500px;overflow:hidden" >
		<iframe scrolling="yes" id="orderFrm" frameborder="0" src="emkInStorageController.do?goAdd&id=${inStorageEntity.id}" style="width:100%;height:100%;"></iframe>
	</div>
	<div id="proDiv" title="商品信息" style="padding:5px;height:440px;overflow:hidden" >
		<iframe scrolling="yes" id="proFrm"  frameborder="0"  src="emkOrderDetailController.do?list&orderId=${param.id}" style="width:100%;height:100%;"></iframe>
	</div>
	<div id="companyDiv" title="公司抬头" style="height:500px;overflow:hidden" >
		<iframe scrolling="yes" id="companyFrm"  frameborder="0"  src="emkOrderCompanyTopController.do?goAdd&orderId=${param.id}" style="width:100%;height:100%;"></iframe>
	</div>
	<div id="fileDiv" title="附件文档" style="height:500px;overflow:hidden" >
		<iframe scrolling="yes" id="fileFrm"  frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
	</div>
	<div id="relatDiv" title="关联单据" style="height:500px;overflow:hidden" >
		<iframe scrolling="yes" id="relationFrm"   frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
	</div>
</div>
</body>

