<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagContent = 0;
    var currentFlag = 0;


    $('#addBtnContent').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnContent').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtnContent').bind('click', function(){
        flagContent++;

        if(flagContent>1 || ${param.sampleId ne null}){
            $("html,body").animate({scrollTop:200},1);
        }
        $("#seqNum").html(flagContent+parseInt($("#listSize").val())+"&nbsp;&nbsp;&nbsp;");

        var tr =  $("#add_jeecgOrderProduct_tableContent_templateContent tr").clone();
        $("#add_jeecgOrderProduct_tableContent").append(tr);


        /*$("#add_jeecgOrderProduct_tableContent").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_tableContent").find("[id='signPrice00']").attr("id","signPrice"+flag);*/
        resetTrNum('add_jeecgOrderProduct_tableContent');
        $("#orderMxListIDContent").val($("#mxtbContent").find("tr").length-1);
    });
    $('#delBtnContent').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:200},1);
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_tableContent").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableContent');
        $("#orderMxListIDContent").val($("#mxtbContent").find("tr").length-1);

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
            $('#addBtnContent').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>

<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnContent" href="#"></a> <a id="delBtnContent" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="listSize" type="hidden" value="${fn:length(sampleContentEntities)}"/>
<input id="orderMxListIDContent" type="hidden" name="orderMxListIDContent" value="${fn:length(sampleContentEntities)}"/>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableContent_templateContent">
    <tr>
        <td align="center" width="2%"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="right" width="15%"><span id="seqNum"></span></td>
        <td align="center" width="83%"><input id="content00" nullmsg="请输入数量！"  errormsg="请输入整数" name="orderMxList[#index#].content00" maxlength="100" type="text" value=""
                                  style="width: 98%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div class="table-c">
    <table id="mxtbContent" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody id="add_jeecgOrderProduct_tableContent">
        <c:if test="${fn:length(sampleContentEntities)  > 0 }">
            <c:forEach items="${sampleContentEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center" width="2%"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="right" width="15%">${status.index+1}&nbsp;&nbsp;&nbsp;
                    </td>
                    <td align="center" width="83%"><input  nullmsg="请输入数量！"  errormsg="请输入整数" value="${poVal.content}" name="orderMxList[${status.index}].content00" maxlength="100" type="text" value=""
                                              style="width: 98%;" ignore="ignore"></td>

                </tr>
            </c:forEach>
        </c:if>
        </tbody>
        <tr>
            <td align="center">
            </td>
            <td align="right">所用机台尺寸：
            </td>
            <td align="center"><input   value="${emkSamplePage.syjtcc}" name="syjtcc" maxlength="100" type="text" value=""
                                       style="width: 98%;" ignore="ignore"></td>
        </tr>
        <tr>
            <td align="center">
            </td>
            <td align="right">下机重量：
            </td>
            <td align="center"><input   value="${emkSamplePage.xjzl}" name="xjzl" maxlength="100" type="text" value=""
                                       style="width: 98%;" ignore="ignore"></td>
        </tr>
        <tr>
            <td align="center">
            </td>
            <td align="right">下机尺寸：
            </td>
            <td align="center"><input   value="${emkSamplePage.xjcc}" name="xjcc" maxlength="100" type="text" value=""
                                       style="width: 98%;" ignore="ignore"></td>
        </tr>
        <tr>
            <td align="center">
            </td>
            <td align="right">密度：
            </td>
            <td align="center"><input   value="${emkSamplePage.md}" name="md" maxlength="100" type="text" value=""
                                       style="width: 98%;" ignore="ignore"></td>
        </tr>
        <tr>
            <td align="center">
            </td>
            <td align="right">用料：
            </td>
            <td align="center"><input   value="${emkSamplePage.yl}" name="yl" maxlength="100" type="text" value=""
                                       style="width: 98%;" ignore="ignore"></td>
        </tr>
        <tr>
            <td align="center">
            </td>
            <td align="right">单件织造时间：
            </td>
            <td align="center"><input   value="${emkSamplePage.djzjsj}" name="djzjsj" maxlength="100" type="text" value=""
                                       style="width: 98%;" ignore="ignore"></td>
        </tr>
    </table>
</div>

