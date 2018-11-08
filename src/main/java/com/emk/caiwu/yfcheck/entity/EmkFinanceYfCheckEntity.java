package com.emk.caiwu.yfcheck.entity;

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
 * @Description: 应付核准单
 * @author onlineGenerator
 * @date 2018-11-03 21:55:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_finance_yf_check", schema = "")
@SuppressWarnings("serial")
public class EmkFinanceYfCheckEntity implements java.io.Serializable {
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
	/**应付通知单号*/
	@Excel(name="应付通知单号",width=15)
	private String yfCheckNo;
	/**应付核准单编号*/
	@Excel(name="应付核准单编号",width=15)
	private String yhzCheckNo;
	/**付款项目类别*/
	@Excel(name="付款项目类别",width=15)
	private String fkxmlb;
	/**应付通知单日期*/
	@Excel(name="应付通知单日期",width=15)
	private String yftzDate;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outFormnNo;
	/**货代费用付款申请单号*/
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
	/**船务员*/
	@Excel(name="船务员",width=15)
	private String cwer;
	
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
	 *@return: java.lang.String  应付通知单号
	 */

	@Column(name ="YF_CHECK_NO",nullable=true,length=32)
	public String getYfCheckNo(){
		return this.yfCheckNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应付通知单号
	 */
	public void setYfCheckNo(String yfCheckNo){
		this.yfCheckNo = yfCheckNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应付核准单编号
	 */

	@Column(name ="YHZ_CHECK_NO",nullable=true,length=32)
	public String getYhzCheckNo(){
		return this.yhzCheckNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应付核准单编号
	 */
	public void setYhzCheckNo(String yhzCheckNo){
		this.yhzCheckNo = yhzCheckNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款项目类别
	 */

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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应付通知单日期
	 */

	@Column(name ="YFTZ_DATE",nullable=true,length=32)
	public String getYftzDate(){
		return this.yftzDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应付通知单日期
	 */
	public void setYftzDate(String yftzDate){
		this.yftzDate = yftzDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货通知单号
	 */

	@Column(name ="OUT_FORMN_NO",nullable=true,length=32)
	public String getOutFormnNo(){
		return this.outFormnNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货通知单号
	 */
	public void setOutFormnNo(String outFormnNo){
		this.outFormnNo = outFormnNo;
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
}
