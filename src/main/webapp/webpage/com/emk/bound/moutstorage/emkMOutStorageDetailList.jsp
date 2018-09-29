<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = 0;
    var currentFlag = 0;

    function productLook(indexflag){
        currentFlag = indexflag;
        $("#chkInfoForPro").click();
    }
    function returnToVal(){
        $("#proNum"+currentFlag).val($("#proNum").val());
        $("#proName"+currentFlag).val($("#proZnName").val());
        $("#brand"+currentFlag).val($("#brand").val());
    }
    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn').bind('click', function(){
        var idStr;
        flag++;
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);


        /* $("#add_jeecgOrderProduct_table").find("[id='proName00']").attr("datatype","*");
         $("#add_jeecgOrderProduct_table").find("[id='signTotal00']").attr("datatype","*");
         $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("datatype","*");*/

        $("#add_jeecgOrderProduct_table").find("[id='proName00']").attr("id","proName"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='brand00']").attr("id","brand"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='proNum00']").attr("id","proNum"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='htTotal00']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='totall00']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='inTotal00']").attr("datatype","n");
        $("#add_jeecgOrderProduct_table").find("[id='actualTotal00']").attr("datatype","n");


        idStr = flag;
        $("#proNum"+idStr.toString()).on("click", function () {
            productLook(idStr);
        });
        /* $("#proName"+idStr.toString()).on("click", function () {
         productLook(idStr);
         });*/
        resetTrNum('add_jeecgOrderProduct_table');
        $("#rkglMxListID").val($("#mxtb").find("tr").length-1);
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_table").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_table');
        $("#rkglMxListID").val($("#mxtb").find("tr").length-1);
        /* var selectedData = [];
         $(":checkbox:checked").each(function(){
         var tablerow = $("#add_jeecgOrderProduct_table").parent("tr");
         var code = tablerow.find("[name='p_code']").val();
         var name= tablerow.find("[name='p_name']").val();
         var price= tablerow.find("[name='p_price']").val();
         selectedData.push({Code:code,Name:name,Price:price});
         });*/
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
        <c:if test="${param.inStorageId eq null}">
        $('#addBtn').click();
        </c:if>
        <c:if test="${param.inStorageId ne null}">
        flag = ${fn:length(rkglMxList)};
        </c:if>
    });
</script>

<div style="display:none ;">
    <input id="proNum" name="proNum" type="text"/>
    <input id="proZnName" name="proZnName" type="text" />
    <input id="brand" name="brand" type="text" />
    <input id="id" name="id" type="text" />
    <input id="indexFlag" name="indexFlag" type="text" value="0"/>

    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect&selectType=${param.selectType}" name="emkProductList" width="750px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proZnName,brand" isclear="true" isInit="true"></t:choose></div>


<c:if test="${orderFinish eq ''}">
    <div style="padding: 3px; height: 25px; width: width:800px; " class="datagrid-toolbar"><a id="addBtn" href="#">添加</a> <a id="delBtn" href="#">删除</a></div>
</c:if>

<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="rkglMxListID" type="hidden" name="dataRowsVal" value="${fn:length(rkglMxList)}"/>
<table id="mxtb" style="width:100%;" cellpadding="0" cellspacing="2" border="0">
    <tr bgcolor="#E6E6E6" style="height: 32px;">
        <td align="center" bgcolor="#EEEEEE" width="40">序号</td>
        <td align="left" bgcolor="#EEEEEE" width="100">物料代码</td>
        <td align="left" bgcolor="#EEEEEE" width="120">物料名称</td>
        <td align="left" bgcolor="#EEEEEE" width="90">规格</td>
        <td align="left" bgcolor="#EEEEEE" width="90">数量</td>
        <td align="left" bgcolor="#EEEEEE" width="90">采购数量</td>
        <td align="left" bgcolor="#EEEEEE" width="90">申请出库数量</td>
        <td align="left" bgcolor="#EEEEEE" width="90">实际出库数量</td>

    </tr>

    <tbody id="add_jeecgOrderProduct_table">
    <c:if test="${fn:length(rkglMxList)  > 0 }">
    <c:forEach items="${rkglMxList}" var="poVal" varStatus="status">
        <tr>
                <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/><input type="hidden" name="rkglMxList[${status.index}].id" value="${poVal.id}"/>
                </td>
                <td align="left">
                    <input nullmsg="请输入物料编号！" id="proNum${status.index}" onclick="productLook(${status.index})"  datatype="*" value="${poVal.proNum}" errormsg="请输入物料编号" name="rkglMxList[${status.index}].proNum" maxlength="100" type="text" value="0"
                           style="width: 95%;">
                </td>
                <td align="left">
                    <input nullmsg="请输入物料名称！" id="proName${status.index}" onclick="productLook(${status.index})"  datatype="*" value="${poVal.proZnName}" errormsg="请输入物料名称" name="rkglMxList[${status.index}].proName" maxlength="100" type="text" value="0"
                           style="width: 95%;">
                </td>
                <td align="left"><input id="brand${status.index}" nullmsg="请输入规格！" datatype="*" value="${poVal.brand}" errormsg="请输入规格" name="rkglMxList[${status.index}].brand" maxlength="100" type="text" value="0"
                                        style="width: 95%;"></td>
                <td align="left"><input id="htTotal0" nullmsg="请输入数量！"value="${poVal.htTotal}" errormsg="输入数量内容不正确" datatype="n" name="rkglMxList[${status.index}].htTotal" maxlength="100" type="text" value="0"
                                        style="width: 95%;"></td>
                <td align="left"><input nullmsg="请输入采购数量！" id="total0" errormsg="输入采购数量内容不正确" value="${poVal.total}" datatype="n" name="rkglMxList[${status.index}].total" maxlength="100" type="text" value=""
                                        style="width: 95%;"></td>
                <td align="left"><input nullmsg="请输入出库数量！" id="inTotal0" errormsg="输入出库数量内容不正确" value="${poVal.inTotal}" datatype="n" name="rkglMxList[${status.index}].inTotal" maxlength="100" type="text" value=""
                                        style="width: 95%;"></td>
                <td align="left"><input nullmsg="请输入实际出库数量！" id="actualTotall0" errormsg="输入出库数量内容不正确" value="${poVal.actualTotal}" datatype="n" name="rkglMxList[${status.index}].actualTotal" maxlength="100" type="text" value=""
                                    style="width: 95%;"></td>

            </tr>
        </c:forEach>
        </c:if>
    </tbody>
</table>
