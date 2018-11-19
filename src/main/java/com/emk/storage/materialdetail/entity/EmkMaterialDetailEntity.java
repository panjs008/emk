package com.emk.storage.materialdetail.entity;

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
@Table(name = "emk_material_detail", schema = "")
public class EmkMaterialDetailEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "物料ID")
    private String proId;
    @Excel(name = "物料编号")
    private String proNum;
    @Excel(name = "数量")
    private Integer signTotal;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "捻向")
    private String direction;
    @Excel(name = "批号")
    private String betchNum;
    @Excel(name = "幅宽")
    private String width;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "克重")
    private String weight;
    @Excel(name = "成分")
    private String chengf;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商Code")
    private String gysCode;
    @Excel(name = "原料面料名称")
    private String proZnName;
    @Excel(name = "需求ID")
    private String materialId;
    @Excel(name = "规格")
    private String brand;

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

    @Column(name = "SIGN_TOTAL", nullable = true, length = 32)
    public Integer getSignTotal() {
        return this.signTotal;
    }

    public void setSignTotal(Integer signTotal) {
        this.signTotal = signTotal;
    }

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "DIRECTION", nullable = true, length = 32)
    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Column(name = "BETCH_NUM", nullable = true, length = 32)
    public String getBetchNum() {
        return this.betchNum;
    }

    public void setBetchNum(String betchNum) {
        this.betchNum = betchNum;
    }

    @Column(name = "WIDTH", nullable = true, length = 32)
    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    @Column(name = "PRO_ZN_NAME", nullable = true, length = 32)
    public String getProZnName() {
        return this.proZnName;
    }

    public void setProZnName(String proZnName) {
        this.proZnName = proZnName;
    }

    @Column(name = "MATERIAL_ID", nullable = true, length = 32)
    public String getMaterialId() {
        return this.materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Column(name = "BRAND", nullable = true, length = 32)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
