package com.emk.bound.moutstorage.entity;

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
 * @Description: 出库申请表
 * @author onlineGenerator
 * @date 2018-09-10 20:31:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_m_out_storage", schema = "")
@SuppressWarnings("serial")
public class EmkMOutStorageEntity implements java.io.Serializable {
	/**出库单号*/
	@Excel(name="出库单号",width=15)
	private String ckNo;
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
	/**采购员*/
	@Excel(name="采购员",width=15)
	private String caigouer;
	/**采购员*/
	private String caigouerName;

	@Excel(name="申请日期",width=15)
	private String sqDate;
	@Excel(name="业务日期",width=15)
	private String businesserDate;
	@Excel(name="业务跟日期",width=15)
	private String tracerDate;
	@Excel(name="采购日期",width=15)
	private String caigouerDate;
	@Excel(name="数量",width=15)
	private String total;

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

	private String processName;
	private String materialNo;
	private String formType;
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
	//@Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
	//@Column(name ="CREATE_NAME",nullable=true,length=32)
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

	@Column(name="caigouer", nullable=true, length=32)
	public String getCaigouer() {
		return caigouer;
	}

	public void setCaigouer(String caigouer) {
		this.caigouer = caigouer;
	}

	@Column(name="caigouer_name", nullable=true, length=32)
	public String getCaigouerName() {
		return caigouerName;
	}

	public void setCaigouerName(String caigouerName) {
		this.caigouerName = caigouerName;
	}

	@Column(name="sq_date", nullable=true, length=32)
	public String getSqDate() {
		return sqDate;
	}

	public void setSqDate(String sqDate) {
		this.sqDate = sqDate;
	}

	@Column(name="businesser_date", nullable=true, length=32)
	public String getBusinesserDate() {
		return businesserDate;
	}

	public void setBusinesserDate(String businesserDate) {
		this.businesserDate = businesserDate;
	}

	@Column(name="tracer_date", nullable=true, length=32)
	public String getTracerDate() {
		return tracerDate;
	}

	public void setTracerDate(String tracerDate) {
		this.tracerDate = tracerDate;
	}

	@Column(name="caigouer_date", nullable=true, length=32)
	public String getCaigouerDate() {
		return caigouerDate;
	}

	public void setCaigouerDate(String caigouerDate) {
		this.caigouerDate = caigouerDate;
	}

	@Column(name="total", nullable=true, length=32)
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Formula("(select CONCAT(p.NAME_,'-',p.TASK_DEF_KEY_) from act_ru_task p where p.ASSIGNEE_ = id limit 0,1)")
	@Column(name = "process_name", nullable = true, length = 32)
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Column(name = "form_type", nullable = true, length = 32)
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	@Column(name = "ck_no", nullable = true, length = 32)
	public String getCkNo() {
		return ckNo;
	}

	public void setCkNo(String ckNo) {
		this.ckNo = ckNo;
	}

	@Column(name = "MATERIAL_NO", nullable = true, length = 32)
	public String getMaterialNo() {
		return this.materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
}
