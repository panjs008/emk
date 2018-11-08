package com.emk.storage.checkfactory.service;
import com.emk.storage.checkfactory.entity.EmkCheckFactoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkCheckFactoryServiceI extends CommonService{
	
 	public void delete(EmkCheckFactoryEntity entity) throws Exception;
 	
 	public Serializable save(EmkCheckFactoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkCheckFactoryEntity entity) throws Exception;
 	
}
