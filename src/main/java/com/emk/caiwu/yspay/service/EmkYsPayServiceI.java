package com.emk.caiwu.yspay.service;
import com.emk.caiwu.yspay.entity.EmkYsPayEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkYsPayServiceI extends CommonService{
	
 	public void delete(EmkYsPayEntity entity) throws Exception;
 	
 	public Serializable save(EmkYsPayEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkYsPayEntity entity) throws Exception;
 	
}
