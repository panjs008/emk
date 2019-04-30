<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var boxflag = 0;
    var currentFlag = 0;


    $('#addBtnBox').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnBox').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnBox').bind('click', function(){
        boxflag++;
        //$("#add_jeecgOrderProduct_tableBox").find("[id='signPrice00']").attr("datatype","d");

        var tr =  $("#add_jeecgOrderProduct_tableBox_template tr").clone();
        $("#add_jeecgOrderProduct_tableBox").append(tr);
        if(boxflag>1 || ${param.proOrderId ne null}){
            $("html,body").animate({scrollTop:1000},1);
        }

        /*$("#add_jeecgOrderProduct_tableBox").find("[id='atotalBox00']").attr("id","atotalBox"+boxflag);
        $("#add_jeecgOrderProduct_tableBox").find("[id='asumZsl00']").attr("id","asumZsl"+boxflag);*/
        resetTrNum('add_jeecgOrderProduct_tableBox');
        $("#orderMxListIDBox").val($("#mxtbBox").find("tr").length-1);
    });
    $('#delBtnBox').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("html,body").animate({scrollTop:1000},1);
        $("#add_jeecgOrderProduct_tableBox").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableBox');
        $("#orderMxListIDBox").val($("#mxtbBox").find("tr").length-1);

        if(chk_value.length>0){

        }

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        <c:if test="${param.proOrderId eq null || param.proOrderId eq ''}">
            $('#addBtnBox').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableBox_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="acolorName00" nullmsg="请输入颜色名称！"  errormsg="请输入颜色名称" name="orderMxList[#index#].acolorName00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select name="orderMxList[#index#].asizeBox00" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                <c:forEach items="${sizeList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>

        <td align="center"><input id="ainboxTotal00" datatype="n"  nullmsg="请输入箱内数量！"  errormsg="请输入整数" name="orderMxList[#index#].ainboxTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="atotalBox00" datatype="n"  nullmsg="请输入箱数！"  errormsg="请输入整数" name="orderMxList[#index#].atotalBox00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="asumZsl00" datatype="n"  nullmsg="请输入总数量！"  errormsg="请输入整数" name="orderMxList[#index#].asumZsl00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="asumTotal00" datatype="n"  nullmsg="请输入总箱数！"  errormsg="请输入整数" name="orderMxList[#index#].asumTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="alongVal00" datatype="d"  nullmsg="请输入长！"  errormsg="请输入数值型数据" name="orderMxList[#index#].alongVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="awidthVal00" datatype="d" nullmsg="请输入宽！"  errormsg="请输入数值型数据" name="orderMxList[#index#].awidthVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="aheightVal00" datatype="d" nullmsg="请输入高！"  errormsg="请输入数值型数据" name="orderMxList[#index#].heightVal00" maxlength="100" type="text" value=""
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
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnBox" href="#"></a> <a id="delBtnBox" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<form id="boxFrm0">
    <input id="orderMxListIDBox" type="hidden" name="orderMxListIDBox" value="${fn:length(emkProOrderBoxEntities)}"/>
    <div class="table-c">
        <c:set var="zxs" value="0"/>
        <c:set var="xnzs" value="0"/>

        <table id="mxtbBox" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40" >序号</td>
                <td align="center"  width="90">颜色名称</td>
                <td align="center"  width="90">尺码</td>
                <td align="center"  width="90">箱内数量</td>
                <td align="center"  width="90">箱数</td>
                <td align="center"  width="90">总数量</td>
                <td align="center"  width="90">总箱数</td>
                <td align="center"  width="90">长（CM)</td>
                <td align="center"  width="90">宽(cm)</td>
                <td align="center"  width="90">高(CM)</td>
                <td align="center"  width="90">单箱毛重</td>
                <td align="center"  width="90">单箱净重</td>
                <td align="center"  width="90">单箱体积</td>
                <td align="center"  width="90">总体积</td>
                <td align="center"  width="90">总毛重</td>
                <td align="center"  width="90">总净重</td>
            </tr>

            <tbody id="add_jeecgOrderProduct_tableBox">
            <c:if test="${fn:length(emkProOrderBoxEntities)  > 0 }">
                <c:forEach items="${emkProOrderBoxEntities}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                        </td>
                        <td align="center"><input  nullmsg="请输入颜色名称！"  errormsg="请输入颜色名称" value="${poVal.colorName}" name="orderMxList[${status.index}].acolorName00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <select name="orderMxList[${status.index}].asizeBox00" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                                <c:forEach items="${sizeList}" var="category">
                                    <option value="${category.typecode}" ${category.typecode eq poVal.size ? 'selected':''}>${category.typename}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="center"><input  nullmsg="请输入箱内数量！" datatype="n"  errormsg="请输入整数" value="${poVal.inboxTotal}" name="orderMxList[${status.index}].ainboxTotal00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <c:set var="zxs" value="${zxs+poVal.total}"/>
                            <c:set var="xnzs" value="${xnzs+poVal.inboxTotal}"/>

                            <input  nullmsg="请输入箱数！"  datatype="n" errormsg="请输入整数" value="${poVal.total}" name="orderMxList[${status.index}].atotalBox00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入总数量！" datatype="n"  errormsg="请输入整数" value="${poVal.sumZsl}" name="orderMxList[${status.index}].asumZsl00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入总箱数！" datatype="n" errormsg="请输入整数" value="${poVal.sumTotal}" name="orderMxList[${status.index}].asumTotal00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入长！" datatype="d" errormsg="请输入数值型数据" value="${poVal.longVal}" name="orderMxList[${status.index}].alongVal00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入宽！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.widthVal}" name="orderMxList[${status.index}].awidthVal00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入高！"  datatype="d" errormsg="请输入整数" value="${poVal.heightVal}" name="orderMxList[${status.index}].aheightVal00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单箱毛重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.boxWeightMao}" name="orderMxList[${status.index}].aboxWeightMao00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单箱净重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.boxWeightJz}" name="orderMxList[${status.index}].aboxWeightJz00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单箱体积！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.boxVolume}" name="orderMxList[${status.index}].aboxVolume00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入总体积！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.sumVolume}" name="orderMxList[${status.index}].asumVolume00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入总毛重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.sumWeightMao}" name="orderMxList[${status.index}].asumWeightMao00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入总净重！"  datatype="d" errormsg="请输入数值型数据" value="${poVal.sumWeightJz}" name="orderMxList[${status.index}].asumWeightJz00" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>

                    </tr>

                </c:forEach>
                <tr>
                    <td colspan="3"></td>
                    <td align="center"><input   type="text" value="${xnzs}" style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input   type="text" value="${zxs}" style="width: 86%;" ignore="ignore"></td>
                    <td colspan="11"></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</form>

