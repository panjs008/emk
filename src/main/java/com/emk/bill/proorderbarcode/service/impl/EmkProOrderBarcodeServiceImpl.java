package com.emk.bill.proorderbarcode.service.impl;

import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;
import com.emk.bill.proorderbarcode.service.EmkProOrderBarcodeServiceI;

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

@Service("emkProOrderBarcodeService")
@Transactional
public class EmkProOrderBarcodeServiceImpl
        extends CommonServiceImpl
        implements EmkProOrderBarcodeServiceI {
    public void delete(EmkProOrderBarcodeEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProOrderBarcodeEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProOrderBarcodeEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProOrderBarcodeEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProOrderBarcodeEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProOrderBarcodeEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProOrderBarcodeEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("order_id", t.getOrderId());
        map.put("color", t.getColor());
        map.put("size", t.getSize());
        map.put("code", t.getCode());
        map.put("total", t.getTotal());
        map.put("type", t.getType());
        return map;
    }

    public String replaceVal(String sql, EmkProOrderBarcodeEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{size}", String.valueOf(t.getSize()));
        sql = sql.replace("#{code}", String.valueOf(t.getCode()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{type}", String.valueOf(t.getType()));
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
                    javaInter.execute("emk_pro_order_barcode", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
