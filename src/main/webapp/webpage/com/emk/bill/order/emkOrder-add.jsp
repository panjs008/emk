<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>采购合同表</title>
	<t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>

	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>
	<script src="metro/js/jquery.nicescroll.js" type="text/javascript"></script>

	<script type="text/javascript">
        //编写自定义JS代码
		var flagClause = 0;
        $(document).ready(
            function() {
                $("body").niceScroll();
                $('#emkOrderClauseListpanel').panel("refresh", "emkOrderClauseController.do?list&orderId=${emkOrderPage.id}");
                var width = window.top.document.body.offsetWidth*0.97;
                $("#cke_clauseContent_text").width(width);

                $("#tradeCode").val("${emkOrderPage.tradeCode}");
                <c:if test="${order.htbz ne null}">
                	BindSelect("htbz","",1,"${emkOrderPage.htbz}","cointype");
                </c:if>
                <c:if test="${order.htbz eq null}">
					BindSelect("htbz","",0,"","cointype");
                </c:if>
                <c:if test="${order.ysfs ne null}">
                	BindSelect("ysfs","",3,"${emkOrderPage.ysfs}","ystype");
                </c:if>
                <c:if test="${order.ysfs eq null}">
                	BindSelect("ysfs","",2,"","ystype");
                </c:if>
                <c:if test="${order.tradeCode ne null}">
                	BindSelect("tradeCode","",5,"${emkOrderPage.tradeCode}","trade");
                </c:if>
                <c:if test="${order.tradeCode eq null}">
                	BindSelect("tradeCode","",4,"","trade");
                </c:if>
                <c:if test="${order.state ne 0  && order.state ne null}">
					setReadOnly($("#baseInfoId"));
                	setReadOnly($("#htInfoId"));
                	setReadOnly($("#remarkId"));

                </c:if>

                $("#clause_num").change(function(){
                    if(flagClause>0){
                        $.ajax({
                            url : "emkClauseController.do?getClause&clause_num="+$("#clause_num").val(),
                            type : 'post',
                            cache : false,
                            data: null,
                            success : function(data) {
                                var d = $.parseJSON(data);
                                if (d.success) {
                                    ckeditor_clauseContent.setData(d.obj);
                                }
                            }
                        });
					}
                    flagClause++;
                });

                <c:if test="${emkOrderClauseEntity.clauseNum eq null}">
                	BindSelect2("clause_num","",0,"");
                </c:if>
                <c:if test="${emkOrderClauseEntity.clauseNum ne null}">
                	BindSelect2("clause_num","",1,"${emkOrderClauseEntity.clauseNum}");
                </c:if>
            });

        function getClauseContent(){
            ckeditor_clauseContent.setData(ckeditor_clauseContent.getData());
            $("#clause_content").val(ckeditor_clauseContent.getData());

        }

		function getRemarkContent(){
			ckeditor_remarkval.setData(ckeditor_remarkval.getData());
			$("#remark").val(ckeditor_remarkval.getData());

		}

        function goClauseUpdate(){
            createwindow("添加合同条款", "emkOrderClauseController.do?goAdd&orderId=${param.orderId}",600,300);
        }

        function goAdd(){
            createwindow("添加合同条款", "emkOrderClauseController.do?goAdd&orderId=${param.orderId}",600,300);
        }
        function goAdd2(){
            createwindow("添加收款方式", "emkOrderGatherController.do?goAdd&orderId=${param.orderId}",700,400);
        }

        function setReadOnly(obj) {
            obj.find(":text,textarea").attr("disabled",true);
            obj.find(":checkbox,:radio,select").attr("disabled",true);
            //obj.find("a").hide();
            obj.find(".date").removeClass("date");
            //obj.find(".required").removeClass("required");
        }

        function BindSelect(ctrlName, url,type,categoryId,typecode) {
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
            $.getJSON('emkOrderController.do?findBzList&typecode='+typecode, function (data) {
                control.empty();//清空下拉框
                if(type == 0){
                    control.append("<option value=''>请选择币种</option>");
                }
                if(type == 2){
                    control.append("<option value=''>请选择运输方式</option>");
                }
                if(type == 4){
                    control.append("<option value=''>请选择贸易国别</option>");
                }
                $.each(data.obj, function (i, item) {
                    control.append("<option value='" + item.typename + "'>&nbsp;" + item.typename + "</option>");
                });

                if(type ==1 || type ==3 || type ==5){
                    $("#"+ctrlName).select2('val',categoryId);
                }
            });
        }

        function formatState (state) {
            if (!state.id) { return state.text; }
            var $state = $(
                '<span>' + state.text + '</span>'
            );
            return $state;
        };


        function BindSelect2(ctrlName, url,type,categoryId) {
            var control = $('#' + ctrlName);
            //设置Select2的处理
            control.select2({
                formatResult: formatState2,
                formatSelection: formatState2,
                escapeMarkup: function (m) {
                    return m;
                }
            });
            //绑定Ajax的内容
            $.getJSON('emkClauseController.do?findClauseList', function (data) {
                control.empty();//清空下拉框
                if(type == 0){
                    control.append("<option value=''>请选择合同编号</option>");
                }
                $.each(data.obj, function (i, item) {
                    control.append("<option value='" + item.clause_num + "'>&nbsp;" + item.clause_num + "</option>");
                });

                if(type ==1){
                    $("#"+ctrlName).select2('val',categoryId);
                }
            });
        }

        function formatState2 (state) {
            if (!state.id) { return state.text; }
            var $state = $(
                '<span>' + state.text + '</span>'
            );
            return $state;
        };


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderController.do?doAdd" >
	<input id="id" name="id" type="hidden" value="${emkOrderPage.id }"/>
	<input id="orderBtn" type="submit" style="display: none">
	<strong>&nbsp;公共信息</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:0px;" />
	<table id="baseInfoId" style="width: 100%;margin-top: -10px;" cellpadding="0" cellspacing="0" class="formtable" border="0">
		<tr>
			<td align="right" class="value" style="color:red" >
				<label class="Validform_label">
					采购合同号:
				</label>
			</td>
			<td class="value">
				<input id="orderNum" name="orderNum" value="${emkOrderPage.orderNum eq null ? orderNum:emkOrderPage.orderNum}" validType="emk_order,order_num,id" datatype="*" type="text" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">采购合同号</label>
			</td>
			<td align="right" class="value" style="color:red">
				<label class="Validform_label">
					合同币种:
				</label>
			</td>
			<td class="value">
				<select id="htbz" name="htbz" style="width:157px;">
				</select>
				<%--<input id="htbz" name="htbz" type="text" value="${emkOrderPage.htbz eq  null ? bz:emkOrderPage.htbz }" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同币种</label>
			</td>
			<td align="right" class="value" style="color:red">
				<label class="Validform_label">
					签约日期:
				</label>
			</td>
			<td class="value">
				<input id="signDate" name="signDate" value="${emkOrderPage.signDate eq null ? signDate:emkOrderPage.signDate}" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签约日期</label>
			</td>
		</tr>

		<tr>
			<td align="right" class="value" style="color:red" height="30px;">
				<label class="Validform_label">
					签约地:
				</label>
			</td>
			<td class="value"class="value">
				<input id="signPlace" name="signPlace" value="${emkOrderPage.signPlace}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签约地</label>
			</td>
			<td align="right" class="value" style="color:red">
				<label class="Validform_label">
					运输方式:
				</label>
			</td>
			<td class="value" class="value">
				<select id="ysfs" name="ysfs" style="width:157px;">
				</select>
				<%--<input id="ysfs" name="ysfs" type="text" value="${emkOrderPage.ysfs}" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输方式</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					目的地:
				</label>
			</td>
			<td class="value">
				<input id="mdd" name="mdd" type="text" value="${emkOrderPage.mdd}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">目的地</label>
			</td>
		</tr>

		<tr>
			<td align="right" class="value" style="color:red" height="30px;">
				<label class="Validform_label">
					贸易国别:
				</label>
			</td>
			<td class="value"class="value" colspan="5">
				<select id="tradeCode" name="tradeCode" style="width:157px;">
				</select>
				<%--<t:dictSelect id="tradeCode" field="tradeCode" typeGroupCode="trade" datatype="*" defaultVal="default" hasLabel="false" title="贸易国别"></t:dictSelect>--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签约地</label>
			</td>


		</tr>

	</table>

	<strong>&nbsp;合同条款</strong>
	<hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:0px;" />
	<table id="htInfoId" style="width: 100%;" cellpadding="0" cellspacing="0" class="formtable" border="0">
		<tr>
			<td align="right" class="value" >
				<label class="Validform_label">
					条款编号:
				</label>
			</td>
			<td class="value">
				<input id="clause_content" type="hidden" name="clause_content"/>
				<select id="clause_num" name="clause_num" style="width:150px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">采购合同号</label>
			</td>
			<td width="56%" class="value">&nbsp;</td>
		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					内容描述:
				</label>
			</td>
			<td class="value" colspan="2">
					<%--<iframe scrolling="yes" id="orderFrm" frameborder="0" src="emkOrderClauseController.do?list&orderId=${order.id}&state=${order.state}" style="width:100%;height:100%;"></iframe>--%>

				<t:ckeditor name="clauseContent" type="height:150" value="${emkOrderClauseEntity.clauseContent}"></t:ckeditor>
			</td>
		</tr>

	</table>

	<%--<iframe scrolling="yes" id="orderFrm" frameborder="0" src="emkOrderClauseController.do?list&orderId=${order.id}&state=${order.state}" style="width:100%;height:100%;"></iframe>--%>
	<strong>&nbsp;收汇方式</strong>
	<iframe scrolling="yes" id="orderFrm" frameborder="0" src="emkOrderGatherController.do?list&orderId=${order.id}&state=${order.state}" style="width:100%;height:100%;"></iframe>

	<%--<div>
		<t:datagrid name="emkOrderClauseList" height="30" checkbox="false"  pagination="false" fitColumns="false" title="" actionUrl="emkOrderClauseController.do?datagrid&orderId=${order.id}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
			<t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" hidden="${(order.state eq 0 || order.state eq null)  ? false:true}" width="130" ></t:dgCol>
			<t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="条款编号"  field="clauseNum"  queryMode="single"  width="200"></t:dgCol>
			<t:dgCol title="内容描述"  field="clauseContent"  queryMode="single"  width="350"></t:dgCol>
			<t:dgFunOpt funname="goAdd" title="添加" exp="createName#ne#1" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
			<t:dgDelOpt title="删除" operationCode="delete" exp="createName#ne#1" url="emkOrderClauseController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>

		</t:datagrid>
	</div>

	<strong>&nbsp;收汇方式</strong>
	<div>
		<t:datagrid name="emkOrderGatherList" height="20" checkbox="false"  pagination="false" fitColumns="false" title="" actionUrl="emkOrderGatherController.do?datagrid&orderId=${order.id}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
			<t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="130"></t:dgCol>
			<t:dgCol title="操作" field="opt" hidden="${(order.state eq 0 || order.state eq null)  ? false:true}"  width="130"></t:dgCol>
			<t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="订单ID"  field="orderId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="货前后"  field="hpreNext" replace="货前_0,货后_1"  queryMode="single"  width="90"></t:dgCol>
			<t:dgCol title="收款方式"  field="gatherType"  queryMode="single"  width="150"></t:dgCol>
			<t:dgCol title="日期方式"  field="dateType" dictionary="rqfs" queryMode="single"  width="180"></t:dgCol>
			<t:dgCol title="比例"  field="dopute"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="金额"  field="money"  queryMode="single"  width="150"></t:dgCol>
			<t:dgCol title="备注"  field="remark"  queryMode="single"  width="200"></t:dgCol>
			<t:dgFunOpt funname="goAdd2" title="添加"  exp="createName#ne#1"  urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
			<t:dgDelOpt title="删除" operationCode="delete" exp="createName#ne#1" url="emkOrderGatherController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
			&lt;%&ndash; <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderGatherController.do?goAdd" funname="add"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOrderGatherController.do?goUpdate" funname="update"></t:dgToolBar>
            <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkOrderGatherController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>&ndash;%&gt;
		</t:datagrid>
	</div>--%>

	<strong>&nbsp;备注</strong><br/><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" /><br/>
	<table id="remarkId" style="width: 100%;margin-top: -20px" cellpadding="0" cellspacing="0" class="formtable" border="0">
		<tr>
			<td class="value">
				<input id="remark" type="hidden" name="remark" value="${emkOrderPage.remark}"/>
				<t:ckeditor name="remarkval" type="height:150" value="${emkOrderPage.remark}"></t:ckeditor>
				<%--<textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark">${emkOrderPage.remark}</textarea>--%>
			</td>
		</tr>
	</table>
</t:formvalid>


</body>
<script src = "webpage/com/emk/bill/order/emkOrder.js"></script>
