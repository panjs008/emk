package com.emk.product.product.service.impl;

import com.emk.product.product.entity.EmkProductEntity;
import com.emk.product.product.service.EmkProductServiceI;

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

@Service("emkProductService")
@Transactional
public class EmkProductServiceImpl
        extends CommonServiceImpl
        implements EmkProductServiceI {
    public void delete(EmkProductEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProductEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProductEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProductEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProductEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProductEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProductEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("pro_num", t.getProNum());
        map.put("pro_type", t.getProType());
        map.put("pro_en_name", t.getProEnName());
        map.put("pro_zn_name", t.getProZnName());
        map.put("brand", t.getBrand());
        map.put("unit", t.getUnit());
        map.put("remark_en", t.getRemarkEn());
        map.put("remark_zn", t.getRemarkZn());
        map.put("pro_remark", t.getProRemark());
        return map;
    }

    public String replaceVal(String sql, EmkProductEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_en_name}", String.valueOf(t.getProEnName()));
        sql = sql.replace("#{pro_zn_name}", String.valueOf(t.getProZnName()));
        sql = sql.replace("#{brand}", String.valueOf(t.getBrand()));
        sql = sql.replace("#{unit}", String.valueOf(t.getUnit()));
        sql = sql.replace("#{remark_en}", String.valueOf(t.getRemarkEn()));
        sql = sql.replace("#{remark_zn}", String.valueOf(t.getRemarkZn()));
        sql = sql.replace("#{pro_remark}", String.valueOf(t.getProRemark()));
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
                    javaInter.execute("emk_product", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
