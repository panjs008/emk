package com.emk.storage.checkfactory.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

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
public class EmkCheckFactoryEntity2 implements java.io.Serializable {
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
	/**提交申请日期*/
	@Excel(name="提交申请日期",width=15)
	private String kdDate;
	/**供应商类型*/
	@Excel(name="供应商类型",width=15)
	private String gysType;
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
	/**审核类型*/
	@Excel(name="审核类型",width=15)
	private String checkType;
	/**验厂内容*/
	@Excel(name="验厂内容",width=15)
	private String checkContent;
	/**验厂标准*/
	@Excel(name="验厂标准",width=15)
	private String brand;
	/**申请审核日期*/
	@Excel(name="申请审核日期",width=15)
	private String shDate;
	/**验厂申请编号*/
	@Excel(name="验厂申请编号",width=15)
	private String ycsqbh;

	@Excel(name = "审核意见")
	private String leadAdvice;
	private String isPass;
	private String leadUserId;
	@Excel(name = "审核人")
	private String leader;
	private String state;

	private String cyUserId;
	private String cyer;
	private String cyAdvice;
	private String bgUserId;
	private String bger;
	private String bgAdvice;
	private String isHg;

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
	 *@return: java.lang.String  供应商类型
	 */

	@Column(name ="GYS_TYPE",nullable=true,length=32)
	public String getGysType(){
		return this.gysType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商类型
	 */
	public void setGysType(String gysType){
		this.gysType = gysType;
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
	 *@return: java.lang.String  验厂内容
	 */

	@Column(name ="CHECK_CONTENT",nullable=true,length=512)
	public String getCheckContent(){
		return this.checkContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验厂内容
	 */
	public void setCheckContent(String checkContent){
		this.checkContent = checkContent;
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

	@Column(name ="cy_user_id",nullable=true,length=32)
	public String getCyUserId() {
		return cyUserId;
	}

	public void setCyUserId(String cyUserId) {
		this.cyUserId = cyUserId;
	}

	@Column(name ="cyer",nullable=true,length=32)
	public String getCyer() {
		return cyer;
	}

	public void setCyer(String cyer) {
		this.cyer = cyer;
	}

	@Column(name ="cy_advice",nullable=true,length=32)
	public String getCyAdvice() {
		return cyAdvice;
	}

	public void setCyAdvice(String cyAdvice) {
		this.cyAdvice = cyAdvice;
	}

	@Column(name ="bg_user_id",nullable=true,length=32)

	public String getBgUserId() {
		return bgUserId;
	}

	public void setBgUserId(String bgUserId) {
		this.bgUserId = bgUserId;
	}

	@Column(name ="bger",nullable=true,length=32)
	public String getBger() {
		return bger;
	}

	public void setBger(String bger) {
		this.bger = bger;
	}

	@Column(name ="bg_advice",nullable=true,length=32)
	public String getBgAdvice() {
		return bgAdvice;
	}

	public void setBgAdvice(String bgAdvice) {
		this.bgAdvice = bgAdvice;
	}

	@Column(name ="is_hg",nullable=true,length=32)
	public String getIsHg() {
		return isHg;
	}

	public void setIsHg(String isHg) {
		this.isHg = isHg;
	}
}
