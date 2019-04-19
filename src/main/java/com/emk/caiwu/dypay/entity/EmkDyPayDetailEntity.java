package com.emk.caiwu.dypay.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emk_dy_pay_detail", schema = "")
public class EmkDyPayDetailEntity implements Serializable {
    private String id;
    private String dyPayId;

    @Excel(name = "品名")
    private String proZnName;
    @Excel(name = "物料ID")
    private String proId;
    @Excel(name = "物料编号")
    private String proNum;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "数量")
    private String signTotal;
    @Excel(name = "单价")
    private String signPrice;
    @Excel(name = "物料类型")
    private String type;

    @Excel(name = "总金额")
    private String sumPrice;
    @Excel(name = "采购需求单号")
    private String cgxqdh;
    @Excel(name = "原料布料采购单号")
    private String cgdh;
    @Excel(name = "款号")
    private String sampleNo;


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

    @Column(name = "PRO_ZN_NAME", nullable = true, length = 56)
    public String getProZnName() {
        return this.proZnName;
    }

    public void setProZnName(String proZnName) {
        this.proZnName = proZnName;
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
    public String getSignTotal() {
        return this.signTotal;
    }

    public void setSignTotal(String signTotal) {
        this.signTotal = signTotal;
    }

    @Column(name = "SIGN_PRICE", nullable = true, length = 32)
    public String getSignPrice() {
        return this.signPrice;
    }

    public void setSignPrice(String signPrice) {
        this.signPrice = signPrice;
    }

    @Column(name = "TYPE", nullable = true, length = 6)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "BRAND", nullable = true, length = 56)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "sum_price", nullable = true, length = 32)
    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Column(name = "cgxqdh", nullable = true, length = 32)
    public String getCgxqdh() {
        return cgxqdh;
    }

    public void setCgxqdh(String cgxqdh) {
        this.cgxqdh = cgxqdh;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "dy_pay_id", nullable = true, length = 32)
    public String getDyPayId() {
        return dyPayId;
    }

    public void setDyPayId(String dyPayId) {
        this.dyPayId = dyPayId;
    }

    @Column(name = "cgdh", nullable = true, length = 32)
    public String getCgdh() {
        return cgdh;
    }

    public void setCgdh(String cgdh) {
        this.cgdh = cgdh;
    }
}
