package com.emk.storage.supplier.entity;

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
@Table(name="emk_supplier", schema="")
public class EmkSupplierEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String sysOrgCode;
  @Excel(name="企业全称", width=15.0D)
  private String supplier;
  @Excel(name="供应商代码", width=15.0D)
  private String supplierCode;
  @Excel(name="供应商类型", width=15.0D)
  private String supplierType;
  @Excel(name="营业执照号", width=15.0D)
  private String licence;
  @Excel(name="有效期", width=15.0D)
  private String limitDate;
  @Excel(name="地址", width=15.0D)
  private String address;
  @Excel(name="产品类型", width=15.0D)
  private String productType;
  @Excel(name="纳税人识别号", width=15.0D)
  private String taxpayerNum;
  @Excel(name="开户行", width=15.0D)
  private String bankName;
  @Excel(name="开户账号 ", width=15.0D)
  private String bankAccount;
  @Excel(name="电话", width=15.0D)
  private String telphone;
  @Excel(name="法定代表人", width=15.0D)
  private String legaler;
  @Excel(name="联系人", width=15.0D)
  private String contacter;
  @Excel(name="财务联系人", width=15.0D)
  private String cwContacter;
  @Excel(name="营业执照URL", width=15.0D)
  private String licenceUrl;
  
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
  
  @Column(name="SUPPLIER", nullable=true, length=256)
  public String getSupplier()
  {
    return this.supplier;
  }
  
  public void setSupplier(String supplier)
  {
    this.supplier = supplier;
  }
  
  @Column(name="SUPPLIER_CODE", nullable=true, length=32)
  public String getSupplierCode()
  {
    return this.supplierCode;
  }
  
  public void setSupplierCode(String supplierCode)
  {
    this.supplierCode = supplierCode;
  }
  
  @Column(name="SUPPLIER_TYPE", nullable=true, length=32)
  public String getSupplierType()
  {
    return this.supplierType;
  }
  
  public void setSupplierType(String supplierType)
  {
    this.supplierType = supplierType;
  }
  
  @Column(name="LICENCE", nullable=true, length=32)
  public String getLicence()
  {
    return this.licence;
  }
  
  public void setLicence(String licence)
  {
    this.licence = licence;
  }
  
  @Column(name="LIMIT_DATE", nullable=true, length=32)
  public String getLimitDate()
  {
    return this.limitDate;
  }
  
  public void setLimitDate(String limitDate)
  {
    this.limitDate = limitDate;
  }
  
  @Column(name="ADDRESS", nullable=true, length=256)
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  @Column(name="PRODUCT_TYPE", nullable=true, length=32)
  public String getProductType()
  {
    return this.productType;
  }
  
  public void setProductType(String productType)
  {
    this.productType = productType;
  }
  
  @Column(name="TAXPAYER_NUM", nullable=true, length=32)
  public String getTaxpayerNum()
  {
    return this.taxpayerNum;
  }
  
  public void setTaxpayerNum(String taxpayerNum)
  {
    this.taxpayerNum = taxpayerNum;
  }
  
  @Column(name="BANK_NAME", nullable=true, length=256)
  public String getBankName()
  {
    return this.bankName;
  }
  
  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }
  
  @Column(name="BANK_ACCOUNT", nullable=true, length=56)
  public String getBankAccount()
  {
    return this.bankAccount;
  }
  
  public void setBankAccount(String bankAccount)
  {
    this.bankAccount = bankAccount;
  }
  
  @Column(name="TELPHONE", nullable=true, length=32)
  public String getTelphone()
  {
    return this.telphone;
  }
  
  public void setTelphone(String telphone)
  {
    this.telphone = telphone;
  }
  
  @Column(name="LEGALER", nullable=true, length=32)
  public String getLegaler()
  {
    return this.legaler;
  }
  
  public void setLegaler(String legaler)
  {
    this.legaler = legaler;
  }
  
  @Column(name="CONTACTER", nullable=true, length=32)
  public String getContacter()
  {
    return this.contacter;
  }
  
  public void setContacter(String contacter)
  {
    this.contacter = contacter;
  }
  
  @Column(name="CW_CONTACTER", nullable=true, length=32)
  public String getCwContacter()
  {
    return this.cwContacter;
  }
  
  public void setCwContacter(String cwContacter)
  {
    this.cwContacter = cwContacter;
  }
  
  @Column(name="LICENCE_URL", nullable=true, length=256)
  public String getLicenceUrl()
  {
    return this.licenceUrl;
  }
  
  public void setLicenceUrl(String licenceUrl)
  {
    this.licenceUrl = licenceUrl;
  }
}
