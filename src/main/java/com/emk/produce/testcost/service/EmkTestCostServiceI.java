package com.emk.produce.testcost.service;
import com.emk.produce.testcost.entity.EmkTestCostEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkTestCostServiceI extends CommonService{
	
 	public void delete(EmkTestCostEntity entity) throws Exception;
 	
 	public Serializable save(EmkTestCostEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkTestCostEntity entity) throws Exception;
 	
}
