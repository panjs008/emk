package com.emk.produce.testcost.service.impl;
import com.emk.produce.testcost.service.EmkTestCostServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.produce.testcost.entity.EmkTestCostEntity;
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

@Service("emkTestCostService")
@Transactional
public class EmkTestCostServiceImpl extends CommonServiceImpl implements EmkTestCostServiceI {

	
 	public void delete(EmkTestCostEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkTestCostEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkTestCostEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkTestCostEntity t) throws Exception{
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
	private void doUpdateBus(EmkTestCostEntity t) throws Exception{
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
	private void doDelBus(EmkTestCostEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkTestCostEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("businesser", t.getBusinesser());
		map.put("businesser_name", t.getBusinesserName());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());
		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("developer", t.getDeveloper());
		map.put("developer_name", t.getDeveloperName());
		map.put("sample_no", t.getSampleNo());
		map.put("sample_no_desc", t.getSampleNoDesc());
		map.put("order_no", t.getOrderNo());
		map.put("produce_num", t.getProduceNum());
		map.put("cost_no", t.getCostNo());
		map.put("test_no", t.getTestNo());
		map.put("kd_date", t.getKdDate());
		map.put("csjgmc", t.getCsjgmc());
		map.put("csjgdm", t.getCsjgdm());
		map.put("cus_num", t.getCusNum());
		map.put("cus_name", t.getCusName());
		map.put("total", t.getTotal());
		map.put("test_type", t.getTestType());
		map.put("test_content", t.getTestContent());
		map.put("limit_date", t.getLimitDate());
		map.put("test_result", t.getTestResult());
		map.put("cost_state", t.getCostState());
		map.put("cost_money", t.getCostMoney());
		map.put("skdw", t.getSkdw());
		map.put("bank_name", t.getBankName());
		map.put("bank_account", t.getBankAccount());
		map.put("telphone", t.getTelphone());
		map.put("fp_state", t.getFpState());
		map.put("fp_no", t.getFpNo());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkTestCostEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{businesser}",String.valueOf(t.getBusinesser()));
 		sql  = sql.replace("#{businesser_name}",String.valueOf(t.getBusinesserName()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));
 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{developer}",String.valueOf(t.getDeveloper()));
 		sql  = sql.replace("#{developer_name}",String.valueOf(t.getDeveloperName()));
 		sql  = sql.replace("#{sample_no}",String.valueOf(t.getSampleNo()));
 		sql  = sql.replace("#{sample_no_desc}",String.valueOf(t.getSampleNoDesc()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{produce_num}",String.valueOf(t.getProduceNum()));
 		sql  = sql.replace("#{cost_no}",String.valueOf(t.getCostNo()));
 		sql  = sql.replace("#{test_no}",String.valueOf(t.getTestNo()));
 		sql  = sql.replace("#{kd_date}",String.valueOf(t.getKdDate()));
 		sql  = sql.replace("#{csjgmc}",String.valueOf(t.getCsjgmc()));
 		sql  = sql.replace("#{csjgdm}",String.valueOf(t.getCsjgdm()));
 		sql  = sql.replace("#{cus_num}",String.valueOf(t.getCusNum()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{total}",String.valueOf(t.getTotal()));
 		sql  = sql.replace("#{test_type}",String.valueOf(t.getTestType()));
 		sql  = sql.replace("#{test_content}",String.valueOf(t.getTestContent()));
 		sql  = sql.replace("#{limit_date}",String.valueOf(t.getLimitDate()));
 		sql  = sql.replace("#{test_result}",String.valueOf(t.getTestResult()));
 		sql  = sql.replace("#{cost_state}",String.valueOf(t.getCostState()));
 		sql  = sql.replace("#{cost_money}",String.valueOf(t.getCostMoney()));
 		sql  = sql.replace("#{skdw}",String.valueOf(t.getSkdw()));
 		sql  = sql.replace("#{bank_name}",String.valueOf(t.getBankName()));
 		sql  = sql.replace("#{bank_account}",String.valueOf(t.getBankAccount()));
 		sql  = sql.replace("#{telphone}",String.valueOf(t.getTelphone()));
 		sql  = sql.replace("#{fp_state}",String.valueOf(t.getFpState()));
 		sql  = sql.replace("#{fp_no}",String.valueOf(t.getFpNo()));
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
					javaInter.execute("emk_test_cost",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}