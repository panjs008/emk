package com.emk.bound.minstorage.service;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkMInStorageServiceI extends CommonService{
	
 	public void delete(EmkMInStorageEntity entity) throws Exception;
 	
 	public Serializable save(EmkMInStorageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkMInStorageEntity entity) throws Exception;
 	
}
