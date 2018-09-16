package com.emk.storage.storage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name="emk_storage", schema="")
public class EmkStorageEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="产品编号", width=15.0D)
  private String proNum;
  @Excel(name="商品ID", width=15.0D)
  private String proId;
  @Excel(name="中文描述", width=15.0D)
  private String proZnName;
  @Excel(name="规格型号", width=15.0D)
  private String brand;
  @Excel(name="数量", width=15.0D)
  private BigDecimal total;
  @Excel(name="单位", width=15.0D)
  private String unit;
  @Excel(name="英文描述", width=15.0D)
  private String proEnName;
  @Excel(name="商品类型", width=15.0D)
  private String proType;
  @Excel(name="备注", width=15.0D)
  private String remark;
  @Excel(name="商品类型", width=15.0D)
  private String proTypeName;
  @Excel(name="仓库ID", width=15.0D)
  private String storageSetId;
  @Excel(name="仓库名称", width=15.0D)
  private String storageName;
  @Excel(name="库位ID", width=15.0D)
  private String positionId;
  @Excel(name="库位名称", width=15.0D)
  private String positionName;
  private double price;
  
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
  
  @Column(name="PRO_NUM", nullable=true, length=32)
  public String getProNum()
  {
    return this.proNum;
  }
  
  public void setProNum(String proNum)
  {
    this.proNum = proNum;
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
  
  @Column(name="PRO_ZN_NAME", nullable=true, length=32)
  public String getProZnName()
  {
    return this.proZnName;
  }
  
  public void setProZnName(String proZnName)
  {
    this.proZnName = proZnName;
  }
  
  @Column(name="BRAND", nullable=true, length=32)
  public String getBrand()
  {
    return this.brand;
  }
  
  public void setBrand(String brand)
  {
    this.brand = brand;
  }
  
  @Column(name="TOTAL", nullable=true, scale=4, length=32)
  public BigDecimal getTotal()
  {
    return this.total;
  }
  
  public void setTotal(BigDecimal total)
  {
    this.total = total;
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
  
  @Column(name="PRO_EN_NAME", nullable=true, length=32)
  public String getProEnName()
  {
    return this.proEnName;
  }
  
  public void setProEnName(String proEnName)
  {
    this.proEnName = proEnName;
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
  
  @Column(name="REMARK", nullable=true, length=32)
  public String getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(String remark)
  {
    this.remark = remark;
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
  
  @Column(name="STORAGE_SET_ID", nullable=true, length=32)
  public String getStorageSetId()
  {
    return this.storageSetId;
  }
  
  public void setStorageSetId(String storageSetId)
  {
    this.storageSetId = storageSetId;
  }
  
  @Column(name="STORAGE_NAME", nullable=true, length=32)
  public String getStorageName()
  {
    return this.storageName;
  }
  
  public void setStorageName(String storageName)
  {
    this.storageName = storageName;
  }
  
  @Column(name="POSITION_ID", nullable=true, length=32)
  public String getPositionId()
  {
    return this.positionId;
  }
  
  public void setPositionId(String positionId)
  {
    this.positionId = positionId;
  }
  
  @Column(name="POSITION_NAME", nullable=true, length=32)
  public String getPositionName()
  {
    return this.positionName;
  }
  
  public void setPositionName(String positionName)
  {
    this.positionName = positionName;
  }
  
  @Column(name="price", nullable=true, length=32)
  public double getPrice()
  {
    return this.price;
  }
  
  public void setPrice(double price)
  {
    this.price = price;
  }
}
