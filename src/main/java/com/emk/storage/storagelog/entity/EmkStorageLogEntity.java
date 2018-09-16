package com.emk.storage.storagelog.entity;

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
@Table(name="emk_storage_log", schema="")
public class EmkStorageLogEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="商品ID", width=15.0D)
  private String proId;
  @Excel(name="产品编号", width=15.0D)
  private String proNum;
  @Excel(name="中文描述", width=15.0D)
  private String proZnName;
  @Excel(name="入库前数量", width=15.0D)
  private String preTotal;
  @Excel(name="入库后数量", width=15.0D)
  private String nextTotal;
  @Excel(name="入库数量", width=15.0D)
  private String total;
  @Excel(name="商品类型", width=15.0D)
  private String proTypeName;
  @Excel(name="入库单号", width=15.0D)
  private String rkNo;
  
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
  
  @Column(name="PRO_ID", nullable=true, length=32)
  public String getProId()
  {
    return this.proId;
  }
  
  public void setProId(String proId)
  {
    this.proId = proId;
  }
  
  @Column(name="PRO_NUM", nullable=true, length=32)
  public String getProNum()
  {
    return this.proNum;
  }
  
  public void setProNum(String proNum)
  {
    this.proNum = proNum;
  }
  
  @Column(name="PRO_ZN_NAME", nullable=true, length=32)
  public String getProZnName()
  {
    return this.proZnName;
  }
  
  public void setProZnName(String proZnName)
  {
    this.proZnName = proZnName;
  }
  
  @Column(name="PRE_TOTAL", nullable=true, length=32)
  public String getPreTotal()
  {
    return this.preTotal;
  }
  
  public void setPreTotal(String preTotal)
  {
    this.preTotal = preTotal;
  }
  
  @Column(name="NEXT_TOTAL", nullable=true, length=32)
  public String getNextTotal()
  {
    return this.nextTotal;
  }
  
  public void setNextTotal(String nextTotal)
  {
    this.nextTotal = nextTotal;
  }
  
  @Column(name="TOTAL", nullable=true, length=32)
  public String getTotal()
  {
    return this.total;
  }
  
  public void setTotal(String total)
  {
    this.total = total;
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
  
  @Column(name="RK_NO", nullable=true, length=32)
  public String getRkNo()
  {
    return this.rkNo;
  }
  
  public void setRkNo(String rkNo)
  {
    this.rkNo = rkNo;
  }
}
