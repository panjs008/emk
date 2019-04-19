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
    });

    function returnToSelect(){
        BindSelect("businesserId","ymkCustomController.do?findUserList&userKey=业务员",1,$("#businesserName").val()+","+$("#businesser").val());
        //returnToDept($("#businesser").val());
    }

    function returnToDept(userName){
        $.ajax({
            url: "ymkCustomController.do?getDeptInfoByUser&userName="+userName,
            type: 'post',
            cache: false,
            data: null,
            success: function (data) {
                var dept = $.parseJSON(data);

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
            if(type ==1 && categoryId !='' && categoryId != null && categoryId!= 'undefined,undefined'){
                $("#"+ctrlName).select2('val',categoryId);
            }
        });

    }

    function findDetail(photoUrl) {
        $.dialog({
            content: 'url:emkEnquiryController.do?photo&photoUrl='+photoUrl,
            zIndex: getzIndex(),
            title : "查看",
            lock : true,
            width:900,
            height: 500,
            opacity : 0.3,
            cache:false,
            lock : true,
            cache:false,
            max: true,
            min: true,
            drag: true,
            resize: false
        });
    }

    function setEndTime() {
        var d1  =  $("#kdDate").val();
        var d2  =  $("#ysDate").val();
        $("#levelDays").val(DateDiff(d1,d2));
    }

    function DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式
        var  aDate,  oDate1,  oDate2,  iDays
        aDate  =  sDate1.split("-")
        oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式
        aDate  =  sDate2.split("-")
        oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
        iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
        return  iDays
    }

    function resetTrNum(tableId) {
        $tbody = $("#"+tableId+"");
        $tbody.find('>tr').each(function(i){
            $(':input, select', this).each(function(){
                var $this = $(this), name = $this.attr('name'), val = $this.val();
                if(name!=null){
                    if (name.indexOf("#index#") >= 0){
                        $this.attr("name",name.replace('#index#',i));
                    }else{
                        var s = name.indexOf("[");
                        var e = name.indexOf("]");
                        var new_name = name.substring(s+1,e);
                        $this.attr("name",name.replace(new_name,i));
                    }
                }
            });
        });
    }
</script>