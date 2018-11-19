package com.emk.produce.cargospace.entity;

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
@Table(name = "emk_cargo_space", schema = "")
public class EmkCargoSpaceEntity
        implements Serializable {
    @Excel(name = "业务员")
    private String businesser;
    private String businesserName;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "款式大类")
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "数量")
    private Integer total;
    @Excel(name = "总金额")
    private Double sumMoney;
    @Excel(name = "尺码")
    private String size;
    @Excel(name = "单价")
    private Double price;
    @Excel(name = "币种")
    private String bz;
    @Excel(name = "总箱数")
    private Integer sumBoxTotal;
    @Excel(name = "总体积")
    private Double sumBoxVolume;
    @Excel(name = "总净重")
    private Double sumBoxJz;
    @Excel(name = "总毛重")
    private Double sumBoxMao;
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "订舱通知单号")
    private String cargoNo;
    @Excel(name = "提交日期")
    private String kdDate;
    @Excel(name = "出货通知单号")
    private String outForumNo;
    @Excel(name = "离厂放行条号")
    private String levealFactoryNo;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商")
    private String gysCode;
    @Excel(name = "船务员")
    private String cwyer;
    @Excel(name = "货代名称")
    private String hdName;
    @Excel(name = "目的国")
    private String destination;
    @Excel(name = "货好时间")
    private String haoDate;
    @Excel(name = "开船时间")
    private String goDate;
    @Excel(name = "发票号")
    private String fpNum;
    @Excel(name = "到港时间")
    private String arrvieDate;
    @Excel(name = "收货人")
    private String shrer;
    @Excel(name = "电话")
    private String telphone;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "生产合同号")
    private String produceNum;
    @Excel(name = "长度")
    private String chang;
    @Excel(name = "宽度")
    private String kuan;
    @Excel(name = "高度")
    private String gao;
    @Excel(name = "单箱毛重")
    private String oneMz;
    @Excel(name = "单箱净重")
    private String oneJz;
    @Excel(name = "单箱体积")
    private String oneVolume;
    @Excel(name = "金额")
    private String money;
    @Excel(name = "订舱状态")
    private String cargoState;
    @Excel(name = "出厂状态")
    private String outFactoryState;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;

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

    @Column(name = "DEVELOPER", nullable = true, length = 32)
    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Column(name = "DEVELOPER_NAME", nullable = true, length = 32)
    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
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

    @Column(name = "TOTAL", nullable = true, length = 32)
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "SUM_MONEY", nullable = true, scale = 2, length = 32)
    public Double getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "SIZE", nullable = true, length = 32)
    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "PRICE", nullable = true, scale = 2, length = 32)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "BZ", nullable = true, length = 32)
    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    @Column(name = "CARGO_NO", nullable = true, length = 32)
    public String getCargoNo() {
        return this.cargoNo;
    }

    public void setCargoNo(String cargoNo) {
        this.cargoNo = cargoNo;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }

    @Column(name = "OUT_FORUM_NO", nullable = true, length = 32)
    public String getOutForumNo() {
        return this.outForumNo;
    }

    public void setOutForumNo(String outForumNo) {
        this.outForumNo = outForumNo;
    }

    @Column(name = "LEVEAL_FACTORY_NO", nullable = true, length = 32)
    public String getLevealFactoryNo() {
        return this.levealFactoryNo;
    }

    public void setLevealFactoryNo(String levealFactoryNo) {
        this.levealFactoryNo = levealFactoryNo;
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

    @Column(name = "CWYER", nullable = true, length = 32)
    public String getCwyer() {
        return this.cwyer;
    }

    public void setCwyer(String cwyer) {
        this.cwyer = cwyer;
    }

    @Column(name = "HD_NAME", nullable = true, length = 56)
    public String getHdName() {
        return this.hdName;
    }

    public void setHdName(String hdName) {
        this.hdName = hdName;
    }

    @Column(name = "DESTINATION", nullable = true, length = 32)
    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "HAO_DATE", nullable = true, length = 32)
    public String getHaoDate() {
        return this.haoDate;
    }

    public void setHaoDate(String haoDate) {
        this.haoDate = haoDate;
    }

    @Column(name = "GO_DATE", nullable = true, length = 32)
    public String getGoDate() {
        return this.goDate;
    }

    public void setGoDate(String goDate) {
        this.goDate = goDate;
    }

    @Column(name = "FP_NUM", nullable = true, length = 32)
    public String getFpNum() {
        return this.fpNum;
    }

    public void setFpNum(String fpNum) {
        this.fpNum = fpNum;
    }

    @Column(name = "ARRVIE_DATE", nullable = true, length = 32)
    public String getArrvieDate() {
        return this.arrvieDate;
    }

    public void setArrvieDate(String arrvieDate) {
        this.arrvieDate = arrvieDate;
    }

    @Column(name = "SHRER", nullable = true, length = 32)
    public String getShrer() {
        return this.shrer;
    }

    public void setShrer(String shrer) {
        this.shrer = shrer;
    }

    @Column(name = "TELPHONE", nullable = true, length = 32)
    public String getTelphone() {
        return this.telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "PRODUCE_NUM", nullable = true, length = 32)
    public String getProduceNum() {
        return this.produceNum;
    }

    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }

    @Column(name = "CHANG", nullable = true, length = 32)
    public String getChang() {
        return this.chang;
    }

    public void setChang(String chang) {
        this.chang = chang;
    }

    @Column(name = "KUAN", nullable = true, length = 32)
    public String getKuan() {
        return this.kuan;
    }

    public void setKuan(String kuan) {
        this.kuan = kuan;
    }

    @Column(name = "GAO", nullable = true, length = 32)
    public String getGao() {
        return this.gao;
    }

    public void setGao(String gao) {
        this.gao = gao;
    }

    @Column(name = "ONE_MZ", nullable = true, length = 32)
    public String getOneMz() {
        return this.oneMz;
    }

    public void setOneMz(String oneMz) {
        this.oneMz = oneMz;
    }

    @Column(name = "ONE_JZ", nullable = true, length = 32)
    public String getOneJz() {
        return this.oneJz;
    }

    public void setOneJz(String oneJz) {
        this.oneJz = oneJz;
    }

    @Column(name = "ONE_VOLUME", nullable = true, length = 32)
    public String getOneVolume() {
        return this.oneVolume;
    }

    public void setOneVolume(String oneVolume) {
        this.oneVolume = oneVolume;
    }

    @Column(name = "MONEY", nullable = true, length = 32)
    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Column(name = "CARGO_STATE", nullable = true, length = 32)
    public String getCargoState() {
        return this.cargoState;
    }

    public void setCargoState(String cargoState) {
        this.cargoState = cargoState;
    }

    @Column(name = "OUT_FACTORY_STATE", nullable = true, length = 32)
    public String getOutFactoryState() {
        return this.outFactoryState;
    }

    public void setOutFactoryState(String outFactoryState) {
        this.outFactoryState = outFactoryState;
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
