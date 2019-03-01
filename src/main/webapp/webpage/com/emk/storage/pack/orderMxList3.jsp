<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag3 = 0;
    var currentFlag3 = 0;


    $('#addBtn3').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn3').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn3').bind('click', function(){
        flag3++;
        /* if(flag3>1 || ${param.proOrderId ne null}){
         $("html,body").animate({scrollTop:400},1);
         }*/

        var tr =  $("#add_jeecgOrderProduct_table3_template3 tr").clone();
        $("#add_jeecgOrderProduct_table3").append(tr);

        $("#add_jeecgOrderProduct_table3").find("[id='cproZnName00']").attr("id","cproZnName"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cproNum00']").attr("id","cproNum"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cprecent00']").attr("id","cprecent"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cyongliang00']").attr("id","cyongliang"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='csunhaoPrecent00']").attr("id","csunhaoPrecent"+flag3);

        /*$("#add_jeecgOrderProduct_table3").find("[id='signPrice00']").attr("datatype","d");
         $("#add_jeecgOrderProduct_table3").find("[id='signPrice00']").attr("id","signPrice"+flag3);*/
        resetTrNum('add_jeecgOrderProduct_table3');
        $("#cproZnName"+flag3).attr("onclick","productLook3("+flag3+")");
        $("#orderMxListID3").val($("#mxtb3").find("tr").length-1);
    });
    $('#delBtn3').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_table3").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_table3');
        $("#orderMxListID3").val($("#mxtb3").find("tr").length-1);

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
        <c:if test="${param.priceId eq null || param.priceId eq ''}">
        $('#addBtn3').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_table3_template3">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="cproZnName00" nullmsg="请输入包装辅料名称！"  errormsg="请输入包装辅料名称"  name="orderMxList[#index#].cproZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cproNum00" nullmsg="请输入包装辅料代码！"  errormsg="请输入包装辅料代码" name="orderMxList[#index#].cproNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cgysCode00" nullmsg="请输入供应商！"  errormsg="请输入供应商" name="orderMxList[#index#].cgysCode" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cchengf00" nullmsg="请输入成分！"  errormsg="请输入成分" name="orderMxList[#index#].cchengf" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cweight00" nullmsg="请输入克重！"  errormsg="请输入克重" name="orderMxList[#index#].cweight" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="ccbrand00" nullmsg="请输入规格！"  errormsg="请输入规格" name="orderMxList[#index#].cbrand" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="csignPrice00" nullmsg="请输入目标价位！" datatype="d" errormsg="请输入目标价位" name="orderMxList[#index#].csignPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="csignTotal00" nullmsg="请输入预计数量！" datatype="d" errormsg="请输入预计数量" name="orderMxList[#index#].csignTotal" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div style="display:none">
    <input id="cproNum" name="proNum" type="text"/>
    <input id="cproType" name="proType" type="text" />
    <input id="cproTypeName" name="proTypeName" type="text" />
    <input id="cproZnName" name="proZnName" type="text" />
    <input id="cbrand" name="brand" type="text" />
    <input id="cyongliang" name="yongliang" type="text" />
    <input id="csunhaoPrecent" name="sunhaoPrecent" type="text" />
    <input id="cchengben" name="chengben" type="text" />

    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect3&selectType=2" name="emkProductList" width="820px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proType,proTypeName,proZnName,precent,brand,unit,yongliang,sunhaoPrecent,chengben" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn3" href="#"></a> <a id="delBtn3" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<form id="bzfrm">
    <input id="orderMxListID3" type="hidden" name="orderMxListID3" value="${fn:length(emkSampleDetailEntities2)}"/>

    <div class="table-c">
        <table id="mxtb3" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40">序号</td>
                <td align="center"  width="150">包装辅料名称</td>
                <td align="center"  width="150">包装辅料代码</td>
                <td align="center"  width="150">供应商代码</td>
                <td align="center"  width="150">成分</td>
                <td align="center"  width="150">克重</td>
                <td align="center"  width="150">规格</td>
                <td align="center"  width="150">目标价位</td>
                <td align="center"  width="150">预计数量</td>
            </tr>
            <tbody id="add_jeecgOrderProduct_table3">
            <c:if test="${fn:length(emkSampleDetailEntities2)  > 0 }">
                <c:forEach items="${emkSampleDetailEntities2}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                        <td align="center"><input  nullmsg="请输入包装辅料名称！"  errormsg="请输入包装辅料名称" value="${poVal.proZnName}" name="orderMxList[${status.index}].cproZnName" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入包装辅料代码！"  errormsg="请输入包装辅料代码" value="${poVal.proNum}" name="orderMxList[${status.index}].cproNum" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码"  value="${poVal.gysCode}" name="orderMxList[${status.index}].cgysCode" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input nullmsg="请输入成分！"  errormsg="请输入成分" value="${poVal.chengf}" name="orderMxList[${status.index}].cchengf" maxlength="100" type="text" value=""
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入克重！"  errormsg="请输入克重" value="${poVal.weight}" name="orderMxList[${status.index}].cweight" maxlength="100" type="text" value=""
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入规格！"  errormsg="请输入规格" value="${poVal.brand}" name="orderMxList[${status.index}].cbrand" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>

                        <td align="center"><input  nullmsg="请输入目标价位！"  datatype="d" errormsg="请输入目标价位" value="${poVal.signPrice}" name="orderMxList[${status.index}].csignPrice" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入预计数量！" datatype="d" errormsg="请输入预计数量" value="${poVal.signTotal}"  name="orderMxList[${status.index}].csignTotal" maxlength="100" type="text" value=""
                                                  style="width: 86%;" ignore="ignore"></td>




                    </tr>

                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</form>
<script type="text/javascript">
    function productLook3(indexFlag){
        currentFlag3 = indexFlag;
        $("#chkInfoForPro3").click();

    }

    function returnToVal3(){
        $("#cproName"+currentFlag3).val($("#cproTypeName").val());
        $("#cproNum"+currentFlag3).val($("#cproNum").val());
        $("#cproZnName"+currentFlag3).val($("#cproZnName").val());
        $("#cbrand"+currentFlag3).val($("#cbrand").val());
        $("#cyongliang"+currentFlag3).val($("#cyongliang").val());
        $("#csunhaoPrecent"+currentFlag3).val($("#csunhaoPrecent").val());
        $("#cchengben"+currentFlag3).val($("#cchengben").val());

    }
</script>