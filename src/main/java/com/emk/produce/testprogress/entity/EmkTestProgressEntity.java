package com.emk.produce.testprogress.entity;

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
 * @Description: 测试进度更新表
 * @author onlineGenerator
 * @date 2018-10-27 10:49:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_test_progress", schema = "")
@SuppressWarnings("serial")
public class EmkTestProgressEntity implements java.io.Serializable {
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
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**工艺种类*/
	@Excel(name="工艺种类",width=15)
	private String gyzl;
	/**款式大类*/
	private String proType;
	/**款式大类*/
	@Excel(name="款式大类",width=15)
	private String proTypeName;
	/**颜色*/
	@Excel(name="颜色",width=15)
	private String color;
	/**总数量*/
	@Excel(name="总数量",width=15)
	private String total;
	/**测试状态*/
	@Excel(name="测试状态",width=15)
	private String testState;
	/**最晚确认时间*/
	@Excel(name="最晚确认时间",width=15)
	private String lastConfrimDate;
	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private String outDate;
	/**更新日期*/
	@Excel(name="更新日期",width=15)
	private String updateDate;
	
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
	 *@return: java.lang.String  颜色
	 */

	@Column(name ="COLOR",nullable=true,length=32)
	public String getColor(){
		return this.color;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  颜色
	 */
	public void setColor(String color){
		this.color = color;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public String getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总数量
	 */
	public void setTotal(String total){
		this.total = total;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测试状态
	 */

	@Column(name ="TEST_STATE",nullable=true,length=32)
	public String getTestState(){
		return this.testState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测试状态
	 */
	public void setTestState(String testState){
		this.testState = testState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最晚确认时间
	 */

	@Column(name ="LAST_CONFRIM_DATE",nullable=true,length=32)
	public String getLastConfrimDate(){
		return this.lastConfrimDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最晚确认时间
	 */
	public void setLastConfrimDate(String lastConfrimDate){
		this.lastConfrimDate = lastConfrimDate;
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
	 *@return: java.lang.String  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=32)
	public String getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新日期
	 */
	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}
}
