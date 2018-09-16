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
    <script type="text/javascript" src="${webRoot}/andrew/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/andrew.mobile.plugin.min.js"></script><!--AK插件引入-->
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Popupwin.js"></script>

</head>
<body style="overflow-y: hidden;overflow-x: hidden">

<!----------Andrew_PopupWin---------->
<header class="press bor_bottom bor_gray_ddd bg_gray_f9f">
    <h1 class="text_al_c">智慧维保云平台</h1>
    <button type="button" class="right_0 w_25 pr_3 text_al_r text_12em" id="plug_menu"><i class="text_14em icon-im_fenlei"></i></button>
</header>

<main>

    <!----------Andrew_TouchDelete---------->
    <div class="module list press mt_5">
        <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">工单列表</h3>
        <ul class="plug_touchdel bg_white ovh">
            <c:forEach items="${uRepairEntityList}"  var="repair" varStatus="status">
                <li class="h_42em rel ovh bor_bottom bor_gray_ddd bg_white touchstart">
                    <a href="javascript:goto('uRepairController.do?gomReapirInfo&id=${repair.id}')"><article class="ml_3">
                        <h3 class="mt_05em c_gray_333">${status.index+1} ${repair.repairNum}（${repair.applyDate}）</h3>
                        <p class="line_h_18em c_gray_777">${repair.fault}</p>
                    </article></a>
                </li>
            </c:forEach>
        </ul>
    </div>


    <!----------Andrew_PopupWin Start---------->
    <div id="plug_MenuWin" class="dis_none bg_white pa_3 w_100 mt_32em"> <!--顶部位置显示的弹窗-->
        <ul class="wp_94 c_gray_777">
            <li class="fl h_3em line_h_3em mr_1em"><a href="loginController.do?logout">注销</a></li>
            <li class="fl h_3em line_h_3em mr_1em">修改密码</li>
        </ul>
    </div>
</main>

<footer class="press bg_gray_f9f">
    <menu class="bor_gray_ddd bor_top" id="menuId">
        <button type="button" class="fl" onclick="goto('uRepairController.do?mRepair')">
            <i class="text_18em c_gray_777" ></i>
            <em class="text_08em c_gray_777" >报修工单</em>
        </button>

        <button type="button" class="fl "  onclick="goto('uRepairController.do?mReapirList')">
            <i class="text_18em c_gray_777 c_title" ></i>
            <em class="text_08em c_gray_777 c_title" >工单进度</em>
        </button>

        <button type="button" class="fl">
            <i class="text_18em c_gray_777" id="i3"></i>
            <em class="text_08em c_gray_777" id="em3">个人信息</em>
        </button>
    </menu>
</footer>

</body>
<script type="text/javascript">

    function goto(url){
     /*   $("#i1").attr("class","text_18em c_gray_777");
        $("#em1").attr("class","text_08em c_gray_777");
        $("#i2").attr("class","text_18em c_gray_777");
        $("#em2").attr("class","text_08em c_gray_777");
        $("#i3").attr("class","text_18em c_gray_777");
        $("#em3").attr("class","text_08em c_gray_777");

        $("#i"+flag).attr("class","text_18em c_gray_777 c_title");
        $("#em"+flag).attr("class","text_08em c_gray_777 c_title");*/

        window.location.href = url;
    }
    //引入Andrew插件的区域
    /*-----------------------------------------------Andrew_Config (全局设置）使用方法-------------------------------------------*/
    $(function () {
        Andrew_Config({ //环境配置管理
            MaskStyle: ["style3","opacity07"], //1.所有弹窗背景图案选择（样式style1~6）、2.遮挡层背景的透明度（opacity01~09）
            touchstart: true, //是否开启移动端active效果, 建议开启 （元素的class里加touchstart即可用）(使用 true,不使用 false）
            ButtonLink: true, //通过元素中加data-href属性的方式跳转界面, 建议开启路由功能后使用。(使用button超链接 true,不使用button超链接 false）
            WechatHeader: false, //是否通过微信浏览器访问时自动隐藏应用的头部区域, 可以通用建议开启 (使用隐藏 true, 不使用隐藏 false）
            Topdblclick: true, //是否开启点击应用的头部让页面回头顶部 (开启 true, 停用 false）
            fixedBar: true, //输入信息时应用的头部绝对固定在屏幕最上方，底部有输入框时不被虚拟键盘遮挡 （不通过微信访问才生效，开启WechatHeader的参数时请关闭该参数）
            Orientation: true, //是否开启应用只允许竖屏浏览 (使用 true, 不使用 false）
            Prompt: "为了更好的视觉体验，请在竖屏下进行操作。" //应用横屏是提示文字 (必须开启Orientation的选项才能生效)
        });
    });

    /*-----------------------------------------------Andrew_Menu 使用方法-------------------------------------------*/
    $(function() {
        Andrew_Menu({ //底部菜单的图标以及文字样式变化设置
            //active_color: "c_title", //被选中的文字和图标的颜色
            menu_icon: new Array( //每个菜单的默认显示图标设置 （为了正常的显示布局最多设置5个）
                    "icon-mn_gongneng", //第1个按钮的图标
                    "icon-mn_hudong", //第2个按钮的图标
                    "icon-mn_huodong", //第3个按钮的图标
                    "icon-mn_kuangjia", //第4个按钮的图标
                    "icon-mn_kongjian" //第5个按钮的图标
            ),
            menu_icon_active: new Array( //每个菜单的被选中后的图标设置 （为了正常的显示布局最多设置5个）
                    "icon-mn_gongneng_fill", //第1个按钮的图标
                    "icon-mn_hudong_fill", //第2个按钮的图标
                    "icon-mn_huodong_fill", //第3个按钮的图标
                    "icon-mn_kuangjia_fill", //第4个按钮的图标
                    "icon-mn_kongjian_fill" //第5个按钮的图标
            )
        });
    });

    /*-----------------------------------------------Andrew_Popupwin 使用方法-------------------------------------------*/
    $(function(){
        $("#plug_menu").on("click", function (e) {
            Andrew_Popupwin({
                dom: "#plug_MenuWin", //弹窗内容的布局
                position: "top", //位置类型(top，bottom，left，right，middle)
                effectIn: "slideInDown ani_05s", //弹窗显示效果 (css动画库：andrew.animate.css)
                effectOut: "slideOutUp ani_05s", //弹窗消失效果 (css动画库：andrew.animate.css)
                maskPosition:"4", //Mask的z-index数值
                closeBtn: ".plug_closeIn", //关闭弹窗按钮的Class名
                OneButton: e.currentTarget, //点击按钮和关闭按钮通用时需要加该属性
                toggleIcon: "text_14em icon-bt_quxiao_a", //是否点击后替换图标功能，加图标的Class名 （建议启用OneButton的参数后使用）
                callback :function (ele) { //通过回调获取弹窗显示后的以上参数
                    console.log($(ele[0].dom)); //获取当前的弹窗元素
                }
            })
        })
    });

    $(function(){
        $("#plug_popup").click(function(){
            Andrew_Popupwin({
                dom: "#plug_PopupWin", //弹窗内容的布局
                position: "middle", //位置类型(top，bottom，left，right，middle)
                effectIn: "bounceInDown ani_1s", //弹窗显示效果
                effectOut: "bounceOutDown ani_1s", //弹窗消失效果
                maskPosition:"11", //Mask的z-index数值
                closeBtn: ".plug_closeIn", //关闭弹窗按钮的Class名
                callback :function (ele) { //通过回调获取弹窗显示后的以上参数
                    console.log($(ele[0].dom)); //获取当前的弹窗元素
                }
            })
        })
    });

</script>
</html>