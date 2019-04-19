package com.emk.produce.meeting.entity;

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
@Table(name = "emk_meeting", schema = "")
public class EmkMeetingEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysCompanyCode;
    @Excel(name = "会议记录表编号")
    private String meetingNo;
    @Excel(name = "会议时间")
    private String meetingDate;
    @Excel(name = "会议记录人")
    private String recorder;
    private String recorderId;
    @Excel(name = "会议地点")
    private String address;
    @Excel(name = "入会人员")
    private String partUsers;
    private String partUserIds;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "工艺种类")
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "业务员")
    private String businesser;
    private String businesserName;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "出货日期")
    private String outDate;
    @Excel(name = "中期检查时间")
    private String zqjcDate;
    @Excel(name = "尾期检查时间")
    private String wqjcDate;
    @Excel(name = "会议记录")
    private String content;
    @Excel(name = "技术部布面意见")
    private String jsbbmAdvice;
    @Excel(name = "技术部染色意见")
    private String jsbranAdvice;
    @Excel(name = "缝制部意见")
    private String fzAdvice;
    @Excel(name = "烫标组意见")
    private String tbzAdvice;
    @Excel(name = "包装组意见")
    private String bzAdvice;
    @Excel(name = "采购部意见")
    private String cgAdvice;
    @Excel(name = "业务部意见")
    private String ybAdvice;
    @Excel(name = "质检组意见")
    private String zjzAdvice;
    @Excel(name = "计划部意见")
    private String jhbAdvice;
    @Excel(name = "生产部综合意见")
    private String scbzhAdvice;

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

    @Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
    public String getSysCompanyCode() {
        return this.sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }

    @Column(name = "MEETING_NO", nullable = true, length = 32)
    public String getMeetingNo() {
        return this.meetingNo;
    }

    public void setMeetingNo(String meetingNo) {
        this.meetingNo = meetingNo;
    }

    @Column(name = "MEETING_DATE", nullable = true, length = 32)
    public String getMeetingDate() {
        return this.meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Column(name = "RECORDER", nullable = true, length = 32)
    public String getRecorder() {
        return this.recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    @Column(name = "RECORDER_ID", nullable = true, length = 32)
    public String getRecorderId() {
        return this.recorderId;
    }

    public void setRecorderId(String recorderId) {
        this.recorderId = recorderId;
    }

    @Column(name = "ADDRESS", nullable = true, length = 32)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "PART_USERS", nullable = true, length = 256)
    public String getPartUsers() {
        return this.partUsers;
    }

    public void setPartUsers(String partUsers) {
        this.partUsers = partUsers;
    }

    @Column(name = "PART_USER_IDS", nullable = true, length = 256)
    public String getPartUserIds() {
        return this.partUserIds;
    }

    public void setPartUserIds(String partUserIds) {
        this.partUserIds = partUserIds;
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

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 32)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    @Column(name = "ZQJC_DATE", nullable = true, length = 32)
    public String getZqjcDate() {
        return this.zqjcDate;
    }

    public void setZqjcDate(String zqjcDate) {
        this.zqjcDate = zqjcDate;
    }

    @Column(name = "WQJC_DATE", nullable = true, length = 32)
    public String getWqjcDate() {
        return this.wqjcDate;
    }

    public void setWqjcDate(String wqjcDate) {
        this.wqjcDate = wqjcDate;
    }

    @Column(name = "CONTENT", nullable = true, length = 32)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "JSBBM_ADVICE", nullable = true, length = 256)
    public String getJsbbmAdvice() {
        return this.jsbbmAdvice;
    }

    public void setJsbbmAdvice(String jsbbmAdvice) {
        this.jsbbmAdvice = jsbbmAdvice;
    }

    @Column(name = "JSBRAN_ADVICE", nullable = true, length = 256)
    public String getJsbranAdvice() {
        return this.jsbranAdvice;
    }

    public void setJsbranAdvice(String jsbranAdvice) {
        this.jsbranAdvice = jsbranAdvice;
    }

    @Column(name = "FZ_ADVICE", nullable = true, length = 256)
    public String getFzAdvice() {
        return this.fzAdvice;
    }

    public void setFzAdvice(String fzAdvice) {
        this.fzAdvice = fzAdvice;
    }

    @Column(name = "TBZ_ADVICE", nullable = true, length = 256)
    public String getTbzAdvice() {
        return this.tbzAdvice;
    }

    public void setTbzAdvice(String tbzAdvice) {
        this.tbzAdvice = tbzAdvice;
    }

    @Column(name = "BZ_ADVICE", nullable = true, length = 256)
    public String getBzAdvice() {
        return this.bzAdvice;
    }

    public void setBzAdvice(String bzAdvice) {
        this.bzAdvice = bzAdvice;
    }

    @Column(name = "CG_ADVICE", nullable = true, length = 256)
    public String getCgAdvice() {
        return this.cgAdvice;
    }

    public void setCgAdvice(String cgAdvice) {
        this.cgAdvice = cgAdvice;
    }

    @Column(name = "YB_ADVICE", nullable = true, length = 256)
    public String getYbAdvice() {
        return this.ybAdvice;
    }

    public void setYbAdvice(String ybAdvice) {
        this.ybAdvice = ybAdvice;
    }

    @Column(name = "ZJZ_ADVICE", nullable = true, length = 256)
    public String getZjzAdvice() {
        return this.zjzAdvice;
    }

    public void setZjzAdvice(String zjzAdvice) {
        this.zjzAdvice = zjzAdvice;
    }

    @Column(name = "JHB_ADVICE", nullable = true, length = 256)
    public String getJhbAdvice() {
        return this.jhbAdvice;
    }

    public void setJhbAdvice(String jhbAdvice) {
        this.jhbAdvice = jhbAdvice;
    }

    @Column(name = "SCBZH_ADVICE", nullable = true, length = 256)
    public String getScbzhAdvice() {
        return this.scbzhAdvice;
    }

    public void setScbzhAdvice(String scbzhAdvice) {
        this.scbzhAdvice = scbzhAdvice;
    }

    @Column(name = "out_date", nullable = true, length = 256)
    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
