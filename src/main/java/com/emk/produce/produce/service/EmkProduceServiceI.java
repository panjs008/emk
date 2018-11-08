package com.emk.produce.produce.service;
import com.emk.produce.produce.entity.EmkProduceEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkProduceServiceI extends CommonService{
	
 	public void delete(EmkProduceEntity entity) throws Exception;
 	
 	public Serializable save(EmkProduceEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkProduceEntity entity) throws Exception;
 	
}
