package com.emk.bill.proorderdetail.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.emk.check.sizecheck.entity.EmkSizeTotalEntityD;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_pro_order_detail", schema = "")
public class EmkProOrderDetailEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "订单ID")
    private String proOrderId;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleDesc;
    @Excel(name = "数量")
    private String total;
    @Excel(name = "尺码")
    private String size;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "价格")
    private String price;
    @Excel(name = "产品编号")
    private String proNum;
    @Excel(name = "产品名称")
    private String proName;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "单位")
    private String unit;
    private String sortDesc;

    @Excel(name = "金额")
    private String sumPrice;
    @Excel(name = "总数量")
    private String sumTotal;
    @Excel(name = "总金额")
    private String sumMoney;
    @Excel(name = "货期")
    private String hqDate;

    @Excel(name = "总箱数")
    private String sumBox;
    @Excel(name = "总体积")
    private String sumVol;
    @Excel(name = "总毛重")
    private String sumMao;
    @Excel(name = "总净重")
    private String sumJz;

    private EmkSizeTotalEntityD emkSizeTotalEntity;

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

    @Column(name = "PRO_ORDER_ID", nullable = true, length = 32)
    public String getProOrderId() {
        return this.proOrderId;
    }

    public void setProOrderId(String proOrderId) {
        this.proOrderId = proOrderId;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Column(name = "SIZE", nullable = true, length = 32)
    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "PRICE", nullable = true, length = 32)
    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Column(name = "PRO_NUM", nullable = true, length = 32)
    public String getProNum() {
        return this.proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    @Column(name = "PRO_NAME", nullable = true, length = 32)
    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Column(name = "BRAND", nullable = true, length = 32)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "UNIT", nullable = true, length = 12)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "sort_desc", nullable = true, length = 12)
    public String getSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    @Column(name = "sum_total", nullable = true, length = 12)
    public String getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(String sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Column(name = "order_no", nullable = true, length = 12)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "sample_desc", nullable = true, length = 12)
    public String getSampleDesc() {
        return sampleDesc;
    }

    public void setSampleDesc(String sampleDesc) {
        this.sampleDesc = sampleDesc;
    }

    @Column(name = "sum_money", nullable = true, length = 12)
    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "hq_date", nullable = true, length = 12)
    public String getHqDate() {
        return hqDate;
    }

    public void setHqDate(String hqDate) {
        this.hqDate = hqDate;
    }

    @Column(name = "sum_price", nullable = true, length = 12)
    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Column(name = "sum_box", nullable = true, length = 12)
    public String getSumBox() {
        return sumBox;
    }

    public void setSumBox(String sumBox) {
        this.sumBox = sumBox;
    }

    @Column(name = "sum_vol", nullable = true, length = 12)
    public String getSumVol() {
        return sumVol;
    }

    public void setSumVol(String sumVol) {
        this.sumVol = sumVol;
    }

    @Column(name = "sum_mao", nullable = true, length = 12)
    public String getSumMao() {
        return sumMao;
    }

    public void setSumMao(String sumMao) {
        this.sumMao = sumMao;
    }

    @Column(name = "sum_jz", nullable = true, length = 12)
    public String getSumJz() {
        return sumJz;
    }

    public void setSumJz(String sumJz) {
        this.sumJz = sumJz;
    }

    @OneToOne(mappedBy="emkProOrderDetailEntity")
    public EmkSizeTotalEntityD getEmkSizeTotalEntity() {
        return emkSizeTotalEntity;
    }

    public void setEmkSizeTotalEntity(EmkSizeTotalEntityD emkSizeTotalEntity) {
        this.emkSizeTotalEntity = emkSizeTotalEntity;
    }
}
