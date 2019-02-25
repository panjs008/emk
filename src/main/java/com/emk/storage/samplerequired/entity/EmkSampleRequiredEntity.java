package com.emk.storage.samplerequired.entity;

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
@Table(name = "emk_sample_required", schema = "")
public class EmkSampleRequiredEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "需求单号")
    private String requiredNo;
    @Excel(name = "提交日期")
    private String kdDate;
    @Excel(name = "业务员")
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
    @Excel(name = "图片")
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "是否收取打样费")
    private String isGetSample;
    @Excel(name = "是否有原样")
    private String isHaveOld;
    private String oldImageUrl;
    @Excel(name = "原样")
    private String oldImage;
    @Excel(name = "是否有设计稿")
    private String isHaveDgr;
    private String dgrImageUrl;
    @Excel(name = "设计稿")
    private String dgrImage;
    @Excel(name = "是否有尺寸表")
    private String isHaveSize;
    private String sizeImageUrl;
    @Excel(name = "尺寸表")
    private String sizeImage;
    @Excel(name = "是否有色样")
    private String isHaveColor;
    private String colorImageUrl;
    @Excel(name = "色样")
    private String colorImage;
    @Excel(name = "是否有色号")
    private String isHaveColorNum;
    private String colorNumImageUrl;
    @Excel(name = "色号")
    private String colorNumImage;
    @Excel(name = "是否有意向订单")
    private String isEnquiry;
    @Excel(name = "意向订单号")
    private String enquiryNo;
    @Excel(name = "大货交期")
    private String dhjq;
    @Excel(name = "距大货交期余天数")
    private String leaveldhjqDays;
    @Excel(name = "是否有大货")
    private String isDh;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "大货交期")
    private String dhjq2;
    @Excel(name = "距大货交期余天数")
    private String leaveldhjq2Days;
    @Excel(name = "是否有过预报价")
    private String isPrice;
    @Excel(name = "报价单号")
    private String priceNo;
    @Excel(name = "意向货交期")
    private String ysDate;
    @Excel(name = "距交期余天数")
    private String levelDays;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "布面克重")
    private String weight;
    @Excel(name = "布面成分")
    private String chengf;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "染色要求")
    private String ranRequired;
    @Excel(name = "缝制要求")
    private String fengRequired;
    @Excel(name = "状态")
    private String state;
    @Excel(name = "样品去向")
    private String sampleTo;
    @Excel(name = "收件人")
    private String recevier;
    @Excel(name = "收件日期")
    private String recevieDate;
    @Excel(name = "寄件人")
    private String sender;
    @Excel(name = "寄件数量")
    private Integer sendTotal;
    @Excel(name = "剩余数量")
    private Integer levelTotal;
    @Excel(name = "审批意见")
    private String checkAdvice;
    @Excel(name = "布面意见")
    private String buAdvice;
    @Excel(name = "缝制意见")
    private String fengAdvice;
    @Excel(name = "交期意见")
    private String jqAdvice;
    @Excel(name = "客户评语")
    private String customRate;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "样品类型")
    private String type;

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

    @Column(name = "REQUIRED_NO", nullable = true, length = 32)
    public String getRequiredNo() {
        return this.requiredNo;
    }

    public void setRequiredNo(String requiredNo) {
        this.requiredNo = requiredNo;
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
    public String getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(String levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "VERSION", nullable = true, length = 32)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "WEIGHT", nullable = true, length = 32)
    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "CHENGF", nullable = true, length = 32)
    public String getChengf() {
        return this.chengf;
    }

    public void setChengf(String chengf) {
        this.chengf = chengf;
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

    @Column(name = "RAN_REQUIRED", nullable = true, length = 256)
    public String getRanRequired() {
        return this.ranRequired;
    }

    public void setRanRequired(String ranRequired) {
        this.ranRequired = ranRequired;
    }

    @Column(name = "FENG_REQUIRED", nullable = true, length = 256)
    public String getFengRequired() {
        return this.fengRequired;
    }

    public void setFengRequired(String fengRequired) {
        this.fengRequired = fengRequired;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "SAMPLE_TO", nullable = true, length = 256)
    public String getSampleTo() {
        return this.sampleTo;
    }

    public void setSampleTo(String sampleTo) {
        this.sampleTo = sampleTo;
    }

    @Column(name = "RECEVIER", nullable = true, length = 32)
    public String getRecevier() {
        return this.recevier;
    }

    public void setRecevier(String recevier) {
        this.recevier = recevier;
    }

    @Column(name = "RECEVIE_DATE", nullable = true, length = 32)
    public String getRecevieDate() {
        return this.recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }

    @Column(name = "SENDER", nullable = true, length = 32)
    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "SEND_TOTAL", nullable = true, length = 32)
    public Integer getSendTotal() {
        return this.sendTotal;
    }

    public void setSendTotal(Integer sendTotal) {
        this.sendTotal = sendTotal;
    }

    @Column(name = "LEVEL_TOTAL", nullable = true, length = 32)
    public Integer getLevelTotal() {
        return this.levelTotal;
    }

    public void setLevelTotal(Integer levelTotal) {
        this.levelTotal = levelTotal;
    }

    @Column(name = "CHECK_ADVICE", nullable = true, length = 256)
    public String getCheckAdvice() {
        return this.checkAdvice;
    }

    public void setCheckAdvice(String checkAdvice) {
        this.checkAdvice = checkAdvice;
    }

    @Column(name = "BU_ADVICE", nullable = true, length = 256)
    public String getBuAdvice() {
        return this.buAdvice;
    }

    public void setBuAdvice(String buAdvice) {
        this.buAdvice = buAdvice;
    }

    @Column(name = "FENG_ADVICE", nullable = true, length = 256)
    public String getFengAdvice() {
        return this.fengAdvice;
    }

    public void setFengAdvice(String fengAdvice) {
        this.fengAdvice = fengAdvice;
    }

    @Column(name = "JQ_ADVICE", nullable = true, length = 256)
    public String getJqAdvice() {
        return this.jqAdvice;
    }

    public void setJqAdvice(String jqAdvice) {
        this.jqAdvice = jqAdvice;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "is_have_color", nullable = true, length = 32)
    public String getIsHaveColor() {
        return isHaveColor;
    }

    public void setIsHaveColor(String isHaveColor) {
        this.isHaveColor = isHaveColor;
    }

    @Column(name = "color_image_url", nullable = true, length = 32)
    public String getColorImageUrl() {
        return colorImageUrl;
    }

    public void setColorImageUrl(String colorImageUrl) {
        this.colorImageUrl = colorImageUrl;
    }

    @Column(name = "color_image", nullable = true, length = 32)
    public String getColorImage() {
        return colorImage;
    }

    public void setColorImage(String colorImage) {
        this.colorImage = colorImage;
    }

    @Column(name = "is_have_color_num", nullable = true, length = 32)
    public String getIsHaveColorNum() {
        return isHaveColorNum;
    }

    public void setIsHaveColorNum(String isHaveColorNum) {
        this.isHaveColorNum = isHaveColorNum;
    }

    @Column(name = "color_num_image_url", nullable = true, length = 32)
    public String getColorNumImageUrl() {
        return colorNumImageUrl;
    }

    public void setColorNumImageUrl(String colorNumImageUrl) {
        this.colorNumImageUrl = colorNumImageUrl;
    }

    @Column(name = "color_num_image", nullable = true, length = 32)
    public String getColorNumImage() {
        return colorNumImage;
    }

    public void setColorNumImage(String colorNumImage) {
        this.colorNumImage = colorNumImage;
    }

    @Column(name = "is_enquiry", nullable = true, length = 32)
    public String getIsEnquiry() {
        return isEnquiry;
    }

    public void setIsEnquiry(String isEnquiry) {
        this.isEnquiry = isEnquiry;
    }

    @Column(name = "enquiry_no", nullable = true, length = 32)
    public String getEnquiryNo() {
        return enquiryNo;
    }

    public void setEnquiryNo(String enquiryNo) {
        this.enquiryNo = enquiryNo;
    }

    @Column(name = "dhjq", nullable = true, length = 32)
    public String getDhjq() {
        return dhjq;
    }

    public void setDhjq(String dhjq) {
        this.dhjq = dhjq;
    }

    @Column(name = "leaveldhjq_days", nullable = true, length = 32)
    public String getLeaveldhjqDays() {
        return leaveldhjqDays;
    }

    public void setLeaveldhjqDays(String leaveldhjqDays) {
        this.leaveldhjqDays = leaveldhjqDays;
    }

    @Column(name = "is_dh", nullable = true, length = 32)
    public String getIsDh() {
        return isDh;
    }

    public void setIsDh(String isDh) {
        this.isDh = isDh;
    }

    @Column(name = "order_no", nullable = true, length = 32)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "dhjq2", nullable = true, length = 32)
    public String getDhjq2() {
        return dhjq2;
    }

    public void setDhjq2(String dhjq2) {
        this.dhjq2 = dhjq2;
    }

    @Column(name = "leaveldhjq2_days", nullable = true, length = 32)
    public String getLeaveldhjq2Days() {
        return leaveldhjq2Days;
    }

    public void setLeaveldhjq2Days(String leaveldhjq2Days) {
        this.leaveldhjq2Days = leaveldhjq2Days;
    }

    @Column(name = "is_price", nullable = true, length = 32)
    public String getIsPrice() {
        return isPrice;
    }

    public void setIsPrice(String isPrice) {
        this.isPrice = isPrice;
    }

    @Column(name = "price_no", nullable = true, length = 32)
    public String getPriceNo() {
        return priceNo;
    }

    public void setPriceNo(String priceNo) {
        this.priceNo = priceNo;
    }

    @Column(name = "custom_rate", nullable = true, length = 32)
    public String getCustomRate() {
        return customRate;
    }

    public void setCustomRate(String customRate) {
        this.customRate = customRate;
    }
}
