package com.process.repair.service;
import com.process.repair.entity.URepairEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface URepairServiceI extends CommonService{
	
 	public void delete(URepairEntity entity) throws Exception;
 	
 	public Serializable save(URepairEntity entity) throws Exception;
 	
 	public void saveOrUpdate(URepairEntity entity) throws Exception;
 	
}
