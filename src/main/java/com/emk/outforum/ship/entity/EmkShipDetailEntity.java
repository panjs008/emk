package com.emk.outforum.ship.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 客用船务文件明细
 * @author onlineGenerator
 * @date 2018-09-23 16:41:16
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_ship_detail", schema = "")
@SuppressWarnings("serial")
public class EmkShipDetailEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**主键*/
	private String shipId;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private String sumBoxTotal;
	/**总体积*/
	@Excel(name="总体积",width=15)
	private String sumBoxVolume;
	/**总净重*/
	@Excel(name="总净重",width=15)
	private String sumBoxJz;
	/**总毛重*/
	@Excel(name="总毛重",width=15)
	private String sumBoxMao;
	/**订舱通知单号*/
	@Excel(name="订舱通知单号",width=15)
	private String cargoNo;
	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outForumNo;
	/**离厂放行条号*/
	@Excel(name="离厂放行条号",width=15)
	private String levealFactoryNo;
	/**离厂日期*/
	@Excel(name="离厂日期",width=15)
	private String levealDate;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**生产合同号*/
	@Excel(name="生产合同号",width=15)
	private String htNum;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gys;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private String gysCode;
	@Excel(name = "款号")
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String shipDesc;
	/**数量*/
	@Excel(name="数量",width=15)
	private Integer total;
	/**提单号*/
	@Excel(name="提单号",width=15)
	private String tdNum;
	/**提单状态*/
	@Excel(name="提单状态",width=15)
	private String tdState;
	/**发票编号*/
	@Excel(name="发票编号",width=15)
	private String fpNum;

	/**装箱单编号*/
	@Excel(name="装箱单编号",width=15)
	private String boxNum;

	
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
	 *@return: java.lang.String  总箱数
	 */

	@Column(name ="SUM_BOX_TOTAL",nullable=true,length=32)
	public String getSumBoxTotal(){
		return this.sumBoxTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总箱数
	 */
	public void setSumBoxTotal(String sumBoxTotal){
		this.sumBoxTotal = sumBoxTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总体积
	 */

	@Column(name ="SUM_BOX_VOLUME",nullable=true,length=32)
	public String getSumBoxVolume(){
		return this.sumBoxVolume;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总体积
	 */
	public void setSumBoxVolume(String sumBoxVolume){
		this.sumBoxVolume = sumBoxVolume;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总净重
	 */

	@Column(name ="SUM_BOX_JZ",nullable=true,length=32)
	public String getSumBoxJz(){
		return this.sumBoxJz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总净重
	 */
	public void setSumBoxJz(String sumBoxJz){
		this.sumBoxJz = sumBoxJz;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总毛重
	 */

	@Column(name ="SUM_BOX_MAO",nullable=true,length=32)
	public String getSumBoxMao(){
		return this.sumBoxMao;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总毛重
	 */
	public void setSumBoxMao(String sumBoxMao){
		this.sumBoxMao = sumBoxMao;
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
	 *@return: java.lang.String  提单状态
	 */

	@Column(name ="TD_STATE",nullable=true,length=32)
	public String getTdState(){
		return this.tdState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提单状态
	 */
	public void setTdState(String tdState){
		this.tdState = tdState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票编号
	 */

	@Column(name ="FP_NUM",nullable=true,length=32)
	public String getFpNum(){
		return this.fpNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票编号
	 */
	public void setFpNum(String fpNum){
		this.fpNum = fpNum;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  装箱单编号
	 */

	@Column(name ="BOX_NUM",nullable=true,length=32)
	public String getBoxNum(){
		return this.boxNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  装箱单编号
	 */
	public void setBoxNum(String boxNum){
		this.boxNum = boxNum;
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
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="GYS_CODE",nullable=true,length=32)
	public String getGysCode(){
		return this.gysCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setGysCode(String gysCode){
		this.gysCode = gysCode;
	}

	@Column(name ="ship_id",nullable=true,length=32)
	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	@Column(name = "SAMPLE_NO", nullable = true, length = 32)
	public String getSampleNo() {
		return this.sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
}
