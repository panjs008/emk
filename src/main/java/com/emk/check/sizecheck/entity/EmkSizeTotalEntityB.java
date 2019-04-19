package com.emk.check.sizecheck.entity;

import com.emk.produce.invoices.entity.EmkInvoicesDetailEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Title: Entity
 * @Description: 尺码数量表
 * @author onlineGenerator
 * @date 2018-09-26 22:35:18
 * @version V1.0
 *
 */
@Entity
@Table(name = "emk_size_total", schema = "")
@SuppressWarnings("serial")
public class EmkSizeTotalEntityB implements java.io.Serializable {
	/**主键*/
	private String id;
	private String totalA;
	private String totalB;
	private String totalC;
	private String totalD;
	private String totalE;
	private String totalF;
	private String totalG;
	private String totalH;
	private String totalI;
	private String totalJ;
	private String totalK;

	private EmkInvoicesDetailEntity emkInvoicesDetailEntity;

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

	@Column(name ="total_a",nullable=false,length=36)
	public String getTotalA() {
		return totalA;
	}

	public void setTotalA(String totalA) {
		this.totalA = totalA;
	}

	@Column(name ="total_b",nullable=false,length=36)
	public String getTotalB() {
		return totalB;
	}

	public void setTotalB(String totalB) {
		this.totalB = totalB;
	}

	@Column(name ="total_c",nullable=false,length=36)
	public String getTotalC() {
		return totalC;
	}

	public void setTotalC(String totalC) {
		this.totalC = totalC;
	}

	@Column(name ="total_d",nullable=false,length=36)
	public String getTotalD() {
		return totalD;
	}

	public void setTotalD(String totalD) {
		this.totalD = totalD;
	}

	@Column(name ="total_e",nullable=false,length=36)
	public String getTotalE() {
		return totalE;
	}

	public void setTotalE(String totalE) {
		this.totalE = totalE;
	}

	@Column(name ="total_f",nullable=false,length=36)
	public String getTotalF() {
		return totalF;
	}

	public void setTotalF(String totalF) {
		this.totalF = totalF;
	}

	@Column(name ="total_g",nullable=false,length=36)
	public String getTotalG() {
		return totalG;
	}

	public void setTotalG(String totalG) {
		this.totalG = totalG;
	}

	@Column(name ="total_h",nullable=false,length=36)
	public String getTotalH() {
		return totalH;
	}

	public void setTotalH(String totalH) {
		this.totalH = totalH;
	}

	@Column(name ="total_i",nullable=false,length=36)
	public String getTotalI() {
		return totalI;
	}

	public void setTotalI(String totalI) {
		this.totalI = totalI;
	}

	@Column(name ="total_j",nullable=false,length=36)
	public String getTotalJ() {
		return totalJ;
	}

	public void setTotalJ(String totalJ) {
		this.totalJ = totalJ;
	}

	@Column(name ="total_k",nullable=false,length=36)
	public String getTotalK() {
		return totalK;
	}

	public void setTotalK(String totalK) {
		this.totalK = totalK;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="p_id")
	public EmkInvoicesDetailEntity getEmkInvoicesDetailEntity() {
		return emkInvoicesDetailEntity;
	}

	public void setEmkInvoicesDetailEntity(EmkInvoicesDetailEntity emkInvoicesDetailEntity) {
		this.emkInvoicesDetailEntity = emkInvoicesDetailEntity;
	}
}
