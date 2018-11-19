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

<div id="history" style="margin-top: 5px;margin-left:-50px;">

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
							<div class="histt"><a style="font-size: 12px;"><c:if test="${task.TASK_DEF_KEY_ eq 'khxpTask'}">【${task.workname}】发起工单，工单号：${emkWorkOrderPage.workNo}，询盘单号：${emkWorkOrderPage.askNo},处理意见：${emkWorkOrderPage.askWorkAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'sampleTask'}">处理人【${task.workname}】，样品通知单号：${emkWorkOrderPage.sampleNum}，处理意见：${emkWorkOrderPage.sampleAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'sampleCheckTask'}">处理人【${task.workname}】，指派给【${emkWorkOrderPage.orderUser}】，样品质检单号：${emkWorkOrderPage.sampleCheckNo}，处理意见：${emkWorkOrderPage.sampleCheckAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'billTask'}">处理人【${task.workname}】，指派给【${emkWorkOrderPage.htUser}】，订单单号：${emkWorkOrderPage.orderNo}，处理内容：${emkWorkOrderPage.orderAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'htTask'}">处理人【${task.workname}】，指派给【${emkWorkOrderPage.sampleCheckUser}】，打样单号：${emkWorkOrderPage.sampleNum}，处理意见：${emkWorkOrderPage.sampleAdvice}</c:if>

								<c:if test="${task.TASK_DEF_KEY_ eq 'checkTask'}">审核人【${emkInStorage.leader}】，指派给【${emkInStorage.financer}】,处理意见：${emkInStorage.leadAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'cwTask'}">审核人【${emkInStorage.financer}】,处理意见：${emkInStorage.financeAdvice}</c:if>
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