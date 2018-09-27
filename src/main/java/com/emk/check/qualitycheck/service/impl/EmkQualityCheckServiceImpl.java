package com.emk.check.qualitycheck.service.impl;
import com.emk.check.qualitycheck.service.EmkQualityCheckServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.check.qualitycheck.entity.EmkQualityCheckEntity;
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

@Service("emkQualityCheckService")
@Transactional
public class EmkQualityCheckServiceImpl extends CommonServiceImpl implements EmkQualityCheckServiceI {

	
 	public void delete(EmkQualityCheckEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkQualityCheckEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkQualityCheckEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkQualityCheckEntity t) throws Exception{
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
	private void doUpdateBus(EmkQualityCheckEntity t) throws Exception{
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
	private void doDelBus(EmkQualityCheckEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkQualityCheckEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("businesser_name", t.getBusinesserName());
		map.put("businesser", t.getBusinesser());
		map.put("tracer", t.getTracer());
		map.put("tracer_name", t.getTracerName());
		map.put("businesse_dept_name", t.getBusinesseDeptName());
		map.put("businesse_dept_id", t.getBusinesseDeptId());
		map.put("developer", t.getDeveloper());
		map.put("developer_name", t.getDeveloperName());
		map.put("gys", t.getGys());
		map.put("gys_code", t.getGysCode());
		map.put("factory_name", t.getFactoryName());
		map.put("address", t.getAddress());
		map.put("relationer", t.getRelationer());
		map.put("telphone", t.getTelphone());
		map.put("check_type", t.getCheckType());
		map.put("order_no", t.getOrderNo());
		map.put("sample_no", t.getSampleNo());
		map.put("sample_desc", t.getSampleDesc());
		map.put("color", t.getColor());
		map.put("size", t.getSize());
		map.put("total", t.getTotal());
		map.put("box_total", t.getBoxTotal());
		map.put("ht_num", t.getHtNum());
		map.put("version", t.getVersion());
		map.put("check_total", t.getCheckTotal());
		map.put("wuji", t.getWuji());
		map.put("youji", t.getYouji());
		map.put("qimao", t.getQimao());
		map.put("duans", t.getDuans());
		map.put("chous", t.getChous());
		map.put("fans", t.getFans());
		map.put("pod", t.getPod());
		map.put("hengw", t.getHengw());
		map.put("ranhua", t.getRanhua());
		map.put("secha", t.getSecha());
		map.put("psbl", t.getPsbl());
		map.put("baokou", t.getBaokou());
		map.put("xt", t.getXt());
		map.put("dx", t.getDx());
		map.put("lk", t.getLk());
		map.put("zybdc", t.getZybdc());
		map.put("tjbl", t.getTjbl());
		map.put("rongzhou", t.getRongzhou());
		map.put("zhikou", t.getZhikou());
		map.put("chicun", t.getChicun());
		map.put("mczs", t.getMczs());
		map.put("tgbl", t.getTgbl());
		map.put("gwfxcw", t.getGwfxcw());
		map.put("bxbl", t.getBxbl());
		map.put("xzbl", t.getXzbl());
		map.put("lwl", t.getLwl());
		map.put("zhenkong", t.getZhenkong());
		map.put("gcdp", t.getGcdp());
		map.put("cdtxm", t.getCdtxm());
		map.put("gpwzcw", t.getGpwzcw());
		map.put("yjyc", t.getYjyc());
		map.put("yjfxcw", t.getYjfxcw());
		map.put("gdgc", t.getGdgc());
		map.put("clms", t.getClms());
		map.put("jdncmcw", t.getJdncmcw());
		map.put("jdnpbcw", t.getJdnpbcw());
		map.put("zxzmhs", t.getZxzmhs());
		map.put("bzwgbl", t.getBzwgbl());
		map.put("txmbnsm", t.getTxmbnsm());
		map.put("dpbw", t.getDpbw());
		map.put("tzbw", t.getTzbw());
		map.put("bzzlby", t.getBzzlby());
		map.put("cyjg", t.getCyjg());
		map.put("quality_check_num", t.getQualityCheckNum());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkQualityCheckEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{businesser_name}",String.valueOf(t.getBusinesserName()));
 		sql  = sql.replace("#{businesser}",String.valueOf(t.getBusinesser()));
 		sql  = sql.replace("#{tracer}",String.valueOf(t.getTracer()));
 		sql  = sql.replace("#{tracer_name}",String.valueOf(t.getTracerName()));
 		sql  = sql.replace("#{businesse_dept_name}",String.valueOf(t.getBusinesseDeptName()));
 		sql  = sql.replace("#{businesse_dept_id}",String.valueOf(t.getBusinesseDeptId()));
 		sql  = sql.replace("#{developer}",String.valueOf(t.getDeveloper()));
 		sql  = sql.replace("#{developer_name}",String.valueOf(t.getDeveloperName()));
 		sql  = sql.replace("#{gys}",String.valueOf(t.getGys()));
 		sql  = sql.replace("#{gys_code}",String.valueOf(t.getGysCode()));
 		sql  = sql.replace("#{factory_name}",String.valueOf(t.getFactoryName()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{relationer}",String.valueOf(t.getRelationer()));
 		sql  = sql.replace("#{telphone}",String.valueOf(t.getTelphone()));
 		sql  = sql.replace("#{check_type}",String.valueOf(t.getCheckType()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{sample_no}",String.valueOf(t.getSampleNo()));
 		sql  = sql.replace("#{sample_desc}",String.valueOf(t.getSampleDesc()));
 		sql  = sql.replace("#{color}",String.valueOf(t.getColor()));
 		sql  = sql.replace("#{size}",String.valueOf(t.getSize()));
 		sql  = sql.replace("#{total}",String.valueOf(t.getTotal()));
 		sql  = sql.replace("#{box_total}",String.valueOf(t.getBoxTotal()));
 		sql  = sql.replace("#{ht_num}",String.valueOf(t.getHtNum()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{check_total}",String.valueOf(t.getCheckTotal()));
 		sql  = sql.replace("#{wuji}",String.valueOf(t.getWuji()));
 		sql  = sql.replace("#{youji}",String.valueOf(t.getYouji()));
 		sql  = sql.replace("#{qimao}",String.valueOf(t.getQimao()));
 		sql  = sql.replace("#{duans}",String.valueOf(t.getDuans()));
 		sql  = sql.replace("#{chous}",String.valueOf(t.getChous()));
 		sql  = sql.replace("#{fans}",String.valueOf(t.getFans()));
 		sql  = sql.replace("#{pod}",String.valueOf(t.getPod()));
 		sql  = sql.replace("#{hengw}",String.valueOf(t.getHengw()));
 		sql  = sql.replace("#{ranhua}",String.valueOf(t.getRanhua()));
 		sql  = sql.replace("#{secha}",String.valueOf(t.getSecha()));
 		sql  = sql.replace("#{psbl}",String.valueOf(t.getPsbl()));
 		sql  = sql.replace("#{baokou}",String.valueOf(t.getBaokou()));
 		sql  = sql.replace("#{xt}",String.valueOf(t.getXt()));
 		sql  = sql.replace("#{dx}",String.valueOf(t.getDx()));
 		sql  = sql.replace("#{lk}",String.valueOf(t.getLk()));
 		sql  = sql.replace("#{zybdc}",String.valueOf(t.getZybdc()));
 		sql  = sql.replace("#{tjbl}",String.valueOf(t.getTjbl()));
 		sql  = sql.replace("#{rongzhou}",String.valueOf(t.getRongzhou()));
 		sql  = sql.replace("#{zhikou}",String.valueOf(t.getZhikou()));
 		sql  = sql.replace("#{chicun}",String.valueOf(t.getChicun()));
 		sql  = sql.replace("#{mczs}",String.valueOf(t.getMczs()));
 		sql  = sql.replace("#{tgbl}",String.valueOf(t.getTgbl()));
 		sql  = sql.replace("#{gwfxcw}",String.valueOf(t.getGwfxcw()));
 		sql  = sql.replace("#{bxbl}",String.valueOf(t.getBxbl()));
 		sql  = sql.replace("#{xzbl}",String.valueOf(t.getXzbl()));
 		sql  = sql.replace("#{lwl}",String.valueOf(t.getLwl()));
 		sql  = sql.replace("#{zhenkong}",String.valueOf(t.getZhenkong()));
 		sql  = sql.replace("#{gcdp}",String.valueOf(t.getGcdp()));
 		sql  = sql.replace("#{cdtxm}",String.valueOf(t.getCdtxm()));
 		sql  = sql.replace("#{gpwzcw}",String.valueOf(t.getGpwzcw()));
 		sql  = sql.replace("#{yjyc}",String.valueOf(t.getYjyc()));
 		sql  = sql.replace("#{yjfxcw}",String.valueOf(t.getYjfxcw()));
 		sql  = sql.replace("#{gdgc}",String.valueOf(t.getGdgc()));
 		sql  = sql.replace("#{clms}",String.valueOf(t.getClms()));
 		sql  = sql.replace("#{jdncmcw}",String.valueOf(t.getJdncmcw()));
 		sql  = sql.replace("#{jdnpbcw}",String.valueOf(t.getJdnpbcw()));
 		sql  = sql.replace("#{zxzmhs}",String.valueOf(t.getZxzmhs()));
 		sql  = sql.replace("#{bzwgbl}",String.valueOf(t.getBzwgbl()));
 		sql  = sql.replace("#{txmbnsm}",String.valueOf(t.getTxmbnsm()));
 		sql  = sql.replace("#{dpbw}",String.valueOf(t.getDpbw()));
 		sql  = sql.replace("#{tzbw}",String.valueOf(t.getTzbw()));
 		sql  = sql.replace("#{bzzlby}",String.valueOf(t.getBzzlby()));
 		sql  = sql.replace("#{cyjg}",String.valueOf(t.getCyjg()));
 		sql  = sql.replace("#{quality_check_num}",String.valueOf(t.getQualityCheckNum()));
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
					javaInter.execute("emk_quality_check",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}