package com.emk.storage.enquirydetail.entity;

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
@Table(name="emk_enquiry_detail", schema="")
public class EmkEnquiryDetailEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="询盘ID", width=15.0D)
  private String enquiryId;
  @Excel(name="颜色名称", width=15.0D)
  private String color;
  @Excel(name="色号", width=15.0D)
  private String colorValue;
  @Excel(name="尺码", width=15.0D)
  private String size;
  @Excel(name="数量", width=15.0D)
  private Integer total;
  @Excel(name="单尺码总数", width=15.0D)
  private Integer sizeTotal;
  @Excel(name="单颜色总数", width=15.0D)
  private Integer colorTotal;
  @Excel(name="单价", width=15.0D)
  private Double price;
  
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
  
  @Column(name="ENQUIRY_ID", nullable=true, length=32)
  public String getEnquiryId()
  {
    return this.enquiryId;
  }
  
  public void setEnquiryId(String enquiryId)
  {
    this.enquiryId = enquiryId;
  }
  
  @Column(name="COLOR", nullable=true, length=32)
  public String getColor()
  {
    return this.color;
  }
  
  public void setColor(String color)
  {
    this.color = color;
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
  
  @Column(name="SIZE_TOTAL", nullable=true, length=32)
  public Integer getSizeTotal()
  {
    return this.sizeTotal;
  }
  
  public void setSizeTotal(Integer sizeTotal)
  {
    this.sizeTotal = sizeTotal;
  }
  
  @Column(name="COLOR_TOTAL", nullable=true, length=32)
  public Integer getColorTotal()
  {
    return this.colorTotal;
  }
  
  public void setColorTotal(Integer colorTotal)
  {
    this.colorTotal = colorTotal;
  }
  
  @Column(name="PRICE", nullable=true, scale=2, length=32)
  public Double getPrice()
  {
    return this.price;
  }
  
  public void setPrice(Double price)
  {
    this.price = price;
  }
}
