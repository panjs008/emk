package com.emk.bound.minstoragedetail.service;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkMInStorageDetailServiceI extends CommonService{
	
 	public void delete(EmkMInStorageDetailEntity entity) throws Exception;
 	
 	public Serializable save(EmkMInStorageDetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkMInStorageDetailEntity entity) throws Exception;
 	
}
