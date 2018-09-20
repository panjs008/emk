package com.emk.produce.meeting.service.impl;

import com.emk.produce.meeting.entity.EmkMeetingEntity;
import com.emk.produce.meeting.service.EmkMeetingServiceI;

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

@Service("emkMeetingService")
@Transactional
public class EmkMeetingServiceImpl
        extends CommonServiceImpl
        implements EmkMeetingServiceI {
    public void delete(EmkMeetingEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkMeetingEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkMeetingEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkMeetingEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkMeetingEntity t)
            throws Exception {
    }

    private void doDelBus(EmkMeetingEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkMeetingEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_company_code", t.getSysCompanyCode());
        map.put("meeting_no", t.getMeetingNo());
        map.put("meeting_date", t.getMeetingDate());
        map.put("recorder", t.getRecorder());
        map.put("recorder_id", t.getRecorderId());
        map.put("address", t.getAddress());
        map.put("part_users", t.getPartUsers());
        map.put("part_user_ids", t.getPartUserIds());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("gyzl", t.getGyzl());
        map.put("pro_type", t.getProType());
        map.put("pro_type_name", t.getProTypeName());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_no_desc", t.getSampleNoDesc());
        map.put("order_no", t.getOrderNo());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("developer", t.getDeveloper());
        map.put("developer_name", t.getDeveloperName());
        map.put("zqjc_date", t.getZqjcDate());
        map.put("wqjc_date", t.getWqjcDate());
        map.put("content", t.getContent());
        map.put("jsbbm_advice", t.getJsbbmAdvice());
        map.put("jsbran_advice", t.getJsbranAdvice());
        map.put("fz_advice", t.getFzAdvice());
        map.put("tbz_advice", t.getTbzAdvice());
        map.put("bz_advice", t.getBzAdvice());
        map.put("cg_advice", t.getCgAdvice());
        map.put("yb_advice", t.getYbAdvice());
        map.put("zjz_advice", t.getZjzAdvice());
        map.put("jhb_advice", t.getJhbAdvice());
        map.put("scbzh_advice", t.getScbzhAdvice());
        return map;
    }

    public String replaceVal(String sql, EmkMeetingEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
        sql = sql.replace("#{meeting_no}", String.valueOf(t.getMeetingNo()));
        sql = sql.replace("#{meeting_date}", String.valueOf(t.getMeetingDate()));
        sql = sql.replace("#{recorder}", String.valueOf(t.getRecorder()));
        sql = sql.replace("#{recorder_id}", String.valueOf(t.getRecorderId()));
        sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
        sql = sql.replace("#{part_users}", String.valueOf(t.getPartUsers()));
        sql = sql.replace("#{part_user_ids}", String.valueOf(t.getPartUserIds()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{gyzl}", String.valueOf(t.getGyzl()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
        sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{developer}", String.valueOf(t.getDeveloper()));
        sql = sql.replace("#{developer_name}", String.valueOf(t.getDeveloperName()));
        sql = sql.replace("#{zqjc_date}", String.valueOf(t.getZqjcDate()));
        sql = sql.replace("#{wqjc_date}", String.valueOf(t.getWqjcDate()));
        sql = sql.replace("#{content}", String.valueOf(t.getContent()));
        sql = sql.replace("#{jsbbm_advice}", String.valueOf(t.getJsbbmAdvice()));
        sql = sql.replace("#{jsbran_advice}", String.valueOf(t.getJsbranAdvice()));
        sql = sql.replace("#{fz_advice}", String.valueOf(t.getFzAdvice()));
        sql = sql.replace("#{tbz_advice}", String.valueOf(t.getTbzAdvice()));
        sql = sql.replace("#{bz_advice}", String.valueOf(t.getBzAdvice()));
        sql = sql.replace("#{cg_advice}", String.valueOf(t.getCgAdvice()));
        sql = sql.replace("#{yb_advice}", String.valueOf(t.getYbAdvice()));
        sql = sql.replace("#{zjz_advice}", String.valueOf(t.getZjzAdvice()));
        sql = sql.replace("#{jhb_advice}", String.valueOf(t.getJhbAdvice()));
        sql = sql.replace("#{scbzh_advice}", String.valueOf(t.getScbzhAdvice()));
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
                    javaInter.execute("emk_meeting", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
