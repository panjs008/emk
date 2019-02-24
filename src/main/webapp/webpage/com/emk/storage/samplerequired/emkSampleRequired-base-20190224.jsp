<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			$("#type").change(function(){
				varval = $('#type').val();
				if(varval == 'cy'){
					$("#orderNo").attr("ignore","ignore");
					$("#pirceNo").removeAttr("ignore");
				}else{
					/* $("#settb").css("display","none");
					 $("#settb2").css("display","none");

					 $("#tk0").attr("ignore","ignore");
					 $("#settb").find(":text,textarea").attr("ignore","ignore");
					 $("#settb2").find(":text,textarea").attr("ignore","ignore");*/
					$("#pirceNo").attr("ignore","ignore");
					$("#orderNo").removeAttr("ignore");
				}
			});


		});

		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#khyyId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#sampleSizeUrl").val(d.attributes.url);
			$("#sampleSize").val(d.attributes.name);
			$("#ccbId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg2").attr('src',d.attributes.url);

		}


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkYptzdController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSamplePage.id }"/>
	<input id="flag" name="flag" type="hidden" value="${param.flag}"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					报价单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="pirceNo" name="pirceNo" datatype="*" value="${emkSamplePage.pirceNo}" type="text" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报价单号</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="orderNo" name="orderNo" datatype="*"  value="${emkSamplePage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
		</tr>
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
				<select class="form-control select2" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
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
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
			<%--<tr>
				<td align="right">
					<label class="Validform_label">
						打样需求单号:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="xqdh" name="xqdh" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">打样需求单编号</label>
				</td>
			</tr>--%>
		<tr>
			<td align="right">
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="type" field="type"  typeGroupCode="sampletype" datatype="*" defaultVal="default" hasLabel="false" title="样品类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工厂:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="factoryCode" field="factoryCode" typeGroupCode="gongchang" datatype="*" defaultVal="default" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂</label>
			</td>
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
		</tr>
		<tr>
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

			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="default" hasLabel="false" title="工艺类型"></t:dictSelect>
					<%--<input id="projectNo" name="projectNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					通知单日期:
				</label>
			</td>
			<td class="value">
				<input id="kdTime" name="kdTime" value="${kdDate}" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">通知单日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="receviceDate" name="receviceDate" readonly  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdTime\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					布面克重:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value">
				<input id="customSample" name="customSample" type="hidden" />
				<img id="uploadimg" src="${emkSamplePage.customSampleUrl eq null ? 'images/bjlogo.png':emkSamplePage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>
				<span id="khyyId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" />
			</td>

			<td align="right">
				<label class="Validform_label">
					尺寸表:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg2" src="${emkSamplePage.sampleSizeUrl eq null ? 'images/bjlogo.png':emkSamplePage.sampleSizeUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>
				<span id="ccbId"></span>
				<input id="sampleSizeUrl" name="sampleSizeUrl" type="hidden" />
				<input id="sampleSize" name="sampleSize" type="hidden" />
			</td>

		</tr>
		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>


			<%--	<tr>
                  <td align="right">
                      <label class="Validform_label">
                          包装方案:
                      </label>
                  </td>
                  <td class="value" colspan="3">
                      <textarea  id="packing" style="width:95%;height:70px" class="inputxt" rows="5" name="packing"></textarea>
                      <span class="Validform_checktip"></span>
                      <label class="Validform_label" style="display: none;">包装方案</label>
                  </td>
              </tr>
          <tr>
              <td align="right">
                  <label class="Validform_label">
                      工艺要求:
                  </label>
              </td>
              <td class="value" colspan="3">
                  <textarea  id="requiretext" style="width:95%;height:70px" class="inputxt" rows="5" name="requiretext"></textarea>
                      <span class="Validform_checktip"></span>
                      <label class="Validform_label" style="display: none;">要求</label>
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
              </tr>--%>

		<tr>
			<td align="right">
				<label class="Validform_label">
					下机克重:
				</label>
			</td>
			<td class="value">
				<input id="xjkz" name="xjkz" datatype="d" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单件所需时间:
				</label>
			</td>
			<td class="value">
				<input id="djsxTime" name="djsxTime"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件所需时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					机台日产量:
				</label>
			</td>
			<td class="value">
				<input id="jtrcl" name="jtrcl"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">机台日产量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					前道损耗率:
				</label>
			</td>
			<td class="value">
				<input id="qdshl" name="qdshl" datatype="d"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">前道损耗率</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					后道损耗率:
				</label>
			</td>
			<td class="value">
				<input id="hdshl" name="hdshl"  datatype="d" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">后道损耗率</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
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