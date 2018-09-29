package com.service.custom.entity;

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
@Table(name = "ymk_custom", schema = "")
public class YmkCustomEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "省")
    private String shengFen;
    @Excel(name = "城市")
    private String chengShi;
    @Excel(name = "片区")
    private String pianQu;
    @Excel(name = "客户编码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;
    @Excel(name = "客户简称", width = 15)
    private String cusCode;
    @Excel(name = "客户类型", width = 15)
    private String cusType;
    @Excel(name = "客户来源", width = 15)
    private String cusFrom;
    @Excel(name = "客户地址", width = 15)
    private String address;
    @Excel(name = "联系人电话", width = 15)
    private String telphone;
    @Excel(name = "办公传真", width = 15)
    private String fax;
    @Excel(name = "公司网址", width = 15)
    private String companyNet;
    @Excel(name = "备注", width = 15)
    private String remark;
    private String sysCompanyCode;
    @Excel(name = "经营年限")
    private String manageYear;
    @Excel(name = "行业")
    private String hangye;
    @Excel(name = "注册地址")
    private String regAddress;
    @Excel(name = "员工人数")
    private String people;
    @Excel(name = "营业执照")
    private String manageImage;
    @Excel(name = "营业执照URL")
    private String manageImageUrl;
    @Excel(name = "主营业务")
    private String mainContent;
    @Excel(name = "状态")
    private String status;
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
    @Excel(name = "实际业务量", width = 15)
    private String ywl;
    @Excel(name = "单位", width = 15)
    private String unit;
    @Excel(name = "实际业务金额", width = 15)
    private String ywMoney;
    @Excel(name = "币种", width = 15)
    private String bz;
    @Excel(name = "潜在业务量/年", width = 15)
    private String qzywl;
    @Excel(name = "单位", width = 15)
    private String qzunit;
    @Excel(name = "潜在业务金额/年", width = 15)
    private String qzywMoney;
    @Excel(name = "币种", width = 15)
    private String qzbz;
    @Excel(name = "建立商业关系时间", width = 15)
    private String relationDate;
    @Excel(name = "国家", width = 15)
    private String guoJia;
    @Excel(name = "主联系人", width = 15)
    private String zlxr;
    @Excel(name = "业务联系人一", width = 15)
    private String ywlxr1;
    @Excel(name = "业务联系人二", width = 15)
    private String ywlxr2;
    @Excel(name = "业务联系人三", width = 15)
    private String ywlxr3;
    @Excel(name = "试身联系人", width = 15)
    private String sslxr;
    @Excel(name = "包装联系人", width = 15)
    private String bzlxr;
    @Excel(name = "测试联系人", width = 15)
    private String cslxr;
    @Excel(name = "质量联系人", width = 15)
    private String zllxr;
    @Excel(name = "验厂联系人", width = 15)
    private String yclxr;
    @Excel(name = "船务联系人", width = 15)
    private String cwlxr;
    @Excel(name = "法律联系人", width = 15)
    private String fllxr;
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

    @Column(name = "cheng_shi", nullable = true, length = 50)
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
    }

    @Column(name = "manage_year", nullable = true, length = 50)
    public String getManageYear() {
        return this.manageYear;
    }

    public void setManageYear(String manageYear) {
        this.manageYear = manageYear;
    }

    @Column(name = "hangye", nullable = true, length = 50)
    public String getHangye() {
        return this.hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    @Column(name = "reg_address", nullable = true, length = 50)
    public String getRegAddress() {
        return this.regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    @Column(name = "people", nullable = true, length = 50)
    public String getPeople() {
        return this.people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Column(name = "manage_image", nullable = true, length = 50)
    public String getManageImage() {
        return this.manageImage;
    }

    public void setManageImage(String manageImage) {
        this.manageImage = manageImage;
    }

    @Column(name = "manage_image_url", nullable = true, length = 50)
    public String getManageImageUrl() {
        return this.manageImageUrl;
    }

    public void setManageImageUrl(String manageImageUrl) {
        this.manageImageUrl = manageImageUrl;
    }

    @Column(name = "main_content", nullable = true, length = 50)
    public String getMainContent() {
        return this.mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

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

    @Column(name = "FAX", nullable = true, length = 32)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "COMPANY_NET", nullable = true, length = 32)
    public String getCompanyNet() {
        return this.companyNet;
    }

    public void setCompanyNet(String companyNet) {
        this.companyNet = companyNet;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "SYS_COMPANY_CODE", nullable = true, length = 32)
    public String getSysCompanyCode() {
        return this.sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
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

    @Column(name = "ywl", nullable = true, length = 32)
    public String getYwl() {
        return this.ywl;
    }

    public void setYwl(String ywl) {
        this.ywl = ywl;
    }

    @Column(name = "unit", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "yw_money", nullable = true, length = 32)
    public String getYwMoney() {
        return this.ywMoney;
    }

    public void setYwMoney(String ywMoney) {
        this.ywMoney = ywMoney;
    }

    @Column(name = "bz", nullable = true, length = 32)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "qzywl", nullable = true, length = 32)
    public String getQzywl() {
        return this.qzywl;
    }

    public void setQzywl(String qzywl) {
        this.qzywl = qzywl;
    }

    @Column(name = "qzunit", nullable = true, length = 32)
    public String getQzunit() {
        return this.qzunit;
    }

    public void setQzunit(String qzunit) {
        this.qzunit = qzunit;
    }

    @Column(name = "qzyw_money", nullable = true, length = 32)
    public String getQzywMoney() {
        return this.qzywMoney;
    }

    public void setQzywMoney(String qzywMoney) {
        this.qzywMoney = qzywMoney;
    }

    @Column(name = "qzbz", nullable = true, length = 32)
    public String getQzbz() {
        return this.qzbz;
    }

    public void setQzbz(String qzbz) {
        this.qzbz = qzbz;
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

    @Column(name = "ywlxr1", nullable = true, length = 32)
    public String getYwlxr1() {
        return this.ywlxr1;
    }

    public void setYwlxr1(String ywlxr1) {
        this.ywlxr1 = ywlxr1;
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

    @Column(name = "sslxr", nullable = true, length = 32)
    public String getSslxr() {
        return this.sslxr;
    }

    public void setSslxr(String sslxr) {
        this.sslxr = sslxr;
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
}
