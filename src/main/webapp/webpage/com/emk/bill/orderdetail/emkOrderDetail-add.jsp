<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单商品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderDetailController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkOrderDetailPage.id }"/>
	  <input id="orderId" name="orderId" type="hidden" value="${param.orderId}"/>
	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品编号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							产品类型:
						</label>
					</td>
					<td class="value">
						<input id="proNameZn1" name="proNameZn1" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<t:choose  hiddenName="id" hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="900" height="500"
								   icon="icon-search" title="选择型号" textname="proNum,proZnName,proEnName,brand" isclear="true" isInit="true"></t:choose>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">产品类型</label>
					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							中文描述:
						</label>
					</td>
					<td class="value">
						<input id="proZnName" name="proZnName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">中文描述</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							英文描述:
						</label>
					</td>
					<td class="value">
						<input id="proEnName" name="proEnName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">英文描述</label>
					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格型号:
						</label>
					</td>
					<td class="value">
						<input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">规格型号</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							签约数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="signTotal" name="signTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约数量</label>
						</td>

					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							签约单位:
						</label>
					</td>
					<td class="value">
						<input id="signUnit" name="signUnit" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">签约单位</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							签约单价:
						</label>
					</td>
					<td class="value">
					     	 <input id="signPrice" name="signPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约单价</label>
						</td>

					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							不含税金额:
						</label>
					</td>
					<td class="value">
						<input id="notSignPrice" name="notSignPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">不含税金额</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							增值税率:
						</label>
					</td>
					<td class="value">
					     	 <input id="zzsl" name="zzsl" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">增值税率</label>
						</td>

					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							增值税额:
						</label>
					</td>
					<td class="value">
						<input id="zzse" name="zzse" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">增值税额</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							退税率:
						</label>
					</td>
					<td class="value">
					     	 <input id="tsl" name="tsl" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">退税率</label>
						</td>

					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退税额:
						</label>
					</td>
					<td class="value">
						<input id="tse" name="tse" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">退税额</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							海关商品编码:
						</label>
					</td>
					<td class="value" >
					     	 <input id="hairProNum" name="hairProNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">海关商品编码</label>
						</td>
				</tr>
			<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3"><textarea id="remark" style="width:95%;height:80px" class="inputxt" rows="5" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>

				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/orderdetail/emkOrderDetail.js"></script>		
