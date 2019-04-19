package com.emk.storage.enquirydetail.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.emk.check.sizecheck.entity.EmkSizeTotalEntityE;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_enquiry_detail", schema = "")
public class EmkEnquiryDetailEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "询盘ID")
    private String enquiryId;
    @Excel(name = "颜色名称")
    private String color;
    @Excel(name = "色号")
    private String colorValue;
    @Excel(name = "尺码")
    private String size;
    @Excel(name = "数量")
    private Integer total;
    @Excel(name = "单尺码总数")
    private Integer sizeTotal;
    @Excel(name = "单颜色总数")
    private Integer colorTotal;
    @Excel(name = "单价")
    private Double price;
    private String sortDesc;
    @Excel(name = "实际数量")
    private Integer sjtotal;

    private EmkSizeTotalEntityE emkSizeTotalEntity;


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

    @Column(name = "ENQUIRY_ID", nullable = true, length = 32)
    public String getEnquiryId() {
        return this.enquiryId;
    }

    public void setEnquiryId(String enquiryId) {
        this.enquiryId = enquiryId;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "COLOR_VALUE", nullable = true, length = 32)
    public String getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    @Column(name = "SIZE", nullable = true, length = 32)
    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "SIZE_TOTAL", nullable = true, length = 32)
    public Integer getSizeTotal() {
        return this.sizeTotal;
    }

    public void setSizeTotal(Integer sizeTotal) {
        this.sizeTotal = sizeTotal;
    }

    @Column(name = "COLOR_TOTAL", nullable = true, length = 32)
    public Integer getColorTotal() {
        return this.colorTotal;
    }

    public void setColorTotal(Integer colorTotal) {
        this.colorTotal = colorTotal;
    }

    @Column(name = "PRICE", nullable = true, scale = 2, length = 32)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "sort_desc", nullable = true, scale = 2, length = 32)
    public String getSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    @Column(name = "sjtotal", nullable = true, scale = 2, length = 32)
    public Integer getSjtotal() {
        return sjtotal;
    }

    public void setSjtotal(Integer sjtotal) {
        this.sjtotal = sjtotal;
    }

    @OneToOne(mappedBy="emkEnquiryDetailEntity")
    public EmkSizeTotalEntityE getEmkSizeTotalEntity() {
        return emkSizeTotalEntity;
    }

    public void setEmkSizeTotalEntity(EmkSizeTotalEntityE emkSizeTotalEntity) {
        this.emkSizeTotalEntity = emkSizeTotalEntity;
    }
}
