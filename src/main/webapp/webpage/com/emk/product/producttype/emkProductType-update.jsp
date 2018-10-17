<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>部门</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //编写自定义JS代码
        $(function() {
            $('#cc').combotree({
                url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
                panelHeight: 200,
                width: 157,
                onClick: function(node){
                    $("#officeId").val(node.id);
                }
            });
            if($('#officeLevel').val()=='1'){
                $('#pfun').show();
            }else{
                $('#pfun').hide();
            }


            $('#officeLevel').change(function(){
                if($(this).val()=='1'){
                    $('#pfun').show();
                    var t = $('#cc').combotree('tree');
                    var nodes = t.tree('getRoots');
                    if(nodes.length>0){
                        $('#cc').combotree('setValue', nodes[0].id);
                        $("#officeId").val(nodes[0].id);
                    }
                }else{
                    var t = $('#cc').combotree('tree');
                    var node = t.tree('getSelected');
                    if(node){
                        $('#cc').combotree('setValue', null);
                    }
                    $("#officeId").val(null);
                    $('#pfun').hide();
                }
            });
        });

    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProductTypeController.do?doUpdate" tiptype="1" >
    <input id="id" name="id" type="hidden" value="${emkProductTypePage.id }">
    <input id="createName" name="createName" type="hidden" value="${emkProductTypePage.createName }">
    <input id="createDate" name="createDate" type="hidden" value="${emkProductTypePage.createDate }">
    <input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${emkProductTypePage.sysOrgCode }">
    <input id="isDel" name="isDel" type="hidden" value="0">
    <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    类别名称:
                </label>
            </td>
            <td class="value" colspan="3">
                <input id="content" name="content" type="text" style="width:90%" value="${emkProductTypePage.content }" class="inputxt" datatype="*">
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">类别名称</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    类别代码:
                </label>
            </td>
            <td class="value" colspan="3">
                <input id="proCode" name="proCode" type="text" style="width: 250px" value="${emkProductTypePage.proCode }" class="inputxt" datatype="*">
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">产品代码</label>
            </td>
        </tr>


        <tr>
            <td align="right">
                <label class="Validform_label">
                    类别:
                </label>
            </td>
            <td class="value" colspan="3">
                <select name="officeLevel" id="officeLevel" datatype="*">
                    <option value="0" <c:if test="${emkProductTypePage.officeLevel eq 0}">selected="selected"</c:if>>
                        一级类别
                    </option>
                    <option value="1" <c:if test="${emkProductTypePage.officeLevel > 0}"> selected="selected"</c:if>>
                        下级类别
                    </option>
                </select>
                <label class="Validform_label" style="display: none;">父级类别</label>
            </td>
        </tr>
        <tr id="pfun">
            <td align="right">
                <label class="Validform_label">
                    父级类别:
                </label>
            </td>
            <td class="value" colspan="3">
                <input id="cc" value="${emkProductTypePage.productTypeEntity.content}">
                <input id="officeId" name="productTypeEntity.id" style="display: none;" value="${emkProductTypePage.productTypeEntity.id}">
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">父级类别</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    备注:
                </label>
            </td>
            <td class="value" colspan="3">
                <textarea id="remark" style="width:90%;height:60px" class="inputxt" rows="6" name="remark">${emkProductTypePage.remark }</textarea>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">备注</label>
            </td>
        </tr>


    </table>
</t:formvalid>
</body>
<script src = "webpage/com/gxedu/spokenpager/gjsSpokenPager.js"></script>
