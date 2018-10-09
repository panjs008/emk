package com.emk.workorder.workorder.service;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkWorkOrderServiceI extends CommonService{
	
 	public void delete(EmkWorkOrderEntity entity) throws Exception;
 	
 	public Serializable save(EmkWorkOrderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkWorkOrderEntity entity) throws Exception;
 	
}
