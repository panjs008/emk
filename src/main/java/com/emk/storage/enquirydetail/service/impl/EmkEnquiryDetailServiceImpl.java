package com.emk.storage.enquirydetail.service.impl;

import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.enquirydetail.service.EmkEnquiryDetailServiceI;

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

@Service("emkEnquiryDetailService")
@Transactional
public class EmkEnquiryDetailServiceImpl
        extends CommonServiceImpl
        implements EmkEnquiryDetailServiceI {
    public void delete(EmkEnquiryDetailEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkEnquiryDetailEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkEnquiryDetailEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkEnquiryDetailEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkEnquiryDetailEntity t)
            throws Exception {
    }

    private void doDelBus(EmkEnquiryDetailEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkEnquiryDetailEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("color", t.getColor());
        map.put("color_value", t.getColorValue());
        map.put("size", t.getSize());
        map.put("total", t.getTotal());
        map.put("size_total", t.getSizeTotal());
        map.put("color_total", t.getColorTotal());
        map.put("price", t.getPrice());
        return map;
    }

    public String replaceVal(String sql, EmkEnquiryDetailEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{color}", String.valueOf(t.getColor()));
        sql = sql.replace("#{color_value}", String.valueOf(t.getColorValue()));
        sql = sql.replace("#{size}", String.valueOf(t.getSize()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{size_total}", String.valueOf(t.getSizeTotal()));
        sql = sql.replace("#{color_total}", String.valueOf(t.getColorTotal()));
        sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
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
                    javaInter.execute("emk_enquiry_detail", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
