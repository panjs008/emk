package com.emk.storage.pb.entity;

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
 * @Description: 坯布
 * @author onlineGenerator
 * @date 2019-02-15 22:25:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_pb", schema = "")
@SuppressWarnings("serial")
public class EmkPbEntity implements java.io.Serializable {
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
	/**下机克重*/
	@Excel(name="下机克重",width=15)
	private String xjkz;
	/**单件所需时间*/
	@Excel(name="单件所需时间",width=15)
	private Double djsxsj;
	/**单位*/
	@Excel(name="单位",width=15)
	private String dw;
	/**机台日产量*/
	@Excel(name="机台日产量",width=15)
	private Integer jtrcl;
	/**前道损耗率*/
	@Excel(name="前道损耗率",width=15)
	private Double qdhsl;
	/**后道损耗率*/
	@Excel(name="后道损耗率",width=15)
	private Double hdhsl;
	/**报价单ID*/
	@Excel(name="报价单ID",width=15)
	private String priceId;
	
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
	 *@return: java.lang.String  下机克重
	 */

	@Column(name ="XJKZ",nullable=true,length=32)
	public String getXjkz(){
		return this.xjkz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下机克重
	 */
	public void setXjkz(String xjkz){
		this.xjkz = xjkz;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单件所需时间
	 */

	@Column(name ="DJSXSJ",nullable=true,length=32)
	public Double getDjsxsj(){
		return this.djsxsj;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单件所需时间
	 */
	public void setDjsxsj(Double djsxsj){
		this.djsxsj = djsxsj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */

	@Column(name ="DW",nullable=true,length=32)
	public String getDw(){
		return this.dw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setDw(String dw){
		this.dw = dw;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  机台日产量
	 */

	@Column(name ="JTRCL",nullable=true,length=32)
	public Integer getJtrcl(){
		return this.jtrcl;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  机台日产量
	 */
	public void setJtrcl(Integer jtrcl){
		this.jtrcl = jtrcl;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  前道损耗率
	 */

	@Column(name ="QDHSL",nullable=true,length=32)
	public Double getQdhsl(){
		return this.qdhsl;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  前道损耗率
	 */
	public void setQdhsl(Double qdhsl){
		this.qdhsl = qdhsl;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  后道损耗率
	 */

	@Column(name ="HDHSL",nullable=true,length=32)
	public Double getHdhsl(){
		return this.hdhsl;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  后道损耗率
	 */
	public void setHdhsl(Double hdhsl){
		this.hdhsl = hdhsl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报价单ID
	 */

	@Column(name ="PRICE_ID",nullable=true,length=32)
	public String getPriceId(){
		return this.priceId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报价单ID
	 */
	public void setPriceId(String priceId){
		this.priceId = priceId;
	}
}
