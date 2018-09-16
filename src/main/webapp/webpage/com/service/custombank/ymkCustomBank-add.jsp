<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行账户表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="ymkCustomBankController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${ymkCustomBankPage.id }"/>
	  <input id="customId" name="customId" type="hidden" value="${param.customId }"/>

	  <table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankName" name="bankName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankAccount" name="bankAccount" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankAccountName" name="bankAccountName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行户名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							SWIFT号:
						</label>
					</td>
					<td class="value">
					     	 <input id="swifi" name="swifi" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">SWIFT号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							币种:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="bz" field="bz" typeGroupCode="cointype" datatype="*" defaultVal="default" hasLabel="false" title="币种"></t:dictSelect>

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">币种</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							默认账号:
						</label>
					</td>
					<td class="value">
							<select id="state" name="state" >
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">默认账号</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/service/custombank/ymkCustomBank.js"></script>		
