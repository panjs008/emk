<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>运输费用申请单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkYsPayController.do?doUpdate" tiptype="1">
	<input id="ysPayId" name="ysPayId" type="hidden" value="${emkYsPayPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%">
				<label class="Validform_label">
					运输单位代码:
				</label>
			</td>
			<td class="value">
				<input id="ysdwCode" name="ysdwCode" value="${emkYsPayPage.ysdwCode }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输单位代码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					运输单位名称:
				</label>
			</td>
			<td class="value">
				<input id="ysdwName" name="ysdwName" value="${emkYsPayPage.ysdwName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输单位名称</label>
			</td>
		</tr>

	</table>
	<t:tabs id="detailDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkYsPayController.do?detailMxList&ysPayId=${emkYsPayPage.id}" icon="icon-search" title="明细" id="detail"></t:tab>
	</t:tabs>
	<table style="width: 100%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<input id="bz" name="bz" type="text" value="${emkYsPayPage.bz }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总金额:
				</label>
			</td>
			<td class="value">
				<input id="sumMoney" name="sumMoney" value="${emkYsPayPage.sumMoney }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					收款单位名称:
				</label>
			</td>
			<td class="value">
				<input id="skdwName" name="skdwName" value="${emkYsPayPage.skdwName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收款单位名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					账号:
				</label>
			</td>
			<td class="value">
				<input id="account" name="account" value="${emkYsPayPage.account }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="relationer" name="relationer" value="${emkYsPayPage.relationer }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					电话:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" value="${emkYsPayPage.telphone }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/caiwu/yspay/emkYsPay.js"></script>
