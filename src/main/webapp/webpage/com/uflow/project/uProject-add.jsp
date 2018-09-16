<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>中标项目表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  function returnToVal0(){
	  $.ajax({
		  url : "uProjectController.do?getDeptInfo&userId="+$("#purchaserId").val(),
		  type : 'post',
		  cache : false,
		  data: null,
		  success : function(data) {
			  var d = $.parseJSON(data);
			  if (d.success) {
				  var dataItems = new Array(); //定义一数组
				  dataItems = d.obj.split(","); //字符分割
				  $("#orgCode").val(dataItems[0]);
				  $("#departname").val(dataItems[1]);

			  }
		  }
	  });
  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="uProjectController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${uProjectPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目编号:
						</label>
					</td>
					<td class="value">
						<input id="projectNum" name="projectNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">项目编号</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="projectName" name="projectName" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							采购人名称:
						</label>
					</td>
					<td class="value">
						<input id="purchaserId" name="purchaserId" type="hidden" />
						<input id="realName" name="realName" type="text" readonly datatype="*" style="width: 150px" class="inputxt"  />
						<t:choose  hiddenName="purchaserId"  hiddenid="id" url="userController.do?userOwner&userKey=业主管理员" name="userList0" width="700px" height="500px"
								   icon="icon-search" title="选择业主" textname="id,realName,departname,orgCode" isclear="true" isInit="true"></t:choose>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">采购人名称</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							采购人单位:
						</label>
					</td>
					<td class="value">
						<input id="orgCode" name="orgCode" type="hidden" style="width: 150px" class="inputxt"  />
						<input id="departname" name="departname" readonly datatype="*" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购人单位</label>
						</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							合同编号:
						</label>
					</td>
					<td class="value">
						<input id="htNum" name="htNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">合同编号</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark"></textarea>
					</td>
				</tr>
				<%--<tr>
					<td align="right">
						<label class="Validform_label">
							单位ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="ownerId" name="ownerId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="supplyer" name="supplyer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="supplyerId" name="supplyerId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商ID</label>
						</td>
				</tr>--%>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/uflow/project/uProject.js"></script>		
