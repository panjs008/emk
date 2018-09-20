package com.emk.bill.proorderdetail.service.impl;

import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;
import com.emk.bill.proorderdetail.service.EmkProOrderDetailServiceI;

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

@Service("emkProOrderDetailService")
@Transactional
public class EmkProOrderDetailServiceImpl
        extends CommonServiceImpl
        implements EmkProOrderDetailServiceI {
    public void delete(EmkProOrderDetailEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProOrderDetailEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProOrderDetailEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProOrderDetailEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProOrderDetailEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProOrderDetailEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProOrderDetailEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("pro_order_id", t.getProOrderId());
        map.put("sample_no", t.getSampleNo());
        map.put("total", t.getTotal());
        map.put("size", t.getSize());
        map.put("color", t.getColor());
        map.put("remark", t.getRemark());
        map.put("price", t.getPrice());
        return map;
    }

    public String replaceVal(String sql, EmkProOrderDetailEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{pro_order_id}", String.valueOf(t.getProOrderId()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{size}", String.valueOf(t.getSize()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
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
                    javaInter.execute("emk_pro_order_detail", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
