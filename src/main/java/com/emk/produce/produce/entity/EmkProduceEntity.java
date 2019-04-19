package com.emk.produce.produce.entity;

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
public class EmkProduceEntity implements java.io.Serializable {
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
	private String materialNo;
	/**提交日期*/
	@Excel(name="提交日期",width=15)
	private String kdDate;
	/**业务员*/
	@Excel(name="业务员",width=15)
	private String businesser;
	/**业务员ID*/
	private String businesserName;
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
	private String leadAdvice;
	/**是否通过*/
	private String isPass;
	/**审核人ID*/
	private String leadUserId;
	/**审核人*/
	private String leader;
	/**状态*/
	private String state;
	/**试身打样处理人*/
	private String ssSampleUser;
	/**试身打样处理人ID*/
	private String ssSampleUserId;
	/**产前打样处理人*/
	private String cqSampleUser;
	/**产前打样处理人ID*/
	private String cqSampleUserId;
	/**试身打样处理意见*/
	private String ssSampleAdvice;
	/**产前打样处理意见*/
	private String cqSampleAdvice;
	/**色样处理人*/
	private String colorUser;
	/**色样处理人ID*/
	private String colorUserId;
	/**色样处理意见*/
	private String colorAdvice;
	/**测试人*/
	private String testUser;
	/**测试人ID*/
	private String testUserId;
	/**测试处理意见*/
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
