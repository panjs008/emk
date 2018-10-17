package com.emk.storage.enquiry.entity;

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
@Table(name = "emk_enquiry", schema = "")
public class EmkEnquiryEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "意向订单号", width = 15)
    private String enquiryNo;
    @Excel(name="是否先打样",width=15)
    private String isPrint;
    @Excel(name="询盘状态",width=15)
    private String state;
    @Excel(name = "提交日期", width = 15)
    private String kdDate;
    @Excel(name = "业务员", width = 15)
    private String businesser;
    private String businesserName;
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;
    @Excel(name = "工艺种类", width = 15)
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类", width = 15)
    private String proTypeName;
    private String customSampleUrl;
    @Excel(name = "图片", width = 15)
    private String customSample;
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "总数量", width = 15)
    private Integer sumTotal;
    @Excel(name = "币种", width = 15)
    private String bz;
    @Excel(name = "总金额", width = 15)
    private Double sumMoney;
    @Excel(name = "是否打过初样", width = 15)
    private String isPrintSample;
    @Excel(name = "是否收取打样费", width = 15)
    private String isGetSample;
    @Excel(name = "是否有原样", width = 15)
    private String isHaveOld;
    private String oldImageUrl;
    @Excel(name = "原样", width = 15)
    private String oldImage;
    @Excel(name = "是否有设计稿", width = 15)
    private String isHaveDgr;
    private String dgrImageUrl;
    @Excel(name = "计稿", width = 15)
    private String dgrImage;
    @Excel(name = "是否有尺寸表", width = 15)
    private String isHaveSize;
    private String sizeImageUrl;
    @Excel(name = "尺寸表", width = 15)
    private String sizeImage;
    @Excel(name = "意向货交期", width = 15)
    private String ysDate;
    @Excel(name = "距交期余天数", width = 15)
    private Integer levelDays;
    @Excel(name = "备注", width = 15)
    private String remark;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    private String developerName;
    @Excel(name = "业务部门", width = 15)
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "审核意见", width = 15)
    private String leadAdvice;
    @Excel(name = "处理意见", width = 15)
    private String financeAdvice;
    @Excel(name = "是否通过", width = 15)
    private String isPass;
    @Excel(name = "审核人ID", width = 15)
    private String leadUserId;
    @Excel(name = "审核人", width = 15)
    private String leader;
    @Excel(name = "财务处理人ID", width = 15)
    private String financeUserId;
    @Excel(name = "财务处理人", width = 15)
    private String financer;

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

    @Column(name = "ENQUIRY_NO", nullable = true, length = 32)
    public String getEnquiryNo() {
        return this.enquiryNo;
    }

    public void setEnquiryNo(String enquiryNo) {
        this.enquiryNo = enquiryNo;
    }

    @Column(name ="IS_PRINT",nullable=true,length=32)
    public String getIsPrint(){
        return this.isPrint;
    }

    @Column(name ="STATE",nullable=true,length=32)
    public String getState(){
        return this.state;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  工单状态
     */
    public void setState(String state){
        this.state = state;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  是否先打样
     */
    public void setIsPrint(String isPrint){
        this.isPrint = isPrint;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
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

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "SUM_TOTAL", nullable = true, length = 32)
    public Integer getSumTotal() {
        return this.sumTotal;
    }

    public void setSumTotal(Integer sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Column(name = "BZ", nullable = true, length = 12)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "SUM_MONEY", nullable = true, scale = 2, length = 32)
    public Double getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "IS_PRINT_SAMPLE", nullable = true, length = 6)
    public String getIsPrintSample() {
        return this.isPrintSample;
    }

    public void setIsPrintSample(String isPrintSample) {
        this.isPrintSample = isPrintSample;
    }

    @Column(name = "IS_GET_SAMPLE", nullable = true, length = 6)
    public String getIsGetSample() {
        return this.isGetSample;
    }

    public void setIsGetSample(String isGetSample) {
        this.isGetSample = isGetSample;
    }

    @Column(name = "IS_HAVE_OLD", nullable = true, length = 6)
    public String getIsHaveOld() {
        return this.isHaveOld;
    }

    public void setIsHaveOld(String isHaveOld) {
        this.isHaveOld = isHaveOld;
    }

    @Column(name = "OLD_IMAGE_URL", nullable = true, length = 256)
    public String getOldImageUrl() {
        return this.oldImageUrl;
    }

    public void setOldImageUrl(String oldImageUrl) {
        this.oldImageUrl = oldImageUrl;
    }

    @Column(name = "OLD_IMAGE", nullable = true, length = 32)
    public String getOldImage() {
        return this.oldImage;
    }

    public void setOldImage(String oldImage) {
        this.oldImage = oldImage;
    }

    @Column(name = "IS_HAVE_DGR", nullable = true, length = 6)
    public String getIsHaveDgr() {
        return this.isHaveDgr;
    }

    public void setIsHaveDgr(String isHaveDgr) {
        this.isHaveDgr = isHaveDgr;
    }

    @Column(name = "DGR_IMAGE_URL", nullable = true, length = 256)
    public String getDgrImageUrl() {
        return this.dgrImageUrl;
    }

    public void setDgrImageUrl(String dgrImageUrl) {
        this.dgrImageUrl = dgrImageUrl;
    }

    @Column(name = "DGR_IMAGE", nullable = true, length = 32)
    public String getDgrImage() {
        return this.dgrImage;
    }

    public void setDgrImage(String dgrImage) {
        this.dgrImage = dgrImage;
    }

    @Column(name = "IS_HAVE_SIZE", nullable = true, length = 6)
    public String getIsHaveSize() {
        return this.isHaveSize;
    }

    public void setIsHaveSize(String isHaveSize) {
        this.isHaveSize = isHaveSize;
    }

    @Column(name = "SIZE_IMAGE_URL", nullable = true, length = 256)
    public String getSizeImageUrl() {
        return this.sizeImageUrl;
    }

    public void setSizeImageUrl(String sizeImageUrl) {
        this.sizeImageUrl = sizeImageUrl;
    }

    @Column(name = "SIZE_IMAGE", nullable = true, length = 32)
    public String getSizeImage() {
        return this.sizeImage;
    }

    public void setSizeImage(String sizeImage) {
        this.sizeImage = sizeImage;
    }

    @Column(name = "YS_DATE", nullable = true, length = 32)
    public String getYsDate() {
        return this.ysDate;
    }

    public void setYsDate(String ysDate) {
        this.ysDate = ysDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Column(name = "LEAD_ADVICE", nullable = true, length = 256)
    public String getLeadAdvice() {
        return this.leadAdvice;
    }

    public void setLeadAdvice(String leadAdvice) {
        this.leadAdvice = leadAdvice;
    }

    @Column(name = "FINANCE_ADVICE", nullable = true, length = 256)
    public String getFinanceAdvice() {
        return this.financeAdvice;
    }

    public void setFinanceAdvice(String financeAdvice) {
        this.financeAdvice = financeAdvice;
    }

    @Column(name = "IS_PASS", nullable = true, length = 32)
    public String getIsPass() {
        return this.isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    @Column(name = "LEAD_USER_ID", nullable = true, length = 32)
    public String getLeadUserId() {
        return this.leadUserId;
    }

    public void setLeadUserId(String leadUserId) {
        this.leadUserId = leadUserId;
    }

    @Column(name = "LEADER", nullable = true, length = 32)
    public String getLeader() {
        return this.leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Column(name = "FINANCE_USER_ID", nullable = true, length = 32)
    public String getFinanceUserId() {
        return this.financeUserId;
    }

    public void setFinanceUserId(String financeUserId) {
        this.financeUserId = financeUserId;
    }

    @Column(name = "FINANCER", nullable = true, length = 32)
    public String getFinancer() {
        return this.financer;
    }

    public void setFinancer(String financer) {
        this.financer = financer;
    }
}
