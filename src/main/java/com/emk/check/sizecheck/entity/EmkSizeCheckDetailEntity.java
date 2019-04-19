package com.emk.check.sizecheck.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emk_size_detail", schema = "")
public class EmkSizeCheckDetailEntity implements Serializable {
    private String id;
    private String sizeCheckId;
    private String seqNum;
    private String buWei;
    private String sortDesc;
    private String btotalA;
    private String btotalB;
    private String btotalC;
    private String btotalD;
    private String btotalE;
    private String btotalF;
    private String btotalG;
    private String btotalH;
    private String btotalI;
    private String btotalJ;
    private String btotalK;

    private String ctotalA;
    private String ctotalB;
    private String ctotalC;
    private String ctotalD;
    private String ctotalE;
    private String ctotalF;
    private String ctotalG;
    private String ctotalH;
    private String ctotalI;
    private String ctotalJ;
    private String ctotalK;

    private String dtotalA;
    private String dtotalB;
    private String dtotalC;
    private String dtotalD;
    private String dtotalE;
    private String dtotalF;
    private String dtotalG;
    private String dtotalH;
    private String dtotalI;
    private String dtotalJ;
    private String dtotalK;

    private EmkSizeTotalEntityA emkSizeTotalEntity;

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

    @Column(name ="size_check_id",nullable=true,length=50)
    public String getSizeCheckId() {
        return sizeCheckId;
    }

    public void setSizeCheckId(String sizeCheckId) {
        this.sizeCheckId = sizeCheckId;
    }

    @Column(name ="bu_wei",nullable=true,length=50)
    public String getBuWei() {
        return buWei;
    }

    public void setBuWei(String buWei) {
        this.buWei = buWei;
    }

    @Column(name ="sort_desc",nullable=true,length=50)
    public String getSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    @Column(name ="btotal_a",nullable=false,length=36)
    public String getBtotalA() {
        return btotalA;
    }

    public void setBtotalA(String btotalA) {
        this.btotalA = btotalA;
    }

    @Column(name ="btotal_b",nullable=false,length=36)
    public String getBtotalB() {
        return btotalB;
    }

    public void setBtotalB(String btotalB) {
        this.btotalB = btotalB;
    }

    @Column(name ="btotal_c",nullable=false,length=36)

    public String getBtotalC() {
        return btotalC;
    }

    public void setBtotalC(String btotalC) {
        this.btotalC = btotalC;
    }

    @Column(name ="btotal_d",nullable=false,length=36)

    public String getBtotalD() {
        return btotalD;
    }

    public void setBtotalD(String btotalD) {
        this.btotalD = btotalD;
    }

    @Column(name ="btotal_e",nullable=false,length=36)

    public String getBtotalE() {
        return btotalE;
    }

    public void setBtotalE(String btotalE) {
        this.btotalE = btotalE;
    }

    @Column(name ="btotal_f",nullable=false,length=36)

    public String getBtotalF() {
        return btotalF;
    }

    public void setBtotalF(String btotalF) {
        this.btotalF = btotalF;
    }

    @Column(name ="btotal_g",nullable=false,length=36)

    public String getBtotalG() {
        return btotalG;
    }

    public void setBtotalG(String btotalG) {
        this.btotalG = btotalG;
    }

    @Column(name ="btotal_h",nullable=false,length=36)

    public String getBtotalH() {
        return btotalH;
    }

    public void setBtotalH(String btotalH) {
        this.btotalH = btotalH;
    }

    @Column(name ="btotal_i",nullable=false,length=36)

    public String getBtotalI() {
        return btotalI;
    }

    public void setBtotalI(String btotalI) {
        this.btotalI = btotalI;
    }

    @Column(name ="btotal_j",nullable=false,length=36)

    public String getBtotalJ() {
        return btotalJ;
    }

    public void setBtotalJ(String btotalJ) {
        this.btotalJ = btotalJ;
    }

    @Column(name ="btotal_k",nullable=false,length=36)

    public String getBtotalK() {
        return btotalK;
    }

    public void setBtotalK(String btotalK) {
        this.btotalK = btotalK;
    }

    @Column(name ="ctotal_a",nullable=false,length=36)

    public String getCtotalA() {
        return ctotalA;
    }

    public void setCtotalA(String ctotalA) {
        this.ctotalA = ctotalA;
    }

    @Column(name ="ctotal_b",nullable=false,length=36)

    public String getCtotalB() {
        return ctotalB;
    }

    public void setCtotalB(String ctotalB) {
        this.ctotalB = ctotalB;
    }

    @Column(name ="ctotal_c",nullable=false,length=36)

    public String getCtotalC() {
        return ctotalC;
    }

    public void setCtotalC(String ctotalC) {
        this.ctotalC = ctotalC;
    }

    @Column(name ="ctotal_d",nullable=false,length=36)

    public String getCtotalD() {
        return ctotalD;
    }

    public void setCtotalD(String ctotalD) {
        this.ctotalD = ctotalD;
    }

    @Column(name ="ctotal_e",nullable=false,length=36)

    public String getCtotalE() {
        return ctotalE;
    }

    public void setCtotalE(String ctotalE) {
        this.ctotalE = ctotalE;
    }

    @Column(name ="ctotal_f",nullable=false,length=36)

    public String getCtotalF() {
        return ctotalF;
    }

    public void setCtotalF(String ctotalF) {
        this.ctotalF = ctotalF;
    }

    @Column(name ="ctotal_g",nullable=false,length=36)

    public String getCtotalG() {
        return ctotalG;
    }

    public void setCtotalG(String ctotalG) {
        this.ctotalG = ctotalG;
    }

    @Column(name ="ctotal_h",nullable=false,length=36)

    public String getCtotalH() {
        return ctotalH;
    }

    public void setCtotalH(String ctotalH) {
        this.ctotalH = ctotalH;
    }

    @Column(name ="ctotal_i",nullable=false,length=36)

    public String getCtotalI() {
        return ctotalI;
    }

    public void setCtotalI(String ctotalI) {
        this.ctotalI = ctotalI;
    }

    @Column(name ="ctotal_j",nullable=false,length=36)

    public String getCtotalJ() {
        return ctotalJ;
    }

    public void setCtotalJ(String ctotalJ) {
        this.ctotalJ = ctotalJ;
    }

    @Column(name ="ctotal_k",nullable=false,length=36)

    public String getCtotalK() {
        return ctotalK;
    }

    public void setCtotalK(String ctotalK) {
        this.ctotalK = ctotalK;
    }

    @Column(name ="dtotal_a",nullable=false,length=36)

    public String getDtotalA() {
        return dtotalA;
    }

    public void setDtotalA(String dtotalA) {
        this.dtotalA = dtotalA;
    }

    @Column(name ="dtotal_b",nullable=false,length=36)

    public String getDtotalB() {
        return dtotalB;
    }

    public void setDtotalB(String dtotalB) {
        this.dtotalB = dtotalB;
    }

    @Column(name ="dtotal_c",nullable=false,length=36)

    public String getDtotalC() {
        return dtotalC;
    }

    public void setDtotalC(String dtotalC) {
        this.dtotalC = dtotalC;
    }

    @Column(name ="dtotal_d",nullable=false,length=36)

    public String getDtotalD() {
        return dtotalD;
    }

    public void setDtotalD(String dtotalD) {
        this.dtotalD = dtotalD;
    }

    @Column(name ="dtotal_e",nullable=false,length=36)

    public String getDtotalE() {
        return dtotalE;
    }

    public void setDtotalE(String dtotalE) {
        this.dtotalE = dtotalE;
    }

    @Column(name ="dtotal_f",nullable=false,length=36)

    public String getDtotalF() {
        return dtotalF;
    }

    public void setDtotalF(String dtotalF) {
        this.dtotalF = dtotalF;
    }

    @Column(name ="dtotal_g",nullable=false,length=36)

    public String getDtotalG() {
        return dtotalG;
    }

    public void setDtotalG(String dtotalG) {
        this.dtotalG = dtotalG;
    }

    @Column(name ="dtotal_h",nullable=false,length=36)

    public String getDtotalH() {
        return dtotalH;
    }

    public void setDtotalH(String dtotalH) {
        this.dtotalH = dtotalH;
    }

    @Column(name ="dtotal_i",nullable=false,length=36)

    public String getDtotalI() {
        return dtotalI;
    }

    public void setDtotalI(String dtotalI) {
        this.dtotalI = dtotalI;
    }

    @Column(name ="dtotal_j",nullable=false,length=36)

    public String getDtotalJ() {
        return dtotalJ;
    }

    public void setDtotalJ(String dtotalJ) {
        this.dtotalJ = dtotalJ;
    }

    @Column(name ="dtotal_k",nullable=false,length=36)

    public String getDtotalK() {
        return dtotalK;
    }

    public void setDtotalK(String dtotalK) {
        this.dtotalK = dtotalK;
    }

    @OneToOne(mappedBy="emkSizeCheckDetailEntity")
    public EmkSizeTotalEntityA getEmkSizeTotalEntity() {
        return emkSizeTotalEntity;
    }

    public void setEmkSizeTotalEntity(EmkSizeTotalEntityA emkSizeTotalEntity) {
        this.emkSizeTotalEntity = emkSizeTotalEntity;
    }

    @Column(name ="seq_num",nullable=true,length=50)
    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }
}
