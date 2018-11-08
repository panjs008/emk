package com.emk.caiwu.financereceivable.entity;

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
 * @Description: 应收通知单
 * @author onlineGenerator
 * @date 2018-09-22 11:46:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_finance_receivable", schema = "")
@SuppressWarnings("serial")
public class EmkFinanceReceivableEntity implements java.io.Serializable {
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
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**总数量*/
	@Excel(name="总数量",width=15)
	private Integer sumTotal;
	/**总金额*/
	@Excel(name="总金额",width=15)
	private Double sumMoney;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private Integer sumBoxTotal;
	/**总体积*/
	@Excel(name="总体积",width=15)
	private Double sumBoxVolume;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**所属部门*/
	private String sysOrgCode;
	/**应收通知单号*/
	@Excel(name="应收通知单号",width=15)
	private String receiveNum;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outFourmNo;
	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private String outForrmDate;
	/**船务员*/
	@Excel(name="船务员",width=15)
	private String cwer;
	/**订舱通知单号*/
	@Excel(name="订舱通知单号",width=15)
	private String cargoNo;
	/**提交订舱日期*/
	@Excel(name="提交订舱日期",width=15)
	private String cargoDate;
	/**离厂放行条号*/
	@Excel(name="离厂放行条号",width=15)
	private String levealFactoryNo;
	/**离厂日期*/
	@Excel(name="离厂日期",width=15)
	private String levealDate;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**收款人*/
	@Excel(name="收款人",width=15)
	private String sker;
	/**账号*/
	@Excel(name="账号",width=15)
	private String bankAccount;
	/**开户行*/
	@Excel(name="开户行",width=15)
	private String bankName;
	/**付款方式*/
	@Excel(name="付款方式",width=15)
	private String payType;
	/**收款日期*/
	@Excel(name="收款日期",width=15)
	private String skDate;
	/**付款状态*/
	@Excel(name="付款状态",width=15)
	private String fkState;
	/**实际付款时间*/
	@Excel(name="实际付款时间",width=15)
	private String fkDate;
	/**提单状态*/
	@Excel(name="提单状态",width=15)
	private String tdstate;
	/**类型*/
	@Excel(name="类型",width=15)
	private String type;
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	@Excel(name = "审核意见", width = 15)
	private String leadAdvice;
	private String isPass;
	private String leadUserId;
	@Excel(name = "审核人", width = 15)
	private String leader;
	private String state;
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
	 *@return: java.lang.Integer  总数量
	 */

	@Column(name ="SUM_TOTAL",nullable=true,length=32)
	public Integer getSumTotal(){
		return this.sumTotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总数量
	 */
	public void setSumTotal(Integer sumTotal){
		this.sumTotal = sumTotal;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总金额
	 */

	@Column(name ="SUM_MONEY",nullable=true,scale=2,length=32)
	public Double getSumMoney(){
		return this.sumMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总金额
	 */
	public void setSumMoney(Double sumMoney){
		this.sumMoney = sumMoney;
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
	 *@return: java.lang.String  应收通知单号
	 */

	@Column(name ="RECEIVE_NUM",nullable=true,length=32)
	public String getReceiveNum(){
		return this.receiveNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应收通知单号
	 */
	public void setReceiveNum(String receiveNum){
		this.receiveNum = receiveNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货通知单号
	 */

	@Column(name ="OUT_FOURM_NO",nullable=true,length=32)
	public String getOutFourmNo(){
		return this.outFourmNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货通知单号
	 */
	public void setOutFourmNo(String outFourmNo){
		this.outFourmNo = outFourmNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货日期
	 */

	@Column(name ="OUT_FORRM_DATE",nullable=true,length=32)
	public String getOutForrmDate(){
		return this.outForrmDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货日期
	 */
	public void setOutForrmDate(String outForrmDate){
		this.outForrmDate = outForrmDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  船务员
	 */

	@Column(name ="CWER",nullable=true,length=32)
	public String getCwer(){
		return this.cwer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  船务员
	 */
	public void setCwer(String cwer){
		this.cwer = cwer;
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
	 *@return: java.lang.String  提交订舱日期
	 */

	@Column(name ="CARGO_DATE",nullable=true,length=32)
	public String getCargoDate(){
		return this.cargoDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交订舱日期
	 */
	public void setCargoDate(String cargoDate){
		this.cargoDate = cargoDate;
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
	 *@return: java.lang.String  收款人
	 */

	@Column(name ="SKER",nullable=true,length=32)
	public String getSker(){
		return this.sker;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款人
	 */
	public void setSker(String sker){
		this.sker = sker;
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
	 *@return: java.lang.String  开户行
	 */

	@Column(name ="BANK_NAME",nullable=true,length=32)
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
	 *@return: java.lang.String  付款方式
	 */

	@Column(name ="PAY_TYPE",nullable=true,length=32)
	public String getPayType(){
		return this.payType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款方式
	 */
	public void setPayType(String payType){
		this.payType = payType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款日期
	 */

	@Column(name ="SK_DATE",nullable=true,length=32)
	public String getSkDate(){
		return this.skDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款日期
	 */
	public void setSkDate(String skDate){
		this.skDate = skDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款状态
	 */

	@Column(name ="FK_STATE",nullable=true,length=32)
	public String getFkState(){
		return this.fkState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款状态
	 */
	public void setFkState(String fkState){
		this.fkState = fkState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际付款时间
	 */

	@Column(name ="FK_DATE",nullable=true,length=32)
	public String getFkDate(){
		return this.fkDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际付款时间
	 */
	public void setFkDate(String fkDate){
		this.fkDate = fkDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提单状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提单状态
	 */
	public void setState(String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */

	@Column(name ="TYPE",nullable=true,length=32)
	public String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(String type){
		this.type = type;
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

	@Column(name ="tdstate",nullable=true,length=32)
	public String getTdstate() {
		return tdstate;
	}

	public void setTdstate(String tdstate) {
		this.tdstate = tdstate;
	}

	@Column(name = "LEAD_ADVICE", nullable = true, length = 256)
	public String getLeadAdvice() {
		return this.leadAdvice;
	}

	public void setLeadAdvice(String leadAdvice) {
		this.leadAdvice = leadAdvice;
	}

	@Column(name = "IS_PASS", nullable = true, length = 32)
	public String getIsPass() {
		return this.isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	@Column(name = "LEAD_USER_ID", nullable = true, length = 32)
	public String getLeadUserId() {
		return this.leadUserId;
	}

	public void setLeadUserId(String leadUserId) {
		this.leadUserId = leadUserId;
	}

	@Column(name = "LEADER", nullable = true, length = 32)
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

}
