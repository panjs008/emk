package com.emk.storage.storage.entity;

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
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 库存表
 * @date 2018-03-09 11:10:11
 */
@Entity
@Table(name = "emk_storage", schema = "")
@SuppressWarnings("serial")
public class EmkStorageEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建人登录名称
     */
    private String createBy;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 所属部门
     */
    private String sysOrgCode;
    /**
     * 产品编号
     */
    @Excel(name = "产品编号")
    private String proNum;
    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    private String proId;
    /**
     * 中文描述
     */
    @Excel(name = "中文描述")
    private String proZnName;
    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String brand;
    /**
     * 数量
     */
    @Excel(name = "数量")
    private String total;
    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;
    /**
     * 英文描述
     */
    @Excel(name = "英文描述")
    private String proEnName;
    /**
     * 商品类型
     */
    @Excel(name = "商品类型")
    private String proType;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
    /**
     * 商品类型
     */
    @Excel(name = "商品类型")
    private String proTypeName;
    /**
     * 仓库ID
     */
    @Excel(name = "仓库ID")
    private String storageSetId;
    /**
     * 仓库名称
     */
    @Excel(name = "仓库名称")
    private String storageName;
    /**
     * 库位ID
     */
    @Excel(name = "库位ID")
    private String positionId;
    /**
     * 库位名称
     */
    @Excel(name = "库位名称")
    private String positionName;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人名称
     */

    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public String getCreateName() {
        return this.createName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人名称
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人登录名称
     */

    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人登录名称
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */

    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  所属部门
     */

    @Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  所属部门
     */
    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  产品编号
     */

    @Column(name = "PRO_NUM", nullable = true, length = 32)
    public String getProNum() {
        return this.proNum;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  产品编号
     */
    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商品ID
     */

    @Column(name = "PRO_ID", nullable = true, length = 32)
    public String getProId() {
        return this.proId;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  商品ID
     */
    public void setProId(String proId) {
        this.proId = proId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  中文描述
     */

    @Column(name = "PRO_ZN_NAME", nullable = true, length = 32)
    public String getProZnName() {
        return this.proZnName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  中文描述
     */
    public void setProZnName(String proZnName) {
        this.proZnName = proZnName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  规格型号
     */

    @Column(name = "BRAND", nullable = true, length = 32)
    public String getBrand() {
        return this.brand;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  规格型号
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 方法: 取得java.math.BigDecimal
     *
     * @return: java.math.BigDecimal  数量
     */

    @Column(name = "TOTAL", nullable = true, scale = 4, length = 32)
    public String getTotal() {
        return this.total;
    }

    /**
     * 方法: 设置java.math.BigDecimal
     *
     * @param: java.math.BigDecimal  数量
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  单位
     */

    @Column(name = "UNIT", nullable = true, length = 32)
    public String getUnit() {
        return this.unit;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  英文描述
     */

    @Column(name = "PRO_EN_NAME", nullable = true, length = 32)
    public String getProEnName() {
        return this.proEnName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  英文描述
     */
    public void setProEnName(String proEnName) {
        this.proEnName = proEnName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商品类型
     */

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  商品类型
     */
    public void setProType(String proType) {
        this.proType = proType;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  备注
     */

    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商品类型
     */

    @Column(name = "PRO_TYPE_NAME", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  商品类型
     */
    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  仓库ID
     */

    @Column(name = "STORAGE_SET_ID", nullable = true, length = 32)
    public String getStorageSetId() {
        return this.storageSetId;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  仓库ID
     */
    public void setStorageSetId(String storageSetId) {
        this.storageSetId = storageSetId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  仓库名称
     */

    @Column(name = "STORAGE_NAME", nullable = true, length = 32)
    public String getStorageName() {
        return this.storageName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  仓库名称
     */
    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  库位ID
     */

    @Column(name = "POSITION_ID", nullable = true, length = 32)
    public String getPositionId() {
        return this.positionId;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  库位ID
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  库位名称
     */

    @Column(name = "POSITION_NAME", nullable = true, length = 32)
    public String getPositionName() {
        return this.positionName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  库位名称
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
