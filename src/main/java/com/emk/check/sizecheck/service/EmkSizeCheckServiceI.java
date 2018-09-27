package com.emk.check.sizecheck.service;
import com.emk.check.sizecheck.entity.EmkSizeCheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkSizeCheckServiceI extends CommonService{
	
 	public void delete(EmkSizeCheckEntity entity) throws Exception;
 	
 	public Serializable save(EmkSizeCheckEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkSizeCheckEntity entity) throws Exception;
 	
}
