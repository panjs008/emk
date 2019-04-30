<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工厂档案</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码

		$(function(){
			$("#level").val("${emkFactoryArchivesPage.level}");
			$.ajax({
				url : "ymkCustomController.do?getCity&code=A01A05",
				type : 'post',
				cache : false,
				data: null,
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						var dataItems = new Array(); //定义一数组
						dataItems = d.obj.split(";"); //字符分割
						//W.document.location.reload(true);
						$('#shengFen').empty();
						var option1 = '<option value="">--选择--</option>';
						var firstJgmc;
						for (i=0;i<dataItems.length ;i++ ) {
							var dataitem = new Array(); //定义一数组
							dataitem = dataItems[i].split(","); //字符分割
							if(dataitem[0]!="") {
								if(dataitem[0] == '${emkFactoryArchivesPage.shengFen}'){
									option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
								}else{
									option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}
						}
						$("#shengFen").append(option1);
						if(${emkFactoryArchivesPage.shengFen ne '' && emkFactoryArchivesPage.shengFen ne null}){
							$.ajax({
								url : "ymkCustomController.do?getCity&code=${emkFactoryArchivesPage.shengFen}",
								type : 'post',
								cache : false,
								data: null,
								success : function(data) {
									var d = $.parseJSON(data);
									if (d.success) {
										var msg = d.msg;
										var dataItems = new Array(); //定义一数组
										dataItems = d.obj.split(";"); //字符分割
										$('#chengShi').empty();
										var option1='';
										for (i=0;i<dataItems.length ;i++ ) {
											var dataitem = new Array(); //定义一数组
											dataitem = dataItems[i].split(","); //字符分割
											if(dataitem[0] == '${emkFactoryArchivesPage.chengShi}'){
												option1 += '<option value='+dataitem[0]+' selected>'+dataitem[1]+'</option>';
											}else{
												option1 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
											}
										}
										$("#chengShi").append(option1);

									}
								}
							});
						}

					}
				}
			});


			$("#shengFen").change(function(){
				$.ajax({
					url : "ymkCustomController.do?getCity&code="+$("#shengFen").val(),
					type : 'post',
					cache : false,
					data: null,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							console.log("chengshi:"+ d.obj);
							var dataItems = new Array(); //定义一数组
							dataItems = d.obj.split(";"); //字符分割
							//W.document.location.reload(true);
							$('#chengShi').empty();
							$('#pianQu').empty();

							var option3='';
							var firstJgmc;

							for (i=0;i<dataItems.length ;i++ ) {
								var dataitem = new Array(); //定义一数组
								dataitem = dataItems[i].split(","); //字符分割
								if(i == 0){
									firstJgmc = dataitem[0];
								}
								if(dataitem[0]!="") {
									option3 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
								}
							}
							$("#chengShi").append(option3);

						}
					}
				});
			});

		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkFactoryArchivesController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkFactoryArchivesPage.id }"/>
	<table style="width: 100%;margin-top: 5px;" cellpadding="0" cellspacing="1" class="formtable">
		<c:if test="${param.node eq 'checkfactoryTask' }">
			<tr>
				<td align="right" >
					<label class="Validform_label">
						操作类型:
					</label>
				</td>
				<td class="value"  colspan="3">
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
			<td class="value" align="right" colspan="3">
				<label class="Validform_label">
					档案编号:
				</label>
			</td>
			<td class="value">
				<input id="archivesNo" name="archivesNo" value="${emkFactoryArchivesPage.archivesNo }"  type="text" readonly style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">档案编号</label>
			</td>


		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					国家:
				</label>
			</td>
			<td class="value" colspan="3">
				<t:dictSelect id="guoJia" field="guoJia" typeGroupCode="trade" datatype="*" defaultVal="${emkFactoryArchivesPage.guoJia }" hasLabel="false" title="国家"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">国家</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					省份:
				</label>
			</td>
			<td class="value">
				<select id="shengFen" name="shengFen"  style="width:130px;">
					<option>--选择--</option>
					<c:forEach items="${codeList}" var="codeObj">
						<option value="${codeObj.code}"  ${codeObj.code eq emkFactoryArchivesPage.shengFen ? 'selected':''}>${codeObj.name}</option>
					</c:forEach>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">省份</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					城市:
				</label>
			</td>
			<td class="value">
				<select id="chengShi" name="chengShi" style="width:150px;">
					<option>--选择--</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">城市</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商产品类型:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="proTypeTree" value="${emkFactoryArchivesPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" value="${emkFactoryArchivesPage.proTypeName }" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkFactoryArchivesPage.proType }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商产品类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					被审核企业信息提交时间:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sumbitDate" name="sumbitDate" value="${emkFactoryArchivesPage.sumbitDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">被审核企业信息提交时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					供应商代码:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="companyCode" name="companyCode" datatype="*" validType="emk_factory_archives,company_code,id"  value="${emkFactoryArchivesPage.companyCode }" type="text" style="width: 150px" class="inputxt" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供应商代码</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					Company Name
				</label>
			</td>
			<td align="left" class="value">
				<label class="Validform_label">
					English英文:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="companyNameEn" name="companyNameEn" value="${emkFactoryArchivesPage.companyNameEn }" type="text" style="width: 80%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">English英文</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					(English & Local Language)公司名
				</label>
			</td>
			<td align="left" class="value">
				<label class="Validform_label">
					Local 中文:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="companyNameZn" name="companyNameZn" value="${emkFactoryArchivesPage.companyNameZn }" datatype="*" type="text" style="width: 80%" class="inputxt" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">Local 中文</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Address
				</label>
			</td>
			<td align="left" class="value">
				<label class="Validform_label">
					English英文:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="addressEn" name="addressEn" type="text" value="${emkFactoryArchivesPage.addressEn }" style="width: 80%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">English英文</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Address
				</label>
			</td>
			<td align="left" class="value">
				<label class="Validform_label">
					Local 中文:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="addressZn" name="addressZn" type="text" value="${emkFactoryArchivesPage.addressZn }" style="width: 80%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">Local 中文</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Additional sites (address)其它地址:
				</label>
			</td>
			<td class="value" colspan="3">
				Provide address if the facility has additional sites remote from the premises若工厂还有其它经营地址请指明:<br/>
				<input id="otherAddress" name="otherAddress" type="text" value="${emkFactoryArchivesPage.otherAddress }" style="width: 99%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">Additional sites (address)其它地址</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Location of the Employee Documents<br/>审核文件所存放的地址:
				</label>
			</td>
			<td class="value" colspan="3">
				Provide address if employee documents (e.g. HR / Pay Roll/ Time Attendance Records) are stored in a different location若审核文件（如人事、工资考勤记录等）存放在另一地址请指明:<br/>
				<textarea  id="locationDocuments" style="width:99%;height:90px" class="inputxt" rows="7" name="locationDocuments">${emkFactoryArchivesPage.locationDocuments }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审核文件存放地址</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					Primary Facility Contact & Title <br/>主要联系人姓名及头衔:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="primaryContact" name="primaryContact" value="${emkFactoryArchivesPage.primaryContact }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主要联系人</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					Secondary Facility Contact & Title <br/>第二联系人姓名及头衔:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="secondaryContact" name="secondaryContact" value="${emkFactoryArchivesPage.secondaryContact }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">第二联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					E-mail Address邮箱:
				</label>
			</td>
			<td class="value">
				<input id="primaryContactEmail" name="primaryContactEmail" value="${emkFactoryArchivesPage.primaryContactEmail }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">邮箱</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					E-mail Address邮箱:
				</label>
			</td>
			<td class="value" >
				<input id="secondaryContactEmail" name="secondaryContactEmail" value="${emkFactoryArchivesPage.secondaryContactEmail }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">E-mail Address邮箱</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Telephone Number电话:
				</label>
			</td>
			<td class="value">
				<input id="primaryContactTel" name="primaryContactTel" value="${emkFactoryArchivesPage.primaryContactTel }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					Telephone number电话:
				</label>
			</td>
			<td class="value" >
				<input id="secondaryContactTel" name="secondaryContactTel" value="${emkFactoryArchivesPage.secondaryContactTel }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电话</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					Year of Facility Established<br/>成立年份:
				</label>
			</td>
			<td class="value">
				<input id="yearEstablished" name="yearEstablished" value="${emkFactoryArchivesPage.yearEstablished }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="width: 220px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">成立年份</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					Business License Number<br/>营业执照号码:
				</label>
			</td>
			<td class="value" >
				<input id="licenseNumber" name="licenseNumber" value="${emkFactoryArchivesPage.licenseNumber }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">营业执照号码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					开户行:
				</label>
			</td>
			<td class="value">
				<input id="bankName" name="bankName" type="text" value="${emkFactoryArchivesPage.bankName }" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户行</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					账号:
				</label>
			</td>
			<td class="value" >
				<input id="bankAccount" name="bankAccount" type="text" value="${emkFactoryArchivesPage.bankAccount }" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账号</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					Facility Business License/:
				</label>
			</td>
			<td class="value">
				<input id="facilityBusinessLicense" name="facilityBusinessLicense" value="${emkFactoryArchivesPage.facilityBusinessLicense }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">businesslicen</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					Business License/ Permit is issued by<br/>营业执照签发机构::
				</label>
			</td>
			<td class="value" >
				<input id="permitSsued" name="permitSsued" type="text" value="${emkFactoryArchivesPage.permitSsued }" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">签发机构</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					营业执照有效期:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="permitExpirationDate" name="permitExpirationDate" value="${emkFactoryArchivesPage.permitExpirationDate }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">营业执照有效期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Production Process生产工序:
				</label>
			</td>
			<td class="value">
				<input id="productionProcess" name="productionProcess" value="${emkFactoryArchivesPage.productionProcess }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产工序</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					Full production on proposed audit date<br/>审核当天是否全员生产:
				</label>
			</td>
			<td class="value">
				<input name="fullProduction" type="radio" ${emkFactoryArchivesPage.fullProduction eq '0' ? 'checked':''} datatype="*" value="0">
				YES
				<input name="fullProduction" type="radio" ${emkFactoryArchivesPage.fullProduction eq '1' ? 'checked':''} datatype="*" value="1">
				NO
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否全员生产</label>
			</td>
		</tr>
		<tr>

			<td align="right">
				<label class="Validform_label">
					Product Classification of Manufacturing<br/>产业类型:
				</label>
			</td>
			<td class="value">
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk0" type="checkbox" value="[0]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[0]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Home communications, entertainment & hobby equipment<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk2" type="checkbox" value="[2]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[2]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Furniture and Furnishings<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk4" type="checkbox" value="[4]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[4]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />House appliance<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk6" type="checkbox" value="[6]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[6]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Housewares (non-powered)<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk8" type="checkbox" value="[8]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[8]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Chemicals & chemical products<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk10" type="checkbox" value="[10]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[10]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Home workshop apparatus tools<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk12" type="checkbox" value="[12]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[12]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Yard and garden<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk14" type="checkbox" value="[14]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[14]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Child nursery equipment & supplies<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk16" type="checkbox" value="[16]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[16]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Personal use items<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk18" type="checkbox" value="[18]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[18]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Garments, Footwear & Accessories<br/>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产业类型</label>
			</td>
			<td class="value" colspan="2">
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk1" type="checkbox" value="[1]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[1]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Sports and Outdoor Recreation Equipment<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk3" type="checkbox" value="[3]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[3]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Packaging and Containers<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk5" type="checkbox" value="[5]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[5]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Toys<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk7" type="checkbox" value="[7]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[7]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Personal care items<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk9" type="checkbox" value="[9]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[9]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Auto Part & Accessory<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk11" type="checkbox" value="[11]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[11]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Beverage & Food<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk13" type="checkbox" value="[13]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[13]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Pet related products<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk15" type="checkbox" value="[15]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[15]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Medicine<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk17" type="checkbox" value="[17]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[17]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Raw Material<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk19" type="checkbox" value="[19]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[19]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Miscellaneous Products<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk21" type="checkbox" value="[21]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[21]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Servicing Facilities<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk23" type="checkbox" value="[23]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[23]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Other<br/>
				<input name="productClassification" class="rt2"  ignore="ignore"  id="tk25" type="checkbox" value="[25]" ${fn:contains(emkFactoryArchivesPage.productClassification,'[25]') eq true ? 'checked':''} datatype="need1"  nullmsg="请选择产业类型！" />Pet related products<br/>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产业类型</label>
			</td>
		</tr>
		<tr>
			<td align="right" rowspan="2">
				<label class="Validform_label">
					Facility Size 企业面积:
				</label>
			</td>

			<td align="left">
				<label class="Validform_label">
					Facility Land Size (m2 )企业用地面积:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="facilityLandSize" name="facilityLandSize" value="${emkFactoryArchivesPage.facilityLandSize }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">企业总楼层面积</label>
			</td>
		</tr>
		<tr>
			<td align="left">
				<label class="Validform_label">
					Total Facility Floor Size (m2) 企业总楼层面积:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="facilityFoorSize" name="facilityFoorSize" value="${emkFactoryArchivesPage.facilityFoorSize }" type="text" style="width: 220px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">企业总楼层面积</label>
			</td>
		</tr>
	</table>
	<table style="width: 100%;margin-top: 3px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" rowspan="2">
				<label class="Validform_label">
					Number of Buildings建筑物数目
				</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Production 生产:
				</label>
			</td>
			<td class="value">
				<input id="production" name="production" type="text" value="${emkFactoryArchivesPage.production }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Dormitory宿舍:
				</label>
			</td>
			<td class="value">
				<input id="dormitory" name="dormitory" type="text" value="${emkFactoryArchivesPage.dormitory }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">宿舍</label>
			</td>
		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					Warehouse仓库:
				</label>
			</td>
			<td class="value">
				<input id="warehouse" name="warehouse" type="text" value="${emkFactoryArchivesPage.warehouse }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">仓库</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Other (specify)其它（请注明）:
				</label>
			</td>
			<td class="value">
				<input id="otherSpecify" name="otherSpecify" value="${emkFactoryArchivesPage.otherSpecify }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">其它</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Onsite Service Providers (e.g. security, janitor)<br/>服务供应商（如保安，门卫）:
				</label>
			</td>
			<td class="value" colspan="4">
				Provide name of each service provider and roles请提供服务供应商的名称及职位:<br/>
				<input id="provideNameProvider" value="${emkFactoryArchivesPage.provideNameProvider }" name="provideNameProvider" type="text" style="width: 90%" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">服务供应商</label>
			</td>
		</tr>
		<tr>
			<td align="right" rowspan="2">
				<label class="Validform_label">
					Number of Employee Breakdown<br/>员工人数明细
				</label>
			</td>

			<td align="right" class="value">
				<label class="Validform_label">
					Permanent Employee<br/>直接雇佣员工人数:
				</label>
			</td>
			<td class="value">
				<input id="permanentEmployee" name="permanentEmployee" value="${emkFactoryArchivesPage.permanentEmployee }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">直接雇佣员工人数</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Temporary/ Agency临时工/<br/>劳务派遣工/外包工人数:
				</label>
			</td>
			<td class="value">
				<input id="temporary" name="temporary" type="text" value="${emkFactoryArchivesPage.temporary }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">临时工</label>
			</td>
		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					Migrant labour<br/>移民员工:
				</label>
			</td>
			<td class="value">
				<input id="migrantLabour" name="migrantLabour" value="${emkFactoryArchivesPage.migrantLabour }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">移民员工</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Home workers <br/>家内工作者:
				</label>
			</td>
			<td class="value">
				<input id="homeWorkers" name="homeWorkers" value="${emkFactoryArchivesPage.homeWorkers }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">家内工作者</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Gender Breakdown性别明细
				</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Female女性员工人数:
				</label>
			</td>
			<td class="value">
				<input id="female" name="female" type="text" value="${emkFactoryArchivesPage.female }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">女性员工人数</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Male男性员工人数:
				</label>
			</td>
			<td class="value">
				<input id="male" name="male" type="text" value="${emkFactoryArchivesPage.male }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">男性员工人数</label>
			</td>
		</tr>
		<tr>
			<td align="right" rowspan="2">
				<label class="Validform_label">
					Employee Type Breakdown 员工类型明细
				</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					# of Production Employees<br/>生产员工人数:
				</label>
			</td>
			<td class="value">
				<input id="productionEmployees" value="${emkFactoryArchivesPage.productionEmployees }" name="productionEmployees" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产员工人数</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					# of Office / Admin staff<br/>办公/行政人员人数:
				</label>
			</td>
			<td class="value">
				<input id="adminStaf" name="adminStaf" value="${emkFactoryArchivesPage.adminStaf }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">行政人员人数</label>
			</td>
		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					# of Management <br/>管理人员人数:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="management" name="management" value="${emkFactoryArchivesPage.management }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">管理人员人数</label>
			</td>
		</tr>
		<tr>
			<td align="right" rowspan="2">
				<label class="Validform_label">
					Language Spoken by Employee员工语种
				</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Language 1语种1:
				</label>
			</td>
			<td class="value">
				<input id="language1" name="language1" value="${emkFactoryArchivesPage.language1 }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">语种1</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Total workforce in %<br/>占总人数百分比:
				</label>
			</td>
			<td class="value">
				<input id="zzsPre1" name="zzsPre1" value="${emkFactoryArchivesPage.zzsPre1 }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">占总人数百分比</label>
			</td>

		</tr>
		<tr>
			<td align="right" class="value">
				<label class="Validform_label">
					Language 2语种2::
				</label>
			</td>
			<td class="value">
				<input id="language2" name="language2" value="${emkFactoryArchivesPage.language2 }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">语种2</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Total workforce in %<br/>占总人数百分比:
				</label>
			</td>
			<td class="value">
				<input id="zzsPre2" name="zzsPre2" value="${emkFactoryArchivesPage.zzsPre2 }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">占总人数百分比</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					Language Spoken by Management管理人员语种
				</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Language 1语种1:
				</label>
			</td>
			<td class="value">
				<input id="mlanguage1" name="mlanguage1" value="${emkFactoryArchivesPage.mlanguage1 }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">语种1</label>
			</td>
			<td align="right" class="value">
				<label class="Validform_label">
					Language 2语种2:
				</label>
			</td>
			<td class="value">
				<input id="mlanguage2" name="mlanguage2" value="${emkFactoryArchivesPage.mlanguage2 }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">语种2</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					评估风险等级:
				</label>
			</td>
			<td class="value" colspan="4">
				<select id="level" name="level"  datatype="*">
					<option value="0">不能做单</option>
					<option value="1">能做单有风险</option>
					<option value="2">放心做单</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">评估风险等级</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/factoryarchives/emkFactoryArchives.js"></script>
<script>
	$(document).ready(function() {
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