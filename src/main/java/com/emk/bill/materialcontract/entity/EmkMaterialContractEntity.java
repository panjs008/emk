package com.emk.bill.materialcontract.entity;

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
@Table(name = "emk_material_contract", schema = "")
public class EmkMaterialContractEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "业务员", width = 15)
    private String businesser;
    @Excel(name = "业务员ID", width = 15)
    private String businesserName;
    @Excel(name = "业务部门", width = 15)
    private String businesseDeptName;
    @Excel(name = "业务部门ID", width = 15)
    private String businesseDeptId;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    @Excel(name = "业务跟单员ID", width = 15)
    private String tracerName;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    @Excel(name = "生产跟单员ID", width = 15)
    private String developerName;
    @Excel(name = "合同编号", width = 15)
    private String htNum;
    @Excel(name = "订单号", width = 15)
    private String orderNo;
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "总数量", width = 15)
    private Integer sumTotal;
    @Excel(name = "款式大类", width = 15)
    private String proType;
    @Excel(name = "款式大类", width = 15)
    private String proTypeName;
    @Excel(name = "工艺种类", width = 15)
    private String gyzl;
    @Excel(name = "预采购合同号", width = 15)
    private String yhtNum;
    @Excel(name = "生产合同号", width = 15)
    private String produceNum;
    @Excel(name = "供应商", width = 15)
    private String gys;
    @Excel(name = "供应商Code", width = 15)
    private String gysCode;
    @Excel(name = "出货日期", width = 15)
    private String outDate;
    @Excel(name = "最迟到厂日期", width = 15)
    private String limitDate;
    @Excel(name = "距最迟到厂剩余天数", width = 15)
    private Integer leaveLimit;
    @Excel(name = "类型", width = 15)
    private String type;
    @Excel(name = "原料布料状态", width = 15)
    private String mState;
    @Excel(name = "开单日期", width = 15)
    private String kdDate;
    @Excel(name = "审核意见", width = 15)
    private String leadAdvice;
    @Excel(name = "处理意见", width = 15)
    private String financeAdvice;
    @Excel(name = "是否通过", width = 15)
    private String isPass;
    @Excel(name = "审核人ID", width = 15)
    private String leadUserId;
    @Excel(name = "审核人", width = 15)
    private String leader;
    @Excel(name = "财务处理人ID", width = 15)
    private String financeUserId;
    @Excel(name = "财务处理人", width = 15)
    private String financer;
    @Excel(name = "状态", width = 15)
    private String state;
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;

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

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "YHT_NUM", nullable = true, length = 32)
    public String getYhtNum() {
        return this.yhtNum;
    }

    public void setYhtNum(String yhtNum) {
        this.yhtNum = yhtNum;
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

    @Column(name = "OUT_DATE", nullable = true, length = 32)
    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Column(name = "LIMIT_DATE", nullable = true, length = 32)
    public String getLimitDate() {
        return this.limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    @Column(name = "LEAVE_LIMIT", nullable = true, length = 32)
    public Integer getLeaveLimit() {
        return this.leaveLimit;
    }

    public void setLeaveLimit(Integer leaveLimit) {
        this.leaveLimit = leaveLimit;
    }

    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "M_STATE", nullable = true, length = 32)
    public String getMState() {
        return this.mState;
    }

    public void setMState(String mState) {
        this.mState = mState;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }

    @Column(name = "LEAD_ADVICE", nullable = true, length = 256)
    public String getLeadAdvice() {
        return this.leadAdvice;
    }

    public void setLeadAdvice(String leadAdvice) {
        this.leadAdvice = leadAdvice;
    }

    @Column(name = "FINANCE_ADVICE", nullable = true, length = 256)
    public String getFinanceAdvice() {
        return this.financeAdvice;
    }

    public void setFinanceAdvice(String financeAdvice) {
        this.financeAdvice = financeAdvice;
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

    @Column(name = "FINANCE_USER_ID", nullable = true, length = 32)
    public String getFinanceUserId() {
        return this.financeUserId;
    }

    public void setFinanceUserId(String financeUserId) {
        this.financeUserId = financeUserId;
    }

    @Column(name = "FINANCER", nullable = true, length = 32)
    public String getFinancer() {
        return this.financer;
    }

    public void setFinancer(String financer) {
        this.financer = financer;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
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
