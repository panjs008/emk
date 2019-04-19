<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>质量检查图片</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){
			//$("#detailId").load("emkEnquiryController.do?orderMxList");
		});

		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl").val(d.attributes.url);
			$("#imageName").val(d.attributes.name);
			$("#imageId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg").attr('src',d.attributes.url);
		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl2").val(d.attributes.url);
			$("#imageName2").val(d.attributes.name);
			$("#imageId2").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg2").attr('src',d.attributes.url);
		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl3").val(d.attributes.url);
			$("#imageName3").val(d.attributes.name);
			$("#imageId3").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg3").attr('src',d.attributes.url);
		}
		function uploadSuccess4(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl4").val(d.attributes.url);
			$("#imageName4").val(d.attributes.name);
			$("#imageId4").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg4").attr('src',d.attributes.url);
		}
		function uploadSuccess5(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl5").val(d.attributes.url);
			$("#imageName5").val(d.attributes.name);
			$("#imageId5").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg5").attr('src',d.attributes.url);
		}
		function uploadSuccess6(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl6").val(d.attributes.url);
			$("#imageName6").val(d.attributes.name);
			$("#imageId6").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg6").attr('src',d.attributes.url);
		}
		function uploadSuccess7(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl7").val(d.attributes.url);
			$("#imageName7").val(d.attributes.name);
			$("#imageId7").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg7").attr('src',d.attributes.url);
		}
		function uploadSuccess8(d,file,response){
			var src = d.attributes.url;
			$("#imageUrl8").val(d.attributes.url);
			$("#imageName8").val(d.attributes.name);
			$("#imageId8").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
			$("#uploadimg8").attr('src',d.attributes.url);
		}


	</script>
</head>
<body>
<t:formvalid formid="imageFrm" dialog="true" usePlugin="password" layout="table" action="emkEnquiryController.do?doAdd" tiptype="1">
		<table style="width: 80%;margin-top:22px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" align="center">
				<input id="imageName" name="imageName1" type="hidden" value="${emkQualityImageEntities[0].imageName}"/>
				<img id="uploadimg" onclick="findDetail('${emkQualityImageEntities[0].imageUrl }')"  src="${emkQualityImageEntities[0].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[0].imageUrl}" width="250" height="250">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>
				<c:if test="${emkQualityImageEntities[0].imageUrl ne null && emkQualityImageEntities[0].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[0].imageUrl }')">${emkQualityImageEntities[0].imageName}</a>]</c:if>
				<span id="imageId"></span>
				<input id="imageUrl" name="imageUrl1" type="hidden" value="${emkQualityImageEntities[0].imageUrl}"/>
			</td>
			<td class="value" align="center">
				<input id="imageName2" name="imageName2" type="hidden" value="${emkQualityImageEntities[1].imageName}"/>
				<img id="uploadimg2" onclick="findDetail('${emkQualityImageEntities[1].imageUrl }')"  src="${emkQualityImageEntities[1].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[1].imageUrl}" width="250" height="250">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>
				<c:if test="${emkQualityImageEntities[1].imageUrl ne null && emkQualityImageEntities[1].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[1].imageUrl }')">${emkQualityImageEntities[1].imageName}</a>]</c:if>
				<span id="imageId2"></span>
				<input id="imageUrl2" name="imageUrl2" type="hidden" value="${emkQualityImageEntities[1].imageUrl}"/>
			</td>
			<td class="value" align="center">
				<input id="imageName3" name="imageName3" type="hidden" value="${emkQualityImageEntities[2].imageName}"/>
				<img id="uploadimg3" onclick="findDetail('${emkQualityImageEntities[2].imageUrl }')"  src="${emkQualityImageEntities[2].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[2].imageUrl}" width="250" height="250">
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload>
				<c:if test="${emkQualityImageEntities[2].imageUrl ne null && emkQualityImageEntities[2].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[2].imageUrl }')">${emkQualityImageEntities[2].imageName}</a>]</c:if>

				<span id="imageId3"></span>
				<input id="imageUrl3" name="imageUrl3" type="hidden" value="${emkQualityImageEntities[2].imageUrl}"/>
			</td>
		</tr>
		<tr>
			<td class="value" align="center" >
				<input id="imageName4" name="imageName4" type="hidden" value="${emkQualityImageEntities[3].imageName}"/>
				<img id="uploadimg4" onclick="findDetail('${emkQualityImageEntities[3].imageUrl }')"  src="${emkQualityImageEntities[3].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[3].imageUrl}" width="250" height="250">
				<t:upload name="instruction4" id="instruction4" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess4" >
				</t:upload>
				<c:if test="${emkQualityImageEntities[3].imageUrl ne null && emkQualityImageEntities[3].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[3].imageUrl }')">${emkQualityImageEntities[3].imageName}</a>]</c:if>

				<span id="imageId4"></span>
				<input id="imageUrl4" name="imageUrl4" type="hidden" value="${emkQualityImageEntities[3].imageUrl}"/>
			</td>
			<td class="value" align="center" >
				<input id="imageName5" name="imageName5" type="hidden" value="${emkQualityImageEntities[4].imageName}"/>
				<img id="uploadimg5" onclick="findDetail('${emkQualityImageEntities[4].imageUrl }')"  src="${emkQualityImageEntities[4].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[4].imageUrl}" width="250" height="250">
				<t:upload name="instruction5" id="instruction5" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess5" >
				</t:upload>
				<c:if test="${emkQualityImageEntities[4].imageUrl ne null && emkQualityImageEntities[4].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[4].imageUrl }')">${emkQualityImageEntities[4].imageName}</a>]</c:if>

				<span id="imageId5"></span>
				<input id="imageUrl5" name="imageUrl5" type="hidden" value="${emkQualityImageEntities[4].imageUrl}"/>
			</td>
			<td class="value" align="center" >
				<input id="imageName6" name="imageName6" type="hidden" value="${emkQualityImageEntities[5].imageName}"/>
				<img id="uploadimg6" onclick="findDetail('${emkQualityImageEntities[5].imageUrl }')"  src="${emkQualityImageEntities[5].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[5].imageUrl}" width="250" height="250">
				<t:upload name="instruction6" id="instruction6" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess6" >
				</t:upload>
				<c:if test="${emkQualityImageEntities[5].imageUrl ne null && emkQualityImageEntities[5].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[5].imageUrl }')">${emkQualityImageEntities[5].imageName}</a>]</c:if>

				<span id="imageId6"></span>
				<input id="imageUrl6" name="imageUrl6" type="hidden" value="${emkQualityImageEntities[5].imageUrl}"/>
			</td>
		</tr>
			<tr>
				<td class="value" align="center" >
					<input id="imageName7" name="imageName7" type="hidden" value="${emkQualityImageEntities[6].imageName}"/>
					<img id="uploadimg7" onclick="findDetail('${emkQualityImageEntities[6].imageUrl }')"  src="${emkQualityImageEntities[6].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[6].imageUrl}" width="250" height="250">
					<t:upload name="instruction7" id="instruction7" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess7" >
					</t:upload>
					<c:if test="${emkQualityImageEntities[6].imageUrl ne null && emkQualityImageEntities[6].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[6].imageUrl }')">${emkQualityImageEntities[6].imageName}</a>]</c:if>

					<span id="imageId7"></span>
					<input id="imageUrl7" name="imageUrl7" type="hidden" value="${emkQualityImageEntities[6].imageUrl}"/>
				</td>
				<td class="value" align="center" >
					<input id="imageName8" name="imageName8" type="hidden" value="${emkQualityImageEntities[7].imageName}"/>
					<img id="uploadimg8" onclick="findDetail('${emkQualityImageEntities[7].imageUrl }')"  src="${emkQualityImageEntities[7].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[7].imageUrl}" width="250" height="250">
					<t:upload name="instruction8" id="instruction8" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess8" >
					</t:upload>
					<c:if test="${emkQualityImageEntities[7].imageUrl ne null && emkQualityImageEntities[7].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[7].imageUrl }')">${emkQualityImageEntities[7].imageName}</a>]</c:if>

					<span id="imageId8"></span>
					<input id="imageUrl8" name="imageUrl8" type="hidden" value="${emkQualityImageEntities[7].imageUrl}"/>
				</td>
				<td class="value" align="center" >
					<input id="imageName9" name="imageName9" type="hidden" value="${emkQualityImageEntities[8].imageName}"/>
					<img id="uploadimg9" onclick="findDetail('${emkQualityImageEntities[8].imageUrl }')"  src="${emkQualityImageEntities[8].imageUrl eq null ? 'images/bjlogo.png':emkQualityImageEntities[8].imageUrl}" width="250" height="250">
					<t:upload name="instruction9" id="instruction9" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile2" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess9" >
					</t:upload>
					<c:if test="${emkQualityImageEntities[8].imageUrl ne null && emkQualityImageEntities[8].imageUrl ne ''}">[<a href="javascript:findDetail('${emkQualityImageEntities[8].imageUrl }')">${emkQualityImageEntities[8].imageName}</a>]</c:if>

					<span id="imageId9"></span>
					<input id="imageUrl9" name="imageUrl9" type="hidden" value="${emkQualityImageEntities[8].imageUrl}"/>
				</td>
			</tr>
		<tr>
			<td colspan="3" id="instructionfile2" class="value">
			</td>
		</tr>

	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");
		$("#instruction4-button").css("width","70px");
		$("#instruction5-button").css("width","70px");
		$("#instruction6-button").css("width","70px");
		$("#instruction7-button").css("width","70px");
		$("#instruction8-button").css("width","70px");
		$("#instruction9-button").css("width","70px");
	});
</script>
