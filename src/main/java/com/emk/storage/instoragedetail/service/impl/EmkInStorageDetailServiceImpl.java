package com.emk.storage.instoragedetail.service.impl;

import com.emk.storage.instoragedetail.entity.EmkInStorageDetailEntity;
import com.emk.storage.instoragedetail.service.EmkInStorageDetailServiceI;

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

@Service("emkInStorageDetailService")
@Transactional
public class EmkInStorageDetailServiceImpl
        extends CommonServiceImpl
        implements EmkInStorageDetailServiceI {
    public void delete(EmkInStorageDetailEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkInStorageDetailEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkInStorageDetailEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkInStorageDetailEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkInStorageDetailEntity t)
            throws Exception {
    }

    private void doDelBus(EmkInStorageDetailEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkInStorageDetailEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("sys_company_code", t.getSysCompanyCode());
        map.put("in_storage_id", t.getInStorageId());
        map.put("pro_id", t.getProId());
        map.put("pro_num", t.getProNum());
        map.put("brand", t.getBrand());
        map.put("sign_total", t.getSignTotal());
        map.put("sign_unit", t.getSignUnit());
        map.put("sign_price", t.getSignPrice());
        map.put("remark", t.getRemark());
        map.put("pro_name", t.getProName());
        map.put("storage_set_id", t.getStorageSetId());
        map.put("storage_name", t.getStorageName());
        map.put("position_id", t.getPositionId());
        map.put("position_name", t.getPositionName());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_name", t.getSampleName());
        return map;
    }

    public String replaceVal(String sql, EmkInStorageDetailEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
        sql = sql.replace("#{in_storage_id}", String.valueOf(t.getInStorageId()));
        sql = sql.replace("#{pro_id}", String.valueOf(t.getProId()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{brand}", String.valueOf(t.getBrand()));
        sql = sql.replace("#{sign_total}", String.valueOf(t.getSignTotal()));
        sql = sql.replace("#{sign_unit}", String.valueOf(t.getSignUnit()));
        sql = sql.replace("#{sign_price}", String.valueOf(t.getSignPrice()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{pro_name}", String.valueOf(t.getProName()));
        sql = sql.replace("#{storage_set_id}", String.valueOf(t.getStorageSetId()));
        sql = sql.replace("#{storage_name}", String.valueOf(t.getStorageName()));
        sql = sql.replace("#{position_id}", String.valueOf(t.getPositionId()));
        sql = sql.replace("#{position_name}", String.valueOf(t.getPositionName()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_name}", String.valueOf(t.getSampleName()));
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
                    javaInter.execute("emk_in_storage_detail", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
