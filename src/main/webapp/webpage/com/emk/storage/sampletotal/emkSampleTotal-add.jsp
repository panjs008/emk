<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>样品数量</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSampleTotalController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkSampleTotalPage.sampleId }"/>
	  <input id="sampleId" name="sampleId" type="hidden"  value="${param.sampleId }" style="width: 150px" class="inputxt"  ignore="ignore" />
	  <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							颜色:
						</label>
					</td>
					<td class="value">
					     	 <input id="color" name="color" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颜色</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							尺码:
						</label>
					</td>
					<td class="value">
					     	 <input id="size" name="size" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">尺码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="total" name="total" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/sampletotal/emkSampleTotal.js"></script>		
