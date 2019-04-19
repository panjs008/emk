package com.emk.outforum.tdhdcost.entity;

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
 * @Description: 提单货代费用
 * @author onlineGenerator
 * @date 2018-09-23 22:51:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_tdhd_cost", schema = "")
@SuppressWarnings("serial")
public class EmkTdhdCostEntity implements java.io.Serializable {
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
	/**货代费用付款申请表编号*/
	@Excel(name="货代费用付款申请表编号",width=15)
	private String hdfyPayNo;
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
	/**船务员*/
	@Excel(name="船务员",width=15)
	private String cwyer;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;

	/**货代名称*/
	@Excel(name="货代名称",width=15)
	private String hdName;
	/**货代代码*/
	@Excel(name="货代代码",width=15)
	private String hdCode;
	/**货代费用发票号*/
	@Excel(name="货代费用发票号",width=15)
	private String hdfyFp;
	/**货代费用发票号*/
	@Excel(name="货代发票状态",width=15)
	private String hdfyFpState;
	/**费用金额*/
	@Excel(name="费用金额",width=15)
	private String cost;
	/**费用付款状态*/
	@Excel(name="费用付款状态",width=15)
	private String payState;
	/**费用付款状态*/
	@Excel(name="费用付款日期",width=15)
	private String payDate;

	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;

	@Excel(name = "收款单位")
	private String skDept;
	@Excel(name = "账号")
	private String account;
	@Excel(name = "开户行")
	private String bankName;
	@Excel(name = "电话")
	private String telphone;
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
	 *@return: java.lang.String  货代费用发票号
	 */

	@Column(name ="HDFY_FP",nullable=true,length=32)
	public String getHdfyFp(){
		return this.hdfyFp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货代费用发票号
	 */
	public void setHdfyFp(String hdfyFp){
		this.hdfyFp = hdfyFp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用金额
	 */

	@Column(name ="COST",nullable=true,length=32)
	public String getCost(){
		return this.cost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用金额
	 */
	public void setCost(String cost){
		this.cost = cost;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用付款状态
	 */

	@Column(name ="PAY_STATE",nullable=true,length=32)
	public String getPayState(){
		return this.payState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用付款状态
	 */
	public void setPayState(String payState){
		this.payState = payState;
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

	@Column(name = "sk_dept", nullable = true, length = 32)
	public String getSkDept() {
		return skDept;
	}

	public void setSkDept(String skDept) {
		this.skDept = skDept;
	}

	@Column(name = "account", nullable = true, length = 32)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "bank_name", nullable = true, length = 32)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "telphone", nullable = true, length = 32)
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "hdfy_fp_state", nullable = true, length = 32)
	public String getHdfyFpState() {
		return hdfyFpState;
	}

	public void setHdfyFpState(String hdfyFpState) {
		this.hdfyFpState = hdfyFpState;
	}

	@Column(name = "pay_date", nullable = true, length = 32)
	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	@Column(name = "hdfy_pay_no", nullable = true, length = 32)
	public String getHdfyPayNo() {
		return hdfyPayNo;
	}

	public void setHdfyPayNo(String hdfyPayNo) {
		this.hdfyPayNo = hdfyPayNo;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货代名称
	 */

	@Column(name ="HD_NAME",nullable=true,length=32)
	public String getHdName(){
		return this.hdName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货代名称
	 */
	public void setHdName(String hdName){
		this.hdName = hdName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货代代码
	 */

	@Column(name ="HD_CODE",nullable=true,length=32)
	public String getHdCode(){
		return this.hdCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货代代码
	 */
	public void setHdCode(String hdCode){
		this.hdCode = hdCode;
	}
}
