package com.service.customcontact.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "ymk_custom_contact", schema = "")
public class YmkCustomContactEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "姓名", width = 15)
    private String userName;
    @Excel(name = "性别", width = 15)
    private String sex;
    @Excel(name = "职务", width = 15)
    private String position;
    @Excel(name = "生日", width = 15)
    private String brithDay;
    @Excel(name = "email", width = 15)
    private String email;
    @Excel(name = "手机", width = 15)
    private String telphone;
    @Excel(name = "电话", width = 15)
    private String mobile;
    @Excel(name = "备注", width = 15)
    private String remark;
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

    @Formula("(SELECT t1.user_id FROM ymk_custom_contact_user t1 WHERE t1.contact_id=id)")
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

    @Column(name = "USER_NAME", nullable = true, length = 32)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "SEX", nullable = true, length = 32)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "POSITION", nullable = true, length = 32)
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "BRITH_DAY", nullable = true, length = 32)
    public String getBrithDay() {
        return this.brithDay;
    }

    public void setBrithDay(String brithDay) {
        this.brithDay = brithDay;
    }

    @Column(name = "EMAIL", nullable = true, length = 32)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "TELPHONE", nullable = true, length = 32)
    public String getTelphone() {
        return this.telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Column(name = "MOBILE", nullable = true, length = 32)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "CUSTOM_ID", nullable = true, length = 32)
    public String getCustomId() {
        return this.customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Column(name = "CUSTOM_NAME", nullable = true, length = 256)
    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
