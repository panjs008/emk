<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var barcodeflag2 = 0;
    var currentFlag = 0;


    $('#addBtnBarCode2').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnBarCode2').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnBarCode2').bind('click', function(){
        barcodeflag2++;
        var tr =  $("#add_jeecgOrderProduct_tableBarCode2_template tr").clone();
        $("#add_jeecgOrderProduct_tableBarCode2").append(tr);
        if(barcodeflag2>1 || ${param.proOrderId ne null}){
            $("html,body").animate({scrollTop:500},1);
        }
        /*$("#add_jeecgOrderProduct_tableBarCode2").find("[id='signPrice00']").attr("datatype","d");
         $("#add_jeecgOrderProduct_tableBarCode2").find("[id='signPrice00']").attr("id","signPrice"+barcodeflag2);*/
        resetTrNum('add_jeecgOrderProduct_tableBarCode2');
        $("#orderMxListIDBarCode2").val($("#mxtbBarCode2").find("tr").length-1);
    });
    $('#delBtnBarCode2').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("html,body").animate({scrollTop:500},1);

        $("#add_jeecgOrderProduct_tableBarCode2").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableBarCode2');
        $("#orderMxListIDBarCode2").val($("#mxtbBarCode2").find("tr").length-1);

        if(chk_value.length>0){

        }

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");

        <c:if test="${param.proOrderId eq null || param.proOrderId eq ''}">
            $('#addBtnBarCode2').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableBarCode2_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center">
            <select name="orderMxList[#index#].ccolor00" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                <c:forEach items="${colorList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>
        <td align="center">
            <select name="orderMxList[#index#].csize00" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                <c:forEach items="${sizeList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
        </td>

        <td align="center"><input id="ccode00" nullmsg="请输入条码！" datatype="*"  errormsg="请输入条码" name="orderMxList[#index#].ccode00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="csignTotal00" nullmsg="请输入数量！" datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].csignTotal00" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>


    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnBarCode2" href="#"></a> <a id="delBtnBarCode2" href="#"></a></div>
<input id="orderMxListIDBarCode2" type="hidden" name="orderMxListIDBarCode2" value="${fn:length(emkProOrderBarcodeEntities)}"/>
<div class="table-c">
    <table id="mxtbBarCode2" width="50%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" >序号</td>
            <td align="center"  width="90">颜色</td>
            <td align="center"  width="90">尺码</td>
            <td align="center"  width="120">条码</td>
            <td align="center"  width="120">数量</td>

        </tr>

        <tbody id="add_jeecgOrderProduct_tableBarCode2">
        <c:if test="${fn:length(emkProOrderBarcodeEntities)  > 0 }">
            <c:forEach items="${emkProOrderBarcodeEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].ccolor00" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                            <c:forEach items="${colorList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.color ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].csize00" style="width: 86%;" nullmsg="请输入尺码！" errormsg="请输入尺码" datatype="*">
                            <c:forEach items="${sizeList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.size ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center"><input  nullmsg="请输入条码！"  errormsg="请输入条码" datatype="*"  value="${poVal.code}" name="orderMxList[${status.index}].ccode00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  errormsg="请输入整数" datatype="n"  value="${poVal.total}" name="orderMxList[${status.index}].csignTotal00" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

