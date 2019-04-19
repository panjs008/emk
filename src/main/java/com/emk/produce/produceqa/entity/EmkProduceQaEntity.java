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
public class EmkProduceQaEntity implements Serializable {
    @Excel(name = "业务员")
    private String businesser;
    private String businesserName;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "生产跟单员")
    private String developer;
    @Excel(name = "生产跟单员ID")
    private String developerName;
    @Excel(name = "工艺种类")
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "总数量")
    private Integer sumTotal;
    @Excel(name = "出厂日期")
    private String outDate;
    @Excel(name = "距交期剩余天数")
    private Integer levelDays;
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "生产问题函号")
    private String qaNo;
    @Excel(name = "发函日期")
    private String qaDate;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "生产合同号")
    private String produceNum;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商Code")
    private String gysCode;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "收函部门")
    private String recevieDeptName;
    @Excel(name = "收函部门代码")
    private String recevieDeptCode;
    @Excel(name = "收函人")
    private String recevier;
    @Excel(name = "抄送部门")
    private String copyDeptName;
    @Excel(name = "抄送部门代码")
    private String copyDeptCode;
    @Excel(name = "抄送收函人")
    private String copyer;
    @Excel(name = "问题描述")
    private String qaDesc;
    @Excel(name = "问题责任人")
    private String qazrer;
    @Excel(name = "发件人")
    private String fjer;
    @Excel(name = "业务审核")
    private String ywsher;
    @Excel(name = "业务审核ID")
    private String ywsherId;
    @Excel(name = "经济损失")
    private String loss;
    @Excel(name = "解决方案")
    private String solve;
    @Excel(name = "方案执行人")
    private String solver;
    @Excel(name = "方案执行人ID")
    private String solverId;
    @Excel(name = "方案批准人")
    private String solverPzer;
    @Excel(name = "方案批准人ID")
    private String solverPzerId;

    @Excel(name = "相关抄送人意见")
    private String copyerAdvice;
    @Excel(name = "财务意见")
    private String cwAdvice;
    @Excel(name = "财务批准人")
    private String cwPzer;
    @Excel(name = "财务批准人ID")
    private String cwPzerId;
    @Excel(name = "总经理意见")
    private String zjlAdvice;
    @Excel(name = "生产问题函状态")
    private String qaState;
    private String scanUrl;
    @Excel(name = "扫描件")
    private String scanName;
    @Excel(name = "收函人")
    private String recevierUserNames;
    @Excel(name = "抄送收函人")
    private String copyerUserNames;
    @Excel(name = "发函部门")
    private String sendDeptName;
    @Excel(name = "发函部门代码")
    private String sendDeptCode;
    @Excel(name = "发函人")
    private String sender;
    @Excel(name = "发函人")
    private String senderUserNames;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
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

    @Column(name = "qazrer", nullable = true, length = 32)
    public String getQazrer() {
        return qazrer;
    }

    public void setQazrer(String qazrer) {
        this.qazrer = qazrer;
    }

    @Column(name = "fjer", nullable = true, length = 32)
    public String getFjer() {
        return fjer;
    }

    public void setFjer(String fjer) {
        this.fjer = fjer;
    }

    @Column(name = "ywsher", nullable = true, length = 32)
    public String getYwsher() {
        return ywsher;
    }

    public void setYwsher(String ywsher) {
        this.ywsher = ywsher;
    }

    @Column(name = "ywsher_id", nullable = true, length = 32)
    public String getYwsherId() {
        return ywsherId;
    }

    public void setYwsherId(String ywsherId) {
        this.ywsherId = ywsherId;
    }

    @Column(name = "solver", nullable = true, length = 32)
    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    @Column(name = "solver_id", nullable = true, length = 32)
    public String getSolverId() {
        return solverId;
    }

    public void setSolverId(String solverId) {
        this.solverId = solverId;
    }

    @Column(name = "solver_pzer", nullable = true, length = 32)
    public String getSolverPzer() {
        return solverPzer;
    }

    public void setSolverPzer(String solverPzer) {
        this.solverPzer = solverPzer;
    }

    @Column(name = "solver_pzer_id", nullable = true, length = 32)
    public String getSolverPzerId() {
        return solverPzerId;
    }

    public void setSolverPzerId(String solverPzerId) {
        this.solverPzerId = solverPzerId;
    }

    @Column(name = "cw_pzer", nullable = true, length = 32)
    public String getCwPzer() {
        return cwPzer;
    }

    public void setCwPzer(String cwPzer) {
        this.cwPzer = cwPzer;
    }

    @Column(name = "cw_pzer_id", nullable = true, length = 32)
    public String getCwPzerId() {
        return cwPzerId;
    }

    public void setCwPzerId(String cwPzerId) {
        this.cwPzerId = cwPzerId;
    }
}
