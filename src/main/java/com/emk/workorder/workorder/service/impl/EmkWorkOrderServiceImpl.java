package com.emk.workorder.workorder.service.impl;
import com.emk.workorder.workorder.service.EmkWorkOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
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

@Service("emkWorkOrderService")
@Transactional
public class EmkWorkOrderServiceImpl extends CommonServiceImpl implements EmkWorkOrderServiceI {

	
 	public void delete(EmkWorkOrderEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkWorkOrderEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkWorkOrderEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkWorkOrderEntity t) throws Exception{
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
	private void doUpdateBus(EmkWorkOrderEntity t) throws Exception{
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
	private void doDelBus(EmkWorkOrderEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkWorkOrderEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("ask_no", t.getAskNo());
		map.put("is_print", t.getIsPrint());
		map.put("ask_date", t.getAskDate());
		map.put("process_name", t.getProcessName());
		map.put("ask_work_user", t.getAskWorkUser());
		map.put("ask_work_user_id", t.getAskWorkUserId());
		map.put("ask_work_date", t.getAskWorkDate());
		map.put("sample_num", t.getSampleNum());
		map.put("sample_user", t.getSampleUser());
		map.put("sample_date", t.getSampleDate());
		map.put("sample_check_user", t.getSampleCheckUser());
		map.put("sample_check_date", t.getSampleCheckDate());
		map.put("sample_check_advice", t.getSampleCheckAdvice());
		map.put("sample_advice", t.getSampleAdvice());
		map.put("is_pass", t.getIsPass());
		map.put("order_user", t.getOrderUser());
		map.put("order_user_id", t.getOrderUserId());
		map.put("order_no", t.getOrderNo());
		map.put("order_date", t.getOrderDate());
		map.put("order_advice", t.getOrderAdvice());
		map.put("sample_check_user_id", t.getSampleCheckUserId());
		map.put("sample_user_id", t.getSampleUserId());
		map.put("ht_user", t.getHtUser());
		map.put("ht_user_id", t.getHtUserId());
		map.put("ht_no", t.getHtNo());
		map.put("ht_advice", t.getHtAdvice());
		map.put("produce_user", t.getProduceUser());
		map.put("produce_user_id", t.getProduceUserId());
		map.put("produce_date", t.getProduceDate());
		map.put("ht_date", t.getHtDate());
		map.put("produce_advice", t.getProduceAdvice());
		map.put("check_user", t.getCheckUser());
		map.put("check_user_id", t.getCheckUserId());
		map.put("check_date", t.getCheckDate());
		map.put("check_advice", t.getCheckAdvice());
		map.put("is_pass_check", t.getIsPassCheck());
		map.put("out_user", t.getOutUser());
		map.put("out_user_id", t.getOutUserId());
		map.put("out_date", t.getOutDate());
		map.put("out_advice", t.getOutAdvice());
		map.put("caiwu_user", t.getCaiwuUser());
		map.put("caiwu_user_id", t.getCaiwuUserId());
		map.put("caiwu_date", t.getCaiwuDate());
		map.put("caiwu_advice", t.getCaiwuAdvice());
		map.put("state", t.getState());
		map.put("work_no", t.getWorkNo());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkWorkOrderEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{ask_no}",String.valueOf(t.getAskNo()));
 		sql  = sql.replace("#{is_print}",String.valueOf(t.getIsPrint()));
 		sql  = sql.replace("#{ask_date}",String.valueOf(t.getAskDate()));
 		sql  = sql.replace("#{process_name}",String.valueOf(t.getProcessName()));
 		sql  = sql.replace("#{ask_work_user}",String.valueOf(t.getAskWorkUser()));
 		sql  = sql.replace("#{ask_work_user_id}",String.valueOf(t.getAskWorkUserId()));
 		sql  = sql.replace("#{ask_work_date}",String.valueOf(t.getAskWorkDate()));
 		sql  = sql.replace("#{sample_num}",String.valueOf(t.getSampleNum()));
 		sql  = sql.replace("#{sample_user}",String.valueOf(t.getSampleUser()));
 		sql  = sql.replace("#{sample_date}",String.valueOf(t.getSampleDate()));
 		sql  = sql.replace("#{sample_check_user}",String.valueOf(t.getSampleCheckUser()));
 		sql  = sql.replace("#{sample_check_date}",String.valueOf(t.getSampleCheckDate()));
 		sql  = sql.replace("#{sample_check_advice}",String.valueOf(t.getSampleCheckAdvice()));
 		sql  = sql.replace("#{sample_advice}",String.valueOf(t.getSampleAdvice()));
 		sql  = sql.replace("#{is_pass}",String.valueOf(t.getIsPass()));
 		sql  = sql.replace("#{order_user}",String.valueOf(t.getOrderUser()));
 		sql  = sql.replace("#{order_user_id}",String.valueOf(t.getOrderUserId()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{order_date}",String.valueOf(t.getOrderDate()));
 		sql  = sql.replace("#{order_advice}",String.valueOf(t.getOrderAdvice()));
 		sql  = sql.replace("#{sample_check_user_id}",String.valueOf(t.getSampleCheckUserId()));
 		sql  = sql.replace("#{sample_user_id}",String.valueOf(t.getSampleUserId()));
 		sql  = sql.replace("#{ht_user}",String.valueOf(t.getHtUser()));
 		sql  = sql.replace("#{ht_user_id}",String.valueOf(t.getHtUserId()));
 		sql  = sql.replace("#{ht_no}",String.valueOf(t.getHtNo()));
 		sql  = sql.replace("#{ht_advice}",String.valueOf(t.getHtAdvice()));
 		sql  = sql.replace("#{produce_user}",String.valueOf(t.getProduceUser()));
 		sql  = sql.replace("#{produce_user_id}",String.valueOf(t.getProduceUserId()));
 		sql  = sql.replace("#{produce_date}",String.valueOf(t.getProduceDate()));
 		sql  = sql.replace("#{ht_date}",String.valueOf(t.getHtDate()));
 		sql  = sql.replace("#{produce_advice}",String.valueOf(t.getProduceAdvice()));
 		sql  = sql.replace("#{check_user}",String.valueOf(t.getCheckUser()));
 		sql  = sql.replace("#{check_user_id}",String.valueOf(t.getCheckUserId()));
 		sql  = sql.replace("#{check_date}",String.valueOf(t.getCheckDate()));
 		sql  = sql.replace("#{check_advice}",String.valueOf(t.getCheckAdvice()));
 		sql  = sql.replace("#{is_pass_check}",String.valueOf(t.getIsPassCheck()));
 		sql  = sql.replace("#{out_user}",String.valueOf(t.getOutUser()));
 		sql  = sql.replace("#{out_user_id}",String.valueOf(t.getOutUserId()));
 		sql  = sql.replace("#{out_date}",String.valueOf(t.getOutDate()));
 		sql  = sql.replace("#{out_advice}",String.valueOf(t.getOutAdvice()));
 		sql  = sql.replace("#{caiwu_user}",String.valueOf(t.getCaiwuUser()));
 		sql  = sql.replace("#{caiwu_user_id}",String.valueOf(t.getCaiwuUserId()));
 		sql  = sql.replace("#{caiwu_date}",String.valueOf(t.getCaiwuDate()));
 		sql  = sql.replace("#{caiwu_advice}",String.valueOf(t.getCaiwuAdvice()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{work_no}",String.valueOf(t.getWorkNo()));
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
					javaInter.execute("emk_work_order",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}