package com.emk.outforum.tdhdcost.service;
import com.emk.outforum.tdhdcost.entity.EmkTdhdCostEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkTdhdCostServiceI extends CommonService{
	
 	public void delete(EmkTdhdCostEntity entity) throws Exception;
 	
 	public Serializable save(EmkTdhdCostEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkTdhdCostEntity entity) throws Exception;
 	
}
