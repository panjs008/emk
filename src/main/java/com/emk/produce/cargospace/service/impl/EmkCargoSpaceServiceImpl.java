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

        map.put("total", t.getTotal());
        map.put("sum_money", t.getSumMoney());

        map.put("bz", t.getBz());
        map.put("sum_box_total", t.getSumBoxTotal());

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

        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{sum_money}", String.valueOf(t.getSumMoney()));

        sql = sql.replace("#{bz}", String.valueOf(t.getBz()));
        sql = sql.replace("#{sum_box_total}", String.valueOf(t.getSumBoxTotal()));

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
