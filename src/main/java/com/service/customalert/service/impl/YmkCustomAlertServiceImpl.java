package com.service.customalert.service.impl;

import com.service.customalert.entity.YmkCustomAlertEntity;
import com.service.customalert.service.YmkCustomAlertServiceI;

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

@Service("ymkCustomAlertService")
@Transactional
public class YmkCustomAlertServiceImpl
        extends CommonServiceImpl
        implements YmkCustomAlertServiceI {
    public void delete(YmkCustomAlertEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(YmkCustomAlertEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(YmkCustomAlertEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(YmkCustomAlertEntity t)
            throws Exception {
    }

    private void doUpdateBus(YmkCustomAlertEntity t)
            throws Exception {
    }

    private void doDelBus(YmkCustomAlertEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(YmkCustomAlertEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("alert_content", t.getAlertContent());
        map.put("alert_time", t.getAlertTime());
        map.put("alert_user_ids", t.getAlertUserIds());
        map.put("alert_user_names", t.getAlertUserNames());
        return map;
    }

    public String replaceVal(String sql, YmkCustomAlertEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{alert_content}", String.valueOf(t.getAlertContent()));
        sql = sql.replace("#{alert_time}", String.valueOf(t.getAlertTime()));
        sql = sql.replace("#{alert_user_ids}", String.valueOf(t.getAlertUserIds()));
        sql = sql.replace("#{alert_user_names}", String.valueOf(t.getAlertUserNames()));
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
                    javaInter.execute("ymk_custom_alert", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
