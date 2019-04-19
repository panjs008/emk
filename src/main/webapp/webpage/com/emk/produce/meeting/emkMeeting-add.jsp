<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>产前会议通知表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>
	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
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
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMeetingController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkMeetingPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" >
				<label class="Validform_label">
					产前会议记录表编号:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="meetingNo" name="meetingNo" value="${emkMeetingPage.meetingNo }"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产前会议记录表编号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					会议时间:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="meetingDate" name="meetingDate" value="${emkMeetingPage.meetingDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">会议时间</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					会议记录人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="recorder" name="recorder"  type="text" value="${emkMeetingPage.recorder }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">会议记录人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					会议地点:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="address" name="address"   type="text" value="${emkMeetingPage.address }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">会议地点</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					参会人员:
				</label>
			</td>
			<td class="value" colspan="3">
					<%--<input id="partUsers" name="partUsers"  type="text"  style="width: 150px" class="inputxt"  ignore="ignore" />--%>
				<input id="realName" name="realName" type="text" readonly style="width: 300px" class="inputxt" value="${emkMeetingPage.partUsers }">
				<input name="userName"   type="hidden"  id="userName" type="text"  value="${emkMeetingPage.partUserIds }"/>
				<t:choose  hiddenName="userName"  hiddenid="userName" url="userController.do?userSelect0" name="userList0" width="700px" height="500px"
						   icon="icon-search" title="选择处理人" textname="realName" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">参会人员</label>
			</td>

		</tr>

		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%" colspan="3">
				<input id="cusName" name="cusName" readonly type="text" value="${emkMeetingPage.cusName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,developer,developerName,tracer,tracerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMeetingPage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMeetingPage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId" value="${emkMeetingPage.businesseDeptId }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" value="${emkMeetingPage.sampleNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkMeetingPage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkMeetingPage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value">
				<input id="sampleNoDesc" name="sampleNoDesc" type="text" value="${emkMeetingPage.sampleNoDesc }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMeetingPage.tracer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  value="${emkMeetingPage.tracerName }" type="hidden"  />
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
				<input id="proTypeTree" value="${emkMeetingPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*" value="${emkMeetingPage.proTypeName }"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkMeetingPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
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
				<input id="developer" name="developer" readonly value="${emkMeetingPage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  value="${emkMeetingPage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkMeetingPage.gyzl}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					出货日期:
				</label>
			</td>
			<td class="value">
				<input id="outDate" name="outDate" value="${emkMeetingPage.outDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出货日期</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					订单号:
				</label>
			</td>
			<td class="value" >
				<input id="orderNo" name="orderNo"  type="text" value="${emkMeetingPage.orderNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					中期检查时间:
				</label>
			</td>
			<td class="value">
				<input id="zqjcDate" name="zqjcDate" value="${emkMeetingPage.zqjcDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">中期检查时间</label>
			</td>
		</tr>
		<tr>
			<td class="value" colspan="2">

			</td>
			<td align="right" >
				<label class="Validform_label">
					尾期检查时间:
				</label>
			</td>
			<td class="value">
				<input id="wqjcDate" name="wqjcDate" value="${emkMeetingPage.wqjcDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">尾期检查时间</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					会议记录:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="content" style="width:95%;height:50px" class="inputxt" rows="3" name="content">${emkMeetingPage.content }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">会议记录</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					技术部布面意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="jsbbmAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="jsbbmAdvice">${emkMeetingPage.jsbbmAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">技术部布面意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					技术部染色意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="jsbranAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="jsbranAdvice">${emkMeetingPage.jsbranAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">技术部染色意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					缝制部意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="fzAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="fzAdvice">${emkMeetingPage.fzAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">缝制部意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					烫标组意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="tbzAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="tbzAdvice">${emkMeetingPage.tbzAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">烫标组意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					包装组意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="bzAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="bzAdvice">${emkMeetingPage.bzAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装组意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					采购部意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="cgAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="cgAdvice">${emkMeetingPage.cgAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">采购部意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					业务部意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="ybAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="ybAdvice">${emkMeetingPage.ybAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					质检组意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="zjzAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="zjzAdvice">${emkMeetingPage.zjzAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">质检组意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					计划部意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="jhbAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="jhbAdvice">${emkMeetingPage.jhbAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">计划部意见</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					生产部综合意见:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="scbzhAdvice" style="width:95%;height:50px" class="inputxt" rows="3" name="scbzhAdvice">${emkMeetingPage.scbzhAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产部综合意见</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>