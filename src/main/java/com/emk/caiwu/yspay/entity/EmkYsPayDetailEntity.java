package com.emk.caiwu.yspay.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 运输费用申请单明细
 * @author onlineGenerator
 * @date 2018-09-23 22:51:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_ys_pay_detail", schema = "")
@SuppressWarnings("serial")
public class EmkYsPayDetailEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String ysPayId;

	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	@Excel(name = "款号")
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String shipDesc;
	/**数量*/
	@Excel(name="数量",width=15)
	private Integer total;
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
	/**运费*/
	@Excel(name="运费",width=15)
	private String cost;

	/**出货日期*/
	@Excel(name="出货日期",width=15)
	private String outDate;
	/**进仓日期*/
	@Excel(name="进仓日期",width=15)
	private String inDate;
	/**费用金额*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**始发地*/
	@Excel(name="始发地",width=15)
	private String startPlace;
	/**始发地*/
	@Excel(name="目的地",width=15)
	private String endPlace;
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

	@Column(name = "SAMPLE_NO", nullable = true, length = 32)
	public String getSampleNo() {
		return this.sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */

	@Column(name ="SHIP_DESC",nullable=true,length=32)
	public String getShipDesc(){
		return this.shipDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setShipDesc(String shipDesc){
		this.shipDesc = shipDesc;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public Integer getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setTotal(Integer total){
		this.total = total;
	}

	@Column(name ="ys_pay_id",nullable=true,length=32)
	public String getYsPayId() {
		return ysPayId;
	}

	public void setYsPayId(String ysPayId) {
		this.ysPayId = ysPayId;
	}

	@Column(name ="out_date",nullable=true,length=32)
	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	@Column(name ="in_date",nullable=true,length=32)
	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	@Column(name ="cus_num",nullable=true,length=32)
	public String getCusNum() {
		return cusNum;
	}

	public void setCusNum(String cusNum) {
		this.cusNum = cusNum;
	}

	@Column(name ="start_place",nullable=true,length=32)
	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	@Column(name ="end_place",nullable=true,length=32)
	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
}
