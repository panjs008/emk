package com.emk.storage.pb.service;
import com.emk.storage.pb.entity.EmkPbEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkPbServiceI extends CommonService{
	
 	public void delete(EmkPbEntity entity) throws Exception;
 	
 	public Serializable save(EmkPbEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkPbEntity entity) throws Exception;
 	
}
