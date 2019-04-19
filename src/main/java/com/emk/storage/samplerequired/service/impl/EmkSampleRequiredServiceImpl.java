package com.emk.storage.samplerequired.service.impl;

import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.samplerequired.service.EmkSampleRequiredServiceI;

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

@Service("emkSampleRequiredService")
@Transactional
public class EmkSampleRequiredServiceImpl
        extends CommonServiceImpl
        implements EmkSampleRequiredServiceI {
    public void delete(EmkSampleRequiredEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkSampleRequiredEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkSampleRequiredEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkSampleRequiredEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkSampleRequiredEntity t)
            throws Exception {
    }

    private void doDelBus(EmkSampleRequiredEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkSampleRequiredEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("kd_date", t.getKdDate());
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("cus_num", t.getCusNum());
        map.put("cus_name", t.getCusName());
        map.put("gyzl", t.getGyzl());
        map.put("pro_type", t.getProType());
        map.put("pro_type_name", t.getProTypeName());
        map.put("custom_sample_url", t.getCustomSampleUrl());
        map.put("custom_sample", t.getCustomSample());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_no_desc", t.getSampleNoDesc());
        map.put("is_get_sample", t.getIsGetSample());
        map.put("is_have_old", t.getIsHaveOld());
        map.put("old_image_url", t.getOldImageUrl());
        map.put("old_image", t.getOldImage());
        map.put("is_have_dgr", t.getIsHaveDgr());
        map.put("dgr_image_url", t.getDgrImageUrl());
        map.put("dgr_image", t.getDgrImage());
        map.put("is_have_size", t.getIsHaveSize());
        map.put("size_image_url", t.getSizeImageUrl());
        map.put("size_image", t.getSizeImage());
        map.put("ys_date", t.getYsDate());
        map.put("level_days", t.getLevelDays());
        map.put("remark", t.getRemark());
        map.put("version", t.getVersion());
        map.put("weight", t.getWeight());
        map.put("chengf", t.getChengf());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());

        map.put("state", t.getState());
        map.put("sample_to", t.getSampleTo());
        map.put("recevier", t.getRecevier());
        map.put("recevie_date", t.getRecevieDate());
        map.put("sender", t.getSender());
        map.put("send_total", t.getSendTotal());
        map.put("level_total", t.getLevelTotal());

        map.put("remark", t.getRemark());
        return map;
    }

    public String replaceVal(String sql, EmkSampleRequiredEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{kd_date}", String.valueOf(t.getKdDate()));
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{cus_num}", String.valueOf(t.getCusNum()));
        sql = sql.replace("#{cus_name}", String.valueOf(t.getCusName()));
        sql = sql.replace("#{gyzl}", String.valueOf(t.getGyzl()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{custom_sample_url}", String.valueOf(t.getCustomSampleUrl()));
        sql = sql.replace("#{custom_sample}", String.valueOf(t.getCustomSample()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
        sql = sql.replace("#{is_get_sample}", String.valueOf(t.getIsGetSample()));
        sql = sql.replace("#{is_have_old}", String.valueOf(t.getIsHaveOld()));
        sql = sql.replace("#{old_image_url}", String.valueOf(t.getOldImageUrl()));
        sql = sql.replace("#{old_image}", String.valueOf(t.getOldImage()));
        sql = sql.replace("#{is_have_dgr}", String.valueOf(t.getIsHaveDgr()));
        sql = sql.replace("#{dgr_image_url}", String.valueOf(t.getDgrImageUrl()));
        sql = sql.replace("#{dgr_image}", String.valueOf(t.getDgrImage()));
        sql = sql.replace("#{is_have_size}", String.valueOf(t.getIsHaveSize()));
        sql = sql.replace("#{size_image_url}", String.valueOf(t.getSizeImageUrl()));
        sql = sql.replace("#{size_image}", String.valueOf(t.getSizeImage()));
        sql = sql.replace("#{ys_date}", String.valueOf(t.getYsDate()));
        sql = sql.replace("#{level_days}", String.valueOf(t.getLevelDays()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{version}", String.valueOf(t.getVersion()));
        sql = sql.replace("#{weight}", String.valueOf(t.getWeight()));
        sql = sql.replace("#{chengf}", String.valueOf(t.getChengf()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));

        sql = sql.replace("#{state}", String.valueOf(t.getState()));
        sql = sql.replace("#{sample_to}", String.valueOf(t.getSampleTo()));
        sql = sql.replace("#{recevier}", String.valueOf(t.getRecevier()));
        sql = sql.replace("#{recevie_date}", String.valueOf(t.getRecevieDate()));
        sql = sql.replace("#{sender}", String.valueOf(t.getSender()));
        sql = sql.replace("#{send_total}", String.valueOf(t.getSendTotal()));
        sql = sql.replace("#{level_total}", String.valueOf(t.getLevelTotal()));
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
                    javaInter.execute("emk_sample_required", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
