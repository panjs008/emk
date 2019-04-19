package com.emk.storage.sampledetail.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.emk.check.sizecheck.entity.EmkSizeTotalEntityE;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntityG;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_sample_detail", schema = "")
public class EmkSampleDetailEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "品名")
    private String proZnName;
    @Excel(name = "物料ID")
    private String proId;
    @Excel(name = "物料编号")
    private String proNum;
    @Excel(name = "规格")
    private String brand;
    @Excel(name = "数量")
    private String signTotal;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "单价")
    private String signPrice;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "款号")
    private String hsCode;
    @Excel(name = "款号名称")
    private String hsName;
    @Excel(name = "样品ID")
    private String sampleId;
    @Excel(name = "物料类型")
    private String type;
    @Excel(name = "比例")
    private String precent;
    @Excel(name = "单件用量")
    private Double yongliang;
    @Excel(name = "损耗率")
    private Double sunhaoPrecent;
    @Excel(name = "成本")
    private Double chengben;
    @Excel(name = "捻向")
    private String direction;
    @Excel(name = "批号")
    private String betchNum;
    @Excel(name = "幅宽")
    private String width;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "克重")
    private String weight;
    @Excel(name = "成分")
    private String chengf;
    @Excel(name = "位置")
    private String position;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商Code")
    private String gysCode;
    @Excel(name = "入库状态")
    private String rkState;
    @Excel(name = "总数量")
    private String sumTotal;
    @Excel(name = "总金额")
    private String sumPrice;
    @Excel(name = "总用量")
    private String sumYongliang;
    @Excel(name = "正式合同编号")
    private String htNum;
    @Excel(name = "采购需求单号")
    private String cgxqdh;
    @Excel(name = "采购合同号")
    private String cghtbh;
    @Excel(name = "订单号")
    private String orderNum;
    @Excel(name = "大货交期")
    private String dhjqDate;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;

    private EmkSizeTotalEntityG emkSizeTotalEntity;


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

    @Column(name = "PRO_ZN_NAME", nullable = true, length = 56)
    public String getProZnName() {
        return this.proZnName;
    }

    public void setProZnName(String proZnName) {
        this.proZnName = proZnName;
    }

    @Column(name = "PRO_ID", nullable = true, length = 32)
    public String getProId() {
        return this.proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    @Column(name = "PRO_NUM", nullable = true, length = 32)
    public String getProNum() {
        return this.proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    @Column(name = "SIGN_TOTAL", nullable = true, length = 32)
    public String getSignTotal() {
        return this.signTotal;
    }

    public void setSignTotal(String signTotal) {
        this.signTotal = signTotal;
    }

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "SIGN_PRICE", nullable = true, length = 32)
    public String getSignPrice() {
        return this.signPrice;
    }

    public void setSignPrice(String signPrice) {
        this.signPrice = signPrice;
    }

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "HS_CODE", nullable = true, length = 32)
    public String getHsCode() {
        return this.hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    @Column(name = "HS_NAME", nullable = true, length = 32)
    public String getHsName() {
        return this.hsName;
    }

    public void setHsName(String hsName) {
        this.hsName = hsName;
    }

    @Column(name = "SAMPLE_ID", nullable = true, length = 32)
    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "TYPE", nullable = true, length = 6)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "PRECENT", nullable = true, length = 32)
    public String getPrecent() {
        return this.precent;
    }

    public void setPrecent(String precent) {
        this.precent = precent;
    }

    @Column(name = "YONGLIANG", nullable = true, scale = 2, length = 32)
    public Double getYongliang() {
        return this.yongliang;
    }

    public void setYongliang(Double yongliang) {
        this.yongliang = yongliang;
    }

    @Column(name = "SUNHAO_PRECENT", nullable = true, scale = 2, length = 32)
    public Double getSunhaoPrecent() {
        return this.sunhaoPrecent;
    }

    public void setSunhaoPrecent(Double sunhaoPrecent) {
        this.sunhaoPrecent = sunhaoPrecent;
    }

    @Column(name = "CHENGBEN", nullable = true, scale = 2, length = 32)
    public Double getChengben() {
        return this.chengben;
    }

    public void setChengben(Double chengben) {
        this.chengben = chengben;
    }

    @Column(name = "DIRECTION", nullable = true, length = 32)
    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Column(name = "BETCH_NUM", nullable = true, length = 32)
    public String getBetchNum() {
        return this.betchNum;
    }

    public void setBetchNum(String betchNum) {
        this.betchNum = betchNum;
    }

    @Column(name = "WIDTH", nullable = true, length = 32)
    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "WEIGHT", nullable = true, length = 32)
    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "POSITION", nullable = true, length = 32)
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "BRAND", nullable = true, length = 56)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "GYS", nullable = true, length = 32)
    public String getGys() {
        return this.gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    @Column(name = "GYS_CODE", nullable = true, length = 32)
    public String getGysCode() {
        return this.gysCode;
    }

    public void setGysCode(String gysCode) {
        this.gysCode = gysCode;
    }

    @Column(name = "RK_STATE", nullable = true, length = 32)
    public String getRkState() {
        return this.rkState;
    }

    public void setRkState(String rkState) {
        this.rkState = rkState;
    }

    @Column(name = "chengf", nullable = true, length = 32)
    public String getChengf() {
        return chengf;
    }

    public void setChengf(String chengf) {
        this.chengf = chengf;
    }

    @Column(name = "sum_total", nullable = true, length = 32)
    public String getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(String sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Column(name = "sum_yongliang", nullable = true, length = 32)
    public String getSumYongliang() {
        return sumYongliang;
    }

    public void setSumYongliang(String sumYongliang) {
        this.sumYongliang = sumYongliang;
    }

    @Column(name = "ht_num", nullable = true, length = 32)
    public String getHtNum() {
        return htNum;
    }

    public void setHtNum(String htNum) {
        this.htNum = htNum;
    }

    @Column(name = "sum_price", nullable = true, length = 32)
    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Column(name = "cgxqdh", nullable = true, length = 32)
    public String getCgxqdh() {
        return cgxqdh;
    }

    public void setCgxqdh(String cgxqdh) {
        this.cgxqdh = cgxqdh;
    }

    @Column(name = "cghtbh", nullable = true, length = 32)
    public String getCghtbh() {
        return cghtbh;
    }

    public void setCghtbh(String cghtbh) {
        this.cghtbh = cghtbh;
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

    @Column(name = "order_num", nullable = true, length = 32)
    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "DHJQ_DATE", nullable = true, length = 32)
    public String getDhjqDate() {
        return this.dhjqDate;
    }

    public void setDhjqDate(String dhjqDate) {
        this.dhjqDate = dhjqDate;
    }

    @OneToOne(mappedBy="emkSampleDetailEntity")
    public EmkSizeTotalEntityG getEmkSizeTotalEntity() {
        return emkSizeTotalEntity;
    }

    public void setEmkSizeTotalEntity(EmkSizeTotalEntityG emkSizeTotalEntity) {
        this.emkSizeTotalEntity = emkSizeTotalEntity;
    }
}
