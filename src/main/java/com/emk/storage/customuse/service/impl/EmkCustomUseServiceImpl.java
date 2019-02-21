package com.emk.storage.customuse.service.impl;
import com.emk.storage.customuse.service.EmkCustomUseServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.storage.customuse.entity.EmkCustomUseEntity;
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

@Service("emkCustomUseService")
@Transactional
public class EmkCustomUseServiceImpl extends CommonServiceImpl implements EmkCustomUseServiceI {

	
 	public void delete(EmkCustomUseEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkCustomUseEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkCustomUseEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkCustomUseEntity t) throws Exception{
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
	private void doUpdateBus(EmkCustomUseEntity t) throws Exception{
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
	private void doDelBus(EmkCustomUseEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkCustomUseEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("cus_num", t.getCusNum());
		map.put("cus_name", t.getCusName());
		map.put("sub_cus_num", t.getSubCusNum());
		map.put("sub_cus_name", t.getSubCusName());
		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("businesser", t.getBusinesser());
		map.put("businesser_name", t.getBusinesserName());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());
		map.put("people_advice", t.getPeopleAdvice());
		map.put("zl_advice", t.getZlAdvice());
		map.put("is_people_advice", t.getIsPeopleAdvice());
		map.put("is_zl_advice", t.getIsZlAdvice());
		map.put("fk_advice", t.getFkAdvice());
		map.put("is_fk_advice", t.getIsFkAdvice());
		map.put("check_huo", t.getCheckHuo());
		map.put("is_check_huo", t.getIsCheckHuo());
		map.put("test", t.getTest());
		map.put("is_test", t.getIsTest());
		map.put("pact", t.getPact());
		map.put("is_pact", t.getIsPact());
		map.put("sample", t.getSample());
		map.put("is_sample", t.getIsSample());
		map.put("send_huo", t.getSendHuo());
		map.put("is_send_huo", t.getIsSendHuo());
		map.put("zdhdmc", t.getZdhdmc());
		map.put("relation", t.getRelation());
		map.put("people_file_url", t.getPeopleFileUrl());
		map.put("people_file", t.getPeopleFile());
		map.put("zl_file_url", t.getZlFileUrl());
		map.put("zl_file", t.getZlFile());
		map.put("check_huo_file_url", t.getCheckHuoFileUrl());
		map.put("check_huo_file", t.getCheckHuoFile());
		map.put("test_file_url", t.getTestFileUrl());
		map.put("test_file", t.getTestFile());
		map.put("pact_file_url", t.getPactFileUrl());
		map.put("pact_file", t.getPactFile());
		map.put("fk_file_url", t.getFkFileUrl());
		map.put("fk_file", t.getFkFile());
		map.put("sample_file_url", t.getSampleFileUrl());
		map.put("sample_file", t.getSampleFile());
		map.put("send_huo_file_url", t.getSendHuoFileUrl());
		map.put("send_huo_file", t.getSendHuoFile());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkCustomUseEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{cus_num}",String.valueOf(t.getCusNum()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{sub_cus_num}",String.valueOf(t.getSubCusNum()));
 		sql  = sql.replace("#{sub_cus_name}",String.valueOf(t.getSubCusName()));
 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{businesser}",String.valueOf(t.getBusinesser()));
 		sql  = sql.replace("#{businesser_name}",String.valueOf(t.getBusinesserName()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));
 		sql  = sql.replace("#{people_advice}",String.valueOf(t.getPeopleAdvice()));
 		sql  = sql.replace("#{zl_advice}",String.valueOf(t.getZlAdvice()));
 		sql  = sql.replace("#{is_people_advice}",String.valueOf(t.getIsPeopleAdvice()));
 		sql  = sql.replace("#{is_zl_advice}",String.valueOf(t.getIsZlAdvice()));
 		sql  = sql.replace("#{fk_advice}",String.valueOf(t.getFkAdvice()));
 		sql  = sql.replace("#{is_fk_advice}",String.valueOf(t.getIsFkAdvice()));
 		sql  = sql.replace("#{check_huo}",String.valueOf(t.getCheckHuo()));
 		sql  = sql.replace("#{is_check_huo}",String.valueOf(t.getIsCheckHuo()));
 		sql  = sql.replace("#{test}",String.valueOf(t.getTest()));
 		sql  = sql.replace("#{is_test}",String.valueOf(t.getIsTest()));
 		sql  = sql.replace("#{pact}",String.valueOf(t.getPact()));
 		sql  = sql.replace("#{is_pact}",String.valueOf(t.getIsPact()));
 		sql  = sql.replace("#{sample}",String.valueOf(t.getSample()));
 		sql  = sql.replace("#{is_sample}",String.valueOf(t.getIsSample()));
 		sql  = sql.replace("#{send_huo}",String.valueOf(t.getSendHuo()));
 		sql  = sql.replace("#{is_send_huo}",String.valueOf(t.getIsSendHuo()));
 		sql  = sql.replace("#{zdhdmc}",String.valueOf(t.getZdhdmc()));
 		sql  = sql.replace("#{relation}",String.valueOf(t.getRelation()));
 		sql  = sql.replace("#{people_file_url}",String.valueOf(t.getPeopleFileUrl()));
 		sql  = sql.replace("#{people_file}",String.valueOf(t.getPeopleFile()));
 		sql  = sql.replace("#{zl_file_url}",String.valueOf(t.getZlFileUrl()));
 		sql  = sql.replace("#{zl_file}",String.valueOf(t.getZlFile()));
 		sql  = sql.replace("#{check_huo_file_url}",String.valueOf(t.getCheckHuoFileUrl()));
 		sql  = sql.replace("#{check_huo_file}",String.valueOf(t.getCheckHuoFile()));
 		sql  = sql.replace("#{test_file_url}",String.valueOf(t.getTestFileUrl()));
 		sql  = sql.replace("#{test_file}",String.valueOf(t.getTestFile()));
 		sql  = sql.replace("#{pact_file_url}",String.valueOf(t.getPactFileUrl()));
 		sql  = sql.replace("#{pact_file}",String.valueOf(t.getPactFile()));
 		sql  = sql.replace("#{fk_file_url}",String.valueOf(t.getFkFileUrl()));
 		sql  = sql.replace("#{fk_file}",String.valueOf(t.getFkFile()));
 		sql  = sql.replace("#{sample_file_url}",String.valueOf(t.getSampleFileUrl()));
 		sql  = sql.replace("#{sample_file}",String.valueOf(t.getSampleFile()));
 		sql  = sql.replace("#{send_huo_file_url}",String.valueOf(t.getSendHuoFileUrl()));
 		sql  = sql.replace("#{send_huo_file}",String.valueOf(t.getSendHuoFile()));
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
					javaInter.execute("emk_custom_use",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}