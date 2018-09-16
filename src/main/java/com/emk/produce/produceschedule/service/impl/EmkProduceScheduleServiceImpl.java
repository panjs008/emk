package com.emk.produce.produceschedule.service.impl;

import com.emk.produce.produceschedule.entity.EmkProduceScheduleEntity;
import com.emk.produce.produceschedule.service.EmkProduceScheduleServiceI;
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

@Service("emkProduceScheduleService")
@Transactional
public class EmkProduceScheduleServiceImpl
  extends CommonServiceImpl
  implements EmkProduceScheduleServiceI
{
  public void delete(EmkProduceScheduleEntity entity)
    throws Exception
  {
    super.delete(entity);
    
    doDelBus(entity);
  }
  
  public Serializable save(EmkProduceScheduleEntity entity)
    throws Exception
  {
    Serializable t = super.save(entity);
    
    doAddBus(entity);
    return t;
  }
  
  public void saveOrUpdate(EmkProduceScheduleEntity entity)
    throws Exception
  {
    super.saveOrUpdate(entity);
    
    doUpdateBus(entity);
  }
  
  private void doAddBus(EmkProduceScheduleEntity t)
    throws Exception
  {}
  
  private void doUpdateBus(EmkProduceScheduleEntity t)
    throws Exception
  {}
  
  private void doDelBus(EmkProduceScheduleEntity t)
    throws Exception
  {}
  
  private Map<String, Object> populationMap(EmkProduceScheduleEntity t)
  {
    Map<String, Object> map = new HashMap();
    map.put("id", t.getId());
    map.put("create_name", t.getCreateName());
    map.put("create_by", t.getCreateBy());
    map.put("create_date", t.getCreateDate());
    map.put("sys_org_code", t.getSysOrgCode());
    map.put("material_no", t.getMaterialNo());
    map.put("kd_date", t.getKdDate());
    map.put("businesser", t.getBusinesser());
    map.put("cus_num", t.getCusNum());
    map.put("businesser_name", t.getBusinesserName());
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
    map.put("tracer", t.getTracer());
    map.put("tracer_name", t.getTracerName());
    map.put("ht_num", t.getHtNum());
    map.put("sum_total", t.getSumTotal());
    map.put("custom_sample_url", t.getCustomSampleUrl());
    map.put("custom_sample", t.getCustomSample());
    map.put("produce_ht_num", t.getProduceHtNum());
    map.put("order_no", t.getOrderNo());
    map.put("ylbl_state", t.getYlblState());
    map.put("ylbl_limit_date", t.getYlblLimitDate());
    map.put("leavel_ylbl_day", t.getLeavelYlblDay());
    map.put("fzbl_state", t.getFzblState());
    map.put("fzbl_limit_date", t.getFzblLimitDate());
    map.put("leavel_fzbl_day", t.getLeavelFzblDay());
    map.put("bzbl_state", t.getBzblState());
    map.put("bzbl_limit_date", t.getBzblLimitDate());
    map.put("leavel_bzbl_day", t.getLeavelBzblDay());
    map.put("ran_state", t.getRanState());
    map.put("ran_finish", t.getRanFinish());
    map.put("ran_unfinish", t.getRanUnfinish());
    map.put("fz_state", t.getFzState());
    map.put("fz_finish", t.getFzFinish());
    map.put("fz_unfinish", t.getFzUnfinish());
    map.put("bt_state", t.getBtState());
    map.put("bt_finish", t.getBtFinish());
    map.put("bt_unfinish", t.getBtUnfinish());
    map.put("zt_state", t.getZtState());
    map.put("zt_finish", t.getZtFinish());
    map.put("zt_unfinish", t.getZtUnfinish());
    map.put("bz_state", t.getBzState());
    map.put("bz_finish", t.getBzFinish());
    map.put("bz_unfinish", t.getBzUnfinish());
    map.put("out_date", t.getOutDate());
    return map;
  }
  
  public String replaceVal(String sql, EmkProduceScheduleEntity t)
  {
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
    sql = sql.replace("#{material_no}", String.valueOf(t.getMaterialNo()));
    sql = sql.replace("#{kd_date}", String.valueOf(t.getKdDate()));
    sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
    sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
    sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
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
    sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
    sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
    sql = sql.replace("#{ht_num}", String.valueOf(t.getHtNum()));
    sql = sql.replace("#{sum_total}", String.valueOf(t.getSumTotal()));
    sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
    sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
    sql = sql.replace("#{produce_ht_num}", String.valueOf(t.getProduceHtNum()));
    sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
    sql = sql.replace("#{ylbl_state}", String.valueOf(t.getYlblState()));
    sql = sql.replace("#{ylbl_limit_date}", String.valueOf(t.getYlblLimitDate()));
    sql = sql.replace("#{leavel_ylbl_day}", String.valueOf(t.getLeavelYlblDay()));
    sql = sql.replace("#{fzbl_state}", String.valueOf(t.getFzblState()));
    sql = sql.replace("#{fzbl_limit_date}", String.valueOf(t.getFzblLimitDate()));
    sql = sql.replace("#{leavel_fzbl_day}", String.valueOf(t.getLeavelFzblDay()));
    sql = sql.replace("#{bzbl_state}", String.valueOf(t.getBzblState()));
    sql = sql.replace("#{bzbl_limit_date}", String.valueOf(t.getBzblLimitDate()));
    sql = sql.replace("#{leavel_bzbl_day}", String.valueOf(t.getLeavelBzblDay()));
    sql = sql.replace("#{ran_state}", String.valueOf(t.getRanState()));
    sql = sql.replace("#{ran_finish}", String.valueOf(t.getRanFinish()));
    sql = sql.replace("#{ran_unfinish}", String.valueOf(t.getRanUnfinish()));
    sql = sql.replace("#{fz_state}", String.valueOf(t.getFzState()));
    sql = sql.replace("#{fz_finish}", String.valueOf(t.getFzFinish()));
    sql = sql.replace("#{fz_unfinish}", String.valueOf(t.getFzUnfinish()));
    sql = sql.replace("#{bt_state}", String.valueOf(t.getBtState()));
    sql = sql.replace("#{bt_finish}", String.valueOf(t.getBtFinish()));
    sql = sql.replace("#{bt_unfinish}", String.valueOf(t.getBtUnfinish()));
    sql = sql.replace("#{zt_state}", String.valueOf(t.getZtState()));
    sql = sql.replace("#{zt_finish}", String.valueOf(t.getZtFinish()));
    sql = sql.replace("#{zt_unfinish}", String.valueOf(t.getZtUnfinish()));
    sql = sql.replace("#{bz_state}", String.valueOf(t.getBzState()));
    sql = sql.replace("#{bz_finish}", String.valueOf(t.getBzFinish()));
    sql = sql.replace("#{bz_unfinish}", String.valueOf(t.getBzUnfinish()));
    sql = sql.replace("#{out_date}", String.valueOf(t.getOutDate()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    return sql;
  }
  
  private void executeJavaExtend(String cgJavaType, String cgJavaValue, Map<String, Object> data)
    throws Exception
  {
    if (StringUtil.isNotEmpty(cgJavaValue))
    {
      Object obj = null;
      try
      {
        if ("class".equals(cgJavaType)) {
          obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
        } else if ("spring".equals(cgJavaType)) {
          obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
        }
        if ((obj instanceof CgformEnhanceJavaInter))
        {
          CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter)obj;
          javaInter.execute("emk_produce_schedule", data);
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
        throw new Exception("执行JAVA增强出现异常！");
      }
    }
  }
}
