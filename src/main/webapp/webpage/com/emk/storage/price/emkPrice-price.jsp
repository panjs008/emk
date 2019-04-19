<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单报价</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">


	</script>
</head>
<body>

<t:formvalid formid="formprice" dialog="true" usePlugin="password" layout="table" tiptype="1">
<table style="width: 98%;" cellpadding="0" cellspacing="1" class="formtable">
	<c:if test="${emkPricePage.state eq '24' || emkPricePage.state eq '27' }">
	<tr>
		<td align="right" >
			<label class="Validform_label">
				操作类型:
			</label>
		</td>
		<td class="value"  colspan="7">
			<input  name="isPass" type="radio" datatype="*" value="0">
			保存数据&nbsp;&nbsp;&nbsp;&nbsp;
			<input  name="isPass" type="radio" datatype="*"  value="1">
			提交审核
			<span class="Validform_checktip"></span>
			<label class="Validform_label" style="display: none;">操作类型</label>
		</td>
	</tr>
	</c:if>
	<tr>
		<td align="right" >
			<label class="Validform_label">
				测试费:
			</label>
		</td>
		<td class="value"  colspan="7">
			<input id="testMoney" name="testMoney" value="${emkPricePage.testMoney }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
			<span class="Validform_checktip"></span>
			<label class="Validform_label" style="display: none;">测试费</label>
		</td>
	</tr>

	<tr>
		<td align="right" >
			<label class="Validform_label">
				利润:
			</label>
		</td>
		<td class="value"  colspan="7">
			<input id="profit" name="profit" value="${emkPricePage.profit }" datatype="d" type="text" style="width: 150px" class="inputxt"   />
			<span class="Validform_checktip"></span>
			<label class="Validform_label" style="display: none;">利润</label>
		</td>
	</tr>
	<tr>
		<td align="right" >
			<label class="Validform_label">
				不可预见成本:
			</label>
		</td>
		<td class="value"  colspan="7">
			<input id="unableMoney" name="unableMoney" value="${emkPricePage.unableMoney }" datatype="d" type="text" style="width: 150px" class="inputxt"  />
			<span class="Validform_checktip"></span>
			<label class="Validform_label" style="display: none;">不可预见成本</label>
		</td>
	</tr>
	<tr>
		<td align="right" >
			<label class="Validform_label">
				税收:
			</label>
		</td>
		<td class="value"  colspan="7">
			<input id="tax" name="tax" value="${emkPricePage.tax }"  type="text" datatype="d" style="width: 150px" class="inputxt"  />
			<span class="Validform_checktip"></span>
			<label class="Validform_label" style="display: none;">税收</label>
		</td>
	</tr>
	<tr>
		<td align="right" >
			<label class="Validform_label">
				小计
			</label>
		</td>
		<td class="value"  colspan="7">
			<table>
				<table style="width: 98%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr align="center">
						<td  width="100px" height="32px">面料材料</td>
						<td  width="100px">缝制材料</td>
						<td  width="100px">包装材料</td>
						<td  width="100px">人工</td>
						<td  width="100px">染色</td>
						<td  width="100px">助剂</td>
						<td  width="100px">印花</td>
						<td  width="100px">管理</td>
						<td  width="100px">测试</td>
						<td  width="100px">利润</td>
						<td  width="100px">不可预见成本</td>
						<td  width="100px">税收</td>
						<td  width="100px">总计</td>

					</tr>
					<tr align="center">
						<td class="value">${emkPricePage.sumYl }</td>
						<td class="value">${emkPricePage.sumFeng }</td>
						<td class="value">${emkPricePage.sumBao }</td>
						<td class="value">${emkPricePage.sumRg }</td>
						<td class="value">${emkPricePage.sumRan }</td>
						<td class="value">${emkPricePage.sumZj }</td>
						<td class="value">${emkPricePage.sumYin }</td>
						<td class="value">${emkPricePage.sumGl }</td>
						<td class="value">${emkPricePage.testMoney }</td>
						<td class="value">${emkPricePage.profit }</td>
						<td class="value">${emkPricePage.unableMoney }</td>
						<td class="value">${emkPricePage.tax }</td>
						<td class="value">${emkPricePage.sumMoney }</td>
					</tr>
				</table>
				</td>
				</tr>
				<tr>
					<td align="right" >
						<label class="Validform_label">
							目标价:
						</label>
					</td>
					<td class="value" colspan="7">
						<input id="targetJw" name="targetJw" value="${emkPricePage.targetJw }" datatype="d" type="text" style="width: 150px" class="inputxt"   />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">目标价</label>
					</td>
				</tr>
				<tr>
					<td align="right" >
						<label class="Validform_label">
							外币价:
						</label>
					</td>
					<td class="value">
						<input id="sumWb" name="sumWb" value="${emkPricePage.sumWb }"  type="text" datatype="d" style="width: 150px" class="inputxt"   />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">外币价</label>
					</td>
					<td align="right" >
						<label class="Validform_label">
							币种:
						</label>
					</td>
					<td class="value" >
						<input id="bizhong" name="bizhong" value="${emkPricePage.bizhong }" datatype="*" type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">币种</label>
					</td>
					<td align="right" >
						<label class="Validform_label">
							汇率:
						</label>
					</td>
					<td class="value" >
						<input id="huilv" name="huilv" value="${emkPricePage.huilv }" datatype="d" type="text" style="width: 150px" class="inputxt" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">汇率</label>
					</td>
					<td align="right" >
						<label class="Validform_label">
							汇率日期:
						</label>
					</td>
					<td class="value" >
						<input id="huilvDate" name="huilvDate" value="${emkPricePage.huilvDate }" datatype="*" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">汇率日期</label>
					</td>
				</tr>
				<tr>
					<td align="right" >
						<label class="Validform_label">
							差距:
						</label>
					</td>
					<td class="value" colspan="7">
						<input value=""  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">差距</label>
					</td>
				</tr>
				<tr>
					<td align="right" >
						<label class="Validform_label">
							利润率:
						</label>
					</td>
					<td class="value" colspan="7">
						<input id="maoRate" name="maoRate" value="${emkPricePage.maoRate }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">利润率</label>
					</td>
				</tr>
				<tr>
					<td align="right" >
						<label class="Validform_label">
							买家同意价:
						</label>
					</td>
					<td class="value" colspan="7">
						<input id="gysArgeePrice" name="gysArgeePrice" value="${emkPricePage.gysArgeePrice }" datatype="d"  type="text" style="width: 150px" class="inputxt"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">买家同意价</label>
					</td>
				</tr>
				<tr>
					<td align="right" >
						<label class="Validform_label">
							卖家同意价:
						</label>
					</td>
					<td class="value" colspan="7">
						<input id="cusArgeePrice" name="cusArgeePrice" value="${emkPricePage.cusArgeePrice }" datatype="d" type="text" style="width: 150px" class="inputxt" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">卖家同意价</label>
					</td>
				</tr>
			</table>
			</t:formvalid>
</body>
