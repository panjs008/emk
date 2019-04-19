package com.emk.check.sizecheck.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 尺码表
 * @author onlineGenerator
 * @date 2018-09-26 22:35:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_size", schema = "")
@SuppressWarnings("serial")
public class EmkSizeEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String formId;
	private String sizeA;
	private String sizeB;
	private String sizeC;
	private String sizeD;
	private String sizeE;
	private String sizeF;
	private String sizeG;
	private String sizeH;
	private String sizeI;
	private String sizeJ;
	private String sizeK;


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

	public void setId(String id) {
		this.id = id;
	}

	@Column(name ="form_id",nullable=false,length=36)
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	@Column(name ="size_a",nullable=false,length=36)
	public String getSizeA() {
		return sizeA;
	}

	public void setSizeA(String sizeA) {
		this.sizeA = sizeA;
	}

	@Column(name ="size_b",nullable=false,length=36)
	public String getSizeB() {
		return sizeB;
	}

	public void setSizeB(String sizeB) {
		this.sizeB = sizeB;
	}

	@Column(name ="size_c",nullable=false,length=36)
	public String getSizeC() {
		return sizeC;
	}

	public void setSizeC(String sizeC) {
		this.sizeC = sizeC;
	}

	@Column(name ="size_d",nullable=false,length=36)
	public String getSizeD() {
		return sizeD;
	}

	public void setSizeD(String sizeD) {
		this.sizeD = sizeD;
	}

	@Column(name ="size_e",nullable=false,length=36)
	public String getSizeE() {
		return sizeE;
	}

	public void setSizeE(String sizeE) {
		this.sizeE = sizeE;
	}

	@Column(name ="size_f",nullable=false,length=36)
	public String getSizeF() {
		return sizeF;
	}

	public void setSizeF(String sizeF) {
		this.sizeF = sizeF;
	}

	@Column(name ="size_g",nullable=false,length=36)
	public String getSizeG() {
		return sizeG;
	}

	public void setSizeG(String sizeG) {
		this.sizeG = sizeG;
	}

	@Column(name ="size_h",nullable=false,length=36)
	public String getSizeH() {
		return sizeH;
	}

	public void setSizeH(String sizeH) {
		this.sizeH = sizeH;
	}

	@Column(name ="size_i",nullable=false,length=36)
	public String getSizeI() {
		return sizeI;
	}

	public void setSizeI(String sizeI) {
		this.sizeI = sizeI;
	}

	@Column(name ="size_j",nullable=false,length=36)
	public String getSizeJ() {
		return sizeJ;
	}

	public void setSizeJ(String sizeJ) {
		this.sizeJ = sizeJ;
	}

	@Column(name ="size_k",nullable=false,length=36)
	public String getSizeK() {
		return sizeK;
	}

	public void setSizeK(String sizeK) {
		this.sizeK = sizeK;
	}


}
