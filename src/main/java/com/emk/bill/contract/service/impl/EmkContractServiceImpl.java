package com.emk.bill.contract.service.impl;

import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.contract.service.EmkContractServiceI;

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

@Service("emkContractService")
@Transactional
public class EmkContractServiceImpl
        extends CommonServiceImpl
        implements EmkContractServiceI {
    public void delete(EmkContractEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkContractEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkContractEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkContractEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkContractEntity t)
            throws Exception {
    }

    private void doDelBus(EmkContractEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkContractEntity t) {
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
        map.put("ht_num", t.getHtNum());
        map.put("party_a", t.getPartyA());
        map.put("party_a_id", t.getPartyAId());
        map.put("party_b", t.getPartyB());
        map.put("party_b_id", t.getPartyBId());
        map.put("fob", t.getFob());
        map.put("place", t.getPlace());
        map.put("ycd", t.getYcd());
        map.put("pay_type", t.getPayType());
        map.put("sqdb", t.getSqdb());
        map.put("address", t.getAddress());
        map.put("telphone", t.getTelphone());
        map.put("sign_date", t.getSignDate());
        map.put("order_no", t.getOrderNo());
        return map;
    }

    public String replaceVal(String sql, EmkContractEntity t) {
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
        sql = sql.replace("#{ht_num}", String.valueOf(t.getHtNum()));
        sql = sql.replace("#{party_a}", String.valueOf(t.getPartyA()));
        sql = sql.replace("#{party_a_id}", String.valueOf(t.getPartyAId()));
        sql = sql.replace("#{party_b}", String.valueOf(t.getPartyB()));
        sql = sql.replace("#{party_b_id}", String.valueOf(t.getPartyBId()));
        sql = sql.replace("#{fob}", String.valueOf(t.getFob()));
        sql = sql.replace("#{place}", String.valueOf(t.getPlace()));
        sql = sql.replace("#{ycd}", String.valueOf(t.getYcd()));
        sql = sql.replace("#{pay_type}", String.valueOf(t.getPayType()));
        sql = sql.replace("#{sqdb}", String.valueOf(t.getSqdb()));
        sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
        sql = sql.replace("#{telphone}", String.valueOf(t.getTelphone()));
        sql = sql.replace("#{sign_date}", String.valueOf(t.getSignDate()));
        sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
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
                    javaInter.execute("emk_contract", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
