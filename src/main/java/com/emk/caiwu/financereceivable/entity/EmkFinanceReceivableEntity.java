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
	@Excel(name = "审核意见")
	private String leadAdvice;
	private String isPass;
	private String leadUserId;
	@Excel(name = "审核人")
	private String leader;
	private String state;
	@Excel(name = "供应商")
	private String gys;
	@Excel(name = "供应商")
	private String gysCode;
	@Excel(name="付款项目类别",width=15)
	private String fkxmlb;
	@Excel(name="货代费用付款申请单号",width=15)
	private String hdfyfkNo;
	/**运输费用付款申请单号*/
	@Excel(name="运输费用付款申请单号",width=15)
	private String ysfyfkNo;
	/**原料面料打样费用付款申请单号*/
	@Excel(name="原料面料打样费用付款申请单号",width=15)
	private String ylmldyfyfkNo;
	/**测试费用付款申请单号*/
	@Excel(name="测试费用付款申请单号",width=15)
	private String testNo;
	/**招待费用付款申请单编号*/
	@Excel(name="招待费用付款申请单编号",width=15)
	private String zdfyfkNo;
	/**应付金额*/
	@Excel(name="应付金额",width=15)
	private Double money;
	/**预计付款日期*/
	@Excel(name="预计付款日期",width=15)
	private String yjfkDate;

	@Excel(name="生产合同号",width=15)
	private String produceHtNum;

	@Excel(name="原料面料打样通知单编号",width=15)
	private String ylmldyTzdNo;
	@Excel(name="货款",width=15)
	private String dk;
	@Excel(name="招待申请单编号",width=15)
	private String zdsqNo;
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

	@Column(name = "GYS", nullable = true, length = 32)
	public String getGys() {
		return this.gys;
	}

	public void setGys(String gys) {
		this.gys = gys;
	}

	@Column(name = "GYS_CODE", nullable = true, length = 32)
	public String getGysCode() {
		return this.gysCode;
	}

	public void setGysCode(String gysCode) {
		this.gysCode = gysCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货代费用付款申请单号
	 */

	@Column(name ="HDFYFK_NO",nullable=true,length=32)
	public String getHdfyfkNo(){
		return this.hdfyfkNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货代费用付款申请单号
	 */
	public void setHdfyfkNo(String hdfyfkNo){
		this.hdfyfkNo = hdfyfkNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输费用付款申请单号
	 */

	@Column(name ="YSFYFK_NO",nullable=true,length=32)
	public String getYsfyfkNo(){
		return this.ysfyfkNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输费用付款申请单号
	 */
	public void setYsfyfkNo(String ysfyfkNo){
		this.ysfyfkNo = ysfyfkNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料面料打样费用付款申请单号
	 */

	@Column(name ="YLMLDYFYFK_NO",nullable=true,length=32)
	public String getYlmldyfyfkNo(){
		return this.ylmldyfyfkNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料面料打样费用付款申请单号
	 */
	public void setYlmldyfyfkNo(String ylmldyfyfkNo){
		this.ylmldyfyfkNo = ylmldyfyfkNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试费用付款申请单号
	 */

	@Column(name ="TEST_NO",nullable=true,length=32)
	public String getTestNo(){
		return this.testNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试费用付款申请单号
	 */
	public void setTestNo(String testNo){
		this.testNo = testNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  招待费用付款申请单编号
	 */

	@Column(name ="ZDFYFK_NO",nullable=true,length=32)
	public String getZdfyfkNo(){
		return this.zdfyfkNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  招待费用付款申请单编号
	 */
	public void setZdfyfkNo(String zdfyfkNo){
		this.zdfyfkNo = zdfyfkNo;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  应付金额
	 */

	@Column(name ="MONEY",nullable=true,length=32)
	public Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  应付金额
	 */
	public void setMoney(Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预计付款日期
	 */

	@Column(name ="YJFK_DATE",nullable=true,length=32)
	public String getYjfkDate(){
		return this.yjfkDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计付款日期
	 */
	public void setYjfkDate(String yjfkDate){
		this.yjfkDate = yjfkDate;
	}


	@Column(name ="FKXMLB",nullable=true,length=32)
	public String getFkxmlb(){
		return this.fkxmlb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款项目类别
	 */
	public void setFkxmlb(String fkxmlb){
		this.fkxmlb = fkxmlb;
	}

	@Column(name ="produce_ht_num",nullable=true,length=32)
	public String getProduceHtNum() {
		return produceHtNum;
	}

	public void setProduceHtNum(String produceHtNum) {
		this.produceHtNum = produceHtNum;
	}

	@Column(name ="ylmldy_tzd_no",nullable=true,length=32)
	public String getYlmldyTzdNo() {
		return ylmldyTzdNo;
	}

	public void setYlmldyTzdNo(String ylmldyTzdNo) {
		this.ylmldyTzdNo = ylmldyTzdNo;
	}

	@Column(name ="dk",nullable=true,length=32)
	public String getDk() {
		return dk;
	}

	public void setDk(String dk) {
		this.dk = dk;
	}

	@Column(name ="zdsq_no",nullable=true,length=32)
	public String getZdsqNo() {
		return zdsqNo;
	}

	public void setZdsqNo(String zdsqNo) {
		this.zdsqNo = zdsqNo;
	}
}
