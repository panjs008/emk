package com.service.customcontact.service.impl;

import com.service.customcontact.entity.YmkCustomContactEntity;
import com.service.customcontact.service.YmkCustomContactServiceI;

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

@Service("ymkCustomContactService")
@Transactional
public class YmkCustomContactServiceImpl
        extends CommonServiceImpl
        implements YmkCustomContactServiceI {
    public void delete(YmkCustomContactEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(YmkCustomContactEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(YmkCustomContactEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(YmkCustomContactEntity t)
            throws Exception {
    }

    private void doUpdateBus(YmkCustomContactEntity t)
            throws Exception {
    }

    private void doDelBus(YmkCustomContactEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(YmkCustomContactEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("user_name", t.getUserName());
        map.put("sex", t.getSex());
        map.put("position", t.getPosition());
        map.put("brith_day", t.getBrithDay());
        map.put("email", t.getEmail());
        map.put("telphone", t.getTelphone());
        map.put("mobile", t.getMobile());
        map.put("remark", t.getRemark());
        map.put("custom_id", t.getCustomId());
        return map;
    }

    public String replaceVal(String sql, YmkCustomContactEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{user_name}", String.valueOf(t.getUserName()));
        sql = sql.replace("#{sex}", String.valueOf(t.getSex()));
        sql = sql.replace("#{position}", String.valueOf(t.getPosition()));
        sql = sql.replace("#{brith_day}", String.valueOf(t.getBrithDay()));
        sql = sql.replace("#{email}", String.valueOf(t.getEmail()));
        sql = sql.replace("#{telphone}", String.valueOf(t.getTelphone()));
        sql = sql.replace("#{mobile}", String.valueOf(t.getMobile()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{custom_id}", String.valueOf(t.getCustomId()));
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
                    javaInter.execute("ymk_custom_contact", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
