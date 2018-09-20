package com.service.custombank.entity;

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
@Table(name = "ymk_custom_bank", schema = "")
public class YmkCustomBankEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "银行名称", width = 15)
    private String bankName;
    @Excel(name = "银行账号", width = 15)
    private String bankAccount;
    @Excel(name = "银行户名", width = 15)
    private String bankAccountName;
    @Excel(name = "SWIFT号", width = 15)
    private String swifi;
    @Excel(name = "币种", width = 15)
    private String bz;
    @Excel(name = "默认账号", width = 15)
    private String state;
    @Excel(name = "客户ID", width = 15)
    private String customId;
    @Excel(name = "客户名称", width = 15)
    private String customName;

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

    @Column(name = "BANK_NAME", nullable = true, length = 256)
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "BANK_ACCOUNT", nullable = true, length = 32)
    public String getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Column(name = "BANK_ACCOUNT_NAME", nullable = true, length = 256)
    public String getBankAccountName() {
        return this.bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    @Column(name = "SWIFI", nullable = true, length = 32)
    public String getSwifi() {
        return this.swifi;
    }

    public void setSwifi(String swifi) {
        this.swifi = swifi;
    }

    @Column(name = "BZ", nullable = true, length = 32)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "CUSTOM_ID", nullable = true, length = 32)
    public String getCustomId() {
        return this.customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Column(name = "CUSTOM_NAME", nullable = true, length = 32)
    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
