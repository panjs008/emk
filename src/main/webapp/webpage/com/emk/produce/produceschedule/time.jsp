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

<div id="history" style="margin-top: 5px;margin-left:50px;">

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
							<div class="histt"><a style="font-size: 12px;"><c:if test="${task.TASK_DEF_KEY_ eq 'produceTask'}">【${task.workname}】发起订单生产单请单，订单号：${emkProduceSchedule.orderNo}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'checkTask'}">审核人【${emkProduceSchedule.leader}】，处理意见：${emkProduceSchedule.leadAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'ssyTask'}">处理人【${emkProduceSchedule.ssSampleUser}】，处理意见：${emkProduceSchedule.ssSampleAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'cqyTask'}">处理人【${emkProduceSchedule.cqSampleUser}】，处理意见：${emkProduceSchedule.cqSampleAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'syTask'}">处理人【${emkProduceSchedule.colorUser}】，处理意见：${emkProduceSchedule.colorAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'testTask'}">处理人【${emkProduceSchedule.testUser}】，处理意见：${emkProduceSchedule.testUserAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'meetingTask'}">处理人【${emkProduceSchedule.cqSampleUser}】，处理意见：${emkProduceSchedule.cqSampleAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'ylTask'}">处理人【${emkProduceSchedule.ylflcgUserName}】，处理意见：${emkProduceSchedule.ylflcgAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'ranTask'}">处理人【${emkProduceSchedule.ranUserName}】，处理意见：${emkProduceSchedule.ranAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'caiTask'}">处理人【${emkProduceSchedule.caiUserName}】，处理意见：${emkProduceSchedule.caiUserAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'fengTask'}">处理人【${emkProduceSchedule.fengUserName}】，处理意见：${emkProduceSchedule.fengUserAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'zqjcTask'}">处理人【${emkProduceSchedule.zqjcUserName}】，处理意见：${emkProduceSchedule.zqjcAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'btTask'}">处理人【${emkProduceSchedule.biaoUserName}】，处理意见：${emkProduceSchedule.biaoAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'ztTask'}">处理人【${emkProduceSchedule.zhengtUserName}】，处理意见：${emkProduceSchedule.zhengtAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'cyTask'}">处理人【${emkProduceSchedule.chuangUserName}】，处理意见：${emkProduceSchedule.chuangAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'bzTask'}">处理人【${emkProduceSchedule.boxUserName}】，处理意见：${emkProduceSchedule.boxAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'outTask'}">处理人【${emkProduceSchedule.outUserName}】，处理意见：${emkProduceSchedule.outAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'wqTask'}">处理人【${emkProduceSchedule.weiUserName}】，处理意见：${emkProduceSchedule.weiAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'skTask'}">处理人【${emkProduceSchedule.shouUserName}】，处理意见：${emkProduceSchedule.shouAdvice}</c:if>

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