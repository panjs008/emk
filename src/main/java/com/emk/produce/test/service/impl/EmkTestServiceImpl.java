package com.emk.produce.test.service.impl;

import com.emk.produce.test.entity.EmkTestEntity;
import com.emk.produce.test.service.EmkTestServiceI;

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

@Service("emkTestService")
@Transactional
public class EmkTestServiceImpl
        extends CommonServiceImpl
        implements EmkTestServiceI {
    public void delete(EmkTestEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkTestEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkTestEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkTestEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkTestEntity t)
            throws Exception {
    }

    private void doDelBus(EmkTestEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkTestEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("developer", t.getDeveloper());
        map.put("developer_name", t.getDeveloperName());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("order_no", t.getOrderNo());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_no_desc", t.getSampleNoDesc());
        map.put("total", t.getTotal());
        map.put("test_type", t.getTestType());
        map.put("produce_num", t.getProduceNum());
        map.put("test_required", t.getTestRequired());
        map.put("test_content", t.getTestContent());
        map.put("test_content_url", t.getTestContentUrl());
        map.put("limit_date", t.getLimitDate());
        map.put("ys_date", t.getYsDate());
        map.put("level_days", t.getLevelDays());
        map.put("kd_date", t.getKdDate());
        map.put("test_jd", t.getTestJd());
        map.put("test_notic_date", t.getTestNoticDate());
        map.put("send_date", t.getSendDate());
        map.put("recevie_date", t.getRecevieDate());
        map.put("test_result", t.getTestResult());
        map.put("test_state", t.getTestState());
        return map;
    }

    public String replaceVal(String sql, EmkTestEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{developer}", String.valueOf(t.getDeveloper()));
        sql = sql.replace("#{developer_name}", String.valueOf(t.getDeveloperName()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{test_type}", String.valueOf(t.getTestType()));
        sql = sql.replace("#{produce_num}", String.valueOf(t.getProduceNum()));
        sql = sql.replace("#{test_required}", String.valueOf(t.getTestRequired()));
        sql = sql.replace("#{test_content}", String.valueOf(t.getTestContent()));
        sql = sql.replace("#{test_content_url}", String.valueOf(t.getTestContentUrl()));
        sql = sql.replace("#{limit_date}", String.valueOf(t.getLimitDate()));
        sql = sql.replace("#{ys_date}", String.valueOf(t.getYsDate()));
        sql = sql.replace("#{level_days}", String.valueOf(t.getLevelDays()));
        sql = sql.replace("#{kd_date}", String.valueOf(t.getKdDate()));
        sql = sql.replace("#{test_jd}", String.valueOf(t.getTestJd()));
        sql = sql.replace("#{test_notic_date}", String.valueOf(t.getTestNoticDate()));
        sql = sql.replace("#{send_date}", String.valueOf(t.getSendDate()));
        sql = sql.replace("#{recevie_date}", String.valueOf(t.getRecevieDate()));
        sql = sql.replace("#{test_result}", String.valueOf(t.getTestResult()));
        sql = sql.replace("#{test_state}", String.valueOf(t.getTestState()));
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
                    javaInter.execute("emk_test", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
