<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag3 = ${fn:length(emkSampleDetailEntities2)};
    var currentFlag3 = ${fn:length(emkSampleDetailEntities2)};

    $('#addBtn3').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn3').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn3').bind('click', function(){
       /* if(flag3>1 || ${param.proOrderId ne null}){
            $("html,body").animate({scrollTop:400},1);
        }*/
        $("#cseqNum").html(flag3+1);

        var tr =  $("#add_jeecgOrderProduct_table3_template3 tr").clone();
        $("#add_jeecgOrderProduct_table3").append(tr);

        $("#add_jeecgOrderProduct_table3").find("[id='cproZnName00']").attr("id","cproZnName"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cproNum00']").attr("id","cproNum"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cbrand00']").attr("id","cbrand"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cunit00']").attr("id","cunit"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cyongliang00']").attr("id","cyongliang"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='csunhaoPrecent00']").attr("id","csunhaoPrecent"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='csignPrice00']").attr("id","csignPrice"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='cchengben00']").attr("id","cchengben"+flag3);

        /*$("#add_jeecgOrderProduct_table3").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_table3").find("[id='signPrice00']").attr("id","signPrice"+flag3);*/
        resetTrNum('add_jeecgOrderProduct_table3');
        $("#cproZnName"+flag3).attr("onclick","productLook3("+flag3+")");
        $("#csignPrice"+flag3).attr("onkeyup","setPrice3("+flag3+")");
        $("#orderMxListID3").val($("#mxtb3").find("tr").length-1);

        flag3++;

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
        <c:if test="${param.priceId eq null || param.priceId eq ''  || fn:length(emkSampleDetailEntities2) eq 0 }">
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
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" /></td>
        <td align="center" width="40"><span id="cseqNum"></span></td>
        <td align="center"><input id="cproZnName00" nullmsg="请输入包装辅料名称！"  errormsg="请输入包装辅料名称"  name="orderMxList[#index#].cproZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cproNum00" nullmsg="请输入包装辅料代码！"  errormsg="请输入包装辅料代码" name="orderMxList[#index#].cproNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cbrand00" nullmsg="请输入包装辅料规格！"  errormsg="请输入包装辅料规格" name="orderMxList[#index#].cbrand" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cyongliang00" nullmsg="请输入单件用量！"  errormsg="请输入单件用量" name="orderMxList[#index#].cyongliang" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select name="orderMxList[#index#].cgysCode" style="width: 86%;" nullmsg="请输入供应商代码！" errormsg="请输入供应商代码" datatype="*">
                <c:forEach items="${gysList}" var="category">
                    <option value="${category.gys}">${category.gys}</option>
                </c:forEach>
            </select>
        </td>
        <td align="center"><input id="csignPrice00" nullmsg="请输入价格！" datatype="d" errormsg="请输入价格" name="orderMxList[#index#].csignPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cunit00" nullmsg="请输入单位！" errormsg="请输入单位" name="orderMxList[#index#].cunit" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="csunhaoPrecent00" nullmsg="请输入损耗率！" datatype="d" errormsg="请输入损耗率" name="orderMxList[#index#].csunhaoPrecent" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="cchengben00" nullmsg="请输入成本(含损耗)！" datatype="d" errormsg="请输入成本(含损耗)" name="orderMxList[#index#].cchengben" maxlength="100" type="text" value=""
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
    <input id="cunit" name="unit" type="text" />

    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect3&selectType=2" name="emkProductList" width="820px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proType,proTypeName,proZnName,brand,unit" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn3" href="#"></a> <a id="delBtn3" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<form id="bzfrm">
    <input id="orderMxListID3" type="hidden" name="orderMxListID3" value="${fn:length(emkSampleDetailEntities2)}"/>

    <div class="table-c">
        <table id="mxtb3" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
                <td align="center" width="40">序号</td>
                <td align="center"  width="150">包装辅料名称</td>
                <td align="center"  width="150">包装辅料代码</td>
                <td align="center"  width="150">包装辅料规格</td>
                <td align="center"  width="150">单件用量</td>
                <td align="center"  width="150">供应商代码</td>
                <td align="center"  width="150">价格</td>
                <td align="center"  width="60">单位</td>
                <td align="center"  width="150">损耗率</td>
                <td align="center"  width="150">成本(含损耗)</td>
            </tr>
            <tbody id="add_jeecgOrderProduct_table3">
            <c:if test="${fn:length(emkSampleDetailEntities2)  > 0 }">
                <c:forEach items="${emkSampleDetailEntities2}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/></td>
                        <td align="center" width="40">${status.index+1}</td>
                        <td align="center"><input  nullmsg="请输入包装辅料名称！" id="cproZnName${status.index}" onclick="productLook3(${status.index});"  errormsg="请输入包装辅料名称" value="${poVal.proZnName}" name="orderMxList[${status.index}].cproZnName" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入包装辅料代码！" id="cproNum${status.index}"  errormsg="请输入包装辅料代码" value="${poVal.proNum}" name="orderMxList[${status.index}].cproNum" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入包装辅料规格！"  id="cbrand${status.index}" errormsg="请输入包装辅料规格" value="${poVal.brand}" name="orderMxList[${status.index}].cbrand" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单件用量！" id="cyongliang${status.index}" errormsg="请输入单件用量" value="${poVal.yongliang}" name="orderMxList[${status.index}].cyongliang" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <select name="orderMxList[${status.index}].cgysCode" style="width: 86%;" nullmsg="请输入供应商代码！" errormsg="请输入供应商代码" datatype="*">
                                <c:forEach items="${gysList}" var="category">
                                    <option value="${category.gys}" ${category.typecode eq poVal.gysCode ? 'selected':''}>${category.gys}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="center"><input  nullmsg="请输入价格！"  datatype="d" errormsg="请输入价格" id="csignPrice${status.index}" onkeyup="setPrice3(${status.index})" value="${poVal.signPrice}" name="orderMxList[${status.index}].csignPrice" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单位！" id="cunit${status.index}"   errormsg="请输入单位" value="${poVal.unit}" name="orderMxList[${status.index}].cunit" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入损耗率！" id="csunhaoPrecent${status.index}" datatype="d" errormsg="请输入损耗率" value="${poVal.sunhaoPrecent}" name="orderMxList[${status.index}].csunhaoPrecent" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入成本(含损耗)！"  id="cchengben${status.index}" datatype="d"  errormsg="请输入成本(含损耗)" value="${poVal.chengben}" name="orderMxList[${status.index}].cchengben" maxlength="100" type="text"
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
            $("#cproName"+currentFlag3).val($("#cproTypeName").val());
            $("#cproNum"+currentFlag3).val($("#cproNum").val());
            $("#cproZnName"+currentFlag3).val($("#cproZnName").val());
            $("#cbrand"+currentFlag3).val($("#cbrand").val());
            $("#cunit"+currentFlag3).val($("#cunit").val());
            var sunhaoPrecent = (parseFloat($('#qdhsl').val())+parseFloat($('#hdhsl').val()))/100;
            $("#csunhaoPrecent"+currentFlag3).val(toDecimal(sunhaoPrecent));
        }

    }
</script>