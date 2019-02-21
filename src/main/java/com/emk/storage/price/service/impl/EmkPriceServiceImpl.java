package com.emk.storage.price.service.impl;

import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.price.service.EmkPriceServiceI;

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

@Service("emkPriceService")
@Transactional
public class EmkPriceServiceImpl
        extends CommonServiceImpl
        implements EmkPriceServiceI {
    public void delete(EmkPriceEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkPriceEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkPriceEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkPriceEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkPriceEntity t)
            throws Exception {
    }

    private void doDelBus(EmkPriceEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkPriceEntity t) {
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
        map.put("is_print_sample", t.getIsPrintSample());
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
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("weight", t.getWeight());
        map.put("chengf", t.getChengf());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("state", t.getState());
        map.put("bjlx", t.getBjlx());
        map.put("limit_date", t.getLimitDate());
        map.put("bz", t.getBz());
        map.put("target_price", t.getTargetPrice());
        map.put("evlate_total", t.getEvlateTotal());
        map.put("size_fawei", t.getSizeFawei());
        map.put("test_money", t.getTestMoney());
        map.put("profit", t.getProfit());
        map.put("unable_money", t.getUnableMoney());
        map.put("tax", t.getTax());
        map.put("is_test", t.getIsTest());
        map.put("is_chfactory", t.getIsChfactory());
        map.put("is_chkhuo", t.getIsChkhuo());
        map.put("sum_yl", t.getSumYl());
        map.put("sum_feng", t.getSumFeng());
        map.put("sum_bao", t.getSumBao());
        map.put("sum_rg", t.getSumRg());
        map.put("sum_ran", t.getSumRan());
        map.put("sum_yin", t.getSumYin());
        map.put("sum_money", t.getSumMoney());
        map.put("sum_wb", t.getSumWb());
        map.put("huilv", t.getHuilv());
        map.put("huilv_date", t.getHuilvDate());
        map.put("argee_price", t.getArgeePrice());
        map.put("mao_rate", t.getMaoRate());
        map.put("gys_argee_price", t.getGysArgeePrice());
        map.put("cus_argee_price", t.getCusArgeePrice());
        return map;
    }

    public String replaceVal(String sql, EmkPriceEntity t) {
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
        sql = sql.replace("#{is_print_sample}", String.valueOf(t.getIsPrintSample()));
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
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{weight}", String.valueOf(t.getWeight()));
        sql = sql.replace("#{chengf}", String.valueOf(t.getChengf()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));

        sql = sql.replace("#{state}", String.valueOf(t.getState()));

        sql = sql.replace("#{bjlx}", String.valueOf(t.getBjlx()));
        sql = sql.replace("#{limit_date}", String.valueOf(t.getLimitDate()));
        sql = sql.replace("#{bz}", String.valueOf(t.getBz()));
        sql = sql.replace("#{target_price}", String.valueOf(t.getTargetPrice()));
        sql = sql.replace("#{evlate_total}", String.valueOf(t.getEvlateTotal()));
        sql = sql.replace("#{size_fawei}", String.valueOf(t.getSizeFawei()));
        sql = sql.replace("#{test_money}", String.valueOf(t.getTestMoney()));
        sql = sql.replace("#{profit}", String.valueOf(t.getProfit()));
        sql = sql.replace("#{unable_money}", String.valueOf(t.getUnableMoney()));
        sql = sql.replace("#{tax}", String.valueOf(t.getTax()));
        sql = sql.replace("#{is_test}", String.valueOf(t.getIsTest()));
        sql = sql.replace("#{is_chfactory}", String.valueOf(t.getIsChfactory()));
        sql = sql.replace("#{is_chkhuo}", String.valueOf(t.getIsChkhuo()));
        sql = sql.replace("#{sum_yl}", String.valueOf(t.getSumYl()));
        sql = sql.replace("#{sum_feng}", String.valueOf(t.getSumFeng()));
        sql = sql.replace("#{sum_bao}", String.valueOf(t.getSumBao()));
        sql = sql.replace("#{sum_rg}", String.valueOf(t.getSumRg()));
        sql = sql.replace("#{sum_ran}", String.valueOf(t.getSumRan()));
        sql = sql.replace("#{sum_yin}", String.valueOf(t.getSumYin()));
        sql = sql.replace("#{sum_money}", String.valueOf(t.getSumMoney()));
        sql = sql.replace("#{sum_wb}", String.valueOf(t.getSumWb()));
        sql = sql.replace("#{huilv}", String.valueOf(t.getHuilv()));
        sql = sql.replace("#{huilv_date}", String.valueOf(t.getHuilvDate()));
        sql = sql.replace("#{argee_price}", String.valueOf(t.getArgeePrice()));
        sql = sql.replace("#{mao_rate}", String.valueOf(t.getMaoRate()));
        sql = sql.replace("#{gys_argee_price}", String.valueOf(t.getGysArgeePrice()));
        sql = sql.replace("#{cus_argee_price}", String.valueOf(t.getCusArgeePrice()));
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
                    javaInter.execute("emk_price", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
