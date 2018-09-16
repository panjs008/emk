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
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Dialog.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_PortraitImage.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_PreviewImage.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Progress.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Radio.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Select.css" />
    <link rel="stylesheet" type="text/css" href="${webRoot}/andrew/js/plugin/css/Andrew_Viewer.css" />
    <script type="text/javascript" src="${webRoot}/andrew/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/data.js"></script><!--Json数据-->
    <script type="text/javascript" src="${webRoot}/andrew/js/andrew.mobile.plugin.min.js"></script><!--AK插件引入-->
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_ChangeIcon.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_ChangeInput.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_ChooseList.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Dialog.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_IntlTelInput.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_PortraitImage.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_PreviewImage.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Select.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Viewer.js"></script>
    <script type="text/javascript" src="${webRoot}/andrew/js/plugin/Andrew_Popupwin.js"></script>

    <script>
        $(function () {
            if(${message ne "-1"}){
                $ak.alert("报修单申请成功！", { //(标准的H5使用方法 $ak.alert)
                    title: "提示", //弹窗的标题文字
                    button_ok: "确定", //弹窗的确定按钮文字
                    animateIn: "bounceInDown", //弹窗显示动画效果 (css动画库：andrew.animate.css)
                    animateOut: "bounceOutUp", //弹窗消失动画效果 (css动画库：andrew.animate.css)
                    onSubmit:function(res){ //点击确定按钮后的回调
                    }
                });
            }
        });
        function saveData() {
            var formData =  $("#repairFrm").serializeArray();
            if($.trim($("#plug_trigger2").val()).length==0){
                $ak.alert("请输入地区！", { //(标准的H5使用方法 $ak.alert)
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
            if($.trim($("#proZnName").val()).length==0){
                $ak.alert("请输入设备名称！", { //(标准的H5使用方法 $ak.alert)
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
            if($.trim($("#sblx").val()).length==0){
                $ak.alert("请输入设备类型！", { //(标准的H5使用方法 $ak.alert)
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
            $.ajax({
                async : false,
                cache : false,
                type : 'POST',
                url : 'uRepairController.do?doWxAdd&address='+$("#plug_trigger2").val(),// 请求的action路径
                data : formData,
                error : function() {// 请求失败处理函数
                },
                success : function(data) {
                    var d = $.parseJSON(data);
                    $ak.alert(d.msg, { //(标准的H5使用方法 $ak.alert)
                        title: "提示", //弹窗的标题文字
                        button_ok: "确定", //弹窗的确定按钮文字
                        animateIn: "bounceInDown", //弹窗显示动画效果 (css动画库：andrew.animate.css)
                        animateOut: "bounceOutUp", //弹窗消失动画效果 (css动画库：andrew.animate.css)
                        onSubmit:function(res){ //点击确定按钮后的回调

                        }
                    });
                }
            });
        }
    </script>
</head>
<body style="overflow-y: hidden;overflow-x: hidden">
<%--<audio src="${webRoot}/images/1527932515305.mp3" controls="controls">
</audio>--%>

<!----------Andrew_PopupWin---------->
<header class="press bor_bottom bor_gray_ddd bg_gray_f9f">
    <h1 class="text_al_c">智慧维保云平台</h1>
    <button type="button" class="right_0 w_25 pr_3 text_al_r text_12em" id="plug_menu"><i class="text_14em icon-im_fenlei"></i></button>
</header>
<audio src="${webRoot}/images/1527932515305.mp3" controls="controls">
</audio>
<main>
    <form id="repairFrm"  action="uRepairController.do?doWxAdd"  enctype="multipart/form-data"  method="post" >
        <div class="module press mt_5">
            <ul class="list_h_36em">
                <li class="bor_top bor_gray_ddd bg_white">
                    <dl class="ovh list_h_36em">
                        <dt class="fl ovh ml_3 mr_03em">紧急度：</dt>
                        <dd class="fl ovh w_80">
                            <select id="jjd" name="jjd">
                                <option value="0">一般</option>
                                <option value="1">紧急</option>
                                <option value="2">非常紧急</option>
                            </select>
                        </dd>
                    </dl>
                </li>

            </ul>
        </div>
        <div class="module list press mt_5">
            <ul class="list_h_36em">

                <li class="rel bor_bottom bor_gray_ddd bg_white touchstart">
                    <dl class="rel h_in list_h_36em wm_94">
                        <dt class="fl">选择地区</dt>
                        <dd class="fr rel w_70 mr_7">
                            <input type="hidden" name="address" id="address" />
                            <input type="button" id="plug_trigger2" class="fr bg_white c_gray_777"  name="address" readonly value="" placeholder="请选择地区" />
                        </dd>
                        <dd class="abs top_0 right_0">
                            <i class="fr icon-ln_qianjin_b text_16em c_gray_777"></i>
                        </dd>
                    </dl>
                </li>
                <%-- <li class="rel bor_bottom bor_gray_ddd bg_white touchstart">
                     <dl class="rel h_in list_h_36em wm_94">
                         <dt class="fl">选择日期</dt>
                         <dd class="fr rel w_70 mr_7">
                             <input type="button" id="plug_datetime" class="fr bg_white c_gray_777" required readonly value="" placeholder="请选择日期" />
                         </dd>
                         <dd class="abs top_0 right_0">
                             <i class="fr icon-ln_qianjin_b text_16em c_gray_777"></i>
                         </dd>
                     </dl>
                 </li>--%>
            </ul>
        </div>
        <div class="module list mt_5 plug_validate">
            <ul class="wp_94 press bg_white list_h_36em">
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-im_yanzhengma c_title text_16em"></i>
                    <input type="hidden" class="fl ml_8 w_92 c_gray_777" name="repairNum"  value="${repairNum}" maxlength="32" placeholder="请输入设备品牌" />
                    <input type="hidden" class="fl ml_8 w_92 c_gray_777" name="applyDate"  value="${applyDate}" maxlength="32" placeholder="请输入设备品牌" />
                    <input type="text" class="fl ml_8 w_92 c_gray_777" id="proZnName" name="proZnName" required value="" maxlength="32" placeholder="请输入设备名称" />
                </li>
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-im_yanzhengma c_title text_16em"></i>
                    <input type="text" class="fl ml_8 w_92 c_gray_777" id="sblx" name="sblx"  required value="" maxlength="32" placeholder="请输入设备类型" />
                </li>
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-im_yanzhengma c_title text_16em"></i>
                    <input type="text" class="fl ml_8 w_92 c_gray_777" name="pp"  value="" maxlength="32" placeholder="请输入设备品牌" />
                </li>
                <li class="rel bor_bottom bor_gray_ddd">
                    <i class="abs left_0 icon-im_yanzhengma c_title text_16em"></i>
                    <input type="text" class="fl ml_8 w_92 c_gray_777" name="pp"  value="" maxlength="32" placeholder="请输入设备型号" />
                </li>
            </ul>
        </div>



        <!----------Andrew_PreviewImage---------->
        <!----------Andrew_PreviewImage---------->
        <div class="rel press bg_white mt_5 clear" >
            <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">报修照片</h3>
            <ul class="w_94 center length4 pt_5">
                <li class="rel fl mb_5" >
                    <label class="plug_previewImage bg_white dis_block text_al_c ovh rel w_80 h_5em line_h_5em border bor_gray_ddd" >
                        <i class="icon-ln_jia_a text_24em line_h_16em text_bold c_gray_ddd text_al_m"></i>
                        <input id="fileId" type="file" name="myfiles" />
                    </label>
                </li>
                <li class="rel fl mb_5">
                    <button type="button" class="plug_DelImage bg_white dis_block text_al_c ovh rel w_80 h_5em line_h_5em border bor_gray_ddd">
                        <i class="icon-ln_jian_a text_24em line_h_16em text_bold c_gray_ddd text_al_m"></i>
                    </button>
                </li>
            </ul>
        </div>

        <div id="fileDiv" style="display: none">
        </div>


        <div class="module mt_5 plug_validate">
            <dl class="ovh bg_white">
                <dt class="press c_title bg_white pl_3 h_3em line_h_3em">故障描述</dt>
                <dd class="rel minus_mt_05em">
                    <textarea class="plug_textarea ml_3 mr_3 w_94 h_12_em c_gray_777" name="fault" placeholder="请输入遇到的故障描述..." required></textarea>
                </dd>
            </dl>
        </div>


        <div class="module mt_5 plug_validate">
            <dl class="ovh bg_white">
                <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">录音信息</h3>

                <dd class="rel minus_mt_05em">
                    <table style="float: left;">
                        <tr>
                            <td><button type="button" class="btn_h_3em wm_94 bg_title c_white mt_5 h_32em" onclick="funStart(this);" id="btnStart" disabled>录制</button>
                            </td>
                            <td><button type="button" class="btn_h_3em wm_94 bg_title c_white mt_5 h_32em" onclick="funStop(this);"  id="btnStop" disabled>停止</button>
                            </td>
                            <td><button type="button" class="btn_h_3em wm_94 bg_title c_white mt_5 h_32em" onclick="funUpload(this);"  id="btnUpload" disabled>上传</button>
                            </td>
                        </tr>
                    </table>
                    <div id="recordingslist"></div>
                </dd>
            </dl>
        </div>
        <button type="submit"  class="btn_h_3em wm_94 bg_title c_white mt_5 h_32em">提交</button>

    </form>



    <!----------Andrew_PopupWin Start---------->
    <div id="plug_MenuWin" class="dis_none bg_white pa_3 w_100 mt_32em"> <!--顶部位置显示的弹窗-->
        <ul class="wp_94 c_gray_777">
            <li class="fl h_3em line_h_3em mr_1em"><a href="loginController.do?logout">注销</a></li>
            <li class="fl h_3em line_h_3em mr_1em">修改密码</li>
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


</main>

<footer class="press bg_gray_f9f">
    <menu class="bor_gray_ddd bor_top">
        <button type="button" class="fl" onclick="goto('uRepairController.do?mRepair')">
            <i class="text_18em c_gray_777"></i>
            <em class="text_08em c_gray_777">报修工单</em>
        </button>

        <button type="button" class="fl" onclick="goto('uRepairController.do?mReapirList')">
            <i class="text_18em c_gray_777"></i>
            <em class="text_08em c_gray_777">工单进度</em>
        </button>

        <button type="button" class="fl">
            <i class="text_18em c_gray_777"></i>
            <em class="text_08em c_gray_777">个人信息</em>
        </button>
    </menu>
</footer>

</body>

<script src="${webRoot}/recordMp3/js/recordmp3.js"></script>
<script>
    var recorder = new MP3Recorder({
        debug:true,
        funOk: function () {
            btnStart.disabled = false;
            log('初始化成功');
        },
        funCancel: function (msg) {
            log(msg);
            recorder = null;
        }
    });
    var mp3Blob;


    function funStart(button) {
        btnStart.disabled = true;
        btnStop.disabled = false;
        btnUpload.disabled = true;
        log('录音开始...');
        recorder.start();
    }

    function funStop(button) {
        recorder.stop();
        btnStart.disabled = false;
        btnStop.disabled = true;
        btnUpload.disabled = false;
        log('录音结束，MP3导出中...');
        recorder.getMp3Blob(function (blob) {
            log('MP3导出成功');

            mp3Blob = blob;
            var url = URL.createObjectURL(mp3Blob);
            var div = document.createElement('div');
            var au = document.createElement('audio');
            var hf = document.createElement('a');

            au.controls = true;
            au.src = url;
            hf.href = url;
            hf.download = new Date().toISOString() + '.mp3';
            hf.innerHTML = hf.download;
            div.appendChild(au);
            div.appendChild(hf);
            recordingslist.appendChild(div);
        });
    }

    function log(str) {
        recordingslist.innerHTML += str + '<br/>';
    }

    function funUpload() {
        var fd = new FormData();
        var mp3Name = encodeURIComponent('audio_recording_' + new Date().getTime() + '.mp3');
        fd.append('mp3Name', mp3Name);
        fd.append('file', mp3Blob);

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                recordingslist.innerHTML += '上传成功：<a href="' + xhr.responseText + '" target="_blank">' + mp3Name + '</a>';
            }
        };

        xhr.open('POST', 'upload.ashx');
        xhr.send(fd);
    }
</script>
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
            active_color: "c_title", //被选中的文字和图标的颜色
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



    /*-----------------------------------------------Andrew_ChooseList 使用方法-------------------------------------------*/
    $(function(){
        $(".plug_choose").Andrew_ChooseList({
            active: "bg_title c_white bor_title", //选中后的样式
            multi: false, //单选和多选设置（单选：false，多选：true --- 或者元素里直接加 multiple="multiple" ）
            full: false, //Choose的宽度，默认为null将自动获取Choose的宽度；
            click: function(index, item) { //Choose元素点击时的回调，diabled时不发生。
                console.log("click", index, item.text());
            },
            change: function(index, item) { //choose值改变时的回调；
                console.log("change", index, item.text());
            }
        });
    });

    /*-----------------------------------------------Andrew_ChangeInput 使用方法-------------------------------------------*/
    $(function(){
        $(".plug_change_btn").Andrew_ChangeInput({
            text_input: new Array( //两个左侧和右侧的输入框设置
                    ".plug_change_text1", //切换按钮的“左侧”输入框Class
                    ".plug_change_text2" //切换按钮的“右侧”输入框Class
            ),
            onChange:function(l_val,r_val){ //回调获取当前实时变化的左右两个值
                console.log("左侧："+l_val);
                console.log("右侧："+r_val);
            }
        });
    });


    /*-----------------------------------------------Andrew_intlTelInput 使用方法-------------------------------------------*/
    $(function(){
        $(".plug_mobileNum").Andrew_IntlTelInput({
            Title_Text: "选择国家和地区", //头部的标题文字 （注：微信平台不显示头部所以在微信平台中使用，请把该参数设置为空。）
            Close_btn: "left_0 w_25 pl_3 text_al_l text_12em", //关闭按钮的样式设置
            Close_Text: "关闭", //头部的关闭按钮文字 （注：微信平台不显示头部所以在微信平台中使用，请把该参数设置为空。）
            Close_Icon:"text_14em icon-bt_quxiao_a mr_03em", //头部的关闭按钮图标  （注：微信平台不显示头部所以在微信平台中使用，请把该参数设置为空。）
            list_Class: "bg_white bor_bottom bor_gray_ddd c_gray_777 touchstart", //数据列表的Class样式
            Nav_active: "c_title text_bold", //右侧固定字母菜单的选中样式
            show_color: "c_title text_bold text_4em", //右侧固定字母点击后中间淡入字母效果的样式
            data: json_countryData, //Json获取data数据(data.js)
            showBack:function(ele){ //回调获取被展开后显示的元素
                console.log("选择区域的元素被显示了。您可以对该元素进行操作。");
                console.log(ele);
            },
            clickBack:function(num){ //回调获取当前的值
                console.log($(num)[0].dataset);
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

    /*-----------------------------------------------Andrew_Viewer 使用方法-------------------------------------------*/
    $(function() {
        $(".plug_viewer").Andrew_Viewer({
            url: "data-original" //图片放大后高清图片URL设置
        });
    });


    /*-----------------------------------------------Andrew_Select 使用方法-------------------------------------------*/
    $(function () {
        new Andrew_Select({
            trigger: "#plug_trigger1", //需要绑定的元素
            title: "选择星期", //弹窗标题文字
            ensure: "确定", //弹窗的确定按钮文字
            cancel: "取消", //弹窗的取消按钮文字
            wheels: [
                {data: json_weekData} //Json获取data数据(data.js)
            ],
            position:[0], //初始化定位，打开时默认选中的哪个。如果不填默认为0
            callback: function (val,length) { //回调获取当前的值（val是值,length是数量以及数组）
                console.log($(val));
                console.log($(length));
            }
        });
    });

    $(function () {
        new Andrew_Select({
            trigger: "#plug_trigger2",  //需要绑定的元素
            title: "选择地区", //弹窗标题文字
            ensure: "确定", //弹窗的确定按钮文字
            cancel: "取消", //弹窗的取消按钮文字
            wheels: [
                {data : json_cityData} //Json获取data数据(data.js)
            ],
            callback: function (val,length) { //回调获取当前的值（val是值,length是数量以及数组）
                console.log($(val));
                console.log($(length));
            }
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