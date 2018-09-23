package com.emk.outforum.fobbusiness.service;
import com.emk.outforum.fobbusiness.entity.EmkFobBusinessEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkFobBusinessServiceI extends CommonService{
	
 	public void delete(EmkFobBusinessEntity entity) throws Exception;
 	
 	public Serializable save(EmkFobBusinessEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkFobBusinessEntity entity) throws Exception;
 	
}
