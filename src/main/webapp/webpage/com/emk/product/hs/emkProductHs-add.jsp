<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>海关编码表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkProductHsController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkProductHsPage.id }"/>
		<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							海关编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="hsCode" datatype="*" name="hsCode" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">海关编码</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							海关名称:
						</label>
					</td>
					<td class="value">
						<input id="hsName" datatype="*"  name="hsName" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">海关名称</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							报关要素:
						</label>
					</td>
					<td class="value">
					     	 <input id="bgys" name="bgys" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报关要素</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							增值税率(%):
						</label>
					</td>
					<td class="value">
						<input id="zzVal" datatype="d"   name="zzVal" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">增值税率</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							退税率(%):
						</label>
					</td>
					<td class="value">
					     	 <input id="tsVal" datatype="d"  name="tsVal" type="text" style="width: 150px" class="inputxt"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">退税率</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							销售指导价:
						</label>
					</td>
					<td class="value">
						<input id="salePrice" name="salePrice" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">销售指导价</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark">${emkOrderPage.remark}</textarea>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/product/hs/emkProductHs.js"></script>		
