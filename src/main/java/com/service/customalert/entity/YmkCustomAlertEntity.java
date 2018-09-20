package com.service.customalert.entity;

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
@Table(name = "ymk_custom_alert", schema = "")
public class YmkCustomAlertEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "提醒内容", width = 15)
    private String alertContent;
    @Excel(name = "提醒时间", width = 15)
    private String alertTime;
    @Excel(name = "提醒人员ID", width = 15)
    private String alertUserIds;
    @Excel(name = "提醒人员", width = 15)
    private String alertUserNames;
    @Excel(name = "客户ID", width = 15)
    private String customId;
    @Excel(name = "状态", width = 15)
    private String state;
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

    @Column(name = "ALERT_CONTENT", nullable = true, length = 256)
    public String getAlertContent() {
        return this.alertContent;
    }

    public void setAlertContent(String alertContent) {
        this.alertContent = alertContent;
    }

    @Column(name = "ALERT_TIME", nullable = true, length = 32)
    public String getAlertTime() {
        return this.alertTime;
    }

    public void setAlertTime(String alertTime) {
        this.alertTime = alertTime;
    }

    @Column(name = "ALERT_USER_IDS", nullable = true, length = 256)
    public String getAlertUserIds() {
        return this.alertUserIds;
    }

    public void setAlertUserIds(String alertUserIds) {
        this.alertUserIds = alertUserIds;
    }

    @Column(name = "ALERT_USER_NAMES", nullable = true, length = 256)
    public String getAlertUserNames() {
        return this.alertUserNames;
    }

    public void setAlertUserNames(String alertUserNames) {
        this.alertUserNames = alertUserNames;
    }

    @Column(name = "CUSTOM_ID", nullable = true, length = 32)
    public String getCustomId() {
        return this.customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "CUSTOM_NAME", nullable = true, length = 256)
    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
