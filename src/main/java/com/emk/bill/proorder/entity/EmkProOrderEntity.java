package com.emk.bill.proorder.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_pro_order", schema = "")
public class EmkProOrderEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    private String state;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "开单时间")
    private String orderTime;
    @Excel(name = "客户编号")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "交货时间")
    private String supplyTime;
    @Excel(name = "业务员")
    private String businesser;
    @Excel(name = "业务员ID")
    private String businesserName;
    @Excel(name = "业务跟单员")
    private String tracer;
    @Excel(name = "业务跟单员ID")
    private String tracerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    @Excel(name = "业务部门ID")
    private String businesseDeptId;
    @Excel(name = "生产跟单员")
    private String developer;
    @Excel(name = "生产跟单员ID")
    private String developerName;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "款式大类")
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "图片")
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "总数量")
    private Integer sumTotal;
    @Excel(name = "总金额")
    private Double sumMoney;
    @Excel(name = "尺码范围")
    private String sizeFw;
    @Excel(name = "单价")
    private Double price;
    @Excel(name = "币种")
    private String bz;
    @Excel(name = "交期")
    private String recevieDate;
    @Excel(name = "单件")
    private String one;
    @Excel(name = "胶袋")
    private String polybag;
    @Excel(name = "总箱数")
    private Integer sumBoxTotal;
    @Excel(name = "总体积")
    private Double sumBoxVolume;
    @Excel(name = "总净重")
    private Double sumBoxJz;
    @Excel(name = "总毛重")
    private Double sumBoxMao;
    @Excel(name = "装箱")
    private String boxup;
    @Excel(name = "包装效果图")
    private String boxImageUrl;
    @Excel(name = "包装效果图")
    private String boxImage;
    @Excel(name = "中期验货日期")
    private String zqyhDate;
    @Excel(name = "尾期验货日期")
    private String wqyhDate;
    @Excel(name = "船样状态")
    private String cystate;
    @Excel(name = "出厂日期")
    private String outDate;
    @Excel(name = "距交期剩余天数")
    private Integer levelDays;
    @Excel(name = "包装方式")
    private String bzfs;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商Code")
    private String gysCode;
    @Excel(name="审核意见")
    private String leadAdvice;
    @Excel(name="是否通过")
    private String isPass;
    private String leadUserId;
    @Excel(name="审核人")
    private String leader;
    private String orderType;
    private String workNo;

    @Column(name = "GYS", nullable = true, length = 32)
    public String getGys() {
        return this.gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    @Column(name = "GYS_CODE", nullable = true, length = 32)
    public String getGysCode() {
        return this.gysCode;
    }

    public void setGysCode(String gysCode) {
        this.gysCode = gysCode;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "ORDER_TIME", nullable = true, length = 32)
    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Column(name = "CUS_NUM", nullable = true, length = 32)
    public String getCusNum() {
        return this.cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    @Column(name = "CUS_NAME", nullable = true, length = 32)
    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Column(name = "REMARK", nullable = true, length = 56)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "SUPPLY_TIME", nullable = true, length = 32)
    public String getSupplyTime() {
        return this.supplyTime;
    }

    public void setSupplyTime(String supplyTime) {
        this.supplyTime = supplyTime;
    }

    @Column(name = "BUSINESSER", nullable = true, length = 32)
    public String getBusinesser() {
        return this.businesser;
    }

    public void setBusinesser(String businesser) {
        this.businesser = businesser;
    }

    @Column(name = "BUSINESSER_NAME", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
    }

    @Column(name = "TRACER", nullable = true, length = 32)
    public String getTracer() {
        return this.tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    @Column(name = "TRACER_NAME", nullable = true, length = 32)
    public String getTracerName() {
        return this.tracerName;
    }

    public void setTracerName(String tracerName) {
        this.tracerName = tracerName;
    }

    @Column(name = "BUSINESSE_DEPT_NAME", nullable = true, length = 32)
    public String getBusinesseDeptName() {
        return this.businesseDeptName;
    }

    public void setBusinesseDeptName(String businesseDeptName) {
        this.businesseDeptName = businesseDeptName;
    }

    @Column(name = "BUSINESSE_DEPT_ID", nullable = true, length = 32)
    public String getBusinesseDeptId() {
        return this.businesseDeptId;
    }

    public void setBusinesseDeptId(String businesseDeptId) {
        this.businesseDeptId = businesseDeptId;
    }

    @Column(name = "DEVELOPER", nullable = true, length = 32)
    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Column(name = "DEVELOPER_NAME", nullable = true, length = 32)
    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "PRO_TYPE_NAME", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 32)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "CUSTOM_SAMPLE_URL", nullable = true, length = 256)
    public String getCustomSampleUrl() {
        return this.customSampleUrl;
    }

    public void setCustomSampleUrl(String customSampleUrl) {
        this.customSampleUrl = customSampleUrl;
    }

    @Column(name = "CUSTOM_SAMPLE", nullable = true, length = 32)
    public String getCustomSample() {
        return this.customSample;
    }

    public void setCustomSample(String customSample) {
        this.customSample = customSample;
    }

    @Column(name = "SUM_TOTAL", nullable = true, length = 32)
    public Integer getSumTotal() {
        return this.sumTotal;
    }

    public void setSumTotal(Integer sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Column(name = "SUM_MONEY", nullable = true, scale = 2, length = 32)
    public Double getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "SIZE_FW", nullable = true, length = 32)
    public String getSizeFw() {
        return this.sizeFw;
    }

    public void setSizeFw(String sizeFw) {
        this.sizeFw = sizeFw;
    }

    @Column(name = "PRICE", nullable = true, scale = 2, length = 32)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "BZ", nullable = true, length = 32)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "RECEVIE_DATE", nullable = true, length = 32)
    public String getRecevieDate() {
        return this.recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }

    @Column(name = "ONE", nullable = true, length = 32)
    public String getOne() {
        return this.one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    @Column(name = "POLYBAG", nullable = true, length = 32)
    public String getPolybag() {
        return this.polybag;
    }

    public void setPolybag(String polybag) {
        this.polybag = polybag;
    }

    @Column(name = "SUM_BOX_TOTAL", nullable = true, length = 32)
    public Integer getSumBoxTotal() {
        return this.sumBoxTotal;
    }

    public void setSumBoxTotal(Integer sumBoxTotal) {
        this.sumBoxTotal = sumBoxTotal;
    }

    @Column(name = "SUM_BOX_VOLUME", nullable = true, scale = 2, length = 32)
    public Double getSumBoxVolume() {
        return this.sumBoxVolume;
    }

    public void setSumBoxVolume(Double sumBoxVolume) {
        this.sumBoxVolume = sumBoxVolume;
    }

    @Column(name = "SUM_BOX_JZ", nullable = true, scale = 2, length = 32)
    public Double getSumBoxJz() {
        return this.sumBoxJz;
    }

    public void setSumBoxJz(Double sumBoxJz) {
        this.sumBoxJz = sumBoxJz;
    }

    @Column(name = "SUM_BOX_MAO", nullable = true, scale = 2, length = 32)
    public Double getSumBoxMao() {
        return this.sumBoxMao;
    }

    public void setSumBoxMao(Double sumBoxMao) {
        this.sumBoxMao = sumBoxMao;
    }

    @Column(name = "BOXUP", nullable = true, length = 256)
    public String getBoxup() {
        return this.boxup;
    }

    public void setBoxup(String boxup) {
        this.boxup = boxup;
    }

    @Column(name = "BOX_IMAGE_URL", nullable = true, length = 256)
    public String getBoxImageUrl() {
        return this.boxImageUrl;
    }

    public void setBoxImageUrl(String boxImageUrl) {
        this.boxImageUrl = boxImageUrl;
    }

    @Column(name = "BOX_IMAGE", nullable = true, length = 32)
    public String getBoxImage() {
        return this.boxImage;
    }

    public void setBoxImage(String boxImage) {
        this.boxImage = boxImage;
    }

    @Column(name = "ZQYH_DATE", nullable = true, length = 32)
    public String getZqyhDate() {
        return this.zqyhDate;
    }

    public void setZqyhDate(String zqyhDate) {
        this.zqyhDate = zqyhDate;
    }

    @Column(name = "WQYH_DATE", nullable = true, length = 32)
    public String getWqyhDate() {
        return this.wqyhDate;
    }

    public void setWqyhDate(String wqyhDate) {
        this.wqyhDate = wqyhDate;
    }

    @Column(name = "CYSTATE", nullable = true, length = 32)
    public String getCystate() {
        return this.cystate;
    }

    public void setCystate(String cystate) {
        this.cystate = cystate;
    }

    @Column(name = "OUT_DATE", nullable = true, length = 32)
    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "BZFS", nullable = true, length = 32)
    public String getBzfs() {
        return this.bzfs;
    }

    public void setBzfs(String bzfs) {
        this.bzfs = bzfs;
    }

    @Column(name="LEAD_ADVICE", nullable=true, length=256)
    public String getLeadAdvice()
    {
        return this.leadAdvice;
    }

    public void setLeadAdvice(String leadAdvice)
    {
        this.leadAdvice = leadAdvice;
    }

    @Column(name="IS_PASS", nullable=true, length=32)
    public String getIsPass()
    {
        return this.isPass;
    }

    public void setIsPass(String isPass)
    {
        this.isPass = isPass;
    }

    @Column(name="LEAD_USER_ID", nullable=true, length=32)
    public String getLeadUserId()
    {
        return this.leadUserId;
    }

    public void setLeadUserId(String leadUserId)
    {
        this.leadUserId = leadUserId;
    }

    @Column(name="LEADER", nullable=true, length=32)
    public String getLeader()
    {
        return this.leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    @Column(name="order_type", nullable=true, length=32)
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Column(name ="WORK_NO",nullable=true,length=32)
    public String getWorkNo(){
        return this.workNo;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  工单编号
     */
    public void setWorkNo(String workNo){
        this.workNo = workNo;
    }
}
