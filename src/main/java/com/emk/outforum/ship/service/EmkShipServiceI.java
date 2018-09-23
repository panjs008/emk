package com.emk.outforum.ship.service;
import com.emk.outforum.ship.entity.EmkShipEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkShipServiceI extends CommonService{
	
 	public void delete(EmkShipEntity entity) throws Exception;
 	
 	public Serializable save(EmkShipEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkShipEntity entity) throws Exception;
 	
}
