package com.service.custom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class YmkCustomEntityA implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "省")
    private String shengFen;
    /*@Excel(name = "城市")
    private String chengShi;
    @Excel(name = "片区")
    private String pianQu;*/
    @Excel(name = "客户编码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;
    @Excel(name = "客户简称", width = 15)
    private String cusCode;
    @Excel(name = "客户类型", width = 15)
    private String cusType;
    @Excel(name = "客户类型名称", width = 15)
    private String cusTypeName;
    @Excel(name = "客户来源", width = 15)
    private String cusFrom;
    @Excel(name = "客户地址", width = 15)
    private String address;
    @Excel(name = "联系人电话", width = 15)
    private String telphone;
    @Excel(name = "备注", width = 15)
    private String remark;
    @Excel(name = "状态")
    private String status;              //0 激活、1 暂停、2 禁止
    @Excel(name = "办公电话", width = 15)
    private String workphone;
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "业务员", width = 15)
    private String businesser;
    private String businesserName;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    private String developerName;
    @Excel(name = "业务类型", width = 15)
    private String businessType;
    @Excel(name = "主营产品", width = 15)
    private String mainContent;
    @Excel(name = "潜在业务量/年", width = 15)
    private String qzywl;
    @Excel(name = "建立商业关系时间", width = 15)
    private String relationDate;
    @Excel(name = "国家", width = 15)
    private String guoJia;
    @Excel(name = "主联系人", width = 15)
    private String zlxr;
    @Excel(name = "业务联系人二", width = 15)
    private String ywlxr2;
    @Excel(name = "业务联系人二邮箱", width = 15)
    private String ywlxr2Email;
    @Excel(name = "业务联系人二电话", width = 15)
    private String ywlxr2Telphone;
    @Excel(name = "业务联系人三", width = 15)
    private String ywlxr3;
    @Excel(name = "业务联系人三邮箱", width = 15)
    private String ywlxr3Email;
    @Excel(name = "业务联系人三电话", width = 15)
    private String ywlxr3Telphone;
    @Excel(name = "业务联系人四", width = 15)
    private String ywlxr4;
    @Excel(name = "业务联系人四邮箱", width = 15)
    private String ywlxr4Email;
    @Excel(name = "业务联系人四电话", width = 15)
    private String ywlxr4Telphone;
    @Excel(name = "业务联系人五", width = 15)
    private String ywlxr5;
    @Excel(name = "业务联系人五邮箱", width = 15)
    private String ywlxr5Email;
    @Excel(name = "业务联系人五电话", width = 15)
    private String ywlxr5Telphone;
    @Excel(name = "包装联系人", width = 15)
    private String bzlxr;
    @Excel(name = "包装联系人邮箱", width = 15)
    private String bzlxrEmail;
    @Excel(name = "包装联系人电话", width = 15)
    private String bzlxrTelphone;
    @Excel(name = "测试联系人", width = 15)
    private String cslxr;
    @Excel(name = "测试联系人邮箱", width = 15)
    private String cslxrEmail;
    @Excel(name = "测试联系人电话", width = 15)
    private String cslxrTelphone;
    @Excel(name = "质量联系人", width = 15)
    private String zllxr;
    @Excel(name = "质量联系人邮箱", width = 15)
    private String zllxrEmail;
    @Excel(name = "质量联系人电话", width = 15)
    private String zllxrTelphone;
    @Excel(name = "验厂联系人", width = 15)
    private String yclxr;
    @Excel(name = "验厂联系人邮箱", width = 15)
    private String yclxrEmail;
    @Excel(name = "验厂联系人电话", width = 15)
    private String yclxrTelphone;
    @Excel(name = "船务联系人", width = 15)
    private String cwlxr;
    @Excel(name = "船务联系人邮箱", width = 15)
    private String cwlxrEmail;
    @Excel(name = "船务联系人电话", width = 15)
    private String cwlxrTelphone;
    @Excel(name = "法律联系人", width = 15)
    private String fllxr;
    @Excel(name = "法律联系人邮箱", width = 15)
    private String fllxrEmail;
    @Excel(name = "法律联系人电话", width = 15)
    private String fllxrTelphone;
    @Excel(name = "邮箱", width = 15)
    private String email;
    @Excel(name = "档案编号", width = 15)
    private String daanNum;

    @Column(name = "sheng_fen", nullable = true, length = 50)
    public String getShengFen() {
        return this.shengFen;
    }

    public void setShengFen(String shengFen) {
        this.shengFen = shengFen;
    }

   /* @Column(name = "cheng_shi", nullable = true, length = 50)
    public String getChengShi() {
        return this.chengShi;
    }

    public void setChengShi(String chengShi) {
        this.chengShi = chengShi;
    }

    @Column(name = "pian_qu", nullable = true, length = 50)
    public String getPianQu() {
        return this.pianQu;
    }

    public void setPianQu(String pianQu) {
        this.pianQu = pianQu;
    }*/



    @Column(name = "status", nullable = true, length = 50)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "workphone", nullable = true, length = 50)
    public String getWorkphone() {
        return this.workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

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

    @Column(name = "cus_code", nullable = true, length = 32)
    public String getCusCode() {
        return this.cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    @Column(name = "CUS_TYPE", nullable = true, length = 32)
    public String getCusType() {
        return this.cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    @Column(name = "CUS_FROM", nullable = true, length = 32)
    public String getCusFrom() {
        return this.cusFrom;
    }

    public void setCusFrom(String cusFrom) {
        this.cusFrom = cusFrom;
    }

    @Column(name = "ADDRESS", nullable = true, length = 32)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "TELPHONE", nullable = true, length = 32)
    public String getTelphone() {
        return this.telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "businesse_dept_name", nullable = true, length = 32)
    public String getBusinesseDeptName() {
        return this.businesseDeptName;
    }

    public void setBusinesseDeptName(String businesseDeptName) {
        this.businesseDeptName = businesseDeptName;
    }

    @Column(name = "businesse_dept_id", nullable = true, length = 32)
    public String getBusinesseDeptId() {
        return this.businesseDeptId;
    }

    public void setBusinesseDeptId(String businesseDeptId) {
        this.businesseDeptId = businesseDeptId;
    }

    @Column(name = "businesser", nullable = true, length = 32)
    public String getBusinesser() {
        return this.businesser;
    }

    public void setBusinesser(String businesser) {
        this.businesser = businesser;
    }

    @Column(name = "businesser_name", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
    }

    @Column(name = "tracer", nullable = true, length = 32)
    public String getTracer() {
        return this.tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    @Column(name = "tracer_name", nullable = true, length = 32)
    public String getTracerName() {
        return this.tracerName;
    }

    public void setTracerName(String tracerName) {
        this.tracerName = tracerName;
    }

    @Column(name = "developer", nullable = true, length = 32)
    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Column(name = "developer_name", nullable = true, length = 32)
    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    @Column(name = "business_type", nullable = true, length = 32)
    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }


    @Column(name = "qzywl", nullable = true, length = 32)
    public String getQzywl() {
        return this.qzywl;
    }

    public void setQzywl(String qzywl) {
        this.qzywl = qzywl;
    }


    @Column(name = "relation_date", nullable = true, length = 32)
    public String getRelationDate() {
        return this.relationDate;
    }

    public void setRelationDate(String relationDate) {
        this.relationDate = relationDate;
    }

    @Column(name = "guo_jia", nullable = true, length = 32)
    public String getGuoJia() {
        return this.guoJia;
    }

    public void setGuoJia(String guoJia) {
        this.guoJia = guoJia;
    }

    @Column(name = "zlxr", nullable = true, length = 32)
    public String getZlxr() {
        return this.zlxr;
    }

    public void setZlxr(String zlxr) {
        this.zlxr = zlxr;
    }

    @Column(name = "ywlxr2", nullable = true, length = 32)
    public String getYwlxr2() {
        return this.ywlxr2;
    }

    public void setYwlxr2(String ywlxr2) {
        this.ywlxr2 = ywlxr2;
    }

    @Column(name = "ywlxr3", nullable = true, length = 32)
    public String getYwlxr3() {
        return this.ywlxr3;
    }

    public void setYwlxr3(String ywlxr3) {
        this.ywlxr3 = ywlxr3;
    }

    @Column(name = "bzlxr", nullable = true, length = 32)
    public String getBzlxr() {
        return this.bzlxr;
    }

    public void setBzlxr(String bzlxr) {
        this.bzlxr = bzlxr;
    }

    @Column(name = "cslxr", nullable = true, length = 32)
    public String getCslxr() {
        return this.cslxr;
    }

    public void setCslxr(String cslxr) {
        this.cslxr = cslxr;
    }

    @Column(name = "zllxr", nullable = true, length = 32)
    public String getZllxr() {
        return this.zllxr;
    }

    public void setZllxr(String zllxr) {
        this.zllxr = zllxr;
    }

    @Column(name = "yclxr", nullable = true, length = 32)
    public String getYclxr() {
        return this.yclxr;
    }

    public void setYclxr(String yclxr) {
        this.yclxr = yclxr;
    }

    @Column(name = "cwlxr", nullable = true, length = 32)
    public String getCwlxr() {
        return this.cwlxr;
    }

    public void setCwlxr(String cwlxr) {
        this.cwlxr = cwlxr;
    }

    @Column(name = "fllxr", nullable = true, length = 32)
    public String getFllxr() {
        return this.fllxr;
    }

    public void setFllxr(String fllxr) {
        this.fllxr = fllxr;
    }

    @Column(name = "email", nullable = true, length = 32)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "daan_num", nullable = true, length = 32)
    public String getDaanNum() {
        return this.daanNum;
    }

    public void setDaanNum(String daanNum) {
        this.daanNum = daanNum;
    }

    @Column(name = "ywlxr4", nullable = true, length = 32)
    public String getYwlxr4() {
        return ywlxr4;
    }

    public void setYwlxr4(String ywlxr4) {
        this.ywlxr4 = ywlxr4;
    }

    @Column(name = "ywlxr5", nullable = true, length = 32)
    public String getYwlxr5() {
        return ywlxr5;
    }

    public void setYwlxr5(String ywlxr5) {
        this.ywlxr5 = ywlxr5;
    }

    @Column(name = "bzlxr_email", nullable = true, length = 32)
    public String getBzlxrEmail() {
        return bzlxrEmail;
    }

    public void setBzlxrEmail(String bzlxrEmail) {
        this.bzlxrEmail = bzlxrEmail;
    }

    @Column(name = "bzlxr_telphone", nullable = true, length = 32)
    public String getBzlxrTelphone() {
        return bzlxrTelphone;
    }

    public void setBzlxrTelphone(String bzlxrTelphone) {
        this.bzlxrTelphone = bzlxrTelphone;
    }

    @Column(name = "cslxr_email", nullable = true, length = 32)
    public String getCslxrEmail() {
        return cslxrEmail;
    }

    public void setCslxrEmail(String cslxrEmail) {
        this.cslxrEmail = cslxrEmail;
    }

    @Column(name = "cslxr_telphone", nullable = true, length = 32)
    public String getCslxrTelphone() {
        return cslxrTelphone;
    }

    public void setCslxrTelphone(String cslxrTelphone) {
        this.cslxrTelphone = cslxrTelphone;
    }

    @Column(name = "zllxr_email", nullable = true, length = 32)
    public String getZllxrEmail() {
        return zllxrEmail;
    }

    public void setZllxrEmail(String zllxrEmail) {
        this.zllxrEmail = zllxrEmail;
    }

    @Column(name = "zllxr_telphone", nullable = true, length = 32)
    public String getZllxrTelphone() {
        return zllxrTelphone;
    }

    public void setZllxrTelphone(String zllxrTelphone) {
        this.zllxrTelphone = zllxrTelphone;
    }

    @Column(name = "yclxr_email", nullable = true, length = 32)
    public String getYclxrEmail() {
        return yclxrEmail;
    }

    public void setYclxrEmail(String yclxrEmail) {
        this.yclxrEmail = yclxrEmail;
    }

    @Column(name = "yclxr_telphone", nullable = true, length = 32)
    public String getYclxrTelphone() {
        return yclxrTelphone;
    }

    public void setYclxrTelphone(String yclxrTelphone) {
        this.yclxrTelphone = yclxrTelphone;
    }

    @Column(name = "cwlxr_email", nullable = true, length = 32)
    public String getCwlxrEmail() {
        return cwlxrEmail;
    }

    public void setCwlxrEmail(String cwlxrEmail) {
        this.cwlxrEmail = cwlxrEmail;
    }

    @Column(name = "cwlxr_telphone", nullable = true, length = 32)
    public String getCwlxrTelphone() {
        return cwlxrTelphone;
    }

    public void setCwlxrTelphone(String cwlxrTelphone) {
        this.cwlxrTelphone = cwlxrTelphone;
    }

    @Column(name = "fllxr_email", nullable = true, length = 32)
    public String getFllxrEmail() {
        return fllxrEmail;
    }

    public void setFllxrEmail(String fllxrEmail) {
        this.fllxrEmail = fllxrEmail;
    }

    @Column(name = "fllxr_telphone", nullable = true, length = 32)
    public String getFllxrTelphone() {
        return fllxrTelphone;
    }

    public void setFllxrTelphone(String fllxrTelphone) {
        this.fllxrTelphone = fllxrTelphone;
    }

    @Column(name = "cus_type_name", nullable = true, length = 32)
    public String getCusTypeName() {
        return cusTypeName;
    }

    public void setCusTypeName(String cusTypeName) {
        this.cusTypeName = cusTypeName;
    }

    @Column(name = "main_content", nullable = true, length = 32)
    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    @Column(name = "ywlxr2_email", nullable = true, length = 32)
    public String getYwlxr2Email() {
        return ywlxr2Email;
    }

    public void setYwlxr2Email(String ywlxr2Email) {
        this.ywlxr2Email = ywlxr2Email;
    }

    @Column(name = "ywlxr2_telphone", nullable = true, length = 32)
    public String getYwlxr2Telphone() {
        return ywlxr2Telphone;
    }

    public void setYwlxr2Telphone(String ywlxr2Telphone) {
        this.ywlxr2Telphone = ywlxr2Telphone;
    }

    @Column(name = "ywlxr3_email", nullable = true, length = 32)
    public String getYwlxr3Email() {
        return ywlxr3Email;
    }

    public void setYwlxr3Email(String ywlxr3Email) {
        this.ywlxr3Email = ywlxr3Email;
    }

    @Column(name = "ywlxr3_telphone", nullable = true, length = 32)
    public String getYwlxr3Telphone() {
        return ywlxr3Telphone;
    }

    public void setYwlxr3Telphone(String ywlxr3Telphone) {
        this.ywlxr3Telphone = ywlxr3Telphone;
    }

    @Column(name = "ywlxr4_email", nullable = true, length = 32)
    public String getYwlxr4Email() {
        return ywlxr4Email;
    }

    public void setYwlxr4Email(String ywlxr4Email) {
        this.ywlxr4Email = ywlxr4Email;
    }

    @Column(name = "ywlxr4_telphone", nullable = true, length = 32)
    public String getYwlxr4Telphone() {
        return ywlxr4Telphone;
    }

    public void setYwlxr4Telphone(String ywlxr4Telphone) {
        this.ywlxr4Telphone = ywlxr4Telphone;
    }

    @Column(name = "ywlxr5_email", nullable = true, length = 32)
    public String getYwlxr5Email() {
        return ywlxr5Email;
    }

    public void setYwlxr5Email(String ywlxr5Email) {
        this.ywlxr5Email = ywlxr5Email;
    }

    @Column(name = "ywlxr5_telphone", nullable = true, length = 32)
    public String getYwlxr5Telphone() {
        return ywlxr5Telphone;
    }

    public void setYwlxr5Telphone(String ywlxr5Telphone) {
        this.ywlxr5Telphone = ywlxr5Telphone;
    }
}
