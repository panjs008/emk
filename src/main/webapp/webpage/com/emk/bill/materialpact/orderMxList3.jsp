<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<script type="text/javascript">
    var flag3 = ${fn:length(emkSampleDetailEntities)};
    var currentFlag3 = ${fn:length(emkSampleDetailEntities)};

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

        var tr =  $("#add_jeecgOrderProduct_table3_template tr").clone();
        $("#add_jeecgOrderProduct_table3").append(tr);

        $("#add_jeecgOrderProduct_table3").find("[id='proZnName00']").attr("id","proZnName"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='proNum00']").attr("id","proNum"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='brand00']").attr("id","brand"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='precent00']").attr("id","precent"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='unit00']").attr("id","unit"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='yongliang00']").attr("id","yongliang"+flag3);
        $("#add_jeecgOrderProduct_table3").find("[id='sunhaoPrecent00']").attr("id","sunhaoPrecent"+flag3);

        /*$("#add_jeecgOrderProduct_table3").find("[id='signPrice00']").attr("datatype","d");
        $("#add_jeecgOrderProduct_table3").find("[id='signPrice00']").attr("id","signPrice"+flag3);*/
        resetTrNum('add_jeecgOrderProduct_table3');
        $("#proZnName"+flag3).attr("onclick","productLook("+flag3+")");
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
        <c:if test="${param.proOrderId eq null || param.proOrderId eq ''}">
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
    <tbody id="add_jeecgOrderProduct_table3_template">
    <tr>
        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
        <td align="center"><input id="cgxqdh00" nullmsg="请输入采购需求单号！"  errormsg="请输入采购需求单号"  name="orderMxList[#index#].cgxqdh" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <c:if test="${param.flag eq '1'}">
            <td align="center"><input id="cghtbh00" nullmsg="请输入采购合同号！"  errormsg="请输入采购合同号"  name="orderMxList[#index#].cghtbh" maxlength="100" type="text" value=""
                                      style="width: 86%;" ignore="ignore"></td>
        </c:if>
        <td align="center"><input id="proZnName00" nullmsg="请输入包装辅料名称！"  errormsg="请输入包装辅料名称"  name="orderMxList[#index#].proZnName" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="proNum00" nullmsg="请输入包装辅料代码！"  errormsg="请输入包装辅料代码" name="orderMxList[#index#].proNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="gysCode00" nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码" name="orderMxList[#index#].gysCode" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="brand00" nullmsg="请输入规格！"   errormsg="请输入规格" name="orderMxList[#index#].brand" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="direction00" nullmsg="请输入捻向！"  errormsg="请输入捻向" name="orderMxList[#index#].direction" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="betchNum00" nullmsg="请输入批号！"  errormsg="请输入批号" name="orderMxList[#index#].betchNum" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="width00" nullmsg="请输入幅宽！"  errormsg="请输入幅宽" name="orderMxList[#index#].width" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="color00" nullmsg="请输入颜色！"  errormsg="请输入颜色" name="orderMxList[#index#].color" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="weight00" nullmsg="请输入克重！"  errormsg="请输入克重" name="orderMxList[#index#].weight" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="chengf00" nullmsg="请输入成分！"  errormsg="请输入成分" name="orderMxList[#index#].chengf" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="yongliang00" nullmsg="请输入单件用量！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].yongliang" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="signPrice00" nullmsg="请输入单价！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].signPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sunhaoPrecent00" nullmsg="请输入损耗率！" datatype="d"  errormsg="请输入数值" name="orderMxList[#index#].sunhaoPrecent" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sumYongliang00" nullmsg="请输入总用量！" datatype="d"  errormsg="请输入数值" name="orderMxList[#index#].sumYongliang" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="sumPrice00" nullmsg="请输入总金额！"  datatype="d" errormsg="请输入数值" name="orderMxList[#index#].sumPrice" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="unit00" nullmsg="请输入单位！" errormsg="请输入单位" name="orderMxList[#index#].unit" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="remark00" nullmsg="请输入备注！"   errormsg="请输入备注" name="orderMxList[#index#].remark" maxlength="100" type="text" value=""
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="display:none">
    <input id="proNum" name="proNum" type="text"/>
    <input id="proType" name="proType" type="text" />
    <input id="proTypeName" name="proTypeName" type="text" />
    <input id="proZnName" name="proZnName" type="text" />
    <input id="precent" name="precent" type="text" />
    <input id="brand" name="brand" type="text" />
    <input id="unit" name="unit" type="text" />
    <input id="remark" name="remark" type="text" />


    <input id="id" name="id" type="text" />
    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect&selectType=2" name="emkProductList" width="820px" height="500px"
               icon="icon-search" title="选择面料" textname="id,proNum,proType,proTypeName,proZnName,precent,brand,unit,remark" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn3" href="#"></a> <a id="delBtn3" href="#"></a></div>
<input id="orderMxListID3" type="hidden" name="orderMxListID3" value="${fn:length(emkSampleDetailEntities)}"/>
<div class="table-c">
    <table id="mxtb3" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="40">序号</td>
            <td align="center"  width="230">采购需求单号</td>
            <c:if test="${param.flag eq '1'}">
                <td align="center"  width="220">采购合同号</td>
            </c:if>
            <td align="center"  width="150">包装辅料名称</td>
            <td align="center"  width="150">包装辅料代码</td>
            <td align="center"  width="110">供应商代码</td>
            <td align="center"  width="110">规格</td>
            <td align="center"  width="150">捻向</td>
            <td align="center"  width="150">批号</td>
            <td align="center"  width="100">幅宽</td>
            <td align="center"  width="80">颜色</td>
            <td align="center"  width="90">克重</td>
            <td align="center"  width="90">成分</td>
            <td align="center"  width="80">损耗率</td>
            <td align="center"  width="90">单件用量</td>
            <td align="center"  width="90">单价</td>
            <td align="center"  width="100">总用量</td>
            <td align="center"  width="120">总金额</td>
            <td align="center"  width="60">单位</td>
            <td align="center"  width="80">备注</td>

        </tr>
        <tbody id="add_jeecgOrderProduct_table3">
        <c:if test="${fn:length(emkSampleDetailEntities)  > 0 }">
            <c:forEach items="${emkSampleDetailEntities}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    <td align="center"><input  nullmsg="请输入采购需求单号！"   errormsg="请输入采购需求单号" value="${poVal.cgxqdh}" name="orderMxList[${status.index}].cgxqdh" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <c:if test="${param.flag eq '1'}">
                        <td align="center"><input  nullmsg="请输入采购合同号！"   errormsg="请输入采购合同号" value="${poVal.cghtbh}" name="orderMxList[${status.index}].cghtbh" maxlength="100" type="text" value=""
                                                   style="width: 86%;" ignore="ignore"></td>
                    </c:if>
                    <td align="center"><input  nullmsg="请输入包装辅料名称！" id="proZnName${status.index}" onclick="productLook(${status.index});"  errormsg="请输入包装辅料名称" value="${poVal.proZnName}" name="orderMxList[${status.index}].proZnName" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入包装辅料代码！" id="proNum${status.index}"   errormsg="请输入包装辅料代码" value="${poVal.proNum}" name="orderMxList[${status.index}].proNum" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入供应商代码！"  errormsg="请输入供应商代码"  value="${poVal.gysCode}" name="orderMxList[${status.index}].gysCode" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入规格！" id="brand${status.index}"  errormsg="请输入规格" value="${poVal.brand}" name="orderMxList[${status.index}].brand" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入捻向！"  errormsg="请输入捻向" value="${poVal.direction}" name="orderMxList[${status.index}].direction" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入批号！"  errormsg="请输入批号" value="${poVal.betchNum}" name="orderMxList[${status.index}].betchNum" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入幅宽！"  errormsg="请输入幅宽" value="${poVal.width}" name="orderMxList[${status.index}].width" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入颜色！"  errormsg="请输入颜色" value="${poVal.color}" name="orderMxList[${status.index}].color" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入克重！"  errormsg="请输入克重" value="${poVal.weight}" name="orderMxList[${status.index}].weight" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入成分！"  errormsg="请输入成分" value="${poVal.chengf}" name="orderMxList[${status.index}].chengf" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入损耗率！"  id="sunhaoPrecent${status.index}"  errormsg="请输入数值" datatype="d" value="${poVal.sunhaoPrecent}" name="orderMxList[${status.index}].sunhaoPrecent" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单件用量！" id="yongliang${status.index}"   errormsg="请输入数值" datatype="d" value="${poVal.yongliang}" name="orderMxList[${status.index}].yongliang" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单价！"  errormsg="请输入数值" datatype="d" value="${poVal.signPrice}" name="orderMxList[${status.index}].signPrice" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入总用量！"  errormsg="请输入数值" datatype="d" value="${poVal.sumYongliang}" name="orderMxList[${status.index}].sumYongliang" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入总金额！"  errormsg="请输入数值" datatype="d" value="${poVal.sumPrice}" name="orderMxList[${status.index}].sumPrice" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入单位！" id="unit${status.index}"   errormsg="请输入单位" value="${poVal.unit}" name="orderMxList[${status.index}].unit" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入备注！"  id="remark${status.index}"  errormsg="请输入备注" value="${poVal.remark}" name="orderMxList[${status.index}].remark" maxlength="100" type="text" value=""
                                               style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    function productLook(indexFlag){
        currentFlag3 = indexFlag;
        $("#chkInfoForPro").click();
    }

    function returnToVal(){
        $("#proName"+currentFlag3).val($("#proTypeName").val());
        $("#proNum"+currentFlag3).val($("#proNum").val());
        $("#proZnName"+currentFlag3).val($("#proZnName").val());
        $("#brand"+currentFlag3).val($("#brand").val());
        $("#unit"+currentFlag3).val($("#unit").val());
        $("#proId"+currentFlag3).val($("#id").val());
        $("#remark"+currentFlag3).val($("#remark").val());

    }
    </script>

