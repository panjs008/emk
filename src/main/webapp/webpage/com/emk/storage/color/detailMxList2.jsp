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
        if(srflag>1 || ${param.colorId ne null}){
            $("html,body").animate({scrollTop:400},1);
        }
        var tr =  $("#add_jeecgOrderProduct_tableSR_template tr").clone();
        $("#add_jeecgOrderProduct_tableSR").append(tr);

        /*$("#add_jeecgOrderProduct_tableSR").find("[id='signPrice00']").attr("datatype","d");
         $("#add_jeecgOrderProduct_tableSR").find("[id='signPrice00']").attr("id","signPrice"+srflag);*/
        resetTrNum('add_jeecgOrderProduct_tableSR');
        $("#orderMxListIDSR").val($("#mxtbSR").find("tr").length-1);
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:400},1);

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
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.colorId eq null || param.colorId eq ''}">
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
        <td align="center"><input id="colorZnName00" nullmsg="请输入颜色！"  errormsg="请输入颜色" name="orderMxList[#index#].colorZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="seNum00" nullmsg="请输入色号！"  errormsg="请输入色号" name="orderMxList[#index#].seNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="version00" nullmsg="请输入版次！"  errormsg="请输入版次"  type="text"  name="orderMxList[#index#].version" maxlength="100" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="colorBrand00" nullmsg="请输入色样规格！"  errormsg="请输入色样规格" name="orderMxList[#index#].colorBrand" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="colorTotal00" nullmsg="请输入色样数量！"  errormsg="请输入色样数量" name="orderMxList[#index#].colorTotal" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnSR" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListIDSR" type="hidden" name="orderMxListIDSR" value="${fn:length(detailEntities)}"/>
<div class="table-c">
    <table id="mxtbSR" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" >序号</td>
            <td align="center"  width="90">颜色</td>
            <td align="center"  width="90">色号</td>
            <td align="center"  width="90">版次</td>
            <td align="center"  width="90">色样规格</td>
            <td align="center"  width="90">色样数量</td>
        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(detailEntities)  > 0 }">
            <c:forEach items="${detailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center"><input  nullmsg="请输入颜色！"  errormsg="请输入颜色" value="${poVal.colorZnName}" name="orderMxList[${status.index}].colorZnName" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入色号！"  errormsg="请输入色号" value="${poVal.seNum}" name="orderMxList[${status.index}].seNum" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入版次！"  errormsg="请输入版次" value="${poVal.version}" name="orderMxList[${status.index}].version" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入色样规格！"  errormsg="请输入色样规格" value="${poVal.colorBrand}" name="orderMxList[${status.index}].colorBrand" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入色样数量！"  errormsg="请输入色样数量" value="${poVal.colorTotal}" name="orderMxList[${status.index}].colorTotal" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>

                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

