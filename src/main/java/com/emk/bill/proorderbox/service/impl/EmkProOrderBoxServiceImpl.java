package com.emk.bill.proorderbox.service.impl;

import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.bill.proorderbox.service.EmkProOrderBoxServiceI;

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

@Service("emkProOrderBoxService")
@Transactional
public class EmkProOrderBoxServiceImpl
        extends CommonServiceImpl
        implements EmkProOrderBoxServiceI {
    public void delete(EmkProOrderBoxEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkProOrderBoxEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkProOrderBoxEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkProOrderBoxEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkProOrderBoxEntity t)
            throws Exception {
    }

    private void doDelBus(EmkProOrderBoxEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkProOrderBoxEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("order_id", t.getOrderId());
        map.put("long_val", t.getLongVal());
        map.put("width_val", t.getWidthVal());
        map.put("height_val", t.getHeightVal());
        map.put("sum_total", t.getSumTotal());
        map.put("one_weight_mao", t.getOneWeightMao());
        map.put("one_weight_jz", t.getOneWeightJz());
        map.put("inbox_total", t.getInboxTotal());
        map.put("box_weight_mao", t.getBoxWeightMao());
        map.put("box_weight_jz", t.getBoxWeightJz());
        map.put("box_volume", t.getBoxVolume());
        map.put("sum_volume", t.getSumVolume());
        map.put("sum_weight_jz", t.getSumWeightJz());
        map.put("box_type", t.getBoxType());
        return map;
    }

    public String replaceVal(String sql, EmkProOrderBoxEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
        sql = sql.replace("#{long_val}", String.valueOf(t.getLongVal()));
        sql = sql.replace("#{width_val}", String.valueOf(t.getWidthVal()));
        sql = sql.replace("#{height_val}", String.valueOf(t.getHeightVal()));
        sql = sql.replace("#{sum_total}", String.valueOf(t.getSumTotal()));
        sql = sql.replace("#{one_weight_mao}", String.valueOf(t.getOneWeightMao()));
        sql = sql.replace("#{one_weight_jz}", String.valueOf(t.getOneWeightJz()));
        sql = sql.replace("#{inbox_total}", String.valueOf(t.getInboxTotal()));
        sql = sql.replace("#{box_weight_mao}", String.valueOf(t.getBoxWeightMao()));
        sql = sql.replace("#{box_weight_jz}", String.valueOf(t.getBoxWeightJz()));
        sql = sql.replace("#{box_volume}", String.valueOf(t.getBoxVolume()));
        sql = sql.replace("#{sum_volume}", String.valueOf(t.getSumVolume()));
        sql = sql.replace("#{sum_weight_jz}", String.valueOf(t.getSumWeightJz()));
        sql = sql.replace("#{box_type}", String.valueOf(t.getBoxType()));
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
                    javaInter.execute("emk_pro_order_box", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
