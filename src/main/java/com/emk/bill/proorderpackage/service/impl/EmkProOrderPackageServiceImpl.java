package com.emk.bill.proorderpackage.service.impl;

import com.emk.bill.proorderpackage.entity.EmkProOrderPackageEntity;
import com.emk.bill.proorderpackage.service.EmkProOrderPackageServiceI;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("emkProOrderPackageService")
@Transactional
public class EmkProOrderPackageServiceImpl
        extends CommonServiceImpl
        implements EmkProOrderPackageServiceI {
    public void delete(EmkProOrderPackageEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProOrderPackageEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProOrderPackageEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProOrderPackageEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProOrderPackageEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProOrderPackageEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProOrderPackageEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("order_id", t.getOrderId());
        map.put("color", t.getColor());
        map.put("size", t.getSize());
        map.put("inbox_total", t.getInboxTotal());
        map.put("box_total", t.getBoxTotal());
        map.put("sum_total", t.getSumTotal());
        map.put("package_type", t.getPackageType());
        return map;
    }

    public String replaceVal(String sql, EmkProOrderPackageEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{size}", String.valueOf(t.getSize()));
        sql = sql.replace("#{inbox_total}", String.valueOf(t.getInboxTotal()));
        sql = sql.replace("#{box_total}", String.valueOf(t.getBoxTotal()));
        sql = sql.replace("#{sum_total}", String.valueOf(t.getSumTotal()));
        sql = sql.replace("#{package_type}", String.valueOf(t.getPackageType()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    private void executeJavaExtend(String cgJavaType, String cgJavaValue, Map<String, Object> data)
            throws Exception {
        if (StringUtil.isNotEmpty(cgJavaValue)) {
            Object obj = null;
            try {
                if ("class".equals(cgJavaType)) {
                    obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
                } else if ("spring".equals(cgJavaType)) {
                    obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                }
                if ((obj instanceof CgformEnhanceJavaInter)) {
                    CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
                    javaInter.execute("emk_pro_order_package", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
