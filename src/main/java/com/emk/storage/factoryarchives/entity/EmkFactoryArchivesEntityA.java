package com.emk.storage.factoryarchives.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 工厂档案
 * @author onlineGenerator
 * @date 2019-02-22 10:50:09
 * @version V1.0   
 *
 */
public class EmkFactoryArchivesEntityA implements java.io.Serializable {
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**所属部门*/
	private String sysOrgCode;
	/**产品类型*/
	@Excel(name="产品类型",width=15)
	private String proType;
	/**提交时间*/
	@Excel(name="提交时间",width=15)
	private String sumbitDate;
	/**公司名英文*/
	@Excel(name="公司名英文",width=15)
	private String companyNameEn;
	/**公司名中文*/
	@Excel(name="公司名中文",width=15)
	private String companyNameZn;
	/**地址英文*/
	@Excel(name="地址英文",width=15)
	private String addressEn;
	/**地址中文*/
	@Excel(name="地址中文",width=15)
	private String addressZn;
	/**其它地址*/
	@Excel(name="其它地址",width=15)
	private String otherAddress;
	/**审核文件存放地址*/
	@Excel(name="审核文件存放地址",width=15)
	private String locationDocuments;
	/**主要联系人*/
	@Excel(name="主要联系人",width=15)
	private String primaryContact;
	/**邮箱*/
	@Excel(name="邮箱",width=15)
	private String primaryContactEmail;
	/**电话*/
	@Excel(name="电话",width=15)
	private String primaryContactTel;
	/**第二联系人*/
	@Excel(name="第二联系人",width=15)
	private String secondaryContact;
	/**邮箱*/
	@Excel(name="邮箱",width=15)
	private String secondaryContactEmail;
	/**电话*/
	@Excel(name="电话",width=15)
	private String secondaryContactTel;
	/**成立年份*/
	@Excel(name="成立年份",width=15)
	private String yearEstablished;
	/**营业执照号码*/
	@Excel(name="营业执照号码",width=15)
	private String licenseNumber;
	/**开户行*/
	@Excel(name="开户行",width=15)
	private String bankName;
	/**账号*/
	@Excel(name="账号",width=15)
	private String bankAccount;
	/**签发机构*/
	@Excel(name="签发机构",width=15)
	private String permitSsued;
	/**营业执照有效期*/
	@Excel(name="营业执照有效期",width=15)
	private String permitExpirationDate;
	/**businesslicen*/
	@Excel(name="businesslicen",width=15)
	private String facilityBusinessLicense;
	/**生产工序*/
	@Excel(name="生产工序",width=15)
	private String productionProcess;
	/**是否全员生产*/
	@Excel(name="是否全员生产",width=15)
	private String fullProduction;
	/**产业类型*/
	@Excel(name="产业类型",width=15)
	private String productClassification;
	/**企业面积*/
	@Excel(name="企业面积",width=15)
	private String facilityLandSize;
	/**企业总楼层面积*/
	@Excel(name="企业总楼层面积",width=15)
	private String facilityFoorSize;
	/**生产*/
	@Excel(name="生产",width=15)
	private String production;
	/**仓库*/
	@Excel(name="仓库",width=15)
	private String warehouse;
	/**宿舍*/
	@Excel(name="宿舍",width=15)
	private String dormitory;
	/**其它*/
	@Excel(name="其它",width=15)
	private String otherSpecify;
	/**服务供应商*/
	@Excel(name="服务供应商",width=15)
	private String provideNameProvider;
	/**直接雇佣员工人数*/
	@Excel(name="直接雇佣员工人数",width=15)
	private String permanentEmployee;
	/**临时工*/
	@Excel(name="临时工",width=15)
	private String temporary;
	/**移民员工*/
	@Excel(name="移民员工",width=15)
	private String migrantLabour;
	/**家内工作者*/
	@Excel(name="家内工作者",width=15)
	private String homeWorkers;
	/**女性员工人数*/
	@Excel(name="女性员工人数",width=15)
	private String female;
	/**男性员工人数*/
	@Excel(name="男性员工人数",width=15)
	private String male;
	/**生产员工人数*/
	@Excel(name="生产员工人数",width=15)
	private String productionEmployees;
	/**行政人员人数*/
	@Excel(name="行政人员人数",width=15)
	private String adminStaf;
	/**管理人员人数*/
	@Excel(name="管理人员人数",width=15)
	private String management;
	/**语种1*/
	@Excel(name="语种1",width=15)
	private String language1;
	/**语种2*/
	@Excel(name="语种2",width=15)
	private String language2;
	/**占总人数百分比*/
	@Excel(name="占总人数百分比",width=15)
	private String zzsPre1;
	/**占总人数百分比*/
	@Excel(name="占总人数百分比",width=15)
	private String zzsPre2;
	/**语种1*/
	@Excel(name="语种1",width=15)
	private String mlanguage1;
	/**语种2*/
	@Excel(name="语种2",width=15)
	private String mlanguage2;
	/**评估风险等级*/
	@Excel(name="评估风险等级",width=15)
	private String level;
	/**工厂代码*/
	@Excel(name="工厂代码",width=15)
	private String companyCode;
	/**省份*/
	@Excel(name="省份",width=15)
	private String shengFen;
	/**城市*/
	@Excel(name="城市",width=15)
	private String chengShi;
	/**国家*/
	@Excel(name="国家",width=15)
	private String guoJia;
	/**档案编号*/
	@Excel(name="档案编号",width=15)
	private String archivesNo;
	/**产品类型*/
	@Excel(name="产品类型",width=15)
	private String proTypeName;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品类型
	 */

	@Column(name ="PRO_TYPE",nullable=true,length=32)
	public String getProType(){
		return this.proType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品类型
	 */
	public void setProType(String proType){
		this.proType = proType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交时间
	 */

	@Column(name ="SUMBIT_DATE",nullable=true,length=32)
	public String getSumbitDate(){
		return this.sumbitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交时间
	 */
	public void setSumbitDate(String sumbitDate){
		this.sumbitDate = sumbitDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名英文
	 */

	@Column(name ="COMPANY_NAME_EN",nullable=true,length=256)
	public String getCompanyNameEn(){
		return this.companyNameEn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名英文
	 */
	public void setCompanyNameEn(String companyNameEn){
		this.companyNameEn = companyNameEn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名中文
	 */

	@Column(name ="COMPANY_NAME_ZN",nullable=true,length=256)
	public String getCompanyNameZn(){
		return this.companyNameZn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名中文
	 */
	public void setCompanyNameZn(String companyNameZn){
		this.companyNameZn = companyNameZn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址英文
	 */

	@Column(name ="ADDRESS_EN",nullable=true,length=512)
	public String getAddressEn(){
		return this.addressEn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址英文
	 */
	public void setAddressEn(String addressEn){
		this.addressEn = addressEn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址中文
	 */

	@Column(name ="ADDRESS_ZN",nullable=true,length=512)
	public String getAddressZn(){
		return this.addressZn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址中文
	 */
	public void setAddressZn(String addressZn){
		this.addressZn = addressZn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其它地址
	 */

	@Column(name ="OTHER_ADDRESS",nullable=true,length=512)
	public String getOtherAddress(){
		return this.otherAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其它地址
	 */
	public void setOtherAddress(String otherAddress){
		this.otherAddress = otherAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核文件存放地址
	 */

	@Column(name ="LOCATION_DOCUMENTS",nullable=true,length=512)
	public String getLocationDocuments(){
		return this.locationDocuments;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核文件存放地址
	 */
	public void setLocationDocuments(String locationDocuments){
		this.locationDocuments = locationDocuments;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主要联系人
	 */

	@Column(name ="PRIMARY_CONTACT",nullable=true,length=56)
	public String getPrimaryContact(){
		return this.primaryContact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主要联系人
	 */
	public void setPrimaryContact(String primaryContact){
		this.primaryContact = primaryContact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */

	@Column(name ="PRIMARY_CONTACT_EMAIL",nullable=true,length=32)
	public String getPrimaryContactEmail(){
		return this.primaryContactEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setPrimaryContactEmail(String primaryContactEmail){
		this.primaryContactEmail = primaryContactEmail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */

	@Column(name ="PRIMARY_CONTACT_TEL",nullable=true,length=32)
	public String getPrimaryContactTel(){
		return this.primaryContactTel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPrimaryContactTel(String primaryContactTel){
		this.primaryContactTel = primaryContactTel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第二联系人
	 */

	@Column(name ="SECONDARY_CONTACT",nullable=true,length=56)
	public String getSecondaryContact(){
		return this.secondaryContact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第二联系人
	 */
	public void setSecondaryContact(String secondaryContact){
		this.secondaryContact = secondaryContact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */

	@Column(name ="SECONDARY_CONTACT_EMAIL",nullable=true,length=32)
	public String getSecondaryContactEmail(){
		return this.secondaryContactEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setSecondaryContactEmail(String secondaryContactEmail){
		this.secondaryContactEmail = secondaryContactEmail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */

	@Column(name ="SECONDARY_CONTACT_TEL",nullable=true,length=32)
	public String getSecondaryContactTel(){
		return this.secondaryContactTel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setSecondaryContactTel(String secondaryContactTel){
		this.secondaryContactTel = secondaryContactTel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成立年份
	 */

	@Column(name ="YEAR_ESTABLISHED",nullable=true,length=32)
	public String getYearEstablished(){
		return this.yearEstablished;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成立年份
	 */
	public void setYearEstablished(String yearEstablished){
		this.yearEstablished = yearEstablished;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业执照号码
	 */

	@Column(name ="LICENSE_NUMBER",nullable=true,length=56)
	public String getLicenseNumber(){
		return this.licenseNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业执照号码
	 */
	public void setLicenseNumber(String licenseNumber){
		this.licenseNumber = licenseNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户行
	 */

	@Column(name ="BANK_NAME",nullable=true,length=256)
	public String getBankName(){
		return this.bankName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户行
	 */
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */

	@Column(name ="BANK_ACCOUNT",nullable=true,length=56)
	public String getBankAccount(){
		return this.bankAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setBankAccount(String bankAccount){
		this.bankAccount = bankAccount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签发机构
	 */

	@Column(name ="PERMIT_SSUED",nullable=true,length=256)
	public String getPermitSsued(){
		return this.permitSsued;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签发机构
	 */
	public void setPermitSsued(String permitSsued){
		this.permitSsued = permitSsued;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业执照有效期
	 */

	@Column(name ="PERMIT_EXPIRATION_DATE",nullable=true,length=32)
	public String getPermitExpirationDate(){
		return this.permitExpirationDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业执照有效期
	 */
	public void setPermitExpirationDate(String permitExpirationDate){
		this.permitExpirationDate = permitExpirationDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  businesslicen
	 */

	@Column(name ="FACILITY_BUSINESS_LICENSE",nullable=true,length=32)
	public String getFacilityBusinessLicense(){
		return this.facilityBusinessLicense;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  businesslicen
	 */
	public void setFacilityBusinessLicense(String facilityBusinessLicense){
		this.facilityBusinessLicense = facilityBusinessLicense;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产工序
	 */

	@Column(name ="PRODUCTION_PROCESS",nullable=true,length=56)
	public String getProductionProcess(){
		return this.productionProcess;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产工序
	 */
	public void setProductionProcess(String productionProcess){
		this.productionProcess = productionProcess;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否全员生产
	 */

	@Column(name ="FULL_PRODUCTION",nullable=true,length=32)
	public String getFullProduction(){
		return this.fullProduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否全员生产
	 */
	public void setFullProduction(String fullProduction){
		this.fullProduction = fullProduction;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产业类型
	 */

	@Column(name ="PRODUCT_CLASSIFICATION",nullable=true,length=32)
	public String getProductClassification(){
		return this.productClassification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产业类型
	 */
	public void setProductClassification(String productClassification){
		this.productClassification = productClassification;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  企业面积
	 */

	@Column(name ="FACILITY_LAND_SIZE",nullable=true,length=32)
	public String getFacilityLandSize(){
		return this.facilityLandSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  企业面积
	 */
	public void setFacilityLandSize(String facilityLandSize){
		this.facilityLandSize = facilityLandSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  企业总楼层面积
	 */

	@Column(name ="FACILITY_FOOR_SIZE",nullable=true,length=32)
	public String getFacilityFoorSize(){
		return this.facilityFoorSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  企业总楼层面积
	 */
	public void setFacilityFoorSize(String facilityFoorSize){
		this.facilityFoorSize = facilityFoorSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产
	 */

	@Column(name ="PRODUCTION",nullable=true,length=32)
	public String getProduction(){
		return this.production;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产
	 */
	public void setProduction(String production){
		this.production = production;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */

	@Column(name ="WAREHOUSE",nullable=true,length=32)
	public String getWarehouse(){
		return this.warehouse;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setWarehouse(String warehouse){
		this.warehouse = warehouse;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  宿舍
	 */

	@Column(name ="DORMITORY",nullable=true,length=32)
	public String getDormitory(){
		return this.dormitory;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  宿舍
	 */
	public void setDormitory(String dormitory){
		this.dormitory = dormitory;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其它
	 */

	@Column(name ="OTHER_SPECIFY",nullable=true,length=32)
	public String getOtherSpecify(){
		return this.otherSpecify;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其它
	 */
	public void setOtherSpecify(String otherSpecify){
		this.otherSpecify = otherSpecify;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务供应商
	 */

	@Column(name ="PROVIDE_NAME_PROVIDER",nullable=true,length=256)
	public String getProvideNameProvider(){
		return this.provideNameProvider;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务供应商
	 */
	public void setProvideNameProvider(String provideNameProvider){
		this.provideNameProvider = provideNameProvider;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  直接雇佣员工人数
	 */

	@Column(name ="PERMANENT_EMPLOYEE",nullable=true,length=32)
	public String getPermanentEmployee(){
		return this.permanentEmployee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  直接雇佣员工人数
	 */
	public void setPermanentEmployee(String permanentEmployee){
		this.permanentEmployee = permanentEmployee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  临时工
	 */

	@Column(name ="TEMPORARY",nullable=true,length=32)
	public String getTemporary(){
		return this.temporary;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  临时工
	 */
	public void setTemporary(String temporary){
		this.temporary = temporary;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  移民员工
	 */

	@Column(name ="MIGRANT_LABOUR",nullable=true,length=32)
	public String getMigrantLabour(){
		return this.migrantLabour;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  移民员工
	 */
	public void setMigrantLabour(String migrantLabour){
		this.migrantLabour = migrantLabour;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家内工作者
	 */

	@Column(name ="HOME_WORKERS",nullable=true,length=32)
	public String getHomeWorkers(){
		return this.homeWorkers;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家内工作者
	 */
	public void setHomeWorkers(String homeWorkers){
		this.homeWorkers = homeWorkers;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  女性员工人数
	 */

	@Column(name ="FEMALE",nullable=true,length=32)
	public String getFemale(){
		return this.female;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  女性员工人数
	 */
	public void setFemale(String female){
		this.female = female;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  男性员工人数
	 */

	@Column(name ="MALE",nullable=true,length=32)
	public String getMale(){
		return this.male;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  男性员工人数
	 */
	public void setMale(String male){
		this.male = male;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产员工人数
	 */

	@Column(name ="PRODUCTION_EMPLOYEES",nullable=true,length=32)
	public String getProductionEmployees(){
		return this.productionEmployees;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产员工人数
	 */
	public void setProductionEmployees(String productionEmployees){
		this.productionEmployees = productionEmployees;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政人员人数
	 */

	@Column(name ="ADMIN_STAF",nullable=true,length=32)
	public String getAdminStaf(){
		return this.adminStaf;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政人员人数
	 */
	public void setAdminStaf(String adminStaf){
		this.adminStaf = adminStaf;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  管理人员人数
	 */

	@Column(name ="MANAGEMENT",nullable=true,length=32)
	public String getManagement(){
		return this.management;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  管理人员人数
	 */
	public void setManagement(String management){
		this.management = management;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语种1
	 */

	@Column(name ="LANGUAGE1",nullable=true,length=32)
	public String getLanguage1(){
		return this.language1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语种1
	 */
	public void setLanguage1(String language1){
		this.language1 = language1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语种2
	 */

	@Column(name ="LANGUAGE2",nullable=true,length=32)
	public String getLanguage2(){
		return this.language2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语种2
	 */
	public void setLanguage2(String language2){
		this.language2 = language2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  占总人数百分比
	 */

	@Column(name ="ZZS_PRE1",nullable=true,length=32)
	public String getZzsPre1(){
		return this.zzsPre1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  占总人数百分比
	 */
	public void setZzsPre1(String zzsPre1){
		this.zzsPre1 = zzsPre1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  占总人数百分比
	 */

	@Column(name ="ZZS_PRE2",nullable=true,length=32)
	public String getZzsPre2(){
		return this.zzsPre2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  占总人数百分比
	 */
	public void setZzsPre2(String zzsPre2){
		this.zzsPre2 = zzsPre2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语种1
	 */

	@Column(name ="MLANGUAGE1",nullable=true,length=32)
	public String getMlanguage1(){
		return this.mlanguage1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语种1
	 */
	public void setMlanguage1(String mlanguage1){
		this.mlanguage1 = mlanguage1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语种2
	 */

	@Column(name ="MLANGUAGE2",nullable=true,length=32)
	public String getMlanguage2(){
		return this.mlanguage2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语种2
	 */
	public void setMlanguage2(String mlanguage2){
		this.mlanguage2 = mlanguage2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评估风险等级
	 */

	@Column(name ="LEVEL",nullable=true,length=32)
	public String getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评估风险等级
	 */
	public void setLevel(String level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工厂代码
	 */

	@Column(name ="COMPANY_CODE",nullable=true,length=32)
	public String getCompanyCode(){
		return this.companyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂代码
	 */
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */

	@Column(name ="SHENG_FEN",nullable=true,length=32)
	public String getShengFen(){
		return this.shengFen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setShengFen(String shengFen){
		this.shengFen = shengFen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  城市
	 */

	@Column(name ="CHENG_SHI",nullable=true,length=32)
	public String getChengShi(){
		return this.chengShi;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  城市
	 */
	public void setChengShi(String chengShi){
		this.chengShi = chengShi;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家
	 */

	@Column(name ="GUO_JIA",nullable=true,length=32)
	public String getGuoJia(){
		return this.guoJia;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家
	 */
	public void setGuoJia(String guoJia){
		this.guoJia = guoJia;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  档案编号
	 */

	@Column(name ="ARCHIVES_NO",nullable=true,length=32)
	public String getArchivesNo(){
		return this.archivesNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  档案编号
	 */
	public void setArchivesNo(String archivesNo){
		this.archivesNo = archivesNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品类型
	 */

	@Column(name ="PRO_TYPE_NAME",nullable=true,length=56)
	public String getProTypeName(){
		return this.proTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品类型
	 */
	public void setProTypeName(String proTypeName){
		this.proTypeName = proTypeName;
	}
}
