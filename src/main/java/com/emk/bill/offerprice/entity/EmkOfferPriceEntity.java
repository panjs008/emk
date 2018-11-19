package com.emk.bill.offerprice.entity;

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
@Table(name = "emk_offer_price", schema = "")
public class EmkOfferPriceEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "客户编号")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "开单日期")
    private String kdTime;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "款式")
    private String proTypeName;
    @Excel(name = "款式")
    private String proType;
    @Excel(name = "加工费")
    private Double workPrice;
    @Excel(name = "厂皮损耗费")
    private Double lossPrice;
    @Excel(name = "商检运费")
    private Double freightPrice;
    @Excel(name = "税费")
    private Double tax;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "图片")
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;

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

    @Column(name = "KD_TIME", nullable = true, length = 32)
    public String getKdTime() {
        return this.kdTime;
    }

    public void setKdTime(String kdTime) {
        this.kdTime = kdTime;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "PRO_TYPE_NAME", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "WORK_PRICE", nullable = true, length = 32)
    public Double getWorkPrice() {
        return this.workPrice;
    }

    public void setWorkPrice(Double workPrice) {
        this.workPrice = workPrice;
    }

    @Column(name = "LOSS_PRICE", nullable = true, length = 32)
    public Double getLossPrice() {
        return this.lossPrice;
    }

    public void setLossPrice(Double lossPrice) {
        this.lossPrice = lossPrice;
    }

    @Column(name = "FREIGHT_PRICE", nullable = true, length = 32)
    public Double getFreightPrice() {
        return this.freightPrice;
    }

    public void setFreightPrice(Double freightPrice) {
        this.freightPrice = freightPrice;
    }

    @Column(name = "TAX", nullable = true, length = 32)
    public Double getTax() {
        return this.tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
