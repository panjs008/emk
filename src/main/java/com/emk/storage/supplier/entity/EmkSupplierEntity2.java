package com.emk.storage.supplier.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class EmkSupplierEntity2
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "企业全称")
    private String supplier;
    @Excel(name = "供应商代码")
    private String supplierCode;
    @Excel(name = "供应商类型")
    private String supplierType;
    @Excel(name = "地址")
    private String address;
    @Excel(name = "产品类型")
    private String productType;
    @Excel(name = "纳税人识别号")
    private String taxpayerNum;
    @Excel(name = "开户行")
    private String bankName;
    @Excel(name = "开户账号 ")
    private String bankAccount;
    @Excel(name = "电话")
    private String telphone;
    @Excel(name = "法定代表人")
    private String legaler;
    @Excel(name = "法定代表人电话")
    private String legalerTelphone;
    @Excel(name = "法定代表人邮箱")
    private String legalerEmail;
    @Excel(name = "联系人")
    private String contacter;
    @Excel(name = "联系人电话")
    private String contacterTelphone;
    @Excel(name = "联系人邮箱")
    private String contacterEmail;
    @Excel(name = "财务联系人")
    private String cwContacter;
    @Excel(name = "财务联系人电话")
    private String cwContacterTelphone;
    @Excel(name = "财务联系人邮箱")
    private String cwContacterEmail;
    private String licenceUrl;
    private String licenceName;

    private String factoryInfoUrl;
    private String factoryInfoName;

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

    @Column(name = "SUPPLIER", nullable = true, length = 256)
    public String getSupplier() {
        return this.supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Column(name = "SUPPLIER_CODE", nullable = true, length = 32)
    public String getSupplierCode() {
        return this.supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Column(name = "SUPPLIER_TYPE", nullable = true, length = 32)
    public String getSupplierType() {
        return this.supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    @Column(name = "ADDRESS", nullable = true, length = 256)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "PRODUCT_TYPE", nullable = true, length = 32)
    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Column(name = "TAXPAYER_NUM", nullable = true, length = 32)
    public String getTaxpayerNum() {
        return this.taxpayerNum;
    }

    public void setTaxpayerNum(String taxpayerNum) {
        this.taxpayerNum = taxpayerNum;
    }

    @Column(name = "BANK_NAME", nullable = true, length = 256)
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "BANK_ACCOUNT", nullable = true, length = 56)
    public String getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Column(name = "TELPHONE", nullable = true, length = 32)
    public String getTelphone() {
        return this.telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Column(name = "LEGALER", nullable = true, length = 32)
    public String getLegaler() {
        return this.legaler;
    }

    public void setLegaler(String legaler) {
        this.legaler = legaler;
    }

    @Column(name = "CONTACTER", nullable = true, length = 32)
    public String getContacter() {
        return this.contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    @Column(name = "CW_CONTACTER", nullable = true, length = 32)
    public String getCwContacter() {
        return this.cwContacter;
    }

    public void setCwContacter(String cwContacter) {
        this.cwContacter = cwContacter;
    }

    @Column(name = "LICENCE_URL", nullable = true, length = 256)
    public String getLicenceUrl() {
        return this.licenceUrl;
    }

    public void setLicenceUrl(String licenceUrl) {
        this.licenceUrl = licenceUrl;
    }

    @Column(name = "legaler_telphone", nullable = true, length = 256)
    public String getLegalerTelphone() {
        return legalerTelphone;
    }

    public void setLegalerTelphone(String legalerTelphone) {
        this.legalerTelphone = legalerTelphone;
    }

    @Column(name = "legaler_email", nullable = true, length = 256)
    public String getLegalerEmail() {
        return legalerEmail;
    }

    public void setLegalerEmail(String legalerEmail) {
        this.legalerEmail = legalerEmail;
    }

    @Column(name = "contacter_telphone", nullable = true, length = 256)
    public String getContacterTelphone() {
        return contacterTelphone;
    }

    public void setContacterTelphone(String contacterTelphone) {
        this.contacterTelphone = contacterTelphone;
    }

    @Column(name = "contacter_email", nullable = true, length = 256)
    public String getContacterEmail() {
        return contacterEmail;
    }

    public void setContacterEmail(String contacterEmail) {
        this.contacterEmail = contacterEmail;
    }

    @Column(name = "cw_contacter_telphone", nullable = true, length = 256)
    public String getCwContacterTelphone() {
        return cwContacterTelphone;
    }

    public void setCwContacterTelphone(String cwContacterTelphone) {
        this.cwContacterTelphone = cwContacterTelphone;
    }

    @Column(name = "cw_contacter_email", nullable = true, length = 256)
    public String getCwContacterEmail() {
        return cwContacterEmail;
    }

    public void setCwContacterEmail(String cwContacterEmail) {
        this.cwContacterEmail = cwContacterEmail;
    }

    @Column(name = "factory_info_url", nullable = true, length = 256)
    public String getFactoryInfoUrl() {
        return factoryInfoUrl;
    }

    public void setFactoryInfoUrl(String factoryInfoUrl) {
        this.factoryInfoUrl = factoryInfoUrl;
    }


    @Column(name = "licence_name", nullable = true, length = 256)
    public String getLicenceName() {
        return licenceName;
    }

    public void setLicenceName(String licenceName) {
        this.licenceName = licenceName;
    }


    @Column(name = "factory_info_name", nullable = true, length = 256)
    public String getFactoryInfoName() {
        return factoryInfoName;
    }

    public void setFactoryInfoName(String factoryInfoName) {
        this.factoryInfoName = factoryInfoName;
    }
}
