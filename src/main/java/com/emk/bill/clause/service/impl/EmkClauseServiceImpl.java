package com.emk.bill.clause.service.impl;

import com.emk.bill.clause.entity.EmkClauseEntity;
import com.emk.bill.clause.service.EmkClauseServiceI;
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

@Service("emkClauseService")
@Transactional
public class EmkClauseServiceImpl
  extends CommonServiceImpl
  implements EmkClauseServiceI
{
  public void delete(EmkClauseEntity entity)
    throws Exception
  {
    super.delete(entity);
    
    doDelBus(entity);
  }
  
  public Serializable save(EmkClauseEntity entity)
    throws Exception
  {
    Serializable t = super.save(entity);
    
    doAddBus(entity);
    return t;
  }
  
  public void saveOrUpdate(EmkClauseEntity entity)
    throws Exception
  {
    super.saveOrUpdate(entity);
    
    doUpdateBus(entity);
  }
  
  private void doAddBus(EmkClauseEntity t)
    throws Exception
  {}
  
  private void doUpdateBus(EmkClauseEntity t)
    throws Exception
  {}
  
  private void doDelBus(EmkClauseEntity t)
    throws Exception
  {}
  
  private Map<String, Object> populationMap(EmkClauseEntity t)
  {
    Map<String, Object> map = new HashMap();
    map.put("id", t.getId());
    map.put("create_name", t.getCreateName());
    map.put("create_by", t.getCreateBy());
    map.put("create_date", t.getCreateDate());
    map.put("sys_org_code", t.getSysOrgCode());
    map.put("clause_num", t.getClauseNum());
    map.put("clause_content", t.getClauseContent());
    map.put("remark", t.getRemark());
    return map;
  }
  
  public String replaceVal(String sql, EmkClauseEntity t)
  {
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
    sql = sql.replace("#{clause_num}", String.valueOf(t.getClauseNum()));
    sql = sql.replace("#{clause_content}", String.valueOf(t.getClauseContent()));
    sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
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
          javaInter.execute("emk_clause", data);
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
