package com.emk.storage.storagesetposition.service.impl;

import com.emk.storage.storagesetposition.entity.EmkStorageSetPositionEntity;
import com.emk.storage.storagesetposition.service.EmkStorageSetPositionServiceI;

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

@Service("emkStorageSetPositionService")
@Transactional
public class EmkStorageSetPositionServiceImpl
        extends CommonServiceImpl
        implements EmkStorageSetPositionServiceI {
    public void delete(EmkStorageSetPositionEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkStorageSetPositionEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkStorageSetPositionEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkStorageSetPositionEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkStorageSetPositionEntity t)
            throws Exception {
    }

    private void doDelBus(EmkStorageSetPositionEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkStorageSetPositionEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("storage_id", t.getStorageId());
        map.put("code", t.getCode());
        map.put("position_name", t.getPositionName());
        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, EmkStorageSetPositionEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{storage_id}", String.valueOf(t.getStorageId()));
        sql = sql.replace("#{code}", String.valueOf(t.getCode()));
        sql = sql.replace("#{position_name}", String.valueOf(t.getPositionName()));
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
                    javaInter.execute("emk_storage_set_position", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
