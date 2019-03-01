<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagContent3 = 0;
    var currentFlag = 0;


    $('#addBtnContent3').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnContent3').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnContent3').bind('click', function(){
        flagContent3++;

        if(flagContent3>1 || ${param.sampleId ne null}){
            $("html,body").animate({scrollTop:600},1);
        }
        $("#seqNum3").html(flagContent3+parseInt($("#listSize").val())+"&nbsp;&nbsp;&nbsp;");

        var tr =  $("#add_jeecgOrderProduct_tableContent3_templateContent3_templateContent3 tr").clone();
        $("#add_jeecgOrderProduct_tableContent3_templateContent3").append(tr);


        /*$("#add_jeecgOrderProduct_tableContent3_templateContent3").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_tableContent3_templateContent3").find("[id='signPrice00']").attr("id","signPrice"+flag);*/
        resetTrNum('add_jeecgOrderProduct_tableContent3_templateContent3');
        $("#orderMxListIDContent3").val($("#mxtbContent3").find("tr").length-1);
    });
    $('#delBtnContent3').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:600},1);
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tableContent3_templateContent3").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableContent3_templateContent3');
        $("#orderMxListIDContent3").val($("#mxtbContent3").find("tr").length-1);

        if(chk_value.length>0){
           /* $.ajax({
                url : "dxRkglMxController.do?doBatchDel&ids="+chk_value,
                type : 'post',
                cache : false,
                data: null,
                success : function(data) {
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var msg = d.msg;
                        tip(msg);
                        //W.document.location.reload(true);
                    }
                }
            });*/
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
        <c:if test="${param.sampleId eq null || param.sampleId eq ''}">
            $('#addBtnContent3').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>

<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnContent3" href="#"></a> <a id="delBtnContent3" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="listSize" type="hidden"  value="${fn:length(sampleContentEntities)}"/>
<input id="orderMxListIDContent3" type="hidden" name="orderMxListIDContent3" value="${fn:length(sampleContentEntities)}"/>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableContent3_templateContent3_templateContent3">
    <tr>
        <td align="center" width="2%"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="right" width="15%"><span id="seqNum3"></span>
        <td align="center" width="83%" colspan="7"><input id="ccontent00" nullmsg="请输入数量！"  errormsg="请输入数量" name="orderMxList[#index#].ccontent00" maxlength="100" type="text" value=""
                                  style="width: 98%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div class="table-c">
    <table id="mxtbContent3" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">
            </td>
            <td align="right">是否有设计稿&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isDrg" type="radio" datatype="*" <c:if test="${emkSamplePage.isDrg eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isDrg" type="radio" datatype="*"  <c:if test="${emkSamplePage.isDrg eq '1'}">checked="true"</c:if> value="1">
                否</td>
            <td align="right">是否有原样&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isSample" type="radio" datatype="*" <c:if test="${emkSamplePage.isSample eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isSample" type="radio" datatype="*"  <c:if test="${emkSamplePage.isSample eq '1'}">checked="true"</c:if> value="1">
                否</td>
            <td align="right">是否有物料明细&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isDetail" type="radio" datatype="*" <c:if test="${emkSamplePage.isDetail eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isDetail" type="radio" datatype="*"  <c:if test="${emkSamplePage.isDetail eq '1'}">checked="true"</c:if> value="1">
                否</td>
            <td align="right">是否有尺寸表&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isSize" type="radio" datatype="*" <c:if test="${emkSamplePage.isSize eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isSize" type="radio" datatype="*"  <c:if test="${emkSamplePage.isSize eq '1'}">checked="true"</c:if> value="1">
                否</td>
        </tr>
        <tbody id="add_jeecgOrderProduct_tableContent3_templateContent3">
        <c:if test="${fn:length(sampleContentEntities)  > 0 }">
            <c:forEach items="${sampleContentEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center" width="2%"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="right" width="15%">${status.index+1}&nbsp;&nbsp;&nbsp;
                    </td>
                    <td align="center" colspan="7" width="83%"><input  nullmsg="请输入数量！"  errormsg="请输入数量" value="${poVal.content}" name="orderMxList[${status.index}].ccontent00" maxlength="100" type="text" value=""
                                              style="width: 98%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

