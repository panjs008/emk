package com.emk.check.sizecheck.entity;

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
 * @Description: 尺寸检查表
 * @author onlineGenerator
 * @date 2018-09-26 22:35:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_size_check", schema = "")
@SuppressWarnings("serial")
public class EmkSizeCheckEntity implements java.io.Serializable {
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
	/**尺寸检查编号*/
	@Excel(name="尺寸检查编号",width=15)
	private String sizeCheckNum;
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
	/**生产跟单员ID*/
	@Excel(name="生产跟单员ID",width=15)
	private String developerName;
	/**生产跟单员*/
	@Excel(name="生产跟单员",width=15)
	private String developer;
	/**供应商名称*/
	@Excel(name="供应商名称",width=15)
	private String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**合同号*/
	@Excel(name="合同号",width=15)
	private String htNum;
	/**工厂名称*/
	@Excel(name="工厂名称",width=15)
	private String factoryName;
	/**工厂地址*/
	@Excel(name="工厂地址",width=15)
	private String address;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private String relationer;
	/**电话*/
	@Excel(name="电话",width=15)
	private String telphone;
	/**验货类型*/
	@Excel(name="验货类型",width=15)
	private String checkType;
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleDesc;
	/**颜色*/
	@Excel(name="颜色",width=15)
	private String color;
	/**尺码范围*/
	@Excel(name="尺码范围",width=15)
	private String size;
	/**总数量*/
	@Excel(name="总数量",width=15)
	private String total;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private String boxTotal;
	/**版次*/
	@Excel(name="版次",width=15)
	private String vesion;
	/**抽检数量*/
	@Excel(name="抽检数量",width=15)
	private String checkTotal;
	/**检查日期*/
	@Excel(name="检查日期",width=15)
	private String checkDate;
	/**测量部位A*/
	@Excel(name="测量部位A",width=15)
	private String clbwa;
	/**尺码 */
	@Excel(name="尺码 ",width=15)
	private String chima;
	/**测量部位A标准尺*/
	@Excel(name="测量部位A标准尺",width=15)
	private String clbwabzc;
	/**样衣1尺寸*/
	@Excel(name="样衣1尺寸",width=15)
	private String yycc;
	/**抽检结果*/
	@Excel(name="抽检结果",width=15)
	private String checkResult;
	/**备注*/
	@Excel(name="备注",width=15)
	private String remart;
	/**报告日期*/
	@Excel(name="报告日期",width=15)
	private String kdDate;
	
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
	 *@return: java.lang.String  尺寸检查编号
	 */

	@Column(name ="SIZE_CHECK_NUM",nullable=true,length=32)
	public String getSizeCheckNum(){
		return this.sizeCheckNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尺寸检查编号
	 */
	public void setSizeCheckNum(String sizeCheckNum){
		this.sizeCheckNum = sizeCheckNum;
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
	 *@return: java.lang.String  供应商名称
	 */

	@Column(name ="GYS",nullable=true,length=32)
	public String getGys(){
		return this.gys;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
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

	@Column(name ="CUS_NAME",nullable=true,length=56)
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
	 *@return: java.lang.String  合同号
	 */

	@Column(name ="HT_NUM",nullable=true,length=32)
	public String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同号
	 */
	public void setHtNum(String htNum){
		this.htNum = htNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工厂名称
	 */

	@Column(name ="FACTORY_NAME",nullable=true,length=56)
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
	 *@return: java.lang.String  工厂地址
	 */

	@Column(name ="ADDRESS",nullable=true,length=256)
	public String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂地址
	 */
	public void setAddress(String address){
		this.address = address;
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
	 *@return: java.lang.String  电话
	 */

	@Column(name ="TELPHONE",nullable=true,length=32)
	public String getTelphone(){
		return this.telphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验货类型
	 */

	@Column(name ="CHECK_TYPE",nullable=true,length=32)
	public String getCheckType(){
		return this.checkType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验货类型
	 */
	public void setCheckType(String checkType){
		this.checkType = checkType;
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

	@Column(name ="SAMPLE_DESC",nullable=true,length=32)
	public String getSampleDesc(){
		return this.sampleDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setSampleDesc(String sampleDesc){
		this.sampleDesc = sampleDesc;
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
	 *@return: java.lang.String  尺码范围
	 */

	@Column(name ="SIZE",nullable=true,length=32)
	public String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尺码范围
	 */
	public void setSize(String size){
		this.size = size;
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
	 *@return: java.lang.String  总箱数
	 */

	@Column(name ="BOX_TOTAL",nullable=true,length=32)
	public String getBoxTotal(){
		return this.boxTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总箱数
	 */
	public void setBoxTotal(String boxTotal){
		this.boxTotal = boxTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版次
	 */

	@Column(name ="VESION",nullable=true,length=32)
	public String getVesion(){
		return this.vesion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版次
	 */
	public void setVesion(String vesion){
		this.vesion = vesion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抽检数量
	 */

	@Column(name ="CHECK_TOTAL",nullable=true,length=32)
	public String getCheckTotal(){
		return this.checkTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抽检数量
	 */
	public void setCheckTotal(String checkTotal){
		this.checkTotal = checkTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查日期
	 */

	@Column(name ="CHECK_DATE",nullable=true,length=32)
	public String getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查日期
	 */
	public void setCheckDate(String checkDate){
		this.checkDate = checkDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测量部位A
	 */

	@Column(name ="CLBWA",nullable=true,length=32)
	public String getClbwa(){
		return this.clbwa;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测量部位A
	 */
	public void setClbwa(String clbwa){
		this.clbwa = clbwa;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尺码 
	 */

	@Column(name ="CHIMA",nullable=true,length=32)
	public String getChima(){
		return this.chima;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尺码 
	 */
	public void setChima(String chima){
		this.chima = chima;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测量部位A标准尺
	 */

	@Column(name ="CLBWABZC",nullable=true,length=32)
	public String getClbwabzc(){
		return this.clbwabzc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测量部位A标准尺
	 */
	public void setClbwabzc(String clbwabzc){
		this.clbwabzc = clbwabzc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  样衣1尺寸
	 */

	@Column(name ="YYCC",nullable=true,length=32)
	public String getYycc(){
		return this.yycc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  样衣1尺寸
	 */
	public void setYycc(String yycc){
		this.yycc = yycc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抽检结果
	 */

	@Column(name ="CHECK_RESULT",nullable=true,length=256)
	public String getCheckResult(){
		return this.checkResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抽检结果
	 */
	public void setCheckResult(String checkResult){
		this.checkResult = checkResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMART",nullable=true,length=256)
	public String getRemart(){
		return this.remart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemart(String remart){
		this.remart = remart;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报告日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报告日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}
}
