package com.emk.outforum.shipexpenses.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 运费费
 * @author onlineGenerator
 * @date 2018-09-24 11:25:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_ship_expenses_detail", schema = "")
@SuppressWarnings("serial")
public class EmkShipExpensesDetailEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String shipExpensesId;

	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private String htNum;
	@Excel(name = "款号")
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private Integer sumBoxTotal;
	/**总体积*/
	@Excel(name="总体积",width=15)
	private Double sumBoxVolume;
	/**总净重*/
	@Excel(name="总净重",width=15)
	private Double sumBoxJz;
	/**总毛重*/
	@Excel(name="总毛重",width=15)
	private Double sumBoxMao;

	/**离厂日期*/
	@Excel(name="离厂日期",width=15)
	private String levealDate;

	/**数量*/
	@Excel(name="数量",width=15)
	private String total;

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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总箱数
	 */

	@Column(name ="SUM_BOX_TOTAL",nullable=true,length=32)
	public Integer getSumBoxTotal(){
		return this.sumBoxTotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总箱数
	 */
	public void setSumBoxTotal(Integer sumBoxTotal){
		this.sumBoxTotal = sumBoxTotal;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总体积
	 */

	@Column(name ="SUM_BOX_VOLUME",nullable=true,scale=2,length=32)
	public Double getSumBoxVolume(){
		return this.sumBoxVolume;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总体积
	 */
	public void setSumBoxVolume(Double sumBoxVolume){
		this.sumBoxVolume = sumBoxVolume;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总净重
	 */

	@Column(name ="SUM_BOX_JZ",nullable=true,scale=2,length=32)
	public Double getSumBoxJz(){
		return this.sumBoxJz;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总净重
	 */
	public void setSumBoxJz(Double sumBoxJz){
		this.sumBoxJz = sumBoxJz;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总毛重
	 */

	@Column(name ="SUM_BOX_MAO",nullable=true,scale=2,length=32)
	public Double getSumBoxMao(){
		return this.sumBoxMao;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总毛重
	 */
	public void setSumBoxMao(Double sumBoxMao){
		this.sumBoxMao = sumBoxMao;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=32)
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
	 *@return: java.lang.String  离厂日期
	 */

	@Column(name ="LEVEAL_DATE",nullable=true,length=32)
	public String getLevealDate(){
		return this.levealDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离厂日期
	 */
	public void setLevealDate(String levealDate){
		this.levealDate = levealDate;
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

	@Column(name ="HT_NUM",nullable=true,length=32)
	public String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产合同号
	 */
	public void setHtNum(String htNum){
		this.htNum = htNum;
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

	@Column(name = "SAMPLE_NO", nullable = true, length = 32)
	public String getSampleNo() {
		return this.sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	@Column(name = "ship_expenses_id", nullable = true, length = 32)
	public String getShipExpensesId() {
		return shipExpensesId;
	}

	public void setShipExpensesId(String shipExpensesId) {
		this.shipExpensesId = shipExpensesId;
	}
}
