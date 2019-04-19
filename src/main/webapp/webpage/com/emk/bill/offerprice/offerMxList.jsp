<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = 0;
    var currentFlag = 0;

    function productLook(indexflag){
        currentFlag = indexflag;
        $("#chkInfo").click();
    }
    function returnToValForProductInfo(){
        $("#sampleNo"+currentFlag).val($("#hsCode").val());
        $("#sampleName"+currentFlag).val($("#hsName").val());
        $("#proNum"+currentFlag).val($("#proNum").val());
        $("#proName"+currentFlag).val($("#proZnName").val());
        $("#brand"+currentFlag).val($("#brand").val());
        $("#signUnit"+currentFlag).val($("#unit").val());
        $("#price"+currentFlag).val($("#price").val());
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

        /*$("#add_jeecgOrderProduct_table").find("[id='sampleNo0']").attr("name","offerMxList[#index#].sampleNo");
        $("#add_jeecgOrderProduct_table").find("[id='sampleName0']").attr("name","offerMxList[#index#].sampleName");
        $("#add_jeecgOrderProduct_table").find("[id='proName0']").attr("name","offerMxList[#index#].proName");
        $("#add_jeecgOrderProduct_table").find("[id='proId0']").attr("name","offerMxList[#index#].proId");
        $("#add_jeecgOrderProduct_table").find("[id='proNum0']").attr("name","offerMxList[#index#].proNum");
        $("#add_jeecgOrderProduct_table").find("[id='brand0']").attr("name","offerMxList[#index#].brand");
        $("#add_jeecgOrderProduct_table").find("[id='signTotal0']").attr("name","offerMxList[#index#].signTotal");
        $("#add_jeecgOrderProduct_table").find("[id='signUnit0']").attr("name","offerMxList[#index#].signUnit");
        $("#add_jeecgOrderProduct_table").find("[id='signPrice0']").attr("name","offerMxList[#index#].signPrice");
        $("#add_jeecgOrderProduct_table").find("[id='remark0']").attr("name","offerMxList[#index#].remark");*/



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
        $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("id","price"+flag);



        idStr = flag;
        $("#sampleNo"+idStr.toString()).on("click", function () {
            productLook(idStr);
        });
        $("#sampleName"+idStr.toString()).on("click", function () {
            productLook(idStr);
        });

        resetTrNum('add_jeecgOrderProduct_table');
        $("#offerMxListID").val($("#mxtb").find("tr").length-1);
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_table").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_table');
        $("#offerMxListID").val($("#mxtb").find("tr").length-1);


    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });

});

</script>

<div style="display:none;">
    <input id="hsCode" name="hsCode" type="text"/>
    <input id="hsName" name="hsName" type="text"/>
    <input id="proNum" name="proNum" type="text"/>
    <input id="proZnName" name="proZnName" type="text" />
    <input id="brand" name="brand" type="text" />
    <input id="price" name="price" type="text" />
    <input id="unit" name="unit" type="text" />
    <input id="id" name="id" type="text" />
    <input id="indexFlag" name="indexFlag" type="text" value="0"/>

    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductInfoController.do?proSelect" name="emkProductInfoList" width="750px" height="500px"
               icon="icon-search" title="选择商品" textname="id,proNum,proZnName,brand,unit,hsCode,hsName,price" isclear="true" isInit="true"></t:choose></div>
<c:if test="${orderFinish eq ''}">
    </c:if>
<div style="padding: 3px; height: 25px; width: width:800px; " class="datagrid-toolbar"><a id="addBtn" href="#">添加</a> <a id="delBtn" href="#">删除</a></div>

<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="offerMxListID" type="hidden" name="dataRowsVal" value="${fn:length(offerMxList)}"/>
<table id="mxtb" style="width:100%;" cellpadding="0" cellspacing="2" border="0">
    <tr bgcolor="#E6E6E6" style="height: 32px;">
        <td align="center" bgcolor="#EEEEEE" width="40">序号</td>
        <td align="left" bgcolor="#EEEEEE" width="100">款号</td>
        <td align="left" bgcolor="#EEEEEE" width="120">产品名称</td>
        <td align="left" bgcolor="#EEEEEE" width="60">颜色</td>
        <td align="left" bgcolor="#EEEEEE" width="120">尺码</td>
        <td align="left" bgcolor="#EEEEEE" width="120">数量</td>
        <td align="left" bgcolor="#EEEEEE" width="60">单位</td>
        <td align="left" bgcolor="#EEEEEE" width="100">单价</td>
        <td align="left" bgcolor="#EEEEEE" width="100">备注</td>

    </tr>

    <tbody id="add_jeecgOrderProduct_table">
    <c:if test="${fn:length(emkOfferPriceDetailEntities)  > 0 }">
        <c:forEach items="${emkOfferPriceDetailEntities}" var="poVal" varStatus="status">
            <tr>
                <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/><input type="hidden" name="offerMxList[${status.index}].id" value="${poVal.id}"/>
                    <input type="hidden" id="proNum0" name="offerMxList[#index#].proNum" value="${poVal.proNum}"/>
                </td>
                <td align="left"><input nullmsg="请输入款号！" onclick="productLook(${status.index})" id="sampleNo0" value="${poVal.sampleNo}" datatype="*" errormsg="请输入款号" name="offerMxList[${status.index}].sampleNo" maxlength="100" type="text" value="0"
                                        style="width: 100px;"></td>
                <td align="left">
                    <input nullmsg="请输入产品名称！" id="sampleName0" onclick="productLook(${status.index})"  datatype="*" value="${poVal.proName}" errormsg="请输入产品名称" name="offerMxList[${status.index}].proName" maxlength="100" type="text" value="0"
                           style="width: 120px;">
                </td>
                <td align="left">
                    <input nullmsg="请输入颜色！" id="proName0" onclick="productLook(${status.index})"  datatype="*" value="${poVal.color}" errormsg="请输入颜色" name="offerMxList[${status.index}].color" maxlength="100" type="text" value="0"
                           style="width: 120px;">
                </td>
                <td align="left"><input id="brand0" nullmsg="请输入尺码！" datatype="*" value="${poVal.brand}" errormsg="请输入尺码" name="offerMxList[${status.index}].brand" maxlength="100" type="text" value="0"
                                        style="width: 120px;"></td>
                <td align="left"><input id="signTotal0" nullmsg="请输入数量！" datatype="*" value="${poVal.signTotal}" errormsg="请输入整数" name="offerMxList[${status.index}].signTotal" maxlength="100" type="text" value="0"
                                        style="width: 120px;"></td>
                <td align="left">
                    <input nullmsg="请输入单位！" id="signUnit0"  errormsg="请输入单位" value="${poVal.unit}" name="offerMxList[${status.index}].signUnit" maxlength="100" type="text" value="-1"
                           style="width: 60px;"></td>
                <td align="left"><input nullmsg="请输入单价！" id="signPrice0" datatype="*" value="${poVal.signPrice}" errormsg="请输入单价" name="offerMxList[${status.index}].signPrice" maxlength="100" type="text" value="0"
                                        style="width: 100px;"></td>
                <td align="left"><input nullmsg="请输入备注！" id="remark0"  errormsg="请输入备注" value="${poVal.remark}" name="offerMxList[${status.index}].remark" maxlength="100" type="text" value="0"
                                        style="width: 100px;"></td>
            </tr>

        </c:forEach>
    </c:if>
    </tbody>
</table>
