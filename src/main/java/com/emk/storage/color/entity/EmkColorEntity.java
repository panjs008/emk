package com.emk.storage.color.entity;

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
@Table(name = "emk_color", schema = "")
public class EmkColorEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "色样需求单号")
    private String colorNo;
    @Excel(name = "色样询盘号")
    private String syxpNum;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "客户编号")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "是否标准色卡")
    private String isColorCard;
    private String colorCardUrl;
    @Excel(name = "标准色卡")
    private String colorCard;
    private String sampleId;

    @Excel(name = "是否标准色号")
    private String isColorNum;
    private String colorNumUrl;
    @Excel(name = "标准色号")
    private String colorNum;
    @Excel(name = "色卡说明")
    private String colorCardRemark;
    @Excel(name = "色号说明")
    private String colorNumRemark;
    @Excel(name = "是否标准色数据")
    private String isColorData;
    @Excel(name = "标准色数据")
    private String colorData;
    private String colorDataUrl;
    @Excel(name = "是否QTX")
    private String isColorQtx;
    @Excel(name = "QTX")
    private String colorQtx;
    private String colorQtxUrl;
    @Excel(name = "色样种类")
    private String colorType;
    private String cgy;
    @Excel(name = "开单日期")
    private String kdDate;
    @Excel(name = "色牢度")
    private Double colorSlg;
    @Excel(name = "化学物质")
    private String hxwz;
    @Excel(name = "重金属")
    private String gjs;
    @Excel(name = "色样去向")
    private String syTo;
    @Excel(name = "客户评语")
    private String cusRemark;
    @Excel(name = "样品交期")
    private String ysDate;
    @Excel(name = "距交期余天数")
    private String levelDays;
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

    @Excel(name = "工艺种类")
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "交货时间")
    private String recevieDate;

    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "备注")
    private String remark;

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

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
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

    @Column(name = "IS_COLOR_CARD", nullable = true, length = 6)
    public String getIsColorCard() {
        return this.isColorCard;
    }

    public void setIsColorCard(String isColorCard) {
        this.isColorCard = isColorCard;
    }

    @Column(name = "COLOR_CARD_URL", nullable = true, length = 256)
    public String getColorCardUrl() {
        return this.colorCardUrl;
    }

    public void setColorCardUrl(String colorCardUrl) {
        this.colorCardUrl = colorCardUrl;
    }

    @Column(name = "COLOR_CARD", nullable = true, length = 32)
    public String getColorCard() {
        return this.colorCard;
    }

    public void setColorCard(String colorCard) {
        this.colorCard = colorCard;
    }

    @Column(name = "SAMPLE_ID", nullable = true, length = 32)
    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "COLOR_NO", nullable = true, length = 32)
    public String getColorNo() {
        return this.colorNo;
    }

    public void setColorNo(String colorNo) {
        this.colorNo = colorNo;
    }

    @Column(name = "IS_COLOR_NUM", nullable = true, length = 6)
    public String getIsColorNum() {
        return this.isColorNum;
    }

    public void setIsColorNum(String isColorNum) {
        this.isColorNum = isColorNum;
    }

    @Column(name = "COLOR_NUM_URL", nullable = true, length = 256)
    public String getColorNumUrl() {
        return this.colorNumUrl;
    }

    public void setColorNumUrl(String colorNumUrl) {
        this.colorNumUrl = colorNumUrl;
    }

    @Column(name = "COLOR_NUM", nullable = true, length = 32)
    public String getColorNum() {
        return this.colorNum;
    }

    public void setColorNum(String colorNum) {
        this.colorNum = colorNum;
    }

    @Column(name = "COLOR_CARD_REMARK", nullable = true, length = 256)
    public String getColorCardRemark() {
        return this.colorCardRemark;
    }

    public void setColorCardRemark(String colorCardRemark) {
        this.colorCardRemark = colorCardRemark;
    }

    @Column(name = "COLOR_NUM_REMARK", nullable = true, length = 256)
    public String getColorNumRemark() {
        return this.colorNumRemark;
    }

    public void setColorNumRemark(String colorNumRemark) {
        this.colorNumRemark = colorNumRemark;
    }

    @Column(name = "IS_COLOR_DATA", nullable = true, length = 6)
    public String getIsColorData() {
        return this.isColorData;
    }

    public void setIsColorData(String isColorData) {
        this.isColorData = isColorData;
    }

    @Column(name = "COLOR_DATA", nullable = true, length = 32)
    public String getColorData() {
        return this.colorData;
    }

    public void setColorData(String colorData) {
        this.colorData = colorData;
    }

    @Column(name = "COLOR_DATA_URL", nullable = true, length = 256)
    public String getColorDataUrl() {
        return this.colorDataUrl;
    }

    public void setColorDataUrl(String colorDataUrl) {
        this.colorDataUrl = colorDataUrl;
    }

    @Column(name = "IS_COLOR_QTX", nullable = true, length = 6)
    public String getIsColorQtx() {
        return this.isColorQtx;
    }

    public void setIsColorQtx(String isColorQtx) {
        this.isColorQtx = isColorQtx;
    }

    @Column(name = "COLOR_QTX", nullable = true, length = 32)
    public String getColorQtx() {
        return this.colorQtx;
    }

    public void setColorQtx(String colorQtx) {
        this.colorQtx = colorQtx;
    }

    @Column(name = "COLOR_QTX_URL", nullable = true, length = 256)
    public String getColorQtxUrl() {
        return this.colorQtxUrl;
    }

    public void setColorQtxUrl(String colorQtxUrl) {
        this.colorQtxUrl = colorQtxUrl;
    }

    @Column(name = "COLOR_TYPE", nullable = true, length = 32)
    public String getColorType() {
        return this.colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    @Column(name = "CGY", nullable = true, length = 32)
    public String getCgy() {
        return this.cgy;
    }

    public void setCgy(String cgy) {
        this.cgy = cgy;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }

    @Column(name = "COLOR_SLG", nullable = true, length = 32)
    public Double getColorSlg() {
        return this.colorSlg;
    }

    public void setColorSlg(Double colorSlg) {
        this.colorSlg = colorSlg;
    }

    @Column(name = "HXWZ", nullable = true, length = 32)
    public String getHxwz() {
        return this.hxwz;
    }

    public void setHxwz(String hxwz) {
        this.hxwz = hxwz;
    }

    @Column(name = "GJS", nullable = true, length = 32)
    public String getGjs() {
        return this.gjs;
    }

    public void setGjs(String gjs) {
        this.gjs = gjs;
    }

    @Column(name = "SY_TO", nullable = true, length = 32)
    public String getSyTo() {
        return this.syTo;
    }

    public void setSyTo(String syTo) {
        this.syTo = syTo;
    }

    @Column(name = "CUS_REMARK", nullable = true, length = 256)
    public String getCusRemark() {
        return this.cusRemark;
    }

    public void setCusRemark(String cusRemark) {
        this.cusRemark = cusRemark;
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

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "syxp_num", nullable = true, length = 256)
    public String getSyxpNum() {
        return syxpNum;
    }

    public void setSyxpNum(String syxpNum) {
        this.syxpNum = syxpNum;
    }

    @Column(name = "recevie_date", nullable = true, length = 256)
    public String getRecevieDate() {
        return recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }
    @Column(name = "YS_DATE", nullable = true, length = 32)
    public String getYsDate() {
        return this.ysDate;
    }

    public void setYsDate(String ysDate) {
        this.ysDate = ysDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public String getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(String levelDays) {
        this.levelDays = levelDays;
    }

}
