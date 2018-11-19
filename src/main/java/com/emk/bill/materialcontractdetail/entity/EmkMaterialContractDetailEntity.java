package com.emk.bill.materialcontractdetail.entity;

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
@Table(name = "emk_material_contract_detail", schema = "")
public class EmkMaterialContractDetailEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "采购合同ID")
    private String contractId;
    @Excel(name = "品名")
    private String proZnName;
    @Excel(name = "物料编号")
    private String proNum;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "物料类型")
    private String type;
    @Excel(name = "单价")
    private Double signPrice;
    @Excel(name = "采购数量")
    private Integer total;
    @Excel(name = "入库数量")
    private Integer inTotal;
    @Excel(name = "出库数量")
    private Integer outTotal;
    @Excel(name = "剩余数量")
    private Integer leavelTotal;

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

    @Column(name = "CONTRACT_ID", nullable = true, length = 32)
    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Column(name = "PRO_ZN_NAME", nullable = true, length = 32)
    public String getProZnName() {
        return this.proZnName;
    }

    public void setProZnName(String proZnName) {
        this.proZnName = proZnName;
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

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "SIGN_PRICE", nullable = true, scale = 2, length = 32)
    public Double getSignPrice() {
        return this.signPrice;
    }

    public void setSignPrice(Double signPrice) {
        this.signPrice = signPrice;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "IN_TOTAL", nullable = true, length = 32)
    public Integer getInTotal() {
        return this.inTotal;
    }

    public void setInTotal(Integer inTotal) {
        this.inTotal = inTotal;
    }

    @Column(name = "OUT_TOTAL", nullable = true, length = 32)
    public Integer getOutTotal() {
        return this.outTotal;
    }

    public void setOutTotal(Integer outTotal) {
        this.outTotal = outTotal;
    }

    @Column(name = "LEAVEL_TOTAL", nullable = true, length = 32)
    public Integer getLeavelTotal() {
        return this.leavelTotal;
    }

    public void setLeavelTotal(Integer leavelTotal) {
        this.leavelTotal = leavelTotal;
    }
}
