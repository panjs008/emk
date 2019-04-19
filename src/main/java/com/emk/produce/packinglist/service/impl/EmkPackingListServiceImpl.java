package com.emk.produce.packinglist.service.impl;

import com.emk.produce.packinglist.entity.EmkPackingListEntity;
import com.emk.produce.packinglist.service.EmkPackingListServiceI;

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

@Service("emkPackingListService")
@Transactional
public class EmkPackingListServiceImpl
        extends CommonServiceImpl
        implements EmkPackingListServiceI {
    public void delete(EmkPackingListEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkPackingListEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkPackingListEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkPackingListEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkPackingListEntity t)
            throws Exception {
    }

    private void doDelBus(EmkPackingListEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkPackingListEntity t) {
        Map<String, Object> map = new HashMap();

        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());

        map.put("scfmc", t.getScfmc());
        map.put("address", t.getAddress());
        map.put("signer", t.getSigner());
        map.put("sign_date", t.getSignDate());
        map.put("fpbh", t.getFpbh());
        map.put("fp_date", t.getFpDate());

        return map;
    }

    public String replaceVal(String sql, EmkPackingListEntity t) {

        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));

        sql = sql.replace("#{scfmc}", String.valueOf(t.getScfmc()));
        sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
        sql = sql.replace("#{signer}", String.valueOf(t.getSigner()));
        sql = sql.replace("#{sign_date}", String.valueOf(t.getSignDate()));
        sql = sql.replace("#{fpbh}", String.valueOf(t.getFpbh()));
        sql = sql.replace("#{fp_date}", String.valueOf(t.getFpDate()));

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
                    javaInter.execute("emk_packing_list", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
