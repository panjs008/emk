package com.emk.produce.cargospace.service.impl;

import com.emk.produce.cargospace.entity.EmkCargoSpaceEntity;
import com.emk.produce.cargospace.service.EmkCargoSpaceServiceI;

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

@Service("emkCargoSpaceService")
@Transactional
public class EmkCargoSpaceServiceImpl extends CommonServiceImpl implements EmkCargoSpaceServiceI {
    public void delete(EmkCargoSpaceEntity entity) throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkCargoSpaceEntity entity) throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkCargoSpaceEntity entity) throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkCargoSpaceEntity t) throws Exception {
    }

    private void doUpdateBus(EmkCargoSpaceEntity t) throws Exception {
    }

    private void doDelBus(EmkCargoSpaceEntity t) throws Exception {
    }

    private Map<String, Object> populationMap(EmkCargoSpaceEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("developer", t.getDeveloper());
        map.put("developer_name", t.getDeveloperName());
        map.put("gyzl", t.getGyzl());
        map.put("pro_type", t.getProType());
        map.put("pro_type_name", t.getProTypeName());
        map.put("sample_no", t.getSampleNo());
        map.put("sample_no_desc", t.getSampleNoDesc());
        map.put("total", t.getTotal());
        map.put("sum_money", t.getSumMoney());
        map.put("size", t.getSize());
        map.put("price", t.getPrice());
        map.put("bz", t.getBz());
        map.put("sum_box_total", t.getSumBoxTotal());
        map.put("sum_box_volume", t.getSumBoxVolume());
        map.put("sum_box_jz", t.getSumBoxJz());
        map.put("sum_box_mao", t.getSumBoxMao());
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("cargo_no", t.getCargoNo());
        map.put("kd_date", t.getKdDate());
        map.put("out_forum_no", t.getOutForumNo());
        map.put("leveal_factory_no", t.getLevealFactoryNo());
        map.put("gys", t.getGys());
        map.put("gys_code", t.getGysCode());
        map.put("cwyer", t.getCwyer());
        map.put("hd_name", t.getHdName());
        map.put("destination", t.getDestination());
        map.put("hao_date", t.getHaoDate());
        map.put("go_date", t.getGoDate());
        map.put("fp_num", t.getFpNum());
        map.put("arrvie_date", t.getArrvieDate());
        map.put("shrer", t.getShrer());
        map.put("telphone", t.getTelphone());
        map.put("order_no", t.getOrderNo());
        map.put("produce_num", t.getProduceNum());
        map.put("chang", t.getChang());
        map.put("kuan", t.getKuan());
        map.put("gao", t.getGao());
        map.put("one_mz", t.getOneMz());
        map.put("one_jz", t.getOneJz());
        map.put("one_volume", t.getOneVolume());
        map.put("money", t.getMoney());
        map.put("cargo_state", t.getCargoState());
        map.put("out_factory_state", t.getOutFactoryState());
        return map;
    }

    public String replaceVal(String sql, EmkCargoSpaceEntity t) {
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
        sql = sql.replace("#{developer}", String.valueOf(t.getDeveloper()));
        sql = sql.replace("#{developer_name}", String.valueOf(t.getDeveloperName()));
        sql = sql.replace("#{gyzl}", String.valueOf(t.getGyzl()));
        sql = sql.replace("#{pro_type}", String.valueOf(t.getProType()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{sample_no}", String.valueOf(t.getSampleNo()));
        sql = sql.replace("#{sample_no_desc}", String.valueOf(t.getSampleNoDesc()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{sum_money}", String.valueOf(t.getSumMoney()));
        sql = sql.replace("#{size}", String.valueOf(t.getSize()));
        sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
        sql = sql.replace("#{bz}", String.valueOf(t.getBz()));
        sql = sql.replace("#{sum_box_total}", String.valueOf(t.getSumBoxTotal()));
        sql = sql.replace("#{sum_box_volume}", String.valueOf(t.getSumBoxVolume()));
        sql = sql.replace("#{sum_box_jz}", String.valueOf(t.getSumBoxJz()));
        sql = sql.replace("#{sum_box_mao}", String.valueOf(t.getSumBoxMao()));
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{cargo_no}", String.valueOf(t.getCargoNo()));
        sql = sql.replace("#{kd_date}", String.valueOf(t.getKdDate()));
        sql = sql.replace("#{out_forum_no}", String.valueOf(t.getOutForumNo()));
        sql = sql.replace("#{leveal_factory_no}", String.valueOf(t.getLevealFactoryNo()));
        sql = sql.replace("#{gys}", String.valueOf(t.getGys()));
        sql = sql.replace("#{gys_code}", String.valueOf(t.getGysCode()));
        sql = sql.replace("#{cwyer}", String.valueOf(t.getCwyer()));
        sql = sql.replace("#{hd_name}", String.valueOf(t.getHdName()));
        sql = sql.replace("#{destination}", String.valueOf(t.getDestination()));
        sql = sql.replace("#{hao_date}", String.valueOf(t.getHaoDate()));
        sql = sql.replace("#{go_date}", String.valueOf(t.getGoDate()));
        sql = sql.replace("#{fp_num}", String.valueOf(t.getFpNum()));
        sql = sql.replace("#{arrvie_date}", String.valueOf(t.getArrvieDate()));
        sql = sql.replace("#{shrer}", String.valueOf(t.getShrer()));
        sql = sql.replace("#{telphone}", String.valueOf(t.getTelphone()));
        sql = sql.replace("#{order_no}", String.valueOf(t.getOrderNo()));
        sql = sql.replace("#{produce_num}", String.valueOf(t.getProduceNum()));
        sql = sql.replace("#{chang}", String.valueOf(t.getChang()));
        sql = sql.replace("#{kuan}", String.valueOf(t.getKuan()));
        sql = sql.replace("#{gao}", String.valueOf(t.getGao()));
        sql = sql.replace("#{one_mz}", String.valueOf(t.getOneMz()));
        sql = sql.replace("#{one_jz}", String.valueOf(t.getOneJz()));
        sql = sql.replace("#{one_volume}", String.valueOf(t.getOneVolume()));
        sql = sql.replace("#{money}", String.valueOf(t.getMoney()));
        sql = sql.replace("#{cargo_state}", String.valueOf(t.getCargoState()));
        sql = sql.replace("#{out_factory_state}", String.valueOf(t.getOutFactoryState()));
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
                    javaInter.execute("emk_cargo_space", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
