<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = 0;
    var currentFlag = 0;


    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn').bind('click', function(){
        flag++;

        if(flag>1 || ${param.proOrderId ne null}){
            $("html,body").animate({scrollTop:400},1);
        }
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);

        $("#add_jeecgOrderProduct_table").find("[id='signTotal00']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal00']").attr("id","signTotal"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='signTotal01']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal01']").attr("id","signTotal"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='signTotal02']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal02']").attr("id","signTotal"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='signTotal03']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal03']").attr("id","signTotal"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='signTotal04']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal04']").attr("id","signTotal"+flag);
        /*$("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("id","signPrice"+flag);*/
        resetTrNum('add_jeecgOrderProduct_table');
        $("#orderMxListID").val($("#mxtb").find("tr").length-1);
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:400},1);
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_table").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_table');
        $("#orderMxListID").val($("#mxtb").find("tr").length-1);

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
        <c:if test="${param.proOrderId eq null}">
            $('#addBtn').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>

<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListID" type="hidden" name="dataRowsVal" value="${fn:length(emkProOrderDetailEntities)}"/>
<div class="table-c">
    <table id="mxtb" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40" rowspan="2">序号</td>
            <td align="center"  width="150" rowspan="2">颜色</td>
            <td align="center"  width="150" rowspan="2">色号</td>
            <td align="center"  width="150" colspan="5">尺码</td>
        </tr>
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="150">S</td>
            <td align="center"  width="150">M</td>
            <td align="center"  width="150">L</td>
            <td align="center"  width="150">XL</td>
            <td align="center"  width="150">XXL</td>
        </tr>
        <tbody id="add_jeecgOrderProduct_table">
        <c:if test="${fn:length(emkProOrderDetailEntities)  > 0 }">
            <c:forEach items="${emkProOrderDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].color" style="width: 86%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                            <c:forEach items="${colorList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.color ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].colorNum" style="width: 86%;" nullmsg="请输入色号！" errormsg="请输入色号" datatype="*">
                            <c:forEach items="${colorNumList}" var="category">
                                <option value="${category.typecode}" ${category.typecode eq poVal.colorVal ? 'selected':''}>${category.typename}</option>
                            </c:forEach>
                        </select></td>
                    <td align="center"><input  nullmsg="请输入数量！"  errormsg="请输入数量" value="${poVal.stotal}"name="orderMxList[${status.index}].signTotal" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  errormsg="请输入数量" value="${poVal.mtotal}" name="orderMxList[${status.index}].signTotal01" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  errormsg="请输入数量" value="${poVal.ltotal}" name="orderMxList[${status.index}].signTotal02" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入数量！"  errormsg="请输入数量" value="${poVal.xltotal}" name="orderMxList[${status.index}].signTotal03" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入数量！"  errormsg="请输入数量" value="${poVal.xxltotal}" name="orderMxList[${status.index}].signTotal04" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>


                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

