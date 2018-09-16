<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>报修单表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(
				function() {
					<c:if test="${repairPorcess.taskDefinitionKey ne ''}">
						setReadOnly($("#baseInfoId"));
					</c:if>

					<%--<c:if test="${repairPorcess.taskDefinitionKey ne 'serviceTask'}">
						setReadOnly($("#serviceTask"));
					</c:if>
					<c:if test="${repairPorcess.taskDefinitionKey ne 'checkTask'}">
						setReadOnly($("#checkTask"));
					</c:if>
					<c:if test="${repairPorcess.taskDefinitionKey ne 'zxRepair'}">
						setReadOnly($("#zxRepair"));
					</c:if>
					<c:if test="${repairPorcess.taskDefinitionKey ne 'saveRecord'}">
						setReadOnly($("#saveRecord"));
					</c:if>
					<c:if test="${repairPorcess.taskDefinitionKey ne 'qdService'}">
						setReadOnly($("#qdService"));
					</c:if>
					<c:if test="${repairPorcess.taskDefinitionKey ne 'normalRepairTask'}">
						setReadOnly($("#normalRepairTask"));
					</c:if>
					<c:if test="${repairPorcess.taskDefinitionKey ne 'systemToService'}">
						setReadOnly($("#systemToService"));
					</c:if>--%>
				});

		function setReadOnly(obj) {
			obj.find(":text,textarea").attr("disabled",true);
			obj.find(":checkbox,:radio,select").attr("disabled",true);
			//obj.find("a").hide();
			obj.find(".date").removeClass("date");
			//obj.find(".required").removeClass("required");
		}

		var myloader = function(param,success,error){
			var q = param.q || '';
			if (q.length <= 1){return false}
			$.ajax({
				url: 'ymkCustomController.do?findCustomList',
				dataType: 'json',
				data: {
					q: q,
					maxRows: 20
				},
				success: function(data){
					var items = $.map(data, function(item,index){
						return {
							id: item.id,
							name: item.name
						};
					});
					success(items);
				},
				error: function(){
					///alert("1234");
					//error.apply(this, arguments);
				}
			});
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="uRepairController.do?doUpdate"  tiptype="1" >
	<input id="id" name="id" type="hidden" value="${uRepairPage.id }"/>
	<table id="baseInfoId" style="width: 100%;margin-bottom:4px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					报修单号:
				</label>
			</td>
			<td class="value" width="30%">
				<input id="repairNum" name="repairNum" datatype="*" readonly value="${uRepairPage.repairNum}" type="text" style="width: 150px" class="inputxt"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报修单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					报修类型:
				</label>
			</td>
			<td class="value" width="30%">
				<t:dictSelect id="bxType" field="bxType" typeGroupCode="bxType" datatype="*" defaultVal="${uRepairPage.bxType}" hasLabel="false" title="报修类型"></t:dictSelect>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报修类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					报修人:
				</label>
			</td>
			<td class="value" width="30%">
				<input id="userName" name="userName" readonly type="text" value="${uRepairPage.userName }" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报修人</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					报修时间:
				</label>
			</td>
			<td class="value" width="30%">
				<input id="applyDate" name="applyDate" readonly type="text"  value="${uRepairPage.applyDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报修时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="relationer" name="relationer" type="text" value="${uRepairPage.relationer }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					设备编号:
				</label>
			</td>
			<td class="value">
				<input id="proNum" name="proNum" type="text" value="${uRepairPage.proNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					设备名称:
				</label>
			</td>
			<td class="value">
				<input id="proId" name="proId" type="hidden" value="${uRepairPage.proId }" style="width: 100px" class="inputxt"  ignore="ignore" />
				<input id="proZnName" name="proZnName" value="${uRepairPage.proZnName }" type="text" style="width: 100px" class="inputxt"  ignore="ignore" />

				<t:choose  hiddenName="proId"  hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="750px" height="500px"
						   icon="icon-search" title="选择商品" textname="proNum,proType,proTypeName,proZnName,brand,unit" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品名称</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					设备类型:
				</label>
			</td>
			<td class="value">
				<input id="proType" name="proType" type="hidden" value="${uRepairPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proTypeName" name="proTypeName" type="text" value="${uRepairPage.proTypeName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">设备类型</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					设备型号:
				</label>
			</td>
			<td class="value">
				<input id="brand" name="brand" type="text" style="width: 150px"  value="${uRepairPage.brand }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">设备型号</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					报修数量:
				</label>
			</td>
			<td class="value">
				<input id="total" name="total" type="text" style="width: 150px" value="${uRepairPage.total }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">报修数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit" type="text" style="width: 150px" value="${uRepairPage.unit }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>

		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					故障描述:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="fault" style="width:95%;height:80px" class="inputxt" rows="5" name="fault">${uRepairPage.fault }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">故障描述</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="3" name="remark">${uRepairPage.remark }</textarea>
			</td>
		</tr>


	</table>
<%--<c:if test="${repairPorcess.taskDefinitionKey eq 'checkTask' || fn:contains(processTaskNames, 'checkTask')}">
	<strong >&nbsp;设备检测</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />
	<table id="checkTask" style="width: 100%;margin-top:2px;margin-bottom: 4px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					检测人:
				</label>
			</td>
			<td class="value" width="30%">
				<input id="checkUserId" name="checkUserId"  type="hidden" value="${uRepairPage.checkUserId eq null ? CUR_USER.id:uRepairPage.checkUserId}" style="width: 150px" class="inputxt"  />

				<input id="checkUserName" name="checkUserName" readonly type="text" value="${uRepairPage.checkUserName eq null ? CUR_USER.realName:uRepairPage.checkUserName}" style="width: 150px" class="inputxt"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">检测人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					检测时间:
				</label>
			</td>
			<td class="value" width="30%">
				<input id="checkTime" name="checkTime" readonly datatype="*" type="text"  value="${uRepairPage.checkTime eq null ? applyDate:uRepairPage.checkTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">检测时间</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否自修:
				</label>
			</td>
			<td class="value" width="30%" colspan="3">
				<input name="isFinish" type="radio" datatype="*" <c:if test="${uRepairPage.isFinish eq '0'}">checked="true"</c:if> value="0">
				是
				<input name="isFinish" type="radio" datatype="*"  <c:if test="${uRepairPage.isFinish eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否自修</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					检测情况:
				</label>
			</td>
			<td class="value" colspan="3"><textarea id="checkContent" style="width:95%;height:80px" class="inputxt" rows="5" name="checkContent">${uRepairPage.checkContent }</textarea>
			</td>
		</tr>

	</table>
</c:if>

	<c:if test="${repairPorcess.taskDefinitionKey eq 'zxRepair'  || fn:contains(processTaskNames, 'zxRepair')}">
		<strong >&nbsp;自行维修</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />
		<table id="zxRepair" style="width: 100%;margin-top:2px;margin-bottom: 4px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						维修人:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="repairUserId" name="repairUserId"  type="hidden" value="${uRepairPage.repairUserId eq null ? CUR_USER.id:uRepairPage.repairUserId}" style="width: 150px" class="inputxt"  />

					<input id="repairUserName" name="repairUserName" readonly type="text" value="${uRepairPage.repairUserName eq null ? CUR_USER.realName:uRepairPage.repairUserName}" style="width: 150px" class="inputxt"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">维修人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						维修时间:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="repairTime" name="repairTime" readonly datatype="*" type="text"  value="${uRepairPage.repairTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />

					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">维修时间</label>
				</td>


			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						维修内容:
					</label>
				</td>
				<td class="value" colspan="3"><textarea id="repairContent" style="width:95%;height:80px" class="inputxt" rows="5" name="repairContent">${uRepairPage.repairContent }</textarea>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${repairPorcess.taskDefinitionKey eq 'saveRecord' || fn:contains(processTaskNames, 'saveRecord')}">
		<strong >&nbsp;考核记录</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />
		<table id="saveRecord" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						被考核人:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="kaoheUserId" name="kaoheUserId"  type="hidden" value="${uRepairPage.kaoheUserId eq null ? CUR_USER.id:uRepairPage.kaoheUserId}" style="width: 150px" class="inputxt"  />

					<input id="kaoheUserName" name="kaoheUserName" readonly type="text" value="${uRepairPage.kaoheUserName eq null ? CUR_USER.realName:uRepairPage.kaoheUserName}" style="width: 150px" class="inputxt"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">被考核人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						考核时间:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="kaoheTime" name="kaoheTime" readonly datatype="*" type="text"  value="${uRepairPage.kaoheTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">考核时间</label>
				</td>

			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						考核备注:
					</label>
				</td>
				<td class="value" colspan="3"><textarea id="kaoheRemark" style="width:95%;height:80px" class="inputxt" rows="5" name="kaoheRemark">${uRepairPage.kaoheRemark }</textarea>
				</td>
			</tr>

		</table>
	</c:if>
	<c:if test="${repairPorcess.taskDefinitionKey eq 'serviceTask' || fn:contains(processTaskNames, 'serviceTask')}">
		<strong >&nbsp;服务台</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />
		<table id="serviceTask" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						保修类别:
					</label>
				</td>
				<td class="value" width="30%">
					<input name="repairType" type="radio" datatype="*" <c:if test="${uRepairPage.repairType eq '0'}">checked="true"</c:if> value="0">
					保外
					<input name="repairType" type="radio" datatype="*"  <c:if test="${uRepairPage.repairType eq '1'}">checked="true"</c:if> value="1">
					保内
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">保修类别</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						服务商:
					</label>
				</td>
				<td class="value">
					<input id="cusId" datatype="*" name="cusId" type="hidden" value="${uRepairPage.cusId}" />

					<input class="easyui-combobox" name="customIdVal" value="${uRepairPage.cusName}" style="width:145px;" data-options="
                    loader: myloader,
                    mode: 'remote',
                    valueField: 'id',
                    textField: 'name',
                     onSelect: function(rec){
							$('#cusId').val(rec.id);
						}
                    ">

					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">客户名称</label>
				</td>
			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						派单日期:
					</label>
				</td>
				<td class="value" width="30%" colspan="3">
					<input id="sendDatae" name="sendDatae" readonly datatype="*" type="text"  value="${uRepairPage.sendDatae eq null ? applyDate:uRepairPage.sendDatae}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">派单日期</label>
				</td>

			</tr>
		</table>
	</c:if>
	<c:if test="${repairPorcess.taskDefinitionKey eq 'qdService' || fn:contains(processTaskNames, 'qdService')}">
		<strong >&nbsp;抢单信息</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />
		<table id="qdService" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">

			<tr>
				<td align="right">
					<label class="Validform_label">
						接单人:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="recevieUserId" name="recevieUserId"  type="hidden" value="${uRepairPage.recevieUserId eq null ? CUR_USER.id:uRepairPage.recevieUserId}"  class="inputxt"  />
					<input id="recevieUserName" name="recevieUserName" readonly type="text" value="${uRepairPage.recevieUserName eq null ? CUR_USER.realName:uRepairPage.recevieUserName}" style="width: 150px" class="inputxt"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">接单人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						接单时间:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="recevieDate" name="recevieDate" readonly datatype="*" type="text"  value="${uRepairPage.recevieDate eq null ? applyDate:uRepairPage.recevieDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">接单时间</label>
				</td>

			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						备注:
					</label>
				</td>
				<td class="value" colspan="3"><textarea id="recevieRemark" style="width:95%;height:80px" class="inputxt" rows="5" name="recevieRemark">${uRepairPage.recevieRemark }</textarea>
				</td>
			</tr>

		</table>
	</c:if>

	<c:if test="${repairPorcess.taskDefinitionKey eq 'systemToService' || fn:contains(processTaskNames, 'systemToService')}">
		<strong >&nbsp;中标商派单</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />
		<table id="systemToService" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						处理类型:
					</label>
				</td>
				<td class="value" width="30%" colspan="3">
					<input name="csType" type="radio" datatype="*" <c:if test="${uRepairPage.csType eq '0'}">checked="true"</c:if> value="0">
					委托
					<input name="csType" type="radio" datatype="*"  <c:if test="${uRepairPage.csType eq '1'}">checked="true"</c:if> value="1">
					自有
					<input name="csType" type="radio" datatype="*"  <c:if test="${uRepairPage.csType eq '2'}">checked="true"</c:if> value="2">
					原厂
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">处理类型</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						接单人:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="recevieUserId2" name="recevieUserId2"  type="hidden" value="${uRepairPage.recevieUserId eq null ? CUR_USER.id:uRepairPage.recevieUserId}"  class="inputxt"  />
					<input id="recevieUserName2" name="recevieUserName2" readonly type="text" value="${uRepairPage.recevieUserName eq null ? CUR_USER.realName:uRepairPage.recevieUserName}" style="width: 150px" class="inputxt"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">接单人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						接单时间:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="recevieDate2" name="recevieDate2" readonly datatype="*" type="text"  value="${uRepairPage.recevieDate eq null ? applyDate:uRepairPage.recevieDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">接单时间</label>
				</td>

			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						备注:
					</label>
				</td>
				<td class="value" colspan="3"><textarea id="recevieRemark2" style="width:95%;height:80px" class="inputxt" rows="5" name="recevieRemark2">${uRepairPage.recevieRemark }</textarea>
				</td>
			</tr>

		</table>
	</c:if>
	<c:if test="${repairPorcess.taskDefinitionKey eq 'normalRepairTask' || fn:contains(processTaskNames, 'normalRepairTask')}">
		<strong >&nbsp;正常维修</strong><hr style="height:1px;border:none;border-top:1px solid #E8E8E8;margin-top:2px;" />

		<table id="normalRepairTask" style="width: 100%;margin-top:2px;margin-bottom: 4px" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">
						维修人:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="workUserId" name="workUserId"  type="hidden" value="${uRepairPage.repairUserId eq null ? CUR_USER.id:uRepairPage.repairUserId}" style="width: 150px" class="inputxt"  />

					<input id="workUserName" name="workUserName" readonly type="text" value="${uRepairPage.repairUserName eq null ? CUR_USER.realName:uRepairPage.repairUserName}" style="width: 150px" class="inputxt"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">维修人</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						维修时间:
					</label>
				</td>
				<td class="value" width="30%">
					<input id="workTime" name="workTime" readonly datatype="*" type="text"  value="${uRepairPage.repairTime eq null ? applyDate:uRepairPage.repairTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 150px" class="Wdate"   />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">维修时间</label>
				</td>

			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						维修情况:
					</label>
				</td>
				<td class="value" colspan="3"><textarea id="workContent" style="width:95%;height:80px" class="inputxt" rows="5" name="workContent">${uRepairPage.repairContent }</textarea>
				</td>
			</tr>

		</table>
	</c:if>--%>
</t:formvalid>
</body>
<script src = "webpage/com/process/repair/uRepair.js"></script>
