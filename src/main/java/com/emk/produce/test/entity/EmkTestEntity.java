package com.emk.produce.test.entity;

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
@Table(name = "emk_test", schema = "")
public class EmkTestEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "测试申请表编号", width = 15)
    private String cssqdh;
    @Excel(name = "业务员", width = 15)
    private String businesser;
    private String businesserName;
    @Excel(name = "业务部门", width = 15)
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    private String developerName;
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;
    @Excel(name = "订单号", width = 15)
    private String orderNo;
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "数量", width = 15)
    private Integer total;
    @Excel(name = "测试种类", width = 15)
    private String testType;
    @Excel(name = "生产合同号", width = 15)
    private String produceNum;

    @Excel(name = "测试样品要求", width = 15)
    private String testRequired;
    @Excel(name = "测试内容", width = 15)
    private String testContent;
    private String testContentUrl;
    @Excel(name = "报告有效期", width = 15)
    private String limitDate;
    @Excel(name = "大货交期", width = 15)
    private String ysDate;
    @Excel(name = "距交期余天数", width = 15)
    private Integer levelDays;
    @Excel(name = "提交日期", width = 15)
    private String kdDate;
    @Excel(name = "测试进度", width = 15)
    private String testJd;
    @Excel(name = "测试样通知日期", width = 15)
    private String testNoticDate;
    @Excel(name = "寄出测试日期", width = 15)
    private String sendDate;
    @Excel(name = "测试报告收到日期", width = 15)
    private String recevieDate;
    @Excel(name = "测试结果", width = 15)
    private String testResult;
    @Excel(name = "测试费付款状态", width = 15)
    private String testState;
    @Excel(name = "测试机构代码", width = 15)
    private String testCode;
    @Excel(name = "测试机构", width = 15)
    private String testOrgName;

    @Excel(name = "审核意见", width = 15)
    private String leadAdvice;
    private String isPass;
    private String leadUserId;
    @Excel(name = "审核人", width = 15)
    private String leader;
    private String state;


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

    @Column(name = "cssqdh", nullable = true, length = 32)
    public String getCssqdh() {
        return cssqdh;
    }

    public void setCssqdh(String cssqdh) {
        this.cssqdh = cssqdh;
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

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "TEST_TYPE", nullable = true, length = 32)
    public String getTestType() {
        return this.testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    @Column(name = "PRODUCE_NUM", nullable = true, length = 32)
    public String getProduceNum() {
        return this.produceNum;
    }

    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }


    @Column(name = "TEST_REQUIRED", nullable = true, length = 256)
    public String getTestRequired() {
        return this.testRequired;
    }

    public void setTestRequired(String testRequired) {
        this.testRequired = testRequired;
    }

    @Column(name = "TEST_CONTENT", nullable = true, length = 32)
    public String getTestContent() {
        return this.testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    @Column(name = "TEST_CONTENT_URL", nullable = true, length = 256)
    public String getTestContentUrl() {
        return this.testContentUrl;
    }

    public void setTestContentUrl(String testContentUrl) {
        this.testContentUrl = testContentUrl;
    }

    @Column(name = "LIMIT_DATE", nullable = true, length = 32)
    public String getLimitDate() {
        return this.limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    @Column(name = "YS_DATE", nullable = true, length = 32)
    public String getYsDate() {
        return this.ysDate;
    }

    public void setYsDate(String ysDate) {
        this.ysDate = ysDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }

    @Column(name = "TEST_JD", nullable = true, length = 32)
    public String getTestJd() {
        return this.testJd;
    }

    public void setTestJd(String testJd) {
        this.testJd = testJd;
    }

    @Column(name = "TEST_NOTIC_DATE", nullable = true, length = 32)
    public String getTestNoticDate() {
        return this.testNoticDate;
    }

    public void setTestNoticDate(String testNoticDate) {
        this.testNoticDate = testNoticDate;
    }

    @Column(name = "SEND_DATE", nullable = true, length = 32)
    public String getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    @Column(name = "RECEVIE_DATE", nullable = true, length = 32)
    public String getRecevieDate() {
        return this.recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }

    @Column(name = "TEST_RESULT", nullable = true, length = 32)
    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name = "TEST_STATE", nullable = true, length = 32)
    public String getTestState() {
        return this.testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
    }

    @Column(name = "TEST_CODE", nullable = true, length = 32)
    public String getTestCode() {
        return this.testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    @Column(name = "TEST_ORG_NAME", nullable = true, length = 32)
    public String getTestOrgName() {
        return this.testOrgName;
    }

    public void setTestOrgName(String testOrgName) {
        this.testOrgName = testOrgName;
    }

    @Column(name = "LEAD_ADVICE", nullable = true, length = 256)
    public String getLeadAdvice() {
        return this.leadAdvice;
    }

    public void setLeadAdvice(String leadAdvice) {
        this.leadAdvice = leadAdvice;
    }

    @Column(name = "IS_PASS", nullable = true, length = 32)
    public String getIsPass() {
        return this.isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    @Column(name = "LEAD_USER_ID", nullable = true, length = 32)
    public String getLeadUserId() {
        return this.leadUserId;
    }

    public void setLeadUserId(String leadUserId) {
        this.leadUserId = leadUserId;
    }

    @Column(name = "LEADER", nullable = true, length = 32)
    public String getLeader() {
        return this.leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Column(name ="STATE",nullable=true,length=32)
    public String getState(){
        return this.state;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  工单状态
     */
    public void setState(String state){
        this.state = state;
    }

}

