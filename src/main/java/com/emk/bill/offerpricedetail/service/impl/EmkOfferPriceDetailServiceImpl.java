package com.emk.bill.offerpricedetail.service.impl;

import com.emk.bill.offerpricedetail.entity.EmkOfferPriceDetailEntity;
import com.emk.bill.offerpricedetail.service.EmkOfferPriceDetailServiceI;

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

@Service("emkOfferPriceDetailService")
@Transactional
public class EmkOfferPriceDetailServiceImpl
        extends CommonServiceImpl
        implements EmkOfferPriceDetailServiceI {
    public void delete(EmkOfferPriceDetailEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkOfferPriceDetailEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkOfferPriceDetailEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkOfferPriceDetailEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkOfferPriceDetailEntity t)
            throws Exception {
    }

    private void doDelBus(EmkOfferPriceDetailEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkOfferPriceDetailEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("offer_price_id", t.getOfferPriceId());
        map.put("pro_id", t.getProId());
        map.put("pro_num", t.getProNum());
        map.put("brand", t.getBrand());
        map.put("sign_total", t.getSignTotal());
        map.put("sign_price", t.getSignPrice());
        map.put("unit", t.getUnit());
        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, EmkOfferPriceDetailEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{offer_price_id}", String.valueOf(t.getOfferPriceId()));
        sql = sql.replace("#{pro_id}", String.valueOf(t.getProId()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{brand}", String.valueOf(t.getBrand()));
        sql = sql.replace("#{sign_total}", String.valueOf(t.getSignTotal()));
        sql = sql.replace("#{sign_price}", String.valueOf(t.getSignPrice()));
        sql = sql.replace("#{unit}", String.valueOf(t.getUnit()));
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
                    javaInter.execute("emk_offer_price_detail", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
