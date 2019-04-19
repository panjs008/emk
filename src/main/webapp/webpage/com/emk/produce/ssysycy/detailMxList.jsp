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
        <c:if test="${param.ssyId eq null || param.ssyId eq ''}">
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
        <td align="center">
            <select name="orderMxList[#index#].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                <c:forEach items="${colorList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>

        <td align="center"><input id="signTotal00" nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" name="orderMxList[#index#].signTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select id="ssyzt00" name="orderMxList[#index#].ssyzt00" style="width: 86%;" nullmsg="请输入试身样状态！" errormsg="请输入试身样状态" >
                <option value="0">未开始</option>
                <option value="1">生产中</option>
                <option value="2">已生产</option>
            </select>
          </td>
        <td align="center"><input id="ssyDate00" nullmsg="请输入试身样最晚确认时间！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  errormsg="请输入试身样最晚确认时间" name="orderMxList[#index#].ssyDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelSsy00" nullmsg="请输入试身样距离最晚确认时间剩余天数！"  datatype="n" errormsg="请输入试身样距离最晚确认时间剩余天数" name="orderMxList[#index#].leavelSsy00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="cqyzt00" name="orderMxList[#index#].cqyzt00" style="width: 86%;" nullmsg="请输入产前样状态！" errormsg="请输入产前样状态" >
                <option value="0">未开始</option>
                <option value="1">生产中</option>
                <option value="2">已生产</option>
            </select>
           </td>
        <td align="center"><input id="cqyDate00" nullmsg="请输入产前样最晚确认时间！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入产前样最晚确认时间" name="orderMxList[#index#].cqyDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelCq00" nullmsg="请输入产前样距离最晚确认时间剩余天数！"  datatype="n" errormsg="请输入产前样距离最晚确认时间剩余天数" name="orderMxList[#index#].leavelCq00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="syzt00" name="orderMxList[#index#].syzt00" style="width: 86%;" nullmsg="请输入色样状态！" errormsg="请输入色样状态" >
                <option value="0">未开始</option>
                <option value="1">生产中</option>
                <option value="2">已生产</option>
            </select>
           </td>
        <td align="center"><input id="syDate00" nullmsg="请输入色样最晚确认时间！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入色样最晚确认时间" name="orderMxList[#index#].syDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelSy00" nullmsg="请输入色样距离最晚确认时间剩余天数！"  datatype="n" errormsg="请输入色样距离最晚确认时间剩余天数" name="orderMxList[#index#].leavelSy00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center">
            <select id="cyzt00" name="orderMxList[#index#].cyzt00" style="width: 86%;" nullmsg="请输入船样状态！" errormsg="请输入船样状态" >
                <option value="0">未开始</option>
                <option value="1">生产中</option>
                <option value="2">已生产</option>
            </select>
           </td>
        <td align="center"><input id="cyDate00" nullmsg="请输入船样最晚确认时间！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入船样最晚确认时间" name="orderMxList[#index#].cyDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="leavelCy00" nullmsg="请输入船样距离最晚确认时间剩余天数！"  datatype="n" errormsg="请输入船样距离最晚确认时间剩余天数" name="orderMxList[#index#].leavelCy00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="outDate00" nullmsg="请输入出货日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入出货日期" name="orderMxList[#index#].outDate00" maxlength="100" class="Wdate" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkSsysycyDetailEntities)}"/>
<div class="table-c" style="overflow: auto; width: 100%;">
    <table id="mxtbSR" width="2400px"  border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" rowspan="2">序号</td>
            <td align="center"  width="120" colspan="10"></td>
            <td align="center"  width="120" colspan="3">试身样</td>
            <td align="center"  width="120" colspan="3">产前样</td>
            <td align="center"  width="120" colspan="3">色样</td>
            <td align="center"  width="120" colspan="3">船样</td>
            <td align="center" rowspan="2" width="100">出货日期</td>
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
            <td align="center"  width="90">颜色</td>
            <td align="center"  width="90">数量</td>
            <td align="center"  width="90">试身样状态</td>
            <td align="center"  width="100">试身样最晚<br/>确认时间</td>
            <td align="center"  width="100">试身样距离最晚<br/>确认时间剩余天数</td>

            <td align="center"  width="90">产前样状态</td>
            <td align="center"  width="100">产前样最晚<br/>确认时间</td>
            <td align="center"  width="100">产前样距离最晚<br/>确认时间剩余天数</td>

            <td align="center"  width="90">色样状态</td>
            <td align="center"  width="100">色样最晚<br/>确认时间</td>
            <td align="center"  width="100">色样距离最晚<br/>确认时间剩余天数</td>

            <td align="center"  width="90">船样状态</td>
            <td align="center"  width="100">船样最晚<br/>确认时间</td>
            <td align="center"  width="100">船样距离最晚<br/>确认时间剩余天数</td>
        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkSsysycyDetailEntities)  > 0 }">
            <c:forEach items="${emkSsysycyDetailEntities}" var="poVal" varStatus="status">
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
                    <td align="center">
                        <select name="orderMxList[${status.index}].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                            <c:forEach items="${colorList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.color ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td align="center"><input  nullmsg="请输入数量！" datatype="n" errormsg="请输入数量" value="${poVal.sumTotal}" name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center">
                        <select  name="orderMxList[#index#].ssyzt00" style="width: 86%;" nullmsg="请输入试身样状态！" errormsg="请输入试身样状态" >
                            <option value="0" ${poVal.ssyzt eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.ssyzt eq '1' ? 'selected':''}>生产中</option>
                            <option value="2" ${poVal.ssyzt eq '2' ? 'selected':''}>已生产</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入试身样最晚确认时间！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入试身样最晚确认时间" class="Wdate" value="${poVal.ssyDate}" name="orderMxList[${status.index}].ssyDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入试身样距离最晚确认时间剩余天数！" datatype="n" errormsg="请输入试身样距离最晚确认时间剩余天数" value="${poVal.leavelSsy}" name="orderMxList[${status.index}].leavelSsy00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[#index#].cqyzt00" style="width: 86%;" nullmsg="请输入产前样状态！" errormsg="请输入产前样状态" >
                            <option value="0" ${poVal.cqyzt eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.cqyzt eq '1' ? 'selected':''}>生产中</option>
                            <option value="2" ${poVal.cqyzt eq '2' ? 'selected':''}>已生产</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入产前样最晚确认时间！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入产前样最晚确认时间" class="Wdate" value="${poVal.cqyDate}" name="orderMxList[${status.index}].cqyDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入产前样距离最晚确认时间剩余天数！" datatype="n" errormsg="请输入产前样距离最晚确认时间剩余天数" value="${poVal.leavelCq}" name="orderMxList[${status.index}].leavelCq00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[#index#].syzt00" style="width: 86%;" nullmsg="请输入色样状态！" errormsg="请输入色样状态" >
                            <option value="0" ${poVal.syzt eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.syzt eq '1' ? 'selected':''}>生产中</option>
                            <option value="2" ${poVal.syzt eq '2' ? 'selected':''}>已生产</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入色样最晚确认时间！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入色样最晚确认时间" class="Wdate" value="${poVal.syDate}" name="orderMxList[${status.index}].syDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入色样距离最晚确认时间剩余天数！" datatype="n" errormsg="请输入色样距离最晚确认时间剩余天数" value="${poVal.leavelSy}" name="orderMxList[${status.index}].leavelSy00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center">
                        <select  name="orderMxList[#index#].cyzt00" style="width: 86%;" nullmsg="请输入船样状态！" errormsg="请输入船样状态" >
                            <option value="0" ${poVal.cyzt eq '0' ? 'selected':''}>未开始</option>
                            <option value="1" ${poVal.cyzt eq '1' ? 'selected':''}>生产中</option>
                            <option value="2" ${poVal.cyzt eq '2' ? 'selected':''}>已生产</option>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入船样最晚确认时间！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入船样最晚确认时间" class="Wdate" value="${poVal.cyDate}" name="orderMxList[${status.index}].cyDate00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入船样距离最晚确认时间剩余天数！" datatype="n" errormsg="请输入船样距离最晚确认时间剩余天数" value="${poVal.leavelCy}" name="orderMxList[${status.index}].leavelCy00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入出货日期！"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" errormsg="请输入出货日期" value="${poVal.outDate}" name="orderMxList[${status.index}].outDate00" class="Wdate" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

