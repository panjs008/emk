package com.emk.product.productinfo.service.impl;

import com.emk.product.productinfo.entity.EmkProductInfoEntity;
import com.emk.product.productinfo.service.EmkProductInfoServiceI;

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

@Service("emkProductInfoService")
@Transactional
public class EmkProductInfoServiceImpl
        extends CommonServiceImpl
        implements EmkProductInfoServiceI {
    public void delete(EmkProductInfoEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProductInfoEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProductInfoEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProductInfoEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProductInfoEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProductInfoEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProductInfoEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("hs_code", t.getHsCode());
        map.put("hs_name", t.getHsName());
        map.put("pro_num", t.getProNum());
        map.put("pro_name", t.getProName());
        map.put("brand", t.getBrand());
        map.put("unit", t.getUnit());
        map.put("price", t.getPrice());
        return map;
    }

    public String replaceVal(String sql, EmkProductInfoEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{hs_code}", String.valueOf(t.getHsCode()));
        sql = sql.replace("#{hs_name}", String.valueOf(t.getHsName()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{pro_name}", String.valueOf(t.getProName()));
        sql = sql.replace("#{brand}", String.valueOf(t.getBrand()));
        sql = sql.replace("#{unit}", String.valueOf(t.getUnit()));
        sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
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
                    javaInter.execute("emk_product_info", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
