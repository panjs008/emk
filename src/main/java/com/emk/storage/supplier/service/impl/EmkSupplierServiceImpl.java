package com.emk.storage.supplier.service.impl;

import com.emk.storage.supplier.entity.EmkSupplierEntity;
import com.emk.storage.supplier.service.EmkSupplierServiceI;

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

@Service("emkSupplierService")
@Transactional
public class EmkSupplierServiceImpl
        extends CommonServiceImpl
        implements EmkSupplierServiceI {
    public void delete(EmkSupplierEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkSupplierEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkSupplierEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkSupplierEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkSupplierEntity t)
            throws Exception {
    }

    private void doDelBus(EmkSupplierEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkSupplierEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("supplier", t.getSupplier());
        map.put("supplier_code", t.getSupplierCode());
        map.put("supplier_type", t.getSupplierType());
        map.put("address", t.getAddress());
        map.put("product_type", t.getProductType());
        map.put("taxpayer_num", t.getTaxpayerNum());
        map.put("bank_name", t.getBankName());
        map.put("bank_account", t.getBankAccount());
        map.put("telphone", t.getTelphone());
        map.put("legaler", t.getLegaler());
        map.put("contacter", t.getContacter());
        map.put("cw_contacter", t.getCwContacter());
        map.put("licence_url", t.getLicenceUrl());
        return map;
    }

    public String replaceVal(String sql, EmkSupplierEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{supplier}", String.valueOf(t.getSupplier()));
        sql = sql.replace("#{supplier_code}", String.valueOf(t.getSupplierCode()));
        sql = sql.replace("#{supplier_type}", String.valueOf(t.getSupplierType()));
        sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
        sql = sql.replace("#{product_type}", String.valueOf(t.getProductType()));
        sql = sql.replace("#{taxpayer_num}", String.valueOf(t.getTaxpayerNum()));
        sql = sql.replace("#{bank_name}", String.valueOf(t.getBankName()));
        sql = sql.replace("#{bank_account}", String.valueOf(t.getBankAccount()));
        sql = sql.replace("#{telphone}", String.valueOf(t.getTelphone()));
        sql = sql.replace("#{legaler}", String.valueOf(t.getLegaler()));
        sql = sql.replace("#{contacter}", String.valueOf(t.getContacter()));
        sql = sql.replace("#{cw_contacter}", String.valueOf(t.getCwContacter()));
        sql = sql.replace("#{licence_url}", String.valueOf(t.getLicenceUrl()));
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
                    javaInter.execute("emk_supplier", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
