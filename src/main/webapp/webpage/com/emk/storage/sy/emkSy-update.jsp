<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>色样通知单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkSyController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${emkSyPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								色样单号:
							</label>
						</td>
						<td class="value">
						    <input id="sytzdbh" name="sytzdbh" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sytzdbh}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">色样单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								日期:
							</label>
						</td>
						<td class="value">
						    <input id="sytzdrq" name="sytzdrq" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sytzdrq}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								需求单号:
							</label>
						</td>
						<td class="value">
						    <input id="syxqdbh" name="syxqdbh" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.syxqdbh}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">需求单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								工厂:
							</label>
						</td>
						<td class="value">
						    <input id="gc" name="gc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.gc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工厂</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务部门:
							</label>
						</td>
						<td class="value">
						    <input id="ywbm" name="ywbm" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ywbm}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员:
							</label>
						</td>
						<td class="value">
						    <input id="ywy" name="ywy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ywy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务跟单员:
							</label>
						</td>
						<td class="value">
						    <input id="ywgdy" name="ywgdy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ywgdy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务跟单员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								生产跟单员:
							</label>
						</td>
						<td class="value">
						    <input id="scgdy" name="scgdy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.scgdy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产跟单员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户代码:
							</label>
						</td>
						<td class="value">
						    <input id="khdm" name="khdm" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.khdm}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								工艺种类:
							</label>
						</td>
						<td class="value">
						    <input id="gyzl" name="gyzl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.gyzl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工艺种类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								款式大类:
							</label>
						</td>
						<td class="value">
						    <input id="ksdl" name="ksdl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ksdl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款式大类</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								描述 :
							</label>
						</td>
						<td class="value">
						    <input id="ms" name="ms" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ms}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述 </label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								颜色英文名:
							</label>
						</td>
						<td class="value">
						    <input id="ysywm" name="ysywm" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ysywm}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颜色英文名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								颜色中文名:
							</label>
						</td>
						<td class="value">
						    <input id="yaswm" name="yaswm" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.yaswm}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">颜色中文名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								色号:
							</label>
						</td>
						<td class="value">
						    <input id="sh" name="sh" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sh}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">色号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								色样规格:
							</label>
						</td>
						<td class="value">
						    <input id="sygg" name="sygg" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sygg}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">色样规格</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								色样数量:
							</label>
						</td>
						<td class="value">
						    <input id="sysl" name="sysl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sysl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">色样数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								技术参数:
							</label>
						</td>
						<td class="value">
						    <input id="jscs" name="jscs" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.jscs}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">技术参数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								配方:
							</label>
						</td>
						<td class="value">
						    <input id="pf" name="pf" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.pf}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">配方</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								温度:
							</label>
						</td>
						<td class="value">
						    <input id="wd" name="wd" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.wd}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">温度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								时间:
							</label>
						</td>
						<td class="value">
						    <input id="sj" name="sj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								样品完成检查意见:
							</label>
						</td>
						<td class="value">
						    <input id="ypwcjcyj" name="ypwcjcyj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ypwcjcyj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品完成检查意见</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								技术员染色意见:
							</label>
						</td>
						<td class="value">
						    <input id="jsylsyj" name="jsylsyj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.jsylsyj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">技术员染色意见</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								样品质检员意见:
							</label>
						</td>
						<td class="value">
						    <input id="ypzjyyj" name="ypzjyyj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ypzjyyj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">样品质检员意见</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								跟单员意见:
							</label>
						</td>
						<td class="value">
						    <input id="gdyyj" name="gdyyj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.gdyyj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">跟单员意见</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务员意见:
							</label>
						</td>
						<td class="value">
						    <input id="ywyyj" name="ywyyj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.ywyyj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务员意见</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								综合意见:
							</label>
						</td>
						<td class="value">
						    <input id="zhyj" name="zhyj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.zhyj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">综合意见</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								图片:
							</label>
						</td>
						<td class="value">
						    <input id="customSampleUrl" name="customSampleUrl" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.customSampleUrl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								图片:
							</label>
						</td>
						<td class="value">
						    <input id="customSample" name="customSample" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.customSample}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								款号:
							</label>
						</td>
						<td class="value">
						    <input id="sampleNo" name="sampleNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.sampleNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">款号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								版次:
							</label>
						</td>
						<td class="value">
						    <input id="version" name="version" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.version}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版次</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								成分:
							</label>
						</td>
						<td class="value">
						    <input id="chengf" name="chengf" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.chengf}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成分</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								克重:
							</label>
						</td>
						<td class="value">
						    <input id="weight" name="weight" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.weight}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">克重</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						    <input id="orderNum" name="orderNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.orderNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								用途:
							</label>
						</td>
						<td class="value">
						    <input id="purpose" name="purpose" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${emkSyPage.purpose}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用途</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/sy/emkSy.js"></script>		
