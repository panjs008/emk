<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%--<c:set value="0" var="flagValue" scope="session" />--%>
<!DOCTYPE html>
<html>
 <head>
  <title>入库表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  function resetTrNum(tableId) {
	  $tbody = $("#"+tableId+"");
	  $tbody.find('>tr').each(function(i){
		  $(':input, select', this).each(function(){
			  var $this = $(this), name = $this.attr('name'), val = $this.val();
			  if(name!=null){
				  if (name.indexOf("#index#") >= 0){
					  $this.attr("name",name.replace('#index#',i));
				  }else{
					  var s = name.indexOf("[");
					  var e = name.indexOf("]");
					  var new_name = name.substring(s+1,e);
					  $this.attr("name",name.replace(new_name,i));
				  }
			  }
		  });
	  });
  }
  $(document).ready(function(){
	 $("#detailId").load("emkInStorageController.do?emkInStorageDetailList");
  });
  </script>
 </head>
 <body>

  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkInStorageController.do?doAdd"  tiptype="1">
					<input id="id" name="id" type="hidden" value="${emkInStoragePage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							采购单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="rkNo" name="rkNo" value="${roNo}" readonly type="text" style="width: 150px" class="inputxt"  datatype="*" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购单号</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							开单日期:
						</label>
					</td>
					<td class="value">
						<input id="kdDate" datatype="*" name="kdDate" value="${createDate}" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">开单日期</label>
					</td>
				</tr>


				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<select name="type" id="type" datatype="*">
							<option value="0">打样</option>
							<option value="1">生产</option>
						</select>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">类型</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							供应商:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="factoryCode" field="factoryCode" typeGroupCode="factory" datatype="*" defaultVal="default" hasLabel="false" title="供应商"></t:dictSelect>
						<%--<input id="factoryName" name="factoryName" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
						<%--<input id="factoryCode" name="factoryCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							运费:
						</label>
					</td>
					<td class="value">
					     	 <input id="yunfei" name="yunfei" datatype="*" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运费</label>
						</td>

					<td align="right">
						<label class="Validform_label">
							交付日期:
						</label>
					</td>
					<td class="value">
						<input id="receiveDate" name="receiveDate" datatype="*" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly style="width: 150px" class="Wdate"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">交付日期</label>
					</td>
				</tr>


				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>


			</table>

	  <div id="detailId" style="width: auto; height: 200px;" ><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
			  <%--  <div style="width: 100%; height: 1px;"></div>
                <t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
                    <t:tab href="emkInStorageController.do?emkInStorageDetailList" icon="icon-search" title="商品明细" id="Product"></t:tab>
                </t:tabs>--%>
	  </div>
	  <!-- 添加 产品明细 模版-->
	  <table style="width:100%;display: none" cellpadding="0" cellspacing="2" border="0">
		  <tbody id="add_jeecgOrderProduct_table_template">
		  <tr>
			  <td align="center"><input style="width: 40px;" type="checkbox" name="ck" />
				  <input type="hidden" id="proId00" name="rkglMxList[#index#].proId" />
				  <input type="hidden" id="proNum00" name="rkglMxList[#index#].proNum" /></td>
			  <td align="left"><input nullmsg="请输入款号！" id="sampleNo00"   errormsg="请输入款号" name="rkglMxList[#index#].sampleNo" maxlength="100" type="text" value=""
									  style="width: 100px;"></td>
			  <td align="left">
				  <input nullmsg="请输入款号名称！" id="sampleName00"  errormsg="请输入款号名称" name="rkglMxList[#index#].sampleName" maxlength="100" type="text" value=""
						 style="width: 120px;">
			  </td>
			  <td align="left">
				  <input nullmsg="请输入物料名称！" id="proName00"  errormsg="请输入物料名称" name="rkglMxList[#index#].proName" maxlength="100" type="text" value=""
						 style="width: 120px;">
			  </td>
			  <td align="left"><input id="brand00" nullmsg="请输入规格！"  errormsg="请输入规格" name="rkglMxList[#index#].brand" maxlength="100" type="text" value=""
									  style="width: 120px;"></td>
			  <td align="left"><input id="signTotal00" nullmsg="请输入数量！"  errormsg="请输入整数" name="rkglMxList[#index#].signTotal" maxlength="100" type="text" value=""
									  style="width: 120px;"></td>
			  <td align="left">
				  <input nullmsg="请输入单位！" id="signUnit00" errormsg="请输入单位" name="rkglMxList[#index#].signUnit" maxlength="100" type="text" value=""
						 style="width: 60px;"></td>
			  <td align="left"><input nullmsg="请输入单价！" id="signPrice00" errormsg="请输入单价" name="rkglMxList[#index#].signPrice" maxlength="100" type="text" value=""
									  style="width: 100px;"></td>
			  <td align="left"><input nullmsg="请输入备注！" id="remark00"  errormsg="请输入备注" name="rkglMxList[#index#].remark" maxlength="100" type="text" value=""
									  style="width: 100px;"></td>
		  </tr>
		  </tbody>

	  </table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/storage/instorage/emkInStorage.js"></script>
