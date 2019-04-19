package com.emk.bill.materialcontract.service.impl;

import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity;
import com.emk.bill.materialcontract.service.EmkMaterialContractServiceI;

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

@Service("emkMaterialContractService")
@Transactional
public class EmkMaterialContractServiceImpl
        extends CommonServiceImpl
        implements EmkMaterialContractServiceI {
    public void delete(EmkMaterialContractEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkMaterialContractEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkMaterialContractEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkMaterialContractEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkMaterialContractEntity t)
            throws Exception {
    }

    private void doDelBus(EmkMaterialContractEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkMaterialContractEntity t) {
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

        map.put("gys", t.getGys());
        map.put("gys_code", t.getGysCode());
        map.put("out_date", t.getOutDate());
        map.put("limit_date", t.getLimitDate());
        map.put("leave_limit", t.getLeaveLimit());
        map.put("m_state", t.getMState());
        map.put("type", t.getType());
        return map;
    }

    public String replaceVal(String sql, EmkMaterialContractEntity t) {
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

        sql = sql.replace("#{gys}", String.valueOf(t.getGys()));
        sql = sql.replace("#{gys_code}", String.valueOf(t.getGysCode()));
        sql = sql.replace("#{out_date}", String.valueOf(t.getOutDate()));
        sql = sql.replace("#{limit_date}", String.valueOf(t.getLimitDate()));
        sql = sql.replace("#{leave_limit}", String.valueOf(t.getLeaveLimit()));
        sql = sql.replace("#{m_state}", String.valueOf(t.getMState()));
        sql = sql.replace("#{type}", String.valueOf(t.getType()));
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
                    javaInter.execute("emk_material_contract", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
