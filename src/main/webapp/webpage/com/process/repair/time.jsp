<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>时间轴</title>
	<link rel="stylesheet" type="text/css" href="plug-in/jquery-time-11/css/history.css">
	<script type="text/javascript" src="plug-in/jquery-time-11/js/jquery.js"></script>
	<script type="text/javascript" src="plug-in/jquery-time-11/js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="plug-in/jquery-time-11/js/jquery.easing.js"></script>
	<script type="text/javascript" src="plug-in/jquery-time-11/js/history.js"></script>


</head>

<body>

<div id="arrow">
	<ul>
		<li class="arrowup"></li>
		<li class="arrowdown"></li>
	</ul>
</div>

<div id="history" style="margin-top: 5px;margin-left:-250px;">

	<div id="content">

		<ul class="list">
			<c:forEach items="${taskList}" var="task" varStatus="status">
				<li>
					<div class="liwrap">
						<div class="lileft">
							<div class="date">
								<span class="year">${task.startTime}</span>
							</div>
						</div>
						<div class="point"><b></b></div>
						<div class="liright">
							<div class="histt"><a style="font-size: 14px;">${task.NAME_}</a></div>
							<div class="histt"><a style="font-size: 12px;"><c:if test="${task.TASK_DEF_KEY_ eq 'applyTask'}">【${task.workname}】发起工单，故障描述：${uRepairPage.fault}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'checkTask' && task.TASK_DEF_KEY_ ne repairPorcess.taskDefinitionKey }">受理人【${uRepairPage.checkUserName}】，<c:if test="${uRepairPage.isFinish eq '0'}">自行维修并指派给【${uRepairPage.recevieUserName}】</c:if>
									<c:if test="${uRepairPage.isFinish eq '1'}">无法自行维修并指派给【${uRepairPage.serviceUserName}】</c:if>，处理意见：${uRepairPage.checkContent}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'zxRepair'}">受理人【${uRepairPage.recevieUserName}】，指派给【${uRepairPage.repairUserName}】,维修情况：${uRepairPage.recevieRemark}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'saveRecord'}">受理人【${uRepairPage.repairUserName}】，维修情况：${uRepairPage.kaoheRemark}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'serviceTask'}">受理人【${task.workname}】，指派给${uRepairPage.repairType eq '1' ? '中标商':'服务商'}【${uRepairPage.cusName}】,处理意见：${uRepairPage.serviceContent}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'servicePd'}">受理人【${task.workname}】，指派给【${uRepairPage.recevieUserName}】,处理意见：${uRepairPage.cusContent}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'systemToService'}">受理人【${task.workname}】，指派给【${uRepairPage.recevieUserName}】,处理意见：${uRepairPage.cusContent}</c:if>

								<c:if test="${task.TASK_DEF_KEY_ eq 'localService' || task.TASK_DEF_KEY_ eq 'tjService' || task.TASK_DEF_KEY_ eq 'belongService'}">受理人【${task.workname}】，指派给【${uRepairPage.repairUserName}】,处理意见：${uRepairPage.recevieRemark}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'homeService'}">受理人【${task.workname}】，指派给【${uRepairPage.repairUserName}】,处理意见：${uRepairPage.localContent}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'normalRepairTask'}">受理人【${task.workname}】,维修情况：${uRepairPage.kaoheRemark}</c:if>

							</a></div>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>

	</div>

</div>


</body>

</html>