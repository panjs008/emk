package com.emk.check.qualitycheck.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 质量检查图片表
 * @author onlineGenerator
 * @date 2018-09-25 22:24:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "emk_quality_check_image", schema = "")
@SuppressWarnings("serial")
public class EmkQualityImageEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	private String qualityId;
	private String imageName;
	private String imageUrl;
	private String uploadId;
	private String uploadTime;
	private String sortDesc;


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

	@Column(name ="quality_id",nullable=false,length=36)
	public String getQualityId() {
		return qualityId;
	}

	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}

	@Column(name ="image_name",nullable=false,length=36)
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Column(name ="image_url",nullable=false,length=36)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name ="upload_id",nullable=false,length=36)
	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	@Column(name ="upload_time",nullable=false,length=36)
	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name ="sort_desc",nullable=false,length=36)
	public String getSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}
}
