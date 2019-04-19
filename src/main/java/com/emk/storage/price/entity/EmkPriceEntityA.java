package com.emk.storage.price.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class EmkPriceEntityA implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "报价单号")
    private String pirceNo;
    @Excel(name = "报价日期")
    private String kdDate;
    @Excel(name = "业务员")
    private String businesser;
    @Excel(name = "业务员ID")
    private String businesserName;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "款式大类")
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "是否打过初样")
    private String isPrintSample;
    @Excel(name = "是否收取打样费")
    private String isGetSample;
    @Excel(name = "是否有原样")
    private String isHaveOld;
    private String oldImageUrl;
    @Excel(name = "原样")
    private String oldImage;
    @Excel(name = "是否有设计稿")
    private String isHaveDgr;
    private String dgrImageUrl;
    @Excel(name = "设计稿")
    private String dgrImage;
    @Excel(name = "是否有尺寸表")
    private String isHaveSize;
    private String sizeImageUrl;
    @Excel(name = "尺寸表")
    private String sizeImage;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    @Excel(name = "业务部门ID")
    private String businesseDeptId;
    @Excel(name = "状态")
    private String state;

    @Excel(name = "报价类型")
    private String bjlx;
    @Excel(name = "报价有效期")
    private String limitDate;
    @Excel(name = "目的国 ")
    private String guoJia;
    @Excel(name = "报价币种")
    private String bz;
    @Excel(name = "布面克重")
    private String weight;
    @Excel(name = "布面成分")
    private String chengf;

    @Excel(name = "预计数量")
    private Integer evlateTotal;
    @Excel(name = "尺码范围")
    private String sizeFawei;

    @Excel(name = "测试费")
    private Double testMoney;
    @Excel(name = "利润")
    private Double profit;
    @Excel(name = "不可预见成本")
    private Double unableMoney;
    @Excel(name = "税收成本")
    private Double tax;
    @Excel(name = "是否测试")
    private String isTest;
    @Excel(name = "是否验厂")
    private String isChfactory;
    @Excel(name = "是否验货")
    private String isChkhuo;
    @Excel(name = "面料小计")
    private Double sumYl;
    @Excel(name = "缝制小计")
    private Double sumFeng;
    @Excel(name = "包装小计")
    private Double sumBao;
    @Excel(name = "人工小计")
    private Double sumRg;
    @Excel(name = "染色小计")
    private Double sumRan;
    @Excel(name = "助剂小计")
    private Double sumZj;
    @Excel(name = "管理小计")
    private Double sumGl;
    @Excel(name = "印花小计")
    private Double sumYin;
    @Excel(name = "总计")
    private Double sumMoney;
    @Excel(name = "目标价位")
    private Double targetPrice;
    @Excel(name = "目标价")
    private Double targetJw;
    @Excel(name = "外币价")
    private Double sumWb;
    @Excel(name = "汇率")
    private Double huilv;
    @Excel(name = "汇率日期")
    private String huilvDate;
    @Excel(name = "双方同意价")
    private Double argeePrice;
    @Excel(name = "毛利润率")
    private Double maoRate;
    @Excel(name = "买家同意价")
    private Double gysArgeePrice;
    @Excel(name = "卖家同意价")
    private Double cusArgeePrice;
    @Excel(name = "最终确认价")
    private Double confirmPrice;
    @Excel(name = "币种")
    private String bizhong;

    @Excel(name = "询盘单号")
    private String xpNo;
    @Excel(name = "打样通知单号")
    private String dyNo;

    @Excel(name = "报价说明")
    private String bjsm;
    @Excel(name = "测试说明")
    private String testDesc;

    @Column(name = "bjsm", nullable = true, length = 20)
    public String getBjsm() {
        return bjsm;
    }

    public void setBjsm(String bjsm) {
        this.bjsm = bjsm;
    }

    @Column(name = "test_desc", nullable = true, length = 20)
    public String getTestDesc() {
        return testDesc;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }

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

    @Formula("(select p.NAME_ from act_ru_task p where p.ASSIGNEE_ = id)")
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

    @Column(name = "PIRCE_NO", nullable = true, length = 32)
    public String getPirceNo() {
        return this.pirceNo;
    }

    public void setPirceNo(String pirceNo) {
        this.pirceNo = pirceNo;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }

    @Column(name = "BUSINESSER", nullable = true, length = 32)
    public String getBusinesser() {
        return this.businesser;
    }

    public void setBusinesser(String businesser) {
        this.businesser = businesser;
    }

    @Column(name = "BUSINESSER_NAME", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
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

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "PRO_TYPE_NAME", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    @Column(name = "CUSTOM_SAMPLE_URL", nullable = true, length = 256)
    public String getCustomSampleUrl() {
        return this.customSampleUrl;
    }

    public void setCustomSampleUrl(String customSampleUrl) {
        this.customSampleUrl = customSampleUrl;
    }

    @Column(name = "CUSTOM_SAMPLE", nullable = true, length = 32)
    public String getCustomSample() {
        return this.customSample;
    }

    public void setCustomSample(String customSample) {
        this.customSample = customSample;
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

    @Column(name = "IS_PRINT_SAMPLE", nullable = true, length = 6)
    public String getIsPrintSample() {
        return this.isPrintSample;
    }

    public void setIsPrintSample(String isPrintSample) {
        this.isPrintSample = isPrintSample;
    }

    @Column(name = "IS_GET_SAMPLE", nullable = true, length = 6)
    public String getIsGetSample() {
        return this.isGetSample;
    }

    public void setIsGetSample(String isGetSample) {
        this.isGetSample = isGetSample;
    }

    @Column(name = "IS_HAVE_OLD", nullable = true, length = 6)
    public String getIsHaveOld() {
        return this.isHaveOld;
    }

    public void setIsHaveOld(String isHaveOld) {
        this.isHaveOld = isHaveOld;
    }

    @Column(name = "OLD_IMAGE_URL", nullable = true, length = 256)
    public String getOldImageUrl() {
        return this.oldImageUrl;
    }

    public void setOldImageUrl(String oldImageUrl) {
        this.oldImageUrl = oldImageUrl;
    }

    @Column(name = "OLD_IMAGE", nullable = true, length = 32)
    public String getOldImage() {
        return this.oldImage;
    }

    public void setOldImage(String oldImage) {
        this.oldImage = oldImage;
    }

    @Column(name = "IS_HAVE_DGR", nullable = true, length = 6)
    public String getIsHaveDgr() {
        return this.isHaveDgr;
    }

    public void setIsHaveDgr(String isHaveDgr) {
        this.isHaveDgr = isHaveDgr;
    }

    @Column(name = "DGR_IMAGE_URL", nullable = true, length = 256)
    public String getDgrImageUrl() {
        return this.dgrImageUrl;
    }

    public void setDgrImageUrl(String dgrImageUrl) {
        this.dgrImageUrl = dgrImageUrl;
    }

    @Column(name = "DGR_IMAGE", nullable = true, length = 32)
    public String getDgrImage() {
        return this.dgrImage;
    }

    public void setDgrImage(String dgrImage) {
        this.dgrImage = dgrImage;
    }

    @Column(name = "IS_HAVE_SIZE", nullable = true, length = 6)
    public String getIsHaveSize() {
        return this.isHaveSize;
    }

    public void setIsHaveSize(String isHaveSize) {
        this.isHaveSize = isHaveSize;
    }

    @Column(name = "SIZE_IMAGE_URL", nullable = true, length = 256)
    public String getSizeImageUrl() {
        return this.sizeImageUrl;
    }

    public void setSizeImageUrl(String sizeImageUrl) {
        this.sizeImageUrl = sizeImageUrl;
    }

    @Column(name = "SIZE_IMAGE", nullable = true, length = 32)
    public String getSizeImage() {
        return this.sizeImage;
    }

    public void setSizeImage(String sizeImage) {
        this.sizeImage = sizeImage;
    }

    @Column(name = "TRACER", nullable = true, length = 32)
    public String getTracer() {
        return this.tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    @Column(name = "TRACER_NAME", nullable = true, length = 32)
    public String getTracerName() {
        return this.tracerName;
    }

    public void setTracerName(String tracerName) {
        this.tracerName = tracerName;
    }

    @Column(name = "WEIGHT", nullable = true, length = 32)
    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "CHENGF", nullable = true, length = 32)
    public String getChengf() {
        return this.chengf;
    }

    public void setChengf(String chengf) {
        this.chengf = chengf;
    }

    @Column(name = "BUSINESSE_DEPT_NAME", nullable = true, length = 32)
    public String getBusinesseDeptName() {
        return this.businesseDeptName;
    }

    public void setBusinesseDeptName(String businesseDeptName) {
        this.businesseDeptName = businesseDeptName;
    }

    @Column(name = "BUSINESSE_DEPT_ID", nullable = true, length = 32)
    public String getBusinesseDeptId() {
        return this.businesseDeptId;
    }

    public void setBusinesseDeptId(String businesseDeptId) {
        this.businesseDeptId = businesseDeptId;
    }

    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "BJLX", nullable = true, length = 32)
    public String getBjlx() {
        return this.bjlx;
    }

    public void setBjlx(String bjlx) {
        this.bjlx = bjlx;
    }

    @Column(name = "LIMIT_DATE", nullable = true, length = 32)
    public String getLimitDate() {
        return this.limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    @Column(name = "GUO_JIA", nullable = true, length = 32)
    public String getGuoJia() {
        return this.guoJia;
    }

    public void setGuoJia(String guoJia) {
        this.guoJia = guoJia;
    }

    @Column(name = "BZ", nullable = true, length = 32)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "TARGET_PRICE", nullable = true, scale = 2, length = 32)
    public Double getTargetPrice() {
        return this.targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    @Column(name = "EVLATE_TOTAL", nullable = true, length = 32)
    public Integer getEvlateTotal() {
        return this.evlateTotal;
    }

    public void setEvlateTotal(Integer evlateTotal) {
        this.evlateTotal = evlateTotal;
    }

    @Column(name = "SIZE_FAWEI", nullable = true, length = 32)
    public String getSizeFawei() {
        return this.sizeFawei;
    }

    public void setSizeFawei(String sizeFawei) {
        this.sizeFawei = sizeFawei;
    }

    @Column(name = "TEST_MONEY", nullable = true, scale = 2, length = 32)
    public Double getTestMoney() {
        return this.testMoney;
    }

    public void setTestMoney(Double testMoney) {
        this.testMoney = testMoney;
    }

    @Column(name = "PROFIT", nullable = true, scale = 2, length = 32)
    public Double getProfit() {
        return this.profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Column(name = "UNABLE_MONEY", nullable = true, scale = 2, length = 32)
    public Double getUnableMoney() {
        return this.unableMoney;
    }

    public void setUnableMoney(Double unableMoney) {
        this.unableMoney = unableMoney;
    }

    @Column(name = "TAX", nullable = true, scale = 2, length = 32)
    public Double getTax() {
        return this.tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Column(name = "IS_TEST", nullable = true, length = 32)
    public String getIsTest() {
        return this.isTest;
    }

    public void setIsTest(String isTest) {
        this.isTest = isTest;
    }

    @Column(name = "IS_CHFACTORY", nullable = true, length = 32)
    public String getIsChfactory() {
        return this.isChfactory;
    }

    public void setIsChfactory(String isChfactory) {
        this.isChfactory = isChfactory;
    }

    @Column(name = "IS_CHKHUO", nullable = true, length = 32)
    public String getIsChkhuo() {
        return this.isChkhuo;
    }

    public void setIsChkhuo(String isChkhuo) {
        this.isChkhuo = isChkhuo;
    }

    @Column(name = "SUM_YL", nullable = true, scale = 2, length = 32)
    public Double getSumYl() {
        return this.sumYl;
    }

    public void setSumYl(Double sumYl) {
        this.sumYl = sumYl;
    }

    @Column(name = "SUM_FENG", nullable = true, scale = 2, length = 32)
    public Double getSumFeng() {
        return this.sumFeng;
    }

    public void setSumFeng(Double sumFeng) {
        this.sumFeng = sumFeng;
    }

    @Column(name = "SUM_BAO", nullable = true, scale = 2, length = 32)
    public Double getSumBao() {
        return this.sumBao;
    }

    public void setSumBao(Double sumBao) {
        this.sumBao = sumBao;
    }

    @Column(name = "SUM_RG", nullable = true, scale = 2, length = 32)
    public Double getSumRg() {
        return this.sumRg;
    }

    public void setSumRg(Double sumRg) {
        this.sumRg = sumRg;
    }

    @Column(name = "SUM_RAN", nullable = true, scale = 2, length = 32)
    public Double getSumRan() {
        return this.sumRan;
    }

    public void setSumRan(Double sumRan) {
        this.sumRan = sumRan;
    }

    @Column(name = "SUM_YIN", nullable = true, scale = 2, length = 32)
    public Double getSumYin() {
        return this.sumYin;
    }

    public void setSumYin(Double sumYin) {
        this.sumYin = sumYin;
    }

    @Column(name = "SUM_MONEY", nullable = true, scale = 2, length = 32)
    public Double getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "SUM_WB", nullable = true, scale = 2, length = 32)
    public Double getSumWb() {
        return this.sumWb;
    }

    public void setSumWb(Double sumWb) {
        this.sumWb = sumWb;
    }

    @Column(name = "HUILV", nullable = true, scale = 2, length = 32)
    public Double getHuilv() {
        return this.huilv;
    }

    public void setHuilv(Double huilv) {
        this.huilv = huilv;
    }

    @Column(name = "HUILV_DATE", nullable = true, length = 32)
    public String getHuilvDate() {
        return this.huilvDate;
    }

    public void setHuilvDate(String huilvDate) {
        this.huilvDate = huilvDate;
    }

    @Column(name = "ARGEE_PRICE", nullable = true, scale = 2, length = 32)
    public Double getArgeePrice() {
        return this.argeePrice;
    }

    public void setArgeePrice(Double argeePrice) {
        this.argeePrice = argeePrice;
    }

    @Column(name = "MAO_RATE", nullable = true, scale = 2, length = 32)
    public Double getMaoRate() {
        return this.maoRate;
    }

    public void setMaoRate(Double maoRate) {
        this.maoRate = maoRate;
    }

    @Column(name = "GYS_ARGEE_PRICE", nullable = true, scale = 2, length = 32)
    public Double getGysArgeePrice() {
        return this.gysArgeePrice;
    }

    public void setGysArgeePrice(Double gysArgeePrice) {
        this.gysArgeePrice = gysArgeePrice;
    }

    @Column(name = "CUS_ARGEE_PRICE", nullable = true, scale = 2, length = 32)
    public Double getCusArgeePrice() {
        return this.cusArgeePrice;
    }

    public void setCusArgeePrice(Double cusArgeePrice) {
        this.cusArgeePrice = cusArgeePrice;
    }

    @Column(name = "bizhong", nullable = true, scale = 2, length = 32)
    public String getBizhong() {
        return bizhong;
    }

    public void setBizhong(String bizhong) {
        this.bizhong = bizhong;
    }

    @Column(name = "xp_no", nullable = true, length = 32)
    public String getXpNo() {
        return xpNo;
    }

    public void setXpNo(String xpNo) {
        this.xpNo = xpNo;
    }

    @Column(name = "dy_no", nullable = true, length = 32)
    public String getDyNo() {
        return dyNo;
    }

    public void setDyNo(String dyNo) {
        this.dyNo = dyNo;
    }

    @Column(name = "confirm_price", nullable = true, length = 32)
    public Double getConfirmPrice() {
        return confirmPrice;
    }

    public void setConfirmPrice(Double confirmPrice) {
        this.confirmPrice = confirmPrice;
    }

    @Column(name = "sum_zj", nullable = true, length = 32)
    public Double getSumZj() {
        return sumZj;
    }

    public void setSumZj(Double sumZj) {
        this.sumZj = sumZj;
    }

    @Column(name = "sum_gl", nullable = true, length = 32)
    public Double getSumGl() {
        return sumGl;
    }

    public void setSumGl(Double sumGl) {
        this.sumGl = sumGl;
    }

    @Column(name = "target_jw", nullable = true, length = 32)
    public Double getTargetJw() {
        return targetJw;
    }

    public void setTargetJw(Double targetJw) {
        this.targetJw = targetJw;
    }
}
