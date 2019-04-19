package com.emk.produce.invoices.service.impl;

import com.emk.produce.invoices.entity.EmkInvoicesEntity;
import com.emk.produce.invoices.service.EmkInvoicesServiceI;

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

@Service("emkInvoicesService")
@Transactional
public class EmkInvoicesServiceImpl
        extends CommonServiceImpl
        implements EmkInvoicesServiceI {
    public void delete(EmkInvoicesEntity entity)
            throws Exception {
        super.delete(entity);

        doDelBus(entity);
    }

    public Serializable save(EmkInvoicesEntity entity)
            throws Exception {
        Serializable t = super.save(entity);

        doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkInvoicesEntity entity)
            throws Exception {
        super.saveOrUpdate(entity);

        doUpdateBus(entity);
    }

    private void doAddBus(EmkInvoicesEntity t)
            throws Exception {
    }

    private void doUpdateBus(EmkInvoicesEntity t)
            throws Exception {
    }

    private void doDelBus(EmkInvoicesEntity t)
            throws Exception {
    }

    private Map<String, Object> populationMap(EmkInvoicesEntity t) {
        Map<String, Object> map = new HashMap();
        map.put("businesser", t.getBusinesser());
        map.put("businesser_name", t.getBusinesserName());
        map.put("tracer", t.getTracer());
        map.put("tracer_name", t.getTracerName());
        map.put("businesse_dept_name", t.getBusinesseDeptName());
        map.put("businesse_dept_id", t.getBusinesseDeptId());
        map.put("developer", t.getDeveloper());
        map.put("developer_name", t.getDeveloperName());

        map.put("out_date", t.getOutDate());
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("cwyer", t.getCwyer());
        map.put("scfmc", t.getScfmc());
        map.put("address", t.getAddress());
        map.put("signer", t.getSigner());
        map.put("sign_date", t.getSignDate());
        map.put("fpbh", t.getFpbh());
        map.put("fp_date", t.getFpDate());
        map.put("bz", t.getBz());
        map.put("sum_money", t.getSumMoney());
        map.put("skfs", t.getSkfs());
        map.put("td_num", t.getTdNum());
        map.put("tdzt", t.getTdzt());
        map.put("sign_address", t.getSignAddress());
        map.put("sign_telphone", t.getSignTelphone());
        map.put("cargo_no", t.getCargoNo());
        map.put("leveal_factory_no", t.getLevealFactoryNo());
        return map;
    }

    public String replaceVal(String sql, EmkInvoicesEntity t) {
        sql = sql.replace("#{businesser}", String.valueOf(t.getBusinesser()));
        sql = sql.replace("#{businesser_name}", String.valueOf(t.getBusinesserName()));
        sql = sql.replace("#{tracer}", String.valueOf(t.getTracer()));
        sql = sql.replace("#{tracer_name}", String.valueOf(t.getTracerName()));
        sql = sql.replace("#{businesse_dept_name}", String.valueOf(t.getBusinesseDeptName()));
        sql = sql.replace("#{businesse_dept_id}", String.valueOf(t.getBusinesseDeptId()));
        sql = sql.replace("#{developer}", String.valueOf(t.getDeveloper()));
        sql = sql.replace("#{developer_name}", String.valueOf(t.getDeveloperName()));
        sql = sql.replace("#{out_date}", String.valueOf(t.getOutDate()));
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{cwyer}", String.valueOf(t.getCwyer()));
        sql = sql.replace("#{scfmc}", String.valueOf(t.getScfmc()));
        sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
        sql = sql.replace("#{signer}", String.valueOf(t.getSigner()));
        sql = sql.replace("#{sign_date}", String.valueOf(t.getSignDate()));
        sql = sql.replace("#{fpbh}", String.valueOf(t.getFpbh()));
        sql = sql.replace("#{fp_date}", String.valueOf(t.getFpDate()));
        sql = sql.replace("#{bz}", String.valueOf(t.getBz()));
        sql = sql.replace("#{sum_money}", String.valueOf(t.getSumMoney()));
        sql = sql.replace("#{skfs}", String.valueOf(t.getSkfs()));
        sql = sql.replace("#{td_num}", String.valueOf(t.getTdNum()));
        sql = sql.replace("#{tdzt}", String.valueOf(t.getTdzt()));
        sql = sql.replace("#{sign_address}", String.valueOf(t.getSignAddress()));
        sql = sql.replace("#{sign_telphone}", String.valueOf(t.getSignTelphone()));
        sql = sql.replace("#{cargo_no}", String.valueOf(t.getCargoNo()));
        sql = sql.replace("#{leveal_factory_no}", String.valueOf(t.getLevealFactoryNo()));
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
                    javaInter.execute("emk_invoices", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
