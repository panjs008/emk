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
        <td align="center"><input id="colorZnName00" nullmsg="请输入颜色中文名称！"  errormsg="请输入颜色中文名称" name="orderMxList[#index#].colorZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="colorEnName00" nullmsg="请输入颜色英文名称！"  errormsg="请输入颜色英文名称" name="orderMxList[#index#].colorEnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="seNum00" nullmsg="请输入色号！"  errormsg="请输入色号" name="orderMxList[#index#].seNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="syNum00" nullmsg="请输入色样编号！"  errormsg="请输入色样编号" name="orderMxList[#index#].syNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="zgy00" nullmsg="请输入主光源！"  errormsg="请输入主光源" name="orderMxList[#index#].zgy" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cgy00" nullmsg="请输入次光源！"  errormsg="请输入次光源" name="orderMxList[#index#].cgy" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="recevieDate00" nullmsg="请输入交期！"  errormsg="请输入交期" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly type="text" class="Wdate"  name="orderMxList[#index#].recevieDate" maxlength="100" value=""
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
            <td align="center"  width="90">颜色中文名称</td>
            <td align="center"  width="90">颜色英文名称</td>
            <td align="center"  width="90">色号</td>
            <td align="center"  width="90">色样编号</td>
            <td align="center"  width="90">主光源</td>
            <td align="center"  width="90">次光源</td>
            <td align="center"  width="90">交期</td>
            <td align="center"  width="90">色样规格</td>
            <td align="center"  width="90">色样数量</td>
        </tr>

        <tbody id="add_jeecgOrderProduct_tableSR">
        <c:if test="${fn:length(detailEntities)  > 0 }">
            <c:forEach items="${detailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center"><input  nullmsg="请输入颜色中文名称！"  errormsg="请输入颜色中文名称" value="${poVal.colorZnName}" name="orderMxList[${status.index}].colorZnName" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入颜色英文名称！"  errormsg="请输入颜色英文名称" value="${poVal.colorEnName}" name="orderMxList[${status.index}].colorEnName" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入色号！"  errormsg="请输入色号" value="${poVal.seNum}" name="orderMxList[${status.index}].seNum" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入色样编号！"  errormsg="请输入色样编号" value="${poVal.syNum}" name="orderMxList[${status.index}].syNum" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入主光源！"  errormsg="请输入主光源" value="${poVal.zgy}" name="orderMxList[${status.index}].zgy" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入次光源！"  errormsg="请输入次光源" value="${poVal.cgy}" name="orderMxList[${status.index}].cgy" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入交期！"  errormsg="请输入交期" value="${poVal.recevieDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly type="text" class="Wdate"  name="orderMxList[${status.index}].recevieDate" maxlength="100" type="text" value=""
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

