<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var srflag2 = 0;
    var currentFlag2 = 0;


    $('#addBtnSR2').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn2').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnSR2').bind('click', function(){
        srflag2++;
        var tr =  $("#add_jeecgOrderProduct_tableSR2_template tr").clone();
        $("#add_jeecgOrderProduct_tableSR2").append(tr);

        /*$("#add_jeecgOrderProduct_tableSR2").find("[id='signPrice00']").attr("datatype","d");
         $("#add_jeecgOrderProduct_tableSR2").find("[id='signPrice00']").attr("id","signPrice"+srflag2);*/
        resetTrNum('add_jeecgOrderProduct_tableSR2');
        $("#orderMxListIDSR2").val($("#mxtbSR2").find("tr").length-1);
    });
    $('#delBtn2').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tableSR2").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableSR2');
        $("#orderMxListIDSR2").val($("#mxtbSR2").find("tr").length-1);

        if(chk_value.length>0){

        }

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        <c:if test="${param.rId eq null || param.rId eq ''}">
            $('#addBtnSR2').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableSR2_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="orderNo00" nullmsg="请输入订单号！"  errormsg="请输入订单号" name="orderMxList[#index#].orderNo00" maxlength="100" type="text" value=""
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
        <td align="center">
            <select name="orderMxList[#index#].size" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                <c:forEach items="${sizeList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>
        <td align="center"><input id="signTotal00" nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" name="orderMxList[#index#].signTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="signPrice00" nullmsg="请输入单价！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].signPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sumMoney00" nullmsg="请输入总金额！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].sumMoney" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR2" href="#"></a> <a id="delBtn2" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR2" type="hidden" name="orderMxListIDSR2" value="${fn:length(emkInvoicesDetailEntities)}"/>
<div class="table-c">
    <table id="mxtbSR2" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" >序号</td>
            <td align="center"  width="120">订单号</td>
            <td align="center"  width="100">款号</td>
            <td align="center"  width="100">描述</td>
            <td align="center"  width="90">颜色</td>
            <td align="center"  width="90">尺码</td>
            <td align="center"  width="90">数量</td>
            <td align="center"  width="90">单价</td>
            <td align="center"  width="100">金额</td>
            <td align="center"  width="100">总金额</td>

        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR2">
        <c:if test="${fn:length(emkInvoicesDetailEntities)  > 0 }">
            <c:forEach items="${emkInvoicesDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center"><input  nullmsg="请输入订单号！"  errormsg="请输入订单号" value="${poVal.orderNo}" name="orderMxList[${status.index}].orderNo00" maxlength="100" type="text" value=""
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
                    <td align="center">
                        <select name="orderMxList[${status.index}].size" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                            <c:forEach items="${sizeList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.size ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入数量！" datatype="n" errormsg="请输入数量" value="${poVal.total}" name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单价！"  errormsg="请输入数值" datatype="d" value="${poVal.price}" name="orderMxList[${status.index}].signPrice" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入金额！"  errormsg="请输入数值" datatype="d" value="${poVal.money}" name="orderMxList[${status.index}].money" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入总金额！"  datatype="d" errormsg="请输入数值" value="${poVal.sumMoney}" name="orderMxList[${status.index}].sumMoney" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

