package com.emk.bill.offerpricedetail.entity;

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
@Table(name = "emk_offer_price_detail", schema = "")
public class EmkOfferPriceDetailEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "方案ID")
    private String offerPriceId;
    @Excel(name = "物料ID")
    private String proId;
    @Excel(name = "物料编号")
    private String proNum;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "数量")
    private Double signTotal;
    @Excel(name = "单价")
    private Double signPrice;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "产品名称")
    private String proName;

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

    @Column(name = "OFFER_PRICE_ID", nullable = true, length = 32)
    public String getOfferPriceId() {
        return this.offerPriceId;
    }

    public void setOfferPriceId(String offerPriceId) {
        this.offerPriceId = offerPriceId;
    }

    @Column(name = "PRO_ID", nullable = true, length = 32)
    public String getProId() {
        return this.proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    @Column(name = "PRO_NUM", nullable = true, length = 32)
    public String getProNum() {
        return this.proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    @Column(name = "BRAND", nullable = true, length = 32)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "SIGN_TOTAL", nullable = true, length = 32)
    public Double getSignTotal() {
        return this.signTotal;
    }

    public void setSignTotal(Double signTotal) {
        this.signTotal = signTotal;
    }

    @Column(name = "SIGN_PRICE", nullable = true, length = 32)
    public Double getSignPrice() {
        return this.signPrice;
    }

    public void setSignPrice(Double signPrice) {
        this.signPrice = signPrice;
    }

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "PRO_NAME", nullable = true, length = 32)
    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
