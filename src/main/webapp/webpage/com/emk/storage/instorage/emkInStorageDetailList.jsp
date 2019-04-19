<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = 0;
    var currentFlag = 0;

    function productLook(indexflag){
        currentFlag = indexflag;
        $("#chkInfo").click();
    }
    function returnToVal(){
        $("#sampleNo"+currentFlag).val($("#hsCode").val());
        $("#sampleName"+currentFlag).val($("#hsName").val());
        $("#proNum"+currentFlag).val($("#proNum").val());
        $("#proName"+currentFlag).val($("#proZnName").val());
        $("#brand"+currentFlag).val($("#brand").val());
        $("#signUnit"+currentFlag).val($("#unit").val());
        $("#proId"+currentFlag).val($("#proId").val());
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

        /*$("#add_jeecgOrderProduct_table").find("[id='sampleNo0']").attr("name","rkglMxList[#index#].sampleNo");
        $("#add_jeecgOrderProduct_table").find("[id='sampleName0']").attr("name","rkglMxList[#index#].sampleName");
        $("#add_jeecgOrderProduct_table").find("[id='proName0']").attr("name","rkglMxList[#index#].proName");
        $("#add_jeecgOrderProduct_table").find("[id='proId0']").attr("name","rkglMxList[#index#].proId");
        $("#add_jeecgOrderProduct_table").find("[id='proNum0']").attr("name","rkglMxList[#index#].proNum");
        $("#add_jeecgOrderProduct_table").find("[id='brand0']").attr("name","rkglMxList[#index#].brand");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal0']").attr("name","rkglMxList[#index#].signTotal");
        $("#add_jeecgOrderProduct_table").find("[id='signUnit0']").attr("name","rkglMxList[#index#].signUnit");
        $("#add_jeecgOrderProduct_table").find("[id='signPrice0']").attr("name","rkglMxList[#index#].signPrice");
        $("#add_jeecgOrderProduct_table").find("[id='remark0']").attr("name","rkglMxList[#index#].remark");*/



        $("#add_jeecgOrderProduct_table").find("[id='sampleNo00']").attr("datatype","*");
        $("#add_jeecgOrderProduct_table").find("[id='sampleNo00']").attr("id","sampleNo"+flag);

        $("#add_jeecgOrderProduct_table").find("[id='sampleNo00']").attr("datatype","*");
        $("#add_jeecgOrderProduct_table").find("[id='sampleName00']").attr("id","sampleName"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='proName00']").attr("datatype","*");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal00']").attr("datatype","*");
        $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("datatype","*");

        $("#add_jeecgOrderProduct_table").find("[id='proName00']").attr("id","proName"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='brand00']").attr("id","brand"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='signUnit00']").attr("id","signUnit"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='proId00']").attr("id","proId"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='proNum00']").attr("id","proNum"+flag);



        idStr = flag;
        $("#sampleNo"+idStr.toString()).on("click", function () {
            productLook(idStr);
        });
        $("#sampleName"+idStr.toString()).on("click", function () {
            productLook(idStr);
        });
        $("#proName"+idStr.toString()).on("click", function () {
            productLook(idStr);
        });
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

<div style="display:none;">
    <input id="hsCode" name="hsCode" type="text"/>
    <input id="hsName" name="hsName" type="text"/>
    <input id="proNum" name="proNum" type="text"/>
    <input id="proZnName" name="proZnName" type="text" />
    <input id="brand" name="brand" type="text" />
    <input id="unit" name="unit" type="text" />
    <input id="id" name="id" type="text" />
    <input id="indexFlag" name="indexFlag" type="text" value="0"/>

    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="750px" height="500px"
               icon="icon-search" title="选择商品" textname="id,proNum,proZnName,brand,unit,hsCode,hsName" isclear="true" isInit="true"></t:choose></div>
<c:if test="${orderFinish eq ''}">
    <div style="padding: 3px; height: 25px; width: width:800px; " class="datagrid-toolbar"><a id="addBtn" href="#">添加</a> <a id="delBtn" href="#">删除</a></div>
    </c:if>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="rkglMxListID" type="hidden" name="dataRowsVal" value="${fn:length(rkglMxList)}"/>
<table id="mxtb" style="width:100%;" cellpadding="0" cellspacing="2" border="0">
    <tr bgcolor="#E6E6E6" style="height: 32px;">
        <td align="center" bgcolor="#EEEEEE" width="40">序号</td>
        <td align="left" bgcolor="#EEEEEE" width="100">款号</td>
        <td align="left" bgcolor="#EEEEEE" width="120">款号名称</td>
        <td align="left" bgcolor="#EEEEEE" width="120">物料名称</td>
        <td align="left" bgcolor="#EEEEEE" width="120">规格</td>
        <td align="left" bgcolor="#EEEEEE" width="120">数量</td>
        <td align="left" bgcolor="#EEEEEE" width="60">单位</td>
        <td align="left" bgcolor="#EEEEEE" width="100">单价</td>
        <td align="left" bgcolor="#EEEEEE" width="100">备注</td>

    </tr>

    <tbody id="add_jeecgOrderProduct_table">
    <c:if test="${fn:length(rkglMxList)  > 0 }">
        <c:forEach items="${rkglMxList}" var="poVal" varStatus="status">
            <tr>
                <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/><input type="hidden" name="rkglMxList[${status.index}].id" value="${poVal.id}"/>
                    <input type="hidden" id="proId0" name="rkglMxList[#index#].proId" value="${poVal.proId}"/>
                    <input type="hidden" id="proNum0" name="rkglMxList[#index#].proNum" value="${poVal.proNum}"/>
                </td>
                <td align="left"><input nullmsg="请输入款号！" onclick="productLook(${status.index})" id="sampleNo0" value="${poVal.sampleNo}" datatype="*" errormsg="请输入款号" name="rkglMxList[${status.index}].sampleNo" maxlength="100" type="text" value="0"
                                        style="width: 100px;"></td>
                <td align="left">
                    <input nullmsg="请输入款号名称！" id="sampleName0" onclick="productLook(${status.index})"  datatype="*" value="${poVal.sampleName}" errormsg="请输入款号名称" name="rkglMxList[${status.index}].sampleName" maxlength="100" type="text" value="0"
                           style="width: 120px;">
                </td>
                <td align="left">
                    <input nullmsg="请输入物料名称！" id="proName0" onclick="productLook(${status.index})"  datatype="*" value="${poVal.proName}" errormsg="请输入物料名称" name="rkglMxList[${status.index}].proName" maxlength="100" type="text" value="0"
                           style="width: 120px;">
                </td>
                <td align="left"><input id="brand0" nullmsg="请输入规格！" datatype="*" value="${poVal.brand}" errormsg="请输入规格" name="rkglMxList[${status.index}].brand" maxlength="100" type="text" value="0"
                                        style="width: 120px;"></td>
                <td align="left"><input id="signTotal0" nullmsg="请输入数量！" datatype="*" value="${poVal.signTotal}" errormsg="请输入整数" name="rkglMxList[${status.index}].signTotal" maxlength="100" type="text" value="0"
                                        style="width: 120px;"></td>
                <td align="left">
                    <input nullmsg="请输入单位！" id="signUnit0"  errormsg="请输入单位" value="${poVal.signUnit}" name="rkglMxList[${status.index}].signUnit" maxlength="100" type="text" value="-1"
                           style="width: 60px;"></td>
                <td align="left"><input nullmsg="请输入单价！" id="signPrice0" datatype="*" value="${poVal.signPrice}" errormsg="请输入单价" name="rkglMxList[${status.index}].signPrice" maxlength="100" type="text" value="0"
                                        style="width: 100px;"></td>
                <td align="left"><input nullmsg="请输入备注！" id="remark0"  errormsg="请输入备注" value="${poVal.remark}" name="rkglMxList[${status.index}].remark" maxlength="100" type="text" value="0"
                                        style="width: 100px;"></td>
            </tr>

        </c:forEach>
    </c:if>
    </tbody>
</table>
