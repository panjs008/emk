package com.emk.storage.color.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emk_color_detail", schema = "")
public class EmkColorDetailEntity implements Serializable {
    private String id;
    @Excel(name = "颜色中文名称")
    private String colorZnName;
    @Excel(name = "颜色英文名称")
    private String colorEnName;
    @Excel(name = "色号")
    private String seNum;
    @Excel(name = "色样编号")
    private String syNum;
    @Excel(name = "主光源")
    private String zgy;
    @Excel(name = "次光源")
    private String cgy;
    @Excel(name = "交期")
    private String recevieDate;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "色样规格")
    private String colorBrand;
    @Excel(name = "色样数量")
    private String colorTotal;
    private String colorId;
    private String sortDesc;

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

    @Column(name = "COLOR_ZN_NAME", nullable = true, length = 32)
    public String getColorZnName() {
        return this.colorZnName;
    }

    public void setColorZnName(String colorZnName) {
        this.colorZnName = colorZnName;
    }

    @Column(name = "COLOR_EN_NAME", nullable = true, length = 32)
    public String getColorEnName() {
        return this.colorEnName;
    }

    public void setColorEnName(String colorEnName) {
        this.colorEnName = colorEnName;
    }

    @Column(name = "SE_NUM", nullable = true, length = 32)
    public String getSeNum() {
        return this.seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum;
    }

    @Column(name = "SY_NUM", nullable = true, length = 32)
    public String getSyNum() {
        return this.syNum;
    }

    public void setSyNum(String syNum) {
        this.syNum = syNum;
    }

    @Column(name = "ZGY", nullable = true, length = 32)
    public String getZgy() {
        return this.zgy;
    }

    public void setZgy(String zgy) {
        this.zgy = zgy;
    }

    @Column(name = "CGY", nullable = true, length = 32)
    public String getCgy() {
        return this.cgy;
    }

    public void setCgy(String cgy) {
        this.cgy = cgy;
    }

    @Column(name = "RECEVIE_DATE", nullable = true, length = 32)
    public String getRecevieDate() {
        return this.recevieDate;
    }

    public void setRecevieDate(String recevieDate) {
        this.recevieDate = recevieDate;
    }

    @Column(name = "COLOR_BRAND", nullable = true, length = 32)
    public String getColorBrand() {
        return this.colorBrand;
    }

    public void setColorBrand(String colorBrand) {
        this.colorBrand = colorBrand;
    }

    @Column(name = "COLOR_TOTAL", nullable = true, length = 32)
    public String getColorTotal() {
        return this.colorTotal;
    }

    public void setColorTotal(String colorTotal) {
        this.colorTotal = colorTotal;
    }

    @Column(name = "color_id", nullable = true, length = 32)
    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    @Column(name = "sort_desc", nullable = true, length = 32)
    public String getSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    @Column(name = "version", nullable = true, length = 32)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
