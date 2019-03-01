package com.emk.storage.color.service.impl;

import com.emk.storage.color.entity.EmkColorEntity;
import com.emk.storage.color.service.EmkColorServiceI;

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

@Service("emkColorService")
@Transactional
public class EmkColorServiceImpl
        extends CommonServiceImpl
        implements EmkColorServiceI {
    public void delete(EmkColorEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkColorEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkColorEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkColorEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkColorEntity t)
            throws Exception {
    }

    private void doDelBus(EmkColorEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkColorEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("sample_no", t.getSampleNo());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("is_color_card", t.getIsColorCard());
        map.put("color_card_url", t.getColorCardUrl());
        map.put("color_card", t.getColorCard());
        map.put("sample_id", t.getSampleId());
        map.put("color_no", t.getColorNo());
        map.put("is_color_num", t.getIsColorNum());
        map.put("color_num_url", t.getColorNumUrl());
        map.put("color_num", t.getColorNum());
        map.put("color_card_remark", t.getColorCardRemark());
        map.put("color_num_remark", t.getColorNumRemark());
        map.put("is_color_data", t.getIsColorData());
        map.put("color_data", t.getColorData());
        map.put("color_data_url", t.getColorDataUrl());
        map.put("is_color_qtx", t.getIsColorQtx());
        map.put("color_qtx", t.getColorQtx());
        map.put("color_qtx_url", t.getColorQtxUrl());
        map.put("color_type", t.getColorType());

        map.put("cgy", t.getCgy());
        map.put("kd_date", t.getKdDate());

        map.put("color_slg", t.getColorSlg());
        map.put("hxwz", t.getHxwz());
        map.put("gjs", t.getGjs());
        map.put("sy_to", t.getSyTo());
        map.put("cus_remark", t.getCusRemark());

        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        return map;
    }

    public String replaceVal(String sql, EmkColorEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{is_color_card}", String.valueOf(t.getIsColorCard()));
        sql = sql.replace("#{color_card_url}", String.valueOf(t.getColorCardUrl()));
        sql = sql.replace("#{color_card}", String.valueOf(t.getColorCard()));
        sql = sql.replace("#{sample_id}", String.valueOf(t.getSampleId()));
        sql = sql.replace("#{color_no}", String.valueOf(t.getColorNo()));
        sql = sql.replace("#{is_color_num}", String.valueOf(t.getIsColorNum()));
        sql = sql.replace("#{color_num_url}", String.valueOf(t.getColorNumUrl()));
        sql = sql.replace("#{color_num}", String.valueOf(t.getColorNum()));
        sql = sql.replace("#{color_card_remark}", String.valueOf(t.getColorCardRemark()));
        sql = sql.replace("#{color_num_remark}", String.valueOf(t.getColorNumRemark()));
        sql = sql.replace("#{is_color_data}", String.valueOf(t.getIsColorData()));
        sql = sql.replace("#{color_data}", String.valueOf(t.getColorData()));
        sql = sql.replace("#{color_data_url}", String.valueOf(t.getColorDataUrl()));
        sql = sql.replace("#{is_color_qtx}", String.valueOf(t.getIsColorQtx()));
        sql = sql.replace("#{color_qtx}", String.valueOf(t.getColorQtx()));
        sql = sql.replace("#{color_qtx_url}", String.valueOf(t.getColorQtxUrl()));
        sql = sql.replace("#{color_type}", String.valueOf(t.getColorType()));

        sql = sql.replace("#{cgy}", String.valueOf(t.getCgy()));
        sql = sql.replace("#{kd_date}", String.valueOf(t.getKdDate()));

        sql = sql.replace("#{color_slg}", String.valueOf(t.getColorSlg()));
        sql = sql.replace("#{hxwz}", String.valueOf(t.getHxwz()));
        sql = sql.replace("#{gjs}", String.valueOf(t.getGjs()));
        sql = sql.replace("#{sy_to}", String.valueOf(t.getSyTo()));
        sql = sql.replace("#{cus_remark}", String.valueOf(t.getCusRemark()));

        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
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
                    javaInter.execute("emk_color", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
