<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>产品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  $(function() {
      $('#proTypeTree').combotree({
          url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
          panelHeight: 200,
          width: 157,
          onClick: function(node){
              $("#proType").val(node.id);
              $("#proTypeName").val(node.text);

          }
      });

  });
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="emkProductController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${emkProductPage.id }"/>
	  <input id="proType" name="proType" type="hidden" value="${emkProductPage.proType }"/>
	  <input id="proTypeName" name="proTypeName" type="hidden" value="${emkProductPage.proTypeName }"/>
	  <input id="type" name="type" type="hidden" value="${param.type }"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
			 <%-- <tr>
				  <td align="right">
					  <label class="Validform_label">
						  面料代码:
					  </label>
				  </td>
				  <td class="value">
					  <input id="hsCode" datatype="*"  name="hsCode" type="text" style="width: 150px" class="inputxt"  />
					  <span class="Validform_checktip"></span>
					  <label class="Validform_label" style="display: none;">面料代码</label>
				  </td>

				  <td align="right">
					  <label class="Validform_label">
						  面料名称:
					  </label>
				  </td>
				  <td class="value">
						  &lt;%&ndash;<input id="hsId"  readonly name="hsId" type="hidden" class="inputxt"  />&ndash;%&gt;
					  <input id="hsName" datatype="*"   name="hsName" type="text" style="width: 150px" class="inputxt"  />
						  &lt;%&ndash;  <t:choose  hiddenName="hsId"  hiddenid="id" url="emkProductHsController.do?select" name="emkProductHsList" width="700px" height="500px"
									   icon="icon-search" title="选择海关编码信息" textname="hsCode,hsName,zzVal,tsVal" isclear="true" isInit="true"></t:choose>&ndash;%&gt;
					  <span class="Validform_checktip"></span>
					  <label class="Validform_label" style="display: none;">面料名称</label>
				  </td>
			  </tr>--%>
		  		<tr>
					<td align="right" width="150">
						<label class="Validform_label">
							类别:
						</label>
					</td>
					<td class="value" colspan="${param.type eq 0 ? '0':'3'}">
						<input id="proTypeTree" value="${emkProductTypePage.productTypeEntity.content}">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">类别</label>
					</td>
					<c:if test="${param.type eq 0}">
						<td align="right">
							<label class="Validform_label">
								比例:
							</label>
						</td>
						<td class="value">
							<input id="precent" name="precent"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">比例</label>
						</td>
					</c:if>

				</tr>
				<tr>
					<td align="right" width="150">
						<label class="Validform_label">
								${param.type eq 0 ? '面料代码':'辅料代码'}:
						</label>
					</td>
					<td class="value" width="260">
					     	 <input id="proNum" name="proNum" type="text" validType="emk_product,pro_num,id" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">${param.type eq 0 ? '面料代码':'辅料代码'}
							</label>
						</td>

					<td align="right">
						<label class="Validform_label">
								${param.type eq 0 ? '面料名称':'辅料名称'}:
						</label>
					</td>
					<td class="value" >
						<input id="proZnName" name="proZnName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">${param.type eq 0 ? '面料名称':'辅料名称'}
						</label>
					</td>
				</tr>

				<%--<tr>
					<td align="right">
						<label class="Validform_label">
							单件用量:
						</label>
					</td>
					<td class="value">
						<input id="yongliang" name="yongliang" datatype="d" type="text" style="width: 150px" class="inputxt"   />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">单件用量</label>
					</td>

				</tr>--%>
				 <tr>
					 <td align="right">
						 <label class="Validform_label">
							 规格:
						 </label>
					 </td>
					 <td class="value">
						 <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						 <span class="Validform_checktip"></span>
						 <label class="Validform_label" style="display: none;">规格型号</label>
					 </td>
					 <td align="right">
						 <label class="Validform_label">
							 单位:
						 </label>
					 </td>
					 <td class="value">
						 <input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						 <span class="Validform_checktip"></span>
						 <label class="Validform_label" style="display: none;">单位</label>
					 </td>
				 </tr>
				  <tr>
					  <td align="right">
						  <label class="Validform_label">
							  备注:
						  </label>
					  </td>
					  <td class="value" colspan="3"><textarea id="proRemark" style="width:95%;height:80px" class="inputxt" rows="5" name="proRemark"></textarea>
						  <span class="Validform_checktip"></span>
						  <label class="Validform_label" style="display: none;">备注</label>
					  </td>
				  </tr>


		<%--  <tr>
			  <td align="right">
				  <label class="Validform_label">
					  增值税率:
				  </label>
			  </td>
			  <td class="value">
				  <input id="zzVal" datatype="*"  readonly name="zzVal" type="text" style="width: 150px" class="inputxt"  />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">增值税率</label>
			  </td>

			  <td align="right">
				  <label class="Validform_label">
					  退税率:
				  </label>
			  </td>
			  <td class="value">
				  <input id="tsVal" datatype="*"  readonly name="tsVal" type="text" style="width: 150px" class="inputxt"  />
				  <span class="Validform_checktip"></span>
				  <label class="Validform_label" style="display: none;">退税率</label>
			  </td>
		  </tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							英文描述:
						</label>
					</td>
					<td class="value" colspan="3"><textarea id="remarkEn" style="width:95%;height:80px" class="inputxt" rows="5" name="remarkEn"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文描述</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							中文描述:
						</label>
					</td>
					<td class="value" colspan="3"><textarea id="remarkZn" style="width:95%;height:80px" class="inputxt" rows="5" name="remarkZn"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文描述</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3"><textarea id="proRemark" style="width:95%;height:80px" class="inputxt" rows="5" name="proRemark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>--%>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/emk/product/product/emkProduct.js"></script>		
