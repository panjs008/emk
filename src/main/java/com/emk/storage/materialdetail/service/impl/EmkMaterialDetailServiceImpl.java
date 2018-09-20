package com.emk.storage.materialdetail.service.impl;

import com.emk.storage.materialdetail.entity.EmkMaterialDetailEntity;
import com.emk.storage.materialdetail.service.EmkMaterialDetailServiceI;

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

@Service("emkMaterialDetailService")
@Transactional
public class EmkMaterialDetailServiceImpl
        extends CommonServiceImpl
        implements EmkMaterialDetailServiceI {
    public void delete(EmkMaterialDetailEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkMaterialDetailEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkMaterialDetailEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkMaterialDetailEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkMaterialDetailEntity t)
            throws Exception {
    }

    private void doDelBus(EmkMaterialDetailEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkMaterialDetailEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("pro_id", t.getProId());
        map.put("pro_num", t.getProNum());
        map.put("sign_total", t.getSignTotal());
        map.put("unit", t.getUnit());
        map.put("direction", t.getDirection());
        map.put("betch_num", t.getBetchNum());
        map.put("width", t.getWidth());
        map.put("color", t.getColor());
        map.put("weight", t.getWeight());
        map.put("chengf", t.getChengf());
        map.put("remark", t.getRemark());
        map.put("gys", t.getGys());
        map.put("gys_code", t.getGysCode());
        map.put("pro_zn_name", t.getProZnName());
        map.put("material_id", t.getMaterialId());
        return map;
    }

    public String replaceVal(String sql, EmkMaterialDetailEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{pro_id}", String.valueOf(t.getProId()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{sign_total}", String.valueOf(t.getSignTotal()));
        sql = sql.replace("#{unit}", String.valueOf(t.getUnit()));
        sql = sql.replace("#{direction}", String.valueOf(t.getDirection()));
        sql = sql.replace("#{betch_num}", String.valueOf(t.getBetchNum()));
        sql = sql.replace("#{width}", String.valueOf(t.getWidth()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{weight}", String.valueOf(t.getWeight()));
        sql = sql.replace("#{chengf}", String.valueOf(t.getChengf()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{gys}", String.valueOf(t.getGys()));
        sql = sql.replace("#{gys_code}", String.valueOf(t.getGysCode()));
        sql = sql.replace("#{pro_zn_name}", String.valueOf(t.getProZnName()));
        sql = sql.replace("#{material_id}", String.valueOf(t.getMaterialId()));
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
                    javaInter.execute("emk_material_detail", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
