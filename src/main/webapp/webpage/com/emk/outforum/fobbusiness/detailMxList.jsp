<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var srflag = 0;
    var currentFlag = 0;


    $('#addBtnSR').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnSR').bind('click', function(){
        srflag++;
        var tr =  $("#add_jeecgOrderProduct_tableSR_template tr").clone();
        $("#add_jeecgOrderProduct_tableSR").append(tr);

        /*$("#add_jeecgOrderProduct_tableSR").find("[id='signPrice00']").attr("datatype","d");
         $("#add_jeecgOrderProduct_tableSR").find("[id='signPrice00']").attr("id","signPrice"+srflag);*/
        resetTrNum('add_jeecgOrderProduct_tableSR');
        $("#orderMxListIDSR").val($("#mxtbSR").find("tr").length-1);
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tableSR").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableSR');
        $("#orderMxListIDSR").val($("#mxtbSR").find("tr").length-1);

        if(chk_value.length>0){

        }

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        <c:if test="${param.fobId eq null || param.fobId eq ''}">
            $('#addBtnSR').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableSR_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="cusNum00" nullmsg="请输入客户代码！"  errormsg="请输入客户代码" name="orderMxList[#index#].cusNum00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="orderNo00" nullmsg="请输入订单号！"  errormsg="请输入订单号" name="orderMxList[#index#].orderNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sampleNo00" nullmsg="请输入款号！"  errormsg="请输入款号" name="orderMxList[#index#].sampleNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sampleDesc00" nullmsg="请输入描述！"  errormsg="请输入描述" name="orderMxList[#index#].sampleDesc00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="signTotal00" nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" name="orderMxList[#index#].signTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="price00" nullmsg="请输入单价！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].price00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sumMoney00" nullmsg="请输入金额！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].sumMoney00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="factoryCode00" nullmsg="请输入工厂代码！"  errormsg="请输入工厂代码" name="orderMxList[#index#].factoryCode00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="jqDate00" nullmsg="请输入交期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"  errormsg="请输入交期" name="orderMxList[#index#].jqDate00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cargoState00" nullmsg="请输入订舱状态！"  errormsg="请输入订舱状态" name="orderMxList[#index#].cargoState00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="outDate00" nullmsg="请输入出货日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"  errormsg="请输入出货日期" name="orderMxList[#index#].outDate00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cargoNo00" nullmsg="请输入订舱通知单号！"  errormsg="请输入订舱通知单号" name="orderMxList[#index#].cargoNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="outForumNo00" nullmsg="请输入出货通知单号！"  errormsg="请输入出货通知单号" name="orderMxList[#index#].outForumNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="levealFactoryNo00" nullmsg="请输入离厂放行条号！"  errormsg="请输入离厂放行条号" name="orderMxList[#index#].levealFactoryNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="levealDate00" nullmsg="请输入离厂日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"  errormsg="请输入离厂日期" name="orderMxList[#index#].levealDate00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="tdNum00" nullmsg="请输入提单号！"  errormsg="请输入提单号" name="orderMxList[#index#].tdNum00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="tdState00" nullmsg="请输入提单状态！"  errormsg="请输入提单状态" name="orderMxList[#index#].tdState00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="startPlace00" nullmsg="请输入起运港！"  errormsg="请输入起运港" name="orderMxList[#index#].startPlace00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="endPlace00" nullmsg="请输入目的港！"  errormsg="请输入目的港" name="orderMxList[#index#].endPlace00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="hdCode00" nullmsg="请输入货代代码！"  errormsg="请输入货代代码" name="orderMxList[#index#].hdCode00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="chCost00" nullmsg="请输入出货费用金额！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].chCost00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="chState00" nullmsg="请输入出货费用状态！"  errormsg="请输入出货费用状态" name="orderMxList[#index#].chState00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="ysEntCode00" nullmsg="请输入运输企业代码！"  errormsg="请输入运输企业代码" name="orderMxList[#index#].ysEntCode00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="ysEntCost00" nullmsg="请输入运输费用金额！" datatype="d" errormsg="请输入数值"  name="orderMxList[#index#].ysEntCost00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="ysState00" nullmsg="请输入运输费状态！"  errormsg="请输入运输费状态" name="orderMxList[#index#].ysState00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bgd00" nullmsg="请输入报关单！"  errormsg="请输入报关单" name="orderMxList[#index#].bgd00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="fxt00" nullmsg="请输入放行条！"  errormsg="请输入放行条" name="orderMxList[#index#].fxt00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="fpNum00" nullmsg="请输入发票编号！"  errormsg="请输入发票编号" name="orderMxList[#index#].fpNum00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="boxNum00" nullmsg="请输入装箱单编号！"  errormsg="请输入装箱单编号" name="orderMxList[#index#].boxNum00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="tdsmj00" nullmsg="请输入提单扫描件！"  errormsg="请输入提单扫描件" name="orderMxList[#index#].tdsmj00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkFobBusinessDetailEntities)}"/>
<div class="table-c" style="overflow: auto; width: 100%;">
    <table id="mxtbSR" width="2800px"  border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" rowspan="2">序号</td>
            <td align="center"  width="120" colspan="15"></td>
            <td align="center"  width="120" colspan="4">提单</td>
            <td align="center"  width="120" colspan="3">货代费用</td>
            <td align="center"  width="120" colspan="3">运输费用金额</td>
            <td align="center"  width="120" colspan="5">客户文件</td>
        </tr>
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="150">客户代码</td>
            <td align="center"  width="150">订单号</td>
            <td align="center"  width="100">款号</td>
            <td align="center"  width="100">描述</td>
            <td align="center"  width="90">数量</td>
            <td align="center"  width="90">单价</td>
            <td align="center"  width="90">金额</td>
            <td align="center"  width="120">工厂代码</td>
            <td align="center"  width="120">交期</td>
            <td align="center"  width="90">订舱状态</td>
            <td align="center"  width="120">出货日期</td>
            <td align="center"  width="150">订舱通知单号</td>
            <td align="center"  width="150">出货通知单号</td>
            <td align="center"  width="150">离厂放行条号</td>
            <td align="center"  width="120">离厂日期</td>
            <td align="center"  width="150">提单号</td>
            <td align="center"  width="80">提单状态</td>
            <td align="center"  width="80">起运港</td>
            <td align="center"  width="80">目的港</td>
            <td align="center"  width="150">货代代码</td>
            <td align="center"  width="80">出货费用<br/>金额</td>
            <td align="center"  width="80">出货费用<br/>状态</td>
            <td align="center"  width="150">运输企业<br/>代码</td>
            <td align="center"  width="80">运输费用<br/>金额</td>
            <td align="center"  width="80">运输费状态</td>
            <td align="center"  width="150">报关单</td>
            <td align="center"  width="180">放行条</td>
            <td align="center"  width="180">发票编号</td>
            <td align="center"  width="180">装箱单编号</td>
            <td align="center"  width="150">提单扫描件</td>
        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkFobBusinessDetailEntities)  > 0 }">
            <c:forEach items="${emkFobBusinessDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center"><input  nullmsg="请输入客户代码！"  errormsg="请输入客户代码"  value="${poVal.cusNum}"  name="orderMxList[${status.index}].cusNum00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入订单号！"  errormsg="请输入订单号" value="${poVal.orderNo}" name="orderMxList[${status.index}].orderNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入款号！"  errormsg="请输入款号" value="${poVal.sampleNo}" name="orderMxList[${status.index}].sampleNo00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入描述！"  errormsg="请输入描述" value="${poVal.sampleNoDesc}" name="orderMxList[${status.index}].sampleDesc00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" value="${poVal.total}"  name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入单价！"  datatype="d" errormsg="请输入数值" value="${poVal.price}"  name="orderMxList[${status.index}].price00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入金额！"  datatype="d" errormsg="请输入数值" value="${poVal.sumMoney}"  name="orderMxList[${status.index}].sumMoney00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入工厂代码！"  errormsg="请输入工厂代码" value="${poVal.factoryCode}"  name="orderMxList[${status.index}].factoryCode00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入交期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${poVal.jqDate}"  class="Wdate"  errormsg="请输入交期" name="orderMxList[${status.index}].jqDate00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入订舱状态！"  errormsg="请输入订舱状态" value="${poVal.cargoState}"  name="orderMxList[${status.index}].cargoState00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入出货日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${poVal.outDate}"  class="Wdate"  errormsg="请输入出货日期" name="orderMxList[${status.index}].outDate00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入订舱通知单号！"  errormsg="请输入订舱通知单号" value="${poVal.cargoNo}" name="orderMxList[${status.index}].cargoNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入出货通知单号！"  errormsg="请输入出货通知单号" value="${poVal.outFourmNo}" name="orderMxList[${status.index}].outForumNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入离厂放行条号！"  errormsg="请输入离厂放行条号" value="${poVal.levealFactoryNo}" name="orderMxList[${status.index}].levealFactoryNo00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入离厂日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${poVal.levealDate}" class="Wdate"  errormsg="请输入离厂日期" name="orderMxList[${status.index}].levealDate00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入提单号！"  errormsg="请输入提单号" value="${poVal.tdNum}" name="orderMxList[${status.index}].tdNum00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入提单状态！"  errormsg="请输入提单状态" value="${poVal.tdState}"name="orderMxList[${status.index}].tdState00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入起运港！"  errormsg="请输入起运港"  value="${poVal.startPlace}"name="orderMxList[${status.index}].startPlace00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入目的港！"  errormsg="请输入目的港" value="${poVal.endPlace}" name="orderMxList[${status.index}].endPlace00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入货代代码！"  errormsg="请输入货代代码" value="${poVal.hdCode}"name="orderMxList[${status.index}].hdCode00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入出货费用金额！"  datatype="d" errormsg="请输入数值" value="${poVal.chCost}" name="orderMxList[${status.index}].chCost00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入出货费用状态！"  errormsg="请输入出货费用状态" value="${poVal.chState}" name="orderMxList[${status.index}].chState00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入运输企业代码！"  errormsg="请输入运输企业代码" value="${poVal.ysEntCode}" name="orderMxList[${status.index}].ysEntCode00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入运输费用金额！" datatype="d" errormsg="请输入数值" value="${poVal.ysEntCost}"  name="orderMxList[${status.index}].ysEntCost00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入运输费状态！"  errormsg="请输入运输费状态"  value="${poVal.ysState}" name="orderMxList[${status.index}].ysState00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入报关单！"  errormsg="请输入报关单" value="${poVal.bgd}" name="orderMxList[${status.index}].bgd00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入放行条！"  errormsg="请输入放行条" value="${poVal.fxt}" name="orderMxList[${status.index}].fxt00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入发票编号！"  errormsg="请输入发票编号" value="${poVal.fpNum}" name="orderMxList[${status.index}].fpNum00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入装箱单编号！"  errormsg="请输入装箱单编号" value="${poVal.zxd}" name="orderMxList[${status.index}].boxNum00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入提单扫描件！"  errormsg="请输入提单扫描件" value="${poVal.tdsmj}" name="orderMxList[${status.index}].tdsmj00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

