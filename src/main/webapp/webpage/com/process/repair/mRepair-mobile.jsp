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
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_PortraitImage.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_PreviewImage.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_lightSlider.css" />

    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Progress.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Viewer.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_WebToast.css" />
    <script type="text/javascript" src="${webRoot}/andrew/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/data.js"></script><!--Json数据-->
    <script type="text/javascript" src="${webRoot}/andrew/js/andrew.mobile.plugin.min.js"></script><!--AK插件引入-->
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_lightSlider.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_PortraitImage.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_PreviewImage.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Ratyli.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Progress.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Viewer.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_WebToast.js"></script>

</head>
<body style="overflow-y: hidden;overflow-x: hidden">

<!----------Andrew_PopupWin---------->
<header class="press bor_bottom bor_gray_ddd bg_gray_f9f">
    <h1 class="text_al_c">智慧维保云平台</h1>
    <button type="button" class="right_0 w_25 pr_3 text_al_r text_12em" id="plug_menu"><i class="text_14em icon-im_fenlei"></i></button>
</header>

<main>
    <!----------Andrew_Progress---------->
    <div class="module press mt_5">
        <ul class="list_h_36em">
            <li class="ovh rel bg_white">
                <dl class="ovh h_in">
                    <dt class="fl ovh ml_3 mr_03em">当前进度：</dt>
                    <dd class="fl ovh w_70 h_in">
                        <div class="plug_progress h_2em bg_gray_ddd"></div>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>
    <!----------Andrew_StepOrder---------->
    <div class="module list press mt_5">
        <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">${uRepairPage.repairNum}</h3>
        <article class="plug_step pa_3 bg_white dis_none">
            <ul class="length4">
                <c:forEach items="${tasks}"  var="task" varStatus="status">
                    <li>
                        <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50">1</strong>
                        <h6 class="c_gray_777 line_h_24em">${task.name}</h6>
                    </li>
                </c:forEach>
               <%-- <li>
                    <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50">1</strong>
                    <h6 class="c_gray_777 line_h_24em">工单申请</h6>
                </li>
                <li>
                    <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50">2</strong>
                    <h6 class="c_gray_777 line_h_24em">设备检测</h6>
                </li>
                <li>
                    <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50">3</strong>
                    <h6 class="c_gray_777 line_h_24em">服务台受理</h6>
                </li>
                <li>
                    <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50">4</strong>
                    <h6 class="c_gray_777 line_h_24em">已完成</h6>
                </li>--%>
            </ul>
        </article>
    </div>


    <!----------Andrew_Radio&Andrew_Checkbox&Andrew_Switch---------->
    <div class="module list press mt_5">
        <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">工单信息</h3>
        <ul class="list_h_36em">
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        报修时间：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.applyDate}</dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        地区：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.address}</dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        紧急度：
                    </dt>
                    <dd class="fl h_in ml_05em"><c:if test="${uRepairPage.jjd eq 0}">一般</c:if>
                        <c:if test="${uRepairPage.jjd eq 1}">紧急</c:if>
                        <c:if test="${uRepairPage.jjd eq 2}">非常紧急</c:if></dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        设备名称：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.proZnName}</dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        设备类型：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.sblx}</dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        品牌：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.pp}</dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        型号：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.xh}</dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white touchstart">
                <dl class="h_in">
                    <dt class="fl ml_3 h_in">
                        故障描述：
                    </dt>
                    <dd class="fl h_in ml_05em">${uRepairPage.fault}</dd>
                </dl>
            </li>
        </ul>
    </div>

    <!----------Andrew_lightSlider---------->
    <div class="module bg_white press mt_5 pb_3">
        <h3 class="bg_white c_title pl_3 mb_1em line_h_36em bor_bottom bor_gray_ddd">图片滑动展示</h3>
        <div class="wm_94 ovh rel">
            <ul id="plug_photo_slider">
                <c:forEach items="${attchEntities}"  var="attch" varStatus="status">
                    <li class="h_8em">
                        <figure class="bg_gray_f5f border bor_gray_ddd h_in img_auto">
                            <img src="${webRoot}/${attch.filePath}" />
                        </figure>
                    </li>
                </c:forEach>


            </ul>
        </div>
    </div>



    <!----------Andrew_Ratyli---------->
    <div class="module list press mt_5">
        <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">星级评分</h3>
        <ul class="list_h_36em">
            <li class="bor_bottom bor_gray_ddd bg_white">
                <dl class="ovh h_in">
                    <dt class="fl ovh ml_3 mr_03em">可选评价：</dt>
                    <dd class="fl ovh">
                        <div class="line_h_in ovh">
                            <span id="plug_ratyli1" data-rate="1" data-ratemax="5"></span>
                        </div>
                    </dd>
                </dl>
            </li>
            <li class="bor_bottom bor_gray_ddd bg_white">
                <dl class="ovh h_in">
                    <dt class="fl ovh ml_3 mr_03em">展示评价：</dt>
                    <dd class="fl ovh">
                        <div class="line_h_in ovh">
                            <span id="plug_ratyli2" data-rate="4" data-ratemax="5"></span>
                        </div>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>



    <!----------Andrew_PopupWin Start---------->
    <div id="plug_MenuWin" class="dis_none bg_white pa_3 w_100 mt_32em"> <!--顶部位置显示的弹窗-->
        <ul class="wp_94 c_gray_777">
            <li class="fl h_3em line_h_3em mr_1em"><a href="loginController.do?logout">注销</a></li>
            <li class="fl h_3em line_h_3em mr_1em">报修工单</li>

        </ul>
    </div>

    <div id="plug_PopupWin" class="dis_none bg_white pa_5 w_85"> <!--中间位置显示的弹窗-->
        <button type="button" class="plug_closeIn dis_block wh_12em mt_01em mr_01em abs bg_white top_0 right_0 c_gray_333 text_18em icon-ln_quxiao"></button>
        <h2 class="text_bold press text_12em">弹窗标题</h2>
        <ul class="bor_top_dashed bor_gray_ddd mt_05em pt_05em c_gray_777">
            <li class="h_3em line_h_3em">弹窗内容显示中1</li>
            <li class="h_3em line_h_3em">弹窗内容显示中2</li>
            <li class="h_3em line_h_3em">弹窗内容显示中3</li>
        </ul>
    </div>

    <div id="plug_filterWin" class="dis_none w_100 ovh"> <!--对应位置显示的弹窗-->
        <ul class="abs ovh wp_94 bg_white bor_top bor_gray_ddd pt_3 pb_3 zindex_2 c_gray_777">
            <li class="fl h_3em line_h_3em mr_1em">筛选A</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选B</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选C</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选D</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选E</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选F</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选G</li>
            <li class="fl h_3em line_h_3em mr_1em">筛选H</li>
        </ul>
    </div>
    <!----------Andrew_PopupWin End---------->

</main>

<footer class="press bg_gray_f9f">
    <menu class="bor_gray_ddd bor_top">
        <button type="button" class="fl" onclick="goto('uRepairController.do?mRepair')">
            <i class="text_18em c_gray_777"></i>
            <em class="text_08em c_gray_777">报修工单</em>
        </button>

        <button type="button" class="fl" style="color:#79c34a !important" onclick="goto('uRepairController.do?mReapirList')">
            <i class="text_18em c_gray_777 c_title"></i>
            <em class="text_08em c_gray_777 c_title">工单进度</em>
        </button>

        <button type="button" class="fl">
            <i class="text_18em c_gray_777"></i>
            <em class="text_08em c_gray_777">个人信息</em>
        </button>
    </menu>
</footer>

</body>
<script type="text/javascript">
    function goto(url){
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

    /*-----------------------------------------------Andrew_lightSlider 使用方法-------------------------------------------*/
    $(function(){
        $("#plug_photo_slider").Andrew_lightSlider({
            item:3, //默认显示几张
            activeClass: '', //图片点击后的class样式(建议在左右能滑动的Tabs功能时使用)
            loop:true, //是否重复联播（true，false）
            autoWidth: false, //是否开启自定义宽度（true：不设置自动宽度可以手动设置宽度，false:自动比率加宽度）
            auto:false, //是否自动播放（true，false）
            slideMargin: 10, //每张图的右侧间距 （输入数字自动识别为px）
            speed:1000, //自动播放速度
            pager: false, //是否显示联播图位置按钮（true，false）
            dotClass: new Array( //底部图片位置的样式设置（开启pager的参数才有效）
                    "bg_gray_777 wh_08em bor_rad_50 ml_02em mr_02em", //默认样式
                    "bg_title" //点击后的样式
            ),
            onSliderClick: function($el,num) { //通过回调获取点击事件 （$el：当前元素，num：当前点击的序号）
                console.log(num)
            },
            onSliderLoad: function ($el) { //加载后的回调 （$el：当前元素）
                //console.log($el)
            }
        });
    });
    /*-----------------------------------------------Andrew_Progress 使用方法-------------------------------------------*/
    $(function () {
        $(".plug_progress").Andrew_Progress({
            goalAmount: 100, //总进度数值
            currentAmount: ${uRepairPage.processValue eq null ? 0:uRepairPage.processValue}, //当前进度数值
            milestoneNumber: 90, //数字达到当前设置的参数后进度条的颜色变化
            ColorStyle: "bg_title c_white", //进度条样式设置
            textBefore: "", //当前进度文字
            textAfter: "%", //当前进度百分比文字
            callback: function() { //通过回调获取元素
                //console.log(this);
            }
        });
    });



    /*-----------------------------------------------Andrew_PreviewImage 使用方法-------------------------------------------*/
    $(function () {
        $(".plug_previewImage").Andrew_PreviewImage({ //删除图片提示设置
            messege: "确认要删除当前的图片吗？", //弹窗中的提示文字
            btn_ok: "确定", //弹窗中的确定按钮文字
            btn_cancel: "取消", //弹窗中的取消按钮文字
            box_title: new Array(//弹窗中的标题名称文字
                    "确认", //confirm
                    "提示" //alert
            ),
            delbtnClass: ".plug_DelImage", //设置控制显示和隐藏删除按钮的控制按钮
            ak_webToast: "成功删除!", //删除图片后提示的文字
            Class:"bg_white dis_block text_al_c ovh rel w_80 h_5em line_h_5em border bor_gray_ddd", //图片列表边框样式
            Del_icon:"icon-ios-close-empty", //图片删除图标样式
            length:4, //默认允许上传多少照片
            length_title:"最大支持上传图片数量为：", //超出上传限制数量后提示的文字
            errorTip:"图片上传格式不正确！", //图片上传格式有误时提示的文字
            addCallbak: function(imglist) { //上传图片后的回调（获取图片列表元素）
                console.log(imglist)
            },
            delCallbak: function(image) { //点击删除按钮后的回调，获取要删除的图片元素
                console.log(image);
                image.remove();
            }
        });
    });

    /*-----------------------------------------------Andrew_PortraitImage 使用方法-------------------------------------------*/
    $(function () {
        $(".plug_PortraitImage").Andrew_PortraitImage({
            errorTip:"图片上传格式不正确！", //图片上传格式有误时提示的文字
            btn_ok: "确定", //弹窗中的确定按钮文字
            box_title: "提示", //弹窗中的标题名称文字
            addCallbak: function(img) { //上传图片后的回调（获取图片元素）
                console.log(img)
            }
        });
    });

    /*-----------------------------------------------Andrew_Ratyli 使用方法-------------------------------------------*/
    $(function() {
        $("#plug_ratyli1").Andrew_Ratyli({
            rate:1, //当前被选中的数量
            ratemax:5, //总数量
            disable:false, //是否能点击(不可点击：true，可点击：false)
            full:"<i class='c_title text_18em line_h_2em icon-mn_xingxing_fill mr_05em'></i>", //被选中状态
            empty:"<i class='c_title text_18em line_h_2em icon-mn_xingxing mr_05em'></i>", //未选中状态
            onRated:function(value,init){ //init是默认触发事件
                if(!init) console.log(value); // 获取点击后的值
                if(!init) console.log(this); //这里的this指向对象
            }
        });
    });

    /*-----------------------------------------------Andrew_Viewer 使用方法-------------------------------------------*/
    $(function() {
        $(".plug_viewer").Andrew_Viewer({
            url: "data-original" //图片放大后高清图片URL设置
        });
    });



</script>
</html>