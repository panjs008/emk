package com.process.repairattch.service;
import com.process.repairattch.entity.URepairAttchEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface URepairAttchServiceI extends CommonService{
	
 	public void delete(URepairAttchEntity entity) throws Exception;
 	
 	public Serializable save(URepairAttchEntity entity) throws Exception;
 	
 	public void saveOrUpdate(URepairAttchEntity entity) throws Exception;
 	
}
