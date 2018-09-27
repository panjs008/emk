package com.emk.outforum.shipexpenses.entity;

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
 * @Description: 运费费
 * @author onlineGenerator
 * @date 2018-09-24 11:25:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_ship_expenses", schema = "")
@SuppressWarnings("serial")
public class EmkShipExpensesEntity implements java.io.Serializable {
	/**订舱通知单号*/
	@Excel(name="订舱通知单号",width=15)
	private String cargoNo;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outForumNo;
	/**离厂放行条号*/
	@Excel(name="离厂放行条号",width=15)
	private String levealFactoryNo;
	/**船务员*/
	@Excel(name="船务员",width=15)
	private String cwyer;
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
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private Integer sumBoxTotal;
	/**总体积*/
	@Excel(name="总体积",width=15)
	private Double sumBoxVolume;
	/**总净重*/
	@Excel(name="总净重",width=15)
	private Double sumBoxJz;
	/**总毛重*/
	@Excel(name="总毛重",width=15)
	private Double sumBoxMao;
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

	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gysCode;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gys;
	/**离厂日期*/
	@Excel(name="离厂日期",width=15)
	private String levealDate;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private String htNum;
	/**运输企业名称*/
	@Excel(name="运输企业名称",width=15)
	private String ysqyName;
	/**运输企业代码*/
	@Excel(name="运输企业代码",width=15)
	private String ysqyCode;
	/**运输费用金额*/
	@Excel(name="运输费用金额",width=15)
	private String ysCost;
	/**运输费用总金额*/
	@Excel(name="运输费用总金额",width=15)
	private String ysSumMoney;
	/**运输费用发票号*/
	@Excel(name="运输费用发票号",width=15)
	private String ysFp;
	/**运输费用付款状态*/
	@Excel(name="运输费用付款状态",width=15)
	private String ysPayState;
	/**收款单位*/
	@Excel(name="收款单位",width=15)
	private String skdw;
	/**账号*/
	@Excel(name="账号",width=15)
	private String bankAccount;
	/**开户行*/
	@Excel(name="开户行",width=15)
	private String bankName;
	/**运输费用单号*/
	@Excel(name="运输费用单号",width=15)
	private String expensesNum;
	/**提交日期*/
	@Excel(name="提交日期",width=15)
	private String kdDate;
	/**始发地*/
	@Excel(name="始发地",width=15)
	private String startPlace;
	/**目的地*/
	@Excel(name="目的地",width=15)
	private String overPlace;
	/**数量*/
	@Excel(name="数量",width=15)
	private String total;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	
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
	 *@return: java.lang.String  描述
	 */

	@Column(name ="SAMPLE_NO_DESC",nullable=true,length=32)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总箱数
	 */

	@Column(name ="SUM_BOX_TOTAL",nullable=true,length=32)
	public Integer getSumBoxTotal(){
		return this.sumBoxTotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总箱数
	 */
	public void setSumBoxTotal(Integer sumBoxTotal){
		this.sumBoxTotal = sumBoxTotal;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总体积
	 */

	@Column(name ="SUM_BOX_VOLUME",nullable=true,scale=2,length=32)
	public Double getSumBoxVolume(){
		return this.sumBoxVolume;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总体积
	 */
	public void setSumBoxVolume(Double sumBoxVolume){
		this.sumBoxVolume = sumBoxVolume;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总净重
	 */

	@Column(name ="SUM_BOX_JZ",nullable=true,scale=2,length=32)
	public Double getSumBoxJz(){
		return this.sumBoxJz;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总净重
	 */
	public void setSumBoxJz(Double sumBoxJz){
		this.sumBoxJz = sumBoxJz;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总毛重
	 */

	@Column(name ="SUM_BOX_MAO",nullable=true,scale=2,length=32)
	public Double getSumBoxMao(){
		return this.sumBoxMao;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总毛重
	 */
	public void setSumBoxMao(Double sumBoxMao){
		this.sumBoxMao = sumBoxMao;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=32)
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

	@Column(name ="CREATE_NAME",nullable=true,length=32)
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

	@Column(name ="CREATE_BY",nullable=true,length=32)
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

	@Column(name ="CREATE_DATE",nullable=true,length=32)
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

	@Column(name ="SYS_ORG_CODE",nullable=true,length=32)
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
	 *@return: java.lang.String  订舱通知单号
	 */

	@Column(name ="CARGO_NO",nullable=true,length=32)
	public String getCargoNo(){
		return this.cargoNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订舱通知单号
	 */
	public void setCargoNo(String cargoNo){
		this.cargoNo = cargoNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货通知单号
	 */

	@Column(name ="OUT_FORUM_NO",nullable=true,length=32)
	public String getOutForumNo(){
		return this.outForumNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货通知单号
	 */
	public void setOutForumNo(String outForumNo){
		this.outForumNo = outForumNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离厂放行条号
	 */

	@Column(name ="LEVEAL_FACTORY_NO",nullable=true,length=32)
	public String getLevealFactoryNo(){
		return this.levealFactoryNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离厂放行条号
	 */
	public void setLevealFactoryNo(String levealFactoryNo){
		this.levealFactoryNo = levealFactoryNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船务员
	 */

	@Column(name ="CWYER",nullable=true,length=32)
	public String getCwyer(){
		return this.cwyer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船务员
	 */
	public void setCwyer(String cwyer){
		this.cwyer = cwyer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="GYS_CODE",nullable=true,length=32)
	public String getGysCode(){
		return this.gysCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setGysCode(String gysCode){
		this.gysCode = gysCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="GYS",nullable=true,length=56)
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
	 *@return: java.lang.String  离厂日期
	 */

	@Column(name ="LEVEAL_DATE",nullable=true,length=32)
	public String getLevealDate(){
		return this.levealDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离厂日期
	 */
	public void setLevealDate(String levealDate){
		this.levealDate = levealDate;
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
	 *@return: java.lang.String  生产合同号
	 */

	@Column(name ="HT_NUM",nullable=true,length=32)
	public String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产合同号
	 */
	public void setHtNum(String htNum){
		this.htNum = htNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输企业名称
	 */

	@Column(name ="YSQY_NAME",nullable=true,length=256)
	public String getYsqyName(){
		return this.ysqyName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输企业名称
	 */
	public void setYsqyName(String ysqyName){
		this.ysqyName = ysqyName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输企业代码
	 */

	@Column(name ="YSQY_CODE",nullable=true,length=32)
	public String getYsqyCode(){
		return this.ysqyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输企业代码
	 */
	public void setYsqyCode(String ysqyCode){
		this.ysqyCode = ysqyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输费用金额
	 */

	@Column(name ="YS_COST",nullable=true,length=32)
	public String getYsCost(){
		return this.ysCost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输费用金额
	 */
	public void setYsCost(String ysCost){
		this.ysCost = ysCost;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输费用总金额
	 */

	@Column(name ="YS_SUM_MONEY",nullable=true,length=32)
	public String getYsSumMoney(){
		return this.ysSumMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输费用总金额
	 */
	public void setYsSumMoney(String ysSumMoney){
		this.ysSumMoney = ysSumMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输费用发票号
	 */

	@Column(name ="YS_FP",nullable=true,length=32)
	public String getYsFp(){
		return this.ysFp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输费用发票号
	 */
	public void setYsFp(String ysFp){
		this.ysFp = ysFp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输费用付款状态
	 */

	@Column(name ="YS_PAY_STATE",nullable=true,length=32)
	public String getYsPayState(){
		return this.ysPayState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输费用付款状态
	 */
	public void setYsPayState(String ysPayState){
		this.ysPayState = ysPayState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款单位
	 */

	@Column(name ="SKDW",nullable=true,length=256)
	public String getSkdw(){
		return this.skdw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款单位
	 */
	public void setSkdw(String skdw){
		this.skdw = skdw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */

	@Column(name ="BANK_ACCOUNT",nullable=true,length=256)
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
	 *@return: java.lang.String  运输费用单号
	 */

	@Column(name ="EXPENSES_NUM",nullable=true,length=32)
	public String getExpensesNum(){
		return this.expensesNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输费用单号
	 */
	public void setExpensesNum(String expensesNum){
		this.expensesNum = expensesNum;
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
	 *@return: java.lang.String  始发地
	 */

	@Column(name ="START_PLACE",nullable=true,length=32)
	public String getStartPlace(){
		return this.startPlace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  始发地
	 */
	public void setStartPlace(String startPlace){
		this.startPlace = startPlace;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  目的地
	 */

	@Column(name ="OVER_PLACE",nullable=true,length=32)
	public String getOverPlace(){
		return this.overPlace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  目的地
	 */
	public void setOverPlace(String overPlace){
		this.overPlace = overPlace;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public String getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setTotal(String total){
		this.total = total;
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
}
