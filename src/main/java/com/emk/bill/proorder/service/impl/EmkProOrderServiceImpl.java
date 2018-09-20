package com.emk.bill.proorder.service.impl;

import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.bill.proorder.service.EmkProOrderServiceI;

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

@Service("emkProOrderService")
@Transactional
public class EmkProOrderServiceImpl
        extends CommonServiceImpl
        implements EmkProOrderServiceI {
    public void delete(EmkProOrderEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProOrderEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProOrderEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProOrderEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProOrderEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProOrderEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProOrderEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("state", t.getState());
        map.put("order_no", t.getOrderNo());
        map.put("order_time", t.getOrderTime());
        map.put("remark", t.getRemark());
        map.put("supply_time", t.getSupplyTime());
        return map;
    }

    public String replaceVal(String sql, EmkProOrderEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{state}", String.valueOf(t.getState()));
        sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
        sql = sql.replace("#{order_time}", String.valueOf(t.getOrderTime()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{supply_time}", String.valueOf(t.getSupplyTime()));
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
                    javaInter.execute("emk_pro_order", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
