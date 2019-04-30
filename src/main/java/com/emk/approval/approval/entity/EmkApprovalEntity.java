package com.emk.approval.approval.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 审批业务表
 * @author onlineGenerator
 * @date 2019-01-26 19:12:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_approval", schema = "")
@SuppressWarnings("serial")
public class EmkApprovalEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**所属部门*/
	private String sysOrgCode;
	/**申请人ID*/
	@Excel(name="申请人ID",width=15)
	private String commitId;
	/**提交时间*/
	@Excel(name="提交时间",width=15)
	private String commitTime;
	/**工单类型*/
	@Excel(name="工单类型",width=15)
	private Integer type;								// 0 意向询盘单，1 验厂申请单， 2 报价 3 打样 4 原料面料样品材料采购 5 入库申请单 6 出库申请单
														// 7 缝制辅料样品材料采购 8 订单表 9 原料面料采购需求单 10 缝制辅料采购需求单 11 包装辅料采购需求单
														// 12 原料面料开发费付款申请单 13 缝制辅料开发费付款申请单 14 包装辅料开发费付款申请单
	/**当前节点名称*/
	@Excel(name="当前节点名称",width=15)
	private String processName;
	/**当前节点代码*/
	@Excel(name="当前节点代码",width=15)
	private String processNode;
	/**状态*/
	@Excel(name="状态",width=15)
	private Integer status;						 // 0 创建 1 提交 2 已完成 3 业务经理通过 4 回退业务经理 5 技术经理通过 6 回退技术经理 7 染色部经理通过 8 回退染色部经理 9 缝制部经理通过 10 回退缝制部经理
													 // 11 烫标整烫组长通过 12 回退烫标整烫组长 13 包装组长通过 14 回退包装组长 15 采购部经理通过 16 回退采购部经理  17 生产计划部通过 18 回退生产计划部
													 // 19 生产总负责人通过 20 报价 21 回退业务员 22 技术员通过 23 回退技术员 24 采购员通过 25 财务员通过 26 财务经理通过 27 回退财务员 29 回退财务经理 30 价格确认 31 打样 32 打样报价
													 // 33 生产跟单员通过 34 生产跟单员更新进度 35 业务员通过 36 回退采购员 37 采购员执行 38 采购员进度  39 入库申请单【采购员】 40 采购员质检通过 41 采购部经理通过 42
														// 43 出库申请单【技术员】 44 【采购员】预采购合同 45 【业务员】购销合同 46 【采购员】正式购销合同 47【财务员】付款核准单 48 总经理通过 49 回退正式购销合同
														// 50 验厂申请表 51 验厂经理通过 52 执行验厂 53 验厂报告

	/**审核人*/
	@Excel(name="审核人",width=15)
	private String bpmSher;
	/**审核状态*/
	@Excel(name="审核状态",width=15)
	private String bpmStatus;
	/**审核人ID*/
	@Excel(name="审核人ID",width=15)
	private String bpmSherId;
	/**下一环节处理人*/
	@Excel(name="下一环节处理人",width=15)
	private String nextBpmSher;
	/**下一环节处理人ID*/
	@Excel(name="下一环节处理人ID",width=15)
	private String nextBpmSherId;
	/**审核时间*/
	@Excel(name="审核时间",width=15)
	private String bpmDate;
	/**单号*/
	@Excel(name="单号",width=15)
	private String workNum;
	/**表单ID*/
	@Excel(name="表单ID",width=15)
	private String formId;

	private String state1;
	private String state2;
	private String state3;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请人ID
	 */

	@Column(name ="COMMIT_ID",nullable=true,length=32)
	public String getCommitId(){
		return this.commitId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人ID
	 */
	public void setCommitId(String commitId){
		this.commitId = commitId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交时间
	 */

	@Column(name ="COMMIT_TIME",nullable=true,length=32)
	public String getCommitTime(){
		return this.commitTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交时间
	 */
	public void setCommitTime(String commitTime){
		this.commitTime = commitTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  工单类型
	 */

	@Column(name ="TYPE",nullable=true,length=2)
	public Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  工单类型
	 */
	public void setType(Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当前节点名称
	 */
	@Column(name ="PROCESS_NAME",nullable=true,length=32)
	public String getProcessName(){
		return this.processName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前节点名称
	 */
	public void setProcessName(String processName){
		this.processName = processName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当前节点代码
	 */
	@Formula("(select CONCAT(p.NAME_,'-',p.TASK_DEF_KEY_) from act_ru_task p where p.ASSIGNEE_ = form_id limit 0,1)")
	@Column(name ="PROCESS_NODE",nullable=true,length=32)
	public String getProcessNode(){
		return this.processNode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前节点代码
	 */
	public void setProcessNode(String processNode){
		this.processNode = processNode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */

	@Column(name ="STATUS",nullable=true,length=2)
	public Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */

	@Column(name ="BPM_SHER",nullable=true,length=32)
	public String getBpmSher(){
		return this.bpmSher;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setBpmSher(String bpmSher){
		this.bpmSher = bpmSher;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核状态
	 */
	public void setBpmStatus(String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人ID
	 */

	@Column(name ="BPM_SHER_ID",nullable=true,length=32)
	public String getBpmSherId(){
		return this.bpmSherId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人ID
	 */
	public void setBpmSherId(String bpmSherId){
		this.bpmSherId = bpmSherId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核时间
	 */

	@Column(name ="BPM_DATE",nullable=true,length=32)
	public String getBpmDate(){
		return this.bpmDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核时间
	 */
	public void setBpmDate(String bpmDate){
		this.bpmDate = bpmDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单号
	 */

	@Column(name ="WORK_NUM",nullable=true,length=32)
	public String getWorkNum(){
		return this.workNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单号
	 */
	public void setWorkNum(String workNum){
		this.workNum = workNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  表单ID
	 */

	@Column(name ="FORM_ID",nullable=true,length=32)
	public String getFormId(){
		return this.formId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  表单ID
	 */
	public void setFormId(String formId){
		this.formId = formId;
	}

	@Column(name = "state1", nullable = true, length = 32)
	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	@Column(name = "state2", nullable = true, length = 32)
	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	@Column(name = "state3", nullable = true, length = 32)
	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}

	@Column(name = "next_bpm_sher", nullable = true, length = 32)
	public String getNextBpmSher() {
		return nextBpmSher;
	}

	public void setNextBpmSher(String nextBpmSher) {
		this.nextBpmSher = nextBpmSher;
	}

	@Column(name = "next_bpm_sher_id", nullable = true, length = 32)
	public String getNextBpmSherId() {
		return nextBpmSherId;
	}

	public void setNextBpmSherId(String nextBpmSherId) {
		this.nextBpmSherId = nextBpmSherId;
	}
}
