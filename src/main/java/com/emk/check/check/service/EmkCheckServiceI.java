package com.emk.check.check.service;
import com.emk.check.check.entity.EmkCheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkCheckServiceI extends CommonService{
	
 	public void delete(EmkCheckEntity entity) throws Exception;
 	
 	public Serializable save(EmkCheckEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkCheckEntity entity) throws Exception;
 	
}
