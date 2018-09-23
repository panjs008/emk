package com.emk.caiwu.financereceivable.service.impl;
import com.emk.caiwu.financereceivable.service.EmkFinanceReceivableServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.caiwu.financereceivable.entity.EmkFinanceReceivableEntity;
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

@Service("emkFinanceReceivableService")
@Transactional
public class EmkFinanceReceivableServiceImpl extends CommonServiceImpl implements EmkFinanceReceivableServiceI {

	
 	public void delete(EmkFinanceReceivableEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkFinanceReceivableEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkFinanceReceivableEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkFinanceReceivableEntity t) throws Exception{
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
	private void doUpdateBus(EmkFinanceReceivableEntity t) throws Exception{
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
	private void doDelBus(EmkFinanceReceivableEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkFinanceReceivableEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
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
		map.put("sum_total", t.getSumTotal());
		map.put("sum_money", t.getSumMoney());
		map.put("sum_box_total", t.getSumBoxTotal());
		map.put("sum_box_volume", t.getSumBoxVolume());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("receive_num", t.getReceiveNum());
		map.put("out_fourm_no", t.getOutFourmNo());
		map.put("out_forrm_date", t.getOutForrmDate());
		map.put("cwer", t.getCwer());
		map.put("cargo_no", t.getCargoNo());
		map.put("cargo_date", t.getCargoDate());
		map.put("leveal_factory_no", t.getLevealFactoryNo());
		map.put("leveal_date", t.getLevealDate());
		map.put("order_no", t.getOrderNo());
		map.put("sker", t.getSker());
		map.put("bank_account", t.getBankAccount());
		map.put("bank_name", t.getBankName());
		map.put("pay_type", t.getPayType());
		map.put("sk_date", t.getSkDate());
		map.put("fk_state", t.getFkState());
		map.put("fk_date", t.getFkDate());
		map.put("state", t.getState());
		map.put("type", t.getType());
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkFinanceReceivableEntity t){
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
 		sql  = sql.replace("#{sum_total}",String.valueOf(t.getSumTotal()));
 		sql  = sql.replace("#{sum_money}",String.valueOf(t.getSumMoney()));
 		sql  = sql.replace("#{sum_box_total}",String.valueOf(t.getSumBoxTotal()));
 		sql  = sql.replace("#{sum_box_volume}",String.valueOf(t.getSumBoxVolume()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{receive_num}",String.valueOf(t.getReceiveNum()));
 		sql  = sql.replace("#{out_fourm_no}",String.valueOf(t.getOutFourmNo()));
 		sql  = sql.replace("#{out_forrm_date}",String.valueOf(t.getOutForrmDate()));
 		sql  = sql.replace("#{cwer}",String.valueOf(t.getCwer()));
 		sql  = sql.replace("#{cargo_no}",String.valueOf(t.getCargoNo()));
 		sql  = sql.replace("#{cargo_date}",String.valueOf(t.getCargoDate()));
 		sql  = sql.replace("#{leveal_factory_no}",String.valueOf(t.getLevealFactoryNo()));
 		sql  = sql.replace("#{leveal_date}",String.valueOf(t.getLevealDate()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{sker}",String.valueOf(t.getSker()));
 		sql  = sql.replace("#{bank_account}",String.valueOf(t.getBankAccount()));
 		sql  = sql.replace("#{bank_name}",String.valueOf(t.getBankName()));
 		sql  = sql.replace("#{pay_type}",String.valueOf(t.getPayType()));
 		sql  = sql.replace("#{sk_date}",String.valueOf(t.getSkDate()));
 		sql  = sql.replace("#{fk_state}",String.valueOf(t.getFkState()));
 		sql  = sql.replace("#{fk_date}",String.valueOf(t.getFkDate()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
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
					javaInter.execute("emk_finance_receivable",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}