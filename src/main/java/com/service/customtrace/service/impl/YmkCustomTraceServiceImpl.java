package com.service.customtrace.service.impl;

import com.service.customtrace.entity.YmkCustomTraceEntity;
import com.service.customtrace.service.YmkCustomTraceServiceI;

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

@Service("ymkCustomTraceService")
@Transactional
public class YmkCustomTraceServiceImpl
        extends CommonServiceImpl
        implements YmkCustomTraceServiceI {
    public void delete(YmkCustomTraceEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(YmkCustomTraceEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(YmkCustomTraceEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(YmkCustomTraceEntity t)
            throws Exception {
    }

    private void doUpdateBus(YmkCustomTraceEntity t)
            throws Exception {
    }

    private void doDelBus(YmkCustomTraceEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(YmkCustomTraceEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("cus_name", t.getCusName());
        map.put("cus_id", t.getCusId());
        map.put("contact_name", t.getContactName());
        map.put("contact_id", t.getContactId());
        map.put("trace_name", t.getTraceName());
        map.put("trace_id", t.getTraceId());
        map.put("trace_type", t.getTraceType());
        map.put("trace_state", t.getTraceState());
        map.put("trace_content", t.getTraceContent());
        map.put("trace_time", t.getTraceTime());
        return map;
    }

    public String replaceVal(String sql, YmkCustomTraceEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{cus_id}", String.valueOf(t.getCusId()));
        sql = sql.replace("#{contact_name}", String.valueOf(t.getContactName()));
        sql = sql.replace("#{contact_id}", String.valueOf(t.getContactId()));
        sql = sql.replace("#{trace_name}", String.valueOf(t.getTraceName()));
        sql = sql.replace("#{trace_id}", String.valueOf(t.getTraceId()));
        sql = sql.replace("#{trace_type}", String.valueOf(t.getTraceType()));
        sql = sql.replace("#{trace_state}", String.valueOf(t.getTraceState()));
        sql = sql.replace("#{trace_content}", String.valueOf(t.getTraceContent()));
        sql = sql.replace("#{trace_time}", String.valueOf(t.getTraceTime()));
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
                    javaInter.execute("ymk_custom_trace", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
