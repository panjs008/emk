<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>公司抬头信息表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>
	<script src="metro/js/jquery.nicescroll.js" type="text/javascript"></script>

	<script type="text/javascript">
        //编写自定义JS代码
        $(function(){
            $("body").niceScroll();
            <c:if test="${order.state ne null}">
            BindSelect("cusName","",1,"${emkOrderCompanyTopPage.cusName}");
            </c:if>
            <c:if test="${order.state eq null}">
            BindSelect("cusName","",0,"");
            </c:if>

            <c:if test="${order.state eq 1}">
            setReadOnly($("#companyBaseInfoId"));
            setReadOnly($("#myCompanyBaseInfoId"));
            </c:if>

            $.ajax({
                url : "ymkCustomContactController.do?getContect&cusName=${emkOrderCompanyTopPage.cusName}",
                type : 'post',
                cache : false,
                data: null,
                success : function(data) {
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var msg = d.msg;
                        var dataItems = new Array(); //定义一数组
                        dataItems = d.obj.split(";"); //字符分割
                        //W.document.location.reload(true);
                        var option1='';
                        for (i=0;i<dataItems.length ;i++ ) {
                            var dataitem = new Array(); //定义一数组
                            dataitem = dataItems[i].split(","); //字符分割

                            if(dataitem[1] == '${emkOrderCompanyTopPage.cusRelationer}'){
                                option1 += '<option value='+dataitem[1]+' selected>'+dataitem[1]+'</option>';
                            }else{
                                option1 += '<option value='+dataitem[1]+'>'+dataitem[1]+'</option>';
                            }
                        }
                        $("#cusRelationer").append(option1);
                    }
                }
            });
            $("#cusName").change(function(){
                $('#cusRelationer').empty();

                $.ajax({
                    url : "ymkCustomContactController.do?getContect&cusName="+$("#cusName").val(),
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        if (d.success) {
                            var msg = d.msg;
                            var dataItems = new Array(); //定义一数组
                            dataItems = d.obj.split(";"); //字符分割
                            //W.document.location.reload(true);
                            var option1='';
                            for (i=0;i<dataItems.length ;i++ ) {
                                var dataitem = new Array(); //定义一数组
                                dataitem = dataItems[i].split(","); //字符分割

                                if(dataitem[1] == '${emkOrderCompanyTopPage.cusRelationer}'){
                                    option1 += '<option value='+dataitem[1]+' selected>'+dataitem[1]+'</option>';
                                }else{
                                    option1 += '<option value='+dataitem[1]+'>'+dataitem[1]+'</option>';
                                }
                            }
                            $("#cusRelationer").append(option1);
                        }
                    }
                });
            });

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
            $.getJSON('ymkCustomController.do?findCustomList', function (data) {
                control.empty();//清空下拉框
                if(type == 0){
                    control.append("<option value=''>请选择客户</option>");
                }

                $.each(data.obj, function (i, item) {
                    control.append("<option value='" + item.fname + "'>&nbsp;" + item.fname + "</option>");
                });

                if(type ==1){
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

        function setReadOnly(obj) {
            obj.find(":text,textarea").attr("disabled",true);
            obj.find(":checkbox,:radio,select").attr("disabled",true);
            //obj.find("a").hide();
            obj.find(".date").removeClass("date");
            //obj.find(".required").removeClass("required");
        }
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderCompanyTopController.do?doAdd" >
	<input id="id" name="id" type="hidden" value="${emkOrderCompanyTopPage.id }"/>
	<input id="orderId" name="orderId" type="text" value="${emkOrderCompanyTopPage.orderId eq null ? param.orderId:emkOrderCompanyTopPage.orderId }"/>
	<strong>&nbsp;客户信息</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;" />
	<table id="companyBaseInfoId" style="width: 100%;" cellpadding="0" cellspacing="0" class="formtable" border="0">
		<tr>
			<td align="right" class="value" style="color:red">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="cusName" name="cusName" datatype="*"  >
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					客户地址:
				</label>
			</td>
			<td class="value">
				<input id="cusAddress" name="cusAddress" value="${emkOrderCompanyTopPage.cusAddress }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户地址</label>
			</td>

			<td align="right" class="value">
				<label class="Validform_label">
					客户联系人:
				</label>
			</td>
			<td class="value">
				<select id="cusRelationer" name="cusRelationer" style="width:157px;">
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户联系人</label>
			</td>

		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					联系人电话:
				</label>
			</td>
			<td class="value">
				<input id="cusTelphone" name="cusTelphone" value="${emkOrderCompanyTopPage.cusTelphone }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人电话</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					办公电话:
				</label>
			</td>
			<td class="value">
				<input id="workTel" name="workTel" type="text" value="${emkOrderCompanyTopPage.workTel }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公电话</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					办公传真:
				</label>
			</td>
			<td class="value">
				<input id="workFax" name="workFax" type="text" value="${emkOrderCompanyTopPage.workFax }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公传真</label>
			</td>
		</tr>

		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					其他说明:
				</label>
			</td>
			<td class="value" colspan="5"><textarea id="other"  style="width:70%;height:50px" class="inputxt" rows="3" name="other">${emkOrderCompanyTopPage.other}</textarea>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">其他说明</label>
			</td>
		</tr>
	</table>
	<strong>&nbsp;我司信息</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;" />
	<table id="myCompanyBaseInfoId" style="width: 100%;" cellpadding="0" cellspacing="0" class="formtable" border="0">
		<tr>
			<td align="right" class="value" style="color:red">
				<label class="Validform_label">
					公司抬头:
				</label>
			</td>
			<td class="value">
				<input id="comTop" name="comTop" type="text" value="${emkOrderCompanyTopPage.comTop eq null ? companyEntity.znName:emkOrderCompanyTopPage.comTop}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">公司抬头</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					公司地址:
				</label>
			</td>
			<td class="value">
				<input id="comAddress" name="comAddress" value="${emkOrderCompanyTopPage.comAddress eq null ? companyEntity.znAddress:emkOrderCompanyTopPage.comAddress}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">公司地址</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value">
				<input id="comDept" name="comDept" type="text" value="${emkOrderCompanyTopPage.comDept eq null ? CUR_USER.currentDepart.departname:emkOrderCompanyTopPage.comDept}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>

		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value">
				<input id="comYwer" name="comYwer" readonly type="text" value="${(emkOrderCompanyTopPage.comYwer eq null || emkOrderCompanyTopPage.comYwer  eq '') ? CUR_USER.realName:emkOrderCompanyTopPage.comYwer}" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					联系人电话:
				</label>
			</td>
			<td class="value">
				<input id="comTelphone" name="comTelphone" type="text" value="${emkOrderCompanyTopPage.comTelphone }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人电话</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					办公电话:
				</label>
			</td>
			<td class="value">
				<input id="comWorkTel" name="comWorkTel" type="text" style="width: 150px" value="${emkOrderCompanyTopPage.comWorkTel eq null ? companyEntity.workTel:emkOrderCompanyTopPage.comWorkTel}" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公电话</label>
			</td>
		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					办公传真:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="comFax" name="comFax" type="text" style="width: 150px" value="${emkOrderCompanyTopPage.comFax eq null ? companyEntity.fax:emkOrderCompanyTopPage.comFax}" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">办公传真</label>
			</td>
		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="5"><textarea id="remark" style="width:70%;height:50px" class="inputxt" rows="3" name="remark">${emkOrderCompanyTopPage.other}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>

		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/bill/ordercompanytop/emkOrderCompanyTop.js"></script>
