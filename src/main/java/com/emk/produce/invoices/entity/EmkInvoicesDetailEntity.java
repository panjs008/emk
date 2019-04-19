package com.emk.produce.invoices.entity;

import com.emk.check.sizecheck.entity.EmkSizeTotalEntityB;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emk_invoices_detail", schema = "")
public class EmkInvoicesDetailEntity implements Serializable {
    private String id;
    private String invoicesId;

    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "出厂日期")
    private String outDate;

    @Excel(name = "颜色")
    private String color;
    @Excel(name = "尺码")
    private String size;
    @Excel(name = "数量")
    private Integer total;
    @Excel(name = "单价")
    private Double price;
    @Excel(name = "金额")
    private String money;
    @Excel(name = "总金额")
    private String sumMoney;
    @Excel(name = "总箱数")
    private Integer sumTotal;

    private EmkSizeTotalEntityB emkSizeTotalEntity;


    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = true, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "SUM_MONEY", nullable = true, length = 32)
    public String getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "SIZE", nullable = true, length = 32)
    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "PRICE", nullable = true, scale = 2, length = 32)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "order_no", nullable = true, length = 32)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "color", nullable = true, length = 32)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "money", nullable = true, length = 32)
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 32)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "OUT_DATE", nullable = true, length = 32)
    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Column(name = "invoices_id", nullable = true, length = 32)
    public String getInvoicesId() {
        return invoicesId;
    }

    public void setInvoicesId(String invoicesId) {
        this.invoicesId = invoicesId;
    }

    @Column(name = "sum_total", nullable = true, length = 32)
    public Integer getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(Integer sumTotal) {
        this.sumTotal = sumTotal;
    }

    @OneToOne(mappedBy="emkInvoicesDetailEntity")
    public EmkSizeTotalEntityB getEmkSizeTotalEntity() {
        return emkSizeTotalEntity;
    }

    public void setEmkSizeTotalEntity(EmkSizeTotalEntityB emkSizeTotalEntity) {
        this.emkSizeTotalEntity = emkSizeTotalEntity;
    }
}
