package com.emk.produce.testprogress.service;
import com.emk.produce.testprogress.entity.EmkTestProgressEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkTestProgressServiceI extends CommonService{
	
 	public void delete(EmkTestProgressEntity entity) throws Exception;
 	
 	public Serializable save(EmkTestProgressEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkTestProgressEntity entity) throws Exception;
 	
}
