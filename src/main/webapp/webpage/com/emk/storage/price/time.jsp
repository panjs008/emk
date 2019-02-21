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
							<div class="histt"><a style="font-size: 12px;"><c:if test="${task.TASK_DEF_KEY_ eq 'instorageTask'}">【${task.workname}】发起报价单，报价单号：${emkPrice.pirceNo}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'checkTask'}">审核人【${emkPrice.leader}】，处理意见：${emkPrice.leadAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'jsTask'}">审核人【${emkPrice.jser}】,指派给【${emkPrice.cger}】，处理意见：${emkPrice.jsAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'cgTask'}">审核人【${emkPrice.cger}】,指派给【${emkPrice.cwer}】，处理意见：${emkPrice.cgAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'cwTask'}">审核人【${emkPrice.cwer}】,指派给【${emkPrice.jger}】，处理意见：${emkPrice.cwAdvice}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'usertask1'}">处理人【${emkPrice.jger}】，处理意见：${emkPrice.jgAdvice}</c:if>

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