<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户手册</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header.jsp"%>
	<script src="${webRoot}/context/gys.js"></script>

	<script type="text/javascript">
		//编写自定义JS代码


	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkCheckFactoryController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkCheckFactoryPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

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
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
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
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="gysId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="gysCode" name="gysCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="gys" name="gys" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					供应商类型:
				</label>
			</td>
			<td class="value">
				<input id="gysType" name="gysType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					申请日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" name="kdDate" value="${kdDate}" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">申请日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					审核日期:
				</label>
			</td>
			<td class="value">
				<input id="shDate" name="shDate" readonly  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审核日期</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					工厂名称:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="factoryCode" field="factoryCode" typeGroupCode="gongchang" datatype="*" defaultVal="default" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂名称</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					工厂地址:
				</label>
			</td>
			<td class="value">
				<input id="factoryAddress" name="factoryAddress" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工厂地址</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					联系人:
				</label>
			</td>
			<td class="value">
				<input id="relationer" name="relationer"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					审核类型:
				</label>
			</td>
			<td class="value">
				<input id="checkType" name="checkType"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审核类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					验厂内容:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="checkContent" style="width:95%;height:70px" class="inputxt" rows="5" name="checkContent"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验厂内容</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					验厂标准:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="brand" style="width:95%;height:70px" class="inputxt" rows="5" name="brand"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验厂标准</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
