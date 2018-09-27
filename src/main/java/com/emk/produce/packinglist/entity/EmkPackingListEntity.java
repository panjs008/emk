package com.emk.produce.packinglist.entity;

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
@Table(name = "emk_packing_list", schema = "")
public class EmkPackingListEntity implements Serializable {
    @Excel(name = "款号", width = 15)
    private String sampleNo;
    @Excel(name = "描述", width = 15)
    private String sampleNoDesc;
    @Excel(name = "总箱数", width = 15)
    private Integer sumBoxTotal;
    @Excel(name = "总体积", width = 15)
    private Double sumBoxVolume;
    @Excel(name = "总净重", width = 15)
    private Double sumBoxJz;
    @Excel(name = "总毛重", width = 15)
    private Double sumBoxMao;
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "订单号", width = 15)
    private String orderNo;
    @Excel(name = "数量", width = 15)
    private String total;
    @Excel(name = "生产方名称", width = 15)
    private String scfmc;
    @Excel(name = "地址", width = 15)
    private String address;
    @Excel(name = "法定代表签名", width = 15)
    private String signer;
    @Excel(name = "签名日期", width = 15)
    private String signDate;
    @Excel(name = "发票编号", width = 15)
    private String fpbh;
    @Excel(name = "发票日期", width = 15)
    private String fpDate;
    @Excel(name = "长度", width = 15)
    private String changdu;
    @Excel(name = "宽度", width = 15)
    private String kuandu;
    @Excel(name = "高度", width = 15)
    private String gaodu;
    @Excel(name = "单箱毛重", width = 15)
    private String oneBoxVolume;
    @Excel(name = "单箱毛重", width = 15)
    private String oneBoxMz;
    @Excel(name = "单箱净重", width = 15)
    private String oneBoxJz;
    @Excel(name = "尺码", width = 15)
    private String size;
    @Excel(name = "颜色", width = 15)
    private String color;
    @Excel(name = "客户代码", width = 15)
    private String cusNum;
    @Excel(name = "客户名称", width = 15)
    private String cusName;

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 32)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "SUM_BOX_TOTAL", nullable = true, length = 32)
    public Integer getSumBoxTotal() {
        return this.sumBoxTotal;
    }

    public void setSumBoxTotal(Integer sumBoxTotal) {
        this.sumBoxTotal = sumBoxTotal;
    }

    @Column(name = "SUM_BOX_VOLUME", nullable = true, scale = 2, length = 32)
    public Double getSumBoxVolume() {
        return this.sumBoxVolume;
    }

    public void setSumBoxVolume(Double sumBoxVolume) {
        this.sumBoxVolume = sumBoxVolume;
    }

    @Column(name = "SUM_BOX_JZ", nullable = true, scale = 2, length = 32)
    public Double getSumBoxJz() {
        return this.sumBoxJz;
    }

    public void setSumBoxJz(Double sumBoxJz) {
        this.sumBoxJz = sumBoxJz;
    }

    @Column(name = "SUM_BOX_MAO", nullable = true, scale = 2, length = 32)
    public Double getSumBoxMao() {
        return this.sumBoxMao;
    }

    public void setSumBoxMao(Double sumBoxMao) {
        this.sumBoxMao = sumBoxMao;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CREATE_NAME", nullable = true, length = 32)
    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name = "CREATE_BY", nullable = true, length = 32)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "CREATE_DATE", nullable = true, length = 32)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "SYS_ORG_CODE", nullable = true, length = 32)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "TOTAL", nullable = true, length = 32)
    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Column(name = "SCFMC", nullable = true, length = 56)
    public String getScfmc() {
        return this.scfmc;
    }

    public void setScfmc(String scfmc) {
        this.scfmc = scfmc;
    }

    @Column(name = "ADDRESS", nullable = true, length = 256)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "SIGNER", nullable = true, length = 32)
    public String getSigner() {
        return this.signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    @Column(name = "SIGN_DATE", nullable = true, length = 32)
    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    @Column(name = "FPBH", nullable = true, length = 32)
    public String getFpbh() {
        return this.fpbh;
    }

    public void setFpbh(String fpbh) {
        this.fpbh = fpbh;
    }

    @Column(name = "FP_DATE", nullable = true, length = 32)
    public String getFpDate() {
        return this.fpDate;
    }

    public void setFpDate(String fpDate) {
        this.fpDate = fpDate;
    }

    @Column(name = "CHANGDU", nullable = true, length = 32)
    public String getChangdu() {
        return this.changdu;
    }

    public void setChangdu(String changdu) {
        this.changdu = changdu;
    }

    @Column(name = "KUANDU", nullable = true, length = 32)
    public String getKuandu() {
        return this.kuandu;
    }

    public void setKuandu(String kuandu) {
        this.kuandu = kuandu;
    }

    @Column(name = "GAODU", nullable = true, length = 32)
    public String getGaodu() {
        return this.gaodu;
    }

    public void setGaodu(String gaodu) {
        this.gaodu = gaodu;
    }

    @Column(name = "ONE_BOX_VOLUME", nullable = true, length = 32)
    public String getOneBoxVolume() {
        return this.oneBoxVolume;
    }

    public void setOneBoxVolume(String oneBoxVolume) {
        this.oneBoxVolume = oneBoxVolume;
    }

    @Column(name = "ONE_BOX_MZ", nullable = true, length = 32)
    public String getOneBoxMz() {
        return this.oneBoxMz;
    }

    public void setOneBoxMz(String oneBoxMz) {
        this.oneBoxMz = oneBoxMz;
    }

    @Column(name = "ONE_BOX_JZ", nullable = true, length = 32)
    public String getOneBoxJz() {
        return this.oneBoxJz;
    }

    public void setOneBoxJz(String oneBoxJz) {
        this.oneBoxJz = oneBoxJz;
    }

    @Column(name = "SIZE", nullable = true, length = 32)
    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "COLOR", nullable = true, length = 32)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
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
}
