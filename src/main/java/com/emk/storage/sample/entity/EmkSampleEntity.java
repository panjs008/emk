package com.emk.storage.sample.entity;

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
@Table(name = "emk_sample", schema = "")
public class EmkSampleEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "客户编号")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "款号")
    private String sampleNo;
    private String projectNo;

    @Excel(name = "产品类别")
    private String proType;
    @Excel(name = "产品类别名称")
    private String proTypeName;
    @Excel(name = "样品交期")
    private String ypjqDate;
    @Excel(name = "距样品交期余天数")
    private String leavelypjqDays;
    @Excel(name = "大货交期")
    private String dhjq;
    @Excel(name = "距大货交期余天数")
    private String leaveldhjqDays;

    @Excel(name = "状态")
    private String state;
    @Excel(name = "打样通知单号")
    private String sampleNum;
    @Excel(name = "样品类型")
    private String type;
    @Excel(name = "开单日期")
    private String kdTime;
    @Excel(name = "打样需求单日期")
    private String dyxqdTime;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "打样需求单号")
    private String xqdh;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "备注")
    private String remark;

    @Excel(name = "所用机台尺寸")
    private String syjtcc;
    @Excel(name = "下机重量")
    private String xjzl;
    @Excel(name = "下机尺寸")
    private String xjcc;
    @Excel(name = "密度")
    private String md;
    @Excel(name = "用料")
    private String yl;
    @Excel(name = "单件织造时间")
    private String djzjsj;

    @Excel(name = "是否有标准色样")
    private String isColorStrand;
    @Excel(name = "是否有潘通色号")
    private String isPan;
    @Excel(name = "是否有参考样")
    private String isCan;
    @Excel(name = "是否有设计稿")
    private String isDrg;
    @Excel(name = "是否有原样")
    private String isSample;
    @Excel(name = "是否有物料明细")
    private String isDetail;
    @Excel(name = "是否有尺寸表")
    private String isSize;

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
    @Excel(name = "技术员")
    private String jser;
    private String jserName;
    @Excel(name = "质检员")
    private String zjer;
    private String zjerName;
    private String formType;
    private String factoryCode;
    private String ylgg;
    private String pfkz;


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

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "PROJECT_NO", nullable = true, length = 32)
    public String getProjectNo() {
        return this.projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Column(name = "ypjq_date", nullable = true, length = 32)
    public String getYpjqDate() {
        return ypjqDate;
    }

    public void setYpjqDate(String ypjqDate) {
        this.ypjqDate = ypjqDate;
    }

    @Column(name = "leavelypjq_days", nullable = true, length = 32)
    public String getLeavelypjqDays() {
        return leavelypjqDays;
    }

    public void setLeavelypjqDays(String leavelypjqDays) {
        this.leavelypjqDays = leavelypjqDays;
    }

    @Column(name = "dhjq", nullable = true, length = 32)
    public String getDhjq() {
        return dhjq;
    }

    public void setDhjq(String dhjq) {
        this.dhjq = dhjq;
    }

    @Column(name = "leaveldhjq_days", nullable = true, length = 32)
    public String getLeaveldhjqDays() {
        return leaveldhjqDays;
    }

    public void setLeaveldhjqDays(String leaveldhjqDays) {
        this.leaveldhjqDays = leaveldhjqDays;
    }

    @Column(name = "STATE", nullable = true, length = 6)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "SAMPLE_NUM", nullable = true, length = 32)
    public String getSampleNum() {
        return this.sampleNum;
    }

    public void setSampleNum(String sampleNum) {
        this.sampleNum = sampleNum;
    }

    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "KD_TIME", nullable = true, length = 32)
    public String getKdTime() {
        return this.kdTime;
    }

    public void setKdTime(String kdTime) {
        this.kdTime = kdTime;
    }

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "VERSION", nullable = true, length = 32)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "XQDH", nullable = true, length = 32)
    public String getXqdh() {
        return this.xqdh;
    }

    public void setXqdh(String xqdh) {
        this.xqdh = xqdh;
    }

    @Column(name = "dyxqd_time", nullable = true, length = 32)
    public String getDyxqdTime() {
        return dyxqdTime;
    }

    public void setDyxqdTime(String dyxqdTime) {
        this.dyxqdTime = dyxqdTime;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "is_color_strand", nullable = true, length = 256)
    public String getIsColorStrand() {
        return isColorStrand;
    }

    public void setIsColorStrand(String isColorStrand) {
        this.isColorStrand = isColorStrand;
    }

    @Column(name = "is_pan", nullable = true, length = 256)
    public String getIsPan() {
        return isPan;
    }

    public void setIsPan(String isPan) {
        this.isPan = isPan;
    }

    @Column(name = "is_can", nullable = true, length = 256)
    public String getIsCan() {
        return isCan;
    }

    public void setIsCan(String isCan) {
        this.isCan = isCan;
    }

    @Column(name = "is_drg", nullable = true, length = 256)
    public String getIsDrg() {
        return isDrg;
    }

    public void setIsDrg(String isDrg) {
        this.isDrg = isDrg;
    }

    @Column(name = "is_sample", nullable = true, length = 256)
    public String getIsSample() {
        return isSample;
    }

    public void setIsSample(String isSample) {
        this.isSample = isSample;
    }

    @Column(name = "is_detail", nullable = true, length = 256)
    public String getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(String isDetail) {
        this.isDetail = isDetail;
    }

    @Column(name = "is_size", nullable = true, length = 256)
    public String getIsSize() {
        return isSize;
    }

    public void setIsSize(String isSize) {
        this.isSize = isSize;
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

    @Column(name = "jser", nullable = true, length = 32)
    public String getJser() {
        return jser;
    }

    public void setJser(String jser) {
        this.jser = jser;
    }

    @Column(name = "jser_name", nullable = true, length = 32)
    public String getJserName() {
        return jserName;
    }

    public void setJserName(String jserName) {
        this.jserName = jserName;
    }

    @Column(name = "zjer", nullable = true, length = 32)
    public String getZjer() {
        return zjer;
    }

    public void setZjer(String zjer) {
        this.zjer = zjer;
    }

    @Column(name = "zjer_name", nullable = true, length = 32)
    public String getZjerName() {
        return zjerName;
    }

    public void setZjerName(String zjerName) {
        this.zjerName = zjerName;
    }

    @Column(name = "syjtcc", nullable = true, length = 32)
    public String getSyjtcc() {
        return syjtcc;
    }

    public void setSyjtcc(String syjtcc) {
        this.syjtcc = syjtcc;
    }

    @Column(name = "xjzl", nullable = true, length = 32)
    public String getXjzl() {
        return xjzl;
    }

    public void setXjzl(String xjzl) {
        this.xjzl = xjzl;
    }

    @Column(name = "xjcc", nullable = true, length = 32)
    public String getXjcc() {
        return xjcc;
    }

    public void setXjcc(String xjcc) {
        this.xjcc = xjcc;
    }

    @Column(name = "md", nullable = true, length = 32)
    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    @Column(name = "yl", nullable = true, length = 32)
    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    @Column(name = "djzjsj", nullable = true, length = 32)
    public String getDjzjsj() {
        return djzjsj;
    }

    public void setDjzjsj(String djzjsj) {
        this.djzjsj = djzjsj;
    }

    @Column(name = "form_type", nullable = true, length = 32)
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Column(name = "factory_code", nullable = true, length = 32)
    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    @Column(name = "ylgg", nullable = true, length = 32)
    public String getYlgg() {
        return ylgg;
    }

    public void setYlgg(String ylgg) {
        this.ylgg = ylgg;
    }

    @Column(name = "pfkz", nullable = true, length = 32)
    public String getPfkz() {
        return pfkz;
    }

    public void setPfkz(String pfkz) {
        this.pfkz = pfkz;
    }
}
