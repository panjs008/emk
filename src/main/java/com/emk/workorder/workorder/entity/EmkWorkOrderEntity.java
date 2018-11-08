package com.emk.workorder.workorder.entity;

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
 * @Description: 工单管理
 * @author onlineGenerator
 * @date 2018-10-08 19:29:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_work_order", schema = "")
@SuppressWarnings("serial")
public class EmkWorkOrderEntity implements java.io.Serializable {
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
	/**询盘单号*/
	@Excel(name="询盘单号",width=15)
	private String askNo;
	/**是否先打样*/
	@Excel(name="是否先打样",width=15)
	private String isPrint;
	/**询盘时间*/
	@Excel(name="询盘时间",width=15)
	private String askDate;
	/**工单环节*/
	@Excel(name="工单环节",width=15)
	private String processName;
	/**询盘处理人*/
	@Excel(name="询盘处理人",width=15)
	private String askWorkUser;
	/**询盘处理人ID*/
	private String askWorkUserId;
	/**询盘处理时间*/
	@Excel(name="询盘处理时间",width=15)
	private String askWorkDate;
	/**打样单号*/
	@Excel(name="打样单号",width=15)
	private String sampleNum;
	/**打样处理人*/
	@Excel(name="打样处理人",width=15)
	private String sampleUser;
	/**打样处理时间*/
	@Excel(name="打样处理时间",width=15)
	private String sampleDate;
	/**样品质检人*/
	@Excel(name="样品质检人",width=15)
	private String sampleCheckUser;
	/**样品质检时间*/
	@Excel(name="样品质检时间",width=15)
	private String sampleCheckDate;
	/**样品质检处理意见*/
	@Excel(name="样品质检处理意见",width=15)
	private String sampleCheckAdvice;
	/**打样处理意见*/
	@Excel(name="打样处理意见",width=15)
	private String sampleAdvice;
	/**样品质检是否通过*/
	@Excel(name="样品质检是否通过",width=15)
	private String isPass;
	/**录单人*/
	@Excel(name="录单人",width=15)
	private String orderUser;
	/**录单人ID*/
	private String orderUserId;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**入单时间*/
	@Excel(name="入单时间",width=15)
	private String orderDate;
	/**入单处理内容*/
	@Excel(name="入单处理内容",width=15)
	private String orderAdvice;
	/**样品质检人ID*/
	private String sampleCheckUserId;
	/**打样处理人ID*/
	private String sampleUserId;
	/**合同处理人*/
	@Excel(name="合同处理人",width=15)
	private String htUser;
	/**合同处理人ID*/
	private String htUserId;
	/**合同单号*/
	@Excel(name="合同单号",width=15)
	private String htNo;
	/**合同处理内容*/
	@Excel(name="合同处理内容",width=15)
	private String htAdvice;
	/**生产处理人*/
	@Excel(name="生产处理人",width=15)
	private String produceUser;
	/**生产处理人ID*/
	private String produceUserId;
	/**生产处理时间*/
	@Excel(name="生产处理时间",width=15)
	private String produceDate;
	/**合同处理时间*/
	@Excel(name="合同处理时间",width=15)
	private String htDate;
	/**生产处理内容*/
	@Excel(name="生产处理内容",width=15)
	private String produceAdvice;
	/**质检人*/
	@Excel(name="质检人",width=15)
	private String checkUser;
	/**质检人ID*/
	private String checkUserId;
	/**质检日期*/
	@Excel(name="质检日期",width=15)
	private String checkDate;
	/**质检处理意见*/
	@Excel(name="质检处理意见",width=15)
	private String checkAdvice;
	/**是否通过质检*/
	@Excel(name="是否通过质检",width=15)
	private String isPassCheck;
	/**出货人*/
	@Excel(name="出货人",width=15)
	private String outUser;
	/**出货人ID*/
	private String outUserId;
	/**出货时间*/
	@Excel(name="出货时间",width=15)
	private String outDate;
	/**出货处理意见*/
	@Excel(name="出货处理意见",width=15)
	private String outAdvice;
	/**财务处理人*/
	@Excel(name="财务处理人",width=15)
	private String caiwuUser;
	/**财务处理人ID*/
	private String caiwuUserId;
	/**财务处理时间*/
	@Excel(name="财务处理时间",width=15)
	private String caiwuDate;
	/**财务处理意见*/
	@Excel(name="财务处理意见",width=15)
	private String caiwuAdvice;
	/**工单状态*/
	@Excel(name="工单状态",width=15)
	private String state;
	/**工单编号*/
	@Excel(name="工单编号",width=15)
	private String workNo;
	/**工单日期*/
	@Excel(name="工单日期",width=15)
	private String kdDate;
	/**生产单号*/
	@Excel(name="生产单号",width=15)
	private String produceNo;
	/**质检单号*/
	@Excel(name="质检单号",width=15)
	private String checkNo;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outNo;
	/**样品质检单号*/
	@Excel(name="样品质检单号",width=15)
	private String sampleCheckNo;
	/**询盘处理意见*/
	@Excel(name="询盘处理意见",width=15)
	private String askWorkAdvice;
	/**订单类型*/
	@Excel(name="订单类型",width=15)
	private String orderType;
	/**打样订单号*/
	@Excel(name="打样订单号",width=15)
	private String sampleOrderNo;
	
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
	@Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
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
	 *@return: java.lang.String  询盘单号
	 */

	@Column(name ="ASK_NO",nullable=true,length=32)
	public String getAskNo(){
		return this.askNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  询盘单号
	 */
	public void setAskNo(String askNo){
		this.askNo = askNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否先打样
	 */

	@Column(name ="IS_PRINT",nullable=true,length=32)
	public String getIsPrint(){
		return this.isPrint;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否先打样
	 */
	public void setIsPrint(String isPrint){
		this.isPrint = isPrint;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  询盘时间
	 */

	@Column(name ="ASK_DATE",nullable=true,length=32)
	public String getAskDate(){
		return this.askDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  询盘时间
	 */
	public void setAskDate(String askDate){
		this.askDate = askDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单环节
	 */
	@Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
	@Column(name ="PROCESS_NAME",nullable=true,length=32)
	public String getProcessName(){
		return this.processName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单环节
	 */
	public void setProcessName(String processName){
		this.processName = processName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  询盘处理人
	 */

	@Column(name ="ASK_WORK_USER",nullable=true,length=32)
	public String getAskWorkUser(){
		return this.askWorkUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  询盘处理人
	 */
	public void setAskWorkUser(String askWorkUser){
		this.askWorkUser = askWorkUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  询盘处理人ID
	 */

	@Column(name ="ASK_WORK_USER_ID",nullable=true,length=32)
	public String getAskWorkUserId(){
		return this.askWorkUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  询盘处理人ID
	 */
	public void setAskWorkUserId(String askWorkUserId){
		this.askWorkUserId = askWorkUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  询盘处理时间
	 */

	@Column(name ="ASK_WORK_DATE",nullable=true,length=32)
	public String getAskWorkDate(){
		return this.askWorkDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  询盘处理时间
	 */
	public void setAskWorkDate(String askWorkDate){
		this.askWorkDate = askWorkDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打样单号
	 */

	@Column(name ="SAMPLE_NUM",nullable=true,length=32)
	public String getSampleNum(){
		return this.sampleNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打样单号
	 */
	public void setSampleNum(String sampleNum){
		this.sampleNum = sampleNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打样处理人
	 */

	@Column(name ="SAMPLE_USER",nullable=true,length=32)
	public String getSampleUser(){
		return this.sampleUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打样处理人
	 */
	public void setSampleUser(String sampleUser){
		this.sampleUser = sampleUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打样处理时间
	 */

	@Column(name ="SAMPLE_DATE",nullable=true,length=32)
	public String getSampleDate(){
		return this.sampleDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打样处理时间
	 */
	public void setSampleDate(String sampleDate){
		this.sampleDate = sampleDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品质检人
	 */

	@Column(name ="SAMPLE_CHECK_USER",nullable=true,length=32)
	public String getSampleCheckUser(){
		return this.sampleCheckUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品质检人
	 */
	public void setSampleCheckUser(String sampleCheckUser){
		this.sampleCheckUser = sampleCheckUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品质检时间
	 */

	@Column(name ="SAMPLE_CHECK_DATE",nullable=true,length=32)
	public String getSampleCheckDate(){
		return this.sampleCheckDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品质检时间
	 */
	public void setSampleCheckDate(String sampleCheckDate){
		this.sampleCheckDate = sampleCheckDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品质检处理意见
	 */

	@Column(name ="SAMPLE_CHECK_ADVICE",nullable=true,length=256)
	public String getSampleCheckAdvice(){
		return this.sampleCheckAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品质检处理意见
	 */
	public void setSampleCheckAdvice(String sampleCheckAdvice){
		this.sampleCheckAdvice = sampleCheckAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打样处理意见
	 */

	@Column(name ="SAMPLE_ADVICE",nullable=true,length=256)
	public String getSampleAdvice(){
		return this.sampleAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打样处理意见
	 */
	public void setSampleAdvice(String sampleAdvice){
		this.sampleAdvice = sampleAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品质检是否通过
	 */

	@Column(name ="IS_PASS",nullable=true,length=32)
	public String getIsPass(){
		return this.isPass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品质检是否通过
	 */
	public void setIsPass(String isPass){
		this.isPass = isPass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  录单人
	 */

	@Column(name ="ORDER_USER",nullable=true,length=32)
	public String getOrderUser(){
		return this.orderUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  录单人
	 */
	public void setOrderUser(String orderUser){
		this.orderUser = orderUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  录单人ID
	 */

	@Column(name ="ORDER_USER_ID",nullable=true,length=32)
	public String getOrderUserId(){
		return this.orderUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  录单人ID
	 */
	public void setOrderUserId(String orderUserId){
		this.orderUserId = orderUserId;
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
	 *@return: java.lang.String  入单时间
	 */

	@Column(name ="ORDER_DATE",nullable=true,length=32)
	public String getOrderDate(){
		return this.orderDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入单时间
	 */
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入单处理内容
	 */

	@Column(name ="ORDER_ADVICE",nullable=true,length=256)
	public String getOrderAdvice(){
		return this.orderAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入单处理内容
	 */
	public void setOrderAdvice(String orderAdvice){
		this.orderAdvice = orderAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品质检人ID
	 */

	@Column(name ="SAMPLE_CHECK_USER_ID",nullable=true,length=32)
	public String getSampleCheckUserId(){
		return this.sampleCheckUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品质检人ID
	 */
	public void setSampleCheckUserId(String sampleCheckUserId){
		this.sampleCheckUserId = sampleCheckUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打样处理人ID
	 */

	@Column(name ="SAMPLE_USER_ID",nullable=true,length=32)
	public String getSampleUserId(){
		return this.sampleUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打样处理人ID
	 */
	public void setSampleUserId(String sampleUserId){
		this.sampleUserId = sampleUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同处理人
	 */

	@Column(name ="HT_USER",nullable=true,length=32)
	public String getHtUser(){
		return this.htUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同处理人
	 */
	public void setHtUser(String htUser){
		this.htUser = htUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同处理人ID
	 */

	@Column(name ="HT_USER_ID",nullable=true,length=32)
	public String getHtUserId(){
		return this.htUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同处理人ID
	 */
	public void setHtUserId(String htUserId){
		this.htUserId = htUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同单号
	 */

	@Column(name ="HT_NO",nullable=true,length=32)
	public String getHtNo(){
		return this.htNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同单号
	 */
	public void setHtNo(String htNo){
		this.htNo = htNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同处理内容
	 */

	@Column(name ="HT_ADVICE",nullable=true,length=256)
	public String getHtAdvice(){
		return this.htAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同处理内容
	 */
	public void setHtAdvice(String htAdvice){
		this.htAdvice = htAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产处理人
	 */

	@Column(name ="PRODUCE_USER",nullable=true,length=32)
	public String getProduceUser(){
		return this.produceUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产处理人
	 */
	public void setProduceUser(String produceUser){
		this.produceUser = produceUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产处理人ID
	 */

	@Column(name ="PRODUCE_USER_ID",nullable=true,length=32)
	public String getProduceUserId(){
		return this.produceUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产处理人ID
	 */
	public void setProduceUserId(String produceUserId){
		this.produceUserId = produceUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产处理时间
	 */

	@Column(name ="PRODUCE_DATE",nullable=true,length=32)
	public String getProduceDate(){
		return this.produceDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产处理时间
	 */
	public void setProduceDate(String produceDate){
		this.produceDate = produceDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同处理时间
	 */

	@Column(name ="HT_DATE",nullable=true,length=32)
	public String getHtDate(){
		return this.htDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同处理时间
	 */
	public void setHtDate(String htDate){
		this.htDate = htDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产处理内容
	 */

	@Column(name ="PRODUCE_ADVICE",nullable=true,length=256)
	public String getProduceAdvice(){
		return this.produceAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产处理内容
	 */
	public void setProduceAdvice(String produceAdvice){
		this.produceAdvice = produceAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质检人
	 */

	@Column(name ="CHECK_USER",nullable=true,length=32)
	public String getCheckUser(){
		return this.checkUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质检人
	 */
	public void setCheckUser(String checkUser){
		this.checkUser = checkUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质检人ID
	 */

	@Column(name ="CHECK_USER_ID",nullable=true,length=32)
	public String getCheckUserId(){
		return this.checkUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质检人ID
	 */
	public void setCheckUserId(String checkUserId){
		this.checkUserId = checkUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质检日期
	 */

	@Column(name ="CHECK_DATE",nullable=true,length=32)
	public String getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质检日期
	 */
	public void setCheckDate(String checkDate){
		this.checkDate = checkDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质检处理意见
	 */

	@Column(name ="CHECK_ADVICE",nullable=true,length=256)
	public String getCheckAdvice(){
		return this.checkAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质检处理意见
	 */
	public void setCheckAdvice(String checkAdvice){
		this.checkAdvice = checkAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否通过质检
	 */

	@Column(name ="IS_PASS_CHECK",nullable=true,length=32)
	public String getIsPassCheck(){
		return this.isPassCheck;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否通过质检
	 */
	public void setIsPassCheck(String isPassCheck){
		this.isPassCheck = isPassCheck;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货人
	 */

	@Column(name ="OUT_USER",nullable=true,length=32)
	public String getOutUser(){
		return this.outUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货人
	 */
	public void setOutUser(String outUser){
		this.outUser = outUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货人ID
	 */

	@Column(name ="OUT_USER_ID",nullable=true,length=32)
	public String getOutUserId(){
		return this.outUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货人ID
	 */
	public void setOutUserId(String outUserId){
		this.outUserId = outUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货时间
	 */

	@Column(name ="OUT_DATE",nullable=true,length=32)
	public String getOutDate(){
		return this.outDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货时间
	 */
	public void setOutDate(String outDate){
		this.outDate = outDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货处理意见
	 */

	@Column(name ="OUT_ADVICE",nullable=true,length=256)
	public String getOutAdvice(){
		return this.outAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货处理意见
	 */
	public void setOutAdvice(String outAdvice){
		this.outAdvice = outAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财务处理人
	 */

	@Column(name ="CAIWU_USER",nullable=true,length=32)
	public String getCaiwuUser(){
		return this.caiwuUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财务处理人
	 */
	public void setCaiwuUser(String caiwuUser){
		this.caiwuUser = caiwuUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财务处理人ID
	 */

	@Column(name ="CAIWU_USER_ID",nullable=true,length=32)
	public String getCaiwuUserId(){
		return this.caiwuUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财务处理人ID
	 */
	public void setCaiwuUserId(String caiwuUserId){
		this.caiwuUserId = caiwuUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财务处理时间
	 */

	@Column(name ="CAIWU_DATE",nullable=true,length=32)
	public String getCaiwuDate(){
		return this.caiwuDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财务处理时间
	 */
	public void setCaiwuDate(String caiwuDate){
		this.caiwuDate = caiwuDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财务处理意见
	 */

	@Column(name ="CAIWU_ADVICE",nullable=true,length=256)
	public String getCaiwuAdvice(){
		return this.caiwuAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财务处理意见
	 */
	public void setCaiwuAdvice(String caiwuAdvice){
		this.caiwuAdvice = caiwuAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单状态
	 */
	public void setState(String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单编号
	 */

	@Column(name ="WORK_NO",nullable=true,length=32)
	public String getWorkNo(){
		return this.workNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单编号
	 */
	public void setWorkNo(String workNo){
		this.workNo = workNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产单号
	 */

	@Column(name ="PRODUCE_NO",nullable=true,length=32)
	public String getProduceNo(){
		return this.produceNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产单号
	 */
	public void setProduceNo(String produceNo){
		this.produceNo = produceNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质检单号
	 */

	@Column(name ="CHECK_NO",nullable=true,length=32)
	public String getCheckNo(){
		return this.checkNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质检单号
	 */
	public void setCheckNo(String checkNo){
		this.checkNo = checkNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货通知单号
	 */

	@Column(name ="OUT_NO",nullable=true,length=32)
	public String getOutNo(){
		return this.outNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货通知单号
	 */
	public void setOutNo(String outNo){
		this.outNo = outNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样品质检单号
	 */

	@Column(name ="SAMPLE_CHECK_NO",nullable=true,length=32)
	public String getSampleCheckNo(){
		return this.sampleCheckNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样品质检单号
	 */
	public void setSampleCheckNo(String sampleCheckNo){
		this.sampleCheckNo = sampleCheckNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  询盘处理意见
	 */

	@Column(name ="ASK_WORK_ADVICE",nullable=true,length=256)
	public String getAskWorkAdvice(){
		return this.askWorkAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  询盘处理意见
	 */
	public void setAskWorkAdvice(String askWorkAdvice){
		this.askWorkAdvice = askWorkAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单类型
	 */

	@Column(name ="ORDER_TYPE",nullable=true,length=32)
	public String getOrderType(){
		return this.orderType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单类型
	 */
	public void setOrderType(String orderType){
		this.orderType = orderType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打样订单号
	 */

	@Column(name ="SAMPLE_ORDER_NO",nullable=true,length=32)
	public String getSampleOrderNo(){
		return this.sampleOrderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打样订单号
	 */
	public void setSampleOrderNo(String sampleOrderNo){
		this.sampleOrderNo = sampleOrderNo;
	}
}
