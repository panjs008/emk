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
    @Excel(name = "供应商")
    private String factoryName;
    @Excel(name = "供应商代码")
    private String factoryCode;
    private String customSampleUrl;
    @Excel(name = "客户原样")
    private String customSample;
    private String sampleSizeUrl;
    @Excel(name = "尺寸表")
    private String sampleSize;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "包装方案")
    private String packing;
    @Excel(name = "商品名称")
    private String proName;
    @Excel(name = "产品类别")
    private String proType;
    @Excel(name = "产品类别名称")
    private String proTypeName;
    @Excel(name = "交货时间")
    private String receviceDate;
    @Excel(name = "样衣完成时间")
    private String finishDate;
    @Excel(name = "生产板完成时间")
    private String produceDate;
    @Excel(name = "要求")
    private String requiretext;
    @Excel(name = "状态")
    private String state;
    @Excel(name = "打样通知单号")
    private String sampleNum;
    @Excel(name = "样品类型")
    private String type;
    @Excel(name = "开单日期")
    private String kdTime;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "克重")
    private String weight;
    @Excel(name = "成分")
    private String chengf;
    @Excel(name = "类型")
    private String flag;
    @Excel(name = "完成检查意见")
    private String finishAdvice;
    @Excel(name = "布面意见")
    private String bmAdvice;
    @Excel(name = "缝制意见")
    private String fzAdvice;
    @Excel(name = "染色意见")
    private String rsAdvice;
    @Excel(name = "质检员意见")
    private String zjyAdvice;
    @Excel(name = "跟单员意见")
    private String traceAdvice;
    @Excel(name = "业务员意见")
    private String businessAdvice;
    @Excel(name = "综合意见")
    private String zhAdvice;
    @Excel(name = "下机克重")
    private Double xjkz;
    @Excel(name = "单件所需时间")
    private Double djsxTime;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "机台日产量")
    private Integer jtrcl;
    @Excel(name = "前道损耗率")
    private Double qdshl;
    @Excel(name = "后道损耗率")
    private Double hdshl;
    @Excel(name = "业务员")
    private String businesser;
    private String businesserName;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "打样需求单号")
    private String xqdh;
    @Excel(name = "报价单号")
    private String pirceNo;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "审核意见")
    private String leadAdvice;
    @Excel(name = "是否通过")
    private String isPass;
    @Excel(name = "审核人ID")
    private String leadUserId;
    @Excel(name = "审核人")
    private String leader;


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

    @Column(name = "SAMPLE_SIZE_URL", nullable = true, length = 2546)
    public String getSampleSizeUrl() {
        return this.sampleSizeUrl;
    }

    public void setSampleSizeUrl(String sampleSizeUrl) {
        this.sampleSizeUrl = sampleSizeUrl;
    }

    @Column(name = "SAMPLE_SIZE", nullable = true, length = 32)
    public String getSampleSize() {
        return this.sampleSize;
    }

    public void setSampleSize(String sampleSize) {
        this.sampleSize = sampleSize;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "PACKING", nullable = true, length = 512)
    public String getPacking() {
        return this.packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    @Column(name = "PRO_NAME", nullable = true, length = 56)
    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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

    @Column(name = "RECEVICE_DATE", nullable = true, length = 32)
    public String getReceviceDate() {
        return this.receviceDate;
    }

    public void setReceviceDate(String receviceDate) {
        this.receviceDate = receviceDate;
    }

    @Column(name = "FINISH_DATE", nullable = true, length = 32)
    public String getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    @Column(name = "PRODUCE_DATE", nullable = true, length = 32)
    public String getProduceDate() {
        return this.produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    @Column(name = "REQUIRETEXT", nullable = true, length = 512)
    public String getRequiretext() {
        return this.requiretext;
    }

    public void setRequiretext(String requiretext) {
        this.requiretext = requiretext;
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

    @Column(name = "WEIGHT", nullable = true, length = 32)
    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "CHENGF", nullable = true, length = 32)
    public String getChengf() {
        return this.chengf;
    }

    public void setChengf(String chengf) {
        this.chengf = chengf;
    }

    @Column(name = "FLAG", nullable = true, length = 32)
    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Column(name = "FINISH_ADVICE", nullable = true, length = 256)
    public String getFinishAdvice() {
        return this.finishAdvice;
    }

    public void setFinishAdvice(String finishAdvice) {
        this.finishAdvice = finishAdvice;
    }

    @Column(name = "BM_ADVICE", nullable = true, length = 256)
    public String getBmAdvice() {
        return this.bmAdvice;
    }

    public void setBmAdvice(String bmAdvice) {
        this.bmAdvice = bmAdvice;
    }

    @Column(name = "FZ_ADVICE", nullable = true, length = 256)
    public String getFzAdvice() {
        return this.fzAdvice;
    }

    public void setFzAdvice(String fzAdvice) {
        this.fzAdvice = fzAdvice;
    }

    @Column(name = "RS_ADVICE", nullable = true, length = 256)
    public String getRsAdvice() {
        return this.rsAdvice;
    }

    public void setRsAdvice(String rsAdvice) {
        this.rsAdvice = rsAdvice;
    }

    @Column(name = "ZJY_ADVICE", nullable = true, length = 256)
    public String getZjyAdvice() {
        return this.zjyAdvice;
    }

    public void setZjyAdvice(String zjyAdvice) {
        this.zjyAdvice = zjyAdvice;
    }

    @Column(name = "TRACE_ADVICE", nullable = true, length = 256)
    public String getTraceAdvice() {
        return this.traceAdvice;
    }

    public void setTraceAdvice(String traceAdvice) {
        this.traceAdvice = traceAdvice;
    }

    @Column(name = "BUSINESS_ADVICE", nullable = true, length = 256)
    public String getBusinessAdvice() {
        return this.businessAdvice;
    }

    public void setBusinessAdvice(String businessAdvice) {
        this.businessAdvice = businessAdvice;
    }

    @Column(name = "ZH_ADVICE", nullable = true, length = 256)
    public String getZhAdvice() {
        return this.zhAdvice;
    }

    public void setZhAdvice(String zhAdvice) {
        this.zhAdvice = zhAdvice;
    }

    @Column(name = "XJKZ", nullable = true, length = 32)
    public Double getXjkz() {
        return this.xjkz;
    }

    public void setXjkz(Double xjkz) {
        this.xjkz = xjkz;
    }

    @Column(name = "DJSX_TIME", nullable = true, length = 32)
    public Double getDjsxTime() {
        return this.djsxTime;
    }

    public void setDjsxTime(Double djsxTime) {
        this.djsxTime = djsxTime;
    }

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "JTRCL", nullable = true, length = 32)
    public Integer getJtrcl() {
        return this.jtrcl;
    }

    public void setJtrcl(Integer jtrcl) {
        this.jtrcl = jtrcl;
    }

    @Column(name = "QDSHL", nullable = true, scale = 2, length = 32)
    public Double getQdshl() {
        return this.qdshl;
    }

    public void setQdshl(Double qdshl) {
        this.qdshl = qdshl;
    }

    @Column(name = "HDSHL", nullable = true, scale = 2, length = 32)
    public Double getHdshl() {
        return this.hdshl;
    }

    public void setHdshl(Double hdshl) {
        this.hdshl = hdshl;
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

    @Column(name = "XQDH", nullable = true, length = 32)
    public String getXqdh() {
        return this.xqdh;
    }

    public void setXqdh(String xqdh) {
        this.xqdh = xqdh;
    }

    @Column(name = "PIRCE_NO", nullable = true, length = 32)
    public String getPirceNo() {
        return this.pirceNo;
    }

    public void setPirceNo(String pirceNo) {
        this.pirceNo = pirceNo;
    }

    @Column(name = "order_no", nullable = true, length = 32)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
}
