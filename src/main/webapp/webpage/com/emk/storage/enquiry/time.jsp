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
			<%--<c:forEach items="${taskList}" var="task" varStatus="status">
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
							<div class="histt"><a style="font-size: 12px;"><c:if test="${task.TASK_DEF_KEY_ eq 'instorageTask'}">【${task.workname}】发起询盘申请单，询盘单号：${emkEnquiry.enquiryNo}</c:if>
								<c:if test="${task.TASK_DEF_KEY_ eq 'checkTask'}">审核人【${emkEnquiry.leader}】，处理意见：${emkEnquiry.leadAdvice}</c:if>
							</a></div>
						</div>
					</div>
				</li>
			</c:forEach>--%>
				<li>
					<div class="liwrap">
						<div class="lileft">
							<div class="date">
								<span class="year">${createDate}</span>
							</div>
						</div>
						<div class="point"><b></b></div>
						<div class="liright">
							<div class="histt"><a style="font-size: 14px;">询盘申请单</a></div>
							<div class="histt"><a style="font-size: 12px;">【${approvalEntity.createName}】发起询盘申请单，询盘单号：${approvalEntity.workNum}
							</a></div>
						</div>
					</div>
				</li>
				<c:forEach items="${approvalDetailEntityList}" var="task" varStatus="status">
					<c:if test="${task.bpmName ne '意向询盘单' }">
						<li>
							<div class="liwrap">
								<div class="lileft">
									<div class="date">
										<span class="year">${task.approveDate}</span>
									</div>
								</div>
								<div class="point"><b></b></div>
								<div class="liright">
									<div class="histt"><a style="font-size: 14px;">${task.bpmName}</a></div>
									<div class="histt"><a style="font-size: 12px;">
										审核人【${task.createName}】，处理意见：${task.approveAdvice}
										<%--<c:choose>
											<c:when test="${task.bpmNode eq 'ywbCheckTask'}">
												审核人【${task.createName}】，评估意见：${task.approveAdvice}，包装部意见：${task.approveAdvice2}
											</c:when>
											<c:when test="${task.bpmNode eq 'jsbCheckTask'}">
												审核人【${task.createName}】，技术部意见：${task.approveAdvice3}，染色部意见：${task.approveAdvice4}
												，缝制部意见：${task.approveAdvice5}，烫标整烫部意见：${task.approveAdvice6}
											</c:when>
											<c:when test="${task.bpmNode eq 'scbCheckTask'}">
												审核人【${task.createName}】，采购部意见：${task.approveAdvice7}，生产计划部意见：${task.approveAdvice8}，生产总负责人意见：${task.approveAdvice9}
											</c:when>
											<c:otherwise>
												审核人【${task.createName}】，处理意见：${task.approveAdvice}
											</c:otherwise>
										</c:choose>--%>

									</div>
								</div>
							</div>
						</li>
					</c:if>

				</c:forEach>
		</ul>

	</div>

</div>


</body>

</html>