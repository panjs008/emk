package com.emk.produce.testcost.entity;

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
 * @Description: 测试费用付款申请
 * @author onlineGenerator
 * @date 2018-10-27 14:01:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_test_cost", schema = "")
@SuppressWarnings("serial")
public class EmkTestCostEntity implements java.io.Serializable {
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
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private String produceNum;
	/**费用付款申请单号*/
	@Excel(name="费用付款申请单号",width=15)
	private String costNo;
	/**测试申请单号*/
	@Excel(name="测试申请单号",width=15)
	private String testNo;
	/**申请提交日期*/
	@Excel(name="申请提交日期",width=15)
	private String kdDate;
	/**测试机构名称*/
	@Excel(name="测试机构名称",width=15)
	private String csjgmc;
	/**测试机构代码*/
	@Excel(name="测试机构代码",width=15)
	private String csjgdm;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**数量*/
	@Excel(name="数量",width=15)
	private String total;
	/**测试种类*/
	@Excel(name="测试种类",width=15)
	private String testType;
	/**测试内容*/
	@Excel(name="测试内容",width=15)
	private String testContent;
	/**测试有效期*/
	@Excel(name="测试有效期",width=15)
	private String limitDate;
	/**测试结果*/
	@Excel(name="测试结果",width=15)
	private String testResult;
	/**费付款状态*/
	@Excel(name="费付款状态",width=15)
	private String costState;
	/**测试费用金额*/
	@Excel(name="测试费用金额",width=15)
	private String costMoney;
	/**收款单位*/
	@Excel(name="收款单位",width=15)
	private String skdw;
	/**开户行*/
	@Excel(name="开户行",width=15)
	private String bankName;
	/**账号*/
	@Excel(name="账号",width=15)
	private String bankAccount;
	/**电话*/
	@Excel(name="电话",width=15)
	private String telphone;
	/**发票状态*/
	@Excel(name="发票状态",width=15)
	private String fpState;
	/**发票号*/
	@Excel(name="发票号",width=15)
	private String fpNo;
	/**测试内容*/
	@Excel(name="测试内容",width=15)
	private String testContentUrl;
	@Excel(name = "审核意见")
	private String leadAdvice;
	@Excel(name = "处理意见")
	private String financeAdvice;
	private String isPass;
	private String leadUserId;
	@Excel(name = "审核人")
	private String leader;
	private String financeUserId;
	@Excel(name = "财务处理人")
	private String financer;
	private String state;


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

	@Column(name ="PRODUCE_NUM",nullable=true,length=32)
	public String getProduceNum(){
		return this.produceNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产合同号
	 */
	public void setProduceNum(String produceNum){
		this.produceNum = produceNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用付款申请单号
	 */

	@Column(name ="COST_NO",nullable=true,length=32)
	public String getCostNo(){
		return this.costNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用付款申请单号
	 */
	public void setCostNo(String costNo){
		this.costNo = costNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试申请单号
	 */

	@Column(name ="TEST_NO",nullable=true,length=32)
	public String getTestNo(){
		return this.testNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试申请单号
	 */
	public void setTestNo(String testNo){
		this.testNo = testNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请提交日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请提交日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试机构名称
	 */

	@Column(name ="CSJGMC",nullable=true,length=32)
	public String getCsjgmc(){
		return this.csjgmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试机构名称
	 */
	public void setCsjgmc(String csjgmc){
		this.csjgmc = csjgmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试机构代码
	 */

	@Column(name ="CSJGDM",nullable=true,length=32)
	public String getCsjgdm(){
		return this.csjgdm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试机构代码
	 */
	public void setCsjgdm(String csjgdm){
		this.csjgdm = csjgdm;
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
	 *@return: java.lang.String  测试种类
	 */

	@Column(name ="TEST_TYPE",nullable=true,length=32)
	public String getTestType(){
		return this.testType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试种类
	 */
	public void setTestType(String testType){
		this.testType = testType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试内容
	 */

	@Column(name ="TEST_CONTENT",nullable=true,length=256)
	public String getTestContent(){
		return this.testContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试内容
	 */
	public void setTestContent(String testContent){
		this.testContent = testContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试有效期
	 */

	@Column(name ="LIMIT_DATE",nullable=true,length=32)
	public String getLimitDate(){
		return this.limitDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试有效期
	 */
	public void setLimitDate(String limitDate){
		this.limitDate = limitDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试结果
	 */

	@Column(name ="TEST_RESULT",nullable=true,length=32)
	public String getTestResult(){
		return this.testResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试结果
	 */
	public void setTestResult(String testResult){
		this.testResult = testResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费付款状态
	 */

	@Column(name ="COST_STATE",nullable=true,length=32)
	public String getCostState(){
		return this.costState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费付款状态
	 */
	public void setCostState(String costState){
		this.costState = costState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试费用金额
	 */

	@Column(name ="COST_MONEY",nullable=true,length=32)
	public String getCostMoney(){
		return this.costMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试费用金额
	 */
	public void setCostMoney(String costMoney){
		this.costMoney = costMoney;
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
	 *@return: java.lang.String  账号
	 */

	@Column(name ="BANK_ACCOUNT",nullable=true,length=32)
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
	 *@return: java.lang.String  电话
	 */

	@Column(name ="TELPHONE",nullable=true,length=32)
	public String getTelphone(){
		return this.telphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票状态
	 */

	@Column(name ="FP_STATE",nullable=true,length=32)
	public String getFpState(){
		return this.fpState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票状态
	 */
	public void setFpState(String fpState){
		this.fpState = fpState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票号
	 */

	@Column(name ="FP_NO",nullable=true,length=32)
	public String getFpNo(){
		return this.fpNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票号
	 */
	public void setFpNo(String fpNo){
		this.fpNo = fpNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试内容
	 */

	@Column(name ="TEST_CONTENT_URL",nullable=true,length=256)
	public String getTestContentUrl(){
		return this.testContentUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试内容
	 */
	public void setTestContentUrl(String testContentUrl){
		this.testContentUrl = testContentUrl;
	}

	@Column(name = "LEAD_ADVICE", nullable = true, length = 256)
	public String getLeadAdvice() {
		return this.leadAdvice;
	}

	public void setLeadAdvice(String leadAdvice) {
		this.leadAdvice = leadAdvice;
	}

	@Column(name = "FINANCE_ADVICE", nullable = true, length = 256)
	public String getFinanceAdvice() {
		return this.financeAdvice;
	}

	public void setFinanceAdvice(String financeAdvice) {
		this.financeAdvice = financeAdvice;
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

	@Column(name = "FINANCE_USER_ID", nullable = true, length = 32)
	public String getFinanceUserId() {
		return this.financeUserId;
	}

	public void setFinanceUserId(String financeUserId) {
		this.financeUserId = financeUserId;
	}

	@Column(name = "FINANCER", nullable = true, length = 32)
	public String getFinancer() {
		return this.financer;
	}

	public void setFinancer(String financer) {
		this.financer = financer;
	}

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
}
