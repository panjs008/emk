package com.emk.produce.produceqa.service.impl;

import com.emk.produce.produceqa.entity.EmkProduceQaEntity;
import com.emk.produce.produceqa.service.EmkProduceQaServiceI;

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

@Service("emkProduceQaService")
@Transactional
public class EmkProduceQaServiceImpl
        extends CommonServiceImpl
        implements EmkProduceQaServiceI {
    public void delete(EmkProduceQaEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProduceQaEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProduceQaEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProduceQaEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProduceQaEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProduceQaEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProduceQaEntity t) {
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
        map.put("sum_total", t.getSumTotal());
        map.put("out_date", t.getOutDate());
        map.put("level_days", t.getLevelDays());
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("qa_no", t.getQaNo());
        map.put("qa_date", t.getQaDate());
        map.put("order_no", t.getOrderNo());
        map.put("produce_num", t.getProduceNum());
        map.put("gys", t.getGys());
        map.put("gys_code", t.getGysCode());
        map.put("color", t.getColor());
        map.put("recevie_dept_name", t.getRecevieDeptName());
        map.put("recevie_dept_code", t.getRecevieDeptCode());
        map.put("recevier", t.getRecevier());
        map.put("copy_dept_name", t.getCopyDeptName());
        map.put("copy_dept_code", t.getCopyDeptCode());
        map.put("copyer", t.getCopyer());
        map.put("qa_desc", t.getQaDesc());
        map.put("loss", t.getLoss());
        map.put("solve", t.getSolve());
        map.put("copyer_advice", t.getCopyerAdvice());
        map.put("cw_advice", t.getCwAdvice());
        map.put("zjl_advice", t.getZjlAdvice());
        map.put("qa_state", t.getQaState());
        map.put("scan_url", t.getScanUrl());
        map.put("scan_name", t.getScanName());
        return map;
    }

    public String replaceVal(String sql, EmkProduceQaEntity t) {
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
        sql = sql.replace("#{sum_total}", String.valueOf(t.getSumTotal()));
        sql = sql.replace("#{out_date}", String.valueOf(t.getOutDate()));
        sql = sql.replace("#{level_days}", String.valueOf(t.getLevelDays()));
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{qa_no}", String.valueOf(t.getQaNo()));
        sql = sql.replace("#{qa_date}", String.valueOf(t.getQaDate()));
        sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
        sql = sql.replace("#{produce_num}", String.valueOf(t.getProduceNum()));
        sql = sql.replace("#{gys}", String.valueOf(t.getGys()));
        sql = sql.replace("#{gys_code}", String.valueOf(t.getGysCode()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{recevie_dept_name}", String.valueOf(t.getRecevieDeptName()));
        sql = sql.replace("#{recevie_dept_code}", String.valueOf(t.getRecevieDeptCode()));
        sql = sql.replace("#{recevier}", String.valueOf(t.getRecevier()));
        sql = sql.replace("#{copy_dept_name}", String.valueOf(t.getCopyDeptName()));
        sql = sql.replace("#{copy_dept_code}", String.valueOf(t.getCopyDeptCode()));
        sql = sql.replace("#{copyer}", String.valueOf(t.getCopyer()));
        sql = sql.replace("#{qa_desc}", String.valueOf(t.getQaDesc()));
        sql = sql.replace("#{loss}", String.valueOf(t.getLoss()));
        sql = sql.replace("#{solve}", String.valueOf(t.getSolve()));
        sql = sql.replace("#{copyer_advice}", String.valueOf(t.getCopyerAdvice()));
        sql = sql.replace("#{cw_advice}", String.valueOf(t.getCwAdvice()));
        sql = sql.replace("#{zjl_advice}", String.valueOf(t.getZjlAdvice()));
        sql = sql.replace("#{qa_state}", String.valueOf(t.getQaState()));
        sql = sql.replace("#{scan_url}", String.valueOf(t.getScanUrl()));
        sql = sql.replace("#{scan_name}", String.valueOf(t.getScanName()));
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
                    javaInter.execute("emk_produce_qa", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
