<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title>智慧维保云平台</title>
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

</head>
<body>
<div class="container" style="margin-top:-40px;">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <!-- Start Sign In Form -->

            <form action="#" id="repairFrm" class="fh5co-form animate-box" data-animate-effect="fadeIn">
                <input id="repairNum" name="repairNum"  value="${repairNum}" type="hidden" />
                <input id="sblx" name="sblx"  value="" type="hidden" />
                <input id="bxType" name="bxType"  value="weixin" type="hidden" />
                <input id="applyDate" name="applyDate"  value="${applyDate}" type="hidden" />
                <h2 style="text-align: center">报修单申请</h2>
                <div id="errMsgContiner" class="form-group">
                    <div class="alert alert-success" role="alert"> <div id="showErrMsg"></div></div>
                </div>
                <div class="form-group">
                    <label for="proZnName" class="sr-only">紧急度</label>
                    <select id="jjd" name="jjd" style="width:157px;"class="form-control" >
                        <option value="">--选择--</option>
                        <option value="0">一般</option>
                        <option value="1">紧急</option>
                        <option value="2">非常紧急</option>

                    </select>
                </div>
                <div class="form-group">
                    <label for="proZnName" class="sr-only">设备名称</label>
                    <input type="text" class="form-control" id="proZnName" value="" name="proZnName" placeholder="设备名称" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="proZnName" class="sr-only">设备类型</label>
                    <input type="text" class="form-control" id="sblxVal" value="" name="sblxVal" placeholder="设备类型" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="proZnName" class="sr-only">品牌</label>
                    <input type="text" class="form-control" id="pp" value="" name="pp" placeholder="品牌" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="proZnName" class="sr-only">型号</label>
                    <input type="text" class="form-control" id="xh" value="" name="xh" placeholder="型号" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="proZnName" class="sr-only">故障描述</label>
                    <input type="text" class="form-control" id="fault" value="" name="fault" placeholder="故障描述" autocomplete="off">
                </div>
                <div class="form-group">
                    <input type="button" id="but_login" value="提交"  onclick="doSubmit()"class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->

        </div>
    </div>

</div>


  <script type="text/javascript">
      $(function(){
          optErrMsg();
      });
      $("#errMsgContiner").hide();
      function optErrMsg(){
          $("#showErrMsg").html('');
          $("#errMsgContiner").hide();
      }
      //登录提示消息显示
      function showErrorMsg(msg){
          $("#errMsgContiner").show();
          $("#showErrMsg").html(msg);
          window.setTimeout(optErrMsg,3000);
      }
      function doSubmit() {
          if($.trim($("#jjd").val()).length==0){
              showErrorMsg("请选择紧急度！！");
              return false;
          }
          if($.trim($("#proZnName").val()).length==0){
              showErrorMsg("请设备名称！！");
              return false;
          }

          if($.trim($("#sblxVal").val()).length==0){
              showErrorMsg("请输入设备类型！！");
              return false;
          }

          if($.trim($("#fault").val()).length==0){
              showErrorMsg("请输入故障描述！！");
              return false;
          }
          var formArray = $("#repairFrm").serializeArray();
          $.ajax({
              url : "uRepairController.do?doAdd",
              type : 'post',
              cache : false,
              data: formArray,
              success : function(data) {
                  var d = $.parseJSON(data);
                  if (d.success) {
                      alert(d.msg);
                  }else{
                      alert("报修单提交失败");
                  }
              }
          });

      }
  </script>
</body>
</html>