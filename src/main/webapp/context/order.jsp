<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">


        //编写自定义JS代码
        $(function(){
            BindSelect("cusFrom","",0,"");
			/*if(${khmc ne null}){
			 BindSelect("khmc","",1,"${khmc}");
			 }else{
			 BindSelect("khmc","",0,"");
			 }*/

            //$("#khmc").val("${xhdPage.khCode}");

        });
        function BindSelect(ctrlName, url,type,categoryId) {
            var control = $('#' + ctrlName);
            //设置Select2的处理
            control.select2({
                formatResult: formatState,
                formatSelection: formatState,
                escapeMarkup: function (m) {
                    return m;
                }
            });
            //绑定Ajax的内容
            $.getJSON('ymkCustomFromController.do?findSelectList', function (data) {
                control.empty();//清空下拉框
                control.append("<option value=''>请选择客户</option>");
                $.each(data.obj, function (i, item) {
                    control.append("<option value='" + item.fname + "'>&nbsp;" + item.fname + "</option>");
                });

				/* if(type ==1){
				 $("#"+ctrlName).select2('val',categoryId);
				 }*/
            });
        }

        function formatState (state) {
            if (!state.id) { return state.text; }
            var $state = $(
                '<span>' + state.text + '</span>'
            );
            return $state;
        };

        $(document).ready(function(){
            $(".datagrid-toolbar").parent().css("width","auto");

        });
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ymkCustomController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${ymkCustomPage.id }"/>
	<strong>公共信息</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;" />
		<table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td align="right" class="value">
					<label class="Validform_label">
						采购合同号:
					</label>
				</td>
				<td class="value">
					<input id="cusNum" name="cusNum" type="text" value="${cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">采购合同号</label>
				</td>

				<td align="right" class="value">
					<label class="Validform_label">
						业务人员:
					</label>
				</td>
				<td class="value" >
					<input id="cusName1" name="cusName1" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">业务人员</label>
				</td>
				<td align="right" class="value">
					<label class="Validform_label">
						公司:
					</label>
				</td>
				<td class="value">
					<input id="cusNum1" name="cusNum1" type="text" value="${cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">公司</label>
				</td>
			</tr>
		</table>
	<strong>基本信息 </strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;" />
	<table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
	<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="cusType" field="cusType" typeGroupCode="custom" datatype="*" defaultVal="default" hasLabel="false" title="客户类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>

			<td align="right" class="value">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="cusNum111" name="cusNum111" type="text" value="${cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>

			<td align="right" class="value">
				<label class="Validform_label">
					贸易地区:
				</label>
			</td>
			<td class="value">
				<input id="cusNum11" name="cusNum11" type="text" value="${cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">贸易地区</label>
			</td>
		</tr>

		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					客户地址:
				</label>
			</td>
			<td class="value">
				<input id="address" name="address" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户地址</label>
			</td>

			<td align="right" class="value">
				<label class="Validform_label">
					办公电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公电话</label>
			</td>

			<td align="right" class="value">
				<label class="Validform_label">
					客户编码:
				</label>
			</td>
			<td class="value">
				<input id="cusNum12" name="cusNum12" type="text" value="${cusNum}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编码</label>
			</td>
		</tr>



	</table>
</t:formvalid>
<strong>付款方式</strong>
<iframe id="printfrm" src="ymkCustomTraceController.do?list" width="100%" height="200"
		style="border: 0px; line-height: 21px; background: #fff;overflow-x : hidden ; " frameborder="no" border="0" scrolling="yes">
</iframe>


</body>

