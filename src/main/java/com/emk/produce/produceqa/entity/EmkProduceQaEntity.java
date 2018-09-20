package com.emk.produce.produceqa.entity;

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
@Table(name = "emk_produce_qa", schema = "")
public class EmkProduceQaEntity
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
    @Excel(name = "工艺种类", width = 15)
    private String gyzl;
    @Excel(name = "款式大类", width = 15)
    private String proType;
    @Excel(name = "款式大类", width = 15)
    private String proTypeName;
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "总数量", width = 15)
    private Integer sumTotal;
    @Excel(name = "出厂日期", width = 15)
    private String outDate;
    @Excel(name = "距交期剩余天数", width = 15)
    private Integer levelDays;
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
    @Excel(name = "生产问题函号", width = 15)
    private String qaNo;
    @Excel(name = "发函日期", width = 15)
    private String qaDate;
    @Excel(name = "订单号", width = 15)
    private String orderNo;
    @Excel(name = "生产合同号", width = 15)
    private String produceNum;
    @Excel(name = "供应商", width = 15)
    private String gys;
    @Excel(name = "供应商Code", width = 15)
    private String gysCode;
    @Excel(name = "颜色", width = 15)
    private String color;
    @Excel(name = "收函部门", width = 15)
    private String recevieDeptName;
    @Excel(name = "收函部门代码", width = 15)
    private String recevieDeptCode;
    @Excel(name = "收函人", width = 15)
    private String recevier;
    @Excel(name = "抄送部门", width = 15)
    private String copyDeptName;
    @Excel(name = "抄送部门代码", width = 15)
    private String copyDeptCode;
    @Excel(name = "抄送收函人", width = 15)
    private String copyer;
    @Excel(name = "问题描述", width = 15)
    private String qaDesc;
    @Excel(name = "经济损失", width = 15)
    private String loss;
    @Excel(name = "解决方案", width = 15)
    private String solve;
    @Excel(name = "相关抄送人意见", width = 15)
    private String copyerAdvice;
    @Excel(name = "财务意见", width = 15)
    private String cwAdvice;
    @Excel(name = "总经理意见", width = 15)
    private String zjlAdvice;
    @Excel(name = "生产问题函状态", width = 15)
    private String qaState;
    @Excel(name = "扫描件", width = 15)
    private String scanUrl;
    @Excel(name = "扫描件", width = 15)
    private String scanName;
    @Excel(name = "收函人", width = 15)
    private String recevierUserNames;
    @Excel(name = "抄送收函人", width = 15)
    private String copyerUserNames;
    @Excel(name = "发函部门", width = 15)
    private String sendDeptName;
    @Excel(name = "发函部门代码", width = 15)
    private String sendDeptCode;
    @Excel(name = "发函人", width = 15)
    private String sender;
    @Excel(name = "发函人", width = 15)
    private String senderUserNames;
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

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "PRO_TYPE_NAME", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
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

    @Column(name = "OUT_DATE", nullable = true, length = 32)
    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 32)
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

    @Column(name = "QA_NO", nullable = true, length = 32)
    public String getQaNo() {
        return this.qaNo;
    }

    public void setQaNo(String qaNo) {
        this.qaNo = qaNo;
    }

    @Column(name = "QA_DATE", nullable = true, length = 32)
    public String getQaDate() {
        return this.qaDate;
    }

    public void setQaDate(String qaDate) {
        this.qaDate = qaDate;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "PRODUCE_NUM", nullable = true, length = 32)
    public String getProduceNum() {
        return this.produceNum;
    }

    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }

    @Column(name = "GYS", nullable = true, length = 32)
    public String getGys() {
        return this.gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    @Column(name = "GYS_CODE", nullable = true, length = 32)
    public String getGysCode() {
        return this.gysCode;
    }

    public void setGysCode(String gysCode) {
        this.gysCode = gysCode;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "RECEVIE_DEPT_NAME", nullable = true, length = 256)
    public String getRecevieDeptName() {
        return this.recevieDeptName;
    }

    public void setRecevieDeptName(String recevieDeptName) {
        this.recevieDeptName = recevieDeptName;
    }

    @Column(name = "RECEVIE_DEPT_CODE", nullable = true, length = 256)
    public String getRecevieDeptCode() {
        return this.recevieDeptCode;
    }

    public void setRecevieDeptCode(String recevieDeptCode) {
        this.recevieDeptCode = recevieDeptCode;
    }

    @Column(name = "RECEVIER", nullable = true, length = 256)
    public String getRecevier() {
        return this.recevier;
    }

    public void setRecevier(String recevier) {
        this.recevier = recevier;
    }

    @Column(name = "COPY_DEPT_NAME", nullable = true, length = 256)
    public String getCopyDeptName() {
        return this.copyDeptName;
    }

    public void setCopyDeptName(String copyDeptName) {
        this.copyDeptName = copyDeptName;
    }

    @Column(name = "COPY_DEPT_CODE", nullable = true, length = 256)
    public String getCopyDeptCode() {
        return this.copyDeptCode;
    }

    public void setCopyDeptCode(String copyDeptCode) {
        this.copyDeptCode = copyDeptCode;
    }

    @Column(name = "COPYER", nullable = true, length = 256)
    public String getCopyer() {
        return this.copyer;
    }

    public void setCopyer(String copyer) {
        this.copyer = copyer;
    }

    @Column(name = "QA_DESC", nullable = true, length = 256)
    public String getQaDesc() {
        return this.qaDesc;
    }

    public void setQaDesc(String qaDesc) {
        this.qaDesc = qaDesc;
    }

    @Column(name = "LOSS", nullable = true, length = 256)
    public String getLoss() {
        return this.loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    @Column(name = "SOLVE", nullable = true, length = 32)
    public String getSolve() {
        return this.solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    @Column(name = "COPYER_ADVICE", nullable = true, length = 256)
    public String getCopyerAdvice() {
        return this.copyerAdvice;
    }

    public void setCopyerAdvice(String copyerAdvice) {
        this.copyerAdvice = copyerAdvice;
    }

    @Column(name = "CW_ADVICE", nullable = true, length = 256)
    public String getCwAdvice() {
        return this.cwAdvice;
    }

    public void setCwAdvice(String cwAdvice) {
        this.cwAdvice = cwAdvice;
    }

    @Column(name = "ZJL_ADVICE", nullable = true, length = 256)
    public String getZjlAdvice() {
        return this.zjlAdvice;
    }

    public void setZjlAdvice(String zjlAdvice) {
        this.zjlAdvice = zjlAdvice;
    }

    @Column(name = "QA_STATE", nullable = true, length = 32)
    public String getQaState() {
        return this.qaState;
    }

    public void setQaState(String qaState) {
        this.qaState = qaState;
    }

    @Column(name = "SCAN_URL", nullable = true, length = 256)
    public String getScanUrl() {
        return this.scanUrl;
    }

    public void setScanUrl(String scanUrl) {
        this.scanUrl = scanUrl;
    }

    @Column(name = "SCAN_NAME", nullable = true, length = 32)
    public String getScanName() {
        return this.scanName;
    }

    public void setScanName(String scanName) {
        this.scanName = scanName;
    }

    @Column(name = "RECEVIER_USER_NAMES", nullable = true, length = 256)
    public String getRecevierUserNames() {
        return this.recevierUserNames;
    }

    public void setRecevierUserNames(String recevierUserNames) {
        this.recevierUserNames = recevierUserNames;
    }

    @Column(name = "COPYER_USER_NAMES", nullable = true, length = 256)
    public String getCopyerUserNames() {
        return this.copyerUserNames;
    }

    public void setCopyerUserNames(String copyerUserNames) {
        this.copyerUserNames = copyerUserNames;
    }

    @Column(name = "SEND_DEPT_NAME", nullable = true, length = 256)
    public String getSendDeptName() {
        return this.sendDeptName;
    }

    public void setSendDeptName(String sendDeptName) {
        this.sendDeptName = sendDeptName;
    }

    @Column(name = "SEND_DEPT_CODE", nullable = true, length = 256)
    public String getSendDeptCode() {
        return this.sendDeptCode;
    }

    public void setSendDeptCode(String sendDeptCode) {
        this.sendDeptCode = sendDeptCode;
    }

    @Column(name = "SENDER", nullable = true, length = 256)
    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "SENDER_USER_NAMES", nullable = true, length = 256)
    public String getSenderUserNames() {
        return this.senderUserNames;
    }

    public void setSenderUserNames(String senderUserNames) {
        this.senderUserNames = senderUserNames;
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
