package com.emk.produce.examtime.entity;

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
@Table(name = "emk_exam_time", schema = "")
public class EmkExamTimeEntity implements Serializable {
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
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "数量")
    private Integer total;
    @Excel(name = "颜色")
    private Double color;
    @Excel(name = "尺码")
    private String size;
    @Excel(name = "预计中期验货日期")
    private String zqyhDate;
    @Excel(name = "预计尾期验货日期")
    private String wqyhDate;
    @Excel(name = "距交期剩余天数")
    private Integer levelDays;
    @Excel(name = "中期验货状态")
    private String zqyhState;
    @Excel(name = "距中期验货剩余天数")
    private Integer levealZqyh;
    @Excel(name = "确认中期验货日期")
    private String qrzqyhDate;
    @Excel(name = "尾期验货状态")
    private String wqyhState;
    @Excel(name = "距尾期验货剩余天数")
    private Integer levealWqyh;
    @Excel(name = "确认尾期验货日期")
    private String qrwqyhDate;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商")
    private String gysCode;
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "生产合同号")
    private String produceNum;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "更新时间")
    private String kdDate;

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

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "COLOR", nullable = true, scale = 2, length = 32)
    public Double getColor() {
        return this.color;
    }

    public void setColor(Double color) {
        this.color = color;
    }

    @Column(name = "SIZE", nullable = true, length = 32)
    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "ZQYH_DATE", nullable = true, length = 32)
    public String getZqyhDate() {
        return this.zqyhDate;
    }

    public void setZqyhDate(String zqyhDate) {
        this.zqyhDate = zqyhDate;
    }

    @Column(name = "WQYH_DATE", nullable = true, length = 32)
    public String getWqyhDate() {
        return this.wqyhDate;
    }

    public void setWqyhDate(String wqyhDate) {
        this.wqyhDate = wqyhDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "ZQYH_STATE", nullable = true, length = 32)
    public String getZqyhState() {
        return this.zqyhState;
    }

    public void setZqyhState(String zqyhState) {
        this.zqyhState = zqyhState;
    }

    @Column(name = "LEVEAL_ZQYH", nullable = true, length = 32)
    public Integer getLevealZqyh() {
        return this.levealZqyh;
    }

    public void setLevealZqyh(Integer levealZqyh) {
        this.levealZqyh = levealZqyh;
    }

    @Column(name = "QRZQYH_DATE", nullable = true, length = 32)
    public String getQrzqyhDate() {
        return this.qrzqyhDate;
    }

    public void setQrzqyhDate(String qrzqyhDate) {
        this.qrzqyhDate = qrzqyhDate;
    }

    @Column(name = "WQYH_STATE", nullable = true, length = 32)
    public String getWqyhState() {
        return this.wqyhState;
    }

    public void setWqyhState(String wqyhState) {
        this.wqyhState = wqyhState;
    }

    @Column(name = "LEVEAL_WQYH", nullable = true, length = 32)
    public Integer getLevealWqyh() {
        return this.levealWqyh;
    }

    public void setLevealWqyh(Integer levealWqyh) {
        this.levealWqyh = levealWqyh;
    }

    @Column(name = "QRWQYH_DATE", nullable = true, length = 32)
    public String getQrwqyhDate() {
        return this.qrwqyhDate;
    }

    public void setQrwqyhDate(String qrwqyhDate) {
        this.qrwqyhDate = qrwqyhDate;
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

    @Column(name = "PRODUCE_NUM", nullable = true, length = 32)
    public String getProduceNum() {
        return this.produceNum;
    }

    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }
}
