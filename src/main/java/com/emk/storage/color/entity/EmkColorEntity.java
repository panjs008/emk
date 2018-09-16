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
@Table(name="emk_color", schema="")
public class EmkColorEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="款号", width=15.0D)
  private String sampleNo;
  @Excel(name="客户编号", width=15.0D)
  private String cusNum;
  @Excel(name="客户名称", width=15.0D)
  private String cusName;
  @Excel(name="是否标准色卡", width=15.0D)
  private String isColorCard;
  @Excel(name="标准色卡URL", width=15.0D)
  private String colorCardUrl;
  @Excel(name="标准色卡", width=15.0D)
  private String colorCard;
  @Excel(name="样品ID", width=15.0D)
  private String sampleId;
  @Excel(name="色样需求单号", width=15.0D)
  private String colorNo;
  @Excel(name="是否标准色号", width=15.0D)
  private String isColorNum;
  @Excel(name="标准色号URL", width=15.0D)
  private String colorNumUrl;
  @Excel(name="标准色号", width=15.0D)
  private String colorNum;
  @Excel(name="色卡说明", width=15.0D)
  private String colorCardRemark;
  @Excel(name="色号说明", width=15.0D)
  private String colorNumRemark;
  @Excel(name="是否标准色数据", width=15.0D)
  private String isColorData;
  @Excel(name="标准色数据", width=15.0D)
  private String colorData;
  @Excel(name="标准色数据", width=15.0D)
  private String colorDataUrl;
  @Excel(name="是否QTX", width=15.0D)
  private String isColorQtx;
  @Excel(name="QTX", width=15.0D)
  private String colorQtx;
  @Excel(name="QTX", width=15.0D)
  private String colorQtxUrl;
  @Excel(name="色样种类", width=15.0D)
  private String colorType;
  @Excel(name="颜色中文名称", width=15.0D)
  private String colorZnName;
  @Excel(name="颜色英文名称", width=15.0D)
  private String colorEnName;
  @Excel(name="色号", width=15.0D)
  private String seNum;
  @Excel(name="色样编号", width=15.0D)
  private String syNum;
  @Excel(name="主光源", width=15.0D)
  private String zgy;
  @Excel(name="次光源", width=15.0D)
  private String cgy;
  @Excel(name="开单日期", width=15.0D)
  private String kdDate;
  @Excel(name="交期", width=15.0D)
  private String recevieDate;
  @Excel(name="色样规格", width=15.0D)
  private String colorBrand;
  @Excel(name="色样数量", width=15.0D)
  private String colorTotal;
  @Excel(name="色牢度", width=15.0D)
  private Double colorSlg;
  @Excel(name="化学物质", width=15.0D)
  private String hxwz;
  @Excel(name="重金属", width=15.0D)
  private String gjs;
  @Excel(name="色样去向", width=15.0D)
  private String syTo;
  @Excel(name="收件人", width=15.0D)
  private String recevier;
  @Excel(name="日期", width=15.0D)
  private String riqi;
  @Excel(name="寄件人", width=15.0D)
  private String sender;
  @Excel(name="寄件数量", width=15.0D)
  private Integer sendTotal;
  @Excel(name="剩余数量", width=15.0D)
  private Integer syTotal;
  @Excel(name="客户评语", width=15.0D)
  private String cusRemark;
  @Excel(name="色样状态", width=15.0D)
  private String colorState;
  @Excel(name="需求单审批意见", width=15.0D)
  private String advice;
  @Excel(name="技术员染色意见", width=15.0D)
  private String jsyAdvice;
  @Excel(name="技术部交期意见", width=15.0D)
  private String jsbAdvice;
  @Excel(name="业务员", width=15.0D)
  private String businesser;
  @Excel(name="业务员ID", width=15.0D)
  private String businesserName;
  @Excel(name="业务跟单员", width=15.0D)
  private String tracer;
  @Excel(name="业务跟单员ID", width=15.0D)
  private String tracerName;
  @Excel(name="业务部门", width=15.0D)
  private String businesseDeptName;
  @Excel(name="业务部门ID", width=15.0D)
  private String businesseDeptId;
  @Excel(name="寄件日期", width=15.0D)
  private String sendDate;
  @Excel(name="工艺种类", width=15.0D)
  private String gyzl;
  @Excel(name="款式大类", width=15.0D)
  private String proType;
  @Excel(name="款式大类", width=15.0D)
  private String proTypeName;
  @Excel(name="图片", width=15.0D)
  private String customSampleUrl;
  @Excel(name="图片", width=15.0D)
  private String customSample;
  @Excel(name="版次", width=15.0D)
  private String version;
  @Excel(name="描述", width=15.0D)
  private String sampleNoDesc;
  @Excel(name="备注", width=15.0D)
  private String remark;
  
  @Id
  @GeneratedValue(generator="paymentableGenerator")
  @GenericGenerator(name="paymentableGenerator", strategy="uuid")
  @Column(name="ID", nullable=false, length=36)
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  @Column(name="CREATE_NAME", nullable=true, length=50)
  public String getCreateName()
  {
    return this.createName;
  }
  
  public void setCreateName(String createName)
  {
    this.createName = createName;
  }
  
  @Column(name="CREATE_BY", nullable=true, length=50)
  public String getCreateBy()
  {
    return this.createBy;
  }
  
  public void setCreateBy(String createBy)
  {
    this.createBy = createBy;
  }
  
  @Column(name="CREATE_DATE", nullable=true, length=20)
  public Date getCreateDate()
  {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }
  
  @Column(name="SYS_ORG_CODE", nullable=true, length=50)
  public String getSysOrgCode()
  {
    return this.sysOrgCode;
  }
  
  public void setSysOrgCode(String sysOrgCode)
  {
    this.sysOrgCode = sysOrgCode;
  }
  
  @Column(name="SAMPLE_NO", nullable=true, length=32)
  public String getSampleNo()
  {
    return this.sampleNo;
  }
  
  public void setSampleNo(String sampleNo)
  {
    this.sampleNo = sampleNo;
  }
  
  @Column(name="CUS_NUM", nullable=true, length=32)
  public String getCusNum()
  {
    return this.cusNum;
  }
  
  public void setCusNum(String cusNum)
  {
    this.cusNum = cusNum;
  }
  
  @Column(name="CUS_NAME", nullable=true, length=32)
  public String getCusName()
  {
    return this.cusName;
  }
  
  public void setCusName(String cusName)
  {
    this.cusName = cusName;
  }
  
  @Column(name="IS_COLOR_CARD", nullable=true, length=6)
  public String getIsColorCard()
  {
    return this.isColorCard;
  }
  
  public void setIsColorCard(String isColorCard)
  {
    this.isColorCard = isColorCard;
  }
  
  @Column(name="COLOR_CARD_URL", nullable=true, length=256)
  public String getColorCardUrl()
  {
    return this.colorCardUrl;
  }
  
  public void setColorCardUrl(String colorCardUrl)
  {
    this.colorCardUrl = colorCardUrl;
  }
  
  @Column(name="COLOR_CARD", nullable=true, length=32)
  public String getColorCard()
  {
    return this.colorCard;
  }
  
  public void setColorCard(String colorCard)
  {
    this.colorCard = colorCard;
  }
  
  @Column(name="SAMPLE_ID", nullable=true, length=32)
  public String getSampleId()
  {
    return this.sampleId;
  }
  
  public void setSampleId(String sampleId)
  {
    this.sampleId = sampleId;
  }
  
  @Column(name="COLOR_NO", nullable=true, length=32)
  public String getColorNo()
  {
    return this.colorNo;
  }
  
  public void setColorNo(String colorNo)
  {
    this.colorNo = colorNo;
  }
  
  @Column(name="IS_COLOR_NUM", nullable=true, length=6)
  public String getIsColorNum()
  {
    return this.isColorNum;
  }
  
  public void setIsColorNum(String isColorNum)
  {
    this.isColorNum = isColorNum;
  }
  
  @Column(name="COLOR_NUM_URL", nullable=true, length=256)
  public String getColorNumUrl()
  {
    return this.colorNumUrl;
  }
  
  public void setColorNumUrl(String colorNumUrl)
  {
    this.colorNumUrl = colorNumUrl;
  }
  
  @Column(name="COLOR_NUM", nullable=true, length=32)
  public String getColorNum()
  {
    return this.colorNum;
  }
  
  public void setColorNum(String colorNum)
  {
    this.colorNum = colorNum;
  }
  
  @Column(name="COLOR_CARD_REMARK", nullable=true, length=256)
  public String getColorCardRemark()
  {
    return this.colorCardRemark;
  }
  
  public void setColorCardRemark(String colorCardRemark)
  {
    this.colorCardRemark = colorCardRemark;
  }
  
  @Column(name="COLOR_NUM_REMARK", nullable=true, length=256)
  public String getColorNumRemark()
  {
    return this.colorNumRemark;
  }
  
  public void setColorNumRemark(String colorNumRemark)
  {
    this.colorNumRemark = colorNumRemark;
  }
  
  @Column(name="IS_COLOR_DATA", nullable=true, length=6)
  public String getIsColorData()
  {
    return this.isColorData;
  }
  
  public void setIsColorData(String isColorData)
  {
    this.isColorData = isColorData;
  }
  
  @Column(name="COLOR_DATA", nullable=true, length=32)
  public String getColorData()
  {
    return this.colorData;
  }
  
  public void setColorData(String colorData)
  {
    this.colorData = colorData;
  }
  
  @Column(name="COLOR_DATA_URL", nullable=true, length=256)
  public String getColorDataUrl()
  {
    return this.colorDataUrl;
  }
  
  public void setColorDataUrl(String colorDataUrl)
  {
    this.colorDataUrl = colorDataUrl;
  }
  
  @Column(name="IS_COLOR_QTX", nullable=true, length=6)
  public String getIsColorQtx()
  {
    return this.isColorQtx;
  }
  
  public void setIsColorQtx(String isColorQtx)
  {
    this.isColorQtx = isColorQtx;
  }
  
  @Column(name="COLOR_QTX", nullable=true, length=32)
  public String getColorQtx()
  {
    return this.colorQtx;
  }
  
  public void setColorQtx(String colorQtx)
  {
    this.colorQtx = colorQtx;
  }
  
  @Column(name="COLOR_QTX_URL", nullable=true, length=256)
  public String getColorQtxUrl()
  {
    return this.colorQtxUrl;
  }
  
  public void setColorQtxUrl(String colorQtxUrl)
  {
    this.colorQtxUrl = colorQtxUrl;
  }
  
  @Column(name="COLOR_TYPE", nullable=true, length=32)
  public String getColorType()
  {
    return this.colorType;
  }
  
  public void setColorType(String colorType)
  {
    this.colorType = colorType;
  }
  
  @Column(name="COLOR_ZN_NAME", nullable=true, length=32)
  public String getColorZnName()
  {
    return this.colorZnName;
  }
  
  public void setColorZnName(String colorZnName)
  {
    this.colorZnName = colorZnName;
  }
  
  @Column(name="COLOR_EN_NAME", nullable=true, length=32)
  public String getColorEnName()
  {
    return this.colorEnName;
  }
  
  public void setColorEnName(String colorEnName)
  {
    this.colorEnName = colorEnName;
  }
  
  @Column(name="SE_NUM", nullable=true, length=32)
  public String getSeNum()
  {
    return this.seNum;
  }
  
  public void setSeNum(String seNum)
  {
    this.seNum = seNum;
  }
  
  @Column(name="SY_NUM", nullable=true, length=32)
  public String getSyNum()
  {
    return this.syNum;
  }
  
  public void setSyNum(String syNum)
  {
    this.syNum = syNum;
  }
  
  @Column(name="ZGY", nullable=true, length=32)
  public String getZgy()
  {
    return this.zgy;
  }
  
  public void setZgy(String zgy)
  {
    this.zgy = zgy;
  }
  
  @Column(name="CGY", nullable=true, length=32)
  public String getCgy()
  {
    return this.cgy;
  }
  
  public void setCgy(String cgy)
  {
    this.cgy = cgy;
  }
  
  @Column(name="KD_DATE", nullable=true, length=32)
  public String getKdDate()
  {
    return this.kdDate;
  }
  
  public void setKdDate(String kdDate)
  {
    this.kdDate = kdDate;
  }
  
  @Column(name="RECEVIE_DATE", nullable=true, length=32)
  public String getRecevieDate()
  {
    return this.recevieDate;
  }
  
  public void setRecevieDate(String recevieDate)
  {
    this.recevieDate = recevieDate;
  }
  
  @Column(name="COLOR_BRAND", nullable=true, length=32)
  public String getColorBrand()
  {
    return this.colorBrand;
  }
  
  public void setColorBrand(String colorBrand)
  {
    this.colorBrand = colorBrand;
  }
  
  @Column(name="COLOR_TOTAL", nullable=true, length=32)
  public String getColorTotal()
  {
    return this.colorTotal;
  }
  
  public void setColorTotal(String colorTotal)
  {
    this.colorTotal = colorTotal;
  }
  
  @Column(name="COLOR_SLG", nullable=true, length=32)
  public Double getColorSlg()
  {
    return this.colorSlg;
  }
  
  public void setColorSlg(Double colorSlg)
  {
    this.colorSlg = colorSlg;
  }
  
  @Column(name="HXWZ", nullable=true, length=32)
  public String getHxwz()
  {
    return this.hxwz;
  }
  
  public void setHxwz(String hxwz)
  {
    this.hxwz = hxwz;
  }
  
  @Column(name="GJS", nullable=true, length=32)
  public String getGjs()
  {
    return this.gjs;
  }
  
  public void setGjs(String gjs)
  {
    this.gjs = gjs;
  }
  
  @Column(name="SY_TO", nullable=true, length=32)
  public String getSyTo()
  {
    return this.syTo;
  }
  
  public void setSyTo(String syTo)
  {
    this.syTo = syTo;
  }
  
  @Column(name="RECEVIER", nullable=true, length=32)
  public String getRecevier()
  {
    return this.recevier;
  }
  
  public void setRecevier(String recevier)
  {
    this.recevier = recevier;
  }
  
  @Column(name="RIQI", nullable=true, length=32)
  public String getRiqi()
  {
    return this.riqi;
  }
  
  public void setRiqi(String riqi)
  {
    this.riqi = riqi;
  }
  
  @Column(name="SENDER", nullable=true, length=32)
  public String getSender()
  {
    return this.sender;
  }
  
  public void setSender(String sender)
  {
    this.sender = sender;
  }
  
  @Column(name="SEND_TOTAL", nullable=true, length=32)
  public Integer getSendTotal()
  {
    return this.sendTotal;
  }
  
  public void setSendTotal(Integer sendTotal)
  {
    this.sendTotal = sendTotal;
  }
  
  @Column(name="SY_TOTAL", nullable=true, length=32)
  public Integer getSyTotal()
  {
    return this.syTotal;
  }
  
  public void setSyTotal(Integer syTotal)
  {
    this.syTotal = syTotal;
  }
  
  @Column(name="CUS_REMARK", nullable=true, length=256)
  public String getCusRemark()
  {
    return this.cusRemark;
  }
  
  public void setCusRemark(String cusRemark)
  {
    this.cusRemark = cusRemark;
  }
  
  @Column(name="COLOR_STATE", nullable=true, length=32)
  public String getColorState()
  {
    return this.colorState;
  }
  
  public void setColorState(String colorState)
  {
    this.colorState = colorState;
  }
  
  @Column(name="ADVICE", nullable=true, length=256)
  public String getAdvice()
  {
    return this.advice;
  }
  
  public void setAdvice(String advice)
  {
    this.advice = advice;
  }
  
  @Column(name="JSY_ADVICE", nullable=true, length=256)
  public String getJsyAdvice()
  {
    return this.jsyAdvice;
  }
  
  public void setJsyAdvice(String jsyAdvice)
  {
    this.jsyAdvice = jsyAdvice;
  }
  
  @Column(name="JSB_ADVICE", nullable=true, length=256)
  public String getJsbAdvice()
  {
    return this.jsbAdvice;
  }
  
  public void setJsbAdvice(String jsbAdvice)
  {
    this.jsbAdvice = jsbAdvice;
  }
  
  @Column(name="BUSINESSER", nullable=true, length=32)
  public String getBusinesser()
  {
    return this.businesser;
  }
  
  public void setBusinesser(String businesser)
  {
    this.businesser = businesser;
  }
  
  @Column(name="BUSINESSER_NAME", nullable=true, length=32)
  public String getBusinesserName()
  {
    return this.businesserName;
  }
  
  public void setBusinesserName(String businesserName)
  {
    this.businesserName = businesserName;
  }
  
  @Column(name="TRACER", nullable=true, length=32)
  public String getTracer()
  {
    return this.tracer;
  }
  
  public void setTracer(String tracer)
  {
    this.tracer = tracer;
  }
  
  @Column(name="TRACER_NAME", nullable=true, length=32)
  public String getTracerName()
  {
    return this.tracerName;
  }
  
  public void setTracerName(String tracerName)
  {
    this.tracerName = tracerName;
  }
  
  @Column(name="BUSINESSE_DEPT_NAME", nullable=true, length=32)
  public String getBusinesseDeptName()
  {
    return this.businesseDeptName;
  }
  
  public void setBusinesseDeptName(String businesseDeptName)
  {
    this.businesseDeptName = businesseDeptName;
  }
  
  @Column(name="BUSINESSE_DEPT_ID", nullable=true, length=32)
  public String getBusinesseDeptId()
  {
    return this.businesseDeptId;
  }
  
  public void setBusinesseDeptId(String businesseDeptId)
  {
    this.businesseDeptId = businesseDeptId;
  }
  
  @Column(name="SEND_DATE", nullable=true, length=32)
  public String getSendDate()
  {
    return this.sendDate;
  }
  
  public void setSendDate(String sendDate)
  {
    this.sendDate = sendDate;
  }
  
  @Column(name="GYZL", nullable=true, length=32)
  public String getGyzl()
  {
    return this.gyzl;
  }
  
  public void setGyzl(String gyzl)
  {
    this.gyzl = gyzl;
  }
  
  @Column(name="PRO_TYPE", nullable=true, length=32)
  public String getProType()
  {
    return this.proType;
  }
  
  public void setProType(String proType)
  {
    this.proType = proType;
  }
  
  @Column(name="PRO_TYPE_NAME", nullable=true, length=32)
  public String getProTypeName()
  {
    return this.proTypeName;
  }
  
  public void setProTypeName(String proTypeName)
  {
    this.proTypeName = proTypeName;
  }
  
  @Column(name="CUSTOM_SAMPLE_URL", nullable=true, length=256)
  public String getCustomSampleUrl()
  {
    return this.customSampleUrl;
  }
  
  public void setCustomSampleUrl(String customSampleUrl)
  {
    this.customSampleUrl = customSampleUrl;
  }
  
  @Column(name="CUSTOM_SAMPLE", nullable=true, length=32)
  public String getCustomSample()
  {
    return this.customSample;
  }
  
  public void setCustomSample(String customSample)
  {
    this.customSample = customSample;
  }
  
  @Column(name="VERSION", nullable=true, length=32)
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  @Column(name="SAMPLE_NO_DESC", nullable=true, length=256)
  public String getSampleNoDesc()
  {
    return this.sampleNoDesc;
  }
  
  public void setSampleNoDesc(String sampleNoDesc)
  {
    this.sampleNoDesc = sampleNoDesc;
  }
  
  @Column(name="REMARK", nullable=true, length=256)
  public String getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(String remark)
  {
    this.remark = remark;
  }
}
