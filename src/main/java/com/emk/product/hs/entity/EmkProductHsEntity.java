package com.emk.product.hs.entity;

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
@Table(name = "emk_product_hs", schema = "")
public class EmkProductHsEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "海关编码")
    private String hsCode;
    @Excel(name = "海关名称")
    private String hsName;
    @Excel(name = "报关要素")
    private String bgys;
    @Excel(name = "增值税率")
    private String zzVal;
    @Excel(name = "退税率")
    private String tsVal;
    @Excel(name = "销售指导价")
    private String salePrice;
    @Excel(name = "备注")
    private String remark;

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

    @Column(name = "HS_CODE", nullable = true, length = 32)
    public String getHsCode() {
        return this.hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    @Column(name = "HS_NAME", nullable = true, length = 256)
    public String getHsName() {
        return this.hsName;
    }

    public void setHsName(String hsName) {
        this.hsName = hsName;
    }

    @Column(name = "BGYS", nullable = true, length = 256)
    public String getBgys() {
        return this.bgys;
    }

    public void setBgys(String bgys) {
        this.bgys = bgys;
    }

    @Column(name = "ZZ_VAL", nullable = true, length = 32)
    public String getZzVal() {
        return this.zzVal;
    }

    public void setZzVal(String zzVal) {
        this.zzVal = zzVal;
    }

    @Column(name = "TS_VAL", nullable = true, length = 32)
    public String getTsVal() {
        return this.tsVal;
    }

    public void setTsVal(String tsVal) {
        this.tsVal = tsVal;
    }

    @Column(name = "SALE_PRICE", nullable = true, length = 32)
    public String getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
