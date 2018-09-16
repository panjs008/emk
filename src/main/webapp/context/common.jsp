<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,tools"></t:base>

<script>
	function closePrintWin(){
        frameElement.api.close();
        W.document.location.reload(true);
	}

    function goAlertAdd(customId){
        //createwindow("添加提醒","ymkCustomAlertController.do?goAdd&customId="+customId,600,350);
        $.dialog({
            content: 'url:ymkCustomAlertController.do?goAdd&customId='+customId,
            lock : true,
            zIndex: getzIndex(),
            width:600,
            height:350,
            title:"添加提醒",
            opacity : 0.3,
            cache:false,
            ok: function(){
                iframe = this.iframe.contentWindow;
                saveObj();
                window.location.href='ymkCustomController.do?jump&r=common&showflag=3&id='+customId;
                return false;
            },
            cancelVal: '关闭',
            cancel: true /*为true等价于function(){}*/
        });
    }
    function goAlertUpdate(alertId,cid){
        //createwindow("添加提醒","ymkCustomAlertController.do?goAdd&customId="+customId,600,350);
        $.dialog({
            content: 'url:ymkCustomAlertController.do?goUpdate&id='+alertId,
            lock : true,
            zIndex: getzIndex(),
            width:600,
            height:350,
            title:"修改提醒",
            opacity : 0.3,
            cache:false,
            ok: function(){
                iframe = this.iframe.contentWindow;
                saveObj();
                window.location.href='ymkCustomController.do?jump&r=common&showflag=3&id='+cid;
                return false;
            },
            cancelVal: '关闭',
            cancel: true /*为true等价于function(){}*/
        });
    }
    function doOver(alertId,cid){
        $.dialog.confirm('您是否确定完成?', function(r) {
            if (r) {
                $.ajax({
                    url : "ymkCustomAlertController.do?doOver&id="+alertId,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        if (d.success) {
                            window.location.href='ymkCustomController.do?jump&r=common&showflag=3&id='+cid;
                        }
                    }
                });
            }
        });

    }

    function doDel(alertId,cid){
        $.dialog.confirm('您是否确定删除提醒?', function(r) {
            if (r) {
                $.ajax({
                    url : "ymkCustomAlertController.do?doDel&id="+alertId,
                    type : 'post',
                    cache : false,
                    data: null,
                    success : function(data) {
                        var d = $.parseJSON(data);
                        if (d.success) {
                            window.location.href='ymkCustomController.do?jump&r=common&showflag=3&id='+cid;
                        }
                    }
                });
            }
        });

    }
</script>

<div style="width:100%;height:100%">
	<iframe id="printfrm" src="${webRoot}/${url}" width="100%" height="100%"
			style="border: 0px; line-height: 21px; background: #fff;overflow-x : hidden ; " frameborder="no" border="0" scrolling="yes">
	</iframe>
</div>

