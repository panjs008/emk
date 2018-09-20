package com.emk.produce.invoices.entity;

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
@Table(name = "emk_invoices", schema = "")
public class EmkInvoicesEntity
        implements Serializable {
    @Excel(name = "业务员", width = 15)
    private String businesser;
    @Excel(name = "业务员ID", width = 15)
    private String businesserName;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    @Excel(name = "业务跟单员ID", width = 15)
    private String tracerName;
    @Excel(name = "业务部门", width = 15)
    private String businesseDeptName;
    @Excel(name = "业务部门ID", width = 15)
    private String businesseDeptId;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    @Excel(name = "生产跟单员ID", width = 15)
    private String developerName;
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "出厂日期", width = 15)
    private String outDate;
    @Excel(name = "主键", width = 15)
    private String id;
    @Excel(name = "创建人名称", width = 15)
    private String createName;
    @Excel(name = "创建人登录名称", width = 15)
    private String createBy;
    @Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
    private Date createDate;
    @Excel(name = "所属部门", width = 15)
    private String sysOrgCode;
    @Excel(name = "订单号", width = 15)
    private String orderNo;
    @Excel(name = "船务员", width = 15)
    private String cwyer;
    @Excel(name = "生产方名称", width = 15)
    private String scfmc;
    @Excel(name = "地址", width = 15)
    private String address;
    @Excel(name = "法定代表签名", width = 15)
    private String signer;
    @Excel(name = "签名日期", width = 15)
    private String signDate;
    @Excel(name = "发票编号", width = 15)
    private String fpbh;
    @Excel(name = "发票日期", width = 15)
    private String fpDate;
    @Excel(name = "币种", width = 15)
    private String bz;
    @Excel(name = "总金额", width = 15)
    private String sumMoney;
    @Excel(name = "收款方式", width = 15)
    private String skfs;
    @Excel(name = "提单号", width = 15)
    private String tdNum;
    @Excel(name = "提单状态", width = 15)
    private String tdzt;
    @Excel(name = "地址", width = 15)
    private String signAddress;
    @Excel(name = "电话", width = 15)
    private String signTelphone;
    @Excel(name = "订舱通知单号", width = 15)
    private String cargoNo;
    @Excel(name = "离厂放行条号", width = 15)
    private String levealFactoryNo;
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;

    @Column(name = "BUSINESSER", nullable = true, length = 32)
    public String getBusinesser() {
        return this.businesser;
    }

    public void setBusinesser(String businesser) {
        this.businesser = businesser;
    }

    @Column(name = "BUSINESSER_NAME", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
    }

    @Column(name = "TRACER", nullable = true, length = 32)
    public String getTracer() {
        return this.tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    @Column(name = "TRACER_NAME", nullable = true, length = 32)
    public String getTracerName() {
        return this.tracerName;
    }

    public void setTracerName(String tracerName) {
        this.tracerName = tracerName;
    }

    @Column(name = "BUSINESSE_DEPT_NAME", nullable = true, length = 32)
    public String getBusinesseDeptName() {
        return this.businesseDeptName;
    }

    public void setBusinesseDeptName(String businesseDeptName) {
        this.businesseDeptName = businesseDeptName;
    }

    @Column(name = "BUSINESSE_DEPT_ID", nullable = true, length = 32)
    public String getBusinesseDeptId() {
        return this.businesseDeptId;
    }

    public void setBusinesseDeptId(String businesseDeptId) {
        this.businesseDeptId = businesseDeptId;
    }

    @Column(name = "DEVELOPER", nullable = true, length = 32)
    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Column(name = "DEVELOPER_NAME", nullable = true, length = 32)
    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
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

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "CWYER", nullable = true, length = 32)
    public String getCwyer() {
        return this.cwyer;
    }

    public void setCwyer(String cwyer) {
        this.cwyer = cwyer;
    }

    @Column(name = "SCFMC", nullable = true, length = 32)
    public String getScfmc() {
        return this.scfmc;
    }

    public void setScfmc(String scfmc) {
        this.scfmc = scfmc;
    }

    @Column(name = "ADDRESS", nullable = true, length = 32)
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

    @Column(name = "BZ", nullable = true, length = 32)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "SUM_MONEY", nullable = true, length = 32)
    public String getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "SKFS", nullable = true, length = 32)
    public String getSkfs() {
        return this.skfs;
    }

    public void setSkfs(String skfs) {
        this.skfs = skfs;
    }

    @Column(name = "TD_NUM", nullable = true, length = 32)
    public String getTdNum() {
        return this.tdNum;
    }

    public void setTdNum(String tdNum) {
        this.tdNum = tdNum;
    }

    @Column(name = "TDZT", nullable = true, length = 32)
    public String getTdzt() {
        return this.tdzt;
    }

    public void setTdzt(String tdzt) {
        this.tdzt = tdzt;
    }

    @Column(name = "SIGN_ADDRESS", nullable = true, length = 32)
    public String getSignAddress() {
        return this.signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    @Column(name = "SIGN_TELPHONE", nullable = true, length = 32)
    public String getSignTelphone() {
        return this.signTelphone;
    }

    public void setSignTelphone(String signTelphone) {
        this.signTelphone = signTelphone;
    }

    @Column(name = "CARGO_NO", nullable = true, length = 32)
    public String getCargoNo() {
        return this.cargoNo;
    }

    public void setCargoNo(String cargoNo) {
        this.cargoNo = cargoNo;
    }

    @Column(name = "LEVEAL_FACTORY_NO", nullable = true, length = 32)
    public String getLevealFactoryNo() {
        return this.levealFactoryNo;
    }

    public void setLevealFactoryNo(String levealFactoryNo) {
        this.levealFactoryNo = levealFactoryNo;
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
}
