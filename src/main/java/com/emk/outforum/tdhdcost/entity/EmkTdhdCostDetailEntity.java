package com.emk.outforum.tdhdcost.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 提单货代费用明细
 * @author onlineGenerator
 * @date 2018-09-23 22:51:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_tdhd_cost_detail", schema = "")
@SuppressWarnings("serial")
public class EmkTdhdCostDetailEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String tdhdCostId;

	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**订舱通知单号*/
	@Excel(name="订舱通知单号",width=15)
	private String cargoNo;
	/**离厂放行条号*/
	@Excel(name="离厂放行条号",width=15)
	private String levealFactoryNo;
	/**离厂日期*/
	@Excel(name="离厂日期",width=15)
	private String levealDate;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outForumNo;
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
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private String htNum;
	/**提单号*/
	@Excel(name="提单号",width=15)
	private String tdNum;
	/**费用金额*/
	@Excel(name="费用金额",width=15)
	private String cost;

	

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
	 *@return: java.lang.String  订舱通知单号
	 */

	@Column(name ="CARGO_NO",nullable=true,length=32)
	public String getCargoNo(){
		return this.cargoNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订舱通知单号
	 */
	public void setCargoNo(String cargoNo){
		this.cargoNo = cargoNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离厂放行条号
	 */

	@Column(name ="LEVEAL_FACTORY_NO",nullable=true,length=32)
	public String getLevealFactoryNo(){
		return this.levealFactoryNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离厂放行条号
	 */
	public void setLevealFactoryNo(String levealFactoryNo){
		this.levealFactoryNo = levealFactoryNo;
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
	 *@return: java.lang.String  出货通知单号
	 */

	@Column(name ="OUT_FORUM_NO",nullable=true,length=32)
	public String getOutForumNo(){
		return this.outForumNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货通知单号
	 */
	public void setOutForumNo(String outForumNo){
		this.outForumNo = outForumNo;
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
	 *@return: java.lang.String  提单号
	 */

	@Column(name ="TD_NUM",nullable=true,length=32)
	public String getTdNum(){
		return this.tdNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提单号
	 */
	public void setTdNum(String tdNum){
		this.tdNum = tdNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用金额
	 */

	@Column(name ="COST",nullable=true,length=32)
	public String getCost(){
		return this.cost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用金额
	 */
	public void setCost(String cost){
		this.cost = cost;
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

	@Column(name ="tdhd_cost_id",nullable=true,length=32)
	public String getTdhdCostId() {
		return tdhdCostId;
	}

	public void setTdhdCostId(String tdhdCostId) {
		this.tdhdCostId = tdhdCostId;
	}
}
