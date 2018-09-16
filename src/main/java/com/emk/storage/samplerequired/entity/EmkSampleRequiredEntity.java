package com.emk.storage.samplerequired.entity;

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
@Table(name="emk_sample_required", schema="")
public class EmkSampleRequiredEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="需求单号", width=15.0D)
  private String requiredNo;
  @Excel(name="提交日期", width=15.0D)
  private String kdDate;
  @Excel(name="业务员", width=15.0D)
  private String businesser;
  @Excel(name="业务员ID", width=15.0D)
  private String businesserName;
  @Excel(name="客户代码", width=15.0D)
  private String cusNum;
  @Excel(name="客户名称", width=15.0D)
  private String cusName;
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
  @Excel(name="款号", width=15.0D)
  private String sampleNo;
  @Excel(name="描述", width=15.0D)
  private String sampleNoDesc;
  @Excel(name="是否打过初样", width=15.0D)
  private String isPrintSample;
  @Excel(name="是否收取打样费", width=15.0D)
  private String isGetSample;
  @Excel(name="是否有原样", width=15.0D)
  private String isHaveOld;
  @Excel(name="原样", width=15.0D)
  private String oldImageUrl;
  @Excel(name="原样", width=15.0D)
  private String oldImage;
  @Excel(name="是否有设计稿", width=15.0D)
  private String isHaveDgr;
  @Excel(name="设计稿", width=15.0D)
  private String dgrImageUrl;
  @Excel(name="设计稿", width=15.0D)
  private String dgrImage;
  @Excel(name="是否有尺寸表", width=15.0D)
  private String isHaveSize;
  @Excel(name="尺寸表", width=15.0D)
  private String sizeImageUrl;
  @Excel(name="尺寸表", width=15.0D)
  private String sizeImage;
  @Excel(name="意向货交期", width=15.0D)
  private String ysDate;
  @Excel(name="距交期余天数", width=15.0D)
  private Integer levelDays;
  @Excel(name="业务跟单员", width=15.0D)
  private String tracer;
  @Excel(name="业务跟单员ID", width=15.0D)
  private String tracerName;
  @Excel(name="版次", width=15.0D)
  private String version;
  @Excel(name="布面克重", width=15.0D)
  private String weight;
  @Excel(name="布面成分", width=15.0D)
  private String chengf;
  @Excel(name="业务部门", width=15.0D)
  private String businesseDeptName;
  @Excel(name="业务部门ID", width=15.0D)
  private String businesseDeptId;
  @Excel(name="颜色英文名称", width=15.0D)
  private String colorEnName;
  @Excel(name="颜色中文名称", width=15.0D)
  private String colorZnName;
  @Excel(name="色号", width=15.0D)
  private String colorValue;
  @Excel(name="尺码", width=15.0D)
  private String size;
  @Excel(name="数量", width=15.0D)
  private Integer total;
  @Excel(name="机台尺寸", width=15.0D)
  private String machineSize;
  @Excel(name="下机重量", width=15.0D)
  private String machineWeight;
  @Excel(name="下机尺寸", width=15.0D)
  private String downMachineSize;
  @Excel(name="密度", width=15.0D)
  private String prcent;
  @Excel(name="用料", width=15.0D)
  private String userLiao;
  @Excel(name="单件织造时间", width=15.0D)
  private String oneMakeDate;
  @Excel(name="染色要求", width=15.0D)
  private String ranRequired;
  @Excel(name="缝制要求", width=15.0D)
  private String fengRequired;
  @Excel(name="状态", width=15.0D)
  private String state;
  @Excel(name="样品去向", width=15.0D)
  private String sampleTo;
  @Excel(name="收件人", width=15.0D)
  private String recevier;
  @Excel(name="收件日期", width=15.0D)
  private String recevieDate;
  @Excel(name="寄件人", width=15.0D)
  private String sender;
  @Excel(name="寄件数量", width=15.0D)
  private Integer sendTotal;
  @Excel(name="剩余数量", width=15.0D)
  private Integer levelTotal;
  @Excel(name="审批意见", width=15.0D)
  private String checkAdvice;
  @Excel(name="布面意见", width=15.0D)
  private String buAdvice;
  @Excel(name="缝制意见", width=15.0D)
  private String fengAdvice;
  @Excel(name="交期意见", width=15.0D)
  private String jqAdvice;
  @Excel(name="备注", width=15.0D)
  private String remark;
  @Excel(name="样品类型", width=15.0D)
  private String type;
  @Excel(name="下机克重", width=15.0D)
  private Double xjkz;
  @Excel(name="单件所需时间", width=15.0D)
  private String djsxTime;
  @Excel(name="单位", width=15.0D)
  private String unit;
  @Excel(name="机台日产量", width=15.0D)
  private Integer jtrcl;
  @Excel(name="前道损耗率", width=15.0D)
  private Double qdshl;
  @Excel(name="后道损耗率", width=15.0D)
  private Double hdshl;
  
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
  
  @Column(name="REQUIRED_NO", nullable=true, length=32)
  public String getRequiredNo()
  {
    return this.requiredNo;
  }
  
  public void setRequiredNo(String requiredNo)
  {
    this.requiredNo = requiredNo;
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
  
  @Column(name="SAMPLE_NO", nullable=true, length=32)
  public String getSampleNo()
  {
    return this.sampleNo;
  }
  
  public void setSampleNo(String sampleNo)
  {
    this.sampleNo = sampleNo;
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
  
  @Column(name="IS_PRINT_SAMPLE", nullable=true, length=6)
  public String getIsPrintSample()
  {
    return this.isPrintSample;
  }
  
  public void setIsPrintSample(String isPrintSample)
  {
    this.isPrintSample = isPrintSample;
  }
  
  @Column(name="IS_GET_SAMPLE", nullable=true, length=6)
  public String getIsGetSample()
  {
    return this.isGetSample;
  }
  
  public void setIsGetSample(String isGetSample)
  {
    this.isGetSample = isGetSample;
  }
  
  @Column(name="IS_HAVE_OLD", nullable=true, length=6)
  public String getIsHaveOld()
  {
    return this.isHaveOld;
  }
  
  public void setIsHaveOld(String isHaveOld)
  {
    this.isHaveOld = isHaveOld;
  }
  
  @Column(name="OLD_IMAGE_URL", nullable=true, length=256)
  public String getOldImageUrl()
  {
    return this.oldImageUrl;
  }
  
  public void setOldImageUrl(String oldImageUrl)
  {
    this.oldImageUrl = oldImageUrl;
  }
  
  @Column(name="OLD_IMAGE", nullable=true, length=32)
  public String getOldImage()
  {
    return this.oldImage;
  }
  
  public void setOldImage(String oldImage)
  {
    this.oldImage = oldImage;
  }
  
  @Column(name="IS_HAVE_DGR", nullable=true, length=6)
  public String getIsHaveDgr()
  {
    return this.isHaveDgr;
  }
  
  public void setIsHaveDgr(String isHaveDgr)
  {
    this.isHaveDgr = isHaveDgr;
  }
  
  @Column(name="DGR_IMAGE_URL", nullable=true, length=256)
  public String getDgrImageUrl()
  {
    return this.dgrImageUrl;
  }
  
  public void setDgrImageUrl(String dgrImageUrl)
  {
    this.dgrImageUrl = dgrImageUrl;
  }
  
  @Column(name="DGR_IMAGE", nullable=true, length=32)
  public String getDgrImage()
  {
    return this.dgrImage;
  }
  
  public void setDgrImage(String dgrImage)
  {
    this.dgrImage = dgrImage;
  }
  
  @Column(name="IS_HAVE_SIZE", nullable=true, length=6)
  public String getIsHaveSize()
  {
    return this.isHaveSize;
  }
  
  public void setIsHaveSize(String isHaveSize)
  {
    this.isHaveSize = isHaveSize;
  }
  
  @Column(name="SIZE_IMAGE_URL", nullable=true, length=256)
  public String getSizeImageUrl()
  {
    return this.sizeImageUrl;
  }
  
  public void setSizeImageUrl(String sizeImageUrl)
  {
    this.sizeImageUrl = sizeImageUrl;
  }
  
  @Column(name="SIZE_IMAGE", nullable=true, length=32)
  public String getSizeImage()
  {
    return this.sizeImage;
  }
  
  public void setSizeImage(String sizeImage)
  {
    this.sizeImage = sizeImage;
  }
  
  @Column(name="YS_DATE", nullable=true, length=32)
  public String getYsDate()
  {
    return this.ysDate;
  }
  
  public void setYsDate(String ysDate)
  {
    this.ysDate = ysDate;
  }
  
  @Column(name="LEVEL_DAYS", nullable=true, length=32)
  public Integer getLevelDays()
  {
    return this.levelDays;
  }
  
  public void setLevelDays(Integer levelDays)
  {
    this.levelDays = levelDays;
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
  
  @Column(name="VERSION", nullable=true, length=32)
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  @Column(name="WEIGHT", nullable=true, length=32)
  public String getWeight()
  {
    return this.weight;
  }
  
  public void setWeight(String weight)
  {
    this.weight = weight;
  }
  
  @Column(name="CHENGF", nullable=true, length=32)
  public String getChengf()
  {
    return this.chengf;
  }
  
  public void setChengf(String chengf)
  {
    this.chengf = chengf;
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
  
  @Column(name="COLOR_EN_NAME", nullable=true, length=32)
  public String getColorEnName()
  {
    return this.colorEnName;
  }
  
  public void setColorEnName(String colorEnName)
  {
    this.colorEnName = colorEnName;
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
  
  @Column(name="COLOR_VALUE", nullable=true, length=32)
  public String getColorValue()
  {
    return this.colorValue;
  }
  
  public void setColorValue(String colorValue)
  {
    this.colorValue = colorValue;
  }
  
  @Column(name="SIZE", nullable=true, length=32)
  public String getSize()
  {
    return this.size;
  }
  
  public void setSize(String size)
  {
    this.size = size;
  }
  
  @Column(name="TOTAL", nullable=true, length=32)
  public Integer getTotal()
  {
    return this.total;
  }
  
  public void setTotal(Integer total)
  {
    this.total = total;
  }
  
  @Column(name="MACHINE_SIZE", nullable=true, length=32)
  public String getMachineSize()
  {
    return this.machineSize;
  }
  
  public void setMachineSize(String machineSize)
  {
    this.machineSize = machineSize;
  }
  
  @Column(name="MACHINE_WEIGHT", nullable=true, length=32)
  public String getMachineWeight()
  {
    return this.machineWeight;
  }
  
  public void setMachineWeight(String machineWeight)
  {
    this.machineWeight = machineWeight;
  }
  
  @Column(name="DOWN_MACHINE_SIZE", nullable=true, length=32)
  public String getDownMachineSize()
  {
    return this.downMachineSize;
  }
  
  public void setDownMachineSize(String downMachineSize)
  {
    this.downMachineSize = downMachineSize;
  }
  
  @Column(name="PRCENT", nullable=true, length=32)
  public String getPrcent()
  {
    return this.prcent;
  }
  
  public void setPrcent(String prcent)
  {
    this.prcent = prcent;
  }
  
  @Column(name="USER_LIAO", nullable=true, length=32)
  public String getUserLiao()
  {
    return this.userLiao;
  }
  
  public void setUserLiao(String userLiao)
  {
    this.userLiao = userLiao;
  }
  
  @Column(name="ONE_MAKE_DATE", nullable=true, length=32)
  public String getOneMakeDate()
  {
    return this.oneMakeDate;
  }
  
  public void setOneMakeDate(String oneMakeDate)
  {
    this.oneMakeDate = oneMakeDate;
  }
  
  @Column(name="RAN_REQUIRED", nullable=true, length=256)
  public String getRanRequired()
  {
    return this.ranRequired;
  }
  
  public void setRanRequired(String ranRequired)
  {
    this.ranRequired = ranRequired;
  }
  
  @Column(name="FENG_REQUIRED", nullable=true, length=256)
  public String getFengRequired()
  {
    return this.fengRequired;
  }
  
  public void setFengRequired(String fengRequired)
  {
    this.fengRequired = fengRequired;
  }
  
  @Column(name="STATE", nullable=true, length=32)
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  @Column(name="SAMPLE_TO", nullable=true, length=256)
  public String getSampleTo()
  {
    return this.sampleTo;
  }
  
  public void setSampleTo(String sampleTo)
  {
    this.sampleTo = sampleTo;
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
  
  @Column(name="RECEVIE_DATE", nullable=true, length=32)
  public String getRecevieDate()
  {
    return this.recevieDate;
  }
  
  public void setRecevieDate(String recevieDate)
  {
    this.recevieDate = recevieDate;
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
  
  @Column(name="LEVEL_TOTAL", nullable=true, length=32)
  public Integer getLevelTotal()
  {
    return this.levelTotal;
  }
  
  public void setLevelTotal(Integer levelTotal)
  {
    this.levelTotal = levelTotal;
  }
  
  @Column(name="CHECK_ADVICE", nullable=true, length=256)
  public String getCheckAdvice()
  {
    return this.checkAdvice;
  }
  
  public void setCheckAdvice(String checkAdvice)
  {
    this.checkAdvice = checkAdvice;
  }
  
  @Column(name="BU_ADVICE", nullable=true, length=256)
  public String getBuAdvice()
  {
    return this.buAdvice;
  }
  
  public void setBuAdvice(String buAdvice)
  {
    this.buAdvice = buAdvice;
  }
  
  @Column(name="FENG_ADVICE", nullable=true, length=256)
  public String getFengAdvice()
  {
    return this.fengAdvice;
  }
  
  public void setFengAdvice(String fengAdvice)
  {
    this.fengAdvice = fengAdvice;
  }
  
  @Column(name="JQ_ADVICE", nullable=true, length=256)
  public String getJqAdvice()
  {
    return this.jqAdvice;
  }
  
  public void setJqAdvice(String jqAdvice)
  {
    this.jqAdvice = jqAdvice;
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
  
  @Column(name="TYPE", nullable=true, length=32)
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  @Column(name="XJKZ", nullable=true, scale=2, length=32)
  public Double getXjkz()
  {
    return this.xjkz;
  }
  
  public void setXjkz(Double xjkz)
  {
    this.xjkz = xjkz;
  }
  
  @Column(name="DJSX_TIME", nullable=true, length=32)
  public String getDjsxTime()
  {
    return this.djsxTime;
  }
  
  public void setDjsxTime(String djsxTime)
  {
    this.djsxTime = djsxTime;
  }
  
  @Column(name="UNIT", nullable=true, length=32)
  public String getUnit()
  {
    return this.unit;
  }
  
  public void setUnit(String unit)
  {
    this.unit = unit;
  }
  
  @Column(name="JTRCL", nullable=true, length=32)
  public Integer getJtrcl()
  {
    return this.jtrcl;
  }
  
  public void setJtrcl(Integer jtrcl)
  {
    this.jtrcl = jtrcl;
  }
  
  @Column(name="QDSHL", nullable=true, scale=2, length=32)
  public Double getQdshl()
  {
    return this.qdshl;
  }
  
  public void setQdshl(Double qdshl)
  {
    this.qdshl = qdshl;
  }
  
  @Column(name="HDSHL", nullable=true, scale=2, length=32)
  public Double getHdshl()
  {
    return this.hdshl;
  }
  
  public void setHdshl(Double hdshl)
  {
    this.hdshl = hdshl;
  }
}
