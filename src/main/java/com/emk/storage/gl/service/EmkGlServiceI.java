package com.emk.storage.gl.service;
import com.emk.storage.gl.entity.EmkGlEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkGlServiceI extends CommonService{
	
 	public void delete(EmkGlEntity entity) throws Exception;
 	
 	public Serializable save(EmkGlEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkGlEntity entity) throws Exception;
 	
}
