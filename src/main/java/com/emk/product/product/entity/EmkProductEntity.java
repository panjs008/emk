package com.emk.product.product.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_product", schema = "")
public class EmkProductEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    private String hsCode;
    private String hsName;
    @Excel(name = "产品编号")
    private String proNum;
    private String proType;
    @Excel(name = "产品类别")
    private String proTypeName;
    private String proEnName;
    @Excel(name = "中文名称")
    private String proZnName;
    @Excel(name = "规格型号")
    private String brand;
    @Excel(name = "单位")
    private String unit;
    private String remarkEn;
    private String remarkZn;
    @Excel(name = "产品备注")
    private String proRemark;
    private String hsId;
    private String zzVal;
    private String tsVal;
    @Excel(name = "比例")
    private String precent;
    @Excel(name = "单件用量")
    private String yongliang;
    @Excel(name = "损耗率")
    private String sunhaoPrecent;
    @Excel(name = "成本")
    private String chengben;
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

    @Column(name = "PRO_NUM", nullable = true, length = 32)
    public String getProNum() {
        return this.proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "PRO_EN_NAME", nullable = true, length = 32)
    public String getProEnName() {
        return this.proEnName;
    }

    public void setProEnName(String proEnName) {
        this.proEnName = proEnName;
    }

    @Column(name = "PRO_ZN_NAME", nullable = true, length = 32)
    public String getProZnName() {
        return this.proZnName;
    }

    public void setProZnName(String proZnName) {
        this.proZnName = proZnName;
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

    @Column(name = "REMARK_EN", nullable = true, length = 256)
    public String getRemarkEn() {
        return this.remarkEn;
    }

    public void setRemarkEn(String remarkEn) {
        this.remarkEn = remarkEn;
    }

    @Column(name = "REMARK_ZN", nullable = true, length = 256)
    public String getRemarkZn() {
        return this.remarkZn;
    }

    public void setRemarkZn(String remarkZn) {
        this.remarkZn = remarkZn;
    }

    @Column(name = "PRO_REMARK", nullable = true, length = 32)
    public String getProRemark() {
        return this.proRemark;
    }

    public void setProRemark(String proRemark) {
        this.proRemark = proRemark;
    }

    @Column(name = "pro_type_name", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    @Column(name = "hs_id", nullable = true, length = 32)
    public String getHsId() {
        return this.hsId;
    }

    public void setHsId(String hsId) {
        this.hsId = hsId;
    }

    @Column(name = "hs_code", nullable = true, length = 32)
    public String getHsCode() {
        return this.hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    @Column(name = "hs_name", nullable = true, length = 32)
    public String getHsName() {
        return this.hsName;
    }

    public void setHsName(String hsName) {
        this.hsName = hsName;
    }

    @Formula("(select p.zz_val from emk_product_hs p where p.id = hs_id)")
    public String getZzVal() {
        return this.zzVal;
    }

    public void setZzVal(String zzVal) {
        this.zzVal = zzVal;
    }

    @Formula("(select p.ts_val from emk_product_hs p where p.id = hs_id)")
    public String getTsVal() {
        return this.tsVal;
    }

    public void setTsVal(String tsVal) {
        this.tsVal = tsVal;
    }

    @Column(name = "precent", nullable = true, length = 32)
    public String getPrecent() {
        return this.precent;
    }

    public void setPrecent(String precent) {
        this.precent = precent;
    }

    @Column(name = "yongliang", nullable = true, length = 32)
    public String getYongliang() {
        return this.yongliang;
    }

    public void setYongliang(String yongliang) {
        this.yongliang = yongliang;
    }

    @Column(name = "sunhao_precent", nullable = true, length = 32)
    public String getSunhaoPrecent() {
        return this.sunhaoPrecent;
    }

    public void setSunhaoPrecent(String sunhaoPrecent) {
        this.sunhaoPrecent = sunhaoPrecent;
    }

    @Column(name = "chengben", nullable = true, length = 32)
    public String getChengben() {
        return this.chengben;
    }

    public void setChengben(String chengben) {
        this.chengben = chengben;
    }

    @Column(name = "type", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
