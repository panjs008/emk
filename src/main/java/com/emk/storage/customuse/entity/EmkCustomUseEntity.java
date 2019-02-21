package com.emk.storage.customuse.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 客户手册
 * @author onlineGenerator
 * @date 2018-10-26 20:33:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_custom_use", schema = "")
@SuppressWarnings("serial")
public class EmkCustomUseEntity implements java.io.Serializable {
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
	/**客户手册编号*/
	@Excel(name="客户手册编号",width=15)
	private String kyscbh;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**子客户代码*/
	@Excel(name="子客户代码",width=15)
	private String subCusNum;
	/**子客户名称*/
	@Excel(name="子客户名称",width=15)
	private String subCusName;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private String businesseDeptName;
	/**业务部门ID*/
	private String businesseDeptId;
	/**业务员*/
	@Excel(name="业务员",width=15)
	private String businesser;
	/**业务员ID*/
	private String businesserName;
	/**业务跟单员*/
	@Excel(name="业务跟单员",width=15)
	private String tracer;
	/**业务跟单员ID*/
	private String tracerName;
	@Excel(name = "生产跟单员")
	private String developer;
	private String developerName;
	/**人权验厂*/
	@Excel(name="人权验厂",width=15)
	private String peopleAdvice;
	/**质量验厂*/
	@Excel(name="质量验厂",width=15)
	private String zlAdvice;
	/**是否有人权验厂手册*/
	@Excel(name="是否有人权验厂手册",width=15)
	private String isPeopleAdvice;
	/**是否有质量验厂手册*/
	@Excel(name="是否有质量验厂手册",width=15)
	private String isZlAdvice;
	/**反恐验厂*/
	@Excel(name="反恐验厂",width=15)
	private String fkAdvice;
	/**是否有反恐验厂手册*/
	@Excel(name="是否有反恐验厂手册",width=15)
	private String isFkAdvice;
	/**验货*/
	@Excel(name="验货",width=15)
	private String checkHuo;
	/**是否有验货手册*/
	@Excel(name="是否有验货手册",width=15)
	private String isCheckHuo;
	/**测试*/
	@Excel(name="测试",width=15)
	private String test;
	/**是否有测试手册*/
	@Excel(name="是否有测试手册",width=15)
	private String isTest;
	/**包装*/
	@Excel(name="包装",width=15)
	private String pact;
	/**是否有包装手册*/
	@Excel(name="是否有包装手册",width=15)
	private String isPact;
	/**样品*/
	@Excel(name="样品",width=15)
	private String sample;
	/**是否有样品手册*/
	@Excel(name="是否有样品手册",width=15)
	private String isSample;
	/**发货*/
	@Excel(name="发货",width=15)
	private String sendHuo;
	/**是否有发货要求手册*/
	@Excel(name="是否有发货要求手册",width=15)
	private String isSendHuo;
	/**指定货代名称*/
	@Excel(name="指定货代名称",width=15)
	private String zdhdmc;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private String relation;
	/**人权验厂*/
	@Excel(name="人权验厂",width=15)
	private String peopleFileUrl;
	/**人权验厂*/
	@Excel(name="人权验厂",width=15)
	private String peopleFile;
	/**质量验厂*/
	@Excel(name="质量验厂",width=15)
	private String zlFileUrl;
	/**质量验厂*/
	@Excel(name="质量验厂",width=15)
	private String zlFile;
	/**验货手册*/
	@Excel(name="验货手册",width=15)
	private String checkHuoFileUrl;
	/**验货手册*/
	@Excel(name="验货手册",width=15)
	private String checkHuoFile;
	/**测试手册*/
	@Excel(name="测试手册",width=15)
	private String testFileUrl;
	/**测试手册*/
	@Excel(name="测试手册",width=15)
	private String testFile;
	/**包装手册*/
	@Excel(name="包装手册",width=15)
	private String pactFileUrl;
	/**包装手册*/
	@Excel(name="包装手册",width=15)
	private String pactFile;
	/**反恐验厂手册*/
	@Excel(name="反恐验厂手册",width=15)
	private String fkFileUrl;
	/**反恐验厂手册*/
	@Excel(name="反恐验厂手册",width=15)
	private String fkFile;
	/**样品手册*/
	@Excel(name="样品手册",width=15)
	private String sampleFileUrl;
	/**样品手册*/
	@Excel(name="样品手册",width=15)
	private String sampleFile;
	/**发货要求手册*/
	@Excel(name="发货要求手册",width=15)
	private String sendHuoFileUrl;
	/**发货要求手册*/
	@Excel(name="发货要求手册",width=15)
	private String sendHuoFile;
	@Excel(name="其他",width=15)
	private String other;
	@Excel(name="备注",width=15)
	private String remark;
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
	 *@return: java.lang.String  客户手册编号
	 */

	@Column(name ="KYSCBH",nullable=true,length=32)
	public String getKyscbh(){
		return this.kyscbh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户手册编号
	 */
	public void setKyscbh(String kyscbh){
		this.kyscbh = kyscbh;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户代码
	 */

	@Column(name ="CUS_NUM",nullable=true,length=32)
	public String getCusNum(){
		return this.cusNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户代码
	 */
	public void setCusNum(String cusNum){
		this.cusNum = cusNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */

	@Column(name ="CUS_NAME",nullable=true,length=32)
	public String getCusName(){
		return this.cusName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCusName(String cusName){
		this.cusName = cusName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  子客户代码
	 */

	@Column(name ="SUB_CUS_NUM",nullable=true,length=32)
	public String getSubCusNum(){
		return this.subCusNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  子客户代码
	 */
	public void setSubCusNum(String subCusNum){
		this.subCusNum = subCusNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  子客户名称
	 */

	@Column(name ="SUB_CUS_NAME",nullable=true,length=32)
	public String getSubCusName(){
		return this.subCusName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  子客户名称
	 */
	public void setSubCusName(String subCusName){
		this.subCusName = subCusName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务部门
	 */

	@Column(name ="BUSINESSE_DEPT_NAME",nullable=true,length=32)
	public String getBusinesseDeptName(){
		return this.businesseDeptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务部门
	 */
	public void setBusinesseDeptName(String businesseDeptName){
		this.businesseDeptName = businesseDeptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务部门ID
	 */

	@Column(name ="BUSINESSE_DEPT_ID",nullable=true,length=32)
	public String getBusinesseDeptId(){
		return this.businesseDeptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务部门ID
	 */
	public void setBusinesseDeptId(String businesseDeptId){
		this.businesseDeptId = businesseDeptId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */

	@Column(name ="BUSINESSER",nullable=true,length=32)
	public String getBusinesser(){
		return this.businesser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员
	 */
	public void setBusinesser(String businesser){
		this.businesser = businesser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员ID
	 */

	@Column(name ="BUSINESSER_NAME",nullable=true,length=32)
	public String getBusinesserName(){
		return this.businesserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员ID
	 */
	public void setBusinesserName(String businesserName){
		this.businesserName = businesserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务跟单员
	 */

	@Column(name ="TRACER",nullable=true,length=32)
	public String getTracer(){
		return this.tracer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务跟单员
	 */
	public void setTracer(String tracer){
		this.tracer = tracer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务跟单员ID
	 */

	@Column(name ="TRACER_NAME",nullable=true,length=32)
	public String getTracerName(){
		return this.tracerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务跟单员ID
	 */
	public void setTracerName(String tracerName){
		this.tracerName = tracerName;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人权验厂
	 */

	@Column(name ="PEOPLE_ADVICE",nullable=true,length=256)
	public String getPeopleAdvice(){
		return this.peopleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人权验厂
	 */
	public void setPeopleAdvice(String peopleAdvice){
		this.peopleAdvice = peopleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质量验厂
	 */

	@Column(name ="ZL_ADVICE",nullable=true,length=256)
	public String getZlAdvice(){
		return this.zlAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质量验厂
	 */
	public void setZlAdvice(String zlAdvice){
		this.zlAdvice = zlAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有人权验厂手册
	 */

	@Column(name ="IS_PEOPLE_ADVICE",nullable=true,length=32)
	public String getIsPeopleAdvice(){
		return this.isPeopleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有人权验厂手册
	 */
	public void setIsPeopleAdvice(String isPeopleAdvice){
		this.isPeopleAdvice = isPeopleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有质量验厂手册
	 */

	@Column(name ="IS_ZL_ADVICE",nullable=true,length=32)
	public String getIsZlAdvice(){
		return this.isZlAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有质量验厂手册
	 */
	public void setIsZlAdvice(String isZlAdvice){
		this.isZlAdvice = isZlAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  反恐验厂
	 */

	@Column(name ="FK_ADVICE",nullable=true,length=256)
	public String getFkAdvice(){
		return this.fkAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  反恐验厂
	 */
	public void setFkAdvice(String fkAdvice){
		this.fkAdvice = fkAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有反恐验厂手册
	 */

	@Column(name ="IS_FK_ADVICE",nullable=true,length=32)
	public String getIsFkAdvice(){
		return this.isFkAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有反恐验厂手册
	 */
	public void setIsFkAdvice(String isFkAdvice){
		this.isFkAdvice = isFkAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验货
	 */

	@Column(name ="CHECK_HUO",nullable=true,length=256)
	public String getCheckHuo(){
		return this.checkHuo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验货
	 */
	public void setCheckHuo(String checkHuo){
		this.checkHuo = checkHuo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有验货手册
	 */

	@Column(name ="IS_CHECK_HUO",nullable=true,length=32)
	public String getIsCheckHuo(){
		return this.isCheckHuo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有验货手册
	 */
	public void setIsCheckHuo(String isCheckHuo){
		this.isCheckHuo = isCheckHuo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试
	 */

	@Column(name ="TEST",nullable=true,length=256)
	public String getTest(){
		return this.test;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试
	 */
	public void setTest(String test){
		this.test = test;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有测试手册
	 */

	@Column(name ="IS_TEST",nullable=true,length=32)
	public String getIsTest(){
		return this.isTest;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有测试手册
	 */
	public void setIsTest(String isTest){
		this.isTest = isTest;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装
	 */

	@Column(name ="PACT",nullable=true,length=256)
	public String getPact(){
		return this.pact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装
	 */
	public void setPact(String pact){
		this.pact = pact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有包装手册
	 */

	@Column(name ="IS_PACT",nullable=true,length=32)
	public String getIsPact(){
		return this.isPact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有包装手册
	 */
	public void setIsPact(String isPact){
		this.isPact = isPact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品
	 */

	@Column(name ="SAMPLE",nullable=true,length=256)
	public String getSample(){
		return this.sample;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品
	 */
	public void setSample(String sample){
		this.sample = sample;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有样品手册
	 */

	@Column(name ="IS_SAMPLE",nullable=true,length=32)
	public String getIsSample(){
		return this.isSample;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有样品手册
	 */
	public void setIsSample(String isSample){
		this.isSample = isSample;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货
	 */

	@Column(name ="SEND_HUO",nullable=true,length=256)
	public String getSendHuo(){
		return this.sendHuo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货
	 */
	public void setSendHuo(String sendHuo){
		this.sendHuo = sendHuo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有发货要求手册
	 */

	@Column(name ="IS_SEND_HUO",nullable=true,length=32)
	public String getIsSendHuo(){
		return this.isSendHuo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有发货要求手册
	 */
	public void setIsSendHuo(String isSendHuo){
		this.isSendHuo = isSendHuo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  指定货代名称
	 */

	@Column(name ="ZDHDMC",nullable=true,length=256)
	public String getZdhdmc(){
		return this.zdhdmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  指定货代名称
	 */
	public void setZdhdmc(String zdhdmc){
		this.zdhdmc = zdhdmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */

	@Column(name ="RELATION",nullable=true,length=32)
	public String getRelation(){
		return this.relation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setRelation(String relation){
		this.relation = relation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人权验厂
	 */

	@Column(name ="PEOPLE_FILE_URL",nullable=true,length=256)
	public String getPeopleFileUrl(){
		return this.peopleFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人权验厂
	 */
	public void setPeopleFileUrl(String peopleFileUrl){
		this.peopleFileUrl = peopleFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人权验厂
	 */

	@Column(name ="PEOPLE_FILE",nullable=true,length=32)
	public String getPeopleFile(){
		return this.peopleFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人权验厂
	 */
	public void setPeopleFile(String peopleFile){
		this.peopleFile = peopleFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质量验厂
	 */

	@Column(name ="ZL_FILE_URL",nullable=true,length=256)
	public String getZlFileUrl(){
		return this.zlFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质量验厂
	 */
	public void setZlFileUrl(String zlFileUrl){
		this.zlFileUrl = zlFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质量验厂
	 */

	@Column(name ="ZL_FILE",nullable=true,length=32)
	public String getZlFile(){
		return this.zlFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质量验厂
	 */
	public void setZlFile(String zlFile){
		this.zlFile = zlFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验货手册
	 */

	@Column(name ="CHECK_HUO_FILE_URL",nullable=true,length=256)
	public String getCheckHuoFileUrl(){
		return this.checkHuoFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验货手册
	 */
	public void setCheckHuoFileUrl(String checkHuoFileUrl){
		this.checkHuoFileUrl = checkHuoFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验货手册
	 */

	@Column(name ="CHECK_HUO_FILE",nullable=true,length=32)
	public String getCheckHuoFile(){
		return this.checkHuoFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验货手册
	 */
	public void setCheckHuoFile(String checkHuoFile){
		this.checkHuoFile = checkHuoFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试手册
	 */

	@Column(name ="TEST_FILE_URL",nullable=true,length=256)
	public String getTestFileUrl(){
		return this.testFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试手册
	 */
	public void setTestFileUrl(String testFileUrl){
		this.testFileUrl = testFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试手册
	 */

	@Column(name ="TEST_FILE",nullable=true,length=32)
	public String getTestFile(){
		return this.testFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试手册
	 */
	public void setTestFile(String testFile){
		this.testFile = testFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装手册
	 */

	@Column(name ="PACT_FILE_URL",nullable=true,length=256)
	public String getPactFileUrl(){
		return this.pactFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装手册
	 */
	public void setPactFileUrl(String pactFileUrl){
		this.pactFileUrl = pactFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装手册
	 */

	@Column(name ="PACT_FILE",nullable=true,length=32)
	public String getPactFile(){
		return this.pactFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装手册
	 */
	public void setPactFile(String pactFile){
		this.pactFile = pactFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  反恐验厂手册
	 */

	@Column(name ="FK_FILE_URL",nullable=true,length=256)
	public String getFkFileUrl(){
		return this.fkFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  反恐验厂手册
	 */
	public void setFkFileUrl(String fkFileUrl){
		this.fkFileUrl = fkFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  反恐验厂手册
	 */

	@Column(name ="FK_FILE",nullable=true,length=32)
	public String getFkFile(){
		return this.fkFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  反恐验厂手册
	 */
	public void setFkFile(String fkFile){
		this.fkFile = fkFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品手册
	 */

	@Column(name ="SAMPLE_FILE_URL",nullable=true,length=256)
	public String getSampleFileUrl(){
		return this.sampleFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品手册
	 */
	public void setSampleFileUrl(String sampleFileUrl){
		this.sampleFileUrl = sampleFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品手册
	 */

	@Column(name ="SAMPLE_FILE",nullable=true,length=32)
	public String getSampleFile(){
		return this.sampleFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品手册
	 */
	public void setSampleFile(String sampleFile){
		this.sampleFile = sampleFile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货要求手册
	 */

	@Column(name ="SEND_HUO_FILE_URL",nullable=true,length=256)
	public String getSendHuoFileUrl(){
		return this.sendHuoFileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货要求手册
	 */
	public void setSendHuoFileUrl(String sendHuoFileUrl){
		this.sendHuoFileUrl = sendHuoFileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货要求手册
	 */

	@Column(name ="SEND_HUO_FILE",nullable=true,length=32)
	public String getSendHuoFile(){
		return this.sendHuoFile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货要求手册
	 */
	public void setSendHuoFile(String sendHuoFile){
		this.sendHuoFile = sendHuoFile;
	}

	@Column(name ="other",nullable=true,length=32)
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name ="remark",nullable=true,length=32)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "DEVELOPER", nullable = true, length = 32)
	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	@Column(name = "DEVELOPER_NAME", nullable = true, length = 32)
	public String getDeveloperName() {
		return this.developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
}
