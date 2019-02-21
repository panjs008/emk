<%@include file="/context/mytags.jsp"%>
<link href="${webRoot}/plug-in/jquery-plugs/fileupload/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${webRoot}/plug-in/jquery-plugs/fileupload/css/dropzone.css" type="text/css" rel="stylesheet" />
<div id="progress" class="progress">
    <div class="bar" style="width:${param.recent}%;">${param.recent}%</div>
</div>
