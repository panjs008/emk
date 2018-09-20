package com.service.custombank.service.impl;

import com.service.custombank.entity.YmkCustomBankEntity;
import com.service.custombank.service.YmkCustomBankServiceI;

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

@Service("ymkCustomBankService")
@Transactional
public class YmkCustomBankServiceImpl
        extends CommonServiceImpl
        implements YmkCustomBankServiceI {
    public void delete(YmkCustomBankEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(YmkCustomBankEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(YmkCustomBankEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(YmkCustomBankEntity t)
            throws Exception {
    }

    private void doUpdateBus(YmkCustomBankEntity t)
            throws Exception {
    }

    private void doDelBus(YmkCustomBankEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(YmkCustomBankEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("bank_name", t.getBankName());
        map.put("bank_account", t.getBankAccount());
        map.put("bank_account_name", t.getBankAccountName());
        map.put("swifi", t.getSwifi());
        map.put("bz", t.getBz());
        map.put("state", t.getState());
        return map;
    }

    public String replaceVal(String sql, YmkCustomBankEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{bank_name}", String.valueOf(t.getBankName()));
        sql = sql.replace("#{bank_account}", String.valueOf(t.getBankAccount()));
        sql = sql.replace("#{bank_account_name}", String.valueOf(t.getBankAccountName()));
        sql = sql.replace("#{swifi}", String.valueOf(t.getSwifi()));
        sql = sql.replace("#{bz}", String.valueOf(t.getBz()));
        sql = sql.replace("#{state}", String.valueOf(t.getState()));
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
                    javaInter.execute("ymk_custom_bank", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
