package com.emk.check.qualitycheck.service;
import com.emk.check.qualitycheck.entity.EmkQualityCheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkQualityCheckServiceI extends CommonService{
	
 	public void delete(EmkQualityCheckEntity entity) throws Exception;
 	
 	public Serializable save(EmkQualityCheckEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkQualityCheckEntity entity) throws Exception;
 	
}
