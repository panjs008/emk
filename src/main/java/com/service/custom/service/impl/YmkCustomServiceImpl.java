package com.service.custom.service.impl;

import com.service.custom.entity.YmkCustomEntity;
import com.service.custom.service.YmkCustomServiceI;

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

@Service("ymkCustomService")
@Transactional
public class YmkCustomServiceImpl
        extends CommonServiceImpl
        implements YmkCustomServiceI {
    public void delete(YmkCustomEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(YmkCustomEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(YmkCustomEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(YmkCustomEntity t)
            throws Exception {
    }

    private void doUpdateBus(YmkCustomEntity t)
            throws Exception {
    }

    private void doDelBus(YmkCustomEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(YmkCustomEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("cus_type", t.getCusType());
        map.put("cus_from", t.getCusFrom());
        map.put("address", t.getAddress());
        map.put("telphone", t.getTelphone());
        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, YmkCustomEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{cus_type}", String.valueOf(t.getCusType()));
        sql = sql.replace("#{cus_from}", String.valueOf(t.getCusFrom()));
        sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
        sql = sql.replace("#{telphone}", String.valueOf(t.getTelphone()));
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
                    javaInter.execute("ymk_custom", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
