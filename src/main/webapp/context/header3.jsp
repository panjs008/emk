<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

<script type="text/javascript">
    //编写自定义JS代码
    $(function(){
        BindSelect("businesserId","ymkCustomController.do?findUserList&userKey=业务员",1,$("#businesserName").val()+","+$("#businesser").val());

        $("#businesserId").change(function(){
            var itemarr = $("#businesserId").val().split(","); //字符分割
            $("#businesser").val(itemarr[1]);
            $("#businesserName").val(itemarr[0]);

            returnToDept($("#businesser").val());
        });

        BindSelect("tracerId","ymkCustomController.do?findUserList&userKey=业务跟单员",1,$("#tracerName").val()+","+$("#tracer").val());
        $("#tracerId").change(function(){
            var itemarr = $("#tracerId").val().split(","); //字符分割
            $("#tracer").val(itemarr[1]);
            $("#tracerName").val(itemarr[0]);
        });

        BindSelect("developerId","ymkCustomController.do?findUserList&userKey=生产跟单员",1,$("#developerName").val()+","+$("#developer").val());
        $("#developerId").change(function(){
            var itemarr = $("#developerId").val().split(","); //字符分割
            $("#developer").val(itemarr[1]);
            $("#developerName").val(itemarr[0]);
        });
    });



    function returnToDept(userName){
        $.ajax({
            url: "ymkCustomController.do?getDeptInfoByUser&userName="+userName,
            type: 'post',
            cache: false,
            data: null,
            success: function (data) {
                var dept = $.parseJSON(data)
                $("#businesseDeptName").val(dept.departname);
                $("#businesseDeptId").val(dept.orgCode);
            }
        });
    }

    function formatState (state) {
        if (!state.id) { return state.text; }
        var $state = $(
                '<span>' + state.text + '</span>'
        );
        return $state;
    }

    function BindSelect(ctrlName, url,type,categoryId) {
        var control = $('#' + ctrlName);
        //设置Select2的处理
        control.select2({
            formatResult: formatState,
            formatSelection: formatState,
            escapeMarkup: function (m) {
                return m;
            }
        });
        //绑定Ajax的内容
        $.getJSON(url, function (data) {
            control.empty();//清空下拉框
            control.append("<option value=''>请选择</option>");
            $.each(data.obj, function (i, item) {
                control.append("<option value='" + item.realName + ","+item.userName +"'>&nbsp;" + item.realName + "</option>");
            });
            if(type ==1){
                $("#"+ctrlName).select2('val',categoryId);
            }
        });

    }

</script>