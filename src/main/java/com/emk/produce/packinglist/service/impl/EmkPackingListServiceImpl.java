package com.emk.produce.packinglist.service.impl;

import com.emk.produce.packinglist.entity.EmkPackingListEntity;
import com.emk.produce.packinglist.service.EmkPackingListServiceI;
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

@Service("emkPackingListService")
@Transactional
public class EmkPackingListServiceImpl
  extends CommonServiceImpl
  implements EmkPackingListServiceI
{
  public void delete(EmkPackingListEntity entity)
    throws Exception
  {
    super.delete(entity);
    
    doDelBus(entity);
  }
  
  public Serializable save(EmkPackingListEntity entity)
    throws Exception
  {
    Serializable t = super.save(entity);
    
    doAddBus(entity);
    return t;
  }
  
  public void saveOrUpdate(EmkPackingListEntity entity)
    throws Exception
  {
    super.saveOrUpdate(entity);
    
    doUpdateBus(entity);
  }
  
  private void doAddBus(EmkPackingListEntity t)
    throws Exception
  {}
  
  private void doUpdateBus(EmkPackingListEntity t)
    throws Exception
  {}
  
  private void doDelBus(EmkPackingListEntity t)
    throws Exception
  {}
  
  private Map<String, Object> populationMap(EmkPackingListEntity t)
  {
    Map<String, Object> map = new HashMap();
    map.put("sample_no", t.getSampleNo());
    map.put("sample_no_desc", t.getSampleNoDesc());
    map.put("sum_box_total", t.getSumBoxTotal());
    map.put("sum_box_volume", t.getSumBoxVolume());
    map.put("sum_box_jz", t.getSumBoxJz());
    map.put("sum_box_mao", t.getSumBoxMao());
    map.put("id", t.getId());
    map.put("create_name", t.getCreateName());
    map.put("create_by", t.getCreateBy());
    map.put("create_date", t.getCreateDate());
    map.put("sys_org_code", t.getSysOrgCode());
    map.put("order_no", t.getOrderNo());
    map.put("total", t.getTotal());
    map.put("scfmc", t.getScfmc());
    map.put("address", t.getAddress());
    map.put("signer", t.getSigner());
    map.put("sign_date", t.getSignDate());
    map.put("fpbh", t.getFpbh());
    map.put("fp_date", t.getFpDate());
    map.put("changdu", t.getChangdu());
    map.put("kuandu", t.getKuandu());
    map.put("gaodu", t.getGaodu());
    map.put("one_box_volume", t.getOneBoxVolume());
    map.put("one_box_mz", t.getOneBoxMz());
    map.put("one_box_jz", t.getOneBoxJz());
    map.put("size", t.getSize());
    map.put("color", t.getColor());
    return map;
  }
  
  public String replaceVal(String sql, EmkPackingListEntity t)
  {
    sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
    sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
    sql = sql.replace("#{sum_box_total}", String.valueOf(t.getSumBoxTotal()));
    sql = sql.replace("#{sum_box_volume}", String.valueOf(t.getSumBoxVolume()));
    sql = sql.replace("#{sum_box_jz}", String.valueOf(t.getSumBoxJz()));
    sql = sql.replace("#{sum_box_mao}", String.valueOf(t.getSumBoxMao()));
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
    sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
    sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
    sql = sql.replace("#{scfmc}", String.valueOf(t.getScfmc()));
    sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
    sql = sql.replace("#{signer}", String.valueOf(t.getSigner()));
    sql = sql.replace("#{sign_date}", String.valueOf(t.getSignDate()));
    sql = sql.replace("#{fpbh}", String.valueOf(t.getFpbh()));
    sql = sql.replace("#{fp_date}", String.valueOf(t.getFpDate()));
    sql = sql.replace("#{changdu}", String.valueOf(t.getChangdu()));
    sql = sql.replace("#{kuandu}", String.valueOf(t.getKuandu()));
    sql = sql.replace("#{gaodu}", String.valueOf(t.getGaodu()));
    sql = sql.replace("#{one_box_volume}", String.valueOf(t.getOneBoxVolume()));
    sql = sql.replace("#{one_box_mz}", String.valueOf(t.getOneBoxMz()));
    sql = sql.replace("#{one_box_jz}", String.valueOf(t.getOneBoxJz()));
    sql = sql.replace("#{size}", String.valueOf(t.getSize()));
    sql = sql.replace("#{color}", String.valueOf(t.getColor()));
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
          javaInter.execute("emk_packing_list", data);
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
