package com.emk.storage.customuse.service;
import com.emk.storage.customuse.entity.EmkCustomUseEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkCustomUseServiceI extends CommonService{
	
 	public void delete(EmkCustomUseEntity entity) throws Exception;
 	
 	public Serializable save(EmkCustomUseEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkCustomUseEntity entity) throws Exception;
 	
}
