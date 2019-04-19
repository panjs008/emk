<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag2 = ${fn:length(emkSampleDetailEntities1)};
    var currentFlag2 = ${fn:length(emkSampleDetailEntities1)};


    $('#addBtn2').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn2').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn2').bind('click', function(){
        /* if(flag2>1 || ${param.proOrderId ne null}){
         $("html,body").animate({scrollTop:400},1);
         }*/
        $("#bseqNum").html(flag2+1);

        var tr =  $("#add_jeecgOrderProduct_table2_template2 tr").clone();
        $("#add_jeecgOrderProduct_table2").append(tr);


        $("#add_jeecgOrderProduct_table2").find("[id='bproZnName00']").attr("id","bproZnName"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='bproNum00']").attr("id","bproNum"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='bbrand00']").attr("id","bbrand"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='bunit00']").attr("id","bunit"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='byongliang00']").attr("id","byongliang"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='bsunhaoPrecent00']").attr("id","bsunhaoPrecent"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='bsignPrice00']").attr("id","bsignPrice"+flag2);
        $("#add_jeecgOrderProduct_table2").find("[id='bchengben00']").attr("id","bchengben"+flag2);


        /*$("#add_jeecgOrderProduct_table2").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_table2").find("[id='signPrice00']").attr("id","signPrice"+flag2);*/
        resetTrNum('add_jeecgOrderProduct_table2');
        $("#bproZnName"+flag2).attr("onclick","productLook2("+flag2+")");
        $("#bsignPrice"+flag2).attr("onkeyup","setPrice2("+flag2+")");
        $("#orderMxListID2").val($("#mxtb2").find("tr").length-1);

        flag2++;
    });
    $('#delBtn2').bind('click', function(){
        var chk_value =[];
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $("#add_jeecgOrderProduct_table2").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_table2');
        $("#orderMxListID2").val($("#mxtb2").find("tr").length-1);

    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.priceId eq null || param.priceId eq ''  || fn:length(emkSampleDetailEntities1) eq 0 }">
            $('#addBtn2').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_table2_template2">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" /></td>
        <td align="center" width="40"><span id="bseqNum"></span></td>

        <td align="center"><input id="bproZnName00" nullmsg="请输入缝制辅料名称！"  errormsg="请输入缝制辅料名称"  name="orderMxList[#index#].bproZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bproNum00" nullmsg="请输入缝制辅料代码！"  errormsg="请输入缝制辅料代码" name="orderMxList[#index#].bproNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bbrand00" nullmsg="请输入缝制辅料规格！"  errormsg="请输入缝制辅料规格" name="orderMxList[#index#].bbrand" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="byongliang00" nullmsg="请输入单件用量！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].byongliang" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select name="orderMxList[#index#].bgysCode" style="width: 86%;" nullmsg="请输入供应商代码！" errormsg="请输入供应商代码" datatype="*">
                <c:forEach items="${gysList}" var="category">
                    <option value="${category.gys}">${category.gys}</option>
                </c:forEach>
            </select>
        </td>

        <td align="center"><input id="bsignPrice00" nullmsg="请输入价格！"  datatype="d" errormsg="请输入价格" name="orderMxList[#index#].bsignPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bunit00" nullmsg="请输入单位！" errormsg="请输入单位" name="orderMxList[#index#].bunit" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bsunhaoPrecent00" nullmsg="请输入损耗率！" datatype="d"  errormsg="请输入损耗率" name="orderMxList[#index#].bsunhaoPrecent" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="bchengben00" nullmsg="请输入成本(含损耗)！" readonly  datatype="d" errormsg="请输入成本(含损耗)" name="orderMxList[#index#].bchengben" maxlength="100" type="text" value=""
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
    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect2&selectType=1" name="emkProductList" width="820px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proType,proTypeName,proZnName,brand,unit" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn2" href="#"></a> <a id="delBtn2" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<form id="fzfrm">
    <input id="orderMxListID2" type="hidden" name="orderMxListID2" value="${fn:length(emkSampleDetailEntities1)}"/>
    <div class="table-c">
        <table id="mxtb2" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
                <td align="center" width="40">序号</td>
                <td align="center"  width="150">缝制辅料名称</td>
                <td align="center"  width="150">缝制辅料代码</td>
                <td align="center"  width="150">缝制辅料规格</td>
                <td align="center"  width="150">单件用量</td>
                <td align="center"  width="150">供应商代码</td>
                <td align="center"  width="150">价格</td>
                <td align="center"  width="60">单位</td>
                <td align="center"  width="150">损耗率</td>
                <td align="center"  width="150">成本(含损耗)</td>
            </tr>
            <tbody id="add_jeecgOrderProduct_table2">
                <c:if test="${fn:length(emkSampleDetailEntities1)  > 0 }">
                    <c:forEach items="${emkSampleDetailEntities1}" var="poVal" varStatus="status">
                        <tr>
                            <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/></td>
                            <td align="center" width="40">${status.index+1}</td>
                            <td align="center"><input  nullmsg="请输入缝制辅料名称！" id="bproZnName${status.index}" onclick="productLook2(${status.index});" errormsg="请输入缝制辅料名称" value="${poVal.proZnName}" name="orderMxList[${status.index}].bproZnName" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center"><input  nullmsg="请输入缝制辅料代码！" id="bproNum${status.index}" errormsg="请输入缝制辅料代码" value="${poVal.proNum}" name="orderMxList[${status.index}].bproNum" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center"><input  nullmsg="请输入缝制辅料规格！" id="bbrand${status.index}" errormsg="请输入缝制辅料规格" value="${poVal.brand}" name="orderMxList[${status.index}].bbrand" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center"><input  nullmsg="请输入单件用量！" datatype="d" id="byongliang${status.index}" errormsg="请输入单件用量" value="${poVal.yongliang}" name="orderMxList[${status.index}].byongliang" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center">
                                <select name="orderMxList[${status.index}].bgysCode" style="width: 86%;" nullmsg="请输入供应商代码！" errormsg="请输入供应商代码" datatype="*">
                                    <c:forEach items="${gysList}" var="category">
                                        <option value="${category.gys}" ${category.typecode eq poVal.gysCode ? 'selected':''}>${category.gys}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td align="center"><input  nullmsg="请输入价格！"  errormsg="请输入价格" id="bsignPrice${status.index}" onkeyup="setPrice2(${status.index})" datatype="d" value="${poVal.signPrice}"  name="orderMxList[${status.index}].bsignPrice" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center"><input  nullmsg="请输入单位！" id="bunit${status.index}"   errormsg="请输入单位" value="${poVal.unit}" name="orderMxList[${status.index}].bunit" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center"><input  nullmsg="请输入损耗率！" id="bsunhaoPrecent${status.index}" errormsg="请输入损耗率" datatype="d" value="${poVal.sunhaoPrecent}" name="orderMxList[${status.index}].bsunhaoPrecent" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                            <td align="center"><input  nullmsg="请输入成本(含损耗)！" readonly id="bchengben${status.index}"  errormsg="请输入成本(含损耗)" datatype="d" value="${poVal.chengben}" name="orderMxList[${status.index}].bchengben" maxlength="100" type="text"
                                                       style="width: 86%;" ignore="ignore"></td>
                        </tr>

                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</form>
<script type="text/javascript">
    function productLook2(indexFlag){
        currentFlag2 = indexFlag;
        $("#chkInfoForPro2").click();
    }

    function returnToVal2(){
        if($('#xjkz').val() == '' || $('#xjkz').val() == null || $('#xjkz').val() == 'undefined'){
            tip("请填写无缝坯布下克机重参数！");
            return false;

        }else if($('#djsxsj').val() == '' || $('#djsxsj').val() == null || $('djsxsj').val() == 'undefined'){
            tip("请填写无缝坯布单件所需时间参数！");
            return false;

        }else if($('#qdhsl').val() == '' || $('#qdhsl').val() == null || $('qdhsl').val() == 'undefined'){
            tip("请填写无缝坯布前道损耗率参数！");
            return false;

        }else if($('#hdhsl').val() == '' || $('#hdhsl').val() == null || $('hdhsl').val() == 'undefined'){
            tip("请填写坯布参数后道损耗率参数！");
            return false;

        }else{
            $("#bproName"+currentFlag2).val($("#proTypeName").val());
            $("#bproNum"+currentFlag2).val($("#proNum").val());
            $("#bproZnName"+currentFlag2).val($("#proZnName").val());
            $("#bbrand"+currentFlag2).val($("#brand").val());
            $("#bunit"+currentFlag2).val($("#unit").val());
            var sunhaoPrecent = (parseFloat($('#qdhsl').val())+parseFloat($('#hdhsl').val()))/100;
            $("#bsunhaoPrecent"+currentFlag2).val(toDecimal(sunhaoPrecent));
        }
    }
    </script>
