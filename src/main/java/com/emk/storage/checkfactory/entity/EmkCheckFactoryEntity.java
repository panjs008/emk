package com.emk.storage.checkfactory.entity;

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
 * @Description: 验厂申请表
 * @author onlineGenerator
 * @date 2018-10-26 21:51:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_check_factory", schema = "")
@SuppressWarnings("serial")
public class EmkCheckFactoryEntity implements java.io.Serializable {
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
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private String businesseDeptName;
	/**业务部门ID*/
	private String businesseDeptId;
	/**业务员*/
	@Excel(name="业务员",width=15)
	private String businesser;
	/**业务员ID*/
	private String businesserName;
	@Excel(name = "业务跟单员")
	private String tracer;
	private String tracerName;
	@Excel(name = "生产跟单员")
	private String developer;
	private String developerName;
	/**提交申请日期*/
	@Excel(name="提交申请日期",width=15)
	private String kdDate;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**工厂名称*/
	@Excel(name="工厂名称",width=15)
	private String factoryName;
	/**工厂代码*/
	@Excel(name="工厂代码",width=15)
	private String factoryCode;
	/**工厂地址*/
	@Excel(name="工厂地址",width=15)
	private String factoryAddress;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private String relationer;
	@Excel(name="电话",width=15)
	private String telphone;
	/**审核类型*/
	@Excel(name="审核类型",width=15)
	private String checkType;
	/**验厂标准*/
	@Excel(name="验厂标准",width=15)
	private String brand;
	/**申请审核日期*/
	@Excel(name="申请审核日期",width=15)
	private String shDate;
	/**验厂申请编号*/
	@Excel(name="验厂申请编号",width=15)
	private String ycsqbh;
	@Excel(name="申请事由",width=15)
	private String applyReason;
	@Excel(name="审核项目",width=15)
	private String checkItem;
	private String state;					//0 创建 1 提交 2 已完成 3 未完成
	private String ycstate;			    //0 未分配验厂员 2 已分配验厂员 3 已完成 4 未完成

	private String noticType;
	@Excel(name="计划审核日期",width=15)
	private String jhshDate;

	private String processName;

	private String fileName;
	private String fileNameUrl;


	@Formula("(select CONCAT(p.NAME_,'-',p.TASK_DEF_KEY_) from act_ru_task p where p.ASSIGNEE_ = id limit 0,1)")
	@Column(name = "process_name", nullable = true, length = 32)
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Column(name ="notic_type",nullable=false,length=36)
	public String getNoticType() {
		return noticType;
	}

	public void setNoticType(String noticType) {
		this.noticType = noticType;
	}

	@Column(name ="jhsh_date",nullable=false,length=36)
	public String getJhshDate() {
		return jhshDate;
	}

	public void setJhshDate(String jhshDate) {
		this.jhshDate = jhshDate;
	}

	@Column(name ="ycstate",nullable=false,length=36)
	public String getYcstate() {
		return ycstate;
	}

	public void setYcstate(String ycstate) {
		this.ycstate = ycstate;
	}

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

	@Column(name ="CUS_NAME",nullable=true,length=256)
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
	 *@return: java.lang.String  业务部门
	 */

	@Column(name ="BUSINESSE_DEPT_NAME",nullable=true,length=256)
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
	 *@return: java.lang.String  提交申请日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交申请日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
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
	 *@return: java.lang.String  工厂名称
	 */

	@Column(name ="FACTORY_NAME",nullable=true,length=256)
	public String getFactoryName(){
		return this.factoryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂名称
	 */
	public void setFactoryName(String factoryName){
		this.factoryName = factoryName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工厂代码
	 */

	@Column(name ="FACTORY_CODE",nullable=true,length=32)
	public String getFactoryCode(){
		return this.factoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂代码
	 */
	public void setFactoryCode(String factoryCode){
		this.factoryCode = factoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工厂地址
	 */

	@Column(name ="FACTORY_ADDRESS",nullable=true,length=256)
	public String getFactoryAddress(){
		return this.factoryAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂地址
	 */
	public void setFactoryAddress(String factoryAddress){
		this.factoryAddress = factoryAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */

	@Column(name ="RELATIONER",nullable=true,length=32)
	public String getRelationer(){
		return this.relationer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setRelationer(String relationer){
		this.relationer = relationer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核类型
	 */

	@Column(name ="CHECK_TYPE",nullable=true,length=32)
	public String getCheckType(){
		return this.checkType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核类型
	 */
	public void setCheckType(String checkType){
		this.checkType = checkType;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验厂标准
	 */

	@Column(name ="BRAND",nullable=true,length=512)
	public String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验厂标准
	 */
	public void setBrand(String brand){
		this.brand = brand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请审核日期
	 */

	@Column(name ="SH_DATE",nullable=true,length=32)
	public String getShDate(){
		return this.shDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请审核日期
	 */
	public void setShDate(String shDate){
		this.shDate = shDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验厂申请编号
	 */

	@Column(name ="YCSQBH",nullable=true,length=32)
	public String getYcsqbh(){
		return this.ycsqbh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验厂申请编号
	 */
	public void setYcsqbh(String ycsqbh){
		this.ycsqbh = ycsqbh;
	}

	@Column(name ="apply_reason",nullable=true,length=32)
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	@Column(name ="state",nullable=true,length=32)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name ="check_item",nullable=true,length=32)
	public String getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}

	@Column(name ="telphone",nullable=true,length=32)
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "TRACER", nullable = true, length = 32)
	public String getTracer() {
		return this.tracer;
	}

	public void setTracer(String tracer) {
		this.tracer = tracer;
	}

	@Column(name = "TRACER_NAME", nullable = true, length = 32)
	public String getTracerName() {
		return this.tracerName;
	}

	public void setTracerName(String tracerName) {
		this.tracerName = tracerName;
	}

	@Column(name = "DEVELOPER", nullable = true, length = 32)
	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	@Column(name = "DEVELOPER_NAME", nullable = true, length = 32)
	public String getDeveloperName() {
		return this.developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	@Column(name = "file_name", nullable = true, length = 256)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_name_url", nullable = true, length = 512)
	public String getFileNameUrl() {
		return fileNameUrl;
	}

	public void setFileNameUrl(String fileNameUrl) {
		this.fileNameUrl = fileNameUrl;
	}
}
