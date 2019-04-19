<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagyin = 0;
    var currentFlagyin = 0;


    $('#addBtnyin').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnyin').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnyin').bind('click', function(){
        flagyin++;
        var tr =  $("#add_jeecgOrderProduct_tabletyin2_templateyin tr").clone();
        $("#add_jeecgOrderProduct_tabletyin").append(tr);

        resetTrNum('add_jeecgOrderProduct_tabletyin');
        $("#yinListID").val($("#mxtbyin").find("tr").length-1);
    });
    $('#delBtnyin').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tabletyin").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tabletyin');
        $("#yinListID").val($("#mxtbyin").find("tr").length-1);

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.priceId eq null || param.priceId eq '' || fn:length(emkSampleYinEntities) eq 0}">
            $('#addBtnyin').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tabletyin2_templateyin">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center">
            <select name="orderMxList[#index#].yhzl" style="width: 86%;" nullmsg="请输入印花工艺种类！" errormsg="请输入印花工艺种类" datatype="*">
                <c:forEach items="${yhgyzlList}" var="category">
                    <option value="${category.typecode}" ${category.typecode eq poVal.gyzy ? 'selected':''}>${category.typename}</option>
                </c:forEach>
            </select>
          </td>
        <td align="center"><input id="gxdjhs00" nullmsg="请输入印花大小！"  errormsg="请输入印花大小" name="orderMxList[#index#].yhdx" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gxunit00" nullmsg="请输入成本！" datatype="d"  errormsg="请输入成本" name="orderMxList[#index#].yhcb" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>
</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnyin" href="#"></a> <a id="delBtnyin" href="#"></a></div>
<form id="yinfrm">
    <input id="yinListID" type="hidden" name="yinListID" value="${fn:length(emkSampleYinEntities)}"/>

    <div class="table-c">
        <table id="mxtbyin" width="40%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40">序号</td>
                <td align="center"  width="150">印花工艺种类</td>
                <td align="center"  width="150">印花大小</td>
                <td align="center"  width="150">成本</td>
            </tr>

            <tbody id="add_jeecgOrderProduct_tabletyin">
            <c:if test="${fn:length(emkSampleYinEntities)  > 0 }">
                <c:forEach items="${emkSampleYinEntities}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                        </td>

                        <td align="center">
                            <select name="orderMxList[${status.index}].yhzl" style="width: 86%;" nullmsg="请输入印花工艺种类！" errormsg="请输入印花工艺种类" datatype="*">
                                <c:forEach items="${yhgyzlList}" var="category">
                                    <option value="${category.typecode}" ${category.typecode eq poVal.gyzy ? 'selected':''}>${category.typename}</option>
                                </c:forEach>
                            </select>
                           <%-- <input  nullmsg="请输入印花工艺种类！"  errormsg="请输入印花工艺种类" value="${poVal.gyzy}" name="orderMxList[${status.index}].yhzl" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore">--%></td>
                        <td align="center"><input  nullmsg="请输入印花大小！"  errormsg="请输入印花大小" value="${poVal.size}" name="orderMxList[${status.index}].yhdx" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入成本！" datatype="d" errormsg="请输入成本" value="${poVal.chengben}" name="orderMxList[${status.index}].yhcb" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>

                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    </form>

