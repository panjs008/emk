package com.emk.produce.produceschedule.entity;

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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 采购生产进度管理
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
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**需求单号*/
	private java.lang.String materialNo;
	/**提交日期*/
	@Excel(name="提交日期",width=15)
	private java.lang.String kdDate;
	/**业务员*/
	@Excel(name="业务员",width=15)
	private java.lang.String businesser;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private java.lang.String cusNum;
	/**业务员ID*/
	private java.lang.String businesserName;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private java.lang.String cusName;
	/**工艺种类*/
	@Excel(name="工艺种类",width=15)
	private java.lang.String gyzl;
	/**款式大类*/
	private java.lang.String proType;
	/**款式大类*/
	@Excel(name="款式大类",width=15)
	private java.lang.String proTypeName;
	/**款号*/
	@Excel(name="款号",width=15)
	private java.lang.String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private java.lang.String sampleNoDesc;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private java.lang.String businesseDeptName;
	/**业务部门ID*/
	private java.lang.String businesseDeptId;
	/**生产跟单员*/
	@Excel(name="生产跟单员",width=15)
	private java.lang.String developer;
	/**生产跟单员ID*/
	private java.lang.String developerName;
	/**业务跟单员*/
	@Excel(name="业务跟单员",width=15)
	private java.lang.String tracer;
	/**业务跟单员ID*/
	private java.lang.String tracerName;
	/**合同编号*/
	@Excel(name="合同编号",width=15)
	private java.lang.String htNum;
	/**总数量*/
	@Excel(name="总数量",width=15)
	private java.lang.Double sumTotal;
	/**图片*/
	private java.lang.String customSampleUrl;
	/**图片*/
	@Excel(name="图片",width=15)
	private java.lang.String customSample;
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private java.lang.String produceHtNum;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private java.lang.String orderNo;
	/**原料布料状态*/
	@Excel(name="原料布料状态",width=15)
	private java.lang.String ylblState;
	/**原料布料到厂日期*/
	@Excel(name="原料布料到厂日期",width=15)
	private java.lang.String ylblLimitDate;
	/**距原料到厂剩余天数*/
	@Excel(name="距原料到厂剩余天数",width=15)
	private java.lang.Integer leavelYlblDay;
	/**缝制辅料状态*/
	@Excel(name="缝制辅料状态",width=15)
	private java.lang.String fzblState;
	/**缝制辅料到厂日期*/
	@Excel(name="缝制辅料到厂日期",width=15)
	private java.lang.String fzblLimitDate;
	/**距缝制到厂剩余天数*/
	@Excel(name="距缝制到厂剩余天数",width=15)
	private java.lang.Integer leavelFzblDay;
	/**包装辅料状态*/
	@Excel(name="包装辅料状态",width=15)
	private java.lang.String bzblState;
	/**包装辅料到厂日期*/
	@Excel(name="包装辅料到厂日期",width=15)
	private java.lang.String bzblLimitDate;
	/**距包装到厂剩余天数*/
	@Excel(name="距包装到厂剩余天数",width=15)
	private java.lang.Integer leavelBzblDay;
	/**染色状态*/
	@Excel(name="染色状态",width=15)
	private java.lang.String ranState;
	/**染色已完成数量*/
	@Excel(name="染色已完成数量",width=15)
	private java.lang.String ranFinish;
	/**染色未完成数量*/
	@Excel(name="染色未完成数量",width=15)
	private java.lang.String ranUnfinish;
	/**裁剪状态*/
	@Excel(name="裁剪状态",width=15)
	private java.lang.String caiState;
	/**裁剪已完成数量*/
	@Excel(name="裁剪已完成数量",width=15)
	private java.lang.String caiFinish;
	/**裁剪未完成数量*/
	@Excel(name="裁剪未完成数量",width=15)
	private java.lang.String caiUnfinish;
	/**缝制状态*/
	@Excel(name="缝制状态",width=15)
	private java.lang.String fzState;
	/**缝制已完成数量*/
	@Excel(name="缝制已完成数量",width=15)
	private java.lang.String fzFinish;
	/**缝制未完成数量*/
	@Excel(name="缝制未完成数量",width=15)
	private java.lang.String fzUnfinish;
	/**烫标状态*/
	@Excel(name="烫标状态",width=15)
	private java.lang.String btState;
	/**烫标已完成数量*/
	@Excel(name="烫标已完成数量",width=15)
	private java.lang.String btFinish;
	/**烫标未完成数量*/
	@Excel(name="烫标未完成数量",width=15)
	private java.lang.String btUnfinish;
	/**整烫状态*/
	@Excel(name="整烫状态",width=15)
	private java.lang.String ztState;
	/**整烫已完成数量*/
	@Excel(name="整烫已完成数量",width=15)
	private java.lang.String ztFinish;
	/**整烫未完成数量*/
	@Excel(name="整烫未完成数量",width=15)
	private java.lang.String ztUnfinish;
	/**包装状态*/
	@Excel(name="包装状态",width=15)
	private java.lang.String bzState;
	/**包装已完成数量*/
	@Excel(name="包装已完成数量",width=15)
	private java.lang.String bzFinish;
	/**包装已完成数量*/
	@Excel(name="包装已完成数量",width=15)
	private java.lang.String bzUnfinish;
	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private java.lang.String outDate;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private java.lang.String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private java.lang.String gysCode;
	/**审核意见*/
	@Excel(name="审核意见",width=15)
	private java.lang.String leadAdvice;
	/**是否通过*/
	@Excel(name="是否通过",width=15)
	private java.lang.String isPass;
	/**审核人ID*/
	private java.lang.String leadUserId;
	/**审核人*/
	@Excel(name="审核人",width=15)
	private java.lang.String leader;
	/**状态*/
	@Excel(name="状态",width=15)
	private java.lang.String state;
	/**试身打样处理人*/
	@Excel(name="试身打样处理人",width=15)
	private java.lang.String ssSampleUser;
	/**试身打样处理人ID*/
	private java.lang.String ssSampleUserId;
	/**产前打样处理人*/
	@Excel(name="产前打样处理人",width=15)
	private java.lang.String cqSampleUser;
	/**产前打样处理人ID*/
	private java.lang.String cqSampleUserId;
	/**试身打样处理意见*/
	@Excel(name="试身打样处理意见",width=15)
	private java.lang.String ssSampleAdvice;
	/**产前打样处理意见*/
	@Excel(name="产前打样处理意见",width=15)
	private java.lang.String cqSampleAdvice;
	/**色样处理人*/
	@Excel(name="色样处理人",width=15)
	private java.lang.String colorUser;
	/**色样处理人ID*/
	private java.lang.String colorUserId;
	/**色样处理意见*/
	@Excel(name="色样处理意见",width=15)
	private java.lang.String colorAdvice;
	/**测试人*/
	@Excel(name="测试人",width=15)
	private java.lang.String testUser;
	/**测试人ID*/
	private java.lang.String testUserId;
	/**测试处理意见*/
	@Excel(name="测试处理意见",width=15)
	private java.lang.String testUserAdvice;
	/**试身样状态*/
	@Excel(name="试身样状态",width=15)
	private java.lang.String ssyzt;
	/**试身样最晚确认时间*/
	@Excel(name="试身样最晚确认时间",width=15)
	private java.lang.String ssyDate;
	/**试身样距离剩余天数*/
	@Excel(name="试身样距离剩余天数",width=15)
	private java.lang.Integer leavelSsy;
	/**产前样状态*/
	@Excel(name="产前样状态",width=15)
	private java.lang.String cqyzt;
	/**产前样最晚确认时间*/
	@Excel(name="产前样最晚确认时间",width=15)
	private java.lang.String cqyDate;
	/**产前样距离剩余天数*/
	@Excel(name="产前样距离剩余天数",width=15)
	private java.lang.Integer leavelCq;
	/**色样状态*/
	@Excel(name="色样状态",width=15)
	private java.lang.String syzt;
	/**色样最晚确认时间*/
	@Excel(name="色样最晚确认时间",width=15)
	private java.lang.String syDate;
	/**色样距离剩余天数*/
	@Excel(name="色样距离剩余天数",width=15)
	private java.lang.String leavelSy;
	/**船样状态*/
	@Excel(name="船样状态",width=15)
	private java.lang.String cyzt;
	/**船样最晚确认时间*/
	@Excel(name="船样最晚确认时间",width=15)
	private java.lang.String cyDate;
	/**船样距离剩余天数*/
	@Excel(name="船样距离剩余天数",width=15)
	private java.lang.String leavelCy;
	/**产前会议*/
	@Excel(name="产前会议",width=15)
	private java.lang.String cqhy;
	/**产前会议最晚日期*/
	@Excel(name="产前会议最晚日期",width=15)
	private java.lang.String cqhyDate;
	/**测试通过日期*/
	@Excel(name="测试通过日期",width=15)
	private java.lang.String testPass;
	/**最晚通过测试日期*/
	@Excel(name="最晚通过测试日期",width=15)
	private java.lang.String testPassDate;
	/**中查日期*/
	@Excel(name="中查日期",width=15)
	private java.lang.String zcrq;
	/**中查最晚日期*/
	@Excel(name="中查最晚日期",width=15)
	private java.lang.String zcrqDate;
	/**尾查日期*/
	@Excel(name="尾查日期",width=15)
	private java.lang.String wcrq;
	/**尾查最晚日期*/
	@Excel(name="尾查最晚日期",width=15)
	private java.lang.String wcrqDate;
	/**订舱状态*/
	@Excel(name="订舱状态",width=15)
	private java.lang.String dczt;
	/**订舱最晚日期*/
	@Excel(name="订舱最晚日期",width=15)
	private java.lang.String dcztDate;
	/**交期*/
	@Excel(name="交期",width=15)
	private java.lang.String recevieDate;
	/**收款*/
	@Excel(name="收款",width=15)
	private java.lang.String shouK;
	/**产前会议处理ID*/
	private java.lang.String cqhyUserId;
	/**产前会议处理人*/
	@Excel(name="产前会议处理人",width=15)
	private java.lang.String cqhyUserName;
	/**产前会议处理意见*/
	@Excel(name="产前会议处理意见",width=15)
	private java.lang.String cqhyAdvice;
	/**原料辅料采购人ID*/
	private java.lang.String ylflcgUserId;
	/**原料辅料采购人*/
	@Excel(name="原料辅料采购人",width=15)
	private java.lang.String ylflcgUserName;
	/**原料辅料采购意见*/
	@Excel(name="原料辅料采购意见",width=15)
	private java.lang.String ylflcgAdvice;
	/**染色处理人ID*/
	private java.lang.String ranUserId;
	/**染色处理人*/
	@Excel(name="染色处理人",width=15)
	private java.lang.String ranUserName;
	/**染色处理意见*/
	@Excel(name="染色处理意见",width=15)
	private java.lang.String ranAdvice;
	/**裁剪处理人ID*/
	private java.lang.String caiUserId;
	/**裁剪处理人*/
	@Excel(name="裁剪处理人",width=15)
	private java.lang.String caiUserName;
	/**裁剪处理意见*/
	@Excel(name="裁剪处理意见",width=15)
	private java.lang.String caiUserAdvice;
	/**缝制处理人ID*/
	private java.lang.String fengUserId;
	/**缝制处理人*/
	@Excel(name="缝制处理人",width=15)
	private java.lang.String fengUserName;
	/**缝制处理意见*/
	@Excel(name="缝制处理意见",width=15)
	private java.lang.String fengUserAdvice;
	/**中期检查人ID*/
	private java.lang.String zqjcUserId;
	/**中期检查人*/
	@Excel(name="中期检查人",width=15)
	private java.lang.String zqjcUserName;
	/**中期检查意见*/
	@Excel(name="中期检查意见",width=15)
	private java.lang.String zqjcAdvice;
	/**烫标处理人ID*/
	private java.lang.String biaoUserId;
	/**烫标处理人*/
	@Excel(name="烫标处理人",width=15)
	private java.lang.String biaoUserName;
	/**烫标处理意见*/
	@Excel(name="烫标处理意见",width=15)
	private java.lang.String biaoAdvice;
	/**整烫处理人ID*/
	private java.lang.String zhengtUserId;
	/**整烫处理人*/
	@Excel(name="整烫处理人",width=15)
	private java.lang.String zhengtUserName;
	/**整烫处理意见*/
	@Excel(name="整烫处理意见",width=15)
	private java.lang.String zhengtAdvice;
	/**船样处理人ID*/
	private java.lang.String chuangUserId;
	/**船样处理人*/
	@Excel(name="船样处理人",width=15)
	private java.lang.String chuangUserName;
	/**船样处理意见*/
	@Excel(name="船样处理意见",width=15)
	private java.lang.String chuangAdvice;
	/**包装处理人ID*/
	private java.lang.String boxUserId;
	/**包装处理人*/
	@Excel(name="包装处理人",width=15)
	private java.lang.String boxUserName;
	/**包装处理意见*/
	@Excel(name="包装处理意见",width=15)
	private java.lang.String boxAdvice;
	/**出货处理人ID*/
	private java.lang.String outUserId;
	/**出货处理人*/
	@Excel(name="出货处理人",width=15)
	private java.lang.String outUserName;
	/**出货处理意见*/
	@Excel(name="出货处理意见",width=15)
	private java.lang.String outAdvice;
	/**尾期检查处理人ID*/
	private java.lang.String weiUserId;
	/**尾期检查处理人*/
	@Excel(name="尾期检查处理人",width=15)
	private java.lang.String weiUserName;
	/**尾期检查处理意见*/
	@Excel(name="尾期检查处理意见",width=15)
	private java.lang.String weiAdvice;
	/**收款处理人ID*/
	private java.lang.String shouUserId;
	/**收款处理人*/
	@Excel(name="收款处理人",width=15)
	private java.lang.String shouUserName;
	/**收款处理意见*/
	@Excel(name="收款处理意见",width=15)
	private java.lang.String shouAdvice;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	//@Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  需求单号
	 */

	@Column(name ="MATERIAL_NO",nullable=true,length=32)
	public java.lang.String getMaterialNo(){
		return this.materialNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  需求单号
	 */
	public void setMaterialNo(java.lang.String materialNo){
		this.materialNo = materialNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public java.lang.String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交日期
	 */
	public void setKdDate(java.lang.String kdDate){
		this.kdDate = kdDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */

	@Column(name ="BUSINESSER",nullable=true,length=32)
	public java.lang.String getBusinesser(){
		return this.businesser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员
	 */
	public void setBusinesser(java.lang.String businesser){
		this.businesser = businesser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户代码
	 */

	@Column(name ="CUS_NUM",nullable=true,length=32)
	public java.lang.String getCusNum(){
		return this.cusNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户代码
	 */
	public void setCusNum(java.lang.String cusNum){
		this.cusNum = cusNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员ID
	 */

	@Column(name ="BUSINESSER_NAME",nullable=true,length=32)
	public java.lang.String getBusinesserName(){
		return this.businesserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员ID
	 */
	public void setBusinesserName(java.lang.String businesserName){
		this.businesserName = businesserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */

	@Column(name ="CUS_NAME",nullable=true,length=32)
	public java.lang.String getCusName(){
		return this.cusName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCusName(java.lang.String cusName){
		this.cusName = cusName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工艺种类
	 */

	@Column(name ="GYZL",nullable=true,length=32)
	public java.lang.String getGyzl(){
		return this.gyzl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工艺种类
	 */
	public void setGyzl(java.lang.String gyzl){
		this.gyzl = gyzl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款式大类
	 */

	@Column(name ="PRO_TYPE",nullable=true,length=32)
	public java.lang.String getProType(){
		return this.proType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款式大类
	 */
	public void setProType(java.lang.String proType){
		this.proType = proType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款式大类
	 */

	@Column(name ="PRO_TYPE_NAME",nullable=true,length=32)
	public java.lang.String getProTypeName(){
		return this.proTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款式大类
	 */
	public void setProTypeName(java.lang.String proTypeName){
		this.proTypeName = proTypeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款号
	 */

	@Column(name ="SAMPLE_NO",nullable=true,length=32)
	public java.lang.String getSampleNo(){
		return this.sampleNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款号
	 */
	public void setSampleNo(java.lang.String sampleNo){
		this.sampleNo = sampleNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */

	@Column(name ="SAMPLE_NO_DESC",nullable=true,length=256)
	public java.lang.String getSampleNoDesc(){
		return this.sampleNoDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setSampleNoDesc(java.lang.String sampleNoDesc){
		this.sampleNoDesc = sampleNoDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务部门
	 */

	@Column(name ="BUSINESSE_DEPT_NAME",nullable=true,length=32)
	public java.lang.String getBusinesseDeptName(){
		return this.businesseDeptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务部门
	 */
	public void setBusinesseDeptName(java.lang.String businesseDeptName){
		this.businesseDeptName = businesseDeptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务部门ID
	 */

	@Column(name ="BUSINESSE_DEPT_ID",nullable=true,length=32)
	public java.lang.String getBusinesseDeptId(){
		return this.businesseDeptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务部门ID
	 */
	public void setBusinesseDeptId(java.lang.String businesseDeptId){
		this.businesseDeptId = businesseDeptId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产跟单员
	 */

	@Column(name ="DEVELOPER",nullable=true,length=32)
	public java.lang.String getDeveloper(){
		return this.developer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产跟单员
	 */
	public void setDeveloper(java.lang.String developer){
		this.developer = developer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产跟单员ID
	 */

	@Column(name ="DEVELOPER_NAME",nullable=true,length=32)
	public java.lang.String getDeveloperName(){
		return this.developerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产跟单员ID
	 */
	public void setDeveloperName(java.lang.String developerName){
		this.developerName = developerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务跟单员
	 */

	@Column(name ="TRACER",nullable=true,length=32)
	public java.lang.String getTracer(){
		return this.tracer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务跟单员
	 */
	public void setTracer(java.lang.String tracer){
		this.tracer = tracer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务跟单员ID
	 */

	@Column(name ="TRACER_NAME",nullable=true,length=32)
	public java.lang.String getTracerName(){
		return this.tracerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务跟单员ID
	 */
	public void setTracerName(java.lang.String tracerName){
		this.tracerName = tracerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同编号
	 */

	@Column(name ="HT_NUM",nullable=true,length=32)
	public java.lang.String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setHtNum(java.lang.String htNum){
		this.htNum = htNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总数量
	 */

	@Column(name ="SUM_TOTAL",nullable=true,scale=2,length=32)
	public java.lang.Double getSumTotal(){
		return this.sumTotal;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总数量
	 */
	public void setSumTotal(java.lang.Double sumTotal){
		this.sumTotal = sumTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */

	@Column(name ="CUSTOM_SAMPLE_URL",nullable=true,length=256)
	public java.lang.String getCustomSampleUrl(){
		return this.customSampleUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setCustomSampleUrl(java.lang.String customSampleUrl){
		this.customSampleUrl = customSampleUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */

	@Column(name ="CUSTOM_SAMPLE",nullable=true,length=32)
	public java.lang.String getCustomSample(){
		return this.customSample;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setCustomSample(java.lang.String customSample){
		this.customSample = customSample;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产合同号
	 */

	@Column(name ="PRODUCE_HT_NUM",nullable=true,length=32)
	public java.lang.String getProduceHtNum(){
		return this.produceHtNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产合同号
	 */
	public void setProduceHtNum(java.lang.String produceHtNum){
		this.produceHtNum = produceHtNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单号
	 */

	@Column(name ="ORDER_NO",nullable=true,length=32)
	public java.lang.String getOrderNo(){
		return this.orderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderNo(java.lang.String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料布料状态
	 */

	@Column(name ="YLBL_STATE",nullable=true,length=32)
	public java.lang.String getYlblState(){
		return this.ylblState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料布料状态
	 */
	public void setYlblState(java.lang.String ylblState){
		this.ylblState = ylblState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料布料到厂日期
	 */

	@Column(name ="YLBL_LIMIT_DATE",nullable=true,length=32)
	public java.lang.String getYlblLimitDate(){
		return this.ylblLimitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料布料到厂日期
	 */
	public void setYlblLimitDate(java.lang.String ylblLimitDate){
		this.ylblLimitDate = ylblLimitDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  距原料到厂剩余天数
	 */

	@Column(name ="LEAVEL_YLBL_DAY",nullable=true,length=32)
	public java.lang.Integer getLeavelYlblDay(){
		return this.leavelYlblDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  距原料到厂剩余天数
	 */
	public void setLeavelYlblDay(java.lang.Integer leavelYlblDay){
		this.leavelYlblDay = leavelYlblDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制辅料状态
	 */

	@Column(name ="FZBL_STATE",nullable=true,length=32)
	public java.lang.String getFzblState(){
		return this.fzblState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制辅料状态
	 */
	public void setFzblState(java.lang.String fzblState){
		this.fzblState = fzblState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制辅料到厂日期
	 */

	@Column(name ="FZBL_LIMIT_DATE",nullable=true,length=32)
	public java.lang.String getFzblLimitDate(){
		return this.fzblLimitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制辅料到厂日期
	 */
	public void setFzblLimitDate(java.lang.String fzblLimitDate){
		this.fzblLimitDate = fzblLimitDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  距缝制到厂剩余天数
	 */

	@Column(name ="LEAVEL_FZBL_DAY",nullable=true,length=32)
	public java.lang.Integer getLeavelFzblDay(){
		return this.leavelFzblDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  距缝制到厂剩余天数
	 */
	public void setLeavelFzblDay(java.lang.Integer leavelFzblDay){
		this.leavelFzblDay = leavelFzblDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装辅料状态
	 */

	@Column(name ="BZBL_STATE",nullable=true,length=32)
	public java.lang.String getBzblState(){
		return this.bzblState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装辅料状态
	 */
	public void setBzblState(java.lang.String bzblState){
		this.bzblState = bzblState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装辅料到厂日期
	 */

	@Column(name ="BZBL_LIMIT_DATE",nullable=true,length=32)
	public java.lang.String getBzblLimitDate(){
		return this.bzblLimitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装辅料到厂日期
	 */
	public void setBzblLimitDate(java.lang.String bzblLimitDate){
		this.bzblLimitDate = bzblLimitDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  距包装到厂剩余天数
	 */

	@Column(name ="LEAVEL_BZBL_DAY",nullable=true,length=32)
	public java.lang.Integer getLeavelBzblDay(){
		return this.leavelBzblDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  距包装到厂剩余天数
	 */
	public void setLeavelBzblDay(java.lang.Integer leavelBzblDay){
		this.leavelBzblDay = leavelBzblDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色状态
	 */

	@Column(name ="RAN_STATE",nullable=true,length=32)
	public java.lang.String getRanState(){
		return this.ranState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色状态
	 */
	public void setRanState(java.lang.String ranState){
		this.ranState = ranState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色已完成数量
	 */

	@Column(name ="RAN_FINISH",nullable=true,length=32)
	public java.lang.String getRanFinish(){
		return this.ranFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色已完成数量
	 */
	public void setRanFinish(java.lang.String ranFinish){
		this.ranFinish = ranFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色未完成数量
	 */

	@Column(name ="RAN_UNFINISH",nullable=true,length=32)
	public java.lang.String getRanUnfinish(){
		return this.ranUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色未完成数量
	 */
	public void setRanUnfinish(java.lang.String ranUnfinish){
		this.ranUnfinish = ranUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪状态
	 */

	@Column(name ="CAI_STATE",nullable=true,length=32)
	public java.lang.String getCaiState(){
		return this.caiState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪状态
	 */
	public void setCaiState(java.lang.String caiState){
		this.caiState = caiState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪已完成数量
	 */

	@Column(name ="CAI_FINISH",nullable=true,length=32)
	public java.lang.String getCaiFinish(){
		return this.caiFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪已完成数量
	 */
	public void setCaiFinish(java.lang.String caiFinish){
		this.caiFinish = caiFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪未完成数量
	 */

	@Column(name ="CAI_UNFINISH",nullable=true,length=32)
	public java.lang.String getCaiUnfinish(){
		return this.caiUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪未完成数量
	 */
	public void setCaiUnfinish(java.lang.String caiUnfinish){
		this.caiUnfinish = caiUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制状态
	 */

	@Column(name ="FZ_STATE",nullable=true,length=32)
	public java.lang.String getFzState(){
		return this.fzState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制状态
	 */
	public void setFzState(java.lang.String fzState){
		this.fzState = fzState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制已完成数量
	 */

	@Column(name ="FZ_FINISH",nullable=true,length=32)
	public java.lang.String getFzFinish(){
		return this.fzFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制已完成数量
	 */
	public void setFzFinish(java.lang.String fzFinish){
		this.fzFinish = fzFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制未完成数量
	 */

	@Column(name ="FZ_UNFINISH",nullable=true,length=32)
	public java.lang.String getFzUnfinish(){
		return this.fzUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制未完成数量
	 */
	public void setFzUnfinish(java.lang.String fzUnfinish){
		this.fzUnfinish = fzUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标状态
	 */

	@Column(name ="BT_STATE",nullable=true,length=32)
	public java.lang.String getBtState(){
		return this.btState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标状态
	 */
	public void setBtState(java.lang.String btState){
		this.btState = btState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标已完成数量
	 */

	@Column(name ="BT_FINISH",nullable=true,length=32)
	public java.lang.String getBtFinish(){
		return this.btFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标已完成数量
	 */
	public void setBtFinish(java.lang.String btFinish){
		this.btFinish = btFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标未完成数量
	 */

	@Column(name ="BT_UNFINISH",nullable=true,length=32)
	public java.lang.String getBtUnfinish(){
		return this.btUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标未完成数量
	 */
	public void setBtUnfinish(java.lang.String btUnfinish){
		this.btUnfinish = btUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫状态
	 */

	@Column(name ="ZT_STATE",nullable=true,length=32)
	public java.lang.String getZtState(){
		return this.ztState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫状态
	 */
	public void setZtState(java.lang.String ztState){
		this.ztState = ztState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫已完成数量
	 */

	@Column(name ="ZT_FINISH",nullable=true,length=32)
	public java.lang.String getZtFinish(){
		return this.ztFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫已完成数量
	 */
	public void setZtFinish(java.lang.String ztFinish){
		this.ztFinish = ztFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫未完成数量
	 */

	@Column(name ="ZT_UNFINISH",nullable=true,length=32)
	public java.lang.String getZtUnfinish(){
		return this.ztUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫未完成数量
	 */
	public void setZtUnfinish(java.lang.String ztUnfinish){
		this.ztUnfinish = ztUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装状态
	 */

	@Column(name ="BZ_STATE",nullable=true,length=32)
	public java.lang.String getBzState(){
		return this.bzState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装状态
	 */
	public void setBzState(java.lang.String bzState){
		this.bzState = bzState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装已完成数量
	 */

	@Column(name ="BZ_FINISH",nullable=true,length=32)
	public java.lang.String getBzFinish(){
		return this.bzFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装已完成数量
	 */
	public void setBzFinish(java.lang.String bzFinish){
		this.bzFinish = bzFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装已完成数量
	 */

	@Column(name ="BZ_UNFINISH",nullable=true,length=32)
	public java.lang.String getBzUnfinish(){
		return this.bzUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装已完成数量
	 */
	public void setBzUnfinish(java.lang.String bzUnfinish){
		this.bzUnfinish = bzUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货日期
	 */

	@Column(name ="OUT_DATE",nullable=true,length=32)
	public java.lang.String getOutDate(){
		return this.outDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货日期
	 */
	public void setOutDate(java.lang.String outDate){
		this.outDate = outDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="GYS",nullable=true,length=32)
	public java.lang.String getGys(){
		return this.gys;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setGys(java.lang.String gys){
		this.gys = gys;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商代码
	 */

	@Column(name ="GYS_CODE",nullable=true,length=32)
	public java.lang.String getGysCode(){
		return this.gysCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商代码
	 */
	public void setGysCode(java.lang.String gysCode){
		this.gysCode = gysCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核意见
	 */

	@Column(name ="LEAD_ADVICE",nullable=true,length=256)
	public java.lang.String getLeadAdvice(){
		return this.leadAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核意见
	 */
	public void setLeadAdvice(java.lang.String leadAdvice){
		this.leadAdvice = leadAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否通过
	 */

	@Column(name ="IS_PASS",nullable=true,length=12)
	public java.lang.String getIsPass(){
		return this.isPass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否通过
	 */
	public void setIsPass(java.lang.String isPass){
		this.isPass = isPass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人ID
	 */

	@Column(name ="LEAD_USER_ID",nullable=true,length=32)
	public java.lang.String getLeadUserId(){
		return this.leadUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人ID
	 */
	public void setLeadUserId(java.lang.String leadUserId){
		this.leadUserId = leadUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */

	@Column(name ="LEADER",nullable=true,length=32)
	public java.lang.String getLeader(){
		return this.leader;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setLeader(java.lang.String leader){
		this.leader = leader;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身打样处理人
	 */

	@Column(name ="SS_SAMPLE_USER",nullable=true,length=32)
	public java.lang.String getSsSampleUser(){
		return this.ssSampleUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身打样处理人
	 */
	public void setSsSampleUser(java.lang.String ssSampleUser){
		this.ssSampleUser = ssSampleUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身打样处理人ID
	 */

	@Column(name ="SS_SAMPLE_USER_ID",nullable=true,length=32)
	public java.lang.String getSsSampleUserId(){
		return this.ssSampleUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身打样处理人ID
	 */
	public void setSsSampleUserId(java.lang.String ssSampleUserId){
		this.ssSampleUserId = ssSampleUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前打样处理人
	 */

	@Column(name ="CQ_SAMPLE_USER",nullable=true,length=32)
	public java.lang.String getCqSampleUser(){
		return this.cqSampleUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前打样处理人
	 */
	public void setCqSampleUser(java.lang.String cqSampleUser){
		this.cqSampleUser = cqSampleUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前打样处理人ID
	 */

	@Column(name ="CQ_SAMPLE_USER_ID",nullable=true,length=32)
	public java.lang.String getCqSampleUserId(){
		return this.cqSampleUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前打样处理人ID
	 */
	public void setCqSampleUserId(java.lang.String cqSampleUserId){
		this.cqSampleUserId = cqSampleUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身打样处理意见
	 */

	@Column(name ="SS_SAMPLE_ADVICE",nullable=true,length=256)
	public java.lang.String getSsSampleAdvice(){
		return this.ssSampleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身打样处理意见
	 */
	public void setSsSampleAdvice(java.lang.String ssSampleAdvice){
		this.ssSampleAdvice = ssSampleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前打样处理意见
	 */

	@Column(name ="CQ_SAMPLE_ADVICE",nullable=true,length=256)
	public java.lang.String getCqSampleAdvice(){
		return this.cqSampleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前打样处理意见
	 */
	public void setCqSampleAdvice(java.lang.String cqSampleAdvice){
		this.cqSampleAdvice = cqSampleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样处理人
	 */

	@Column(name ="COLOR_USER",nullable=true,length=32)
	public java.lang.String getColorUser(){
		return this.colorUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样处理人
	 */
	public void setColorUser(java.lang.String colorUser){
		this.colorUser = colorUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样处理人ID
	 */

	@Column(name ="COLOR_USER_ID",nullable=true,length=32)
	public java.lang.String getColorUserId(){
		return this.colorUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样处理人ID
	 */
	public void setColorUserId(java.lang.String colorUserId){
		this.colorUserId = colorUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样处理意见
	 */

	@Column(name ="COLOR_ADVICE",nullable=true,length=256)
	public java.lang.String getColorAdvice(){
		return this.colorAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样处理意见
	 */
	public void setColorAdvice(java.lang.String colorAdvice){
		this.colorAdvice = colorAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试人
	 */

	@Column(name ="TEST_USER",nullable=true,length=32)
	public java.lang.String getTestUser(){
		return this.testUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试人
	 */
	public void setTestUser(java.lang.String testUser){
		this.testUser = testUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试人ID
	 */

	@Column(name ="TEST_USER_ID",nullable=true,length=32)
	public java.lang.String getTestUserId(){
		return this.testUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试人ID
	 */
	public void setTestUserId(java.lang.String testUserId){
		this.testUserId = testUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试处理意见
	 */

	@Column(name ="TEST_USER_ADVICE",nullable=true,length=256)
	public java.lang.String getTestUserAdvice(){
		return this.testUserAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试处理意见
	 */
	public void setTestUserAdvice(java.lang.String testUserAdvice){
		this.testUserAdvice = testUserAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身样状态
	 */

	@Column(name ="SSYZT",nullable=true,length=32)
	public java.lang.String getSsyzt(){
		return this.ssyzt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身样状态
	 */
	public void setSsyzt(java.lang.String ssyzt){
		this.ssyzt = ssyzt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身样最晚确认时间
	 */

	@Column(name ="SSY_DATE",nullable=true,length=32)
	public java.lang.String getSsyDate(){
		return this.ssyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身样最晚确认时间
	 */
	public void setSsyDate(java.lang.String ssyDate){
		this.ssyDate = ssyDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  试身样距离剩余天数
	 */

	@Column(name ="LEAVEL_SSY",nullable=true,length=32)
	public java.lang.Integer getLeavelSsy(){
		return this.leavelSsy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  试身样距离剩余天数
	 */
	public void setLeavelSsy(java.lang.Integer leavelSsy){
		this.leavelSsy = leavelSsy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前样状态
	 */

	@Column(name ="CQYZT",nullable=true,length=32)
	public java.lang.String getCqyzt(){
		return this.cqyzt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前样状态
	 */
	public void setCqyzt(java.lang.String cqyzt){
		this.cqyzt = cqyzt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前样最晚确认时间
	 */

	@Column(name ="CQY_DATE",nullable=true,length=32)
	public java.lang.String getCqyDate(){
		return this.cqyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前样最晚确认时间
	 */
	public void setCqyDate(java.lang.String cqyDate){
		this.cqyDate = cqyDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  产前样距离剩余天数
	 */

	@Column(name ="LEAVEL_CQ",nullable=true,length=32)
	public java.lang.Integer getLeavelCq(){
		return this.leavelCq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  产前样距离剩余天数
	 */
	public void setLeavelCq(java.lang.Integer leavelCq){
		this.leavelCq = leavelCq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样状态
	 */

	@Column(name ="SYZT",nullable=true,length=32)
	public java.lang.String getSyzt(){
		return this.syzt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样状态
	 */
	public void setSyzt(java.lang.String syzt){
		this.syzt = syzt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样最晚确认时间
	 */

	@Column(name ="SY_DATE",nullable=true,length=32)
	public java.lang.String getSyDate(){
		return this.syDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样最晚确认时间
	 */
	public void setSyDate(java.lang.String syDate){
		this.syDate = syDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样距离剩余天数
	 */

	@Column(name ="LEAVEL_SY",nullable=true,length=32)
	public java.lang.String getLeavelSy(){
		return this.leavelSy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样距离剩余天数
	 */
	public void setLeavelSy(java.lang.String leavelSy){
		this.leavelSy = leavelSy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船样状态
	 */

	@Column(name ="CYZT",nullable=true,length=32)
	public java.lang.String getCyzt(){
		return this.cyzt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船样状态
	 */
	public void setCyzt(java.lang.String cyzt){
		this.cyzt = cyzt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船样最晚确认时间
	 */

	@Column(name ="CY_DATE",nullable=true,length=32)
	public java.lang.String getCyDate(){
		return this.cyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船样最晚确认时间
	 */
	public void setCyDate(java.lang.String cyDate){
		this.cyDate = cyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船样距离剩余天数
	 */

	@Column(name ="LEAVEL_CY",nullable=true,length=32)
	public java.lang.String getLeavelCy(){
		return this.leavelCy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船样距离剩余天数
	 */
	public void setLeavelCy(java.lang.String leavelCy){
		this.leavelCy = leavelCy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前会议
	 */

	@Column(name ="CQHY",nullable=true,length=32)
	public java.lang.String getCqhy(){
		return this.cqhy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前会议
	 */
	public void setCqhy(java.lang.String cqhy){
		this.cqhy = cqhy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前会议最晚日期
	 */

	@Column(name ="CQHY_DATE",nullable=true,length=32)
	public java.lang.String getCqhyDate(){
		return this.cqhyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前会议最晚日期
	 */
	public void setCqhyDate(java.lang.String cqhyDate){
		this.cqhyDate = cqhyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试通过日期
	 */

	@Column(name ="TEST_PASS",nullable=true,length=32)
	public java.lang.String getTestPass(){
		return this.testPass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试通过日期
	 */
	public void setTestPass(java.lang.String testPass){
		this.testPass = testPass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最晚通过测试日期
	 */

	@Column(name ="TEST_PASS_DATE",nullable=true,length=32)
	public java.lang.String getTestPassDate(){
		return this.testPassDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最晚通过测试日期
	 */
	public void setTestPassDate(java.lang.String testPassDate){
		this.testPassDate = testPassDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中查日期
	 */

	@Column(name ="ZCRQ",nullable=true,length=32)
	public java.lang.String getZcrq(){
		return this.zcrq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中查日期
	 */
	public void setZcrq(java.lang.String zcrq){
		this.zcrq = zcrq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中查最晚日期
	 */

	@Column(name ="ZCRQ_DATE",nullable=true,length=32)
	public java.lang.String getZcrqDate(){
		return this.zcrqDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中查最晚日期
	 */
	public void setZcrqDate(java.lang.String zcrqDate){
		this.zcrqDate = zcrqDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尾查日期
	 */

	@Column(name ="WCRQ",nullable=true,length=32)
	public java.lang.String getWcrq(){
		return this.wcrq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尾查日期
	 */
	public void setWcrq(java.lang.String wcrq){
		this.wcrq = wcrq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尾查最晚日期
	 */

	@Column(name ="WCRQ_DATE",nullable=true,length=32)
	public java.lang.String getWcrqDate(){
		return this.wcrqDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尾查最晚日期
	 */
	public void setWcrqDate(java.lang.String wcrqDate){
		this.wcrqDate = wcrqDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订舱状态
	 */

	@Column(name ="DCZT",nullable=true,length=32)
	public java.lang.String getDczt(){
		return this.dczt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订舱状态
	 */
	public void setDczt(java.lang.String dczt){
		this.dczt = dczt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订舱最晚日期
	 */

	@Column(name ="DCZT_DATE",nullable=true,length=32)
	public java.lang.String getDcztDate(){
		return this.dcztDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订舱最晚日期
	 */
	public void setDcztDate(java.lang.String dcztDate){
		this.dcztDate = dcztDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交期
	 */

	@Column(name ="RECEVIE_DATE",nullable=true,length=32)
	public java.lang.String getRecevieDate(){
		return this.recevieDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交期
	 */
	public void setRecevieDate(java.lang.String recevieDate){
		this.recevieDate = recevieDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款
	 */

	@Column(name ="SHOU_K",nullable=true,length=256)
	public java.lang.String getShouK(){
		return this.shouK;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款
	 */
	public void setShouK(java.lang.String shouK){
		this.shouK = shouK;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前会议处理ID
	 */

	@Column(name ="CQHY_USER_ID",nullable=true,length=32)
	public java.lang.String getCqhyUserId(){
		return this.cqhyUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前会议处理ID
	 */
	public void setCqhyUserId(java.lang.String cqhyUserId){
		this.cqhyUserId = cqhyUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前会议处理人
	 */

	@Column(name ="CQHY_USER_NAME",nullable=true,length=32)
	public java.lang.String getCqhyUserName(){
		return this.cqhyUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前会议处理人
	 */
	public void setCqhyUserName(java.lang.String cqhyUserName){
		this.cqhyUserName = cqhyUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前会议处理意见
	 */

	@Column(name ="CQHY_ADVICE",nullable=true,length=256)
	public java.lang.String getCqhyAdvice(){
		return this.cqhyAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前会议处理意见
	 */
	public void setCqhyAdvice(java.lang.String cqhyAdvice){
		this.cqhyAdvice = cqhyAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料辅料采购人ID
	 */

	@Column(name ="YLFLCG_USER_ID",nullable=true,length=32)
	public java.lang.String getYlflcgUserId(){
		return this.ylflcgUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料辅料采购人ID
	 */
	public void setYlflcgUserId(java.lang.String ylflcgUserId){
		this.ylflcgUserId = ylflcgUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料辅料采购人
	 */

	@Column(name ="YLFLCG_USER_NAME",nullable=true,length=32)
	public java.lang.String getYlflcgUserName(){
		return this.ylflcgUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料辅料采购人
	 */
	public void setYlflcgUserName(java.lang.String ylflcgUserName){
		this.ylflcgUserName = ylflcgUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料辅料采购意见
	 */

	@Column(name ="YLFLCG_ADVICE",nullable=true,length=256)
	public java.lang.String getYlflcgAdvice(){
		return this.ylflcgAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料辅料采购意见
	 */
	public void setYlflcgAdvice(java.lang.String ylflcgAdvice){
		this.ylflcgAdvice = ylflcgAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色处理人ID
	 */

	@Column(name ="RAN_USER_ID",nullable=true,length=32)
	public java.lang.String getRanUserId(){
		return this.ranUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色处理人ID
	 */
	public void setRanUserId(java.lang.String ranUserId){
		this.ranUserId = ranUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色处理人
	 */

	@Column(name ="RAN_USER_NAME",nullable=true,length=32)
	public java.lang.String getRanUserName(){
		return this.ranUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色处理人
	 */
	public void setRanUserName(java.lang.String ranUserName){
		this.ranUserName = ranUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色处理意见
	 */

	@Column(name ="RAN_ADVICE",nullable=true,length=256)
	public java.lang.String getRanAdvice(){
		return this.ranAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色处理意见
	 */
	public void setRanAdvice(java.lang.String ranAdvice){
		this.ranAdvice = ranAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪处理人ID
	 */

	@Column(name ="CAI_USER_ID",nullable=true,length=32)
	public java.lang.String getCaiUserId(){
		return this.caiUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪处理人ID
	 */
	public void setCaiUserId(java.lang.String caiUserId){
		this.caiUserId = caiUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪处理人
	 */

	@Column(name ="CAI_USER_NAME",nullable=true,length=32)
	public java.lang.String getCaiUserName(){
		return this.caiUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪处理人
	 */
	public void setCaiUserName(java.lang.String caiUserName){
		this.caiUserName = caiUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪处理意见
	 */

	@Column(name ="CAI_USER_ADVICE",nullable=true,length=256)
	public java.lang.String getCaiUserAdvice(){
		return this.caiUserAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪处理意见
	 */
	public void setCaiUserAdvice(java.lang.String caiUserAdvice){
		this.caiUserAdvice = caiUserAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制处理人ID
	 */

	@Column(name ="FENG_USER_ID",nullable=true,length=32)
	public java.lang.String getFengUserId(){
		return this.fengUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制处理人ID
	 */
	public void setFengUserId(java.lang.String fengUserId){
		this.fengUserId = fengUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制处理人
	 */

	@Column(name ="FENG_USER_NAME",nullable=true,length=32)
	public java.lang.String getFengUserName(){
		return this.fengUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制处理人
	 */
	public void setFengUserName(java.lang.String fengUserName){
		this.fengUserName = fengUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制处理意见
	 */

	@Column(name ="FENG_USER_ADVICE",nullable=true,length=256)
	public java.lang.String getFengUserAdvice(){
		return this.fengUserAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制处理意见
	 */
	public void setFengUserAdvice(java.lang.String fengUserAdvice){
		this.fengUserAdvice = fengUserAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中期检查人ID
	 */

	@Column(name ="ZQJC_USER_ID",nullable=true,length=32)
	public java.lang.String getZqjcUserId(){
		return this.zqjcUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中期检查人ID
	 */
	public void setZqjcUserId(java.lang.String zqjcUserId){
		this.zqjcUserId = zqjcUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中期检查人
	 */

	@Column(name ="ZQJC_USER_NAME",nullable=true,length=32)
	public java.lang.String getZqjcUserName(){
		return this.zqjcUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中期检查人
	 */
	public void setZqjcUserName(java.lang.String zqjcUserName){
		this.zqjcUserName = zqjcUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中期检查意见
	 */

	@Column(name ="ZQJC_ADVICE",nullable=true,length=256)
	public java.lang.String getZqjcAdvice(){
		return this.zqjcAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中期检查意见
	 */
	public void setZqjcAdvice(java.lang.String zqjcAdvice){
		this.zqjcAdvice = zqjcAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标处理人ID
	 */

	@Column(name ="BIAO_USER_ID",nullable=true,length=32)
	public java.lang.String getBiaoUserId(){
		return this.biaoUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标处理人ID
	 */
	public void setBiaoUserId(java.lang.String biaoUserId){
		this.biaoUserId = biaoUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标处理人
	 */

	@Column(name ="BIAO_USER_NAME",nullable=true,length=32)
	public java.lang.String getBiaoUserName(){
		return this.biaoUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标处理人
	 */
	public void setBiaoUserName(java.lang.String biaoUserName){
		this.biaoUserName = biaoUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标处理意见
	 */

	@Column(name ="BIAO_ADVICE",nullable=true,length=256)
	public java.lang.String getBiaoAdvice(){
		return this.biaoAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标处理意见
	 */
	public void setBiaoAdvice(java.lang.String biaoAdvice){
		this.biaoAdvice = biaoAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫处理人ID
	 */

	@Column(name ="ZHENGT_USER_ID",nullable=true,length=32)
	public java.lang.String getZhengtUserId(){
		return this.zhengtUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫处理人ID
	 */
	public void setZhengtUserId(java.lang.String zhengtUserId){
		this.zhengtUserId = zhengtUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫处理人
	 */

	@Column(name ="ZHENGT_USER_NAME",nullable=true,length=32)
	public java.lang.String getZhengtUserName(){
		return this.zhengtUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫处理人
	 */
	public void setZhengtUserName(java.lang.String zhengtUserName){
		this.zhengtUserName = zhengtUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫处理意见
	 */

	@Column(name ="ZHENGT_ADVICE",nullable=true,length=256)
	public java.lang.String getZhengtAdvice(){
		return this.zhengtAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫处理意见
	 */
	public void setZhengtAdvice(java.lang.String zhengtAdvice){
		this.zhengtAdvice = zhengtAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船样处理人ID
	 */

	@Column(name ="CHUANG_USER_ID",nullable=true,length=32)
	public java.lang.String getChuangUserId(){
		return this.chuangUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船样处理人ID
	 */
	public void setChuangUserId(java.lang.String chuangUserId){
		this.chuangUserId = chuangUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船样处理人
	 */

	@Column(name ="CHUANG_USER_NAME",nullable=true,length=32)
	public java.lang.String getChuangUserName(){
		return this.chuangUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船样处理人
	 */
	public void setChuangUserName(java.lang.String chuangUserName){
		this.chuangUserName = chuangUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船样处理意见
	 */

	@Column(name ="CHUANG_ADVICE",nullable=true,length=256)
	public java.lang.String getChuangAdvice(){
		return this.chuangAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船样处理意见
	 */
	public void setChuangAdvice(java.lang.String chuangAdvice){
		this.chuangAdvice = chuangAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装处理人ID
	 */

	@Column(name ="BOX_USER_ID",nullable=true,length=32)
	public java.lang.String getBoxUserId(){
		return this.boxUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装处理人ID
	 */
	public void setBoxUserId(java.lang.String boxUserId){
		this.boxUserId = boxUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装处理人
	 */

	@Column(name ="BOX_USER_NAME",nullable=true,length=32)
	public java.lang.String getBoxUserName(){
		return this.boxUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装处理人
	 */
	public void setBoxUserName(java.lang.String boxUserName){
		this.boxUserName = boxUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装处理意见
	 */

	@Column(name ="BOX_ADVICE",nullable=true,length=256)
	public java.lang.String getBoxAdvice(){
		return this.boxAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装处理意见
	 */
	public void setBoxAdvice(java.lang.String boxAdvice){
		this.boxAdvice = boxAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货处理人ID
	 */

	@Column(name ="OUT_USER_ID",nullable=true,length=32)
	public java.lang.String getOutUserId(){
		return this.outUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货处理人ID
	 */
	public void setOutUserId(java.lang.String outUserId){
		this.outUserId = outUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货处理人
	 */

	@Column(name ="OUT_USER_NAME",nullable=true,length=32)
	public java.lang.String getOutUserName(){
		return this.outUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货处理人
	 */
	public void setOutUserName(java.lang.String outUserName){
		this.outUserName = outUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货处理意见
	 */

	@Column(name ="OUT_ADVICE",nullable=true,length=256)
	public java.lang.String getOutAdvice(){
		return this.outAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货处理意见
	 */
	public void setOutAdvice(java.lang.String outAdvice){
		this.outAdvice = outAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尾期检查处理人ID
	 */

	@Column(name ="WEI_USER_ID",nullable=true,length=32)
	public java.lang.String getWeiUserId(){
		return this.weiUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尾期检查处理人ID
	 */
	public void setWeiUserId(java.lang.String weiUserId){
		this.weiUserId = weiUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尾期检查处理人
	 */

	@Column(name ="WEI_USER_NAME",nullable=true,length=32)
	public java.lang.String getWeiUserName(){
		return this.weiUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尾期检查处理人
	 */
	public void setWeiUserName(java.lang.String weiUserName){
		this.weiUserName = weiUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尾期检查处理意见
	 */

	@Column(name ="WEI_ADVICE",nullable=true,length=256)
	public java.lang.String getWeiAdvice(){
		return this.weiAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尾期检查处理意见
	 */
	public void setWeiAdvice(java.lang.String weiAdvice){
		this.weiAdvice = weiAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款处理人ID
	 */

	@Column(name ="SHOU_USER_ID",nullable=true,length=32)
	public java.lang.String getShouUserId(){
		return this.shouUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款处理人ID
	 */
	public void setShouUserId(java.lang.String shouUserId){
		this.shouUserId = shouUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款处理人
	 */

	@Column(name ="SHOU_USER_NAME",nullable=true,length=32)
	public java.lang.String getShouUserName(){
		return this.shouUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款处理人
	 */
	public void setShouUserName(java.lang.String shouUserName){
		this.shouUserName = shouUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款处理意见
	 */

	@Column(name ="SHOU_ADVICE",nullable=true,length=256)
	public java.lang.String getShouAdvice(){
		return this.shouAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款处理意见
	 */
	public void setShouAdvice(java.lang.String shouAdvice){
		this.shouAdvice = shouAdvice;
	}
}
