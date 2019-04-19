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
        <c:if test="${param.costId eq null || param.costId eq ''}">
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
            <select name="orderMxList[${status.index}].testType00" style="width: 86%;" nullmsg="请输入测试种类！" errormsg="请输入测试种类" datatype="*">
                <c:forEach items="${testTypeList}" var="category">
                    <option value="${category.typecode}" ${category.typecode eq poVal.testType ? 'selected':''}>${category.typename}</option>
                </c:forEach>
            </select></td>
        <td align="center"><input id="testContent00" nullmsg="请输入测试内容！" errormsg="请输入测试内容" name="orderMxList[#index#].testContent00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="testResult00" nullmsg="请输入测试结果！" errormsg="请输入测试结果" name="orderMxList[#index#].testResult00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="limitDate00" nullmsg="请输入报告有效期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="Wdate" errormsg="请输入报告有效期" name="orderMxList[#index#].limitDate00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="testMoney00" nullmsg="请输入测试费用！" datatype="d" errormsg="请输入测试费用" name="orderMxList[#index#].testMoney00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="testState00" nullmsg="请输入测试费用付费状态！" errormsg="请输入测试费用付费状态" name="orderMxList[#index#].testState00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="testFp00" nullmsg="请输入测试费发票号！" errormsg="请输入测试费发票号" name="orderMxList[#index#].testFp00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="testNo00" nullmsg="请输入测试报告号！" errormsg="请输入测试报告号" name="orderMxList[#index#].testNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cssqdh00" nullmsg="请输入测试申请表编号！" errormsg="请输入测试申请表编号" name="orderMxList[#index#].cssqdh00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkTestCostDetailEntities)}"/>
<div class="table-c">
    <table id="mxtbSR" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" >序号</td>
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
            <td align="center"  width="100">测试种类</td>
            <td align="center"  width="150">测试内容</td>
            <td align="center"  width="150">测试结果</td>
            <td align="center"  width="120">报告有效期</td>
            <td align="center"  width="100">测试费用</td>
            <td align="center"  width="120">测试费用付费状态</td>
            <td align="center"  width="120">测试费发票号</td>
            <td align="center"  width="120">测试报告号</td>
            <td align="center"  width="120">测试申请表编号</td>

        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkTestCostDetailEntities)  > 0 }">
            <c:forEach items="${emkTestCostDetailEntities}" var="poVal" varStatus="status">
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

                    <td align="center"><input  nullmsg="请输入数量！" datatype="n" errormsg="请输入数量" value="${poVal.sumTotal}" name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>


                    <td align="center">
                        <select name="orderMxList[${status.index}].testType00" style="width: 86%;" nullmsg="请输入测试种类！" errormsg="请输入测试种类" datatype="*">
                            <c:forEach items="${testTypeList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.testType ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入测试内容！"  errormsg="请输入测试内容" value="${poVal.testContent}" name="orderMxList[${status.index}].testContent00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入测试结果！"  errormsg="请输入测试结果" value="${poVal.testResult}" name="orderMxList[${status.index}].testResult00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input nullmsg="请输入报告有效期！" readonly  value="${poVal.testResult}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="Wdate" errormsg="请输入报告有效期" name="orderMxList[#index#].limitDate00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入测试费用金额！"  errormsg="请输入数值" datatype="n" value="${poVal.testMoney}" name="orderMxList[${status.index}].testMoney00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入测试费用付费状态！" value="${poVal.testState}" errormsg="请输入测试费用付费状态" name="orderMxList[${status.index}].testState00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入测试费发票号！" value="${poVal.testFp}" errormsg="请输入测试费发票号" name="orderMxList[${status.index}].testFp00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入测试报告号！"  errormsg="请输入测试报告号" value="${poVal.testNo}" name="orderMxList[${status.index}].testNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入测试申请表编号！" errormsg="请输入测试申请表编号" value="${poVal.testMoney}" name="orderMxList[${status.index}].cssqdh00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

