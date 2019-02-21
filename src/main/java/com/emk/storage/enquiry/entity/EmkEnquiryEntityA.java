package com.emk.storage.enquiry.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class EmkEnquiryEntityA implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "意向订单号")
    private String enquiryNo;
    /*@Excel(name="是否先打样",width=15)
    private String isPrint;*/
    @Excel(name="询盘状态",width=15)
    private String state;               //0 创建 1 提交 2 审核 3 正在核价 4 已回复客户，待消息 5 买家要求打样，已安排打样和核价 6 价格不合适，意向已取消；7 尚未接到买家回复；8 已接单，备注订单号
    @Excel(name = "提交日期")
    private String kdDate;
    @Excel(name = "业务员")
    private String businesser;
    private String businesserName;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "工艺种类")
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "是否打过初样")
    private String isPrintSample;
    @Excel(name = "是否收取打样费")
    private String isGetSample;
    @Excel(name = "是否有原样")
    private String isHaveOld;
    private String oldImageUrl;
    @Excel(name = "原样")
    private String oldImage;
    @Excel(name = "是否有设计稿")
    private String isHaveDgr;
    private String dgrImageUrl;
    @Excel(name = "计稿")
    private String dgrImage;
    @Excel(name = "是否有尺寸表")
    private String isHaveSize;
    private String sizeImageUrl;
    @Excel(name = "尺寸表")
    private String sizeImage;
    @Excel(name = "意向货交期")
    private String ysDate;
    @Excel(name = "距交期余天数")
    private Integer levelDays;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    private String processName;

    private String state1;
    private String state2;
    private String state3;


   /* @Excel(name = "审核意见")
    private String leadAdvice;
    @Excel(name = "处理意见")
    private String financeAdvice;
    @Excel(name = "是否通过")
    private String isPass;
    private String leadUserId;
    @Excel(name = "审核人")
    private String leader;*/


   /* private String financeUserId;
    @Excel(name = "财务处理人")
    private String financer;*/

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

    @Column(name = "ENQUIRY_NO", nullable = true, length = 32)
    public String getEnquiryNo() {
        return this.enquiryNo;
    }

    public void setEnquiryNo(String enquiryNo) {
        this.enquiryNo = enquiryNo;
    }

    @Column(name ="STATE",nullable=true,length=32)
    public String getState(){
        return this.state;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  工单状态
     */
    public void setState(String state){
        this.state = state;
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

    @Column(name = "IS_PRINT_SAMPLE", nullable = true, length = 6)
    public String getIsPrintSample() {
        return this.isPrintSample;
    }

    public void setIsPrintSample(String isPrintSample) {
        this.isPrintSample = isPrintSample;
    }

    @Column(name = "IS_GET_SAMPLE", nullable = true, length = 6)
    public String getIsGetSample() {
        return this.isGetSample;
    }

    public void setIsGetSample(String isGetSample) {
        this.isGetSample = isGetSample;
    }

    @Column(name = "IS_HAVE_OLD", nullable = true, length = 6)
    public String getIsHaveOld() {
        return this.isHaveOld;
    }

    public void setIsHaveOld(String isHaveOld) {
        this.isHaveOld = isHaveOld;
    }

    @Column(name = "OLD_IMAGE_URL", nullable = true, length = 256)
    public String getOldImageUrl() {
        return this.oldImageUrl;
    }

    public void setOldImageUrl(String oldImageUrl) {
        this.oldImageUrl = oldImageUrl;
    }

    @Column(name = "OLD_IMAGE", nullable = true, length = 32)
    public String getOldImage() {
        return this.oldImage;
    }

    public void setOldImage(String oldImage) {
        this.oldImage = oldImage;
    }

    @Column(name = "IS_HAVE_DGR", nullable = true, length = 6)
    public String getIsHaveDgr() {
        return this.isHaveDgr;
    }

    public void setIsHaveDgr(String isHaveDgr) {
        this.isHaveDgr = isHaveDgr;
    }

    @Column(name = "DGR_IMAGE_URL", nullable = true, length = 256)
    public String getDgrImageUrl() {
        return this.dgrImageUrl;
    }

    public void setDgrImageUrl(String dgrImageUrl) {
        this.dgrImageUrl = dgrImageUrl;
    }

    @Column(name = "DGR_IMAGE", nullable = true, length = 32)
    public String getDgrImage() {
        return this.dgrImage;
    }

    public void setDgrImage(String dgrImage) {
        this.dgrImage = dgrImage;
    }

    @Column(name = "IS_HAVE_SIZE", nullable = true, length = 6)
    public String getIsHaveSize() {
        return this.isHaveSize;
    }

    public void setIsHaveSize(String isHaveSize) {
        this.isHaveSize = isHaveSize;
    }

    @Column(name = "SIZE_IMAGE_URL", nullable = true, length = 256)
    public String getSizeImageUrl() {
        return this.sizeImageUrl;
    }

    public void setSizeImageUrl(String sizeImageUrl) {
        this.sizeImageUrl = sizeImageUrl;
    }

    @Column(name = "SIZE_IMAGE", nullable = true, length = 32)
    public String getSizeImage() {
        return this.sizeImage;
    }

    public void setSizeImage(String sizeImage) {
        this.sizeImage = sizeImage;
    }

    @Column(name = "YS_DATE", nullable = true, length = 32)
    public String getYsDate() {
        return this.ysDate;
    }

    public void setYsDate(String ysDate) {
        this.ysDate = ysDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Formula("(select CONCAT(p.NAME_,'-',p.TASK_DEF_KEY_) from act_ru_task p where p.ASSIGNEE_ = id limit 0,1)")
    @Column(name = "process_name", nullable = true, length = 32)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Column(name = "state1", nullable = true, length = 32)
    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    @Column(name = "state2", nullable = true, length = 32)
    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    @Column(name = "state3", nullable = true, length = 32)
    public String getState3() {
        return state3;
    }

    public void setState3(String state3) {
        this.state3 = state3;
    }

    /*@Column(name = "LEAD_ADVICE", nullable = true, length = 256)
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
    }*/

    /*@Column(name = "FINANCE_USER_ID", nullable = true, length = 32)
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
    }*/
}
