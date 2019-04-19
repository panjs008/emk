<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var boxflag1 = 0;
    var currentFlag = 0;


    $('#addBtnBox1').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnBox1').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnBox1').bind('click', function(){
        boxflag1++;
        var tr =  $("#add_jeecgOrderProduct_tableBox1_template tr").clone();
        $("#add_jeecgOrderProduct_tableBox1").append(tr);
        if(boxflag1>1 || ${param.proOrderId ne null}){
            $("html,body").animate({scrollTop:1100},1);
        }
        /*$("#add_jeecgOrderProduct_tableBox1").find("[id='signPrice00']").attr("datatype","d");
         $("#add_jeecgOrderProduct_tableBox1").find("[id='signPrice00']").attr("id","signPrice"+boxflag1);*/
        resetTrNum('add_jeecgOrderProduct_tableBox1');
        $("#orderMxListIDBox1").val($("#mxtbBox1").find("tr").length-1);
    });
    $('#delBtnBox1').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("html,body").animate({scrollTop:1100},1);
        $("#add_jeecgOrderProduct_tableBox1").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableBox1');
        $("#orderMxListIDBox1").val($("#mxtbBox1").find("tr").length-1);

        if(chk_value.length>0){

        }

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        <c:if test="${param.proOrderId eq null || param.proOrderId eq ''}">
        $('#addBtnBox1').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableBox1_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="bcolorName00" nullmsg="请输入颜色名称！"  errormsg="请输入颜色名称" name="orderMxList[#index#].bcolorName00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select name="orderMxList[#index#].bsizeBox00" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                <c:forEach items="${sizeList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>

        <td align="center"><input id="binboxTotal00" nullmsg="请输入箱内数量！"  errormsg="请输入整数" name="orderMxList[#index#].binboxTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="btotalBox00" nullmsg="请输入箱数！"  errormsg="请输入整数" name="orderMxList[#index#].btotalBox00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bsumZsl00" nullmsg="请输入总数量！"  errormsg="请输入整数" name="orderMxList[#index#].bsumZsl00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bsumTotal00" nullmsg="请输入总箱数！"  errormsg="请输入整数" name="orderMxList[#index#].bsumTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="blongVal00" nullmsg="请输入长！"  errormsg="请输入数值型数据" name="orderMxList[#index#].blongVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bwidthVal00" nullmsg="请输入宽！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bwidthVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bheightVal00" nullmsg="请输入高！"  errormsg="请输入数值型数据" name="orderMxList[#index#].heightVal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bboxWeightMao00" nullmsg="请输入单箱毛重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bboxWeightMao00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bboxWeightJz00" nullmsg="请输入单箱净重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bboxWeightJz00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bboxVolume00" nullmsg="请输入单箱体积！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bboxVolume00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bsumVolume00" nullmsg="请输入总体积！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bsumVolume00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bsumWeightMao00" nullmsg="请输入总毛重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bsumWeightMao00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bsumWeightJz00" nullmsg="请输入总净重！"  errormsg="请输入数值型数据" name="orderMxList[#index#].bsumWeightJz00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnBox1" href="#"></a> <a id="delBtnBox1" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDBox1" type="hidden" name="orderMxListIDBox1" value="${fn:length(emkProOrderBoxEntities)}"/>
<div class="table-c">
    <table id="mxtbBox1" width="100%" border="0" cellspacing="0" cellpadding="0">
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

        <tbody id="add_jeecgOrderProduct_tableBox1">
        <c:if test="${fn:length(emkProOrderBoxEntities)  > 0 }">
        <c:forEach items="${emkProOrderBoxEntities}" var="poVal" varStatus="status">
        <tr>
            <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
            </td>
            <td align="center"><input  nullmsg="请输入颜色名称！"  errormsg="请输入颜色名称" value="${poVal.colorName}" name="orderMxList[${status.index}].bcolorName00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center">
                <select name="orderMxList[${status.index}].bsizeBox00" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                    <c:forEach items="${sizeList}" var="category">
                        <option value="${category.typecode}" ${category.typecode eq poVal.size ? 'selected':''}>${category.typename}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="center"><input  nullmsg="请输入箱内数量！"  errormsg="请输入整数" value="${poVal.total}" name="orderMxList[${status.index}].binboxTotal00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入箱数！"  errormsg="请输入整数" value="${poVal.total}" name="orderMxList[${status.index}].btotalBox00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入总数量！"  errormsg="请输入整数" value="${poVal.sumZsl}" name="orderMxList[${status.index}].bsumZsl00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入总箱数！"  errormsg="请输入整数" value="${poVal.sumTotal}" name="orderMxList[${status.index}].bsumTotal00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入长！"  errormsg="请输入数值型数据" value="${poVal.longVal}" name="orderMxList[${status.index}].blongVal00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入宽！"  errormsg="请输入数值型数据" value="${poVal.widthVal}" name="orderMxList[${status.index}].bwidthVal00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入高！"  errormsg="请输入整数" value="${poVal.heightVal}" name="orderMxList[${status.index}].bheightVal00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入单箱毛重！"  errormsg="请输入数值型数据" value="${poVal.boxWeightMao}" name="orderMxList[${status.index}].bboxWeightMao00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入单箱净重！"  errormsg="请输入数值型数据" value="${poVal.boxWeightJz}" name="orderMxList[${status.index}].bboxWeightJz00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入单箱体积！"  errormsg="请输入数值型数据" value="${poVal.boxVolume}" name="orderMxList[${status.index}].bboxVolume00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入总体积！"  errormsg="请输入数值型数据" value="${poVal.sumVolume}" name="orderMxList[${status.index}].bsumVolume00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入总毛重！"  errormsg="请输入数值型数据" value="${poVal.sumWeightMao}" name="orderMxList[${status.index}].bsumWeightMao00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input  nullmsg="请输入总净重！"  errormsg="请输入数值型数据" value="${poVal.sumWeightJz}" name="orderMxList[${status.index}].bsumWeightJz00" maxlength="100" type="text" value=""
                                       style="width: 86%;" ignore="ignore"></td>

                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

