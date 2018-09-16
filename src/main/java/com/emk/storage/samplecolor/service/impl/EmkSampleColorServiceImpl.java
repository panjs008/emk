package com.emk.storage.samplecolor.service.impl;

import com.emk.storage.samplecolor.entity.EmkSampleColorEntity;
import com.emk.storage.samplecolor.service.EmkSampleColorServiceI;
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

@Service("emkSampleColorService")
@Transactional
public class EmkSampleColorServiceImpl
  extends CommonServiceImpl
  implements EmkSampleColorServiceI
{
  public void delete(EmkSampleColorEntity entity)
    throws Exception
  {
    super.delete(entity);
    
    doDelBus(entity);
  }
  
  public Serializable save(EmkSampleColorEntity entity)
    throws Exception
  {
    Serializable t = super.save(entity);
    
    doAddBus(entity);
    return t;
  }
  
  public void saveOrUpdate(EmkSampleColorEntity entity)
    throws Exception
  {
    super.saveOrUpdate(entity);
    
    doUpdateBus(entity);
  }
  
  private void doAddBus(EmkSampleColorEntity t)
    throws Exception
  {}
  
  private void doUpdateBus(EmkSampleColorEntity t)
    throws Exception
  {}
  
  private void doDelBus(EmkSampleColorEntity t)
    throws Exception
  {}
  
  private Map<String, Object> populationMap(EmkSampleColorEntity t)
  {
    Map<String, Object> map = new HashMap();
    map.put("id", t.getId());
    map.put("create_name", t.getCreateName());
    map.put("create_by", t.getCreateBy());
    map.put("create_date", t.getCreateDate());
    map.put("update_name", t.getUpdateName());
    map.put("update_by", t.getUpdateBy());
    map.put("update_date", t.getUpdateDate());
    map.put("sys_org_code", t.getSysOrgCode());
    map.put("sys_company_code", t.getSysCompanyCode());
    map.put("bpm_status", t.getBpmStatus());
    map.put("sytzdbh", t.getSytzdbh());
    map.put("sytzdrq", t.getSytzdrq());
    map.put("syxqdbh", t.getSyxqdbh());
    map.put("gc", t.getGc());
    map.put("businesser", t.getBusinesser());
    map.put("tracer", t.getTracer());
    map.put("gyzl", t.getGyzl());
    map.put("sample_no_desc", t.getSampleNoDesc());
    map.put("ysywm", t.getYsywm());
    map.put("yaswm", t.getYaswm());
    map.put("sh", t.getSh());
    map.put("sygg", t.getSygg());
    map.put("sysl", t.getSysl());
    map.put("jscs", t.getJscs());
    map.put("pf", t.getPf());
    map.put("wd", t.getWd());
    map.put("sj", t.getSj());
    map.put("ypwcjcyj", t.getYpwcjcyj());
    map.put("jsylsyj", t.getJsylsyj());
    map.put("ypzjyyj", t.getYpzjyyj());
    map.put("gdyyj", t.getGdyyj());
    map.put("ywyyj", t.getYwyyj());
    map.put("zhyj", t.getZhyj());
    map.put("custom_sample_url", t.getCustomSampleUrl());
    map.put("custom_sample", t.getCustomSample());
    map.put("sample_no", t.getSampleNo());
    map.put("version", t.getVersion());
    map.put("chengf", t.getChengf());
    map.put("weight", t.getWeight());
    map.put("order_num", t.getOrderNum());
    map.put("remark", t.getRemark());
    map.put("purpose", t.getPurpose());
    map.put("businesser_name", t.getBusinesserName());
    map.put("tracer_name", t.getTracerName());
    map.put("businesse_dept_name", t.getBusinesseDeptName());
    map.put("businesse_dept_id", t.getBusinesseDeptId());
    map.put("cus_num", t.getCusNum());
    map.put("cus_name", t.getCusName());
    map.put("pro_type", t.getProType());
    map.put("pro_type_name", t.getProTypeName());
    map.put("sample_no", t.getSampleNo());
    return map;
  }
  
  public String replaceVal(String sql, EmkSampleColorEntity t)
  {
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
    sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
    sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
    sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
    sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
    sql = sql.replace("#{bpm_status}", String.valueOf(t.getBpmStatus()));
    sql = sql.replace("#{sytzdbh}", String.valueOf(t.getSytzdbh()));
    sql = sql.replace("#{sytzdrq}", String.valueOf(t.getSytzdrq()));
    sql = sql.replace("#{syxqdbh}", String.valueOf(t.getSyxqdbh()));
    sql = sql.replace("#{gc}", String.valueOf(t.getGc()));
    sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
    sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
    sql = sql.replace("#{gyzl}", String.valueOf(t.getGyzl()));
    sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
    sql = sql.replace("#{ysywm}", String.valueOf(t.getYsywm()));
    sql = sql.replace("#{yaswm}", String.valueOf(t.getYaswm()));
    sql = sql.replace("#{sh}", String.valueOf(t.getSh()));
    sql = sql.replace("#{sygg}", String.valueOf(t.getSygg()));
    sql = sql.replace("#{sysl}", String.valueOf(t.getSysl()));
    sql = sql.replace("#{jscs}", String.valueOf(t.getJscs()));
    sql = sql.replace("#{pf}", String.valueOf(t.getPf()));
    sql = sql.replace("#{wd}", String.valueOf(t.getWd()));
    sql = sql.replace("#{sj}", String.valueOf(t.getSj()));
    sql = sql.replace("#{ypwcjcyj}", String.valueOf(t.getYpwcjcyj()));
    sql = sql.replace("#{jsylsyj}", String.valueOf(t.getJsylsyj()));
    sql = sql.replace("#{ypzjyyj}", String.valueOf(t.getYpzjyyj()));
    sql = sql.replace("#{gdyyj}", String.valueOf(t.getGdyyj()));
    sql = sql.replace("#{ywyyj}", String.valueOf(t.getYwyyj()));
    sql = sql.replace("#{zhyj}", String.valueOf(t.getZhyj()));
    sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
    sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
    sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
    sql = sql.replace("#{version}", String.valueOf(t.getVersion()));
    sql = sql.replace("#{chengf}", String.valueOf(t.getChengf()));
    sql = sql.replace("#{weight}", String.valueOf(t.getWeight()));
    sql = sql.replace("#{order_num}", String.valueOf(t.getOrderNum()));
    sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
    sql = sql.replace("#{purpose}", String.valueOf(t.getPurpose()));
    sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
    sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
    sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
    sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
    sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
    sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
    sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
    sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
    sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
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
          javaInter.execute("emk_sample_color", data);
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
