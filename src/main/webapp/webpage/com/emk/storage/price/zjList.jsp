<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagzj = 0;
    var currentFlagzj = 0;

    $('#addBtnzj').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnzj').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnzj').bind('click', function(){
        flagzj++;
        var tr =  $("#add_jeecgOrderProduct_tabletzj2_templatezj tr").clone();
        $("#add_jeecgOrderProduct_tabletzj").append(tr);


        resetTrNum('add_jeecgOrderProduct_tabletzj');
        $("#zjListID").val($("#mxtbzj").find("tr").length-1);
    });
    $('#delBtnzj').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tabletzj").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tabletzj');
        $("#zjListID").val($("#mxtbzj").find("tr").length-1);


    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.priceId eq null || param.priceId eq '' || fn:length(emkSampleZjEntities) eq 0}">
        $('#addBtnzj').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tabletzj2_templatezj">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center">
            <select name="orderMxList[#index#].zjbmzl" style="width: 86%;" nullmsg="请输入布面种类！" errormsg="请输入布面种类" datatype="*">
                <c:forEach items="${zjList}" var="category">
                    <option value="${category.typecode}">${category.typename}</option>
                </c:forEach>
            </select>
            <%--<input id="gxgxmc00" nullmsg="请输入布面种类！"  errormsg="请输入布面种类"  name="orderMxList[#index#].bmzl" maxlength="100" type="text" value=""
                                      style="width: 86%;" ignore="ignore">--%></td>
        <td align="center"><input nullmsg="请输入助剂单价！"  errormsg="请输入助剂单价"  datatype="d" name="orderMxList[#index#].zjrsdj" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input nullmsg="请输入染色克重！"  errormsg="请输入染色克重" name="orderMxList[#index#].zjrskz" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input  nullmsg="请输入助剂成本！"  errormsg="请输入助剂成本" datatype="d" name="orderMxList[#index#].zjrscb" maxlength="100" type="text" value=""
                                   style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>
</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnzj" href="#"></a> <a id="delBtnzj" href="#"></a></div>
<form id="zjfrm">
    <input id="zjListID" type="hidden" name="zjListID" value="${fn:length(emkSampleZjEntities)}"/>

    <div class="table-c">
        <table id="mxtbzj" width="50%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40">序号</td>
                <td align="center"  width="150">布面种类</td>
                <td align="center"  width="150">助剂单价</td>
                <td align="center"  width="150">染色克重</td>
                <td align="center"  width="150">助剂成本</td>
            </tr>

            <tbody id="add_jeecgOrderProduct_tabletzj">
            <c:if test="${fn:length(emkSampleZjEntities)  > 0 }">
                <c:forEach items="${emkSampleZjEntities}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                        </td>
                        <td align="center">
                            <select name="orderMxList[${status.index}].zjbmzl" style="width: 86%;" nullmsg="请输入布面种类！" errormsg="请输入布面种类" datatype="*">
                                <c:forEach items="${zjList}" var="category">
                                    <option value="${category.typecode}" ${category.typecode eq poVal.buType ? 'selected':''}>${category.typename}</option>
                                </c:forEach>
                            </select>
                                <%-- <input  nullmsg="请输入布面种类！"  errormsg="请输入布面种类" value="${poVal.buType}" name="orderMxList[${status.index}].bmzl" maxlength="100" type="text" value=""
                                                        style="width: 86%;" ignore="ignore">--%></td>
                        <td align="center"><input  nullmsg="请输入助剂单价！"  errormsg="请输入助剂单价" datatype="d" value="${poVal.price}" name="orderMxList[${status.index}].zjrsdj" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入染色克重！"  errormsg="请输入染色克重" value="${poVal.oneWeight}" name="orderMxList[${status.index}].zjrskz" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input nullmsg="请输入助剂成本！"  errormsg="请输入助剂成本" datatype="d" value="${poVal.chengben}" name="orderMxList[${status.index}].zjrscb" maxlength="100" type="text" value=""
                                                  style="width: 86%;" ignore="ignore"></td>

                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</form>

