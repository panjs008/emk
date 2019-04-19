package com.emk.caiwu.dypay.service;
import com.emk.caiwu.dypay.entity.EmkDyPayEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkDyPayServiceI extends CommonService{
	
 	public void delete(EmkDyPayEntity entity) throws Exception;
 	
 	public Serializable save(EmkDyPayEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkDyPayEntity entity) throws Exception;
 	
}
