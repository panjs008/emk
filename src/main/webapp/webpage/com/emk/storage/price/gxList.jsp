<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flaggx = ${fn:length(emkSampleGxEntities)};
    var currentFlaggx = 0;


    $('#addBtngx').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtngx').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtngx').bind('click', function(){
        flaggx++;
        $("#dseqNum").html(flaggx);
        var tr =  $("#add_jeecgOrderProduct_tabletgx2_templategx tr").clone();
        $("#add_jeecgOrderProduct_tabletgx").append(tr);

        /*$("#add_jeecgOrderProduct_tabletgx").find("[id='signTotal00']").attr("datatype","n");
        $("#add_jeecgOrderProduct_tabletgx").find("[id='signTotal00']").attr("id","signTotal"+flaggx);*/

        resetTrNum('add_jeecgOrderProduct_tabletgx');
        $("#gxListID").val($("#mxtbgx").find("tr").length-1);
    });
    $('#delBtngx').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tabletgx").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tabletgx');
        $("#gxListID").val($("#mxtbgx").find("tr").length-1);

        if(chk_value.length>0){

        }

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.priceId eq null || param.priceId eq '' || fn:length(emkSampleGxEntities) eq 0}">
            $('#addBtngx').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tabletgx2_templategx">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" /></td>
        <td align="center" width="40"><span id="dseqNum"></span></td>
        <td align="center"><input id="gxmc00" nullmsg="请输入工序名称！"  errormsg="请输入工序名称"  name="orderMxList[#index#].gxmc" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gxdjhs00" nullmsg="请输入单件耗时！"  errormsg="请输入单件耗时" name="orderMxList[#index#].gxdjhs" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gxunit00" nullmsg="请输入单位！"  errormsg="请输入单位" name="orderMxList[#index#].gxunit" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gxproductTotal00" nullmsg="请输入每天产量！" datatype="d" errormsg="请输入每天产量" name="orderMxList[#index#].gxproductTotal" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gxchengben00" nullmsg="请输入成本！" datatype="d" errormsg="请输入成本" name="orderMxList[#index#].gxchengben" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>
</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtngx" href="#"></a> <a id="delBtngx" href="#"></a></div>
<form id="gxfrm">
    <input id="gxListID" type="hidden" name="gxListID" value="${fn:length(emkSampleGxEntities)}"/>

    <div class="table-c">
        <table id="mxtbgx" width="70%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
                <td align="center" width="40">序号</td>
                <td align="center"  width="150">工序名称</td>
                <td align="center"  width="150">单件耗时</td>
                <td align="center"  width="150">单位</td>
                <td align="center"  width="150">每天产量</td>
                <td align="center"  width="150">成本</td>
            </tr>

            <tbody id="add_jeecgOrderProduct_tabletgx">
            <c:if test="${fn:length(emkSampleGxEntities)  > 0 }">
                <c:forEach items="${emkSampleGxEntities}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/></td>
                        <td align="center" width="40">${status.index+1}</td>
                        <td align="center"><input  nullmsg="请输入工序名称！"  errormsg="请输入工序名称" value="${poVal.gxmc}" name="orderMxList[${status.index}].gxmc" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单件耗时！"  errormsg="请输入单件耗时" value="${poVal.djhs}" name="orderMxList[${status.index}].gxdjhs" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单位！"  errormsg="请输入单位" value="${poVal.unit}" name="orderMxList[${status.index}].gxunit" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input nullmsg="请输入每天产量！"  errormsg="请输入每天产量" datatype="d" value="${poVal.productTotal}" name="orderMxList[${status.index}].gxproductTotal" maxlength="100" type="text" value=""
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入成本！"  errormsg="请输入成本" datatype="d" value="${poVal.chengben}" name="orderMxList[${status.index}].gxchengben" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</form>

