package com.emk.bound.minstoragedetail.entity;

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
 * @Description: 出入库明细
 * @author onlineGenerator
 * @date 2018-09-13 22:20:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_m_in_storage_detail", schema = "")
@SuppressWarnings("serial")
public class EmkMInStorageDetailEntity implements java.io.Serializable {
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
	/**采购合同号*/
	@Excel(name="采购合同号",width=15)
	private String htNum;
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**品名*/
	@Excel(name="品名",width=15)
	private String proZnName;
	/**物料编号*/
	@Excel(name="物料编号",width=15)
	private String proNum;
	/**采购数量*/
	@Excel(name="采购数量",width=15)
	private String total;
	/**入库数量*/
	@Excel(name="入库数量",width=15)
	private String inTotal;
	/**数量*/
	@Excel(name="数量",width=15)
	private String htTotal;
	/**实际入库数量*/
	@Excel(name="实际入库数量",width=15)
	private String actualTotal;
	/**类型*/
	@Excel(name="类型",width=15)
	private String type;
	/**入库ID*/
	@Excel(name="入库ID",width=15)
	private String inStorageId;
	/**规格*/
	@Excel(name="规格",width=15)
	private String brand;
	/**单位*/
	@Excel(name="单位",width=15)
	private String unit;
	/**入库日期*/
	@Excel(name="入库日期",width=15)
	private String kdDate;
	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private String outDate;
	
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
	 *@return: java.lang.String  品名
	 */

	@Column(name ="PRO_ZN_NAME",nullable=true,length=32)
	public String getProZnName(){
		return this.proZnName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品名
	 */
	public void setProZnName(String proZnName){
		this.proZnName = proZnName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料编号
	 */

	@Column(name ="PRO_NUM",nullable=true,length=32)
	public String getProNum(){
		return this.proNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料编号
	 */
	public void setProNum(String proNum){
		this.proNum = proNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public String getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购数量
	 */
	public void setTotal(String total){
		this.total = total;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库数量
	 */

	@Column(name ="IN_TOTAL",nullable=true,length=32)
	public String getInTotal(){
		return this.inTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库数量
	 */
	public void setInTotal(String inTotal){
		this.inTotal = inTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */

	@Column(name ="HT_TOTAL",nullable=true,length=32)
	public String getHtTotal(){
		return this.htTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setHtTotal(String htTotal){
		this.htTotal = htTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际入库数量
	 */

	@Column(name ="ACTUAL_TOTAL",nullable=true,length=32)
	public String getActualTotal(){
		return this.actualTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际入库数量
	 */
	public void setActualTotal(String actualTotal){
		this.actualTotal = actualTotal;
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
	 *@return: java.lang.String  入库ID
	 */

	@Column(name ="IN_STORAGE_ID",nullable=true,length=32)
	public String getInStorageId(){
		return this.inStorageId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库ID
	 */
	public void setInStorageId(String inStorageId){
		this.inStorageId = inStorageId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格
	 */

	@Column(name ="BRAND",nullable=true,length=32)
	public String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格
	 */
	public void setBrand(String brand){
		this.brand = brand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */

	@Column(name ="UNIT",nullable=true,length=32)
	public String getUnit(){
		return this.unit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setUnit(String unit){
		this.unit = unit;
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
	 *@return: java.lang.String  入库日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
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
}
