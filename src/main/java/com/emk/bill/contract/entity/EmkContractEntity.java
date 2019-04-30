package com.emk.bill.contract.entity;

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
@Table(name = "emk_contract", schema = "")
public class EmkContractEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    /**状态*/
    private String state;
    @Excel(name = "业务员")
    private String businesser;
    private String businesserName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "合同编号")
    private String htNum;
    @Excel(name = "甲方")
    private String partyA;
    private String partyAId;
    @Excel(name = "乙方")
    private String partyB;
    private String partyBId;
    @Excel(name = "FOB")
    private String fob;
    @Excel(name = "交货地点")
    private String place;
    @Excel(name = "仓库")
    private String boundName;
    @Excel(name = "原产地")
    private String ycd;
    @Excel(name = "付款方式")
    private String payType;
    @Excel(name = "授权代表")
    private String sqdb;
    @Excel(name = "地址")
    private String address;
    @Excel(name = "电话")
    private String telphone;
    @Excel(name = "签定日期")
    private String signDate;
    @Excel(name = "乙方授权代表")
    private String ysqdb;
    @Excel(name = "乙方地址")
    private String yaddress;
    @Excel(name = "乙方电话")
    private String ytelphone;
    @Excel(name = "签定日期")
    private String ysignDate;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "总数量")
    private Integer sumTotal;
    @Excel(name = "总金额")
    private Double sumMoney;
    @Excel(name = "交货期")
    private String recevieDate;
    @Excel(name="出厂价")
    private String outPrice;
    private String formType;


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

    @Column(name = "HT_NUM", nullable = true, length = 32)
    public String getHtNum() {
        return this.htNum;
    }

    public void setHtNum(String htNum) {
        this.htNum = htNum;
    }

    @Column(name = "PARTY_A", nullable = true, length = 32)
    public String getPartyA() {
        return this.partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    @Column(name = "PARTY_A_ID", nullable = true, length = 32)
    public String getPartyAId() {
        return this.partyAId;
    }

    public void setPartyAId(String partyAId) {
        this.partyAId = partyAId;
    }

    @Column(name = "PARTY_B", nullable = true, length = 32)
    public String getPartyB() {
        return this.partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    @Column(name = "PARTY_B_ID", nullable = true, length = 32)
    public String getPartyBId() {
        return this.partyBId;
    }

    public void setPartyBId(String partyBId) {
        this.partyBId = partyBId;
    }

    @Column(name = "FOB", nullable = true, length = 32)
    public String getFob() {
        return this.fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    @Column(name = "PLACE", nullable = true, length = 32)
    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "YCD", nullable = true, length = 32)
    public String getYcd() {
        return this.ycd;
    }

    public void setYcd(String ycd) {
        this.ycd = ycd;
    }

    @Column(name = "PAY_TYPE", nullable = true, length = 32)
    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Column(name = "SQDB", nullable = true, length = 32)
    public String getSqdb() {
        return this.sqdb;
    }

    public void setSqdb(String sqdb) {
        this.sqdb = sqdb;
    }

    @Column(name = "ADDRESS", nullable = true, length = 512)
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

    @Column(name = "SIGN_DATE", nullable = true, length = 32)
    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    @Column(name = "SUM_TOTAL", nullable = true, length = 32)
    public Integer getSumTotal() {
        return this.sumTotal;
    }

    public void setSumTotal(Integer sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Column(name = "SUM_MONEY", nullable = true, scale = 2, length = 32)
    public Double getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "RECEVIE_DATE", nullable = true, length = 32)
    public String getRecevieDate() {
        return this.recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }

    @Column(name ="STATE",nullable=true,length=32)
    public String getState(){
        return this.state;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  状态
     */
    public void setState(String state){
        this.state = state;
    }

    @Column(name="out_price", nullable=true, length=32)
    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    @Column(name="ysqdb", nullable=true, length=32)
    public String getYsqdb() {
        return ysqdb;
    }

    public void setYsqdb(String ysqdb) {
        this.ysqdb = ysqdb;
    }

    @Column(name="yaddress", nullable=true, length=32)
    public String getYaddress() {
        return yaddress;
    }

    public void setYaddress(String yaddress) {
        this.yaddress = yaddress;
    }

    @Column(name="ytelphone", nullable=true, length=32)
    public String getYtelphone() {
        return ytelphone;
    }

    public void setYtelphone(String ytelphone) {
        this.ytelphone = ytelphone;
    }

    @Column(name="ysign_date", nullable=true, length=32)
    public String getYsignDate() {
        return ysignDate;
    }

    public void setYsignDate(String ysignDate) {
        this.ysignDate = ysignDate;
    }

    @Column(name="bound_name", nullable=true, length=32)
    public String getBoundName() {
        return boundName;
    }

    public void setBoundName(String boundName) {
        this.boundName = boundName;
    }

    @Column(name = "form_type", nullable = true, length = 32)
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
}
