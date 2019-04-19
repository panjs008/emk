package com.emk.produce.produce.service.impl;
import com.emk.produce.produce.service.EmkProduceServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.produce.produce.entity.EmkProduceEntity;
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

@Service("emkProduceService")
@Transactional
public class EmkProduceServiceImpl extends CommonServiceImpl implements EmkProduceServiceI {

	
 	public void delete(EmkProduceEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkProduceEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkProduceEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkProduceEntity t) throws Exception{
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
	private void doUpdateBus(EmkProduceEntity t) throws Exception{
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
	private void doDelBus(EmkProduceEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkProduceEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("material_no", t.getMaterialNo());
		map.put("kd_date", t.getKdDate());
		map.put("businesser", t.getBusinesser());

		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("developer", t.getDeveloper());
		map.put("developer_name", t.getDeveloperName());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());

		map.put("lead_advice", t.getLeadAdvice());
		map.put("is_pass", t.getIsPass());
		map.put("lead_user_id", t.getLeadUserId());
		map.put("leader", t.getLeader());
		map.put("state", t.getState());
		map.put("ss_sample_user", t.getSsSampleUser());
		map.put("ss_sample_user_id", t.getSsSampleUserId());
		map.put("cq_sample_user", t.getCqSampleUser());
		map.put("cq_sample_user_id", t.getCqSampleUserId());
		map.put("ss_sample_advice", t.getSsSampleAdvice());
		map.put("cq_sample_advice", t.getCqSampleAdvice());
		map.put("color_user", t.getColorUser());
		map.put("color_user_id", t.getColorUserId());
		map.put("color_advice", t.getColorAdvice());
		map.put("test_user", t.getTestUser());
		map.put("test_user_id", t.getTestUserId());
		map.put("test_user_advice", t.getTestUserAdvice());

		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkProduceEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{material_no}",String.valueOf(t.getMaterialNo()));
 		sql  = sql.replace("#{kd_date}",String.valueOf(t.getKdDate()));
 		sql  = sql.replace("#{businesser}",String.valueOf(t.getBusinesser()));

 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{developer}",String.valueOf(t.getDeveloper()));
 		sql  = sql.replace("#{developer_name}",String.valueOf(t.getDeveloperName()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));

 		sql  = sql.replace("#{lead_advice}",String.valueOf(t.getLeadAdvice()));
 		sql  = sql.replace("#{is_pass}",String.valueOf(t.getIsPass()));
 		sql  = sql.replace("#{lead_user_id}",String.valueOf(t.getLeadUserId()));
 		sql  = sql.replace("#{leader}",String.valueOf(t.getLeader()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{ss_sample_user}",String.valueOf(t.getSsSampleUser()));
 		sql  = sql.replace("#{ss_sample_user_id}",String.valueOf(t.getSsSampleUserId()));
 		sql  = sql.replace("#{cq_sample_user}",String.valueOf(t.getCqSampleUser()));
 		sql  = sql.replace("#{cq_sample_user_id}",String.valueOf(t.getCqSampleUserId()));
 		sql  = sql.replace("#{ss_sample_advice}",String.valueOf(t.getSsSampleAdvice()));
 		sql  = sql.replace("#{cq_sample_advice}",String.valueOf(t.getCqSampleAdvice()));
 		sql  = sql.replace("#{color_user}",String.valueOf(t.getColorUser()));
 		sql  = sql.replace("#{color_user_id}",String.valueOf(t.getColorUserId()));
 		sql  = sql.replace("#{color_advice}",String.valueOf(t.getColorAdvice()));
 		sql  = sql.replace("#{test_user}",String.valueOf(t.getTestUser()));
 		sql  = sql.replace("#{test_user_id}",String.valueOf(t.getTestUserId()));
 		sql  = sql.replace("#{test_user_advice}",String.valueOf(t.getTestUserAdvice()));
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
					javaInter.execute("emk_produce",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}