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
        <c:if test="${param.pactId eq null || param.pactId eq ''}">
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
        <td align="center"><input id="totalA" nullmsg="请输入数量！" datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalA" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalB" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalB" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalC" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalC" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalD" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalD" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalE" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalE" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalF" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalF" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalG" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalG" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalH" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalH" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalI" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalI" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalJ" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalJ" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalK" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalK" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
       <%-- <td align="center">
            <select name="orderMxList[#index#].size" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                <c:forEach items="${sizeList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>
        <td align="center"><input id="signTotal00" nullmsg="请输入数量！"  datatype="n" errormsg="请输入整数" name="orderMxList[#index#].signTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>--%>
        <td align="center"><input id="alongVal00" datatype="d"  nullmsg="请输入长！"  errormsg="请输入数值型数据" name="orderMxList[#index#].alongVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="awidthVal00" datatype="d" nullmsg="请输入宽！"  errormsg="请输入数值型数据" name="orderMxList[#index#].awidthVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="aheightVal00" datatype="d" nullmsg="请输入高！"  errormsg="请输入数值型数据" name="orderMxList[#index#].heightVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="atotalBox00" datatype="n"  nullmsg="请输入箱数！"  errormsg="请输入整数" name="orderMxList[#index#].atotalBox00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="aboxWeightMao00" datatype="d" nullmsg="请输入单箱毛重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].aboxWeightMao00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="aboxWeightJz00" datatype="d" nullmsg="请输入单箱净重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].aboxWeightJz00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="aboxVolume00" datatype="d" nullmsg="请输入单箱体积！"  errormsg="请输入数值型数据" name="orderMxList[#index#].aboxVolume00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="asumVolume00" datatype="d" nullmsg="请输入总体积！"  errormsg="请输入数值型数据" name="orderMxList[#index#].asumVolume00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="asumWeightMao00" datatype="d" nullmsg="请输入总毛重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].asumWeightMao00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="asumWeightJz00" datatype="d" nullmsg="请输入总净重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].asumWeightJz00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkPackingListDetailEntities)}"/>
<div class="table-c">
    <table id="mxtbSR" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" rowspan="2"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
            <td align="center"  width="110" rowspan="2">订单号</td>
            <td align="center"  width="80" rowspan="2">款号</td>
            <td align="center"  width="80" rowspan="2">描述</td>
            <td align="center"  width="70" rowspan="2">颜色</td>
            <%--<td align="center"  width="70" rowspan="2">尺码</td>
            <td align="center"  width="90" rowspan="2">数量</td>--%>
            <td align="center"  width="500" colspan="11" >尺码</td>
            <td align="center"  width="50" rowspan="2">长</td>
            <td align="center"  width="50" rowspan="2">宽</td>
            <td align="center"  width="50" rowspan="2">高</td>
            <td align="center"  width="50" rowspan="2">箱数</td>
            <td align="center"  width="50" rowspan="2">单箱<br/>毛重</td>
            <td align="center"  width="50" rowspan="2">单箱<br/>净重</td>
            <td align="center"  width="50" rowspan="2">单箱<br/>体积</td>
            <td align="center"  width="50" rowspan="2">总体积</td>
            <td align="center"  width="50" rowspan="2">总毛重</td>
            <td align="center"  width="50" rowspan="2">总净重</td>
        </tr>
        <tr>
            <td align="center" ><input  value="${emkSizePage.sizeA}" name="sizeA" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeB}" name="sizeB" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeC}" name="sizeC" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeD}" name="sizeD" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeE}" name="sizeE" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeF}" name="sizeF" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeG}" name="sizeG" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeH}" name="sizeH" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeI}" name="sizeI" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeJ}" name="sizeJ" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
            <td align="center" ><input  value="${emkSizePage.sizeK}" name="sizeK" maxlength="100" type="text" value="" style="width: 80%;" ignore="ignore"></td>
        </tr>
        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkPackingListDetailEntities)  > 0 }">
            <c:forEach items="${emkPackingListDetailEntities}" var="poVal" varStatus="status">
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
                   <%-- <td align="center">
                        <select name="orderMxList[${status.index}].size" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                            <c:forEach items="${sizeList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.size ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入数量！" datatype="n" errormsg="请输入数量" value="${poVal.total}" name="orderMxList[${status.index}].signTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>--%>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" value="${poVal.emkSizeTotalEntity.totalA}" name="orderMxList[${status.index}].totalA" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalB}" name="orderMxList[${status.index}].totalB" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalC}" name="orderMxList[${status.index}].totalC" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalD}" name="orderMxList[${status.index}].totalD" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalE}" name="orderMxList[${status.index}].totalE" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalF}" name="orderMxList[${status.index}].totalF" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalG}" name="orderMxList[${status.index}].totalG" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalH}" name="orderMxList[${status.index}].totalH" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalI}" name="orderMxList[${status.index}].totalI" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalJ}" name="orderMxList[${status.index}].totalJ" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalK}" name="orderMxList[${status.index}].totalK" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入长！" datatype="d" errormsg="请输入数值型数据" value="${poVal.changdu}" name="orderMxList[${status.index}].alongVal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入宽！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.kuandu}" name="orderMxList[${status.index}].awidthVal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入高！"  datatype="d" errormsg="请输入整数" value="${poVal.gaodu}" name="orderMxList[${status.index}].aheightVal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入箱数！"  datatype="n" errormsg="请输入整数" value="${poVal.sumBoxTotal}" name="orderMxList[${status.index}].atotalBox00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单箱毛重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.oneBoxVolume}" name="orderMxList[${status.index}].aboxWeightMao00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单箱净重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.oneBoxJz}" name="orderMxList[${status.index}].aboxWeightJz00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单箱体积！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.oneBoxMz}" name="orderMxList[${status.index}].aboxVolume00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入总体积！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.sumBoxVolume}" name="orderMxList[${status.index}].asumVolume00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入总毛重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.sumBoxMao}" name="orderMxList[${status.index}].asumWeightMao00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入总净重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.sumBoxJz}" name="orderMxList[${status.index}].asumWeightJz00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

