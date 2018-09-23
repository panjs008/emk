package com.emk.outforum.passfactory.service;
import com.emk.outforum.passfactory.entity.EmkPassFactoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkPassFactoryServiceI extends CommonService{
	
 	public void delete(EmkPassFactoryEntity entity) throws Exception;
 	
 	public Serializable save(EmkPassFactoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkPassFactoryEntity entity) throws Exception;
 	
}
