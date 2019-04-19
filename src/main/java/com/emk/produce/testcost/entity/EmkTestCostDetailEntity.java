package com.emk.produce.testcost.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emk_test_cost_detail", schema = "")
public class EmkTestCostDetailEntity implements Serializable {
    private String id;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name="客户代码",width=15)
    private String cusNum;
    @Excel(name="供应商代码",width=15)
    private String gysCode;
    @Excel(name="生产合同号",width=15)
    private String produceHtNum;
    /**工艺种类*/
    @Excel(name="工艺种类",width=15)
    private String gyzl;
    /**款式大类*/
    @Excel(name="款式大类",width=15)
    private String proType;
    /**款式大类*/
    @Excel(name="款式大类",width=15)
    private String proTypeName;
    /**描述*/
    @Excel(name="描述",width=15)
    private String sampleNoDesc;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "数量")
    private String total;

    @Excel(name = "生产合同号")
    private String produceNum;
    @Excel(name = "测试种类")
    private String testType;
    @Excel(name = "测试内容")
    private String testContent;
    private String testContentUrl;
    @Excel(name = "测试报告号")
    private String testNo;
    @Excel(name = "测试结果")
    private String testResult;
    @Excel(name = "测试费用金额")
    private String testMoney;
    private String testCostId;

    @Excel(name = "报告有效期")
    private String limitDate;
    @Excel(name = "测试费付款状态")
    private String testState;
    @Excel(name = "测试费发票号")
    private String testFp;
    @Excel(name = "测试申请表编号")
    private String cssqdh;

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

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "PRODUCE_NUM", nullable = true, length = 32)
    public String getProduceNum() {
        return this.produceNum;
    }

    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }

    @Column(name = "TEST_TYPE", nullable = true, length = 32)
    public String getTestType() {
        return this.testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    @Column(name = "TEST_CONTENT", nullable = true, length = 32)
    public String getTestContent() {
        return this.testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    @Column(name = "TEST_CONTENT_URL", nullable = true, length = 256)
    public String getTestContentUrl() {
        return this.testContentUrl;
    }

    public void setTestContentUrl(String testContentUrl) {
        this.testContentUrl = testContentUrl;
    }

    @Column(name = "TEST_RESULT", nullable = true, length = 32)
    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name ="test_no",nullable=true,length=32)
    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    @Column(name ="test_money",nullable=true,length=32)
    public String getTestMoney() {
        return testMoney;
    }

    public void setTestMoney(String testMoney) {
        this.testMoney = testMoney;
    }

    @Column(name ="test_cost_id",nullable=true,length=32)
    public String getTestCostId() {
        return testCostId;
    }

    public void setTestCostId(String testCostId) {
        this.testCostId = testCostId;
    }

    @Column(name ="CUS_NUM",nullable=true,length=32)
    public String getCusNum(){
        return this.cusNum;
    }

    /**
     *方法: 设置String
     *@param: String  客户代码
     */
    public void setCusNum(String cusNum){
        this.cusNum = cusNum;
    }

    @Column(name ="PRODUCE_HT_NUM",nullable=true,length=32)
    public String getProduceHtNum(){
        return this.produceHtNum;
    }

    /**
     *方法: 设置String
     *@param: String  生产合同号
     */
    public void setProduceHtNum(String produceHtNum){
        this.produceHtNum = produceHtNum;
    }

    /**
     *方法: 取得String
     *@return: String  工艺种类
     */

    @Column(name ="GYZL",nullable=true,length=32)
    public String getGyzl(){
        return this.gyzl;
    }

    /**
     *方法: 设置String
     *@param: String  工艺种类
     */
    public void setGyzl(String gyzl){
        this.gyzl = gyzl;
    }
    /**
     *方法: 取得String
     *@return: String  款式大类
     */

    @Column(name ="PRO_TYPE",nullable=true,length=32)
    public String getProType(){
        return this.proType;
    }

    /**
     *方法: 设置String
     *@param: String  款式大类
     */
    public void setProType(String proType){
        this.proType = proType;
    }
    /**
     *方法: 取得String
     *@return: String  款式大类
     */

    @Column(name ="PRO_TYPE_NAME",nullable=true,length=32)
    public String getProTypeName(){
        return this.proTypeName;
    }

    /**
     *方法: 设置String
     *@param: String  款式大类
     */
    public void setProTypeName(String proTypeName){
        this.proTypeName = proTypeName;
    }
    /**
     *方法: 取得String
     *@return: String  描述
     */

    @Column(name ="SAMPLE_NO_DESC",nullable=true,length=256)
    public String getSampleNoDesc(){
        return this.sampleNoDesc;
    }

    /**
     *方法: 设置String
     *@param: String  描述
     */
    public void setSampleNoDesc(String sampleNoDesc){
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "LIMIT_DATE", nullable = true, length = 32)
    public String getLimitDate() {
        return this.limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    @Column(name = "TEST_STATE", nullable = true, length = 32)
    public String getTestState() {
        return this.testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
    }

    @Column(name = "cssqdh", nullable = true, length = 32)
    public String getCssqdh() {
        return cssqdh;
    }

    public void setCssqdh(String cssqdh) {
        this.cssqdh = cssqdh;
    }

    @Column(name = "gys_code", nullable = true, length = 32)
    public String getGysCode() {
        return gysCode;
    }

    public void setGysCode(String gysCode) {
        this.gysCode = gysCode;
    }

    @Column(name = "color", nullable = true, length = 32)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "total", nullable = true, length = 32)
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Column(name = "test_fp", nullable = true, length = 32)
    public String getTestFp() {
        return testFp;
    }

    public void setTestFp(String testFp) {
        this.testFp = testFp;
    }
}

