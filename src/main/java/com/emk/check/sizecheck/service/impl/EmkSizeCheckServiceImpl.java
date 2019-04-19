package com.emk.check.sizecheck.service.impl;
import com.emk.check.sizecheck.service.EmkSizeCheckServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.check.sizecheck.entity.EmkSizeCheckEntity;
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

@Service("emkSizeCheckService")
@Transactional
public class EmkSizeCheckServiceImpl extends CommonServiceImpl implements EmkSizeCheckServiceI {

	
 	public void delete(EmkSizeCheckEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkSizeCheckEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkSizeCheckEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkSizeCheckEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(EmkSizeCheckEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(EmkSizeCheckEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkSizeCheckEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("size_check_num", t.getSizeCheckNum());
		map.put("businesser", t.getBusinesser());
		map.put("businesser_name", t.getBusinesserName());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());
		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("developer_name", t.getDeveloperName());
		map.put("developer", t.getDeveloper());
		map.put("gys", t.getGys());
		map.put("gys_code", t.getGysCode());
		map.put("cus_num", t.getCusNum());
		map.put("cus_name", t.getCusName());
		map.put("order_no", t.getOrderNo());
		map.put("ht_num", t.getHtNum());
		map.put("factory_name", t.getFactoryName());
		map.put("address", t.getAddress());
		map.put("relationer", t.getRelationer());
		map.put("telphone", t.getTelphone());
		map.put("check_type", t.getCheckType());
		map.put("sample_no", t.getSampleNo());
		map.put("sample_desc", t.getSampleDesc());
		map.put("color", t.getColor());
		map.put("size", t.getSize());
		map.put("box_total", t.getBoxTotal());
		map.put("vesion", t.getVesion());
		map.put("check_total", t.getCheckTotal());
		map.put("check_date", t.getCheckDate());
		map.put("chima", t.getChima());
		map.put("check_result", t.getCheckResult());
		map.put("remart", t.getRemart());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkSizeCheckEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{size_check_num}",String.valueOf(t.getSizeCheckNum()));
 		sql  = sql.replace("#{businesser}",String.valueOf(t.getBusinesser()));
 		sql  = sql.replace("#{businesser_name}",String.valueOf(t.getBusinesserName()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));
 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{developer_name}",String.valueOf(t.getDeveloperName()));
 		sql  = sql.replace("#{developer}",String.valueOf(t.getDeveloper()));
 		sql  = sql.replace("#{gys}",String.valueOf(t.getGys()));
 		sql  = sql.replace("#{gys_code}",String.valueOf(t.getGysCode()));
 		sql  = sql.replace("#{cus_num}",String.valueOf(t.getCusNum()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{ht_num}",String.valueOf(t.getHtNum()));
 		sql  = sql.replace("#{factory_name}",String.valueOf(t.getFactoryName()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{relationer}",String.valueOf(t.getRelationer()));
 		sql  = sql.replace("#{telphone}",String.valueOf(t.getTelphone()));
 		sql  = sql.replace("#{check_type}",String.valueOf(t.getCheckType()));
 		sql  = sql.replace("#{sample_no}",String.valueOf(t.getSampleNo()));
 		sql  = sql.replace("#{sample_desc}",String.valueOf(t.getSampleDesc()));
 		sql  = sql.replace("#{color}",String.valueOf(t.getColor()));
 		sql  = sql.replace("#{size}",String.valueOf(t.getSize()));
 		sql  = sql.replace("#{box_total}",String.valueOf(t.getBoxTotal()));
 		sql  = sql.replace("#{vesion}",String.valueOf(t.getVesion()));
 		sql  = sql.replace("#{check_total}",String.valueOf(t.getCheckTotal()));
 		sql  = sql.replace("#{check_date}",String.valueOf(t.getCheckDate()));
 		sql  = sql.replace("#{chima}",String.valueOf(t.getChima()));
 		sql  = sql.replace("#{check_result}",String.valueOf(t.getCheckResult()));
 		sql  = sql.replace("#{remart}",String.valueOf(t.getRemart()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("emk_size_check",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}