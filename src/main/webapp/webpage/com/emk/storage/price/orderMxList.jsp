<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<script type="text/javascript">
    var flag = ${fn:length(emkSampleDetailEntities)};
    var currentFlag = ${fn:length(emkSampleDetailEntities)};


    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#addBtn').bind('click', function(){
       /* if(flag>1 || ${param.proOrderId ne null}){
            $("html,body").animate({scrollTop:400},1);
        }*/
        $("#seqNum").html(flag+1);
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);

        $("#add_jeecgOrderProduct_table").find("[id='proZnName00']").attr("id","proZnName"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='proNum00']").attr("id","proNum"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='precent00']").attr("id","precent"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='unit00']").attr("id","unit"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='yongliang00']").attr("id","yongliang"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='sunhaoPrecent00']").attr("id","sunhaoPrecent"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("id","signPrice"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='chengben00']").attr("id","chengben"+flag);


        /*$("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_table").find("[id='signPrice00']").attr("id","signPrice"+flag);*/
        resetTrNum('add_jeecgOrderProduct_table');
        $("#proZnName"+flag).attr("onclick","productLook("+flag+")");
        $("#precent"+flag).attr("onkeyup","setYongliang("+flag+")");
        $("#signPrice"+flag).attr("onkeyup","setPrice("+flag+")");

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
        <c:if test="${param.priceId eq null || param.priceId eq '' || fn:length(emkSampleDetailEntities) eq 0 }">
            $('#addBtn').click();
        </c:if>
    });

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_table_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" /></td>
        <td align="center" width="40"><span id="seqNum"></span></td>
        <td align="center"><input id="proZnName00" nullmsg="请输入原料面料名称！"  errormsg="请输入原料面料名称"  name="orderMxList[#index#].proZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="proNum00" nullmsg="请输入原料面料代码！"  errormsg="请输入原料面料代码" name="orderMxList[#index#].proNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="precent00" nullmsg="请输入比例(%)！"  errormsg="请输入数值型" datatype="d" name="orderMxList[#index#].precent" maxlength="100" type="text" value=""
                                  style="width: 86%;" ></td>
        <td align="center"><input id="yongliang00" nullmsg="请输入单件用量！" datatype="d"  errormsg="请输入数值型" name="orderMxList[#index#].yongliang" maxlength="100" type="text" value=""
                                  style="width: 86%;" ></td>
        <td align="center">
            <select name="orderMxList[#index#].gysCode" style="width: 86%;" nullmsg="请输入供应商代码！" errormsg="请输入供应商代码" datatype="*">
                <c:forEach items="${gysList}" var="category">
                    <option value="${category.gys}">${category.gys}</option>
                </c:forEach>
            </select>
        </td>
        <%--<td align="center"><input id="gysCode00" nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码" name="orderMxList[#index#].gysCode" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>--%>
        <td align="center"><input id="signPrice00" nullmsg="请输入价格！" datatype="d" errormsg="请输入价格" name="orderMxList[#index#].signPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="unit00" nullmsg="请输入单位！" errormsg="请输入单位" name="orderMxList[#index#].unit" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sunhaoPrecent00" nullmsg="请输入损耗率！" datatype="d"  errormsg="请输入数值型" name="orderMxList[#index#].sunhaoPrecent" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="chengben00" nullmsg="请输入成本(含损耗)！" readonly datatype="d"  errormsg="请输入数值型" name="orderMxList[#index#].chengben" maxlength="100" type="text" value=""
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
    <input id="chengben" name="chengben" type="text" />

    <input id="id" name="id" type="text" />
    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect&selectType=0" name="emkProductList" width="820px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proType,proTypeName,proZnName,brand,unit,chengben" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn" href="#"></a> <a id="delBtn" href="#"></a></div>
<form id="ylfrm">
    <input id="orderMxListID" type="hidden" name="orderMxListID" value="${fn:length(emkSampleDetailEntities)}"/>
    <div class="table-c">
        <table id="mxtb" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr bgcolor="#F8F8F8" style="height: 32px;" >
                <td align="center"  width="40"><input type="checkbox" onclick="selectAll(this.checked)" /></td>
                <td align="center" width="40">序号</td>
                <td align="center"  width="150">原料面料名称</td>
                <td align="center"  width="150">原料面料代码</td>
                <td align="center"  width="150">比例(%)</td>
                <td align="center"  width="150">单件用量</td>
                <td align="center"  width="150">供应商代码</td>
                <td align="center"  width="150">价格</td>
                <td align="center"  width="60">单位</td>

                <td align="center"  width="150">损耗率</td>
                <td align="center"  width="150">成本(含损耗)</td>
            </tr>
            <tbody id="add_jeecgOrderProduct_table">
            <c:if test="${fn:length(emkSampleDetailEntities)  > 0 }">
                <c:forEach items="${emkSampleDetailEntities}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/></td>
                        <td align="center" width="40">${status.index+1}</td>
                        <td align="center"><input  nullmsg="请输入原料面料名称！" id="proZnName${status.index}" onclick="productLook(${status.index});" errormsg="请输入原料面料名称" value="${poVal.proZnName}" name="orderMxList[${status.index}].proZnName" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入原料面料代码！" id="proNum${status.index}" errormsg="请输入原料面料代码" value="${poVal.proNum}" name="orderMxList[${status.index}].proNum" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入比例(%)！" id="precent${status.index}" onkeyup="setYongliang(${status.index})" errormsg="请输入数值型" value="${poVal.precent}" name="orderMxList[${status.index}].precent" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单件用量！" id="yongliang${status.index}" errormsg="请输入数值型" value="${poVal.yongliang}" name="orderMxList[${status.index}].yongliang" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <select name="orderMxList[${status.index}].gysCode" style="width: 86%;" nullmsg="请输入供应商代码！" errormsg="请输入供应商代码" datatype="*">
                                <c:forEach items="${gysList}" var="category">
                                    <option value="${category.gys}" ${category.typecode eq poVal.gysCode ? 'selected':''}>${category.gys}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="center"><input  nullmsg="请输入价格！"  id="signPrice${status.index}"  errormsg="请输入数值型" datatype="d" value="${poVal.signPrice}" onkeyup="setPrice(${status.index})" name="orderMxList[${status.index}].signPrice" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入单位！" id="unit${status.index}"   errormsg="请输入单位" value="${poVal.unit}" name="orderMxList[${status.index}].unit" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入损耗率！" id="sunhaoPrecent${status.index}"  errormsg="请输入数值型" datatype="d" value="${poVal.sunhaoPrecent}" name="orderMxList[${status.index}].sunhaoPrecent" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入成本(含损耗)！" id="chengben${status.index}" readonly errormsg="请输入数值型" datatype="d" value="${poVal.chengben}" name="orderMxList[${status.index}].chengben" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                    </tr>

                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</form>
<script type="text/javascript">
    function productLook(indexFlag){
        currentFlag = indexFlag;
        $("#chkInfoForPro").click();
    }

    function returnToVal(){
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
            $("#proName"+currentFlag).val($("#proTypeName").val());
            $("#proNum"+currentFlag).val($("#proNum").val());
            $("#proZnName"+currentFlag).val($("#proZnName").val());
            $("#precent"+currentFlag).val($("#precent").val());
            $("#brand"+currentFlag).val($("#brand").val());
            $("#unit"+currentFlag).val($("#unit").val());
            $("#yongliang"+currentFlag).val($("#yongliang").val());

            var sunhaoPrecent = (parseFloat($('#qdhsl').val())+parseFloat($('#hdhsl').val()))/100;
            $("#sunhaoPrecent"+currentFlag).val(toDecimal(sunhaoPrecent));
        }
    }
    </script>

