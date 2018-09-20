package com.emk.bill.offerprice.service.impl;

import com.emk.bill.offerprice.entity.EmkOfferPriceEntity;
import com.emk.bill.offerprice.service.EmkOfferPriceServiceI;

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

@Service("emkOfferPriceService")
@Transactional
public class EmkOfferPriceServiceImpl
        extends CommonServiceImpl
        implements EmkOfferPriceServiceI {
    public void delete(EmkOfferPriceEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkOfferPriceEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkOfferPriceEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkOfferPriceEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkOfferPriceEntity t)
            throws Exception {
    }

    private void doDelBus(EmkOfferPriceEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkOfferPriceEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("kd_time", t.getKdTime());
        map.put("sample_no", t.getSampleNo());
        map.put("pro_type_name", t.getProTypeName());
        map.put("pro_type", t.getProType());
        map.put("work_price", t.getWorkPrice());
        map.put("loss_price", t.getLossPrice());
        map.put("freight_price", t.getFreightPrice());
        map.put("tax", t.getTax());
        map.put("remark", t.getRemark());
        map.put("custom_sample_url", t.getCustomSampleUrl());
        map.put("custom_sample", t.getCustomSample());
        return map;
    }

    public String replaceVal(String sql, EmkOfferPriceEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{kd_time}", String.valueOf(t.getKdTime()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{work_price}", String.valueOf(t.getWorkPrice()));
        sql = sql.replace("#{loss_price}", String.valueOf(t.getLossPrice()));
        sql = sql.replace("#{freight_price}", String.valueOf(t.getFreightPrice()));
        sql = sql.replace("#{tax}", String.valueOf(t.getTax()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
        sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
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
                    javaInter.execute("emk_offer_price", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
