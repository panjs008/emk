package com.emk.produce.produceschedule.entity;

import java.math.BigDecimal;
import java.util.Date;

import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 订单生产管理
 * @author onlineGenerator
 * @date 2018-10-31 14:24:06
 * @version V1.0
 *
 */
@Entity
@Table(name = "emk_produce", schema = "")
@SuppressWarnings("serial")
public class EmkProduceScheduleEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**所属部门*/
	private String sysOrgCode;
	/**需求单号*/
	private String materialNo;
	/**提交日期*/
	@Excel(name="提交日期",width=15)
	private String kdDate;
	/**业务员*/
	@Excel(name="业务员",width=15)
	private String businesser;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**业务员ID*/
	private String businesserName;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private String businesseDeptName;
	/**业务部门ID*/
	private String businesseDeptId;
	/**生产跟单员*/
	@Excel(name="生产跟单员",width=15)
	private String developer;
	/**生产跟单员ID*/
	private String developerName;
	/**业务跟单员*/
	@Excel(name="业务跟单员",width=15)
	private String tracer;
	/**业务跟单员ID*/
	private String tracerName;

	/**审核意见*/
	@Excel(name="审核意见",width=15)
	private String leadAdvice;
	/**是否通过*/
	@Excel(name="是否通过",width=15)
	private String isPass;
	/**审核人ID*/
	private String leadUserId;
	/**审核人*/
	@Excel(name="审核人",width=15)
	private String leader;
	/**状态*/
	@Excel(name="状态",width=15)
	private String state;
	/**试身打样处理人*/
	@Excel(name="试身打样处理人",width=15)
	private String ssSampleUser;
	/**试身打样处理人ID*/
	private String ssSampleUserId;
	/**产前打样处理人*/
	@Excel(name="产前打样处理人",width=15)
	private String cqSampleUser;
	/**产前打样处理人ID*/
	private String cqSampleUserId;
	/**试身打样处理意见*/
	@Excel(name="试身打样处理意见",width=15)
	private String ssSampleAdvice;
	/**产前打样处理意见*/
	@Excel(name="产前打样处理意见",width=15)
	private String cqSampleAdvice;
	/**色样处理人*/
	@Excel(name="色样处理人",width=15)
	private String colorUser;
	/**色样处理人ID*/
	private String colorUserId;
	/**色样处理意见*/
	@Excel(name="色样处理意见",width=15)
	private String colorAdvice;
	/**测试人*/
	@Excel(name="测试人",width=15)
	private String testUser;
	/**测试人ID*/
	private String testUserId;
	/**测试处理意见*/
	@Excel(name="测试处理意见",width=15)
	private String testUserAdvice;
	/**试身样状态*/
	@Excel(name="试身样状态",width=15)
	private String ssyzt;
	/**试身样最晚确认时间*/
	@Excel(name="试身样最晚确认时间",width=15)
	private String ssyDate;
	/**试身样距离剩余天数*/
	@Excel(name="试身样距离剩余天数",width=15)
	private Integer leavelSsy;
	/**产前样状态*/
	@Excel(name="产前样状态",width=15)
	private String cqyzt;
	/**产前样最晚确认时间*/
	@Excel(name="产前样最晚确认时间",width=15)
	private String cqyDate;
	/**产前样距离剩余天数*/
	@Excel(name="产前样距离剩余天数",width=15)
	private Integer leavelCq;
	/**色样状态*/
	@Excel(name="色样状态",width=15)
	private String syzt;
	/**色样最晚确认时间*/
	@Excel(name="色样最晚确认时间",width=15)
	private String syDate;
	/**色样距离剩余天数*/
	@Excel(name="色样距离剩余天数",width=15)
	private String leavelSy;
	/**船样状态*/
	@Excel(name="船样状态",width=15)
	private String cyzt;
	/**船样最晚确认时间*/
	@Excel(name="船样最晚确认时间",width=15)
	private String cyDate;
	/**船样距离剩余天数*/
	@Excel(name="船样距离剩余天数",width=15)
	private String leavelCy;
	/**产前会议*/
	@Excel(name="产前会议",width=15)
	private String cqhy;
	/**产前会议最晚日期*/
	@Excel(name="产前会议最晚日期",width=15)
	private String cqhyDate;
	/**测试通过日期*/
	@Excel(name="测试通过日期",width=15)
	private String testPass;
	/**最晚通过测试日期*/
	@Excel(name="最晚通过测试日期",width=15)
	private String testPassDate;
	/**中查日期*/
	@Excel(name="中查日期",width=15)
	private String zcrq;
	/**中查最晚日期*/
	@Excel(name="中查最晚日期",width=15)
	private String zcrqDate;
	/**尾查日期*/
	@Excel(name="尾查日期",width=15)
	private String wcrq;
	/**尾查最晚日期*/
	@Excel(name="尾查最晚日期",width=15)
	private String wcrqDate;
	/**订舱状态*/
	@Excel(name="订舱状态",width=15)
	private String dczt;
	/**订舱最晚日期*/
	@Excel(name="订舱最晚日期",width=15)
	private String dcztDate;
	/**交期*/
	@Excel(name="交期",width=15)
	private String recevieDate;
	/**收款*/
	@Excel(name="收款",width=15)
	private String shouK;
	/**产前会议处理ID*/
	private String cqhyUserId;
	/**产前会议处理人*/
	@Excel(name="产前会议处理人",width=15)
	private String cqhyUserName;
	/**产前会议处理意见*/
	@Excel(name="产前会议处理意见",width=15)
	private String cqhyAdvice;
	/**原料辅料采购人ID*/
	private String ylflcgUserId;
	/**原料辅料采购人*/
	@Excel(name="原料辅料采购人",width=15)
	private String ylflcgUserName;
	/**原料辅料采购意见*/
	@Excel(name="原料辅料采购意见",width=15)
	private String ylflcgAdvice;
	/**染色处理人ID*/
	private String ranUserId;
	/**染色处理人*/
	@Excel(name="染色处理人",width=15)
	private String ranUserName;
	/**染色处理意见*/
	@Excel(name="染色处理意见",width=15)
	private String ranAdvice;
	/**裁剪处理人ID*/
	private String caiUserId;
	/**裁剪处理人*/
	@Excel(name="裁剪处理人",width=15)
	private String caiUserName;
	/**裁剪处理意见*/
	@Excel(name="裁剪处理意见",width=15)
	private String caiUserAdvice;
	/**缝制处理人ID*/
	private String fengUserId;
	/**缝制处理人*/
	@Excel(name="缝制处理人",width=15)
	private String fengUserName;
	/**缝制处理意见*/
	@Excel(name="缝制处理意见",width=15)
	private String fengUserAdvice;
	/**中期检查人ID*/
	private String zqjcUserId;
	/**中期检查人*/
	@Excel(name="中期检查人",width=15)
	private String zqjcUserName;
	/**中期检查意见*/
	@Excel(name="中期检查意见",width=15)
	private String zqjcAdvice;
	/**烫标处理人ID*/
	private String biaoUserId;
	/**烫标处理人*/
	@Excel(name="烫标处理人",width=15)
	private String biaoUserName;
	/**烫标处理意见*/
	@Excel(name="烫标处理意见",width=15)
	private String biaoAdvice;
	/**整烫处理人ID*/
	private String zhengtUserId;
	/**整烫处理人*/
	@Excel(name="整烫处理人",width=15)
	private String zhengtUserName;
	/**整烫处理意见*/
	@Excel(name="整烫处理意见",width=15)
	private String zhengtAdvice;
	/**船样处理人ID*/
	private String chuangUserId;
	/**船样处理人*/
	@Excel(name="船样处理人",width=15)
	private String chuangUserName;
	/**船样处理意见*/
	@Excel(name="船样处理意见",width=15)
	private String chuangAdvice;
	/**包装处理人ID*/
	private String boxUserId;
	/**包装处理人*/
	@Excel(name="包装处理人",width=15)
	private String boxUserName;
	/**包装处理意见*/
	@Excel(name="包装处理意见",width=15)
	private String boxAdvice;
	/**出货处理人ID*/
	private String outUserId;
	/**出货处理人*/
	@Excel(name="出货处理人",width=15)
	private String outUserName;
	/**出货处理意见*/
	@Excel(name="出货处理意见",width=15)
	private String outAdvice;
	/**尾期检查处理人ID*/
	private String weiUserId;
	/**尾期检查处理人*/
	@Excel(name="尾期检查处理人",width=15)
	private String weiUserName;
	/**尾期检查处理意见*/
	@Excel(name="尾期检查处理意见",width=15)
	private String weiAdvice;
	/**收款处理人ID*/
	private String shouUserId;
	/**收款处理人*/
	@Excel(name="收款处理人",width=15)
	private String shouUserName;
	/**收款处理意见*/
	@Excel(name="收款处理意见",width=15)
	private String shouAdvice;

	/**
	 *方法: 取得String
	 *@return: String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置String
	 *@param: String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得String
	 *@return: String  创建人名称
	 */
	//@Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得String
	 *@return: String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得String
	 *@return: String  需求单号
	 */

	@Column(name ="MATERIAL_NO",nullable=true,length=32)
	public String getMaterialNo(){
		return this.materialNo;
	}

	/**
	 *方法: 设置String
	 *@param: String  需求单号
	 */
	public void setMaterialNo(String materialNo){
		this.materialNo = materialNo;
	}
	/**
	 *方法: 取得String
	 *@return: String  提交日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  提交日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  业务员
	 */

	@Column(name ="BUSINESSER",nullable=true,length=32)
	public String getBusinesser(){
		return this.businesser;
	}

	/**
	 *方法: 设置String
	 *@param: String  业务员
	 */
	public void setBusinesser(String businesser){
		this.businesser = businesser;
	}
	/**
	 *方法: 取得String
	 *@return: String  客户代码
	 */

	@Column(name ="CUS_NUM",nullable=true,length=32)
	public String getCusNum(){
		return this.cusNum;
	}

	/**
	 *方法: 设置String
	 *@param: String  客户代码
	 */
	public void setCusNum(String cusNum){
		this.cusNum = cusNum;
	}
	/**
	 *方法: 取得String
	 *@return: String  业务员ID
	 */

	@Column(name ="BUSINESSER_NAME",nullable=true,length=32)
	public String getBusinesserName(){
		return this.businesserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  业务员ID
	 */
	public void setBusinesserName(String businesserName){
		this.businesserName = businesserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  客户名称
	 */

	@Column(name ="CUS_NAME",nullable=true,length=32)
	public String getCusName(){
		return this.cusName;
	}

	/**
	 *方法: 设置String
	 *@param: String  客户名称
	 */
	public void setCusName(String cusName){
		this.cusName = cusName;
	}

	/**
	 *方法: 取得String
	 *@return: String  描述
	 */

	@Column(name ="SAMPLE_NO_DESC",nullable=true,length=256)
	public String getSampleNoDesc(){
		return this.sampleNoDesc;
	}

	/**
	 *方法: 设置String
	 *@param: String  描述
	 */
	public void setSampleNoDesc(String sampleNoDesc){
		this.sampleNoDesc = sampleNoDesc;
	}
	/**
	 *方法: 取得String
	 *@return: String  业务部门
	 */

	@Column(name ="BUSINESSE_DEPT_NAME",nullable=true,length=32)
	public String getBusinesseDeptName(){
		return this.businesseDeptName;
	}

	/**
	 *方法: 设置String
	 *@param: String  业务部门
	 */
	public void setBusinesseDeptName(String businesseDeptName){
		this.businesseDeptName = businesseDeptName;
	}
	/**
	 *方法: 取得String
	 *@return: String  业务部门ID
	 */

	@Column(name ="BUSINESSE_DEPT_ID",nullable=true,length=32)
	public String getBusinesseDeptId(){
		return this.businesseDeptId;
	}

	/**
	 *方法: 设置String
	 *@param: String  业务部门ID
	 */
	public void setBusinesseDeptId(String businesseDeptId){
		this.businesseDeptId = businesseDeptId;
	}
	/**
	 *方法: 取得String
	 *@return: String  生产跟单员
	 */

	@Column(name ="DEVELOPER",nullable=true,length=32)
	public String getDeveloper(){
		return this.developer;
	}

	/**
	 *方法: 设置String
	 *@param: String  生产跟单员
	 */
	public void setDeveloper(String developer){
		this.developer = developer;
	}
	/**
	 *方法: 取得String
	 *@return: String  生产跟单员ID
	 */

	@Column(name ="DEVELOPER_NAME",nullable=true,length=32)
	public String getDeveloperName(){
		return this.developerName;
	}

	/**
	 *方法: 设置String
	 *@param: String  生产跟单员ID
	 */
	public void setDeveloperName(String developerName){
		this.developerName = developerName;
	}
	/**
	 *方法: 取得String
	 *@return: String  业务跟单员
	 */

	@Column(name ="TRACER",nullable=true,length=32)
	public String getTracer(){
		return this.tracer;
	}

	/**
	 *方法: 设置String
	 *@param: String  业务跟单员
	 */
	public void setTracer(String tracer){
		this.tracer = tracer;
	}
	/**
	 *方法: 取得String
	 *@return: String  业务跟单员ID
	 */

	@Column(name ="TRACER_NAME",nullable=true,length=32)
	public String getTracerName(){
		return this.tracerName;
	}

	/**
	 *方法: 设置String
	 *@param: String  业务跟单员ID
	 */
	public void setTracerName(String tracerName){
		this.tracerName = tracerName;
	}

	/**
	 *方法: 取得String
	 *@return: String  审核意见
	 */

	@Column(name ="LEAD_ADVICE",nullable=true,length=256)
	public String getLeadAdvice(){
		return this.leadAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  审核意见
	 */
	public void setLeadAdvice(String leadAdvice){
		this.leadAdvice = leadAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  是否通过
	 */

	@Column(name ="IS_PASS",nullable=true,length=12)
	public String getIsPass(){
		return this.isPass;
	}

	/**
	 *方法: 设置String
	 *@param: String  是否通过
	 */
	public void setIsPass(String isPass){
		this.isPass = isPass;
	}
	/**
	 *方法: 取得String
	 *@return: String  审核人ID
	 */

	@Column(name ="LEAD_USER_ID",nullable=true,length=32)
	public String getLeadUserId(){
		return this.leadUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  审核人ID
	 */
	public void setLeadUserId(String leadUserId){
		this.leadUserId = leadUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  审核人
	 */

	@Column(name ="LEADER",nullable=true,length=32)
	public String getLeader(){
		return this.leader;
	}

	/**
	 *方法: 设置String
	 *@param: String  审核人
	 */
	public void setLeader(String leader){
		this.leader = leader;
	}
	/**
	 *方法: 取得String
	 *@return: String  状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public String getState(){
		return this.state;
	}

	/**
	 *方法: 设置String
	 *@param: String  状态
	 */
	public void setState(String state){
		this.state = state;
	}
	/**
	 *方法: 取得String
	 *@return: String  试身打样处理人
	 */

	@Column(name ="SS_SAMPLE_USER",nullable=true,length=32)
	public String getSsSampleUser(){
		return this.ssSampleUser;
	}

	/**
	 *方法: 设置String
	 *@param: String  试身打样处理人
	 */
	public void setSsSampleUser(String ssSampleUser){
		this.ssSampleUser = ssSampleUser;
	}
	/**
	 *方法: 取得String
	 *@return: String  试身打样处理人ID
	 */

	@Column(name ="SS_SAMPLE_USER_ID",nullable=true,length=32)
	public String getSsSampleUserId(){
		return this.ssSampleUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  试身打样处理人ID
	 */
	public void setSsSampleUserId(String ssSampleUserId){
		this.ssSampleUserId = ssSampleUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前打样处理人
	 */

	@Column(name ="CQ_SAMPLE_USER",nullable=true,length=32)
	public String getCqSampleUser(){
		return this.cqSampleUser;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前打样处理人
	 */
	public void setCqSampleUser(String cqSampleUser){
		this.cqSampleUser = cqSampleUser;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前打样处理人ID
	 */

	@Column(name ="CQ_SAMPLE_USER_ID",nullable=true,length=32)
	public String getCqSampleUserId(){
		return this.cqSampleUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前打样处理人ID
	 */
	public void setCqSampleUserId(String cqSampleUserId){
		this.cqSampleUserId = cqSampleUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  试身打样处理意见
	 */

	@Column(name ="SS_SAMPLE_ADVICE",nullable=true,length=256)
	public String getSsSampleAdvice(){
		return this.ssSampleAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  试身打样处理意见
	 */
	public void setSsSampleAdvice(String ssSampleAdvice){
		this.ssSampleAdvice = ssSampleAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前打样处理意见
	 */

	@Column(name ="CQ_SAMPLE_ADVICE",nullable=true,length=256)
	public String getCqSampleAdvice(){
		return this.cqSampleAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前打样处理意见
	 */
	public void setCqSampleAdvice(String cqSampleAdvice){
		this.cqSampleAdvice = cqSampleAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  色样处理人
	 */

	@Column(name ="COLOR_USER",nullable=true,length=32)
	public String getColorUser(){
		return this.colorUser;
	}

	/**
	 *方法: 设置String
	 *@param: String  色样处理人
	 */
	public void setColorUser(String colorUser){
		this.colorUser = colorUser;
	}
	/**
	 *方法: 取得String
	 *@return: String  色样处理人ID
	 */

	@Column(name ="COLOR_USER_ID",nullable=true,length=32)
	public String getColorUserId(){
		return this.colorUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  色样处理人ID
	 */
	public void setColorUserId(String colorUserId){
		this.colorUserId = colorUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  色样处理意见
	 */

	@Column(name ="COLOR_ADVICE",nullable=true,length=256)
	public String getColorAdvice(){
		return this.colorAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  色样处理意见
	 */
	public void setColorAdvice(String colorAdvice){
		this.colorAdvice = colorAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  测试人
	 */

	@Column(name ="TEST_USER",nullable=true,length=32)
	public String getTestUser(){
		return this.testUser;
	}

	/**
	 *方法: 设置String
	 *@param: String  测试人
	 */
	public void setTestUser(String testUser){
		this.testUser = testUser;
	}
	/**
	 *方法: 取得String
	 *@return: String  测试人ID
	 */

	@Column(name ="TEST_USER_ID",nullable=true,length=32)
	public String getTestUserId(){
		return this.testUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  测试人ID
	 */
	public void setTestUserId(String testUserId){
		this.testUserId = testUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  测试处理意见
	 */

	@Column(name ="TEST_USER_ADVICE",nullable=true,length=256)
	public String getTestUserAdvice(){
		return this.testUserAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  测试处理意见
	 */
	public void setTestUserAdvice(String testUserAdvice){
		this.testUserAdvice = testUserAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  试身样状态
	 */

	@Column(name ="SSYZT",nullable=true,length=32)
	public String getSsyzt(){
		return this.ssyzt;
	}

	/**
	 *方法: 设置String
	 *@param: String  试身样状态
	 */
	public void setSsyzt(String ssyzt){
		this.ssyzt = ssyzt;
	}
	/**
	 *方法: 取得String
	 *@return: String  试身样最晚确认时间
	 */

	@Column(name ="SSY_DATE",nullable=true,length=32)
	public String getSsyDate(){
		return this.ssyDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  试身样最晚确认时间
	 */
	public void setSsyDate(String ssyDate){
		this.ssyDate = ssyDate;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  试身样距离剩余天数
	 */

	@Column(name ="LEAVEL_SSY",nullable=true,length=32)
	public Integer getLeavelSsy(){
		return this.leavelSsy;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  试身样距离剩余天数
	 */
	public void setLeavelSsy(Integer leavelSsy){
		this.leavelSsy = leavelSsy;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前样状态
	 */

	@Column(name ="CQYZT",nullable=true,length=32)
	public String getCqyzt(){
		return this.cqyzt;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前样状态
	 */
	public void setCqyzt(String cqyzt){
		this.cqyzt = cqyzt;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前样最晚确认时间
	 */

	@Column(name ="CQY_DATE",nullable=true,length=32)
	public String getCqyDate(){
		return this.cqyDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前样最晚确认时间
	 */
	public void setCqyDate(String cqyDate){
		this.cqyDate = cqyDate;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  产前样距离剩余天数
	 */

	@Column(name ="LEAVEL_CQ",nullable=true,length=32)
	public Integer getLeavelCq(){
		return this.leavelCq;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  产前样距离剩余天数
	 */
	public void setLeavelCq(Integer leavelCq){
		this.leavelCq = leavelCq;
	}
	/**
	 *方法: 取得String
	 *@return: String  色样状态
	 */

	@Column(name ="SYZT",nullable=true,length=32)
	public String getSyzt(){
		return this.syzt;
	}

	/**
	 *方法: 设置String
	 *@param: String  色样状态
	 */
	public void setSyzt(String syzt){
		this.syzt = syzt;
	}
	/**
	 *方法: 取得String
	 *@return: String  色样最晚确认时间
	 */

	@Column(name ="SY_DATE",nullable=true,length=32)
	public String getSyDate(){
		return this.syDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  色样最晚确认时间
	 */
	public void setSyDate(String syDate){
		this.syDate = syDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  色样距离剩余天数
	 */

	@Column(name ="LEAVEL_SY",nullable=true,length=32)
	public String getLeavelSy(){
		return this.leavelSy;
	}

	/**
	 *方法: 设置String
	 *@param: String  色样距离剩余天数
	 */
	public void setLeavelSy(String leavelSy){
		this.leavelSy = leavelSy;
	}
	/**
	 *方法: 取得String
	 *@return: String  船样状态
	 */

	@Column(name ="CYZT",nullable=true,length=32)
	public String getCyzt(){
		return this.cyzt;
	}

	/**
	 *方法: 设置String
	 *@param: String  船样状态
	 */
	public void setCyzt(String cyzt){
		this.cyzt = cyzt;
	}
	/**
	 *方法: 取得String
	 *@return: String  船样最晚确认时间
	 */

	@Column(name ="CY_DATE",nullable=true,length=32)
	public String getCyDate(){
		return this.cyDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  船样最晚确认时间
	 */
	public void setCyDate(String cyDate){
		this.cyDate = cyDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  船样距离剩余天数
	 */

	@Column(name ="LEAVEL_CY",nullable=true,length=32)
	public String getLeavelCy(){
		return this.leavelCy;
	}

	/**
	 *方法: 设置String
	 *@param: String  船样距离剩余天数
	 */
	public void setLeavelCy(String leavelCy){
		this.leavelCy = leavelCy;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前会议
	 */

	@Column(name ="CQHY",nullable=true,length=32)
	public String getCqhy(){
		return this.cqhy;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前会议
	 */
	public void setCqhy(String cqhy){
		this.cqhy = cqhy;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前会议最晚日期
	 */

	@Column(name ="CQHY_DATE",nullable=true,length=32)
	public String getCqhyDate(){
		return this.cqhyDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前会议最晚日期
	 */
	public void setCqhyDate(String cqhyDate){
		this.cqhyDate = cqhyDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  测试通过日期
	 */

	@Column(name ="TEST_PASS",nullable=true,length=32)
	public String getTestPass(){
		return this.testPass;
	}

	/**
	 *方法: 设置String
	 *@param: String  测试通过日期
	 */
	public void setTestPass(String testPass){
		this.testPass = testPass;
	}
	/**
	 *方法: 取得String
	 *@return: String  最晚通过测试日期
	 */

	@Column(name ="TEST_PASS_DATE",nullable=true,length=32)
	public String getTestPassDate(){
		return this.testPassDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  最晚通过测试日期
	 */
	public void setTestPassDate(String testPassDate){
		this.testPassDate = testPassDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  中查日期
	 */

	@Column(name ="ZCRQ",nullable=true,length=32)
	public String getZcrq(){
		return this.zcrq;
	}

	/**
	 *方法: 设置String
	 *@param: String  中查日期
	 */
	public void setZcrq(String zcrq){
		this.zcrq = zcrq;
	}
	/**
	 *方法: 取得String
	 *@return: String  中查最晚日期
	 */

	@Column(name ="ZCRQ_DATE",nullable=true,length=32)
	public String getZcrqDate(){
		return this.zcrqDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  中查最晚日期
	 */
	public void setZcrqDate(String zcrqDate){
		this.zcrqDate = zcrqDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  尾查日期
	 */

	@Column(name ="WCRQ",nullable=true,length=32)
	public String getWcrq(){
		return this.wcrq;
	}

	/**
	 *方法: 设置String
	 *@param: String  尾查日期
	 */
	public void setWcrq(String wcrq){
		this.wcrq = wcrq;
	}
	/**
	 *方法: 取得String
	 *@return: String  尾查最晚日期
	 */

	@Column(name ="WCRQ_DATE",nullable=true,length=32)
	public String getWcrqDate(){
		return this.wcrqDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  尾查最晚日期
	 */
	public void setWcrqDate(String wcrqDate){
		this.wcrqDate = wcrqDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  订舱状态
	 */

	@Column(name ="DCZT",nullable=true,length=32)
	public String getDczt(){
		return this.dczt;
	}

	/**
	 *方法: 设置String
	 *@param: String  订舱状态
	 */
	public void setDczt(String dczt){
		this.dczt = dczt;
	}
	/**
	 *方法: 取得String
	 *@return: String  订舱最晚日期
	 */

	@Column(name ="DCZT_DATE",nullable=true,length=32)
	public String getDcztDate(){
		return this.dcztDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  订舱最晚日期
	 */
	public void setDcztDate(String dcztDate){
		this.dcztDate = dcztDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  交期
	 */

	@Column(name ="RECEVIE_DATE",nullable=true,length=32)
	public String getRecevieDate(){
		return this.recevieDate;
	}

	/**
	 *方法: 设置String
	 *@param: String  交期
	 */
	public void setRecevieDate(String recevieDate){
		this.recevieDate = recevieDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  收款
	 */

	@Column(name ="SHOU_K",nullable=true,length=256)
	public String getShouK(){
		return this.shouK;
	}

	/**
	 *方法: 设置String
	 *@param: String  收款
	 */
	public void setShouK(String shouK){
		this.shouK = shouK;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前会议处理ID
	 */

	@Column(name ="CQHY_USER_ID",nullable=true,length=32)
	public String getCqhyUserId(){
		return this.cqhyUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前会议处理ID
	 */
	public void setCqhyUserId(String cqhyUserId){
		this.cqhyUserId = cqhyUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前会议处理人
	 */

	@Column(name ="CQHY_USER_NAME",nullable=true,length=32)
	public String getCqhyUserName(){
		return this.cqhyUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前会议处理人
	 */
	public void setCqhyUserName(String cqhyUserName){
		this.cqhyUserName = cqhyUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  产前会议处理意见
	 */

	@Column(name ="CQHY_ADVICE",nullable=true,length=256)
	public String getCqhyAdvice(){
		return this.cqhyAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  产前会议处理意见
	 */
	public void setCqhyAdvice(String cqhyAdvice){
		this.cqhyAdvice = cqhyAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  原料辅料采购人ID
	 */

	@Column(name ="YLFLCG_USER_ID",nullable=true,length=32)
	public String getYlflcgUserId(){
		return this.ylflcgUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  原料辅料采购人ID
	 */
	public void setYlflcgUserId(String ylflcgUserId){
		this.ylflcgUserId = ylflcgUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  原料辅料采购人
	 */

	@Column(name ="YLFLCG_USER_NAME",nullable=true,length=32)
	public String getYlflcgUserName(){
		return this.ylflcgUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  原料辅料采购人
	 */
	public void setYlflcgUserName(String ylflcgUserName){
		this.ylflcgUserName = ylflcgUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  原料辅料采购意见
	 */

	@Column(name ="YLFLCG_ADVICE",nullable=true,length=256)
	public String getYlflcgAdvice(){
		return this.ylflcgAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  原料辅料采购意见
	 */
	public void setYlflcgAdvice(String ylflcgAdvice){
		this.ylflcgAdvice = ylflcgAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  染色处理人ID
	 */

	@Column(name ="RAN_USER_ID",nullable=true,length=32)
	public String getRanUserId(){
		return this.ranUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  染色处理人ID
	 */
	public void setRanUserId(String ranUserId){
		this.ranUserId = ranUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  染色处理人
	 */

	@Column(name ="RAN_USER_NAME",nullable=true,length=32)
	public String getRanUserName(){
		return this.ranUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  染色处理人
	 */
	public void setRanUserName(String ranUserName){
		this.ranUserName = ranUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  染色处理意见
	 */

	@Column(name ="RAN_ADVICE",nullable=true,length=256)
	public String getRanAdvice(){
		return this.ranAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  染色处理意见
	 */
	public void setRanAdvice(String ranAdvice){
		this.ranAdvice = ranAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  裁剪处理人ID
	 */

	@Column(name ="CAI_USER_ID",nullable=true,length=32)
	public String getCaiUserId(){
		return this.caiUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  裁剪处理人ID
	 */
	public void setCaiUserId(String caiUserId){
		this.caiUserId = caiUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  裁剪处理人
	 */

	@Column(name ="CAI_USER_NAME",nullable=true,length=32)
	public String getCaiUserName(){
		return this.caiUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  裁剪处理人
	 */
	public void setCaiUserName(String caiUserName){
		this.caiUserName = caiUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  裁剪处理意见
	 */

	@Column(name ="CAI_USER_ADVICE",nullable=true,length=256)
	public String getCaiUserAdvice(){
		return this.caiUserAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  裁剪处理意见
	 */
	public void setCaiUserAdvice(String caiUserAdvice){
		this.caiUserAdvice = caiUserAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  缝制处理人ID
	 */

	@Column(name ="FENG_USER_ID",nullable=true,length=32)
	public String getFengUserId(){
		return this.fengUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  缝制处理人ID
	 */
	public void setFengUserId(String fengUserId){
		this.fengUserId = fengUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  缝制处理人
	 */

	@Column(name ="FENG_USER_NAME",nullable=true,length=32)
	public String getFengUserName(){
		return this.fengUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  缝制处理人
	 */
	public void setFengUserName(String fengUserName){
		this.fengUserName = fengUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  缝制处理意见
	 */

	@Column(name ="FENG_USER_ADVICE",nullable=true,length=256)
	public String getFengUserAdvice(){
		return this.fengUserAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  缝制处理意见
	 */
	public void setFengUserAdvice(String fengUserAdvice){
		this.fengUserAdvice = fengUserAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  中期检查人ID
	 */

	@Column(name ="ZQJC_USER_ID",nullable=true,length=32)
	public String getZqjcUserId(){
		return this.zqjcUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  中期检查人ID
	 */
	public void setZqjcUserId(String zqjcUserId){
		this.zqjcUserId = zqjcUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  中期检查人
	 */

	@Column(name ="ZQJC_USER_NAME",nullable=true,length=32)
	public String getZqjcUserName(){
		return this.zqjcUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  中期检查人
	 */
	public void setZqjcUserName(String zqjcUserName){
		this.zqjcUserName = zqjcUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  中期检查意见
	 */

	@Column(name ="ZQJC_ADVICE",nullable=true,length=256)
	public String getZqjcAdvice(){
		return this.zqjcAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  中期检查意见
	 */
	public void setZqjcAdvice(String zqjcAdvice){
		this.zqjcAdvice = zqjcAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  烫标处理人ID
	 */

	@Column(name ="BIAO_USER_ID",nullable=true,length=32)
	public String getBiaoUserId(){
		return this.biaoUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  烫标处理人ID
	 */
	public void setBiaoUserId(String biaoUserId){
		this.biaoUserId = biaoUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  烫标处理人
	 */

	@Column(name ="BIAO_USER_NAME",nullable=true,length=32)
	public String getBiaoUserName(){
		return this.biaoUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  烫标处理人
	 */
	public void setBiaoUserName(String biaoUserName){
		this.biaoUserName = biaoUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  烫标处理意见
	 */

	@Column(name ="BIAO_ADVICE",nullable=true,length=256)
	public String getBiaoAdvice(){
		return this.biaoAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  烫标处理意见
	 */
	public void setBiaoAdvice(String biaoAdvice){
		this.biaoAdvice = biaoAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  整烫处理人ID
	 */

	@Column(name ="ZHENGT_USER_ID",nullable=true,length=32)
	public String getZhengtUserId(){
		return this.zhengtUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  整烫处理人ID
	 */
	public void setZhengtUserId(String zhengtUserId){
		this.zhengtUserId = zhengtUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  整烫处理人
	 */

	@Column(name ="ZHENGT_USER_NAME",nullable=true,length=32)
	public String getZhengtUserName(){
		return this.zhengtUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  整烫处理人
	 */
	public void setZhengtUserName(String zhengtUserName){
		this.zhengtUserName = zhengtUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  整烫处理意见
	 */

	@Column(name ="ZHENGT_ADVICE",nullable=true,length=256)
	public String getZhengtAdvice(){
		return this.zhengtAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  整烫处理意见
	 */
	public void setZhengtAdvice(String zhengtAdvice){
		this.zhengtAdvice = zhengtAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  船样处理人ID
	 */

	@Column(name ="CHUANG_USER_ID",nullable=true,length=32)
	public String getChuangUserId(){
		return this.chuangUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  船样处理人ID
	 */
	public void setChuangUserId(String chuangUserId){
		this.chuangUserId = chuangUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  船样处理人
	 */

	@Column(name ="CHUANG_USER_NAME",nullable=true,length=32)
	public String getChuangUserName(){
		return this.chuangUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  船样处理人
	 */
	public void setChuangUserName(String chuangUserName){
		this.chuangUserName = chuangUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  船样处理意见
	 */

	@Column(name ="CHUANG_ADVICE",nullable=true,length=256)
	public String getChuangAdvice(){
		return this.chuangAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  船样处理意见
	 */
	public void setChuangAdvice(String chuangAdvice){
		this.chuangAdvice = chuangAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  包装处理人ID
	 */

	@Column(name ="BOX_USER_ID",nullable=true,length=32)
	public String getBoxUserId(){
		return this.boxUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  包装处理人ID
	 */
	public void setBoxUserId(String boxUserId){
		this.boxUserId = boxUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  包装处理人
	 */

	@Column(name ="BOX_USER_NAME",nullable=true,length=32)
	public String getBoxUserName(){
		return this.boxUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  包装处理人
	 */
	public void setBoxUserName(String boxUserName){
		this.boxUserName = boxUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  包装处理意见
	 */

	@Column(name ="BOX_ADVICE",nullable=true,length=256)
	public String getBoxAdvice(){
		return this.boxAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  包装处理意见
	 */
	public void setBoxAdvice(String boxAdvice){
		this.boxAdvice = boxAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  出货处理人ID
	 */

	@Column(name ="OUT_USER_ID",nullable=true,length=32)
	public String getOutUserId(){
		return this.outUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  出货处理人ID
	 */
	public void setOutUserId(String outUserId){
		this.outUserId = outUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  出货处理人
	 */

	@Column(name ="OUT_USER_NAME",nullable=true,length=32)
	public String getOutUserName(){
		return this.outUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  出货处理人
	 */
	public void setOutUserName(String outUserName){
		this.outUserName = outUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  出货处理意见
	 */

	@Column(name ="OUT_ADVICE",nullable=true,length=256)
	public String getOutAdvice(){
		return this.outAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  出货处理意见
	 */
	public void setOutAdvice(String outAdvice){
		this.outAdvice = outAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  尾期检查处理人ID
	 */

	@Column(name ="WEI_USER_ID",nullable=true,length=32)
	public String getWeiUserId(){
		return this.weiUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  尾期检查处理人ID
	 */
	public void setWeiUserId(String weiUserId){
		this.weiUserId = weiUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  尾期检查处理人
	 */

	@Column(name ="WEI_USER_NAME",nullable=true,length=32)
	public String getWeiUserName(){
		return this.weiUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  尾期检查处理人
	 */
	public void setWeiUserName(String weiUserName){
		this.weiUserName = weiUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  尾期检查处理意见
	 */

	@Column(name ="WEI_ADVICE",nullable=true,length=256)
	public String getWeiAdvice(){
		return this.weiAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  尾期检查处理意见
	 */
	public void setWeiAdvice(String weiAdvice){
		this.weiAdvice = weiAdvice;
	}
	/**
	 *方法: 取得String
	 *@return: String  收款处理人ID
	 */

	@Column(name ="SHOU_USER_ID",nullable=true,length=32)
	public String getShouUserId(){
		return this.shouUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  收款处理人ID
	 */
	public void setShouUserId(String shouUserId){
		this.shouUserId = shouUserId;
	}
	/**
	 *方法: 取得String
	 *@return: String  收款处理人
	 */

	@Column(name ="SHOU_USER_NAME",nullable=true,length=32)
	public String getShouUserName(){
		return this.shouUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  收款处理人
	 */
	public void setShouUserName(String shouUserName){
		this.shouUserName = shouUserName;
	}
	/**
	 *方法: 取得String
	 *@return: String  收款处理意见
	 */

	@Column(name ="SHOU_ADVICE",nullable=true,length=256)
	public String getShouAdvice(){
		return this.shouAdvice;
	}

	/**
	 *方法: 设置String
	 *@param: String  收款处理意见
	 */
	public void setShouAdvice(String shouAdvice){
		this.shouAdvice = shouAdvice;
	}
}
