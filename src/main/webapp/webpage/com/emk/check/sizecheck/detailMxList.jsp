<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var srflag = ${fn:length(emkSizeCheckDetailEntities)};
    var currentFlag = ${fn:length(emkSizeCheckDetailEntities)};


    $('#addBtnSR').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnSR').bind('click', function(){
        srflag++;
        $("#seqNum").html(String.fromCharCode(srflag+64));
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
        <c:if test="${param.sizeCheckId eq null || param.sizeCheckId eq ''}">
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
        <td align="center" width="2%"><span id="seqNum"></span></td>
        <td align="center"><input id="buWei00" nullmsg="请输入部位！"  errormsg="请输入部位" name="orderMxList[#index#].buWei00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalA" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalA" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalA2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalA2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalA3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalA3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalA4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalA4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalB" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalB" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalB2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalB2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalB3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalB3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalB4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalB4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalC" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalC" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalC2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalC2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalC3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalC3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalC4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalC4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalD" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalD" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalD2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalD2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalD3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalD3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalD4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalD4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalE" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalE" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalE2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalE2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalE3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalE3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalE4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalE4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalF" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalF" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalF2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalF2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalF3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalF3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalF4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalF4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalG" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalG" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalG2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalG2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalG3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalG3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalG4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalG4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalH" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalH" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalH2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalH2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalH3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalH3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalH4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalH4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalI" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalI" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalI2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalI2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalI3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalI3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalI4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalI4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalJ" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalJ" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalJ2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalJ2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalJ3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalJ3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalJ4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalJ4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

        <td align="center"><input id="totalK" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalK" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalK2" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalK2" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalK3" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalK3" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="totalK4" nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].totalK4" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(emkSizeCheckDetailEntities)}"/>
<div class="table-c" style="overflow: auto; width: 100%;">
    <table id="mxtbSR" width="2800px"  border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" rowspan="2"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
            <td align="center"  width="120" colspan="2"  rowspan="2">测量部位</td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeA}" name="sizeA" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeB}" name="sizeB" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeC}" name="sizeC" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeD}" name="sizeD" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeE}" name="sizeE" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeF}" name="sizeF" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeG}" name="sizeG" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeH}" name="sizeH" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeI}" name="sizeI" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeJ}" name="sizeJ" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
            <td align="center" colspan="4" ><input   value="${emkSizePage.sizeK}" name="sizeK" maxlength="100" type="text" value=""
                                        style="width: 25%;" ignore="ignore"></td>
        </tr>
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>
            <td align="center"  width="70">标准尺寸</td>
            <td align="center"  width="150" colspan="3">样衣尺寸</td>

        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(emkSizeCheckDetailEntities)  > 0 }">
            <c:forEach items="${emkSizeCheckDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center" width="2%">${poVal.seqNum}</td>
                    <td align="center"><input  nullmsg="请输入部位！"  errormsg="请输入部位" value="${poVal.buWei}" name="orderMxList[${status.index}].buWei00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数" value="${poVal.emkSizeTotalEntity.totalA}" name="orderMxList[${status.index}].totalA" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalA}" name="orderMxList[${status.index}].totalA2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalA}" name="orderMxList[${status.index}].totalA3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalA}" name="orderMxList[${status.index}].totalA4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalB}" name="orderMxList[${status.index}].totalB" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalB}" name="orderMxList[${status.index}].totalB2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalB}" name="orderMxList[${status.index}].totalB3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalB}" name="orderMxList[${status.index}].totalB4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalC}" name="orderMxList[${status.index}].totalC" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalC}" name="orderMxList[${status.index}].totalC2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalC}" name="orderMxList[${status.index}].totalC3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalC}" name="orderMxList[${status.index}].totalC4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalD}" name="orderMxList[${status.index}].totalD" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalD}" name="orderMxList[${status.index}].totalD2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalD}" name="orderMxList[${status.index}].totalD3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalD}" name="orderMxList[${status.index}].totalD4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalE}" name="orderMxList[${status.index}].totalE" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalE}" name="orderMxList[${status.index}].totalE2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalE}" name="orderMxList[${status.index}].totalE3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalE}" name="orderMxList[${status.index}].totalE4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalF}" name="orderMxList[${status.index}].totalF" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalF}" name="orderMxList[${status.index}].totalF2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalF}" name="orderMxList[${status.index}].totalF3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalF}" name="orderMxList[${status.index}].totalF4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalG}" name="orderMxList[${status.index}].totalG" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalG}" name="orderMxList[${status.index}].totalG2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalG}" name="orderMxList[${status.index}].totalG3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalG}" name="orderMxList[${status.index}].totalG4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalH}" name="orderMxList[${status.index}].totalH" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalH}" name="orderMxList[${status.index}].totalH2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalH}" name="orderMxList[${status.index}].totalH3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalH}" name="orderMxList[${status.index}].totalH4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalI}" name="orderMxList[${status.index}].totalI" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalI}" name="orderMxList[${status.index}].totalI2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalI}" name="orderMxList[${status.index}].totalI3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalI}" name="orderMxList[${status.index}].totalI4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalJ}" name="orderMxList[${status.index}].totalJ" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalJ}" name="orderMxList[${status.index}].totalJ2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalJ}" name="orderMxList[${status.index}].totalJ3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalJ}" name="orderMxList[${status.index}].totalJ4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>

                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.emkSizeTotalEntity.totalK}" name="orderMxList[${status.index}].totalK" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.btotalK}" name="orderMxList[${status.index}].totalK2" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.ctotalK}" name="orderMxList[${status.index}].totalK3" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  datatype="n"  errormsg="请输入整数"  value="${poVal.dtotalK}" name="orderMxList[${status.index}].totalK4" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

