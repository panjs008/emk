<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<head>
	<meta charset="utf-8" />
	<title>易贸客</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />

	<link href="metro/assets/jquery-ui/jquery-ui-1.10.1.custom.min.css" rel="stylesheet">

	<link href="metro/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="metro/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link href="metro/assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
	<link href="metro/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link href="metro/css/style.css" rel="stylesheet" />
	<link href="metro/css/style-responsive.css" rel="stylesheet" />
	<link href="metro/css/style-default.css" rel="stylesheet" id="style_color" />
	<link href="metro/css/timeline-component.css" rel="stylesheet"  type="text/css" />


	<link href="metro/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="metro/assets/uniform/css/uniform.default.css" />

	<link rel="stylesheet" type="text/css" href="metro/assets/chosen-bootstrap/chosen/chosen.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/jquery-tags-input/jquery.tagsinput.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/clockface/css/clockface.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/bootstrap-datepicker/css/datepicker.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/bootstrap-timepicker/compiled/timepicker.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/bootstrap-colorpicker/css/colorpicker.css" />
	<link rel="stylesheet" href="metro/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
	<link rel="stylesheet" type="text/css" href="metro/assets/bootstrap-daterangepicker/daterangepicker.css" />
	<link rel="stylesheet" href="metro/ui/1.10.3/themes/smoothness/jquery-ui.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
<div class="row-fluid">
	<div class="span12">
		<!-- BEGIN INLINE TABS PORTLET-->
		<div class="widget green">
			<div class="widget-body">
				<div class="bs-docs-example">
					<ul class="nav nav-tabs" id="myTab">
						<li <c:if test="${param.showflag eq null ||  param.showflag eq '' || param.showflag eq 1}">class="active" </c:if>><input type="hidden" id="showflag" value="${param.showflag}"/><a data-toggle="tab" href="#home" onclick="setShow(1)">客户概况</a></li>
						<li <c:if test="${param.showflag eq 2}">class="active" </c:if>><a data-toggle="tab" id="btn2" href="#profile1" onclick="setShow(2)">联系人</a></li>
						<li <c:if test="${param.showflag eq 3}">class="active" </c:if>><a data-toggle="tab" id="btn3" href="#profile2" onclick="setShow(3)">提醒</a></li>
						<li <c:if test="${param.showflag eq 4}">class="active" </c:if>><a data-toggle="tab" id="btn4" href="#profile3" onclick="setShow(4)">跟进</a></li>
						<li <c:if test="${param.showflag eq 5}">class="active" </c:if>><a data-toggle="tab" id="btn5" href="#profile4" onclick="setShow(5)">银行账户</a></li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div id="home" <c:if test="${param.showflag eq null ||  param.showflag eq ''  || param.showflag eq 1}">class="tab-pane fade in active" </c:if> <c:if test="${param.showflag ne null && param.showflag ne 1}">class="tab-pane fade" </c:if>>
							<div class="row-fluid">
								<div class="span6">
									<!-- BEGIN TAB PORTLET-->
									<div class="widget blue">
										<div class="widget-title">
											<h4><i class="icon-reorder"></i>基本信息</h4>
										</div>
										<div class="widget-body">
											<div class="widget-body">

												<div class="alert alert-success">
													客户编号：${ymkCustomPage.cusNum}<br/><br/>
													客户名称：${ymkCustomPage.cusName}<br/><br/>
													客户类型：${customType}<br/><br/>
													客户来源：${ymkCustomPage.cusFrom}<br/><br/>
													客户地址：${ymkCustomPage.address}<br/><br/>
													办公电话：${ymkCustomPage.telphone}<br/><br/>
													办公传真：${ymkCustomPage.fax}<br/><br/>
													公司网址：${ymkCustomPage.companyNet}<br/><br/>
												</div>

											</div>
										</div>
									</div>
									<c:forEach var="list" items="${contactEntities}" varStatus="status">
										<div class="widget blue">
											<div class="widget-title">
												<h4><i class="icon-reorder"></i>联系人${status.index+1}</h4>
											</div>
											<div class="widget-body">
												<div class="widget-body">
													<div class="alert">
														姓名：${list.userName}<br/><br/>
														性别：${list.sex eq 0 ?  '男':'女'}<br/><br/>
														职务：${list.position}<br/><br/>
														E-mail：${list.email}<br/><br/>
														电话：${list.mobile}<br/><br/>
														手机：${list.telphone}<br/><br/>
														<%--传真：${list.userName}<br/><br/>--%>
														生日：${list.brithDay}<br/><br/>
														备注：${list.remark}<br/><br/>


													</div>
													<!--<div class="alert">
                                                        <button data-dismiss="alert" class="close">×</button>
                                                        <strong>Warning!</strong> Best check yo self, you're not looking too good.
                                                    </div>
                                                    <div class="alert alert-success">
                                                        <button data-dismiss="alert" class="close">×</button>
                                                        <strong>Success!</strong> You successfully read this important message.
                                                    </div>
                                                    <div class="alert alert-info">
                                                        <button data-dismiss="alert" class="close">×</button>
                                                        <strong>Info!</strong> Heads up! This alert needs your attention.
                                                    </div>
                                                    <div class="alert alert-error">
                                                        <button data-dismiss="alert" class="close">×</button>
                                                        <strong>Error!</strong> Change a few things. Please submit again.
                                                    </div>-->
												</div>
											</div>
										</div>
									</c:forEach>

									<!-- END TAB PORTLET-->
								</div>
								<div class="span6">
									<!-- BEGIN ACCORDION PORTLET-->
									<div class="widget blue">
										<div class="widget-title">
											<h4><i class="icon-reorder"></i>提醒</h4>
										</div>
										<div class="widget-body">
											<div class="accordion" id="accordion0">
												<c:if test="${alert.create_name ne null}">
												<ul class="metro_tmtimeline">
													<li class="blue">
														<div class="metro_tmtime" datetime="2018-01-10 18:30">
															<span class="date" >${alert.create_date1}</span>
															<span class="time" style="font-size: 28px">${alert.create_date2}&nbsp;&nbsp;</span>
														</div>
														<div class="metro_tmicon">
															<i class="icon-bell"></i>
														</div>
														<div class="metro_tmlabel">
															<!--<h2>跟进1</h2>-->
															<font style="font-size: 14px">提醒：${alert.create_name}</font>
															<h2></h2>
															<p><font style="font-size: 14px">${alert.alert_content}</font></p>

														</div>
													</li>
												</ul>
												</c:if>
											</div>
										</div>
									</div>
									<div class="widget blue">
										<div class="widget-title">
											<h4><i class="icon-reorder"></i> 跟进情况</h4>

										</div>



										<div class="widget-body">
											<div class="accordion" id="accordion1">
												<ul class="metro_tmtimeline">
													<c:forEach var="list" items="${traceList}" varStatus="status">
														<c:if test="${status.index eq 0}">
															<li class="blue">
																<div class="metro_tmtime" datetime="">
																	<span class="date" >${list.create_date1}</span>
																	<span class="time" style="font-size: 28px">${list.create_date2}&nbsp;&nbsp;</span>
																</div>
																<div class="metro_tmicon">
																	<i class="icon-bell"></i>
																</div>
																<div class="metro_tmlabel">
																	<font style="font-size: 14px">跟进人：${list.create_name}</font>
																	<h2></h2>
																	<p><font style="font-size: 14px">${list.trace_content}</font></p>
																</div>
															</li>
														</c:if>
														<c:if test="${status.index > 0}">
															<li></li>
															<li></li>
															<li></li>

															<li class="gray">
																<div class="metro_tmtime" datetime="">
																	<span class="date" >${list.create_date1}</span>
																	<span class="time" style="font-size: 28px">${list.create_date2}&nbsp;&nbsp;</span>
																</div>
																<div class="metro_tmicon">
																	<i class="icon-bell"></i>
																</div>
																<div class="metro_tmlabel">
																	<font style="font-size: 14px">跟进人：${list.create_name}</font>
																	<h2></h2>
																	<p><font style="font-size: 14px">${list.trace_content}</font></p>
																</div>
															</li>
														</c:if>

													</c:forEach>

												</ul>
											</div>
										</div>
										<!-- END ACCORDION PORTLET-->
									</div>
								</div>

							</div>
						</div>
						<div id="profile1" <c:if test="${param.showflag eq 2}">class="tab-pane fade in active" </c:if><c:if test="${param.showflag ne 2}">class="tab-pane fade" </c:if>>
							<iframe id="printfrm2" src="ymkCustomContactController.do?list&customId=${ymkCustomPage.id}" width="100%" height="425"
									style="border: 0px; line-height: 21px; background: #fff;" frameborder="no" border="0" scrolling="yes">
							</iframe>
						</div>


						<div id="profile2"  <c:if test="${param.showflag eq 3}">class="tab-pane fade in active" </c:if><c:if test="${param.showflag ne 3}">class="tab-pane fade" </c:if>>
							<div align="right"><button class="btn btn-primary" type="button" onclick="goAlertAdd('${ymkCustomPage.id}');" >新增提醒</button></div>
							<ul class="metro_tmtimeline">
								<c:forEach var="list" items="${alertList}" varStatus="status">
									<li class="blue">
										<div class="metro_tmtime" datetime="">
											<span class="date" >${list.create_date1}</span>
											<span class="time" style="font-size: 28px">${list.create_date2}&nbsp;&nbsp;</span>
										</div>
										<div class="metro_tmicon">
											<i class="icon-bell"></i>
										</div>
										<div class="metro_tmlabel">
											<font style="font-size: 14px">提醒：${list.create_name}</font>
											<h2></h2>
											<p><font style="font-size: 14px">${list.alert_content}</font></p>
											<h2></h2>
											<c:if test="${list.state eq 0}">
											<button class="btn" type="button" onclick="doOver('${list.id}','${ymkCustomPage.id}');">完成</button>
											<button class="btn" type="button" onclick="goAlertUpdate('${list.id}','${ymkCustomPage.id}');">修改</button>
											</c:if>
											<button class="btn" type="button" onclick="doDel('${list.id}','${ymkCustomPage.id}');">删除</button>
										</div>
									</li>
								</c:forEach>
						</div>
						<div id="profile3" <c:if test="${param.showflag eq 4}">class="tab-pane fade in active" </c:if><c:if test="${param.showflag ne 4}">class="tab-pane fade" </c:if>>
							<iframe id="printfrm3" src="ymkCustomTraceController.do?list&customId=${ymkCustomPage.id}" width="100%" height="425"
									style="border: 0px; line-height: 21px; background: #fff;" frameborder="no" border="0" scrolling="yes">
							</iframe>
						</div>

						<div id="profile4" <c:if test="${param.showflag eq 5}">class="tab-pane fade in active" </c:if><c:if test="${param.showflag ne 5}">class="tab-pane fade" </c:if>>
							<iframe id="printfrm4" src="ymkCustomBankController.do?list&customId=${ymkCustomPage.id}" width="100%" height="425"
									style="border: 0px; line-height: 21px; background: #fff;" frameborder="no" border="0" scrolling="yes">
							</iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END INLINE TABS PORTLET-->
	</div>
</div>
<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->


<script src="metro/js/jquery-1.8.2.min.js"></script>
<script src="metro/js/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="metro/assets/ckeditor/ckeditor.js"></script>
<script src="metro/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap/js/bootstrap-fileupload.js"></script>
<script src="metro/js/jquery.blockui.js"></script>

<script src="metro/ui/1.10.3/jquery-ui.js"></script>
<script src="metro/js/jQuery.dualListBox-1.3.js" language="javascript" type="text/javascript"></script>


<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="metro/js/excanvas.js"></script>
<script src="metro/js/respond.js"></script>
<![endif]-->
<script type="text/javascript" src="metro/assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
<script type="text/javascript" src="metro/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="metro/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script type="text/javascript" src="metro/assets/clockface/js/clockface.js"></script>
<script type="text/javascript" src="metro/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-daterangepicker/date.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="metro/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
<script src="metro/assets/fancybox/source/jquery.fancybox.pack.js"></script>
<script src="metro/js/jquery.scrollTo.min.js"></script>
<script src="metro/assets/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>


<!--common script for all pages-->
<script src="metro/js/common-scripts.js"></script>

<!--script for this page-->
<script src="metro/js/form-component.js"></script>
<!-- END JAVASCRIPTS -->

<script language="javascript" type="text/javascript">

    $(function() {

        $.configureBoxes();
        <%--<c:if test="${param.showflag eq 2}">
        $("#btn2").click();
        </c:if>
        <c:if test="${param.showflag eq 3}">
        $("#btn3").click();
        </c:if>
        <c:if test="${param.showflag eq 4}">
        $("#btn4").click();
        </c:if>
        <c:if test="${param.showflag eq 5}">
        $("#btn5").click();
        </c:if>--%>

    });

    function setShow(flag){
        $("#showflag").val(flag)
	}

    function goAlertAdd(customId){
        window.parent.goAlertAdd(customId);
	}
    function goAlertUpdate(alertId,cid){
        window.parent.goAlertUpdate(alertId,cid);
    }

    function doOver(alertId,cid){
        window.parent.doOver(alertId,cid);
    }

    function doDel(alertId,cid){
        window.parent.doDel(alertId,cid);
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
