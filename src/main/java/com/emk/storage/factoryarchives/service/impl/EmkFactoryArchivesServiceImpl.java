package com.emk.storage.factoryarchives.service.impl;
import com.emk.storage.factoryarchives.service.EmkFactoryArchivesServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
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

@Service("emkFactoryArchivesService")
@Transactional
public class EmkFactoryArchivesServiceImpl extends CommonServiceImpl implements EmkFactoryArchivesServiceI {

	
 	public void delete(EmkFactoryArchivesEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(EmkFactoryArchivesEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(EmkFactoryArchivesEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(EmkFactoryArchivesEntity t) throws Exception{
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
	private void doUpdateBus(EmkFactoryArchivesEntity t) throws Exception{
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
	private void doDelBus(EmkFactoryArchivesEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(EmkFactoryArchivesEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("pro_type", t.getProType());
		map.put("sumbit_date", t.getSumbitDate());
		map.put("company_name_en", t.getCompanyNameEn());
		map.put("company_name_zn", t.getCompanyNameZn());
		map.put("address_en", t.getAddressEn());
		map.put("address_zn", t.getAddressZn());
		map.put("other_address", t.getOtherAddress());
		map.put("location_documents", t.getLocationDocuments());
		map.put("primary_contact", t.getPrimaryContact());
		map.put("primary_contact_email", t.getPrimaryContactEmail());
		map.put("primary_contact_tel", t.getPrimaryContactTel());
		map.put("secondary_contact", t.getSecondaryContact());
		map.put("secondary_contact_email", t.getSecondaryContactEmail());
		map.put("secondary_contact_tel", t.getSecondaryContactTel());
		map.put("year_established", t.getYearEstablished());
		map.put("license_number", t.getLicenseNumber());
		map.put("bank_name", t.getBankName());
		map.put("bank_account", t.getBankAccount());
		map.put("permit_ssued", t.getPermitSsued());
		map.put("permit_expiration_date", t.getPermitExpirationDate());
		map.put("facility_business_license", t.getFacilityBusinessLicense());
		map.put("production_process", t.getProductionProcess());
		map.put("full_production", t.getFullProduction());
		map.put("product_classification", t.getProductClassification());
		map.put("facility_land_size", t.getFacilityLandSize());
		map.put("facility_foor_size", t.getFacilityFoorSize());
		map.put("production", t.getProduction());
		map.put("warehouse", t.getWarehouse());
		map.put("dormitory", t.getDormitory());
		map.put("other_specify", t.getOtherSpecify());
		map.put("provide_name_provider", t.getProvideNameProvider());
		map.put("permanent_employee", t.getPermanentEmployee());
		map.put("temporary", t.getTemporary());
		map.put("migrant_labour", t.getMigrantLabour());
		map.put("home_workers", t.getHomeWorkers());
		map.put("female", t.getFemale());
		map.put("male", t.getMale());
		map.put("production_employees", t.getProductionEmployees());
		map.put("admin_staf", t.getAdminStaf());
		map.put("management", t.getManagement());
		map.put("language1", t.getLanguage1());
		map.put("language2", t.getLanguage2());
		map.put("zzs_pre1", t.getZzsPre1());
		map.put("zzs_pre2", t.getZzsPre2());
		map.put("mlanguage1", t.getMlanguage1());
		map.put("mlanguage2", t.getMlanguage2());
		map.put("level", t.getLevel());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,EmkFactoryArchivesEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{pro_type}",String.valueOf(t.getProType()));
 		sql  = sql.replace("#{sumbit_date}",String.valueOf(t.getSumbitDate()));
 		sql  = sql.replace("#{company_name_en}",String.valueOf(t.getCompanyNameEn()));
 		sql  = sql.replace("#{company_name_zn}",String.valueOf(t.getCompanyNameZn()));
 		sql  = sql.replace("#{address_en}",String.valueOf(t.getAddressEn()));
 		sql  = sql.replace("#{address_zn}",String.valueOf(t.getAddressZn()));
 		sql  = sql.replace("#{other_address}",String.valueOf(t.getOtherAddress()));
 		sql  = sql.replace("#{location_documents}",String.valueOf(t.getLocationDocuments()));
 		sql  = sql.replace("#{primary_contact}",String.valueOf(t.getPrimaryContact()));
 		sql  = sql.replace("#{primary_contact_email}",String.valueOf(t.getPrimaryContactEmail()));
 		sql  = sql.replace("#{primary_contact_tel}",String.valueOf(t.getPrimaryContactTel()));
 		sql  = sql.replace("#{secondary_contact}",String.valueOf(t.getSecondaryContact()));
 		sql  = sql.replace("#{secondary_contact_email}",String.valueOf(t.getSecondaryContactEmail()));
 		sql  = sql.replace("#{secondary_contact_tel}",String.valueOf(t.getSecondaryContactTel()));
 		sql  = sql.replace("#{year_established}",String.valueOf(t.getYearEstablished()));
 		sql  = sql.replace("#{license_number}",String.valueOf(t.getLicenseNumber()));
 		sql  = sql.replace("#{bank_name}",String.valueOf(t.getBankName()));
 		sql  = sql.replace("#{bank_account}",String.valueOf(t.getBankAccount()));
 		sql  = sql.replace("#{permit_ssued}",String.valueOf(t.getPermitSsued()));
 		sql  = sql.replace("#{permit_expiration_date}",String.valueOf(t.getPermitExpirationDate()));
 		sql  = sql.replace("#{facility_business_license}",String.valueOf(t.getFacilityBusinessLicense()));
 		sql  = sql.replace("#{production_process}",String.valueOf(t.getProductionProcess()));
 		sql  = sql.replace("#{full_production}",String.valueOf(t.getFullProduction()));
 		sql  = sql.replace("#{product_classification}",String.valueOf(t.getProductClassification()));
 		sql  = sql.replace("#{facility_land_size}",String.valueOf(t.getFacilityLandSize()));
 		sql  = sql.replace("#{facility_foor_size}",String.valueOf(t.getFacilityFoorSize()));
 		sql  = sql.replace("#{production}",String.valueOf(t.getProduction()));
 		sql  = sql.replace("#{warehouse}",String.valueOf(t.getWarehouse()));
 		sql  = sql.replace("#{dormitory}",String.valueOf(t.getDormitory()));
 		sql  = sql.replace("#{other_specify}",String.valueOf(t.getOtherSpecify()));
 		sql  = sql.replace("#{provide_name_provider}",String.valueOf(t.getProvideNameProvider()));
 		sql  = sql.replace("#{permanent_employee}",String.valueOf(t.getPermanentEmployee()));
 		sql  = sql.replace("#{temporary}",String.valueOf(t.getTemporary()));
 		sql  = sql.replace("#{migrant_labour}",String.valueOf(t.getMigrantLabour()));
 		sql  = sql.replace("#{home_workers}",String.valueOf(t.getHomeWorkers()));
 		sql  = sql.replace("#{female}",String.valueOf(t.getFemale()));
 		sql  = sql.replace("#{male}",String.valueOf(t.getMale()));
 		sql  = sql.replace("#{production_employees}",String.valueOf(t.getProductionEmployees()));
 		sql  = sql.replace("#{admin_staf}",String.valueOf(t.getAdminStaf()));
 		sql  = sql.replace("#{management}",String.valueOf(t.getManagement()));
 		sql  = sql.replace("#{language1}",String.valueOf(t.getLanguage1()));
 		sql  = sql.replace("#{language2}",String.valueOf(t.getLanguage2()));
 		sql  = sql.replace("#{zzs_pre1}",String.valueOf(t.getZzsPre1()));
 		sql  = sql.replace("#{zzs_pre2}",String.valueOf(t.getZzsPre2()));
 		sql  = sql.replace("#{mlanguage1}",String.valueOf(t.getMlanguage1()));
 		sql  = sql.replace("#{mlanguage2}",String.valueOf(t.getMlanguage2()));
 		sql  = sql.replace("#{level}",String.valueOf(t.getLevel()));
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
					javaInter.execute("emk_factory_archives",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}