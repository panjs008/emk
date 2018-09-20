package com.emk.storage.sample.service.impl;

import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sample.service.EmkSampleServiceI;

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

@Service("emkSampleService")
@Transactional
public class EmkSampleServiceImpl
        extends CommonServiceImpl
        implements EmkSampleServiceI {
    public void delete(EmkSampleEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkSampleEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkSampleEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkSampleEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkSampleEntity t)
            throws Exception {
    }

    private void doDelBus(EmkSampleEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkSampleEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("sample_no", t.getSampleNo());
        map.put("project_no", t.getProjectNo());
        map.put("factory_name", t.getFactoryName());
        map.put("factory_code", t.getFactoryCode());
        map.put("custom_sample_url", t.getCustomSampleUrl());
        map.put("custom_sample", t.getCustomSample());
        map.put("sample_size_url", t.getSampleSizeUrl());
        map.put("sample_size", t.getSampleSize());
        map.put("remark", t.getRemark());
        map.put("packing", t.getPacking());
        map.put("pro_name", t.getProName());
        map.put("pro_type", t.getProType());
        map.put("pro_type_name", t.getProTypeName());
        map.put("recevice_date", t.getReceviceDate());
        map.put("finish_date", t.getFinishDate());
        map.put("produce_date", t.getProduceDate());
        map.put("requiretext", t.getRequiretext());
        return map;
    }

    public String replaceVal(String sql, EmkSampleEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{project_no}", String.valueOf(t.getProjectNo()));
        sql = sql.replace("#{factory_name}", String.valueOf(t.getFactoryName()));
        sql = sql.replace("#{factory_code}", String.valueOf(t.getFactoryCode()));
        sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
        sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
        sql = sql.replace("#{sample_size_url}", String.valueOf(t.getSampleSizeUrl()));
        sql = sql.replace("#{sample_size}", String.valueOf(t.getSampleSize()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{packing}", String.valueOf(t.getPacking()));
        sql = sql.replace("#{pro_name}", String.valueOf(t.getProName()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{recevice_date}", String.valueOf(t.getReceviceDate()));
        sql = sql.replace("#{finish_date}", String.valueOf(t.getFinishDate()));
        sql = sql.replace("#{produce_date}", String.valueOf(t.getProduceDate()));
        sql = sql.replace("#{requiretext}", String.valueOf(t.getRequiretext()));
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
                    javaInter.execute("emk_sample", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
