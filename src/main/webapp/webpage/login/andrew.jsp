<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>

<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="renderer" content="webkit" />
    <meta name="full-screen" content="yes" />
    <meta name="x5-fullscreen" content="true" />
    <meta name="format-detection" content="telphone=no, email=no" />
    <meta name="apple-mobile-web-app-status-bar-style" content="default" />
    <title>智慧维保云平台</title>
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/css/style.css" /><!--公共样式以及布局相关样式-->
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/css/theme.default.css" /><!--颜色相关样式-->
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Checkbox.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Codeval.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_DateTime.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Dialog.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_DropLoad.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_IntlTelInput.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_lightSlider.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Loader.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_MultiDate.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_PortraitImage.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_PreviewImage.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Progress.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Radio.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Select.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Slider.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Switch.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_TouchDelete.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Typeahead.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Viewer.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_WebToast.css" />
    <script type="text/javascript" src="${webRoot}/andrew/js/jquery-3.3.1.min.js"></script>

    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Dialog.js"></script>

</head>
<body style="overflow-y: hidden;overflow-x: hidden">
<!----------Andrew_PopupWin---------->
<header class="press bor_bottom bor_gray_ddd bg_gray_f9f">
    <h1 class="text_al_c">智慧维保云平台</h1>
</header>
<main>
    <form id="loginFrm" action="demo.html" method="get">
        <div class="module list mt_5 plug_validate" style="margin-top:100px;">
            <ul class="wp_94 press bg_white list_h_36em">
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-mb_shouji c_title text_16em"></i>
                    <label class="plug_mobileNum dis_block rel ovh fl ml_8 mr_05em line_h_in bor_right bor_gray_eee touchstart">
                        <input type="button" class="fl c_gray_777" value="+86" />
                        <i class="fl icon-ln_qianjin_b text_16em c_gray_777"></i>
                    </label>
                    <input type="text" id="userName" name="userName" class="fl w_70 plug_Delete c_gray_777" required value="" maxlength="11" placeholder="请输入手机号码或者账号"  />
                </li>
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-mb_suoding c_title text_16em"></i>
                    <input type="password" id="password" name="password" class="fl ml_8 w_92 c_gray_777 plug_PassCheck plug_Password" required value="" maxlength="16" placeholder="请输入密码" />
                </li>
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-im_yanzhengma c_title text_16em"></i>
                    <img id="randCodeImage" src="randCodeImage"  style="float: right;margin-bottom:-30px;"/>
                    <input type="text"  id="randCode"  name="randCode"  style="margin-top:-10px;" class="fl ml_8 w_92 c_gray_777" required value="" maxlength="6" placeholder="请输入验证码" />
                </li>
            </ul>
            <button type="button" id="but_login" onclick="checkUser()" class="btn_h_3em wm_94 bg_title c_white mt_5 h_32em">登录</button>
            <button type="button" class="btn_h_3em wm_94 bg_title c_white mt_5 h_32em">注册</button>
        </div>
    </form>
</main>


</body>
<script type="text/javascript">
    $(function(){

    });


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
            $ak.alert("请输入手机号或者账号！", { //(标准的H5使用方法 $ak.alert)
                title: "提示", //弹窗的标题文字
                button_ok: "确定", //弹窗的确定按钮文字
                animateIn: "bounceInDown", //弹窗显示动画效果 (css动画库：andrew.animate.css)
                animateOut: "bounceOutUp", //弹窗消失动画效果 (css动画库：andrew.animate.css)
                onSubmit:function(res){ //点击确定按钮后的回调
                    console.log(res);
                }
            });
            return false;
        }

        if($.trim($("#password").val()).length==0){
            $ak.alert("请输入密码！", { //(标准的H5使用方法 $ak.alert)
                title: "提示", //弹窗的标题文字
                button_ok: "确定", //弹窗的确定按钮文字
                animateIn: "bounceInDown", //弹窗显示动画效果 (css动画库：andrew.animate.css)
                animateOut: "bounceOutUp", //弹窗消失动画效果 (css动画库：andrew.animate.css)
                onSubmit:function(res){ //点击确定按钮后的回调
                    console.log(res);
                }
            });
            return false;
        }

        if($.trim($("#randCode").val()).length==0){
            $ak.alert("请输入验证码！", { //(标准的H5使用方法 $ak.alert)
                title: "提示", //弹窗的标题文字
                button_ok: "确定", //弹窗的确定按钮文字
                animateIn: "bounceInDown", //弹窗显示动画效果 (css动画库：andrew.animate.css)
                animateOut: "bounceOutUp", //弹窗消失动画效果 (css动画库：andrew.animate.css)
                onSubmit:function(res){ //点击确定按钮后的回调
                    console.log(res);
                }
            });
            return false;
        }
        return true;
    }

    //登录处理函数
    function newLogin(orgId) {
        //setCookie();

        var actionurl="loginController.do?login";//提交路径
        var checkurl="loginController.do?checkuser";//验证路径
        var formData =  $("#loginFrm").serializeArray();

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
                    window.location.href = "uRepairController.do?mRepair";
                } else {
                    $ak.alert(d.msg, { //(标准的H5使用方法 $ak.alert)
                        title: "提示", //弹窗的标题文字
                        button_ok: "确定", //弹窗的确定按钮文字
                        icon: "warning", //图标类型（warning，error，info，question，success）
                        animateIn: "bounceInDown", //弹窗显示动画效果 (css动画库：andrew.animate.css)
                        animateOut: "bounceOutUp", //弹窗消失动画效果 (css动画库：andrew.animate.css)
                        onSubmit:function(res){ //点击确定按钮后的回调
                            console.log(res);
                        }
                    });
                    reloadRandCodeImage();
                    $("#randCode").val("")
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


</script>
</html>