package com.emk.bill.materialcontractdetail.service.impl;

import com.emk.bill.materialcontractdetail.entity.EmkMaterialContractDetailEntity;
import com.emk.bill.materialcontractdetail.service.EmkMaterialContractDetailServiceI;

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

@Service("emkMaterialContractDetailService")
@Transactional
public class EmkMaterialContractDetailServiceImpl extends CommonServiceImpl implements EmkMaterialContractDetailServiceI {
    public void delete(EmkMaterialContractDetailEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkMaterialContractDetailEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkMaterialContractDetailEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkMaterialContractDetailEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkMaterialContractDetailEntity t)
            throws Exception {
    }

    private void doDelBus(EmkMaterialContractDetailEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkMaterialContractDetailEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("contract_id", t.getContractId());
        map.put("pro_zn_name", t.getProZnName());
        map.put("pro_num", t.getProNum());
        map.put("brand", t.getBrand());
        map.put("unit", t.getUnit());
        map.put("type", t.getType());
        map.put("sign_price", t.getSignPrice());
        map.put("total", t.getTotal());
        map.put("in_total", t.getInTotal());
        map.put("out_total", t.getOutTotal());
        map.put("leavel_total", t.getLeavelTotal());
        return map;
    }

    public String replaceVal(String sql, EmkMaterialContractDetailEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{contract_id}", String.valueOf(t.getContractId()));
        sql = sql.replace("#{pro_zn_name}", String.valueOf(t.getProZnName()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{brand}", String.valueOf(t.getBrand()));
        sql = sql.replace("#{unit}", String.valueOf(t.getUnit()));
        sql = sql.replace("#{type}", String.valueOf(t.getType()));
        sql = sql.replace("#{sign_price}", String.valueOf(t.getSignPrice()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{in_total}", String.valueOf(t.getInTotal()));
        sql = sql.replace("#{out_total}", String.valueOf(t.getOutTotal()));
        sql = sql.replace("#{leavel_total}", String.valueOf(t.getLeavelTotal()));
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
                    javaInter.execute("emk_material_contract_detail", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
