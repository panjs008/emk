package com.emk.outforum.tdhdcost.service.impl;
import com.emk.outforum.tdhdcost.service.EmkTdhdCostServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.outforum.tdhdcost.entity.EmkTdhdCostEntity;
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

@Service("emkTdhdCostService")
@Transactional
public class EmkTdhdCostServiceImpl extends CommonServiceImpl implements EmkTdhdCostServiceI {

	
 	public void delete(EmkTdhdCostEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkTdhdCostEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkTdhdCostEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkTdhdCostEntity t) throws Exception{
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
	private void doUpdateBus(EmkTdhdCostEntity t) throws Exception{
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
	private void doDelBus(EmkTdhdCostEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkTdhdCostEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("businesser", t.getBusinesser());
		map.put("businesser_name", t.getBusinesserName());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());
		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("developer", t.getDeveloper());
		map.put("developer_name", t.getDeveloperName());
		map.put("sample_no_desc", t.getSampleNoDesc());
		map.put("price", t.getPrice());
		map.put("sum_box_total", t.getSumBoxTotal());
		map.put("sum_box_volume", t.getSumBoxVolume());
		map.put("sum_box_jz", t.getSumBoxJz());
		map.put("sum_box_mao", t.getSumBoxMao());
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("cwyer", t.getCwyer());
		map.put("cargo_no", t.getCargoNo());
		map.put("leveal_factory_no", t.getLevealFactoryNo());
		map.put("cus_num", t.getCusNum());
		map.put("cus_name", t.getCusName());
		map.put("gys", t.getGys());
		map.put("gys_code", t.getGysCode());
		map.put("leveal_date", t.getLevealDate());
		map.put("out_forum_no", t.getOutForumNo());
		map.put("total", t.getTotal());
		map.put("ht_num", t.getHtNum());
		map.put("td_num", t.getTdNum());
		map.put("td_state", t.getTdState());
		map.put("hdfy_fp", t.getHdfyFp());
		map.put("cost", t.getCost());
		map.put("pay_state", t.getPayState());
		map.put("hd_name", t.getHdName());
		map.put("hd_code", t.getHdCode());
		map.put("bgzt", t.getBgzt());
		map.put("hgfxzt", t.getHgfxzt());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkTdhdCostEntity t){
 		sql  = sql.replace("#{businesser}",String.valueOf(t.getBusinesser()));
 		sql  = sql.replace("#{businesser_name}",String.valueOf(t.getBusinesserName()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));
 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{developer}",String.valueOf(t.getDeveloper()));
 		sql  = sql.replace("#{developer_name}",String.valueOf(t.getDeveloperName()));
 		sql  = sql.replace("#{sample_no_desc}",String.valueOf(t.getSampleNoDesc()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{sum_box_total}",String.valueOf(t.getSumBoxTotal()));
 		sql  = sql.replace("#{sum_box_volume}",String.valueOf(t.getSumBoxVolume()));
 		sql  = sql.replace("#{sum_box_jz}",String.valueOf(t.getSumBoxJz()));
 		sql  = sql.replace("#{sum_box_mao}",String.valueOf(t.getSumBoxMao()));
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{cwyer}",String.valueOf(t.getCwyer()));
 		sql  = sql.replace("#{cargo_no}",String.valueOf(t.getCargoNo()));
 		sql  = sql.replace("#{leveal_factory_no}",String.valueOf(t.getLevealFactoryNo()));
 		sql  = sql.replace("#{cus_num}",String.valueOf(t.getCusNum()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{gys}",String.valueOf(t.getGys()));
 		sql  = sql.replace("#{gys_code}",String.valueOf(t.getGysCode()));
 		sql  = sql.replace("#{leveal_date}",String.valueOf(t.getLevealDate()));
 		sql  = sql.replace("#{out_forum_no}",String.valueOf(t.getOutForumNo()));
 		sql  = sql.replace("#{total}",String.valueOf(t.getTotal()));
 		sql  = sql.replace("#{ht_num}",String.valueOf(t.getHtNum()));
 		sql  = sql.replace("#{td_num}",String.valueOf(t.getTdNum()));
 		sql  = sql.replace("#{td_state}",String.valueOf(t.getTdState()));
 		sql  = sql.replace("#{hdfy_fp}",String.valueOf(t.getHdfyFp()));
 		sql  = sql.replace("#{cost}",String.valueOf(t.getCost()));
 		sql  = sql.replace("#{pay_state}",String.valueOf(t.getPayState()));
 		sql  = sql.replace("#{hd_name}",String.valueOf(t.getHdName()));
 		sql  = sql.replace("#{hd_code}",String.valueOf(t.getHdCode()));
 		sql  = sql.replace("#{bgzt}",String.valueOf(t.getBgzt()));
 		sql  = sql.replace("#{hgfxzt}",String.valueOf(t.getHgfxzt()));
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
					javaInter.execute("emk_tdhd_cost",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}