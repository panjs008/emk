package com.emk.check.qualitycheck.entity;

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
 * @Description: 质量检查表
 * @author onlineGenerator
 * @date 2018-09-26 22:25:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_quality_check", schema = "")
@SuppressWarnings("serial")
public class EmkQualityCheckEntity implements java.io.Serializable {
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
	/**业务员ID*/
	private String businesserName;
	/**业务员*/
	@Excel(name="业务员",width=15)
	private String businesser;
	/**业务跟单员*/
	@Excel(name="业务跟单员",width=15)
	private String tracer;
	/**业务跟单员ID*/
	private String tracerName;
	/**业务部门*/
	@Excel(name="业务部门",width=15)
	private String businesseDeptName;
	/**业务部门ID*/
	private String businesseDeptId;
	/**生产跟单员*/
	@Excel(name="生产跟单员",width=15)
	private String developer;
	/**生产跟单员ID*/
	private String developerName;
	/**供应商名称*/
	@Excel(name="供应商名称",width=15)
	private String gys;
	/**供应商代码*/
	@Excel(name="供应商代码",width=15)
	private String gysCode;
	/**工厂名称*/
	@Excel(name="工厂名称",width=15)
	private String factoryName;
	/**工厂地址*/
	@Excel(name="工厂地址",width=15)
	private String address;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private String relationer;
	/**电话*/
	@Excel(name="电话",width=15)
	private String telphone;
	/**验货类型*/
	@Excel(name="验货类型",width=15)
	private String checkType;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private String orderNo;
	/**款号*/
	@Excel(name="款号",width=15)
	private String sampleNo;
	/**描述*/
	@Excel(name="描述",width=15)
	private String sampleDesc;
	/**颜色*/
	@Excel(name="颜色",width=15)
	private String color;
	/**尺码范围*/
	@Excel(name="尺码范围",width=15)
	private String size;
	/**总数量*/
	@Excel(name="总数量",width=15)
	private Integer total;
	/**总箱数*/
	@Excel(name="总箱数",width=15)
	private Integer boxTotal;
	/**合同号*/
	@Excel(name="合同号",width=15)
	private String htNum;
	/**版次*/
	@Excel(name="版次",width=15)
	private String version;
	/**抽检数量*/
	@Excel(name="抽检数量",width=15)
	private String checkTotal;
	/**污渍*/
	@Excel(name="污渍",width=15)
	private String wuji;
	/**油渍*/
	@Excel(name="油渍",width=15)
	private String youji;
	/**起毛*/
	@Excel(name="起毛",width=15)
	private String qimao;
	/**断纱*/
	@Excel(name="断纱",width=15)
	private String duans;
	/**抽纱*/
	@Excel(name="抽纱",width=15)
	private String chous;
	/**反纱*/
	@Excel(name="反纱",width=15)
	private String fans;
	/**破洞*/
	@Excel(name="破洞",width=15)
	private String pod;
	/**横纹*/
	@Excel(name="横纹",width=15)
	private String hengw;
	/**染花*/
	@Excel(name="染花",width=15)
	private String ranhua;
	/**色差*/
	@Excel(name="色差",width=15)
	private String secha;
	/**配色不良*/
	@Excel(name="配色不良",width=15)
	private String psbl;
	/**爆口*/
	@Excel(name="爆口",width=15)
	private String baokou;
	/**线头*/
	@Excel(name="线头",width=15)
	private String xt;
	/**断线*/
	@Excel(name="断线",width=15)
	private String dx;
	/**落坑*/
	@Excel(name="落坑",width=15)
	private String lk;
	/**左右不对称*/
	@Excel(name="左右不对称",width=15)
	private String zybdc;
	/**套结不良*/
	@Excel(name="套结不良",width=15)
	private String tjbl;
	/**容皱*/
	@Excel(name="容皱",width=15)
	private String rongzhou;
	/**止口*/
	@Excel(name="止口",width=15)
	private String zhikou;
	/**尺寸*/
	@Excel(name="尺寸",width=15)
	private String chicun;
	/**每寸针数*/
	@Excel(name="每寸针数",width=15)
	private String mczs;
	/**烫工不良*/
	@Excel(name="烫工不良",width=15)
	private String tgbl;
	/**骨位方向错误*/
	@Excel(name="骨位方向错误",width=15)
	private String gwfxcw;
	/**驳线不良*/
	@Excel(name="驳线不良",width=15)
	private String bxbl;
	/**形状不良*/
	@Excel(name="形状不良",width=15)
	private String xzbl;
	/**漏物料*/
	@Excel(name="漏物料",width=15)
	private String lwl;
	/**针孔*/
	@Excel(name="针孔",width=15)
	private String zhenkong;
	/**挂错漏打吊牌*/
	@Excel(name="挂错漏打吊牌",width=15)
	private String gcdp;
	/**错打漏打条形码*/
	@Excel(name="错打漏打条形码",width=15)
	private String cdtxm;
	/**挂牌位置错误*/
	@Excel(name="挂牌位置错误",width=15)
	private String gpwzcw;
	/**衣架用错*/
	@Excel(name="衣架用错",width=15)
	private String yjyc;
	/**衣架方向错误*/
	@Excel(name="衣架方向错误",width=15)
	private String yjfxcw;
	/**挂得过长*/
	@Excel(name="挂得过长",width=15)
	private String gdgc;
	/**错漏码数*/
	@Excel(name="错漏码数",width=15)
	private String clms;
	/**胶袋内尺码错误*/
	@Excel(name="胶袋内尺码错误",width=15)
	private String jdncmcw;
	/**胶袋内配比错误*/
	@Excel(name="胶袋内配比错误",width=15)
	private String jdnpbcw;
	/**纸箱杂码混色*/
	@Excel(name="纸箱杂码混色",width=15)
	private String zxzmhs;
	/**包装外观不良*/
	@Excel(name="包装外观不良",width=15)
	private String bzwgbl;
	/**条形码不能扫描*/
	@Excel(name="条形码不能扫描",width=15)
	private String txmbnsm;
	/**吊牌不稳*/
	@Excel(name="吊牌不稳",width=15)
	private String dpbw;
	/**贴纸不稳*/
	@Excel(name="贴纸不稳",width=15)
	private String tzbw;
	/**包装资料不一*/
	@Excel(name="包装资料不一",width=15)
	private String bzzlby;
	/**查验结果*/
	@Excel(name="查验结果",width=15)
	private String cyjg;
	/**质量检查编号*/
	@Excel(name="质量检查编号",width=15)
	private String qualityCheckNum;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private String cusName;
	/**客户代码*/
	@Excel(name="客户代码",width=15)
	private String cusNum;
	/**报告日期*/
	@Excel(name="报告日期",width=15)
	private String kdDate;
	/**检查日期*/
	@Excel(name="检查日期",width=15)
	private String checkDate;
	
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
	 *@return: java.lang.String  业务员ID
	 */

	@Column(name ="BUSINESSER_NAME",nullable=true,length=32)
	public String getBusinesserName(){
		return this.businesserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员ID
	 */
	public void setBusinesserName(String businesserName){
		this.businesserName = businesserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */

	@Column(name ="BUSINESSER",nullable=true,length=32)
	public String getBusinesser(){
		return this.businesser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员
	 */
	public void setBusinesser(String businesser){
		this.businesser = businesser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务跟单员
	 */

	@Column(name ="TRACER",nullable=true,length=32)
	public String getTracer(){
		return this.tracer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务跟单员
	 */
	public void setTracer(String tracer){
		this.tracer = tracer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务跟单员ID
	 */

	@Column(name ="TRACER_NAME",nullable=true,length=32)
	public String getTracerName(){
		return this.tracerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务跟单员ID
	 */
	public void setTracerName(String tracerName){
		this.tracerName = tracerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务部门
	 */

	@Column(name ="BUSINESSE_DEPT_NAME",nullable=true,length=32)
	public String getBusinesseDeptName(){
		return this.businesseDeptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务部门
	 */
	public void setBusinesseDeptName(String businesseDeptName){
		this.businesseDeptName = businesseDeptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务部门ID
	 */

	@Column(name ="BUSINESSE_DEPT_ID",nullable=true,length=32)
	public String getBusinesseDeptId(){
		return this.businesseDeptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务部门ID
	 */
	public void setBusinesseDeptId(String businesseDeptId){
		this.businesseDeptId = businesseDeptId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产跟单员
	 */

	@Column(name ="DEVELOPER",nullable=true,length=32)
	public String getDeveloper(){
		return this.developer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产跟单员
	 */
	public void setDeveloper(String developer){
		this.developer = developer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产跟单员ID
	 */

	@Column(name ="DEVELOPER_NAME",nullable=true,length=32)
	public String getDeveloperName(){
		return this.developerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产跟单员ID
	 */
	public void setDeveloperName(String developerName){
		this.developerName = developerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商名称
	 */

	@Column(name ="GYS",nullable=true,length=32)
	public String getGys(){
		return this.gys;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
	 */
	public void setGys(String gys){
		this.gys = gys;
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
	 *@return: java.lang.String  工厂名称
	 */

	@Column(name ="FACTORY_NAME",nullable=true,length=32)
	public String getFactoryName(){
		return this.factoryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂名称
	 */
	public void setFactoryName(String factoryName){
		this.factoryName = factoryName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工厂地址
	 */

	@Column(name ="ADDRESS",nullable=true,length=256)
	public String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工厂地址
	 */
	public void setAddress(String address){
		this.address = address;
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
	 *@return: java.lang.String  电话
	 */

	@Column(name ="TELPHONE",nullable=true,length=32)
	public String getTelphone(){
		return this.telphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验货类型
	 */

	@Column(name ="CHECK_TYPE",nullable=true,length=32)
	public String getCheckType(){
		return this.checkType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验货类型
	 */
	public void setCheckType(String checkType){
		this.checkType = checkType;
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

	@Column(name ="SAMPLE_DESC",nullable=true,length=32)
	public String getSampleDesc(){
		return this.sampleDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setSampleDesc(String sampleDesc){
		this.sampleDesc = sampleDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  颜色
	 */

	@Column(name ="COLOR",nullable=true,length=32)
	public String getColor(){
		return this.color;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  颜色
	 */
	public void setColor(String color){
		this.color = color;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尺码范围
	 */

	@Column(name ="SIZE",nullable=true,length=32)
	public String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尺码范围
	 */
	public void setSize(String size){
		this.size = size;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public Integer getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总数量
	 */
	public void setTotal(Integer total){
		this.total = total;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总箱数
	 */

	@Column(name ="BOX_TOTAL",nullable=true,length=32)
	public Integer getBoxTotal(){
		return this.boxTotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总箱数
	 */
	public void setBoxTotal(Integer boxTotal){
		this.boxTotal = boxTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同号
	 */

	@Column(name ="HT_NUM",nullable=true,length=32)
	public String getHtNum(){
		return this.htNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同号
	 */
	public void setHtNum(String htNum){
		this.htNum = htNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版次
	 */

	@Column(name ="VERSION",nullable=true,length=32)
	public String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版次
	 */
	public void setVersion(String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抽检数量
	 */

	@Column(name ="CHECK_TOTAL",nullable=true,length=32)
	public String getCheckTotal(){
		return this.checkTotal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抽检数量
	 */
	public void setCheckTotal(String checkTotal){
		this.checkTotal = checkTotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  污渍
	 */

	@Column(name ="WUJI",nullable=true,length=32)
	public String getWuji(){
		return this.wuji;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  污渍
	 */
	public void setWuji(String wuji){
		this.wuji = wuji;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  油渍
	 */

	@Column(name ="YOUJI",nullable=true,length=32)
	public String getYouji(){
		return this.youji;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  油渍
	 */
	public void setYouji(String youji){
		this.youji = youji;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  起毛
	 */

	@Column(name ="QIMAO",nullable=true,length=32)
	public String getQimao(){
		return this.qimao;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  起毛
	 */
	public void setQimao(String qimao){
		this.qimao = qimao;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  断纱
	 */

	@Column(name ="DUANS",nullable=true,length=32)
	public String getDuans(){
		return this.duans;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  断纱
	 */
	public void setDuans(String duans){
		this.duans = duans;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抽纱
	 */

	@Column(name ="CHOUS",nullable=true,length=32)
	public String getChous(){
		return this.chous;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抽纱
	 */
	public void setChous(String chous){
		this.chous = chous;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  反纱
	 */

	@Column(name ="FANS",nullable=true,length=32)
	public String getFans(){
		return this.fans;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  反纱
	 */
	public void setFans(String fans){
		this.fans = fans;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  破洞
	 */

	@Column(name ="POD",nullable=true,length=32)
	public String getPod(){
		return this.pod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  破洞
	 */
	public void setPod(String pod){
		this.pod = pod;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  横纹
	 */

	@Column(name ="HENGW",nullable=true,length=32)
	public String getHengw(){
		return this.hengw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  横纹
	 */
	public void setHengw(String hengw){
		this.hengw = hengw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  染花
	 */

	@Column(name ="RANHUA",nullable=true,length=32)
	public String getRanhua(){
		return this.ranhua;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  染花
	 */
	public void setRanhua(String ranhua){
		this.ranhua = ranhua;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  色差
	 */

	@Column(name ="SECHA",nullable=true,length=32)
	public String getSecha(){
		return this.secha;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  色差
	 */
	public void setSecha(String secha){
		this.secha = secha;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  配色不良
	 */

	@Column(name ="PSBL",nullable=true,length=32)
	public String getPsbl(){
		return this.psbl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  配色不良
	 */
	public void setPsbl(String psbl){
		this.psbl = psbl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  爆口
	 */

	@Column(name ="BAOKOU",nullable=true,length=32)
	public String getBaokou(){
		return this.baokou;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  爆口
	 */
	public void setBaokou(String baokou){
		this.baokou = baokou;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  线头
	 */

	@Column(name ="XT",nullable=true,length=32)
	public String getXt(){
		return this.xt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  线头
	 */
	public void setXt(String xt){
		this.xt = xt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  断线
	 */

	@Column(name ="DX",nullable=true,length=32)
	public String getDx(){
		return this.dx;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  断线
	 */
	public void setDx(String dx){
		this.dx = dx;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  落坑
	 */

	@Column(name ="LK",nullable=true,length=32)
	public String getLk(){
		return this.lk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  落坑
	 */
	public void setLk(String lk){
		this.lk = lk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  左右不对称
	 */

	@Column(name ="ZYBDC",nullable=true,length=32)
	public String getZybdc(){
		return this.zybdc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  左右不对称
	 */
	public void setZybdc(String zybdc){
		this.zybdc = zybdc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  套结不良
	 */

	@Column(name ="TJBL",nullable=true,length=32)
	public String getTjbl(){
		return this.tjbl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  套结不良
	 */
	public void setTjbl(String tjbl){
		this.tjbl = tjbl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  容皱
	 */

	@Column(name ="RONGZHOU",nullable=true,length=32)
	public String getRongzhou(){
		return this.rongzhou;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  容皱
	 */
	public void setRongzhou(String rongzhou){
		this.rongzhou = rongzhou;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  止口
	 */

	@Column(name ="ZHIKOU",nullable=true,length=32)
	public String getZhikou(){
		return this.zhikou;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  止口
	 */
	public void setZhikou(String zhikou){
		this.zhikou = zhikou;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  尺寸
	 */

	@Column(name ="CHICUN",nullable=true,length=32)
	public String getChicun(){
		return this.chicun;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  尺寸
	 */
	public void setChicun(String chicun){
		this.chicun = chicun;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  每寸针数
	 */

	@Column(name ="MCZS",nullable=true,length=32)
	public String getMczs(){
		return this.mczs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  每寸针数
	 */
	public void setMczs(String mczs){
		this.mczs = mczs;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  烫工不良
	 */

	@Column(name ="TGBL",nullable=true,length=32)
	public String getTgbl(){
		return this.tgbl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  烫工不良
	 */
	public void setTgbl(String tgbl){
		this.tgbl = tgbl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  骨位方向错误
	 */

	@Column(name ="GWFXCW",nullable=true,length=32)
	public String getGwfxcw(){
		return this.gwfxcw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  骨位方向错误
	 */
	public void setGwfxcw(String gwfxcw){
		this.gwfxcw = gwfxcw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  驳线不良
	 */

	@Column(name ="BXBL",nullable=true,length=32)
	public String getBxbl(){
		return this.bxbl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  驳线不良
	 */
	public void setBxbl(String bxbl){
		this.bxbl = bxbl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  形状不良
	 */

	@Column(name ="XZBL",nullable=true,length=32)
	public String getXzbl(){
		return this.xzbl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  形状不良
	 */
	public void setXzbl(String xzbl){
		this.xzbl = xzbl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  漏物料
	 */

	@Column(name ="LWL",nullable=true,length=32)
	public String getLwl(){
		return this.lwl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  漏物料
	 */
	public void setLwl(String lwl){
		this.lwl = lwl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  针孔
	 */

	@Column(name ="ZHENKONG",nullable=true,length=32)
	public String getZhenkong(){
		return this.zhenkong;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  针孔
	 */
	public void setZhenkong(String zhenkong){
		this.zhenkong = zhenkong;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  挂错漏打吊牌
	 */

	@Column(name ="GCDP",nullable=true,length=32)
	public String getGcdp(){
		return this.gcdp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  挂错漏打吊牌
	 */
	public void setGcdp(String gcdp){
		this.gcdp = gcdp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  错打漏打条形码
	 */

	@Column(name ="CDTXM",nullable=true,length=32)
	public String getCdtxm(){
		return this.cdtxm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  错打漏打条形码
	 */
	public void setCdtxm(String cdtxm){
		this.cdtxm = cdtxm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  挂牌位置错误
	 */

	@Column(name ="GPWZCW",nullable=true,length=32)
	public String getGpwzcw(){
		return this.gpwzcw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  挂牌位置错误
	 */
	public void setGpwzcw(String gpwzcw){
		this.gpwzcw = gpwzcw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  衣架用错
	 */

	@Column(name ="YJYC",nullable=true,length=32)
	public String getYjyc(){
		return this.yjyc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  衣架用错
	 */
	public void setYjyc(String yjyc){
		this.yjyc = yjyc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  衣架方向错误
	 */

	@Column(name ="YJFXCW",nullable=true,length=32)
	public String getYjfxcw(){
		return this.yjfxcw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  衣架方向错误
	 */
	public void setYjfxcw(String yjfxcw){
		this.yjfxcw = yjfxcw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  挂得过长
	 */

	@Column(name ="GDGC",nullable=true,length=32)
	public String getGdgc(){
		return this.gdgc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  挂得过长
	 */
	public void setGdgc(String gdgc){
		this.gdgc = gdgc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  错漏码数
	 */

	@Column(name ="CLMS",nullable=true,length=32)
	public String getClms(){
		return this.clms;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  错漏码数
	 */
	public void setClms(String clms){
		this.clms = clms;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  胶袋内尺码错误
	 */

	@Column(name ="JDNCMCW",nullable=true,length=32)
	public String getJdncmcw(){
		return this.jdncmcw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  胶袋内尺码错误
	 */
	public void setJdncmcw(String jdncmcw){
		this.jdncmcw = jdncmcw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  胶袋内配比错误
	 */

	@Column(name ="JDNPBCW",nullable=true,length=32)
	public String getJdnpbcw(){
		return this.jdnpbcw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  胶袋内配比错误
	 */
	public void setJdnpbcw(String jdnpbcw){
		this.jdnpbcw = jdnpbcw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纸箱杂码混色
	 */

	@Column(name ="ZXZMHS",nullable=true,length=32)
	public String getZxzmhs(){
		return this.zxzmhs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纸箱杂码混色
	 */
	public void setZxzmhs(String zxzmhs){
		this.zxzmhs = zxzmhs;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装外观不良
	 */

	@Column(name ="BZWGBL",nullable=true,length=32)
	public String getBzwgbl(){
		return this.bzwgbl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装外观不良
	 */
	public void setBzwgbl(String bzwgbl){
		this.bzwgbl = bzwgbl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  条形码不能扫描
	 */

	@Column(name ="TXMBNSM",nullable=true,length=32)
	public String getTxmbnsm(){
		return this.txmbnsm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  条形码不能扫描
	 */
	public void setTxmbnsm(String txmbnsm){
		this.txmbnsm = txmbnsm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  吊牌不稳
	 */

	@Column(name ="DPBW",nullable=true,length=32)
	public String getDpbw(){
		return this.dpbw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  吊牌不稳
	 */
	public void setDpbw(String dpbw){
		this.dpbw = dpbw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  贴纸不稳
	 */

	@Column(name ="TZBW",nullable=true,length=32)
	public String getTzbw(){
		return this.tzbw;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  贴纸不稳
	 */
	public void setTzbw(String tzbw){
		this.tzbw = tzbw;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装资料不一
	 */

	@Column(name ="BZZLBY",nullable=true,length=32)
	public String getBzzlby(){
		return this.bzzlby;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装资料不一
	 */
	public void setBzzlby(String bzzlby){
		this.bzzlby = bzzlby;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  查验结果
	 */

	@Column(name ="CYJG",nullable=true,length=256)
	public String getCyjg(){
		return this.cyjg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  查验结果
	 */
	public void setCyjg(String cyjg){
		this.cyjg = cyjg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质量检查编号
	 */

	@Column(name ="QUALITY_CHECK_NUM",nullable=true,length=32)
	public String getQualityCheckNum(){
		return this.qualityCheckNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质量检查编号
	 */
	public void setQualityCheckNum(String qualityCheckNum){
		this.qualityCheckNum = qualityCheckNum;
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
	 *@return: java.lang.String  报告日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报告日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查日期
	 */

	@Column(name ="CHECK_DATE",nullable=true,length=32)
	public String getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查日期
	 */
	public void setCheckDate(String checkDate){
		this.checkDate = checkDate;
	}
}
