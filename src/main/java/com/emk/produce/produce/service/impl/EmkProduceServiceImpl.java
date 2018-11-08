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
		map.put("cus_num", t.getCusNum());
		map.put("businesser_name", t.getBusinesserName());
		map.put("cus_name", t.getCusName());
		map.put("gyzl", t.getGyzl());
		map.put("pro_type", t.getProType());
		map.put("pro_type_name", t.getProTypeName());
		map.put("sample_no", t.getSampleNo());
		map.put("sample_no_desc", t.getSampleNoDesc());
		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("developer", t.getDeveloper());
		map.put("developer_name", t.getDeveloperName());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());
		map.put("ht_num", t.getHtNum());
		map.put("sum_total", t.getSumTotal());
		map.put("custom_sample_url", t.getCustomSampleUrl());
		map.put("custom_sample", t.getCustomSample());
		map.put("produce_ht_num", t.getProduceHtNum());
		map.put("order_no", t.getOrderNo());
		map.put("ylbl_state", t.getYlblState());
		map.put("ylbl_limit_date", t.getYlblLimitDate());
		map.put("leavel_ylbl_day", t.getLeavelYlblDay());
		map.put("fzbl_state", t.getFzblState());
		map.put("fzbl_limit_date", t.getFzblLimitDate());
		map.put("leavel_fzbl_day", t.getLeavelFzblDay());
		map.put("bzbl_state", t.getBzblState());
		map.put("bzbl_limit_date", t.getBzblLimitDate());
		map.put("leavel_bzbl_day", t.getLeavelBzblDay());
		map.put("ran_state", t.getRanState());
		map.put("ran_finish", t.getRanFinish());
		map.put("ran_unfinish", t.getRanUnfinish());
		map.put("cai_state", t.getCaiState());
		map.put("cai_finish", t.getCaiFinish());
		map.put("cai_unfinish", t.getCaiUnfinish());
		map.put("fz_state", t.getFzState());
		map.put("fz_finish", t.getFzFinish());
		map.put("fz_unfinish", t.getFzUnfinish());
		map.put("bt_state", t.getBtState());
		map.put("bt_finish", t.getBtFinish());
		map.put("bt_unfinish", t.getBtUnfinish());
		map.put("zt_state", t.getZtState());
		map.put("zt_finish", t.getZtFinish());
		map.put("zt_unfinish", t.getZtUnfinish());
		map.put("bz_state", t.getBzState());
		map.put("bz_finish", t.getBzFinish());
		map.put("bz_unfinish", t.getBzUnfinish());
		map.put("out_date", t.getOutDate());
		map.put("gys", t.getGys());
		map.put("gys_code", t.getGysCode());
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
 		sql  = sql.replace("#{cus_num}",String.valueOf(t.getCusNum()));
 		sql  = sql.replace("#{businesser_name}",String.valueOf(t.getBusinesserName()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{gyzl}",String.valueOf(t.getGyzl()));
 		sql  = sql.replace("#{pro_type}",String.valueOf(t.getProType()));
 		sql  = sql.replace("#{pro_type_name}",String.valueOf(t.getProTypeName()));
 		sql  = sql.replace("#{sample_no}",String.valueOf(t.getSampleNo()));
 		sql  = sql.replace("#{sample_no_desc}",String.valueOf(t.getSampleNoDesc()));
 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{developer}",String.valueOf(t.getDeveloper()));
 		sql  = sql.replace("#{developer_name}",String.valueOf(t.getDeveloperName()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));
 		sql  = sql.replace("#{ht_num}",String.valueOf(t.getHtNum()));
 		sql  = sql.replace("#{sum_total}",String.valueOf(t.getSumTotal()));
 		sql  = sql.replace("#{custom_sample_url}",String.valueOf(t.getCustomSampleUrl()));
 		sql  = sql.replace("#{custom_sample}",String.valueOf(t.getCustomSample()));
 		sql  = sql.replace("#{produce_ht_num}",String.valueOf(t.getProduceHtNum()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{ylbl_state}",String.valueOf(t.getYlblState()));
 		sql  = sql.replace("#{ylbl_limit_date}",String.valueOf(t.getYlblLimitDate()));
 		sql  = sql.replace("#{leavel_ylbl_day}",String.valueOf(t.getLeavelYlblDay()));
 		sql  = sql.replace("#{fzbl_state}",String.valueOf(t.getFzblState()));
 		sql  = sql.replace("#{fzbl_limit_date}",String.valueOf(t.getFzblLimitDate()));
 		sql  = sql.replace("#{leavel_fzbl_day}",String.valueOf(t.getLeavelFzblDay()));
 		sql  = sql.replace("#{bzbl_state}",String.valueOf(t.getBzblState()));
 		sql  = sql.replace("#{bzbl_limit_date}",String.valueOf(t.getBzblLimitDate()));
 		sql  = sql.replace("#{leavel_bzbl_day}",String.valueOf(t.getLeavelBzblDay()));
 		sql  = sql.replace("#{ran_state}",String.valueOf(t.getRanState()));
 		sql  = sql.replace("#{ran_finish}",String.valueOf(t.getRanFinish()));
 		sql  = sql.replace("#{ran_unfinish}",String.valueOf(t.getRanUnfinish()));
 		sql  = sql.replace("#{cai_state}",String.valueOf(t.getCaiState()));
 		sql  = sql.replace("#{cai_finish}",String.valueOf(t.getCaiFinish()));
 		sql  = sql.replace("#{cai_unfinish}",String.valueOf(t.getCaiUnfinish()));
 		sql  = sql.replace("#{fz_state}",String.valueOf(t.getFzState()));
 		sql  = sql.replace("#{fz_finish}",String.valueOf(t.getFzFinish()));
 		sql  = sql.replace("#{fz_unfinish}",String.valueOf(t.getFzUnfinish()));
 		sql  = sql.replace("#{bt_state}",String.valueOf(t.getBtState()));
 		sql  = sql.replace("#{bt_finish}",String.valueOf(t.getBtFinish()));
 		sql  = sql.replace("#{bt_unfinish}",String.valueOf(t.getBtUnfinish()));
 		sql  = sql.replace("#{zt_state}",String.valueOf(t.getZtState()));
 		sql  = sql.replace("#{zt_finish}",String.valueOf(t.getZtFinish()));
 		sql  = sql.replace("#{zt_unfinish}",String.valueOf(t.getZtUnfinish()));
 		sql  = sql.replace("#{bz_state}",String.valueOf(t.getBzState()));
 		sql  = sql.replace("#{bz_finish}",String.valueOf(t.getBzFinish()));
 		sql  = sql.replace("#{bz_unfinish}",String.valueOf(t.getBzUnfinish()));
 		sql  = sql.replace("#{out_date}",String.valueOf(t.getOutDate()));
 		sql  = sql.replace("#{gys}",String.valueOf(t.getGys()));
 		sql  = sql.replace("#{gys_code}",String.valueOf(t.getGysCode()));
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