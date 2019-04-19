package com.emk.approval.approvaldetail.entity;

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
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 审批业务处理表
 * @author onlineGenerator
 * @date 2019-01-27 21:42:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_approval_detail", schema = "")
@SuppressWarnings("serial")
public class EmkApprovalDetailEntity implements java.io.Serializable {
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
	/**所属审批业务ID*/
	@Excel(name="所属审批业务ID",width=15)
	private String approvalId;
	/**表单ID*/
	@Excel(name="表单ID",width=15)
	private String formId;
	/**审批人ID*/
	@Excel(name="审批人ID",width=15)
	private String approveUserId;
	/**审核状态*/
	@Excel(name="审核状态",width=15)
	private Integer approveStatus;
	/**审批时间*/
	@Excel(name="审批时间",width=15)
	private String approveDate;
	/**审批意见*/
	@Excel(name="审批意见",width=15)
	private String approveAdvice;
	/**审批意见2*/
	@Excel(name="审批意见2",width=15)
	private String approveAdvice2;
	/**审批意见3*/
	@Excel(name="审批意见3",width=15)
	private String approveAdvice3;
	/**审批意见4*/
	@Excel(name="审批意见4",width=15)
	private String approveAdvice4;
	/**审批意见5*/
	@Excel(name="审批意见5",width=15)
	private String approveAdvice5;
	private String approveAdvice6;
	private String approveAdvice7;
	private String approveAdvice8;
	private String approveAdvice9;

	@Excel(name="流程环节名称",width=15)
	private String bpmName;
	private String bpmNode;
	private String cusType;


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
	 *@return: java.lang.String  所属审批业务ID
	 */

	@Column(name ="APPROVAL_ID",nullable=true,length=32)
	public String getApprovalId(){
		return this.approvalId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属审批业务ID
	 */
	public void setApprovalId(String approvalId){
		this.approvalId = approvalId;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批人ID
	 */

	@Column(name ="APPROVE_USER_ID",nullable=true,length=32)
	public String getApproveUserId(){
		return this.approveUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批人ID
	 */
	public void setApproveUserId(String approveUserId){
		this.approveUserId = approveUserId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  审核状态
	 */

	@Column(name ="APPROVE_STATUS",nullable=true,length=2)
	public Integer getApproveStatus(){
		return this.approveStatus;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  审核状态
	 */
	public void setApproveStatus(Integer approveStatus){
		this.approveStatus = approveStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批时间
	 */

	@Column(name ="APPROVE_DATE",nullable=true,length=32)
	public String getApproveDate(){
		return this.approveDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批时间
	 */
	public void setApproveDate(String approveDate){
		this.approveDate = approveDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见
	 */

	@Column(name ="APPROVE_ADVICE",nullable=true,length=512)
	public String getApproveAdvice(){
		return this.approveAdvice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见
	 */
	public void setApproveAdvice(String approveAdvice){
		this.approveAdvice = approveAdvice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见2
	 */

	@Column(name ="APPROVE_ADVICE2",nullable=true,length=512)
	public String getApproveAdvice2(){
		return this.approveAdvice2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见2
	 */
	public void setApproveAdvice2(String approveAdvice2){
		this.approveAdvice2 = approveAdvice2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见3
	 */

	@Column(name ="APPROVE_ADVICE3",nullable=true,length=512)
	public String getApproveAdvice3(){
		return this.approveAdvice3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见3
	 */
	public void setApproveAdvice3(String approveAdvice3){
		this.approveAdvice3 = approveAdvice3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见4
	 */

	@Column(name ="APPROVE_ADVICE4",nullable=true,length=512)
	public String getApproveAdvice4(){
		return this.approveAdvice4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见4
	 */
	public void setApproveAdvice4(String approveAdvice4){
		this.approveAdvice4 = approveAdvice4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见5
	 */

	@Column(name ="APPROVE_ADVICE5",nullable=true,length=512)
	public String getApproveAdvice5(){
		return this.approveAdvice5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见5
	 */
	public void setApproveAdvice5(String approveAdvice5){
		this.approveAdvice5 = approveAdvice5;
	}

	@Column(name ="APPROVE_ADVICE6",nullable=true,length=512)
	public String getApproveAdvice6() {
		return approveAdvice6;
	}

	public void setApproveAdvice6(String approveAdvice6) {
		this.approveAdvice6 = approveAdvice6;
	}

	@Column(name ="APPROVE_ADVICE7",nullable=true,length=512)
	public String getApproveAdvice7() {
		return approveAdvice7;
	}

	public void setApproveAdvice7(String approveAdvice7) {
		this.approveAdvice7 = approveAdvice7;
	}

	@Column(name ="APPROVE_ADVICE8",nullable=true,length=512)
	public String getApproveAdvice8() {
		return approveAdvice8;
	}

	public void setApproveAdvice8(String approveAdvice8) {
		this.approveAdvice8 = approveAdvice8;
	}

	@Column(name ="APPROVE_ADVICE9",nullable=true,length=512)
	public String getApproveAdvice9() {
		return approveAdvice9;
	}

	public void setApproveAdvice9(String approveAdvice9) {
		this.approveAdvice9 = approveAdvice9;
	}

	@Column(name ="bpm_name",nullable=true,length=512)
	public String getBpmName() {
		return bpmName;
	}

	public void setBpmName(String bpmName) {
		this.bpmName = bpmName;
	}

	@Column(name ="bpm_node",nullable=true,length=512)
	public String getBpmNode() {
		return bpmNode;
	}

	public void setBpmNode(String bpmNode) {
		this.bpmNode = bpmNode;
	}

	@Column(name ="cus_type",nullable=true,length=512)
	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
}
