package com.emk.produce.packinglist.entity;

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
@Table(name = "emk_packing_list", schema = "")
public class EmkPackingListEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "生产方名称")
    private String scfmc;
    @Excel(name = "地址")
    private String address;
    @Excel(name = "法定代表签名")
    private String signer;
    @Excel(name = "签名日期")
    private String signDate;
    @Excel(name = "发票编号")
    private String fpbh;
    @Excel(name = "发票日期")
    private String fpDate;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "客户地址")
    private String cusAddress;
    private String telphone;


    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CREATE_NAME", nullable = true, length = 32)
    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name = "CREATE_BY", nullable = true, length = 32)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "CREATE_DATE", nullable = true, length = 32)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "SYS_ORG_CODE", nullable = true, length = 32)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    @Column(name = "SCFMC", nullable = true, length = 56)
    public String getScfmc() {
        return this.scfmc;
    }

    public void setScfmc(String scfmc) {
        this.scfmc = scfmc;
    }

    @Column(name = "ADDRESS", nullable = true, length = 256)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "SIGNER", nullable = true, length = 32)
    public String getSigner() {
        return this.signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    @Column(name = "SIGN_DATE", nullable = true, length = 32)
    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    @Column(name = "FPBH", nullable = true, length = 32)
    public String getFpbh() {
        return this.fpbh;
    }

    public void setFpbh(String fpbh) {
        this.fpbh = fpbh;
    }

    @Column(name = "FP_DATE", nullable = true, length = 32)
    public String getFpDate() {
        return this.fpDate;
    }

    public void setFpDate(String fpDate) {
        this.fpDate = fpDate;
    }


    @Column(name = "CUS_NUM", nullable = true, length = 32)
    public String getCusNum() {
        return this.cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    @Column(name = "CUS_NAME", nullable = true, length = 32)
    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Column(name = "cus_address", nullable = true, length = 32)
    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    @Column(name = "telphone", nullable = true, length = 32)
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
