<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagContent2 = 0;
    var currentFlag = 0;


    $('#addBtnContent2').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnContent2').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnContent2').bind('click', function(){
        flagContent2++;

        if(flagContent2>1 || ${param.sampleId ne null}){
            $("html,body").animate({scrollTop:500},1);
        }
        $("#seqNum2").html(flagContent2+parseInt($("#listSize").val())+"&nbsp;&nbsp;&nbsp;");
        var tr =  $("#add_jeecgOrderProduct_tableContent2_templateContent2 tr").clone();
        $("#add_jeecgOrderProduct_tableContent2").append(tr);


        /*$("#add_jeecgOrderProduct_tableContent2").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_tableContent2").find("[id='signPrice00']").attr("id","signPrice"+flag);*/
        resetTrNum('add_jeecgOrderProduct_tableContent2');
        $("#orderMxListIDContent2").val($("#mxtbContent2").find("tr").length-1);
    });
    $('#delBtnContent2').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:500},1);
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tableContent2").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableContent2');
        $("#orderMxListIDContent2").val($("#mxtbContent2").find("tr").length-1);

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
        <c:if test="${param.sampleId eq null || param.sampleId eq '' || fn:length(sampleContentEntities) eq 0}">
            $('#addBtnContent2').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>

<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnContent2" href="#"></a> <a id="delBtnContent2" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="listSize" type="hidden"  value="${fn:length(sampleContentEntities)}"/>
<input id="orderMxListIDContent2" type="hidden" name="orderMxListIDContent2" value="${fn:length(sampleContentEntities)}"/>


<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableContent2_templateContent2">
    <tr>
        <td align="center" width="2%"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="right" width="15%"><span id="seqNum2"></span></td>
        <td align="center" width="83%" colspan="5"><input id="bcontent00" nullmsg="请输入数量！"  errormsg="请输入整数" name="orderMxList[#index#].bcontent00" maxlength="100" type="text" value=""
                                  style="width: 98%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div class="table-c">
    <table id="mxtbContent2" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">
            </td>
            <td align="right">是否有标准色样&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isColorStrand" type="radio" datatype="*" <c:if test="${emkSamplePage.isColorStrand eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isColorStrand" type="radio" datatype="*"  <c:if test="${emkSamplePage.isColorStrand eq '1'}">checked="true"</c:if> value="1">
                否</td>
            <td align="right">是否有潘通色号&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isPan" type="radio" datatype="*" <c:if test="${emkSamplePage.isPan eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isPan" type="radio" datatype="*"  <c:if test="${emkSamplePage.isPan eq '1'}">checked="true"</c:if> value="1">
                否</td>
            <td align="right">是否有参考样&nbsp;&nbsp;
            </td>
            <td align="left">&nbsp;&nbsp;<input name="isCan" type="radio" datatype="*" <c:if test="${emkSamplePage.isCan eq '0'}">checked="true"</c:if> value="0">
                是
                &nbsp;&nbsp;<input name="isCan" type="radio" datatype="*"  <c:if test="${emkSamplePage.isCan eq '1'}">checked="true"</c:if> value="1">
                否</td>
        </tr>
        <tbody id="add_jeecgOrderProduct_tableContent2">
        <c:if test="${fn:length(sampleContentEntities)  > 0 }">
            <c:forEach items="${sampleContentEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center" width="2%"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="right" width="15%">${status.index+1}&nbsp;&nbsp;&nbsp;
                    </td>
                    <td align="center" colspan="5" width="83%"><input  nullmsg="请输入数量！"  errormsg="请输入整数" value="${poVal.content}" name="orderMxList[${status.index}].bcontent00" maxlength="100" type="text" value=""
                                              style="width: 98%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>

    </table>
</div>

