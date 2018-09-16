package com.emk.bill.proorderdetail.entity;

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
@Table(name="emk_pro_order_detail", schema="")
public class EmkProOrderDetailEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="订单ID", width=15.0D)
  private String proOrderId;
  @Excel(name="款号", width=15.0D)
  private String sampleNo;
  @Excel(name="数量", width=15.0D)
  private String total;
  @Excel(name="尺码", width=15.0D)
  private String size;
  @Excel(name="颜色", width=15.0D)
  private String color;
  @Excel(name="备注", width=15.0D)
  private String remark;
  @Excel(name="价格", width=15.0D)
  private String price;
  @Excel(name="产品编号", width=15.0D)
  private String proNum;
  @Excel(name="产品名称", width=15.0D)
  private String proName;
  @Excel(name="规格", width=15.0D)
  private String brand;
  @Excel(name="单位", width=15.0D)
  private String unit;
  
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
  
  @Column(name="PRO_ORDER_ID", nullable=true, length=32)
  public String getProOrderId()
  {
    return this.proOrderId;
  }
  
  public void setProOrderId(String proOrderId)
  {
    this.proOrderId = proOrderId;
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
  
  @Column(name="TOTAL", nullable=true, length=32)
  public String getTotal()
  {
    return this.total;
  }
  
  public void setTotal(String total)
  {
    this.total = total;
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
  
  @Column(name="COLOR", nullable=true, length=32)
  public String getColor()
  {
    return this.color;
  }
  
  public void setColor(String color)
  {
    this.color = color;
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
  
  @Column(name="PRICE", nullable=true, length=32)
  public String getPrice()
  {
    return this.price;
  }
  
  public void setPrice(String price)
  {
    this.price = price;
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
  
  @Column(name="PRO_NAME", nullable=true, length=32)
  public String getProName()
  {
    return this.proName;
  }
  
  public void setProName(String proName)
  {
    this.proName = proName;
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
  
  @Column(name="UNIT", nullable=true, length=12)
  public String getUnit()
  {
    return this.unit;
  }
  
  public void setUnit(String unit)
  {
    this.unit = unit;
  }
}
