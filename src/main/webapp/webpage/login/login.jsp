<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title>外贸跟单管理系统</title>
  <link rel="shortcut icon" href="images/favicon.ico">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="stylesheet" href="login/css/bootstrap.min.css">
    <link rel="stylesheet" href="login/css/animate.css">
    <link rel="stylesheet" href="login/css/style.css">
    <!-- Modernizr JS -->
    <script src="login/js/modernizr-2.6.2.min.js"></script>
    <script src="login/js/respond.min.js"></script>
    <script src="login/js/jquery.min.js"></script>
    <script src="login/js/bootstrap.min.js"></script>
    <script src="login/js/jquery.placeholder.min.js"></script>
    <script src="login/js/jquery.waypoints.min.js"></script>
    <script src="login/js/main.js"></script>
    <script type="text/javascript">
        $(function(){
            if(isMobile()){
                window.location.href = "loginController.do?andrew";
            }else{
                $("#containerId").css("display","");
            }
        });
    </script>
</head>
<body>
<div class="container" id="containerId" style="display: none">
  <%--  <div class="row">
        <div class="col-md-12 text-center">
            <ul class="menu">
                <li class="active"><a href="index.html">Style 1</a></li>
                <li><a href="index2.html">Style 2</a></li>
                <li><a href="index3.html">Style 3</a></li>
            </ul>
        </div>
    </div>--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <!-- Start Sign In Form -->
            <form action="#" class="fh5co-form animate-box" data-animate-effect="fadeIn">
                <h2 style="text-align: center">外贸跟单管理系统</h2>
                <div id="errMsgContiner" class="form-group">
                    <div class="alert alert-success" role="alert"> <div id="showErrMsg"></div></div>
                </div>
                <div class="form-group">
                    <label for="userName" class="sr-only">账号</label>
                    <input type="text" class="form-control" id="userName" value="" name="userName" placeholder="账号" autocomplete="off">
                </div>
                <%--<div class="form-group">
                    <label for="password2" class="sr-only">Password</label>
                    <input type="password" class="form-control" id="password2" placeholder="Password" autocomplete="off">
                </div>--%>
                <div class="form-group">
                    <label for="password" class="sr-only">密码</label>
                    <input type="password" class="form-control" name="password" value="" id="password"  placeholder="密码" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="randCode" class="sr-only">验证码</label>
                    <input type="text" class="form-control" id="randCode"  name="randCode" placeholder="请输入验证码" autocomplete="off"/>
                    <img id="randCodeImage" src="randCodeImage"  style="margin-top: -70px;margin-left: 200px;"/>
                </div>
                <div class="form-group">
                    <label for="remember"><input type="checkbox" id="remember">记住密码</label>
                </div>
                <div class="form-group">
                    <p>是否已经注册? <a href="${webRoot}/ymkCustomController.do?regCustom">注册</a> | <a href="forgot.html">忘记密码?</a></p>
                </div>
                <div class="form-group">
                    <input type="button" id="but_login" value="登录"  onclick="checkUser()"class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->

        </div>
    </div>

</div>
<%--
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
          <div class="center">
            <h1 id="id-text2" class="white">
              <i class="ace-icon fa fa-leaf green"></i>
                智慧维保云平台
            </h1>
          </div>
          <div class="space-6"></div>
          <div class="position-relative">
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <!--update-begin--Author:zhangliang  Date:20170628 for：TASK #2116 【性能问题】优化登录逻辑---------------------->
                <form id="loinForm" class="form-horizontal"    method="post">
                  <!--update-end--Author:zhangliang  Date:20170628 for：TASK #2116 【性能问题】优化登录逻辑---------------------->
                  <!-- add-begin--Author:zhoujf  Date:20170602 for:单点登录 -->
                  <input type="hidden" id="ReturnURL"  name="ReturnURL" value="${ReturnURL }"/>
                  <!-- add-end--Author:zhoujf  Date:20170602 for:单点登录 -->
                  <div class="widget-main">
                    <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner">
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <div id="showErrMsg"></div>
                    </div>
                    <h4 class="header blue lighter bigger">
                      <i class="ace-icon fa fa-coffee green"></i>
                      用户登录
                    </h4>
                    <div class="space-6"></div>
                    <label class="block clearfix">
								<span class="block input-icon input-icon-right">
								<!-- update-start--Author:yugwu  Date:20170901 for:TASK #2324 【改进】登录记住用户名不起作用---- -->
									<input type="text"  name="userName" iscookie="true" class="form-control" placeholder="请输入用户名"  id="userName" value="admin"/>
                                  <!-- update-end--Author:yugwu  Date:20170901 for:TASK #2324 【改进】登录记住用户名不起作用---- -->
									<i class="ace-icon fa fa-user"></i>
								</span>
                    </label>
                    <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" value="123456"/>
									<i class="ace-icon fa fa-lock"></i>
								</span>
                    </label>
                    <label class="block clearfix">
                      <div class="input-group">
                        <input type="text" style="width:150px" name="randCode" class="form-control" placeholder="请输入验证码"  id="randCode"/>
                        <span class="input-group-addon" style="padding: 0px;"><img id="randCodeImage" src="randCodeImage"  /></span>
                      </div>
                    </label>
                    <div class="space"></div>
                    <div class="clearfix">
                      <label class="inline">
                        <input type="checkbox" class="ace" id="on_off"  name="remember" value="yes"/>
                        <span class="lbl">记住用户名</span>
                      </label>
                      <button type="button" id="but_login"  onclick="checkUser()" class="width-35 pull-right btn btn-sm btn-primary">
                        <i class="ace-icon fa fa-key"></i>
                        <span class="bigger-110" >登录</span>
                      </button>
                    </div>
                    <div class="space-4"></div>

                  </div>
                  <div class="toolbar clearfix">
                    &lt;%&ndash;<div style="float: right">
                      <a href="#"  class="forgot-password-link">
                        语言
                        <i class="ace-icon fa fa-arrow-right"></i>
                        <t:dictSelect id="langCode" field="langCode" typeGroupCode="lang" hasLabel="false" extendJson="{style:'padding:2px; width:80px;'}" defaultVal="zh-cn"></t:dictSelect>
                      </a>
                    </div>&ndash;%&gt;
                        <div style="float: left;margin-left: 20px">
                            <a href="#"  class="forgot-password-link" onclick="openInit()">
                                <i class="ace-icon fa fa-arrow-right"></i>
                                注册服务商</a>
                        </div>
                  </div>
                </form>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
--%>



  <%--<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>--%>
  <%--<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>--%>

  <!-- add-end--Author:gengjiajia  Date:20160727 for:TASK #1217 【IE兼容】jeecg h+首页兼容性问题,不兼容的浏览器直接切换套shortcut风格 -->
  <script type="text/javascript">
      $(function(){
          optErrMsg();
      });
      $("#errMsgContiner").hide();
      function optErrMsg(){
          $("#showErrMsg").html('');
          $("#errMsgContiner").hide();
      }

      //输入验证码，回车登录
      $(document).keydown(function(e){
          if(e.keyCode == 13) {

              setTimeout("$('#but_login').click()","100");

          }
      });

      //验证用户信息
      function checkUser(){
          if(!validForm()){
              return false;
          }
          newLogin();
      }
      function isMobile() {
          var userAgentInfo = navigator.userAgent;
          var mobileAgents = [ "Android", "iPhone", "SymbianOS", "Windows Phone", "iPad","iPod"];
          var mobile_flag = false;
          //根据userAgent判断是否是手机
          for (var v = 0; v < mobileAgents.length; v++) {
              if (userAgentInfo.indexOf(mobileAgents[v]) > 0) {
                  mobile_flag = true;
                  break;
              }
          }

          var screen_width = window.screen.width;
          var screen_height = window.screen.height;

          //根据屏幕分辨率判断是否是手机
          if(screen_width < 500 && screen_height < 800){
              mobile_flag = true;
          }

          return mobile_flag;
      }
      //表单验证
      function validForm(){
          if($.trim($("#userName").val()).length==0){
              showErrorMsg("请输入用户名！！");
              return false;
          }

          if($.trim($("#password").val()).length==0){
              showErrorMsg("请输入密码！！");
              return false;
          }

          if($.trim($("#randCode").val()).length==0){
              showErrorMsg("请输入验证码！！");
              return false;
          }
          return true;
      }

      //登录处理函数
      function newLogin(orgId) {
          //setCookie();

          var actionurl="loginController.do?login";//提交路径
          var checkurl="loginController.do?checkuser";//验证路径

          var formData = new Object();
          var data=$(":input").each(function() {
              formData[this.name] =$("#"+this.name ).val();
          });
          formData['orgId'] = orgId ? orgId : "";
          //语言
          formData['langCode']=$("#langCode").val();
          formData['langCode'] = $("#langCode option:selected").val();
          $.ajax({
              async : false,
              cache : false,
              type : 'POST',
              url : checkurl,// 请求的action路径
              data : formData,
              error : function() {// 请求失败处理函数
              },
              success : function(data) {
                  var d = $.parseJSON(data);
                  if (d.success) {
                      if(isMobile()){
                          window.location.href = "uRepairController.do?mRepair";

                      }else{
                          window.location.href = actionurl;
                      }
                  } else {
                      showErrorMsg(d.msg);

                      if(d.msg === "用户名或密码错误" || d.msg === "验证码错误")
                          reloadRandCodeImage();

                  }
              }
          });
      }
      //登录提示消息显示
      function showErrorMsg(msg){
          $("#errMsgContiner").show();
          $("#showErrMsg").html(msg);
          window.setTimeout(optErrMsg,3000);
      }
      /**
       * 刷新验证码
       */
      $('#randCodeImage').click(function(){
          reloadRandCodeImage();
      });
      function reloadRandCodeImage() {
          var date = new Date();
          var img = document.getElementById("randCodeImage");
          img.src='randCodeImage?a=' + date.getTime();
      }

      function darkStyle(){
          $('body').attr('class', 'login-layout');
          $('#id-text2').attr('class', 'red');
          $('#id-company-text').attr('class', 'blue');
          e.preventDefault();
      }
      function lightStyle(){
          $('body').attr('class', 'login-layout light-login');
          $('#id-text2').attr('class', 'grey');
          $('#id-company-text').attr('class', 'blue');

          e.preventDefault();
      }
      function blurStyle(){
          $('body').attr('class', 'login-layout blur-login');
          $('#id-text2').attr('class', 'white');
          $('#id-company-text').attr('class', 'light-blue');

          e.preventDefault();
      }
      //设置cookie
      function setCookie()
      {

        /*  if ($('#on_off').attr("checked")) {

              $("input[iscookie='true']").each(function() {
                  $.cookie(this.name, $("#"+this.name).val(), "/",24);
                  $.cookie("COOKIE_NAME","true", "/",24);
              });
          } else {
              $("input[iscookie='true']").each(function() {
                  $.cookie(this.name,null);
                  $.cookie("COOKIE_NAME",null);
              });
          }*/
      }
      //读取cookie
      function getCookie()
      {
          var COOKIE_NAME=$.cookie("COOKIE_NAME");
          if (COOKIE_NAME !=null) {
              $("input[iscookie='true']").each(function() {
                  $($("#"+this.name).val( $.cookie(this.name)));
                  if("admin" == $.cookie(this.name)) {
                      $("#randCode").focus();
                  } else {
                      $("#password").val("");
                      $("#password").focus();
                  }
              });
              $("#on_off").attr("checked", true);
              $("#on_off").val("1");
          }
          else
          {
              $("#on_off").attr("checked", false);
              $("#on_off").val("0");
              $("#randCode").focus();
          }
      }

      //打开导入初始化数据
      function openInit(){
          height =$(window).height()*0.85;
          url = "${webRoot}/ymkCustomController.do?regCustom";
          $.dialog({
              content: 'url:'+url,
              lock : true,
              zIndex: 2000,
              width:600,
              height:350,
              title:'注册用户',
              opacity : 0.3,
              cache:false,
              ok: function(){
                  iframe = this.iframe.contentWindow;
                  $('#btn_sub', iframe.document).click();
                  return false;
              },
              cancelVal: '关闭',
              cancel: true
          });
      }
  </script>
    <%=lhgdialogTheme %>
</body>
</html>