package com.service.customtrace.entity;

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
@Table(name = "ymk_custom_trace", schema = "")
public class YmkCustomTraceEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "客户名称", width = 15)
    private String cusName;
    @Excel(name = "客户ID", width = 15)
    private String cusId;
    @Excel(name = "联系人", width = 15)
    private String contactName;
    @Excel(name = "联系人ID", width = 15)
    private String contactId;
    @Excel(name = "跟进人", width = 15)
    private String traceName;
    @Excel(name = "跟进人ID", width = 15)
    private String traceId;
    @Excel(name = "跟进方式", width = 15)
    private String traceType;
    @Excel(name = "跟进状态", width = 15)
    private String traceState;
    @Excel(name = "跟进内容", width = 15)
    private String traceContent;
    @Excel(name = "跟进时间", width = 15)
    private String traceTime;

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

    @Column(name = "CUS_NAME", nullable = true, length = 32)
    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Column(name = "CUS_ID", nullable = true, length = 32)
    public String getCusId() {
        return this.cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Column(name = "CONTACT_NAME", nullable = true, length = 32)
    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Column(name = "CONTACT_ID", nullable = true, length = 32)
    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Column(name = "TRACE_NAME", nullable = true, length = 32)
    public String getTraceName() {
        return this.traceName;
    }

    public void setTraceName(String traceName) {
        this.traceName = traceName;
    }

    @Column(name = "TRACE_ID", nullable = true, length = 32)
    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Column(name = "TRACE_TYPE", nullable = true, length = 32)
    public String getTraceType() {
        return this.traceType;
    }

    public void setTraceType(String traceType) {
        this.traceType = traceType;
    }

    @Column(name = "TRACE_STATE", nullable = true, length = 32)
    public String getTraceState() {
        return this.traceState;
    }

    public void setTraceState(String traceState) {
        this.traceState = traceState;
    }

    @Column(name = "TRACE_CONTENT", nullable = true, length = 256)
    public String getTraceContent() {
        return this.traceContent;
    }

    public void setTraceContent(String traceContent) {
        this.traceContent = traceContent;
    }

    @Column(name = "TRACE_TIME", nullable = true, length = 32)
    public String getTraceTime() {
        return this.traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }
}
