package com.emk.bill.yjyhtime.service;
import com.emk.bill.yjyhtime.entity.EmkYjyhTimeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkYjyhTimeServiceI extends CommonService{
	
 	public void delete(EmkYjyhTimeEntity entity) throws Exception;
 	
 	public Serializable save(EmkYjyhTimeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkYjyhTimeEntity entity) throws Exception;
 	
}
