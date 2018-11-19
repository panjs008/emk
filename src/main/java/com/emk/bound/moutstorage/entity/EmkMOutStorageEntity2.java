package com.emk.bound.moutstorage.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 出库申请表
 * @author onlineGenerator
 * @date 2018-09-10 20:31:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_m_out_storage", schema = "")
@SuppressWarnings("serial")
public class EmkMOutStorageEntity2 implements java.io.Serializable {
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
	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private String outDate;
	/**申请人*/
	@Excel(name="申请人",width=15)
	private String appler;
	/**申请人ID*/
	private String applerId;
	/**领料人*/
	@Excel(name="领料人",width=15)
	private String ller;
	/**领料ID*/
	private String llerId;
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**采购合同号*/
	@Excel(name="采购合同号",width=15)
	private String htNum;
	/**预采购合同号*/
	@Excel(name="预采购合同号",width=15)
	private String yhtNum;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**类型*/
	@Excel(name="类型",width=15)
	private String type;
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
	/**状态*/
	@Excel(name="状态",width=15)
	private String state;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**客户编号*/
	@Excel(name="客户编号",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**出库日期*/
	@Excel(name="出库日期",width=15)
	private String kdDate;

	@Excel(name="审核意见")
	private String leadAdvice;
	@Excel(name="处理意见")
	private String financeAdvice;
	@Excel(name="是否通过")
	private String isPass;
	private String leadUserId;
	@Excel(name="审核人")
	private String leader;
	private String financeUserId;
	@Excel(name="财务处理人")
	private String financer;
	
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
	 *@return: java.lang.String  领料人
	 */

	@Column(name ="LLER",nullable=true,length=32)
	public String getLler(){
		return this.ller;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料人
	 */
	public void setLler(String ller){
		this.ller = ller;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料ID
	 */

	@Column(name ="LLER_ID",nullable=true,length=32)
	public String getLlerId(){
		return this.llerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料ID
	 */
	public void setLlerId(String llerId){
		this.llerId = llerId;
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
	 *@return: java.lang.String  采购合同号
	 */

	@Column(name ="HT_NUM",nullable=true,length=32)
	public String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购合同号
	 */
	public void setHtNum(String htNum){
		this.htNum = htNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预采购合同号
	 */

	@Column(name ="YHT_NUM",nullable=true,length=32)
	public String getYhtNum(){
		return this.yhtNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预采购合同号
	 */
	public void setYhtNum(String yhtNum){
		this.yhtNum = yhtNum;
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

	@Column(name ="ID",nullable=true,length=32)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=32)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建日期
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
	 *@return: java.lang.String  客户编号
	 */

	@Column(name ="CUS_NUM",nullable=true,length=32)
	public String getCusNum(){
		return this.cusNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户编号
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
	 *@return: java.lang.String  出库日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出库日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}



	@Column(name ="APPLER",nullable=true,length=32)
	public String getAppler(){
		return this.appler;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人
	 */
	public void setAppler(String appler){
		this.appler = appler;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请人ID
	 */

	@Column(name ="APPLER_ID",nullable=true,length=32)
	public String getApplerId(){
		return this.applerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人ID
	 */
	public void setApplerId(String applerId){
		this.applerId = applerId;
	}

	@Column(name="LEAD_ADVICE", nullable=true, length=256)
	public String getLeadAdvice()
	{
		return this.leadAdvice;
	}

	public void setLeadAdvice(String leadAdvice)
	{
		this.leadAdvice = leadAdvice;
	}

	@Column(name="FINANCE_ADVICE", nullable=true, length=256)
	public String getFinanceAdvice()
	{
		return this.financeAdvice;
	}

	public void setFinanceAdvice(String financeAdvice)
	{
		this.financeAdvice = financeAdvice;
	}

	@Column(name="IS_PASS", nullable=true, length=32)
	public String getIsPass()
	{
		return this.isPass;
	}

	public void setIsPass(String isPass)
	{
		this.isPass = isPass;
	}

	@Column(name="LEAD_USER_ID", nullable=true, length=32)
	public String getLeadUserId()
	{
		return this.leadUserId;
	}

	public void setLeadUserId(String leadUserId)
	{
		this.leadUserId = leadUserId;
	}

	@Column(name="LEADER", nullable=true, length=32)
	public String getLeader()
	{
		return this.leader;
	}

	public void setLeader(String leader)
	{
		this.leader = leader;
	}

	@Column(name="FINANCE_USER_ID", nullable=true, length=32)
	public String getFinanceUserId()
	{
		return this.financeUserId;
	}

	public void setFinanceUserId(String financeUserId)
	{
		this.financeUserId = financeUserId;
	}

	@Column(name="FINANCER", nullable=true, length=32)
	public String getFinancer()
	{
		return this.financer;
	}

	public void setFinancer(String financer)
	{
		this.financer = financer;
	}
}
