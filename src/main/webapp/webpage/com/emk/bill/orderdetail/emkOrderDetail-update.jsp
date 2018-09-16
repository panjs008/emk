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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkOrderDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkOrderDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								产品编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="proNum" name="proNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.proNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								中文描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="proNameZn" name="proNameZn" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.proNameZn}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								英文描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="proNameEn" name="proNameEn" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.proNameEn}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文描述</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								规格型号:
							</label>
						</td>
						<td class="value">
						     	 <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.brand}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格型号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								签约数量:
							</label>
						</td>
						<td class="value">
						     	 <input id="signTotal" name="signTotal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.signTotal}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								签约单位:
							</label>
						</td>
						<td class="value">
						     	 <input id="signUnit" name="signUnit" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.signUnit}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								签约单价:
							</label>
						</td>
						<td class="value">
						     	 <input id="signPrice" name="signPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.signPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签约单价</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								不含税金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="notSignPrice" name="notSignPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.notSignPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">不含税金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								增值税率:
							</label>
						</td>
						<td class="value">
						     	 <input id="zzsl" name="zzsl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.zzsl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">增值税率</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								增值税额:
							</label>
						</td>
						<td class="value">
						     	 <input id="zzse" name="zzse" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.zzse}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">增值税额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								退税率:
							</label>
						</td>
						<td class="value">
						     	 <input id="tsl" name="tsl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.tsl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">退税率</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								退税额:
							</label>
						</td>
						<td class="value">
						     	 <input id="tse" name="tse" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.tse}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">退税额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								海关商品编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="hairProNum" name="hairProNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.hairProNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">海关商品编码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkOrderDetailPage.orderId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单ID</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/bill/orderdetail/emkOrderDetail.js"></script>		
