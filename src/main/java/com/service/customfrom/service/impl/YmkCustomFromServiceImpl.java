package com.service.customfrom.service.impl;

import com.service.customfrom.entity.YmkCustomFromEntity;
import com.service.customfrom.service.YmkCustomFromServiceI;

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

@Service("ymkCustomFromService")
@Transactional
public class YmkCustomFromServiceImpl
        extends CommonServiceImpl
        implements YmkCustomFromServiceI {
    public void delete(YmkCustomFromEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(YmkCustomFromEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(YmkCustomFromEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(YmkCustomFromEntity t)
            throws Exception {
    }

    private void doUpdateBus(YmkCustomFromEntity t)
            throws Exception {
    }

    private void doDelBus(YmkCustomFromEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(YmkCustomFromEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("form_code", t.getFormCode());
        map.put("from_name", t.getFromName());
        map.put("custom_id", t.getCustomId());
        map.put("custom_name", t.getCustomName());
        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, YmkCustomFromEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{form_code}", String.valueOf(t.getFormCode()));
        sql = sql.replace("#{from_name}", String.valueOf(t.getFromName()));
        sql = sql.replace("#{custom_id}", String.valueOf(t.getCustomId()));
        sql = sql.replace("#{custom_name}", String.valueOf(t.getCustomName()));
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
                    javaInter.execute("ymk_custom_from", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
