package com.emk.storage.gl.entity;

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
 * @Description: 管理
 * @author onlineGenerator
 * @date 2019-02-15 22:25:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_gl", schema = "")
@SuppressWarnings("serial")
public class EmkGlEntity implements java.io.Serializable {
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
	/**场地*/
	@Excel(name="场地",width=15)
	private Double place;
	/**运输费*/
	@Excel(name="运输费",width=15)
	private Double tranCost;
	/**设备*/
	@Excel(name="设备",width=15)
	private Double equip;
	/**管理费*/
	@Excel(name="管理费",width=15)
	private Double glf;
	/**电费*/
	@Excel(name="电费",width=15)
	private Double dianfei;
	/**车辆使用费*/
	@Excel(name="车辆使用费",width=15)
	private Double carCost;
	/**水费*/
	@Excel(name="水费",width=15)
	private Double waterCost;
	/**其他*/
	@Excel(name="其他",width=15)
	private Double other;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  场地
	 */

	@Column(name ="PLACE",nullable=true,length=32)
	public Double getPlace(){
		return this.place;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  场地
	 */
	public void setPlace(Double place){
		this.place = place;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  运输费
	 */

	@Column(name ="TRAN_COST",nullable=true,length=32)
	public Double getTranCost(){
		return this.tranCost;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  运输费
	 */
	public void setTranCost(Double tranCost){
		this.tranCost = tranCost;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  设备
	 */

	@Column(name ="EQUIP",nullable=true,length=32)
	public Double getEquip(){
		return this.equip;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  设备
	 */
	public void setEquip(Double equip){
		this.equip = equip;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  管理费
	 */

	@Column(name ="GLF",nullable=true,length=32)
	public Double getGlf(){
		return this.glf;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  管理费
	 */
	public void setGlf(Double glf){
		this.glf = glf;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  电费
	 */

	@Column(name ="DIANFEI",nullable=true,length=32)
	public Double getDianfei(){
		return this.dianfei;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  电费
	 */
	public void setDianfei(Double dianfei){
		this.dianfei = dianfei;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  车辆使用费
	 */

	@Column(name ="CAR_COST",nullable=true,length=32)
	public Double getCarCost(){
		return this.carCost;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  车辆使用费
	 */
	public void setCarCost(Double carCost){
		this.carCost = carCost;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  水费
	 */

	@Column(name ="WATER_COST",nullable=true,length=32)
	public Double getWaterCost(){
		return this.waterCost;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  水费
	 */
	public void setWaterCost(Double waterCost){
		this.waterCost = waterCost;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  其他
	 */

	@Column(name ="OTHER",nullable=true,length=32)
	public Double getOther(){
		return this.other;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  其他
	 */
	public void setOther(Double other){
		this.other = other;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  报价单ID
	 */

	@Column(name ="PRICE_ID",nullable=true,length=32)
	public String getPriceId(){
		return this.priceId;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  报价单ID
	 */
	public void setPriceId(String priceId){
		this.priceId = priceId;
	}
}
