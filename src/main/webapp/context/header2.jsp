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

    function returnToSelect(){
        BindSelect("businesserId","ymkCustomController.do?findUserList&userKey=业务员",1,$("#businesserName").val()+","+$("#businesser").val());
        BindSelect("tracerId","ymkCustomController.do?findUserList&userKey=业务跟单员",1,$("#tracerName").val()+","+$("#tracer").val());
        BindSelect("developerId","ymkCustomController.do?findUserList&userKey=生产跟单员",1,$("#developerName").val()+","+$("#developer").val());
    }

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

    function downloadFile(url) {
        window.open("${webRoot}/"+url);
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

    function toDecimal(x) {
        if(!x){
            return x;
        }
        var result = parseFloat(x);
        if (isNaN(result)) {
            return x;
        }
        result = Math.round(x * 100) / 100;
        return result;
    }

    function resetTrNum(tableId) {
        $tbody = $("#"+tableId+"");
        $tbody.find('>tr').each(function(i){
            $(':input, select', this).each(function(){
                var $this = $(this), name = $this.attr('name'), val = $this.val();
                if(name!=null && name != 'ck'){
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

    function downloadFile(url) {
        window.open("${webRoot}/"+url);
    }

    function selectAll(selectStatus){//传入参数（全选框的选中状态）
        //根据name属性获取到单选框的input，使用each方法循环设置所有单选框的选中状态
        if(selectStatus){
            $("input[name='ck']").each(function(i,n){
                n.checked = true;
            });
        }else{
            $("input[name='ck']").each(function(i,n){
                n.checked = false;
            });
        }
    }

    //日期加天数的方法
    //dataStr日期字符串
    //dayCount 要增加的天数
    //return 增加n天后的日期字符串
    function dateAddDays(dataStr,dayCount) {
        var strdate = dataStr; //日期字符串
        var isdate = new Date(strdate.replace(/-/g,"/"));  //把日期字符串转换成日期格式
        isdate = new Date((isdate/1000+(86400*dayCount))*1000);  //日期加1天
        var pdate;
        if(isdate.getMonth()+1>9){
            if(isdate.getDate()>9){
                pdate = isdate.getFullYear()+"-"+(isdate.getMonth()+1)+"-"+(isdate.getDate());   //把日期格式转换成字符串
            }else{
                pdate = isdate.getFullYear()+"-"+(isdate.getMonth()+1)+"-0"+(isdate.getDate());   //把日期格式转换成字符串
            }
        }else{
            if(isdate.getDate()>9){
                pdate = isdate.getFullYear()+"-0"+(isdate.getMonth()+1)+"-"+(isdate.getDate());   //把日期格式转换成字符串
            }else{
                pdate = isdate.getFullYear()+"-0"+(isdate.getMonth()+1)+"-0"+(isdate.getDate());   //把日期格式转换成字符串
            }
        }
        return pdate;
    }

    /*function setYongliang(ii){
        if($("#base").contents().find("#gyzl").val() == 'wufeng'){
            var yongliangV = toDecimal((parseFloat($("#sunhaoPrecent"+ii).val())+1)*parseFloat($('#precent'+ii).val())/100);
            $("#yongliang"+ii).val(yongliangV);
        }
    }

    function setPrice(ii){
        var chengbenV = toDecimal((parseFloat($("#sunhaoPrecent"+ii).val())+1)*parseFloat($('#yongliang'+ii).val()))*parseFloat($('#signPrice'+ii).val());
        $("#chengben"+ii).val(toDecimal(chengbenV));
    }
    function setPrice2(ii){
        var chengbenV = toDecimal((parseFloat($("#bsunhaoPrecent"+ii).val())+1)*parseFloat($('#byongliang'+ii).val()))*parseFloat($('#bsignPrice'+ii).val());
        $("#bchengben"+ii).val(toDecimal(chengbenV));
    }
    function setPrice3(ii){
        var chengbenV = toDecimal((parseFloat($("#csunhaoPrecent"+ii).val())+1)*parseFloat($('#cyongliang'+ii).val()))*parseFloat($('#csignPrice'+ii).val());
        $("#cchengben"+ii).val(toDecimal(chengbenV));
    }*/
</script>