package com.emk.outforum.fobbusiness.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 出货订舱进度明细
 * @author onlineGenerator
 * @date 2018-09-23 18:34:16
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_fob_business_detail", schema = "")
@SuppressWarnings("serial")
public class EmkFobBusinessDetailEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String fobId;
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleNoDesc;
	/**总金额*/
	@Excel(name="总金额",width=15)
	private Double sumMoney;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private Integer sumBox;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outFourmNo;
	/**订舱通知单号*/
	@Excel(name="订舱通知单号",width=15)
	private String cargoNo;
	/**离厂放行条号*/
	@Excel(name="离厂放行条号",width=15)
	private String levealFactoryNo;
	@Excel(name="离厂日期",width=15)
	private String levealDate;
	/**订舱状态*/
	@Excel(name="订舱状态",width=15)
	private String cargoState;
	/**出厂状态*/
	@Excel(name="出厂状态",width=15)
	private String levealState;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;

	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;

	/**数量*/
	@Excel(name="数量",width=15)
	private Integer total;
	/**单价*/
	@Excel(name="单价",width=15)
	private String price;

	@Excel(name="工厂代码",width=15)
	private String factoryCode;
	@Excel(name="交期",width=15)
	private String jqDate;
	@Excel(name="出货日期",width=15)
	private String outDate;
	@Excel(name="提单号",width=15)
	private String tdNum;
	@Excel(name="提单状态",width=15)
	private String tdState;
	@Excel(name="起运港",width=15)
	private String startPlace;
	@Excel(name="目的港",width=15)
	private String endPlace;

	@Excel(name="货代代码",width=15)
	private String hdCode;
	@Excel(name="出货费用金额",width=15)
	private String chCost;
	@Excel(name="出货费用状态",width=15)
	private String chState;
	@Excel(name="运输企业代码",width=15)
	private String ysEntCode;
	@Excel(name="运输费用金额",width=15)
	private String ysEntCost;
	@Excel(name="运输费状态",width=15)
	private String ysState;
	@Excel(name="报关单",width=15)
	private String bgd;
	@Excel(name="放行条",width=15)
	private String fxt;
	@Excel(name="发票号",width=15)
	private String fpNum;
	@Excel(name="装箱单",width=15)
	private String zxd;
	@Excel(name="提单扫描件",width=15)
	private String tdsmj;
	@Excel(name="提单扫描件",width=15)
	private String tdsmjUrl;

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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总金额
	 */

	@Column(name ="SUM_MONEY",nullable=true,scale=2,length=32)
	public Double getSumMoney(){
		return this.sumMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总金额
	 */
	public void setSumMoney(Double sumMoney){
		this.sumMoney = sumMoney;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总箱数
	 */

	@Column(name ="SUM_BOX",nullable=true,length=32)
	public Integer getSumBox(){
		return this.sumBox;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总箱数
	 */
	public void setSumBox(Integer sumBox){
		this.sumBox = sumBox;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出货通知单号
	 */

	@Column(name ="OUT_FOURM_NO",nullable=true,length=32)
	public String getOutFourmNo(){
		return this.outFourmNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出货通知单号
	 */
	public void setOutFourmNo(String outFourmNo){
		this.outFourmNo = outFourmNo;
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
	 *@return: java.lang.String  订舱状态
	 */

	@Column(name ="CARGO_STATE",nullable=true,length=32)
	public String getCargoState(){
		return this.cargoState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订舱状态
	 */
	public void setCargoState(String cargoState){
		this.cargoState = cargoState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出厂状态
	 */

	@Column(name ="LEVEAL_STATE",nullable=true,length=32)
	public String getLevealState(){
		return this.levealState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出厂状态
	 */
	public void setLevealState(String levealState){
		this.levealState = levealState;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */

	@Column(name ="PRICE",nullable=true,length=32)
	public String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setPrice(String price){
		this.price = price;
	}

	@Column(name ="fob_id",nullable=true,length=32)
	public String getFobId() {
		return fobId;
	}

	public void setFobId(String fobId) {
		this.fobId = fobId;
	}

	@Column(name ="factory_code",nullable=true,length=32)
	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Column(name ="jq_date",nullable=true,length=32)
	public String getJqDate() {
		return jqDate;
	}

	public void setJqDate(String jqDate) {
		this.jqDate = jqDate;
	}

	@Column(name ="out_date",nullable=true,length=32)
	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	@Column(name ="td_num",nullable=true,length=32)
	public String getTdNum() {
		return tdNum;
	}

	public void setTdNum(String tdNum) {
		this.tdNum = tdNum;
	}

	@Column(name ="td_state",nullable=true,length=32)
	public String getTdState() {
		return tdState;
	}

	public void setTdState(String tdState) {
		this.tdState = tdState;
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

	@Column(name ="hd_code",nullable=true,length=32)
	public String getHdCode() {
		return hdCode;
	}

	public void setHdCode(String hdCode) {
		this.hdCode = hdCode;
	}

	@Column(name ="ch_cost",nullable=true,length=32)
	public String getChCost() {
		return chCost;
	}

	public void setChCost(String chCost) {
		this.chCost = chCost;
	}

	@Column(name ="ch_state",nullable=true,length=32)
	public String getChState() {
		return chState;
	}

	public void setChState(String chState) {
		this.chState = chState;
	}

	@Column(name ="ys_ent_code",nullable=true,length=32)
	public String getYsEntCode() {
		return ysEntCode;
	}

	public void setYsEntCode(String ysEntCode) {
		this.ysEntCode = ysEntCode;
	}

	@Column(name ="ys_ent_cost",nullable=true,length=32)
	public String getYsEntCost() {
		return ysEntCost;
	}

	public void setYsEntCost(String ysEntCost) {
		this.ysEntCost = ysEntCost;
	}

	@Column(name ="ys_state",nullable=true,length=32)
	public String getYsState() {
		return ysState;
	}

	public void setYsState(String ysState) {
		this.ysState = ysState;
	}

	@Column(name ="fxt",nullable=true,length=32)
	public String getFxt() {
		return fxt;
	}

	public void setFxt(String fxt) {
		this.fxt = fxt;
	}

	@Column(name ="fp_num",nullable=true,length=32)
	public String getFpNum() {
		return fpNum;
	}

	public void setFpNum(String fpNum) {
		this.fpNum = fpNum;
	}

	@Column(name ="zxd",nullable=true,length=32)
	public String getZxd() {
		return zxd;
	}

	public void setZxd(String zxd) {
		this.zxd = zxd;
	}

	@Column(name ="tdsmj",nullable=true,length=32)
	public String getTdsmj() {
		return tdsmj;
	}

	public void setTdsmj(String tdsmj) {
		this.tdsmj = tdsmj;
	}

	@Column(name ="tdsmj_url",nullable=true,length=32)
	public String getTdsmjUrl() {
		return tdsmjUrl;
	}

	public void setTdsmjUrl(String tdsmjUrl) {
		this.tdsmjUrl = tdsmjUrl;
	}

	@Column(name ="bgd",nullable=true,length=32)
	public String getBgd() {
		return bgd;
	}

	public void setBgd(String bgd) {
		this.bgd = bgd;
	}

	@Column(name ="leveal_date",nullable=true,length=32)
	public String getLevealDate() {
		return levealDate;
	}

	public void setLevealDate(String levealDate) {
		this.levealDate = levealDate;
	}
}
