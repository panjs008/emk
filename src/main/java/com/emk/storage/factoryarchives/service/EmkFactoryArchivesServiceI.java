package com.emk.storage.factoryarchives.service;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkFactoryArchivesServiceI extends CommonService{
	
 	public void delete(EmkFactoryArchivesEntity entity) throws Exception;
 	
 	public Serializable save(EmkFactoryArchivesEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkFactoryArchivesEntity entity) throws Exception;
 	
}
