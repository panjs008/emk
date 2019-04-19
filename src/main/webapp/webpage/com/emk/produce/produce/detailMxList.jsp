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
        <c:if test="${param.pId eq null || param.pId eq ''}">
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
        <td align="center"><input id="orderNo00" nullmsg="请输入订单号！"  errormsg="请输入订单号" name="orderMxList[#index#].orderNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cusNum00" nullmsg="请输入客户代码！"  errormsg="请输入客户代码" name="orderMxList[#index#].cusNum00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="produceHtNum00" nullmsg="请输入生产合同号！"  errormsg="请输入生产合同号" name="orderMxList[#index#].produceHtNum00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gysCode00" nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码" name="orderMxList[#index#].gysCode00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gyzl00" nullmsg="请输入工艺大类！"  errormsg="请输入工艺大类" name="orderMxList[#index#].gyzl00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="proTypeName00" nullmsg="请输入款式大类！"  errormsg="请输入款式大类" name="orderMxList[#index#].proTypeName00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sampleNo00" nullmsg="请输入款号！"  errormsg="请输入款号" name="orderMxList[#index#].sampleNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sampleDesc00" nullmsg="请输入描述！"  errormsg="请输入描述" name="orderMxList[#index#].sampleDesc00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="customSampleUrl00" nullmsg="请输入图片！"  errormsg="请输入图片" name="orderMxList[#index#].customSampleUrl00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select name="orderMxList[#index#].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                <c:forEach items="${colorList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>
        <td align="center">
            <select name="orderMxList[#index#].size" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                <c:forEach items="${sizeList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>
        <td align="center"><input id="signTotal00" nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" name="orderMxList[#index#].signTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select id="ylblState00" name="orderMxList[#index#].ylblState00" style="width: 86%;" nullmsg="请输入原料布料状态！" errormsg="请输入原料布料状态" >
                <option value="0">未开始</option>
                <option value="1">准备中</option>
                <option value="2">已到货</option>
            </select>
          </td>
        <td align="center"><input id="ylblLimitDate00" nullmsg="请输入原料布料最迟到厂日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  errormsg="请输入原料布料最迟到厂日期" name="orderMxList[#index#].ylblLimitDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelYlblDay00" nullmsg="请输入距原料布料最迟到厂日期剩余天数！"  datatype="n" errormsg="请输入距原料布料最迟到厂日期剩余天数" name="orderMxList[#index#].leavelYlblDay00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="fzblState00" name="orderMxList[#index#].fzblState00" style="width: 86%;" nullmsg="请输入缝制辅料状态！" errormsg="请输入缝制辅料状态" >
                <option value="0">未开始</option>
                <option value="1">准备中</option>
                <option value="2">已到货</option>
            </select>
        </td>
        <td align="center"><input id="fzblLimitDate00" nullmsg="请输入缝制辅料最迟到厂日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  errormsg="请输入缝制辅料最迟到厂日期" name="orderMxList[#index#].fzblLimitDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelFzblDay00" nullmsg="请输入距缝制辅料最迟到厂日期剩余天数！"  datatype="n" errormsg="请输入距缝制辅料最迟到厂日期剩余天数" name="orderMxList[#index#].leavelFzblDay00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="bzblState00" name="orderMxList[#index#].bzblState00" style="width: 86%;" nullmsg="请输入包装辅料状态！" errormsg="请输入包装辅料状态" >
                <option value="0">未开始</option>
                <option value="1">准备中</option>
                <option value="2">已到货</option>
            </select>
        </td>
        <td align="center"><input id="bzblLimitDate00" nullmsg="请输入包装辅料最迟到厂日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  errormsg="请输入包装辅料最迟到厂日期" name="orderMxList[#index#].bzblLimitDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelBzblDay00" nullmsg="请输入距包装辅料最迟到厂日期剩余天数！"  datatype="n" errormsg="请输入距包装辅料最迟到厂日期剩余天数" name="orderMxList[#index#].leavelBzblDay00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="ranState00" name="orderMxList[#index#].ranState00" style="width: 86%;" nullmsg="请输入染色状态！" errormsg="请输入染色状态" >
                <option value="0">未开始</option>
                <option value="1">染色中</option>
                <option value="2">已染色</option>
            </select>
        </td>
        <td align="center"><input id="ranFinish00" nullmsg="请输入染色已完成数量！"  datatype="n" errormsg="请输入染色已完成数量" name="orderMxList[#index#].ranFinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="ranUnfinish00" nullmsg="请输入染色未完成数量！"  datatype="n" errormsg="请输入染色未完成数量" name="orderMxList[#index#].ranUnfinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="caiState00" name="orderMxList[#index#].caiState00" style="width: 86%;" nullmsg="请输入裁剪状态！" errormsg="请输入裁剪状态" >
                <option value="0">未开始</option>
                <option value="1">裁剪中</option>
                <option value="2">已裁剪</option>
            </select>
        </td>
        <td align="center"><input id="caiFinish00" nullmsg="请输入裁剪已完成数量！"  datatype="n" errormsg="请输入裁剪已完成数量" name="orderMxList[#index#].caiFinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="caiUnfinish00" nullmsg="请输入裁剪未完成数量！"  datatype="n" errormsg="请输入裁剪未完成数量" name="orderMxList[#index#].caiUnfinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="fzState00" name="orderMxList[#index#].fzState00" style="width: 86%;" nullmsg="请输入缝制状态！" errormsg="请输入缝制状态" >
                <option value="0">未开始</option>
                <option value="1">缝制中</option>
                <option value="2">已缝制</option>
            </select>
        </td>
        <td align="center"><input id="fzFinish00" nullmsg="请输入缝制已完成数量！"  datatype="n" errormsg="请输入缝制已完成数量" name="orderMxList[#index#].fzFinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="fzUnfinish00" nullmsg="请输入缝制未完成数量！"  datatype="n" errormsg="请输入缝制未完成数量" name="orderMxList[#index#].fzUnfinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="btState00" name="orderMxList[#index#].btState00" style="width: 86%;" nullmsg="请输入烫标状态！" errormsg="请输入烫标状态" >
                <option value="0">未开始</option>
                <option value="1">烫标中</option>
                <option value="2">已烫标</option>
            </select>
        </td>
        <td align="center"><input id="btFinish00" nullmsg="请输入烫标已完成数量！"  datatype="n" errormsg="请输入烫标已完成数量" name="orderMxList[#index#].btFinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="btUnfinish00" nullmsg="请输入烫标未完成数量！"  datatype="n" errormsg="请输入烫标未完成数量" name="orderMxList[#index#].btUnfinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="ztState00" name="orderMxList[#index#].ztState00" style="width: 86%;" nullmsg="请输入整烫状态！" errormsg="请输入整烫状态" >
                <option value="0">未开始</option>
                <option value="1">整烫中</option>
                <option value="2">已整烫</option>
            </select>
        </td>
        <td align="center"><input id="ztFinish00" nullmsg="请输入整烫已完成数量！"  datatype="n" errormsg="请输入整烫已完成数量" name="orderMxList[#index#].ztFinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="ztUnfinish00" nullmsg="请输入整烫未完成数量！"  datatype="n" errormsg="请输入整烫未完成数量" name="orderMxList[#index#].ztUnfinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="bzState00" name="orderMxList[#index#].bzState00" style="width: 86%;" nullmsg="请输入包装状态！" errormsg="请输入包装状态" >
                <option value="0">未开始</option>
                <option value="1">包装中</option>
                <option value="2">已包装</option>
            </select>
        </td>
        <td align="center"><input id="bzFinish00" nullmsg="请输入包装已完成数量！"  datatype="n" errormsg="请输入包装已完成数量" name="orderMxList[#index#].bzFinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bzUnfinish00" nullmsg="请输入包装未完成数量！"  datatype="n" errormsg="请输入包装未完成数量" name="orderMxList[#index#].bzUnfinish00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>


        <td align="center">
            <select id="zcrq00" name="orderMxList[#index#].zcrq00" style="width: 86%;" nullmsg="请输入中期验货状态！" errormsg="请输入中期验货状态" >
                <option value="0">未开始</option>
                <option value="1">验货中</option>
                <option value="2">已验货</option>
            </select>
        </td>
        <td align="center"><input id="zcrqDate00" nullmsg="请输入预计中期验货日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  errormsg="请输入预计中期验货日期" name="orderMxList[#index#].zcrqDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="levalZcrq00" nullmsg="请输入距预计中期验货日期剩余天数！"  datatype="n" errormsg="请输入距预计中期验货日期剩余天数" name="orderMxList[#index#].levalZcrq00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="wcrq00" name="orderMxList[#index#].wcrq00" style="width: 86%;" nullmsg="请输入尾期验货状态！" errormsg="请输入尾期验货状态" >
                <option value="0">未开始</option>
                <option value="1">验货中</option>
                <option value="2">已验货</option>
            </select>
        </td>
        <td align="center"><input id="wcrqDate00" nullmsg="请输入预计尾期验货日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  errormsg="请输入预计尾期验货日期" name="orderMxList[#index#].wcrqDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="levalWcrq00" nullmsg="请输入距预计尾期验货日期剩余天数！"  datatype="n" errormsg="请输入距预计尾期验货日期剩余天数" name="orderMxList[#index#].levalWcrq00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="outDate00" nullmsg="请输入出货日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入出货日期" name="orderMxList[#index#].outDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkProduceDetailEntities)}"/>
<div class="table-c" style="overflow: auto; width: 100%;">
    <table id="mxtbSR" width="4400px"  border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" rowspan="2">序号</td>
            <td align="center"  width="120" colspan="12"></td>
            <td align="center"  width="120" colspan="9">采购进度</td>
            <td align="center"  width="120" colspan="18">生产进度</td>
            <td align="center"  width="120" colspan="3">中期验货</td>
            <td align="center"  width="120" colspan="4">尾期验货</td>
        </tr>
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="120">订单号</td>
            <td align="center"  width="100">客户代码</td>
            <td align="center"  width="120">生产合同号</td>
            <td align="center"  width="120">供应商代码</td>
            <td align="center"  width="90">工艺大类</td>
            <td align="center"  width="90">款式大类</td>
            <td align="center"  width="100">款号</td>
            <td align="center"  width="100">描述</td>
            <td align="center"  width="100">图片</td>
            <td align="center"  width="90">颜色</td>
            <td align="center"  width="90">尺码</td>
            <td align="center"  width="90">数量</td>

            <td align="center"  width="90">原料布料状态</td>
            <td align="center"  width="100">原料布料<br/>最迟到厂日期</td>
            <td align="center"  width="120">距原料布料最迟<br/>到厂日期剩余天数</td>

            <td align="center"  width="90">缝制辅料状态</td>
            <td align="center"  width="100">缝制辅料<br/>最迟到厂日期</td>
            <td align="center"  width="120">距缝制辅料最迟<br/>到厂日期剩余天数</td>

            <td align="center"  width="90">包装辅料状态</td>
            <td align="center"  width="100">包装辅料<br/>最迟到厂日期</td>
            <td align="center"  width="120">距包装辅料最迟<br/>到厂日期剩余天数</td>

            <td align="center"  width="90">染色状态</td>
            <td align="center"  width="100">染色已完成数量</td>
            <td align="center"  width="100">染色未完成数量</td>

            <td align="center"  width="90">裁剪状态</td>
            <td align="center"  width="100">裁剪已完成数量</td>
            <td align="center"  width="100">裁剪未完成数量</td>

            <td align="center"  width="90">缝制</td>
            <td align="center"  width="100">缝制已完成数量</td>
            <td align="center"  width="100">缝制未完成数量</td>

            <td align="center"  width="90">烫标</td>
            <td align="center"  width="100">烫标已完成数量</td>
            <td align="center"  width="100">烫标未完成数量</td>

            <td align="center"  width="90">整烫</td>
            <td align="center"  width="100">整烫已完成数量</td>
            <td align="center"  width="100">整烫未完成数量</td>

            <td align="center"  width="90">包装状态</td>
            <td align="center"  width="100">包装已完成数量</td>
            <td align="center"  width="100">包装未完成数量</td>

            <td align="center"  width="90">中期验货状态</td>
            <td align="center"  width="100">预计中期<br/>验货日期</td>
            <td align="center"  width="110">距预计中期<br/>验货日期剩余天数</td>

            <td align="center"  width="90">尾期验货状态</td>
            <td align="center"  width="100">预计尾期<br/>验货日期</td>
            <td align="center"  width="110">距预计尾期<br/>验货日期剩余天数</td>
            <td align="center"  width="100">出货日期</td>
        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkProduceDetailEntities)  > 0 }">
            <c:forEach items="${emkProduceDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center"><input  nullmsg="请输入订单号！"  errormsg="请输入订单号" value="${poVal.orderNo}" name="orderMxList[${status.index}].orderNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入客户代码！"  errormsg="请输入客户代码" value="${poVal.cusNum}" name="orderMxList[${status.index}].cusNum00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入生产合同号！"  errormsg="请输入生产合同号" value="${poVal.produceHtNum}" name="orderMxList[${status.index}].produceHtNum00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码" value="${poVal.gysCode}" name="orderMxList[${status.index}].gysCode00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入工艺大类！"  errormsg="请输入工艺大类" value="${poVal.gyzl}" name="orderMxList[${status.index}].gyzl00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入款式大类！"  errormsg="请输入款式大类" value="${poVal.proTypeName}" name="orderMxList[${status.index}].proTypeName00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入款号！"  errormsg="请输入款号" value="${poVal.sampleNo}" name="orderMxList[${status.index}].sampleNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入描述！"  errormsg="请输入描述" value="${poVal.sampleNoDesc}" name="orderMxList[${status.index}].sampleDesc00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入图片！"  errormsg="请输入图片" value="${poVal.customSampleUrl}" name="orderMxList[${status.index}].customSampleUrl00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                            <c:forEach items="${colorList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.color ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].size" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                            <c:forEach items="${sizeList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.size ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入数量！" datatype="n" errormsg="请输入数量" value="${poVal.sumTotal}" name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center">
                        <select  name="orderMxList[${status.index}].ylblState00" style="width: 86%;" nullmsg="请输入原料布料状态！" errormsg="请输入原料布料状态" >
                            <option value="0" ${poVal.ylblState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.ylblState eq '1' ? 'selected':''}>准备中</option>
                            <option value="2" ${poVal.ylblState eq '2' ? 'selected':''}>已到货</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入原料布料最迟到厂日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入原料布料最迟到厂日期" class="Wdate" value="${poVal.ylblLimitDate}" name="orderMxList[${status.index}].ylblLimitDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入距原料布料最迟到厂日期剩余天数！" datatype="n" errormsg="请输入距原料布料最迟到厂日期剩余天数" value="${poVal.leavelYlblDay}" name="orderMxList[${status.index}].leavelYlblDay00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].fzblState00" style="width: 86%;" nullmsg="请输入缝制辅料状态！" errormsg="请输入缝制辅料状态" >
                            <option value="0" ${poVal.fzblState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.fzblState eq '1' ? 'selected':''}>准备中</option>
                            <option value="2" ${poVal.fzblState eq '2' ? 'selected':''}>已到货</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入缝制辅料最迟到厂日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入缝制辅料最迟到厂日期" class="Wdate" value="${poVal.fzblLimitDate}" name="orderMxList[${status.index}].fzblLimitDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入距缝制辅料最迟到厂日期剩余天数！" datatype="n" errormsg="请输入距缝制辅料最迟到厂日期剩余天数" value="${poVal.leavelFzblDay}" name="orderMxList[${status.index}].leavelFzblDay00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].bzblState00" style="width: 86%;" nullmsg="请输入包装辅料状态！" errormsg="请输入包装辅料状态" >
                            <option value="0" ${poVal.bzblState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.bzblState eq '1' ? 'selected':''}>准备中</option>
                            <option value="2" ${poVal.bzblState eq '2' ? 'selected':''}>已到货</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入包装辅料最迟到厂日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入包装辅料最迟到厂日期" class="Wdate" value="${poVal.bzblLimitDate}" name="orderMxList[${status.index}].bzblLimitDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入距包装辅料最迟到厂日期剩余天数！" datatype="n" errormsg="请输入距包装辅料最迟到厂日期剩余天数" value="${poVal.leavelBzblDay}" name="orderMxList[${status.index}].leavelBzblDay00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>


                    <td align="center">
                        <select  name="orderMxList[${status.index}].ranState00" style="width: 86%;" nullmsg="请输入染色状态！" errormsg="请输入染色状态" >
                            <option value="0" ${poVal.ranState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.ranState eq '1' ? 'selected':''}>染色中</option>
                            <option value="2" ${poVal.ranState eq '2' ? 'selected':''}>已染色</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入染色已完成数量！" datatype="n" errormsg="请输入染色已完成数量" value="${poVal.ranFinish}" name="orderMxList[${status.index}].ranFinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入染色未完成数量！" datatype="n" errormsg="请输入染色未完成数量" value="${poVal.ranUnfinish}" name="orderMxList[${status.index}].ranUnfinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].caiState00" style="width: 86%;" nullmsg="请输入裁剪状态！" errormsg="请输入裁剪状态" >
                            <option value="0" ${poVal.caiState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.caiState eq '1' ? 'selected':''}>裁剪中</option>
                            <option value="2" ${poVal.caiState eq '2' ? 'selected':''}>已裁剪</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入裁剪已完成数量！" datatype="n" errormsg="请输入裁剪已完成数量" value="${poVal.caiFinish}" name="orderMxList[${status.index}].caiFinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入裁剪未完成数量！" datatype="n" errormsg="请输入裁剪未完成数量" value="${poVal.caiUnfinish}" name="orderMxList[${status.index}].caiUnfinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].fzState00" style="width: 86%;" nullmsg="请输入缝制状态！" errormsg="请输入缝制状态" >
                            <option value="0" ${poVal.fzState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.fzState eq '1' ? 'selected':''}>缝制中</option>
                            <option value="2" ${poVal.fzState eq '2' ? 'selected':''}>已缝制</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入缝制已完成数量！" datatype="n" errormsg="请输入缝制已完成数量" value="${poVal.fzFinish}" name="orderMxList[${status.index}].fzFinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入缝制未完成数量！" datatype="n" errormsg="请输入缝制未完成数量" value="${poVal.fzUnfinish}" name="orderMxList[${status.index}].fzUnfinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].btState00" style="width: 86%;" nullmsg="请输入烫标状态！" errormsg="请输入烫标状态" >
                            <option value="0" ${poVal.btState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.btState eq '1' ? 'selected':''}>烫标中</option>
                            <option value="2" ${poVal.btState eq '2' ? 'selected':''}>已烫标</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入烫标已完成数量！" datatype="n" errormsg="请输入烫标已完成数量" value="${poVal.btFinish}" name="orderMxList[${status.index}].btFinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入烫标未完成数量！" datatype="n" errormsg="请输入烫标未完成数量" value="${poVal.btUnfinish}" name="orderMxList[${status.index}].btUnfinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].ztState00" style="width: 86%;" nullmsg="请输入整烫状态！" errormsg="请输入整烫状态" >
                            <option value="0" ${poVal.ztState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.ztState eq '1' ? 'selected':''}>整烫中</option>
                            <option value="2" ${poVal.ztState eq '2' ? 'selected':''}>已整烫</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入整烫已完成数量！" datatype="n" errormsg="请输入整烫已完成数量" value="${poVal.ztFinish}" name="orderMxList[${status.index}].ztFinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入整烫未完成数量！" datatype="n" errormsg="请输入整烫未完成数量" value="${poVal.ztUnfinish}" name="orderMxList[${status.index}].ztUnfinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].bzState00" style="width: 86%;" nullmsg="请输入包装状态！" errormsg="请输入包装状态" >
                            <option value="0" ${poVal.bzState eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.bzState eq '1' ? 'selected':''}>包装中</option>
                            <option value="2" ${poVal.bzState eq '2' ? 'selected':''}>已包装</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入包装已完成数量！" datatype="n" errormsg="请输入包装已完成数量" value="${poVal.bzFinish}" name="orderMxList[${status.index}].bzFinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入包装未完成数量！" datatype="n" errormsg="请输入包装未完成数量" value="${poVal.bzUnfinish}" name="orderMxList[${status.index}].bzUnfinish00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].zcrq00" style="width: 86%;" nullmsg="请输入中期验货状态！" errormsg="请输入中期验货状态" >
                            <option value="0" ${poVal.zcrq eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.zcrq eq '1' ? 'selected':''}>包装中</option>
                            <option value="2" ${poVal.zcrq eq '2' ? 'selected':''}>已包装</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入预计中期验货日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入预计中期验货日期" class="Wdate" value="${poVal.zcrqDate}" name="orderMxList[${status.index}].zcrqDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入距预计中期验货日期剩余天数！" datatype="n" errormsg="请输入距预计中期验货日期剩余天数" value="${poVal.levalZcrq}" name="orderMxList[${status.index}].levalZcrq00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[${status.index}].wcrq00" style="width: 86%;" nullmsg="请输入尾期验货状态！" errormsg="请输入尾期验货状态" >
                            <option value="0" ${poVal.wcrq eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.wcrq eq '1' ? 'selected':''}>包装中</option>
                            <option value="2" ${poVal.wcrq eq '2' ? 'selected':''}>已包装</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入预计尾期验货日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入预计尾期验货日期" class="Wdate" value="${poVal.wcrqDate}" name="orderMxList[${status.index}].wcrqDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入距预计尾期验货日期剩余天数！" datatype="n" errormsg="请输入距预计尾期验货日期剩余天数" value="${poVal.levalWcrq}" name="orderMxList[${status.index}].levalWcrq00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入出货日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入出货日期" value="${poVal.outDate}" name="orderMxList[${status.index}].outDate00" class="Wdate" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

