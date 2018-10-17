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
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 采购生产表
 * @author onlineGenerator
 * @date 2018-10-15 21:57:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_produce_schedule", schema = "")
@SuppressWarnings("serial")
public class EmkProduceScheduleEntity implements java.io.Serializable {
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
	/**需求单号*/
	@Excel(name="需求单号",width=15)
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
	@Excel(name="业务员ID",width=15)
	private String businesserName;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**工艺种类*/
	@Excel(name="工艺种类",width=15)
	private String gyzl;
	/**款式大类*/
	@Excel(name="款式大类",width=15)
	private String proType;
	/**款式大类*/
	@Excel(name="款式大类",width=15)
	private String proTypeName;
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private String businesseDeptName;
	/**业务部门ID*/
	@Excel(name="业务部门ID",width=15)
	private String businesseDeptId;
	/**生产跟单员*/
	@Excel(name="生产跟单员",width=15)
	private String developer;
	/**生产跟单员ID*/
	@Excel(name="生产跟单员ID",width=15)
	private String developerName;
	/**业务跟单员*/
	@Excel(name="业务跟单员",width=15)
	private String tracer;
	/**业务跟单员ID*/
	@Excel(name="业务跟单员ID",width=15)
	private String tracerName;
	/**合同编号*/
	@Excel(name="合同编号",width=15)
	private String htNum;
	/**总数量*/
	@Excel(name="总数量",width=15)
	private Double sumTotal;
	/**图片*/
	@Excel(name="图片",width=15)
	private String customSampleUrl;
	/**图片*/
	@Excel(name="图片",width=15)
	private String customSample;
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private String produceHtNum;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**原料布料状态*/
	@Excel(name="原料布料状态",width=15)
	private String ylblState;
	/**原料布料到厂日期*/
	@Excel(name="原料布料到厂日期",width=15)
	private String ylblLimitDate;
	/**距原料到厂剩余天数*/
	@Excel(name="距原料到厂剩余天数",width=15)
	private Integer leavelYlblDay;
	/**缝制辅料状态*/
	@Excel(name="缝制辅料状态",width=15)
	private String fzblState;
	/**缝制辅料到厂日期*/
	@Excel(name="缝制辅料到厂日期",width=15)
	private String fzblLimitDate;
	/**距缝制到厂剩余天数*/
	@Excel(name="距缝制到厂剩余天数",width=15)
	private Integer leavelFzblDay;
	/**包装辅料状态*/
	@Excel(name="包装辅料状态",width=15)
	private String bzblState;
	/**包装辅料到厂日期*/
	@Excel(name="包装辅料到厂日期",width=15)
	private String bzblLimitDate;
	/**距包装到厂剩余天数*/
	@Excel(name="距包装到厂剩余天数",width=15)
	private Integer leavelBzblDay;
	/**染色状态*/
	@Excel(name="染色状态",width=15)
	private String ranState;
	/**染色已完成数量*/
	@Excel(name="染色已完成数量",width=15)
	private String ranFinish;
	/**染色未完成数量*/
	@Excel(name="染色未完成数量",width=15)
	private String ranUnfinish;
	/**裁剪状态*/
	@Excel(name="裁剪状态",width=15)
	private String caiState;
	/**裁剪已完成数量*/
	@Excel(name="裁剪已完成数量",width=15)
	private String caiFinish;
	/**裁剪未完成数量*/
	@Excel(name="裁剪未完成数量",width=15)
	private String caiUnfinish;
	/**缝制状态*/
	@Excel(name="缝制状态",width=15)
	private String fzState;
	/**缝制已完成数量*/
	@Excel(name="缝制已完成数量",width=15)
	private String fzFinish;
	/**缝制未完成数量*/
	@Excel(name="缝制未完成数量",width=15)
	private String fzUnfinish;
	/**烫标状态*/
	@Excel(name="烫标状态",width=15)
	private String btState;
	/**烫标已完成数量*/
	@Excel(name="烫标已完成数量",width=15)
	private String btFinish;
	/**烫标未完成数量*/
	@Excel(name="烫标未完成数量",width=15)
	private String btUnfinish;
	/**整烫状态*/
	@Excel(name="整烫状态",width=15)
	private String ztState;
	/**整烫已完成数量*/
	@Excel(name="整烫已完成数量",width=15)
	private String ztFinish;
	/**整烫未完成数量*/
	@Excel(name="整烫未完成数量",width=15)
	private String ztUnfinish;
	/**包装状态*/
	@Excel(name="包装状态",width=15)
	private String bzState;
	/**包装已完成数量*/
	@Excel(name="包装已完成数量",width=15)
	private String bzFinish;
	/**包装已完成数量*/
	@Excel(name="包装已完成数量",width=15)
	private String bzUnfinish;
	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private String outDate;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**审核意见*/
	@Excel(name="审核意见",width=15)
	private String leadAdvice;
	/**是否通过*/
	@Excel(name="是否通过",width=15)
	private String isPass;
	/**审核人ID*/
	@Excel(name="审核人ID",width=15)
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
	@Excel(name="试身打样处理人ID",width=15)
	private String ssSampleUserId;
	/**产前打样处理人*/
	@Excel(name="产前打样处理人",width=15)
	private String cqSampleUser;
	/**产前打样处理人ID*/
	@Excel(name="产前打样处理人ID",width=15)
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
	@Excel(name="色样处理人ID",width=15)
	private String colorUserId;
	/**色样处理意见*/
	@Excel(name="色样处理意见",width=15)
	private String colorAdvice;
	/**测试人*/
	@Excel(name="测试人",width=15)
	private String testUser;
	/**测试人ID*/
	@Excel(name="测试人ID",width=15)
	private String testUserId;
	/**测试处理意见*/
	@Excel(name="测试处理意见",width=15)
	private String testUserAdvice;
	
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
	 *@return: java.lang.String  需求单号
	 */

	@Column(name ="MATERIAL_NO",nullable=true,length=32)
	public String getMaterialNo(){
		return this.materialNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  需求单号
	 */
	public void setMaterialNo(String materialNo){
		this.materialNo = materialNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
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
	 *@return: java.lang.String  工艺种类
	 */

	@Column(name ="GYZL",nullable=true,length=32)
	public String getGyzl(){
		return this.gyzl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工艺种类
	 */
	public void setGyzl(String gyzl){
		this.gyzl = gyzl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款式大类
	 */

	@Column(name ="PRO_TYPE",nullable=true,length=32)
	public String getProType(){
		return this.proType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款式大类
	 */
	public void setProType(String proType){
		this.proType = proType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款式大类
	 */

	@Column(name ="PRO_TYPE_NAME",nullable=true,length=32)
	public String getProTypeName(){
		return this.proTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款式大类
	 */
	public void setProTypeName(String proTypeName){
		this.proTypeName = proTypeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款号
	 */

	@Column(name ="SAMPLE_NO",nullable=true,length=32)
	public String getSampleNo(){
		return this.sampleNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款号
	 */
	public void setSampleNo(String sampleNo){
		this.sampleNo = sampleNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */

	@Column(name ="SAMPLE_NO_DESC",nullable=true,length=256)
	public String getSampleNoDesc(){
		return this.sampleNoDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setSampleNoDesc(String sampleNoDesc){
		this.sampleNoDesc = sampleNoDesc;
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
	 *@return: java.lang.String  生产跟单员
	 */

	@Column(name ="DEVELOPER",nullable=true,length=32)
	public String getDeveloper(){
		return this.developer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产跟单员
	 */
	public void setDeveloper(String developer){
		this.developer = developer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产跟单员ID
	 */

	@Column(name ="DEVELOPER_NAME",nullable=true,length=32)
	public String getDeveloperName(){
		return this.developerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产跟单员ID
	 */
	public void setDeveloperName(String developerName){
		this.developerName = developerName;
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
	 *@return: java.lang.String  合同编号
	 */

	@Column(name ="HT_NUM",nullable=true,length=32)
	public String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setHtNum(String htNum){
		this.htNum = htNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总数量
	 */

	@Column(name ="SUM_TOTAL",nullable=true,scale=2,length=32)
	public Double getSumTotal(){
		return this.sumTotal;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总数量
	 */
	public void setSumTotal(Double sumTotal){
		this.sumTotal = sumTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */

	@Column(name ="CUSTOM_SAMPLE_URL",nullable=true,length=256)
	public String getCustomSampleUrl(){
		return this.customSampleUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setCustomSampleUrl(String customSampleUrl){
		this.customSampleUrl = customSampleUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */

	@Column(name ="CUSTOM_SAMPLE",nullable=true,length=32)
	public String getCustomSample(){
		return this.customSample;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setCustomSample(String customSample){
		this.customSample = customSample;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产合同号
	 */

	@Column(name ="PRODUCE_HT_NUM",nullable=true,length=32)
	public String getProduceHtNum(){
		return this.produceHtNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产合同号
	 */
	public void setProduceHtNum(String produceHtNum){
		this.produceHtNum = produceHtNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单号
	 */

	@Column(name ="ORDER_NO",nullable=true,length=32)
	public String getOrderNo(){
		return this.orderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料布料状态
	 */

	@Column(name ="YLBL_STATE",nullable=true,length=32)
	public String getYlblState(){
		return this.ylblState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料布料状态
	 */
	public void setYlblState(String ylblState){
		this.ylblState = ylblState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料布料到厂日期
	 */

	@Column(name ="YLBL_LIMIT_DATE",nullable=true,length=32)
	public String getYlblLimitDate(){
		return this.ylblLimitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料布料到厂日期
	 */
	public void setYlblLimitDate(String ylblLimitDate){
		this.ylblLimitDate = ylblLimitDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  距原料到厂剩余天数
	 */

	@Column(name ="LEAVEL_YLBL_DAY",nullable=true,length=32)
	public Integer getLeavelYlblDay(){
		return this.leavelYlblDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  距原料到厂剩余天数
	 */
	public void setLeavelYlblDay(Integer leavelYlblDay){
		this.leavelYlblDay = leavelYlblDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制辅料状态
	 */

	@Column(name ="FZBL_STATE",nullable=true,length=32)
	public String getFzblState(){
		return this.fzblState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制辅料状态
	 */
	public void setFzblState(String fzblState){
		this.fzblState = fzblState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制辅料到厂日期
	 */

	@Column(name ="FZBL_LIMIT_DATE",nullable=true,length=32)
	public String getFzblLimitDate(){
		return this.fzblLimitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制辅料到厂日期
	 */
	public void setFzblLimitDate(String fzblLimitDate){
		this.fzblLimitDate = fzblLimitDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  距缝制到厂剩余天数
	 */

	@Column(name ="LEAVEL_FZBL_DAY",nullable=true,length=32)
	public Integer getLeavelFzblDay(){
		return this.leavelFzblDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  距缝制到厂剩余天数
	 */
	public void setLeavelFzblDay(Integer leavelFzblDay){
		this.leavelFzblDay = leavelFzblDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装辅料状态
	 */

	@Column(name ="BZBL_STATE",nullable=true,length=32)
	public String getBzblState(){
		return this.bzblState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装辅料状态
	 */
	public void setBzblState(String bzblState){
		this.bzblState = bzblState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装辅料到厂日期
	 */

	@Column(name ="BZBL_LIMIT_DATE",nullable=true,length=32)
	public String getBzblLimitDate(){
		return this.bzblLimitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装辅料到厂日期
	 */
	public void setBzblLimitDate(String bzblLimitDate){
		this.bzblLimitDate = bzblLimitDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  距包装到厂剩余天数
	 */

	@Column(name ="LEAVEL_BZBL_DAY",nullable=true,length=32)
	public Integer getLeavelBzblDay(){
		return this.leavelBzblDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  距包装到厂剩余天数
	 */
	public void setLeavelBzblDay(Integer leavelBzblDay){
		this.leavelBzblDay = leavelBzblDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色状态
	 */

	@Column(name ="RAN_STATE",nullable=true,length=32)
	public String getRanState(){
		return this.ranState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色状态
	 */
	public void setRanState(String ranState){
		this.ranState = ranState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色已完成数量
	 */

	@Column(name ="RAN_FINISH",nullable=true,length=32)
	public String getRanFinish(){
		return this.ranFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色已完成数量
	 */
	public void setRanFinish(String ranFinish){
		this.ranFinish = ranFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染色未完成数量
	 */

	@Column(name ="RAN_UNFINISH",nullable=true,length=32)
	public String getRanUnfinish(){
		return this.ranUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染色未完成数量
	 */
	public void setRanUnfinish(String ranUnfinish){
		this.ranUnfinish = ranUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪状态
	 */

	@Column(name ="CAI_STATE",nullable=true,length=32)
	public String getCaiState(){
		return this.caiState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪状态
	 */
	public void setCaiState(String caiState){
		this.caiState = caiState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪已完成数量
	 */

	@Column(name ="CAI_FINISH",nullable=true,length=32)
	public String getCaiFinish(){
		return this.caiFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪已完成数量
	 */
	public void setCaiFinish(String caiFinish){
		this.caiFinish = caiFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  裁剪未完成数量
	 */

	@Column(name ="CAI_UNFINISH",nullable=true,length=32)
	public String getCaiUnfinish(){
		return this.caiUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  裁剪未完成数量
	 */
	public void setCaiUnfinish(String caiUnfinish){
		this.caiUnfinish = caiUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制状态
	 */

	@Column(name ="FZ_STATE",nullable=true,length=32)
	public String getFzState(){
		return this.fzState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制状态
	 */
	public void setFzState(String fzState){
		this.fzState = fzState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制已完成数量
	 */

	@Column(name ="FZ_FINISH",nullable=true,length=32)
	public String getFzFinish(){
		return this.fzFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制已完成数量
	 */
	public void setFzFinish(String fzFinish){
		this.fzFinish = fzFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缝制未完成数量
	 */

	@Column(name ="FZ_UNFINISH",nullable=true,length=32)
	public String getFzUnfinish(){
		return this.fzUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缝制未完成数量
	 */
	public void setFzUnfinish(String fzUnfinish){
		this.fzUnfinish = fzUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标状态
	 */

	@Column(name ="BT_STATE",nullable=true,length=32)
	public String getBtState(){
		return this.btState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标状态
	 */
	public void setBtState(String btState){
		this.btState = btState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标已完成数量
	 */

	@Column(name ="BT_FINISH",nullable=true,length=32)
	public String getBtFinish(){
		return this.btFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标已完成数量
	 */
	public void setBtFinish(String btFinish){
		this.btFinish = btFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫标未完成数量
	 */

	@Column(name ="BT_UNFINISH",nullable=true,length=32)
	public String getBtUnfinish(){
		return this.btUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫标未完成数量
	 */
	public void setBtUnfinish(String btUnfinish){
		this.btUnfinish = btUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫状态
	 */

	@Column(name ="ZT_STATE",nullable=true,length=32)
	public String getZtState(){
		return this.ztState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫状态
	 */
	public void setZtState(String ztState){
		this.ztState = ztState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫已完成数量
	 */

	@Column(name ="ZT_FINISH",nullable=true,length=32)
	public String getZtFinish(){
		return this.ztFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫已完成数量
	 */
	public void setZtFinish(String ztFinish){
		this.ztFinish = ztFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整烫未完成数量
	 */

	@Column(name ="ZT_UNFINISH",nullable=true,length=32)
	public String getZtUnfinish(){
		return this.ztUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整烫未完成数量
	 */
	public void setZtUnfinish(String ztUnfinish){
		this.ztUnfinish = ztUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装状态
	 */

	@Column(name ="BZ_STATE",nullable=true,length=32)
	public String getBzState(){
		return this.bzState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装状态
	 */
	public void setBzState(String bzState){
		this.bzState = bzState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装已完成数量
	 */

	@Column(name ="BZ_FINISH",nullable=true,length=32)
	public String getBzFinish(){
		return this.bzFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装已完成数量
	 */
	public void setBzFinish(String bzFinish){
		this.bzFinish = bzFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装已完成数量
	 */

	@Column(name ="BZ_UNFINISH",nullable=true,length=32)
	public String getBzUnfinish(){
		return this.bzUnfinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装已完成数量
	 */
	public void setBzUnfinish(String bzUnfinish){
		this.bzUnfinish = bzUnfinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货日期
	 */

	@Column(name ="OUT_DATE",nullable=true,length=32)
	public String getOutDate(){
		return this.outDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货日期
	 */
	public void setOutDate(String outDate){
		this.outDate = outDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="GYS",nullable=true,length=32)
	public String getGys(){
		return this.gys;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setGys(String gys){
		this.gys = gys;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商代码
	 */

	@Column(name ="GYS_CODE",nullable=true,length=32)
	public String getGysCode(){
		return this.gysCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商代码
	 */
	public void setGysCode(String gysCode){
		this.gysCode = gysCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核意见
	 */

	@Column(name ="LEAD_ADVICE",nullable=true,length=256)
	public String getLeadAdvice(){
		return this.leadAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核意见
	 */
	public void setLeadAdvice(String leadAdvice){
		this.leadAdvice = leadAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否通过
	 */

	@Column(name ="IS_PASS",nullable=true,length=12)
	public String getIsPass(){
		return this.isPass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否通过
	 */
	public void setIsPass(String isPass){
		this.isPass = isPass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人ID
	 */

	@Column(name ="LEAD_USER_ID",nullable=true,length=32)
	public String getLeadUserId(){
		return this.leadUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人ID
	 */
	public void setLeadUserId(String leadUserId){
		this.leadUserId = leadUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */

	@Column(name ="LEADER",nullable=true,length=32)
	public String getLeader(){
		return this.leader;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setLeader(String leader){
		this.leader = leader;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身打样处理人
	 */

	@Column(name ="SS_SAMPLE_USER",nullable=true,length=32)
	public String getSsSampleUser(){
		return this.ssSampleUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身打样处理人
	 */
	public void setSsSampleUser(String ssSampleUser){
		this.ssSampleUser = ssSampleUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身打样处理人ID
	 */

	@Column(name ="SS_SAMPLE_USER_ID",nullable=true,length=32)
	public String getSsSampleUserId(){
		return this.ssSampleUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身打样处理人ID
	 */
	public void setSsSampleUserId(String ssSampleUserId){
		this.ssSampleUserId = ssSampleUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前打样处理人
	 */

	@Column(name ="CQ_SAMPLE_USER",nullable=true,length=32)
	public String getCqSampleUser(){
		return this.cqSampleUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前打样处理人
	 */
	public void setCqSampleUser(String cqSampleUser){
		this.cqSampleUser = cqSampleUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前打样处理人ID
	 */

	@Column(name ="CQ_SAMPLE_USER_ID",nullable=true,length=32)
	public String getCqSampleUserId(){
		return this.cqSampleUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前打样处理人ID
	 */
	public void setCqSampleUserId(String cqSampleUserId){
		this.cqSampleUserId = cqSampleUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试身打样处理意见
	 */

	@Column(name ="SS_SAMPLE_ADVICE",nullable=true,length=256)
	public String getSsSampleAdvice(){
		return this.ssSampleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试身打样处理意见
	 */
	public void setSsSampleAdvice(String ssSampleAdvice){
		this.ssSampleAdvice = ssSampleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产前打样处理意见
	 */

	@Column(name ="CQ_SAMPLE_ADVICE",nullable=true,length=256)
	public String getCqSampleAdvice(){
		return this.cqSampleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产前打样处理意见
	 */
	public void setCqSampleAdvice(String cqSampleAdvice){
		this.cqSampleAdvice = cqSampleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样处理人
	 */

	@Column(name ="COLOR_USER",nullable=true,length=32)
	public String getColorUser(){
		return this.colorUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样处理人
	 */
	public void setColorUser(String colorUser){
		this.colorUser = colorUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样处理人ID
	 */

	@Column(name ="COLOR_USER_ID",nullable=true,length=32)
	public String getColorUserId(){
		return this.colorUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样处理人ID
	 */
	public void setColorUserId(String colorUserId){
		this.colorUserId = colorUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色样处理意见
	 */

	@Column(name ="COLOR_ADVICE",nullable=true,length=256)
	public String getColorAdvice(){
		return this.colorAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色样处理意见
	 */
	public void setColorAdvice(String colorAdvice){
		this.colorAdvice = colorAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试人
	 */

	@Column(name ="TEST_USER",nullable=true,length=32)
	public String getTestUser(){
		return this.testUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试人
	 */
	public void setTestUser(String testUser){
		this.testUser = testUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试人ID
	 */

	@Column(name ="TEST_USER_ID",nullable=true,length=32)
	public String getTestUserId(){
		return this.testUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试人ID
	 */
	public void setTestUserId(String testUserId){
		this.testUserId = testUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试处理意见
	 */

	@Column(name ="TEST_USER_ADVICE",nullable=true,length=256)
	public String getTestUserAdvice(){
		return this.testUserAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试处理意见
	 */
	public void setTestUserAdvice(String testUserAdvice){
		this.testUserAdvice = testUserAdvice;
	}
}
