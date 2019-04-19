package com.emk.caiwu.yspay.entity;

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
 * @Description: 运输费用申请单
 * @author onlineGenerator
 * @date 2019-03-17 21:32:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_ys_pay", schema = "")
@SuppressWarnings("serial")
public class EmkYsPayEntity implements java.io.Serializable {
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
	/**运输单位代码*/
	@Excel(name="运输单位代码",width=15)
	private String ysdwCode;
	/**运输单位名称*/
	@Excel(name="运输单位名称",width=15)
	private String ysdwName;
	/**提交日期*/
	@Excel(name="提交日期",width=15)
	private String kdName;
	/**币种*/
	@Excel(name="币种",width=15)
	private String bz;
	/**总金额*/
	@Excel(name="总金额",width=15)
	private String sumMoney;
	/**收款单位名称*/
	@Excel(name="收款单位名称",width=15)
	private String skdwName;
	/**账号*/
	@Excel(name="账号",width=15)
	private String account;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private String relationer;
	/**电话*/
	@Excel(name="电话",width=15)
	private String telphone;
	
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
	 *@return: java.lang.String  运输单位代码
	 */

	@Column(name ="YSDW_CODE",nullable=true,length=32)
	public String getYsdwCode(){
		return this.ysdwCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输单位代码
	 */
	public void setYsdwCode(String ysdwCode){
		this.ysdwCode = ysdwCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输单位名称
	 */

	@Column(name ="YSDW_NAME",nullable=true,length=32)
	public String getYsdwName(){
		return this.ysdwName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输单位名称
	 */
	public void setYsdwName(String ysdwName){
		this.ysdwName = ysdwName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交日期
	 */

	@Column(name ="KD_NAME",nullable=true,length=32)
	public String getKdName(){
		return this.kdName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交日期
	 */
	public void setKdName(String kdName){
		this.kdName = kdName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */

	@Column(name ="BZ",nullable=true,length=32)
	public String getBz(){
		return this.bz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setBz(String bz){
		this.bz = bz;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总金额
	 */

	@Column(name ="SUM_MONEY",nullable=true,length=32)
	public String getSumMoney(){
		return this.sumMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总金额
	 */
	public void setSumMoney(String sumMoney){
		this.sumMoney = sumMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款单位名称
	 */

	@Column(name ="SKDW_NAME",nullable=true,length=32)
	public String getSkdwName(){
		return this.skdwName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款单位名称
	 */
	public void setSkdwName(String skdwName){
		this.skdwName = skdwName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */

	@Column(name ="ACCOUNT",nullable=true,length=32)
	public String getAccount(){
		return this.account;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setAccount(String account){
		this.account = account;
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
}
