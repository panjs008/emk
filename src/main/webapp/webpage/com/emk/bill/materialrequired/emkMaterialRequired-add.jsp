<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>面料采购需求单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
	<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
	<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {

		});



		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html(d.attributes.name);
			$("#uploadimg0").attr('src',d.attributes.url);

		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#boxImageUrl").val(d.attributes.url);
			$("#boxImage").val(d.attributes.name);
			$("#boxImageId").html(d.attributes.name);
			$("#uploadimg1").attr('src',d.attributes.url);

		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialRequiredController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkMaterialRequiredPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesser" name="businesser" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户编号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户编号</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="tracer" name="tracer" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="developer" name="developer" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="">
				<input id="proTypeName" name="proTypeName" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					提交日期:
				</label>
			</td>
			<td class="value">
				<input id="orderTime" name="orderTime" readonly value="${kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="recevieDate" name="recevieDate" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>

		</tr>

		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					图片:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="customSample" name="customSample" type="hidden" />
				<img id="uploadimg0" src="${emkMaterialRequiredPage.customSampleUrl eq null ? 'images/bjlogo.png':emkMaterialRequiredPage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="default" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value">
				<input id="levelDays" name="levelDays"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总数量:
				</label>
			</td>
			<td class="value">
				<input id="sumTotal" name="sumTotal"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					总金额:
				</label>
			</td>
			<td class="value">
				<input id="sumMoney" name="sumMoney"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					尺码范围:
				</label>
			</td>
			<td class="value">
				<input id="sizeFw" name="sizeFw"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尺码范围</label>
			</td>
		</tr>
		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					单价:
				</label>
			</td>
			<td class="value">
				<input id="price" name="price"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					币种:
				</label>
			</td>
			<td class="value">
				<input id="bz" name="bz"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="sampleNoDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="sampleNoDesc"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
			<%--<tr>
                <td align="center"  colspan="4" height="30px;">
                    <label class="Validform_label">
                        包装方式
                    </label>
                </td>
            </tr>--%>
		<tr id="dgrImageDiv">
			<td align="right" rowspan="5">
				<label class="Validform_label">
					包装效果图:
				</label>
			</td>
			<td class="value" rowspan="5">
				<input id="boxImage" name="boxImage" type="hidden" />
				<img id="uploadimg1" src="${emkMaterialRequiredPage.boxImageUrl eq null ? 'images/bjlogo.png':emkMaterialRequiredPage.boxImageUrl}" width="150" height="150">
				<t:upload name="instruction1" id="instruction1" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile1" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess1" >
				</t:upload>
				<span id="boxImageId"></span>
				<input id="boxImageUrl" name="boxImageUrl" type="hidden" />
			</td>
			<td align="right">
				<label class="Validform_label">
					包装方式:
				</label>
			</td>
			<td class="value">
				<input id="bzfs" name="bzfs"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单件:
				</label>
			</td>
			<td class="value">
				<input id="one" name="one"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装效果图</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					胶袋:
				</label>
			</td>
			<td class="value">
				<input id="polybag" name="polybag"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">胶袋</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					装箱:
				</label>
			</td>
			<td class="value">
				<input id="boxup" name="boxup"   type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">装箱</label>
			</td>
		</tr>

		<tr>
			<td colspan="2" id="instructionfile1" class="value">&nbsp;
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					预计中期验货日期:
				</label>
			</td>
			<td class="value">
				<input id="zqyhDate" name="zqyhDate"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计中期验货日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					预计尾期验货日期:
				</label>
			</td>
			<td class="value">
				<input id="wqyhDate" name="wqyhDate" readonly   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">预计尾期验货日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					船样状态:
				</label>
			</td>
			<td class="value">
				<input id="cystate" name="cystate"    type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">船样状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					实际出厂日期:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" readonly   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际出厂日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
		$("#instruction1-button").css("width","70px");

		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 157,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>