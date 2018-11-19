package com.emk.storage.instoragedetail.entity;

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
@Table(name = "emk_in_storage_detail", schema = "")
public class EmkInStorageDetailEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    private String sysCompanyCode;
    @Excel(name = "入库单ID")
    private String inStorageId;
    @Excel(name = "商品ID")
    private String proId;
    @Excel(name = "物料编号")
    private String proNum;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "数量")
    private String signTotal;
    @Excel(name = "单位")
    private String signUnit;
    @Excel(name = "单价")
    private String signPrice;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "物料名称")
    private String proName;
    @Excel(name = "仓库ID")
    private String storageSetId;
    @Excel(name = "仓库名称")
    private String storageName;
    @Excel(name = "库位ID")
    private String positionId;
    @Excel(name = "库位名称")
    private String positionName;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "款号名称")
    private String sampleName;

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

    @Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
    public String getSysCompanyCode() {
        return this.sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }

    @Column(name = "IN_STORAGE_ID", nullable = true, length = 32)
    public String getInStorageId() {
        return this.inStorageId;
    }

    public void setInStorageId(String inStorageId) {
        this.inStorageId = inStorageId;
    }

    @Column(name = "PRO_ID", nullable = true, length = 56)
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
    public String getSignTotal() {
        return this.signTotal;
    }

    public void setSignTotal(String signTotal) {
        this.signTotal = signTotal;
    }

    @Column(name = "SIGN_UNIT", nullable = true, length = 32)
    public String getSignUnit() {
        return this.signUnit;
    }

    public void setSignUnit(String signUnit) {
        this.signUnit = signUnit;
    }

    @Column(name = "SIGN_PRICE", nullable = true, length = 32)
    public String getSignPrice() {
        return this.signPrice;
    }

    public void setSignPrice(String signPrice) {
        this.signPrice = signPrice;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "PRO_NAME", nullable = true, length = 32)
    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Column(name = "STORAGE_SET_ID", nullable = true, length = 32)
    public String getStorageSetId() {
        return this.storageSetId;
    }

    public void setStorageSetId(String storageSetId) {
        this.storageSetId = storageSetId;
    }

    @Column(name = "STORAGE_NAME", nullable = true, length = 32)
    public String getStorageName() {
        return this.storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    @Column(name = "POSITION_ID", nullable = true, length = 32)
    public String getPositionId() {
        return this.positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    @Column(name = "POSITION_NAME", nullable = true, length = 32)
    public String getPositionName() {
        return this.positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_NAME", nullable = true, length = 32)
    public String getSampleName() {
        return this.sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }
}
