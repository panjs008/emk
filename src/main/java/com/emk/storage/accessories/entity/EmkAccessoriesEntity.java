package com.emk.storage.accessories.entity;

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
@Table(name = "emk_accessories", schema = "")
public class EmkAccessoriesEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "需求单号", width = 15)
    private String materialNo;
    @Excel(name = "提交日期", width = 15)
    private String kdDate;
    @Excel(name = "业务员", width = 15)
    private String businesser;
    @Excel(name = "业务员ID", width = 15)
    private String businesserName;
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;
    @Excel(name = "工艺种类", width = 15)
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类", width = 15)
    private String proTypeName;
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "工厂", width = 15)
    private String factoryName;
    @Excel(name = "工厂Code", width = 15)
    private String factoryCode;
    @Excel(name = "业务部门", width = 15)
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    private String developerName;
    @Excel(name = "采购员", width = 15)
    private String cger;
    private String cgerName;
    @Excel(name = "样品交期", width = 15)
    private String ypjqDate;
    @Excel(name = "距样品交期剩余天数", width = 15)
    private Integer leaveYpjqDays;
    @Excel(name = "距大货交期剩余天数", width = 15)
    private Integer leaveDhjqDays;
    @Excel(name = "是否有订单", width = 15)
    private String isOrder;
    @Excel(name = "大货交期", width = 15)
    private String dhjqDate;
    @Excel(name = "是否现有原料面料", width = 15)
    private String isHave;
    @Excel(name = "版次", width = 15)
    private String version;
    @Excel(name = "打样原因", width = 15)
    private String sampleReason;
    @Excel(name = "需求开发交期", width = 15)
    private String requiredJqDate;
    @Excel(name = "完成时间剩余天数", width = 15)
    private Integer leaveFinishDays;
    @Excel(name = "开发单状态", width = 15)
    private String state;
    @Excel(name = "技术员意见", width = 15)
    private String jsyAdvice;
    @Excel(name = "跟单员意见", width = 15)
    private String developerAdvice;
    @Excel(name = "业务员意见", width = 15)
    private String businesseAdvice;
    @Excel(name = "综合意见", width = 15)
    private String zhAdvice;
    private String customSampleUrl;
    @Excel(name = "图片", width = 15)
    private String customSample;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    @Excel(name = "业务跟单员ID", width = 15)
    private String tracerName;

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

    @Column(name = "MATERIAL_NO", nullable = true, length = 32)
    public String getMaterialNo() {
        return this.materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
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

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "FACTORY_NAME", nullable = true, length = 32)
    public String getFactoryName() {
        return this.factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    @Column(name = "FACTORY_CODE", nullable = true, length = 32)
    public String getFactoryCode() {
        return this.factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
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

    @Column(name = "CGER", nullable = true, length = 32)
    public String getCger() {
        return this.cger;
    }

    public void setCger(String cger) {
        this.cger = cger;
    }

    @Column(name = "CGER_NAME", nullable = true, length = 32)
    public String getCgerName() {
        return this.cgerName;
    }

    public void setCgerName(String cgerName) {
        this.cgerName = cgerName;
    }

    @Column(name = "YPJQ_DATE", nullable = true, length = 32)
    public String getYpjqDate() {
        return this.ypjqDate;
    }

    public void setYpjqDate(String ypjqDate) {
        this.ypjqDate = ypjqDate;
    }

    @Column(name = "LEAVE_YPJQ_DAYS", nullable = true, length = 32)
    public Integer getLeaveYpjqDays() {
        return this.leaveYpjqDays;
    }

    public void setLeaveYpjqDays(Integer leaveYpjqDays) {
        this.leaveYpjqDays = leaveYpjqDays;
    }

    @Column(name = "LEAVE_DHJQ_DAYS", nullable = true, length = 32)
    public Integer getLeaveDhjqDays() {
        return this.leaveDhjqDays;
    }

    public void setLeaveDhjqDays(Integer leaveDhjqDays) {
        this.leaveDhjqDays = leaveDhjqDays;
    }

    @Column(name = "IS_ORDER", nullable = true, length = 32)
    public String getIsOrder() {
        return this.isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    @Column(name = "DHJQ_DATE", nullable = true, length = 32)
    public String getDhjqDate() {
        return this.dhjqDate;
    }

    public void setDhjqDate(String dhjqDate) {
        this.dhjqDate = dhjqDate;
    }

    @Column(name = "IS_HAVE", nullable = true, length = 32)
    public String getIsHave() {
        return this.isHave;
    }

    public void setIsHave(String isHave) {
        this.isHave = isHave;
    }

    @Column(name = "VERSION", nullable = true, length = 32)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "SAMPLE_REASON", nullable = true, length = 32)
    public String getSampleReason() {
        return this.sampleReason;
    }

    public void setSampleReason(String sampleReason) {
        this.sampleReason = sampleReason;
    }

    @Column(name = "REQUIRED_JQ_DATE", nullable = true, length = 32)
    public String getRequiredJqDate() {
        return this.requiredJqDate;
    }

    public void setRequiredJqDate(String requiredJqDate) {
        this.requiredJqDate = requiredJqDate;
    }

    @Column(name = "LEAVE_FINISH_DAYS", nullable = true, length = 32)
    public Integer getLeaveFinishDays() {
        return this.leaveFinishDays;
    }

    public void setLeaveFinishDays(Integer leaveFinishDays) {
        this.leaveFinishDays = leaveFinishDays;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "JSY_ADVICE", nullable = true, length = 256)
    public String getJsyAdvice() {
        return this.jsyAdvice;
    }

    public void setJsyAdvice(String jsyAdvice) {
        this.jsyAdvice = jsyAdvice;
    }

    @Column(name = "DEVELOPER_ADVICE", nullable = true, length = 256)
    public String getDeveloperAdvice() {
        return this.developerAdvice;
    }

    public void setDeveloperAdvice(String developerAdvice) {
        this.developerAdvice = developerAdvice;
    }

    @Column(name = "BUSINESSE_ADVICE", nullable = true, length = 256)
    public String getBusinesseAdvice() {
        return this.businesseAdvice;
    }

    public void setBusinesseAdvice(String businesseAdvice) {
        this.businesseAdvice = businesseAdvice;
    }

    @Column(name = "ZH_ADVICE", nullable = true, length = 32)
    public String getZhAdvice() {
        return this.zhAdvice;
    }

    public void setZhAdvice(String zhAdvice) {
        this.zhAdvice = zhAdvice;
    }

    @Column(name = "CUSTOM_SAMPLE_URL", nullable = true, length = 256)
    public String getCustomSampleUrl() {
        return this.customSampleUrl;
    }

    public void setCustomSampleUrl(String customSampleUrl) {
        this.customSampleUrl = customSampleUrl;
    }

    @Column(name = "CUSTOM_SAMPLE", nullable = true, length = 32)
    public String getCustomSample() {
        return this.customSample;
    }

    public void setCustomSample(String customSample) {
        this.customSample = customSample;
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
}
