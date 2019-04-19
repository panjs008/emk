<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<script type="text/javascript">
    var flag = ${fn:length(rkglMxList)};
    var currentFlag = ${fn:length(rkglMxList)};
    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn').bind('click', function(){

        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);

        $("#add_jeecgOrderProduct_table").find("[id='proZnName00']").attr("id","proZnName"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='proNum00']").attr("id","proNum"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='brand00']").attr("id","brand"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='unit00']").attr("id","unit"+flag);

        /*$("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("id","signPrice"+flag);*/
        resetTrNum('add_jeecgOrderProduct_table');
        $("#proZnName"+flag).attr("onclick","productLook("+flag+")");
        $("#orderMxListID").val($("#mxtb").find("tr").length-1);

        flag++;
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
        <c:if test="${param.inStorageId eq null || param.inStorageId eq ''}">
            $('#addBtn').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd;}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_table_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="proZnName00" nullmsg="请输入原料面料名称！"  errormsg="请输入原料面料名称"  name="orderMxList[#index#].proZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="proNum00" nullmsg="请输入原料面料代码！"  errormsg="请输入原料面料代码" name="orderMxList[#index#].proNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gysCode" nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码" name="orderMxList[#index#].gysCode" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="htNum" nullmsg="请输入采购合同号！"  errormsg="请输入采购合同号" name="orderMxList[#index#].htNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="brand00" nullmsg="请输入规格！"  errormsg="请输入规格" name="orderMxList[#index#].brand" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="total" nullmsg="请输入采购数量！" datatype="n"  errormsg="请输入整数" name="orderMxList[#index#].total" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="inTotal" nullmsg="请输入申请入库数量！" datatype="n" errormsg="请输入整数" name="orderMxList[#index#].inTotal" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="kdDate" nullmsg="请输入入库日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${kdDate}"  class="Wdate" errormsg="请输入入库日期" name="orderMxList[#index#].kdDate" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="actualTotal" nullmsg="请输入实际入库数量！" datatype="n"    errormsg="请输入实际入库数量" name="orderMxList[#index#].actualTotal" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="outDate" nullmsg="请输入出库日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" errormsg="请输入出库日期" name="orderMxList[#index#].outDate" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>

    </tr>
    </tbody>

</table>
<div style="display:none">
    <input id="proNum" name="proNum" type="text"/>
    <input id="proType" name="proType" type="text" />
    <input id="proTypeName" name="proTypeName" type="text" />
    <input id="proZnName" name="proZnName" type="text" />
    <input id="brand" name="brand" type="text" />
    <input id="unit" name="unit" type="text" />

    <input id="id" name="id" type="text" />
    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect&selectType=0" name="emkProductList" width="820px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proType,proTypeName,proZnName,brand,unit" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn" href="#"></a> <a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListID" type="hidden" name="orderMxListID" value="${fn:length(rkglMxList)}"/>
<div class="table-c" >
    <table id="mxtb" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40">序号</td>
            <td align="center"  width="150">原料面料名称</td>
            <td align="center"  width="150">原料面料代码</td>
            <td align="center"  width="150">供应商代码</td>
            <td align="center"  width="150">采购合同号</td>
            <td align="center"  width="100">规格</td>
            <td align="center"  width="100">采购数量</td>
            <td align="center"  width="100">申请入库数量</td>
            <td align="center"  width="100">入库日期</td>
            <td align="center"  width="100">实际入库数量</td>
            <td align="center"  width="100">出库日期</td>

        </tr>
        <tbody id="add_jeecgOrderProduct_table">
        <c:if test="${fn:length(rkglMxList)  > 0 }">
            <c:forEach items="${rkglMxList}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    <td align="center"><input  nullmsg="请输入原料面料名称！" id="proZnName${status.index}" onclick="productLook(${status.index});"  errormsg="请输入原料面料名称" value="${poVal.proZnName}" name="orderMxList[${status.index}].proZnName" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入原料面料代码！" id="proNum${status.index}"   errormsg="请输入原料面料代码" value="${poVal.proNum}" name="orderMxList[${status.index}].proNum" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码" value="${poVal.gysCode}" name="orderMxList[${status.index}].gysCode" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入采购合同号！"  errormsg="请输入采购合同号" value="${poVal.htNum}" name="orderMxList[${status.index}].htNum" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入规格！"  id="brand${status.index}" errormsg="请输入规格" value="${poVal.brand}" name="orderMxList[${status.index}].brand" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入采购数量！" datatype="n"  errormsg="请输入整数" value="${poVal.total}" name="orderMxList[${status.index}].total" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入申请入库数量！" datatype="n" errormsg="请输入整数" value="${poVal.inTotal}" name="orderMxList[${status.index}].inTotal" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入入库日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value="${poVal.kdDate}" class="Wdate" errormsg="请输入入库日期" name="orderMxList[${status.index}].kdDate" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入实际入库数量！" datatype="n"    errormsg="请输入实际入库数量" value="${poVal.actualTotal}" name="orderMxList[${status.index}].actualTotal" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input i nullmsg="请输入出库日期！" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${poVal.outDate}" class="Wdate" errormsg="请输入出库日期" name="orderMxList[${status.index}].outDate" maxlength="100" type="text" value=""
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    function productLook(indexFlag){
        currentFlag = indexFlag;
        $("#chkInfoForPro").click();
    }

    function returnToVal(){
        $("#proName"+currentFlag).val($("#proTypeName").val());
        $("#proNum"+currentFlag).val($("#proNum").val());
        $("#proZnName"+currentFlag).val($("#proZnName").val());
        $("#unit"+currentFlag).val($("#unit").val());
        $("#brand"+currentFlag).val($("#brand").val());
        $("#proId"+currentFlag).val($("#id").val());

    }
    </script>

