<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>生产问题管理单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#scanUrl").val(d.attributes.url);
			$("#scanName").val(d.attributes.name);

			$("#scanId").html(d.attributes.name);

		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProduceQaController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkProduceQaPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value"  align="right" colspan="5">
				<label class="Validform_label">
					生产问题函号:
				</label>
			</td>
			<td class="value" >
				<input id="qaNo" name="qaNo" value="${emkProduceQaPage.qaNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产问题函号</label>
			</td>
		</tr>
		<tr>
			<td class="value" align="right" colspan="5">
				<label class="Validform_label">
					发函日期:
				</label>
			</td>
			<td class="value">
				<input id="qaDate" name="qaDate" value="${emkProduceQaPage.qaDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提交日期</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="cusName" name="cusName" value="${emkProduceQaPage.cusName }" readonly type="text" style="width: 150px" class="inputxt"  datatype="*" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkProduceQaPage.sampleNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产合同号:
				</label>
			</td>
			<td class="value" >
				<input id="produceNum" name="produceNum"  value="${emkProduceQaPage.produceNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产合同号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly value="${emkProduceQaPage.cusNum }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo" value="${emkProduceQaPage.orderNo }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" value="${emkProduceQaPage.gysCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" value="${emkProduceQaPage.gys }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" value="${emkProduceQaPage.businesseDeptName }" name="businesseDeptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkProduceQaPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkProduceQaPage.sampleNoDesc }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkProduceQaPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" value="${emkProduceQaPage.businesser }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  value="${emkProduceQaPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					颜色:
				</label>
			</td>
			<td class="value" >
				<input id="color" name="color" value="${emkProduceQaPage.color }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">颜色</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkProduceQaPage.proTypeName }" >
				<input id="proTypeName" name="proTypeName" value="${emkProduceQaPage.proTypeName }"  datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" value="${emkProduceQaPage.proType }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" value="${emkProduceQaPage.tracer }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName" value="${emkProduceQaPage.tracerName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					出厂日期:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" value="${emkProduceQaPage.outDate }"  readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大货交期</label>
			</td>
			<td class="value" colspan="2"></td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" value="${emkProduceQaPage.developer }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  value="${emkProduceQaPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>


			<%--<tr>
                <td align="right">
                    <label class="Validform_label">
                        数量:
                    </label>
                </td>
                <td class="value" colspan="3">
                    <input id="sumTotal" name="sumTotal"  value="${emkProduceQaPage.sumTotal }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
                    <span class="Validform_checktip"></span>
                    <label class="Validform_label" style="display: none;">数量</label>
                </td>
            </tr>--%>



		<tr>
			<td align="right">
				<label class="Validform_label">
					发函部门:
				</label>
			</td>
			<td class="value" >
				<input id="sendDeptCode" name="sendDeptCode" value="${emkProduceQaPage.sendDeptCode }" type="hidden" readonly style="width: 180px" class="inputxt" >
				<input id="sendDeptName" name="sendDeptName" value="${emkProduceQaPage.sendDeptName }" type="text" readonly style="width: 250px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发函部门</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					发函人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sender" name="sender" type="text" value="${emkProduceQaPage.sender }" readonly style="width: 60%" class="inputxt" >
				<input name="senderUserNames"   type="hidden" value="${emkProduceQaPage.senderUserNames }" id="senderUserNames" type="text"  />
				<t:choose  hiddenName="senderUserNames"  hiddenid="userName" url="userController.do?userdept0" name="userList0" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="sender,sendDeptName,sendDeptCode" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发函人</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					收函部门:
				</label>
			</td>
			<td class="value" >
				<input id="recevieDeptCode" name="recevieDeptCode" value="${emkProduceQaPage.recevieDeptCode }" type="hidden" readonly style="width: 150px" class="inputxt" >
				<input id="recevieDeptName" name="recevieDeptName" value="${emkProduceQaPage.recevieDeptName }"type="text" readonly style="width: 250px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收函部门</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					收函人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="recevier" name="recevier" type="text" value="${emkProduceQaPage.recevier }" readonly style="width: 60%" class="inputxt" >
				<input name="recevierUserNames"   type="hidden"  value="${emkProduceQaPage.recevierUserNames }" id="recevierUserNames" type="text"  />
				<t:choose  hiddenName="recevierUserNames"  hiddenid="recevierUserNames" url="userController.do?userdept1" name="userList0" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="recevier,recevieDeptName,recevieDeptCode" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收函人</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					抄送部门:
				</label>
			</td>
			<td class="value" >
				<input id="copyDeptCode" name="copyDeptCode" type="hidden" value="${emkProduceQaPage.copyDeptCode }" readonly style="width: 150px" class="inputxt" >
				<input id="copyDeptName" name="copyDeptName" type="text" value="${emkProduceQaPage.copyDeptName }" readonly style="width: 250px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收函部门</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					抄送收函人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="copyer" name="copyer" type="text" readonly value="${emkProduceQaPage.copyer }" style="width: 60%" class="inputxt" >
				<input name="copyerUserNames"   type="hidden" value="${emkProduceQaPage.copyerUserNames }" id="copyerUserNames" type="text"  />
				<t:choose  hiddenName="copyerUserNames"  hiddenid="copyerUserNames" url="userController.do?userdept2" name="userList0" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="copyer,copyDeptName,copyDeptCode" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收函人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					问题描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="qaDesc" style="width:95%;height:70px" class="inputxt" rows="5" name="qaDesc">${emkProduceQaPage.qaDesc }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">问题描述</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right">
				<label class="Validform_label">
					问题责任人:
				</label>
			</td>
			<td class="value">
				<input id="qazrer" name="qazrer" type="text" value="${emkProduceQaPage.qazrer }"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">问题责任人</label>
			</td>
			<td class="value" colspan="2"></td>
		</tr>
		<tr>
			<td class="value" colspan="2"></td>
			<td align="right">
				<label class="Validform_label">
					发件人:
				</label>
			</td>
			<td class="value">
				<input id="fjer" name="fjer" type="text" value="${emkProduceQaPage.fjer }"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发件人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					业务审核:
				</label>
			</td>
			<td class="value">
				<input id="ywsher" name="ywsher" type="text" value="${emkProduceQaPage.ywsher }"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务审核</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					经济损失:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="loss" style="width:95%;height:70px" class="inputxt" rows="5" name="loss">${emkProduceQaPage.loss }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">经济损失</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					解决方案:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="solve" style="width:95%;height:50px" class="inputxt" rows="3" name="solve">${emkProduceQaPage.solve }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">解决方案</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					方案执行人:
				</label>
			</td>
			<td class="value">
				<input id="solver" name="solver" type="text" value="${emkProduceQaPage.solver }"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">方案执行人</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					方案批准人:
				</label>
			</td>
			<td class="value">
				<input id="solverPzer" name="solverPzer" type="text" value="${emkProduceQaPage.solverPzer }"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">方案批准人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					相关抄送人意见:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="copyerAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="copyerAdvice">${emkProduceQaPage.copyerAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">相关抄送人意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					财务意见:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="cwAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="cwAdvice">${emkProduceQaPage.cwAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">财务意见</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="4"></td>
			<td align="right">
				<label class="Validform_label">
					财务批准人:
				</label>
			</td>
			<td class="value">
				<input id="cwPzer" name="cwPzer" type="text" value="${emkProduceQaPage.cwPzer }"  style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">财务批准人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					总经理意见:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="zjlAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="zjlAdvice">${emkProduceQaPage.zjlAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总经理意见</label>
			</td>
		</tr>
		<tr id="dgrImageDiv">
			<td align="right">
				<label class="Validform_label">
					生产问题函状态:
				</label>
			</td>
			<td class="value">
				<select id="qaState" name="qaState">
					<option value="0">未完成</option>
					<option value="1">已完成</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产问题函状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					扫描件:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="scanName" name="scanName" type="hidden" value="${emkProduceQaPage.scanName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="scanUrl" name="scanUrl" type="hidden" value="${emkProduceQaPage.scanUrl }"/>
				<span id="scanId"></span>
				[<a href="emkProduceQaController.do?dowaLoadFile&id=${emkProduceQaPage.id}&scanUrl=${emkProduceQaPage.scanUrl }">${emkProduceQaPage.scanName }</a>]
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.doc;*.xls;*.docx;*.xlsx;*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
			</td>

		</tr>
		<tr>
			<td colspan="6" id="instructionfile" class="value">
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {

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