<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>服务商签约表</title>
  <t:base type="ckeditor,jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="uSignServiceController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${uSignServicePage.id }"/>
		<table style="width: 100%" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="15%">
						<label class="Validform_label">
							甲方:
						</label>
					</td>
					<td class="value" width="35%">
						<input id="partAId" name="partAId" type="hidden" value="${custom.id }" style="width: 150px" class="inputxt"   />
						<input id="partA" name="partA" type="text" datatype="*" value="${custom.cusName }"  readonly style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">甲方</label>
						</td>

					<td align="right" width="15%">
						<label class="Validform_label">
							甲方联系人:
						</label>
					</td>
					<td class="value" width="35%">
						<input id="arealtionerId" name="arealtionerId" value="${CUR_USER.id }"  datatype="*"  readonly  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="arealtioner" name="arealtioner" value="${CUR_USER.realName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">甲方联系人</label>
					</td>
					</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							甲方联系人电话:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="atelphone" name="atelphone"  value="${CUR_USER.mobilePhone }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">甲方联系人电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							乙方:
						</label>
					</td>
					<td class="value">
						<input id="cus_id" name="cus_id" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="cus_name" name="cus_name" type="text" datatype="*" style="width: 150px" class="inputxt"  ignore="ignore" />

						<t:choose  hiddenName="brealtionerId"  hiddenid="id" url="userController.do?userService" name="userList0" width="700px" height="500px"
								   icon="icon-search" title="选择服务商" textname="id,cus_name,cus_id,realName,mobilePhone" isclear="true" isInit="true"></t:choose>
						<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">乙方</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							乙方联系人:
						</label>
					</td>
					<td class="value">
						<input id="brealtionerId" name="brealtionerId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="realName" name="realName" type="text" datatype="*" readonly style="width: 150px" class="inputxt"  ignore="ignore" />

						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">乙方联系人</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							乙方联系人电话:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="mobilePhone" name="mobilePhone" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">乙方联系人电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合作开始时间:
						</label>
					</td>
					<td class="value">
						<input id="workYearBegin" name="workYearBegin" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value=""  style="width: 150px"  class="Wdate"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作期限</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							合作结束时间:
						</label>
					</td>
					<td class="value">
						<input id="workYearEnd" name="workYearEnd" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value=""  style="width: 150px"  class="Wdate"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作期限</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合作内容:
						</label>
					</td>
					<td class="value" colspan="3">
						<t:ckeditor name="workContent" type="height:330" value=""></t:ckeditor>

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作内容</label>
						</td>

					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/uflow/signservice/uSignService.js"></script>		
