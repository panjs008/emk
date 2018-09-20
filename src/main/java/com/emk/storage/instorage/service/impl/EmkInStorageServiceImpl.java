package com.emk.storage.instorage.service.impl;

import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.instorage.service.EmkInStorageServiceI;

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

@Service("emkInStorageService")
@Transactional
public class EmkInStorageServiceImpl
        extends CommonServiceImpl
        implements EmkInStorageServiceI {
    public void delete(EmkInStorageEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkInStorageEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkInStorageEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkInStorageEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkInStorageEntity t)
            throws Exception {
    }

    private void doDelBus(EmkInStorageEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkInStorageEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("rk_no", t.getRkNo());
        map.put("remark", t.getRemark());
        map.put("state", t.getState());
        map.put("factory_name", t.getFactoryName());
        map.put("factory_code", t.getFactoryCode());
        map.put("yunfei", t.getYunfei());
        map.put("receive_date", t.getReceiveDate());
        map.put("type", t.getType());
        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, EmkInStorageEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{rk_no}", String.valueOf(t.getRkNo()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{state}", String.valueOf(t.getState()));
        sql = sql.replace("#{factory_name}", String.valueOf(t.getFactoryName()));
        sql = sql.replace("#{factory_code}", String.valueOf(t.getFactoryCode()));
        sql = sql.replace("#{yunfei}", String.valueOf(t.getYunfei()));
        sql = sql.replace("#{receive_date}", String.valueOf(t.getReceiveDate()));
        sql = sql.replace("#{type}", String.valueOf(t.getType()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
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
                    javaInter.execute("emk_in_storage", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
