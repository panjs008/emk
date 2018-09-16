<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>纸箱尺寸表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkProOrderBoxController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkProOrderBoxPage.id }"/>
	  <input id="orderId" name="orderId" type="hidden" value="${param.orderId }"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="boxType" field="boxType" typeGroupCode="boxtype" datatype="*" defaultVal="default" hasLabel="false" title="条码类型"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">类型</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							长度:
						</label>
					</td>
					<td class="value">
					     	 <input id="longVal" name="longVal" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">长度</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							宽度:
						</label>
					</td>
					<td class="value">
					     	 <input id="widthVal" name="widthVal" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">宽度</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							高度:
						</label>
					</td>
					<td class="value">
					     	 <input id="heightVal" name="heightVal" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">高度</label>
						</td>
					</tr>
				<tr>

					<td align="right">
						<label class="Validform_label">
							单件毛重:
						</label>
					</td>
					<td class="value">
					     	 <input id="oneWeightMao" name="oneWeightMao" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单件毛重</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							单件净重:
						</label>
					</td>
					<td class="value">
						<input id="oneWeightJz" name="oneWeightJz" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">单件净重</label>
					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							箱内数量:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="inboxTotal" name="inboxTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">箱内数量</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单箱毛重:
						</label>
					</td>
					<td class="value">
					     	 <input id="boxWeightMao" name="boxWeightMao" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单箱毛重</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							单箱净重:
						</label>
					</td>
					<td class="value">
					     	 <input id="boxWeightJz" name="boxWeightJz" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单箱净重</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单箱体积:
						</label>
					</td>
					<td class="value">
					     	 <input id="boxVolume" name="boxVolume" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单箱体积</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							总体积:
						</label>
					</td>
					<td class="value">
					     	 <input id="sumVolume" name="sumVolume" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总体积</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总净重:
						</label>
					</td>
					<td class="value">
					     	 <input id="sumWeightJz" name="sumWeightJz" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总净重</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							总箱数:
						</label>
					</td>
					<td class="value">
						<input id="sumTotal" name="sumTotal" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">总箱数</label>
					</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/proorderbox/emkProOrderBox.js"></script>		
