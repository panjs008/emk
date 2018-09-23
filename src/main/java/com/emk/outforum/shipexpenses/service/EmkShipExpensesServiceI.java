package com.emk.outforum.shipexpenses.service;
import com.emk.outforum.shipexpenses.entity.EmkShipExpensesEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkShipExpensesServiceI extends CommonService{
	
 	public void delete(EmkShipExpensesEntity entity) throws Exception;
 	
 	public Serializable save(EmkShipExpensesEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkShipExpensesEntity entity) throws Exception;
 	
}
