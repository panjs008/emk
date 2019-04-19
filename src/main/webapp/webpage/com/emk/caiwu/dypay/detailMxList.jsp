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
        <c:if test="${param.dyPayId eq null || param.dyPayId eq ''}">
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
        <td align="center"><input id="xqdNo00" nullmsg="请输入原料布料需求单号！"  errormsg="请输入原料布料需求单号" name="orderMxList[#index#].xqdNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cgdh00" nullmsg="请输入原料布料采购单号！"  errormsg="请输入原料布料采购单号" name="orderMxList[#index#].cgdh00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sampleNo00" nullmsg="请输入款号！"  errormsg="请输入款号" name="orderMxList[#index#].sampleNo00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="proZnName00" nullmsg="请输入材料名称！"  errormsg="请输入材料名称" name="orderMxList[#index#].proZnName00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="brand00" nullmsg="请输入规格！"  errormsg="请输入规格" name="orderMxList[#index#].brand00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="signTotal00" nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" name="orderMxList[#index#].signTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="signPrice00" nullmsg="请输入单价！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].signPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sumMoney00" nullmsg="请输入总金额！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].sumMoney" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkDyPayDetailEntities)}"/>
<div class="table-c">
    <table id="mxtbSR" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" >序号</td>
            <td align="center"  width="150">原料布料需求单号</td>
            <td align="center"  width="150">原料布料采购单号</td>
            <td align="center"  width="100">款号</td>
            <td align="center"  width="100">材料名称</td>
            <td align="center"  width="100">规格</td>
            <td align="center"  width="90">数量</td>
            <td align="center"  width="90">单价</td>
            <td align="center"  width="100">总金额</td>

        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkDyPayDetailEntities)  > 0 }">
            <c:forEach items="${emkDyPayDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center"><input  nullmsg="请输入原料布料需求单号！"  errormsg="请输入原料布料需求单号" value="${poVal.cgxqdh}" name="orderMxList[#index#].xqdNo00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入原料布料采购单号！"  errormsg="请输入原料布料采购单号" value="${poVal.cgdh}" name="orderMxList[#index#].cgdh00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入款号！"  errormsg="请输入款号" value="${poVal.sampleNo}" name="orderMxList[${status.index}].sampleNo00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入材料名称！"  errormsg="请输入材料名称" value="${poVal.proZnName}" name="orderMxList[#index#].proZnName00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入规格！"  errormsg="请输入规格"  value="${poVal.brand}" name="orderMxList[#index#].brand00" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！" datatype="n" errormsg="请输入数量" value="${poVal.signTotal}" name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单价！"  errormsg="请输入数值" datatype="d" value="${poVal.signPrice}" name="orderMxList[${status.index}].signPrice" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入总金额！"  datatype="d" errormsg="请输入数值" value="${poVal.sumPrice}" name="orderMxList[${status.index}].sumMoney" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

