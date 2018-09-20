package com.emk.product.hs.service.impl;

import com.emk.product.hs.entity.EmkProductHsEntity;
import com.emk.product.hs.service.EmkProductHsServiceI;

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

@Service("emkProductHsService")
@Transactional
public class EmkProductHsServiceImpl
        extends CommonServiceImpl
        implements EmkProductHsServiceI {
    public void delete(EmkProductHsEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProductHsEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProductHsEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProductHsEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProductHsEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProductHsEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProductHsEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("hs_code", t.getHsCode());
        map.put("hs_name", t.getHsName());
        map.put("bgys", t.getBgys());
        map.put("zz_val", t.getZzVal());
        map.put("ts_val", t.getTsVal());
        map.put("sale_price", t.getSalePrice());
        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, EmkProductHsEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{hs_code}", String.valueOf(t.getHsCode()));
        sql = sql.replace("#{hs_name}", String.valueOf(t.getHsName()));
        sql = sql.replace("#{bgys}", String.valueOf(t.getBgys()));
        sql = sql.replace("#{zz_val}", String.valueOf(t.getZzVal()));
        sql = sql.replace("#{ts_val}", String.valueOf(t.getTsVal()));
        sql = sql.replace("#{sale_price}", String.valueOf(t.getSalePrice()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
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
                    javaInter.execute("emk_product_hs", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
