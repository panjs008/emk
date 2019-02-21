<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>坯布</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkPbController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkPbPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								下机克重:
							</label>
						</td>
						<td class="value">
						    <input id="xjkz" name="xjkz" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPbPage.xjkz}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">下机克重</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单件所需时间:
							</label>
						</td>
						<td class="value">
						    <input id="djsxsj" name="djsxsj" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkPbPage.djsxsj}'/>
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
						    <input id="dw" name="dw" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPbPage.dw}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								机台日产量:
							</label>
						</td>
						<td class="value">
						    <input id="jtrcl" name="jtrcl" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${emkPbPage.jtrcl}'/>
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
						    <input id="qdhsl" name="qdhsl" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkPbPage.qdhsl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">前道损耗率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								后道损耗率:
							</label>
						</td>
						<td class="value">
						    <input id="hdhsl" name="hdhsl" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${emkPbPage.hdhsl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">后道损耗率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								报价单ID:
							</label>
						</td>
						<td class="value">
						    <input id="priceId" name="priceId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkPbPage.priceId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报价单ID</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/pb/emkPb.js"></script>		
