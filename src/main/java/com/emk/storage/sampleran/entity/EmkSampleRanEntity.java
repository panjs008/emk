package com.emk.storage.sampleran.entity;

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
@Table(name = "emk_sample_ran", schema = "")
public class EmkSampleRanEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "布面种类")
    private String buType;
    @Excel(name = "染色单价")
    private Double price;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "单件克重")
    private Double oneWeight;
    @Excel(name = "成本")
    private Double chengben;
    @Excel(name = "样品ID")
    private String sampleId;
    private String type;                        // 0 染色 1 助剂
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

    @Column(name = "BU_TYPE", nullable = true, length = 32)
    public String getBuType() {
        return this.buType;
    }

    public void setBuType(String buType) {
        this.buType = buType;
    }

    @Column(name = "PRICE", nullable = true, length = 32)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "ONE_WEIGHT", nullable = true, length = 32)
    public Double getOneWeight() {
        return this.oneWeight;
    }

    public void setOneWeight(Double oneWeight) {
        this.oneWeight = oneWeight;
    }

    @Column(name = "CHENGBEN", nullable = true, length = 32)
    public Double getChengben() {
        return this.chengben;
    }

    public void setChengben(Double chengben) {
        this.chengben = chengben;
    }

    @Column(name = "SAMPLE_ID", nullable = true, length = 32)
    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "type", nullable = true, length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
