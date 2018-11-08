package com.emk.produce.ssysycy.service;
import com.emk.produce.ssysycy.entity.EmkSsysycyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkSsysycyServiceI extends CommonService{
	
 	public void delete(EmkSsysycyEntity entity) throws Exception;
 	
 	public Serializable save(EmkSsysycyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkSsysycyEntity entity) throws Exception;
 	
}
