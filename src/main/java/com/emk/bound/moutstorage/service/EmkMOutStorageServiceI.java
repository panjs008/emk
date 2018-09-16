package com.emk.bound.moutstorage.service;
import com.emk.bound.moutstorage.entity.EmkMOutStorageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkMOutStorageServiceI extends CommonService{
	
 	public void delete(EmkMOutStorageEntity entity) throws Exception;
 	
 	public Serializable save(EmkMOutStorageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkMOutStorageEntity entity) throws Exception;
 	
}
