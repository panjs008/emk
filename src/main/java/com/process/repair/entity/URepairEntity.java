package com.process.repair.entity;

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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 报修单表
 * @author onlineGenerator
 * @date 2018-05-01 14:19:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "u_repair", schema = "")
@SuppressWarnings("serial")
public class URepairEntity implements java.io.Serializable {
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
	/**用户ID*/
	@Excel(name="用户ID",width=15)
	private String userId;
	/**报修人*/
	@Excel(name="报修人",width=15)
	private String userName;
	/**设备类型*/
	@Excel(name="设备类型",width=15)
	private String equipType;
	/**设备型号*/
	@Excel(name="设备型号",width=15)
	private String brand;
	@Excel(name="地区",width=15)
	private String address;
	/**故障描述*/
	@Excel(name="故障描述",width=15)
	private String fault;
	/**报修时间*/
	@Excel(name="报修时间",width=15)
	private String applyDate;
	/**备注*/
	@Excel(name="备注",width=15)
	private String remark;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private String relationer;
	/**状态*/
	@Excel(name="状态",width=15)
	private String status;
	/**产品名称*/
	@Excel(name="产品名称",width=15)
	private String proZnName;
	/**产品编号*/
	@Excel(name="产品编号",width=15)
	private String proNum;
	/**单位*/
	@Excel(name="单位",width=15)
	private String unit;
	/**报修数量*/
	@Excel(name="报修数量",width=15)
	private String total;
	/**产品类型*/
	@Excel(name="产品类型",width=15)
	private String proType;
	/**产品ID*/
	@Excel(name="产品ID",width=15)
	private String proId;
	/**产品类型*/
	@Excel(name="产品类型",width=15)
	private String proTypeName;
	/**检测人ID*/
	@Excel(name="检测人ID",width=15)
	private String checkUserId;
	/**检测人*/
	@Excel(name="检测人",width=15)
	private String checkUserName;
	/**检测时间*/
	@Excel(name="检测时间",width=15)
	private String checkTime;
	/**检测情况*/
	@Excel(name="检测情况",width=15)
	private String checkContent;
	/**维修人ID*/
	@Excel(name="维修人ID",width=15)
	private String repairUserId;
	/**维修人*/
	@Excel(name="维修人",width=15)
	private String repairUserName;
	/**维修内容*/
	@Excel(name="维修内容",width=15)
	private String repairContent;
	/**考核备注*/
	@Excel(name="考核备注",width=15)
	private String kaoheRemark;
	/**考核时间*/
	@Excel(name="考核时间",width=15)
	private String kaoheTime;
	/**是否自修*/
	@Excel(name="是否自修",width=15)
	private String isFinish;
	/**保修类别*/
	@Excel(name="保修类别",width=15)
	private String repairType;
	/**报修方式*/
	@Excel(name="报修方式",width=15)
	private String bxType;
	/**报修单号*/
	@Excel(name="报修单号",width=15)
	private String repairNum;
	/**被考核人ID*/
	@Excel(name="被考核人ID",width=15)
	private String kaoheUserId;
	/**被考核人*/
	@Excel(name="被考核人",width=15)
	private String kaoheUserName;
	/**维修时间*/
	@Excel(name="维修时间",width=15)
	private String repairTime;
	/**服务商ID*/
	@Excel(name="服务商ID",width=15)
	private String cusId;
	/**服务商*/
	@Excel(name="服务商",width=15)
	private String cusName;
	/**派单日期*/
	@Excel(name="派单日期",width=15)
	private String sendDatae;
	/**抢单状态*/
	@Excel(name="抢单状态",width=15)
	private String qdStatus;
	/**接单人ID*/
	@Excel(name="接单人ID",width=15)
	private String recevieUserId;
	/**接单人*/
	@Excel(name="接单人",width=15)
	private String recevieUserName;
	/**接单时间*/
	@Excel(name="接单时间",width=15)
	private String recevieDate;
	/**备注*/
	@Excel(name="备注",width=15)
	private String recevieRemark;
	/**处理类型*/
	@Excel(name="处理类型",width=15)
	private String csType;
	/**服务台处理意见*/
	@Excel(name="服务台处理意见",width=15)
	private String serviceContent;
	/**服务台处理人ID*/
	@Excel(name="服务台处理人ID",width=15)
	private String serviceUserId;
	/**服务台处理人*/
	@Excel(name="服务台处理人",width=15)
	private String serviceUserName;
	/**服务商处理意见*/
	@Excel(name="服务商处理意见",width=15)
	private String cusContent;
	/**本地服务商处理意见*/
	@Excel(name="本地服务商处理意见",width=15)
	private String localContent;
	/**紧急度*/
	@Excel(name="紧急度",width=15)
	private String jjd;
	/**设备类型*/
	@Excel(name="设备类型",width=15)
	private String sblx;
	/**品牌*/
	@Excel(name="品牌",width=15)
	private String pp;
	/**型号*/
	@Excel(name="型号",width=15)
	private String xh;
	private String processValue;
	
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
	@Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
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
	 *@return: java.lang.String  用户ID
	 */

	@Column(name ="USER_ID",nullable=true,length=32)
	public String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户ID
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报修人
	 */

	@Column(name ="USER_NAME",nullable=true,length=32)
	public String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修人
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备类型
	 */

	@Column(name ="EQUIP_TYPE",nullable=true,length=32)
	public String getEquipType(){
		return this.equipType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备类型
	 */
	public void setEquipType(String equipType){
		this.equipType = equipType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备型号
	 */

	@Column(name ="BRAND",nullable=true,length=32)
	public String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备型号
	 */
	public void setBrand(String brand){
		this.brand = brand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  故障描述
	 */

	@Column(name ="FAULT",nullable=true,length=256)
	public String getFault(){
		return this.fault;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  故障描述
	 */
	public void setFault(String fault){
		this.fault = fault;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报修时间
	 */

	@Column(name ="APPLY_DATE",nullable=true,length=32)
	public String getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修时间
	 */
	public void setApplyDate(String applyDate){
		this.applyDate = applyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=32)
	public String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(String remark){
		this.remark = remark;
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
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATUS",nullable=true,length=32)
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品名称
	 */

	@Column(name ="PRO_ZN_NAME",nullable=true,length=32)
	public String getProZnName(){
		return this.proZnName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setProZnName(String proZnName){
		this.proZnName = proZnName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品编号
	 */

	@Column(name ="PRO_NUM",nullable=true,length=32)
	public String getProNum(){
		return this.proNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品编号
	 */
	public void setProNum(String proNum){
		this.proNum = proNum;
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
	 *@return: java.lang.String  报修数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public String getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修数量
	 */
	public void setTotal(String total){
		this.total = total;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品类型
	 */

	@Column(name ="PRO_TYPE",nullable=true,length=32)
	public String getProType(){
		return this.proType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品类型
	 */
	public void setProType(String proType){
		this.proType = proType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品ID
	 */

	@Column(name ="PRO_ID",nullable=true,length=32)
	public String getProId(){
		return this.proId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品ID
	 */
	public void setProId(String proId){
		this.proId = proId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品类型
	 */

	@Column(name ="PRO_TYPE_NAME",nullable=true,length=32)
	public String getProTypeName(){
		return this.proTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品类型
	 */
	public void setProTypeName(String proTypeName){
		this.proTypeName = proTypeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检测人ID
	 */

	@Column(name ="CHECK_USER_ID",nullable=true,length=32)
	public String getCheckUserId(){
		return this.checkUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检测人ID
	 */
	public void setCheckUserId(String checkUserId){
		this.checkUserId = checkUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检测人
	 */

	@Column(name ="CHECK_USER_NAME",nullable=true,length=32)
	public String getCheckUserName(){
		return this.checkUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检测人
	 */
	public void setCheckUserName(String checkUserName){
		this.checkUserName = checkUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检测时间
	 */

	@Column(name ="CHECK_TIME",nullable=true,length=32)
	public String getCheckTime(){
		return this.checkTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检测时间
	 */
	public void setCheckTime(String checkTime){
		this.checkTime = checkTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检测情况
	 */

	@Column(name ="CHECK_CONTENT",nullable=true,length=512)
	public String getCheckContent(){
		return this.checkContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检测情况
	 */
	public void setCheckContent(String checkContent){
		this.checkContent = checkContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修人ID
	 */

	@Column(name ="REPAIR_USER_ID",nullable=true,length=32)
	public String getRepairUserId(){
		return this.repairUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修人ID
	 */
	public void setRepairUserId(String repairUserId){
		this.repairUserId = repairUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修人
	 */

	@Column(name ="REPAIR_USER_NAME",nullable=true,length=32)
	public String getRepairUserName(){
		return this.repairUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修人
	 */
	public void setRepairUserName(String repairUserName){
		this.repairUserName = repairUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修内容
	 */

	@Column(name ="REPAIR_CONTENT",nullable=true,length=32)
	public String getRepairContent(){
		return this.repairContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修内容
	 */
	public void setRepairContent(String repairContent){
		this.repairContent = repairContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  考核备注
	 */

	@Column(name ="KAOHE_REMARK",nullable=true,length=32)
	public String getKaoheRemark(){
		return this.kaoheRemark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  考核备注
	 */
	public void setKaoheRemark(String kaoheRemark){
		this.kaoheRemark = kaoheRemark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  考核时间
	 */

	@Column(name ="KAOHE_TIME",nullable=true,length=32)
	public String getKaoheTime(){
		return this.kaoheTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  考核时间
	 */
	public void setKaoheTime(String kaoheTime){
		this.kaoheTime = kaoheTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否自修
	 */

	@Column(name ="IS_FINISH",nullable=true,length=6)
	public String getIsFinish(){
		return this.isFinish;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否自修
	 */
	public void setIsFinish(String isFinish){
		this.isFinish = isFinish;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修类别
	 */

	@Column(name ="REPAIR_TYPE",nullable=true,length=6)
	public String getRepairType(){
		return this.repairType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修类别
	 */
	public void setRepairType(String repairType){
		this.repairType = repairType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报修方式
	 */

	@Column(name ="BX_TYPE",nullable=true,length=6)
	public String getBxType(){
		return this.bxType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修方式
	 */
	public void setBxType(String bxType){
		this.bxType = bxType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报修单号
	 */

	@Column(name ="REPAIR_NUM",nullable=true,length=32)
	public String getRepairNum(){
		return this.repairNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修单号
	 */
	public void setRepairNum(String repairNum){
		this.repairNum = repairNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  被考核人ID
	 */

	@Column(name ="KAOHE_USER_ID",nullable=true,length=32)
	public String getKaoheUserId(){
		return this.kaoheUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被考核人ID
	 */
	public void setKaoheUserId(String kaoheUserId){
		this.kaoheUserId = kaoheUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  被考核人
	 */

	@Column(name ="KAOHE_USER_NAME",nullable=true,length=32)
	public String getKaoheUserName(){
		return this.kaoheUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被考核人
	 */
	public void setKaoheUserName(String kaoheUserName){
		this.kaoheUserName = kaoheUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修时间
	 */

	@Column(name ="REPAIR_TIME",nullable=true,length=32)
	public String getRepairTime(){
		return this.repairTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修时间
	 */
	public void setRepairTime(String repairTime){
		this.repairTime = repairTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务商ID
	 */

	@Column(name ="CUS_ID",nullable=true,length=32)
	public String getCusId(){
		return this.cusId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务商ID
	 */
	public void setCusId(String cusId){
		this.cusId = cusId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务商
	 */

	@Column(name ="CUS_NAME",nullable=true,length=256)
	public String getCusName(){
		return this.cusName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务商
	 */
	public void setCusName(String cusName){
		this.cusName = cusName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  派单日期
	 */

	@Column(name ="SEND_DATAE",nullable=true,length=32)
	public String getSendDatae(){
		return this.sendDatae;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  派单日期
	 */
	public void setSendDatae(String sendDatae){
		this.sendDatae = sendDatae;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抢单状态
	 */

	@Column(name ="QD_STATUS",nullable=true,length=32)
	public String getQdStatus(){
		return this.qdStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抢单状态
	 */
	public void setQdStatus(String qdStatus){
		this.qdStatus = qdStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  接单人ID
	 */

	@Column(name ="RECEVIE_USER_ID",nullable=true,length=32)
	public String getRecevieUserId(){
		return this.recevieUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  接单人ID
	 */
	public void setRecevieUserId(String recevieUserId){
		this.recevieUserId = recevieUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  接单人
	 */

	@Column(name ="RECEVIE_USER_NAME",nullable=true,length=32)
	public String getRecevieUserName(){
		return this.recevieUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  接单人
	 */
	public void setRecevieUserName(String recevieUserName){
		this.recevieUserName = recevieUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  接单时间
	 */

	@Column(name ="RECEVIE_DATE",nullable=true,length=32)
	public String getRecevieDate(){
		return this.recevieDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  接单时间
	 */
	public void setRecevieDate(String recevieDate){
		this.recevieDate = recevieDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="RECEVIE_REMARK",nullable=true,length=512)
	public String getRecevieRemark(){
		return this.recevieRemark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRecevieRemark(String recevieRemark){
		this.recevieRemark = recevieRemark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理类型
	 */

	@Column(name ="CS_TYPE",nullable=true,length=32)
	public String getCsType(){
		return this.csType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理类型
	 */
	public void setCsType(String csType){
		this.csType = csType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务台处理意见
	 */

	@Column(name ="SERVICE_CONTENT",nullable=true,length=32)
	public String getServiceContent(){
		return this.serviceContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务台处理意见
	 */
	public void setServiceContent(String serviceContent){
		this.serviceContent = serviceContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务台处理人ID
	 */

	@Column(name ="SERVICE_USER_ID",nullable=true,length=32)
	public String getServiceUserId(){
		return this.serviceUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务台处理人ID
	 */
	public void setServiceUserId(String serviceUserId){
		this.serviceUserId = serviceUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务台处理人
	 */

	@Column(name ="SERVICE_USER_NAME",nullable=true,length=32)
	public String getServiceUserName(){
		return this.serviceUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务台处理人
	 */
	public void setServiceUserName(String serviceUserName){
		this.serviceUserName = serviceUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务商处理意见
	 */

	@Column(name ="CUS_CONTENT",nullable=true,length=32)
	public String getCusContent(){
		return this.cusContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务商处理意见
	 */
	public void setCusContent(String cusContent){
		this.cusContent = cusContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本地服务商处理意见
	 */

	@Column(name ="LOCAL_CONTENT",nullable=true,length=32)
	public String getLocalContent(){
		return this.localContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本地服务商处理意见
	 */
	public void setLocalContent(String localContent){
		this.localContent = localContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  紧急度
	 */

	@Column(name ="JJD",nullable=true,length=6)
	public String getJjd(){
		return this.jjd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  紧急度
	 */
	public void setJjd(String jjd){
		this.jjd = jjd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备类型
	 */
	@Formula("(select p.name from t_s_category p where p.code = sblx)")
	@Column(name ="SBLX",nullable=true,length=32)
	public String getSblx(){
		return this.sblx;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备类型
	 */
	public void setSblx(String sblx){
		this.sblx = sblx;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌
	 */
	@Formula("(select p.name from t_s_category p where p.code = pp)")
	@Column(name ="PP",nullable=true,length=32)
	public String getPp(){
		return this.pp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌
	 */
	public void setPp(String pp){
		this.pp = pp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  型号
	 */
	@Formula("(select p.name from t_s_category p where p.code = xh)")
	@Column(name ="XH",nullable=true,length=32)
	public String getXh(){
		return this.xh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  型号
	 */
	public void setXh(String xh){
		this.xh = xh;
	}

	@Column(name ="address",nullable=true,length=256)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name ="process_value",nullable=true,length=256)
	public String getProcessValue() {
		return processValue;
	}

	public void setProcessValue(String processValue) {
		this.processValue = processValue;
	}
}
