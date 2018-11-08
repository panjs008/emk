package com.emk.bill.yjyhtime.entity;

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
 * @Description: 预计验货时间表
 * @author onlineGenerator
 * @date 2018-10-25 21:45:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_yjyh_time", schema = "")
@SuppressWarnings("serial")
public class EmkYjyhTimeEntity implements java.io.Serializable {
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
	@Excel(name="业务员ID",width=15)
	private String businesserName;
	/**业务跟单员*/
	@Excel(name="业务跟单员",width=15)
	private String tracer;
	/**业务跟单员ID*/
	@Excel(name="业务跟单员ID",width=15)
	private String tracerName;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private String businesseDeptName;
	/**业务部门ID*/
	@Excel(name="业务部门ID",width=15)
	private String businesseDeptId;
	/**生产跟单员*/
	@Excel(name="生产跟单员",width=15)
	private String developer;
	/**生产跟单员ID*/
	@Excel(name="生产跟单员ID",width=15)
	private String developerName;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**工艺种类*/
	@Excel(name="工艺种类",width=15)
	private String gyzl;
	/**款式大类*/
	@Excel(name="款式大类",width=15)
	private String proType;
	/**款式大类*/
	@Excel(name="款式大类",width=15)
	private String proTypeName;
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**数量*/
	@Excel(name="数量",width=15)
	private String total;
	/**预计中期验货日期*/
	@Excel(name="预计中期验货日期",width=15)
	private String yjzqyhDate;
	/**实际中期验货日期*/
	@Excel(name="实际中期验货日期",width=15)
	private String sjyhDate;
	/**中期验货状态*/
	@Excel(name="中期验货状态",width=15)
	private String zqyhSate;
	/**预计尾期验货日期*/
	@Excel(name="预计尾期验货日期",width=15)
	private String yjwqyhDate;
	/**实际尾期验货日期*/
	@Excel(name="实际尾期验货日期",width=15)
	private String sjwqyhDate;
	/**尾期验货状态*/
	@Excel(name="尾期验货状态",width=15)
	private String wqyhState;
	/**提交日期*/
	@Excel(name="提交日期",width=15)
	private String kdTime;
	
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
	 *@return: java.lang.String  工艺种类
	 */

	@Column(name ="GYZL",nullable=true,length=32)
	public String getGyzl(){
		return this.gyzl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工艺种类
	 */
	public void setGyzl(String gyzl){
		this.gyzl = gyzl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款式大类
	 */

	@Column(name ="PRO_TYPE",nullable=true,length=32)
	public String getProType(){
		return this.proType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款式大类
	 */
	public void setProType(String proType){
		this.proType = proType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款式大类
	 */

	@Column(name ="PRO_TYPE_NAME",nullable=true,length=32)
	public String getProTypeName(){
		return this.proTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款式大类
	 */
	public void setProTypeName(String proTypeName){
		this.proTypeName = proTypeName;
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
	 *@return: java.lang.String  预计中期验货日期
	 */

	@Column(name ="YJZQYH_DATE",nullable=true,length=32)
	public String getYjzqyhDate(){
		return this.yjzqyhDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计中期验货日期
	 */
	public void setYjzqyhDate(String yjzqyhDate){
		this.yjzqyhDate = yjzqyhDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际中期验货日期
	 */

	@Column(name ="SJYH_DATE",nullable=true,length=32)
	public String getSjyhDate(){
		return this.sjyhDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际中期验货日期
	 */
	public void setSjyhDate(String sjyhDate){
		this.sjyhDate = sjyhDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中期验货状态
	 */

	@Column(name ="ZQYH_SATE",nullable=true,length=32)
	public String getZqyhSate(){
		return this.zqyhSate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中期验货状态
	 */
	public void setZqyhSate(String zqyhSate){
		this.zqyhSate = zqyhSate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预计尾期验货日期
	 */

	@Column(name ="YJWQYH_DATE",nullable=true,length=32)
	public String getYjwqyhDate(){
		return this.yjwqyhDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计尾期验货日期
	 */
	public void setYjwqyhDate(String yjwqyhDate){
		this.yjwqyhDate = yjwqyhDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际尾期验货日期
	 */

	@Column(name ="SJWQYH_DATE",nullable=true,length=32)
	public String getSjwqyhDate(){
		return this.sjwqyhDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际尾期验货日期
	 */
	public void setSjwqyhDate(String sjwqyhDate){
		this.sjwqyhDate = sjwqyhDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尾期验货状态
	 */

	@Column(name ="WQYH_STATE",nullable=true,length=32)
	public String getWqyhState(){
		return this.wqyhState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尾期验货状态
	 */
	public void setWqyhState(String wqyhState){
		this.wqyhState = wqyhState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交日期
	 */

	@Column(name ="KD_TIME",nullable=true,length=32)
	public String getKdTime(){
		return this.kdTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交日期
	 */
	public void setKdTime(String kdTime){
		this.kdTime = kdTime;
	}
}
