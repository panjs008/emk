package com.emk.storage.sampleran.service.impl;

import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.sampleran.service.EmkSampleRanServiceI;

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

@Service("emkSampleRanService")
@Transactional
public class EmkSampleRanServiceImpl
        extends CommonServiceImpl
        implements EmkSampleRanServiceI {
    public void delete(EmkSampleRanEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkSampleRanEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkSampleRanEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkSampleRanEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkSampleRanEntity t)
            throws Exception {
    }

    private void doDelBus(EmkSampleRanEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkSampleRanEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("bu_type", t.getBuType());
        map.put("price", t.getPrice());
        map.put("unit", t.getUnit());
        map.put("one_weight", t.getOneWeight());
        map.put("chengben", t.getChengben());
        map.put("sample_id", t.getSampleId());
        return map;
    }

    public String replaceVal(String sql, EmkSampleRanEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{bu_type}", String.valueOf(t.getBuType()));
        sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
        sql = sql.replace("#{unit}", String.valueOf(t.getUnit()));
        sql = sql.replace("#{one_weight}", String.valueOf(t.getOneWeight()));
        sql = sql.replace("#{chengben}", String.valueOf(t.getChengben()));
        sql = sql.replace("#{sample_id}", String.valueOf(t.getSampleId()));
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
                    javaInter.execute("emk_sample_ran", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
