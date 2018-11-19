package com.emk.storage.samplegx.entity;

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
@Table(name = "emk_sample_gx", schema = "")
public class EmkSampleGxEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "工序名称")
    private String gxmc;
    @Excel(name = "单件耗时")
    private Double djhs;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "每天产量")
    private Integer productTotal;
    @Excel(name = "成本")
    private Double chengben;
    @Excel(name = "样品ID")
    private String sampleId;

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

    @Column(name = "GXMC", nullable = true, length = 256)
    public String getGxmc() {
        return this.gxmc;
    }

    public void setGxmc(String gxmc) {
        this.gxmc = gxmc;
    }

    @Column(name = "DJHS", nullable = true, length = 32)
    public Double getDjhs() {
        return this.djhs;
    }

    public void setDjhs(Double djhs) {
        this.djhs = djhs;
    }

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "PRODUCT_TOTAL", nullable = true, length = 32)
    public Integer getProductTotal() {
        return this.productTotal;
    }

    public void setProductTotal(Integer productTotal) {
        this.productTotal = productTotal;
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
}
