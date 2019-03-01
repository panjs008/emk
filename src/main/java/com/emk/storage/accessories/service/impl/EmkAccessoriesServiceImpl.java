package com.emk.storage.accessories.service.impl;

import com.emk.storage.accessories.entity.EmkAccessoriesEntity;
import com.emk.storage.accessories.service.EmkAccessoriesServiceI;

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

@Service("emkAccessoriesService")
@Transactional
public class EmkAccessoriesServiceImpl
        extends CommonServiceImpl
        implements EmkAccessoriesServiceI {
    public void delete(EmkAccessoriesEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkAccessoriesEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkAccessoriesEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkAccessoriesEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkAccessoriesEntity t)
            throws Exception {
    }

    private void doDelBus(EmkAccessoriesEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkAccessoriesEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("material_no", t.getMaterialNo());
        map.put("kd_date", t.getKdDate());
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("gyzl", t.getGyzl());
        map.put("pro_type", t.getProType());
        map.put("pro_type_name", t.getProTypeName());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_no_desc", t.getSampleNoDesc());

        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("developer", t.getDeveloper());
        map.put("developer_name", t.getDeveloperName());

        map.put("ypjq_date", t.getYpjqDate());
        map.put("leave_ypjq_days", t.getLeaveYpjqDays());
        map.put("leave_dhjq_days", t.getLeaveDhjqDays());
        map.put("is_order", t.getIsOrder());
        map.put("dhjq_date", t.getDhjqDate());
        map.put("is_have", t.getIsHave());
        map.put("version", t.getVersion());
        map.put("sample_reason", t.getSampleReason());
        map.put("required_jq_date", t.getRequiredJqDate());
        map.put("leave_finish_days", t.getLeaveFinishDays());
        map.put("state", t.getState());

        map.put("custom_sample_url", t.getCustomSampleUrl());
        map.put("custom_sample", t.getCustomSample());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        return map;
    }

    public String replaceVal(String sql, EmkAccessoriesEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{material_no}", String.valueOf(t.getMaterialNo()));
        sql = sql.replace("#{kd_date}", String.valueOf(t.getKdDate()));
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{gyzl}", String.valueOf(t.getGyzl()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));

        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
        sql = sql.replace("#{developer}", String.valueOf(t.getDeveloper()));
        sql = sql.replace("#{developer_name}", String.valueOf(t.getDeveloperName()));

        sql = sql.replace("#{ypjq_date}", String.valueOf(t.getYpjqDate()));
        sql = sql.replace("#{leave_ypjq_days}", String.valueOf(t.getLeaveYpjqDays()));
        sql = sql.replace("#{leave_dhjq_days}", String.valueOf(t.getLeaveDhjqDays()));
        sql = sql.replace("#{is_order}", String.valueOf(t.getIsOrder()));
        sql = sql.replace("#{dhjq_date}", String.valueOf(t.getDhjqDate()));
        sql = sql.replace("#{is_have}", String.valueOf(t.getIsHave()));
        sql = sql.replace("#{version}", String.valueOf(t.getVersion()));
        sql = sql.replace("#{sample_reason}", String.valueOf(t.getSampleReason()));
        sql = sql.replace("#{required_jq_date}", String.valueOf(t.getRequiredJqDate()));
        sql = sql.replace("#{leave_finish_days}", String.valueOf(t.getLeaveFinishDays()));
        sql = sql.replace("#{state}", String.valueOf(t.getState()));
        sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
        sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
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
                    javaInter.execute("emk_accessories", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
