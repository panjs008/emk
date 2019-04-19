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
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);
        resetTrNum('add_jeecgOrderProduct_table');
        $("#orderMxListID").val($("#mxtb").find("tr").length-1);
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
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


<div style="padding: 3px; height: 25px; width: width:800px; " class="datagrid-toolbar"><a id="addBtn" href="#">添加</a> <a id="delBtn" href="#">删除</a></div>

<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListID" type="hidden" name="dataRowsVal" value="${fn:length(emkProOrderDetailEntities)}"/>
<table id="mxtb" style="width:70%;" cellpadding="0" cellspacing="2" border="0">
    <tr bgcolor="#E6E6E6" style="height: 32px;">
        <td align="center" bgcolor="#EEEEEE" width="40">序号</td>
        <td align="left" bgcolor="#EEEEEE" width="150">样品颜色</td>
        <td align="left" bgcolor="#EEEEEE" width="150">样品尺码</td>
        <td align="left" bgcolor="#EEEEEE" width="150">样品数量</td>
    </tr>

    <tbody id="add_jeecgOrderProduct_table">
    <c:if test="${fn:length(emkProOrderDetailEntities)  > 0 }">
        <c:forEach items="${emkProOrderDetailEntities}" var="poVal" varStatus="status">
            <tr>
                <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                </td>
                <td align="left">
                    <select name="orderMxList[${status.index}].color" style="width: 80%;" nullmsg="请输入颜色！" errormsg="请输入颜色" datatype="*">
                        <c:forEach items="${categoryEntityList}" var="category">
                            <option value="${category.code}" ${category.code eq poVal.color ? 'selected':''}>${category.name}</option>
                        </c:forEach>
                    </select>
                    <%--<input nullmsg="请输入颜色！" id="proName0" onclick="productLook(${status.index})"  datatype="*" value="${poVal.color}" errormsg="请输入颜色" name="orderMxList[${status.index}].color" maxlength="100" type="text" value="0"--%>
                           <%--style="width: 70%;">--%>
                </td>
                <td align="left"><input id="brand0" nullmsg="请输入尺码！" datatype="*" value="${poVal.size}" errormsg="请输入尺码" name="orderMxList[${status.index}].size" maxlength="100" type="text" value="0"
                                        style="width: 80%;"></td>
                <td align="left"><input id="signTotal0" nullmsg="请输入数量！" datatype="*" value="${poVal.total}" errormsg="请输入整数" name="orderMxList[${status.index}].signTotal" maxlength="100" type="text" value="0"
                                        style="width: 80%;"></td>


            </tr>

        </c:forEach>
    </c:if>
    </tbody>
</table>
