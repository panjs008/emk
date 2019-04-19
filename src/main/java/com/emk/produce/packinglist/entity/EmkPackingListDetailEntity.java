package com.emk.produce.packinglist.entity;

import com.emk.check.sizecheck.entity.EmkSizeTotalEntityB;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntityC;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emk_packing_list_detail", schema = "")
public class EmkPackingListDetailEntity implements Serializable {
    private String id;
    private String pactId;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "总箱数")
    private Integer sumBoxTotal;
    @Excel(name = "总体积")
    private Double sumBoxVolume;
    @Excel(name = "总净重")
    private Double sumBoxJz;
    @Excel(name = "总毛重")
    private Double sumBoxMao;

    @Excel(name = "数量")
    private String total;
    @Excel(name = "长度")
    private String changdu;
    @Excel(name = "宽度")
    private String kuandu;
    @Excel(name = "高度")
    private String gaodu;
    @Excel(name = "单箱毛重")
    private String oneBoxVolume;
    @Excel(name = "单箱毛重")
    private String oneBoxMz;
    @Excel(name = "单箱净重")
    private String oneBoxJz;
    @Excel(name = "尺码")
    private String size;
    @Excel(name = "颜色")
    private String color;

    private EmkSizeTotalEntityC emkSizeTotalEntity;

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

    @Column(name = "pact_id", nullable = true, length = 32)
    public String getPactId() {
        return pactId;
    }

    public void setPactId(String pactId) {
        this.pactId = pactId;
    }

    @OneToOne(mappedBy="emkPackingListDetailEntity")
    public EmkSizeTotalEntityC getEmkSizeTotalEntity() {
        return emkSizeTotalEntity;
    }

    public void setEmkSizeTotalEntity(EmkSizeTotalEntityC emkSizeTotalEntity) {
        this.emkSizeTotalEntity = emkSizeTotalEntity;
    }
}
