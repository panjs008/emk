package com.emk.storage.pack.entity;

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
@Table(name = "emk_pack", schema = "")
public class EmkPackEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "包装辅料需求单号")
    private String parkNo;
    @Excel(name = "提交日期")
    private String kdDate;
    @Excel(name = "业务跟单员")
    private String businesser;
    private String businesserName;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "工艺种类")
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "是否打过初样")
    private String isPrintSample;
    @Excel(name = "是否有原样")
    private String isHaveOld;
    private String oldImageUrl;
    @Excel(name = "原样")
    private String oldImage;
    @Excel(name = "是否有设计稿")
    private String isHaveDgr;
    private String dgrImageUrl;
    @Excel(name = "计稿")
    private String dgrImage;
    @Excel(name = "意向货交期")
    private String ysDate;
    @Excel(name = "距交期余天数")
    private Integer levelDays;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "生产合同号")
    private String productHtNo;
    @Excel(name = "布面成分")
    private String chengf;
    @Excel(name = "布面克重")
    private String weight;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "目标价位")
    private Double targetPrice;
    @Excel(name = "预计数量")
    private Integer total;
    @Excel(name = "开发完成交期")
    private String endDate;
    @Excel(name = "客户意见")
    private String cusAdvice;
    @Excel(name = "业务意见")
    private String yeAdvice;
    @Excel(name = "采购部意见")
    private String cgAdvice;
    @Excel(name = "是否参考图片")
    private String isCkImage;
    private String ckImageUrl;
    @Excel(name = "参考图片")
    private String ckImage;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;

    @Excel(name = "审核意见")
    private String leadAdvice;
    private String isPass;
    private String leadUserId;
    @Excel(name = "审核人")
    private String leader;
    private String state;

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

    @Column(name = "PARK_NO", nullable = true, length = 32)
    public String getParkNo() {
        return this.parkNo;
    }

    public void setParkNo(String parkNo) {
        this.parkNo = parkNo;
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

    @Column(name = "IS_PRINT_SAMPLE", nullable = true, length = 6)
    public String getIsPrintSample() {
        return this.isPrintSample;
    }

    public void setIsPrintSample(String isPrintSample) {
        this.isPrintSample = isPrintSample;
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

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "PRODUCT_HT_NO", nullable = true, length = 32)
    public String getProductHtNo() {
        return this.productHtNo;
    }

    public void setProductHtNo(String productHtNo) {
        this.productHtNo = productHtNo;
    }

    @Column(name = "CHENGF", nullable = true, length = 32)
    public String getChengf() {
        return this.chengf;
    }

    public void setChengf(String chengf) {
        this.chengf = chengf;
    }

    @Column(name = "WEIGHT", nullable = true, length = 32)
    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "BRAND", nullable = true, length = 32)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "TARGET_PRICE", nullable = true, scale = 2, length = 32)
    public Double getTargetPrice() {
        return this.targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "END_DATE", nullable = true, length = 32)
    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Column(name = "CUS_ADVICE", nullable = true, length = 256)
    public String getCusAdvice() {
        return this.cusAdvice;
    }

    public void setCusAdvice(String cusAdvice) {
        this.cusAdvice = cusAdvice;
    }

    @Column(name = "YE_ADVICE", nullable = true, length = 256)
    public String getYeAdvice() {
        return this.yeAdvice;
    }

    public void setYeAdvice(String yeAdvice) {
        this.yeAdvice = yeAdvice;
    }

    @Column(name = "CG_ADVICE", nullable = true, length = 256)
    public String getCgAdvice() {
        return this.cgAdvice;
    }

    public void setCgAdvice(String cgAdvice) {
        this.cgAdvice = cgAdvice;
    }

    @Column(name = "IS_CK_IMAGE", nullable = true, length = 32)
    public String getIsCkImage() {
        return this.isCkImage;
    }

    public void setIsCkImage(String isCkImage) {
        this.isCkImage = isCkImage;
    }

    @Column(name = "CK_IMAGE_URL", nullable = true, length = 256)
    public String getCkImageUrl() {
        return this.ckImageUrl;
    }

    public void setCkImageUrl(String ckImageUrl) {
        this.ckImageUrl = ckImageUrl;
    }

    @Column(name = "CK_IMAGE_", nullable = true, length = 32)
    public String getCkImage() {
        return this.ckImage;
    }

    public void setCkImage(String ckImage) {
        this.ckImage = ckImage;
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
}
