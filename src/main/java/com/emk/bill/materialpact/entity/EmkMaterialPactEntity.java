package com.emk.bill.materialpact.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_material_pact", schema = "")
public class EmkMaterialPactEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "采购合同编号")
    private String materialNo;
    @Excel(name = "提交日期")
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
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "工厂")
    private String factoryName;
    @Excel(name = "工厂Code")
    private String factoryCode;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    @Excel(name = "业务部门ID")
    private String businesseDeptId;
    @Excel(name = "生产跟单员")
    private String developer;
    @Excel(name = "生产跟单员ID")
    private String developerName;
    @Excel(name = "样品交期")
    private String ypjqDate;
    @Excel(name = "距样品交期剩余天数")
    private Integer leaveYpjqDays;
    @Excel(name = "距大货交期剩余天数")
    private Integer leaveDhjqDays;
    @Excel(name = "大货交期")
    private String dhjqDate;
    @Excel(name = "业务跟单员")
    private String tracer;
    @Excel(name = "业务跟单员ID")
    private String tracerName;
    @Excel(name = "合同编号")
    private String htNum;
    @Excel(name = "入库状态")
    private String rkState;
    @Excel(name = "总数量")
    private Double sumTotal;
    @Excel(name = "总金额")
    private Double sumMoney;
    @Excel(name = "总用量")
    private String sumUse;
    @Excel(name = "甲方")
    private String partyA;
    @Excel(name = "甲方ID")
    private String partyAId;
    @Excel(name = "乙方")
    private String partyB;
    @Excel(name = "乙方ID")
    private String partyBId;
    @Excel(name = "FOB")
    private String fob;
    @Excel(name = "交货地点")
    private String place;
    @Excel(name = "仓库")
    private String boundName;
    @Excel(name = "类型")
    private String type;
    @Excel(name = "原产地")
    private String ycd;
    @Excel(name = "付款方式")
    private String payType;
    @Excel(name = "授权代表")
    private String sqdb;
    @Excel(name = "地址")
    private String address;
    @Excel(name = "电话")
    private String telphone;
    @Excel(name = "签定日期")
    private String signDate;
    @Excel(name = "乙方授权代表")
    private String ysqdb;
    @Excel(name = "乙方地址")
    private String yaddress;
    @Excel(name = "乙方电话")
    private String ytelphone;
    @Excel(name = "签定日期")
    private String ysignDate;
    @Excel(name = "订单号")
    private String orderNum;
    @Excel(name = "供应商")
    private String gys;
    @Excel(name = "供应商Code")
    private String gysCode;

    @Excel(name = "正式采购合同编号")
    private String zscghtbh;
    @Excel(name = "采购需求单号")
    private String cgxqdh;
    @Excel(name = "采购合同号")
    private String cghtbh;
    private String flag;            // 0 预采购合同 1 正式采购合同

    @Excel(name = "状态")
    private String state;
    @Excel(name="出厂价")
    private String outPrice;
    private String formType;

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

    @Column(name = "MATERIAL_NO", nullable = true, length = 32)
    public String getMaterialNo() {
        return this.materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
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

    @Column(name = "FACTORY_NAME", nullable = true, length = 32)
    public String getFactoryName() {
        return this.factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    @Column(name = "FACTORY_CODE", nullable = true, length = 32)
    public String getFactoryCode() {
        return this.factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
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

    @Column(name = "YPJQ_DATE", nullable = true, length = 32)
    public String getYpjqDate() {
        return this.ypjqDate;
    }

    public void setYpjqDate(String ypjqDate) {
        this.ypjqDate = ypjqDate;
    }

    @Column(name = "LEAVE_YPJQ_DAYS", nullable = true, length = 32)
    public Integer getLeaveYpjqDays() {
        return this.leaveYpjqDays;
    }

    public void setLeaveYpjqDays(Integer leaveYpjqDays) {
        this.leaveYpjqDays = leaveYpjqDays;
    }

    @Column(name = "LEAVE_DHJQ_DAYS", nullable = true, length = 32)
    public Integer getLeaveDhjqDays() {
        return this.leaveDhjqDays;
    }

    public void setLeaveDhjqDays(Integer leaveDhjqDays) {
        this.leaveDhjqDays = leaveDhjqDays;
    }

    @Column(name = "DHJQ_DATE", nullable = true, length = 32)
    public String getDhjqDate() {
        return this.dhjqDate;
    }

    public void setDhjqDate(String dhjqDate) {
        this.dhjqDate = dhjqDate;
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

    @Column(name = "HT_NUM", nullable = true, length = 32)
    public String getHtNum() {
        return this.htNum;
    }

    public void setHtNum(String htNum) {
        this.htNum = htNum;
    }

    @Column(name = "RK_STATE", nullable = true, length = 32)
    public String getRkState() {
        return this.rkState;
    }

    public void setRkState(String rkState) {
        this.rkState = rkState;
    }

    @Column(name = "SUM_TOTAL", nullable = true, scale = 2, length = 32)
    public Double getSumTotal() {
        return this.sumTotal;
    }

    public void setSumTotal(Double sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Column(name = "SUM_USE", nullable = true, length = 32)
    public String getSumUse() {
        return this.sumUse;
    }

    public void setSumUse(String sumUse) {
        this.sumUse = sumUse;
    }

    @Column(name = "PARTY_A", nullable = true, length = 32)
    public String getPartyA() {
        return this.partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    @Column(name = "PARTY_A_ID", nullable = true, length = 32)
    public String getPartyAId() {
        return this.partyAId;
    }

    public void setPartyAId(String partyAId) {
        this.partyAId = partyAId;
    }

    @Column(name = "PARTY_B", nullable = true, length = 32)
    public String getPartyB() {
        return this.partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    @Column(name = "PARTY_B_ID", nullable = true, length = 32)
    public String getPartyBId() {
        return this.partyBId;
    }

    public void setPartyBId(String partyBId) {
        this.partyBId = partyBId;
    }

    @Column(name = "FOB", nullable = true, length = 32)
    public String getFob() {
        return this.fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    @Column(name = "PLACE", nullable = true, length = 32)
    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "YCD", nullable = true, length = 32)
    public String getYcd() {
        return this.ycd;
    }

    public void setYcd(String ycd) {
        this.ycd = ycd;
    }

    @Column(name = "PAY_TYPE", nullable = true, length = 32)
    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Column(name = "SQDB", nullable = true, length = 32)
    public String getSqdb() {
        return this.sqdb;
    }

    public void setSqdb(String sqdb) {
        this.sqdb = sqdb;
    }

    @Column(name = "ADDRESS", nullable = true, length = 512)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "TELPHONE", nullable = true, length = 32)
    public String getTelphone() {
        return this.telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Column(name = "SIGN_DATE", nullable = true, length = 32)
    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    @Column(name = "order_num", nullable = true, length = 32)
    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "flag", nullable = true, length = 32)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Column(name = "zscghtbh", nullable = true, length = 32)
    public String getZscghtbh() {
        return zscghtbh;
    }

    public void setZscghtbh(String zscghtbh) {
        this.zscghtbh = zscghtbh;
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


    @Column(name = "STATE", nullable = true, length = 32)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="out_price", nullable=true, length=32)
    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    @Column(name="ysqdb", nullable=true, length=32)
    public String getYsqdb() {
        return ysqdb;
    }

    public void setYsqdb(String ysqdb) {
        this.ysqdb = ysqdb;
    }

    @Column(name="yaddress", nullable=true, length=32)
    public String getYaddress() {
        return yaddress;
    }

    public void setYaddress(String yaddress) {
        this.yaddress = yaddress;
    }

    @Column(name="ytelphone", nullable=true, length=32)
    public String getYtelphone() {
        return ytelphone;
    }

    public void setYtelphone(String ytelphone) {
        this.ytelphone = ytelphone;
    }

    @Column(name="ysign_date", nullable=true, length=32)
    public String getYsignDate() {
        return ysignDate;
    }

    public void setYsignDate(String ysignDate) {
        this.ysignDate = ysignDate;
    }

    @Column(name="bound_name", nullable=true, length=32)
    public String getBoundName() {
        return boundName;
    }

    public void setBoundName(String boundName) {
        this.boundName = boundName;
    }

    @Column(name="sum_money", nullable=true, length=32)
    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Column(name = "form_type", nullable = true, length = 32)
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
}
