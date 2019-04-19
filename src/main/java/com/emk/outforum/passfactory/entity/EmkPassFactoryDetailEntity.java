package com.emk.outforum.passfactory.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 离厂放行单明细
 * @author onlineGenerator
 * @date 2018-09-23 19:38:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_pass_factory_detail", schema = "")
@SuppressWarnings("serial")
public class EmkPassFactoryDetailEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String passId;

	/**出货通知单号*/
	@Excel(name="出货通知单号",width=15)
	private String outFourmNo;
	/**订舱通知单号*/
	@Excel(name="订舱通知单号",width=15)
	private String cargoNo;
	/**离厂放行条号*/
	@Excel(name="离厂放行条号",width=15)
	private String levealFactoryNo;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	@Excel(name="生产合同号",width=15)
	private String htNum;
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**款号*/
	@Excel(name="款号",width=15)
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

	@Excel(name="离厂放行状态",width=15)
	private String state;
	

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
	 *@return: java.lang.String  离厂日期
	 */

	@Column(name ="LEVEAL_DATE",nullable=true,length=32)
	public String getLevealDate(){
		return this.levealDate;
	}

	public void setLevealDate(String levealDate) {
		this.levealDate = levealDate;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离厂通知单状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离厂通知单状态
	 */
	public void setState(String state){
		this.state = state;
	}
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


	@Column(name ="total",nullable=true,scale=2,length=32)
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Column(name ="gys_code",nullable=true,scale=2,length=32)
	public String getGysCode() {
		return gysCode;
	}

	public void setGysCode(String gysCode) {
		this.gysCode = gysCode;
	}

	@Column(name ="pass_id",nullable=true,scale=2,length=32)
	public String getPassId() {
		return passId;
	}

	public void setPassId(String passId) {
		this.passId = passId;
	}
}
