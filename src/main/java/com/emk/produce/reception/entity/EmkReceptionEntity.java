package com.emk.produce.reception.entity;

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
@Table(name = "emk_reception", schema = "")
public class EmkReceptionEntity implements Serializable {
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
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "业务接待单号")
    private String recevieNum;
    @Excel(name = "接待单日期")
    private String recevieDate;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "总数量")
    private String total;
    @Excel(name = "出货日期")
    private String outDate;
    @Excel(name = "接待事由")
    private String recevieDesc;
    @Excel(name = "接待地点")
    private String address;
    @Excel(name = "接待时间")
    private String jdsj;
    @Excel(name = "出席人员")
    private String outUserNames;
    @Excel(name = "出席人员")
    private String outUserIds;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商")
    private String gysCode;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "生产合同号")
    private String produceNum;

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

    @Column(name = "RECEVIE_NUM", nullable = true, length = 32)
    public String getRecevieNum() {
        return this.recevieNum;
    }

    public void setRecevieNum(String recevieNum) {
        this.recevieNum = recevieNum;
    }

    @Column(name = "RECEVIE_DATE", nullable = true, length = 32)
    public String getRecevieDate() {
        return this.recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Column(name = "OUT_DATE", nullable = true, length = 32)
    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Column(name = "RECEVIE_DESC", nullable = true, length = 256)
    public String getRecevieDesc() {
        return this.recevieDesc;
    }

    public void setRecevieDesc(String recevieDesc) {
        this.recevieDesc = recevieDesc;
    }

    @Column(name = "ADDRESS", nullable = true, length = 32)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "JDSJ", nullable = true, length = 32)
    public String getJdsj() {
        return this.jdsj;
    }

    public void setJdsj(String jdsj) {
        this.jdsj = jdsj;
    }

    @Column(name = "OUT_USER_NAMES", nullable = true, length = 256)
    public String getOutUserNames() {
        return this.outUserNames;
    }

    public void setOutUserNames(String outUserNames) {
        this.outUserNames = outUserNames;
    }

    @Column(name = "OUT_USER_IDS", nullable = true, length = 256)
    public String getOutUserIds() {
        return this.outUserIds;
    }

    public void setOutUserIds(String outUserIds) {
        this.outUserIds = outUserIds;
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

    @Column(name = "PRODUCE_NUM", nullable = true, length = 32)
    public String getProduceNum() {
        return this.produceNum;
    }

    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }
}
