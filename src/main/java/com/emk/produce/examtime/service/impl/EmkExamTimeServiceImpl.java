package com.emk.produce.examtime.service.impl;

import com.emk.produce.examtime.entity.EmkExamTimeEntity;
import com.emk.produce.examtime.service.EmkExamTimeServiceI;

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

@Service("emkExamTimeService")
@Transactional
public class EmkExamTimeServiceImpl
        extends CommonServiceImpl
        implements EmkExamTimeServiceI {
    public void delete(EmkExamTimeEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkExamTimeEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkExamTimeEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkExamTimeEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkExamTimeEntity t)
            throws Exception {
    }

    private void doDelBus(EmkExamTimeEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkExamTimeEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("developer", t.getDeveloper());
        map.put("developer_name", t.getDeveloperName());
        map.put("gyzl", t.getGyzl());
        map.put("pro_type", t.getProType());
        map.put("pro_type_name", t.getProTypeName());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_no_desc", t.getSampleNoDesc());
        map.put("custom_sample_url", t.getCustomSampleUrl());
        map.put("custom_sample", t.getCustomSample());
        map.put("total", t.getTotal());
        map.put("color", t.getColor());
        map.put("size", t.getSize());
        map.put("zqyh_date", t.getZqyhDate());
        map.put("wqyh_date", t.getWqyhDate());
        map.put("level_days", t.getLevelDays());
        map.put("zqyh_state", t.getZqyhState());
        map.put("leveal_zqyh", t.getLevealZqyh());
        map.put("wqyh_state", t.getWqyhState());
        map.put("leveal_wqyh", t.getLevealWqyh());
        map.put("gys_code", t.getGysCode());
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        return map;
    }

    public String replaceVal(String sql, EmkExamTimeEntity t) {
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
        sql = sql.replace("#{developer}", String.valueOf(t.getDeveloper()));
        sql = sql.replace("#{developer_name}", String.valueOf(t.getDeveloperName()));
        sql = sql.replace("#{gyzl}", String.valueOf(t.getGyzl()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
        sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
        sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{size}", String.valueOf(t.getSize()));
        sql = sql.replace("#{zqyh_date}", String.valueOf(t.getZqyhDate()));
        sql = sql.replace("#{wqyh_date}", String.valueOf(t.getWqyhDate()));
        sql = sql.replace("#{level_days}", String.valueOf(t.getLevelDays()));
        sql = sql.replace("#{zqyh_state}", String.valueOf(t.getZqyhState()));
        sql = sql.replace("#{leveal_zqyh}", String.valueOf(t.getLevealZqyh()));
        sql = sql.replace("#{wqyh_state}", String.valueOf(t.getWqyhState()));
        sql = sql.replace("#{leveal_wqyh}", String.valueOf(t.getLevealWqyh()));
        sql = sql.replace("#{gys_code}", String.valueOf(t.getGysCode()));
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
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
                    javaInter.execute("emk_exam_time", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
