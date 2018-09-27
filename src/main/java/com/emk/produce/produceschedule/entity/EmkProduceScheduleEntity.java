package com.emk.produce.produceschedule.entity;

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
@Table(name = "emk_produce_schedule", schema = "")
public class EmkProduceScheduleEntity implements Serializable {
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
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "业务员ID", width = 15)
    private String businesserName;
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
    @Excel(name = "业务部门", width = 15)
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "生产跟单员", width = 15)
    private String developer;
    private String developerName;
    @Excel(name = "业务跟单员", width = 15)
    private String tracer;
    private String tracerName;
    @Excel(name = "合同编号", width = 15)
    private String htNum;
    @Excel(name = "总数量", width = 15)
    private Double sumTotal;
    private String customSampleUrl;
    @Excel(name = "图片", width = 15)
    private String customSample;
    @Excel(name = "生产合同号", width = 15)
    private String produceHtNum;
    @Excel(name = "订单号", width = 15)
    private String orderNo;
    @Excel(name = "原料布料状态", width = 15)
    private String ylblState;
    @Excel(name = "原料布料到厂日期", width = 15)
    private String ylblLimitDate;
    @Excel(name = "距原料到厂剩余天数", width = 15)
    private Integer leavelYlblDay;
    @Excel(name = "缝制辅料状态", width = 15)
    private String fzblState;
    @Excel(name = "缝制辅料到厂日期", width = 15)
    private String fzblLimitDate;
    @Excel(name = "距缝制到厂剩余天数", width = 15)
    private Integer leavelFzblDay;
    @Excel(name = "包装辅料状态", width = 15)
    private String bzblState;
    @Excel(name = "包装辅料到厂日期", width = 15)
    private String bzblLimitDate;
    @Excel(name = "距包装到厂剩余天数", width = 15)
    private Integer leavelBzblDay;
    @Excel(name = "染色状态", width = 15)
    private String ranState;
    @Excel(name = "染色已完成数量", width = 15)
    private String ranFinish;
    @Excel(name = "染色未完成数量", width = 15)
    private String ranUnfinish;
    @Excel(name = "裁剪状态", width = 15)
    private String caiState;
    @Excel(name = "裁剪已完成数量", width = 15)
    private String caiFinish;
    @Excel(name = "裁剪未完成数量", width = 15)
    private String caiUnfinish;
    @Excel(name = "缝制状态", width = 15)
    private String fzState;
    @Excel(name = "缝制已完成数量", width = 15)
    private String fzFinish;
    @Excel(name = "缝制未完成数量", width = 15)
    private String fzUnfinish;
    @Excel(name = "烫标状态", width = 15)
    private String btState;
    @Excel(name = "烫标已完成数量", width = 15)
    private String btFinish;
    @Excel(name = "烫标未完成数量", width = 15)
    private String btUnfinish;
    @Excel(name = "整烫状态", width = 15)
    private String ztState;
    @Excel(name = "整烫已完成数量", width = 15)
    private String ztFinish;
    @Excel(name = "整烫未完成数量", width = 15)
    private String ztUnfinish;
    @Excel(name = "包装状态", width = 15)
    private String bzState;
    @Excel(name = "包装已完成数量", width = 15)
    private String bzFinish;
    @Excel(name = "包装已完成数量", width = 15)
    private String bzUnfinish;
    @Excel(name = "出货日期", width = 15)
    private String outDate;
    @Excel(name = "供应商", width = 15)
    private String gys;
    @Excel(name = "供应商代码", width = 15)
    private String gysCode;

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

    @Column(name = "CUS_NUM", nullable = true, length = 32)
    public String getCusNum() {
        return this.cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    @Column(name = "BUSINESSER_NAME", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
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

    @Column(name = "HT_NUM", nullable = true, length = 32)
    public String getHtNum() {
        return this.htNum;
    }

    public void setHtNum(String htNum) {
        this.htNum = htNum;
    }

    @Column(name = "SUM_TOTAL", nullable = true, scale = 2, length = 32)
    public Double getSumTotal() {
        return this.sumTotal;
    }

    public void setSumTotal(Double sumTotal) {
        this.sumTotal = sumTotal;
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

    @Column(name = "PRODUCE_HT_NUM", nullable = true, length = 32)
    public String getProduceHtNum() {
        return this.produceHtNum;
    }

    public void setProduceHtNum(String produceHtNum) {
        this.produceHtNum = produceHtNum;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "YLBL_STATE", nullable = true, length = 32)
    public String getYlblState() {
        return this.ylblState;
    }

    public void setYlblState(String ylblState) {
        this.ylblState = ylblState;
    }

    @Column(name = "YLBL_LIMIT_DATE", nullable = true, length = 32)
    public String getYlblLimitDate() {
        return this.ylblLimitDate;
    }

    public void setYlblLimitDate(String ylblLimitDate) {
        this.ylblLimitDate = ylblLimitDate;
    }

    @Column(name = "LEAVEL_YLBL_DAY", nullable = true, length = 32)
    public Integer getLeavelYlblDay() {
        return this.leavelYlblDay;
    }

    public void setLeavelYlblDay(Integer leavelYlblDay) {
        this.leavelYlblDay = leavelYlblDay;
    }

    @Column(name = "FZBL_STATE", nullable = true, length = 32)
    public String getFzblState() {
        return this.fzblState;
    }

    public void setFzblState(String fzblState) {
        this.fzblState = fzblState;
    }

    @Column(name = "FZBL_LIMIT_DATE", nullable = true, length = 32)
    public String getFzblLimitDate() {
        return this.fzblLimitDate;
    }

    public void setFzblLimitDate(String fzblLimitDate) {
        this.fzblLimitDate = fzblLimitDate;
    }

    @Column(name = "LEAVEL_FZBL_DAY", nullable = true, length = 32)
    public Integer getLeavelFzblDay() {
        return this.leavelFzblDay;
    }

    public void setLeavelFzblDay(Integer leavelFzblDay) {
        this.leavelFzblDay = leavelFzblDay;
    }

    @Column(name = "BZBL_STATE", nullable = true, length = 32)
    public String getBzblState() {
        return this.bzblState;
    }

    public void setBzblState(String bzblState) {
        this.bzblState = bzblState;
    }

    @Column(name = "BZBL_LIMIT_DATE", nullable = true, length = 32)
    public String getBzblLimitDate() {
        return this.bzblLimitDate;
    }

    public void setBzblLimitDate(String bzblLimitDate) {
        this.bzblLimitDate = bzblLimitDate;
    }

    @Column(name = "LEAVEL_BZBL_DAY", nullable = true, length = 32)
    public Integer getLeavelBzblDay() {
        return this.leavelBzblDay;
    }

    public void setLeavelBzblDay(Integer leavelBzblDay) {
        this.leavelBzblDay = leavelBzblDay;
    }

    @Column(name = "RAN_STATE", nullable = true, length = 32)
    public String getRanState() {
        return this.ranState;
    }

    public void setRanState(String ranState) {
        this.ranState = ranState;
    }

    @Column(name = "RAN_FINISH", nullable = true, length = 32)
    public String getRanFinish() {
        return this.ranFinish;
    }

    public void setRanFinish(String ranFinish) {
        this.ranFinish = ranFinish;
    }

    @Column(name = "RAN_UNFINISH", nullable = true, length = 32)
    public String getRanUnfinish() {
        return this.ranUnfinish;
    }

    public void setRanUnfinish(String ranUnfinish) {
        this.ranUnfinish = ranUnfinish;
    }

    @Column(name = "CAI_STATE", nullable = true, length = 32)
    public String getCaiState() {
        return this.caiState;
    }

    public void setCaiState(String caiState) {
        this.caiState = caiState;
    }

    @Column(name = "CAI_FINISH", nullable = true, length = 32)
    public String getCaiFinish() {
        return this.caiFinish;
    }

    public void setCaiFinish(String caiFinish) {
        this.caiFinish = caiFinish;
    }

    @Column(name = "CAI_UNFINISH", nullable = true, length = 32)
    public String getCaiUnfinish() {
        return this.caiUnfinish;
    }

    public void setCaiUnfinish(String caiUnfinish) {
        this.caiUnfinish = caiUnfinish;
    }

    @Column(name = "FZ_STATE", nullable = true, length = 32)
    public String getFzState() {
        return this.fzState;
    }

    public void setFzState(String fzState) {
        this.fzState = fzState;
    }

    @Column(name = "FZ_FINISH", nullable = true, length = 32)
    public String getFzFinish() {
        return this.fzFinish;
    }

    public void setFzFinish(String fzFinish) {
        this.fzFinish = fzFinish;
    }

    @Column(name = "FZ_UNFINISH", nullable = true, length = 32)
    public String getFzUnfinish() {
        return this.fzUnfinish;
    }

    public void setFzUnfinish(String fzUnfinish) {
        this.fzUnfinish = fzUnfinish;
    }

    @Column(name = "BT_STATE", nullable = true, length = 32)
    public String getBtState() {
        return this.btState;
    }

    public void setBtState(String btState) {
        this.btState = btState;
    }

    @Column(name = "BT_FINISH", nullable = true, length = 32)
    public String getBtFinish() {
        return this.btFinish;
    }

    public void setBtFinish(String btFinish) {
        this.btFinish = btFinish;
    }

    @Column(name = "BT_UNFINISH", nullable = true, length = 32)
    public String getBtUnfinish() {
        return this.btUnfinish;
    }

    public void setBtUnfinish(String btUnfinish) {
        this.btUnfinish = btUnfinish;
    }

    @Column(name = "ZT_STATE", nullable = true, length = 32)
    public String getZtState() {
        return this.ztState;
    }

    public void setZtState(String ztState) {
        this.ztState = ztState;
    }

    @Column(name = "ZT_FINISH", nullable = true, length = 32)
    public String getZtFinish() {
        return this.ztFinish;
    }

    public void setZtFinish(String ztFinish) {
        this.ztFinish = ztFinish;
    }

    @Column(name = "ZT_UNFINISH", nullable = true, length = 32)
    public String getZtUnfinish() {
        return this.ztUnfinish;
    }

    public void setZtUnfinish(String ztUnfinish) {
        this.ztUnfinish = ztUnfinish;
    }

    @Column(name = "BZ_STATE", nullable = true, length = 32)
    public String getBzState() {
        return this.bzState;
    }

    public void setBzState(String bzState) {
        this.bzState = bzState;
    }

    @Column(name = "BZ_FINISH", nullable = true, length = 32)
    public String getBzFinish() {
        return this.bzFinish;
    }

    public void setBzFinish(String bzFinish) {
        this.bzFinish = bzFinish;
    }

    @Column(name = "BZ_UNFINISH", nullable = true, length = 32)
    public String getBzUnfinish() {
        return this.bzUnfinish;
    }

    public void setBzUnfinish(String bzUnfinish) {
        this.bzUnfinish = bzUnfinish;
    }

    @Column(name = "OUT_DATE", nullable = true, length = 32)
    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
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
}
