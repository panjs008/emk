<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>报修单表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(
				function() {
					<%--<c:if test="${repairPorcess.taskDefinitionKey ne ''}">
						setReadOnly($("#baseInfoId"));
					</c:if>
					$("#jjd").val("${uRepairPage.jjd}")--%>
				});
		function downfile(url){
			alert("${webRoot}"+url);
			window.open("${webRoot}"+url);
		}
		function setReadOnly(obj) {
			obj.find(":text,textarea").attr("disabled",true);
			obj.find(":checkbox,:radio,select").attr("disabled",true);
			//obj.find("a").hide();
			obj.find(".date").removeClass("date");
			//obj.find(".required").removeClass("required");
		}

		var myloader = function(param,success,error){
			var q = param.q || '';
			if (q.length <= 1){return false}
			$.ajax({
				url: 'ymkCustomController.do?findCustomList',
				dataType: 'json',
				data: {
					q: q,
					maxRows: 20
				},
				success: function(data){
					var items = $.map(data, function(item,index){
						return {
							id: item.id,
							name: item.name
						};
					});
					success(items);
				},
				error: function(){
					///alert("1234");
					//error.apply(this, arguments);
				}
			});
		}

	</script>
</head>
<body>
<table width="100%">
	<tr>
		<td width="30%" valign="top">
			<table id="baseInfoId" style="width: 100%;margin-bottom:4px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							报修单号:
						</label>
					</td>
					<td class="value" width="70%">
							${uRepairPage.repairNum}
					</td>
				</tr>
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							报修方式:
						</label>
					</td>
					<td class="value" width="70%">
						<c:if test="${uRepairPage.bxType eq 'telphone'}">电话</c:if>
						<c:if test="${uRepairPage.bxType eq 'weixin'}">微信</c:if>
						<c:if test="${uRepairPage.bxType eq 'saomiao'}">扫描</c:if>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							报修人:
						</label>
					</td>
					<td class="value" width="70%">
							${uRepairPage.userName }
					</td>
					</tr>
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							报修时间:
						</label>
					</td>
					<td class="value" width="70%">
							${uRepairPage.applyDate }
					</td>
				</tr>
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							紧急度:
						</label>
					</td>
					<td class="value" width="70%">
						&nbsp;<c:if test="${uRepairPage.jjd eq '0'}">一般</c:if>
						<c:if test="${uRepairPage.jjd eq '1'}">紧急</c:if>
						<c:if test="${uRepairPage.jjd eq '2'}">非常紧急</c:if>
					</td>
					</tr>


				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							设备名称:
						</label>
					</td>
					<td class="value" width="70%">
							${uRepairPage.proZnName eq null  ? "&nbsp;":uRepairPage.proZnName}

					</td>
					</tr>
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							设备类型:
						</label>
					</td>
					<td class="value" width="70%">
							${uRepairPage.sblx eq null  ? "&nbsp;":uRepairPage.sblx}

					</td>
					</tr>
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							品牌:
						</label>
					</td>
					<td class="value" width="70%">
							${uRepairPage.pp eq null  ? "&nbsp;":uRepairPage.pp}

					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							型号:
						</label>
					</td>
					<td class="value">
								${uRepairPage.xh eq null  ? "&nbsp;":uRepairPage.xh}
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							故障描述:
						</label>
					</td>
					<td class="value">${uRepairPage.fault eq null  ? "&nbsp;":uRepairPage.fault}</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							附件:
						</label>
					</td>
					<td class="value">
						&nbsp;
						<c:forEach items="${attchEntities}"  var="attch" varStatus="status">
							<a href="javascript:window.open('${webRoot}/${attch.filePath}')">[${attch.oldFileName}]</a>
						</c:forEach>
					</td>
				</tr>

			</table>
		</td>
		<td width="70%" align="left" valign="top">
			<div id="timeDiv" title="流程时间轴" style="width:100%;height:410px;overflow:hidden" >
				<iframe scrolling="no" id="processFrm" frameborder="0"  src="uRepairController.do?goTime&id=${param.id}" width="100%" height="100%"></iframe>
			</div>
		</td>

	</tr>
</table>


<t:formvalid formid="baseformobj" dialog="true" usePlugin="password" layout="table" action="uRepairController.do?doUpdate"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${uRepairPage.id }"/>
	<table id="adviceTb" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey eq 'checkTask'}">
			<tr>
				<td align="right" width="15%">
					<label class="Validform_label">
						是否自修:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="isFinish" type="radio" onclick="setchooseUser(0);" datatype="*" <c:if test="${uRepairPage.isFinish eq '0'}">checked="true"</c:if> value="0">
					是
					<input name="isFinish" type="radio" onclick="setchooseUser(1);"  datatype="*"  <c:if test="${uRepairPage.isFinish eq '1'}">checked="true"</c:if> value="1">
					否
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否自修</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey eq 'serviceTask'}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						保修类别:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="repairType" type="radio" onclick="setchooseService(0)" datatype="*" <c:if test="${uRepairPage.repairType eq '0'}">checked="true"</c:if> value="0">
					保外
					<input name="repairType" type="radio" onclick="setchooseService(1)"  datatype="*"  <c:if test="${uRepairPage.repairType eq '1'}">checked="true"</c:if> value="1">
					保内
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">保修类别</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey eq 'systemToService'}">
			<tr>
				<td align="right">
					<label class="Validform_label">
						处理类型:
					</label>
				</td>
				<td class="value" colspan="3">
					<input name="csType" type="radio" onclick="setchooseServiceYg(0)" datatype="*" <c:if test="${uRepairPage.csType eq '0'}">checked="true"</c:if> value="0">
					委托
					<input name="csType" type="radio" onclick="setchooseServiceYg(1)" datatype="*"  <c:if test="${uRepairPage.csType eq '1'}">checked="true"</c:if> value="1">
					自有
					<input name="csType" type="radio" onclick="setchooseServiceYg(2)" datatype="*"  <c:if test="${uRepairPage.csType eq '2'}">checked="true"</c:if> value="2">
					原厂
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">处理类型</label>
				</td>
			</tr>
		</c:if>
		<c:if test="${processFinish eq '0' && repairPorcess.taskDefinitionKey ne 'normalRepairTask'  && repairPorcess.taskDefinitionKey ne 'saveRecord' && repairPorcess.taskDefinitionKey ne 'applyTask' && repairPorcess.taskDefinitionKey ne 'belongService'  && repairPorcess.taskDefinitionKey ne 'homeService'}">
			<tr id="chooseUser">
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						指定下一步操作人:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="realName" name="realName" datatype="*" type="text" readonly style="width: 300px" class="inputxt" >
					<input name="userName"   type="hidden"  id="userName" type="text"  />
					<c:choose>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'checkTask'}">
							<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSignService" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择服务商" textname="realName" isclear="true" isInit="true"></t:choose>

						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'zxRepair'}">
							<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userServiceYg" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择服务商员工" textname="realName" isclear="true" isInit="true"></t:choose>
						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'serviceTask'}">
							<span id="spanId1"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userProjectService" name="userList0" width="700px" height="500px"
														  icon="icon-search" title="选择中标商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
							<span id="spanId2" style="display: none"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userService" name="userList0" width="700px" height="500px"
																				icon="icon-search" title="选择服务商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'systemToService'}">
							<span id="spanId1"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userService" name="userList0" width="700px" height="500px"
														  icon="icon-search" title="选择本地服务商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
							<span id="spanId2" style="display: none"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userServiceYg" name="userList0" width="700px" height="500px"
																				icon="icon-search" title="选择自有工程师" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
							<span id="spanId3" style="display: none"><t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSignService" name="userList0" width="700px" height="500px"
																				icon="icon-search" title="选择厂商" textname="realName" isclear="true" isInit="true"></t:choose>
								</span>
						</c:when>
						<c:when test="${repairPorcess.taskDefinitionKey eq 'localService' || repairPorcess.taskDefinitionKey eq 'tjService'}">
							<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userServiceYg" name="userList0" width="700px" height="500px"
									   icon="icon-search" title="选择员工" textname="realName" isclear="true" isInit="true"></t:choose>
						</c:when>

					</c:choose>

				</td>
			</tr>
		</c:if>
		<c:if test="${processFinish eq '0'}">
			<tr>
				<td align="right" width="150px" valign="middle">
					<label class="Validform_label">
						<c:if test="${repairPorcess.taskDefinitionKey eq 'zxRepair' || repairPorcess.taskDefinitionKey eq 'normalRepairTask'}">维修情况:</c:if>
						<c:if test="${repairPorcess.taskDefinitionKey ne 'zxRepair' && repairPorcess.taskDefinitionKey ne 'normalRepairTask'}">处理意见:</c:if>

					</label>
				</td>
				<td class="value" colspan="3"><textarea datatype="*" id="adviceContent" style="width:95%;height:80px" class="inputxt" rows="5" name="adviceContent"></textarea>
				</td>
			</tr>
		</c:if>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/process/repair/uRepair.js"></script>
