package com.emk.bill.bzgyd.service;
import com.emk.bill.bzgyd.entity.EmkBzgydEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkBzgydServiceI extends CommonService{
	
 	public void delete(EmkBzgydEntity entity) throws Exception;
 	
 	public Serializable save(EmkBzgydEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkBzgydEntity entity) throws Exception;
 	
}
