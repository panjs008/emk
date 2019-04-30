<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div id="orderDiv" title="流程图" style="width:100%;height:350px;overflow:hidden;overflow-x: hidden;overflow-y: hidden" >
    <iframe scrolling="no" id="processFrm" frameborder="0" style=""  src="flowController.do?showProcess&id=${param.id}&tableName=emk_material_required" width="100%" height="100%"></iframe>
</div>
<div id="hisProcessDiv" title="" style="width:100%;height:230px;overflow:hidden;overflow-x: hidden;overflow-y: hidden" >
    <iframe scrolling="no" id="hisProcessFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="flowController.do?processHis&sqlType=samplerequired&id=${param.id}" width="100%" height="100%"></iframe>

</div>

