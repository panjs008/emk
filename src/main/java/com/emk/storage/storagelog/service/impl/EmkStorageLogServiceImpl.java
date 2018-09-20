package com.emk.storage.storagelog.service.impl;

import com.emk.storage.storagelog.service.EmkStorageLogServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("emkStorageLogService")
@Transactional
public class EmkStorageLogServiceImpl extends CommonServiceImpl implements EmkStorageLogServiceI {


    public void delete(EmkStorageLogEntity entity) throws Exception {
        super.delete(entity);
        //执行删除操作增强业务
        this.doDelBus(entity);
    }

    public Serializable save(EmkStorageLogEntity entity) throws Exception {
        Serializable t = super.save(entity);
        //执行新增操作增强业务
        this.doAddBus(entity);
        return t;
    }

    public void saveOrUpdate(EmkStorageLogEntity entity) throws Exception {
        super.saveOrUpdate(entity);
        //执行更新操作增强业务
        this.doUpdateBus(entity);
    }

    /**
     * 新增操作增强业务
     *
     * @param t
     * @return
     */
    private void doAddBus(EmkStorageLogEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    /**
     * 更新操作增强业务
     *
     * @param t
     * @return
     */
    private void doUpdateBus(EmkStorageLogEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    /**
     * 删除操作增强业务
     *
     * @param id
     * @return
     */
    private void doDelBus(EmkStorageLogEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    private Map<String, Object> populationMap(EmkStorageLogEntity t) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("pro_id", t.getProId());
        map.put("pro_num", t.getProNum());
        map.put("pro_zn_name", t.getProZnName());
        map.put("pre_total", t.getPreTotal());
        map.put("next_total", t.getNextTotal());
        map.put("total", t.getTotal());
        map.put("pro_type_name", t.getProTypeName());
        map.put("rk_no", t.getRkNo());
        return map;
    }

    /**
     * 替换sql中的变量
     *
     * @param sql
     * @param t
     * @return
     */
    public String replaceVal(String sql, EmkStorageLogEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{pro_id}", String.valueOf(t.getProId()));
        sql = sql.replace("#{pro_num}", String.valueOf(t.getProNum()));
        sql = sql.replace("#{pro_zn_name}", String.valueOf(t.getProZnName()));
        sql = sql.replace("#{pre_total}", String.valueOf(t.getPreTotal()));
        sql = sql.replace("#{next_total}", String.valueOf(t.getNextTotal()));
        sql = sql.replace("#{total}", String.valueOf(t.getTotal()));
        sql = sql.replace("#{pro_type_name}", String.valueOf(t.getProTypeName()));
        sql = sql.replace("#{rk_no}", String.valueOf(t.getRkNo()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    /**
     * 执行JAVA增强
     */
    private void executeJavaExtend(String cgJavaType, String cgJavaValue, Map<String, Object> data) throws Exception {
        if (StringUtil.isNotEmpty(cgJavaValue)) {
            Object obj = null;
            try {
                if ("class".equals(cgJavaType)) {
                    //因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
                    obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
                } else if ("spring".equals(cgJavaType)) {
                    obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                }
                if (obj instanceof CgformEnhanceJavaInter) {
                    CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
                    javaInter.execute("emk_storage_log", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}