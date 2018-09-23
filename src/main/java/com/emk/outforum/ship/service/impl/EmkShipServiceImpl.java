package com.emk.outforum.ship.service.impl;
import com.emk.outforum.ship.service.EmkShipServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.outforum.ship.entity.EmkShipEntity;
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

@Service("emkShipService")
@Transactional
public class EmkShipServiceImpl extends CommonServiceImpl implements EmkShipServiceI {

	
 	public void delete(EmkShipEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkShipEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkShipEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkShipEntity t) throws Exception{
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
	private void doUpdateBus(EmkShipEntity t) throws Exception{
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
	private void doDelBus(EmkShipEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkShipEntity t){
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
		map.put("cwer", t.getCwer());
		map.put("price", t.getPrice());
		map.put("sum_box_total", t.getSumBoxTotal());
		map.put("sum_box_volume", t.getSumBoxVolume());
		map.put("sum_box_jz", t.getSumBoxJz());
		map.put("sum_box_mao", t.getSumBoxMao());
		map.put("cargo_no", t.getCargoNo());
		map.put("out_forum_no", t.getOutForumNo());
		map.put("leveal_factory_no", t.getLevealFactoryNo());
		map.put("gys", t.getGys());
		map.put("gys_code", t.getGysCode());
		map.put("cus_num", t.getCusNum());
		map.put("cus_name", t.getCusName());
		map.put("leveal_date", t.getLevealDate());
		map.put("order_no", t.getOrderNo());
		map.put("ht_num", t.getHtNum());
		map.put("ship_desc", t.getShipDesc());
		map.put("total", t.getTotal());
		map.put("td_num", t.getTdNum());
		map.put("td_state", t.getTdState());
		map.put("fp_num", t.getFpNum());
		map.put("fp_state", t.getFpState());
		map.put("box_num", t.getBoxNum());
		map.put("box_state", t.getBoxState());
		map.put("arrvie_date", t.getArrvieDate());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkShipEntity t){
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
 		sql  = sql.replace("#{cwer}",String.valueOf(t.getCwer()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{sum_box_total}",String.valueOf(t.getSumBoxTotal()));
 		sql  = sql.replace("#{sum_box_volume}",String.valueOf(t.getSumBoxVolume()));
 		sql  = sql.replace("#{sum_box_jz}",String.valueOf(t.getSumBoxJz()));
 		sql  = sql.replace("#{sum_box_mao}",String.valueOf(t.getSumBoxMao()));
 		sql  = sql.replace("#{cargo_no}",String.valueOf(t.getCargoNo()));
 		sql  = sql.replace("#{out_forum_no}",String.valueOf(t.getOutForumNo()));
 		sql  = sql.replace("#{leveal_factory_no}",String.valueOf(t.getLevealFactoryNo()));
 		sql  = sql.replace("#{gys}",String.valueOf(t.getGys()));
 		sql  = sql.replace("#{gys_code}",String.valueOf(t.getGysCode()));
 		sql  = sql.replace("#{cus_num}",String.valueOf(t.getCusNum()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{leveal_date}",String.valueOf(t.getLevealDate()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{ht_num}",String.valueOf(t.getHtNum()));
 		sql  = sql.replace("#{ship_desc}",String.valueOf(t.getShipDesc()));
 		sql  = sql.replace("#{total}",String.valueOf(t.getTotal()));
 		sql  = sql.replace("#{td_num}",String.valueOf(t.getTdNum()));
 		sql  = sql.replace("#{td_state}",String.valueOf(t.getTdState()));
 		sql  = sql.replace("#{fp_num}",String.valueOf(t.getFpNum()));
 		sql  = sql.replace("#{fp_state}",String.valueOf(t.getFpState()));
 		sql  = sql.replace("#{box_num}",String.valueOf(t.getBoxNum()));
 		sql  = sql.replace("#{box_state}",String.valueOf(t.getBoxState()));
 		sql  = sql.replace("#{arrvie_date}",String.valueOf(t.getArrvieDate()));
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
					javaInter.execute("emk_ship",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}